package net.rudahee.metallics_arts.modules.data_player;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.rudahee.metallics_arts.MetallicsArts;

public class InvestedCapability {

    @CapabilityInject(IDefaultInvestedPlayerData.class)
    public static final Capability<IDefaultInvestedPlayerData> PLAYER_CAP = null;

    public static final ResourceLocation IDENTIFIER = new ResourceLocation(MetallicsArts.MOD_ID, "allomancy_data");

    public static void register() {
        CapabilityManager.INSTANCE.register(IDefaultInvestedPlayerData.class, new InvestedStorage(), DefaultInvestedPlayerData::new);
    }

}
