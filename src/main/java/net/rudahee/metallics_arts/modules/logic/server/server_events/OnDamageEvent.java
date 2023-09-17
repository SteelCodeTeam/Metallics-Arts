package net.rudahee.metallics_arts.modules.logic.server.server_events;

import net.minecraft.core.GlobalPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.player.data.IInvestedPlayerData;
import net.rudahee.metallics_arts.modules.error_handling.exceptions.PlayerException;
import net.rudahee.metallics_arts.modules.logic.server.powers.allomancy.god_metals.AtiumAllomanticHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.allomancy.god_metals.MalatiumAllomanticHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.allomancy.physical_metals.PewterAllomanticHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.allomancy.spiritual_metals.ChromiumAllomanticHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.allomancy.spiritual_metals.NicrosilAllomanticHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.cognitive_metals.BrassFeruchemicHelper;
import net.rudahee.metallics_arts.setup.network.ModNetwork;
import net.rudahee.metallics_arts.utils.CapabilityUtils;

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
            if (targetCapability.isBurning(MetalTagEnum.ATIUM)) {
                event.setAmount(AtiumAllomanticHelper.getCalculateComplexDamage(targetCapability, sourceCapability, event.getAmount()));
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
}
