package net.rudahee.metallics_arts.modules.custom_block_entities.hemalurgy_altar_block.back;

import net.minecraft.resources.ResourceLocation;
import net.rudahee.metallics_arts.MetallicsArts;
import software.bernie.geckolib.model.GeoModel;

public class HemalurgyAltarBackModel extends GeoModel<HemalurgyAltarBackBlockEntity> {
    @Override
    public ResourceLocation getModelResource(HemalurgyAltarBackBlockEntity hemalurgyAltarBackBlockEntity) {
        return new ResourceLocation(MetallicsArts.MOD_ID, "geo/hemalurgy_altar_back.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(HemalurgyAltarBackBlockEntity hemalurgyAltarBackBlockEntity) {
        return new ResourceLocation(MetallicsArts.MOD_ID, "textures/block/hemalurgy_altar.png");
    }

    @Override
    public ResourceLocation getAnimationResource(HemalurgyAltarBackBlockEntity hemalurgyAltarBackBlockEntity) {
        return new ResourceLocation(MetallicsArts.MOD_ID, "animations/hemalurgy_altar_back.animation.json");
    }
}
