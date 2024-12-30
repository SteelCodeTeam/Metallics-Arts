package net.rudahee.metallics_arts.modules.custom_entities.allomancer.pewter_allomancer_entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.rudahee.metallics_arts.MetallicsArts;

public class PewterAllomancerEntityRenderer extends MobRenderer<PewterAllomancerEntity, PewterAllomancerEntityModel> {

    private static final ResourceLocation TEXTURE = new ResourceLocation (MetallicsArts.MOD_ID,"textures/entity/living/allomancer_entity.png");
    public PewterAllomancerEntityRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new PewterAllomancerEntityModel(ctx.bakeLayer(PewterAllomancerEntityModel.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(PewterAllomancerEntity entity) {
        return TEXTURE;
    }
}
