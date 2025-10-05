package net.rudahee.metallics_arts.modules.custom_items.metal_minds.render_metal_minds;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.client.ICurioRenderer;

public class MetalMindCurioRenderer implements ICurioRenderer {
    private static final MetalMindRenderer renderer = new MetalMindRenderer();

    @Override
    public <T extends LivingEntity, M extends EntityModel<T>> void render(
            ItemStack stack,
            SlotContext slotContext,
            PoseStack poseStack,
            RenderLayerParent<T, M> renderLayerParent,
            MultiBufferSource buffer,
            int light,
            float limbSwing,
            float limbSwingAmount,
            float partialTicks,
            float ageInTicks,
            float netHeadYaw,
            float headPitch
    ) {
        int slot = slotContext.index();
        if (slot < 0 || slot > 3) return;

        poseStack.pushPose();
        // right - down - forward
        if (renderLayerParent.getModel() instanceof HumanoidModel<?> humanoidModel) {
            switch (slot) {
                case 0 -> {
                    // rirht arm up
                    humanoidModel.rightArm.translateAndRotate(poseStack);
                    poseStack.translate(-0.9D, -1.7D, -0.5D);
                }
                case 1 -> {
                    // rirht arm down
                    humanoidModel.rightArm.translateAndRotate(poseStack);
                    poseStack.translate(-0.9D, -1.5D, -0.5D);
                }
                case 2 -> {
                    // left arm up
                    humanoidModel.leftArm.translateAndRotate(poseStack);
                    poseStack.translate(-0.85D, -1.7D, -0.5D);
                }
                case 3 -> {
                    // left arm down
                    humanoidModel.leftArm.translateAndRotate(poseStack);
                    poseStack.translate(-0.85D, -1.5D, -0.5D);
                }
            }
        }

        renderer.renderByItem(
                stack,
                ItemDisplayContext.NONE,
                poseStack,
                buffer,
                light,
                OverlayTexture.NO_OVERLAY
        );

        poseStack.popPose();
    }
}
