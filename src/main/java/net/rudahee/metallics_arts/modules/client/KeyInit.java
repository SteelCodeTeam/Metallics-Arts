package net.rudahee.metallics_arts.modules.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
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

    public static final String VERTICAL_JUMP_KEY = "key." + MetallicsArts.MOD_ID + "." + "allomantic";


    public static final KeyMapping ALLOMANTIC_POWER_SELECTOR = new KeyMapping(ALLOMANTIC_KEY,
            KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM,GLFW.GLFW_KEY_M,KEY_CATEGORY);

    public static final KeyMapping FERUCHEMIC_POWER_SELECTOR = new KeyMapping(FERUCHEMIC_KEY,
            KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM,GLFW.GLFW_KEY_N,KEY_CATEGORY);

    public static final KeyMapping VERTICAL_JUMP = new KeyMapping(VERTICAL_JUMP_KEY,
            KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM,GLFW.GLFW_KEY_LEFT_CONTROL,KEY_CATEGORY);

}
