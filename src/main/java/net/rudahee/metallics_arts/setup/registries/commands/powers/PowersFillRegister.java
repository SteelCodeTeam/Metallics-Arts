package net.rudahee.metallics_arts.setup.registries.commands.powers;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.setup.network.ModNetwork;
import net.rudahee.metallics_arts.setup.registries.InvestedPlayerCapabilityRegister;

import java.util.Collection;
import java.util.List;

public class PowersFillRegister {


    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {

        dispatcher.register(Commands.literal("ma-powers").requires(commandSource -> commandSource.hasPermission(2))
                .then(Commands.literal("fill")
                        .then(Commands.literal("all")
                                .executes(context -> fillAllPower(context, List.of(context.getSource().getPlayerOrException())))
                                .then(Commands.argument("target", EntityArgument.players())
                                        .executes(context -> fillAllPower(context, EntityArgument.getPlayers(context, "target")))))
                        .then(Commands.literal("allomantic")
                                .then(Commands.literal("steel")
                                        .executes(context -> fillPowerReserve(context, MetalTagEnum.STEEL, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> fillPowerReserve(context, MetalTagEnum.STEEL, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("iron")
                                        .executes(context -> fillPowerReserve(context, MetalTagEnum.IRON, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> fillPowerReserve(context, MetalTagEnum.IRON, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("tin")
                                        .executes(context -> fillPowerReserve(context, MetalTagEnum.TIN, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> fillPowerReserve(context, MetalTagEnum.TIN, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("pewter")
                                        .executes(context -> fillPowerReserve(context, MetalTagEnum.PEWTER, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> fillPowerReserve(context, MetalTagEnum.PEWTER, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("copper")
                                        .executes(context -> fillPowerReserve(context, MetalTagEnum.COPPER, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> fillPowerReserve(context, MetalTagEnum.COPPER, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("bronze")
                                        .executes(context -> fillPowerReserve(context, MetalTagEnum.BRONZE, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> fillPowerReserve(context, MetalTagEnum.BRONZE, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("zinc")
                                        .executes(context -> fillPowerReserve(context, MetalTagEnum.ZINC, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> fillPowerReserve(context, MetalTagEnum.ZINC, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("brass")
                                        .executes(context -> fillPowerReserve(context, MetalTagEnum.BRASS, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> fillPowerReserve(context, MetalTagEnum.BRASS, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("chromium")
                                        .executes(context -> fillPowerReserve(context, MetalTagEnum.CHROMIUM, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> fillPowerReserve(context, MetalTagEnum.CHROMIUM, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("nicrosil")
                                        .executes(context -> fillPowerReserve(context, MetalTagEnum.NICROSIL, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> fillPowerReserve(context, MetalTagEnum.NICROSIL, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("aluminum")
                                        .executes(context -> fillPowerReserve(context, MetalTagEnum.ALUMINUM, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> fillPowerReserve(context, MetalTagEnum.ALUMINUM, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("duralumin")
                                        .executes(context -> fillPowerReserve(context, MetalTagEnum.DURALUMIN, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> fillPowerReserve(context, MetalTagEnum.DURALUMIN, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("cadmium")
                                        .executes(context -> fillPowerReserve(context, MetalTagEnum.CADMIUM, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> fillPowerReserve(context, MetalTagEnum.CADMIUM, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("bendalloy")
                                        .executes(context -> fillPowerReserve(context, MetalTagEnum.BENDALLOY, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> fillPowerReserve(context, MetalTagEnum.BENDALLOY, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("electrum")
                                        .executes(context -> fillPowerReserve(context, MetalTagEnum.ELECTRUM, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> fillPowerReserve(context, MetalTagEnum.ELECTRUM, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("gold")
                                        .executes(context -> fillPowerReserve(context, MetalTagEnum.GOLD, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> fillPowerReserve(context, MetalTagEnum.GOLD, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("atium")
                                        .executes(context -> fillPowerReserve(context, MetalTagEnum.ATIUM, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> fillPowerReserve(context, MetalTagEnum.ATIUM, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("malatium")
                                        .executes(context -> fillPowerReserve(context, MetalTagEnum.MALATIUM, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> fillPowerReserve(context, MetalTagEnum.MALATIUM, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("lerasium")
                                        .executes(context -> fillPowerReserve(context, MetalTagEnum.LERASIUM, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> fillPowerReserve(context, MetalTagEnum.LERASIUM, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("ettmetal")
                                        .executes(context -> fillPowerReserve(context, MetalTagEnum.ETTMETAL, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> fillPowerReserve(context, MetalTagEnum.ETTMETAL, EntityArgument.getPlayers(context, "target"))))
                                )
                        )));
    }


    public static int fillAllPower(CommandContext<CommandSourceStack> context, Collection<ServerPlayer> players) {

        for (ServerPlayer player: players) {
            player.getCapability(InvestedPlayerCapabilityRegister.PLAYER_CAP).ifPresent(p ->{
                for (MetalTagEnum metal : p.getAllomanticPowers()) {
                    p.setAllomanticMetalsAmount(metal, metal.getMaxAllomanticTicksStorage());
                }

                player.sendSystemMessage(
                        Component.translatable("commands.metallics_arts.all_power_fill")
                                .append(Component.literal(player.getScoreboardName()).withStyle(ChatFormatting.GOLD)));
            });
            ModNetwork.syncInvestedDataPacket(player);
        }

        return 1;
    }

    public static int fillPowerReserve(CommandContext<CommandSourceStack> context, MetalTagEnum metalTagEnum, Collection<ServerPlayer> players) {

        for (ServerPlayer player: players) {
            player.getCapability(InvestedPlayerCapabilityRegister.PLAYER_CAP).ifPresent(p -> {
                p.setAllomanticMetalsAmount(metalTagEnum, metalTagEnum.getMaxAllomanticTicksStorage());
            });
            ModNetwork.syncInvestedDataPacket(player);

            player.sendSystemMessage(Component.literal(
                            Component.translatable("commands.metallics_arts.one_power_fill")
                                    .getString()
                                    .formatted(metalTagEnum.getNameLower()))
                    .append(Component.literal(player.getScoreboardName())
                            .withStyle(ChatFormatting.GOLD)));

        }

        return 1;
    }
}
