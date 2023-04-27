package net.rudahee.metallics_arts.modules.custom_items.metal_minds.rings;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.player.IInvestedPlayerData;
import net.rudahee.metallics_arts.modules.custom_items.metal_minds.RingsMindAbstract;
import net.rudahee.metallics_arts.modules.error_handling.exceptions.PlayerException;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.AbstractFechuchemicHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.hybrid_metals.ElectrumFecuchemicHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.hybrid_metals.GoldFecuchemicHelper;
import net.rudahee.metallics_arts.setup.network.ModNetwork;
import net.rudahee.metallics_arts.utils.CapabilityUtils;
import net.rudahee.metallics_arts.utils.MetalMindsUtils;
import top.theillusivec4.curios.api.SlotContext;

/**
 * Class that specifies the gold and electrum ring, we pass to the abstract class (which has the behavior) the metals that compose it,
 * in the order: metal and its alloy, along with their corresponding suppliers.
 *
 * @author SteelCode Team
 * @since 1.5.1
 *
 * @see RingsMindAbstract
 * @see AbstractFechuchemicHelper
 */
public class RingGoldElectrum extends RingsMindAbstract {

    /**
     * Default constructor, it is important to send the metals by parameter in the correct order, metal and its alloy.
     *
     * @param properties of the item.
     */
    public RingGoldElectrum(Properties properties){
        super(properties, MetalTagEnum.GOLD, MetalTagEnum.ELECTRUM, null, null);
    }

    /**
     * This method modifies the player's internal information when a ring is unequipped.
     * <p>
     * After this, a new metal mind of this type could be equipped.
     *
     * This is a specific specification of the method for this particular item, since it has certain modifications with respect to the generic of the abstract class, it differs in that:
     * <p>
     * - Since the electrum modifies the basic information of the player depending on whether it is storing, hitting or shutting down, and these must be updated when it changes due to different aspects, this mod takes care of updating the internal information of the player when the metal mind is unequipped.
     *
     * @param slotContext slot in which the item is removed.
     * @param stack item that is currently in the slot.
     * @param newStack new item to be placed in the slot.
     */
    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        Player player = (Player) slotContext.getWearer();
        IInvestedPlayerData playerCapability;
        try {
            playerCapability = CapabilityUtils.getCapability(player);

        } catch (PlayerException ex) {
            ex.printCompleteLog();
            return;
        }
        if (stack.getItem() != newStack.getItem()) {
            playerCapability.setMetalMindEquiped(getMetals(0).getGroup(), false);
            playerCapability.setMetalMindEquiped(getMetals(1).getGroup(), false);
            playerCapability.setStoring(getMetals(0), false);
            playerCapability.setStoring(getMetals(1), false);
            playerCapability.setTapping(getMetals(0), false);
            playerCapability.setTapping(getMetals(1), false);
            if (playerCapability.hasModifiedHealth()) {
                ElectrumFecuchemicHelper.restoreHearts(player, playerCapability);
            }
            ModNetwork.syncInvestedDataPacket(playerCapability, player);

        }
        super.onUnequip(slotContext, newStack, stack);
    }
}