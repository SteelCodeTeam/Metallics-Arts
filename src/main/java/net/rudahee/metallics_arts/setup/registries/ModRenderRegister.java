package net.rudahee.metallics_arts.setup.registries;

import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.modules.custom_entities.allomancer.ettmetal_allomancer_entity.EttmetalAllomancerEntityModel;
import net.rudahee.metallics_arts.modules.custom_entities.allomancer.ettmetal_allomancer_entity.EttmetalAllomancerEntityRenderer;
import net.rudahee.metallics_arts.modules.custom_entities.ferrin.gold_ferrin_entity.GoldFerrinEntityModel;
import net.rudahee.metallics_arts.modules.custom_entities.ferrin.gold_ferrin_entity.GoldFerrinEntityRenderer;
import net.rudahee.metallics_arts.modules.custom_entities.haze_killer.haze_killer_melee_entity.HazeKillerMeleeEntityModel;
import net.rudahee.metallics_arts.modules.custom_entities.haze_killer.haze_killer_melee_entity.HazeKillerMeleeEntityRenderer;
import net.rudahee.metallics_arts.modules.custom_entities.haze_killer.haze_killer_ranged_entity.HazeKillerRangedEntityModel;
import net.rudahee.metallics_arts.modules.custom_entities.haze_killer.haze_killer_ranged_entity.HazeKillerRangedEntityRenderer;
import net.rudahee.metallics_arts.modules.custom_entities.haze_killer.haze_killer_tank_entity.HazeKillerTankEntityModel;
import net.rudahee.metallics_arts.modules.custom_entities.haze_killer.haze_killer_tank_entity.HazeKillerTankEntityRenderer;
import net.rudahee.metallics_arts.modules.custom_entities.allomancer.iron_allomancer_entity.IronAllomancerEntityModel;
import net.rudahee.metallics_arts.modules.custom_entities.allomancer.iron_allomancer_entity.IronAllomancerEntityRenderer;
import net.rudahee.metallics_arts.modules.custom_entities.ferrin.iron_ferrin_entity.IronFerrinEntityModel;
import net.rudahee.metallics_arts.modules.custom_entities.ferrin.iron_ferrin_entity.IronFerrinEntityRenderer;
import net.rudahee.metallics_arts.modules.custom_entities.allomancer.pewter_allomancer_entity.PewterAllomancerEntityModel;
import net.rudahee.metallics_arts.modules.custom_entities.allomancer.pewter_allomancer_entity.PewterAllomancerEntityRenderer;
import net.rudahee.metallics_arts.modules.custom_entities.allomancer.steel_allomancer_entity.SteelAllomancerEntityModel;
import net.rudahee.metallics_arts.modules.custom_entities.allomancer.steel_allomancer_entity.SteelAllomancerEntityRenderer;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = MetallicsArts.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModRenderRegister {
    private ModRenderRegister() {
        throw new IllegalStateException("Class can't be instantiated");
    }

    @SubscribeEvent
    public static void register(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntityTypesRegister.BULLET_PROJECTILE.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(ModEntityTypesRegister.COIN_PROJECTILE.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(ModLivingEntityRegister.ETTMETAL_ALLOMANCER.get(), EttmetalAllomancerEntityRenderer::new);
        event.registerEntityRenderer(ModLivingEntityRegister.IRON_ALLOMANCER.get(), IronAllomancerEntityRenderer::new);
        event.registerEntityRenderer(ModLivingEntityRegister.STEEL_ALLOMANCER.get(), SteelAllomancerEntityRenderer::new);
        event.registerEntityRenderer(ModLivingEntityRegister.PEWTER_ALLOMANCER.get(), PewterAllomancerEntityRenderer::new);
        event.registerEntityRenderer(ModLivingEntityRegister.HAZE_KILLER_MELEE.get(), HazeKillerMeleeEntityRenderer::new);
        event.registerEntityRenderer(ModLivingEntityRegister.HAZE_KILLER_RANGED.get(), HazeKillerRangedEntityRenderer::new);
        event.registerEntityRenderer(ModLivingEntityRegister.HAZE_KILLER_TANK.get(), HazeKillerTankEntityRenderer::new);
        event.registerEntityRenderer(ModLivingEntityRegister.GOLD_FERRIN.get(), GoldFerrinEntityRenderer::new);
        event.registerEntityRenderer(ModLivingEntityRegister.IRON_FERRIN.get(), IronFerrinEntityRenderer::new);
    }

    @SubscribeEvent
    public static void registerLayerDefinition(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(EttmetalAllomancerEntityModel.LAYER_LOCATION, EttmetalAllomancerEntityModel::createBodyLayer);
        event.registerLayerDefinition(IronAllomancerEntityModel.LAYER_LOCATION, IronAllomancerEntityModel::createBodyLayer);
        event.registerLayerDefinition(SteelAllomancerEntityModel.LAYER_LOCATION, SteelAllomancerEntityModel::createBodyLayer);
        event.registerLayerDefinition(PewterAllomancerEntityModel.LAYER_LOCATION, PewterAllomancerEntityModel::createBodyLayer);
        event.registerLayerDefinition(HazeKillerMeleeEntityModel.LAYER_LOCATION, HazeKillerMeleeEntityModel::createBodyLayer);
        event.registerLayerDefinition(HazeKillerRangedEntityModel.LAYER_LOCATION, HazeKillerRangedEntityModel::createBodyLayer);
        event.registerLayerDefinition(HazeKillerTankEntityModel.LAYER_LOCATION, HazeKillerTankEntityModel::createBodyLayer);
        event.registerLayerDefinition(GoldFerrinEntityModel.LAYER_LOCATION, GoldFerrinEntityModel::createBodyLayer);
        event.registerLayerDefinition(IronFerrinEntityModel.LAYER_LOCATION, IronFerrinEntityModel::createBodyLayer);
    }

}
