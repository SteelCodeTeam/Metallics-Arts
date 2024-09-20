package net.rudahee.metallics_arts.modules.render;

import net.rudahee.metallics_arts.modules.custom_items.armors.items.LerasiumArmorItem;
import net.rudahee.metallics_arts.modules.custom_items.armors.models.LerasiumArmorModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class LerasiumArmorRenderer extends GeoArmorRenderer<LerasiumArmorItem> {
    public LerasiumArmorRenderer() {
        super(new LerasiumArmorModel());
    }
}