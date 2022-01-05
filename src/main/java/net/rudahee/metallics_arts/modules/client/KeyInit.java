package net.rudahee.metallics_arts.modules.client;

import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.InputMappings;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.rudahee.metallics_arts.MetallicsArts;

public class KeyInit {
    public static KeyBinding exampleKeyBinding;

    private KeyInit() {
    }

    public static void init() {
        exampleKeyBinding = registerKey("example_key",, InputMappings.);
    }

    private static KeyBinding registerKey(String name, String category, int keycode) {
        KeyBinding key = new KeyBinding("key." + MetallicsArts.MOD_ID + "." + name, keycode, category);
        ClientRegistry.registerKeyBinding(key);
        return key;
    }
}

