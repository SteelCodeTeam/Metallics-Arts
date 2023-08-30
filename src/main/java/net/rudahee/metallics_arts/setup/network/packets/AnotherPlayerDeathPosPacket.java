package net.rudahee.metallics_arts.setup.network.packets;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.GlobalPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkEvent;
import net.rudahee.metallics_arts.modules.logic.client.client_events.OnRenderLevelStage;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.UUID;
import java.util.function.Supplier;

public class AnotherPlayerDeathPosPacket {

    private final UUID uuid;
    private GlobalPos pos;

    /**
     * Default constructor that receive all mandatory data.
     *
     * @param player to synchronize pos.
     * @param pos to synchronize.
     */
    public AnotherPlayerDeathPosPacket(Player player, @NonNull GlobalPos pos) {
        this.uuid = player.getUUID();
        this.pos = pos;
    }

    /**
     * Default constructor that receive all mandatory data.
     *
     * @param uuid of the player to synchronize pos.
     * @param pos to synchronize.
     */
    private AnotherPlayerDeathPosPacket(UUID uuid, @NonNull GlobalPos pos) {
        this.uuid = uuid;
        this.pos = pos;
    }

    /**
     * Static method to decode data from buffer.
     *
     * @param buf buffer to be decoded.
     *
     * @return RespawnPositionPacket
     */
    public static AnotherPlayerDeathPosPacket decode(FriendlyByteBuf buf) {
        UUID uuidLocal = buf.readUUID();
        GlobalPos posLocal = buf.readGlobalPos();
        if (posLocal == null) {
            posLocal = GlobalPos.of(Level.OVERWORLD, new BlockPos(Minecraft.getInstance().level.getLevelData().getXSpawn(), Minecraft.getInstance().level.getLevelData().getYSpawn(), Minecraft.getInstance().level.getLevelData().getZSpawn()));
        }
        return new AnotherPlayerDeathPosPacket(uuidLocal, posLocal);
    }

    /**
     * Static method to encode data to buffer.
     *
     * @param buf buffer to be decoded.
     */
    public void encode(FriendlyByteBuf buf) {
        buf.writeUUID(this.uuid);
        buf.writeGlobalPos(this.pos);
    }

    /**
     * Method to handle and do anything when packet its received and decoded.
     *
     * @param ctx Network context with all data of the packet.
     */
    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            OnRenderLevelStage.setAnotherPlayerDeathPos(this.pos);
        });
        ctx.get().setPacketHandled(true);
    }
}
