package net.rudahee.metallics_arts.modules.custom_items.armors.models;

import net.minecraft.resources.ResourceLocation;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.modules.custom_items.armors.items.LerasiumArmorItem;
import software.bernie.geckolib.model.GeoModel;

public class LerasiumArmorModel extends GeoModel<LerasiumArmorItem> {

    @Override
    public ResourceLocation getModelResource(LerasiumArmorItem lerasiumArmorItem) {
        return new ResourceLocation(MetallicsArts.MOD_ID, "geo/lerasium_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(LerasiumArmorItem lerasiumArmorItem) {
        return new ResourceLocation(MetallicsArts.MOD_ID, "textures/armor/lerasium_armor.png");
    }

    @Override
    public ResourceLocation getAnimationResource(LerasiumArmorItem lerasiumArmorItem) {
        return new ResourceLocation(MetallicsArts.MOD_ID, "animations/lerasium_armor.animation.json");
    }
}

