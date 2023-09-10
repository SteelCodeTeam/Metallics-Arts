package net.rudahee.metallics_arts.modules.custom_entities.alomancer_entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.rudahee.metallics_arts.MetallicsArts;

public class EttmetalAllomancerEntityRenderer extends MobRenderer<EttmetalAllomancerEntity, EttmetalAllomancerEntityModel> {

    private static final ResourceLocation TEXTURE = new ResourceLocation (MetallicsArts.MOD_ID,"textures/entity/living/alomancer_entity.png");
    public EttmetalAllomancerEntityRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new EttmetalAllomancerEntityModel(ctx.bakeLayer(EttmetalAllomancerEntityModel.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(EttmetalAllomancerEntity entity) {
        return TEXTURE;
    }
}
