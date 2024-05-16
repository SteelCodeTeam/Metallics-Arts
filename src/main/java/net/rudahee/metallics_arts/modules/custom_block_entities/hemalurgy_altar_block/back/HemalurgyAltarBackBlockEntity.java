package net.rudahee.metallics_arts.modules.custom_block_entities.hemalurgy_altar_block.back;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.rudahee.metallics_arts.data.enums.implementations.custom_items.SpikeEnum;
import net.rudahee.metallics_arts.setup.registries.ModBlockEntitiesRegister;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;


public class HemalurgyAltarBackBlockEntity extends BlockEntity implements MenuProvider {

    // Slots definition

    private LazyOptional<IItemHandler> lazyItemHandlerFront = LazyOptional.empty();
    private final ItemStackHandler itemHandlerFront = new ItemStackHandler(20) {
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
            return 1;
        }

        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            if (slot >= 0 && slot <= 20) {
                return Arrays.stream(SpikeEnum.values()).anyMatch(spike -> spike.getSpike().equals(stack.getItem()));
            }

            return false;
        }
    };

    private LazyOptional<IItemHandler> lazyItemHandlerBack = LazyOptional.empty();
    private final ItemStackHandler itemHandlerBack = new ItemStackHandler(20) {
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
            return 1;
        }

        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            if (slot >= 0 && slot <= 20) {
                return Arrays.stream(SpikeEnum.values()).anyMatch(spike -> spike.getSpike().equals(stack.getItem()));
            }

            return false;
        }
    };



    protected final ContainerData data;

    public HemalurgyAltarBackBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntitiesRegister.HEMALURGY_ALTAR_BACK_ENTITY.get(), pos, state);
        this.data = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index) {
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {

                }
            }

            @Override
            public int getCount() {
                return 0;
            }
        };
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.metallics_arts.menu.hemalurgy_altar");
    }


    @Deprecated(since = "1.6.5", forRemoval = true)
    @SuppressWarnings("removal")
    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return lazyItemHandlerFront.cast();
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandlerFront = LazyOptional.of(() -> itemHandlerFront);
        lazyItemHandlerBack = LazyOptional.of(() -> itemHandlerBack);

    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandlerFront.invalidate();
        lazyItemHandlerBack.invalidate();
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        tag.put("hemalurgy_altar_back", itemHandlerBack.serializeNBT());

        super.saveAdditional(tag);
    }

    @Override
    public void load(CompoundTag tag) {
        itemHandlerBack.deserializeNBT(tag.getCompound("hemalurgy_altar_back"));

        super.load(tag);
    }


    public static void tick(Level level, BlockPos pos, BlockState state, HemalurgyAltarBackBlockEntity entity) {
        if (!level.isClientSide()) {

        }

    }

    public AbstractContainerMenu createMenu(int id, Inventory inv, Player player) {
        return new HemalurgyAltarBackMenu(id, inv, this, this.data);
    }

    public AbstractContainerMenu createBackMenu(int id, Inventory inv, Player player) {
        return new HemalurgyAltarBackMenu(id, inv, this, this.data);
    }
}
