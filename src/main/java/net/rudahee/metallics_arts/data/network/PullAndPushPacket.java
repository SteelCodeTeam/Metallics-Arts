package net.rudahee.metallics_arts.data.network;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.network.NetworkEvent;
import net.rudahee.metallics_arts.modules.blocks.IAllomancyUsableBlock;
import net.rudahee.metallics_arts.modules.powers.helpers.IronAndSteelHelpers;

import java.util.function.Supplier;

public class PullAndPushPacket {

    private final BlockPos blockPos;
    private final int direction;

    /**
     * Send a request to the server to use iron or steel on a block
     *
     * @param block     the block
     * @param direction the direction (1 for push, -1 for pull)
     */
    public PullAndPushPacket(BlockPos block, int direction) {
        this.blockPos = block;
        this.direction = direction;
    }

    public static PullAndPushPacket decode(PacketBuffer buf) {
        return new PullAndPushPacket(buf.readBlockPos(), buf.readInt());
    }

    public void encode(PacketBuffer buf) {
        buf.writeBlockPos(this.blockPos);
        buf.writeInt(this.direction);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayerEntity player = ctx.get().getSender();
            BlockPos pos = this.blockPos;
            // Sanity check to make sure  the block is loaded in the server
            if (player.level.isLoaded(pos)) {
                // activate blocks
                if (IronAndSteelHelpers.isBlockStateMetal(player.level.getBlockState(pos)) // Check whitelist on server
                        && (!player.getProjectile(player.getMainHandItem()).isEmpty()) && this.direction > 0) {
                    IronAndSteelHelpers.move(this.direction, player, pos);
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
