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
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.hybrid_metals.ElectrumFecuchemicHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.hybrid_metals.GoldFecuchemicHelper;
import net.rudahee.metallics_arts.setup.network.ModNetwork;
import net.rudahee.metallics_arts.setup.registries.ModBlocksRegister;
import net.rudahee.metallics_arts.utils.CapabilityUtils;
import net.rudahee.metallics_arts.utils.MetalMindsUtils;
import top.theillusivec4.curios.api.SlotContext;

public class BandElectrumGold extends BandMindAbstract <GoldFecuchemicHelper, ElectrumFecuchemicHelper> {

    public BandElectrumGold(Item.Properties properties) {
        super(properties, MetalTagEnum.GOLD, MetalTagEnum.ELECTRUM, GoldFecuchemicHelper.getInstance(), ElectrumFecuchemicHelper.getInstance());
    }

    private boolean nicConsumeMet0 = false;
    private boolean nicConsumeMet1 = false;
    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        LivingEntity livingEntity = slotContext.entity();
        CompoundTag nbtLocal = stack.getTag();
        if (livingEntity.level instanceof ServerLevel) {
            if (livingEntity instanceof Player) {
                Player player = (Player) livingEntity;
                IInvestedPlayerData playerCapability = CapabilityUtils.getCapability(player);
                if (playerCapability.isTapping(MetalTagEnum.ALUMINUM) || playerCapability.isStoring(MetalTagEnum.ALUMINUM)){
                    stack.setTag(MetalMindsUtils.changeOwner(player, nbtLocal,false,this.getMetals(0),this.getMetals(1)));
                }

                String metalKey = this.getMetals(0).getNameLower()+"_feruchemic_reserve";
                int actualReserve = stack.getTag().getInt(metalKey);
                int maxReserve = this.getMetals(0).getMaxReserveRing();
                /**
                 DECANT
                 */
                if (playerCapability.isTapping(this.getMetals(0))) {
                    if (actualReserve>0) {
                        stack.setTag(getFirstSupplier().calculateDischarge(nbtLocal,player,playerCapability,actualReserve,metalKey,nicConsumeMet0));
                        if (playerCapability.isTapping(MetalTagEnum.NICROSIL)) {
                            nicConsumeMet0 = !nicConsumeMet0;
                        }
                    } else {
                        stack.setTag(MetalMindsUtils.changeOwner(player, nbtLocal,false,this.getMetals(0),this.getMetals(1)));
                        playerCapability.setTapping(this.getMetals(0),false);
                    }
                    /**
                     STORAGE
                     */
                } else if (playerCapability.isStoring(this.getMetals(0))) {
                    if (actualReserve < maxReserve) {
                        stack.setTag(MetalMindsUtils.changeOwner(player, nbtLocal,true,this.getMetals(0),this.getMetals(1)));
                        stack.setTag(getFirstSupplier().calculateCharge(nbtLocal,player,playerCapability,actualReserve,metalKey,nicConsumeMet0));
                        if (playerCapability.isStoring(MetalTagEnum.NICROSIL)) {
                            nicConsumeMet0 = !nicConsumeMet0;
                        }
                    } else {
                        playerCapability.setStoring(this.getMetals(0),false);
                    }
                }
                metalKey = this.getMetals(1).getNameLower()+"_feruchemic_reserve";
                actualReserve = stack.getTag().getInt(metalKey);
                maxReserve = this.getMetals(1).getMaxReserveRing();
                /**
                 DECANT
                 */
                if (playerCapability.isTapping(this.getMetals(1))) {
                    if (actualReserve>0) {
                        stack.setTag(getSecondSupplier().calculateDischarge(nbtLocal,player,playerCapability,actualReserve,metalKey,nicConsumeMet1));
                        if (playerCapability.isTapping(MetalTagEnum.NICROSIL)) {
                            nicConsumeMet1 = !nicConsumeMet1;
                        }
                    } else {
                        stack.setTag(MetalMindsUtils.changeOwner(player, nbtLocal,false, this.getMetals(0),this.getMetals(1)));
                        playerCapability.setTapping(this.getMetals(1),false);
                    }
                    /**
                     STORAGE
                     */
                } else if (playerCapability.isStoring(this.getMetals(1))) {
                    if (actualReserve < maxReserve) {
                        stack.setTag(MetalMindsUtils.changeOwner(player, nbtLocal,true,this.getMetals(0),this.getMetals(1)));
                        stack.setTag(getSecondSupplier().calculateCharge(nbtLocal,player,playerCapability,actualReserve,metalKey,nicConsumeMet1));
                        if (playerCapability.isStoring(MetalTagEnum.NICROSIL)) {
                            nicConsumeMet1 = !nicConsumeMet1;
                        }
                    } else {
                        playerCapability.setStoring(this.getMetals(1),false);
                    }
                } else if (playerCapability.hasModifiedHealth()) {
                    ElectrumFecuchemicHelper.restoreHearts(player, playerCapability);
                }
                ModNetwork.sync(playerCapability, player);
            }
        }
        super.curioTick(slotContext, stack);
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        Player player = (Player) slotContext.getWearer();
        IInvestedPlayerData playerCapability = CapabilityUtils.getCapability(player);
        if (stack.getItem() != newStack.getItem()) {
            player.getCapability(ModBlocksRegister.InvestedCapabilityRegister.PLAYER_CAP).ifPresent(data ->{
                data.setMetalMindEquiped(getMetals(0).getGroup(),false);
                data.setMetalMindEquiped(getMetals(1).getGroup(),false);
                data.setStoring(getMetals(0),false);
                data.setStoring(getMetals(1),false);
                data.setTapping(getMetals(0),false);
                data.setTapping(getMetals(1),false);
                if (playerCapability.hasModifiedHealth()){
                    ElectrumFecuchemicHelper.restoreHearts(player,playerCapability);
                }
                ModNetwork.sync(data, player);
            });
        }
        super.onUnequip(slotContext, newStack, stack);
    }

}