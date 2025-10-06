package net.rudahee.metallics_arts.modules.custom_items.armors.mistcloack;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import net.rudahee.metallics_arts.MetallicsArts;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;

public class MistCloakModel extends GeoModel<MistCloack> {
    @Override
    public ResourceLocation getModelResource(MistCloack animatable) {
        return new ResourceLocation(MetallicsArts.MOD_ID, "geo/mist_cloak.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(MistCloack animatable) {
        return new ResourceLocation(MetallicsArts.MOD_ID, "textures/armor/mist_cloak.png");
    }

    @Override
    public ResourceLocation getAnimationResource(MistCloack animatable) {
        return new ResourceLocation(MetallicsArts.MOD_ID, "animations/mist_cloak.animation.json");
    }



}

