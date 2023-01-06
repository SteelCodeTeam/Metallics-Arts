package net.rudahee.metallics_arts.setup.network.packets;


import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;
import net.rudahee.metallics_arts.modules.logic.server.powers.allomancy.ZincAllomanticHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.allomancy.BrassAllomanticHelper;
import net.rudahee.metallics_arts.setup.registries.ModBlocksRegister;

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

    public static ChangeEmotionPacket decode(FriendlyByteBuf buf) {
        return new ChangeEmotionPacket(buf.readInt(), buf.readBoolean());
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeInt(this.entityID);
        buf.writeBoolean(this.make_aggressive);
    }

    private static boolean enhanced;

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

            if (this.make_aggressive) {
                if (enhanced) {
                    BrassAllomanticHelper.angryEntitiesEnhanced(target, allomancer);
                } else {
                    BrassAllomanticHelper.angryEntities(target, allomancer);
                }
            } else {
                if (enhanced) {
                    ZincAllomanticHelper.happyEntitiesEnhanced(target, allomancer);
                } else {
                    ZincAllomanticHelper.happyEntities(target, allomancer);
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
