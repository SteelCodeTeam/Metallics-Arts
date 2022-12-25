package net.rudahee.metallics_arts.setup.network.packets;


import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import net.rudahee.metallics_arts.modules.tags_player.InvestedCapability;
import net.rudahee.metallics_arts.data.enums.implementations.MetalsNBTData;
import net.rudahee.metallics_arts.setup.network.ModNetwork;

import java.util.function.Supplier;

public class UpdateBurnPacket {

    private final MetalsNBTData metal;
    private final boolean value;

    public UpdateBurnPacket(MetalsNBTData metal, boolean value) {
        this.metal = metal;
        this.value = value;
    }

    public static UpdateBurnPacket decode(FriendlyByteBuf buffer) {
        return new UpdateBurnPacket(buffer.readEnum(MetalsNBTData.class), buffer.readBoolean());
    }

    public void encode(FriendlyByteBuf buffer) {
        buffer.writeEnum(this.metal);
        buffer.writeBoolean(this.value);
    }

    public void handle(Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork( () -> {
            ServerPlayer player = context.get().getSender();

            player.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(cap -> {
                if (cap.hasAllomanticPower(this.metal) && cap.getAllomanticAmount(this.metal) > 0) {
                        cap.setBurning(this.metal, this.value);
                } else {
                    cap.setBurning(this.metal, false);
                }
                ModNetwork.sync(cap, player);
            });
        });

        context.get().setPacketHandled(true);
    }

}
