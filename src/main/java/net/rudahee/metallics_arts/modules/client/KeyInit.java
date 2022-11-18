package net.rudahee.metallics_arts.modules.client;

import net.minecraft.client.KeyMapping;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;
import org.lwjgl.glfw.GLFW;

public class KeyInit {

    public static final String KEY_CATEGORY = "key.category_" + MetallicsArts.MOD_ID;

    public static final String KEY_POWERS_CATEGORY = "key.category_powers_" + MetallicsArts.MOD_ID;
    @OnlyIn(Dist.CLIENT)
    public static KeyMapping ALLOMANTIC_POWER_SELECTOR;
    @OnlyIn(Dist.CLIENT)
    public static KeyMapping FERUCHEMIC_POWER_SELECTOR;
    @OnlyIn(Dist.CLIENT)
    public static KeyMapping VERTICAL_JUMP;

    @OnlyIn(Dist.CLIENT)
    public static KeyMapping[] powers;

    @OnlyIn(Dist.CLIENT)
    public static KeyMapping FERUCHEMIC_DECANT;

    @OnlyIn(Dist.CLIENT)
    public static KeyMapping FERUCHEMIC_STORAGE;

    public static void initKeys(final RegisterKeyMappingsEvent evt){
        ALLOMANTIC_POWER_SELECTOR =  new KeyMapping("key." + MetallicsArts.MOD_ID + "." + "allomantic", GLFW.GLFW_KEY_M,KEY_CATEGORY);
        evt.register(ALLOMANTIC_POWER_SELECTOR);
        FERUCHEMIC_POWER_SELECTOR =  new KeyMapping("key." + MetallicsArts.MOD_ID + "." + "feruchemic", GLFW.GLFW_KEY_N,KEY_CATEGORY);
        evt.register(FERUCHEMIC_POWER_SELECTOR);
        VERTICAL_JUMP = new KeyMapping("key." + MetallicsArts.MOD_ID + "." + "vertical_jump", GLFW.GLFW_KEY_LEFT_CONTROL,KEY_CATEGORY);
        evt.register(VERTICAL_JUMP);

        FERUCHEMIC_DECANT = new KeyMapping("key." + MetallicsArts.MOD_ID + "." + "feruchemic_decant", GLFW.GLFW_KEY_LEFT_CONTROL,KEY_CATEGORY);
        evt.register(FERUCHEMIC_DECANT);
        FERUCHEMIC_STORAGE = new KeyMapping("key." + MetallicsArts.MOD_ID + "." + "feruchemic_store", GLFW.GLFW_KEY_LEFT_SHIFT,KEY_CATEGORY);
        evt.register(FERUCHEMIC_STORAGE);

        powers = new KeyMapping[MetalsNBTData.values().length];
        for (int i = 0; i<MetalsNBTData.values().length; i++) {
            powers[i] = new KeyMapping("key."+MetallicsArts.MOD_ID + "." + MetalsNBTData.getMetal(i).getNameLower()+"_power", GLFW.GLFW_KEY_UNKNOWN,KEY_POWERS_CATEGORY);
            evt.register(powers[i]);
        }
    }

}
