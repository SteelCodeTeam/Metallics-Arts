package net.rudahee.metallics_arts.players;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

public class InvestedPlayerCapability {
    @CapabilityInject(IInvestedPlayerData.class)
    public static final Capability<IInvestedPlayerData> PLAYER_CAP = null;
}
