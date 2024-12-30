package net.rudahee.metallics_arts.modules.custom_entities.allomancer.steel_allomancer_entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.rudahee.metallics_arts.MetallicsArts;

public class SteelAllomancerEntityRenderer extends MobRenderer<SteelAllomancerEntity, SteelAllomancerEntityModel> {

    private static final ResourceLocation TEXTURE = new ResourceLocation (MetallicsArts.MOD_ID,"textures/entity/living/allomancer_entity.png");
    public SteelAllomancerEntityRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new SteelAllomancerEntityModel(ctx.bakeLayer(SteelAllomancerEntityModel.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(SteelAllomancerEntity entity) {
        return TEXTURE;
    }
}
