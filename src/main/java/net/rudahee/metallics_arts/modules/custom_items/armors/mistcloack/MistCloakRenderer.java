package net.rudahee.metallics_arts.modules.custom_items.armors.mistcloack;

import net.minecraft.world.entity.LivingEntity;

import software.bernie.geckolib.renderer.GeoItemRenderer;

public class MistCloakRenderer extends GeoItemRenderer<MistCloack> {

    private LivingEntity currentEntity; // entidad temporal

    public MistCloakRenderer() {
        super(new MistCloakModel());
    }
}