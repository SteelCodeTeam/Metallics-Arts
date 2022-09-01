package net.rudahee.metallics_arts.modules.data_player;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.rudahee.metallics_arts.MetallicsArts;

public class InvestedCapability {

    public static final Capability<IDefaultInvestedPlayerData> PLAYER_CAP = CapabilityManager.get(new CapabilityToken<IDefaultInvestedPlayerData>(){
        @Override
        public String toString() {
            return super.toString();
        }
    });

    public static final ResourceLocation IDENTIFIER = new ResourceLocation(MetallicsArts.MOD_ID, "ma_data");

    public static void register(final RegisterCapabilitiesEvent event) {
        event.register(IDefaultInvestedPlayerData.class);
    }

}
