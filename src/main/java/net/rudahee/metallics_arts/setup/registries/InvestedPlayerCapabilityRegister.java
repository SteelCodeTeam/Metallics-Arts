package net.rudahee.metallics_arts.setup.registries;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.data.player.data.IInvestedPlayerData;

public class InvestedPlayerCapabilityRegister {

        public static final Capability<IInvestedPlayerData> PLAYER_CAP = CapabilityManager.get(new CapabilityToken<>() {
            @Override
            public String toString() {
                return super.toString();
            }
        });

        public static final ResourceLocation IDENTIFIER = new ResourceLocation(MetallicsArts.MOD_ID, "ma_data");

        public static void register(final RegisterCapabilitiesEvent event) {
            event.register(IInvestedPlayerData.class);
        }


}
