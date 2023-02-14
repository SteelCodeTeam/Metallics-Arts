package net.rudahee.metallics_arts.setup.network.packets;


import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.rudahee.metallics_arts.modules.logic.server.powers.allomancy.mental_metals.BrassAllomanticHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.allomancy.mental_metals.ZincAllomanticHelper;
import net.rudahee.metallics_arts.setup.network.ModNetwork;
import net.rudahee.metallics_arts.setup.registries.ModBlocksRegister;

import java.util.function.Supplier;

/**
 * Class to communicate data between Client game and Server game. This packet its doing to make a mob either
 * angry or passive, depending on aggro.
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
public class ChangeEmotionPacket {

    private final int entityID;
    private final boolean makeAggressive;
    private static boolean enhanced;

    /**
     * Default constructor that receive all mandatory data.
     *
     * @param entityID the mob to be effected
     * @param makeAggressive true if mob should be aggressive
     */
    public ChangeEmotionPacket(int entityID, boolean makeAggressive) {
        this.entityID = entityID;
        this.makeAggressive = makeAggressive;
    }

    /**
     * Static method to decode data from buffer.
     *
     * @param buf buffer to be decoded.
     *
     * @return ChangeEmotionPacket
     */
    public static ChangeEmotionPacket decode(FriendlyByteBuf buf) {
        return new ChangeEmotionPacket(buf.readInt(), buf.readBoolean());
    }

    /**
     * Static method to encode data to buffer.
     *
     * @param buf buffer to be decoded.
     */
    public void encode(FriendlyByteBuf buf) {
        buf.writeInt(this.entityID);
        buf.writeBoolean(this.makeAggressive);
    }

    /**
     * Method to handle and do anything when packet its received and decoded.
     *
     * @param ctx Network context with all data of the packet.
     */
    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            Player allomancer = ctx.get().getSender();
            Mob target = (Mob) allomancer.level.getEntity(this.entityID);
            if (target == null) {
                return;
            }
            allomancer.getCapability(ModBlocksRegister.InvestedCapabilityRegister.PLAYER_CAP).ifPresent(data -> {
                enhanced = data.getEnhanced();
            });

            if (this.makeAggressive) {
                if (enhanced) {
                    ZincAllomanticHelper.angryEntitiesEnhanced(target, allomancer);
                } else {
                    ZincAllomanticHelper.angryEntities(target, allomancer);
                }
            } else {
                if (enhanced) {
                    BrassAllomanticHelper.happyEntitiesEnhanced(target, allomancer);
                } else {
                    BrassAllomanticHelper.happyEntities(target, allomancer);
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
