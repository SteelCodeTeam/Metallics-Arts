package net.rudahee.metallics_arts.setup.network.packets;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;
import net.rudahee.metallics_arts.data.enums.implementations.GunsAccess;
import net.rudahee.metallics_arts.modules.custom_items.weapons.guns.BasicGun;
import net.rudahee.metallics_arts.modules.custom_items.weapons.guns.GunUtils;

import java.util.function.Supplier;

public class ShotPacket {

    public ShotPacket() {

    }


    public static ShotPacket decode(FriendlyByteBuf buf) {
        return new ShotPacket();
    }


    public void encode(FriendlyByteBuf buf) {

    }

    public boolean handle (Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() ->{

            ServerPlayer player = context.getSender();

            if (player.getItemInHand(InteractionHand.MAIN_HAND).getItem() instanceof BasicGun instance) {
                ItemStack gun = player.getItemInHand(InteractionHand.MAIN_HAND);
                if (!gun.hasTag()) {
                    gun.setTag(GunUtils.generateGunTags(instance.getGunType()));
                }
                if (gun.getTag().getString(GunsAccess.STATE.getKey()).equals(GunsAccess.READY.getKey())) {
                    gun.setTag(GunUtils.shot(gun, player.level ,player, instance.getGunType()));
                }
            }
        });
        return true;
    }
}
