package net.rudahee.metallics_arts.modules.logic.server.server_events;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.rudahee.metallics_arts.data.player.data.model.enums.EttmetalStateEnum;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.player.data.IInvestedPlayerData;
import net.rudahee.metallics_arts.modules.custom_items.metal_minds.LerasiumEttmetalMetalmind;
import net.rudahee.metallics_arts.modules.error_handling.exceptions.PlayerException;
import net.rudahee.metallics_arts.setup.network.ModNetwork;
import net.rudahee.metallics_arts.utils.CapabilityUtils;
import top.theillusivec4.curios.api.CuriosApi;

/**
 * Handles events related to a player's death.
 *
 * @author SteelCode Team
 * @since 1.5.1
 */
public class OnLivingDeathEvent {

    /**
     * Resets a player's Allomantic and Feruchemical metal states upon death.
     * This method is triggered when a LivingDeathEvent occurs. It sets the player's
     * Allomantic burning and Feruchemical tapping/storing states for all metals to false.
     * It also sets the player's metal mind equipped state to false for all metal groups.
     * Finally, it syncs the player's capability data with the client.
     *
     * @param event The LivingDeathEvent that triggered this method.
     */
    public static void livingDeath(LivingDeathEvent event) {
        ServerPlayer player = (ServerPlayer) event.getEntity();
        try {
            IInvestedPlayerData capability = CapabilityUtils.getCapability(player);

            if (capability.isTapping(MetalTagEnum.ETTMETAL)) {
                capability.setEttmetalState(EttmetalStateEnum.KEEP_ITEMS);
                //todo aqui deberia poder hacerse algo para recordar en que slots deben quedar los items
                //para en el onlivinddrop asignarlos en su slot correspondiente
                CuriosApi.getCuriosHelper().getEquippedCurios(player).ifPresent(curioData -> {
                    for (int i=0; i < curioData.getSlots(); i++) {
                        if (curioData.getStackInSlot(i).getItem() instanceof LerasiumEttmetalMetalmind) {
                            CompoundTag compoundTag = curioData.getStackInSlot(i).getTag();
                            compoundTag.putInt(MetalTagEnum.ETTMETAL.getNameLower() + "_feruchemic_reserve", 0);
                            curioData.getStackInSlot(i).setTag(compoundTag);
                        }
                    }
                });

            } else if (capability.isStoring(MetalTagEnum.ETTMETAL)) {
                capability.setEttmetalState(EttmetalStateEnum.DELETE_ITEMS);
            }

            for (MetalTagEnum metal : MetalTagEnum.values()) {
                capability.setBurning(metal, false);
                capability.setTapping(metal, false);
                capability.setStoring(metal, false);
                capability.setMetalMindEquiped(metal.getGroup(), false);
            }

            ModNetwork.syncInvestedDataPacket(capability, player);
        } catch (PlayerException ex) {
            ex.printCompleteLog();
        }
    }
}
