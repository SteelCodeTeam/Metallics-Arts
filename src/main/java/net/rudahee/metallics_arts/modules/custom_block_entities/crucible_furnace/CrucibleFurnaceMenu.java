package net.rudahee.metallics_arts.modules.custom_block_entities.crucible_furnace;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.rudahee.metallics_arts.setup.registries.ModBlocksRegister;
import net.rudahee.metallics_arts.setup.registries.ModMenuRegister;

public class CrucibleFurnaceMenu extends AbstractContainerMenu {
    public final CrucibleFurnaceBlockEntity blockEntity;
    private final Level level;
    private final ContainerData data;


    public CrucibleFurnaceMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
        this(id, inv, (CrucibleFurnaceBlockEntity) inv.player.level.getBlockEntity(extraData.readBlockPos()), new SimpleContainerData(7));
    }

    @SuppressWarnings("removal")
    public CrucibleFurnaceMenu(int id, Inventory inv, CrucibleFurnaceBlockEntity entity, ContainerData data) {
        super(ModMenuRegister.CRUCIBLE_FURNACE_MENU.get(), id);
        checkContainerSize(inv, 6);

        this.level = inv.player.level;
        this.data = data;
        this.blockEntity = entity;
        addPlayerInventory(inv);
        addPlayerHotbar(inv);

        this.blockEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(handler -> {
            this.addSlot(new SlotItemHandler(handler, 0, 7, 23));
            this.addSlot(new SlotItemHandler(handler, 1, 55, 44));
            this.addSlot(new SlotItemHandler(handler, 2, 74, 44));
            this.addSlot(new SlotItemHandler(handler, 3, 55, 63));
            this.addSlot(new SlotItemHandler(handler, 4, 74, 63));
            this.addSlot(new SlotItemHandler(handler, 5, 137, 65));
        });

        addDataSlots(data);
    }


    // CREDIT GOES TO: diesieben07 | https://github.com/diesieben07/SevenCommons
    // must assign a slot number to each of the slots used by the GUI.
    // For this container, we can see both the tile inventory's slots as well as the player inventory slots and the hotbar.
    // Each time we add a Slot to the container, it automatically increases the slotIndex, which means
    //  0 - 8 = hotbar slots (which will map to the InventoryPlayer slot numbers 0 - 8)
    //  9 - 35 = player inventory slots (which map to the InventoryPlayer slot numbers 9 - 35)
    //  36 - 44 = TileInventory slots, which map to our TileEntity slot numbers 0 - 8)
    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;

    // THIS YOU HAVE TO DEFINE!
    private static final int TE_INVENTORY_SLOT_COUNT = 6;  // must be the number of slots you have!

    @Override
    public ItemStack quickMoveStack(Player playerIn, int index) {
        Slot sourceSlot = slots.get(index);
        if (sourceSlot == null || !sourceSlot.hasItem()) return ItemStack.EMPTY;  //EMPTY_ITEM
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();

        // Check if the slot clicked is one of the vanilla container slots
        if (index < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
            // This is a vanilla container slot so merge the stack into the tile inventory
            if (!moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX, TE_INVENTORY_FIRST_SLOT_INDEX
                    + TE_INVENTORY_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;  // EMPTY_ITEM
            }
        } else if (index < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT) {
            // This is a TE slot so merge the stack into the players inventory
            if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }
        } else {
            System.out.println("Invalid slotIndex:" + index);
            return ItemStack.EMPTY;
        }
        // If stack size == 0 (the entire stack was moved) set slot contents to null
        if (sourceStack.getCount() == 0) {
            sourceSlot.set(ItemStack.EMPTY);
        } else {
            sourceSlot.setChanged();
        }
        sourceSlot.onTake(playerIn, sourceStack);
        return copyOfSourceStack;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()),
                player, ModBlocksRegister.CRUCIBLE_FURNACE.get());
    }

    private void addPlayerInventory(Inventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 7 + l * 18, 113 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 7 + i * 18, 171));
        }
    }

    public int getFuelQty() {
        int maxHeight = 51;
        double percentage = ((double) this.data.get(CrucibleFurnaceBlockEntity.FUEL_STORAGE_INDEX) / this.data.get(CrucibleFurnaceBlockEntity.MAX_FUEL_STORAGE_INDEX)) * 100;

        return Math.min(maxHeight, (int) ((percentage / 100) * maxHeight));
    }

    public int getProgress() {
        int maxWidth = 24;

        double percentage = ((double) this.data.get(CrucibleFurnaceBlockEntity.PROGRESS_INDEX) / this.data.get(CrucibleFurnaceBlockEntity.MAX_PROGRESS_INDEX)) * 100;

        return Math.min(maxWidth, (int) ((percentage / 100) * maxWidth));
    }

    public boolean isHot() {
        return this.data.get(CrucibleFurnaceBlockEntity.FUEL_STORAGE_INDEX) != 0;
    }

    public int timeWithoutRecipe() {
        return this.data.get(CrucibleFurnaceBlockEntity.TIME_WITHOUT_RECIPE_INDEX);
    }
}
