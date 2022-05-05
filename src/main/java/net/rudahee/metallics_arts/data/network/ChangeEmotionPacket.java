package net.rudahee.metallics_arts.data.network;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;
import net.rudahee.metallics_arts.modules.data_player.InvestedCapability;
import net.rudahee.metallics_arts.modules.powers.helpers.ZincAndBrassHelpers;

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

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            PlayerEntity allomancer = ctx.get().getSender();
            CreatureEntity target = (CreatureEntity) allomancer.level.getEntity(this.entityID);
            if (target == null) {
                return;
            }
            boolean enhanced = allomancer.getCapability(InvestedCapability.PLAYER_CAP).isPresent();
            if (this.make_aggressive) {
                ZincAndBrassHelpers.angryEntities(target, allomancer);
            } else {
                ZincAndBrassHelpers.happyEntities(target, allomancer);
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
