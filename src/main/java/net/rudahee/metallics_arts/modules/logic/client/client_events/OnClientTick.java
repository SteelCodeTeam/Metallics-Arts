package net.rudahee.metallics_arts.modules.logic.client.client_events;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.TickEvent;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.player.IInvestedPlayerData;
import net.rudahee.metallics_arts.modules.error_handling.exceptions.PlayerException;
import net.rudahee.metallics_arts.modules.error_handling.messages.ErrorTypes;
import net.rudahee.metallics_arts.modules.logic.client.client_events.on_world_tick.PullOrAttackKey;
import net.rudahee.metallics_arts.modules.logic.client.client_events.on_world_tick.PushOrUseKey;
import net.rudahee.metallics_arts.modules.logic.client.client_events.on_world_tick.VerticalJump;
import net.rudahee.metallics_arts.setup.registries.ModKeyRegister;
import net.rudahee.metallics_arts.utils.FoundNearbyMetalUtils;
import net.rudahee.metallics_arts.utils.MathUtils;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class OnClientTick {
    public static int tick = 0;
    @OnlyIn(Dist.CLIENT)
    public static void onClientTick(TickEvent.ClientTickEvent event, Minecraft minecraft, @Nullable Player player, @Nullable IInvestedPlayerData capability) throws PlayerException {

        if (event.side.isServer()) {
            return;
        }

        if (event.phase != TickEvent.Phase.END || minecraft.isPaused() || !player.isAlive()) {
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
