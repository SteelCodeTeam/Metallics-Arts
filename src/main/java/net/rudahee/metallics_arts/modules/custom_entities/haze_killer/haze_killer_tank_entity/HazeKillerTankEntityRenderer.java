package net.rudahee.metallics_arts.modules.custom_entities.haze_killer.haze_killer_tank_entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.rudahee.metallics_arts.MetallicsArts;

public class HazeKillerTankEntityRenderer extends MobRenderer<HazeKillerTankEntity, HazeKillerTankEntityModel> {

    private static final ResourceLocation TEXTURE = new ResourceLocation (MetallicsArts.MOD_ID,"textures/entity/living/allomancer_entity.png");
    public HazeKillerTankEntityRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new HazeKillerTankEntityModel(ctx.bakeLayer(HazeKillerTankEntityModel.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(HazeKillerTankEntity entity) {
        return TEXTURE;
    }

}
