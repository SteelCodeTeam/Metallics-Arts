package net.rudahee.metallics_arts.modules.custom_items.armors.harmony;

import net.minecraft.resources.ResourceLocation;
import net.rudahee.metallics_arts.MetallicsArts;
import software.bernie.geckolib.model.GeoModel;

public class EttmetalArmorModel extends GeoModel<EttmetalArmor> {
    @Override
    public ResourceLocation getModelResource(EttmetalArmor ettmetalArmor) {
        return new ResourceLocation(MetallicsArts.MOD_ID, "geo/ettmetal_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EttmetalArmor ettmetalArmor) {
        return new ResourceLocation(MetallicsArts.MOD_ID, "textures/armor/ettmetal_armor.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EttmetalArmor ettmetalArmor) {
        return new ResourceLocation(MetallicsArts.MOD_ID, "animations/ettmetal_armor.animation.json");
    }
}
