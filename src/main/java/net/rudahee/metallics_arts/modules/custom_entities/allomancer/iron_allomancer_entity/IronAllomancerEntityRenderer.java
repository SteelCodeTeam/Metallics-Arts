package net.rudahee.metallics_arts.modules.custom_entities.allomancer.iron_allomancer_entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.rudahee.metallics_arts.MetallicsArts;

public class IronAllomancerEntityRenderer extends MobRenderer<IronAllomancerEntity, IronAllomancerEntityModel> {

    private static final ResourceLocation TEXTURE = new ResourceLocation (MetallicsArts.MOD_ID,"textures/entity/living/allomancer_entity.png");
    public IronAllomancerEntityRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new IronAllomancerEntityModel(ctx.bakeLayer(IronAllomancerEntityModel.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(IronAllomancerEntity entity) {
        return TEXTURE;
    }
}
