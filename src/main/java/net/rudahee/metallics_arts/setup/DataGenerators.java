package net.rudahee.metallics_arts.setup;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.data.providers.ModBlockStateProvider;
import net.rudahee.metallics_arts.data.providers.ModItemModelProvider;
import net.rudahee.metallics_arts.data.providers.ModLootTableProvider;
import net.rudahee.metallics_arts.data.providers.ModRecipeProvider;
import net.rudahee.metallics_arts.data.providers.language_providers.ModLanguageProviderEN;
import net.rudahee.metallics_arts.data.providers.language_providers.ModLanguageProviderES;
import net.rudahee.metallics_arts.data.providers.language_providers.ModLanguageProviderJP;
import net.rudahee.metallics_arts.data.providers.tags_providers.ModBannerTagProvider;
import net.rudahee.metallics_arts.data.providers.tags_providers.ModBeaconTagProvider;
import net.rudahee.metallics_arts.data.providers.tags_providers.ModBlockTagProvider;
import net.rudahee.metallics_arts.modules.book.DemoBookProvider;
import net.rudahee.metallics_arts.modules.book.lang.EnUsProvider;

@Mod.EventBusSubscriber (modid = MetallicsArts.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class DataGenerators {
    private DataGenerators() {}

    @SubscribeEvent
    public static void  gatherData (GatherDataEvent event){
        DataGenerator gen = event.getGenerator();

        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        gen.addProvider(true, new ModBlockStateProvider(gen,existingFileHelper));
        gen.addProvider(true, new ModItemModelProvider(gen,existingFileHelper));
        gen.addProvider(true, new ModBlockTagProvider(gen, MetallicsArts.MOD_ID, event.getExistingFileHelper()));
        gen.addProvider(true, new ModLootTableProvider(gen));
        gen.addProvider(true, new ModRecipeProvider(gen));

        gen.addProvider(true, new ModLanguageProviderES(gen, MetallicsArts.MOD_ID, "es_es"));
        gen.addProvider(true, new ModLanguageProviderES(gen, MetallicsArts.MOD_ID, "es_ar"));
        gen.addProvider(true, new ModLanguageProviderES(gen, MetallicsArts.MOD_ID, "es_mx"));
        gen.addProvider(true, new ModLanguageProviderES(gen, MetallicsArts.MOD_ID, "es_uy"));
        gen.addProvider(true, new ModLanguageProviderES(gen, MetallicsArts.MOD_ID, "es_ve"));
        gen.addProvider(true, new ModLanguageProviderEN(gen, MetallicsArts.MOD_ID, "en_us"));
        gen.addProvider(true, new ModLanguageProviderEN(gen, MetallicsArts.MOD_ID, "en_au"));
        gen.addProvider(true, new ModLanguageProviderEN(gen, MetallicsArts.MOD_ID, "en_ca"));
        gen.addProvider(true, new ModLanguageProviderEN(gen, MetallicsArts.MOD_ID, "en_gb"));

        gen.addProvider(true, new ModLanguageProviderJP(gen, MetallicsArts.MOD_ID, "ja_jp"));

        gen.addProvider(event.includeServer(), new ModBannerTagProvider(gen, event.getExistingFileHelper()));
        gen.addProvider(true, new ModBeaconTagProvider(gen,event.getExistingFileHelper()));

        gen.addProvider(event.includeServer(), new DemoBookProvider(gen, MetallicsArts.MOD_ID, null));
        //gen.addProvider(event.includeClient(), null);

    }
}
