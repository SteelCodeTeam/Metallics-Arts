package net.rudahee.metallics_arts.modules.custom_entities.custom_bosses.steel_inquisitor;

import net.minecraft.resources.ResourceLocation;
import net.rudahee.metallics_arts.MetallicsArts;
import software.bernie.geckolib.model.GeoModel;

public class SteelInquisitorModel extends GeoModel<SteelInquisitor>  {
    @Override
    public ResourceLocation getModelResource(SteelInquisitor steelInquisitorEntity) {
        return new ResourceLocation(MetallicsArts.MOD_ID, "geo/iron_inquisitor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SteelInquisitor steelInquisitorEntity) {
        return new ResourceLocation(MetallicsArts.MOD_ID, "textures/entity/living/iron_inquisitor.png");
    }

    @Override
    public ResourceLocation getAnimationResource(SteelInquisitor steelInquisitorEntity) {
        return new ResourceLocation(MetallicsArts.MOD_ID, "animations/iron_inquisitor.animation.json");
    }
}
