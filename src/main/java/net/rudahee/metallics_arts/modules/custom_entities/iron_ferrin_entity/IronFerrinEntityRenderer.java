package net.rudahee.metallics_arts.modules.custom_entities.iron_ferrin_entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.rudahee.metallics_arts.MetallicsArts;

public class IronFerrinEntityRenderer extends MobRenderer<IronFerrinEntity, IronFerrinEntityModel> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(MetallicsArts.MOD_ID, "textures/entity/living/allomancer_entity.png");

    public IronFerrinEntityRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new IronFerrinEntityModel(ctx.bakeLayer(IronFerrinEntityModel.LAYER_LOCATION)), 0.5f);

    }

    @Override
    public ResourceLocation getTextureLocation(IronFerrinEntity entity) {
        return TEXTURE;
    }
}