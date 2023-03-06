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
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.spiritual_metals.AluminumFecuchemicHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.spiritual_metals.DuraluminFecuchemicHelper;
import net.rudahee.metallics_arts.setup.network.ModNetwork;
import net.rudahee.metallics_arts.utils.CapabilityUtils;
import net.rudahee.metallics_arts.utils.MetalMindsUtils;
import top.theillusivec4.curios.api.SlotContext;

/**
 * Class that specifies the aluminum and duralumin band, we pass to the abstract class (which has the behavior) the metals that compose it,
 * in the order: metal and its alloy, along with their corresponding suppliers.
 *
 * @author SteelCode Team
 * @since 1.5.1
 *
 * @see BandMindAbstract
 * @see AbstractFechuchemicHelper
 */
public class BandAluminumDuralumin extends BandMindAbstract<AluminumFecuchemicHelper, DuraluminFecuchemicHelper>{
    /**
     * Default constructor, it is important to send the metals by parameter in the correct order, metal and its alloy.
     *
     * @param properties of the item.
     */
    public BandAluminumDuralumin (Item.Properties properties){
        super(properties, MetalTagEnum.ALUMINUM, MetalTagEnum.DURALUMIN, AluminumFecuchemicHelper.getInstance(), DuraluminFecuchemicHelper.getInstance());
    }

    private boolean nicConsumeMet1 = false;


    /**
     * This method is in charge of loading and unloading the reserves within the mind of metal,
     * as well as granting the corresponding feruchemical powers to the player to whom the slotContext belongs,
     * all this through the metal suppliers.
     * <p>
     * This is a specific specification of the method for this particular item, since it has certain modifications with respect to the generic of the abstract class, it differs in that:
     * <p>
     * - Unlike the other metals, the Aluminum reserve is reflected as a boolean indicating the property of the metal mind, while the others reflect storage time, so this specification removes the loading logic from the generic case.
     *
     * @param slotContext slot the item is in
     * @param stack item being used.
     *
     * @see AluminumFecuchemicHelper
     * @see DuraluminFecuchemicHelper
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
                } catch (PlayerException ex) {
                    ex.printCompleteLog();
                    return;
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
                        stack.setTag(getFirstSupplier().calculateDischarge(nbtLocal, player, playerCapability, actualReserve, metalKey, false));

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
                        stack.setTag(getFirstSupplier().calculateCharge(nbtLocal, player, playerCapability, actualReserve, metalKey, false));

                    } else {
                        playerCapability.setStoring(this.getMetals(0), false);
                    }
                } else if (actualReserve != 3) {
                    stack.setTag(AluminumFecuchemicHelper.turnOffPower(nbtLocal, metalKey));
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
                        stack.setTag(MetalMindsUtils.changeOwner(player, nbtLocal, false, this.getMetals(1)));
                        playerCapability.setTapping(this.getMetals(1), false);
                    }
                    /**
                     STORAGE
                     */
                } else if (playerCapability.isStoring(this.getMetals(1))) {
                    if (actualReserve < maxReserve) {
                        stack.setTag(MetalMindsUtils.changeOwner(player, nbtLocal, true, this.getMetals(1)));
                        stack.setTag(getSecondSupplier().calculateCharge(nbtLocal, player, playerCapability, actualReserve, metalKey, nicConsumeMet1));
                        if (playerCapability.isStoring(MetalTagEnum.NICROSIL)) {
                            nicConsumeMet1 = !nicConsumeMet1;
                        }
                    } else {
                        playerCapability.setStoring(this.getMetals(1), false);
                    }
                }
                ModNetwork.sync(playerCapability, player);
            }
        }
        super.curioTick(slotContext, stack);
    }


}