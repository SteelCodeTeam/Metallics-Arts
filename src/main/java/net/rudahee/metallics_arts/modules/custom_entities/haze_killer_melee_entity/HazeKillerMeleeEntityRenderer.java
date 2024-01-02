package net.rudahee.metallics_arts.modules.custom_entities.haze_killer_entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.rudahee.metallics_arts.MetallicsArts;


public class HazeKillerEntityMeleeRenderer extends MobRenderer<HazeKillerEntityMelee, HazeKillerEntityMeleeModel> {

    private static final ResourceLocation TEXTURE = new ResourceLocation (MetallicsArts.MOD_ID,"textures/entity/living/allomancer_entity.png");
    public HazeKillerEntityMeleeRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new HazeKillerEntityMeleeModel(ctx.bakeLayer(HazeKillerEntityMeleeModel.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(HazeKillerEntityMelee entity) {
        return TEXTURE;
    }

}
