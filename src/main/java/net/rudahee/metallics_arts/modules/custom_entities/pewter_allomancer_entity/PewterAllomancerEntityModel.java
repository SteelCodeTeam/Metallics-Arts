package net.rudahee.metallics_arts.modules.custom_entities.pewter_allomancer_entity;// Made with Blockbench 4.6.5
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
import net.rudahee.metallics_arts.modules.custom_entities.iron_allomancer_entity.IronAllomancerEntity;


public class PewterAllomancerEntityModel extends EntityModel<PewterAllomancerEntity>{
// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(MetallicsArts.MOD_ID, "pewter_alllomancer_entity"), "main");
private final ModelPart Head;
private final ModelPart Body;
private final ModelPart LeftArm;
private final ModelPart RightArm;
private final ModelPart LeftLeg;
private final ModelPart RightLeg;

public PewterAllomancerEntityModel(ModelPart root) {
        this.Head = root.getChild("Head");
        this.Body = root.getChild("Body");
        this.LeftArm = root.getChild("LeftArm");
        this.RightArm = root.getChild("RightArm");
        this.LeftLeg = root.getChild("LeftLeg");
        this.RightLeg = root.getChild("RightLeg");
        }

public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition Head = partdefinition.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
        .texOffs(32, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F))
        .texOffs(64, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(-0.25F))
        .texOffs(96, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(-0.05F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition Body = partdefinition.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
        .texOffs(16, 32).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.25F))
        .texOffs(64, 32).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(-0.1F))
        .texOffs(80, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(-0.15F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition LeftArm = partdefinition.addOrReplaceChild("LeftArm", CubeListBuilder.create().texOffs(32, 48).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
        .texOffs(48, 48).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F))
        .texOffs(112, 48).mirror().addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(-0.1F)).mirror(false)
        .texOffs(103, 50).addBox(0.0F, -1.75F, -1.0F, 2.0F, 11.5F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, 2.0F, 0.0F));

        PartDefinition RightArm = partdefinition.addOrReplaceChild("RightArm", CubeListBuilder.create().texOffs(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
        .texOffs(40, 32).mirror().addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)).mirror(false)
        .texOffs(104, 32).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(-0.1F))
        .texOffs(116, 18).mirror().addBox(-2.0F, -1.75F, -1.0F, 2.0F, 11.5F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-5.0F, 2.0F, 0.0F));

        PartDefinition LeftLeg = partdefinition.addOrReplaceChild("LeftLeg", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-1.9F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
        .texOffs(0, 48).addBox(-1.9F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F))
        .texOffs(64, 32).mirror().addBox(-1.9F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(-0.1F)).mirror(false)
        .texOffs(71, 18).addBox(-0.9F, 0.2F, -1.0F, 2.0F, 11.5F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(1.9F, 12.0F, 0.0F));

        PartDefinition RightLeg = partdefinition.addOrReplaceChild("RightLeg", CubeListBuilder.create().texOffs(0, 16).addBox(-2.1F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
        .texOffs(0, 32).addBox(-2.1F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F))
        .texOffs(64, 48).addBox(-2.1F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(-0.1F))
        .texOffs(71, 18).addBox(-1.1F, 0.2F, -1.0F, 2.0F, 11.5F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.9F, 12.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 64);
        }
@Override
public void setupAnim(PewterAllomancerEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
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