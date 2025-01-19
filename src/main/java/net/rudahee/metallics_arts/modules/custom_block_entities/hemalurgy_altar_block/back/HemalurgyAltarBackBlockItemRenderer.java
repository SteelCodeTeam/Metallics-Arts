package net.rudahee.metallics_arts.modules.custom_block_entities.hemalurgy_altar_block.back;

import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.renderer.GeoBlockRenderer;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class HemalurgyAltarBackBlockItemRenderer extends GeoItemRenderer<HemalurgyAltarBackBlockItem> {
    public HemalurgyAltarBackBlockItemRenderer() {
        super(new HemalurgyAltarBackBlockItemModel());
    }
}
