package net.rudahee.metallics_arts.setup.registries;

import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.modules.custom_entities.ettmetal_allomancer_entity.EttmetalAllomancerEntityModel;
import net.rudahee.metallics_arts.modules.custom_entities.ettmetal_allomancer_entity.EttmetalAllomancerEntityRenderer;
import net.rudahee.metallics_arts.modules.custom_entities.gold_ferrin_entity.GoldFerrinEntityModel;
import net.rudahee.metallics_arts.modules.custom_entities.gold_ferrin_entity.GoldFerrinEntityRenderer;
import net.rudahee.metallics_arts.modules.custom_entities.haze_killer_entity.HazeKillerEntityModel;
import net.rudahee.metallics_arts.modules.custom_entities.haze_killer_entity.HazeKillerEntityRenderer;
import net.rudahee.metallics_arts.modules.custom_entities.iron_allomancer_entity.IronAllomancerEntityModel;
import net.rudahee.metallics_arts.modules.custom_entities.iron_allomancer_entity.IronAllomancerEntityRenderer;
import net.rudahee.metallics_arts.modules.custom_entities.iron_ferrin_entity.IronFerrinEntityModel;
import net.rudahee.metallics_arts.modules.custom_entities.iron_ferrin_entity.IronFerrinEntityRenderer;
import net.rudahee.metallics_arts.modules.custom_entities.pewter_allomancer_entity.PewterAllomancerEntityModel;
import net.rudahee.metallics_arts.modules.custom_entities.pewter_allomancer_entity.PewterAllomancerEntityRenderer;
import net.rudahee.metallics_arts.modules.custom_entities.steel_allomancer_entity.SteelAllomancerEntityModel;
import net.rudahee.metallics_arts.modules.custom_entities.steel_allomancer_entity.SteelAllomancerEntityRenderer;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = MetallicsArts.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModRenderRegister {

    @SubscribeEvent
    public static void register(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntityTypesRegister.BULLET_PROJECTILE.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(ModEntityTypesRegister.COIN_PROJECTILE.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(ModLivingEntityRegister.ETTMETAL_ALLOMANCER.get(), EttmetalAllomancerEntityRenderer::new);
        event.registerEntityRenderer(ModLivingEntityRegister.IRON_ALLOMANCER.get(), IronAllomancerEntityRenderer::new);
        event.registerEntityRenderer(ModLivingEntityRegister.STEEL_ALLOMANCER.get(), SteelAllomancerEntityRenderer::new);
        event.registerEntityRenderer(ModLivingEntityRegister.PEWTER_ALLOMANCER.get(), PewterAllomancerEntityRenderer::new);
        event.registerEntityRenderer(ModLivingEntityRegister.HAZE_KILLER.get(), HazeKillerEntityRenderer::new);
        event.registerEntityRenderer(ModLivingEntityRegister.GOLD_FERRIN.get(), GoldFerrinEntityRenderer::new);
        event.registerEntityRenderer(ModLivingEntityRegister.IRON_FERRIN.get(), IronFerrinEntityRenderer::new);

    }

    @SubscribeEvent
    public static void registerLayerDefinition(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(EttmetalAllomancerEntityModel.LAYER_LOCATION, EttmetalAllomancerEntityModel::createBodyLayer);
        event.registerLayerDefinition(IronAllomancerEntityModel.LAYER_LOCATION, IronAllomancerEntityModel::createBodyLayer);
        event.registerLayerDefinition(SteelAllomancerEntityModel.LAYER_LOCATION, SteelAllomancerEntityModel::createBodyLayer);
        event.registerLayerDefinition(PewterAllomancerEntityModel.LAYER_LOCATION, PewterAllomancerEntityModel::createBodyLayer);
        event.registerLayerDefinition(HazeKillerEntityModel.LAYER_LOCATION, HazeKillerEntityModel::createBodyLayer);
        event.registerLayerDefinition(GoldFerrinEntityModel.LAYER_LOCATION, GoldFerrinEntityModel::createBodyLayer);
        event.registerLayerDefinition(IronFerrinEntityModel.LAYER_LOCATION, IronFerrinEntityModel::createBodyLayer);
    }

}
