package net.rudahee.metallics_arts.setup.registries.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.setup.network.ModNetwork;
import net.rudahee.metallics_arts.setup.registries.ModBlocksRegister;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class PowersAddRegister {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {

        dispatcher.register(Commands.literal("ma-powers").requires(commandSource -> commandSource.hasPermission(2))
                .then(Commands.literal("add")
                        .then(Commands.literal("all")
                                .executes(context -> addAllPower(context, List.of(context.getSource().getPlayerOrException())))
                                .then(Commands.argument("target", EntityArgument.players())
                                        .executes(context -> addAllPower(context, EntityArgument.getPlayers(context, "target")))))
                        .then(Commands.literal("random")
                                .executes(context -> addRandomPower(context, List.of(context.getSource().getPlayerOrException())))
                                .then(Commands.argument("target",EntityArgument.players())
                                        .executes(context -> addRandomPower(context, EntityArgument.getPlayers(context, "target")))))

                        .then(Commands.literal("allomantic")
                                .then(Commands.literal("all")
                                        .executes(context -> addAllAllomanticPower(context, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> addAllAllomanticPower(context, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("steel")
                                        .executes(context -> addAllomanticPower(context, MetalTagEnum.STEEL, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> addAllomanticPower(context, MetalTagEnum.STEEL, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("iron")
                                        .executes(context -> addAllomanticPower(context, MetalTagEnum.IRON, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> addAllomanticPower(context, MetalTagEnum.IRON, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("tin")
                                        .executes(context -> addAllomanticPower(context, MetalTagEnum.TIN, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> addAllomanticPower(context, MetalTagEnum.TIN, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("pewter")
                                        .executes(context -> addAllomanticPower(context, MetalTagEnum.PEWTER, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> addAllomanticPower(context, MetalTagEnum.PEWTER, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("copper")
                                        .executes(context -> addAllomanticPower(context, MetalTagEnum.COPPER, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> addAllomanticPower(context, MetalTagEnum.COPPER, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("bronze")
                                        .executes(context -> addAllomanticPower(context, MetalTagEnum.BRONZE, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> addAllomanticPower(context, MetalTagEnum.BRONZE, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("zinc")
                                        .executes(context -> addAllomanticPower(context, MetalTagEnum.ZINC, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> addAllomanticPower(context, MetalTagEnum.ZINC, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("brass")
                                        .executes(context -> addAllomanticPower(context, MetalTagEnum.BRASS, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> addAllomanticPower(context, MetalTagEnum.BRASS, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("chromium")
                                        .executes(context -> addAllomanticPower(context, MetalTagEnum.CHROMIUM, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> addAllomanticPower(context, MetalTagEnum.CHROMIUM, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("nicrosil")
                                        .executes(context -> addAllomanticPower(context, MetalTagEnum.NICROSIL, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> addAllomanticPower(context, MetalTagEnum.NICROSIL, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("aluminum")
                                        .executes(context -> addAllomanticPower(context, MetalTagEnum.ALUMINUM, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> addAllomanticPower(context, MetalTagEnum.ALUMINUM, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("duralumin")
                                        .executes(context -> addAllomanticPower(context, MetalTagEnum.DURALUMIN, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> addAllomanticPower(context, MetalTagEnum.DURALUMIN, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("cadmium")
                                        .executes(context -> addAllomanticPower(context, MetalTagEnum.CADMIUM, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> addAllomanticPower(context, MetalTagEnum.CADMIUM, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("bendalloy")
                                        .executes(context -> addAllomanticPower(context, MetalTagEnum.BENDALLOY, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> addAllomanticPower(context, MetalTagEnum.BENDALLOY, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("electrum")
                                                .executes(context -> addAllomanticPower(context, MetalTagEnum.ELECTRUM, List.of(context.getSource().getPlayerOrException())))
                                .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> addAllomanticPower(context, MetalTagEnum.ELECTRUM, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("gold")
                                                .executes(context -> addAllomanticPower(context, MetalTagEnum.GOLD, List.of(context.getSource().getPlayerOrException())))
                                .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> addAllomanticPower(context, MetalTagEnum.GOLD, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("atium")
                                        .executes(context -> addAllomanticPower(context, MetalTagEnum.ATIUM, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> addAllomanticPower(context, MetalTagEnum.ATIUM, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("malatium")
                                        .executes(context -> addAllomanticPower(context, MetalTagEnum.MALATIUM, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> addAllomanticPower(context, MetalTagEnum.MALATIUM, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("lerasium")
                                        .executes(context -> addAllomanticPower(context, MetalTagEnum.LERASIUM, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> addAllomanticPower(context, MetalTagEnum.LERASIUM, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("ettmetal")
                                        .executes(context -> addAllomanticPower(context, MetalTagEnum.ETTMETAL, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> addAllomanticPower(context, MetalTagEnum.ETTMETAL, EntityArgument.getPlayers(context, "target"))))
                                )
                        )
                        .then(Commands.literal("feruchemic")
                                .then(Commands.literal("all")
                                        .executes(context -> addAllAllomanticPower(context, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> addAllFeruchemicPower(context, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("steel")
                                        .executes(context -> addAllomanticPower(context, MetalTagEnum.STEEL, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> addFeruchemicPower(context, MetalTagEnum.STEEL, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("iron")
                                        .executes(context -> addAllomanticPower(context, MetalTagEnum.IRON, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> addFeruchemicPower(context, MetalTagEnum.IRON, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("tin")
                                        .executes(context -> addAllomanticPower(context, MetalTagEnum.TIN, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> addFeruchemicPower(context, MetalTagEnum.TIN, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("pewter")
                                        .executes(context -> addAllomanticPower(context, MetalTagEnum.PEWTER, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> addFeruchemicPower(context, MetalTagEnum.PEWTER, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("copper")
                                        .executes(context -> addAllomanticPower(context, MetalTagEnum.COPPER, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> addFeruchemicPower(context, MetalTagEnum.COPPER, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("bronze")
                                        .executes(context -> addAllomanticPower(context, MetalTagEnum.BRONZE, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> addFeruchemicPower(context, MetalTagEnum.BRONZE, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("zinc")
                                        .executes(context -> addAllomanticPower(context, MetalTagEnum.ZINC, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> addFeruchemicPower(context, MetalTagEnum.ZINC, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("brass")
                                        .executes(context -> addAllomanticPower(context, MetalTagEnum.BRASS, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> addFeruchemicPower(context, MetalTagEnum.BRASS, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("chromium")
                                        .executes(context -> addAllomanticPower(context, MetalTagEnum.CHROMIUM, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> addFeruchemicPower(context, MetalTagEnum.CHROMIUM, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("nicrosil")
                                        .executes(context -> addAllomanticPower(context, MetalTagEnum.NICROSIL, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> addFeruchemicPower(context, MetalTagEnum.NICROSIL, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("aluminum")
                                        .executes(context -> addAllomanticPower(context, MetalTagEnum.ALUMINUM, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> addFeruchemicPower(context, MetalTagEnum.ALUMINUM, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("duralumin")
                                        .executes(context -> addAllomanticPower(context, MetalTagEnum.DURALUMIN, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> addFeruchemicPower(context, MetalTagEnum.DURALUMIN, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("cadmium")
                                        .executes(context -> addAllomanticPower(context, MetalTagEnum.CADMIUM, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> addFeruchemicPower(context, MetalTagEnum.CADMIUM, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("bendalloy")
                                        .executes(context -> addAllomanticPower(context, MetalTagEnum.BENDALLOY, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> addFeruchemicPower(context, MetalTagEnum.BENDALLOY, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("electrum")
                                        .executes(context -> addAllomanticPower(context, MetalTagEnum.ELECTRUM, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> addFeruchemicPower(context, MetalTagEnum.ELECTRUM, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("gold")
                                        .executes(context -> addAllomanticPower(context, MetalTagEnum.GOLD, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> addFeruchemicPower(context, MetalTagEnum.GOLD, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("atium")
                                        .executes(context -> addAllomanticPower(context, MetalTagEnum.ATIUM, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> addFeruchemicPower(context, MetalTagEnum.ATIUM, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("malatium")
                                        .executes(context -> addAllomanticPower(context, MetalTagEnum.MALATIUM, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> addFeruchemicPower(context, MetalTagEnum.MALATIUM, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("lerasium")
                                        .executes(context -> addAllomanticPower(context, MetalTagEnum.LERASIUM, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> addFeruchemicPower(context, MetalTagEnum.LERASIUM, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("ettmetal")
                                        .executes(context -> addAllomanticPower(context, MetalTagEnum.ETTMETAL, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> addFeruchemicPower(context, MetalTagEnum.ETTMETAL, EntityArgument.getPlayers(context, "target"))))
                                )
                        )
                ));
    }


    public static int addAllomanticPower (CommandContext<CommandSourceStack> context, MetalTagEnum metalTagEnum, Collection<ServerPlayer> players){

        for (ServerPlayer player: players) {
            player.getCapability(ModBlocksRegister.InvestedCapabilityRegister.PLAYER_CAP).ifPresent(p -> {
                        p.addAllomanticPower(metalTagEnum);
                    }
            );
            ModNetwork.syncInvestedDataPacket(player);
            player.sendSystemMessage(Component.translatable("Added " + metalTagEnum.getNameLower() + " allomantic power to " + player.getScoreboardName()));
        }

        return 1;
    }
    public static int addFeruchemicPower (CommandContext<CommandSourceStack> context, MetalTagEnum metalTagEnum, Collection<ServerPlayer> players){

        for (ServerPlayer player: players) {
            player.getCapability(ModBlocksRegister.InvestedCapabilityRegister.PLAYER_CAP).ifPresent(p -> {
                        p.addFeruchemicPower(metalTagEnum);
                    }
            );
            ModNetwork.syncInvestedDataPacket(player);
            player.sendSystemMessage(Component.translatable("Added " + metalTagEnum.getNameLower() + " feruchemic power to " + player.getScoreboardName()));
        }

        return 1;
    }
    public static int addAllAllomanticPower (CommandContext<CommandSourceStack> context, Collection<ServerPlayer> players){
        ServerPlayer playerEntity = null;

        for (ServerPlayer player: players) {
            player.getCapability(ModBlocksRegister.InvestedCapabilityRegister.PLAYER_CAP).ifPresent(p -> {
                        p.addAllAllomantic();
                    }
            );
            ModNetwork.syncInvestedDataPacket(player);
            player.sendSystemMessage(Component.translatable("Added all allomantics powers to " + player.getScoreboardName()));
        }

        return 1;
    }
    public static int addAllFeruchemicPower (CommandContext<CommandSourceStack> context, Collection<ServerPlayer> players){

        for (ServerPlayer player: players) {
            player.getCapability(ModBlocksRegister.InvestedCapabilityRegister.PLAYER_CAP).ifPresent(p -> {
                        p.addAllFeruchemic();
                    }
            );
            ModNetwork.syncInvestedDataPacket(player);
            player.sendSystemMessage(Component.translatable("Added all feruchemics powers to " + player.getScoreboardName()));
        }

        return 1;
    }
    public static int addAllPower(CommandContext<CommandSourceStack> context, Collection<ServerPlayer> players){

        for (ServerPlayer player: players) {
            player.getCapability(ModBlocksRegister.InvestedCapabilityRegister.PLAYER_CAP).ifPresent(p ->{
                    p.addAllAllomantic();
                    p.addAllFeruchemic();
                }
            );
            ModNetwork.syncInvestedDataPacket(player);
            player.sendSystemMessage(Component.translatable("Added all powers to " + player.getScoreboardName()));
        }

        return 1;
    }
    public static int addRandomPower(CommandContext<CommandSourceStack> context, Collection<ServerPlayer> players) {

        Random random = new Random();

        MetalTagEnum metal = null;
        ArrayList<ServerPlayer> playerCollection;

        for (ServerPlayer player : players) {
            metal = MetalTagEnum.values()[random.nextInt(MetalTagEnum.values().length)];

            playerCollection = new ArrayList<>();
            playerCollection.add(player);


            if (Math.random() > 0.5) {
                addFeruchemicPower(context, metal, playerCollection);
            } else {
                addAllomanticPower(context, metal, playerCollection);
            }

            ModNetwork.syncInvestedDataPacket(player);
        }

        return 1;
    }

}
