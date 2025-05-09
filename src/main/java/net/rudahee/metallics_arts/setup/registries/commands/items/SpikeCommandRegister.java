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
import net.rudahee.metallics_arts.data.enums.implementations.custom_items.SpikeEnum;
import net.rudahee.metallics_arts.modules.custom_items.metal_spikes.MetalSpike;

import java.util.Collection;
import java.util.List;
import java.util.Objects;


public class SpikeCommandRegister {


    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {

        dispatcher.register(Commands.literal("ma-items").requires(commandSource -> commandSource.hasPermission(2))
                .then(Commands.literal("give")
                        .then(Commands.literal("spike")
                                .then(Commands.literal("feruchemic")
                                            .then(Commands.literal("iron")
                                                    .executes(context -> getSpike(context, List.of(context.getSource().getPlayerOrException()), "iron","feruchemic"))
                                                    .then(Commands.argument("target", EntityArgument.players())
                                                            .executes(context -> getSpike(context, EntityArgument.getPlayers(context, "target"), "iron","feruchemic"))))
                                            .then(Commands.literal("steel")
                                                    .executes(context -> getSpike(context, List.of(context.getSource().getPlayerOrException()), "steel","feruchemic"))
                                                    .then(Commands.argument("target", EntityArgument.players())
                                                            .executes(context -> getSpike(context, EntityArgument.getPlayers(context, "target"), "steel","feruchemic"))))
                                            .then(Commands.literal("tin")
                                                    .executes(context -> getSpike(context, List.of(context.getSource().getPlayerOrException()), "tin","feruchemic"))
                                                    .then(Commands.argument("target", EntityArgument.players())
                                                            .executes(context -> getSpike(context, EntityArgument.getPlayers(context, "target"), "tin","feruchemic"))))
                                            .then(Commands.literal("pewter")
                                                    .executes(context -> getSpike(context, List.of(context.getSource().getPlayerOrException()), "pewter","feruchemic"))
                                                    .then(Commands.argument("target", EntityArgument.players())
                                                            .executes(context -> getSpike(context, EntityArgument.getPlayers(context, "target"), "pewter","feruchemic"))))
                                            .then(Commands.literal("zinc")
                                                    .executes(context -> getSpike(context, List.of(context.getSource().getPlayerOrException()), "zinc","feruchemic"))
                                                    .then(Commands.argument("target", EntityArgument.players())
                                                            .executes(context -> getSpike(context, EntityArgument.getPlayers(context, "target"), "zinc","feruchemic"))))
                                            .then(Commands.literal("brass")
                                                    .executes(context -> getSpike(context, List.of(context.getSource().getPlayerOrException()), "brass","feruchemic"))
                                                    .then(Commands.argument("target", EntityArgument.players())
                                                            .executes(context -> getSpike(context, EntityArgument.getPlayers(context, "target"), "brass","feruchemic"))))
                                            .then(Commands.literal("copper")
                                                    .executes(context -> getSpike(context, List.of(context.getSource().getPlayerOrException()), "copper","feruchemic"))
                                                    .then(Commands.argument("target", EntityArgument.players())
                                                            .executes(context -> getSpike(context, EntityArgument.getPlayers(context, "target"), "copper","feruchemic"))))
                                            .then(Commands.literal("bronze")
                                                    .executes(context -> getSpike(context, List.of(context.getSource().getPlayerOrException()), "bronze","feruchemic"))
                                                    .then(Commands.argument("target", EntityArgument.players())
                                                            .executes(context -> getSpike(context, EntityArgument.getPlayers(context, "target"), "bronze","feruchemic"))))
                                            .then(Commands.literal("aluminum")
                                                    .executes(context -> getSpike(context, List.of(context.getSource().getPlayerOrException()), "aluminum","feruchemic"))
                                                    .then(Commands.argument("target", EntityArgument.players())
                                                            .executes(context -> getSpike(context, EntityArgument.getPlayers(context, "target"), "aluminum","feruchemic"))))
                                            .then(Commands.literal("duralumin")
                                                    .executes(context -> getSpike(context, List.of(context.getSource().getPlayerOrException()), "duralumin","feruchemic"))
                                                    .then(Commands.argument("target", EntityArgument.players())
                                                            .executes(context -> getSpike(context, EntityArgument.getPlayers(context, "target"), "duralumin","feruchemic"))))
                                            .then(Commands.literal("chromium")
                                                    .executes(context -> getSpike(context, List.of(context.getSource().getPlayerOrException()), "chromium","feruchemic"))
                                                    .then(Commands.argument("target", EntityArgument.players())
                                                            .executes(context -> getSpike(context, EntityArgument.getPlayers(context, "target"), "chromium","feruchemic"))))
                                            .then(Commands.literal("nicrosil")
                                                    .executes(context -> getSpike(context, List.of(context.getSource().getPlayerOrException()), "nicrosil","feruchemic"))
                                                    .then(Commands.argument("target", EntityArgument.players())
                                                            .executes(context -> getSpike(context, EntityArgument.getPlayers(context, "target"), "nicrosil","feruchemic"))))
                                            .then(Commands.literal("gold")
                                                    .executes(context -> getSpike(context, List.of(context.getSource().getPlayerOrException()), "gold","feruchemic"))
                                                    .then(Commands.argument("target", EntityArgument.players())
                                                            .executes(context -> getSpike(context, EntityArgument.getPlayers(context, "target"), "gold","feruchemic"))))
                                            .then(Commands.literal("electrum")
                                                    .executes(context -> getSpike(context, List.of(context.getSource().getPlayerOrException()), "electrum","feruchemic"))
                                                    .then(Commands.argument("target", EntityArgument.players())
                                                            .executes(context -> getSpike(context, EntityArgument.getPlayers(context, "target"), "electrum","feruchemic"))))
                                            .then(Commands.literal("cadmium")
                                                    .executes(context -> getSpike(context, List.of(context.getSource().getPlayerOrException()), "cadmium","feruchemic"))
                                                    .then(Commands.argument("target", EntityArgument.players())
                                                            .executes(context -> getSpike(context, EntityArgument.getPlayers(context, "target"), "cadmium","feruchemic"))))
                                            .then(Commands.literal("bendalloy")
                                                    .executes(context -> getSpike(context, List.of(context.getSource().getPlayerOrException()), "bendalloy","feruchemic"))
                                                    .then(Commands.argument("target", EntityArgument.players())
                                                            .executes(context -> getSpike(context, EntityArgument.getPlayers(context, "target"), "bendalloy","feruchemic"))))
                                            .then(Commands.literal("atium")
                                                    .executes(context -> getSpike(context, List.of(context.getSource().getPlayerOrException()), "atium","feruchemic"))
                                                    .then(Commands.argument("target", EntityArgument.players())
                                                            .executes(context -> getSpike(context, EntityArgument.getPlayers(context, "target"), "atium","feruchemic"))))
                                            .then(Commands.literal("malatium")
                                                    .executes(context -> getSpike(context, List.of(context.getSource().getPlayerOrException()), "malatium","feruchemic"))
                                                    .then(Commands.argument("target", EntityArgument.players())
                                                            .executes(context -> getSpike(context, EntityArgument.getPlayers(context, "target"), "malatium","feruchemic"))))
                                            .then(Commands.literal("lerasium")
                                                    .executes(context -> getSpike(context, List.of(context.getSource().getPlayerOrException()), "lerasium","feruchemic"))
                                                    .then(Commands.argument("target", EntityArgument.players())
                                                            .executes(context -> getSpike(context, EntityArgument.getPlayers(context, "target"), "lerasium","feruchemic"))))
                                            .then(Commands.literal("ettmetal")
                                                    .executes(context -> getSpike(context, List.of(context.getSource().getPlayerOrException()), "ettmetal","feruchemic"))
                                                    .then(Commands.argument("target", EntityArgument.players())
                                                            .executes(context -> getSpike(context, EntityArgument.getPlayers(context, "target"), "ettmetal","feruchemic")))))

                                .then(Commands.literal("allomantic")
                                            .then(Commands.literal("iron")
                                                    .executes(context -> getSpike(context, List.of(context.getSource().getPlayerOrException()), "iron","allomantic"))
                                                    .then(Commands.argument("target", EntityArgument.players())
                                                            .executes(context -> getSpike(context, EntityArgument.getPlayers(context, "target"), "iron","allomantic"))))
                                            .then(Commands.literal("steel")
                                                    .executes(context -> getSpike(context, List.of(context.getSource().getPlayerOrException()), "steel","allomantic"))
                                                    .then(Commands.argument("target", EntityArgument.players())
                                                            .executes(context -> getSpike(context, EntityArgument.getPlayers(context, "target"), "steel","allomantic"))))
                                            .then(Commands.literal("tin")
                                                    .executes(context -> getSpike(context, List.of(context.getSource().getPlayerOrException()), "tin","allomantic"))
                                                    .then(Commands.argument("target", EntityArgument.players())
                                                            .executes(context -> getSpike(context, EntityArgument.getPlayers(context, "target"), "tin","allomantic"))))
                                            .then(Commands.literal("pewter")
                                                    .executes(context -> getSpike(context, List.of(context.getSource().getPlayerOrException()), "pewter","allomantic"))
                                                    .then(Commands.argument("target", EntityArgument.players())
                                                            .executes(context -> getSpike(context, EntityArgument.getPlayers(context, "target"), "pewter","allomantic"))))
                                            .then(Commands.literal("zinc")
                                                    .executes(context -> getSpike(context, List.of(context.getSource().getPlayerOrException()), "zinc","allomantic"))
                                                    .then(Commands.argument("target", EntityArgument.players())
                                                            .executes(context -> getSpike(context, EntityArgument.getPlayers(context, "target"), "zinc","allomantic"))))
                                            .then(Commands.literal("brass")
                                                    .executes(context -> getSpike(context, List.of(context.getSource().getPlayerOrException()), "brass","allomantic"))
                                                    .then(Commands.argument("target", EntityArgument.players())
                                                            .executes(context -> getSpike(context, EntityArgument.getPlayers(context, "target"), "brass","allomantic"))))
                                            .then(Commands.literal("copper")
                                                    .executes(context -> getSpike(context, List.of(context.getSource().getPlayerOrException()), "copper","allomantic"))
                                                    .then(Commands.argument("target", EntityArgument.players())
                                                            .executes(context -> getSpike(context, EntityArgument.getPlayers(context, "target"), "copper","allomantic"))))
                                            .then(Commands.literal("bronze")
                                                    .executes(context -> getSpike(context, List.of(context.getSource().getPlayerOrException()), "bronze","allomantic"))
                                                    .then(Commands.argument("target", EntityArgument.players())
                                                            .executes(context -> getSpike(context, EntityArgument.getPlayers(context, "target"), "bronze","allomantic"))))
                                            .then(Commands.literal("aluminum")
                                                    .executes(context -> getSpike(context, List.of(context.getSource().getPlayerOrException()), "aluminum","allomantic"))
                                                    .then(Commands.argument("target", EntityArgument.players())
                                                            .executes(context -> getSpike(context, EntityArgument.getPlayers(context, "target"), "aluminum","allomantic"))))
                                            .then(Commands.literal("duralumin")
                                                    .executes(context -> getSpike(context, List.of(context.getSource().getPlayerOrException()), "duralumin","allomantic"))
                                                    .then(Commands.argument("target", EntityArgument.players())
                                                            .executes(context -> getSpike(context, EntityArgument.getPlayers(context, "target"), "duralumin","allomantic"))))
                                            .then(Commands.literal("chromium")
                                                    .executes(context -> getSpike(context, List.of(context.getSource().getPlayerOrException()), "chromium","allomantic"))
                                                    .then(Commands.argument("target", EntityArgument.players())
                                                            .executes(context -> getSpike(context, EntityArgument.getPlayers(context, "target"), "chromium","allomantic"))))
                                            .then(Commands.literal("nicrosil")
                                                    .executes(context -> getSpike(context, List.of(context.getSource().getPlayerOrException()), "nicrosil","allomantic"))
                                                    .then(Commands.argument("target", EntityArgument.players())
                                                            .executes(context -> getSpike(context, EntityArgument.getPlayers(context, "target"), "nicrosil","allomantic"))))
                                            .then(Commands.literal("gold")
                                                    .executes(context -> getSpike(context, List.of(context.getSource().getPlayerOrException()), "gold","allomantic"))
                                                    .then(Commands.argument("target", EntityArgument.players())
                                                            .executes(context -> getSpike(context, EntityArgument.getPlayers(context, "target"), "gold","allomantic"))))
                                            .then(Commands.literal("electrum")
                                                    .executes(context -> getSpike(context, List.of(context.getSource().getPlayerOrException()), "electrum","allomantic"))
                                                    .then(Commands.argument("target", EntityArgument.players())
                                                            .executes(context -> getSpike(context, EntityArgument.getPlayers(context, "target"), "electrum","allomantic"))))
                                            .then(Commands.literal("cadmium")
                                                    .executes(context -> getSpike(context, List.of(context.getSource().getPlayerOrException()), "cadmium","allomantic"))
                                                    .then(Commands.argument("target", EntityArgument.players())
                                                            .executes(context -> getSpike(context, EntityArgument.getPlayers(context, "target"), "cadmium","allomantic"))))
                                            .then(Commands.literal("bendalloy")
                                                    .executes(context -> getSpike(context, List.of(context.getSource().getPlayerOrException()), "bendalloy","allomantic"))
                                                    .then(Commands.argument("target", EntityArgument.players())
                                                            .executes(context -> getSpike(context, EntityArgument.getPlayers(context, "target"), "bendalloy","allomantic"))))
                                            .then(Commands.literal("atium")
                                                    .executes(context -> getSpike(context, List.of(context.getSource().getPlayerOrException()), "atium","allomantic"))
                                                    .then(Commands.argument("target", EntityArgument.players())
                                                            .executes(context -> getSpike(context, EntityArgument.getPlayers(context, "target"), "atium","allomantic"))))
                                            .then(Commands.literal("malatium")
                                                    .executes(context -> getSpike(context, List.of(context.getSource().getPlayerOrException()), "malatium","allomantic"))
                                                    .then(Commands.argument("target", EntityArgument.players())
                                                            .executes(context -> getSpike(context, EntityArgument.getPlayers(context, "target"), "malatium","allomantic"))))
                                            .then(Commands.literal("lerasium")
                                                    .executes(context -> getSpike(context, List.of(context.getSource().getPlayerOrException()), "lerasium","allomantic"))
                                                    .then(Commands.argument("target", EntityArgument.players())
                                                            .executes(context -> getSpike(context, EntityArgument.getPlayers(context, "target"), "lerasium","allomantic"))))
                                            .then(Commands.literal("ettmetal")
                                                    .executes(context -> getSpike(context, List.of(context.getSource().getPlayerOrException()), "ettmetal","allomantic"))
                                                    .then(Commands.argument("target", EntityArgument.players())
                                                            .executes(context -> getSpike(context, EntityArgument.getPlayers(context, "target"), "ettmetal","allomantic"))))

                                ))));
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


}

