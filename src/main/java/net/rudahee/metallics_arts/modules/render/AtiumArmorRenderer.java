package net.rudahee.metallics_arts.modules.render;

import net.rudahee.metallics_arts.modules.custom_items.armors.items.AtiumArmorItem;
import net.rudahee.metallics_arts.modules.custom_items.armors.models.AtiumArmorModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class AtiumArmorRenderer extends GeoArmorRenderer<AtiumArmorItem> {
    public AtiumArmorRenderer() {
        super(new AtiumArmorModel());
    }
}