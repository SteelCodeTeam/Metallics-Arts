package net.rudahee.metallics_arts.data.network;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.network.NetworkEvent;
import net.rudahee.metallics_arts.modules.data_player.InvestedCapability;
import net.rudahee.metallics_arts.modules.powers.helpers.IronAndSteelHelpers;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;
import net.rudahee.metallics_arts.setup.network.ModNetwork;

import java.util.function.Supplier;

public class PullAndPushNuggetPacket {

    private final BlockPos blockPos;
    private final int direction;

    /**
     * Send a request to the server to use iron or steel on a block
     *
     * @param block     the block
     * @param direction the direction (1 for push, -1 for pull)
     */
    public PullAndPushNuggetPacket(BlockPos block, int direction) {
        this.blockPos = block;
        this.direction = direction;
    }

    public static PullAndPushNuggetPacket decode(PacketBuffer buf) {
        return new PullAndPushNuggetPacket(buf.readBlockPos(), buf.readInt());
    }

    public void encode(PacketBuffer buf) {
        buf.writeBlockPos(this.blockPos);
        buf.writeInt(this.direction);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayerEntity player = ctx.get().getSender();
            BlockPos pos = this.blockPos;

            player.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(capability -> {

                if (capability.isBurning(MetalsNBTData.DURALUMIN)) {
                    if (capability.isBurning(MetalsNBTData.IRON)) {
                        capability.drainMetals(MetalsNBTData.DURALUMIN, MetalsNBTData.IRON);
                    }
                    if (capability.isBurning(MetalsNBTData.STEEL)) {
                        capability.drainMetals(MetalsNBTData.DURALUMIN, MetalsNBTData.STEEL);
                    }
                    ModNetwork.sync(capability, player);

                }
            });
            // Sanity check to make sure  the block is loaded in the server
            if (player.level.isLoaded(pos)) {
                IronAndSteelHelpers.move(this.direction, player, pos);
            }
        });
        ctx.get().setPacketHandled(true);
    }


}
