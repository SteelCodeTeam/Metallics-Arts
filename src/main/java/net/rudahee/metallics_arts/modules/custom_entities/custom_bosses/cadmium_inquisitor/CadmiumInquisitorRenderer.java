package net.rudahee.metallics_arts.modules.custom_entities.custom_bosses.cadmium_inquisitor;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.rudahee.metallics_arts.MetallicsArts;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class CadmiumInquisitorRenderer extends GeoEntityRenderer<CadmiumInquisitor> {

    public CadmiumInquisitorRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new CadmiumInquisitorModel());
    }

    @Override
    public ResourceLocation getTextureLocation(CadmiumInquisitor animatable) {
        return new ResourceLocation(MetallicsArts.MOD_ID, "textures/entity/living/cadmium_inquisitor.png");
    }

    @Override
    public void render(CadmiumInquisitor entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
