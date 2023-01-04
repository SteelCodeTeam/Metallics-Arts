package net.rudahee.metallics_arts.modules.custom_items.metal_minds.rings;


import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.players.IInvestedPlayerData;
import net.rudahee.metallics_arts.modules.custom_items.metal_minds.RingsMindAbstract;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.EttmetalFecuchemicHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.LerasiumFecuchemicHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.PewterFeruchemicHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.TinFeruchemicHelper;
import net.rudahee.metallics_arts.setup.network.ModNetwork;
import net.rudahee.metallics_arts.setup.registries.ModBlocksRegister;
import top.theillusivec4.curios.api.SlotContext;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RingLerasiumEttmetal extends RingsMindAbstract {
    public RingLerasiumEttmetal (Properties properties){
        super(properties, MetalTagEnum.LERASIUM, MetalTagEnum.ETTMETAL, LerasiumFecuchemicHelper.getInstance(), EttmetalFecuchemicHelper.getInstance());
    }
    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        LivingEntity livingEntity = slotContext.entity();
        CompoundTag nbtLocal = stack.getTag();

        if (livingEntity.level instanceof ServerLevel) {
            if (livingEntity instanceof Player) {
                Player player = (Player) livingEntity;
                player.getCapability(ModBlocksRegister.InvestedCapabilityRegister.PLAYER_CAP).ifPresent(data -> {

                    if (data.isDecanting(MetalTagEnum.ALUMINUM) || data.isStoring(MetalTagEnum.ALUMINUM)) {
                        stack.getTag().putString("key", changeOwner(player, stack.getTag(), false));
                    }

                    /////////////LERASIUM///////////////////
                    if (data.isDecanting(MetalTagEnum.LERASIUM)) {
                        loadAllomanticReserve(data, stack);
                        nbtLocal.putInt(getMetals(0).getNameLower() + "_feruchemic_reserve", 0);
                        nbtLocal.putString("key", changeOwner(player, nbtLocal, false));
                        data.setDecanting(getMetals(0), false);
                        stack.setTag(nbtLocal);
                    } else if (data.isStoring(MetalTagEnum.LERASIUM)) {
                        if (havePlayerAnyReserve(data)) {
                            saveAllomanticReserve(data, stack);
                            nbtLocal.putString("key", changeOwner(player, nbtLocal, true));
                            nbtLocal.putInt(getMetals(0).getNameLower() + "_feruchemic_reserve",1);
                            data.setStoring(getMetals(0), false);
                        } else {
                            data.setStoring(getMetals(0), false);
                        }

                    }

                    /////////////ETTMETAL////////////////
                    if (data.isDecanting(getMetals(1))) {
                        if (nbtLocal.getInt(getMetals(1).getNameLower() + "_feruchemic_reserve") > 0) {

                            if (nbtLocal.getInt(getMetals(1).getNameLower() + "_feruchemic_reserve") > 10) {
                                generateExplosion(player,nbtLocal);
                                //player.level.explode(player,player.position().x,player.position().y,player.position().z,(float) nbtLocal.getInt(getMetals(1).getNameLower() + "_feruchemic_reserve")/410,Explosion.BlockInteraction.NONE);
                                //player.hurt(DamageSource.MAGIC,(float) nbtLocal.getInt(getMetals(1).getNameLower() + "_feruchemic_reserve")/205); // 410 hace medio corazon por carga, por lo que la 10 serian 5 corazones
                                nbtLocal.putInt(getMetals(1).getNameLower() + "_feruchemic_reserve", 0);
                                stack.setTag(nbtLocal);
                            } else {
                                nbtLocal.putInt(getMetals(1).getNameLower() + "_feruchemic_reserve", 0);
                                nbtLocal.putString("key",changeOwner(player,nbtLocal,false));
                                data.setDecanting(getMetals(1),false);
                            }

                        } else {
                            nbtLocal.putString("key",changeOwner(player,nbtLocal,false));
                            data.setDecanting(getMetals(1),false);
                        }
                    } else if (data.isStoring(getMetals(1))) {
                        if (nbtLocal.getInt(getMetals(1).getNameLower()+"_feruchemic_reserve") < nbtLocal.getInt(getMetals(1).getNameLower()+"_feruchemic_max_capacity")) {

                            if (player.getLastDamageSource() != null){
                                if ((player.getLastDamageSource().isExplosion())){
                                    nbtLocal.putString("key",changeOwner(player,nbtLocal,true));
                                    //player.addEffect(new MobEffectInstance(MobEffects.REGENERATION,20,3,true,true));
                                    nbtLocal.putInt(getMetals(1).getNameLower()+"_feruchemic_reserve",(nbtLocal.getInt(getMetals(1).getNameLower()+"_feruchemic_reserve")+1));
                                }
                            }
                            stack.setTag(nbtLocal);
                        } else {
                            data.setStoring(getMetals(1), false);
                        }
                    }
                    ModNetwork.sync(data, player);
                });
            }
        }

        super.curioTick(slotContext, stack);
    }

    public void generateExplosion(Player player,CompoundTag nbtLocal) {
        player.level.explode(player,player.position().x,player.position().y,player.position().z,(float) nbtLocal.getInt(getMetals(1).getNameLower() + "_feruchemic_reserve")/683,Explosion.BlockInteraction.NONE);
        player.setHealth((player.getHealth() - ((float) nbtLocal.getInt(getMetals(1).getNameLower() + "_feruchemic_reserve")/205)));
    }


    public boolean havePlayerAnyReserve (IInvestedPlayerData playerCapability) {
        for (MetalTagEnum metal: MetalTagEnum.values()){
            if (playerCapability.getAllomanticAmount(metal)>0) {
                return true;
            }
        }
        return false;
    }

    public boolean saveAllomanticReserve(IInvestedPlayerData playerCapability, ItemStack stack) {
        boolean itsDone = false;
        ArrayList<MetalTagEnum> metals = playerCapability.getAllomanticPowers();
        int firstQty = 0;
        int qtyToRemove = 0;
        boolean continueSaving;

        for (MetalTagEnum metal: metals) {
            continueSaving = true;
            while (continueSaving) {
                if (firstQty == 0) {
                    firstQty = playerCapability.getAllomanticAmount(metal);
                    qtyToRemove = Math.toIntExact(Math.round(firstQty * 0.1));
                }
                continueSaving = playerCapability.substractAllomanticMetalAmount(metal, qtyToRemove);
                if (!continueSaving || firstQty == 0) {
                    firstQty = 0;
                    qtyToRemove = 0;
                    continueSaving = false;
                } else {
                    if (!stack.getTag().contains(metal.getNameLower()+"inLerasiumBand")){ //no existe el tag
                        stack.getTag().putInt(metal.getNameLower()+"inLerasiumBand",0);
                    }
                    stack.getTag().putInt(metal.getNameLower()+"inLerasiumBand", stack.getTag().getInt(metal.getNameLower()+"inLerasiumBand")+qtyToRemove);
                    itsDone = true;
                    if (stack.getTag().getInt(metal.getNameLower()+"inLerasiumBand") > (metal.getMaxAllomanticTicksStorage()/2)) {
                        stack.getTag().putInt(metal.getNameLower()+"inLerasiumBand",(metal.getMaxAllomanticTicksStorage()/2));
                        continueSaving = false;
                    }

                }
            }
        }
        return itsDone;
    }

    public boolean loadAllomanticReserve(IInvestedPlayerData playerCapability, ItemStack stack) {
        boolean itsDone = false;
        ArrayList<MetalTagEnum> metals = playerCapability.getAllomanticPowers();
        int firstQty = 0;
        int qtyToAdd = 0;
        boolean continueLoading = true;

        for (MetalTagEnum metal: metals) {
            continueLoading = true;
            while (continueLoading) {
                if (firstQty == 0) {
                    firstQty = stack.getTag().getInt(metal.getNameLower()+"inLerasiumBand");
                    qtyToAdd = Math.toIntExact(Math.round(firstQty * 0.1));
                }
                if (!continueLoading || stack.getTag().getInt(metal.getNameLower()+"inLerasiumBand") == 0) {
                    firstQty = 0;
                    qtyToAdd = 0;
                    continueLoading = false;
                } else {
                    if (!stack.getTag().contains(metal.getNameLower()+"inLerasiumBand")){ //no existe el tag
                        stack.getTag().putInt(metal.getNameLower()+"inLerasiumBand",0);
                    }
                    stack.getTag().putInt(metal.getNameLower()+"inLerasiumBand", stack.getTag().getInt(metal.getNameLower()+"inLerasiumBand")-qtyToAdd);
                    continueLoading = playerCapability.addAllomanticMetalAmount(metal, qtyToAdd);
                    itsDone = true;
                }
            }
        }
        return itsDone;
    }

    /*@Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> toolTips, TooltipFlag flagIn) {
        if (stack.hasTag()) {
            if (stack.getTag().getInt(getMetals(0).getNameLower()+"_feruchemic_reserve")>0) {

                toolTips.add(Component.translatable("metallics_arts.metal_translate."+getMetals(0).getNameLower()).append(": ").append(Component.translatable("metallics_arts.mental_mind_translate.has_reserve")));
            } else {
                toolTips.add(Component.translatable("metallics_arts.metal_translate."+getMetals(0).getNameLower()).append(": ").append(Component.translatable("metallics_arts.mental_mind_translate.not_has_reserve")));
            }
            if (!Screen.hasShiftDown()){
                toolTips.add(Component.translatable("metallics_arts.metal_translate."+getMetals(1).getNameLower()).append(": "+(stack.getTag().getInt(getMetals(1).getNameLower()+"_feruchemic_reserve")/41)));
                if (world != null) {
                    toolTips.add(Component.translatable("metallics_arts.mental_mind.owner").append(": "+ ((stack.getTag().getString("key").equals("Nobody")) ? Component.translatable("metallics_arts.mental_mind.nobody").getString() : (world.getPlayerByUUID(UUID.fromString((stack.getTag().getString("key")))) == null) ? Component.translatable("metallics_arts.mental_mind.owner_someone") : world.getPlayerByUUID(UUID.fromString((stack.getTag().getString("key")))).getName().getString())));
                }
                toolTips.add(Component.translatable(" "));
                toolTips.add(Component.translatable("metallics_arts.mental_mind_translate.shift_info").withStyle(ChatFormatting.BLUE));
            } else {
                toolTips.add(Component.translatable("metallics_arts.metal_translate."+getMetals(1).getNameLower()).append(": "+ ((stack.getTag().getInt(getMetals(1).getNameLower()+"_feruchemic_reserve") * 100)/stack.getTag().getInt(getMetals(1).getNameLower()+"_feruchemic_max_capacity"))+"%"));
                if (world != null) {
                    toolTips.add(Component.translatable("metallics_arts.mental_mind.owner").append(": "+ ((stack.getTag().getString("key").equals("Nobody")) ? Component.translatable("metallics_arts.mental_mind.nobody").getString() : (world.getPlayerByUUID(UUID.fromString((stack.getTag().getString("key")))) == null) ? Component.translatable("metallics_arts.mental_mind.owner_someone") : world.getPlayerByUUID(UUID.fromString((stack.getTag().getString("key")))).getName().getString())));
                }

                toolTips.add(Component.translatable("-------------------"));
                for (MetalTagEnum metal : MetalTagEnum.values()){
                    if(stack.getTag().getInt(metal.getNameLower()+"inLerasiumBand")>0){
                        toolTips.add(Component.translatable(" * ").append(Component.translatable("metallics_arts.metal_translate."+metal.getNameLower())).append(": "+stack.getTag().getInt(metal.getNameLower()+"inLerasiumBand")));
                    }
                }
            }
        }
        super.appendHoverText(stack, world, toolTips, flagIn);
    }*/
}