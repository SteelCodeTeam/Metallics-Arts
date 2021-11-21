package net.rudahee.metallics_arts.minecraft_objects.tile_entity;

import net.minecraft.block.BlockState;
import net.minecraft.crash.ReportedException;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.rudahee.metallics_arts.data.recipes.AlloyFurnaceRecipe;
import net.rudahee.metallics_arts.data.recipes.ModRecipeTypes;
import net.rudahee.metallics_arts.setup.ModItems;
import net.rudahee.metallics_arts.setup.enums.FuelsTime;
import net.rudahee.metallics_arts.setup.enums.MetalBurningRecipeData;
import org.jline.utils.Log;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Optional;

public class AlloyFurnaceTileEntity extends TileEntity implements ITickableTileEntity {

    private int actualFuelBurning = -1;
    private int maxFuelBurning = -1;
    private int actualTimeToActualRecipe = -1;
    private int maxTimeToActualRecipe = -1;

    private final ItemStackHandler itemHandler = createHandler();
    private final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);

    public AlloyFurnaceTileEntity(TileEntityType<?> p_i48289_1_) {
        super(p_i48289_1_);
    }

    public AlloyFurnaceTileEntity() {
        this(ModTileEntities.ALLOY_FURNACE_TILE_ENTITY.get());
    }

    @Override
    public void load(BlockState state, CompoundNBT nbt) {
        itemHandler.deserializeNBT(nbt.getCompound("inv"));
        super.load(state, nbt);
    }

    @Override
    public CompoundNBT save(CompoundNBT compound) {
        compound.put("inv", itemHandler.serializeNBT());
        return super.save(compound);
    }


    private ItemStackHandler createHandler() {
        return new ItemStackHandler(6) {
            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                switch (slot) {
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                    case 5:
                        return (Items.IRON_INGOT == stack.getItem()||
                                Items.GOLD_INGOT == stack.getItem()||
                                Items.COAL == stack.getItem() ||
                                ModItems.ITEM_METAL_INGOT.values().stream().anyMatch(m-> m == stack.getItem())||
                                ModItems.ITEM_GEMS_BASE.values().stream().anyMatch(m -> m == stack.getItem()));

                        //QUITAR LAS ALEACIONES DE LA LISTA
                    case 4:
                        return (Items.COAL_BLOCK.getItem() == stack.getItem()||
                                Items.COAL.getItem() == stack.getItem()||
                                Items.BLAZE_ROD.getItem() == stack.getItem()||
                                Items.DRIED_KELP_BLOCK.getItem() == stack.getItem()||
                                Items.LAVA_BUCKET.getItem() == stack.getItem());
                        // return Items.COAL_BLOCK.getItem() == stack.getItem() ? true : false;
                    default:
                        return false;
                }
            }

            @Override
            public int getSlotLimit(int slot) {
                return 64; // Slot limits
            }

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                if(!isItemValid(slot, stack)) {
                    return stack; // Only insert correct item
                }

                return super.insertItem(slot, stack, simulate);
            }
        };
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return handler.cast();
        }

        return super.getCapability(cap, side);
    }

    public void craft() {
        Inventory inv = new Inventory(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inv.setItem(i, itemHandler.getStackInSlot(i));
        }

        Optional<AlloyFurnaceRecipe> recipe = this.level.getRecipeManager().getRecipeFor(ModRecipeTypes.ALLOY_FURNACE_RECIPE,inv ,level);

        if(!recipe.isPresent()) {
            return;
        }

        ItemStack output = recipe.get().getResultItem();

        // ESTO NO ARREGLA EL PROBLEMA DEL ACERO
        if (output.getDescriptionId().equals(ModItems.ITEM_METAL_INGOT.get("steel"))) {
            if (itemHandler.getStackInSlot(0).isEmpty() || itemHandler.getStackInSlot(1).isEmpty()
                    || itemHandler.getStackInSlot(2).isEmpty() ||itemHandler.getStackInSlot(3).isEmpty()) {
                return;
            }
        }


        if (actualTimeToActualRecipe < 0) {
            try {
                actualTimeToActualRecipe = Arrays.stream(MetalBurningRecipeData.values())
                                        .filter(m -> m.getItem().getDescriptionId().equals(output.getItem().getDescriptionId()))
                                            .findFirst().get()
                                            .getTicksToCompleteBurning();
            } catch (ReportedException | NullPointerException ex) {
                Log.warn("Reported Exception encountered: Ticking Block Entity - Alloy Furnace Tile Entity: " + this.worldPosition.toString());
                actualTimeToActualRecipe = -1;
            }

            maxTimeToActualRecipe = actualTimeToActualRecipe;
        } else {

            if (actualTimeToActualRecipe == 0) {
                craftTheItem(output);
                actualTimeToActualRecipe--;
            } else{
                actualTimeToActualRecipe--;
            }
        }
    }

    private void craftTheItem(ItemStack output) {
        itemHandler.extractItem(0, 1, false);
        itemHandler.extractItem(1, 1, false);
        itemHandler.extractItem(2, 1, false);
        itemHandler.extractItem(3, 1, false);

        itemHandler.insertItem(5, output, false);
    }

    // REFACTOR BURNING TIME TO ONLY WITH ITEM
    @Override
    public void tick() {
        if (!this.level.isClientSide()) {
            if (actualFuelBurning < 0) {
                if (itemHandler.getStackInSlot(4).isEmpty()) {
                    return;
                } else {
                    Item fuelItem = itemHandler.extractItem(4, 1, false).getItem();

                    maxFuelBurning = Arrays.stream(FuelsTime.values())
                            .filter(f -> f.getItem().equals(fuelItem.getItem()))
                            .findFirst().get()
                            .getTicksBurning();

                    this.actualFuelBurning = maxFuelBurning;
                    craft();
                }
            } else {
                this.actualFuelBurning--;
                craft();
            }

        } else {
            return;
        }
    }


    @OnlyIn(Dist.CLIENT)
    public int getBurnProgress() {

        return this.actualFuelBurning / this.maxFuelBurning * 100;
    }

    @OnlyIn(Dist.CLIENT)
    public int getLitProgress() {
        return this.actualTimeToActualRecipe / this.maxTimeToActualRecipe * 100;
    }

    @OnlyIn(Dist.CLIENT)
    public boolean isLit() {
        return (actualFuelBurning < 0) ? true : false;
    }
}

