package net.rudahee.metallics_arts.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.player.data.IInvestedPlayerData;
import net.rudahee.metallics_arts.setup.registries.InvestedPlayerCapabilityRegister;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Minecraft.class)
public class BronzeOutlineOnMinecraftClassMixin {


    @Shadow private static Minecraft instance;

    @Inject(method = "shouldEntityAppearGlowing", at = @At(value = "HEAD"), cancellable = true)
    public void shouldEntityWithPowersActiveAppearGlowing(Entity entity, CallbackInfoReturnable<Boolean> cir) {

        if (instance.player == null) {
            return;
        }

        IInvestedPlayerData playerCap = instance.player.getCapability(InvestedPlayerCapabilityRegister.PLAYER_CAP).orElse(null);

        if (playerCap != null) {
            if (playerCap.isBurning(MetalTagEnum.BRONZE)) {
                if (instance.player.distanceTo(entity) < 32) {
                    cir.setReturnValue(true);
                }
            }
        }

        IInvestedPlayerData otherPlayerCap = null;
        if (entity instanceof Player otherPlayer) {
            otherPlayerCap = otherPlayer.getCapability(InvestedPlayerCapabilityRegister.PLAYER_CAP).orElse(null);
        }


        if (playerCap != null && otherPlayerCap != null) {

            if (playerCap.isBurning(MetalTagEnum.BRONZE) && !otherPlayerCap.isBurning(MetalTagEnum.COPPER)) {
                if (otherPlayerCap.isBurningAnything()) {
                    if (instance.player.distanceTo(entity) < 48) {
                        cir.setReturnValue(true);
                    }
                }
            }
        }
    }

}
