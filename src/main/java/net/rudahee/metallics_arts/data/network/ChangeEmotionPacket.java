package net.rudahee.metallics_arts.data.network;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;
import net.rudahee.metallics_arts.modules.data_player.InvestedCapability;
import net.rudahee.metallics_arts.modules.powers.helpers.ZincAndBrassHelpers;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;
import net.rudahee.metallics_arts.setup.network.ModNetwork;

import java.util.function.Supplier;

public class ChangeEmotionPacket {
    private final int entityID;
    private final boolean make_aggressive;


    /**
     * Make a mob either angry or passive, depending on aggro
     *
     * @param entityID        the mob to be effected
     * @param make_aggressive true if mob should be aggressive
     */
    public ChangeEmotionPacket(int entityID, boolean make_aggressive) {
        this.entityID = entityID;
        this.make_aggressive = make_aggressive;
    }

    public static ChangeEmotionPacket decode(PacketBuffer buf) {
        return new ChangeEmotionPacket(buf.readInt(), buf.readBoolean());
    }

    public void encode(PacketBuffer buf) {
        buf.writeInt(this.entityID);
        buf.writeBoolean(this.make_aggressive);
    }

    private static boolean enhanced;

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            PlayerEntity allomancer = ctx.get().getSender();
            CreatureEntity target = (CreatureEntity) allomancer.level.getEntity(this.entityID);
            if (target == null) {
                return;
            }
            boolean isEnhanced = false;

            allomancer.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(data -> {
                enhanced = data.isBurning(MetalsNBTData.DURALUMIN);
                if (enhanced) {
                    if (data.isBurning(MetalsNBTData.ZINC)) {
                        data.drainMetals(MetalsNBTData.DURALUMIN, MetalsNBTData.ZINC);
                    }
                    if (data.isBurning(MetalsNBTData.BRASS)) {
                        data.drainMetals(MetalsNBTData.DURALUMIN, MetalsNBTData.BRASS);
                    }
                    ModNetwork.sync(data, allomancer);

                }
            });

            if (this.make_aggressive) {
                if (isEnhanced) {
                    ZincAndBrassHelpers.angryEntitiesEnhanced(target, allomancer);

                } else {
                    ZincAndBrassHelpers.angryEntities(target, allomancer);
                }
            } else {
                if (isEnhanced) {
                    ZincAndBrassHelpers.happyEntitiesEnhanced(target, allomancer);
                } else {
                    ZincAndBrassHelpers.happyEntities(target, allomancer);
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
