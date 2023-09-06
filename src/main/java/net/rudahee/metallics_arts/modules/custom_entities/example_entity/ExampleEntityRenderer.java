package net.rudahee.metallics_arts.modules.custom_entities.example_entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.rudahee.metallics_arts.MetallicsArts;

public class ExampleEntityRenderer extends MobRenderer<ExampleEntity, ExampleEntityModel> {

    private static final ResourceLocation TEXTURE = new ResourceLocation (MetallicsArts.MOD_ID,"textures/example_entity.png");
    public ExampleEntityRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new ExampleEntityModel(ctx.bakeLayer(ExampleEntityModel.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(ExampleEntity entity) {
        return TEXTURE;
    }
}
