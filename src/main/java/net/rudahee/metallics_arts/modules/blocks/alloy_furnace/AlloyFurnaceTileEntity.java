package net.rudahee.metallics_arts.modules.blocks.alloy_furnace;

import net.minecraft.block.AbstractFurnaceBlock;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
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

public class AlloyFurnaceTileEntity extends TileEntity implements ITickableTileEntity, INamedContainerProvider {

    private final AlloyFurnaceData data = new AlloyFurnaceData();
    private final ItemStackHandler itemHandler = createHandler();
    private final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);

    private static ItemStack[] itemInSlot = new ItemStack[6];


    public AlloyFurnaceTileEntity(TileEntityType<?> type) {
        super(type);
        Arrays.fill(AlloyFurnaceTileEntity.itemInSlot,null);
    }

    public AlloyFurnaceTileEntity() {
        this(ModTileEntities.ALLOY_FURNACE_TILE_ENTITY.get());
        Arrays.fill(AlloyFurnaceTileEntity.itemInSlot,null);
    }

    @Override
    public void load(BlockState state, CompoundNBT nbt) {
        super.load(state, nbt);
        data.readNBTData(nbt);
        itemHandler.deserializeNBT(nbt.getCompound("inv"));
    }

    @Override
    public CompoundNBT save(CompoundNBT compound) {
        super.save(compound);
        data.updateNBTData(compound);
        compound.put("inv", itemHandler.serializeNBT());

        return compound;
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

                AlloyFurnaceTileEntity.itemInSlot[slot] = stack;

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
            if(cookingRecipe() == 1) {
                this.level.setBlock(this.worldPosition, this.level.getBlockState(this.worldPosition).setValue(AlloyFurnaceBlock.LIT, Boolean.valueOf(this.isLit())), 3);

            } else {
                this.level.setBlock(this.worldPosition, this.level.getBlockState(this.worldPosition).setValue(AlloyFurnaceBlock.LIT, Boolean.valueOf(this.isLit())), 3);

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

    private int cookingRecipe() {

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
        if (!(data.isCrafting == 1)) {
            // If recipe exists and is lit, we start to craft the item.
            if (recipe.isPresent()) {
                if (isLit()) {
                    AlloyFurnaceRecipe presentRecipe = recipe.get();
                    data.isCrafting = 1;

                    data.maxTimeToActualRecipe = Arrays.stream(MetalBurningRecipeData.values())
                            .filter(m -> m.getItem().getDescriptionId().equals(presentRecipe.getResultItem().getDescriptionId()))
                            .findFirst().get()
                            .getTicksToCompleteBurning();

                    data.actualTimeToActualRecipe = data.maxTimeToActualRecipe;
                    data.actualFuelBurning--;
                } else {

                    // If not lit, we check fuel, and we have fuel, get 1 and lit.
                    if (checkFuel()) {
                        refillLit();
                    } else {
                        data.actualTimeToActualRecipe = -1;
                        data.isCrafting = 0;
                    }
                }
            } else {
                if (isLit()) {
                    data.actualFuelBurning--;
                }
            }
        } else {
            if (checkAir(inventoryRecipe)) {
                data.isCrafting = 0;
                data.actualTimeToActualRecipe = -1;
            }

            if (isLit()) {
                data.actualFuelBurning--;
                data.actualTimeToActualRecipe = (data.actualTimeToActualRecipe <= 0) ? data.actualTimeToActualRecipe : --data.actualTimeToActualRecipe;

                if (isCompleteCrafting() && !checkAir(inventoryRecipe)) {
                    craftTheItem(recipe.get().getResultItem());

                    data.actualTimeToActualRecipe = -1;
                    data.isCrafting = 0;

                } else {
                    if (!isLit()) {
                        if (checkFuel()) {
                            refillLit();
                        } else {
                            data.actualTimeToActualRecipe = -1;
                            data.isCrafting = 0;
                        }
                    }
                }
            } else {
                if (checkFuel()) {
                    refillLit();
                } else {
                    data.actualTimeToActualRecipe = -1;
                    data.isCrafting = 0;
                }
            }
        }

        return data.isCrafting;
    }

    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        CompoundNBT nbtTagCompound = new CompoundNBT();
        nbtTagCompound = save(nbtTagCompound);
        int tileEntityType = getType().hashCode();  // arbitrary number; only used for vanilla TileEntities.  You can use it, or not, as you want.
        return new SUpdateTileEntityPacket(getBlockPos(), tileEntityType, nbtTagCompound);
    }

    @Override
    public void handleUpdateTag(BlockState state, CompoundNBT tag) {
        super.handleUpdateTag(state, tag);
        data.updateNBTData(tag);
    }

    @Override
    public CompoundNBT getUpdateTag() {
        super.getUpdateTag();

        CompoundNBT tag = new CompoundNBT();
        tag = save(tag);
        return tag;
    }

    public boolean isLit() {
        return data.actualFuelBurning >= 0;
    }

    public boolean isCompleteCrafting() {
        return data.actualTimeToActualRecipe <= 0;
    }

    public void refillLit() {
        Item fuelItem = itemHandler.extractItem(4, 1, false).getItem();

        data.maxFuelBurning = Arrays.stream(FuelsTime.values())
                .filter(f -> f.getItem().equals(fuelItem.getItem()))
                .findFirst().get()
                .getTicksBurning();


        data.actualFuelBurning = data.maxFuelBurning;
    }

    public static ItemStack getItemInSlot(int slot) {
        return itemInSlot[slot];
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        super.onDataPacket(net, pkt);
        BlockState blockState = getBlockState();
        this.load(blockState, pkt.getTag());   // read from the nbt in the packet

    }

    @Nullable
    @Override
    public AlloyFurnaceContainer createMenu(int windowId, PlayerInventory inventory, PlayerEntity entity) {
        return AlloyFurnaceContainer.createContainerInServerSide(windowId, this.getBlockPos(), inventory, this.data);
    }

    @Override
    public ITextComponent getDisplayName() {
        return new TranslationTextComponent("screen.metallics_arts.alloy_furnace");
    }
}

