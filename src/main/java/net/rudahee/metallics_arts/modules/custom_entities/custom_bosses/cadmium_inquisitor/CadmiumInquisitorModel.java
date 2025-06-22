package net.rudahee.metallics_arts.modules.custom_entities.custom_bosses.cadmium_inquisitor;

import net.minecraft.resources.ResourceLocation;
import net.rudahee.metallics_arts.MetallicsArts;
import software.bernie.geckolib.model.GeoModel;

public class CadmiumInquisitorModel extends GeoModel<CadmiumInquisitor> {
    @Override
    public ResourceLocation getModelResource(CadmiumInquisitor cadmiumInquisitor) {
        return new ResourceLocation(MetallicsArts.MOD_ID, "geo/cadmium_inquisitor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(CadmiumInquisitor cadmiumInquisitor) {
        return new ResourceLocation(MetallicsArts.MOD_ID, "textures/entity/living/cadmium_inquisitor.png");
    }

    @Override
    public ResourceLocation getAnimationResource(CadmiumInquisitor cadmiumInquisitor) {
        return new ResourceLocation(MetallicsArts.MOD_ID, "animations/cadmium_inquisitor.animation.json");
    }
}
