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
import net.rudahee.metallics_arts.data.player.data.IInvestedPlayerData;
import net.rudahee.metallics_arts.modules.error_handling.exceptions.PlayerException;
import net.rudahee.metallics_arts.setup.network.ModNetwork;
import net.rudahee.metallics_arts.setup.registries.InvestedPlayerCapabilityRegister;
import net.rudahee.metallics_arts.utils.CapabilityUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class PowersRemoveRegister {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("ma-powers").requires(commandSource -> commandSource.hasPermission(2))
                .then(Commands.literal("remove")
                        .then(Commands.literal("all")
                                .executes(context -> removeAllPower(context, List.of(context.getSource().getPlayerOrException())))
                                .then(Commands.argument("target", EntityArgument.players())
                                        .executes(context -> removeAllPower(context, EntityArgument.getPlayers(context, "target")))))
                        .then(Commands.literal("random")
                                .executes(context -> removeRandomPower(context, List.of(context.getSource().getPlayerOrException())))
                                .then(Commands.argument("target", EntityArgument.players())
                                        .executes(context -> removeRandomPower(context, EntityArgument.getPlayers(context, "target")))))

                        .then(Commands.literal("allomantic")
                                .then(Commands.literal("all")
                                        .executes(context -> removeAllAllomanticPower(context, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target", EntityArgument.players())
                                                .executes(context -> removeAllAllomanticPower(context, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("steel")
                                        .executes(context -> removeAllomanticPower(context, MetalTagEnum.STEEL, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target", EntityArgument.players())
                                                .executes(context -> removeAllomanticPower(context, MetalTagEnum.STEEL, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("iron")
                                        .executes(context -> removeAllomanticPower(context, MetalTagEnum.IRON, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target", EntityArgument.players())
                                                .executes(context -> removeAllomanticPower(context, MetalTagEnum.IRON, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("tin")
                                        .executes(context -> removeAllomanticPower(context, MetalTagEnum.TIN, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target", EntityArgument.players())
                                                .executes(context -> removeAllomanticPower(context, MetalTagEnum.TIN, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("pewter")
                                        .executes(context -> removeAllomanticPower(context, MetalTagEnum.PEWTER, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target", EntityArgument.players())
                                                .executes(context -> removeAllomanticPower(context, MetalTagEnum.PEWTER, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("copper")
                                        .executes(context -> removeAllomanticPower(context, MetalTagEnum.COPPER, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target", EntityArgument.players())
                                                .executes(context -> removeAllomanticPower(context, MetalTagEnum.COPPER, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("bronze")
                                        .executes(context -> removeAllomanticPower(context, MetalTagEnum.BRONZE, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target", EntityArgument.players())
                                                .executes(context -> removeAllomanticPower(context, MetalTagEnum.BRONZE, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("zinc")
                                        .executes(context -> removeAllomanticPower(context, MetalTagEnum.ZINC, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target", EntityArgument.players())
                                                .executes(context -> removeAllomanticPower(context, MetalTagEnum.ZINC, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("brass")
                                        .executes(context -> removeAllomanticPower(context, MetalTagEnum.BRASS, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target", EntityArgument.players())
                                                .executes(context -> removeAllomanticPower(context, MetalTagEnum.BRASS, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("chromium")
                                        .executes(context -> removeAllomanticPower(context, MetalTagEnum.CHROMIUM, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target", EntityArgument.players())
                                                .executes(context -> removeAllomanticPower(context, MetalTagEnum.CHROMIUM, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("nicrosil")
                                        .executes(context -> removeAllomanticPower(context, MetalTagEnum.NICROSIL, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target", EntityArgument.players())
                                                .executes(context -> removeAllomanticPower(context, MetalTagEnum.NICROSIL, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("aluminum")
                                        .executes(context -> removeAllomanticPower(context, MetalTagEnum.ALUMINUM, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target", EntityArgument.players())
                                                .executes(context -> removeAllomanticPower(context, MetalTagEnum.ALUMINUM, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("duralumin")
                                        .executes(context -> removeAllomanticPower(context, MetalTagEnum.DURALUMIN, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target", EntityArgument.players())
                                                .executes(context -> removeAllomanticPower(context, MetalTagEnum.DURALUMIN, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("cadmium")
                                        .executes(context -> removeAllomanticPower(context, MetalTagEnum.CADMIUM, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target", EntityArgument.players())
                                                .executes(context -> removeAllomanticPower(context, MetalTagEnum.CADMIUM, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("bendalloy")
                                        .executes(context -> removeAllomanticPower(context, MetalTagEnum.BENDALLOY, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target", EntityArgument.players())
                                                .executes(context -> removeAllomanticPower(context, MetalTagEnum.BENDALLOY, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("electrum")
                                        .executes(context -> removeAllomanticPower(context, MetalTagEnum.ELECTRUM, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target", EntityArgument.players())
                                                .executes(context -> removeAllomanticPower(context, MetalTagEnum.ELECTRUM, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("gold")
                                        .executes(context -> removeAllomanticPower(context, MetalTagEnum.GOLD, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target", EntityArgument.players())
                                                .executes(context -> removeAllomanticPower(context, MetalTagEnum.GOLD, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("atium")
                                        .executes(context -> removeAllomanticPower(context, MetalTagEnum.ATIUM, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target", EntityArgument.players())
                                                .executes(context -> removeAllomanticPower(context, MetalTagEnum.ATIUM, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("malatium")
                                        .executes(context -> removeAllomanticPower(context, MetalTagEnum.MALATIUM, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target", EntityArgument.players())
                                                .executes(context -> removeAllomanticPower(context, MetalTagEnum.MALATIUM, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("lerasium")
                                        .executes(context -> removeAllomanticPower(context, MetalTagEnum.LERASIUM, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target", EntityArgument.players())
                                                .executes(context -> removeAllomanticPower(context, MetalTagEnum.LERASIUM, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("ettmetal")
                                        .executes(context -> removeAllomanticPower(context, MetalTagEnum.ETTMETAL, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target", EntityArgument.players())
                                                .executes(context -> removeAllomanticPower(context, MetalTagEnum.ETTMETAL, EntityArgument.getPlayers(context, "target"))))
                                )
                        )
                        .then(Commands.literal("feruchemic")
                                .then(Commands.literal("all")
                                        .executes(context -> removeAllFeruchemicPower(context, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target", EntityArgument.players())
                                                .executes(context -> removeAllFeruchemicPower(context, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("steel")
                                        .executes(context -> removeFeruchemicPower(context, MetalTagEnum.STEEL, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target", EntityArgument.players())
                                                .executes(context -> removeFeruchemicPower(context, MetalTagEnum.STEEL, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("iron")
                                        .executes(context -> removeFeruchemicPower(context, MetalTagEnum.IRON, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target", EntityArgument.players())
                                                .executes(context -> removeFeruchemicPower(context, MetalTagEnum.IRON, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("tin")
                                        .executes(context -> removeFeruchemicPower(context, MetalTagEnum.TIN, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target", EntityArgument.players())
                                                .executes(context -> removeFeruchemicPower(context, MetalTagEnum.TIN, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("pewter")
                                        .executes(context -> removeFeruchemicPower(context, MetalTagEnum.PEWTER, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target", EntityArgument.players())
                                                .executes(context -> removeFeruchemicPower(context, MetalTagEnum.PEWTER, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("copper")
                                        .executes(context -> removeFeruchemicPower(context, MetalTagEnum.COPPER, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target", EntityArgument.players())
                                                .executes(context -> removeFeruchemicPower(context, MetalTagEnum.COPPER, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("bronze")
                                        .executes(context -> removeFeruchemicPower(context, MetalTagEnum.BRONZE, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target", EntityArgument.players())
                                                .executes(context -> removeFeruchemicPower(context, MetalTagEnum.BRONZE, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("zinc")
                                        .executes(context -> removeFeruchemicPower(context, MetalTagEnum.ZINC, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target", EntityArgument.players())
                                                .executes(context -> removeFeruchemicPower(context, MetalTagEnum.ZINC, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("brass")
                                        .executes(context -> removeFeruchemicPower(context, MetalTagEnum.BRASS, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target", EntityArgument.players())
                                                .executes(context -> removeFeruchemicPower(context, MetalTagEnum.BRASS, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("chromium")
                                        .executes(context -> removeFeruchemicPower(context, MetalTagEnum.CHROMIUM, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target", EntityArgument.players())
                                                .executes(context -> removeFeruchemicPower(context, MetalTagEnum.CHROMIUM, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("nicrosil")
                                        .executes(context -> removeFeruchemicPower(context, MetalTagEnum.NICROSIL, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target", EntityArgument.players())
                                                .executes(context -> removeFeruchemicPower(context, MetalTagEnum.NICROSIL, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("aluminum")
                                        .executes(context -> removeFeruchemicPower(context, MetalTagEnum.ALUMINUM, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target", EntityArgument.players())
                                                .executes(context -> removeFeruchemicPower(context, MetalTagEnum.ALUMINUM, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("duralumin")
                                        .executes(context -> removeFeruchemicPower(context, MetalTagEnum.DURALUMIN, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target", EntityArgument.players())
                                                .executes(context -> removeFeruchemicPower(context, MetalTagEnum.DURALUMIN, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("cadmium")
                                        .executes(context -> removeFeruchemicPower(context, MetalTagEnum.CADMIUM, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target", EntityArgument.players())
                                                .executes(context -> removeFeruchemicPower(context, MetalTagEnum.CADMIUM, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("bendalloy")
                                        .executes(context -> removeFeruchemicPower(context, MetalTagEnum.BENDALLOY, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target", EntityArgument.players())
                                                .executes(context -> removeFeruchemicPower(context, MetalTagEnum.BENDALLOY, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("electrum")
                                        .executes(context -> removeFeruchemicPower(context, MetalTagEnum.ELECTRUM, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target", EntityArgument.players())
                                                .executes(context -> removeFeruchemicPower(context, MetalTagEnum.ELECTRUM, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("gold")
                                        .executes(context -> removeFeruchemicPower(context, MetalTagEnum.GOLD, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target", EntityArgument.players())
                                                .executes(context -> removeFeruchemicPower(context, MetalTagEnum.GOLD, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("atium")
                                        .executes(context -> removeFeruchemicPower(context, MetalTagEnum.ATIUM, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target", EntityArgument.players())
                                                .executes(context -> removeFeruchemicPower(context, MetalTagEnum.ATIUM, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("malatium")
                                        .executes(context -> removeFeruchemicPower(context, MetalTagEnum.MALATIUM, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target", EntityArgument.players())
                                                .executes(context -> removeFeruchemicPower(context, MetalTagEnum.MALATIUM, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("lerasium")
                                        .executes(context -> removeFeruchemicPower(context, MetalTagEnum.LERASIUM, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target", EntityArgument.players())
                                                .executes(context -> removeFeruchemicPower(context, MetalTagEnum.LERASIUM, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("ettmetal")
                                        .executes(context -> removeFeruchemicPower(context, MetalTagEnum.ETTMETAL, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target", EntityArgument.players())
                                                .executes(context -> removeFeruchemicPower(context, MetalTagEnum.ETTMETAL, EntityArgument.getPlayers(context, "target"))))
                                )
                        )
                ));

    }

    public static int removeAllomanticPower(CommandContext<CommandSourceStack> context, MetalTagEnum metalTagEnum, Collection<ServerPlayer> players) {

        for (ServerPlayer player: players) {
            player.getCapability(InvestedPlayerCapabilityRegister.PLAYER_CAP).ifPresent(p -> {
                p.removeAllomanticPower(metalTagEnum);
            });

            ModNetwork.syncInvestedDataPacket(player);
            player.sendSystemMessage(Component.literal(
                            Component.translatable("commands.metallics_arts.allomantic_one_power_remove")
                                    .getString()
                                    .formatted(metalTagEnum.getNameLower()))
                    .append(Component.literal(player.getScoreboardName())
                            .withStyle(ChatFormatting.GOLD)));
        }

        return 1;
    }
    public static int removeFeruchemicPower(CommandContext<CommandSourceStack> context, MetalTagEnum metalTagEnum, Collection<ServerPlayer> players) {

        for (ServerPlayer player: players) {
            player.getCapability(InvestedPlayerCapabilityRegister.PLAYER_CAP).ifPresent(p -> {
                        p.removeFeruchemicPower(metalTagEnum);
                    }
            );
            ModNetwork.syncInvestedDataPacket(player);
            player.sendSystemMessage(Component.literal(
                            Component.translatable("commands.metallics_arts.feruchemic_one_power_remove")
                                    .getString()
                                    .formatted(metalTagEnum.getNameLower()))
                    .append(Component.literal(player.getScoreboardName())
                            .withStyle(ChatFormatting.GOLD)));        }

        return 1;
    }
    public static int removeAllAllomanticPower(CommandContext<CommandSourceStack> context, Collection<ServerPlayer> players) {

        for (ServerPlayer player: players) {
            player.getCapability(InvestedPlayerCapabilityRegister.PLAYER_CAP).ifPresent(p -> {
                        p.removeAllAllomanticPower();
                    }
            );
            ModNetwork.syncInvestedDataPacket(player);
            player.sendSystemMessage(
                    Component.translatable("commands.metallics_arts.allomantic_all_power_remove")
                            .append(Component.literal(player.getScoreboardName()).withStyle(ChatFormatting.GOLD)));

        }

        return 1;
    }
    public static int removeAllFeruchemicPower(CommandContext<CommandSourceStack> context, Collection<ServerPlayer> players) {

        for (ServerPlayer player: players) {
            player.getCapability(InvestedPlayerCapabilityRegister.PLAYER_CAP).ifPresent(p -> {
                        p.removeAllFeruchemicPower();
                    }
            );
            ModNetwork.syncInvestedDataPacket(player);
            player.sendSystemMessage(
                    Component.translatable("commands.metallics_arts.feruchemic_all_power_remove")
                            .append(Component.literal(player.getScoreboardName()).withStyle(ChatFormatting.GOLD)));
        }

        return 1;
    }
    public static int removeAllPower(CommandContext<CommandSourceStack> context, Collection<ServerPlayer> players) {
        try {
            for (ServerPlayer player: players) {
                IInvestedPlayerData capability = CapabilityUtils.getCapability(player);

                capability.removeAllAllomanticPower();
                capability.removeAllFeruchemicPower();

                ModNetwork.syncInvestedDataPacket(capability, player);

                player.sendSystemMessage(
                        Component.translatable("commands.metallics_arts.feruchemic_all_power_remove")
                                .append(Component.literal(player.getScoreboardName()).withStyle(ChatFormatting.GOLD)));            }
        } catch (PlayerException e) {
            e.printStackTrace();
        }
        return 1;
    }


    public static int removeRandomPower(CommandContext<CommandSourceStack> context, Collection<ServerPlayer> players) {

        Random random = new Random();

        MetalTagEnum metal = null;
        ArrayList<ServerPlayer> playerCollection;

        for (ServerPlayer player : players) {
            metal = MetalTagEnum.values()[random.nextInt(MetalTagEnum.values().length)];

            playerCollection = new ArrayList<>();
            playerCollection.add(player);


            if (Math.random() > 0.5) {
                removeFeruchemicPower(context, metal, playerCollection);

                player.sendSystemMessage(Component.literal(
                                Component.translatable("commands.metallics_arts.feruchemic_one_power_remove")
                                        .getString()
                                        .formatted(metal.getNameLower()))
                        .append(Component.literal(player.getScoreboardName())
                                .withStyle(ChatFormatting.GOLD)));

            } else {
                removeAllomanticPower(context, metal, playerCollection);

                player.sendSystemMessage(Component.literal(
                                Component.translatable("commands.metallics_arts.allomantic_one_power_remove")
                                        .getString()
                                        .formatted(metal.getNameLower()))
                        .append(Component.literal(player.getScoreboardName())
                                .withStyle(ChatFormatting.GOLD)));
            }

            ModNetwork.syncInvestedDataPacket(player);
        }

        return 1;
    }

}
