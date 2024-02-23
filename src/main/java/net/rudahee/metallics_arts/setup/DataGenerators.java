package net.rudahee.metallics_arts.setup;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.data.providers.*;
import net.rudahee.metallics_arts.data.providers.language_providers.ModLanguageProviderEN;
import net.rudahee.metallics_arts.data.providers.language_providers.ModLanguageProviderES;
import net.rudahee.metallics_arts.data.providers.language_providers.ModLanguageProviderJP;
import net.rudahee.metallics_arts.data.providers.language_providers.ModLanguageProviderPL;
import net.rudahee.metallics_arts.data.providers.tags_providers.ModBlockTagProvider;
import net.rudahee.metallics_arts.data.providers.tags_providers.ModItemTagsProvider;
import net.rudahee.metallics_arts.data.providers.MetallicsArtsGuideBookProvider;
import net.rudahee.metallics_arts.modules.custom_entities.ettmetal_allomancer_entity.EttmetalAllomancerEntity;
import net.rudahee.metallics_arts.modules.custom_entities.gold_ferrin_entity.GoldFerrinEntity;
import net.rudahee.metallics_arts.modules.custom_entities.haze_killer_melee_entity.HazeKillerMeleeEntity;
import net.rudahee.metallics_arts.modules.custom_entities.haze_killer_ranged_entity.HazeKillerRangedEntity;
import net.rudahee.metallics_arts.modules.custom_entities.haze_killer_tank_entity.HazeKillerTankEntity;
import net.rudahee.metallics_arts.modules.custom_entities.iron_allomancer_entity.IronAllomancerEntity;
import net.rudahee.metallics_arts.modules.custom_entities.iron_ferrin_entity.IronFerrinEntity;
import net.rudahee.metallics_arts.modules.custom_entities.pewter_allomancer_entity.PewterAllomancerEntity;
import net.rudahee.metallics_arts.modules.custom_entities.steel_allomancer_entity.SteelAllomancerEntity;
import net.rudahee.metallics_arts.setup.registries.ModLivingEntityRegister;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = MetallicsArts.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        PackOutput packOutput = gen.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();




        gen.addProvider(event.includeServer(), new ModBlockStateProvider(packOutput, existingFileHelper));
        gen.addProvider(event.includeServer(), new ModItemModelProvider(gen, existingFileHelper));
        gen.addProvider(event.includeServer(), new LootTableProvider(packOutput, Collections.emptySet(),
                List.of(new LootTableProvider.SubProviderEntry(ModLootTableProvider::new, LootContextParamSets.BLOCK))));

        gen.addProvider(event.includeServer(), new ModRecipeProvider(gen));

        gen.addProvider(event.includeServer(), new ModLanguageProviderES(gen, "es_es"));
        gen.addProvider(event.includeServer(), new ModLanguageProviderES(gen, "es_ar"));
        gen.addProvider(event.includeServer(), new ModLanguageProviderES(gen, "es_mx"));
        gen.addProvider(event.includeServer(), new ModLanguageProviderES(gen, "es_uy"));
        gen.addProvider(event.includeServer(), new ModLanguageProviderES(gen, "es_ve"));
        gen.addProvider(event.includeServer(), new ModLanguageProviderES(gen, "es_cl"));

        gen.addProvider(event.includeServer(), new ModLanguageProviderEN(gen, "en_us"));
        gen.addProvider(event.includeServer(), new ModLanguageProviderEN(gen, "en_au"));
        gen.addProvider(event.includeServer(), new ModLanguageProviderEN(gen, "en_ca"));
        gen.addProvider(event.includeServer(), new ModLanguageProviderEN(gen, "en_gb"));

        gen.addProvider(event.includeServer(), new ModLanguageProviderJP(gen, "ja_jp"));
        gen.addProvider(event.includeServer(), new ModLanguageProviderPL(gen, "pl_pl"));

        gen.addProvider(event.includeServer(), new MetallicsArtsGuideBookProvider(gen, MetallicsArts.MOD_ID, null));
        //gen.addProvider(event.includeClient(), null);

        gen.addProvider(event.includeServer(), new ModWorldGenerationProvider(packOutput, lookupProvider));


        ModBlockTagProvider blockTags = new  ModBlockTagProvider(packOutput, lookupProvider, existingFileHelper);
        gen.addProvider(event.includeServer(), blockTags);
        gen.addProvider(event.includeServer(), new ModItemTagsProvider(packOutput,lookupProvider, blockTags.contentsGetter(), existingFileHelper));
        //gen.addProvider(event.includeServer(), new ModBannerTagProvider(packOutput, lookupProvider, event.getExistingFileHelper()));
        //gen.addProvider(event.includeServer(), new ModBeaconTagProvider(packOutput, lookupProvider, existingFileHelper));
    }
    
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
        event.put(ModLivingEntityRegister.IRON_FERRIN.get(), IronFerrinEntity.getExampleAttributes().build());


    }

    @SubscribeEvent
    public  static void registerSpawnPlacements(SpawnPlacementRegisterEvent event) {
        event.register(ModLivingEntityRegister.IRON_ALLOMANCER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, IronAllomancerEntity::canSpawn, SpawnPlacementRegisterEvent.Operation.REPLACE );
        event.register(ModLivingEntityRegister.STEEL_ALLOMANCER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, SteelAllomancerEntity::canSpawn, SpawnPlacementRegisterEvent.Operation.REPLACE );
        event.register(ModLivingEntityRegister.ETTMETAL_ALLOMANCER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EttmetalAllomancerEntity::canSpawn, SpawnPlacementRegisterEvent.Operation.REPLACE );
        event.register(ModLivingEntityRegister.PEWTER_ALLOMANCER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, PewterAllomancerEntity::canSpawn, SpawnPlacementRegisterEvent.Operation.REPLACE );
        event.register(ModLivingEntityRegister.HAZE_KILLER_MELEE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, HazeKillerMeleeEntity::canSpawn, SpawnPlacementRegisterEvent.Operation.REPLACE );
        event.register(ModLivingEntityRegister.HAZE_KILLER_RANGED.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, HazeKillerRangedEntity::canSpawn, SpawnPlacementRegisterEvent.Operation.REPLACE );
        event.register(ModLivingEntityRegister.HAZE_KILLER_TANK.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, HazeKillerTankEntity::canSpawn, SpawnPlacementRegisterEvent.Operation.REPLACE );
        event.register(ModLivingEntityRegister.GOLD_FERRIN.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, GoldFerrinEntity::canSpawn, SpawnPlacementRegisterEvent.Operation.REPLACE );
        event.register(ModLivingEntityRegister.IRON_FERRIN.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, IronFerrinEntity::canSpawn, SpawnPlacementRegisterEvent.Operation.REPLACE );

    }

}
