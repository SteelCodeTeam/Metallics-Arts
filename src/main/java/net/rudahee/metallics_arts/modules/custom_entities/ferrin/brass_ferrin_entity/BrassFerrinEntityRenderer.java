package net.rudahee.metallics_arts.modules.custom_entities.ferrin.brass_ferrin_entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.rudahee.metallics_arts.MetallicsArts;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class BrassFerrinEntityRenderer extends GeoEntityRenderer<BrassFerrinEntity> {
    public BrassFerrinEntityRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new BrassFerrinEntityModel());
    }

    @Override
    public ResourceLocation getTextureLocation(BrassFerrinEntity animatable) {
        return new ResourceLocation(MetallicsArts.MOD_ID, "textures/entity/living/brass_ferrin.png");
    }

    @Override
    public void render(BrassFerrinEntity entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        if (entity.wasOnFire) {
            poseStack.scale(1.2f, 1.2f, 1.2f);
        }

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
