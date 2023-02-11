package net.rudahee.metallics_arts.modules.custom_items.metal_minds.rings;


import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.player.IInvestedPlayerData;
import net.rudahee.metallics_arts.modules.custom_items.metal_minds.RingsMindAbstract;
import net.rudahee.metallics_arts.modules.error_handling.exceptions.PlayerException;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.spiritual_metals.AluminumFecuchemicHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.spiritual_metals.DuraluminFecuchemicHelper;
import net.rudahee.metallics_arts.setup.network.ModNetwork;
import net.rudahee.metallics_arts.utils.CapabilityUtils;
import net.rudahee.metallics_arts.utils.MetalMindsUtils;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class RingAluminumDuralumin extends RingsMindAbstract <AluminumFecuchemicHelper, DuraluminFecuchemicHelper> {

    public RingAluminumDuralumin (Properties properties){
        super(properties, MetalTagEnum.ALUMINUM, MetalTagEnum.DURALUMIN, AluminumFecuchemicHelper.getInstance(), DuraluminFecuchemicHelper.getInstance());
    }

    private boolean nicConsumeMet1 = false;

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        LivingEntity livingEntity = slotContext.entity();

        CompoundTag nbtLocal = stack.getTag();


        if (livingEntity.level instanceof ServerLevel) {
            if (livingEntity instanceof Player player) {
                try {
                    IInvestedPlayerData playerCapability = CapabilityUtils.getCapability(player);

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
                } catch (PlayerException ex) {
                    ex.printCompleteLog();
                }
            }
        }
        super.curioTick(slotContext, stack);
    }

    private boolean isEquiped = false;


    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> toolTips, TooltipFlag flagIn) {

        super.appendHoverText(stack, world, toolTips, flagIn);
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        if (stack.getItem() != newStack.getItem()) {
            this.isEquiped = false;
        }
        super.onUnequip(slotContext, newStack, stack);
    }

    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        if (stack.getItem() != prevStack.getItem()) {
            this.isEquiped = true;
        }
        super.onEquip(slotContext, prevStack, stack);
    }
}