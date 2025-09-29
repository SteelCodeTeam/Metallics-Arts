package net.rudahee.metallics_arts.modules.custom_block_entities.distillery;

import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class DistilleryBlockRenderer extends GeoBlockRenderer<DistilleryBlockEntity> {
    public DistilleryBlockRenderer(BlockEntityRendererProvider.Context context) {
        super(new DistilleryBlockModel());
    }
}
