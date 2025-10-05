package net.rudahee.metallics_arts.modules.custom_items.metal_minds.render_metal_minds;

import net.rudahee.metallics_arts.modules.custom_items.metal_minds.abstracts.MetalmindAbstract;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class MetalMindRenderer extends GeoItemRenderer<MetalmindAbstract> {
    public MetalMindRenderer() {
        super(new MetalMindModel());
    }
}
