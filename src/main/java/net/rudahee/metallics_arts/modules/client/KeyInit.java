package net.rudahee.metallics_arts.modules.client;

import net.minecraft.client.KeyMapping;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.rudahee.metallics_arts.MetallicsArts;
import org.lwjgl.glfw.GLFW;

@OnlyIn(Dist.CLIENT)
public class KeyInit {
    
    //particulas y sonidos

    @OnlyIn(Dist.CLIENT)
    public static KeyMapping allomancy;

    @OnlyIn(Dist.CLIENT)
    public static KeyMapping feruchemic;

    @OnlyIn(Dist.CLIENT)
    public static KeyMapping hud;

    public static void register (){
        feruchemic = new KeyMapping("key." + MetallicsArts.MOD_ID + "." + "feruchemic", GLFW.GLFW_KEY_N, "metallic_arts");
        allomancy = new KeyMapping("key." + MetallicsArts.MOD_ID + "." + "allomancy", GLFW.GLFW_KEY_M, "metallic_arts");
        hud = new KeyMapping("key." + MetallicsArts.MOD_ID + "." + "hud", GLFW.GLFW_KEY_O, "metallic_arts");

        ClientRegistry.registerKeyBinding(feruchemic);
        ClientRegistry.registerKeyBinding(allomancy);
    }
}
