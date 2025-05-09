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
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.enums.implementations.custom_items.MetalMindEnum;
import net.rudahee.metallics_arts.modules.custom_items.metal_minds.abstracts.MetalmindAbstract;

import java.util.Collection;
import java.util.List;

public class RingsCommandRegister {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {


        dispatcher.register(Commands.literal("ma-items").requires(commandSource -> commandSource.hasPermission(2))
                .then(Commands.literal("give")
                        .then(Commands.literal("ring")
                                .then(Commands.literal("iron_steel")
                                        .executes(context -> getMetalmind(context, List.of(context.getSource().getPlayerOrException()), "ring_iron_steel"))
                                        .then(Commands.argument("target", EntityArgument.players())
                                                .executes(context -> getMetalmind(context, EntityArgument.getPlayers(context, "target"), "ring_iron_steel"))))

                                .then(Commands.literal("zinc_brass")
                                        .executes(context -> getMetalmind(context, List.of(context.getSource().getPlayerOrException()), "ring_zinc_brass"))
                                        .then(Commands.argument("target", EntityArgument.players())
                                                .executes(context -> getMetalmind(context, EntityArgument.getPlayers(context, "target"), "ring_zinc_brass"))))

                                .then(Commands.literal("aluminium_duralumin")
                                        .executes(context -> getMetalmind(context, List.of(context.getSource().getPlayerOrException()), "ring_aluminium_duralumin"))
                                        .then(Commands.argument("target", EntityArgument.players())
                                                .executes(context -> getMetalmind(context, EntityArgument.getPlayers(context, "target"), "ring_aluminium_duralumin"))))

                                .then(Commands.literal("atium_malatium")
                                        .executes(context -> getMetalmind(context, List.of(context.getSource().getPlayerOrException()), "ring_atium_malatium"))
                                        .then(Commands.argument("target", EntityArgument.players())
                                                .executes(context -> getMetalmind(context, EntityArgument.getPlayers(context, "target"), "ring_atium_malatium"))))

                                .then(Commands.literal("cadmium_bendalloy")
                                        .executes(context -> getMetalmind(context, List.of(context.getSource().getPlayerOrException()), "ring_cadmium_bendalloy"))
                                        .then(Commands.argument("target", EntityArgument.players())
                                                .executes(context -> getMetalmind(context, EntityArgument.getPlayers(context, "target"), "ring_cadmium_bendalloy"))))

                                .then(Commands.literal("chromium_nicrosil")
                                        .executes(context -> getMetalmind(context, List.of(context.getSource().getPlayerOrException()), "ring_chromium_nicrosil"))
                                        .then(Commands.argument("target", EntityArgument.players())
                                                .executes(context -> getMetalmind(context, EntityArgument.getPlayers(context, "target"), "ring_chromium_nicrosil"))))

                                .then(Commands.literal("copper_bronze")
                                        .executes(context -> getMetalmind(context, List.of(context.getSource().getPlayerOrException()), "ring_copper_bronze"))
                                        .then(Commands.argument("target", EntityArgument.players())
                                                .executes(context -> getMetalmind(context, EntityArgument.getPlayers(context, "target"), "ring_copper_bronze"))))

                                .then(Commands.literal("gold_electrum")
                                        .executes(context -> getMetalmind(context, List.of(context.getSource().getPlayerOrException()), "ring_gold_electrum"))
                                        .then(Commands.argument("target", EntityArgument.players())
                                                .executes(context -> getMetalmind(context, EntityArgument.getPlayers(context, "target"), "ring_gold_electrum"))))

                                .then(Commands.literal("lerasium_ettmetal")
                                        .executes(context -> getMetalmind(context, List.of(context.getSource().getPlayerOrException()), "ring_lerasium_ettmetal"))
                                        .then(Commands.argument("target", EntityArgument.players())
                                                .executes(context -> getMetalmind(context, EntityArgument.getPlayers(context, "target"), "ring_lerasium_ettmetal"))))

                                .then(Commands.literal("tin_pewter")
                                        .executes(context -> getMetalmind(context, List.of(context.getSource().getPlayerOrException()), "ring_tin_pewter"))
                                        .then(Commands.argument("target", EntityArgument.players())
                                                .executes(context -> getMetalmind(context, EntityArgument.getPlayers(context, "target"), "ring_tin_pewter")))))));


    }

    private static int getMetalmind(CommandContext<CommandSourceStack> context, Collection<ServerPlayer> targetsPlayers, String band) {
        ItemStack metalmind;
        CompoundTag nbt;

        StringBuilder playersName = new StringBuilder();
        boolean firstLoop = true;


        for(ServerPlayer player: targetsPlayers) {

            if (firstLoop) {
                firstLoop = false;
                playersName.append(player.getScoreboardName());
            } else {
                playersName.append(", ").append(player.getScoreboardName());
            }

            if (band.equals("ring_iron_steel")) {

                metalmind = new ItemStack(MetalMindEnum.IRON_STEEL.getRing());
                nbt = MetalmindAbstract.addBandTagsFull(MetalTagEnum.IRON, MetalTagEnum.STEEL);

            } else if (band.equals("ring_zinc_brass")) {
                metalmind = new ItemStack(MetalMindEnum.ZINC_BRASS.getRing());
                nbt = MetalmindAbstract.addBandTagsFull(MetalTagEnum.ZINC, MetalTagEnum.BRASS);

            } else if (band.equals("ring_aluminium_duralumin")) {

                metalmind = new ItemStack(MetalMindEnum.ALUMINUM_DURALUMIN.getRing());
                nbt = MetalmindAbstract.addBandTagsFull(MetalTagEnum.ALUMINUM, MetalTagEnum.DURALUMIN);

            } else if (band.equals("ring_atium_malatium")) {

                metalmind = new ItemStack(MetalMindEnum.ATIUM_MALTIUM.getRing());
                nbt = MetalmindAbstract.addBandTagsFull(MetalTagEnum.ATIUM, MetalTagEnum.MALATIUM);

            } else if (band.equals("ring_cadmium_bendalloy")) {

                metalmind = new ItemStack(MetalMindEnum.CADMIUM_BENDALLOY.getRing());
                nbt = MetalmindAbstract.addBandTagsFull(MetalTagEnum.CADMIUM, MetalTagEnum.BENDALLOY);

            } else if (band.equals("ring_chromium_nicrosil")) {

                metalmind = new ItemStack(MetalMindEnum.CHROMIUM_NICROSIL.getRing());
                nbt = MetalmindAbstract.addBandTagsFull(MetalTagEnum.CHROMIUM, MetalTagEnum.NICROSIL);

            } else if (band.equals("ring_copper_bronze")) {

                metalmind = new ItemStack(MetalMindEnum.COPPER_BRONZE.getRing());
                nbt = MetalmindAbstract.addBandTagsFull(MetalTagEnum.COPPER, MetalTagEnum.BRONZE);

            } else if (band.equals("ring_gold_electrum")) {

                metalmind = new ItemStack(MetalMindEnum.GOLD_ELECTRUM.getRing());
                nbt = MetalmindAbstract.addBandTagsFull(MetalTagEnum.GOLD, MetalTagEnum.ELECTRUM);

            } else if (band.equals("ring_lerasium_ettmetal")) {

                metalmind = new ItemStack(MetalMindEnum.LERASIUM_ETTMETAL.getRing());
                nbt = MetalmindAbstract.addBandTagsFull(MetalTagEnum.LERASIUM, MetalTagEnum.ETTMETAL);

            } else if (band.equals("ring_tin_pewter")) {

                metalmind = new ItemStack(MetalMindEnum.TIN_PEWTER.getRing());
                nbt = MetalmindAbstract.addBandTagsFull(MetalTagEnum.TIN, MetalTagEnum.PEWTER);

            } else {

                metalmind = new ItemStack(MetalMindEnum.TIN_PEWTER.getRing());
                nbt = MetalmindAbstract.addRingTagsFull(MetalTagEnum.TIN, MetalTagEnum.PEWTER);

            }
            metalmind.setTag(nbt);
            player.getInventory().add(metalmind);
        }
        context.getSource().sendSystemMessage(Component.translatable("Added 1 Metalmind to: " + playersName));

        return  1;
    }
}
