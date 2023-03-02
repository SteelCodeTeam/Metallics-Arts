package net.rudahee.metallics_arts.setup.registries;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.setup.network.ModNetwork;
import net.rudahee.metallics_arts.setup.registries.commands.ItemsCommandRegister;
import net.rudahee.metallics_arts.setup.registries.commands.PowersAddRegister;
import net.rudahee.metallics_arts.setup.registries.commands.PowersGetRegister;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ModCommandsRegister {

    private static final DynamicCommandExceptionType ERROR_CANT_ADD = new DynamicCommandExceptionType(s -> Component.translatable("commands.metallic_arts.err_add", s));
    private static final DynamicCommandExceptionType ERROR_CANT_REMOVE = new DynamicCommandExceptionType(s -> Component.translatable("commands.metallic_arts.err_remove", s));

    private static Predicate<CommandSourceStack> permissions(int level) {
        return (player) -> player.hasPermission(level);
    }

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        ItemsCommandRegister.register(dispatcher);
        PowersGetRegister.register(dispatcher);

        dispatcher.register(Commands.literal("ma-powers").requires(commandSource -> commandSource.hasPermission(2))
                .then(Commands.literal("add")
                        .then(Commands.literal("all")
                                .then(Commands.argument("target",EntityArgument.player())
                                        .executes(context -> addAllPower(context, EntityArgument.getPlayer(context, "target")))))
                        .then(Commands.literal("random")
                                .then(Commands.argument("target",EntityArgument.player())
                                        .executes(context -> addRandomPower(context, EntityArgument.getPlayer(context, "target")))))

                        .then(Commands.literal("allomantic")
                                .then(Commands.literal("all")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addAllAllomanticPower(context, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("steel")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addAllomanticPower(context, MetalTagEnum.STEEL, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("iron")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addAllomanticPower(context, MetalTagEnum.IRON, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("tin")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addAllomanticPower(context, MetalTagEnum.TIN, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("pewter")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addAllomanticPower(context, MetalTagEnum.PEWTER, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("copper")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addAllomanticPower(context, MetalTagEnum.COPPER, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("bronze")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addAllomanticPower(context, MetalTagEnum.BRONZE, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("zinc")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addAllomanticPower(context, MetalTagEnum.ZINC, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("brass")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addAllomanticPower(context, MetalTagEnum.BRASS, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("chromium")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addAllomanticPower(context, MetalTagEnum.CHROMIUM, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("nicrosil")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addAllomanticPower(context, MetalTagEnum.NICROSIL, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("aluminum")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addAllomanticPower(context, MetalTagEnum.ALUMINUM, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("duralumin")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addAllomanticPower(context, MetalTagEnum.DURALUMIN, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("cadmium")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addAllomanticPower(context, MetalTagEnum.CADMIUM, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("bendalloy")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addAllomanticPower(context, MetalTagEnum.BENDALLOY, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("electrum")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addAllomanticPower(context, MetalTagEnum.ELECTRUM, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("gold")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addAllomanticPower(context, MetalTagEnum.GOLD, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("atium")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addAllomanticPower(context, MetalTagEnum.ATIUM, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("malatium")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addAllomanticPower(context, MetalTagEnum.MALATIUM, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("lerasium")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addAllomanticPower(context, MetalTagEnum.LERASIUM, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("ettmetal")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addAllomanticPower(context, MetalTagEnum.ETTMETAL, EntityArgument.getPlayer(context, "target"))))
                                )
                        )
                        .then(Commands.literal("feruchemic")
                                .then(Commands.literal("all")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addAllFeruchemicPower(context, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("steel")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addFeruchemicPower(context, MetalTagEnum.STEEL, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("iron")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addFeruchemicPower(context, MetalTagEnum.IRON, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("tin")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addFeruchemicPower(context, MetalTagEnum.TIN, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("pewter")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addFeruchemicPower(context, MetalTagEnum.PEWTER, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("copper")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addFeruchemicPower(context, MetalTagEnum.COPPER, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("bronze")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addFeruchemicPower(context, MetalTagEnum.BRONZE, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("zinc")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addFeruchemicPower(context, MetalTagEnum.ZINC, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("brass")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addFeruchemicPower(context, MetalTagEnum.BRASS, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("chromium")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addFeruchemicPower(context, MetalTagEnum.CHROMIUM, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("nicrosil")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addFeruchemicPower(context, MetalTagEnum.NICROSIL, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("aluminum")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addFeruchemicPower(context, MetalTagEnum.ALUMINUM, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("duralumin")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addFeruchemicPower(context, MetalTagEnum.DURALUMIN, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("cadmium")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addFeruchemicPower(context, MetalTagEnum.CADMIUM, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("bendalloy")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addFeruchemicPower(context, MetalTagEnum.BENDALLOY, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("electrum")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addFeruchemicPower(context, MetalTagEnum.ELECTRUM, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("gold")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addFeruchemicPower(context, MetalTagEnum.GOLD, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("atium")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addFeruchemicPower(context, MetalTagEnum.ATIUM, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("malatium")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addFeruchemicPower(context, MetalTagEnum.MALATIUM, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("lerasium")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addFeruchemicPower(context, MetalTagEnum.LERASIUM, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("ettmetal")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addFeruchemicPower(context, MetalTagEnum.ETTMETAL, EntityArgument.getPlayer(context, "target"))))
                                )
                        )
                )
                .then(Commands.literal("remove")

                        .then(Commands.literal("all")
                                .then(Commands.argument("target",EntityArgument.player())
                                        .executes(context -> removeAllPower(context, EntityArgument.getPlayer(context, "target")))))
                        .then(Commands.literal("allomantic")
                                .then(Commands.literal("all")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeAllAllomanticPower(context, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("steel")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeAllomanticPower(context, MetalTagEnum.STEEL, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("iron")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeAllomanticPower(context, MetalTagEnum.IRON, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("tin")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeAllomanticPower(context, MetalTagEnum.TIN, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("pewter")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeAllomanticPower(context, MetalTagEnum.PEWTER, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("copper")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeAllomanticPower(context, MetalTagEnum.COPPER, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("bronze")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeAllomanticPower(context, MetalTagEnum.BRONZE, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("zinc")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeAllomanticPower(context, MetalTagEnum.ZINC, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("brass")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeAllomanticPower(context, MetalTagEnum.BRASS, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("chromium")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeAllomanticPower(context, MetalTagEnum.CHROMIUM, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("nicrosil")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeAllomanticPower(context, MetalTagEnum.NICROSIL, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("aluminum")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeAllomanticPower(context, MetalTagEnum.ALUMINUM, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("duralumin")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeAllomanticPower(context, MetalTagEnum.DURALUMIN, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("cadmium")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeAllomanticPower(context, MetalTagEnum.CADMIUM, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("bendalloy")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeAllomanticPower(context, MetalTagEnum.BENDALLOY, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("electrum")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeAllomanticPower(context, MetalTagEnum.ELECTRUM, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("gold")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeAllomanticPower(context, MetalTagEnum.GOLD, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("atium")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeAllomanticPower(context, MetalTagEnum.ATIUM, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("malatium")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeAllomanticPower(context, MetalTagEnum.MALATIUM, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("lerasium")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeAllomanticPower(context, MetalTagEnum.LERASIUM, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("ettmetal")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeAllomanticPower(context, MetalTagEnum.ETTMETAL, EntityArgument.getPlayer(context, "target"))))
                                )
                        )
                        .then(Commands.literal("feruchemic")
                                .then(Commands.literal("all")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeAllFeruchemicPower(context, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("steel")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeFeruchemicPower(context, MetalTagEnum.STEEL, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("iron")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeFeruchemicPower(context, MetalTagEnum.IRON, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("tin")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeFeruchemicPower(context, MetalTagEnum.TIN, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("pewter")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeFeruchemicPower(context, MetalTagEnum.PEWTER, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("copper")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeFeruchemicPower(context, MetalTagEnum.COPPER, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("bronze")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeFeruchemicPower(context, MetalTagEnum.BRONZE, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("zinc")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeFeruchemicPower(context, MetalTagEnum.ZINC, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("brass")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeFeruchemicPower(context, MetalTagEnum.BRASS, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("chromium")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeFeruchemicPower(context, MetalTagEnum.CHROMIUM, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("nicrosil")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeFeruchemicPower(context, MetalTagEnum.NICROSIL, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("aluminum")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeFeruchemicPower(context, MetalTagEnum.ALUMINUM, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("duralumin")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeFeruchemicPower(context, MetalTagEnum.DURALUMIN, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("cadmium")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeFeruchemicPower(context, MetalTagEnum.CADMIUM, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("bendalloy")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeFeruchemicPower(context, MetalTagEnum.BENDALLOY, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("electrum")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeFeruchemicPower(context, MetalTagEnum.ELECTRUM, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("gold")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeFeruchemicPower(context, MetalTagEnum.GOLD, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("atium")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeFeruchemicPower(context, MetalTagEnum.ATIUM, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("malatium")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeFeruchemicPower(context, MetalTagEnum.MALATIUM, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("lerasium")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeFeruchemicPower(context, MetalTagEnum.LERASIUM, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("ettmetal")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeFeruchemicPower(context, MetalTagEnum.ETTMETAL, EntityArgument.getPlayer(context, "target"))))
                                )
                        )
                )
        );

    }



    // Metodo para adquirir por comando mentes de metal


    public static int addAllomanticPower (CommandContext<CommandSourceStack> context, MetalTagEnum metalTagEnum, ServerPlayer player){

        player.getCapability(ModBlocksRegister.InvestedCapabilityRegister.PLAYER_CAP).ifPresent(p ->{
            p.addAllomanticPower(metalTagEnum);
                }
        );
        ModNetwork.syncInvestedDataPacket(player);
        player.sendSystemMessage(Component.translatable("Added " + metalTagEnum.getNameLower() + " allomantic power to " + player.getScoreboardName()));

        return 1;
    }
    public static int addFeruchemicPower (CommandContext<CommandSourceStack> context, MetalTagEnum metalTagEnum, ServerPlayer player){

        player.getCapability(ModBlocksRegister.InvestedCapabilityRegister.PLAYER_CAP).ifPresent(p ->{
                    p.addFeruchemicPower(metalTagEnum);
                }
        );
        ModNetwork.syncInvestedDataPacket(player);
        player.sendSystemMessage(Component.translatable("Added " + metalTagEnum.getNameLower() + " feruchemic power to " + player.getScoreboardName()));

        return 1;
    }
    public static int addAllAllomanticPower (CommandContext<CommandSourceStack> context, ServerPlayer player){
        ServerPlayer playerEntity = null;

            player.getCapability(ModBlocksRegister.InvestedCapabilityRegister.PLAYER_CAP).ifPresent(p ->{
                        p.addAllAllomantic();
                    }
            );
            ModNetwork.syncInvestedDataPacket(player);
            player.sendSystemMessage(Component.translatable("Added all allomantics powers to " + player.getScoreboardName()));

        return 1;
    }
    public static int addAllFeruchemicPower (CommandContext<CommandSourceStack> context, ServerPlayer player){

        player.getCapability(ModBlocksRegister.InvestedCapabilityRegister.PLAYER_CAP).ifPresent(p ->{
                    p.addAllFeruchemic();
                }
        );
        ModNetwork.syncInvestedDataPacket(player);
        player.sendSystemMessage(Component.translatable("Added all feruchemics powers to " + player.getScoreboardName()));


        return 1;
    }
    public static int addAllPower(CommandContext<CommandSourceStack> context, ServerPlayer player){

        player.getCapability(ModBlocksRegister.InvestedCapabilityRegister.PLAYER_CAP).ifPresent(p ->{
                    p.addAllAllomantic();
                    p.addAllFeruchemic();
                }
        );
        ModNetwork.syncInvestedDataPacket(player);
        player.sendSystemMessage(Component.translatable("Added all powers to " + player.getScoreboardName()));


        return 1;
    }
    public static int addRandomPower(CommandContext<CommandSourceStack> context, ServerPlayer player){

        Random random = new Random();
        MetalTagEnum metal;
        if (player != null){
            metal = MetalTagEnum.values()[random.nextInt(MetalTagEnum.values().length)];
            if (Math.random()>0.5){
                addFeruchemicPower(context,metal, player);
            }else{
                addAllomanticPower(context,metal, player);
            }
        }
        ModNetwork.syncInvestedDataPacket(player);
        return 1;
    }

    public static int removeAllomanticPower (CommandContext<CommandSourceStack> context, MetalTagEnum metalTagEnum, ServerPlayer player){

        player.getCapability(ModBlocksRegister.InvestedCapabilityRegister.PLAYER_CAP).ifPresent(p ->{
                    p.removeAllomanticPower(metalTagEnum);
                });

        ModNetwork.syncInvestedDataPacket(player);
        player.sendSystemMessage(Component.translatable("Revoke " + metalTagEnum.getNameLower() + " allomantic power to " + player.getScoreboardName()));

        return 1;
    }
    public static int removeFeruchemicPower (CommandContext<CommandSourceStack> context, MetalTagEnum metalTagEnum, ServerPlayer player){

        player.getCapability(ModBlocksRegister.InvestedCapabilityRegister.PLAYER_CAP).ifPresent(p ->{
                    p.removeFeruchemicPower(metalTagEnum);
                }
        );
        ModNetwork.syncInvestedDataPacket(player);
        player.sendSystemMessage(Component.translatable("Revoke " + metalTagEnum.getNameLower() + " feruchemic power to " + player.getScoreboardName()));

        return 1;
    }
    public static int removeAllAllomanticPower (CommandContext<CommandSourceStack> context, ServerPlayer player){

        player.getCapability(ModBlocksRegister.InvestedCapabilityRegister.PLAYER_CAP).ifPresent(p ->{
                    p.removeAllAllomanticPower();
                }
        );
        ModNetwork.syncInvestedDataPacket(player);
        player.sendSystemMessage(Component.translatable("Revoke all allomantics powers to " + player.getScoreboardName()));


        return 1;
    }
    public static int removeAllFeruchemicPower (CommandContext<CommandSourceStack> context, ServerPlayer player){

        player.getCapability(ModBlocksRegister.InvestedCapabilityRegister.PLAYER_CAP).ifPresent(p ->{
                    p.removeAllFeruchemicPower();
                }
        );
        ModNetwork.syncInvestedDataPacket(player);
        player.sendSystemMessage(Component.translatable("Revoke all feruchemics powers to " + player.getScoreboardName()));

        return 1;
    }
    public static int removeAllPower(CommandContext<CommandSourceStack> context, ServerPlayer player){

        player.getCapability(ModBlocksRegister.InvestedCapabilityRegister.PLAYER_CAP).ifPresent(p ->{
                    p.removeAllAllomanticPower();
                    p.removeAllFeruchemicPower();
                }
        );
        ModNetwork.syncInvestedDataPacket(player);
        player.sendSystemMessage(Component.translatable("Revoke all powers to " + player.getScoreboardName()));

        return 1;
    }

    private static void sendAllMessage(ServerPlayer finalPlayerEntity, ArrayList<MetalTagEnum> result, String type) {

        ArrayList<MetalTagEnum> allMetals = new ArrayList<>(Arrays.asList(MetalTagEnum.values()));


        if (type.equals("all") || type.equals("allomantic")) {

            StringBuilder allAlomanticMetalsHave = new StringBuilder();

            for (MetalTagEnum metal: result) {
                allAlomanticMetalsHave.append(metal.getNameLower() + " ");
            }

            StringBuilder allAllomanticMetalsDontHave = new StringBuilder();

            ArrayList<MetalTagEnum> resultAllomanticDontHave = new ArrayList<>(allMetals.stream().filter(f-> !result.contains(f)).collect(Collectors.toList()));

            for (MetalTagEnum metal: resultAllomanticDontHave) {
                allAllomanticMetalsDontHave.append(metal.getNameLower() + " ");
            }

            finalPlayerEntity.sendSystemMessage(Component.translatable("The player have those allomantic powers: " + allAlomanticMetalsHave));
            finalPlayerEntity.sendSystemMessage(Component.translatable("The player dont have those feruchemic powers: " + allAllomanticMetalsDontHave));

        } else if (type.equals("all") || type.equals("feruchemic")) {
            StringBuilder allFeruchemicMetalsHave = new StringBuilder();

            for (MetalTagEnum metal: result) {
                allFeruchemicMetalsHave.append(metal.getNameLower() + " ");
            }

            StringBuilder allFeruchemicMetalsDontHave = new StringBuilder();

            ArrayList<MetalTagEnum> resultFeruchemicDontHave = new ArrayList<>(allMetals.stream().filter(f-> !result.contains(f)).collect(Collectors.toList()));

            for (MetalTagEnum metal: resultFeruchemicDontHave) {
                allFeruchemicMetalsDontHave.append(metal.getNameLower() + " ");
            }

            finalPlayerEntity.sendSystemMessage(Component.translatable("The player have those feruchemic powers: " + allFeruchemicMetalsHave));
            finalPlayerEntity.sendSystemMessage(Component.translatable("The player dont have those feruchemic powers: " + allFeruchemicMetalsDontHave));

        }
    }
    private static void sendSimpleMessage(ServerPlayer finalPlayerEntity, boolean result, MetalTagEnum metal, String type) {

        String have = (result) ?  "have " : "have not ";


        Component text = Component.translatable("The player " + have + type + " " + metal.getNameLower() + " power");

        finalPlayerEntity.sendSystemMessage(text);
    }
}
