package net.rudahee.metallics_arts.modules.custom_block_entities.hemalurgy_altar_block.front;

import net.minecraft.resources.ResourceLocation;
import net.rudahee.metallics_arts.MetallicsArts;
import software.bernie.geckolib.model.GeoModel;

public class HemalurgyAltarFrontBlockItemModel extends GeoModel<HemalurgyAltarFrontBlockItem> {
    @Override
    public ResourceLocation getModelResource(HemalurgyAltarFrontBlockItem hemalurgyAltarFrontBlockItem) {
        return new ResourceLocation(MetallicsArts.MOD_ID, "geo/hemalurgy_altar_front.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(HemalurgyAltarFrontBlockItem hemalurgyAltarFrontBlockItem) {
        return new ResourceLocation(MetallicsArts.MOD_ID, "textures/block/hemalurgy_altar.png");
    }

    @Override
    public ResourceLocation getAnimationResource(HemalurgyAltarFrontBlockItem hemalurgyAltarFrontBlockItem) {
        return new ResourceLocation(MetallicsArts.MOD_ID, "animations/hemalurgy_altar_front.animation.json");
    }
}
