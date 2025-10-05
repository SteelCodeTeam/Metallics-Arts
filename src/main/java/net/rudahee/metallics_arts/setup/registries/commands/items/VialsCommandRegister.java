package net.rudahee.metallics_arts.setup.registries.commands.items;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.rudahee.metallics_arts.modules.custom_items.vials.Vial;
import net.rudahee.metallics_arts.setup.registries.ModItemsRegister;

import java.util.Collection;
import java.util.List;

public class VialsCommandRegister {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {

        dispatcher.register(Commands.literal("ma-items").requires(commandSource -> commandSource.hasPermission(2))
                .then(Commands.literal("give")
                        .then(Commands.literal("vial")
                                .then(Commands.literal("large")
                                .executes(context -> getLargeVial(context, List.of(context.getSource().getPlayerOrException())))
                                .then(Commands.argument("target", EntityArgument.players())
                                        .executes(context -> getLargeVial(context, EntityArgument.getPlayers(context, "target")))))
                                .then(Commands.literal("small")
                                        .executes(context -> getSmallVial(context, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target", EntityArgument.players())
                                                .executes(context -> getSmallVial(context, EntityArgument.getPlayers(context, "target"))))))));

    }

    private static int getLargeVial(CommandContext<CommandSourceStack> context, Collection<ServerPlayer> targetsPlayer) {

        ItemStack vial = new ItemStack(ModItemsRegister.LARGE_VIAL.get());
        CompoundTag tag = Vial.addFullReserveVialTags();
        tag.putFloat("CustomModelData", 1);
        vial.setTag(tag);

        StringBuilder playersName = new StringBuilder();
        boolean firstLoop = true;

        for (ServerPlayer player: targetsPlayer) {
            player.getInventory().add(vial);
            if (firstLoop) {
                firstLoop = false;
                playersName.append(player.getScoreboardName());
            } else {
                playersName.append(", ").append(player.getScoreboardName());
            }
        }

         context.getSource().sendSystemMessage(Component.translatable("Added 1 Vial with all metals to: " + playersName));

        return  1;
    }

    private static int getSmallVial(CommandContext<CommandSourceStack> context, Collection<ServerPlayer> targetsPlayer) {

        ItemStack vial = new ItemStack(ModItemsRegister.SMALL_VIAL.get());
        CompoundTag tag = Vial.addFullReserveVialTags();
        tag.putFloat("CustomModelData", 1);
        vial.setTag(tag);

        StringBuilder playersName = new StringBuilder();
        boolean firstLoop = true;

        for (ServerPlayer player: targetsPlayer) {
            player.getInventory().add(vial);
            if (firstLoop) {
                firstLoop = false;
                playersName.append(player.getScoreboardName());
            } else {
                playersName.append(", ").append(player.getScoreboardName());
            }
        }

        context.getSource().sendSystemMessage(Component.translatable("Added 1 Vial with all metals to: " + playersName));

        return  1;
    }
}
