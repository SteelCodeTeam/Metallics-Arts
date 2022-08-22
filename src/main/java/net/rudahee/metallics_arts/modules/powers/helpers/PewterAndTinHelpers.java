package net.rudahee.metallics_arts.modules.powers.helpers;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

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
        return amount + 2;
    }

    public static float getDamageWithIncrement(float amount) {
        return amount + 3;
    }

    public static float getDamageWithIncrementAndMultiplier(float amount) {
        return getDamageWithIncrement(getDamageWithMultiplier(amount));
    }

    public static void addPewterEffects(PlayerEntity player) {
        player.addEffect(new EffectInstance(Effects.MOVEMENT_SPEED, 1, 1, true, false));
        player.addEffect(new EffectInstance(Effects.DAMAGE_BOOST, 1, 1, true, false));
        player.addEffect(new EffectInstance(Effects.DAMAGE_RESISTANCE, 1, 1, true, false));
    }


    public static void decantPewterEffectsFeruchemic(PlayerEntity player) {
        player.addEffect(new EffectInstance(Effects.JUMP, 1, 2, true, false));
        player.addEffect(new EffectInstance(Effects.DAMAGE_BOOST, 1, 2, true, false));
    }


    public static void addTinEffects(PlayerEntity player) {
        if(player.tickCount % 20 == 0) {
            player.addEffect(new EffectInstance(Effects.NIGHT_VISION, 320, 1, true, false, false));
        }
    }

    public static void addPewterEffectsEnhanced(PlayerEntity player) {
        player.addEffect(new EffectInstance(Effects.JUMP, 40, 6, true, false));
        player.addEffect(new EffectInstance(Effects.DIG_SPEED, 40, 3, true, false));
        player.addEffect(new EffectInstance(Effects.MOVEMENT_SPEED, 40, 3, true, false));
    }


    public static void addTinEffectsEnhanced(PlayerEntity player) {
        if(player.tickCount % 20 == 0) {
            player.addEffect(new EffectInstance(Effects.BLINDNESS, 60, 1, true, false, false));
            player.addEffect(new EffectInstance(Effects.CONFUSION, 240, 1, true, false, false));
        }
    }


    public static void addFecruchemicVision(PlayerEntity player){
        player.addEffect(new EffectInstance(Effects.NIGHT_VISION, 100, 1, true, false, false));
    }

    public static void removeFeruchemicVision(PlayerEntity player){
        player.addEffect(new EffectInstance(Effects.BLINDNESS, 60, 1, true, false, false));
    }

    public static void addSize (PlayerEntity player){

    }


}
