package net.rudahee.metallics_arts.modules.custom_items.armors.atium;

import net.minecraft.resources.ResourceLocation;
import net.rudahee.metallics_arts.MetallicsArts;
import software.bernie.geckolib.model.GeoModel;

public class AtiumArmorModel extends GeoModel<AtiumArmor> {


    @Override
    public ResourceLocation getModelResource(AtiumArmor atiumArmor) {
        return new ResourceLocation(MetallicsArts.MOD_ID, "geo/atium_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(AtiumArmor atiumArmor) {
        return new ResourceLocation(MetallicsArts.MOD_ID, "textures/armor/atium_armor.png");
    }

    @Override
    public ResourceLocation getAnimationResource(AtiumArmor atiumArmor) {
        return new ResourceLocation(MetallicsArts.MOD_ID, "animations/atium_armor.animation.json");
    }
}
