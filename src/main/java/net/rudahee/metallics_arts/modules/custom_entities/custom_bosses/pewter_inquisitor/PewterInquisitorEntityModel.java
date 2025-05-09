package net.rudahee.metallics_arts.modules.custom_entities.custom_bosses.pewter_inquisitor;

import net.minecraft.resources.ResourceLocation;
import net.rudahee.metallics_arts.MetallicsArts;
import software.bernie.geckolib.model.GeoModel;

public class PewterInquisitorEntityModel extends GeoModel<PewterInquisitorEntity>  {
    @Override
    public ResourceLocation getModelResource(PewterInquisitorEntity animatable) {
        return new ResourceLocation(MetallicsArts.MOD_ID, "geo/iron_inquisitor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(PewterInquisitorEntity animatable) {
        return new ResourceLocation(MetallicsArts.MOD_ID, "textures/entity/living/iron_inquisitor.png");
    }

    @Override
    public ResourceLocation getAnimationResource(PewterInquisitorEntity animatable) {
        return new ResourceLocation(MetallicsArts.MOD_ID, "animations/iron_inquisitor.animation.json");
    }


}
