package net.rudahee.metallics_arts.setup.registries;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import org.lwjgl.glfw.GLFW;

public class ModKeyRegister {

    public static final String KEY_CATEGORY = "key.category_" + MetallicsArts.MOD_ID;
    public static final String KEY_POWERS_CATEGORY = "key.category_powers_" + MetallicsArts.MOD_ID;
    @OnlyIn(Dist.CLIENT)
    public static KeyMapping ALLOMANTIC_POWER_SELECTOR;
    @OnlyIn(Dist.CLIENT)
    public static KeyMapping FERUCHEMIC_POWER_SELECTOR;
    @OnlyIn(Dist.CLIENT)
    public static KeyMapping VERTICAL_JUMP;
    @OnlyIn(Dist.CLIENT)
    public static KeyMapping SWITCH_OVERLAY;
    @OnlyIn(Dist.CLIENT)
    public static KeyMapping[] powers;
    @OnlyIn(Dist.CLIENT)
    public static KeyMapping FERUCHEMIC_DECANT;
    @OnlyIn(Dist.CLIENT)
    public static KeyMapping FERUCHEMIC_STORAGE;
    @OnlyIn(Dist.CLIENT)
    public static KeyMapping ALLOMANTIC_PUSH;
    @OnlyIn(Dist.CLIENT)
    public static KeyMapping ALLOMANTIC_PULL;

    @OnlyIn(Dist.CLIENT)
    public static KeyMapping RELOAD;
    public static void initKeys(final RegisterKeyMappingsEvent evt) {
        ALLOMANTIC_POWER_SELECTOR =  new KeyMapping("key." + MetallicsArts.MOD_ID + "." + "allomantic", GLFW.GLFW_KEY_M,KEY_CATEGORY);
        evt.register(ALLOMANTIC_POWER_SELECTOR);

        FERUCHEMIC_POWER_SELECTOR =  new KeyMapping("key." + MetallicsArts.MOD_ID + "." + "feruchemic", GLFW.GLFW_KEY_N,KEY_CATEGORY);
        evt.register(FERUCHEMIC_POWER_SELECTOR);

        VERTICAL_JUMP = new KeyMapping("key." + MetallicsArts.MOD_ID + "." + "vertical_jump", GLFW.GLFW_KEY_LEFT_CONTROL,KEY_CATEGORY);
        evt.register(VERTICAL_JUMP);

        SWITCH_OVERLAY = new KeyMapping("key." + MetallicsArts.MOD_ID + "." + "switch_overlay", GLFW.GLFW_KEY_UNKNOWN,KEY_CATEGORY);
        evt.register(SWITCH_OVERLAY);

        FERUCHEMIC_DECANT = new KeyMapping("key." + MetallicsArts.MOD_ID + "." + "feruchemic_decant", GLFW.GLFW_KEY_LEFT_CONTROL,KEY_CATEGORY);
        evt.register(FERUCHEMIC_DECANT);

        FERUCHEMIC_STORAGE = new KeyMapping("key." + MetallicsArts.MOD_ID + "." + "feruchemic_store", GLFW.GLFW_KEY_LEFT_SHIFT,KEY_CATEGORY);
        evt.register(FERUCHEMIC_STORAGE);

        ALLOMANTIC_PUSH = new KeyMapping("key." + MetallicsArts.MOD_ID + "." + "allomantic_push", InputConstants.Type.MOUSE, 1, KEY_CATEGORY);
        evt.register(ALLOMANTIC_PUSH);

        ALLOMANTIC_PULL = new KeyMapping("key." + MetallicsArts.MOD_ID + "." + "allomantic_pull", InputConstants.Type.MOUSE, 0,KEY_CATEGORY);
        evt.register(ALLOMANTIC_PULL);

        RELOAD =  new KeyMapping("key." + MetallicsArts.MOD_ID + "." + "reload", GLFW.GLFW_KEY_R, KEY_CATEGORY);
        evt.register(RELOAD);

        powers = new KeyMapping[MetalTagEnum.values().length];
        for (int i = 0; i< MetalTagEnum.values().length; i++) {
            powers[i] = new KeyMapping("key."+MetallicsArts.MOD_ID + "." + MetalTagEnum.getMetal(i).getNameLower()+"_power", GLFW.GLFW_KEY_UNKNOWN,KEY_POWERS_CATEGORY);
            evt.register(powers[i]);
        }
    }

}
