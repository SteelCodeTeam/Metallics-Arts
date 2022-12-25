package net.rudahee.metallics_arts.modules.powers.helpers.metal_helpers;


import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public class PewterAndTinHelpers {


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
        return amount * 1.3f;
    }

    public static float getDamageWithIncrement(float amount) {
        return amount + 3;
    }

    public static float getDamageWithIncrementAndMultiplier(float amount) {
        return getDamageWithIncrement(getDamageWithMultiplier(amount));
    }
    public static void decantPewterEffectsFeruchemic(Player player) {
        player.addEffect(new MobEffectInstance(MobEffects.JUMP, 5, 1, true, true));
        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 5, 1, true, true));
    }

    public static void storePewterEffectsFeruchemic(Player player) {
        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 5, 1, true, false));
        player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 5, 1, true, false));
    }

    public static void addTinEffects(Player player) {
        if(player.tickCount % 20 == 0) {
            player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 320, 1, true, false, false));
        }
    }

    public static void addPewterEffects(Player player, boolean lerasium, boolean duralumin) {
        int damage, resistence, digSpeed;
        if (!duralumin && !lerasium) {
            damage = 0;
            resistence = 1;
            digSpeed = 1;
        } else if(duralumin && !lerasium) {
            damage = 1;
            resistence = 2;
            digSpeed = 2;
        } else if(!duralumin && lerasium) {
            damage = 1;
            resistence = 1;
            digSpeed = 2;
        } else {
            damage = 4;
            resistence = 4;
            digSpeed = 4;
        }
        player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 5, digSpeed, true, true));
        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 5, damage, true, true));
        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 5, resistence, true, true));
    }

    public static void addTinEffectsEnhanced(Player player) {
        if(player.tickCount % 20 == 0) {
            player.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 60, 1, true, false, false));
            player.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 240, 1, true, false, false));
        }
    }
    public static void addFecruchemicVision(Player player){
        player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 320, 1, true, false, false));
    }

    public static void removeFeruchemicVision(Player player){
        player.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 60, 1, true, false, false));
    }

}
