package net.rudahee.metallics_arts.setup.network.packets;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;
import net.rudahee.metallics_arts.modules.logic.server.powers.IronAndSteelHelpers;

import java.util.UUID;
import java.util.function.Supplier;

public class BannerPacket {

    private final UUID uuid;
    private final int slot;

    /**
     *
     * @param slot   slot for remove
     * @param player the player
     */
    public BannerPacket(int slot, Player player) {
        this.uuid = player.getUUID();
        this.slot = slot;
    }

    private BannerPacket(UUID uuid, int slot) {
        this.slot = slot;
        this.uuid = uuid;
    }

    public static BannerPacket decode(FriendlyByteBuf buf) {
        return new BannerPacket(buf.readUUID(), buf.readInt());
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeInt(this.slot);
        buf.writeUUID(this.uuid);
    }

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
