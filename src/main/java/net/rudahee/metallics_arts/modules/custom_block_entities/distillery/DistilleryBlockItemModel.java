package net.rudahee.metallics_arts.modules.custom_block_entities.distillery;

import net.minecraft.resources.ResourceLocation;
import net.rudahee.metallics_arts.MetallicsArts;
import software.bernie.geckolib.model.GeoModel;

public class DistilleryBlockItemModel extends GeoModel<DistilleryBlockItem> {
    @Override
    public ResourceLocation getModelResource(DistilleryBlockItem animatable) {
        return new ResourceLocation(MetallicsArts.MOD_ID, "geo/distillery.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(DistilleryBlockItem animatable) {
        return new ResourceLocation(MetallicsArts.MOD_ID, "textures/block/distillery_item.png");
    }

    @Override
    public ResourceLocation getAnimationResource(DistilleryBlockItem animatable) {
        return new ResourceLocation(MetallicsArts.MOD_ID, "animations/distillery.animation.json");
    }
}
