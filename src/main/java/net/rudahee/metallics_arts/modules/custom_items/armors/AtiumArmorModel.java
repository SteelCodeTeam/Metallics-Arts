package net.rudahee.metallics_arts.modules.custom_items.armors;

import net.minecraft.resources.ResourceLocation;
import net.rudahee.metallics_arts.MetallicsArts;
import software.bernie.geckolib.model.GeoModel;

public class AtiumArmorModel extends GeoModel<AtiumArmorItem> {
    @Override
    public ResourceLocation getModelResource(AtiumArmorItem animatable) {
        return new ResourceLocation(MetallicsArts.MOD_ID, "geo/atium_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(AtiumArmorItem animatable) {
        return new ResourceLocation(MetallicsArts.MOD_ID, "textures/armor/atium_armor.png");
    }

    @Override
    public ResourceLocation getAnimationResource(AtiumArmorItem animatable) {
        return new ResourceLocation(MetallicsArts.MOD_ID, "animations/atium_armor.animation.json");
    }
}
