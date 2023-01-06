package net.rudahee.metallics_arts.modules.logic.server.powers;


import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.rudahee.metallics_arts.data.configs.MetallicsPowersConfig;
import net.rudahee.metallics_arts.data.enums.implementations.MetalEnum;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.setup.registries.ModItemsRegister;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class IronAndSteelHelpers {

    public static final byte PUSH = 1;
    public static final byte PULL = -1;

    private static final Pattern ACTIVE_METAL_REGEX = Pattern.compile(
            ".*(iron|steel|tin_|pewter|zinc|brass|copper|bronze|duralumin|chromium|nicrosil|gold|electrum|cadmium|bendalloy|lead_|silver|platinum|nickle|nickel).*");

    public static boolean doesResourceContainsMetal(ResourceLocation input) {
        return ACTIVE_METAL_REGEX.matcher(input.getPath()).matches();
    }

    public static int haveNuggets(Player player){

        List <Item> list = new ArrayList<>();

        list.addAll(ModItemsRegister.ITEM_METAL_NUGGET.values());
        list.addAll(ModItemsRegister.ITEM_GEMS_NUGGET.values());

        /**Get a list of nuggets of every metal except, ALUMINIUM and SILVER*/
        list = list.stream()
                .filter(item -> !item.equals(ModItemsRegister.ITEM_METAL_NUGGET.get(MetalTagEnum.ALUMINUM.getGemNameLower())))
                .filter(item -> !item.equals(ModItemsRegister.ITEM_METAL_NUGGET.get(MetalEnum.SILVER.getMetalNameLower())))
                .collect(Collectors.toList());list.add(Items.IRON_NUGGET);
        list.add(Items.GOLD_NUGGET);

        for (ItemStack stack: player.getInventory().items){
            if (list.contains(stack.getItem())){
                return player.getInventory().findSlotMatchingItem(stack);
            }
        }
        return -1;
    }

    public static boolean isBlockStateMetal(BlockState state) {
        boolean a = isBlockMetal(state.getBlock());
        return a;
    }

    public static boolean isBlockMetal(Block block) {
        boolean a = isOnWhitelist(block.getDescriptionId());
        return a;
    }

    public static boolean isItemMetal(ItemStack item) {
        return isOnWhitelist(item.getItem().getDescription().toString());
    }

    private static boolean isOnWhitelist(String s) {
       return MetallicsPowersConfig.whitelist.stream().anyMatch(ws -> s.contains(ws));
    }

    public static boolean isEntityMetal(Entity entity) {
        if (entity == null) {
            return false;
        }
        if (entity instanceof ItemEntity) {
            return isItemMetal(((ItemEntity) entity).getItem());
        }
        if (entity instanceof ItemFrame) {
            return isItemMetal(((ItemFrame) entity).getItem());
        }
        if (entity instanceof FallingBlockEntity) {
            return isBlockStateMetal(((FallingBlockEntity) entity).getBlockState());
        }
        if (entity instanceof AbstractMinecart) {
            return true;
        }
        if (entity instanceof LivingEntity) {
            LivingEntity ent = (LivingEntity) entity;
            if (ent instanceof IronGolem) {
                return true;
            }
            if (isItemMetal(ent.getItemInHand(InteractionHand.MAIN_HAND)) || isItemMetal(ent.getItemInHand(InteractionHand.MAIN_HAND))) {
                return true;
            }
            for (ItemStack itemStack : ent.getArmorSlots()) {
                if (isItemMetal(itemStack)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static Vec3 clamp(Vec3 value, Vec3 min, Vec3 max) {
        return new Vec3(Mth.clamp(value.x, min.x, max.x), Mth.clamp(value.y, min.y, max.y), Mth.clamp(value.z, min.z, max.z));
    }
    private static Vec3 abs(Vec3 vec) {
        return new Vec3(Math.abs(vec.x), Math.abs(vec.y), Math.abs(vec.z));
    }
    private static Vec3 cutoff(Vec3 value, double e) {
        Vec3 mag = abs(value);
        return new Vec3(mag.x < e ? 0 : value.x, mag.y < e ? 0 : value.y, mag.z < e ? 0 : value.z);
    }
    public static void move(double directionScalar, Entity toMove, BlockPos block) {
        move(directionScalar, toMove, block, false);
    }
    public static void move(double directionScalar, Entity toMove, BlockPos block, @Nullable Boolean weightModified) {

        if (toMove.isPassenger()) {
            toMove = toMove.getVehicle();
        }
        Vec3 motion;

        if(weightModified == null || !weightModified) {
            motion = toMove.position().subtract(Vec3.atCenterOf(block)).normalize().scale(directionScalar * 1.1);
        } else {
            motion = toMove.position().subtract(Vec3.atCenterOf(block)).normalize().scale(directionScalar * 1.6);
        }

        Vec3 mod = clamp(cutoff(motion.add(toMove.getDeltaMovement()), 0.1), abs(motion).reverse(), abs(motion));


        toMove.setDeltaMovement(mod);
        toMove.hurtMarked = true;

        // Only save players from fall damage
        if (toMove instanceof ServerPlayer) {
            toMove.fallDistance = 0;
        }
    }

    public static float getMultiplier(Player player, boolean duralumin, boolean lerasium) {
        if (duralumin && lerasium){
            return 6f;
        } else if (duralumin && !lerasium){
            return 4f;
        } else if (!duralumin && lerasium){
            return 2f;
        } else {
            return 1f;
        }
    }

}
