package net.rudahee.metallics_arts.modules.logic.client.custom_renders;

import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.modules.custom_projectiles.BulletProjectile;

public class ModEntityRender extends EntityRenderer<BulletProjectile> {
    public ModEntityRender(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public boolean shouldRender(BulletProjectile p_114491_, Frustum p_114492_, double p_114493_, double p_114494_, double p_114495_) {
        return true;
    }

    @Override
    public ResourceLocation getTextureLocation(BulletProjectile bulletProjectile) {
        return new ResourceLocation(MetallicsArts.MOD_ID, "textures/render/bullet.jpg");
    }

}
