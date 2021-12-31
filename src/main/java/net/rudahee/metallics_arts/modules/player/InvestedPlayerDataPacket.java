package net.rudahee.metallics_arts.modules.player;


import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.UUID;
import java.util.function.Supplier;

public class InvestedPlayerDataPacket {

    private final CompoundNBT nbt;
    private final UUID uuid;

    /**
     * Packet for sending Allomancy player data to a client
     *
     * @param data   the AllomancerCapability data for the player
     * @param player the player
     */
    public InvestedPlayerDataPacket(IInvestedPlayerData data, PlayerEntity player) {
        this.uuid = player.getUUID();
        this.nbt = (data != null && InvestedPlayerCapability.PLAYER_CAP != null) ? (CompoundNBT) InvestedPlayerCapability.PLAYER_CAP.writeNBT(data, null) : new CompoundNBT();

    }

    private InvestedPlayerDataPacket(CompoundNBT nbt, UUID uuid) {
        this.nbt = nbt;
        this.uuid = uuid;
    }

    public static InvestedPlayerDataPacket decode(PacketBuffer buf) {
        return new InvestedPlayerDataPacket(buf.readNbt(), buf.readUUID());
    }

    public void encode(PacketBuffer buf) {
        buf.writeNbt(this.nbt);
        buf.writeUUID(this.uuid);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            PlayerEntity player = Minecraft.getInstance().level.getPlayerByUUID(this.uuid);

            if (player != null && InvestedPlayerCapability.PLAYER_CAP != null) {
                player.getCapability(InvestedPlayerCapability.PLAYER_CAP).ifPresent(cap -> InvestedPlayerCapability.PLAYER_CAP.readNBT(cap, null, this.nbt));
            }
        });
        ctx.get().setPacketHandled(true);
    }
}