package net.rudahee.metallics_arts.modules.logic.server.server_events.on_world_tick;

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
import net.rudahee.metallics_arts.modules.effects.ModEffects;

import java.util.logging.Level;

/**
 * Handles the effects and abilities related to Allomantic metals for players.
 *
 * @author SteelCode Team
 * @since 1.5.1
 */
public class AllomaticTick {

    /**
     * Handles the effects of physical, mental, temporal, and god metals that
     * are activated every 3 ticks.
     *
     * @param playerCapability The player's Allomantic and Feruchemical abilities.
     * @param player           The player for whom the actions are being performed.
     * @param level            The server level in which the player is located.
     */
    public static void each3Ticks(IInvestedPlayerData playerCapability, ServerPlayer player, ServerLevel level) {

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
        if (MalatiumAllomanticHelper.isPosRegistered() && !playerCapability.isBurning(MetalTagEnum.MALATIUM)) {
            MalatiumAllomanticHelper.setPos(null, null);
        }
    }

    /**
     * Handles the effects of spiritual metals that are activated every tick.
     * Drains metals when the player is burning Chromium or Aluminum.
     *
     * @param playerCapability The player's Allomantic and Feruchemical abilities.
     * @param player           The player for whom the actions are being performed.
     * @param level            The server level in which the player is located.
     * @return True if metals have been drained, false otherwise.
     */
    public static boolean eachTickWithInstantDrain(IInvestedPlayerData playerCapability, ServerPlayer player, ServerLevel level) {

        boolean isMetalsDrained = false;

        if (playerCapability.isBurning(MetalTagEnum.CHROMIUM) || playerCapability.isBurning(MetalTagEnum.ALUMINUM)) {
            isMetalsDrained = spiritualMetals(playerCapability, player, level);
        }

        return isMetalsDrained;
    }

    /**
     * Handles the effects of god metals (Ettmetal and Malatium).
     *
     * @param playerCapability The player's Allomantic and Feruchemical abilities.
     * @param player           The player for whom the actions are being performed.
     * @param level            The server level in which the player is located.
     */
    private static void godMetals(IInvestedPlayerData playerCapability, ServerPlayer player, ServerLevel level) {
        if (playerCapability.isBurning(MetalTagEnum.ETTMETAL)) {
            EttmetalAllomanticHelper.ettmetalExplotion(level, playerCapability, player);
        }
        if (playerCapability.isBurning(MetalTagEnum.MALATIUM) && playerCapability.isBurning(MetalTagEnum.DURALUMIN) && MalatiumAllomanticHelper.isPosRegistered()) {
            MalatiumAllomanticHelper.teleportToDeathPosFromAnotherPlayer(level, playerCapability, player, playerCapability.isBurning(MetalTagEnum.LERASIUM));
        }
    }

    /**
     * Handles the effects of spiritual metals (Chromium and Aluminum).
     *
     * @param playerCapability The player's Allomantic and Feruchemical abilities.
     * @param player           The player for whom the actions are being performed.
     * @param level            The server level in which the player is located.
     * @return True if metals have been drained, false otherwise.
     */
    private static boolean spiritualMetals(IInvestedPlayerData playerCapability, ServerPlayer player, ServerLevel level) {

        boolean drainedMetals = false;

        if (playerCapability.isBurning(MetalTagEnum.CHROMIUM) && playerCapability.getEnhanced()) {
            ChromiumAllomanticHelper.drainMetalCloudChromium(player, level, playerCapability.isBurning(MetalTagEnum.LERASIUM));
        }

        if (playerCapability.isBurning(MetalTagEnum.ALUMINUM)) {
            drainedMetals = AluminumAllomanticHelper.drainAndCleanEffects(player,playerCapability);
        }

        return drainedMetals;
    }

    /**
     * Handles the effects of temporal metals (Bendalloy, Cadmium, Gold, and Electrum).
     *
     * @param playerCapability The player's Allomantic and Feruchemical abilities.
     * @param player           The player for whom the actions are being performed.
     * @param level            The server level in which the player is located.
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
     * Handles the effects of mental metals (Copper, Bronze, Zinc, and Brass).
     *
     * @param playerCapability The player's Allomantic and Feruchemical abilities.
     * @param player           The player for whom the actions are being performed.
     * @param level            The server level in which the player is located.
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
     * Handles the effects of physical metals (Tin and Pewter).
     *
     * @param playerCapability The player's Allomantic and Feruchemical abilities.
     * @param player           The player for whom the actions are being performed.
     * @param level            The server level in which the player is located.
     */
    private static void physicalMetals(IInvestedPlayerData playerCapability, ServerPlayer player, ServerLevel level) {

        if (playerCapability.isBurning(MetalTagEnum.TIN)) {
                TinAllomanticHelper.addTinEffects(player, playerCapability.getEnhanced());
        }

        if (playerCapability.isBurning(MetalTagEnum.PEWTER)) {
            PewterAllomanticHelper.addPewterEffects(player, playerCapability.isBurning(MetalTagEnum.LERASIUM), playerCapability.getEnhanced());
        }
    }

    /**
     * Handles the effects of each tick for Duralumin and external Nicrosil.
     *
     * @param capability The player's Allomantic and Feruchemical abilities.
     * @param player     The player for whom the actions are being performed.
     */
    public static void eachTick(IInvestedPlayerData capability, ServerPlayer player, ServerLevel level) {
        if (capability.isBurning(MetalTagEnum.ETTMETAL) || capability.isBurning(MetalTagEnum.MALATIUM)) {
            godMetals(capability, player, level);
        }

        OnTickUtils.duraluminAndExternalNicrosilEffect(capability, player);
        for (MetalTagEnum metal : MetalTagEnum.values()) {
            if (capability.isBurning(metal)) {
                ModEffects.giveAllomanticEffect(player, metal);
            }
        }
    }
}
