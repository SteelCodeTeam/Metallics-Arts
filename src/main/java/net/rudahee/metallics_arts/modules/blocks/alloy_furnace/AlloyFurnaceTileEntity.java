package net.rudahee.metallics_arts.modules.blocks.alloy_furnace;

import net.minecraft.block.BlockState;
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
import net.rudahee.metallics_arts.data.recipes.alloy_furnace.AlloyFurnaceRecipe;
import net.rudahee.metallics_arts.setup.enums.extras.FuelsTime;
import net.rudahee.metallics_arts.setup.enums.metals.MetalBurningRecipeData;
import net.rudahee.metallics_arts.setup.registries.ModItems;
import net.rudahee.metallics_arts.setup.registries.ModRecipeTypes;
import net.rudahee.metallics_arts.setup.registries.ModTileEntities;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Optional;

public class AlloyFurnaceTileEntity extends TileEntity implements ITickableTileEntity {

    private int actualFuelBurning = 0;
    private int maxFuelBurning = 0;
    private boolean isCrafting = false;

    private int actualTimeToActualRecipe = 0;
    private int maxTimeToActualRecipe = 0;
    private final boolean existsRecipe = false;


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

    private void craftTheItem(ItemStack output) {
        itemHandler.extractItem(0, 1, false);
        itemHandler.extractItem(1, 1, false);
        itemHandler.extractItem(2, 1, false);
        itemHandler.extractItem(3, 1, false);

        itemHandler.insertItem(5, output, false);
    }

    @Override
    public void tick() {
        if (!this.level.isClientSide()) {
            if(cookingRecipe()) {
                this.getBlockState().setValue(AlloyFurnaceBlock.LIT, true);
            } else {
                this.getBlockState().setValue(AlloyFurnaceBlock.LIT, false);
            }
        }
    }

    private boolean checkFuel() {
        Inventory inventoryFuel = new Inventory(1);
        inventoryFuel.setItem(0, itemHandler.getStackInSlot(4));

        return !inventoryFuel.isEmpty();
    }

    private boolean checkAir(Inventory inventoryRecipe) {
        boolean containAir = false;
        for (int i = 0; i < inventoryRecipe.getContainerSize(); i++) {
            if (inventoryRecipe.getItem(i).getItem().equals(Items.AIR)
                    || inventoryRecipe.getItem(i).isEmpty()
                    || inventoryRecipe.getItem(i).getItem() == null) {

                containAir = true;
            }
        }

        return  containAir;

    }

    private boolean cookingRecipe() {

        // Create inventory with X slots
        Inventory inventoryRecipe = new Inventory(4);

        // For each slot put in inventory what we do here.
        for (int i = 0; i < 4; i++) {
            inventoryRecipe.setItem(i, itemHandler.getStackInSlot(i));
        }

        Optional<AlloyFurnaceRecipe> recipe = this.level.getRecipeManager().getRecipeFor(ModRecipeTypes.ALLOY_FURNACE_RECIPE, inventoryRecipe, level);

        if (checkAir(inventoryRecipe)) {
            recipe = Optional.empty();
        }

        // If not crafting anything
        if (!isCrafting) {
            // If recipe exists and is lit, we start to craft the item.
            if (recipe.isPresent()) {
                if (isLit()) {
                    AlloyFurnaceRecipe presentRecipe = recipe.get();
                    isCrafting = true;

                    maxTimeToActualRecipe = Arrays.stream(MetalBurningRecipeData.values())
                            .filter(m -> m.getItem().getDescriptionId().equals(presentRecipe.getResultItem().getDescriptionId()))
                            .findFirst().get()
                            .getTicksToCompleteBurning();

                    actualTimeToActualRecipe = maxTimeToActualRecipe;
                    actualFuelBurning--;
                } else {

                    // If not lit, we check fuel, and we have fuel, get 1 and lit.
                    if (checkFuel()) {
                        refillLit();
                    } else {
                        actualTimeToActualRecipe = -1;
                        isCrafting = false;
                    }
                }
            } else {
                if (isLit()) {
                    actualFuelBurning--;
                }
            }
        } else {
            if (checkAir(inventoryRecipe)) {
                isCrafting = false;
                actualTimeToActualRecipe = -1;
            }

            if (isLit()) {
                actualFuelBurning--;
                actualTimeToActualRecipe = (actualTimeToActualRecipe <= 0) ? actualTimeToActualRecipe : --actualTimeToActualRecipe;

                if (isCompleteCrafting() && !checkAir(inventoryRecipe)) {
                    craftTheItem(recipe.get().getResultItem());

                    actualTimeToActualRecipe = -1;
                    isCrafting = false;

                } else {
                    if (!isLit()) {
                        if (checkFuel()) {
                            refillLit();
                        } else {
                            actualTimeToActualRecipe = -1;
                            isCrafting = false;
                        }
                    }
                }
            } else {
                if (checkFuel()) {
                    refillLit();
                } else {
                    actualTimeToActualRecipe = -1;
                    isCrafting = false;
                }
            }
        }
        return isCrafting;
    }



    @OnlyIn(Dist.CLIENT)
    public int getBurnProgress() {
        return actualFuelBurning;
    }

    @OnlyIn(Dist.CLIENT)
    public int getLitProgress() {
        return actualTimeToActualRecipe;
    }

    public boolean isLit() {
        return actualFuelBurning >= 0;
    }

    public boolean isCompleteCrafting() {
        return actualTimeToActualRecipe <= 0;
    }

    public void refillLit() {
        Item fuelItem = itemHandler.extractItem(4, 1, false).getItem();

        maxFuelBurning = Arrays.stream(FuelsTime.values())
                .filter(f -> f.getItem().equals(fuelItem.getItem()))
                .findFirst().get()
                .getTicksBurning();

        actualFuelBurning = maxFuelBurning;
    }
}

