package net.rudahee.metallics_arts.modules.logic.server.powers.allomancy.physical_metals;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.rudahee.metallics_arts.setup.registries.ModItemsRegister;

public class PewterAllomanticHelper {
    /**
     *
     * @param player to whom the effect will be applied.
     * @param lerasium if player is burning lerasium
     * @param duralumin if player is burning duralumin
     */
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
        } else if(!duralumin) {
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


    public static void damageWithPewter(LivingHurtEvent event, ServerPlayer target, ServerPlayer source, boolean enhanced) {

        float amountDamage = event.getAmount();

        ItemStack itemInHand = source.getMainHandItem();

        if (itemInHand.getItem() == ModItemsRegister.DUELING_STAFF.get()) {

            amountDamage = amountDamage * (((float) itemInHand.getDamageValue() / (float) itemInHand.getMaxDamage()) * 3.2f);
        }

        if (itemInHand.getItem() == ModItemsRegister.CRISTAL_DAGGER.get()) {
            if (Math.random() < 0.10d) {
                amountDamage = amountDamage * 2;
            }
        }

        if (itemInHand.getItem() == ModItemsRegister.OBSIDIAN_DAGGER.get()) {
            if (Math.random() < 0.30d) {
                target.addEffect(new MobEffectInstance(MobEffects.WITHER, 41, 1, true, true, false));
            }
        }
        if (itemInHand.getItem() == ModItemsRegister.OBSIDIAN_AXE.get()) {
            if (Math.random() < 0.50d) {
                target.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 20, 1, true, true, false));
                target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 20, 1, true, true, false));
                target.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 20, 1, true, true, false));
                target.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 60, 2, true, true, false));
            }
        }

        if (enhanced) {
            if (itemInHand.getItem() == ModItemsRegister.KOLOSS_BLADE.get()) {
                target.setHealth(2f);
                amountDamage = 0;
            }

            event.setAmount(amountDamage);
        }
    }
}
