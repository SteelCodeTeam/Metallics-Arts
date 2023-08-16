package net.rudahee.metallics_arts.modules.logic.client.custom_renders;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.modules.custom_projectiles.BulletProjectile;
import net.rudahee.metallics_arts.setup.registries.ModItemsRegister;

public class ModEntityRender extends EntityRenderer<BulletProjectile> {



    private static final ResourceLocation BULLET_LOCATION = new ResourceLocation("textures/entity/bullet/bullet.png");

    public ModEntityRender(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public ResourceLocation getTextureLocation(BulletProjectile projectile) {
        return BULLET_LOCATION;
    }
}
