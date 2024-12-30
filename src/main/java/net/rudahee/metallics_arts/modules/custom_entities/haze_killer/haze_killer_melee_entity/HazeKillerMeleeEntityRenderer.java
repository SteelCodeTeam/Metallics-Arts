package net.rudahee.metallics_arts.modules.custom_entities.haze_killer.haze_killer_melee_entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.rudahee.metallics_arts.MetallicsArts;


public class HazeKillerMeleeEntityRenderer extends MobRenderer<HazeKillerMeleeEntity, HazeKillerMeleeEntityModel> {

    private static final ResourceLocation TEXTURE = new ResourceLocation (MetallicsArts.MOD_ID,"textures/entity/living/allomancer_entity.png");
    public HazeKillerMeleeEntityRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new HazeKillerMeleeEntityModel(ctx.bakeLayer(HazeKillerMeleeEntityModel.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(HazeKillerMeleeEntity entity) {
        return TEXTURE;
    }

}
