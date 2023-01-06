package net.rudahee.metallics_arts.modules.logic.server.powers.allomancy;


import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.setup.network.ModNetwork;
import net.rudahee.metallics_arts.setup.registries.ModBlocksRegister;

public class ChromiumAllomanticHelper {

    public static void drainMetalChromium(Player entityLiving) {
        entityLiving.getCapability(ModBlocksRegister.InvestedCapabilityRegister.PLAYER_CAP).ifPresent(data ->{
            for (MetalTagEnum metalTagEnum : data.getAllomanticPowers()){
                data.drainMetals(metalTagEnum);
                ModNetwork.sync(data, entityLiving);
            }
        });
    }

    public static void drainMetalCloudChromium(ServerLevel world, AABB axisAlignedBB) {
        world.getEntitiesOfClass(Player.class, axisAlignedBB).forEach(entity -> {
            if (entity != null) {
                entity.getCapability(ModBlocksRegister.InvestedCapabilityRegister.PLAYER_CAP).ifPresent(capability -> {
                    capability.drainMetals(MetalTagEnum.values());

                    ModNetwork.sync(capability, entity);
                });
            }
        });
    }


}


