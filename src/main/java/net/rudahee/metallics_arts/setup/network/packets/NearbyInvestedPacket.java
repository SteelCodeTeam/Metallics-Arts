package net.rudahee.metallics_arts.setup.network.packets;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;
import net.rudahee.metallics_arts.utils.FoundNearbyMetalUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;

public class NearbyInvestedPacket {

    private final UUID uuid;
    private final int qty;
    private final List<BlockPos> pos;

    public NearbyInvestedPacket(Player player, int qty, List<BlockPos> pos) {
        this.uuid = player.getUUID();
        this.qty = qty;
        this.pos = pos;
    }

    /**
     * Default constructor that receive all mandatory data.
     *
     * @param uuid of the player to synchronize pos.
     * @param pos to synchronize.
     */
    private NearbyInvestedPacket(UUID uuid, int qty, List<BlockPos> pos) {
        this.uuid = uuid;
        this.qty = qty;
        this.pos = pos;
    }

    /**
     * Static method to decode data from buffer.
     *
     * @param buf buffer to be decoded.
     *
     * @return RespawnPositionPacket
     */
    public static NearbyInvestedPacket decode(FriendlyByteBuf buf) {
        UUID uuid = buf.readUUID();
        int qty = buf.readInt();
        ArrayList<BlockPos> posList = new ArrayList<>();

        for (int i=0; i<qty; i++) {
            posList.add(buf.readBlockPos());
        }

        return new NearbyInvestedPacket(uuid, qty, posList);
    }

    /**
     * Static method to encode data to buffer.
     *
     * @param buf buffer to be decoded.
     */
    public void encode(FriendlyByteBuf buf) {
        buf.writeUUID(this.uuid);
        buf.writeInt(this.qty);
        for (int i=0; i<qty; i++) {
            buf.writeBlockPos(pos.get(i));
        }

    }

    /**
     * Method to handle and do anything when packet its received and decoded.
     *
     * @param ctx Network context with all data of the packet.
     */
    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();

            if (player != null) {
                if (qty != 0) {
                    FoundNearbyMetalUtils.setNearbyAllomancers(pos);
                }
            }

        });
        ctx.get().setPacketHandled(true);
    }

}
