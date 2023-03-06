package net.rudahee.metallics_arts.setup.network.packets;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.rudahee.metallics_arts.data.player.IInvestedPlayerData;
import net.rudahee.metallics_arts.setup.network.ModNetwork;
import net.rudahee.metallics_arts.setup.registries.ModBlocksRegister;

import java.util.UUID;
import java.util.function.Supplier;

/**
 * Class to communicate data between Client game and Server game. This packet it's for sending
 * Allomancy player data to a client
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
public class InvestedDataPacket {

    private final CompoundTag tag;
    private final UUID uuid;

    /**
     * Default constructor that receive all mandatory data.
     *
     * @param data to be synchronized.
     * @param player to save synchronized data.
     */
    public InvestedDataPacket(IInvestedPlayerData data, Player player) {
        this.uuid = player.getUUID();
        this.tag = (data != null && ModBlocksRegister.InvestedCapabilityRegister.PLAYER_CAP != null) ? data.save() : new CompoundTag();
    }

    /**
     * Default constructor that receive all mandatory data.
     *
     * @param tag it's data to be synchronized.
     * @param uuid of the player to save synchronized data.
     */
    private InvestedDataPacket(CompoundTag tag, UUID uuid) {
        this.tag = tag;
        this.uuid = uuid;
    }

    /**
     * Static method to decode data from buffer.
     *
     * @param buf buffer to be decoded.
     *
     * @return InvestedDataPacket
     */
    public static InvestedDataPacket decode(FriendlyByteBuf buf) {
        return new InvestedDataPacket(buf.readNbt(), buf.readUUID());
    }

    /**
     * Static method to encode data to buffer.
     *
     * @param buf buffer to be decoded.
     */
    public void encode(FriendlyByteBuf buf) {
        buf.writeNbt(this.tag);
        buf.writeUUID(this.uuid);
    }

    /**
     * Method to handle and do anything when packet its received and decoded.
     *
     * @param ctx Network context with all data of the packet.
     */
    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            Player player = Minecraft.getInstance().level.getPlayerByUUID(this.uuid);

            if (player != null && ModBlocksRegister.InvestedCapabilityRegister.PLAYER_CAP != null) {
                player.getCapability(ModBlocksRegister.InvestedCapabilityRegister.PLAYER_CAP).ifPresent(cap -> cap.load(this.tag));
            }
        });

        ctx.get().setPacketHandled(true);
    }
}
