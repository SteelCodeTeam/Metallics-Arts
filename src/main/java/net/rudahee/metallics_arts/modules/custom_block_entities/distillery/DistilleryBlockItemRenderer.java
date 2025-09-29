package net.rudahee.metallics_arts.modules.custom_block_entities.distillery;

import software.bernie.geckolib.renderer.GeoItemRenderer;

public class DistilleryBlockItemRenderer extends GeoItemRenderer<DistilleryBlockItem> {
    public DistilleryBlockItemRenderer() {
        super(new DistilleryBlockItemModel());
    }
}
