package net.rudahee.metallics_arts.modules.items.metalminds.bands;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.rudahee.metallics_arts.modules.data_player.InvestedCapability;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;
import net.rudahee.metallics_arts.setup.network.ModNetwork;
import top.theillusivec4.curios.api.SlotContext;

import javax.annotation.Nullable;
import java.util.List;

public class BandAtiumMalatium extends BandMindAbstract {

    public BandAtiumMalatium (Item.Properties properties){
        super(properties, MetalsNBTData.ATIUM,MetalsNBTData.MALATIUM,MetalsNBTData.ATIUM.getMaxReserveBand(),MetalsNBTData.MALATIUM.getMaxReserveBand());
    }

    private static boolean nicConsume = false;
    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        LivingEntity livingEntity = slotContext.entity();

        CompoundTag nbtLocal = stack.getTag();

        if (livingEntity.level instanceof ServerLevel) {
            if (livingEntity instanceof Player) {
                Player player = (Player) livingEntity;
                player.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(data -> {

                    if (data.isDecanting(MetalsNBTData.ALUMINUM)||data.isStoring(MetalsNBTData.ALUMINUM)){
                        stack.getTag().putString("key",changeOwner(player,stack.getTag(),false));
                    }

                    if (data.isDecanting(getMetals(0))) {
                        if (stack.getTag().getInt(getMetals(0).getNameLower()+"_feruchemic_reserve")>0) {
                            if (data.isDecanting(MetalsNBTData.NICROSIL)){
                                if (!nicConsume){
                                    nbtLocal.putInt(getMetals(0).getNameLower()+"_feruchemic_reserve",(stack.getTag().getInt(getMetals(0).getNameLower()+"_feruchemic_reserve")-1));
                                    stack.setTag(nbtLocal);
                                }
                                nicConsume = !nicConsume;
                            } else {
                                //las dos lineas de abajo van sin el nicrosil
                                nbtLocal.putInt(getMetals(0).getNameLower()+"_feruchemic_reserve",(stack.getTag().getInt(getMetals(0).getNameLower()+"_feruchemic_reserve")-1));
                                stack.setTag(nbtLocal);
                            }
                        } else {
                            stack.getTag().putString("key",changeOwner(player,stack.getTag(),false));
                            data.setDecanting(getMetals(0),false);
                        }
                    } else if (data.isStoring(getMetals(0))) {
                        if (stack.getTag().getInt(getMetals(0).getNameLower()+"_feruchemic_reserve") < stack.getTag().getInt(getMetals(0).getNameLower()+"_feruchemic_max_capacity")) {
                            if (data.isStoring(MetalsNBTData.NICROSIL)) {
                                if (!nicConsume){
                                    stack.getTag().putString("key",changeOwner(player,stack.getTag(),true));
                                    nbtLocal.putInt(getMetals(0).getNameLower()+"_feruchemic_reserve",(stack.getTag().getInt(getMetals(0).getNameLower()+"_feruchemic_reserve")+1));
                                    stack.setTag(nbtLocal);
                                }
                                nicConsume = !nicConsume;
                            } else {
                                //3 lineas, sin nicrosil
                                stack.getTag().putString("key",changeOwner(player,stack.getTag(),true));
                                nbtLocal.putInt(getMetals(0).getNameLower()+"_feruchemic_reserve",(stack.getTag().getInt(getMetals(0).getNameLower()+"_feruchemic_reserve")+1));
                                stack.setTag(nbtLocal);
                            }
                        } else {
                            data.setStoring(getMetals(0),false);
                        }
                    }

                    ///////////// MALATIUM /////////////
                    if (data.isDecanting(getMetals(1))) {
                        if (stack.getTag().getInt(getMetals(1).getNameLower()+"_feruchemic_reserve")>0) {
                            if (isDecanting(player, stack)) {
                                nbtLocal.putInt(getMetals(1).getNameLower()+"_feruchemic_reserve",(stack.getTag().getInt(getMetals(1).getNameLower()+"_feruchemic_reserve")-1));
                                stack.setTag(nbtLocal);
                            }
                        } else {
                            stack.getTag().putString("key",changeOwner(player,stack.getTag(),false));
                            stack.getTag().putInt("tier_malatium_storage",-1);
                            data.setDecanting(getMetals(1),false);
                        }

                    } else if (data.isStoring(getMetals(1))) {
                        if (stack.getTag().getInt(getMetals(1).getNameLower()+"_feruchemic_reserve") < stack.getTag().getInt(getMetals(1).getNameLower()+"_feruchemic_max_capacity")) {
                            if (isStoring(player, stack)){
                                stack.getTag().putString("key",changeOwner(player,stack.getTag(),true));
                                nbtLocal.putInt(getMetals(1).getNameLower()+"_feruchemic_reserve",(stack.getTag().getInt(getMetals(1).getNameLower()+"_feruchemic_reserve")+1));
                                stack.setTag(nbtLocal);
                            }
                        } else {
                            data.setStoring(getMetals(1),false);
                        }
                    }
                    ModNetwork.sync(data, player);
                });
            }
        }
        super.curioTick(slotContext, stack);
    }


    public boolean isStoring (Player player, ItemStack stack){
        if (!stack.getTag().contains("tier_malatium_storage")){
            stack.getTag().putInt("tier_malatium_storage",-1);
        }
        if (stack.getTag().getInt("tier_malatium_storage") == -1){
            if (!generateIternalReserve(player, stack)){
                return false;
            }
        }

        if (player.getMainHandItem().getItem() instanceof TieredItem) {
            TieredItem tiered = (TieredItem) player.getMainHandItem().getItem();
            if (tiered.getTier().getLevel() == stack.getTag().getInt("tier_malatium_storage")){
                if (player.getItemInHand(InteractionHand.MAIN_HAND).getDamageValue() == player.getItemInHand(InteractionHand.MAIN_HAND).getMaxDamage()){
                    player.setItemInHand(InteractionHand.MAIN_HAND,ItemStack.EMPTY);
                    player.level.playLocalSound(player.getX(),player.getY(),player.getZ(), SoundEvents.ITEM_BREAK, SoundSource.NEUTRAL,1.0f, 2.0f, true);
                    return false;
                }
                player.getItemInHand(InteractionHand.MAIN_HAND).setDamageValue(player.getItemInHand(InteractionHand.MAIN_HAND).getDamageValue()+1);
                return true;
            }
            return false; //el item no es del tier de la primer carga de la mente
        } else if ( player.getMainHandItem().getItem() instanceof ArmorItem) {
            ArmorItem armorItem = (ArmorItem) player.getMainHandItem().getItem();
            int tier = convertMaterialToTier(armorItem.getMaterial().getName());
            if (tier == stack.getTag().getInt("tier_malatium_storage")){
                if (player.getItemInHand(InteractionHand.MAIN_HAND).getDamageValue() == player.getItemInHand(InteractionHand.MAIN_HAND).getMaxDamage()){
                    player.setItemInHand(InteractionHand.MAIN_HAND,ItemStack.EMPTY);
                    return false;
                }
                player.getItemInHand(InteractionHand.MAIN_HAND).setDamageValue(player.getItemInHand(InteractionHand.MAIN_HAND).getDamageValue()+1);
                return true;
            }
        }
        return false;
    }

    public boolean isDecanting(Player player, ItemStack stack) {

        if (player.getMainHandItem().getItem() instanceof TieredItem) {
            TieredItem tiered = (TieredItem) player.getMainHandItem().getItem();
            if (tiered.getTier().getLevel() == stack.getTag().getInt("tier_malatium_storage")){
                if (player.getItemInHand(InteractionHand.MAIN_HAND).getDamageValue() == 0){
                    return false;
                }
                player.getItemInHand(InteractionHand.MAIN_HAND).setDamageValue(player.getItemInHand(InteractionHand.MAIN_HAND).getDamageValue()-1);
                return true;
            }
            return false; //el item no es del tier de la primer carga de la mente
        } else if ( player.getMainHandItem().getItem() instanceof ArmorItem) {
            ArmorItem armorItem = (ArmorItem) player.getMainHandItem().getItem();
            int tier = convertMaterialToTier(armorItem.getMaterial().getName());
            if (tier == stack.getTag().getInt("tier_malatium_storage")){
                if (player.getItemInHand(InteractionHand.MAIN_HAND).getDamageValue() == 0){
                    return false;
                }
                player.getItemInHand(InteractionHand.MAIN_HAND).setDamageValue(player.getItemInHand(InteractionHand.MAIN_HAND).getDamageValue()-1);
                return true;
            }
            return false;
        }
        return false;
    }

    public boolean generateIternalReserve (Player player, ItemStack stack){
        if (player.getMainHandItem().getItem() instanceof TieredItem) {
            TieredItem tiered = (TieredItem) player.getMainHandItem().getItem();
            stack.getTag().putInt("tier_malatium_storage",tiered.getTier().getLevel());
            return true;
        }
        if (player.getMainHandItem().getItem() instanceof ArmorItem){
            ArmorItem armorItem = (ArmorItem) player.getMainHandItem().getItem();
            stack.getTag().putInt("tier_malatium_storage",convertMaterialToTier(armorItem.getMaterial().getName()));
            return true;
        }
        return false;
    }

    public int convertMaterialToTier (String material) {

        if (material.equals(ArmorMaterials.GOLD.getName()) || material.equals(ArmorMaterials.LEATHER.getName())) {
            return 0;
        }else  if (material.equals(ArmorMaterials.TURTLE.getName())){
            return 1;
        } else if (material.equals(ArmorMaterials.IRON.getName()) || material.equals(ArmorMaterials.CHAIN.getName())) {
            return 2;
        } else if (material.equals(ArmorMaterials.DIAMOND.getName())) {
            return 3;
        } else if (material.equals(ArmorMaterials.NETHERITE.getName())) {
            return 4;
        }
        return -1;
    }

    public String convertTierToMaterial (int tier) {
        if (tier == 0){

            return Tiers.GOLD.name()+" "+ArmorMaterials.LEATHER.getName().toUpperCase();
        } else if (tier == 1){
            ArmorMaterials.TURTLE.getName();
        } else if (tier == 2){
            return Tiers.IRON.name()+" "+ArmorMaterials.CHAIN.getName().toUpperCase();
        } else if (tier == 3){
            return Tiers.DIAMOND.name();
        } else if (tier == 4){
            return Tiers.NETHERITE.name();
        }
        return "";
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> toolTips, TooltipFlag flagIn) {
        if (stack.hasTag()) {

            if (!stack.getTag().contains("tier_malatium_storage") || stack.getTag().getInt(getMetals(1).getNameLower()+"_feruchemic_reserve") == 0){
                stack.getTag().putInt("tier_malatium_storage",-1);
            }
            if (!Screen.hasControlDown()){
                toolTips.add(Component.translatable(getMetals(0).getNameLower().substring(0,1).toUpperCase()+getMetals(0).getNameLower().substring(1)+": "+ stack.getTag().getInt(getMetals(0).getNameLower()+"_feruchemic_reserve") / 40 + "s"));
                toolTips.add(Component.translatable(getMetals(1).getNameLower().substring(0,1).toUpperCase()+getMetals(1).getNameLower().substring(1)+": "+ stack.getTag().getInt(getMetals(1).getNameLower()+"_feruchemic_reserve")+" Uses"));
            } else {
                toolTips.add(Component.translatable(getMetals(0).getNameLower().substring(0,1).toUpperCase()+getMetals(0).getNameLower().substring(1)+": "+ ((stack.getTag().getInt(getMetals(0).getNameLower()+"_feruchemic_reserve") * 100)/stack.getTag().getInt(getMetals(0).getNameLower()+"_feruchemic_max_capacity"))+"%"));
                toolTips.add(Component.translatable(getMetals(1).getNameLower().substring(0,1).toUpperCase()+getMetals(1).getNameLower().substring(1)+": "+ ((stack.getTag().getInt(getMetals(1).getNameLower()+"_feruchemic_reserve") * 100)/stack.getTag().getInt(getMetals(1).getNameLower()+"_feruchemic_max_capacity"))+"%"));
            }
            toolTips.add(Component.translatable("Owner: "+ (stack.getTag().getString("key"))));
            if (stack.getTag().getInt("tier_malatium_storage")!=-1){
                toolTips.add(Component.translatable("-------------------"));
                toolTips.add(Component.translatable("Tier: "+convertTierToMaterial(stack.getTag().getInt("tier_malatium_storage"))));
            }
        }
        super.appendHoverText(stack, world, toolTips, flagIn);
    }






}