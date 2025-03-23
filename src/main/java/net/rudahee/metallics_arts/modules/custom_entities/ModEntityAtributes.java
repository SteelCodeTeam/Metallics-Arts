package net.rudahee.metallics_arts.modules.custom_entities;

import lombok.extern.log4j.Log4j2;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.modules.custom_entities.allomancer.ettmetal_allomancer_entity.EttmetalAllomancerEntity;
import net.rudahee.metallics_arts.modules.custom_entities.allomancer.iron_allomancer_entity.IronAllomancerEntity;
import net.rudahee.metallics_arts.modules.custom_entities.allomancer.pewter_allomancer_entity.PewterAllomancerEntity;
import net.rudahee.metallics_arts.modules.custom_entities.allomancer.steel_allomancer_entity.SteelAllomancerEntity;
import net.rudahee.metallics_arts.modules.custom_entities.ferrin.brass_ferrin_entity.BrassFerrinEntity;
import net.rudahee.metallics_arts.modules.custom_entities.ferrin.gold_ferrin_entity.GoldFerrinEntity;
import net.rudahee.metallics_arts.modules.custom_entities.ferrin.iron_ferrin_entity.IronFerrinEntity;
import net.rudahee.metallics_arts.modules.custom_entities.haze_killer.haze_killer_melee_entity.HazeKillerMeleeEntity;
import net.rudahee.metallics_arts.setup.registries.ModLivingEntityRegister;

@Log4j2
@Mod.EventBusSubscriber(modid = MetallicsArts.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEntityAtributes {

    @SubscribeEvent
    public static void entityAtributes(EntityAttributeCreationEvent event) {
        event.put(ModLivingEntityRegister.ETTMETAL_ALLOMANCER.get(), EttmetalAllomancerEntity.getExampleAttributes().build());
        event.put(ModLivingEntityRegister.IRON_ALLOMANCER.get(), IronAllomancerEntity.getExampleAttributes().build());
        event.put(ModLivingEntityRegister.STEEL_ALLOMANCER.get(), SteelAllomancerEntity.getExampleAttributes().build());
        event.put(ModLivingEntityRegister.PEWTER_ALLOMANCER.get(), PewterAllomancerEntity.getExampleAttributes().build());
        event.put(ModLivingEntityRegister.HAZE_KILLER_MELEE.get(), HazeKillerMeleeEntity.getExampleAttributes().build());
        event.put(ModLivingEntityRegister.HAZE_KILLER_RANGED.get(), HazeKillerMeleeEntity.getExampleAttributes().build());
        event.put(ModLivingEntityRegister.HAZE_KILLER_TANK.get(), HazeKillerMeleeEntity.getExampleAttributes().build());
        event.put(ModLivingEntityRegister.GOLD_FERRIN.get(), GoldFerrinEntity.getExampleAttributes().build());
        event.put(ModLivingEntityRegister.BRASS_FERRIN.get(), BrassFerrinEntity.getExampleAttributes().build());
        event.put(ModLivingEntityRegister.IRON_FERRIN.get(), IronFerrinEntity.getExampleAttributes().build());
    }
}
