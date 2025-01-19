package net.rudahee.metallics_arts.modules.custom_items.armors.lerasium;

import net.minecraft.resources.ResourceLocation;
import net.rudahee.metallics_arts.MetallicsArts;
import software.bernie.geckolib.model.GeoModel;

public class LerasiumArmorModel extends GeoModel<LerasiumArmor> {


    @Override
    public ResourceLocation getModelResource(LerasiumArmor lerasiumArmor) {
        return new ResourceLocation(MetallicsArts.MOD_ID, "geo/lerasium_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(LerasiumArmor lerasiumArmor) {
        return new ResourceLocation(MetallicsArts.MOD_ID, "textures/armor/lerasium_armor.png");
    }

    @Override
    public ResourceLocation getAnimationResource(LerasiumArmor lerasiumArmor) {
        return new ResourceLocation(MetallicsArts.MOD_ID, "animations/lerasium_armor.animation.json");
    }
}
