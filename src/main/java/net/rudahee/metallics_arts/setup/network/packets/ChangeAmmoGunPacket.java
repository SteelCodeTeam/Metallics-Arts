package net.rudahee.metallics_arts.setup.network.packets;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;
import net.rudahee.metallics_arts.data.enums.implementations.BulletType;
import net.rudahee.metallics_arts.data.enums.implementations.GunsAccess;

import java.util.function.Supplier;

public class ChangeAmmoGunPacket {
    public ChangeAmmoGunPacket() {

    }

    public static ChangeAmmoGunPacket decode(FriendlyByteBuf buf) {
        return new ChangeAmmoGunPacket();
    }


    public void encode(FriendlyByteBuf buf) {

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {

            ServerPlayer player = context.getSender();
            ItemStack itemStack = player.getItemInHand(InteractionHand.MAIN_HAND);
            CompoundTag tag = itemStack.getTag();

            if (itemStack.getTag().getInt(GunsAccess.BULLETS.getKey()) == 0) {
                if (tag.getString(GunsAccess.BULLET_TYPE.getKey()).equals(BulletType.LEAD.getType())) {
                    tag.putString(GunsAccess.BULLET_TYPE.getKey(), BulletType.ALUMINUM.getType());
                } else {
                    tag.putString(GunsAccess.BULLET_TYPE.getKey(), BulletType.LEAD.getType());
                }
                player.displayClientMessage(Component.literal(tag.getString(GunsAccess.BULLET_TYPE.getKey())),true);
            } else {
                player.displayClientMessage(Component.literal("No podes cambiar el tipo manito, vacia el cargador"),true);
            }
            itemStack.setTag(tag);
        });

        return true;
    }
}
