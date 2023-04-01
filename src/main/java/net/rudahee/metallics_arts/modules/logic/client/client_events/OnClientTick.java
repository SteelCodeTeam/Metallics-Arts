package net.rudahee.metallics_arts.modules.logic.client.client_events;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.TickEvent;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.player.IInvestedPlayerData;
import net.rudahee.metallics_arts.modules.error_handling.exceptions.PlayerException;
import net.rudahee.metallics_arts.modules.logic.client.client_events.on_world_tick.PullOrAttackKey;
import net.rudahee.metallics_arts.modules.logic.client.client_events.on_world_tick.PushOrUseKey;
import net.rudahee.metallics_arts.modules.logic.client.client_events.on_world_tick.VerticalJump;
import net.rudahee.metallics_arts.setup.registries.ModKeyRegister;
import net.rudahee.metallics_arts.utils.FoundNearbyMetalUtils;
import net.rudahee.metallics_arts.utils.MathUtils;

import javax.annotation.Nullable;

/**
 * The OnClientTick class is responsible for managing client-side tick events
 * in a modded Minecraft environment. It is designed to handle various key inputs
 * from the player and update the game state accordingly, with a focus on mod-specific
 * functionality.
 * <p>
 * This class should only be used on the client side and is marked as such using the
 * @OnlyIn(Dist.CLIENT) annotation.
 * <p>
 * The class also handles tick counting and resetting, with tick values ranging from 0 to 239.
 *
 * @author SteelCode Team
 * @since 1.5.1
 */
@OnlyIn(Dist.CLIENT)
public class OnClientTick {

    public static int tick = 0;

    /**
     * This method handles the client-side tick event by updating the game state based
     * on key inputs from the player. It is designed to be called on the client side
     * and processes the following actions:
     * <p>
     * - Attack key: Executes the attackKey method if the attack key is pressed.
     * - Allomantic push key: Executes the pushKey method if the custom ALLOMANTIC_PUSH keybind is pressed.
     * - Use key: Executes the useKey method if the use key is pressed.
     * - Vertical jump and flying: Executes the fly method if the jump key, custom VERTICAL_JUMP keybind,
     *   and the STEEL metal tag are all activated.
     * <p>
     * The method also handles tick counting and resetting, with tick values ranging from 0 to 239.
     *
     * @param event        The TickEvent.ClientTickEvent to be processed.
     * @param minecraft    The instance of the Minecraft game.
     * @param player       The player character, nullable.
     * @param capability   The IInvestedPlayerData capability of the player, nullable.
     * @throws PlayerException If any error occurs related to the player.
     */
    @OnlyIn(Dist.CLIENT)
    public static void onClientTick(TickEvent.ClientTickEvent event, Minecraft minecraft, @Nullable Player player, @Nullable IInvestedPlayerData capability) throws PlayerException {

        if (event.side.isServer()) {
            return;
        }

        if (event.phase != TickEvent.Phase.END || minecraft.isPaused() || !player.isAlive() || capability == null) {
            return;
        }

        if (capability.isInvested()) {
            if (MathUtils.isDivisibleBy3(tick)) {

                FoundNearbyMetalUtils.redoLists(player, capability);

                if (ModKeyRegister.ALLOMANTIC_PULL.isDown()) {
                    PullOrAttackKey.pullKey(capability, minecraft, player, player.getLevel());
                }
                if (minecraft.options.keyAttack.isDown()) {
                    PullOrAttackKey.attackKey(capability, minecraft, player, player.getLevel());
                }

                if (ModKeyRegister.ALLOMANTIC_PUSH.isDown()) {
                    PushOrUseKey.pushKey(capability, minecraft, player, player.getLevel());
                }

                if (minecraft.options.keyUse.isDown()) {
                    PushOrUseKey.useKey(capability, minecraft, player, player.getLevel());
                }
            }

            if (MathUtils.isDivisibleBy10(tick)) {
                if (minecraft.options.keyJump.isDown() && ModKeyRegister.VERTICAL_JUMP.isDown() && capability.isBurning(MetalTagEnum.STEEL)) {
                    VerticalJump.fly(capability, minecraft, player, player.getLevel());
                }
            }

        }

        if (tick < 240) {
            tick++;
        } else {
            tick = 0;
        }
    }
}
