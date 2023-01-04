package net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.rudahee.metallics_arts.data.players.IInvestedPlayerData;

import java.util.function.Supplier;

public class MalatiumFecuchemicHelper extends AbstractFechuchemicHelper{

    @Override
    public void decantPower(Player player) {

    }

    @Override
    public void storagePower(Player player) {

    }

    public static Supplier<? extends MalatiumFecuchemicHelper> getInstance() {
        return MalatiumFecuchemicHelper::new;
    }

    @Override
    public CompoundTag calculateDischarge(CompoundTag compoundTag, Player player, IInvestedPlayerData playerCapability, int metalReserve, String metalKey, boolean nicConsume) {
        if (isDecanting(player,compoundTag)) {
            compoundTag.putInt(metalKey, metalReserve - 1);
        }
        return compoundTag;
    }

    @Override
    public CompoundTag CalculateCharge(CompoundTag compoundTag, Player player, IInvestedPlayerData playerCapability, int metalReserve, String metalKey, boolean nicConsume) {
        if (isStoring(player,compoundTag)) {
            compoundTag.putInt(metalKey, metalReserve + 1);
        }
        return compoundTag;
    }


    public boolean isDecanting(Player player, CompoundTag compoundTag) {
        if (player.getMainHandItem().getItem() instanceof TieredItem) {
            TieredItem tiered = (TieredItem) player.getMainHandItem().getItem();
            if (tiered.getTier().getLevel() == compoundTag.getInt("tier_malatium_storage")){
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
            if (tier == compoundTag.getInt("tier_malatium_storage")){
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

    public boolean isStoring (Player player, CompoundTag compoundTag){
        if (!compoundTag.contains("tier_malatium_storage")){
            compoundTag.putInt("tier_malatium_storage",-1);
        }
        if (compoundTag.getInt("tier_malatium_storage") == -1){
            if (!generateIternalReserve(player, compoundTag)){
                return false;
            }
        }

        if (player.getMainHandItem().getItem() instanceof TieredItem) {
            TieredItem tiered = (TieredItem) player.getMainHandItem().getItem();
            if (tiered.getTier().getLevel() == compoundTag.getInt("tier_malatium_storage")){
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
            if (tier == compoundTag.getInt("tier_malatium_storage")){
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

    public boolean generateIternalReserve (Player player, CompoundTag compoundTag){
        if (player.getMainHandItem().getItem() instanceof TieredItem) {
            TieredItem tiered = (TieredItem) player.getMainHandItem().getItem();
            compoundTag.putInt("tier_malatium_storage",tiered.getTier().getLevel());
            return true;
        }
        if (player.getMainHandItem().getItem() instanceof ArmorItem){
            ArmorItem armorItem = (ArmorItem) player.getMainHandItem().getItem();
            compoundTag.putInt("tier_malatium_storage",convertMaterialToTier(armorItem.getMaterial().getName()));
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
        } else if (material.equals("Obsidian")) {
            return 6;
        }
        return -1;
    }

}
