package net.rudahee.metallics_arts.data;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.config.ModConfig;
import net.rudahee.metallics_arts.data.network.PowerConfiguration;

public class AllomancyConfig_idk {

    public static final ForgeConfigSpec COMMON_CONFIG;
    public static final ForgeConfigSpec CLIENT_CONFIG;

    static {
        ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();
        ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();
        //MaterialsConfig.init(COMMON_BUILDER, CLIENT_BUILDER);
        PowerConfiguration.init(COMMON_BUILDER, CLIENT_BUILDER);

        COMMON_CONFIG = COMMON_BUILDER.build();
        CLIENT_CONFIG = CLIENT_BUILDER.build();
    }

    /*public static void onReload(final ModConfig.Reloading e) {
        PowerConfiguration.refresh(e);
    }


    public static void onLoad(final ModConfig.Loading e) {
        PowerConfiguration.load_whitelist(e);
    }*/

}