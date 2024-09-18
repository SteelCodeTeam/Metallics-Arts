package net.rudahee.metallics_arts.modules.custom_block_entities.hemalurgy_altar_block.back;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.rudahee.metallics_arts.data.player.data.IInvestedPlayerData;
import net.rudahee.metallics_arts.data.player.data.model.SpikeEntity;
import net.rudahee.metallics_arts.data.player.data.model.enums.BodyPartEnum;
import net.rudahee.metallics_arts.data.player.data.model.enums.BodySlotEnum;
import net.rudahee.metallics_arts.modules.error_handling.exceptions.PlayerException;
import net.rudahee.metallics_arts.modules.error_handling.utils.LoggerUtils;
import net.rudahee.metallics_arts.setup.registries.ModBlocksRegister;
import net.rudahee.metallics_arts.setup.registries.ModMenuRegister;
import net.rudahee.metallics_arts.utils.CapabilityUtils;
import net.rudahee.metallics_arts.utils.HemalurgyUtils;

public class HemalurgyAltarBackMenu extends AbstractContainerMenu {

    private final Level level;

    private final Player player;

    private final ContainerData data;
    private HemalurgyAltarBackBlockEntity blockEntity;

    public HemalurgyAltarBackMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
        this(id, inv, (HemalurgyAltarBackBlockEntity) inv.player.level.getBlockEntity(extraData.readBlockPos()), new SimpleContainerData(0));
    }

    @SuppressWarnings("removal")
    public HemalurgyAltarBackMenu(int id, Inventory inv, HemalurgyAltarBackBlockEntity entity, ContainerData data) {
        super(ModMenuRegister.HEMALURGY_ALTAR_BACK_MENU.get(), id);
        checkContainerSize(inv, 20);

        this.player = inv.player;
        this.level = inv.player.level;
        this.data = data;
        this.blockEntity = entity;
        addPlayerInventory(inv);
        addPlayerHotbar(inv);

        this.blockEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(handler -> {
            this.addSlot(new SlotItemHandler(handler, 0, -9, 44));
            this.addSlot(new SlotItemHandler(handler, 1, 14, 44));
            this.addSlot(new SlotItemHandler(handler, 2, 33, 44));
            this.addSlot(new SlotItemHandler(handler, 3, 53, 44));
            this.addSlot(new SlotItemHandler(handler, 4, 74, 44));
            this.addSlot(new SlotItemHandler(handler, 5, 95, 44));
            this.addSlot(new SlotItemHandler(handler, 6, 47, 25));
            this.addSlot(new SlotItemHandler(handler, 7, 47, 63));
            this.addSlot(new SlotItemHandler(handler, 8, 40, 1));
            this.addSlot(new SlotItemHandler(handler, 9, 63, 1));
            this.addSlot(new SlotItemHandler(handler, 10, 86, 1));
            this.addSlot(new SlotItemHandler(handler, 11, 40, 88));
            this.addSlot(new SlotItemHandler(handler, 12, 63, 88));
            this.addSlot(new SlotItemHandler(handler, 13, 86, 88));
            this.addSlot(new SlotItemHandler(handler, 14, 122, 30));
            this.addSlot(new SlotItemHandler(handler, 15, 145, 30));
            this.addSlot(new SlotItemHandler(handler, 16, 168, 30));
            this.addSlot(new SlotItemHandler(handler, 17, 122, 57));
            this.addSlot(new SlotItemHandler(handler, 18, 145, 57));
            this.addSlot(new SlotItemHandler(handler, 19, 168, 57));
        });

        addDataSlots(data);

        this.blockEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(handler -> {
            ItemStack stack = null;
            SpikeEntity spikeEntity = null;
            try {
                IInvestedPlayerData playerData = CapabilityUtils.getCapability(player);

                spikeEntity = HemalurgyUtils.generateSpikeEntity(playerData, 0, BodySlotEnum.BACK, BodyPartEnum.HEAD);

                if (spikeEntity != null) {
                    stack = HemalurgyUtils.generateTags(spikeEntity);
                    handler.insertItem(0, stack, false);
                }
            } catch (PlayerException e) {
                LoggerUtils.printLogInfo("Error in HemalurgyAltarBackMenu: " + e.getMessage());
            }
        });
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
    private static final int TE_INVENTORY_SLOT_COUNT = 20;  // must be the number of slots you have!

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
                player, ModBlocksRegister.HEMALURGY_ALTAR_BACK.get());
    }

    private void addPlayerInventory(Inventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 6 + l * 18, 131 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 6 + i * 18, 189));
        }
    }

}
