package net.rudahee.metallics_arts.data.network;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;
import net.rudahee.metallics_arts.modules.data_player.InvestedCapability;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;
import net.rudahee.metallics_arts.setup.network.ModNetwork;

import java.util.function.Supplier;

public class UpdateDecantPacket {
    private final MetalsNBTData metal;
    private final boolean value;

    public UpdateDecantPacket(MetalsNBTData metal, boolean value) {
        this.metal = metal;
        this.value = value;
    }

    public static UpdateDecantPacket decode(PacketBuffer buffer) {
        return new UpdateDecantPacket(buffer.readEnum(MetalsNBTData.class), buffer.readBoolean());
    }

    public void encode(PacketBuffer buffer) {
        buffer.writeEnum(this.metal);
        buffer.writeBoolean(this.value);
    }

    public void handle(Supplier<NetworkEvent.Context> context) {
        /*context.get().enqueueWork( () -> {
            ServerPlayerEntity player = context.get().getSender();

            player.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(cap -> {
                if (cap.hasFeruchemicPower(this.metal)) {
                    cap.setDecanting(this.metal, this.value);
                } else {
                    cap.setDecanting(this.metal, false);
                }
                ModNetwork.sync(cap, player);
            });
        });*/

        context.get().setPacketHandled(true);
    }
}
