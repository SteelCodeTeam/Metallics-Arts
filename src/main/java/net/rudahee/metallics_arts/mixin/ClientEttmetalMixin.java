package net.rudahee.metallics_arts.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.Minecart;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.player.data.IInvestedPlayerData;
import net.rudahee.metallics_arts.data.player.data.model.enums.EttmetalStateEnum;
import net.rudahee.metallics_arts.setup.network.ModNetwork;
import net.rudahee.metallics_arts.setup.registries.InvestedPlayerCapabilityRegister;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public class ClientEttmetalMixin {
    @Shadow @Final private Inventory inventory;

    @Inject(method = "dropEquipment", at = @At(value = "HEAD"), cancellable = true)
    public void doEttmetalFeruchemicPower(@NotNull CallbackInfo ci) {

        IInvestedPlayerData cap = this.inventory.player.getCapability(InvestedPlayerCapabilityRegister.PLAYER_CAP).orElse(null);

        if (cap != null) {
            if (cap.isTapping(MetalTagEnum.ETTMETAL)) {
                cap.setEttmetalState(EttmetalStateEnum.KEEP_ITEMS);
                ModNetwork.syncInvestedDataPacket(cap, this.inventory.player);
                ci.cancel();
            }
        }

    }
}
