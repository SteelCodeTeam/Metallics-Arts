package net.rudahee.metallics_arts.modules.custom_entities.alomancer_entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.modules.custom_entities.alomancer_entity.AlomancerEntity;
import net.rudahee.metallics_arts.modules.custom_entities.alomancer_entity.AlomancerEntityModel;

public class AlomancerEntityRenderer extends MobRenderer<AlomancerEntity, AlomancerEntityModel> {

    private static final ResourceLocation TEXTURE = new ResourceLocation (MetallicsArts.MOD_ID,"textures/entity/living/alomancer_entity.png");
    public AlomancerEntityRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new AlomancerEntityModel(ctx.bakeLayer(AlomancerEntityModel.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(AlomancerEntity entity) {
        return TEXTURE;
    }
}
