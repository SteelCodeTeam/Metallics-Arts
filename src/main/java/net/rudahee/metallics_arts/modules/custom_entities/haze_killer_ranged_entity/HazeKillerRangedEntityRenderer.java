package net.rudahee.metallics_arts.modules.custom_entities.haze_killer_ranged_entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.rudahee.metallics_arts.MetallicsArts;


public class HazeKillerRangedEntityRenderer extends MobRenderer<HazeKillerRangedEntity, HazeKillerRangedEntityModel> {

    private static final ResourceLocation TEXTURE = new ResourceLocation (MetallicsArts.MOD_ID,"textures/entity/living/allomancer_entity.png");
    public HazeKillerRangedEntityRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new HazeKillerRangedEntityModel(ctx.bakeLayer(HazeKillerRangedEntityModel.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(HazeKillerRangedEntity entity) {
        return TEXTURE;
    }

}
