package net.rudahee.metallics_arts.modules.custom_items.armors.mistcloack;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import software.bernie.geckolib.renderer.GeoItemRenderer;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.client.ICurioRenderer;

public class MistCloakRenderer extends GeoItemRenderer<MistCloack> {
    public MistCloakRenderer() {
        super(new MistCloakModel());
    }
}