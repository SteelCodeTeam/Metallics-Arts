package net.rudahee.metallics_arts.utils.event_utils.on_world_tick;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.player.IInvestedPlayerData;
import net.rudahee.metallics_arts.modules.logic.server.powers.allomancy.god_metals.EttmetalAllomanticHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.allomancy.god_metals.MalatiumAllomanticHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.allomancy.mental_metals.BrassAllomanticHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.allomancy.mental_metals.BronzeAllomanticHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.allomancy.mental_metals.CopperAllomanticHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.allomancy.mental_metals.ZincAllomanticHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.allomancy.physical_metals.PewterAllomanticHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.allomancy.physical_metals.TinAllomanticHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.allomancy.spiritual_metals.AluminumAllomanticHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.allomancy.spiritual_metals.ChromiumAllomanticHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.allomancy.temporal_metals.BendalloyAllomanticHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.allomancy.temporal_metals.CadmiumAllomanticHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.allomancy.temporal_metals.ElectrumAllomanticHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.allomancy.temporal_metals.GoldAllomanticHelper;

public class AllomaticTick {

    public static void allomanticTick(IInvestedPlayerData capability, ServerPlayer player, ServerLevel level) {
        OnTickUtils.duraluminAndExternalNicrosilEffect(capability);

        if (capability.isBurning(MetalTagEnum.TIN) || capability.isBurning(MetalTagEnum.PEWTER)) {
            physicalMetals(capability, player, level);
        }
        if (capability.isBurning(MetalTagEnum.COPPER) || capability.isBurning(MetalTagEnum.BRONZE)
                || capability.isBurning(MetalTagEnum.ZINC) || capability.isBurning(MetalTagEnum.BRASS)) {
            mentalMetals(capability, player, level);
        }
        if (capability.isBurning(MetalTagEnum.BENDALLOY) || capability.isBurning(MetalTagEnum.CADMIUM)
                || capability.isBurning(MetalTagEnum.GOLD) || capability.isBurning(MetalTagEnum.ELECTRUM)) {
            temporalMetals(capability, player, level);
        }
        if (capability.isBurning(MetalTagEnum.CHROMIUM) || capability.isBurning(MetalTagEnum.ALUMINUM)) {
            spiritualMetals(capability, player, level);
        }
        if (capability.isBurning(MetalTagEnum.ETTMETAL)) {
            godMetals(capability, player, level);
        }

    }

    private static void godMetals(IInvestedPlayerData capability, ServerPlayer player, ServerLevel level) {
        if (capability.isBurning(MetalTagEnum.ETTMETAL)){
            EttmetalAllomanticHelper.ettmetalExplotion(level, player);
        }

        if (capability.isBurning(MetalTagEnum.MALATIUM)){
            MalatiumAllomanticHelper.teleportToDeathPosFromAnotherPlayer(level, capability, player, capability.isBurning(MetalTagEnum.LERASIUM));
        } /*else if (MalatiumAllomanticHelper.getBlock() != null || MalatiumAllomanticHelper.getDimension() != null) {
            MalatiumAllomanticHelper.setBlock(null);
            MalatiumAllomanticHelper.setDimension(null);
        }*/
    }

    private static void spiritualMetals(IInvestedPlayerData capability, ServerPlayer player, ServerLevel level) {
        if (capability.isBurning(MetalTagEnum.CHROMIUM) && capability.getEnhanced()) {
            ChromiumAllomanticHelper.drainMetalCloudChromium(player, level, capability.isBurning(MetalTagEnum.LERASIUM));
        }

        if (capability.isBurning(MetalTagEnum.ALUMINUM)) {
            AluminumAllomanticHelper.drainAndCleanEffects(player,capability);
        }

    }

    private static void temporalMetals(IInvestedPlayerData capability, ServerPlayer player, ServerLevel level) {
        if (capability.isBurning(MetalTagEnum.BENDALLOY) && !capability.isBurning(MetalTagEnum.CADMIUM)) {
            BendalloyAllomanticHelper.BendalloyMobEffects(player, level,
                    capability.getEnhanced(), capability.isBurning(MetalTagEnum.LERASIUM));
            BendalloyAllomanticHelper.AddAiSteeps(player, capability.getEnhanced());
        }

        if (capability.isBurning(MetalTagEnum.CADMIUM) && !capability.isBurning(MetalTagEnum.BENDALLOY)) {
            CadmiumAllomanticHelper.CadmiumMobEffectsOtherPlayers(player, capability,
                    level, capability.getEnhanced(), capability.isBurning(MetalTagEnum.LERASIUM));
            CadmiumAllomanticHelper.CadmiumEffectSelfPlayer(player, capability.getEnhanced());
        }

        if (capability.isBurning(MetalTagEnum.GOLD) && capability.getEnhanced()) {
            GoldAllomanticHelper.teleportToDeathPos(level,capability,player, capability.isBurning(MetalTagEnum.LERASIUM));
        }

        if (capability.isBurning(MetalTagEnum.ELECTRUM) && capability.getEnhanced()) {
            ElectrumAllomanticHelper.teleportToSpawn(level,capability,player, capability.isBurning(MetalTagEnum.LERASIUM));
        }

    }

    private static void mentalMetals(IInvestedPlayerData capability, ServerPlayer player, ServerLevel level) {

        if (capability.isBurning(MetalTagEnum.COPPER)) {
            CopperAllomanticHelper.CopperAiEntityManipulation(player, level,
                    capability.getEnhanced(), capability.isBurning(MetalTagEnum.LERASIUM));
        }

        if (capability.isBurning(MetalTagEnum.BRONZE)) {
            BronzeAllomanticHelper.BronzeAiEntityManipulation(player, level,
                    capability.getEnhanced(), capability.isBurning(MetalTagEnum.LERASIUM));
        }

        if (capability.isBurning(MetalTagEnum.ZINC) && capability.isBurning(MetalTagEnum.LERASIUM)) {
            ZincAllomanticHelper.angryEntitiesWithLerasium(player, level, capability.getEnhanced());
        }

        if (capability.isBurning(MetalTagEnum.BRASS) && capability.isBurning(MetalTagEnum.LERASIUM)) {
            BrassAllomanticHelper.happyEntitiesWithLerasium(player, level, capability.getEnhanced());
        }
    }

    private static void physicalMetals(IInvestedPlayerData capability, ServerPlayer player, ServerLevel level) {

        if (capability.isBurning(MetalTagEnum.TIN)) {
                TinAllomanticHelper.addTinEffects(player, capability.getEnhanced());
        }

        if (capability.isBurning(MetalTagEnum.PEWTER)) {
            PewterAllomanticHelper.addPewterEffects(player, capability.isBurning(MetalTagEnum.LERASIUM), capability.getEnhanced());
        }
    }


}
