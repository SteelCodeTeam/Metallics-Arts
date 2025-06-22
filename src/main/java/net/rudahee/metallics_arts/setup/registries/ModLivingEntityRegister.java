package net.rudahee.metallics_arts.setup.registries;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.modules.custom_entities.allomancer.ettmetal_allomancer_entity.EttmetalAllomancerEntity;
import net.rudahee.metallics_arts.modules.custom_entities.custom_bosses.cadmium_inquisitor.CadmiumInquisitor;
import net.rudahee.metallics_arts.modules.custom_entities.custom_bosses.iron_inquisitor.IronInquisitor;
import net.rudahee.metallics_arts.modules.custom_entities.custom_bosses.pewter_inquisitor.PewterInquisitor;
import net.rudahee.metallics_arts.modules.custom_entities.custom_bosses.steel_inquisitor.SteelInquisitor;
import net.rudahee.metallics_arts.modules.custom_entities.ferrin.brass_ferrin_entity.BrassFerrinEntity;
import net.rudahee.metallics_arts.modules.custom_entities.ferrin.gold_ferrin_entity.GoldFerrinEntity;
import net.rudahee.metallics_arts.modules.custom_entities.haze_killer.haze_killer_melee_entity.HazeKillerMeleeEntity;
import net.rudahee.metallics_arts.modules.custom_entities.haze_killer.haze_killer_ranged_entity.HazeKillerRangedEntity;
import net.rudahee.metallics_arts.modules.custom_entities.haze_killer.haze_killer_tank_entity.HazeKillerTankEntity;
import net.rudahee.metallics_arts.modules.custom_entities.allomancer.iron_allomancer_entity.IronAllomancerEntity;
import net.rudahee.metallics_arts.modules.custom_entities.ferrin.iron_ferrin_entity.IronFerrinEntity;
import net.rudahee.metallics_arts.modules.custom_entities.allomancer.pewter_allomancer_entity.PewterAllomancerEntity;
import net.rudahee.metallics_arts.modules.custom_entities.allomancer.steel_allomancer_entity.SteelAllomancerEntity;

public class ModLivingEntityRegister {

    public static final float COMMON_HEIGHT = 2.0f;
    public static final float COMMON_WIDTH = 1.0f;

    private ModLivingEntityRegister() {
        throw new IllegalStateException("Class can't be instantiated");
    }

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MetallicsArts.MOD_ID);

    public static final RegistryObject<EntityType<BrassFerrinEntity>> BRASS_FERRIN = ENTITY_TYPES.register("brass_ferrin_entity",
            () -> EntityType.Builder.of(BrassFerrinEntity::new, MobCategory.CREATURE)
                    .sized(COMMON_WIDTH, COMMON_HEIGHT)
                    .canSpawnFarFromPlayer()
                    .fireImmune()
                    .immuneTo(Blocks.MAGMA_BLOCK)
                    .immuneTo(Blocks.FIRE)
                    .immuneTo(Blocks.LAVA)
                    .clientTrackingRange(32)
                    .build(new ResourceLocation(MetallicsArts.MOD_ID, "brass_ferrin").toString()));


    public static final RegistryObject<EntityType<EttmetalAllomancerEntity>> ETTMETAL_ALLOMANCER = ENTITY_TYPES.register("ettmetal_allomancer_entity",
            () -> EntityType.Builder.of(EttmetalAllomancerEntity::new, MobCategory.CREATURE)
                    .sized(COMMON_WIDTH, COMMON_HEIGHT)
                    .canSpawnFarFromPlayer()
                    .clientTrackingRange(64)
                    .build(new ResourceLocation(MetallicsArts.MOD_ID, "ettmetal_allomancer").toString()));

    public static final RegistryObject<EntityType<IronAllomancerEntity>> IRON_ALLOMANCER = ENTITY_TYPES.register("iron_allomancer_entity",
            () -> EntityType.Builder.of(IronAllomancerEntity::new, MobCategory.CREATURE)
                    .sized(COMMON_WIDTH, COMMON_HEIGHT)
                    .build(new ResourceLocation(MetallicsArts.MOD_ID, "iron_allomancer").toString()));

    public static final RegistryObject<EntityType<SteelAllomancerEntity>> STEEL_ALLOMANCER = ENTITY_TYPES.register("steel_allomancer_entity",
            () -> EntityType.Builder.of(SteelAllomancerEntity::new, MobCategory.CREATURE)
                    .sized(COMMON_WIDTH, COMMON_HEIGHT)
                    .build(new ResourceLocation(MetallicsArts.MOD_ID + "steel_allomancer").toString()));

    public static final RegistryObject<EntityType<PewterAllomancerEntity>> PEWTER_ALLOMANCER = ENTITY_TYPES.register("pewter_allomancer_entity",
            () -> EntityType.Builder.of(PewterAllomancerEntity::new, MobCategory.CREATURE)
                    .sized(COMMON_WIDTH, COMMON_HEIGHT)
                    .build(new ResourceLocation(MetallicsArts.MOD_ID + "pewter_allomancer").toString()));

    public static final RegistryObject<EntityType<HazeKillerMeleeEntity>> HAZE_KILLER_MELEE = ENTITY_TYPES.register("haze_killer_melee_entity",
            () -> EntityType.Builder.of(HazeKillerMeleeEntity::new, MobCategory.CREATURE)
                    .sized(COMMON_WIDTH, COMMON_HEIGHT)
                    .build(new ResourceLocation(MetallicsArts.MOD_ID + "haze_killer_melee").toString()));

    public static final RegistryObject<EntityType<HazeKillerRangedEntity>> HAZE_KILLER_RANGED = ENTITY_TYPES.register("haze_killer_ranged_entity",
            () -> EntityType.Builder.of(HazeKillerRangedEntity::new, MobCategory.CREATURE)
                    .sized(COMMON_WIDTH, COMMON_HEIGHT)
                    .build(new ResourceLocation(MetallicsArts.MOD_ID + "haze_killer_ranged").toString()));

    public static final RegistryObject<EntityType<HazeKillerTankEntity>> HAZE_KILLER_TANK = ENTITY_TYPES.register("haze_killer_tank_entity",
            () -> EntityType.Builder.of(HazeKillerTankEntity::new, MobCategory.CREATURE)
                    .sized(COMMON_WIDTH, COMMON_HEIGHT)
                    .build(new ResourceLocation(MetallicsArts.MOD_ID + "haze_killer_tank").toString()));

    public static final RegistryObject<EntityType<GoldFerrinEntity>> GOLD_FERRIN = ENTITY_TYPES.register("gold_ferrin_entity",
            () -> EntityType.Builder.of(GoldFerrinEntity::new, MobCategory.CREATURE)
                    .sized(COMMON_WIDTH, COMMON_HEIGHT)
                    .build(new ResourceLocation(MetallicsArts.MOD_ID + "gold_ferrin").toString()));

    public static final RegistryObject<EntityType<IronFerrinEntity>> IRON_FERRIN = ENTITY_TYPES.register("iron_ferrin_entity",
            () -> EntityType.Builder.of(IronFerrinEntity::new, MobCategory.CREATURE)
                    .sized(COMMON_WIDTH, COMMON_HEIGHT)
                    .build(new ResourceLocation(MetallicsArts.MOD_ID + "iron_ferrin").toString()));

    public static final RegistryObject<EntityType<IronInquisitor>> IRON_INQUISITOR = ENTITY_TYPES.register("iron_inquisitor_entity",
            () -> EntityType.Builder.of(IronInquisitor::new, MobCategory.CREATURE)
                    .sized(1.2f, 2.4f)
                    .fireImmune()
                    .immuneTo(Blocks.MAGMA_BLOCK)
                    .immuneTo(Blocks.FIRE)
                    .immuneTo(Blocks.LAVA)
                    .clientTrackingRange(48)
                    .build(new ResourceLocation(MetallicsArts.MOD_ID, "iron_inquisitor").toString()));

    public static final RegistryObject<EntityType<SteelInquisitor>> STEEL_INQUISITOR = ENTITY_TYPES.register("steel_inquisitor_entity",
            () -> EntityType.Builder.of(SteelInquisitor::new, MobCategory.CREATURE)
                    .sized(1.2f, 2.4f)
                    .fireImmune()
                    .immuneTo(Blocks.MAGMA_BLOCK)
                    .immuneTo(Blocks.FIRE)
                    .immuneTo(Blocks.LAVA)
                    .clientTrackingRange(48)
                    .build(new ResourceLocation(MetallicsArts.MOD_ID, "steel_inquisitor").toString()));

    public static final RegistryObject<EntityType<CadmiumInquisitor>> CADMIUM_INQUISITOR = ENTITY_TYPES.register("cadmium_inquisitor_entity",
            () -> EntityType.Builder.of(CadmiumInquisitor::new, MobCategory.CREATURE)
                    .sized(1.2f, 2.4f)
                    .fireImmune()
                    .immuneTo(Blocks.MAGMA_BLOCK)
                    .immuneTo(Blocks.FIRE)
                    .immuneTo(Blocks.LAVA)
                    .clientTrackingRange(48)
                    .build(new ResourceLocation(MetallicsArts.MOD_ID, "cadmium_inquisitor").toString()));


    public static final RegistryObject<EntityType<PewterInquisitor>> PEWTER_INQUISITOR = ENTITY_TYPES.register("pewter_inquisitor_entity",
            () -> EntityType.Builder.of(PewterInquisitor::new, MobCategory.CREATURE)
                    .sized(1.2f, 2.4f)
                    .fireImmune()
                    .immuneTo(Blocks.MAGMA_BLOCK)
                    .immuneTo(Blocks.FIRE)
                    .immuneTo(Blocks.LAVA)
                    .clientTrackingRange(48)
                    .build(new ResourceLocation(MetallicsArts.MOD_ID, "pewter_inquisitor").toString()));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
