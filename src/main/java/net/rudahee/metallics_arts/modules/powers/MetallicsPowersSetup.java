package net.rudahee.metallics_arts.modules.powers;

import com.mojang.brigadier.arguments.ArgumentType;
import net.minecraft.command.arguments.ArgumentSerializer;
import net.minecraft.command.arguments.ArgumentTypes;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.rudahee.metallics_arts.modules.DataPlayer.InvestedCapability;

public class MetallicsPowersSetup {

    public static void clientInit(final FMLClientSetupEvent event) {
        MinecraftForge.EVENT_BUS.register(new PowersEventHandler());
        //PowersClientSetup.initKeyBindings();
    }

    public static void registerCommands(final RegisterCommandsEvent event) {
        //AllomancyPowerCommand.register(e.getDispatcher());
    }

    public static void init(final FMLCommonSetupEvent event) {

        InvestedCapability.register();
        MinecraftForge.EVENT_BUS.register(PowersEventHandler.class);
    }

    public static void register() {
        MetallicsPowersSetup.register();
    }
}
