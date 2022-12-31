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
import net.minecraftforge.network.NetworkEvent;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.players.IInvestedPlayerData;
import net.rudahee.metallics_arts.modules.logic.server.powers.IronAndSteelHelpers;
import net.rudahee.metallics_arts.setup.registries.ModBlocksRegister;

import java.util.function.Supplier;

public class PullAndPushEntityPacket {
    private final int entityIDOther;
    private final int direction;

    /**
     * Send a request to the server to use iron or steel on an entity
     *
     * @param entityIDOther the entity you are requesting the data of
     * @param direction     the direction (1 for push, -1 for pull)
     */
    public PullAndPushEntityPacket(int entityIDOther, int direction) {
        this.entityIDOther = entityIDOther;
        this.direction = direction;

    }

    public static PullAndPushEntityPacket decode(FriendlyByteBuf buf) {
        return new PullAndPushEntityPacket(buf.readInt(), buf.readInt());
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeInt(this.entityIDOther);
        buf.writeInt(this.direction);
    }

    private int sourceFeruchemicIron;
    private int targetFeruchemicIron;

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer source = ctx.get().getSender();
            Entity target = source.level.getEntity(this.entityIDOther);
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
                    if (target instanceof Player || target instanceof ServerPlayer) {
                        IInvestedPlayerData sourceCapability = source.getCapability(ModBlocksRegister.InvestedCapabilityRegister.PLAYER_CAP).resolve().get();
                        IInvestedPlayerData targetCapability = target.getCapability(ModBlocksRegister.InvestedCapabilityRegister.PLAYER_CAP).resolve().get();
                        boolean areInSameState = false;

                        // if both are decanting or both storing are in same state
                        if ((sourceCapability.isDecanting(MetalTagEnum.IRON) && targetCapability.isDecanting(MetalTagEnum.IRON))
                                || (sourceCapability.isStoring(MetalTagEnum.IRON) && targetCapability.isStoring(MetalTagEnum.IRON))) {
                            areInSameState = true;

                            // otherwise, if both are NOT decanting, o both are not storing, both its doing nothing, also = same state.
                        } else if ((!sourceCapability.isDecanting(MetalTagEnum.IRON) && !targetCapability.isDecanting(MetalTagEnum.IRON))
                                || (!sourceCapability.isStoring(MetalTagEnum.IRON) && !targetCapability.isStoring(MetalTagEnum.IRON))) {
                            areInSameState = true;
                        }

                        // by default, they are not in same state.

                        // Normal move in same state.
                        if (areInSameState) {
                            IronAndSteelHelpers.move(this.direction / 2.0, target, source.blockPosition());
                            IronAndSteelHelpers.move(this.direction / 2.0, source, target.blockPosition());
                        } else {
                            if (sourceCapability.isDecanting(MetalTagEnum.IRON)) { // is decanting, so more weight
                                // is storing, so less weight
                                // Not storing and decanting.
                                IronAndSteelHelpers.move(this.direction, target, target.blockPosition(), targetCapability.isStoring(MetalTagEnum.IRON));
                            } else if (sourceCapability.isStoring(MetalTagEnum.IRON)) {
                                // Not storing and decanting.
                                IronAndSteelHelpers.move(this.direction, source, source.blockPosition(), targetCapability.isDecanting(MetalTagEnum.IRON));
                            } else if (!sourceCapability.isStoring(MetalTagEnum.IRON) && !sourceCapability.isDecanting(MetalTagEnum.IRON)) {
                                if (targetCapability.isDecanting(MetalTagEnum.IRON)) {
                                    IronAndSteelHelpers.move(this.direction, source, source.blockPosition(), true);
                                } else if (targetCapability.isStoring(MetalTagEnum.IRON)) {
                                    IronAndSteelHelpers.move(this.direction, target, target.blockPosition(), false);
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
