package net.rudahee.metallics_arts.setup;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.data.network.client_providers.*;

@Mod.EventBusSubscriber (modid = MetallicsArts.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class DataGenerators {
    private DataGenerators() {}

    @SubscribeEvent
    public static void  gatherData (GatherDataEvent event){
        DataGenerator gen = event.getGenerator();

        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        gen.addProvider(true, new ModBlockStateProvider(gen,existingFileHelper));
        gen.addProvider(true, new ModItemModelProvider(gen,existingFileHelper));
        gen.addProvider(true, new BlockTags(gen, MetallicsArts.MOD_ID, event.getExistingFileHelper()));
        gen.addProvider(true, new ModLootTableProvider(gen));
        gen.addProvider(true, new ModRecipeProvider(gen));
    }
}
