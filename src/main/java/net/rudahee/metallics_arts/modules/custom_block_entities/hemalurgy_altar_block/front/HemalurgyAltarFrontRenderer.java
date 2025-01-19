package net.rudahee.metallics_arts.modules.custom_block_entities.hemalurgy_altar_block.front;

import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class HemalurgyAltarFrontRenderer extends GeoBlockRenderer<HemalurgyAltarFrontBlockEntity> {
    public HemalurgyAltarFrontRenderer(BlockEntityRendererProvider.Context context) {
        super(new HemalurgyAltarFrontModel());
    }
}
