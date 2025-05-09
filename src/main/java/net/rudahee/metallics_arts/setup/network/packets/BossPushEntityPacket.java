package net.rudahee.metallics_arts.setup.network.packets;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkEvent;

import java.util.UUID;
import java.util.function.Supplier;

public class BossPushEntityPacket {

    private final UUID inqusitorId;
    private final UUID playerId;

    public BossPushEntityPacket(UUID inqusitorId, UUID playerId) {
        this.inqusitorId = inqusitorId;
        this.playerId = playerId;
    }

    /**
     * Static method to decode data from buffer.
     *
     * @param buf buffer to be decoded.
     *
     * @return PullAndPushEntityPacket
     */
    public static BossPushEntityPacket decode(FriendlyByteBuf buf) {
        return new BossPushEntityPacket(buf.readUUID(), buf.readUUID());
    }

    /**
     * Static method to encode data to buffer.
     *
     * @param buf buffer to be decoded.
     */
    public void encode(FriendlyByteBuf buf) {
        buf.writeUUID(this.inqusitorId);
        buf.writeUUID(this.playerId);
    }

    /**
     * Method to handle and do anything when packet its received and decoded.
     *
     * @param ctx Network context with all data of the packet.
     */
    public void handle(Supplier<NetworkEvent.Context> ctx) {


        ctx.get().enqueueWork(() -> {

            ServerLevel serverLevel = ctx.get().getSender().getLevel();

            if (serverLevel != null && inqusitorId != null && playerId != null) {

                Entity inquisitor = serverLevel.getEntity(inqusitorId);
                Entity player = serverLevel.getEntity(playerId);

                if (player.isPassenger()) {
                    player = player.getVehicle();
                }

                Vec3 motion = player.position().subtract(new Vec3(inquisitor.getX(), inquisitor.getY() - 0.5f, inquisitor.getZ()))
                        .normalize()
                        .scale(4.5);


                player.setOnGround(false);
                player.hurt(player.damageSources().fall(), 4F);
                player.setDeltaMovement(motion);
                player.hurtMarked = true;
            }

        });
        ctx.get().setPacketHandled(true);

    }

}
