package net.rudahee.metallics_arts.modules.powers;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.play.client.CPlayerAbilitiesPacket;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.rudahee.metallics_arts.modules.player.InvestedPlayerCapability;
import net.rudahee.metallics_arts.modules.powers.helpers.PewterHelper;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;

import java.util.List;

public class PowersEventHandler {


    @SubscribeEvent
    public static void onDamageEvent(final LivingHurtEvent event) {
        if (event.getSource().getDirectEntity() instanceof ServerPlayerEntity) {
            ServerPlayerEntity playerEntity = (ServerPlayerEntity) event.getSource().getEntity();

            playerEntity.getCapability(InvestedPlayerCapability.PLAYER_CAP).ifPresent(
                playerCapability -> {

                    /*

                     DAMAGE WITH - PEWTER -

                     */
                    if (playerCapability.isBurning(MetalsNBTData.PEWTER)) {
                        float amountDamage = event.getAmount();
                        amountDamage =  amountDamage + PewterHelper.getExtraDamageWithItemInHand(playerEntity.getMainHandItem().getItem());

                        /* PEWTER + DURALUMIN */

                        if (playerCapability.isBurning(MetalsNBTData.DURALUMIN)) {
                            amountDamage = PewterHelper.getDamageWithMultiplier(amountDamage);
                        } else {
                            amountDamage = PewterHelper.getDamageWithIncrement(amountDamage);
                        }
                        event.setAmount(amountDamage);
                    }
                });

        }
    }


    @SubscribeEvent
    public static void onWorldTickEvent(final TickEvent.WorldTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {

            World world = event.world;
            List<? extends PlayerEntity> playerList = world.players();

            for (int playerIndex = playerList.size() - 1; playerIndex >= 0; playerIndex--) {

                PlayerEntity player = playerList.get(playerIndex);

                player.getCapability(InvestedPlayerCapability.PLAYER_CAP).ifPresent(
                        playerCapability ->{

                            if (!playerCapability.isUninvested()) {

                                /*

                                    TICK WITH - TIN -

                                 */

                                if (playerCapability.isBurning(MetalsNBTData.TIN)) {
                                    player.addEffect(new EffectInstance(Effects.NIGHT_VISION, Short.MAX_VALUE, 5, true, false));
                                    if (player.hasEffect(Effects.BLINDNESS)) {
                                        player.removeEffect(Effects.BLINDNESS);
                                    }
                                }


                            }
                    });

            }
        }
    }
}
