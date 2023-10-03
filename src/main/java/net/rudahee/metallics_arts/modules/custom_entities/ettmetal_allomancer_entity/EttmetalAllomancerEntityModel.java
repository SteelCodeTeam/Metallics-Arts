package net.rudahee.metallics_arts.modules.custom_entities.ettmetal_allomancer_entity;// Made with Blockbench 4.6.5
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.rudahee.metallics_arts.MetallicsArts;

public class EttmetalAllomancerEntityModel extends EntityModel<EttmetalAllomancerEntity> {
// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(MetallicsArts.MOD_ID, "ettmetal_alllomancer_entity"), "main");
private final ModelPart Head;
private final ModelPart Body;
private final ModelPart RightArm;
private final ModelPart LeftArm;
private final ModelPart RightLeg;
private final ModelPart LeftLeg;

public EttmetalAllomancerEntityModel(ModelPart root) {
        this.Head = root.getChild("head");
        this.Body = root.getChild("body");
        this.RightArm = root.getChild("right_arm");
        this.LeftArm = root.getChild("left_arm");
        this.RightLeg = root.getChild("right_leg");
        this.LeftLeg = root.getChild("left_leg");
        }

        public static LayerDefinition createBodyLayer() {
                var meshdefinition = new MeshDefinition();
                PartDefinition partdefinition = meshdefinition.getRoot();

                PartDefinition Head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(32, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.1047F, 0.0873F, 0.0F));

                PartDefinition Body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(16, 32).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, 0.0F, 0.0F));

                PartDefinition RightArm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(40, 16).addBox(-2.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(40, 32).addBox(-2.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(-5.0F, 2.0F, 0.0F, -0.1745F, 0.0F, 0.0F));

                PartDefinition LeftArm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(32, 48).addBox(-1.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(48, 48).addBox(-1.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(5.0F, 2.0F, 0.0F, 0.2094F, 0.0F, 0.0F));

                PartDefinition RightLeg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 32).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(-1.9F, 12.0F, 0.0F, 0.192F, 0.0F, 0.0349F));

                PartDefinition LeftLeg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(16, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(1.9F, 12.0F, 0.0F, -0.1745F, 0.0F, -0.0349F));

                return LayerDefinition.create(meshdefinition, 64, 64);
        }

@Override
public void setupAnim(EttmetalAllomancerEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
                this.Head.yRot = netHeadYaw * Mth.DEG_TO_RAD;
                this.Head.xRot = headPitch * Mth.DEG_TO_RAD;
                this.LeftLeg.xRot = Mth.cos(limbSwing*0.662F)* 1.4F * limbSwingAmount;
                this.RightLeg.xRot = Mth.cos(limbSwing*0.662F + (float)Math.PI) * 1.4F * limbSwingAmount;
                this.LeftArm.xRot = Mth.cos(limbSwing*0.662F + (float)Math.PI) * 1.4F * limbSwingAmount;
                this.RightArm.xRot =Mth.cos(limbSwing*0.662F)* 1.4F * limbSwingAmount;

        }

@Override
public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        Head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        Body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        RightArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        LeftArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        RightLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        LeftLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        }
        }