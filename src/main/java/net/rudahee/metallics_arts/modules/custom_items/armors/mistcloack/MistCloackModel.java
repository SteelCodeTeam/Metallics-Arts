package net.rudahee.metallics_arts.modules.custom_items.armors.mistcloack;

import net.minecraft.resources.ResourceLocation;
import net.rudahee.metallics_arts.MetallicsArts;
import software.bernie.geckolib.model.GeoModel;

public class MistCloackModel extends GeoModel<MistCloack> {


    @Override
    public ResourceLocation getModelResource(MistCloack mistCloack) {
        return new ResourceLocation(MetallicsArts.MOD_ID, "geo/mistcloack/mistcloack.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(MistCloack mistCloack) {
        return new ResourceLocation(MetallicsArts.MOD_ID, "textures/item/armors/mistcloack.png");
    }

    @Override
    public ResourceLocation getAnimationResource(MistCloack mistCloack) {
        return new ResourceLocation(MetallicsArts.MOD_ID, "animations/mistcloack/mistcloack.animation.json");
    }
}
