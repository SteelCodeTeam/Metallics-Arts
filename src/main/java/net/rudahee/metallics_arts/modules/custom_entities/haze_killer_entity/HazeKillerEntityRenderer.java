package net.rudahee.metallics_arts.modules.custom_entities.haze_killer_entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.rudahee.metallics_arts.MetallicsArts;


public class HazeKillerEntityRenderer extends MobRenderer<HazeKillerEntity, HazeKillerEntityModel> {

    private static final ResourceLocation TEXTURE = new ResourceLocation (MetallicsArts.MOD_ID,"textures/entity/living/allomancer_entity.png");
    public HazeKillerEntityRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new HazeKillerEntityModel(ctx.bakeLayer(HazeKillerEntityModel.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(HazeKillerEntity entity) {
        return TEXTURE;
    }

}
