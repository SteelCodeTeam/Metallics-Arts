package net.rudahee.metallics_arts.setup.network.packets;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkEvent;

import java.util.UUID;
import java.util.function.Supplier;

public class BossPullEntityPacket {

    private final UUID inqusitorId;
    private final UUID playerId;
    private final int direction;
    private final int phase;

    public BossPullEntityPacket(UUID inqusitorId, UUID playerId, int direction, int phase) {
        this.inqusitorId = inqusitorId;
        this.playerId = playerId;
        this.direction = direction;
        this.phase = phase;

    }

    /**
     * Static method to decode data from buffer.
     *
     * @param buf buffer to be decoded.
     *
     * @return PullAndPushEntityPacket
     */
    public static BossPullEntityPacket decode(FriendlyByteBuf buf) {
        return new BossPullEntityPacket(buf.readUUID(), buf.readUUID(), buf.readInt(), buf.readInt());
    }

    /**
     * Static method to encode data to buffer.
     *
     * @param buf buffer to be decoded.
     */
    public void encode(FriendlyByteBuf buf) {
        buf.writeUUID(this.inqusitorId);
        buf.writeUUID(this.playerId);
        buf.writeInt(this.direction);
        buf.writeInt(this.phase);
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

                if (phase == 0) {
                    Vec3 motion = player.position().subtract(new Vec3(inquisitor.getX(), inquisitor.getY(), inquisitor.getZ()))
                            .scale(direction);

                    player.setDeltaMovement(motion);
                    player.hurtMarked = true;
                } else if (phase == 1) {

                    Vec3 motion = player.position().subtract(new Vec3(inquisitor.getX(), inquisitor.getY() + 4, inquisitor.getZ()))
                            .scale(direction * 1.3);

                    player.setOnGround(false);
                    player.hurt(player.damageSources().fall(), 5F);
                    player.setDeltaMovement(motion);
                    player.hurtMarked = true;
                }

            }

        });
        ctx.get().setPacketHandled(true);

    }

}
