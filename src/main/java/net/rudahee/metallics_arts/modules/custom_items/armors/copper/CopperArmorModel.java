package net.rudahee.metallics_arts.modules.custom_items.armors.copper;

import net.minecraft.resources.ResourceLocation;
import net.rudahee.metallics_arts.MetallicsArts;
import software.bernie.geckolib.model.GeoModel;

public class CopperArmorModel extends GeoModel<CopperArmor> {


    @Override
    public ResourceLocation getModelResource(CopperArmor copperArmor) {
        return new ResourceLocation(MetallicsArts.MOD_ID, "geo/copper_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(CopperArmor copperArmor) {
        return new ResourceLocation(MetallicsArts.MOD_ID, "textures/armor/copper_armor.png");
    }

    @Override
    public ResourceLocation getAnimationResource(CopperArmor copperArmor) {
        return new ResourceLocation(MetallicsArts.MOD_ID, "animations/copper_armor.animation.json");
    }
}
