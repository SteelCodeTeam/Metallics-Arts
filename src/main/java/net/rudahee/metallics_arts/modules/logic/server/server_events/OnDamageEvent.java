package net.rudahee.metallics_arts.modules.logic.server.server_events;

import net.minecraft.core.GlobalPos;
import net.minecraft.core.NonNullList;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.enums.implementations.custom_items.ArmorPiecesEnum;
import net.rudahee.metallics_arts.data.player.data.IInvestedPlayerData;
import net.rudahee.metallics_arts.modules.error_handling.exceptions.PlayerException;
import net.rudahee.metallics_arts.modules.logic.server.powers.allomancy.god_metals.AtiumAllomanticHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.allomancy.god_metals.MalatiumAllomanticHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.allomancy.physical_metals.PewterAllomanticHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.allomancy.spiritual_metals.ChromiumAllomanticHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.allomancy.spiritual_metals.NicrosilAllomanticHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.cognitive_metals.BrassFeruchemicHelper;
import net.rudahee.metallics_arts.setup.network.ModNetwork;
import net.rudahee.metallics_arts.setup.registries.ModItemsRegister;
import net.rudahee.metallics_arts.utils.ArmorUtils;
import net.rudahee.metallics_arts.utils.CapabilityUtils;

import java.util.Random;

/**
 * Handles damage events related to Allomantic and Feruchemical powers.
 * This class contains methods that manage the damage effects of LivingHurtEvents
 * based on the source and target players' Allomantic and Feruchemical abilities.
 *
 * @author SteelCode Team
 * @since 1.5.1
 */
public class OnDamageEvent {

    /**
     * Handles the damage effects of Allomantic powers.
     * This method is triggered when a LivingHurtEvent occurs and manages the damage
     * effects based on the source and target players' Allomantic abilities.
     *
     * @param event   The LivingHurtEvent that triggered this method.
     * @param source  The ServerPlayer who is the source of the damage.
     * @param target  The ServerPlayer who is the target of the damage.
     */
    public static void onDamageAllomantic(final LivingHurtEvent event, ServerPlayer source, ServerPlayer target) {
        try {
            IInvestedPlayerData sourceCapability = CapabilityUtils.getCapability(source);
            IInvestedPlayerData targetCapability = CapabilityUtils.getCapability(target);

            // Pewter
            if (sourceCapability.isBurning(MetalTagEnum.PEWTER)) {
                PewterAllomanticHelper.damageWithPewter(event, target, source, sourceCapability.getEnhanced());
            }

            // Chromium
            if (sourceCapability.isBurning(MetalTagEnum.CHROMIUM)) {
                ChromiumAllomanticHelper.drainMetalChromium(target, targetCapability);
            }
            // Malatium
            if (sourceCapability.isBurning(MetalTagEnum.MALATIUM)) {
                if (target.getLastDeathLocation().isPresent()) {
                    MalatiumAllomanticHelper.setPos(target.getLastDeathLocation().get());
                    ModNetwork.syncAnotherPlayerDeathPos(target.getLastDeathLocation().get(), source);
                } else {
                    MalatiumAllomanticHelper.setPos(GlobalPos.of(target.getRespawnDimension(), target.getRespawnPosition()));
                    ModNetwork.syncAnotherPlayerDeathPos(GlobalPos.of(target.getRespawnDimension(), target.getRespawnPosition()), source);
                }
                MalatiumAllomanticHelper.setPosRegistered(true);
            }

            // Nicrosil
            if (sourceCapability.isBurning(MetalTagEnum.NICROSIL)) {
                NicrosilAllomanticHelper.changeTargetEnhancedToTrue(target, targetCapability);
            }

            // Atium
            if (targetCapability.isBurning(MetalTagEnum.ATIUM)) { //todo habria que cancelar si usa atium ? ya que modificar el daño no e vita efectos negativos como el nicrosil, solo evtia daño
                event.setCanceled(AtiumAllomanticHelper.getCalculateComplexDamage(targetCapability, sourceCapability, ArmorUtils.hasAtiumArmor(target)));
            }
        } catch (PlayerException ex) {
            ex.printCompleteLog();
        }
    }

    /**
     * Handles the damage effects of Feruchemical powers.
     * This method is triggered when a LivingHurtEvent occurs and manages the damage
     * effects based on the source and target players' Feruchemical abilities.
     *
     * @param event   The LivingHurtEvent that triggered this method.
     * @param source  The ServerPlayer who is the source of the damage.
     * @param target  The ServerPlayer who is the target of the damage.
     */
    public static void onDamageFeruchemical(final LivingHurtEvent event, ServerPlayer source, ServerPlayer target) {
        try {
            IInvestedPlayerData sourceCapability = CapabilityUtils.getCapability(source);
            IInvestedPlayerData targetCapability = CapabilityUtils.getCapability(target);

            // Brass
            if (sourceCapability.isTapping(MetalTagEnum.BRASS)) {
                BrassFeruchemicHelper.addFireAspectToPlayer(event.getEntity(), 4);
            }

            //Cancel freeze damage
            if (targetCapability.isTapping(MetalTagEnum.BRASS)) {
                if (event.getSource().equals(event.getEntity().damageSources().freeze())) {
                    event.setCanceled(true);
                }
            }
        } catch (PlayerException ex) {
            ex.printCompleteLog();
        }
    }


    public static void onDamageToArmor(LivingHurtEvent event, LivingEntity entity) {
        if (event.getSource().type().msgId().contains("explosion")) {// todo mirar de mejorar este if
            if (ArmorUtils.hasEttmetalArmor(entity)) {
                event.setAmount(event.getAmount()/2);
            }
        }
        if (entity instanceof Player player) {
            try {
                IInvestedPlayerData capability = CapabilityUtils.getCapability(player);
                if (!capability.isBurning(MetalTagEnum.ATIUM) && ArmorUtils.hasAtiumArmor(player)){
                    event.setCanceled(ArmorUtils.cancelHitWithAtiumArmor());
                }
            } catch (PlayerException ex) {
                ex.printCompleteLog();
            }
        } else {
             if (ArmorUtils.hasAtiumArmor(entity)) {
                event.setCanceled(ArmorUtils.cancelHitWithAtiumArmor());
            }
        }

    }
}
