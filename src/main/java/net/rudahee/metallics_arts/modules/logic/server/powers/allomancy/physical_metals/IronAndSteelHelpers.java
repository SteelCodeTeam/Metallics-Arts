package net.rudahee.metallics_arts.modules.logic.server.powers.allomancy.physical_metals;


import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
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
import net.rudahee.metallics_arts.data.configs.MetalListConfig;
import net.rudahee.metallics_arts.data.enums.implementations.MetalEnum;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.setup.registries.ModItemsRegister;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * A utility class that provides helper methods for various functionalities related to Iron and Steel,
 * such as manipulating entities' behavior, applying effects, and more.
 *
 * The class is not meant to be instantiated, as it contains only static methods.
 *
 * @author SteelCode Team
 * @since 1.5.1
 */
public class IronAndSteelHelpers {

    public static final byte PUSH = 1;
    public static final byte PULL = -1;

    private static final Pattern ACTIVE_METAL_REGEX = Pattern.compile(
            ".*(iron|steel|tin_|pewter|zinc|brass|copper|bronze|duralumin|chromium|nicrosil|gold|electrum|cadmium|bendalloy|lead_|silver|platinum|nickle|nickel).*");

    public static boolean doesResourceContainsMetal(ResourceLocation input) {
        return ACTIVE_METAL_REGEX.matcher(input.getPath()).matches();
    }

    /**
     * This method searches if the player has nuggets of any metal that is not aluminum or silver, and if they do, it returns the first inventory position where they are found.
     *
     * @param player who is being checked.
     *
     * @return int
     */
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

    /**
     * Checks if the given BlockState is a metal block.
     *
     * @param state the BlockState to check
     * @return true if the block is a metal block, false otherwise
     */
    public static boolean isBlockStateMetal(BlockState state) {
        return isBlockMetal(state.getBlock());
    }

    /**
     * Checks if the given Block is a metal block.
     *
     * @param block the Block to check
     * @return true if the block is a metal block, false otherwise
     */
    public static boolean isBlockMetal(Block block) {
        return isOnWhitelist(block.getDescriptionId());
    }

    /**
     * Checks if the given ItemStack is a metal item.
     *
     * @param item the ItemStack to check
     * @return true if the item is a metal item, false otherwise
     */
    public static boolean isItemMetal(ItemStack item) {
        return isOnWhitelist(item.getItem().getDescription().toString());
    }

    /**
     * Checks if the given string is on the whitelist by comparing it against the MetalListConfig whitelist.
     *
     * @param s the String to check against the whitelist
     * @return true if the string is on the whitelist, false otherwise
     */
    private static boolean isOnWhitelist(String s) {
       return MetalListConfig.whitelist.stream().anyMatch(ws -> s.contains(ws));
    }

    /**
     * Checks if the given Entity is a metal entity.
     *
     * @param entity the Entity to check
     * @return true if the entity is a metal entity, false otherwise
     */
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

    /**
     * Clamps a Vec3 value between given minimum and maximum Vec3 values.
     *
     * @param value the Vec3 value to clamp
     * @param min the minimum Vec3 value
     * @param max the maximum Vec3 value
     * @return a new Vec3 with clamped x, y, and z components
     */
    private static Vec3 clamp(Vec3 value, Vec3 min, Vec3 max) {
        return new Vec3(Mth.clamp(value.x, min.x, max.x), Mth.clamp(value.y, min.y, max.y), Mth.clamp(value.z, min.z, max.z));
    }

    /**
     * Returns a new Vec3 with absolute values of the given Vec3's components.
     *
     * @param vec the Vec3 to get the absolute values from
     * @return a new Vec3 with absolute x, y, and z components
     */
    private static Vec3 abs(Vec3 vec) {
        return new Vec3(Math.abs(vec.x), Math.abs(vec.y), Math.abs(vec.z));
    }

    /**
     * Cuts off the components of a Vec3 value below a given threshold.
     *
     * @param value the Vec3 value to apply the cutoff to
     * @param e the threshold value for the cutoff
     * @return a new Vec3 with components cut off below the threshold
     */
    private static Vec3 cutoff(Vec3 value, double e) {
        Vec3 mag = abs(value);
        return new Vec3(mag.x < e ? 0 : value.x, mag.y < e ? 0 : value.y, mag.z < e ? 0 : value.z);
    }

    /**
     * Moves the given Entity in the specified direction by a scalar value.
     *
     * @param directionScalar the scalar value to move the entity
     * @param toMove the Entity to move
     * @param block the BlockPos representing the direction in which to move the entity
     */
    public static void move(double directionScalar, Entity toMove, BlockPos block) {
        move(directionScalar, toMove, block, false);
    }

    /**
     * Moves the given Entity in the specified direction by a scalar value, with an optional flag to stop on contact.
     *
     * @param directionScalar the scalar value to move the entity
     * @param toMove the Entity to move
     * @param block the BlockPos representing the direction in which to move the entity
     * @param weightModified flag determining whether the entity should stop on contact with another entity
     */
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

    /**
     * Calculates a multiplier value based on the player and the given duralumin and lerasium flags.
     *
     * @param player the Player for which the multiplier is calculated
     * @param duralumin the boolean flag representing whether duralumin is considered in the calculation
     * @param lerasium the boolean flag representing whether lerasium is considered in the calculation
     * @return the calculated multiplier as a float value (1f, 2f, 4f, or 6f)
     */
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
