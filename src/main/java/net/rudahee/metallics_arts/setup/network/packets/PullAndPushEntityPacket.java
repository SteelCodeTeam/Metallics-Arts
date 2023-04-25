package net.rudahee.metallics_arts.setup.network.packets;


import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.enums.implementations.custom_items.ArmorPiecesEnum;
import net.rudahee.metallics_arts.data.player.IInvestedPlayerData;
import net.rudahee.metallics_arts.modules.logic.server.powers.allomancy.physical_metals.IronAndSteelHelpers;
import net.rudahee.metallics_arts.setup.network.ModNetwork;
import net.rudahee.metallics_arts.setup.registries.ModBlocksRegister;
import net.rudahee.metallics_arts.setup.registries.ModItemsRegister;

import java.util.function.Supplier;
import java.util.stream.StreamSupport;

/**
 * Class to communicate data between Client game and Server game. This packet it's for send a request
 * to the server to use iron or steel on an entity
 * <p>
 * <b>Encode and decode must be symmetrical!</b>
 *
 * @author SteelCode Team
 * @since 1.5.1
 *
 * @see FriendlyByteBuf
 * @see NetworkRegistry
 * @see ModNetwork
 * @see NetworkEvent.Context
 */
public class PullAndPushEntityPacket {
    private final int entityIDOther;
    private final int direction;

    /**
     * Default constructor that receive all mandatory data.
     *
     * @param entityIDOther the entity you are requesting the data of
     * @param direction the direction (1 for push, -1 for pull)
     */
    public PullAndPushEntityPacket(int entityIDOther, int direction) {
        this.entityIDOther = entityIDOther;
        this.direction = direction;

    }

    /**
     * Static method to decode data from buffer.
     *
     * @param buf buffer to be decoded.
     *
     * @return PullAndPushEntityPacket
     */
    public static PullAndPushEntityPacket decode(FriendlyByteBuf buf) {
        return new PullAndPushEntityPacket(buf.readInt(), buf.readInt());
    }

    /**
     * Static method to encode data to buffer.
     *
     * @param buf buffer to be decoded.
     */
    public void encode(FriendlyByteBuf buf) {
        buf.writeInt(this.entityIDOther);
        buf.writeInt(this.direction);
    }

    /**
     * Method to handle and do anything when packet its received and decoded.
     *
     * @param ctx Network context with all data of the packet.
     */
    public void handle(Supplier<NetworkEvent.Context> ctx) {


        ctx.get().enqueueWork(() -> {
            ServerPlayer source = ctx.get().getSender();
            Entity target = source.level.getEntity(this.entityIDOther);

            boolean aluminumArmor = false;

            if (target != null) {

                if (IronAndSteelHelpers.isEntityMetal(target)) {
                    // The source moves
                    if (target instanceof IronGolem || target instanceof ItemFrame) {
                        IronAndSteelHelpers.move(this.direction, source, target.blockPosition());

                    } else if (target instanceof ItemEntity || target instanceof FallingBlockEntity || target instanceof ArmorStand ||
                            (target instanceof AbstractMinecart && !target.isVehicle())) {
                        IronAndSteelHelpers.move(this.direction / 2.0, target, source.blockPosition());

                        // Split the difference
                    }  //2 sources ?

                    // If entity are player.
                    if (target instanceof Player) {
                        aluminumArmor = StreamSupport.stream(target.getArmorSlots().spliterator(), false).allMatch(stack -> stack.getTag().toString().equals("aluminum"));


                        if (aluminumArmor) {
                            IInvestedPlayerData sourceCapability = source.getCapability(ModBlocksRegister.InvestedCapabilityRegister.PLAYER_CAP).resolve().get();
                            IInvestedPlayerData targetCapability = target.getCapability(ModBlocksRegister.InvestedCapabilityRegister.PLAYER_CAP).resolve().get();
                            boolean areInSameState = false;

                            // if both are decanting or both storing are in same state
                            if ((sourceCapability.isTapping(MetalTagEnum.IRON) && targetCapability.isTapping(MetalTagEnum.IRON))
                                    || (sourceCapability.isStoring(MetalTagEnum.IRON) && targetCapability.isStoring(MetalTagEnum.IRON))) {
                                areInSameState = true;

                                // otherwise, if both are NOT decanting, o both are not storing, both its doing nothing, also = same state.
                            } else if ((!sourceCapability.isTapping(MetalTagEnum.IRON) && !targetCapability.isTapping(MetalTagEnum.IRON))
                                    || (!sourceCapability.isStoring(MetalTagEnum.IRON) && !targetCapability.isStoring(MetalTagEnum.IRON))) {
                                areInSameState = true;
                            }

                            // by default, they are not in same state.

                            // Normal move in same state.
                            if (areInSameState) {
                                IronAndSteelHelpers.move(this.direction / 2.0, target, source.blockPosition());
                                IronAndSteelHelpers.move(this.direction / 2.0, source, target.blockPosition());
                            } else {
                                if (sourceCapability.isTapping(MetalTagEnum.IRON)) { // is decanting, so more weight
                                    // is storing, so less weight
                                    // Not storing and decanting.
                                    IronAndSteelHelpers.move(this.direction, target, target.blockPosition(), targetCapability.isStoring(MetalTagEnum.IRON));
                                } else if (sourceCapability.isStoring(MetalTagEnum.IRON)) {
                                    // Not storing and decanting.
                                    IronAndSteelHelpers.move(this.direction, source, source.blockPosition(), targetCapability.isTapping(MetalTagEnum.IRON));
                                } else if (!sourceCapability.isStoring(MetalTagEnum.IRON) && !sourceCapability.isTapping(MetalTagEnum.IRON)) {
                                    if (targetCapability.isTapping(MetalTagEnum.IRON)) {
                                        IronAndSteelHelpers.move(this.direction, source, source.blockPosition(), true);
                                    } else if (targetCapability.isStoring(MetalTagEnum.IRON)) {
                                        IronAndSteelHelpers.move(this.direction, target, target.blockPosition(), false);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        });
        ctx.get().setPacketHandled(true);

    }
}
