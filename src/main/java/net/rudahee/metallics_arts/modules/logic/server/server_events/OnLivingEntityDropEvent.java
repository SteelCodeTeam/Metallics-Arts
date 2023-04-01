package net.rudahee.metallics_arts.modules.logic.server.server_events;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.rudahee.metallics_arts.data.enums.implementations.EttmetalState;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.player.IInvestedPlayerData;
import net.rudahee.metallics_arts.modules.error_handling.exceptions.PlayerException;
import net.rudahee.metallics_arts.utils.CapabilityUtils;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles events related to living entity drops.
 *
 * @author SteelCode Team
 * @since 1.5.1
 */
public class OnLivingEntityDropEvent {

    /**
     * This method is triggered when a LivingDropsEvent occurs. If the player is tapping
     * Zinc, it duplicates the drops, excluding Nether Stars. If the player is storing
     * Zinc, it cancels the event, preventing any drops from being spawned.
     *
     * @param event The LivingDropsEvent that triggered this method.
     */
    public static void livingEntityDrop(LivingDropsEvent event) {
        if (event.getSource().getEntity() instanceof Player && !(event.getEntity() instanceof Player)) {
            try {
                IInvestedPlayerData capability = CapabilityUtils.getCapability(event.getSource().getEntity());

                if (capability.isTapping(MetalTagEnum.ZINC)) {
                    Collection<ItemEntity> drops = event.getDrops();
                    List<ItemEntity> filteredDrops = drops.stream().filter(e -> e.getItem().getItem() != Items.NETHER_STAR).collect(Collectors.toList());
                    event.getDrops().addAll(filteredDrops);

                } else if (capability.isStoring(MetalTagEnum.ZINC)) {
                    event.setCanceled(true);
                }
            } catch (PlayerException ex) {
                ex.printCompleteLog();
            }
        }

        if (event.getEntity() instanceof Player || event.getEntity() instanceof ServerPlayer) {
            try {
                IInvestedPlayerData capability = CapabilityUtils.getCapability(event.getEntity());
                if (capability.getEttmetalState().equals(EttmetalState.KEEP_ITEMS)) {
                    capability.keepInventory(((Player) event.getEntity()).getInventory());
                    event.setCanceled(true);
                    capability.setEttmetalState(EttmetalState.KEEP_ITEMS);
                } else if (capability.getEttmetalState() == EttmetalState.DELETE_ITEMS) {
                    event.setCanceled(true);
                }
            } catch (PlayerException ex) {
                ex.printCompleteLog();
            }
        }
    }
}
