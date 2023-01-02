package net.rudahee.metallics_arts.utils.event_utils;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.players.IInvestedPlayerData;
import net.rudahee.metallics_arts.setup.registries.ModItemsRegister;

public class OnWorldTickEvent {
    public static boolean activationEvery30Tick(int actualTick) {
        return actualTick == 30 || actualTick == 60 || actualTick == 90 || actualTick == 120 || actualTick == 150
                || actualTick == 180 || actualTick == 210 || actualTick == 240;
    }
    public static boolean activationEvery80Tick(int actualTick) {
        return actualTick == 80 || actualTick == 160 || actualTick == 240;
    }
    public static void equipKolossBlade(Player player, IInvestedPlayerData playerCapability) {
        if (!(playerCapability.isBurning(MetalTagEnum.PEWTER) || playerCapability.isDecanting(MetalTagEnum.PEWTER))
                && (player.getMainHandItem().getItem() == ModItemsRegister.KOLOSS_BLADE.get() || player.getOffhandItem().getItem() == ModItemsRegister.KOLOSS_BLADE.get())) {

            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 10, 2, true, true, false));
            player.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 10, 2, true, true, false));
            player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 10, 2, true, true, false));
        }
    }
    private static int buffNicrosilDuralumin = -1;
    public static void duraluminAndExternalNicrosilEffect(IInvestedPlayerData playerCapability) {
        if (playerCapability.isBurning(MetalTagEnum.DURALUMIN)) {
            if ((playerCapability.getAllomanticAmount(MetalTagEnum.DURALUMIN) > (MetalTagEnum.DURALUMIN.getMaxAllomanticTicksStorage()*0.88)) || (buffNicrosilDuralumin != -1)){
                if (buffNicrosilDuralumin == -1) {
                    playerCapability.setEnhanced(true);
                    buffNicrosilDuralumin = MetalTagEnum.DURALUMIN.getMaxAllomanticTicksStorage();
                }
                for (MetalTagEnum metal : MetalTagEnum.values()) {
                    if (playerCapability.isBurning(metal) && !playerCapability.containsInListMetalBuff(metal)){
                        playerCapability.addListMetalBuff(metal);
                    }
                }
            } else {
                playerCapability.setBurning(MetalTagEnum.DURALUMIN,false);
            }
        } else if (playerCapability.getEnhanced()) {
            if (buffNicrosilDuralumin == -1) {
                buffNicrosilDuralumin = MetalTagEnum.DURALUMIN.getMaxAllomanticTicksStorage();
            }
            for (MetalTagEnum metal : MetalTagEnum.values()) {
                if (playerCapability.isBurning(metal) && !playerCapability.containsInListMetalBuff(metal)){
                    playerCapability.addListMetalBuff(metal);
                }
            }
        } else {
            if (!playerCapability.getListMetalBuff().isEmpty()) {
                for (MetalTagEnum metal: playerCapability.getListMetalBuff()) {
                    playerCapability.drainMetals(metal);
                }
                playerCapability.clearListMetalBuff();
            }
        }
        if (playerCapability.getEnhanced()){
            buffNicrosilDuralumin--;
            if (buffNicrosilDuralumin == 0) {
                playerCapability.setEnhanced(false);
                buffNicrosilDuralumin = -1;
            }
        }
    }
}
