package net.rudahee.metallics_arts.modules.custom_entities.ferrin.gold_ferrin_entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.rudahee.metallics_arts.MetallicsArts;

public class GoldFerrinEntityRenderer extends MobRenderer<GoldFerrinEntity, GoldFerrinEntityModel> {

    private static final ResourceLocation TEXTURE = new ResourceLocation (MetallicsArts.MOD_ID,"textures/entity/living/allomancer_entity.png");
    public GoldFerrinEntityRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new GoldFerrinEntityModel(ctx.bakeLayer(GoldFerrinEntityModel.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(GoldFerrinEntity entity) {
        return TEXTURE;
    }
}
