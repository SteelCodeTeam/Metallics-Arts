package net.rudahee.metallics_arts.modules.powers;

import com.mojang.brigadier.arguments.ArgumentType;
import net.minecraft.command.arguments.ArgumentTypes;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class MetallicsPowersSetup {

    public static void clientInit(final FMLClientSetupEvent event) {
        MinecraftForge.EVENT_BUS.register(new PowersEventHandler());
    }

    public static void registerCommands(final RegisterCommandsEvent event) {
        // TODO
    }

    public static void init(final FMLCommonSetupEvent event) {
        // TODO
    }

    public static void register() {
        // TODO
    }
}
