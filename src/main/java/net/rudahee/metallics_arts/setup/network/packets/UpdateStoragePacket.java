package net.rudahee.metallics_arts.setup.network.packets;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.setup.network.ModNetwork;
import net.rudahee.metallics_arts.setup.registries.InvestedPlayerCapabilityRegister;

import java.util.function.Supplier;

/**
 * Class to communicate data between Client game and Server game. This packet it's for activate the feruchemic storage
 * and synchronize the burn between client and server.
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
public class UpdateStoragePacket {
    private final MetalTagEnum metal;
    private final boolean value;

    /**
     * Default constructor that receive all mandatory data.
     *
     * @param metal to synchronize.
     * @param value can be true or false.
     */
    public UpdateStoragePacket(MetalTagEnum metal, boolean value) {
        this.metal = metal;
        this.value = value;
    }

    /**
     * Static method to decode data from buffer.
     *
     * @param buffer buffer to be decoded.
     *
     * @return UpdateBurnPacket
     */
    public static UpdateStoragePacket decode(FriendlyByteBuf buffer) {
        return new UpdateStoragePacket(buffer.readEnum(MetalTagEnum.class), buffer.readBoolean());
    }

    /**
     * Static method to encode data to buffer.
     *
     * @param buffer buffer to be decoded.
     */
    public void encode(FriendlyByteBuf buffer) {
        buffer.writeEnum(this.metal);
        buffer.writeBoolean(this.value);
    }

    /**
     * Method to handle and do anything when packet its received and decoded.
     *
     * @param context Network context with all data of the packet.
     */
    public void handle(Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork( () -> {
            ServerPlayer player = context.get().getSender();

            player.getCapability(InvestedPlayerCapabilityRegister.PLAYER_CAP).ifPresent(cap -> {

                if (cap.hasFeruchemicPower(this.metal)) {
                    cap.setStoring(this.metal, this.value);
                } else {
                    cap.setStoring(this.metal, false);
                }
                ModNetwork.syncInvestedDataPacket(cap, player);
            });
        });

        context.get().setPacketHandled(true);
    }
}
