package net.rudahee.metallics_arts.setup.registries;

import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.modules.custom_entities.alomancer_entity.AlomancerEntityModel;
import net.rudahee.metallics_arts.modules.custom_entities.alomancer_entity.AlomancerEntityRenderer;
import net.rudahee.metallics_arts.modules.custom_entities.example_entity.ExampleEntityModel;
import net.rudahee.metallics_arts.modules.custom_entities.example_entity.ExampleEntityRenderer;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = MetallicsArts.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModRenderRegister {

    @SubscribeEvent
    public static void register(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntityTypesRegister.BULLET_PROJECTILE.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(ModEntityTypesRegister.EXAMPLE.get(), ExampleEntityRenderer::new);
        event.registerEntityRenderer(ModLivingEntityRegister.ALOMANCER.get(), AlomancerEntityRenderer::new);
    }

    @SubscribeEvent
    public static void registerLayerDefinition(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ExampleEntityModel.LAYER_LOCATION, ExampleEntityModel::createBodyLayer);
        event.registerLayerDefinition(AlomancerEntityModel.LAYER_LOCATION, AlomancerEntityModel::createBodyLayer);
    }

}
