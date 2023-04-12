package net.rudahee.metallics_arts.setup.network.packets;

import net.minecraft.core.GlobalPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;
import net.rudahee.metallics_arts.modules.logic.client.client_events.OnRenderLevelStage;

import java.util.UUID;
import java.util.function.Supplier;

public class AnotherPlayerDeathPosPacket {

    private final UUID uuid;
    private final GlobalPos pos;

    /**
     * Default constructor that receive all mandatory data.
     *
     * @param player to synchronize pos.
     * @param pos to synchronize.
     */
    public AnotherPlayerDeathPosPacket(Player player, GlobalPos pos) {
        this.uuid = player.getUUID();
        this.pos = pos;
    }

    /**
     * Default constructor that receive all mandatory data.
     *
     * @param uuid of the player to synchronize pos.
     * @param pos to synchronize.
     */
    private AnotherPlayerDeathPosPacket(UUID uuid, GlobalPos pos) {
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
    public static AnotherPlayerDeathPosPacket decode(FriendlyByteBuf buf) {
        return new AnotherPlayerDeathPosPacket(buf.readUUID(), buf.readGlobalPos());
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
            OnRenderLevelStage.setAnotherPlayerDeathPos(pos);
        });
        ctx.get().setPacketHandled(true);
    }
}
