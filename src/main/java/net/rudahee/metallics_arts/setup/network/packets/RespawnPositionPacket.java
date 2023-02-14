package net.rudahee.metallics_arts.setup.network.packets;

import net.minecraft.core.GlobalPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.rudahee.metallics_arts.modules.logic.client.client_events.OnRenderLevelStage;
import net.rudahee.metallics_arts.setup.network.ModNetwork;

import java.util.UUID;
import java.util.function.Supplier;

/**
 * Class to communicate data between Client game and Server game. This packet it's for synchronize last death position
 * for the current player without synchronize all data.
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
public class RespawnPositionPacket {

    private final UUID uuid;
    private final GlobalPos pos;

    /**
     * Default constructor that receive all mandatory data.
     *
     * @param player to synchronize pos.
     * @param pos to synchronize.
     */
    public RespawnPositionPacket(Player player, GlobalPos pos) {
        this.uuid = player.getUUID();
        this.pos = pos;
    }

    /**
     * Default constructor that receive all mandatory data.
     *
     * @param uuid of the player to synchronize pos.
     * @param pos to synchronize.
     */
    private RespawnPositionPacket(UUID uuid, GlobalPos pos) {
        this.uuid = uuid;
        this.pos = pos;
    }

    /**
     * Static method to decode data from buffer.
     *
     * @param buf buffer to be decoded.
     *
     * @return RespawnPositionPacket
     */
    public static RespawnPositionPacket decode(FriendlyByteBuf buf) {
        return new RespawnPositionPacket(buf.readUUID(), buf.readGlobalPos());
    }

    /**
     * Static method to encode data to buffer.
     *
     * @param buf buffer to be decoded.
     */
    public void encode(FriendlyByteBuf buf) {
        buf.writeUUID(this.uuid);
        buf.writeGlobalPos(this.pos);
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
                if (player.getRespawnPosition() != null) {

                    GlobalPos pos = GlobalPos.of(player.getRespawnDimension(), player.getRespawnPosition());
                    OnRenderLevelStage.setRespawnPos(pos);

                }
            }

        });
        ctx.get().setPacketHandled(true);
    }

}
