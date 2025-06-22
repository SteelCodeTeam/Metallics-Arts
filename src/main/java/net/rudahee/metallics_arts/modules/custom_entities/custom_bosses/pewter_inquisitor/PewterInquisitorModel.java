package net.rudahee.metallics_arts.modules.custom_entities.custom_bosses.pewter_inquisitor;

import net.minecraft.resources.ResourceLocation;
import net.rudahee.metallics_arts.MetallicsArts;
import software.bernie.geckolib.model.GeoModel;

public class PewterInquisitorModel extends GeoModel<PewterInquisitor>  {
    @Override
    public ResourceLocation getModelResource(PewterInquisitor animatable) {
        return new ResourceLocation(MetallicsArts.MOD_ID, "geo/iron_inquisitor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(PewterInquisitor animatable) {
        return new ResourceLocation(MetallicsArts.MOD_ID, "textures/entity/living/iron_inquisitor.png");
    }

    @Override
    public ResourceLocation getAnimationResource(PewterInquisitor animatable) {
        return new ResourceLocation(MetallicsArts.MOD_ID, "animations/iron_inquisitor.animation.json");
    }


}
