package net.rudahee.metallics_arts.modules.custom_entities.iron_allomancer_entity.ettmetal_allomancer_entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.rudahee.metallics_arts.MetallicsArts;

public class EttmetalAllomancerEntityRenderer extends MobRenderer<IronAllomancerEntity, EttmetalAllomancerEntityModel> {

    private static final ResourceLocation TEXTURE = new ResourceLocation (MetallicsArts.MOD_ID,"textures/entity/living/allomancer_entity.png");
    public EttmetalAllomancerEntityRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new EttmetalAllomancerEntityModel(ctx.bakeLayer(EttmetalAllomancerEntityModel.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(IronAllomancerEntity entity) {
        return TEXTURE;
    }
}
