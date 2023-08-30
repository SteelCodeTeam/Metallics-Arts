package net.rudahee.metallics_arts.modules.custom_block_entities.crucible_furnace;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
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
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.rudahee.metallics_arts.data.custom_recipes.tables.CrucibleFurnaceRecipe;
import net.rudahee.metallics_arts.setup.registries.ModBlockEntitiesRegister;
import net.rudahee.metallics_arts.setup.registries.ModItemsRegister;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;


public class CrucibleFurnaceBlockEntity extends BlockEntity implements MenuProvider {

    // Slots definition
    private final ItemStackHandler itemHandler = new ItemStackHandler(6) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }

        @Override
        public @NotNull ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
            if (!isItemValid(slot, stack)) {
                return stack;
            }

            return super.insertItem(slot, stack, simulate);
        }

        @Override
        public int getSlotLimit(int slot) {
            return 64;
        }

        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            switch (slot) {
                case 1:
                case 2:
                case 3:
                case 4:
                    return (Items.IRON_INGOT == stack.getItem() ||
                            Items.GOLD_INGOT == stack.getItem() ||
                            Items.COAL == stack.getItem() ||
                            ModItemsRegister.ITEM_METAL_INGOT.values().stream().anyMatch(m-> m == stack.getItem()) ||
                            ModItemsRegister.ITEM_GEMS_BASE.values().stream().anyMatch(m -> m == stack.getItem()));
                case 0:
                    return Items.LAVA_BUCKET == stack.getItem();
                case 6:
                    return true;
                default:
                    return false;
            }
        }
    };

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 100;
    private int fuelStorage = 0;
    private int maxFuelStorage = 100;
    private int timeWithoutRecipe = 0;
    private int tickFuel = 0;
    private int tickProgress = 0;
    private int tickAnim = 0;

    public static final int PROGRESS_INDEX = 0;
    public static final int MAX_PROGRESS_INDEX = 1;
    public static final int FUEL_STORAGE_INDEX = 2;
    public static final int MAX_FUEL_STORAGE_INDEX = 3;
    public static final int TIME_WITHOUT_RECIPE_INDEX = 4;
    public static final int TICK_FUEL_INDEX = 5;
    public static final int TICK_PROGRESS_INDEX = 6;


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
                    case 5 -> CrucibleFurnaceBlockEntity.this.tickFuel;
                    case 6 -> CrucibleFurnaceBlockEntity.this.tickProgress;
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
                    case 5 -> CrucibleFurnaceBlockEntity.this.tickFuel = value;
                    case 6 -> CrucibleFurnaceBlockEntity.this.tickProgress = value;
                }
            }

            @Override
            public int getCount() {
                return 7;
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
        tag.putInt("crucible_furnace.tick_fuel", this.tickFuel);
        tag.putInt("crucible_furnace.tick_progress", this.tickProgress);
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
        this.tickFuel = tag.getInt("crucible_furnace.tick_fuel");
        this.tickProgress = tag.getInt("crucible_furnace.tick_progress");

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
                setChanged(level, pos, state);

                craftItem(entity);

            } else {
                if (entity.fuelStorage > 0) {
                    entity.timeWithoutRecipe++;

                    if (entity.timeWithoutRecipe >= 20000) {
                        if (entity.itemHandler.getStackInSlot(5).is(Items.AIR)) {
                            entity.itemHandler.setStackInSlot(5, new ItemStack(Items.OBSIDIAN, (int) Math.ceil(entity.fuelStorage / 100.0 * 10)));
                            entity.timeWithoutRecipe = 0;
                            entity.fuelStorage = 0;

                        } else {
                            entity.timeWithoutRecipe--;
                            entity.fuelStorage--;
                        }
                    }
                    entity.resetProgress();
                    setChanged(level, pos, state);
                }
            }

            if (level instanceof ServerLevel servLevel && entity.fuelStorage > 0) {
                if (entity.tickAnim == 5) {
                    servLevel.sendParticles(ParticleTypes.CAMPFIRE_COSY_SMOKE, pos.getX() + 0.5d, pos.getY() + 1d, pos.getZ() + 0.5d, 3, 0d, 0, 0, 0d);
                    //servLevel.sendParticles(ParticleTypes.LANDING_LAVA, pos.getX() + 0.5d, pos.getY() + 0.5d, pos.getZ() + 0.5d, 0, 0d, 0.5d, 0.5d, 0d);
                    servLevel.sendParticles(ParticleTypes.LAVA, pos.getX() + 0.5d, pos.getY() + 0.5d, pos.getZ() + 0.5d, 0, 0d, 0.5d, 0.5d, 0d);
                    entity.tickAnim = 0;
                } else {
                    entity.tickAnim++;
                }
            }
        }



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
        this.tickProgress = 0;
        this.tickFuel = 0;
        this.progress = 0;
    }

    private static void craftItem(CrucibleFurnaceBlockEntity entity) {

        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());

        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }

        Optional<CrucibleFurnaceRecipe> recipe = entity.level.getRecipeManager().getRecipeFor(CrucibleFurnaceRecipe.Type.INSTANCE, inventory, entity.level);

        if (recipe.isPresent() && entity.itemHandler.getStackInSlot(5).getCount() < 64) {

            if (entity.fuelStorage > 0) {

                if (entity.tickProgress <= 1) {
                    entity.tickProgress++;
                } else {
                    entity.progress++;
                    entity.tickProgress = 0;
                }

                if (entity.progress >= entity.maxProgress) {

                    entity.itemHandler.extractItem(1, 1, false);
                    entity.itemHandler.extractItem(2, 1, false);
                    entity.itemHandler.extractItem(3, 1, false);
                    entity.itemHandler.extractItem(4, 1, false);

                    if (entity.itemHandler.getStackInSlot(5).isEmpty()) {
                        entity.itemHandler.setStackInSlot(5, new ItemStack(recipe.get().getResultItem().getItemHolder(), 3));
                    } else {
                        entity.itemHandler.setStackInSlot(5, new ItemStack(recipe.get().getResultItem().getItem(),
                                entity.itemHandler.getStackInSlot(5).getCount() + 3));
                    }

                    entity.progress = 0;
                }
                if (entity.tickFuel <= 1000) {
                    entity.tickFuel++;
                } else {
                    entity.tickFuel = 0;
                    entity.fuelStorage--;
                }
            }
        }
    }

    private static boolean hasRecipe(CrucibleFurnaceBlockEntity entity) {
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }

        Optional<CrucibleFurnaceRecipe> recipe = entity.level.getRecipeManager().getRecipeFor(CrucibleFurnaceRecipe.Type.INSTANCE, inventory, entity.level);

        return recipe.isPresent();

    }


    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inv, Player player) {
        return new CrucibleFurnaceMenu(id, inv, this, this.data);
    }
}
