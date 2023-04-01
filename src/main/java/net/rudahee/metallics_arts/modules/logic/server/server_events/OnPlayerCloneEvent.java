package net.rudahee.metallics_arts.modules.logic.server.server_events;

import net.minecraft.util.Tuple;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.player.IInvestedPlayerData;
import net.rudahee.metallics_arts.modules.error_handling.exceptions.PlayerException;
import net.rudahee.metallics_arts.setup.network.ModNetwork;
import net.rudahee.metallics_arts.setup.registries.ModBlocksRegister;
import net.rudahee.metallics_arts.utils.CapabilityUtils;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

import java.util.function.Predicate;

/**
 * Handles events related to player cloning, e.g., when a player is resurrected.
 *
 * @author SteelCode Team
 * @since 1.5.1
 */
public class OnPlayerCloneEvent {

    /**
     * Transfers Allomantic and Feruchemical powers from the original player
     * to the cloned player upon player cloning.
     * This method is triggered when a PlayerEvent.Clone event occurs. It revives
     * the original player, gets the capabilities of the original and cloned player,
     * and transfers the Allomantic and Feruchemical powers from the original to the
     * cloned player. Finally, it invalidates the old capability and syncs the new
     * capability with the client.
     *
     * @param event The PlayerEvent.Clone event that triggered this method.
     */
    public static void playerClone(PlayerEvent.Clone event) {
        event.getOriginal().revive();
        Player original = event.getOriginal();
        Player player = event.getEntity();

        try {
            IInvestedPlayerData capability = CapabilityUtils.getCapability(player);
            IInvestedPlayerData originalCapability = CapabilityUtils.getCapability(original);

            if (originalCapability.isInvested()) {
                for (MetalTagEnum mt : MetalTagEnum.values()) {
                    if (originalCapability.hasAllomanticPower(mt)) {
                        capability.addAllomanticPower(mt);
                    }
                    if (originalCapability.hasFeruchemicPower(mt)) {
                        capability.addFeruchemicPower(mt);
                    }
                }
            }

            if (originalCapability.isTapping(MetalTagEnum.ETTMETAL)) {

                Inventory originalInventory = original.getInventory();

                for (int i = 0; i < originalInventory.getContainerSize(); i++) {
                    ItemStack stack = originalInventory.getItem(i);

                    if (stack.isEmpty()) {
                        player.getInventory().setItem(i, ItemStack.EMPTY);
                    } else {
                        player.getInventory().setItem(i, stack);
                    }
                }
            }

            original.getCapability(ModBlocksRegister.InvestedCapabilityRegister.PLAYER_CAP).invalidate();
            ModNetwork.syncInvestedDataPacket(player);
        } catch (PlayerException ex) {
            ex.printCompleteLog();
        }
    }
}
