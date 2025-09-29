package net.rudahee.metallics_arts.modules.custom_block_entities.distillery;

import net.minecraft.resources.ResourceLocation;
import net.rudahee.metallics_arts.MetallicsArts;
import software.bernie.geckolib.model.GeoModel;

public class DistilleryBlockModel extends GeoModel<DistilleryBlockEntity> {
    @Override
    public ResourceLocation getModelResource(DistilleryBlockEntity distilleryBlockEntity) {
        return new ResourceLocation(MetallicsArts.MOD_ID, "geo/distillery.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(DistilleryBlockEntity distilleryBlockEntity) {
        return new ResourceLocation(MetallicsArts.MOD_ID, "textures/block/distillery.png");
    }

    @Override
    public ResourceLocation getAnimationResource(DistilleryBlockEntity distilleryBlockEntity) {
        return new ResourceLocation(MetallicsArts.MOD_ID, "animations/distillery.animation.json");
    }
}
