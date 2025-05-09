package net.rudahee.metallics_arts.setup.registries;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.rudahee.metallics_arts.setup.registries.commands.items.BandsCommandsRegister;
import net.rudahee.metallics_arts.setup.registries.commands.items.RingsCommandRegister;
import net.rudahee.metallics_arts.setup.registries.commands.items.SpikeCommandRegister;
import net.rudahee.metallics_arts.setup.registries.commands.items.VialsCommandRegister;
import net.rudahee.metallics_arts.setup.registries.commands.powers.PowersAddRegister;
import net.rudahee.metallics_arts.setup.registries.commands.powers.PowersFillRegister;
import net.rudahee.metallics_arts.setup.registries.commands.powers.PowersGetRegister;
import net.rudahee.metallics_arts.setup.registries.commands.powers.PowersRemoveRegister;

import java.util.function.Predicate;

public class ModCommandsRegister {

    private static final DynamicCommandExceptionType ERROR_CANT_ADD = new DynamicCommandExceptionType(s -> Component.translatable("commands.metallic_arts.err_add", s));
    private static final DynamicCommandExceptionType ERROR_CANT_REMOVE = new DynamicCommandExceptionType(s -> Component.translatable("commands.metallic_arts.err_remove", s));

    private static Predicate<CommandSourceStack> permissions(int level) {
        return (player) -> player.hasPermission(level);
    }

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        VialsCommandRegister.register(dispatcher);
        BandsCommandsRegister.register(dispatcher);
        RingsCommandRegister.register(dispatcher);
        SpikeCommandRegister.register(dispatcher);
        VialsCommandRegister.register(dispatcher);

        PowersGetRegister.register(dispatcher);
        PowersAddRegister.register(dispatcher);
        PowersFillRegister.register(dispatcher);
        PowersRemoveRegister.register(dispatcher);
    }

}
