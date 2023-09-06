package net.rudahee.metallics_arts.modules.custom_entities.example_entity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.rudahee.metallics_arts.MetallicsArts;

public class ExampleEntityModel extends EntityModel<ExampleEntity> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(MetallicsArts.MOD_ID, "example_entity"), "main");

	private final ModelPart body;

	public ExampleEntityModel(ModelPart root) {
		this.body = root.getChild("body");
	}

	public static LayerDefinition createBodyLayer() {
		var mesh = new MeshDefinition();
		PartDefinition parts = mesh.getRoot();

		PartDefinition body = parts.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -16.0F, -8.0F, 16.0F, 7.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		body.addOrReplaceChild("sidetassels", CubeListBuilder.create().texOffs(0, 50).addBox(8.0F, -15.0F, -4.0F, 1.0F, 10.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(24, 45).addBox(-9.0F, -15.0F, -4.0F, 1.0F, 10.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		body.addOrReplaceChild("hump", CubeListBuilder.create().texOffs(0, 33).addBox(-7.0F, -19.0F, -1.0F, 14.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(0, 23).addBox(-7.0F, -18.0F, -1.0F, 14.0F, 2.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(36, 23).addBox(-7.0F, -17.0F, -6.0F, 14.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(37, 36).addBox(-5.0F, -18.0F, -13.0F, 10.0F, 12.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(24, 41).addBox(-1.0F, -7.0F, -14.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 41).addBox(3.0F, -22.0F, -12.0F, 1.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(48, 11).addBox(1.0F, -20.0F, -12.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(10, 50).addBox(-3.0F, -20.0F, -12.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(0, 23).addBox(-4.0F, -22.0F, -12.0F, 1.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition legs = body.addOrReplaceChild("legs", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
		legs.addOrReplaceChild("backright", CubeListBuilder.create().texOffs(48, 0).addBox(-8.0F, -9.0F, 6.0F, 2.0F, 9.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		legs.addOrReplaceChild("frontleft", CubeListBuilder.create().texOffs(56, 0).addBox(6.0F, -9.0F, -8.0F, 2.0F, 9.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		legs.addOrReplaceChild("frontright", CubeListBuilder.create().texOffs(42, 53).addBox(-8.0F, -9.0F, -8.0F, 2.0F, 9.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		legs.addOrReplaceChild("backleft", CubeListBuilder.create().texOffs(50, 53).addBox(6.0F, -9.0F, 6.0F, 2.0F, 9.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(mesh, 128, 128);
	}

	@Override
	public void setupAnim(ExampleEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}