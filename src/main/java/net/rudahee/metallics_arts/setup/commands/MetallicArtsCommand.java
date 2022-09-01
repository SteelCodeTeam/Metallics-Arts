package net.rudahee.metallics_arts.setup.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.rudahee.metallics_arts.modules.data_player.InvestedCapability;
import net.rudahee.metallics_arts.modules.items.vials.Vial;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;
import net.rudahee.metallics_arts.setup.network.ModNetwork;
import net.rudahee.metallics_arts.setup.registries.ModItems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MetallicArtsCommand {

    private static final DynamicCommandExceptionType ERROR_CANT_ADD = new DynamicCommandExceptionType(s -> new TranslationTextComponent("commands.metallic_arts.err_add", s));
    private static final DynamicCommandExceptionType ERROR_CANT_REMOVE = new DynamicCommandExceptionType(s -> new TranslationTextComponent("commands.metallic_arts.err_remove", s));

    private static Predicate<CommandSource> permissions(int level) {
        return (player) -> player.hasPermission(level);
    }

    public static void register(CommandDispatcher<CommandSource> dispatcher) {

        dispatcher.register(Commands.literal("ma-items").requires(commandSource -> commandSource.hasPermission(2))
                .then(Commands.literal("get")
                        .then(Commands.literal("large_vial")
                                .then(Commands.argument("target", EntityArgument.player())
                                        .executes(context -> getLargeVial(context, EntityArgument.getPlayer(context, "target")))))));

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
                                                .executes(context -> getAllomanticPower(context,MetalsNBTData.STEEL, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("iron")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> getAllomanticPower(context,MetalsNBTData.IRON, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("tin")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> getAllomanticPower(context,MetalsNBTData.TIN, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("pewter")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> getAllomanticPower(context,MetalsNBTData.PEWTER, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("copper")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> getAllomanticPower(context,MetalsNBTData.COPPER, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("bronze")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> getAllomanticPower(context,MetalsNBTData.BRONZE, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("zinc")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> getAllomanticPower(context,MetalsNBTData.ZINC, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("brass")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> getAllomanticPower(context,MetalsNBTData.BRASS, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("chromium")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> getAllomanticPower(context,MetalsNBTData.CHROMIUM, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("nicrosil")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> getAllomanticPower(context,MetalsNBTData.NICROSIL, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("aluminum")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> getAllomanticPower(context,MetalsNBTData.ALUMINUM, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("duralumin")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> getAllomanticPower(context,MetalsNBTData.DURALUMIN, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("cadmium")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> getAllomanticPower(context,MetalsNBTData.CADMIUM, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("bendalloy")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> getAllomanticPower(context,MetalsNBTData.BENDALLOY, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("electrum")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> getAllomanticPower(context,MetalsNBTData.ELECTRUM, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("gold")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> getAllomanticPower(context,MetalsNBTData.GOLD, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("atium")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> getAllomanticPower(context,MetalsNBTData.ATIUM, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("malatium")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> getAllomanticPower(context,MetalsNBTData.MALATIUM, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("lerasium")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> getAllomanticPower(context,MetalsNBTData.LERASIUM, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("ettmetal")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> getAllomanticPower(context,MetalsNBTData.ETTMETAL, EntityArgument.getPlayer(context, "target"))))
                                )
                        )
                        .then(Commands.literal("feruchemic")
                                .then(Commands.literal("all")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> getAllPower(context,"feruchemic", EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("steel")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> getFeruchemicPower(context,MetalsNBTData.STEEL, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("iron")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> getFeruchemicPower(context,MetalsNBTData.IRON, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("tin")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> getFeruchemicPower(context,MetalsNBTData.TIN, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("pewter")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> getFeruchemicPower(context,MetalsNBTData.PEWTER, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("copper")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> getFeruchemicPower(context,MetalsNBTData.COPPER, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("bronze")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> getFeruchemicPower(context,MetalsNBTData.BRONZE, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("zinc")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> getFeruchemicPower(context,MetalsNBTData.ZINC, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("brass")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> getFeruchemicPower(context,MetalsNBTData.BRASS, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("chromium")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> getFeruchemicPower(context,MetalsNBTData.CHROMIUM, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("nicrosil")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> getFeruchemicPower(context,MetalsNBTData.NICROSIL, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("aluminum")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> getFeruchemicPower(context,MetalsNBTData.ALUMINUM, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("duralumin")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> getFeruchemicPower(context,MetalsNBTData.DURALUMIN, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("cadmium")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> getFeruchemicPower(context,MetalsNBTData.CADMIUM, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("bendalloy")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> getFeruchemicPower(context,MetalsNBTData.BENDALLOY, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("electrum")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> getFeruchemicPower(context,MetalsNBTData.ELECTRUM, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("gold")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> getFeruchemicPower(context,MetalsNBTData.GOLD, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("atium")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> getFeruchemicPower(context,MetalsNBTData.ATIUM, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("malatium")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> getFeruchemicPower(context,MetalsNBTData.MALATIUM, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("lerasium")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> getFeruchemicPower(context,MetalsNBTData.LERASIUM, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("ettmetal")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> getFeruchemicPower(context,MetalsNBTData.ETTMETAL, EntityArgument.getPlayer(context, "target"))))
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
                                                .executes(context -> addAllomanticPower(context,MetalsNBTData.STEEL, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("iron")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addAllomanticPower(context,MetalsNBTData.IRON, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("tin")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addAllomanticPower(context,MetalsNBTData.TIN, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("pewter")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addAllomanticPower(context,MetalsNBTData.PEWTER, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("copper")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addAllomanticPower(context,MetalsNBTData.COPPER, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("bronze")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addAllomanticPower(context,MetalsNBTData.BRONZE, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("zinc")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addAllomanticPower(context,MetalsNBTData.ZINC, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("brass")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addAllomanticPower(context,MetalsNBTData.BRASS, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("chromium")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addAllomanticPower(context,MetalsNBTData.CHROMIUM, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("nicrosil")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addAllomanticPower(context,MetalsNBTData.NICROSIL, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("aluminum")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addAllomanticPower(context,MetalsNBTData.ALUMINUM, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("duralumin")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addAllomanticPower(context,MetalsNBTData.DURALUMIN, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("cadmium")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addAllomanticPower(context,MetalsNBTData.CADMIUM, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("bendalloy")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addAllomanticPower(context,MetalsNBTData.BENDALLOY, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("electrum")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addAllomanticPower(context,MetalsNBTData.ELECTRUM, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("gold")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addAllomanticPower(context,MetalsNBTData.GOLD, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("atium")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addAllomanticPower(context,MetalsNBTData.ATIUM, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("malatium")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addAllomanticPower(context,MetalsNBTData.MALATIUM, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("lerasium")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addAllomanticPower(context,MetalsNBTData.LERASIUM, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("ettmetal")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addAllomanticPower(context,MetalsNBTData.ETTMETAL, EntityArgument.getPlayer(context, "target"))))
                                )
                        )
                        .then(Commands.literal("feruchemic")
                                .then(Commands.literal("all")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addAllFeruchemicPower(context, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("steel")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addFeruchemicPower(context,MetalsNBTData.STEEL, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("iron")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addFeruchemicPower(context,MetalsNBTData.IRON, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("tin")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addFeruchemicPower(context,MetalsNBTData.TIN, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("pewter")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addFeruchemicPower(context,MetalsNBTData.PEWTER, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("copper")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addFeruchemicPower(context,MetalsNBTData.COPPER, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("bronze")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addFeruchemicPower(context,MetalsNBTData.BRONZE, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("zinc")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addFeruchemicPower(context,MetalsNBTData.ZINC, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("brass")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addFeruchemicPower(context,MetalsNBTData.BRASS, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("chromium")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addFeruchemicPower(context,MetalsNBTData.CHROMIUM, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("nicrosil")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addFeruchemicPower(context,MetalsNBTData.NICROSIL, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("aluminum")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addFeruchemicPower(context,MetalsNBTData.ALUMINUM, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("duralumin")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addFeruchemicPower(context,MetalsNBTData.DURALUMIN, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("cadmium")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addFeruchemicPower(context,MetalsNBTData.CADMIUM, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("bendalloy")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addFeruchemicPower(context,MetalsNBTData.BENDALLOY, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("electrum")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addFeruchemicPower(context,MetalsNBTData.ELECTRUM, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("gold")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addFeruchemicPower(context,MetalsNBTData.GOLD, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("atium")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addFeruchemicPower(context,MetalsNBTData.ATIUM, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("malatium")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addFeruchemicPower(context,MetalsNBTData.MALATIUM, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("lerasium")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addFeruchemicPower(context,MetalsNBTData.LERASIUM, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("ettmetal")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addFeruchemicPower(context,MetalsNBTData.ETTMETAL, EntityArgument.getPlayer(context, "target"))))
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
                                                .executes(context -> removeAllomanticPower(context,MetalsNBTData.STEEL, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("iron")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeAllomanticPower(context,MetalsNBTData.IRON, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("tin")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeAllomanticPower(context,MetalsNBTData.TIN, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("pewter")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeAllomanticPower(context,MetalsNBTData.PEWTER, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("copper")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeAllomanticPower(context,MetalsNBTData.COPPER, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("bronze")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeAllomanticPower(context,MetalsNBTData.BRONZE, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("zinc")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeAllomanticPower(context,MetalsNBTData.ZINC, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("brass")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeAllomanticPower(context,MetalsNBTData.BRASS, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("chromium")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeAllomanticPower(context,MetalsNBTData.CHROMIUM, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("nicrosil")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeAllomanticPower(context,MetalsNBTData.NICROSIL, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("aluminum")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeAllomanticPower(context,MetalsNBTData.ALUMINUM, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("duralumin")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeAllomanticPower(context,MetalsNBTData.DURALUMIN, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("cadmium")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeAllomanticPower(context,MetalsNBTData.CADMIUM, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("bendalloy")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeAllomanticPower(context,MetalsNBTData.BENDALLOY, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("electrum")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeAllomanticPower(context,MetalsNBTData.ELECTRUM, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("gold")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeAllomanticPower(context,MetalsNBTData.GOLD, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("atium")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeAllomanticPower(context,MetalsNBTData.ATIUM, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("malatium")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeAllomanticPower(context,MetalsNBTData.MALATIUM, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("lerasium")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeAllomanticPower(context,MetalsNBTData.LERASIUM, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("ettmetal")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeAllomanticPower(context,MetalsNBTData.ETTMETAL, EntityArgument.getPlayer(context, "target"))))
                                )
                        )
                        .then(Commands.literal("feruchemic")
                                .then(Commands.literal("all")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeAllFeruchemicPower(context, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("steel")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeFeruchemicPower(context,MetalsNBTData.STEEL, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("iron")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeFeruchemicPower(context,MetalsNBTData.IRON, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("tin")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeFeruchemicPower(context,MetalsNBTData.TIN, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("pewter")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeFeruchemicPower(context,MetalsNBTData.PEWTER, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("copper")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeFeruchemicPower(context,MetalsNBTData.COPPER, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("bronze")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeFeruchemicPower(context,MetalsNBTData.BRONZE, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("zinc")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeFeruchemicPower(context,MetalsNBTData.ZINC, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("brass")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeFeruchemicPower(context,MetalsNBTData.BRASS, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("chromium")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeFeruchemicPower(context,MetalsNBTData.CHROMIUM, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("nicrosil")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeFeruchemicPower(context,MetalsNBTData.NICROSIL, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("aluminum")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeFeruchemicPower(context,MetalsNBTData.ALUMINUM, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("duralumin")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeFeruchemicPower(context,MetalsNBTData.DURALUMIN, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("cadmium")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeFeruchemicPower(context,MetalsNBTData.CADMIUM, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("bendalloy")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeFeruchemicPower(context,MetalsNBTData.BENDALLOY, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("electrum")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeFeruchemicPower(context,MetalsNBTData.ELECTRUM, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("gold")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeFeruchemicPower(context,MetalsNBTData.GOLD, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("atium")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeFeruchemicPower(context,MetalsNBTData.ATIUM, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("malatium")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeFeruchemicPower(context,MetalsNBTData.MALATIUM, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("lerasium")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeFeruchemicPower(context,MetalsNBTData.LERASIUM, EntityArgument.getPlayer(context, "target"))))
                                )
                                .then(Commands.literal("ettmetal")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeFeruchemicPower(context,MetalsNBTData.ETTMETAL, EntityArgument.getPlayer(context, "target"))))
                                )
                        )
                )
        );

    }

    private static int getLargeVial(CommandContext<CommandSource> context, ServerPlayerEntity target) {
        ItemStack vial = new ItemStack(ModItems.LARGE_VIAL.get());
        vial.setTag(Vial.addFullReserveVialTags());
        CompoundNBT nbt = new CompoundNBT();
        nbt.putInt("CustomModelData", 1);
        vial.setTag(nbt);

        target.inventory.add(vial);
        target.sendMessage(new StringTextComponent("Added 1 Vial with all metals to " + target.getScoreboardName()), target.getUUID());
        return  1;
    }

    public static int addAllomanticPower (CommandContext<CommandSource> context,MetalsNBTData metalsNBTData, ServerPlayerEntity player){

        player.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(p ->{
            p.addAllomanticPower(metalsNBTData);
                }
        );
        ModNetwork.sync(player);
        player.sendMessage(new StringTextComponent("Added " + metalsNBTData.getNameLower() + " allomantic power to " + player.getScoreboardName()),player.getUUID());

        return 1;
    }
    public static int addFeruchemicPower (CommandContext<CommandSource> context,MetalsNBTData metalsNBTData, ServerPlayerEntity player){

        player.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(p ->{
                    p.addFeruchemicPower(metalsNBTData);
                }
        );
        ModNetwork.sync(player);
        player.sendMessage(new StringTextComponent("Added " + metalsNBTData.getNameLower() + " feruchemic power to " + player.getScoreboardName()), player.getUUID());

        return 1;
    }
    public static int addAllAllomanticPower (CommandContext<CommandSource> context, ServerPlayerEntity player){
        ServerPlayerEntity playerEntity = null;

            player.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(p ->{
                        p.addAllAllomantic();
                    }
            );
            ModNetwork.sync(player);
            player.sendMessage(new StringTextComponent("Added all allomantics powers to " + player.getScoreboardName()),player.getUUID());

        return 1;
    }
    public static int addAllFeruchemicPower (CommandContext<CommandSource> context, ServerPlayerEntity player){

        player.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(p ->{
                    p.addAllFeruchemic();
                }
        );
        ModNetwork.sync(player);
        player.sendMessage(new StringTextComponent("Added all feruchemics powers to " + player.getScoreboardName()),player.getUUID());


        return 1;
    }
    public static int addAllPower(CommandContext<CommandSource> context, ServerPlayerEntity player){

        player.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(p ->{
                    p.addAllAllomantic();
                    p.addAllFeruchemic();
                }
        );
        ModNetwork.sync(player);
        player.sendMessage(new StringTextComponent("Added all powers to " + player.getScoreboardName()),player.getUUID());


        return 1;
    }
    public static int addRandomPower(CommandContext<CommandSource> context, ServerPlayerEntity player){

        Random random = new Random();
        MetalsNBTData metal;
        if (player != null){
            metal = MetalsNBTData.values()[random.nextInt(MetalsNBTData.values().length)];
            if (Math.random()>0.5){
                addFeruchemicPower(context,metal, player);
            }else{
                addAllomanticPower(context,metal, player);
            }
        }
        ModNetwork.sync(player);
        return 1;
    }

    public static int removeAllomanticPower (CommandContext<CommandSource> context,MetalsNBTData metalsNBTData, ServerPlayerEntity player){

        player.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(p ->{
                    p.removeAllomanticPower(metalsNBTData);
                });

        ModNetwork.sync(player);
        player.sendMessage(new StringTextComponent("Revoke " + metalsNBTData.getNameLower() + " allomantic power to " + player.getScoreboardName()),player.getUUID());

        return 1;
    }
    public static int removeFeruchemicPower (CommandContext<CommandSource> context,MetalsNBTData metalsNBTData, ServerPlayerEntity player){

        player.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(p ->{
                    p.removeFeruchemicPower(metalsNBTData);
                }
        );
        ModNetwork.sync(player);
        player.sendMessage(new StringTextComponent("Revoke " + metalsNBTData.getNameLower() + " feruchemic power to " + player.getScoreboardName()),player.getUUID());

        return 1;
    }
    public static int removeAllAllomanticPower (CommandContext<CommandSource> context, ServerPlayerEntity player){

        player.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(p ->{
                    p.removeAllAllomanticPower();
                }
        );
        ModNetwork.sync(player);
        player.sendMessage(new StringTextComponent("Revoke all allomantics powers to " + player.getScoreboardName()),player.getUUID());


        return 1;
    }
    public static int removeAllFeruchemicPower (CommandContext<CommandSource> context, ServerPlayerEntity player){

        player.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(p ->{
                    p.removeAllFeruchemicPower();
                }
        );
        ModNetwork.sync(player);
        player.sendMessage(new StringTextComponent("Revoke all feruchemics powers to " + player.getScoreboardName()),player.getUUID());

        return 1;
    }
    public static int removeAllPower(CommandContext<CommandSource> context, ServerPlayerEntity player){

        player.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(p ->{
                    p.removeAllAllomanticPower();
                    p.removeAllFeruchemicPower();
                }
        );
        ModNetwork.sync(player);
        player.sendMessage(new StringTextComponent("Revoke all powers to " + player.getScoreboardName()),player.getUUID());

        return 1;
    }
    public static int getAllomanticPower (CommandContext<CommandSource> context, MetalsNBTData metalsNBTData, ServerPlayerEntity player){

        player.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(cap -> sendSimpleMessage(player, cap.hasAllomanticPower(metalsNBTData), metalsNBTData, "allomantic"));

        return 1;
    }
    public static int getFeruchemicPower (CommandContext<CommandSource> context,MetalsNBTData metalsNBTData, ServerPlayerEntity player){

            player.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(cap -> sendSimpleMessage(player, cap.hasFeruchemicPower(metalsNBTData), metalsNBTData, "feruchemic"));

        return 1;
    }
    public static int getAllPower (CommandContext<CommandSource> context, String type, ServerPlayerEntity player){

        if(type.equals("allomantic")){
            player.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(cap -> sendAllMessage(player, cap.getAllomanticPowers(), type));
        }else if(type.equals("feruchemic")){
            player.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(cap -> sendAllMessage(player, cap.getFeruchemicPowers(), type));
        }else{
            player.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(cap -> sendAllMessage(player, cap.getFeruchemicPowers(), type));
            player.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(cap -> sendAllMessage(player, cap.getAllomanticPowers(), type));
        }


        return 1;
    }
    private static void sendAllMessage(ServerPlayerEntity finalPlayerEntity, ArrayList<MetalsNBTData> result, String type) {

        ArrayList<MetalsNBTData> allMetals = new ArrayList<>(Arrays.asList(MetalsNBTData.values()));


        if (type.equals("all") || type.equals("allomantic")) {

            StringBuilder allAlomanticMetalsHave = new StringBuilder();

            for (MetalsNBTData metal: result) {
                allAlomanticMetalsHave.append(metal.getNameLower() + " ");
            }

            StringBuilder allAllomanticMetalsDontHave = new StringBuilder();

            ArrayList<MetalsNBTData> resultAllomanticDontHave = new ArrayList<>(allMetals.stream().filter(f-> !result.contains(f)).collect(Collectors.toList()));

            for (MetalsNBTData metal: resultAllomanticDontHave) {
                allAllomanticMetalsDontHave.append(metal.getNameLower() + " ");
            }

            finalPlayerEntity.sendMessage(new StringTextComponent("The player have those allomantic powers: " + allAlomanticMetalsHave),finalPlayerEntity.getUUID());
            finalPlayerEntity.sendMessage(new StringTextComponent("The player dont have those feruchemic powers: " + allAllomanticMetalsDontHave),finalPlayerEntity.getUUID());

        } else if (type.equals("all") || type.equals("feruchemic")) {
            StringBuilder allFeruchemicMetalsHave = new StringBuilder();

            for (MetalsNBTData metal: result) {
                allFeruchemicMetalsHave.append(metal.getNameLower() + " ");
            }

            StringBuilder allFeruchemicMetalsDontHave = new StringBuilder();

            ArrayList<MetalsNBTData> resultFeruchemicDontHave = new ArrayList<>(allMetals.stream().filter(f-> !result.contains(f)).collect(Collectors.toList()));

            for (MetalsNBTData metal: resultFeruchemicDontHave) {
                allFeruchemicMetalsDontHave.append(metal.getNameLower() + " ");
            }

            finalPlayerEntity.sendMessage(new StringTextComponent("The player have those feruchemic powers: " + allFeruchemicMetalsHave),finalPlayerEntity.getUUID());
            finalPlayerEntity.sendMessage(new StringTextComponent("The player dont have those feruchemic powers: " + allFeruchemicMetalsDontHave),finalPlayerEntity.getUUID());

        }
    }
    private static void sendSimpleMessage(ServerPlayerEntity finalPlayerEntity, boolean result, MetalsNBTData metal, String type) {

        String have = (result) ?  "have " : "have not ";


        ITextComponent text = new StringTextComponent("The player " + have + type + " " + metal.getNameLower() + " power");

        finalPlayerEntity.sendMessage(text, finalPlayerEntity.getUUID());
    }
}
