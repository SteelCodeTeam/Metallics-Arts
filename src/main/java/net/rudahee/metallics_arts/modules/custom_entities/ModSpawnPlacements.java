package net.rudahee.metallics_arts.modules.custom_entities;

import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.rudahee.metallics_arts.modules.custom_entities.allomancer.ettmetal_allomancer_entity.EttmetalAllomancerEntity;
import net.rudahee.metallics_arts.modules.custom_entities.allomancer.iron_allomancer_entity.IronAllomancerEntity;
import net.rudahee.metallics_arts.modules.custom_entities.allomancer.pewter_allomancer_entity.PewterAllomancerEntity;
import net.rudahee.metallics_arts.modules.custom_entities.allomancer.steel_allomancer_entity.SteelAllomancerEntity;
import net.rudahee.metallics_arts.modules.custom_entities.ferrin.brass_ferrin_entity.BrassFerrinEntity;
import net.rudahee.metallics_arts.modules.custom_entities.ferrin.gold_ferrin_entity.GoldFerrinEntity;
import net.rudahee.metallics_arts.modules.custom_entities.ferrin.iron_ferrin_entity.IronFerrinEntity;
import net.rudahee.metallics_arts.modules.custom_entities.haze_killer.haze_killer_melee_entity.HazeKillerMeleeEntity;
import net.rudahee.metallics_arts.modules.custom_entities.haze_killer.haze_killer_ranged_entity.HazeKillerRangedEntity;
import net.rudahee.metallics_arts.modules.custom_entities.haze_killer.haze_killer_tank_entity.HazeKillerTankEntity;
import net.rudahee.metallics_arts.setup.registries.ModLivingEntityRegister;

public class ModSpawnPlacements {

    @SubscribeEvent
    public  static void registerSpawnPlacements(SpawnPlacementRegisterEvent event) {
        event.register(ModLivingEntityRegister.IRON_ALLOMANCER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, IronAllomancerEntity::canSpawn, SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(ModLivingEntityRegister.STEEL_ALLOMANCER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, SteelAllomancerEntity::canSpawn, SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(ModLivingEntityRegister.ETTMETAL_ALLOMANCER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EttmetalAllomancerEntity::canSpawn, SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(ModLivingEntityRegister.PEWTER_ALLOMANCER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, PewterAllomancerEntity::canSpawn, SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(ModLivingEntityRegister.HAZE_KILLER_MELEE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, HazeKillerMeleeEntity::canSpawn, SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(ModLivingEntityRegister.HAZE_KILLER_RANGED.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, HazeKillerRangedEntity::canSpawn, SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(ModLivingEntityRegister.HAZE_KILLER_TANK.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, HazeKillerTankEntity::canSpawn, SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(ModLivingEntityRegister.GOLD_FERRIN.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, GoldFerrinEntity::canSpawn, SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(ModLivingEntityRegister.IRON_FERRIN.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, IronFerrinEntity::canSpawn, SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(ModLivingEntityRegister.BRASS_FERRIN.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, BrassFerrinEntity::canSpawn, SpawnPlacementRegisterEvent.Operation.REPLACE);

    }
}
