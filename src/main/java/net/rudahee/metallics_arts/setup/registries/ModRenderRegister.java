package net.rudahee.metallics_arts.setup.registries;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.modules.logic.client.custom_renders.ModEntityRender;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = MetallicsArts.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModRenderRegister {

    @SubscribeEvent
    public static void register(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntityTypesRegister.BULLET_PROJECTILE.get(), ModEntityRender::new);
    }
}
