package net.rudahee.metallics_arts.setup.network.packets;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.rudahee.metallics_arts.modules.logic.server.powers.allomancy.physical_metals.IronAndSteelHelpers;
import net.rudahee.metallics_arts.setup.network.ModNetwork;

import java.util.UUID;
import java.util.function.Supplier;

/**
 * Class to communicate data between Client game and Server game. This packet it's for remove
 * a nugget to inventory when it's flying without valid block below.
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
public class RemoveNuggetPacket {

    private final UUID uuid;
    private final int slot;

    /**
     * Default constructor that receive all mandatory data.
     *
     * @param slot in inventory to remove item.
     * @param player to remove item.
     */
    public RemoveNuggetPacket(int slot, Player player) {
        this.uuid = player.getUUID();
        this.slot = slot;
    }

    /**
     * Default constructor that receive all mandatory data.
     *
     * @param uuid to remove item.
     * @param slot in inventory to remove item.
     */
    private RemoveNuggetPacket(UUID uuid, int slot) {
        this.slot = slot;
        this.uuid = uuid;
    }

    /**
     * Static method to decode data from buffer.
     *
     * @param buf buffer to be decoded.
     *
     * @return RemoveNuggetPacket
     */
    public static RemoveNuggetPacket decode(FriendlyByteBuf buf) {
        return new RemoveNuggetPacket(buf.readUUID(), buf.readInt());
    }

    /**
     * Static method to encode data to buffer.
     *
     * @param buf buffer to be decoded.
     */
    public void encode(FriendlyByteBuf buf) {
        buf.writeInt(this.slot);
        buf.writeUUID(this.uuid);
    }

    /**
     * Method to handle and do anything when packet its received and decoded.
     *
     * @param ctx Network context with all data of the packet.
     */
    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();

            if (player != null) {
                player.getInventory().removeItem(IronAndSteelHelpers.haveNuggets(player), 1);
            }
        });

        ctx.get().setPacketHandled(true);
    }
}
