package net.rudahee.metallics_arts.setup.network.packets;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import net.rudahee.metallics_arts.modules.custom_items.redstone.AllomanticLever;
import net.rudahee.metallics_arts.modules.custom_items.redstone.AllomanticPullButton;
import net.rudahee.metallics_arts.modules.custom_items.redstone.AllomanticPushButton;
import net.rudahee.metallics_arts.modules.logic.server.powers.allomancy.physical_metals.IronAndSteelHelpers;

import java.util.function.Supplier;

public class RedstoneSignalsPacket {

    private final BlockPos blockPos;

    public RedstoneSignalsPacket(BlockPos blockPosition) {
        this.blockPos = blockPosition;
    }


    public static RedstoneSignalsPacket decode(FriendlyByteBuf buf) {
        return new RedstoneSignalsPacket(buf.readBlockPos());
    }


    public void encode(FriendlyByteBuf buf) {
        buf.writeBlockPos(this.blockPos);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();

            // Sanity check to make sure  the block is loaded in the server
            if (player.level.isLoaded(this.blockPos)) {
                // activate blocks

                if (IronAndSteelHelpers.isAllomanticLever(player.level.getBlockState(this.blockPos))) {
                    (((AllomanticLever) player.level.getBlockState(this.blockPos).getBlock())).pull(player.level.getBlockState(this.blockPos), player.level, this.blockPos);
                    //AllomanticLever lever = ((AllomanticLever) player.level.getBlockState(this.blockPos).getBlock());
                    //lever.pull(player.level.getBlockState(this.blockPos), player.level, this.blockPos);
                } else if (IronAndSteelHelpers.isAllomanticPushButton(player.level.getBlockState(this.blockPos))) {
                    ((AllomanticPushButton) player.level.getBlockState(this.blockPos).getBlock()).press(player.level.getBlockState(this.blockPos), player.level, this.blockPos);;
                } else if (IronAndSteelHelpers.isAllomanticPullButton(player.level.getBlockState(this.blockPos))) {
                    ((AllomanticPullButton) player.level.getBlockState(this.blockPos).getBlock()).press(player.level.getBlockState(this.blockPos), player.level, this.blockPos);
                }


            }
        });
        ctx.get().setPacketHandled(true);
    }

}
