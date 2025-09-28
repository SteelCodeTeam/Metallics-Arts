package net.rudahee.metallics_arts.utils;

import net.minecraft.advancements.Advancement;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.enums.implementations.custom_items.SpikeEnum;
import net.rudahee.metallics_arts.data.player.data.IInvestedPlayerData;
import net.rudahee.metallics_arts.data.player.data.model.SpikeEntity;
import net.rudahee.metallics_arts.data.player.data.model.enums.BodyPartEnum;
import net.rudahee.metallics_arts.data.player.data.model.enums.BodySlotEnum;
import net.rudahee.metallics_arts.modules.error_handling.utils.LoggerUtils;

public class HemalurgyUtils {

    public static ItemStack generateTags(SpikeEntity spikeEntity) {
        if (spikeEntity != null) {
            ItemStack stack = new ItemStack(SpikeEnum.valueOf(spikeEntity.getMetal().getMetalNameUpper()).getSpike());

            if (!stack.getTag().contains("metal_spike") || !stack.getTag().contains("feruchemic_power") || !stack.getTag().contains("allomantic_power")) {
                stack.setTag(HemalurgyUtils.generateDefaultTags(stack, spikeEntity.getMetal().getIndex()));
            }
            stack.getTag().putBoolean(spikeEntity.getType().getType() + "_power", true);

            return stack;
        } else {
            return ItemStack.EMPTY;
        }
    }

    public static SpikeEntity generateSpikeEntity(IInvestedPlayerData playerData, int slotNum, BodySlotEnum slotType, BodyPartEnum part) {
        if (part == BodyPartEnum.HEAD) {
            return playerData.getPlayerData().getHead().getSpikeBySlot(slotNum, slotType);
        } else if (part == BodyPartEnum.BACK) {
            return playerData.getPlayerData().getBack().getSpikeBySlot(slotNum, slotType);
        } else if (part == BodyPartEnum.CHEST) {
            return playerData.getPlayerData().getChest().getSpikeBySlot(slotNum, slotType);
        } else if (part == BodyPartEnum.ARMS) {
            return playerData.getPlayerData().getArms().getSpikeBySlot(slotNum, slotType);
        } else if (part == BodyPartEnum.LEGS) {
            return playerData.getPlayerData().getLegs().getSpikeBySlot(slotNum, slotType);
        } else {
            LoggerUtils.printLogInfo("Error in HemalurgyAltarBackMenu: generateSpikeEntity: part not found");
            return null;
        }
    }

    public static CompoundTag generateDefaultTags(ItemStack stack, int index) {
        stack.getTag().putInt("metal_spike",index);
        stack.getTag().putBoolean("feruchemic_power",false);
        stack.getTag().putBoolean("allomantic_power",false);
        return stack.getTag();
    }

    public static int calculateSlotNumBySlotIndex(int slotIndex) {
        if (slotIndex == 0) {
            return 0;
        } else if (slotIndex == 1) {
            return 1;
        } else if (slotIndex >= 2 && slotIndex <= 7) {
            return slotIndex - 2;
        } else if (slotIndex >= 8 && slotIndex <= 13) {
            return slotIndex - 8;
        } else if (slotIndex >= 14 && slotIndex <= 19) {
            return slotIndex - 14;
        } else {
            return -1;
        }
    }
    public static BodyPartEnum calculateBodyPartBySlotIndex(int slotIndex) {
        if (slotIndex >= 0 && slotIndex <= 1) {
            return BodyPartEnum.HEAD;
        } else if (slotIndex >= 2 && slotIndex <= 7) {
            return BodyPartEnum.CHEST;
        } else if (slotIndex >= 8 && slotIndex <= 13) {
            return BodyPartEnum.ARMS;
        } else if (slotIndex >= 14 && slotIndex <= 19) {
            return BodyPartEnum.LEGS;
        } else {
            return null;
        }
    }

    public static void giveAdvancements(MetalTagEnum metal, boolean isAllomancy, ServerPlayer serverPlayer){

        String logro = isAllomancy ? "allomancy_power_"+metal.getNameLower() : "feruchemy_power_"+metal.getNameLower();

        Advancement adv = serverPlayer.server.getAdvancements().getAdvancement(new ResourceLocation(MetallicsArts.MOD_ID, logro));

        if (adv != null) {
            boolean changed = serverPlayer.getAdvancements().award(adv, "Poder adquirido");
        }

    }
}
