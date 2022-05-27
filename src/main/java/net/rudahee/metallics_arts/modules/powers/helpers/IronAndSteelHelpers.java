package net.rudahee.metallics_arts.modules.powers.helpers;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.FallingBlockEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.item.ItemFrameEntity;
import net.minecraft.entity.item.minecart.AbstractMinecartEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.rudahee.metallics_arts.modules.powers.MetallicsPowersConfig;
import net.rudahee.metallics_arts.modules.powers.MetallicsPowersSetup;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class IronAndSteelHelpers {

    public static final byte PUSH = 1;
    public static final byte PULL = -1;
    private static final Pattern ACTIVE_METAL_REGEX = Pattern.compile(
            ".*(iron|steel|tin_|pewter|zinc|brass|copper|bronze|duralumin|chromium|nicrosil|gold|electrum|cadmium|bendalloy|lead_|silver|platinum|nickle|nickel).*");
    public static boolean doesResourceContainsMetal(ResourceLocation input) {
        return ACTIVE_METAL_REGEX.matcher(input.getPath()).matches();
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
        return isOnWhitelist(item.getItem().getRegistryName().toString());
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
        if (entity instanceof ItemFrameEntity) {
            return isItemMetal(((ItemFrameEntity) entity).getItem());
        }
        if (entity instanceof FallingBlockEntity) {
            return isBlockStateMetal(((FallingBlockEntity) entity).getBlockState());
        }
        if (entity instanceof AbstractMinecartEntity) {
            return true;
        }
        if (entity instanceof LivingEntity) {
            LivingEntity ent = (LivingEntity) entity;
            if (ent instanceof IronGolemEntity) {
                return true;
            }
            if (isItemMetal(ent.getItemInHand(Hand.MAIN_HAND)) || isItemMetal(ent.getItemInHand(Hand.OFF_HAND))) {
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

    private static Vector3d clamp(Vector3d value, Vector3d min, Vector3d max) {
        return new Vector3d(MathHelper.clamp(value.x, min.x, max.x), MathHelper.clamp(value.y, min.y, max.y), MathHelper.clamp(value.z, min.z, max.z));
    }
    private static Vector3d abs(Vector3d vec) {
        return new Vector3d(Math.abs(vec.x), Math.abs(vec.y), Math.abs(vec.z));
    }
    private static Vector3d cutoff(Vector3d value, double e) {
        Vector3d mag = abs(value);
        return new Vector3d(mag.x < e ? 0 : value.x, mag.y < e ? 0 : value.y, mag.z < e ? 0 : value.z);
    }

    public static void move(double directionScalar, Entity toMove, BlockPos block) {

        if (toMove.isPassenger()) {
            toMove = toMove.getVehicle();
        }

        Vector3d motion = toMove.position().subtract(Vector3d.atCenterOf(block)).normalize().scale(directionScalar * 1.1);
        Vector3d mod = clamp(cutoff(motion.add(toMove.getDeltaMovement()), 0.1), abs(motion).reverse(), abs(motion));
        toMove.setDeltaMovement(mod);
        toMove.hurtMarked = true;

        // Only save players from fall damage
        if (toMove instanceof ServerPlayerEntity) {
            toMove.fallDistance = 0;
        }
    }

    public static float getMultiplier(PlayerEntity player) {
        return 2.4f;
    }


    public static void addSpeed(PlayerEntity player, int effectLevel){
        player.addEffect(new EffectInstance(Effects.MOVEMENT_SPEED, 20, effectLevel, true, false));
    }

    public static void removeSpeed(PlayerEntity player, int effectLevel){
        player.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 20, effectLevel, true, false));
    }
}
