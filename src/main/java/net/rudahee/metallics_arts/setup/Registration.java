package net.rudahee.metallics_arts.setup;

import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.rudahee.metallics_arts.setup.registries.ModBlock;
import net.rudahee.metallics_arts.setup.registries.ModItems;
import net.rudahee.metallics_arts.setup.registries.ModRecipeTypes;
import net.rudahee.metallics_arts.setup.registries.ModTileEntities;

public class Registration {

    // In this class we register each TYPE OF OBJECT we add in our mod.




    public static void register() {
        // We inject our objects to "Minecraft Bus" to load all of this

        ModBlock.register();
        ModItems.register();
        //BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        //ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());

        ModTileEntities.register(FMLJavaModLoadingContext.get().getModEventBus());
        //ModContainers.register(modEventBus);
        ModRecipeTypes.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

}
