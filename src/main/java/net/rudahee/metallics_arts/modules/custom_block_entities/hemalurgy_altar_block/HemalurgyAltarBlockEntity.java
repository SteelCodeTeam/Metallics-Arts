package net.rudahee.metallics_arts.modules.custom_block_entities.hemalurgy_altar_block;

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
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.rudahee.metallics_arts.data.custom_recipes.tables.CrucibleFurnaceRecipe;
import net.rudahee.metallics_arts.data.enums.implementations.custom_items.SpikeEnum;
import net.rudahee.metallics_arts.setup.registries.ModBlockEntitiesRegister;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Optional;


public class HemalurgyAltarBlockEntity extends BlockEntity implements MenuProvider {

    // Slots definition
    private final ItemStackHandler itemHandler = new ItemStackHandler(40) {
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
            if (slot >= 0 && slot <= 40) {
                return Arrays.stream(SpikeEnum.values()).anyMatch(spike -> spike.getSpike().equals(stack.getItem()));
            }

            return false;
        }
    };

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    protected final ContainerData data;

    public HemalurgyAltarBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntitiesRegister.HEMALURGY_ALTAR_ENTITY.get(), pos, state);
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

        super.saveAdditional(tag);
    }

    @Override
    public void load(CompoundTag tag) {
        itemHandler.deserializeNBT(tag.getCompound("inventory"));

        super.load(tag);
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, HemalurgyAltarBlockEntity entity) {


    }


    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inv, Player player) {
        return new HemalurgyAltarMenu(id, inv, this, this.data);
    }
}
