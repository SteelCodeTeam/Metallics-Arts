package net.rudahee.metallics_arts.setup.integration.jei;


import net.minecraft.resources.ResourceLocation;
import net.rudahee.metallics_arts.MetallicsArts;

//@JeiPlugin
public class MetallicsArtsJei  {

    public ResourceLocation getPluginUid() {
        return new ResourceLocation(MetallicsArts.MOD_ID,"jei_plugin");
    }

}
