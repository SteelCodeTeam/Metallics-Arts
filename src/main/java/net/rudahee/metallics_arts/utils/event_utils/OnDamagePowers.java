package net.rudahee.metallics_arts.utils.event_utils;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.players.IInvestedPlayerData;
import net.rudahee.metallics_arts.modules.logic.server.powers.allomancy.AtiumAllomanticHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.allomancy.ChromiumAllomanticHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.GoldAndElectrumHelpers;
import net.rudahee.metallics_arts.modules.logic.server.powers.allomancy.NicrosilAllomanticHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.BrassFecuchemicHelper;
import net.rudahee.metallics_arts.setup.registries.ModItemsRegister;
import net.rudahee.metallics_arts.utils.CapabilityUtils;

public class OnDamagePowers {

    public static void onDamageAllomantic(final LivingHurtEvent event, ServerPlayer source, ServerPlayer target) {

        IInvestedPlayerData sourceCapability = CapabilityUtils.getCapability(source);
        IInvestedPlayerData targetCapability = CapabilityUtils.getCapability(target);

        /*******************************
         *   DAMAGE WITH - PEWTER -
         *******************************/
        if (sourceCapability.isBurning(MetalTagEnum.PEWTER)) {
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
            if (sourceCapability.getEnhanced()) {
                if (itemInHand.getItem() == ModItemsRegister.KOLOSS_BLADE.get()) {
                    target.setHealth(2f);
                    amountDamage = 0;
                }
            }
            event.setAmount(amountDamage);
        }

        /*******************************
         *   DAMAGE WITH - CHROMIUM -
         *******************************/
        if (sourceCapability.isBurning(MetalTagEnum.CHROMIUM)) {
            if (target instanceof Player) {
                ChromiumAllomanticHelper.drainMetalChromium((Player) event.getEntity());
            }
        }
        /*******************************
         *   DAMAGE WITH - MALATIUM -
         *******************************/
        if (sourceCapability.isBurning(MetalTagEnum.MALATIUM)) {
            if (event.getEntity() instanceof Player) {
                GoldAndElectrumHelpers.takeDeathPosToObjetive((Player) event.getEntity());
            }
        }

        /********************************
         * DAMAGE WITH NICROSIL
         *******************************/
        if (sourceCapability.isBurning(MetalTagEnum.NICROSIL)) {
            if (target instanceof Player) {
                NicrosilAllomanticHelper.changeNBTinTargetForEnhanced((Player) event.getEntity());
            }
        }

        /********************************
         * DAMAGE WITH ATIUM
         *******************************/
        if (target instanceof Player) {
            if (targetCapability.isBurning(MetalTagEnum.ATIUM) ){
                if (source instanceof Player) {
                    event.setAmount(AtiumAllomanticHelper.getCalculateComplexDamage(targetCapability,sourceCapability,event.getAmount()));
                } else {
                    event.setAmount(AtiumAllomanticHelper.getCalculateSimpleDamage(targetCapability,event.getAmount()));
                }
            }
        }
}

    public static void onDamageFeruchemical(final LivingHurtEvent event, ServerPlayer source, ServerPlayer target) {

        IInvestedPlayerData sourceCapability = CapabilityUtils.getCapability(source);
        IInvestedPlayerData targetCapability = CapabilityUtils.getCapability(target);

        /*******************************
         *   DAMAGE WITH - BRASS
         *******************************/
        if (sourceCapability.isDecanting(MetalTagEnum.BRASS)) {
            BrassFecuchemicHelper.addFireAspectToPlayer(event.getEntity(), 4);
        }

        //cancel Freeze damage
        if (targetCapability.isDecanting(MetalTagEnum.BRASS)) {
            if (event.getSource().equals(DamageSource.FREEZE)) {
                event.setCanceled(true);
            }
        }

    }
}
