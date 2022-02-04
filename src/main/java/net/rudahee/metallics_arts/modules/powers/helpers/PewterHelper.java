package net.rudahee.metallics_arts.modules.powers.helpers;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class PewterHelper {


    public static float getExtraDamageWithItemInHand(Item itemInHand) {
        if (itemInHand.getDescriptionId().equals(Items.DIAMOND_AXE.getDescriptionId())) {
            return 20;
        } else if (itemInHand.getDescriptionId().equals(Items.DIAMOND_HOE.getDescriptionId())) {
            return -30;
        } else {
            return 0;
        }
    }

    public static float getDamageWithMultiplier(float amount) {
        return amount + 2;
    }

    public static float getDamageWithIncrement(float amount) {
        return amount + 3;
    }

    public static float getDamageWithIncrementAndMultiplier(float amount) {
        return getDamageWithIncrement(getDamageWithMultiplier(amount));
    }
}
