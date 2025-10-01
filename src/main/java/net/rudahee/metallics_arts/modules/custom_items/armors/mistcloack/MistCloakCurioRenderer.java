package net.rudahee.metallics_arts.modules.custom_items.armors.mistcloack;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
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

public class MistCloakCurioRenderer implements ICurioRenderer {
    private static final MistCloakRenderer renderer = new MistCloakRenderer();

    @Override
    public <T extends LivingEntity, M extends EntityModel<T>> void render(
            ItemStack stack, SlotContext slotContext,
            PoseStack poseStack, RenderLayerParent<T, M> renderLayerParent,
            MultiBufferSource buffer, int light, float limbSwing,
            float limbSwingAmount, float partialTicks, float ageInTicks,
            float netHeadYaw, float headPitch) {

        poseStack.pushPose();

        if (renderLayerParent.getModel() instanceof HumanoidModel<?> humanoidModel) {
            humanoidModel.body.translateAndRotate(poseStack);
        }


        ICurioRenderer.translateIfSneaking(poseStack, slotContext.entity());
        // right - down - forward
        poseStack.translate(0.5D, 2D, -0.5D);

        //Turn forward
        poseStack.mulPose(Axis.YP.rotationDegrees(180f));
        //Turn up
        poseStack.mulPose(Axis.XP.rotationDegrees(180f));

        poseStack.scale(1.0F, 1.0F, 1.0F);

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
