package net.rudahee.metallics_arts.modules.logic.client.custom_renders;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.modules.custom_projectiles.BulletProjectile;

public class ModEntityRender extends EntityRenderer<BulletProjectile> {

    private static final ResourceLocation BULLET_TEXTURE = new ResourceLocation(MetallicsArts.MOD_ID, "textures/entity/bullet/bullet");//new ResourceLocation("textures/entity/bullet/bullet.png");

    public ModEntityRender(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public ResourceLocation getTextureLocation(BulletProjectile projectile) {
        return BULLET_TEXTURE;
    }
}
