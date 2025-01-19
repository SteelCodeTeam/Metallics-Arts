package net.rudahee.metallics_arts.modules.custom_block_entities.hemalurgy_altar_block.back;

import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class HemalurgyAltarBackRenderer extends GeoBlockRenderer<HemalurgyAltarBackBlockEntity> {
    public HemalurgyAltarBackRenderer(BlockEntityRendererProvider.Context context) {
        super(new HemalurgyAltarBackModel());
    }
}
