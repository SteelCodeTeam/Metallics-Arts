package net.rudahee.metallics_arts.setup.registries.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.enums.implementations.custom_items.MetalMindEnum;
import net.rudahee.metallics_arts.modules.custom_items.metal_minds.BandMindAbstract;
import net.rudahee.metallics_arts.modules.custom_items.vials.Vial;
import net.rudahee.metallics_arts.setup.network.ModNetwork;
import net.rudahee.metallics_arts.setup.registries.ModBlocksRegister;
import net.rudahee.metallics_arts.setup.registries.ModItemsRegister;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ModCommands {

    private static final DynamicCommandExceptionType ERROR_CANT_ADD = new DynamicCommandExceptionType(s -> Component.translatable("commands.metallic_arts.err_add", s));
    private static final DynamicCommandExceptionType ERROR_CANT_REMOVE = new DynamicCommandExceptionType(s -> Component.translatable("commands.metallic_arts.err_remove", s));

    private static Predicate<CommandSourceStack> permissions(int level) {
        return (player) -> player.hasPermission(level);
    }

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {

        dispatcher.register(Commands.literal("ma-items").requires(commandSource -> commandSource.hasPermission(2))
                .then(Commands.literal("give")
                        .then(Commands.literal("large_vial")
                                .then(Commands.argument("target", EntityArgument.player())
                                        .executes(context -> getLargeVial(context, EntityArgument.getPlayer(context, "target")))))
                        .then(Commands.literal("band_steel_iron")
                                .then(Commands.argument("target", EntityArgument.player())
                                        .executes(context -> getMetalmind(context, EntityArgument.getPlayer(context, "target"), "band_steel_iron"))))
                        .then(Commands.literal("band_zinc_brass")
                                .then(Commands.argument("target", EntityArgument.player())
                                        .executes(context -> getMetalmind(context, EntityArgument.getPlayer(context, "target"), "band_zinc_brass"))))
                        .then(Commands.literal("band_aluminium_duralumin")
                                .then(Commands.argument("target", EntityArgument.player())
                                        .executes(context -> getMetalmind(context, EntityArgument.getPlayer(context, "target"), "band_aluminium_duralumin"))))
                        .then(Commands.literal("band_atium_malatium")
                                .then(Commands.argument("target", EntityArgument.player())
                                        .executes(context -> getMetalmind(context, EntityArgument.getPlayer(context, "target"), "band_atium_malatium"))))
                        .then(Commands.literal("band_cadmium_bendalloy")
                                .then(Commands.argument("target", EntityArgument.player())
                                        .executes(context -> getMetalmind(context, EntityArgument.getPlayer(context, "target"), "band_cadmium_bendalloy"))))
                        .then(Commands.literal("band_chromium_nicrosil")
                                .then(Commands.argument("target", EntityArgument.player())
                                        .executes(context -> getMetalmind(context, EntityArgument.getPlayer(context, "target"), "band_chromium_nicrosil"))))
                        .then(Commands.literal("band_copper_bronze")
                                .then(Commands.argument("target", EntityArgument.player())
                                        .executes(context -> getMetalmind(context, EntityArgument.getPlayer(context, "target"), "band_copper_bronze"))))
                        .then(Commands.literal("band_electrum_gold")
                                .then(Commands.argument("target", EntityArgument.player())
                                        .executes(context -> getMetalmind(context, EntityArgument.getPlayer(context, "target"), "band_electrum_gold"))))
                        .then(Commands.literal("band_lerasium_ettmetal")
                                .then(Commands.argument("target", EntityArgument.player())
                                        .executes(context -> getMetalmind(context, EntityArgument.getPlayer(context, "target"), "band_lerasium_ettmetal"))))
                        .then(Commands.literal("band_pewter_tin")
                                .then(Commands.argument("target", EntityArgument.player())
                                        .executes(context -> getMetalmind(context, EntityArgument.getPlayer(context, "target"), "band_pewter_tin"))))));

        dispatcher.register(Commands.literal("ma-powers").requires(commandSource -> commandSource.hasPermission(2))
                .then(Commands.literal("get")
                        .then(Commands.literal("all")
                                .then(Commands.argument("target",EntityArgument.player())
                                        .executes(context -> getAllPower(context,"all", EntityArgument.getPlayer(context, "target")))))

                        .then(Commands.literal("allomantic")
                                .then(Commands.literal("all")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> getAllPower(context,"allomantic", EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("steel")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> getAllomanticPower(context, MetalTagEnum.STEEL, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("iron")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> getAllomanticPower(context, MetalTagEnum.IRON, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("tin")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> getAllomanticPower(context, MetalTagEnum.TIN, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("pewter")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> getAllomanticPower(context, MetalTagEnum.PEWTER, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("copper")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> getAllomanticPower(context, MetalTagEnum.COPPER, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("bronze")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> getAllomanticPower(context, MetalTagEnum.BRONZE, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("zinc")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> getAllomanticPower(context, MetalTagEnum.ZINC, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("brass")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> getAllomanticPower(context, MetalTagEnum.BRASS, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("chromium")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> getAllomanticPower(context, MetalTagEnum.CHROMIUM, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("nicrosil")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> getAllomanticPower(context, MetalTagEnum.NICROSIL, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("aluminum")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> getAllomanticPower(context, MetalTagEnum.ALUMINUM, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("duralumin")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> getAllomanticPower(context, MetalTagEnum.DURALUMIN, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("cadmium")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> getAllomanticPower(context, MetalTagEnum.CADMIUM, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("bendalloy")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> getAllomanticPower(context, MetalTagEnum.BENDALLOY, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("electrum")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> getAllomanticPower(context, MetalTagEnum.ELECTRUM, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("gold")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> getAllomanticPower(context, MetalTagEnum.GOLD, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("atium")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> getAllomanticPower(context, MetalTagEnum.ATIUM, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("malatium")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> getAllomanticPower(context, MetalTagEnum.MALATIUM, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("lerasium")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> getAllomanticPower(context, MetalTagEnum.LERASIUM, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("ettmetal")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> getAllomanticPower(context, MetalTagEnum.ETTMETAL, EntityArgument.getPlayer(context, "target"))))
                                )
                        )
                        .then(Commands.literal("feruchemic")
                                .then(Commands.literal("all")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> getAllPower(context,"feruchemic", EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("steel")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> getFeruchemicPower(context, MetalTagEnum.STEEL, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("iron")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> getFeruchemicPower(context, MetalTagEnum.IRON, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("tin")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> getFeruchemicPower(context, MetalTagEnum.TIN, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("pewter")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> getFeruchemicPower(context, MetalTagEnum.PEWTER, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("copper")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> getFeruchemicPower(context, MetalTagEnum.COPPER, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("bronze")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> getFeruchemicPower(context, MetalTagEnum.BRONZE, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("zinc")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> getFeruchemicPower(context, MetalTagEnum.ZINC, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("brass")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> getFeruchemicPower(context, MetalTagEnum.BRASS, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("chromium")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> getFeruchemicPower(context, MetalTagEnum.CHROMIUM, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("nicrosil")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> getFeruchemicPower(context, MetalTagEnum.NICROSIL, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("aluminum")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> getFeruchemicPower(context, MetalTagEnum.ALUMINUM, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("duralumin")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> getFeruchemicPower(context, MetalTagEnum.DURALUMIN, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("cadmium")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> getFeruchemicPower(context, MetalTagEnum.CADMIUM, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("bendalloy")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> getFeruchemicPower(context, MetalTagEnum.BENDALLOY, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("electrum")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> getFeruchemicPower(context, MetalTagEnum.ELECTRUM, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("gold")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> getFeruchemicPower(context, MetalTagEnum.GOLD, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("atium")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> getFeruchemicPower(context, MetalTagEnum.ATIUM, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("malatium")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> getFeruchemicPower(context, MetalTagEnum.MALATIUM, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("lerasium")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> getFeruchemicPower(context, MetalTagEnum.LERASIUM, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("ettmetal")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> getFeruchemicPower(context, MetalTagEnum.ETTMETAL, EntityArgument.getPlayer(context, "target"))))
                                )
                        )
                )
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

    private static int getLargeVial(CommandContext<CommandSourceStack> context, ServerPlayer target) {
        ItemStack vial = new ItemStack(ModItemsRegister.LARGE_VIAL.get());
        CompoundTag nbt = Vial.addFullReserveVialTags();
        nbt.putInt("CustomModelData", 1);
        vial.setTag(nbt);
        target.getInventory().add(vial);
        target.sendSystemMessage(Component.translatable("Added 1 Vial with all metals to " + target.getScoreboardName()));
        return  1;
    }

    // Metodo para adquirir por comando mentes de metal
    private static int getMetalmind(CommandContext<CommandSourceStack> context, ServerPlayer target, String band) {
        ItemStack metalmind;
        CompoundTag nbt = new CompoundTag();
        if (band.equals("band_steel_iron")) {

            metalmind = new ItemStack(MetalMindEnum.STEEL_IRON.getBand());
            nbt = BandMindAbstract.addBandTagsFull(MetalTagEnum.IRON, MetalTagEnum.STEEL);

        }else if(band.equals("band_zinc_brass")) {

            metalmind = new ItemStack(MetalMindEnum.ZINC_BRASS.getBand());
            nbt = BandMindAbstract.addBandTagsFull(MetalTagEnum.ZINC, MetalTagEnum.BRASS);

        }else if(band.equals("band_aluminium_duralumin")) {

            metalmind = new ItemStack(MetalMindEnum.ALUMINUM_DURALUMIN.getBand());
            nbt = BandMindAbstract.addBandTagsFull(MetalTagEnum.ALUMINUM, MetalTagEnum.DURALUMIN);

        }else if(band.equals("band_atium_malatium")) {

            metalmind = new ItemStack(MetalMindEnum.ATIUM_MALTIUM.getBand());
            nbt = BandMindAbstract.addBandTagsFull(MetalTagEnum.ATIUM, MetalTagEnum.MALATIUM);

        }else if(band.equals("band_cadmium_bendalloy")) {

            metalmind = new ItemStack(MetalMindEnum.CADMIUM_BENDALLOY.getBand());
            nbt = BandMindAbstract.addBandTagsFull(MetalTagEnum.CADMIUM, MetalTagEnum.BENDALLOY);

        }else if(band.equals("band_chromium_nicrosil")) {

            metalmind = new ItemStack(MetalMindEnum.CHROMIUM_NICROSIL.getBand());
            nbt = BandMindAbstract.addBandTagsFull(MetalTagEnum.CHROMIUM, MetalTagEnum.NICROSIL);

        }else if(band.equals("band_copper_bronze")) {

            metalmind = new ItemStack(MetalMindEnum.COPPER_BRONZE.getBand());
            nbt = BandMindAbstract.addBandTagsFull(MetalTagEnum.COPPER, MetalTagEnum.BRONZE);

        }else if(band.equals("band_electrum_gold")) {

            metalmind = new ItemStack(MetalMindEnum.ELECTRUM_GOLD.getBand());
            nbt = BandMindAbstract.addBandTagsFull(MetalTagEnum.GOLD, MetalTagEnum.ELECTRUM);

        }else if(band.equals("band_lerasium_ettmetal")) {

            metalmind = new ItemStack(MetalMindEnum.LERASIUM_ETTMETAL.getBand());
            nbt = BandMindAbstract.addBandTagsFull(MetalTagEnum.LERASIUM, MetalTagEnum.ETTMETAL);

        }else if(band.equals("band_pewter_tin")) {

            metalmind = new ItemStack(MetalMindEnum.TIN_PEWTER.getBand());
            nbt = BandMindAbstract.addBandTagsFull(MetalTagEnum.TIN, MetalTagEnum.PEWTER);

        }
        else{

            metalmind = new ItemStack(MetalMindEnum.TIN_PEWTER.getBand());
            nbt = BandMindAbstract.addBandTagsFull(MetalTagEnum.TIN, MetalTagEnum.PEWTER);

        }
        metalmind.setTag(nbt);
        target.getInventory().add(metalmind);
        target.sendSystemMessage(Component.translatable("Added 1 " + band + " to " + target.getScoreboardName()));

        return  1;
    }

    public static int addAllomanticPower (CommandContext<CommandSourceStack> context, MetalTagEnum metalTagEnum, ServerPlayer player){

        player.getCapability(ModBlocksRegister.InvestedCapabilityRegister.PLAYER_CAP).ifPresent(p ->{
            p.addAllomanticPower(metalTagEnum);
                }
        );
        ModNetwork.sync(player);
        player.sendSystemMessage(Component.translatable("Added " + metalTagEnum.getNameLower() + " allomantic power to " + player.getScoreboardName()));

        return 1;
    }
    public static int addFeruchemicPower (CommandContext<CommandSourceStack> context, MetalTagEnum metalTagEnum, ServerPlayer player){

        player.getCapability(ModBlocksRegister.InvestedCapabilityRegister.PLAYER_CAP).ifPresent(p ->{
                    p.addFeruchemicPower(metalTagEnum);
                }
        );
        ModNetwork.sync(player);
        player.sendSystemMessage(Component.translatable("Added " + metalTagEnum.getNameLower() + " feruchemic power to " + player.getScoreboardName()));

        return 1;
    }
    public static int addAllAllomanticPower (CommandContext<CommandSourceStack> context, ServerPlayer player){
        ServerPlayer playerEntity = null;

            player.getCapability(ModBlocksRegister.InvestedCapabilityRegister.PLAYER_CAP).ifPresent(p ->{
                        p.addAllAllomantic();
                    }
            );
            ModNetwork.sync(player);
            player.sendSystemMessage(Component.translatable("Added all allomantics powers to " + player.getScoreboardName()));

        return 1;
    }
    public static int addAllFeruchemicPower (CommandContext<CommandSourceStack> context, ServerPlayer player){

        player.getCapability(ModBlocksRegister.InvestedCapabilityRegister.PLAYER_CAP).ifPresent(p ->{
                    p.addAllFeruchemic();
                }
        );
        ModNetwork.sync(player);
        player.sendSystemMessage(Component.translatable("Added all feruchemics powers to " + player.getScoreboardName()));


        return 1;
    }
    public static int addAllPower(CommandContext<CommandSourceStack> context, ServerPlayer player){

        player.getCapability(ModBlocksRegister.InvestedCapabilityRegister.PLAYER_CAP).ifPresent(p ->{
                    p.addAllAllomantic();
                    p.addAllFeruchemic();
                }
        );
        ModNetwork.sync(player);
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
        ModNetwork.sync(player);
        return 1;
    }

    public static int removeAllomanticPower (CommandContext<CommandSourceStack> context, MetalTagEnum metalTagEnum, ServerPlayer player){

        player.getCapability(ModBlocksRegister.InvestedCapabilityRegister.PLAYER_CAP).ifPresent(p ->{
                    p.removeAllomanticPower(metalTagEnum);
                });

        ModNetwork.sync(player);
        player.sendSystemMessage(Component.translatable("Revoke " + metalTagEnum.getNameLower() + " allomantic power to " + player.getScoreboardName()));

        return 1;
    }
    public static int removeFeruchemicPower (CommandContext<CommandSourceStack> context, MetalTagEnum metalTagEnum, ServerPlayer player){

        player.getCapability(ModBlocksRegister.InvestedCapabilityRegister.PLAYER_CAP).ifPresent(p ->{
                    p.removeFeruchemicPower(metalTagEnum);
                }
        );
        ModNetwork.sync(player);
        player.sendSystemMessage(Component.translatable("Revoke " + metalTagEnum.getNameLower() + " feruchemic power to " + player.getScoreboardName()));

        return 1;
    }
    public static int removeAllAllomanticPower (CommandContext<CommandSourceStack> context, ServerPlayer player){

        player.getCapability(ModBlocksRegister.InvestedCapabilityRegister.PLAYER_CAP).ifPresent(p ->{
                    p.removeAllAllomanticPower();
                }
        );
        ModNetwork.sync(player);
        player.sendSystemMessage(Component.translatable("Revoke all allomantics powers to " + player.getScoreboardName()));


        return 1;
    }
    public static int removeAllFeruchemicPower (CommandContext<CommandSourceStack> context, ServerPlayer player){

        player.getCapability(ModBlocksRegister.InvestedCapabilityRegister.PLAYER_CAP).ifPresent(p ->{
                    p.removeAllFeruchemicPower();
                }
        );
        ModNetwork.sync(player);
        player.sendSystemMessage(Component.translatable("Revoke all feruchemics powers to " + player.getScoreboardName()));

        return 1;
    }
    public static int removeAllPower(CommandContext<CommandSourceStack> context, ServerPlayer player){

        player.getCapability(ModBlocksRegister.InvestedCapabilityRegister.PLAYER_CAP).ifPresent(p ->{
                    p.removeAllAllomanticPower();
                    p.removeAllFeruchemicPower();
                }
        );
        ModNetwork.sync(player);
        player.sendSystemMessage(Component.translatable("Revoke all powers to " + player.getScoreboardName()));

        return 1;
    }
    public static int getAllomanticPower (CommandContext<CommandSourceStack> context, MetalTagEnum metalTagEnum, ServerPlayer player){

        player.getCapability(ModBlocksRegister.InvestedCapabilityRegister.PLAYER_CAP).ifPresent(cap -> sendSimpleMessage(player, cap.hasAllomanticPower(metalTagEnum), metalTagEnum, "allomantic"));

        return 1;
    }
    public static int getFeruchemicPower (CommandContext<CommandSourceStack> context, MetalTagEnum metalTagEnum, ServerPlayer player){

            player.getCapability(ModBlocksRegister.InvestedCapabilityRegister.PLAYER_CAP).ifPresent(cap -> sendSimpleMessage(player, cap.hasFeruchemicPower(metalTagEnum), metalTagEnum, "feruchemic"));

        return 1;
    }
    public static int getAllPower (CommandContext<CommandSourceStack> context, String type, ServerPlayer player){

        if(type.equals("allomantic")){
            player.getCapability(ModBlocksRegister.InvestedCapabilityRegister.PLAYER_CAP).ifPresent(cap -> sendAllMessage(player, cap.getAllomanticPowers(), type));
        }else if(type.equals("feruchemic")){
            player.getCapability(ModBlocksRegister.InvestedCapabilityRegister.PLAYER_CAP).ifPresent(cap -> sendAllMessage(player, cap.getFeruchemicPowers(), type));
        }else{
            player.getCapability(ModBlocksRegister.InvestedCapabilityRegister.PLAYER_CAP).ifPresent(cap -> sendAllMessage(player, cap.getFeruchemicPowers(), type));
            player.getCapability(ModBlocksRegister.InvestedCapabilityRegister.PLAYER_CAP).ifPresent(cap -> sendAllMessage(player, cap.getAllomanticPowers(), type));
        }


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
