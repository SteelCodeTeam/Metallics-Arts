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

public class ReloadGunPacket {

    public ReloadGunPacket() {

    }

    public static ReloadGunPacket decode(FriendlyByteBuf buf) {
        return new ReloadGunPacket();
    }


    public void encode(FriendlyByteBuf buf) {

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() ->{

            ServerPlayer player = context.getSender();

            if (player.getItemInHand(InteractionHand.MAIN_HAND).getItem() instanceof BasicGun instance) {
                ItemStack itemStack = player.getItemInHand(InteractionHand.MAIN_HAND);
                if (!itemStack.hasTag()) {
                    itemStack.setTag(GunUtils.generateGunTags(instance.getGunType()));
                }
                if (itemStack.getTag().getString(GunsAccess.STATE.getKey()).equals(GunsAccess.READY.getKey())) {
                    itemStack.getTag().putString(GunsAccess.STATE.getKey(), GunsAccess.RELOAD.getKey());
                } else {
                    itemStack.getTag().putString(GunsAccess.STATE.getKey(), GunsAccess.READY.getKey());
                }
            }
        });
        return true;
    }
}
