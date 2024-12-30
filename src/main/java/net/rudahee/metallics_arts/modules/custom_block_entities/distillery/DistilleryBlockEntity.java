package net.rudahee.metallics_arts.modules.custom_block_entities.distillery; // Ensure this package is consistent across all files

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
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.modules.custom_items.vials.LargeVial;
import net.rudahee.metallics_arts.modules.custom_items.vials.Vial;
import net.rudahee.metallics_arts.setup.registries.ModBlockEntitiesRegister;
import net.rudahee.metallics_arts.setup.registries.ModItemsRegister;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DistilleryBlockEntity extends BlockEntity implements MenuProvider {
    private final ItemStackHandler itemHandler = new ItemStackHandler(5) {
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
            return switch (slot) {
                case 0, 1, 2, 3 -> 64;
                case 4 -> 1;
                default -> 0;
            };
        }

        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            return switch (slot) {
                case 0 -> Items.BLAZE_POWDER == stack.getItem();

                case 1 -> (Items.RAW_IRON == stack.getItem() ||
                        Items.RAW_GOLD == stack.getItem() ||
                        Items.RAW_COPPER == stack.getItem() ||
                        ModItemsRegister.ITEM_RAW_METAL.values().stream().anyMatch(m -> m == stack.getItem()));

                case 2 -> (Items.IRON_NUGGET == stack.getItem() ||
                        Items.GOLD_NUGGET == stack.getItem() ||
                        ModItemsRegister.ITEM_METAL_NUGGET.values().stream().anyMatch(m -> m == stack.getItem()) ||
                        ModItemsRegister.ITEM_GEMS_NUGGET.values().stream().anyMatch(m -> m == stack.getItem()));

                case 3 -> (Items.IRON_INGOT == stack.getItem() ||
                        Items.GOLD_INGOT == stack.getItem() ||
                        Items.COPPER_INGOT == stack.getItem() ||
                        ModItemsRegister.ITEM_METAL_INGOT.values().stream().anyMatch(m -> m == stack.getItem()) ||
                        ModItemsRegister.ITEM_GEMS_BASE.values().stream().anyMatch(m -> m == stack.getItem()));

                case 4 -> ModItemsRegister.LARGE_VIAL.get() == stack.getItem();

                default -> false;
            };
        }
    };

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 100;
    private int fuelStorage = 0;
    private int maxFuelStorage = 100;
    private int vialCanBeFilled = 0; // it's boolean. True = 1, False = 0;
    private int tickFuelAdded = 0;
    private int slotToCheck = -1;
    private int tickProgress = 0;
    private int tickAnimFuel = 0;
    private int tickAnimRecipe = 0;

    public static final int SLOT_VOID = -1;
    public static final int SLOT_RAW = 1;
    public static final int SLOT_NUGGET = 2;
    public static final int SLOT_INGOT = 3;


    public DistilleryBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntitiesRegister.DISTILLERY_ENTITY.get(), pos, state);
        this.data = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> DistilleryBlockEntity.this.progress;
                    case 1 -> DistilleryBlockEntity.this.maxProgress;
                    case 2 -> DistilleryBlockEntity.this.fuelStorage;
                    case 3 -> DistilleryBlockEntity.this.maxFuelStorage;
                    case 4 -> DistilleryBlockEntity.this.vialCanBeFilled;
                    case 5 -> DistilleryBlockEntity.this.tickFuelAdded;
                    case 6 -> DistilleryBlockEntity.this.tickProgress;
                    case 7 -> DistilleryBlockEntity.this.tickAnimFuel;
                    case 8 -> DistilleryBlockEntity.this.tickAnimRecipe;
                    case 9 -> DistilleryBlockEntity.this.slotToCheck;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> DistilleryBlockEntity.this.progress = value;
                    case 1 -> DistilleryBlockEntity.this.maxProgress = value;
                    case 2 -> DistilleryBlockEntity.this.fuelStorage = value;
                    case 3 -> DistilleryBlockEntity.this.maxFuelStorage = value;
                    case 4 -> DistilleryBlockEntity.this.vialCanBeFilled = value;
                    case 5 -> DistilleryBlockEntity.this.tickFuelAdded = value;
                    case 6 -> DistilleryBlockEntity.this.tickProgress = value;
                    case 7 -> DistilleryBlockEntity.this.tickAnimFuel = value;
                    case 8 -> DistilleryBlockEntity.this.tickAnimRecipe = value;
                    case 9 -> DistilleryBlockEntity.this.slotToCheck = value;

                }
            }

            @Override
            public int getCount() {
                return 10;
            }
        };
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.metallics_arts.menu.distillery_menu.name");
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
        tag.putInt("distillery.progress", this.progress);
        tag.putInt("distillery.max_progress", this.maxProgress);
        tag.putInt("distillery.fuel_storage", this.fuelStorage);
        tag.putInt("distillery.max_fuel_storage", this.maxFuelStorage);
        tag.putInt("distillery.can_be_filled", this.vialCanBeFilled);
        tag.putInt("distillery.tick_fuel_added", this.tickFuelAdded);
        tag.putInt("distillery.slot_to_check", this.slotToCheck);
        tag.putInt("distillery.tick_progress", this.tickProgress);
        tag.putInt("distillery.tick_anim_fuel", this.tickAnimFuel);
        tag.putInt("distillery.tick_anim_recipe", this.tickAnimRecipe);
        super.saveAdditional(tag);
    }

    @Override
    public void load(CompoundTag tag) {
        itemHandler.deserializeNBT(tag.getCompound("inventory"));
        this.progress = tag.getInt("distillery.progress");
        this.maxProgress =tag.getInt("distillery.max_progress");
        this.fuelStorage = tag.getInt("distillery.fuel_storage");
        this.maxFuelStorage = tag.getInt("distillery.max_fuel_storage");
        this.vialCanBeFilled = tag.getInt("distillery.can_be_filled");
        this.tickFuelAdded = tag.getInt("distillery.tick_fuel_added");
        this.tickProgress = tag.getInt("distillery.tick_progress");
        this.slotToCheck = tag.getInt("distillery.slot_to_check");
        this.tickAnimFuel = tag.getInt("distillery.tick_anim_fuel");
        this.tickAnimRecipe = tag.getInt("distillery.tick_anim_recipe");

        super.load(tag);
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, DistilleryBlockEntity entity) {
        if (!level.isClientSide()) {

            manageFuel(entity, level, pos, state);

            if (entity.fuelStorage > 0) {
                if (entity.slotToCheck == SLOT_VOID) {
                    entity.slotToCheck = hasMinimalItemsForRecipe(entity, level, pos, state);
                } else {
                    int newSlot = entity.slotToCheck = hasMinimalItemsForRecipe(entity, level, pos, state);
                    if (newSlot != entity.slotToCheck) {
                        entity.tickProgress = 0;
                        entity.progress = 0;
                        entity.slotToCheck = -1;
                        setChanged(level, pos, state);
                    } else {


                        if (entity.progress < entity.maxProgress) {
                            entity.progress++;
                            setChanged(level, pos, state);
                        } else {
                            removeItemsFromRecipe(entity, level, pos, state);
                            craftItemWithTags(entity, level, pos, state);
                            entity.tickProgress = 0;
                            entity.progress = 0;
                            entity.slotToCheck = SLOT_VOID;
                            entity.fuelStorage = entity.fuelStorage - 3;
                            setChanged(level, pos, state);
                        }
                    }
                }

            } else if (entity.fuelStorage <= 0) {
                entity.tickProgress = 0;
                entity.progress = 0;
                entity.slotToCheck = SLOT_VOID;
                setChanged(level, pos, state);
            }

            if (entity.progress > 0 && (entity.slotToCheck == SLOT_VOID
                        || entity.itemHandler.getStackInSlot(4).is(ItemStack.EMPTY.getItem())
                        || Vial.hasFullReserveWithoutDivine(entity.itemHandler.getStackInSlot(4)))) {
                entity.tickProgress = 0;
                entity.progress = 0;
                entity.slotToCheck = SLOT_VOID;
                setChanged(level, pos, state);
            }
        }


        if (level instanceof ServerLevel servLevel && entity.fuelStorage > 0) {
            if (entity.tickProgress > 0) {
                if (entity.tickAnimRecipe == 2) {
                    servLevel.sendParticles(ParticleTypes.LAVA, pos.getX() + 0.5d, pos.getY() + 1d, pos.getZ() + 0.5d, 3, 0d, 0, 0, 0d);
                    entity.tickAnimRecipe = 0;
                } else {
                    entity.tickAnimRecipe++;
                }
            }

            if (entity.tickFuelAdded > 0) {
                if (entity.tickAnimFuel == 1) {
                    servLevel.sendParticles(ParticleTypes.CLOUD, pos.getX() + 0.5d, pos.getY() + 1d, pos.getZ() + 0.5d, 3, 0d, 0, 0, 0d);
                    entity.tickAnimRecipe = 0;
                } else {
                    entity.tickAnimFuel++;
                }
            }
        }
    }

    private static void manageFuel(DistilleryBlockEntity entity, Level level, BlockPos pos, BlockState state) {
        if (entity.itemHandler.getStackInSlot(0).is(Items.BLAZE_POWDER)) {
            if (entity.fuelStorage < entity.maxFuelStorage) {
                if (entity.tickFuelAdded < 100) {
                    entity.tickFuelAdded++;
                    setChanged(level, pos, state);
                } else {
                    addFuel(entity, level, pos, state);
                    entity.tickFuelAdded = 0;
                    setChanged(level, pos, state);
                }
            } else {
                entity.tickFuelAdded = 0;
                setChanged(level, pos, state);
            }
        } else {
            entity.tickFuelAdded = 0;
            setChanged(level, pos, state);
        }
    }

    private static void removeItemsFromRecipe(DistilleryBlockEntity entity, Level level, BlockPos pos, BlockState state) {
        entity.itemHandler.extractItem(entity.slotToCheck, 1, false);
    }

    private static void craftItemWithTags(DistilleryBlockEntity entity, Level level, BlockPos pos, BlockState state) {
        // Identificar el metal que hay que agregar según el slot
        MetalTagEnum metal = getMetalBasedOnSlot(entity);
        if (metal == null) {
            return; // No se detectó un metal válido, salir
        }

        // Obtener el vial y su tag
        ItemStack vialStack = entity.itemHandler.getStackInSlot(4);
        CompoundTag vialTag = vialStack.getOrCreateTag();

        // Actualizar la cantidad del metal en el vial, respetando los datos existentes
        updateVialTag(entity, vialTag, metal);

        // Añadir información adicional al vial (como CustomModelData)
        vialTag.putFloat("CustomModelData", 1);

        // Crear el nuevo ItemStack con los datos actualizados
        ItemStack finalResult = new ItemStack(ModItemsRegister.LARGE_VIAL.get(), 1);
        finalResult.setTag(vialTag);
    }

    private static MetalTagEnum getMetalBasedOnSlot(DistilleryBlockEntity entity) {
        ItemStack item = entity.itemHandler.getStackInSlot(entity.slotToCheck);
        if (item.isEmpty()) {
            return null;
        }

        String itemKey = item.getItem().toString().toLowerCase();

        if (entity.slotToCheck == SLOT_RAW && itemKey.contains("raw_")) {
            return MetalTagEnum.getMetal(itemKey.replace("raw_", ""));
        } else if (entity.slotToCheck == SLOT_INGOT && itemKey.contains("_ingot")) {
            return MetalTagEnum.getMetal(itemKey.replace("_ingot", ""));
        } else if (entity.slotToCheck == SLOT_NUGGET && itemKey.contains("_nugget")) {
            return MetalTagEnum.getMetal(itemKey.replace("_nugget", ""));
        }

        return null;
    }

    private static void updateVialTag(DistilleryBlockEntity entity, CompoundTag tag, MetalTagEnum metal) {
        String metalKey = metal.getNameLower();
        int currentValue = tag.getInt(metalKey); // Obtener valor actual del metal en el vial

        // Calcular el nuevo valor según el slot activo
        int addedValue = switch (entity.slotToCheck) {
            case SLOT_INGOT -> 10; // Ingot siempre añade hasta 10
            case SLOT_RAW -> calculateRawSlotValue(currentValue);
            case SLOT_NUGGET -> Math.min(currentValue + 1, 10); // Nugget añade 1, máximo 10
            default -> 0;
        };

        // Actualizar el tag con el nuevo valor del metal si se agregó algún valor
        if (addedValue > currentValue) {
            tag.putInt(metalKey, addedValue);
        }
    }

    private static int calculateRawSlotValue(int currentValue) {
        if (currentValue < 10) {
            return Math.min(currentValue + 5, 10);
        }
        return currentValue;
    }

    private static int hasMinimalItemsForRecipe(DistilleryBlockEntity entity, Level level, BlockPos pos, BlockState state) {
        if (entity.itemHandler.getStackInSlot(SLOT_INGOT).isEmpty() &&
                entity.itemHandler.getStackInSlot(SLOT_RAW).isEmpty() &&
                entity.itemHandler.getStackInSlot(SLOT_NUGGET).isEmpty()) {
            return SLOT_VOID;
        }

        if (entity.itemHandler.getStackInSlot(4).isEmpty()) {
            return SLOT_VOID;
        }

        MetalTagEnum metalRaw = null;
        MetalTagEnum metalIngot = null;
        MetalTagEnum metalNugget = null;

        if (entity.itemHandler.getStackInSlot(SLOT_RAW).getItem().toString().toLowerCase().contains("raw_")) {
            metalRaw = MetalTagEnum.getMetal(entity.itemHandler.getStackInSlot(SLOT_RAW).getItem()
                    .toString().toLowerCase().replace("raw_", ""));
        }

        if (entity.itemHandler.getStackInSlot(SLOT_NUGGET).getItem().toString().toLowerCase().contains("_nugget")) {
            metalNugget = MetalTagEnum.getMetal(entity.itemHandler.getStackInSlot(SLOT_NUGGET).getItem().
                    toString().toLowerCase().replace("_nugget", ""));
        }

        if (entity.itemHandler.getStackInSlot(SLOT_INGOT).getItem().toString().toLowerCase().contains("_ingot")) {
            metalIngot = MetalTagEnum.getMetal(entity.itemHandler.getStackInSlot(SLOT_INGOT).getItem()
                    .toString().toLowerCase().replace("_ingot", ""));
        }

        boolean flagRaw = false;
        boolean flagNugget = false;
        boolean flagIngot = false;

        if (metalRaw != null) {
            if (!LargeVial.hasCompletedMetalReserve(entity.itemHandler.getStackInSlot(4), metalRaw)) {
                flagRaw = true;
            }
        }
        if (metalNugget != null) {
            if (!LargeVial.hasCompletedMetalReserve(entity.itemHandler.getStackInSlot(4), metalNugget)) {
                flagNugget = true;
            }
        }
        if (metalIngot != null) {
            if (!LargeVial.hasCompletedMetalReserve(entity.itemHandler.getStackInSlot(4), metalIngot)) {
                flagIngot = true;
            }
        }

        if (!flagRaw && !flagNugget && !flagIngot) {
            return SLOT_VOID;
        }

        if (flagIngot) {
            return SLOT_INGOT;
        }
        if (flagRaw) {
            return SLOT_RAW;
        }
        if (flagNugget) {
            return SLOT_NUGGET;
        }

        return SLOT_VOID;
    }

    private static void addFuel(DistilleryBlockEntity entity, Level level, BlockPos pos, BlockState state) {
        if (entity.fuelStorage >= 95) {
            entity.fuelStorage = entity.maxFuelStorage;
            setChanged(level, pos, state);
        } else {
            entity.fuelStorage = entity.fuelStorage + 5;
            setChanged(level, pos, state);
        }
        entity.itemHandler.extractItem(0, 1, false);
    }


    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inv, Player player) {
        return new DistilleryMenu(id, inv, this, this.data);
    }
}
