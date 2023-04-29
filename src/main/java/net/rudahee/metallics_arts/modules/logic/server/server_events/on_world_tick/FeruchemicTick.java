package net.rudahee.metallics_arts.modules.logic.server.server_events.on_world_tick;

import net.minecraft.server.level.ServerPlayer;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.player.IInvestedPlayerData;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.cognitive_metals.BrassFeruchemicHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.cognitive_metals.BronzeFeruchemicHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.cognitive_metals.CopperFeruchemicHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.cognitive_metals.ZincFeruchemicHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.god_metals.AtiumFeruchemicHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.god_metals.EttmetalFeruchemicHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.god_metals.LerasiumFeruchemicHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.god_metals.MalatiumFeruchemicHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.hybrid_metals.BendalloyFeruchemicHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.hybrid_metals.CadmiumFeruchemicHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.hybrid_metals.ElectrumFeruchemicHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.hybrid_metals.GoldFeruchemicHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.physical_metals.IronFeruchemicHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.physical_metals.PewterFeruchemicHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.physical_metals.SteelFeruchemicHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.physical_metals.TinFeruchemicHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.spiritual_metals.AluminumFeruchemicHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.spiritual_metals.ChromiumFeruchemicHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.spiritual_metals.DuraluminFeruchemicHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.spiritual_metals.NicrosilFeruchemicHelper;

public class FeruchemicTick {

    public static void each3Ticks(IInvestedPlayerData playerCapability, ServerPlayer player) {

        if (isTappingOrStorage(playerCapability, MetalTagEnum.BRASS, MetalTagEnum.BRONZE, MetalTagEnum.COPPER, MetalTagEnum.ZINC)) {
            cognitiveMetals(playerCapability, player);
        }
        if (isTappingOrStorage(playerCapability, MetalTagEnum.ATIUM, MetalTagEnum.MALATIUM, MetalTagEnum.LERASIUM, MetalTagEnum.ETTMETAL)) {
            godMetals(playerCapability, player);
        }
        if (isTappingOrStorage(playerCapability, MetalTagEnum.IRON, MetalTagEnum.STEEL, MetalTagEnum.TIN, MetalTagEnum.PEWTER)) {
            physicalMetals(playerCapability, player);
        }
        if (isTappingOrStorage(playerCapability, MetalTagEnum.CADMIUM, MetalTagEnum.BENDALLOY, MetalTagEnum.GOLD, MetalTagEnum.ELECTRUM)) {
            hybridMetals(playerCapability, player);
        }
        if (isTappingOrStorage(playerCapability, MetalTagEnum.ALUMINUM, MetalTagEnum.DURALUMIN, MetalTagEnum.CHROMIUM, MetalTagEnum.NICROSIL)) {
            spiritualMetals(playerCapability, player);
        }
    }

    public static boolean isTappingOrStorage(IInvestedPlayerData playerCapability, MetalTagEnum... metals) {
        for (MetalTagEnum metal : metals) {
            if (playerCapability.isStoring(metal) || playerCapability.isTapping(metal)) {
                return true;
            }
        }
        return false;
    }

    private static void cognitiveMetals(IInvestedPlayerData playerCapability, ServerPlayer player) {
        if (playerCapability.isTapping(MetalTagEnum.BRASS)) {
            BrassFeruchemicHelper.tapPower(player);
        } else if (playerCapability.isStoring(MetalTagEnum.BRASS)){
            BrassFeruchemicHelper.storagePower(player);
        }
        if (playerCapability.isTapping(MetalTagEnum.BRONZE)) {
            BronzeFeruchemicHelper.tapPower(player);
        } else if (playerCapability.isStoring(MetalTagEnum.BRONZE)){
            BronzeFeruchemicHelper.storagePower(player);
        }
        if (playerCapability.isTapping(MetalTagEnum.COPPER)) {
            CopperFeruchemicHelper.tapPower(player);
        } else if (playerCapability.isStoring(MetalTagEnum.COPPER)){
            CopperFeruchemicHelper.storagePower(player);
        }
        if (playerCapability.isTapping(MetalTagEnum.ZINC)) {
            ZincFeruchemicHelper.tapPower(player);
        } else if (playerCapability.isStoring(MetalTagEnum.ZINC)){
            ZincFeruchemicHelper.storagePower(player);
        }
    }

    private static void godMetals(IInvestedPlayerData playerCapability, ServerPlayer player) {
        if (playerCapability.isTapping(MetalTagEnum.ATIUM)) {
            AtiumFeruchemicHelper.tapPower(player);
        } else if (playerCapability.isStoring(MetalTagEnum.ATIUM)){
            AtiumFeruchemicHelper.storagePower(player);
        }
        if (playerCapability.isTapping(MetalTagEnum.MALATIUM)) {
            MalatiumFeruchemicHelper.tapPower(player);
        } else if (playerCapability.isStoring(MetalTagEnum.MALATIUM)){
            MalatiumFeruchemicHelper.storagePower(player);
        }
        if (playerCapability.isTapping(MetalTagEnum.LERASIUM)) {
            LerasiumFeruchemicHelper.tapPower(player);
        } else if (playerCapability.isStoring(MetalTagEnum.LERASIUM)){
            LerasiumFeruchemicHelper.storagePower(player);
        }
        if (playerCapability.isTapping(MetalTagEnum.ETTMETAL)) {
            EttmetalFeruchemicHelper.tapPower(player);
        } else if (playerCapability.isStoring(MetalTagEnum.ETTMETAL)){
            EttmetalFeruchemicHelper.storagePower(player);
        }
    }

    private static void hybridMetals(IInvestedPlayerData playerCapability, ServerPlayer player) {
        if (playerCapability.isTapping(MetalTagEnum.GOLD)) {
            GoldFeruchemicHelper.tapPower(player);
        } else if (playerCapability.isStoring(MetalTagEnum.GOLD)){
            GoldFeruchemicHelper.storagePower(player);
        }
        if (playerCapability.isTapping(MetalTagEnum.ELECTRUM)) {
            ElectrumFeruchemicHelper.tapPower(player);
        } else if (playerCapability.isStoring(MetalTagEnum.ELECTRUM)){
            ElectrumFeruchemicHelper.storagePower(player);
        } else {
            ElectrumFeruchemicHelper.restoreHearts(player, playerCapability);
        }
        if (playerCapability.isTapping(MetalTagEnum.CADMIUM)) {
            CadmiumFeruchemicHelper.tapPower(player);
        } else if (playerCapability.isStoring(MetalTagEnum.CADMIUM)){
            CadmiumFeruchemicHelper.storagePower(player);
        }
        if (playerCapability.isTapping(MetalTagEnum.BENDALLOY)) {
            BendalloyFeruchemicHelper.tapPower(player);
        } else if (playerCapability.isStoring(MetalTagEnum.BENDALLOY)){
            BendalloyFeruchemicHelper.storagePower(player);
        }
    }

    private static void physicalMetals(IInvestedPlayerData playerCapability, ServerPlayer player) {
        if (playerCapability.isTapping(MetalTagEnum.IRON)) {
            IronFeruchemicHelper.tapPower(player);
        } else if (playerCapability.isStoring(MetalTagEnum.IRON)){
            IronFeruchemicHelper.storagePower(player);
        }
        if (playerCapability.isTapping(MetalTagEnum.STEEL)) {
            SteelFeruchemicHelper.tapPower(player);
        } else if (playerCapability.isStoring(MetalTagEnum.STEEL)){
            SteelFeruchemicHelper.storagePower(player);
        }
        if (playerCapability.isTapping(MetalTagEnum.TIN)) {
            TinFeruchemicHelper.tapPower(player);
        } else if (playerCapability.isStoring(MetalTagEnum.TIN)){
            TinFeruchemicHelper.storagePower(player);
        }
        if (playerCapability.isTapping(MetalTagEnum.PEWTER)) {
            PewterFeruchemicHelper.tapPower(player);
        } else if (playerCapability.isStoring(MetalTagEnum.PEWTER)){
            PewterFeruchemicHelper.storagePower(player);
        }
    }
    private static void spiritualMetals(IInvestedPlayerData playerCapability, ServerPlayer player) {
        if (playerCapability.isTapping(MetalTagEnum.ALUMINUM)) {
            AluminumFeruchemicHelper.tapPower(player);
        } else if (playerCapability.isStoring(MetalTagEnum.ALUMINUM)){
            AluminumFeruchemicHelper.storagePower(player);
        }
        if (playerCapability.isTapping(MetalTagEnum.DURALUMIN)) {
            DuraluminFeruchemicHelper.tapPower(player);
        } else if (playerCapability.isStoring(MetalTagEnum.DURALUMIN)){
            DuraluminFeruchemicHelper.storagePower(player);
        }
        if (playerCapability.isTapping(MetalTagEnum.CHROMIUM)) {
            ChromiumFeruchemicHelper.tapPower(player);
        } else if (playerCapability.isStoring(MetalTagEnum.CHROMIUM)){
            ChromiumFeruchemicHelper.storagePower(player);
        }
        if (playerCapability.isTapping(MetalTagEnum.NICROSIL)) {
            NicrosilFeruchemicHelper.tapPower(player);
        } else if (playerCapability.isStoring(MetalTagEnum.NICROSIL)){
            NicrosilFeruchemicHelper.storagePower(player);
        }
    }
}
