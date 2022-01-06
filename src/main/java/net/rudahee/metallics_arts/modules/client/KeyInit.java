package net.rudahee.metallics_arts.modules.client;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.rudahee.metallics_arts.MetallicsArts;
import org.lwjgl.glfw.GLFW;


@OnlyIn(Dist.CLIENT)
public class KeyInit {

    @OnlyIn(Dist.CLIENT)
    public static KeyBinding allomancy;

    @OnlyIn(Dist.CLIENT)
    public static KeyBinding feruchemic;

    public static void register (){
        feruchemic = new KeyBinding("key." + MetallicsArts.MOD_ID + "." + "Allomancy", GLFW.GLFW_KEY_N, "Metallic Arts");
        allomancy = new KeyBinding("key." + MetallicsArts.MOD_ID + "." + "Feruchemic", GLFW.GLFW_KEY_M, "Metallic Arts");

        ClientRegistry.registerKeyBinding(feruchemic);
        ClientRegistry.registerKeyBinding(allomancy);
    }
}
