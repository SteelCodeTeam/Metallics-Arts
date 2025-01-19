package net.rudahee.metallics_arts.modules.custom_block_entities.hemalurgy_altar_block.front;

import net.minecraft.resources.ResourceLocation;
import net.rudahee.metallics_arts.MetallicsArts;
import software.bernie.geckolib.model.GeoModel;

public class HemalurgyAltarFrontModel extends GeoModel<HemalurgyAltarFrontBlockEntity> {
    @Override
    public ResourceLocation getModelResource(HemalurgyAltarFrontBlockEntity hemalurgyAltarFrontBlockEntity) {
        return new ResourceLocation(MetallicsArts.MOD_ID, "geo/hemalurgy_altar_front.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(HemalurgyAltarFrontBlockEntity hemalurgyAltarFrontBlockEntity) {
        return new ResourceLocation(MetallicsArts.MOD_ID, "textures/block/hemalurgy_altar.png");
    }

    @Override
    public ResourceLocation getAnimationResource(HemalurgyAltarFrontBlockEntity hemalurgyAltarFrontBlockEntity) {
        return new ResourceLocation(MetallicsArts.MOD_ID, "animations/hemalurgy_altar_front.animation.json");
    }
}
