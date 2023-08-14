package net.rudahee.metallics_arts.modules.logic.server.server_events.on_world_tick;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.player.IInvestedPlayerData;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.cognitive_metals.BrassFeruchemicHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.cognitive_metals.BronzeFeruchemicHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.cognitive_metals.CopperFeruchemicHelper;
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

    public static void each5Ticks(IInvestedPlayerData playerCapability, Player player) {

        if (isTappingOrStorageAny(playerCapability, MetalTagEnum.ATIUM)) {
            godMetalsEach5Ticks(playerCapability, player);
        }

        if (isTappingOrStorageAny(playerCapability, MetalTagEnum.IRON, MetalTagEnum.STEEL, MetalTagEnum.TIN, MetalTagEnum.PEWTER)) {
            physicalMetalsEach5Ticks(playerCapability, player);
        }

        if (isTappingOrStorageAny(playerCapability, MetalTagEnum.DURALUMIN, MetalTagEnum.CHROMIUM)) {
            spiritualMetalsEach5Ticks(playerCapability, player);
        }
    }

    public static void eachTick(IInvestedPlayerData playerCapability, Player player) {

        if (isTappingOrStorageAny(playerCapability, MetalTagEnum.BRASS, MetalTagEnum.COPPER) || playerCapability.isTapping(MetalTagEnum.BRONZE)) {
            cognitiveMetals(playerCapability, player);
        }

        if (isTappingOrStorageAny(playerCapability, MetalTagEnum.ELECTRUM) || playerCapability.isStoring(MetalTagEnum.CADMIUM)) {
            hybridMetals(playerCapability, player);
        } else {
            ElectrumFeruchemicHelper.restoreHearts(player, playerCapability);
        }
    }

    public static void each40Tick(IInvestedPlayerData playerCapability, Player player) {
        if (playerCapability.isStoring(MetalTagEnum.BRONZE)) {
            cognitiveMetalsEach40Ticks(playerCapability, player);
        }

        if (isTappingOrStorageAny(playerCapability, MetalTagEnum.BENDALLOY, MetalTagEnum.GOLD) || playerCapability.isTapping(MetalTagEnum.CADMIUM))  {
            hybridMetalsEach40Ticks(playerCapability, player);
        }
    }

    public static boolean isTappingOrStorageAny(IInvestedPlayerData playerCapability, MetalTagEnum... metals) {
        for (MetalTagEnum metal : metals) {
            if (playerCapability.isStoring(metal) || playerCapability.isTapping(metal)) {
                return true;
            }
        }
        return false;
    }

    private static void cognitiveMetals(IInvestedPlayerData playerCapability, Player player) {
        if (playerCapability.isTapping(MetalTagEnum.BRASS)) {
            BrassFeruchemicHelper.tapPower(player);
        } else if (playerCapability.isStoring(MetalTagEnum.BRASS)) {
            BrassFeruchemicHelper.storagePower(player);
        }
        if (playerCapability.isTapping(MetalTagEnum.BRONZE)) {
            BronzeFeruchemicHelper.tapPower(player);
        }
        if (playerCapability.isTapping(MetalTagEnum.COPPER)) {
            CopperFeruchemicHelper.tapPower(player);
        } else if (playerCapability.isStoring(MetalTagEnum.COPPER)) {
            CopperFeruchemicHelper.storagePower(player);
        }
    }

    private static void cognitiveMetalsEach40Ticks(IInvestedPlayerData playerCapability, Player player) {
        if (playerCapability.isStoring(MetalTagEnum.BRONZE)) {
            BronzeFeruchemicHelper.storagePower(player);
        }
    }

    private static void godMetalsEach5Ticks(IInvestedPlayerData playerCapability, Player player) {
        if (playerCapability.isTapping(MetalTagEnum.ATIUM)) {
            AtiumFeruchemicHelper.tapPower(player);
        } else if (playerCapability.isStoring(MetalTagEnum.ATIUM)) {
            AtiumFeruchemicHelper.storagePower(player);
        }
    }

    private static void hybridMetalsEach40Ticks(IInvestedPlayerData playerCapability, Player player) {
        if (playerCapability.isTapping(MetalTagEnum.GOLD)) {
            GoldFeruchemicHelper.tapPower(player);
        } else if (playerCapability.isStoring(MetalTagEnum.GOLD)) {
            GoldFeruchemicHelper.storagePower(player);
        }
        if (playerCapability.isTapping(MetalTagEnum.CADMIUM)) {
            CadmiumFeruchemicHelper.tapPower(player);
        }
        if (playerCapability.isTapping(MetalTagEnum.BENDALLOY)) {
            BendalloyFeruchemicHelper.tapPower(player);
        } else if (playerCapability.isStoring(MetalTagEnum.BENDALLOY)) {
            BendalloyFeruchemicHelper.storagePower(player);
        }
    }

    private static void hybridMetals(IInvestedPlayerData playerCapability, Player player) {
        if (playerCapability.isTapping(MetalTagEnum.ELECTRUM)) {
            ElectrumFeruchemicHelper.tapPower(player);
        } else if (playerCapability.isStoring(MetalTagEnum.ELECTRUM)) {
            ElectrumFeruchemicHelper.storagePower(player);
        }
        if (playerCapability.isStoring(MetalTagEnum.CADMIUM)) {
            CadmiumFeruchemicHelper.storagePower(player);
        }
    }

    private static void physicalMetalsEach5Ticks(IInvestedPlayerData playerCapability, Player player) {
        if (playerCapability.isTapping(MetalTagEnum.IRON)) {
            IronFeruchemicHelper.tapPower(player);
        } else if (playerCapability.isStoring(MetalTagEnum.IRON)) {
            IronFeruchemicHelper.storagePower(player);
        }
        if (playerCapability.isTapping(MetalTagEnum.STEEL)) {
            SteelFeruchemicHelper.tapPower(player);
        } else if (playerCapability.isStoring(MetalTagEnum.STEEL)) {
            SteelFeruchemicHelper.storagePower(player);
        }
        if (playerCapability.isTapping(MetalTagEnum.TIN)) {
            TinFeruchemicHelper.tapPower(player);
        } else if (playerCapability.isStoring(MetalTagEnum.TIN)) {
            TinFeruchemicHelper.storagePower(player);
        }
        if (playerCapability.isTapping(MetalTagEnum.PEWTER)) {
            PewterFeruchemicHelper.tapPower(player);
        } else if (playerCapability.isStoring(MetalTagEnum.PEWTER)) {
            PewterFeruchemicHelper.storagePower(player);
        }
    }
    private static void spiritualMetalsEach5Ticks(IInvestedPlayerData playerCapability, Player player) {

        if (playerCapability.isTapping(MetalTagEnum.DURALUMIN)) {
            DuraluminFeruchemicHelper.tapPower(player);
        } else if (playerCapability.isStoring(MetalTagEnum.DURALUMIN)) {
            DuraluminFeruchemicHelper.storagePower(player);
        }
        if (playerCapability.isTapping(MetalTagEnum.CHROMIUM)) {
            ChromiumFeruchemicHelper.tapPower(player);
        } else if (playerCapability.isStoring(MetalTagEnum.CHROMIUM)) {
            ChromiumFeruchemicHelper.storagePower(player);
        }
    }
}
