package net.rudahee.metallics_arts.modules.custom_entities.ferrin.brass_ferrin_entity;

import net.minecraft.resources.ResourceLocation;
import net.rudahee.metallics_arts.MetallicsArts;
import software.bernie.geckolib.model.GeoModel;

public class BrassFerrinEntityModel extends GeoModel<BrassFerrinEntity> {

    @Override
    public ResourceLocation getModelResource(BrassFerrinEntity animatable) {
        return new ResourceLocation(MetallicsArts.MOD_ID, "geo/brass_ferrin.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(BrassFerrinEntity animatable) {
        return new ResourceLocation(MetallicsArts.MOD_ID, "textures/entity/living/brass_ferrin.png");
    }

    @Override
    public ResourceLocation getAnimationResource(BrassFerrinEntity animatable) {
        return new ResourceLocation(MetallicsArts.MOD_ID, "animations/brass_ferrin.animation.json");
    }
}
