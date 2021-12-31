package net.rudahee.metallics_arts.modules.world;

import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.modules.world.OreGeneration;

@Mod.EventBusSubscriber (modid  = MetallicsArts.MOD_ID)
public class ModWorldEvent {

    @SubscribeEvent
    public static void biomeLoadingEvent (final BiomeLoadingEvent event){
        OreGeneration.generateOres(event);
    }
}
