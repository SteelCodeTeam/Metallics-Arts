package net.rudahee.metallics_arts.setup.network.packets;

import net.minecraft.core.GlobalPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;
import net.rudahee.metallics_arts.modules.logic.client.client_events.OnRenderLevelStage;

import java.util.UUID;
import java.util.function.Supplier;

public class RespawnPositionPacket {

    private UUID uuid;
    private GlobalPos pos;

    public RespawnPositionPacket(Player player, GlobalPos pos) {
        this.uuid = player.getUUID();
        this.pos = pos;
    }

    private RespawnPositionPacket(UUID uuid, GlobalPos pos) {
        this.uuid = uuid;
        this.pos = pos;
    }

    public static RespawnPositionPacket decode(FriendlyByteBuf buf) {
        return new RespawnPositionPacket(buf.readUUID(), buf.readGlobalPos());
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeUUID(this.uuid);
        buf.writeGlobalPos(this.pos);
    }

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
