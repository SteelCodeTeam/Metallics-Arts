package net.rudahee.metallics_arts.modules.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.ToggleKeyMapping;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.rudahee.metallics_arts.MetallicsArts;
import org.lwjgl.glfw.GLFW;

@OnlyIn(Dist.CLIENT)
public class KeyInit {

    public static final String KEY_CATEGORY = "key.category" + MetallicsArts.MOD_ID;
    public static final String FERUCHEMIC_KEY = "key." + MetallicsArts.MOD_ID + "." + "feruchemic";
    public static final String ALLOMANTIC_KEY = "key." + MetallicsArts.MOD_ID + "." + "allomantic";

    public static final String VERTICAL_JUMP_KEY = "key." + MetallicsArts.MOD_ID + "." + "vertical_jump";

    public static final String ALLOMANTIC_PUSH_KEY = "key." + MetallicsArts.MOD_ID + "." + "allomantic_push";
    public static final String ALLOMANTIC_PULL_KEY = "key." + MetallicsArts.MOD_ID + "." + "allomantic_pull";

    public static final String TOGGLE_GUI_KEY = "key." + MetallicsArts.MOD_ID + "." + "toggle_gui";

    public static final KeyMapping ALLOMANTIC_POWER_SELECTOR = new KeyMapping(ALLOMANTIC_KEY,
            KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_M, KEY_CATEGORY);

    public static final KeyMapping FERUCHEMIC_POWER_SELECTOR = new KeyMapping(FERUCHEMIC_KEY,
            KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_N, KEY_CATEGORY);

    public static final KeyMapping VERTICAL_JUMP = new KeyMapping(VERTICAL_JUMP_KEY,
            KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_LEFT_CONTROL, KEY_CATEGORY);

    public static final KeyMapping ALLOMANTIC_PUSH = new KeyMapping(ALLOMANTIC_PUSH_KEY,
            KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_J, KEY_CATEGORY);

    public static final KeyMapping ALLOMANTIC_PULL = new KeyMapping(ALLOMANTIC_PULL_KEY,
            KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_K, KEY_CATEGORY);

}