package net.rudahee.metallics_arts.modules.logic.server.powers.allomancy.physical_metals;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.rudahee.metallics_arts.modules.custom_items.weapons.mele.KolossBlade;
import net.rudahee.metallics_arts.modules.logic.server.server_events.OnDamageEvent;
import net.rudahee.metallics_arts.modules.logic.server.server_events.on_world_tick.AllomaticTick;
import net.rudahee.metallics_arts.setup.registries.ModItemsRegister;

/**
 * Helper class that contains the methods to use the allomantic Pewter
 *
 * @author SteelCode Team
 * @since 1.5.1
 *
 * @see AllomaticTick
 * @see OnDamageEvent
 */
public class PewterAllomanticHelper {

    /**
     * This method adds the necessary effects to physically unbalance the player.
     *
     * @param player to whom the effect will be applied.
     * @param lerasium if the player is burning Lerasium.
     * @param enhanced if player is burning Duralumin or the player was hit with Nicrosil.
     */
    public static void addPewterEffects(Player player, boolean lerasium, boolean enhanced) {
        int damage, resistence, digSpeed;

        if (!enhanced && !lerasium) {
            damage = 0;
            resistence = 1;
            digSpeed = 1;
        } else if(enhanced && !lerasium) {
            damage = 1;
            resistence = 2;
            digSpeed = 2;
        } else if(!enhanced) {
            damage = 1;
            resistence = 1;
            digSpeed = 2;
        } else {
            damage = 4;
            resistence = 4;
            digSpeed = 4;
        }


        player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 5, digSpeed, false, false, false));

        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 5, damage, false,false, false));
        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 5, resistence,false, false, false));

    }

    /**
     * This method adds extra interactions to the pewter, depending on what weapon it uses to deal damage.
     *
     * @param event of damage.
     * @param source of damage.
     * @param target of damage.
     * @param enhanced if player is burning Duralumin or the player was hit with Nicrosil.
     */
    public static void damageWithPewter(LivingHurtEvent event, ServerPlayer target, ServerPlayer source, boolean enhanced) {

        float amountDamage = event.getAmount();

        ItemStack itemInHand = source.getMainHandItem();

        if (itemInHand.getItem() == ModItemsRegister.DUELING_STAFF.get()) {
            amountDamage = amountDamage * (((float) itemInHand.getDamageValue() / (float) itemInHand.getMaxDamage()) * 8.2f);
        }

        if (itemInHand.getItem() == ModItemsRegister.SILVER_KNIFE.get()) {
            if (Math.random() < 0.10d) {
                amountDamage = amountDamage * 2;
            }
        }

        if (itemInHand.getItem() == ModItemsRegister.OBSIDIAN_DAGGER.get()) {
            if (Math.random() < 0.30d) {
                target.addEffect(new MobEffectInstance(MobEffects.WITHER, 41, 1, false, false, false));
            }
        }

        if (itemInHand.getItem() == ModItemsRegister.OBSIDIAN_AXE.get()) {
            if (Math.random() < 0.50d) {
                target.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 40, 1, true, true, false));
                target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 40, 1, true, true, false));
                target.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 40, 1, true, true, false));
                target.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 60, 2, true, true, false));
            }
        }

        if (enhanced) {
            if (itemInHand.getItem() == ModItemsRegister.KOLOSS_BLADE.get()) {
                //target.setHealth(2f);
                //amountDamage = 0;
                source.sendSystemMessage(Component.literal("Buen intento, pero está desactivado temporalmente :)"));
            }
            event.setAmount(amountDamage);
        }
    }
}
