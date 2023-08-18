package net.rudahee.metallics_arts.setup.network;


import net.minecraft.core.BlockPos;
import net.minecraft.core.GlobalPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.data.player.data.IInvestedPlayerData;
import net.rudahee.metallics_arts.setup.network.packets.*;
import net.rudahee.metallics_arts.setup.registries.ModBlocksRegister;

import java.util.List;

/**
 * Class to control communications between Client game and Server game. That it's done defining packets to realize change
 * in the other side. This class register that packets and contains methods to send that packets.
 *
 * @author SteelCode Team
 * @since 1.5.1
 *
 * @see SimpleChannel
 * @see NetworkRegistry
 * @see PacketDistributor
 * @see NetworkEvent.PacketDispatcher
 */
public class ModNetwork {

    private static final String VERSION = MetallicsArts.VERSION;


    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(new ResourceLocation(MetallicsArts.MOD_ID,
                    "network_tunnel"), () -> VERSION, VERSION::equals, VERSION::equals);

    private static int index = 0;

    /**
     * Generates a next int to continue the index.
     *
     * @return int
     */
    private static int nextIndex() {
        return index++;
    }

    /**
     * Method used by Forge to register all packets needed to send info between server and client.
     */
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
        INSTANCE.registerMessage(nextIndex(), NearbyInvestedPacket.class, NearbyInvestedPacket::encode, NearbyInvestedPacket::decode, NearbyInvestedPacket::handle);
        INSTANCE.registerMessage(nextIndex(), AnotherPlayerDeathPosPacket.class, AnotherPlayerDeathPosPacket::encode, AnotherPlayerDeathPosPacket::decode, AnotherPlayerDeathPosPacket::handle);
        INSTANCE.registerMessage(nextIndex(), LeverPacket.class, LeverPacket::encode, LeverPacket::decode, LeverPacket::handle);
        INSTANCE.registerMessage(nextIndex(), FiringGunPacket.class, FiringGunPacket::encode, FiringGunPacket::decode, FiringGunPacket::handle);


    }

    /**
     * Method used to send a message from client to server.
     *
     * @param msg can be any Object packet defined by Game or this class.
     */
    public static void sendToServer(Object msg) {
        INSTANCE.sendToServer(msg);
    }

    /**
     * Method used to send a message from server to specific client.
     *
     * @param msg can be any Object packet defined by Game or this class.
     * @param player Server player that started the communication.
     */
    public static void sendTo(Object msg, ServerPlayer player) {
        if (!(player instanceof FakePlayer)) {
            INSTANCE.sendTo(msg, player.connection.getConnection(), NetworkDirection.PLAY_TO_CLIENT);
        }
    }

    /**
     * Method used to send a message from server to specific client.
     *
     * @param msg can be any Object packet defined by Game or this class.
     * @param target specified target.
     */
    public static void sendTo(Object msg, PacketDistributor.PacketTarget target) {
        INSTANCE.send(target, msg);
    }

    /**
     * Specific method to synchronize data from server player to client player.
     *
     * @param player that need update her/his data from server.
     */
    public static void syncInvestedDataPacket(Player player) {
        player.getCapability(ModBlocksRegister.InvestedCapabilityRegister.PLAYER_CAP).ifPresent(data -> syncInvestedDataPacket(data, player));
    }

    /**
     * Specific method to synchronize data from server player to client player through specific packet.
     *
     * @param capability that be updated.
     * @param player that need update her/his data from server.
     */
    public static void syncInvestedDataPacket(IInvestedPlayerData capability, Player player) {
        sync(new InvestedDataPacket(capability, player), player);
    }

    /**
     * Specific method to synchronize respawn pos from server player to client player through specific packet.
     *
     * @param pos that retrieve to client.
     * @param player that need update her/his respawn pos from server.
     */
    public static void syncRespawnPosPacket(GlobalPos pos, Player player) {
        sync(new RespawnPositionPacket(player, pos), player);
    }

    public static void syncNearbyInvestedPlayer(List<BlockPos> pos, Player player) {
        sync(new NearbyInvestedPacket(player, pos.size(), pos), player);
    }

    public static void syncAnotherPlayerDeathPos(GlobalPos pos, Player player) {
        sync(new AnotherPlayerDeathPosPacket(player, pos), player);
    }


    /**
     * Specific method to synchronize any packet from server to client.
     *
     * @param msg Packet that retrieve to client.
     * @param player that need update her/his packet pos from server.
     */
    public static void sync(Object msg, Player player) {
        sendTo(msg, PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> player));
    }

}
