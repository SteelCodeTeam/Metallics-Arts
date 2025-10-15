package net.rudahee.metallics_arts.modules.custom_entities.custom_bosses.pewter_inquisitor;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.rudahee.metallics_arts.MetallicsArts;
import software.bernie.geckolib.renderer.GeoEntityRenderer;


public class PewterInquisitorRenderer extends GeoEntityRenderer<PewterInquisitor> {
    public PewterInquisitorRenderer(EntityRendererProvider.Context renderManager) {
            super(renderManager, new PewterInquisitorModel());
    }

    @Override
    public ResourceLocation getTextureLocation(PewterInquisitor animatable) {
        return new ResourceLocation(MetallicsArts.MOD_ID, "textures/entity/living/pewter_inquisitor.png");
    }

    @Override
    public void render(PewterInquisitor entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource
    bufferSource, int packedLight) {

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
