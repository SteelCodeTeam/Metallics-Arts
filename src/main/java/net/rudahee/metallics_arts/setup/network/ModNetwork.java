package net.rudahee.metallics_arts.setup.network;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.data.network.*;
import net.rudahee.metallics_arts.modules.data_player.IDefaultInvestedPlayerData;
import net.rudahee.metallics_arts.modules.data_player.InvestedCapability;

public class ModNetwork {
    private static final String VERSION = "1.1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(new ResourceLocation(MetallicsArts.MOD_ID, "networking"), () -> VERSION, VERSION::equals,
            VERSION::equals);

    private static int index = 0;

    private static int nextIndex() {
        return index++;
    }

    public static void registerPackets() {
        INSTANCE.registerMessage(nextIndex(), InvestedDataPacket.class, InvestedDataPacket::encode, InvestedDataPacket::decode, InvestedDataPacket::handle);
        INSTANCE.registerMessage(nextIndex(), UpdateBurnPacket.class, UpdateBurnPacket::encode, UpdateBurnPacket::decode, UpdateBurnPacket::handle);
        INSTANCE.registerMessage(nextIndex(), ChangeEmotionPacket.class, ChangeEmotionPacket::encode, ChangeEmotionPacket::decode, ChangeEmotionPacket::handle);
        INSTANCE.registerMessage(nextIndex(), PullAndPushBlockPacket.class, PullAndPushBlockPacket::encode, PullAndPushBlockPacket::decode, PullAndPushBlockPacket::handle);
        INSTANCE.registerMessage(nextIndex(), PullAndPushEntityPacket.class, PullAndPushEntityPacket::encode, PullAndPushEntityPacket::decode, PullAndPushEntityPacket::handle);
        INSTANCE.registerMessage(nextIndex(), UpdateDecantPacket.class, UpdateDecantPacket::encode, UpdateDecantPacket::decode, UpdateDecantPacket::handle);
        INSTANCE.registerMessage(nextIndex(), UpdateStoragePacket.class, UpdateStoragePacket::encode, UpdateStoragePacket::decode, UpdateStoragePacket::handle);
    }

    public static void sendToServer(Object msg) {
        INSTANCE.sendToServer(msg);
    }

    public static void sendTo(Object msg, ServerPlayerEntity player) {
        if (!(player instanceof FakePlayer)) {
            INSTANCE.sendTo(msg, player.connection.getConnection(), NetworkDirection.PLAY_TO_CLIENT);
        }
    }

    public static void sendTo(Object msg, PacketDistributor.PacketTarget target) {
        INSTANCE.send(target, msg);
    }

    public static void sync(PlayerEntity player) {
        player.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(data -> sync(data, player));
    }

    public static void sync(IDefaultInvestedPlayerData cap, PlayerEntity player) {
        sync(new InvestedDataPacket(cap, player), player);
    }

    public static void sync(Object msg, PlayerEntity player) {
        sendTo(msg, PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> player));
    }

}
