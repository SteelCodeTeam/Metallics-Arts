package net.rudahee.metallics_arts.setup.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.rudahee.metallics_arts.modules.data_player.InvestedCapability;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;

import java.util.function.Predicate;

public class MetallicArtsCommand {

    private static final DynamicCommandExceptionType ERROR_CANT_ADD = new DynamicCommandExceptionType(s -> new TranslationTextComponent("commands.metallic_arts.err_add", s));
    private static final DynamicCommandExceptionType ERROR_CANT_REMOVE = new DynamicCommandExceptionType(s -> new TranslationTextComponent("commands.metallic_arts.err_remove", s));

    private static Predicate<CommandSource> permissions(int level) {
        return (player) -> player.hasPermission(level);
    }

    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(Commands.literal("metallic").requires(commandSource -> commandSource.hasPermission(0))
                .then(Commands.literal("get")
                )

                .then(Commands.literal("add")

                        .then(Commands.literal("all")
                                .then(Commands.argument("target",EntityArgument.player())
                                        .executes(context -> addAllPower(context))))

                        .then(Commands.literal("allomantic")
                                .then(Commands.literal("all")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addAllAllomanticPower(context)))
                                )
                                .then(Commands.literal("steel")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addAllomanticPower(context,MetalsNBTData.STEEL)))
                                )
                                .then(Commands.literal("iron")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addAllomanticPower(context,MetalsNBTData.IRON)))
                                )
                                .then(Commands.literal("tin")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addAllomanticPower(context,MetalsNBTData.TIN)))
                                )
                                .then(Commands.literal("pewter")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addAllomanticPower(context,MetalsNBTData.PEWTER)))
                                )
                                .then(Commands.literal("copper")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addAllomanticPower(context,MetalsNBTData.COPPER)))
                                )
                                .then(Commands.literal("bronze")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addAllomanticPower(context,MetalsNBTData.ZINC)))
                                )
                                .then(Commands.literal("brass")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addAllomanticPower(context,MetalsNBTData.BRASS)))
                                )
                                .then(Commands.literal("chromium")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addAllomanticPower(context,MetalsNBTData.CHROMIUM)))
                                )
                                .then(Commands.literal("nicrosil")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addAllomanticPower(context,MetalsNBTData.NICROSIL)))
                                )
                                .then(Commands.literal("aluminum")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addAllomanticPower(context,MetalsNBTData.ALUMINUM)))
                                )
                                .then(Commands.literal("duralumin")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addAllomanticPower(context,MetalsNBTData.DURALUMIN)))
                                )
                                .then(Commands.literal("cadmium")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addAllomanticPower(context,MetalsNBTData.CADMIUM)))
                                )
                                .then(Commands.literal("bendalloy")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addAllomanticPower(context,MetalsNBTData.BENDALLOY)))
                                )
                                .then(Commands.literal("electrum")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addAllomanticPower(context,MetalsNBTData.ELECTRUM)))
                                )
                                .then(Commands.literal("gold")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addAllomanticPower(context,MetalsNBTData.GOLD)))
                                )
                                .then(Commands.literal("atium")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addAllomanticPower(context,MetalsNBTData.ATIUM)))
                                )
                                .then(Commands.literal("malatium")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addAllomanticPower(context,MetalsNBTData.MALATIUM)))
                                )
                                .then(Commands.literal("lerasium")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addAllomanticPower(context,MetalsNBTData.LERASIUM)))
                                )
                                .then(Commands.literal("ettmetal")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addAllomanticPower(context,MetalsNBTData.ETTMETAL)))
                                )
                        )
                        .then(Commands.literal("feruchemic")
                                .then(Commands.literal("all")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addAllFeruchemicPower(context)))
                                )
                                .then(Commands.literal("steel")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addFeruchemicPower(context,MetalsNBTData.STEEL)))
                                )
                                .then(Commands.literal("iron")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addFeruchemicPower(context,MetalsNBTData.IRON)))
                                )
                                .then(Commands.literal("tin")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addFeruchemicPower(context,MetalsNBTData.TIN)))
                                )
                                .then(Commands.literal("pewter")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addFeruchemicPower(context,MetalsNBTData.PEWTER)))
                                )
                                .then(Commands.literal("copper")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addFeruchemicPower(context,MetalsNBTData.COPPER)))
                                )
                                .then(Commands.literal("bronze")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addFeruchemicPower(context,MetalsNBTData.ZINC)))
                                )
                                .then(Commands.literal("brass")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addFeruchemicPower(context,MetalsNBTData.BRASS)))
                                )
                                .then(Commands.literal("chromium")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addFeruchemicPower(context,MetalsNBTData.CHROMIUM)))
                                )
                                .then(Commands.literal("nicrosil")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addFeruchemicPower(context,MetalsNBTData.NICROSIL)))
                                )
                                .then(Commands.literal("aluminum")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addFeruchemicPower(context,MetalsNBTData.ALUMINUM)))
                                )
                                .then(Commands.literal("duralumin")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addFeruchemicPower(context,MetalsNBTData.DURALUMIN)))
                                )
                                .then(Commands.literal("cadmium")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addFeruchemicPower(context,MetalsNBTData.CADMIUM)))
                                )
                                .then(Commands.literal("bendalloy")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addFeruchemicPower(context,MetalsNBTData.BENDALLOY)))
                                )
                                .then(Commands.literal("electrum")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addFeruchemicPower(context,MetalsNBTData.ELECTRUM)))
                                )
                                .then(Commands.literal("gold")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addFeruchemicPower(context,MetalsNBTData.GOLD)))
                                )
                                .then(Commands.literal("atium")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addFeruchemicPower(context,MetalsNBTData.ATIUM)))
                                )
                                .then(Commands.literal("malatium")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addFeruchemicPower(context,MetalsNBTData.MALATIUM)))
                                )
                                .then(Commands.literal("lerasium")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addFeruchemicPower(context,MetalsNBTData.LERASIUM)))
                                )
                                .then(Commands.literal("ettmetal")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> addFeruchemicPower(context,MetalsNBTData.ETTMETAL)))
                                )
                        )
                )
                .then(Commands.literal("remove")

                        .then(Commands.literal("all")
                                .then(Commands.argument("target",EntityArgument.player())
                                        .executes(context -> removeAllPower(context))))
                        .then(Commands.literal("allomantic")
                                .then(Commands.literal("all")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeAllAllomanticPower(context)))
                                )
                                .then(Commands.literal("steel")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeAllomanticPower(context,MetalsNBTData.STEEL)))
                                )
                                .then(Commands.literal("iron")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeAllomanticPower(context,MetalsNBTData.IRON)))
                                )
                                .then(Commands.literal("tin")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeAllomanticPower(context,MetalsNBTData.TIN)))
                                )
                                .then(Commands.literal("pewter")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeAllomanticPower(context,MetalsNBTData.PEWTER)))
                                )
                                .then(Commands.literal("copper")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeAllomanticPower(context,MetalsNBTData.COPPER)))
                                )
                                .then(Commands.literal("bronze")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeAllomanticPower(context,MetalsNBTData.ZINC)))
                                )
                                .then(Commands.literal("brass")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeAllomanticPower(context,MetalsNBTData.BRASS)))
                                )
                                .then(Commands.literal("chromium")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeAllomanticPower(context,MetalsNBTData.CHROMIUM)))
                                )
                                .then(Commands.literal("nicrosil")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeAllomanticPower(context,MetalsNBTData.NICROSIL)))
                                )
                                .then(Commands.literal("aluminum")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeAllomanticPower(context,MetalsNBTData.ALUMINUM)))
                                )
                                .then(Commands.literal("duralumin")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeAllomanticPower(context,MetalsNBTData.DURALUMIN)))
                                )
                                .then(Commands.literal("cadmium")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeAllomanticPower(context,MetalsNBTData.CADMIUM)))
                                )
                                .then(Commands.literal("bendalloy")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeAllomanticPower(context,MetalsNBTData.BENDALLOY)))
                                )
                                .then(Commands.literal("electrum")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeAllomanticPower(context,MetalsNBTData.ELECTRUM)))
                                )
                                .then(Commands.literal("gold")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeAllomanticPower(context,MetalsNBTData.GOLD)))
                                )
                                .then(Commands.literal("atium")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeAllomanticPower(context,MetalsNBTData.ATIUM)))
                                )
                                .then(Commands.literal("malatium")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeAllomanticPower(context,MetalsNBTData.MALATIUM)))
                                )
                                .then(Commands.literal("lerasium")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeAllomanticPower(context,MetalsNBTData.LERASIUM)))
                                )
                                .then(Commands.literal("ettmetal")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeAllomanticPower(context,MetalsNBTData.ETTMETAL)))
                                )
                        )
                        .then(Commands.literal("feruchemic")
                                .then(Commands.literal("all")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeAllFeruchemicPower(context)))
                                )
                                .then(Commands.literal("steel")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeFeruchemicPower(context,MetalsNBTData.STEEL)))
                                )
                                .then(Commands.literal("iron")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeFeruchemicPower(context,MetalsNBTData.IRON)))
                                )
                                .then(Commands.literal("tin")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeFeruchemicPower(context,MetalsNBTData.TIN)))
                                )
                                .then(Commands.literal("pewter")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeFeruchemicPower(context,MetalsNBTData.PEWTER)))
                                )
                                .then(Commands.literal("copper")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeFeruchemicPower(context,MetalsNBTData.COPPER)))
                                )
                                .then(Commands.literal("bronze")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeFeruchemicPower(context,MetalsNBTData.ZINC)))
                                )
                                .then(Commands.literal("brass")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeFeruchemicPower(context,MetalsNBTData.BRASS)))
                                )
                                .then(Commands.literal("chromium")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeFeruchemicPower(context,MetalsNBTData.CHROMIUM)))
                                )
                                .then(Commands.literal("nicrosil")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeFeruchemicPower(context,MetalsNBTData.NICROSIL)))
                                )
                                .then(Commands.literal("aluminum")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeFeruchemicPower(context,MetalsNBTData.ALUMINUM)))
                                )
                                .then(Commands.literal("duralumin")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeFeruchemicPower(context,MetalsNBTData.DURALUMIN)))
                                )
                                .then(Commands.literal("cadmium")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeFeruchemicPower(context,MetalsNBTData.CADMIUM)))
                                )
                                .then(Commands.literal("bendalloy")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeFeruchemicPower(context,MetalsNBTData.BENDALLOY)))
                                )
                                .then(Commands.literal("electrum")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeFeruchemicPower(context,MetalsNBTData.ELECTRUM)))
                                )
                                .then(Commands.literal("gold")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeFeruchemicPower(context,MetalsNBTData.GOLD)))
                                )
                                .then(Commands.literal("atium")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeFeruchemicPower(context,MetalsNBTData.ATIUM)))
                                )
                                .then(Commands.literal("malatium")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeFeruchemicPower(context,MetalsNBTData.MALATIUM)))
                                )
                                .then(Commands.literal("lerasium")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeFeruchemicPower(context,MetalsNBTData.LERASIUM)))
                                )
                                .then(Commands.literal("ettmetal")
                                        .then(Commands.argument("target",EntityArgument.player())
                                                .executes(context -> removeFeruchemicPower(context,MetalsNBTData.ETTMETAL)))
                                )
                        )
                )
        );

    }

    public static int addAllomanticPower (CommandContext<CommandSource> context,MetalsNBTData metalsNBTData){
        ServerPlayerEntity playerEntity = null;
        try {
            playerEntity = context.getSource().getPlayerOrException();
        }catch (Exception e){
            e.printStackTrace();
        }
        if (playerEntity != null){
            playerEntity.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(p ->{
                p.addAllomanticPower(metalsNBTData);
                    }
            );
            playerEntity.sendMessage(new StringTextComponent("Hecho"),playerEntity.getUUID());
        }
        return 1;
    }
    public static int addFeruchemicPower (CommandContext<CommandSource> context,MetalsNBTData metalsNBTData){
        ServerPlayerEntity playerEntity = null;
        try {
            playerEntity = context.getSource().getPlayerOrException();
        }catch (Exception e){
            e.printStackTrace();
        }
        if (playerEntity != null){
            playerEntity.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(p ->{
                        p.addFeruchemicPower(metalsNBTData);
                    }
            );
            playerEntity.sendMessage(new StringTextComponent("Hecho"),playerEntity.getUUID());
        }
        return 1;
    }
    public static int addAllAllomanticPower (CommandContext<CommandSource> context){
        ServerPlayerEntity playerEntity = null;
        try {
            playerEntity = context.getSource().getPlayerOrException();
        }catch (Exception e){
            e.printStackTrace();
        }
        if (playerEntity != null){
            playerEntity.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(p ->{
                        p.addAllAllomantic();
                    }
            );
            playerEntity.sendMessage(new StringTextComponent("Hecho"),playerEntity.getUUID());
        }
        return 1;
    }
    public static int addAllFeruchemicPower (CommandContext<CommandSource> context){
        ServerPlayerEntity playerEntity = null;
        try {
            playerEntity = context.getSource().getPlayerOrException();
        }catch (Exception e){
            e.printStackTrace();
        }
        if (playerEntity != null){
            playerEntity.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(p ->{
                        p.addAllFeruchemic();
                    }
            );
            playerEntity.sendMessage(new StringTextComponent("Hecho"),playerEntity.getUUID());
        }

        return 1;
    }
    public static int addAllPower(CommandContext<CommandSource> context){
        ServerPlayerEntity playerEntity = null;
        try {
            playerEntity = context.getSource().getPlayerOrException();
        }catch (Exception e){
            e.printStackTrace();
        }
        if (playerEntity != null){
            playerEntity.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(p ->{
                        p.addAllAllomantic();
                        p.addAllFeruchemic();
                    }
            );
            playerEntity.sendMessage(new StringTextComponent("Hecho"),playerEntity.getUUID());
        }

        return 1;
    }
    public static int removeAllomanticPower (CommandContext<CommandSource> context,MetalsNBTData metalsNBTData){
        ServerPlayerEntity playerEntity = null;
        try {
            playerEntity = context.getSource().getPlayerOrException();
        }catch (Exception e){
            e.printStackTrace();
        }
        if (playerEntity != null){
            playerEntity.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(p ->{
                        p.removeAllomanticPower(metalsNBTData);
                    }
            );
            playerEntity.sendMessage(new StringTextComponent("Hecho"),playerEntity.getUUID());
        }
        return 1;
    }
    public static int removeFeruchemicPower (CommandContext<CommandSource> context,MetalsNBTData metalsNBTData){
        ServerPlayerEntity playerEntity = null;
        try {
            playerEntity = context.getSource().getPlayerOrException();
        }catch (Exception e){
            e.printStackTrace();
        }
        if (playerEntity != null){
            playerEntity.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(p ->{
                        p.removeFeruchemicPower(metalsNBTData);
                    }
            );
            playerEntity.sendMessage(new StringTextComponent("Hecho"),playerEntity.getUUID());
        }
        return 1;
    }
    public static int removeAllAllomanticPower (CommandContext<CommandSource> context){
        ServerPlayerEntity playerEntity = null;
        try {
            playerEntity = context.getSource().getPlayerOrException();
        }catch (Exception e){
            e.printStackTrace();
        }
        if (playerEntity != null){
            playerEntity.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(p ->{
                        p.removeAllAllomanticPower();
                    }
            );
            playerEntity.sendMessage(new StringTextComponent("Hecho"),playerEntity.getUUID());
        }

        return 1;
    }
    public static int removeAllFeruchemicPower (CommandContext<CommandSource> context){
        ServerPlayerEntity playerEntity = null;
        try {
            playerEntity = context.getSource().getPlayerOrException();
        }catch (Exception e){
            e.printStackTrace();
        }
        if (playerEntity != null){
            playerEntity.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(p ->{
                        p.removeAllFeruchemicPower();
                    }
            );
            playerEntity.sendMessage(new StringTextComponent("Hecho"),playerEntity.getUUID());
        }
        return 1;
    }
    public static int removeAllPower(CommandContext<CommandSource> context){
        ServerPlayerEntity playerEntity = null;
        try {
            playerEntity = context.getSource().getPlayerOrException();
        }catch (Exception e){
            e.printStackTrace();
        }
        if (playerEntity != null){
            playerEntity.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(p ->{
                        p.removeAllAllomanticPower();
                        p.removeAllFeruchemicPower();
                    }
            );
            playerEntity.sendMessage(new StringTextComponent("Hecho"),playerEntity.getUUID());
        }
        return 1;
    }

    public static int getAllomanticPower (CommandContext<CommandSource> context,MetalsNBTData metalsNBTData){
        ServerPlayerEntity playerEntity = null;
        try {
            playerEntity = context.getSource().getPlayerOrException();
        }catch (Exception e){
            e.printStackTrace();
        }
        if (playerEntity != null){
            playerEntity.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(p ->{ p.hasAllomanticPower(metalsNBTData); } );
        }

        return 1;
    }

    public static int getFeruchemicPower (CommandContext<CommandSource> context){

        return 1;
    }

    public static int getAllAllomanticPower (CommandContext<CommandSource> context){

        return 1;
    }

    public static int getAllFeruchemicPower (CommandContext<CommandSource> context){

        return 1;
    }


}
