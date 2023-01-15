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
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.AluminumFecuchemicHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.DuraluminFecuchemicHelper;
import net.rudahee.metallics_arts.setup.network.ModNetwork;
import net.rudahee.metallics_arts.utils.CapabilityUtils;
import net.rudahee.metallics_arts.utils.MetalMindsUtils;
import top.theillusivec4.curios.api.SlotContext;

public class BandAluminumDuralumin extends BandMindAbstract <AluminumFecuchemicHelper, DuraluminFecuchemicHelper>{

    public BandAluminumDuralumin (Item.Properties properties){
        super(properties, MetalTagEnum.ALUMINUM, MetalTagEnum.DURALUMIN, AluminumFecuchemicHelper.getInstance(), DuraluminFecuchemicHelper.getInstance());
    }

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
                        stack.setTag(getFirstSupplier().calculateDischarge(nbtLocal,player,playerCapability,actualReserve,metalKey,false));

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
                        stack.setTag(getFirstSupplier().calculateCharge(nbtLocal,player,playerCapability,actualReserve,metalKey,false));

                    } else {
                        playerCapability.setStoring(this.getMetals(0),false);
                    }
                } else if (actualReserve != 3){
                    AluminumFecuchemicHelper.turnOffPower(nbtLocal,metalKey);
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
                        stack.setTag(MetalMindsUtils.changeOwner(player, nbtLocal,false,this.getMetals(1)));
                        playerCapability.setTapping(this.getMetals(1),false);
                    }
                    /**
                     STORAGE
                     */
                } else if (playerCapability.isStoring(this.getMetals(1))) {
                    if (actualReserve < maxReserve) {
                        stack.setTag(MetalMindsUtils.changeOwner(player, nbtLocal,true,this.getMetals(1)));
                        stack.setTag(getSecondSupplier().calculateCharge(nbtLocal,player,playerCapability,actualReserve,metalKey,nicConsumeMet1));
                        if (playerCapability.isStoring(MetalTagEnum.NICROSIL)) {
                            nicConsumeMet1 = !nicConsumeMet1;
                        }
                    } else {
                        playerCapability.setStoring(this.getMetals(1),false);
                    }
                }
                ModNetwork.sync(playerCapability, player);
            }
        }
        super.curioTick(slotContext, stack);
    }
    /*@Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> toolTips, TooltipFlag flagIn) {
        if (stack.hasTag()) {
            if (isEquiped) {
                if (stack.getTag().getInt(getMetals(0).getNameLower()+"_feruchemic_reserve") == 2) {
                    toolTips.add(Component.translatable("metallics_arts.metal_translate."+getMetals(0).getNameLower()).append(": ").append(Component.translatable("metallics_arts.mental_mind_translate.store_identity")));
                } else if (stack.getTag().getInt(getMetals(0).getNameLower()+"_feruchemic_reserve") == 1) {
                    toolTips.add(Component.translatable("metallics_arts.metal_translate."+getMetals(0).getNameLower()).append(": ").append(Component.translatable("metallics_arts.spike_allomantic_power.tapping_identity")));
                } else {
                    toolTips.add(Component.translatable("metallics_arts.metal_translate."+getMetals(0).getNameLower()).append(": ").append(Component.translatable("metallics_arts.mental_mind_translate.off_power")));
                }
            } else {
                toolTips.add(Component.translatable("metallics_arts.metal_translate."+getMetals(0).getNameLower()).append(": ").append(Component.translatable("metallics_arts.mental_mind_translate.off_power")));
            }
            if (!Screen.hasShiftDown()){
                toolTips.add(Component.translatable("metallics_arts.metal_translate."+getMetals(1).getNameLower()).append(": "+ stack.getTag().getInt(getMetals(1).getNameLower()+"_feruchemic_reserve") / 20 + "s"));
            } else {
                toolTips.add(Component.translatable("metallics_arts.metal_translate."+getMetals(1).getNameLower()).append(": "+ ((stack.getTag().getInt(getMetals(1).getNameLower()+"_feruchemic_reserve") * 100)/stack.getTag().getInt(getMetals(1).getNameLower()+"_feruchemic_max_capacity"))+"%"));
            }
            if (world != null) {
                toolTips.add(Component.translatable("metallics_arts.mental_mind.owner").append(": "+ ((stack.getTag().getString("key").equals("Nobody")) ? Component.translatable("metallics_arts.mental_mind.nobody").getString() : (world.getPlayerByUUID(UUID.fromString((stack.getTag().getString("key")))) == null) ? Component.translatable("metallics_arts.mental_mind.owner_someone") : world.getPlayerByUUID(UUID.fromString((stack.getTag().getString("key")))).getName().getString())));
            }
            if (!Screen.hasShiftDown()){
                toolTips.add(Component.translatable(" "));
                toolTips.add(Component.translatable("metallics_arts.mental_mind_translate.shift_info").withStyle(ChatFormatting.BLUE));
            }
        }
        super.appendHoverText(stack, world, toolTips, flagIn);
    }*/

}