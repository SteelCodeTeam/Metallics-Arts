package net.rudahee.metallics_arts.modules.logic.server.server_events.on_world_tick;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.player.IInvestedPlayerData;
import net.rudahee.metallics_arts.modules.error_handling.utils.LoggerUtils;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.hybrid_metals.GoldFecuchemicHelper;

public class FeruchemicTick {

    public static void each3Ticks(IInvestedPlayerData playerCapability, ServerPlayer player) {
        LoggerUtils.printLogFatal("METODO DE 3 TICKS");
        LoggerUtils.printLogFatal("CAPABILITY:" + playerCapability.isStoring(MetalTagEnum.GOLD));
        if (playerCapability.isTapping(MetalTagEnum.GOLD)) {
            LoggerUtils.printLogFatal("PRE TAPPING GOLD");
            GoldFecuchemicHelper.tapPower(player);
        } else if (playerCapability.isStoring(MetalTagEnum.GOLD)) {
            LoggerUtils.printLogFatal("PRE STORAGE GOLD");
            GoldFecuchemicHelper.storagePower(player);
        }

    }
}
