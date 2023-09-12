package net.rudahee.metallics_arts.setup;

import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.rudahee.metallics_arts.setup.registries.ModBlocksRegister;
import net.rudahee.metallics_arts.setup.registries.ModItemsRegister;
import net.rudahee.metallics_arts.setup.registries.ModRecipeTypesRegister;

public class Registration {

    // In this class we register each TYPE OF OBJECT we add in our mod.

    public static void register() {
        // We inject our objects to "Minecraft Bus" to load all of this

        ModBlocksRegister.register();
        ModItemsRegister.register();

        ModRecipeTypesRegister.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

}
