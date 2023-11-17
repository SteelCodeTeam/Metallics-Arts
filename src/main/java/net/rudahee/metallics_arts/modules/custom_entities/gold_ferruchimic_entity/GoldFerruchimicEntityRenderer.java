package net.rudahee.metallics_arts.modules.custom_entities.gold_ferruchimic_entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.rudahee.metallics_arts.MetallicsArts;

public class GoldFerruchimicEntityRenderer extends MobRenderer<GoldFerruchimicEntity, GoldFerruchimicEntityModel> {

    private static final ResourceLocation TEXTURE = new ResourceLocation (MetallicsArts.MOD_ID,"textures/entity/living/allomancer_entity.png");
    public GoldFerruchimicEntityRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new GoldFerruchimicEntityModel(ctx.bakeLayer(GoldFerruchimicEntityModel.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(GoldFerruchimicEntity entity) {
        return TEXTURE;
    }
}
