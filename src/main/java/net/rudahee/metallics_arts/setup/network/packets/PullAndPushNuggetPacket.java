package net.rudahee.metallics_arts.setup.network.packets;


import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.rudahee.metallics_arts.modules.logic.server.powers.allomancy.physical_metals.IronAndSteelHelpers;
import net.rudahee.metallics_arts.setup.network.ModNetwork;

import java.util.function.Supplier;

/**
 * Class to communicate data between Client game and Server game. This packet it's for send a request
 * to the server to use iron or steel on a block, but with a nugget, if valid block doesn't exist.
 * <p>
 * <b>Encode and decode must be symmetrical!</b>
 *
 * @author SteelCode Team
 * @since 1.5.1
 *
 * @see FriendlyByteBuf
 * @see NetworkRegistry
 * @see ModNetwork
 * @see NetworkEvent.Context
 */
public class PullAndPushNuggetPacket {

    private final BlockPos blockPos;
    private final int direction;

    /**
     * Default constructor that receive all mandatory data.
     *
     * @param block the block to push/pull
     * @param direction the direction (1 for push, -1 for pull)
     */
    public PullAndPushNuggetPacket(BlockPos block, int direction) {
        this.blockPos = block;
        this.direction = direction;
    }

    /**
     * Static method to decode data from buffer.
     *
     * @param buf buffer to be decoded.
     *
     * @return PullAndPushNuggetPacket
     */
    public static PullAndPushNuggetPacket decode(FriendlyByteBuf buf) {
        return new PullAndPushNuggetPacket(buf.readBlockPos(), buf.readInt());
    }

    /**
     * Static method to encode data to buffer.
     *
     * @param buf buffer to be decoded.
     */
    public void encode(FriendlyByteBuf buf) {
        buf.writeBlockPos(this.blockPos);
        buf.writeInt(this.direction);
    }

    /**
     * Method to handle and do anything when packet its received and decoded.
     *
     * @param ctx Network context with all data of the packet.
     */
    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            BlockPos pos = this.blockPos;
            // Sanity check to make sure  the block is loaded in the server
            if (player.level.isLoaded(pos)) {
                IronAndSteelHelpers.move(this.direction, player, pos);
            }
        });
        ctx.get().setPacketHandled(true);
    }


}
