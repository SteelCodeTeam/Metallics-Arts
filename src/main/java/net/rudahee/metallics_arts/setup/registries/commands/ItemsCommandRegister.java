package net.rudahee.metallics_arts.setup.registries.commands;

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
import net.rudahee.metallics_arts.data.enums.implementations.custom_items.SpikeEnum;
import net.rudahee.metallics_arts.modules.custom_items.metal_minds.abstracts.MetalmindAbstract;
import net.rudahee.metallics_arts.modules.custom_items.metal_spikes.MetalSpike;
import net.rudahee.metallics_arts.modules.custom_items.vials.Vial;
import net.rudahee.metallics_arts.setup.registries.ModItemsRegister;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class ItemsCommandRegister {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {

        dispatcher.register(Commands.literal("ma-items").requires(commandSource -> commandSource.hasPermission(2))
                .then(Commands.literal("give")
                        .then(Commands.literal("vial_large")
                                .executes(context -> getLargeVial(context, List.of(context.getSource().getPlayerOrException())))
                                .then(Commands.argument("target", EntityArgument.players())
                                        .executes(context -> getLargeVial(context, EntityArgument.getPlayers(context, "target")))))

                        .then(Commands.literal("band_iron_steel")
                                .executes(context -> getMetalmind(context, List.of(context.getSource().getPlayerOrException()), "band_iron_steel"))
                                .then(Commands.argument("target", EntityArgument.players())
                                        .executes(context -> getMetalmind(context, EntityArgument.getPlayers(context, "target"), "band_iron_steel"))))

                        .then(Commands.literal("band_zinc_brass")
                                .executes(context -> getMetalmind(context, List.of(context.getSource().getPlayerOrException()), "band_zinc_brass"))
                                .then(Commands.argument("target", EntityArgument.players())
                                        .executes(context -> getMetalmind(context, EntityArgument.getPlayers(context, "target"), "band_zinc_brass"))))

                        .then(Commands.literal("band_aluminium_duralumin")
                                .executes(context -> getMetalmind(context, List.of(context.getSource().getPlayerOrException()), "band_aluminium_duralumin"))
                                .then(Commands.argument("target", EntityArgument.players())
                                        .executes(context -> getMetalmind(context, EntityArgument.getPlayers(context, "target"), "band_aluminium_duralumin"))))

                        .then(Commands.literal("band_atium_malatium")
                                .executes(context -> getMetalmind(context, List.of(context.getSource().getPlayerOrException()), "band_atium_malatium"))
                                .then(Commands.argument("target", EntityArgument.players())
                                        .executes(context -> getMetalmind(context, EntityArgument.getPlayers(context, "target"), "band_atium_malatium"))))

                        .then(Commands.literal("band_cadmium_bendalloy")
                                .executes(context -> getMetalmind(context, List.of(context.getSource().getPlayerOrException()), "band_cadmium_bendalloy"))
                                .then(Commands.argument("target", EntityArgument.players())
                                        .executes(context -> getMetalmind(context, EntityArgument.getPlayers(context, "target"), "band_cadmium_bendalloy"))))

                        .then(Commands.literal("band_chromium_nicrosil")
                                .executes(context -> getMetalmind(context, List.of(context.getSource().getPlayerOrException()), "band_chromium_nicrosil"))
                                .then(Commands.argument("target", EntityArgument.players())
                                        .executes(context -> getMetalmind(context, EntityArgument.getPlayers(context, "target"), "band_chromium_nicrosil"))))

                        .then(Commands.literal("band_copper_bronze")
                                .executes(context -> getMetalmind(context, List.of(context.getSource().getPlayerOrException()), "band_copper_bronze"))
                                .then(Commands.argument("target", EntityArgument.players())
                                        .executes(context -> getMetalmind(context, EntityArgument.getPlayers(context, "target"), "band_copper_bronze"))))

                        .then(Commands.literal("band_gold_electrum")
                                .executes(context -> getMetalmind(context, List.of(context.getSource().getPlayerOrException()), "band_gold_electrum"))
                                .then(Commands.argument("target", EntityArgument.players())
                                        .executes(context -> getMetalmind(context, EntityArgument.getPlayers(context, "target"), "band_gold_electrum"))))

                        .then(Commands.literal("band_lerasium_ettmetal")
                                .executes(context -> getMetalmind(context, List.of(context.getSource().getPlayerOrException()), "band_lerasium_ettmetal"))
                                .then(Commands.argument("target", EntityArgument.players())
                                        .executes(context -> getMetalmind(context, EntityArgument.getPlayers(context, "target"), "band_lerasium_ettmetal"))))

                        .then(Commands.literal("band_tin_pewter")
                                .executes(context -> getMetalmind(context, List.of(context.getSource().getPlayerOrException()), "band_tin_pewter"))
                                .then(Commands.argument("target", EntityArgument.players())
                                        .executes(context -> getMetalmind(context, EntityArgument.getPlayers(context, "target"), "band_tin_pewter")))))

                .then(Commands.literal("allomantic_iron_spike")
                        .executes(context -> getSpike(context, List.of(context.getSource().getPlayerOrException()), "iron","allomantic"))
                        .then(Commands.argument("target", EntityArgument.players())
                                .executes(context -> getSpike(context, EntityArgument.getPlayers(context, "target"), "iron","allomantic"))))
                .then(Commands.literal("allomantic_steel_spike")
                        .executes(context -> getSpike(context, List.of(context.getSource().getPlayerOrException()), "steel","allomantic"))
                        .then(Commands.argument("target", EntityArgument.players())
                                .executes(context -> getSpike(context, EntityArgument.getPlayers(context, "target"), "steel","allomantic"))))
                .then(Commands.literal("allomantic_tin_spike")
                        .executes(context -> getSpike(context, List.of(context.getSource().getPlayerOrException()), "tin","allomantic"))
                        .then(Commands.argument("target", EntityArgument.players())
                                .executes(context -> getSpike(context, EntityArgument.getPlayers(context, "target"), "tin","allomantic"))))
                .then(Commands.literal("allomantic_pewter_spike")
                        .executes(context -> getSpike(context, List.of(context.getSource().getPlayerOrException()), "pewter","allomantic"))
                        .then(Commands.argument("target", EntityArgument.players())
                                .executes(context -> getSpike(context, EntityArgument.getPlayers(context, "target"), "pewter","allomantic"))))
                .then(Commands.literal("allomantic_zinc_spike")
                        .executes(context -> getSpike(context, List.of(context.getSource().getPlayerOrException()), "zinc","allomantic"))
                        .then(Commands.argument("target", EntityArgument.players())
                                .executes(context -> getSpike(context, EntityArgument.getPlayers(context, "target"), "zinc","allomantic"))))
                .then(Commands.literal("allomantic_brass_spike")
                        .executes(context -> getSpike(context, List.of(context.getSource().getPlayerOrException()), "brass","allomantic"))
                        .then(Commands.argument("target", EntityArgument.players())
                                .executes(context -> getSpike(context, EntityArgument.getPlayers(context, "target"), "brass","allomantic"))))
                .then(Commands.literal("allomantic_copper_spike")
                        .executes(context -> getSpike(context, List.of(context.getSource().getPlayerOrException()), "copper","allomantic"))
                        .then(Commands.argument("target", EntityArgument.players())
                                .executes(context -> getSpike(context, EntityArgument.getPlayers(context, "target"), "copper","allomantic"))))
                .then(Commands.literal("allomantic_bronze_spike")
                        .executes(context -> getSpike(context, List.of(context.getSource().getPlayerOrException()), "bronze","allomantic"))
                        .then(Commands.argument("target", EntityArgument.players())
                                .executes(context -> getSpike(context, EntityArgument.getPlayers(context, "target"), "bronze","allomantic"))))
                .then(Commands.literal("allomantic_aluminum_spike")
                        .executes(context -> getSpike(context, List.of(context.getSource().getPlayerOrException()), "aluminum","allomantic"))
                        .then(Commands.argument("target", EntityArgument.players())
                                .executes(context -> getSpike(context, EntityArgument.getPlayers(context, "target"), "aluminum","allomantic"))))
                .then(Commands.literal("allomantic_duralumin_spike")
                        .executes(context -> getSpike(context, List.of(context.getSource().getPlayerOrException()), "duralumin","allomantic"))
                        .then(Commands.argument("target", EntityArgument.players())
                                .executes(context -> getSpike(context, EntityArgument.getPlayers(context, "target"), "duralumin","allomantic"))))
                .then(Commands.literal("allomantic_chromium_spike")
                        .executes(context -> getSpike(context, List.of(context.getSource().getPlayerOrException()), "chromium","allomantic"))
                        .then(Commands.argument("target", EntityArgument.players())
                                .executes(context -> getSpike(context, EntityArgument.getPlayers(context, "target"), "chromium","allomantic"))))
                .then(Commands.literal("allomantic_nicrosil_spike")
                        .executes(context -> getSpike(context, List.of(context.getSource().getPlayerOrException()), "nicrosil","allomantic"))
                        .then(Commands.argument("target", EntityArgument.players())
                                .executes(context -> getSpike(context, EntityArgument.getPlayers(context, "target"), "nicrosil","allomantic"))))
                .then(Commands.literal("allomantic_gold_spike")
                        .executes(context -> getSpike(context, List.of(context.getSource().getPlayerOrException()), "gold","allomantic"))
                        .then(Commands.argument("target", EntityArgument.players())
                                .executes(context -> getSpike(context, EntityArgument.getPlayers(context, "target"), "gold","allomantic"))))
                .then(Commands.literal("allomantic_electrum_spike")
                        .executes(context -> getSpike(context, List.of(context.getSource().getPlayerOrException()), "electrum","allomantic"))
                        .then(Commands.argument("target", EntityArgument.players())
                                .executes(context -> getSpike(context, EntityArgument.getPlayers(context, "target"), "electrum","allomantic"))))
                .then(Commands.literal("allomantic_cadmium_spike")
                        .executes(context -> getSpike(context, List.of(context.getSource().getPlayerOrException()), "cadmium","allomantic"))
                        .then(Commands.argument("target", EntityArgument.players())
                                .executes(context -> getSpike(context, EntityArgument.getPlayers(context, "target"), "cadmium","allomantic"))))
                .then(Commands.literal("allomantic_bendalloy_spike")
                        .executes(context -> getSpike(context, List.of(context.getSource().getPlayerOrException()), "bendalloy","allomantic"))
                        .then(Commands.argument("target", EntityArgument.players())
                                .executes(context -> getSpike(context, EntityArgument.getPlayers(context, "target"), "bendalloy","allomantic"))))
                .then(Commands.literal("allomantic_atium_spike")
                        .executes(context -> getSpike(context, List.of(context.getSource().getPlayerOrException()), "atium","allomantic"))
                        .then(Commands.argument("target", EntityArgument.players())
                                .executes(context -> getSpike(context, EntityArgument.getPlayers(context, "target"), "atium","allomantic"))))
                .then(Commands.literal("allomantic_malatium_spike")
                        .executes(context -> getSpike(context, List.of(context.getSource().getPlayerOrException()), "malatium","allomantic"))
                        .then(Commands.argument("target", EntityArgument.players())
                                .executes(context -> getSpike(context, EntityArgument.getPlayers(context, "target"), "malatium","allomantic"))))
                .then(Commands.literal("allomantic_lerasium_spike")
                        .executes(context -> getSpike(context, List.of(context.getSource().getPlayerOrException()), "lerasium","allomantic"))
                        .then(Commands.argument("target", EntityArgument.players())
                                .executes(context -> getSpike(context, EntityArgument.getPlayers(context, "target"), "lerasium","allomantic"))))
                .then(Commands.literal("allomantic_ettmetal_spike")
                        .executes(context -> getSpike(context, List.of(context.getSource().getPlayerOrException()), "ettmetal","allomantic"))
                        .then(Commands.argument("target", EntityArgument.players())
                                .executes(context -> getSpike(context, EntityArgument.getPlayers(context, "target"), "ettmetal","allomantic"))))


                .then(Commands.literal("feruchemic_iron_spike")
                        .executes(context -> getSpike(context, List.of(context.getSource().getPlayerOrException()), "iron","feruchemic"))
                        .then(Commands.argument("target", EntityArgument.players())
                                .executes(context -> getSpike(context, EntityArgument.getPlayers(context, "target"), "iron","feruchemic"))))
                .then(Commands.literal("feruchemic_steel_spike")
                        .executes(context -> getSpike(context, List.of(context.getSource().getPlayerOrException()), "steel","feruchemic"))
                        .then(Commands.argument("target", EntityArgument.players())
                                .executes(context -> getSpike(context, EntityArgument.getPlayers(context, "target"), "steel","feruchemic"))))
                .then(Commands.literal("feruchemic_tin_spike")
                        .executes(context -> getSpike(context, List.of(context.getSource().getPlayerOrException()), "tin","feruchemic"))
                        .then(Commands.argument("target", EntityArgument.players())
                                .executes(context -> getSpike(context, EntityArgument.getPlayers(context, "target"), "tin","feruchemic"))))
                .then(Commands.literal("feruchemic_pewter_spike")
                        .executes(context -> getSpike(context, List.of(context.getSource().getPlayerOrException()), "pewter","feruchemic"))
                        .then(Commands.argument("target", EntityArgument.players())
                                .executes(context -> getSpike(context, EntityArgument.getPlayers(context, "target"), "pewter","feruchemic"))))
                .then(Commands.literal("feruchemic_zinc_spike")
                        .executes(context -> getSpike(context, List.of(context.getSource().getPlayerOrException()), "zinc","feruchemic"))
                        .then(Commands.argument("target", EntityArgument.players())
                                .executes(context -> getSpike(context, EntityArgument.getPlayers(context, "target"), "zinc","feruchemic"))))
                .then(Commands.literal("feruchemic_brass_spike")
                        .executes(context -> getSpike(context, List.of(context.getSource().getPlayerOrException()), "brass","feruchemic"))
                        .then(Commands.argument("target", EntityArgument.players())
                                .executes(context -> getSpike(context, EntityArgument.getPlayers(context, "target"), "brass","feruchemic"))))
                .then(Commands.literal("feruchemic_copper_spike")
                        .executes(context -> getSpike(context, List.of(context.getSource().getPlayerOrException()), "copper","feruchemic"))
                        .then(Commands.argument("target", EntityArgument.players())
                                .executes(context -> getSpike(context, EntityArgument.getPlayers(context, "target"), "copper","feruchemic"))))
                .then(Commands.literal("feruchemic_bronze_spike")
                        .executes(context -> getSpike(context, List.of(context.getSource().getPlayerOrException()), "bronze","feruchemic"))
                        .then(Commands.argument("target", EntityArgument.players())
                                .executes(context -> getSpike(context, EntityArgument.getPlayers(context, "target"), "bronze","feruchemic"))))
                .then(Commands.literal("feruchemic_aluminum_spike")
                        .executes(context -> getSpike(context, List.of(context.getSource().getPlayerOrException()), "aluminum","feruchemic"))
                        .then(Commands.argument("target", EntityArgument.players())
                                .executes(context -> getSpike(context, EntityArgument.getPlayers(context, "target"), "aluminum","feruchemic"))))
                .then(Commands.literal("feruchemic_duralumin_spike")
                        .executes(context -> getSpike(context, List.of(context.getSource().getPlayerOrException()), "duralumin","feruchemic"))
                        .then(Commands.argument("target", EntityArgument.players())
                                .executes(context -> getSpike(context, EntityArgument.getPlayers(context, "target"), "duralumin","feruchemic"))))
                .then(Commands.literal("feruchemic_chromium_spike")
                        .executes(context -> getSpike(context, List.of(context.getSource().getPlayerOrException()), "chromium","feruchemic"))
                        .then(Commands.argument("target", EntityArgument.players())
                                .executes(context -> getSpike(context, EntityArgument.getPlayers(context, "target"), "chromium","feruchemic"))))
                .then(Commands.literal("feruchemic_nicrosil_spike")
                        .executes(context -> getSpike(context, List.of(context.getSource().getPlayerOrException()), "nicrosil","feruchemic"))
                        .then(Commands.argument("target", EntityArgument.players())
                                .executes(context -> getSpike(context, EntityArgument.getPlayers(context, "target"), "nicrosil","feruchemic"))))
                .then(Commands.literal("feruchemic_gold_spike")
                        .executes(context -> getSpike(context, List.of(context.getSource().getPlayerOrException()), "gold","feruchemic"))
                        .then(Commands.argument("target", EntityArgument.players())
                                .executes(context -> getSpike(context, EntityArgument.getPlayers(context, "target"), "gold","feruchemic"))))
                .then(Commands.literal("feruchemic_electrum_spike")
                        .executes(context -> getSpike(context, List.of(context.getSource().getPlayerOrException()), "electrum","feruchemic"))
                        .then(Commands.argument("target", EntityArgument.players())
                                .executes(context -> getSpike(context, EntityArgument.getPlayers(context, "target"), "electrum","feruchemic"))))
                .then(Commands.literal("feruchemic_cadmium_spike")
                        .executes(context -> getSpike(context, List.of(context.getSource().getPlayerOrException()), "cadmium","feruchemic"))
                        .then(Commands.argument("target", EntityArgument.players())
                                .executes(context -> getSpike(context, EntityArgument.getPlayers(context, "target"), "cadmium","feruchemic"))))
                .then(Commands.literal("feruchemic_bendalloy_spike")
                        .executes(context -> getSpike(context, List.of(context.getSource().getPlayerOrException()), "bendalloy","feruchemic"))
                        .then(Commands.argument("target", EntityArgument.players())
                                .executes(context -> getSpike(context, EntityArgument.getPlayers(context, "target"), "bendalloy","feruchemic"))))
                .then(Commands.literal("feruchemic_atium_spike")
                        .executes(context -> getSpike(context, List.of(context.getSource().getPlayerOrException()), "atium","feruchemic"))
                        .then(Commands.argument("target", EntityArgument.players())
                                .executes(context -> getSpike(context, EntityArgument.getPlayers(context, "target"), "atium","feruchemic"))))
                .then(Commands.literal("feruchemic_malatium_spike")
                        .executes(context -> getSpike(context, List.of(context.getSource().getPlayerOrException()), "malatium","feruchemic"))
                        .then(Commands.argument("target", EntityArgument.players())
                                .executes(context -> getSpike(context, EntityArgument.getPlayers(context, "target"), "malatium","feruchemic"))))
                .then(Commands.literal("feruchemic_lerasium_spike")
                        .executes(context -> getSpike(context, List.of(context.getSource().getPlayerOrException()), "lerasium","feruchemic"))
                        .then(Commands.argument("target", EntityArgument.players())
                                .executes(context -> getSpike(context, EntityArgument.getPlayers(context, "target"), "lerasium","feruchemic"))))
                .then(Commands.literal("feruchemic_ettmetal_spike")
                        .executes(context -> getSpike(context, List.of(context.getSource().getPlayerOrException()), "ettmetal","feruchemic"))
                        .then(Commands.argument("target", EntityArgument.players())
                                .executes(context -> getSpike(context, EntityArgument.getPlayers(context, "target"), "ettmetal","feruchemic"))))



        );

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

    private static int getSpike(CommandContext<CommandSourceStack> context, Collection<ServerPlayer> targetsPlayers, String spike, String powerType) {
        ItemStack spikeItem = null;
        CompoundTag nbt = new CompoundTag();

        StringBuilder playersName = new StringBuilder();
        boolean firstLoop = true;

        if (MetalTagEnum.getMetal(spike) == null) {
            return 0;
        }

        for(ServerPlayer player: targetsPlayers) {

            if (firstLoop) {
                firstLoop = false;
                playersName.append(player.getScoreboardName());
            } else {
                playersName.append(", ").append(player.getScoreboardName());
            }
            for (SpikeEnum temporalSpike: SpikeEnum.values()) {
                if (temporalSpike.getName().equals(spike)) {
                    spikeItem = new ItemStack(temporalSpike.getSpike());
                    nbt = powerType.equals("feruchemic") ? MetalSpike.addSpikeWithFeruchemicalPower(Objects.requireNonNull(MetalTagEnum.getMetal(spike))) : MetalSpike.addSpikeWithAllomanticPower(Objects.requireNonNull(MetalTagEnum.getMetal(spike)));
                }

            }

            if (spikeItem == null) {
                return 0;
            }
            spikeItem.setTag(nbt);
            player.getInventory().add(spikeItem);
        }
        context.getSource().sendSystemMessage(Component.translatable("Added 1 " + spike + " Spike with" + powerType + " power to: " + playersName));

        return  1;
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

            if (band.equals("band_iron_steel")) {

                metalmind = new ItemStack(MetalMindEnum.IRON_STEEL.getBand());
                nbt = MetalmindAbstract.addBandTagsFull(MetalTagEnum.IRON, MetalTagEnum.STEEL);

            } else if (band.equals("band_zinc_brass")) {
                metalmind = new ItemStack(MetalMindEnum.ZINC_BRASS.getBand());
                nbt = MetalmindAbstract.addBandTagsFull(MetalTagEnum.ZINC, MetalTagEnum.BRASS);

            } else if (band.equals("band_aluminium_duralumin")) {

                metalmind = new ItemStack(MetalMindEnum.ALUMINUM_DURALUMIN.getBand());
                nbt = MetalmindAbstract.addBandTagsFull(MetalTagEnum.ALUMINUM, MetalTagEnum.DURALUMIN);

            } else if (band.equals("band_atium_malatium")) {

                metalmind = new ItemStack(MetalMindEnum.ATIUM_MALTIUM.getBand());
                nbt = MetalmindAbstract.addBandTagsFull(MetalTagEnum.ATIUM, MetalTagEnum.MALATIUM);

            } else if (band.equals("band_cadmium_bendalloy")) {

                metalmind = new ItemStack(MetalMindEnum.CADMIUM_BENDALLOY.getBand());
                nbt = MetalmindAbstract.addBandTagsFull(MetalTagEnum.CADMIUM, MetalTagEnum.BENDALLOY);

            } else if (band.equals("band_chromium_nicrosil")) {

                metalmind = new ItemStack(MetalMindEnum.CHROMIUM_NICROSIL.getBand());
                nbt = MetalmindAbstract.addBandTagsFull(MetalTagEnum.CHROMIUM, MetalTagEnum.NICROSIL);

            } else if (band.equals("band_copper_bronze")) {

                metalmind = new ItemStack(MetalMindEnum.COPPER_BRONZE.getBand());
                nbt = MetalmindAbstract.addBandTagsFull(MetalTagEnum.COPPER, MetalTagEnum.BRONZE);

            } else if (band.equals("band_gold_electrum")) {

                metalmind = new ItemStack(MetalMindEnum.GOLD_ELECTRUM.getBand());
                nbt = MetalmindAbstract.addBandTagsFull(MetalTagEnum.GOLD, MetalTagEnum.ELECTRUM);

            } else if (band.equals("band_lerasium_ettmetal")) {

                metalmind = new ItemStack(MetalMindEnum.LERASIUM_ETTMETAL.getBand());
                nbt = MetalmindAbstract.addBandTagsFull(MetalTagEnum.LERASIUM, MetalTagEnum.ETTMETAL);

            } else if (band.equals("band_tin_pewter")) {

                metalmind = new ItemStack(MetalMindEnum.TIN_PEWTER.getBand());
                nbt = MetalmindAbstract.addBandTagsFull(MetalTagEnum.TIN, MetalTagEnum.PEWTER);

            } else {

                metalmind = new ItemStack(MetalMindEnum.TIN_PEWTER.getBand());
                nbt = MetalmindAbstract.addBandTagsFull(MetalTagEnum.TIN, MetalTagEnum.PEWTER);

            }
            metalmind.setTag(nbt);
            player.getInventory().add(metalmind);
        }
        context.getSource().sendSystemMessage(Component.translatable("Added 1 Metalmind to: " + playersName));

        return  1;
    }

}
