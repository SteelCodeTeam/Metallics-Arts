package net.rudahee.metallics_arts.setup.network.packets;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;
import net.rudahee.metallics_arts.data.enums.implementations.GunsAccess;
import net.rudahee.metallics_arts.modules.custom_items.weapons.guns.BasicGun;
import net.rudahee.metallics_arts.modules.custom_items.weapons.guns.GunUtils;

import java.util.function.Supplier;

public class FiringGunPacket {

    public FiringGunPacket() {

    }

    public static FiringGunPacket decode(FriendlyByteBuf buf) {
        return new FiringGunPacket();
    }


    public void encode(FriendlyByteBuf buf) {

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() ->{

            ServerPlayer player = context.getSender();
            ItemStack itemStack = player.getItemInHand(InteractionHand.MAIN_HAND);
            if (itemStack.getItem() instanceof BasicGun instance) {

                if (!GunUtils.hasTags(itemStack.getTag())) {
                    itemStack.setTag(GunUtils.generateGunTags(instance.getGunType()));
                }
                if (itemStack.getTag().getString(GunsAccess.STATE.getKey()).equals(GunsAccess.READY.getKey())) {
                    itemStack.setTag(GunUtils.shot(itemStack, player.level ,player, instance.getGunType()));
                }
            }
        });
        return true;
    }
}
