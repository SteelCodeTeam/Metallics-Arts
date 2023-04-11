package net.rudahee.metallics_arts.modules.custom_items.metal_minds.bands;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.player.IInvestedPlayerData;
import net.rudahee.metallics_arts.modules.custom_items.metal_minds.BandMindAbstract;
import net.rudahee.metallics_arts.modules.error_handling.exceptions.PlayerException;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.AbstractFechuchemicHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.hybrid_metals.ElectrumFecuchemicHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.hybrid_metals.GoldFecuchemicHelper;
import net.rudahee.metallics_arts.setup.network.ModNetwork;
import net.rudahee.metallics_arts.setup.registries.ModBlocksRegister;
import net.rudahee.metallics_arts.utils.CapabilityUtils;
import net.rudahee.metallics_arts.utils.MetalMindsUtils;
import top.theillusivec4.curios.api.SlotContext;

/**
 * Class that specifies the gold and electrum band, we pass to the abstract class (which has the behavior) the metals that compose it,
 * in the order: metal and its alloy, along with their corresponding suppliers.
 *
 * @author SteelCode Team
 * @since 1.5.1
 *
 * @see BandMindAbstract
 * @see AbstractFechuchemicHelper
 */
public class BandGoldElectrum extends BandMindAbstract <GoldFecuchemicHelper, ElectrumFecuchemicHelper> {

    /**
     * Default constructor, it is important to send the metals by parameter in the correct order, metal and its alloy.
     *
     * @param properties of the item.
     */
    public BandGoldElectrum(Item.Properties properties) {
        super(properties, MetalTagEnum.GOLD, MetalTagEnum.ELECTRUM, GoldFecuchemicHelper.getInstance(), ElectrumFecuchemicHelper.getInstance());
    }

    private boolean nicConsumeMet0 = false;
    private boolean nicConsumeMet1 = false;

    /**
     * This method is in charge of loading and unloading the reserves within the mind of metal,
     * as well as granting the corresponding feruchemical powers to the player to whom the slotContext belongs,
     * all this through the metal suppliers.
     * <p>
     * This is a specific specification of the method for this particular item, since it has certain modifications with respect to the generic of the abstract class, it differs in that:
     * <p>
     * - Since the Electrum modifies the player's basic information depending on whether it is storing, tapping or off, and these must be updated when it changes due to different aspects (death of the player, unequipped from the metal mind) this own logic is in charge of adding these checks and modifications extra.
     *
     * @param slotContext slot the item is in
     * @param stack item being used.
     *
     * @see GoldFecuchemicHelper
     * @see ElectrumFecuchemicHelper
     *
     */
    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        LivingEntity livingEntity = slotContext.entity();
        CompoundTag nbtLocal = stack.getTag();
        if (livingEntity.level instanceof ServerLevel) {
            if (livingEntity instanceof Player player) {
                IInvestedPlayerData playerCapability;
                try {
                    playerCapability = CapabilityUtils.getCapability(player);
                } catch(PlayerException ex) {
                    ex.printCompleteLog();
                    return;
                }
                if (!playerCapability.isTapping(MetalTagEnum.ELECTRUM) && !playerCapability.isStoring(MetalTagEnum.ELECTRUM)) {
                    ElectrumFecuchemicHelper.restoreHearts(player, playerCapability);
                }

                if (playerCapability.isTapping(MetalTagEnum.ALUMINUM) || playerCapability.isStoring(MetalTagEnum.ALUMINUM)) {
                    stack.setTag(MetalMindsUtils.changeOwner(player, nbtLocal, false, this.getMetals(0), this.getMetals(1)));
                }

                String metalKey = this.getMetals(0).getNameLower() + "_feruchemic_reserve";
                int actualReserve = stack.getTag().getInt(metalKey);
                int maxReserve = this.getMetals(0).getMaxReserveRing();
                /**
                 DECANT
                 */
                if (playerCapability.isTapping(this.getMetals(0))) {
                    if (actualReserve > 0) {
                        stack.setTag(getFirstSupplier().calculateDischarge(nbtLocal, player, playerCapability, actualReserve, metalKey, nicConsumeMet0));
                        if (playerCapability.isTapping(MetalTagEnum.NICROSIL)) {
                            nicConsumeMet0 = !nicConsumeMet0;
                        }
                    } else {
                        stack.setTag(MetalMindsUtils.changeOwner(player, nbtLocal, false, this.getMetals(0), this.getMetals(1)));
                        playerCapability.setTapping(this.getMetals(0), false);
                    }
                    /**
                     STORAGE
                     */
                } else if (playerCapability.isStoring(this.getMetals(0))) {
                    if (actualReserve < maxReserve) {
                        stack.setTag(MetalMindsUtils.changeOwner(player, nbtLocal, true, this.getMetals(0), this.getMetals(1)));
                        stack.setTag(getFirstSupplier().calculateCharge(nbtLocal, player, playerCapability, actualReserve, metalKey, nicConsumeMet0));
                        if (playerCapability.isStoring(MetalTagEnum.NICROSIL)) {
                            nicConsumeMet0 = !nicConsumeMet0;
                        }
                    } else {
                        playerCapability.setStoring(this.getMetals(0), false);
                    }
                }
                metalKey = this.getMetals(1).getNameLower() + "_feruchemic_reserve";
                actualReserve = stack.getTag().getInt(metalKey);
                maxReserve = this.getMetals(1).getMaxReserveRing();
                /**
                 DECANT
                 */
                if (playerCapability.isTapping(this.getMetals(1))) {
                    if (actualReserve > 0) {
                        stack.setTag(getSecondSupplier().calculateDischarge(nbtLocal, player, playerCapability, actualReserve, metalKey, nicConsumeMet1));
                        if (playerCapability.isTapping(MetalTagEnum.NICROSIL)) {
                            nicConsumeMet1 = !nicConsumeMet1;
                        }
                    } else {
                        stack.setTag(MetalMindsUtils.changeOwner(player, nbtLocal, false, this.getMetals(0), this.getMetals(1)));
                        playerCapability.setTapping(this.getMetals(1), false);
                    }
                    playerCapability.setModifiedHealth(true);
                    /**
                     STORAGE
                     */
                } else if (playerCapability.isStoring(this.getMetals(1))) {
                    if (actualReserve < maxReserve) {
                        stack.setTag(MetalMindsUtils.changeOwner(player, nbtLocal, true, this.getMetals(0), this.getMetals(1)));
                        stack.setTag(getSecondSupplier().calculateCharge(nbtLocal, player, playerCapability, actualReserve, metalKey, nicConsumeMet1));
                        if (playerCapability.isStoring(MetalTagEnum.NICROSIL)) {
                            nicConsumeMet1 = !nicConsumeMet1;
                        }
                    } else {
                        playerCapability.setStoring(this.getMetals(1), false);
                    }
                    if (!playerCapability.hasModifiedHealth()) {
                        playerCapability.setModifiedHealth(true);
                    }
                } else if (playerCapability.hasModifiedHealth()) {
                    ElectrumFecuchemicHelper.restoreHearts(player, playerCapability);
                }
                ModNetwork.syncInvestedDataPacket(playerCapability, player);
            }
        }
        super.curioTick(slotContext, stack);
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

            super.onUnequip(slotContext, newStack, stack);
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
    }

}