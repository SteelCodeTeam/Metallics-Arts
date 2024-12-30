package net.rudahee.metallics_arts.mixin;


import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.rudahee.metallics_arts.modules.custom_items.weapons.guns.RifleWithSpyGlass;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public class ZoomMixin {

    @Inject(at = @At("HEAD"), method = "isScoping", cancellable = true)
    public void getZoomLevel(CallbackInfoReturnable<Boolean> callbackInfo) {
        if (Minecraft.getInstance().player != null) {
            if (Minecraft.getInstance().player.getItemInHand(InteractionHand.MAIN_HAND).getItem() instanceof RifleWithSpyGlass) {
                if (Minecraft.getInstance().options.keyUse.isDown()) {
                    callbackInfo.setReturnValue(true);
                }
            }
        }
    }
}
