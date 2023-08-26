package net.rudahee.metallics_arts.modules.custom_block_entities.crucible_furnace;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;

import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.*;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.rudahee.metallics_arts.setup.registries.ModBlockEntitiesRegister;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public class CrucibleFurnaceBlockEntity extends BlockEntity implements MenuProvider {

    // Slots definition
    private final ItemStackHandler itemHandler = new ItemStackHandler(6) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 100;
    private int fuelStorage = 0;
    private int maxFuelStorage = 100;
    private int timeWithoutRecipe = 0;

    public static final int PROGRESS_INDEX = 0;
    public static final int MAX_PROGRESS_INDEX = 1;
    public static final int FUEL_STORAGE_INDEX = 2;
    public static final int MAX_FUEL_STORAGE_INDEX = 3;
    public static final int TIME_WITHOUT_RECIPE_INDEX = 4;

    public CrucibleFurnaceBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntitiesRegister.CRUCIBLE_FURNACE_ENTITY.get(), pos, state);
        this.data = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> CrucibleFurnaceBlockEntity.this.progress;
                    case 1 -> CrucibleFurnaceBlockEntity.this.maxProgress;
                    case 2 -> CrucibleFurnaceBlockEntity.this.fuelStorage;
                    case 3 -> CrucibleFurnaceBlockEntity.this.maxFuelStorage;
                    case 4 -> CrucibleFurnaceBlockEntity.this.timeWithoutRecipe;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> CrucibleFurnaceBlockEntity.this.progress = value;
                    case 1 -> CrucibleFurnaceBlockEntity.this.maxProgress = value;
                    case 2 -> CrucibleFurnaceBlockEntity.this.fuelStorage = value;
                    case 3 -> CrucibleFurnaceBlockEntity.this.maxFuelStorage = value;
                    case 4 -> CrucibleFurnaceBlockEntity.this.timeWithoutRecipe = value;
                }
            }

            @Override
            public int getCount() {
                return 5;
            }
        };
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.metallics_arts.menu.crucible_furnace");
    }


    @Deprecated(since = "1.6.5", forRemoval = true)
    @SuppressWarnings("removal")
    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return lazyItemHandler.cast();
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        tag.put("inventory", itemHandler.serializeNBT());
        tag.putInt("crucible_furnace.progress", this.progress);
        tag.putInt("crucible_furnace.max_progress", this.maxProgress);
        tag.putInt("crucible_furnace.fuel_storage", this.fuelStorage);
        tag.putInt("crucible_furnace.max_fuel_storage", this.maxFuelStorage);
        tag.putInt("crucible_furnace.time_without_recipe", this.timeWithoutRecipe);

        super.saveAdditional(tag);
    }

    @Override
    public void load(CompoundTag tag) {
        itemHandler.deserializeNBT(tag.getCompound("inventory"));
        this.progress = tag.getInt("crucible_furnace.progress");
        this.maxProgress =tag.getInt("crucible_furnace.max_progress");
        this.fuelStorage = tag.getInt("crucible_furnace.fuel_storage");
        this.maxFuelStorage = tag.getInt("crucible_furnace.max_fuel_storage");
        this.timeWithoutRecipe = tag.getInt("crucible_furnace.time_without_recipe");

        super.load(tag);
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, CrucibleFurnaceBlockEntity entity) {
        if (!level.isClientSide()) {
            if (entity.itemHandler.getStackInSlot(0).is(Items.LAVA_BUCKET)) {
                if (entity.data.get(entity.FUEL_STORAGE_INDEX) < entity.data.get(entity.MAX_FUEL_STORAGE_INDEX)) {
                    rechargeFuel(entity, level, pos, state);
                }
            }
            if (hasRecipe(entity)) {
                entity.progress++;
                setChanged(level, pos, state);

                if (entity.maxProgress <= entity.progress) {
                    craftItem(entity);
                }
            } else {
                entity.resetProgress();
                setChanged(level, pos, state);
            }
        }

        //TODO
    }

    public static void rechargeFuel(CrucibleFurnaceBlockEntity entity, Level level, BlockPos pos, BlockState state) {
        if (entity.fuelStorage >= 80) {
            entity.fuelStorage = entity.maxFuelStorage;
            setChanged(level, pos, state);
        } else {
            entity.fuelStorage = entity.fuelStorage + 20;
            setChanged(level, pos, state);
        }
        entity.itemHandler.setStackInSlot(0, new ItemStack(Items.BUCKET));
    }

    private void resetProgress() {
        this.progress = 0;
    }

    private static void craftItem(CrucibleFurnaceBlockEntity pEntity) {

        SimpleContainer inventory = new SimpleContainer(pEntity.itemHandler.getSlots());
        for (int i = 0; i < pEntity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, pEntity.itemHandler.getStackInSlot(i));
        }

       //TODO
    }

    private static boolean hasRecipe(CrucibleFurnaceBlockEntity entity) {
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }

        //Todo
        //habria que chequear que la receta exista
        /**
         * @see AbstractCookingRecipe
         * @see AbstractFurnaceBlockEntity
         * */
        return canInsertItemIntoOutputSlot(inventory, new ItemStack(Items.EMERALD)) && canInsertAmountIntoOutputSlot(inventory); //add exist recipe
    }

    private static boolean canInsertItemIntoOutputSlot(SimpleContainer inventory, ItemStack stack) {
        return inventory.getItem(5).getItem() == stack.getItem() || inventory.getItem(5).isEmpty();
    }

    private static boolean canInsertAmountIntoOutputSlot(SimpleContainer inventory) {
        return inventory.getItem(5).getMaxStackSize() > inventory.getItem(5).getCount();
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inv, Player player) {
        return new CrucibleFurnaceMenu(id, inv, this, this.data);
    }
}
