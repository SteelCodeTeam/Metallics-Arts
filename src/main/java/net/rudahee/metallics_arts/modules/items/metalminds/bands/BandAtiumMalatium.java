package net.rudahee.metallics_arts.modules.items.metalminds.bands;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.world.NoteBlockEvent;
import net.rudahee.metallics_arts.modules.data_player.IDefaultInvestedPlayerData;
import net.rudahee.metallics_arts.modules.data_player.InvestedCapability;
import net.rudahee.metallics_arts.modules.items.vials.small_vial.SmallVial;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import javax.annotation.Nullable;
import java.util.List;

public class BandAtiumMalatium extends BandMindAbstract {

    public BandAtiumMalatium (Item.Properties properties){
        super(properties, MetalsNBTData.ATIUM,MetalsNBTData.MALATIUM,MetalsNBTData.ATIUM.getMaxReserveBand(),MetalsNBTData.MALATIUM.getMaxReserveBand());
    }

    private static boolean needUpdate = false;

    @Override
    public void curioTick(String identifier, int index, LivingEntity livingEntity, ItemStack stack) {
        CompoundNBT nbtLocal = stack.getTag();

        if (livingEntity.level instanceof ServerWorld) {
            needUpdate = false;
            if (livingEntity instanceof PlayerEntity) {
                PlayerEntity player = (PlayerEntity) livingEntity;
                player.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(data -> {

                    if (data.isDecanting(MetalsNBTData.ALUMINUM)||data.isStoring(MetalsNBTData.ALUMINUM)){
                        stack.getTag().putString("key",changeOwner(player,stack.getTag(),false));
                    }

                    if (data.isDecanting(getMetals(0))) {
                        if (stack.getTag().getInt(getMetals(0).getNameLower()+"_feruchemic_reserve")>0) {
                            nbtLocal.putInt(getMetals(0).getNameLower()+"_feruchemic_reserve",(stack.getTag().getInt(getMetals(0).getNameLower()+"_feruchemic_reserve")-1));
                            stack.setTag(nbtLocal);
                        } else {
                            stack.getTag().putString("key",changeOwner(player,stack.getTag(),false));
                            data.setDecanting(getMetals(0),false);
                        }
                        needUpdate = true;
                    } else if (data.isStoring(getMetals(0))) {
                        if (stack.getTag().getInt(getMetals(0).getNameLower()+"_feruchemic_reserve") < stack.getTag().getInt(getMetals(0).getNameLower()+"_feruchemic_max_capacity")) {
                            stack.getTag().putString("key",changeOwner(player,stack.getTag(),true));
                            nbtLocal.putInt(getMetals(0).getNameLower()+"_feruchemic_reserve",(stack.getTag().getInt(getMetals(0).getNameLower()+"_feruchemic_reserve")+1));
                            stack.setTag(nbtLocal);
                        } else {
                            data.setStoring(getMetals(0),false);
                        }
                        needUpdate = true;
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
                        needUpdate = true;

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
                        needUpdate = true;
                    }
                });
            }
        }
        super.curioTick(identifier, index, livingEntity, stack);
    }


    public boolean isStoring (PlayerEntity player, ItemStack stack){

        if (stack.getTag().getInt("tier_malatium_storage") == -1){
            if (!generateIternalReserve(player, stack)){
                return false;
            }
        }

        if (player.getMainHandItem().getItem() instanceof TieredItem ) {
            TieredItem tiered = (TieredItem) player.getMainHandItem().getItem();
            if (tiered.getTier().getLevel() == stack.getTag().getInt("tier_malatium_storage")){
                if (player.getItemInHand(Hand.MAIN_HAND).getDamageValue() == player.getItemInHand(Hand.MAIN_HAND).getMaxDamage()){
                    player.setItemInHand(Hand.MAIN_HAND,ItemStack.EMPTY);
                    player.level.playLocalSound(player.getX(),player.getY(),player.getZ(), SoundEvents.ITEM_BREAK, SoundCategory.NEUTRAL,1.0f, 2.0f, true);
                    return false;
                }
                player.getItemInHand(Hand.MAIN_HAND).setDamageValue(player.getItemInHand(Hand.MAIN_HAND).getDamageValue()+1);
                return true;
            }
            return false; //el item no es del tier de la primer carga de la mente
        } else if ( player.getMainHandItem().getItem() instanceof ArmorItem) {
            ArmorItem armorItem = (ArmorItem) player.getMainHandItem().getItem();
            int tier = convertMaterialToTier(armorItem.getMaterial().getName());
            if (tier == stack.getTag().getInt("tier_malatium_storage")){
                if (player.getItemInHand(Hand.MAIN_HAND).getDamageValue() == player.getItemInHand(Hand.MAIN_HAND).getMaxDamage()){
                    player.setItemInHand(Hand.MAIN_HAND,ItemStack.EMPTY);
                    return false;
                }
                player.getItemInHand(Hand.MAIN_HAND).setDamageValue(player.getItemInHand(Hand.MAIN_HAND).getDamageValue()+1);
                return true;
            }
        }
        return false;
    }

    public boolean isDecanting(PlayerEntity player, ItemStack stack) {

        if (player.getMainHandItem().getItem() instanceof TieredItem) {
            TieredItem tiered = (TieredItem) player.getMainHandItem().getItem();
            if (tiered.getTier().getLevel() == stack.getTag().getInt("tier_malatium_storage")){
                if (player.getItemInHand(Hand.MAIN_HAND).getDamageValue() == 0){
                    return false;
                }
                player.getItemInHand(Hand.MAIN_HAND).setDamageValue(player.getItemInHand(Hand.MAIN_HAND).getDamageValue()-1);
                return true;
            }
            return false; //el item no es del tier de la primer carga de la mente
        } else if ( player.getMainHandItem().getItem() instanceof ArmorItem) {
            ArmorItem armorItem = (ArmorItem) player.getMainHandItem().getItem();
            int tier = convertMaterialToTier(armorItem.getMaterial().getName());
            if (tier == stack.getTag().getInt("tier_malatium_storage")){
                if (player.getItemInHand(Hand.MAIN_HAND).getDamageValue() == 0){
                    return false;
                }
                player.getItemInHand(Hand.MAIN_HAND).setDamageValue(player.getItemInHand(Hand.MAIN_HAND).getDamageValue()-1);
                return true;
            }
            return false;
        }
        return false;
    }

    public boolean generateIternalReserve (PlayerEntity player, ItemStack stack){
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
        if (material.equals(ArmorMaterial.GOLD.getName()) || material.equals(ArmorMaterial.LEATHER.getName())) {
            return 0;
        }else  if (material.equals(ArmorMaterial.TURTLE.getName())){
            return 1;
        } else if (material.equals(ArmorMaterial.IRON.getName()) || material.equals(ArmorMaterial.CHAIN.getName())) {
            return 2;
        } else if (material.equals(ArmorMaterial.DIAMOND.getName())) {
            return 3;
        } else if (material.equals(ArmorMaterial.NETHERITE.getName())) {
            return 4;
        }
        return -1;
    }

    public String convertTierToMaterial (int tier) {
        if (tier == 0){
            return ItemTier.GOLD.name()+" "+ArmorMaterial.LEATHER.getName().toUpperCase();
        } else if (tier == 1){
            ArmorMaterial.TURTLE.getName();
        } else if (tier == 2){
            return ItemTier.IRON.name()+" "+ArmorMaterial.CHAIN.getName().toUpperCase();
        } else if (tier == 3){
            return ItemTier.DIAMOND.name();
        } else if (tier == 4){
            return ItemTier.NETHERITE.name();
        }
        return "";
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> toolTips, ITooltipFlag flagIn) {
        if (stack.hasTag()) {
            if (stack.getTag().getInt(getMetals(1).getNameLower()+"_feruchemic_reserve")== 0){
                stack.getTag().putInt("tier_malatium_storage",-1);
            }
            if (!Screen.hasControlDown()){
                toolTips.add(new StringTextComponent(getMetals(0).getNameLower().substring(0,1).toUpperCase()+getMetals(0).getNameLower().substring(1)+": "+ stack.getTag().getInt(getMetals(0).getNameLower()+"_feruchemic_reserve") / 40 + "s"));
                toolTips.add(new StringTextComponent(getMetals(1).getNameLower().substring(0,1).toUpperCase()+getMetals(1).getNameLower().substring(1)+": "+ stack.getTag().getInt(getMetals(1).getNameLower()+"_feruchemic_reserve")+" Uses"));
            } else {
                toolTips.add(new StringTextComponent(getMetals(0).getNameLower().substring(0,1).toUpperCase()+getMetals(0).getNameLower().substring(1)+": "+ ((stack.getTag().getInt(getMetals(0).getNameLower()+"_feruchemic_reserve") * 100)/stack.getTag().getInt(getMetals(0).getNameLower()+"_feruchemic_max_capacity"))+"%"));
                toolTips.add(new StringTextComponent(getMetals(1).getNameLower().substring(0,1).toUpperCase()+getMetals(1).getNameLower().substring(1)+": "+ ((stack.getTag().getInt(getMetals(1).getNameLower()+"_feruchemic_reserve") * 100)/stack.getTag().getInt(getMetals(1).getNameLower()+"_feruchemic_max_capacity"))+"%"));
            }
            toolTips.add(new StringTextComponent("Owner: "+ (stack.getTag().getString("key"))));
            if (stack.getTag().getInt("tier_malatium_storage")!=-1){
                toolTips.add(new StringTextComponent("-------------------"));
                toolTips.add(new StringTextComponent("Tier: "+convertTierToMaterial(stack.getTag().getInt("tier_malatium_storage"))));
            }
        }
        super.appendHoverText(stack, world, toolTips, flagIn);
    }






}