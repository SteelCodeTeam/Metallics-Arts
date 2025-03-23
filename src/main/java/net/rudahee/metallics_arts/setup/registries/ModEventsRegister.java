package net.rudahee.metallics_arts.setup.registries;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.rudahee.metallics_arts.modules.logic.client.ClientEventHandler;
import net.rudahee.metallics_arts.modules.logic.server.ServerEventHandler;


public class ModEventsRegister {

    private ModEventsRegister() {
        throw new IllegalStateException("Class can't be instantiated");
    }

    public static void clientInit(final FMLClientSetupEvent event) {
        MinecraftForge.EVENT_BUS.register(new ServerEventHandler());
    }


    public static void register(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {

            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBlocksRegister.MARE_FLOWER.getId(), ModBlocksRegister.POTTED_MARE_FLOWER);

            MinecraftForge.EVENT_BUS.register(new ClientEventHandler());
        });
    }


}
