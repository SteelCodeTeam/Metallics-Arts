package net.rudahee.metallics_arts.modules.custom_items.metal_minds.rings;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.players.IInvestedPlayerData;
import net.rudahee.metallics_arts.modules.custom_items.metal_minds.RingsMindAbstract;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.ElectrumFecuchemicHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.GoldFecuchemicHelper;
import net.rudahee.metallics_arts.setup.network.ModNetwork;
import net.rudahee.metallics_arts.setup.registries.ModBlocksRegister;
import net.rudahee.metallics_arts.utils.CapabilityUtils;
import top.theillusivec4.curios.api.SlotContext;

public class RingElectrumGold extends RingsMindAbstract {
    public RingElectrumGold(Properties properties){
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

                if (playerCapability.isDecanting(MetalTagEnum.ALUMINUM) || playerCapability.isStoring(MetalTagEnum.ALUMINUM)){
                    //stack.getTag().putString("key",changeOwner(player,stack.getTag(),false));
                }

                String metal0Key = this.getMetals(0).getNameLower()+"_feruchemic_reserve";
                int metal0ActualReserve = stack.getTag().getInt(metal0Key);
                int metal0MaxReserve = this.getMetals(0).getMaxReserveRing();
                /**
                    DECANT
                 */
                if (playerCapability.isDecanting(this.getMetals(0))) {
                    if (metal0ActualReserve>0) {
                        stack.setTag(getFirstSupplier().calculateDischarge(nbtLocal,player,playerCapability,metal0ActualReserve,metal0Key,nicConsumeMet0));
                        if (playerCapability.isDecanting(MetalTagEnum.NICROSIL)) {
                            nicConsumeMet0 = !nicConsumeMet0;
                        }
                    } else {
                        //stack.getTag().putString("key",changeOwner(player,stack.getTag(),false));
                        playerCapability.setDecanting(this.getMetals(0),false);
                    }
                /**
                    STORAGE
                 */
                } else if (playerCapability.isStoring(this.getMetals(0))) {
                    if (metal0ActualReserve < metal0MaxReserve) {
                        //stack.getTag().putString("key",changeOwner(player,stack.getTag(),true));
                        stack.setTag(getFirstSupplier().CalculateCharge(nbtLocal,player,playerCapability,metal0ActualReserve,metal0Key,nicConsumeMet0));
                        if (playerCapability.isStoring(MetalTagEnum.NICROSIL)) {
                            nicConsumeMet0 = !nicConsumeMet0;
                        }
                    } else {
                        playerCapability.setStoring(this.getMetals(0),false);
                    }
                }
                String metal1Key = this.getMetals(1).getNameLower()+"_feruchemic_reserve";
                int metal1ActualReserve = stack.getTag().getInt(metal1Key);
                int metal1MaxReserve = this.getMetals(1).getMaxReserveRing();

                /**
                    DECANT
                 */

                if (playerCapability.isDecanting(this.getMetals(1))) {
                    playerCapability.setModifiedHealth(true);
                    if (metal1ActualReserve>0) {
                        stack.setTag(getSecondSupplier().calculateDischarge(nbtLocal,player,playerCapability,metal1ActualReserve,metal1Key,nicConsumeMet1));
                        if (playerCapability.isDecanting(MetalTagEnum.NICROSIL)) {
                            nicConsumeMet1 = !nicConsumeMet1;
                        }
                    } else {
                        //stack.getTag().putString("key",changeOwner(player,stack.getTag(),false));
                        playerCapability.setDecanting(this.getMetals(1),false);
                    }
                /**
                    STORAGE
                 */
                } else if (playerCapability.isStoring(this.getMetals(1))) {
                    playerCapability.setModifiedHealth(true);
                    if (metal1ActualReserve < metal1MaxReserve) {
                        //stack.getTag().putString("key",changeOwner(player,stack.getTag(),true));
                        stack.setTag(getSecondSupplier().CalculateCharge(nbtLocal,player,playerCapability,metal1ActualReserve,metal1Key,nicConsumeMet1));
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
                data.setDecanting(getMetals(0),false);
                data.setDecanting(getMetals(1),false);
                if (playerCapability.hasModifiedHealth()){
                    ElectrumFecuchemicHelper.restoreHearts(player,playerCapability);
                }
                ModNetwork.sync(data, player);
            });
        }
        super.onUnequip(slotContext, newStack, stack);
    }
}