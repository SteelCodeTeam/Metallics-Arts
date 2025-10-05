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
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.Animation;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
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

        if (slotContext.entity() == null) return;

        String[] bodyBones = {"mistcloak", "back_cloth", "bone", "bone2", "bone3"};
        CoreGeoBone armorHead = renderer.getGeoModel().getAnimationProcessor().getBone("armorHead");

        // ========================
        // Parte 1: capa + tiras
        // ========================
        poseStack.pushPose();

        if (renderLayerParent.getModel() instanceof HumanoidModel<?> humanoidModel) {
            humanoidModel.body.translateAndRotate(poseStack); // mover con el torso
        }


        poseStack.mulPose(Axis.XP.rotationDegrees(180f));
        poseStack.mulPose(Axis.YP.rotationDegrees(180f));
            //right - up - front
        poseStack.translate(-0.5D, -2.0D, -0.5D); // ajuste fino de posición

        // Ocultar capucha
        if (armorHead != null) armorHead.setHidden(true);

        // Mostrar capa y tiras
        for (String name : bodyBones) {
            CoreGeoBone bone = renderer.getGeoModel().getAnimationProcessor().getBone(name);
            if (bone != null) bone.setHidden(false);
        }

        renderer.renderByItem(stack, ItemDisplayContext.NONE, poseStack, buffer, light, OverlayTexture.NO_OVERLAY);
        poseStack.popPose();

        // ========================
        // Parte 2: capucha
        // ========================
        poseStack.pushPose();

        if (renderLayerParent.getModel() instanceof HumanoidModel<?> humanoidModel) {
            humanoidModel.head.translateAndRotate(poseStack); // mover con la cabeza
        }
        poseStack.mulPose(Axis.XP.rotationDegrees(180f));
        poseStack.mulPose(Axis.YP.rotationDegrees(180f));
        poseStack.translate(-0.5D, -2.0D, -0.5D); // ajuste fino de posición

        // Mostrar solo la capucha
        if (armorHead != null) armorHead.setHidden(false);

        // Ocultar capa y tiras
        for (String name : bodyBones) {
            CoreGeoBone bone = renderer.getGeoModel().getAnimationProcessor().getBone(name);
            if (bone != null) bone.setHidden(true);
        }

        renderer.renderByItem(stack, ItemDisplayContext.NONE, poseStack, buffer, light, OverlayTexture.NO_OVERLAY);

        // Restaurar visibilidad de todos los bones
        if (armorHead != null) armorHead.setHidden(false);
        for (String name : bodyBones) {
            CoreGeoBone bone = renderer.getGeoModel().getAnimationProcessor().getBone(name);
            if (bone != null) bone.setHidden(false);
        }
        poseStack.popPose();


        LivingEntity entity = slotContext.entity();


    }

}
