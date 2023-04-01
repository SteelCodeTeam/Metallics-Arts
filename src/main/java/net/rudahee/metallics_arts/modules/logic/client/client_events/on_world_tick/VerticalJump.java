package net.rudahee.metallics_arts.modules.logic.client.client_events.on_world_tick;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.player.IInvestedPlayerData;
import net.rudahee.metallics_arts.modules.logic.server.powers.allomancy.physical_metals.IronAndSteelHelpers;
import net.rudahee.metallics_arts.setup.network.ModNetwork;
import net.rudahee.metallics_arts.setup.network.packets.PullAndPushBlockPacket;
import net.rudahee.metallics_arts.setup.network.packets.PullAndPushNuggetPacket;
import net.rudahee.metallics_arts.setup.network.packets.RemoveNuggetPacket;

/**
 * This class contains a method to handle the vertical jump or fly action for a player based on their
 * capabilities and the type of metal they are currently burning.
 *
 * @author SteelCode Team
 * @since 1.5.1
 */
public class VerticalJump {

    /**
     * Handles the fly action, which allows the player to jump or fly vertically based on the metal
     * they are burning and the surrounding blocks. The player can move up or down depending on the
     * key pressed.
     *
     * @param capability the IInvestedPlayerData instance representing the player's capabilities
     * @param minecraft  the Minecraft instance
     * @param player     the Player instance
     * @param level      the Level instance
     * @see IInvestedPlayerData
     */
    public static void fly(IInvestedPlayerData capability, Minecraft minecraft, Player player, Level level) {

        double x = player.getX();
        double y = player.getY();
        double z = player.getZ();

        BlockPos blockPos = new BlockPos(x, y, z);
        Vec3 vector = player.getViewVector(1.0F);

        double pushX;
        double pushZ;

        int maxAltitude = 10;
        int range = 7;

        if (minecraft.options.keyUp.isDown()) {
            pushX = x - (vector.x * range);
            pushZ = z - (vector.z * range);
        } else if (minecraft.options.keyDown.isDown()) {
            pushX = x + (vector.x * range);
            pushZ = z + (vector.z * range);
        } else {
            pushX = x - (vector.x * range);
            pushZ = z - (vector.z * range);
        }
        blockPos = new BlockPos(pushX, y, pushZ);

        for (int i = 0; i < maxAltitude; i++) {
            if (player.level.getBlockState(blockPos).is(Blocks.AIR)) {
                blockPos = new BlockPos(blockPos.getX(), blockPos.getY() - 1, blockPos.getZ());
            }
        }

        if (!player.level.getBlockState(blockPos).is(Blocks.AIR)) {
            // IF ITS A BLOCK
            BlockPos blockPosition = blockPos;

            int slot = IronAndSteelHelpers.haveNuggets(player);


            if (IronAndSteelHelpers.isBlockStateMetal(minecraft.level.getBlockState(blockPosition))) {
                ModNetwork.sendToServer(new PullAndPushBlockPacket(blockPosition,
                        Math.round(IronAndSteelHelpers.PUSH * IronAndSteelHelpers.getMultiplier(player, capability.getEnhanced(),
                                capability.isBurning(MetalTagEnum.LERASIUM)))));

            } else if (slot != -1) {
                ModNetwork.sendToServer(new PullAndPushNuggetPacket(blockPosition,
                        Math.round(IronAndSteelHelpers.PUSH * IronAndSteelHelpers.getMultiplier(player, capability.getEnhanced(),
                                capability.isBurning(MetalTagEnum.LERASIUM)))));

                ModNetwork.sendToServer(new RemoveNuggetPacket(slot, player));

            }
        }
    }
}