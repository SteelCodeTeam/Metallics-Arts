package net.rudahee.metallics_arts.setup;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.data.client_providers.ModBlockStateProvider;
import net.rudahee.metallics_arts.data.client_providers.ModItemModelProvider;
import net.rudahee.metallics_arts.data.client_providers.ModLootTableProvider;
import net.rudahee.metallics_arts.data.client_providers.ModRecipeProvider;

@Mod.EventBusSubscriber (modid = MetallicsArts.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class DataGenerators {
    private DataGenerators() {}

    @SubscribeEvent
    public static void  gatherData (GatherDataEvent event){
        DataGenerator gen = event.getGenerator();

        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        gen.addProvider(new ModBlockStateProvider(gen,existingFileHelper));
        gen.addProvider(new ModItemModelProvider(gen,existingFileHelper));

        gen.addProvider(new ModLootTableProvider(gen));
        gen.addProvider(new ModRecipeProvider(gen));
    }
}
