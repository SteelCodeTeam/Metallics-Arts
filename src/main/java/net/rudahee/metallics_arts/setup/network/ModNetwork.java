package net.rudahee.metallics_arts.setup.network;


import net.minecraft.core.GlobalPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.data.player.IInvestedPlayerData;
import net.rudahee.metallics_arts.setup.network.packets.*;
import net.rudahee.metallics_arts.setup.registries.ModBlocksRegister;

public class ModNetwork {
    private static final String VERSION = "1.6";
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

        INSTANCE.registerMessage(nextIndex(), PullAndPushNuggetPacket.class, PullAndPushNuggetPacket::encode, PullAndPushNuggetPacket::decode, PullAndPushNuggetPacket::handle);

        INSTANCE.registerMessage(nextIndex(), PullAndPushEntityPacket.class, PullAndPushEntityPacket::encode, PullAndPushEntityPacket::decode, PullAndPushEntityPacket::handle);
        INSTANCE.registerMessage(nextIndex(), UpdateTapPacket.class, UpdateTapPacket::encode, UpdateTapPacket::decode, UpdateTapPacket::handle);
        INSTANCE.registerMessage(nextIndex(), UpdateStoragePacket.class, UpdateStoragePacket::encode, UpdateStoragePacket::decode, UpdateStoragePacket::handle);
        INSTANCE.registerMessage(nextIndex(), RemoveNuggetPacket.class, RemoveNuggetPacket::encode, RemoveNuggetPacket::decode, RemoveNuggetPacket::handle);
        INSTANCE.registerMessage(nextIndex(), RespawnPositionPacket.class, RespawnPositionPacket::encode, RespawnPositionPacket::decode, RespawnPositionPacket::handle);
    }

    public static void sendToServer(Object msg) {
        INSTANCE.sendToServer(msg);
    }

    public static void sendTo(Object msg, ServerPlayer player) {
        if (!(player instanceof FakePlayer)) {
            INSTANCE.sendTo(msg, player.connection.getConnection(), NetworkDirection.PLAY_TO_CLIENT);
        }
    }

    public static void sendTo(Object msg, PacketDistributor.PacketTarget target) {
        INSTANCE.send(target, msg);
    }

    public static void syncInvestedDataPacket(Player player) {
        player.getCapability(ModBlocksRegister.InvestedCapabilityRegister.PLAYER_CAP).ifPresent(data -> syncInvestedDataPacket(data, player));
    }

    public static void syncInvestedDataPacket(IInvestedPlayerData cap, Player player) {
        sync(new InvestedDataPacket(cap, player), player);
    }

    public static void syncRespawnPosPacket(GlobalPos pos, Player player) {
        sync(new RespawnPositionPacket(player, pos), player);
    }

    public static void sync(Object msg, Player player) {
        sendTo(msg, PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> player));
    }

}
