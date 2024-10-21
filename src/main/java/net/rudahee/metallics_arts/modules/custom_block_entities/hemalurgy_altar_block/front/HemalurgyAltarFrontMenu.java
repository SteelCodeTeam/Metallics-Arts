package net.rudahee.metallics_arts.modules.custom_block_entities.hemalurgy_altar_block.front;

import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.player.data.IInvestedPlayerData;
import net.rudahee.metallics_arts.data.player.data.model.SpikeEntity;
import net.rudahee.metallics_arts.data.player.data.model.enums.BodyPartEnum;
import net.rudahee.metallics_arts.data.player.data.model.enums.BodySlotEnum;
import net.rudahee.metallics_arts.modules.custom_items.metal_spikes.MetalSpike;
import net.rudahee.metallics_arts.modules.error_handling.exceptions.PlayerException;
import net.rudahee.metallics_arts.modules.error_handling.utils.LoggerUtils;
import net.rudahee.metallics_arts.setup.network.ModNetwork;
import net.rudahee.metallics_arts.setup.registries.ModBlocksRegister;
import net.rudahee.metallics_arts.setup.registries.ModMenuRegister;
import net.rudahee.metallics_arts.utils.CapabilityUtils;
import net.rudahee.metallics_arts.utils.HemalurgyUtils;
import org.jetbrains.annotations.NotNull;

public class HemalurgyAltarFrontMenu extends AbstractContainerMenu {

    private final Level level;
    private final ContainerData data;
    private Player player;
    private HemalurgyAltarFrontBlockEntity blockEntity;

    public HemalurgyAltarFrontMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
        this(id, inv, (HemalurgyAltarFrontBlockEntity) inv.player.level.getBlockEntity(extraData.readBlockPos()), new SimpleContainerData(0));
    }

    @SuppressWarnings("removal")
    public HemalurgyAltarFrontMenu(int id, Inventory inv, HemalurgyAltarFrontBlockEntity entity, ContainerData data) {
        super(ModMenuRegister.HEMALURGY_ALTAR_FRONT_MENU.get(), id);
        checkContainerSize(inv, 20);

        this.level = inv.player.level;
        this.player = inv.player;

        this.data = data;
        this.blockEntity = entity;
        addPlayerInventory(inv);
        addPlayerHotbar(inv);

        this.blockEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(handler -> {
            this.addSlot(new SlotItemHandler(handler, 0, -4, 32));
            this.addSlot(new SlotItemHandler(handler, 1, -4, 57));
            this.addSlot(new SlotItemHandler(handler, 2, 47, 28));
            this.addSlot(new SlotItemHandler(handler, 3, 47, 61));
            this.addSlot(new SlotItemHandler(handler, 4, 68, 32));
            this.addSlot(new SlotItemHandler(handler, 5, 68, 55));
            this.addSlot(new SlotItemHandler(handler, 6, 88, 32));
            this.addSlot(new SlotItemHandler(handler, 7, 88, 55));
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

        addDataSlots(this.data);

        initializeDataSlots();
    }

    @Override
    public void clicked(int slot, int button, @NotNull ClickType type, @NotNull Player player) {
        super.clicked(slot, button, type, player);
        if (player instanceof ServerPlayer) {
            LoggerUtils.printLogWarn("Holi: " + slot + ", " + button + ", " + type);

            Player actualPlayer = player;

            try {
                IInvestedPlayerData cap = CapabilityUtils.getCapability(actualPlayer);

                if (button == 1) {
                    return;
                }

                if (ClickType.QUICK_MOVE == type || ClickType.CLONE == type
                        || ClickType.PICKUP_ALL == type || ClickType.SWAP == type || ClickType.THROW == type) {
                    System.out.println("Haciendo nada con un click invalido");
                    return;
                }

                if (ClickType.PICKUP == type || ClickType.QUICK_CRAFT == type) {
                    if (button == 0 && (slot >= 36 && slot <= 55)) {
                        if (slots.get(slot).getItem() == ItemStack.EMPTY) {
                            actualPlayer = removeMetalFromSlot(getCarried(), slots.get(slot), cap, actualPlayer);

                        } else if (slots.get(slot).getItem().getItem() instanceof MetalSpike) {
                            actualPlayer = addMetalFromSlot(slots.get(slot), cap, actualPlayer);
                        }
                    }
                }

                if (actualPlayer == null) {
                    return;
                }

                this.player = actualPlayer;
                initializeDataSlots();

            } catch (Exception e) {
                e.printStackTrace();
            }

            player.hurt(player.damageSources().playerAttack(player), 18.0F);
            level.setBlock(blockEntity.getBlockPos(), Blocks.AIR.defaultBlockState(), 3);
            level.setBlock(blockEntity.getBlockPos(), ModBlocksRegister.HEMALURGY_ALTAR_FRONT.get().defaultBlockState(), 3);
        }
    }

    @Override
    public ItemStack quickMoveStack(Player playerIn, int index) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()),
                player, ModBlocksRegister.HEMALURGY_ALTAR_FRONT.get());
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



    private Player removeMetalFromSlot(ItemStack stack, Slot slot, IInvestedPlayerData playerData, Player player) {
        if (stack.getTag() == null) {
            return null;
        }

        if (MetalTagEnum.values()[stack.getTag().getInt("metal_spike")] == null) {
            return null;
        }
        MetalTagEnum metal = MetalTagEnum.values()[stack.getTag().getInt("metal_spike")];
        slot.setChanged();

        if (stack.getTag().getBoolean("allomantic_power")) {
            playerData.removeAllomanticPower(metal);
        } else {
            playerData.removeFeruchemicPower(metal);
        }

        System.out.println("REMOVE METAL FROM SLOT. Is server?: " + (player instanceof ServerPlayer));

        if (player instanceof ServerPlayer) {
            ModNetwork.syncInvestedDataPacket(playerData, player);
        }

        return player;
    }

    private Player addMetalFromSlot(Slot slot, IInvestedPlayerData playerData, Player player) {
        if (slot.getItem().getTag() == null) {
            return null;
        }
        if (MetalTagEnum.values()[slot.getItem().getTag().getInt("metal_spike")] == null) {
            return null;
        }
        MetalTagEnum metal = MetalTagEnum.values()[slot.getItem().getTag().getInt("metal_spike")];

        int slotIndex = slot.getSlotIndex();
        int slotNum = HemalurgyUtils.calculateSlotNumBySlotIndex(slotIndex);

        BodyPartEnum part = HemalurgyUtils.calculateBodyPartBySlotIndex(slotIndex);

        if (slot.getItem().getTag().getBoolean("allomantic_power")) {
            playerData.addAllomanticPower(metal, part, BodySlotEnum.FRONT, slotNum);
        } else {
            playerData.addFeruchemicPower(metal, part, BodySlotEnum.FRONT, slotNum);
        }

        if (player instanceof ServerPlayer) {
            ModNetwork.syncInvestedDataPacket(playerData, player);
        }

        return player;
    }

    @SuppressWarnings("removal")
    private void initializeDataSlots() {
        this.blockEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(handler -> {
            ItemStack stack = null;
            SpikeEntity spikeEntity = null;
            try {
                IInvestedPlayerData playerData = CapabilityUtils.getCapability(this.player);

                // HEAD
                spikeEntity = HemalurgyUtils.generateSpikeEntity(playerData, 0, BodySlotEnum.FRONT, BodyPartEnum.HEAD);
                if (spikeEntity != null) {
                    stack = HemalurgyUtils.generateTags(spikeEntity);
                    handler.insertItem(0, stack, false);
                }

                spikeEntity = HemalurgyUtils.generateSpikeEntity(playerData, 1, BodySlotEnum.FRONT, BodyPartEnum.HEAD);
                if (spikeEntity != null) {
                    stack = HemalurgyUtils.generateTags(spikeEntity);
                    handler.insertItem(1, stack, false);
                }

                // CHEST
                spikeEntity = HemalurgyUtils.generateSpikeEntity(playerData, 0, BodySlotEnum.FRONT, BodyPartEnum.CHEST);
                if (spikeEntity != null) {
                    stack = HemalurgyUtils.generateTags(spikeEntity);
                    handler.insertItem(2, stack, false);
                }
                spikeEntity = HemalurgyUtils.generateSpikeEntity(playerData, 1, BodySlotEnum.FRONT, BodyPartEnum.CHEST);
                if (spikeEntity != null) {
                    stack = HemalurgyUtils.generateTags(spikeEntity);
                    handler.insertItem(3, stack, false);
                }
                spikeEntity = HemalurgyUtils.generateSpikeEntity(playerData, 2, BodySlotEnum.FRONT, BodyPartEnum.CHEST);
                if (spikeEntity != null) {
                    stack = HemalurgyUtils.generateTags(spikeEntity);
                    handler.insertItem(4, stack, false);
                }
                spikeEntity = HemalurgyUtils.generateSpikeEntity(playerData, 3, BodySlotEnum.FRONT, BodyPartEnum.CHEST);
                if (spikeEntity != null) {
                    stack = HemalurgyUtils.generateTags(spikeEntity);
                    handler.insertItem(5, stack, false);
                }
                spikeEntity = HemalurgyUtils.generateSpikeEntity(playerData, 4, BodySlotEnum.FRONT, BodyPartEnum.CHEST);
                if (spikeEntity != null) {
                    stack = HemalurgyUtils.generateTags(spikeEntity);
                    handler.insertItem(6, stack, false);
                }
                spikeEntity = HemalurgyUtils.generateSpikeEntity(playerData, 5, BodySlotEnum.FRONT, BodyPartEnum.CHEST);
                if (spikeEntity != null) {
                    stack = HemalurgyUtils.generateTags(spikeEntity);
                    handler.insertItem(7, stack, false);
                }

                //ARMS
                spikeEntity = HemalurgyUtils.generateSpikeEntity(playerData, 0, BodySlotEnum.FRONT, BodyPartEnum.ARMS);
                if (spikeEntity != null) {
                    stack = HemalurgyUtils.generateTags(spikeEntity);
                    handler.insertItem(8, stack, false);
                }
                spikeEntity = HemalurgyUtils.generateSpikeEntity(playerData, 1, BodySlotEnum.FRONT, BodyPartEnum.ARMS);
                if (spikeEntity != null) {
                    stack = HemalurgyUtils.generateTags(spikeEntity);
                    handler.insertItem(9, stack, false);
                }
                spikeEntity = HemalurgyUtils.generateSpikeEntity(playerData, 2, BodySlotEnum.FRONT, BodyPartEnum.ARMS);
                if (spikeEntity != null) {
                    stack = HemalurgyUtils.generateTags(spikeEntity);
                    handler.insertItem(10, stack, false);
                }
                spikeEntity = HemalurgyUtils.generateSpikeEntity(playerData, 3, BodySlotEnum.FRONT, BodyPartEnum.ARMS);
                if (spikeEntity != null) {
                    stack = HemalurgyUtils.generateTags(spikeEntity);
                    handler.insertItem(11, stack, false);
                }
                spikeEntity = HemalurgyUtils.generateSpikeEntity(playerData, 4, BodySlotEnum.FRONT, BodyPartEnum.ARMS);
                if (spikeEntity != null) {
                    stack = HemalurgyUtils.generateTags(spikeEntity);
                    handler.insertItem(12, stack, false);
                }
                spikeEntity = HemalurgyUtils.generateSpikeEntity(playerData, 5, BodySlotEnum.FRONT, BodyPartEnum.ARMS);
                if (spikeEntity != null) {
                    stack = HemalurgyUtils.generateTags(spikeEntity);
                    handler.insertItem(13, stack, false);
                }

                // Legs
                spikeEntity = HemalurgyUtils.generateSpikeEntity(playerData, 0, BodySlotEnum.FRONT, BodyPartEnum.LEGS);
                if (spikeEntity != null) {
                    stack = HemalurgyUtils.generateTags(spikeEntity);
                    handler.insertItem(14, stack, false);
                }
                spikeEntity = HemalurgyUtils.generateSpikeEntity(playerData, 1, BodySlotEnum.FRONT, BodyPartEnum.LEGS);
                if (spikeEntity != null) {
                    stack = HemalurgyUtils.generateTags(spikeEntity);
                    handler.insertItem(15, stack, false);
                }
                spikeEntity = HemalurgyUtils.generateSpikeEntity(playerData, 2, BodySlotEnum.FRONT, BodyPartEnum.LEGS);
                if (spikeEntity != null) {
                    stack = HemalurgyUtils.generateTags(spikeEntity);
                    handler.insertItem(16, stack, false);
                }
                spikeEntity = HemalurgyUtils.generateSpikeEntity(playerData, 3, BodySlotEnum.FRONT, BodyPartEnum.LEGS);
                if (spikeEntity != null) {
                    stack = HemalurgyUtils.generateTags(spikeEntity);
                    handler.insertItem(17, stack, false);
                }
                spikeEntity = HemalurgyUtils.generateSpikeEntity(playerData, 4, BodySlotEnum.FRONT, BodyPartEnum.LEGS);
                if (spikeEntity != null) {
                    stack = HemalurgyUtils.generateTags(spikeEntity);
                    handler.insertItem(18, stack, false);
                }
                spikeEntity = HemalurgyUtils.generateSpikeEntity(playerData, 5, BodySlotEnum.FRONT, BodyPartEnum.LEGS);
                if (spikeEntity != null) {
                    stack = HemalurgyUtils.generateTags(spikeEntity);
                    handler.insertItem(19, stack, false);
                }

            } catch (PlayerException e) {
                LoggerUtils.printLogInfo("Error in HemalurgyAltarBackMenu: " + e.getMessage());
            }
        });
    }
}
