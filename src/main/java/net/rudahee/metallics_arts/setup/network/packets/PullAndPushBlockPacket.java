package net.rudahee.metallics_arts.setup.network.packets;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import net.rudahee.metallics_arts.modules.logic.server.powers.IronAndSteelHelpers;

import java.util.function.Supplier;

public class PullAndPushBlockPacket {

    private final BlockPos blockPos;
    private final int direction;

    /**
     * Send a request to the server to use iron or steel on a block
     *
     * @param block     the block
     * @param direction the direction (1 for push, -1 for pull)
     */
    public PullAndPushBlockPacket(BlockPos block, int direction) {
        this.blockPos = block;
        this.direction = direction;
    }

    public static PullAndPushBlockPacket decode(FriendlyByteBuf buf) {
        return new PullAndPushBlockPacket(buf.readBlockPos(), buf.readInt());
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeBlockPos(this.blockPos);
        buf.writeInt(this.direction);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            BlockPos pos = this.blockPos;

            // Sanity check to make sure  the block is loaded in the server
            if (player.level.isLoaded(pos)) {
                // activate blocks
                if (IronAndSteelHelpers.isBlockStateMetal(player.level.getBlockState(pos))){ // Check whitelist on server)
                    IronAndSteelHelpers.move(this.direction, player, pos);
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }


}
