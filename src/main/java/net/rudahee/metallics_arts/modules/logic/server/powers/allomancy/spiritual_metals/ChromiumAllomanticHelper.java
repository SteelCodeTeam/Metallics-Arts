package net.rudahee.metallics_arts.modules.logic.server.powers.allomancy.spiritual_metals;


import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.player.IInvestedPlayerData;
import net.rudahee.metallics_arts.modules.error_handling.exceptions.PlayerException;
import net.rudahee.metallics_arts.modules.logic.server.server_events.OnDamageEvent;
import net.rudahee.metallics_arts.modules.logic.server.server_events.on_world_tick.AllomaticTick;
import net.rudahee.metallics_arts.setup.network.ModNetwork;
import net.rudahee.metallics_arts.utils.CapabilityUtils;

/**
 * helper class that contains the methods to use the allomantic Chromium
 *
 * @author SteelCode Team
 * @since 1.5.1
 *
 * @see AllomaticTick
 * @see OnDamageEvent
 */
public class ChromiumAllomanticHelper {


    /**
     * This method is responsible for emptying the allomantic reserves of the target that is attacked.
     *
     * @param target           who is attacked.
     * @param targetCapability capabilities (data) of the target.
     */
    public static void drainMetalChromium(Player target, IInvestedPlayerData targetCapability) {
        for (MetalTagEnum metalTagEnum : targetCapability.getAllomanticPowers()) {
            targetCapability.drainMetals(metalTagEnum);
            ModNetwork.syncInvestedDataPacket(targetCapability, target);
        }
    }

    /**
     * This method is responsible for clocarling nearby players and emptying their Allomantic reserves.
     *
     * @param player that is burning Chromium and Duralumin.
     * @param level in which the player is located.
     * @param lerasium if the player is burning Lerasium.
     */
    public static void drainMetalCloudChromium(Player player, Level level, boolean lerasium) {
        level.getEntitiesOfClass(Player.class, CapabilityUtils.getBubble(player, (lerasium) ? 10 : 8)).forEach(entity -> {
            if (entity != null) {
                IInvestedPlayerData targetCapability;
                try {
                    targetCapability = CapabilityUtils.getCapability(entity);
                } catch (PlayerException ex) {
                    ex.printCompleteLog();
                    return;
                }
                targetCapability.drainMetals(MetalTagEnum.values());
                ModNetwork.syncInvestedDataPacket(targetCapability, entity);
            }
        });
    }


}


