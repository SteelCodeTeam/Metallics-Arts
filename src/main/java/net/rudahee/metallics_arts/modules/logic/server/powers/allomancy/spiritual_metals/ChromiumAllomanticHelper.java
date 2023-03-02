package net.rudahee.metallics_arts.modules.logic.server.powers.allomancy.spiritual_metals;


import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.modules.logic.server.server_events.OnDamageEvent;
import net.rudahee.metallics_arts.modules.logic.server.server_events.on_world_tick.AllomaticTick;
import net.rudahee.metallics_arts.setup.network.ModNetwork;
import net.rudahee.metallics_arts.setup.registries.ModBlocksRegister;
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

    public static void drainMetalChromium(Player player) {
        player.getCapability(ModBlocksRegister.InvestedCapabilityRegister.PLAYER_CAP).ifPresent(data ->{
            for (MetalTagEnum metalTagEnum : data.getAllomanticPowers()){
                data.drainMetals(metalTagEnum);
                ModNetwork.syncInvestedDataPacket(data, player);
            }
        });
    }

    public static void drainMetalCloudChromium(ServerPlayer player, ServerLevel serverLevel, boolean lerasium) {
        serverLevel.getEntitiesOfClass(Player.class, CapabilityUtils.getBubble(player, (lerasium) ? 10 : 8)).forEach(entity -> {
            if (entity != null) {
                entity.getCapability(ModBlocksRegister.InvestedCapabilityRegister.PLAYER_CAP).ifPresent(capability -> {
                    capability.drainMetals(MetalTagEnum.values());

                    ModNetwork.syncInvestedDataPacket(capability, entity);
                });
            }
        });
    }


}


