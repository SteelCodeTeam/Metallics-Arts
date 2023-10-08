package net.rudahee.metallics_arts.modules.custom_block_entities.hemalurgy_altar_block;

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

public class HemalurgyAltarMenu extends AbstractContainerMenu {

    public final HemalurgyAltarBlockEntity blockEntity;
    private final Level level;
    private final ContainerData data;

    public HemalurgyAltarMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
        this(id, inv, (HemalurgyAltarBlockEntity) inv.player.level.getBlockEntity(extraData.readBlockPos()), new SimpleContainerData(0));
    }

    @SuppressWarnings("removal")
    public HemalurgyAltarMenu(int id, Inventory inv, HemalurgyAltarBlockEntity entity, ContainerData data) {
        super(ModMenuRegister.HEMALUGY_ALTAR_MENU.get(), id);
        checkContainerSize(inv, 40);

        this.level = inv.player.level;
        this.data = data;
        this.blockEntity = entity;
        addPlayerInventory(inv);
        addPlayerHotbar(inv);

        this.blockEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(handler -> {
            this.addSlot(new SlotItemHandler(handler, 0, -6, 17));
            this.addSlot(new SlotItemHandler(handler, 1, -6, 36));
            this.addSlot(new SlotItemHandler(handler, 2, 14, 1));
            this.addSlot(new SlotItemHandler(handler, 3, 21, 1));
            this.addSlot(new SlotItemHandler(handler, 4, 28, 1));
            this.addSlot(new SlotItemHandler(handler, 5, 35, 1));
            this.addSlot(new SlotItemHandler(handler, 6, 42, 1));
            this.addSlot(new SlotItemHandler(handler, 7, 49, 1));
            this.addSlot(new SlotItemHandler(handler, 8, 56, 1));
            this.addSlot(new SlotItemHandler(handler, 9, 63, 1));
            this.addSlot(new SlotItemHandler(handler, 10, 70, 1));
            this.addSlot(new SlotItemHandler(handler, 11, 77, 1));
            this.addSlot(new SlotItemHandler(handler, 12, 84, 1));
            this.addSlot(new SlotItemHandler(handler, 13, 91, 1));
            this.addSlot(new SlotItemHandler(handler, 14, 98, 1));
            this.addSlot(new SlotItemHandler(handler, 15, 105, 1));
            this.addSlot(new SlotItemHandler(handler, 16, 112, 1));
            this.addSlot(new SlotItemHandler(handler, 17, 1, 7));
            this.addSlot(new SlotItemHandler(handler, 18, 7, 7));
            this.addSlot(new SlotItemHandler(handler, 19, 14, 7));
            this.addSlot(new SlotItemHandler(handler, 20, 21, 7));
            this.addSlot(new SlotItemHandler(handler, 21, 28, 7));
            this.addSlot(new SlotItemHandler(handler, 22, 35, 7));
            this.addSlot(new SlotItemHandler(handler, 23, 42, 7));
            this.addSlot(new SlotItemHandler(handler, 24, 49, 7));
            this.addSlot(new SlotItemHandler(handler, 25, 56, 7));
            this.addSlot(new SlotItemHandler(handler, 26, 63, 7));
            this.addSlot(new SlotItemHandler(handler, 27, 70, 7));
            this.addSlot(new SlotItemHandler(handler, 28, 77, 7));
            this.addSlot(new SlotItemHandler(handler, 29, 84, 7));
            this.addSlot(new SlotItemHandler(handler, 30, 91, 7));
            this.addSlot(new SlotItemHandler(handler, 31, 105, 7));
            this.addSlot(new SlotItemHandler(handler, 32, 112, 7));
            this.addSlot(new SlotItemHandler(handler, 33, 0, 14));
            this.addSlot(new SlotItemHandler(handler, 34, 1, 14));
            this.addSlot(new SlotItemHandler(handler, 35, 14, 14));
            this.addSlot(new SlotItemHandler(handler, 36, 21, 14));
            this.addSlot(new SlotItemHandler(handler, 37, 28, 14));
            this.addSlot(new SlotItemHandler(handler, 38, 35, 14));
            this.addSlot(new SlotItemHandler(handler, 39, 42, 14));

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
    private static final int TE_INVENTORY_SLOT_COUNT = 40;  // must be the number of slots you have!

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
                player, ModBlocksRegister.HEMALURGY_ALTAR.get());
    }

    private void addPlayerInventory(Inventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 7 + l * 18, 129 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 7 + i * 18, 199));
        }
    }

}
