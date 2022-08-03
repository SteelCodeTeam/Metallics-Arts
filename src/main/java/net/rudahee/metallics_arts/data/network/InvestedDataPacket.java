package net.rudahee.metallics_arts.data.network;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;
import net.rudahee.metallics_arts.modules.data_player.IDefaultInvestedPlayerData;
import net.rudahee.metallics_arts.modules.data_player.InvestedCapability;

import java.util.UUID;
import java.util.function.Supplier;

public class InvestedDataPacket {

    private final CompoundNBT nbt;
    private final UUID uuid;

    /**
     * Packet for sending Allomancy player data to a client
     *
     * @param data   the AllomancerCapability data for the player
     * @param player the player
     */
    public InvestedDataPacket(IDefaultInvestedPlayerData data, PlayerEntity player) {
        this.uuid = player.getUUID();
        this.nbt = (data != null && InvestedCapability.PLAYER_CAP != null) ? (CompoundNBT) InvestedCapability.PLAYER_CAP.writeNBT(data, null) : new CompoundNBT();

    }

    private InvestedDataPacket(CompoundNBT nbt, UUID uuid) {
        this.nbt = nbt;
        this.uuid = uuid;
    }

    public static InvestedDataPacket decode(PacketBuffer buf) {
        return new InvestedDataPacket(buf.readNbt(), buf.readUUID());
    }

    public void encode(PacketBuffer buf) {
        buf.writeNbt(this.nbt);
        buf.writeUUID(this.uuid);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            PlayerEntity player = Minecraft.getInstance().level.getPlayerByUUID(this.uuid);

            if (player != null && InvestedCapability.PLAYER_CAP != null) {
                player.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(cap -> InvestedCapability.PLAYER_CAP.readNBT(cap, null, this.nbt));
            }
        });

        ctx.get().setPacketHandled(true);
    }
}
