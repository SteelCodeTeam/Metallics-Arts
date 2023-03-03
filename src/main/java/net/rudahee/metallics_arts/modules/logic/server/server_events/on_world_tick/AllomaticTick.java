package net.rudahee.metallics_arts.modules.logic.server.server_events.on_world_tick;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
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
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.AbstractFechuchemicHelper;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

/**
 * This class contains the methods to execute the Allimantic powers that are carried out passively, in all the ticks of the game.
 *
 * @author SteelCode Team
 * @since 1.5.1
 */
public class AllomaticTick {

    /**
     * This method is used to invoke the methods that the helpers execute, distributing them into: physical, mental, temporal, spiritual and divine metals.
     *
     * @param playerCapability capabilities (data) of the player.
     * @param player to whom the effect will be applied.
     * @param level in which the player is located (world).
     */
    public static void allomanticTick(IInvestedPlayerData playerCapability, ServerPlayer player, ServerLevel level) {
        OnTickUtils.duraluminAndExternalNicrosilEffect(playerCapability);

        if (playerCapability.isBurning(MetalTagEnum.TIN) || playerCapability.isBurning(MetalTagEnum.PEWTER)) {
            physicalMetals(playerCapability, player, level);
        }
        if (playerCapability.isBurning(MetalTagEnum.COPPER) || playerCapability.isBurning(MetalTagEnum.BRONZE)
                || playerCapability.isBurning(MetalTagEnum.ZINC) || playerCapability.isBurning(MetalTagEnum.BRASS)) {
            mentalMetals(playerCapability, player, level);
        }
        if (playerCapability.isBurning(MetalTagEnum.BENDALLOY) || playerCapability.isBurning(MetalTagEnum.CADMIUM)
                || playerCapability.isBurning(MetalTagEnum.GOLD) || playerCapability.isBurning(MetalTagEnum.ELECTRUM)) {
            temporalMetals(playerCapability, player, level);
        }
        if (playerCapability.isBurning(MetalTagEnum.CHROMIUM) || playerCapability.isBurning(MetalTagEnum.ALUMINUM)) {
            spiritualMetals(playerCapability, player, level);
        }
        if (playerCapability.isBurning(MetalTagEnum.ETTMETAL) || playerCapability.isBurning(MetalTagEnum.MALATIUM)) {
            godMetals(playerCapability, player, level);
        }

        if (MalatiumAllomanticHelper.isPosRegistered() && !playerCapability.isBurning(MetalTagEnum.MALATIUM)) {
            MalatiumAllomanticHelper.setPos(null, null);
        }

    }

    /**
     * This method is in charge of executing the helpers of the divine metals
     *
     * @param playerCapability capabilities (data) of the player.
     * @param player to whom the effect will be applied.
     * @param level in which the player is located (world).
     */
    private static void godMetals(IInvestedPlayerData playerCapability, ServerPlayer player, ServerLevel level) {
        if (playerCapability.isBurning(MetalTagEnum.ETTMETAL)) {
            EttmetalAllomanticHelper.ettmetalExplotion(level, playerCapability, player);
        }
        if (playerCapability.isBurning(MetalTagEnum.MALATIUM)) {
            MalatiumAllomanticHelper.teleportToDeathPosFromAnotherPlayer(level, playerCapability, player, playerCapability.isBurning(MetalTagEnum.LERASIUM));
        }
    }

    /**
     * This method is in charge of executing the helpers of the spiritual metals.
     *
     * @param playerCapability capabilities (data) of the player.
     * @param player to whom the effect will be applied.
     * @param level in which the player is located (world).
     */
    private static void spiritualMetals(IInvestedPlayerData playerCapability, ServerPlayer player, ServerLevel level) {
        if (playerCapability.isBurning(MetalTagEnum.CHROMIUM) && playerCapability.getEnhanced()) {
            ChromiumAllomanticHelper.drainMetalCloudChromium(player, level, playerCapability.isBurning(MetalTagEnum.LERASIUM));
        }

        if (playerCapability.isBurning(MetalTagEnum.ALUMINUM)) {
            AluminumAllomanticHelper.drainAndCleanEffects(player,playerCapability);
        }

    }

    /**
     * This method is responsible for executing the helpers of the temporary metals.
     *
     * @param playerCapability capabilities (data) of the player.
     * @param player to whom the effect will be applied.
     * @param level in which the player is located (world).
     */
    private static void temporalMetals(IInvestedPlayerData playerCapability, ServerPlayer player, ServerLevel level) {
        if (playerCapability.isBurning(MetalTagEnum.BENDALLOY) && !playerCapability.isBurning(MetalTagEnum.CADMIUM)) {
            BendalloyAllomanticHelper.BendalloyMobEffects(player, level,
                    playerCapability.getEnhanced(), playerCapability.isBurning(MetalTagEnum.LERASIUM));
            BendalloyAllomanticHelper.AddAiSteeps(player, playerCapability.getEnhanced());
        }

        if (playerCapability.isBurning(MetalTagEnum.CADMIUM) && !playerCapability.isBurning(MetalTagEnum.BENDALLOY)) {
            CadmiumAllomanticHelper.CadmiumMobEffectsOtherPlayers(player, playerCapability,
                    level, playerCapability.getEnhanced(), playerCapability.isBurning(MetalTagEnum.LERASIUM));
            CadmiumAllomanticHelper.CadmiumEffectSelfPlayer(player, playerCapability.getEnhanced());
        }

        if (playerCapability.isBurning(MetalTagEnum.GOLD) && playerCapability.getEnhanced()) {
            GoldAllomanticHelper.teleportToDeathPos(level,playerCapability,player, playerCapability.isBurning(MetalTagEnum.LERASIUM));
        }

        if (playerCapability.isBurning(MetalTagEnum.ELECTRUM) && playerCapability.getEnhanced()) {
            ElectrumAllomanticHelper.teleportToSpawn(level,playerCapability,player, playerCapability.isBurning(MetalTagEnum.LERASIUM));
        }

    }

    /**
     * This method is responsible for executing the helpers of mental metals.
     *
     * @param playerCapability capabilities (data) of the player.
     * @param player to whom the effect will be applied.
     * @param level in which the player is located (world).
     */
    private static void mentalMetals(IInvestedPlayerData playerCapability, ServerPlayer player, ServerLevel level) {

        if (playerCapability.isBurning(MetalTagEnum.COPPER)) {
            CopperAllomanticHelper.CopperAiEntityManipulation(player, level,
                    playerCapability.getEnhanced(), playerCapability.isBurning(MetalTagEnum.LERASIUM));
        }

        if (playerCapability.isBurning(MetalTagEnum.BRONZE)) {
            BronzeAllomanticHelper.BronzeAiEntityManipulation(player, level,
                    playerCapability.getEnhanced(), playerCapability.isBurning(MetalTagEnum.LERASIUM));
        }

        if (playerCapability.isBurning(MetalTagEnum.ZINC) && playerCapability.isBurning(MetalTagEnum.LERASIUM)) {
            ZincAllomanticHelper.angryEntitiesWithLerasium(player, level, playerCapability.getEnhanced());
        }

        if (playerCapability.isBurning(MetalTagEnum.BRASS) && playerCapability.isBurning(MetalTagEnum.LERASIUM)) {
            BrassAllomanticHelper.happyEntitiesWithLerasium(player, level, playerCapability.getEnhanced());
        }
    }

    /**
     * This method is responsible for executing the helpers of physical metals.
     *
     * @param playerCapability capabilities (data) of the player.
     * @param player to whom the effect will be applied.
     * @param level in which the player is located (world).
     */
    private static void physicalMetals(IInvestedPlayerData playerCapability, ServerPlayer player, ServerLevel level) {

        if (playerCapability.isBurning(MetalTagEnum.TIN)) {
                TinAllomanticHelper.addTinEffects(player, playerCapability.getEnhanced());
        }

        if (playerCapability.isBurning(MetalTagEnum.PEWTER)) {
            PewterAllomanticHelper.addPewterEffects(player, playerCapability.isBurning(MetalTagEnum.LERASIUM), playerCapability.getEnhanced());
        }
    }


}
