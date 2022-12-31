package net.rudahee.metallics_arts.setup.network.packets;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.setup.network.ModNetwork;
import net.rudahee.metallics_arts.setup.registries.ModBlocksRegister;

import java.util.function.Supplier;

public class UpdateStoragePacket {
    private final MetalTagEnum metal;
    private final boolean value;

    public UpdateStoragePacket(MetalTagEnum metal, boolean value) {
        this.metal = metal;
        this.value = value;
    }

    public static UpdateStoragePacket decode(FriendlyByteBuf buffer) {
        return new UpdateStoragePacket(buffer.readEnum(MetalTagEnum.class), buffer.readBoolean());
    }

    public void encode(FriendlyByteBuf buffer) {
        buffer.writeEnum(this.metal);
        buffer.writeBoolean(this.value);
    }

    public void handle(Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork( () -> {
            ServerPlayer player = context.get().getSender();

            player.getCapability(ModBlocksRegister.InvestedCapabilityRegister.PLAYER_CAP).ifPresent(cap -> {

                if (cap.hasFeruchemicPower(this.metal)) {
                    cap.setStoring(this.metal, this.value);
                } else {
                    cap.setStoring(this.metal, false);
                }
                ModNetwork.sync(cap, player);
            });
        });

        context.get().setPacketHandled(true);
    }
}
