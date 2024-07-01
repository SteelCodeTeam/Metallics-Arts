package net.rudahee.metallics_arts.setup;

import lombok.extern.log4j.Log4j2;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.rudahee.metallics_arts.setup.registries.ModBlocksRegister;
import net.rudahee.metallics_arts.setup.registries.ModItemsRegister;
import net.rudahee.metallics_arts.setup.registries.ModRecipeTypesRegister;

@Log4j2
public class Registration {

    private Registration() {
        throw new IllegalStateException("Class can't be instantiated");
    }

    public static void register() {
        ModBlocksRegister.register();
        ModItemsRegister.register();

        log.info("[Metallics Arts] Starting Register: Recipes");
        ModRecipeTypesRegister.register(FMLJavaModLoadingContext.get().getModEventBus());
        log.info("[Metallics Arts] Completed Register: Recipes");
    }

}
