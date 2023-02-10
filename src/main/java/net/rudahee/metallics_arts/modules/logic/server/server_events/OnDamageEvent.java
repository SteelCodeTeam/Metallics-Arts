package net.rudahee.metallics_arts.modules.logic.server.server_events;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.player.IInvestedPlayerData;
import net.rudahee.metallics_arts.modules.error_handling.exceptions.PlayerException;
import net.rudahee.metallics_arts.modules.logic.server.powers.allomancy.god_metals.AtiumAllomanticHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.allomancy.god_metals.MalatiumAllomanticHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.allomancy.physical_metals.PewterAllomanticHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.allomancy.spiritual_metals.ChromiumAllomanticHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.allomancy.spiritual_metals.NicrosilAllomanticHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.cognitive_metals.BrassFecuchemicHelper;
import net.rudahee.metallics_arts.utils.CapabilityUtils;

public class OnDamageEvent {

    /**
     * Brings out the damage effects of Allomantic powers
     *
     * @param event to damage,
     * @param source of damage.
     * @param target of damage.
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
                ChromiumAllomanticHelper.drainMetalChromium((Player) event.getEntity());
            }
            // Malatium
            if (sourceCapability.isBurning(MetalTagEnum.MALATIUM)) {
                MalatiumAllomanticHelper.setPos(((Player) event.getEntity()).getLastDeathLocation().get());
            }

            // Nicrosil
            if (sourceCapability.isBurning(MetalTagEnum.NICROSIL)) {
                NicrosilAllomanticHelper.changeTargetEnhancedToTrue((Player) event.getEntity());
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
     * Brings out the damage effects of Feruchemical powers.
     *
     * @param event to damage,
     * @param source of damage.
     * @param target of damage.
     */
    public static void onDamageFeruchemical(final LivingHurtEvent event, ServerPlayer source, ServerPlayer target) {
        try {
            IInvestedPlayerData sourceCapability = CapabilityUtils.getCapability(source);
            IInvestedPlayerData targetCapability = CapabilityUtils.getCapability(target);

            // Brass
            if (sourceCapability.isTapping(MetalTagEnum.BRASS)) {
                BrassFecuchemicHelper.addFireAspectToPlayer(event.getEntity(), 4);
            }

            //Cancel freeze damage
            if (targetCapability.isTapping(MetalTagEnum.BRASS)) {
                if (event.getSource().equals(DamageSource.FREEZE)) {
                    event.setCanceled(true);
                }
            }
        } catch (PlayerException ex) {
            ex.printCompleteLog();
        }
    }
}
