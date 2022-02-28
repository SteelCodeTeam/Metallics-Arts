package net.rudahee.metallics_arts.modules.powers;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.world.World;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.rudahee.metallics_arts.modules.DataPlayer.InvestedCapability;
import net.rudahee.metallics_arts.modules.powers.helpers.PewterHelper;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;

import java.util.List;

public class PowersEventHandler {


    @SubscribeEvent
    public static void onDamageEvent(final LivingHurtEvent event) {
        if (event.getSource().getDirectEntity() instanceof ServerPlayerEntity) {
            ServerPlayerEntity playerEntity = (ServerPlayerEntity) event.getSource().getEntity();

            playerEntity.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(
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

                player.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(
                        playerCapability ->{

                            for (MetalsNBTData metal: MetalsNBTData.values()){
                                System.out.println(metal.getNameLower()+":"+player.getMainHandItem().getItem().getTags().contains(metal.getGemNameLower())+"\n");
                                System.out.println(metal.getNameLower()+":"+playerCapability.hasAllomanticPower(metal)+"\n");

                            }


                            if (!playerCapability.isFullInvested()){
                                for (MetalsNBTData metal: MetalsNBTData.values()){
                                    playerCapability.addAllomanticPower(metal);
                                    playerCapability.setMistborn(true);
                                    playerCapability.addFeruchemicPower(metal);
                                    playerCapability.setFullInvested(true);
                                    playerCapability.setFullFeruchemic(true);
                                }

                            }
                    });

            }
        }
    }
}
