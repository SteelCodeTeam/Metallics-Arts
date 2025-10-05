package net.rudahee.metallics_arts.modules.custom_items.metal_minds.render_metal_minds;

import net.minecraft.resources.ResourceLocation;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.data.enums.implementations.MetalmindType;
import net.rudahee.metallics_arts.modules.custom_items.metal_minds.abstracts.MetalmindAbstract;
import software.bernie.geckolib.model.GeoModel;

public class MetalMindModel extends GeoModel<MetalmindAbstract> {

    @Override
    public ResourceLocation getModelResource(MetalmindAbstract animatable) {
        return new ResourceLocation(MetallicsArts.MOD_ID, "geo/metal_mind.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(MetalmindAbstract animatable) {

        String metal1 = animatable.getMetals(0).getNameLower();
        String metal2 = animatable.getMetals(1).getNameLower();
        String path = animatable.isBand(MetalmindType.BAND)? "textures/models/metal_mind/band_" + metal1 + "_" + metal2 + ".png":"textures/models/metal_mind/ring_" + metal1 + "_" + metal2 + ".png";

        return new ResourceLocation(MetallicsArts.MOD_ID, path);
    }

    @Override
    public ResourceLocation getAnimationResource(MetalmindAbstract animatable) {
        return new ResourceLocation(MetallicsArts.MOD_ID, "animations/metal_mind.animation.json");
    }
}