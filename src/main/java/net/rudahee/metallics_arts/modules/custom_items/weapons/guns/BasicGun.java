package net.rudahee.metallics_arts.modules.custom_items.weapons.guns;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.rudahee.metallics_arts.data.enums.implementations.GunType;
import net.rudahee.metallics_arts.data.enums.implementations.GunsAccess;
import net.rudahee.metallics_arts.setup.registries.ModItemsRegister;
import net.rudahee.metallics_arts.setup.registries.ModKeyRegister;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Predicate;

/**
 * The BasicGun class represents a basic gun item that extends the Item class.
 * It allows the player to use, reload, and interact with the gun in the game.
 *
 * @author SteelCode Team
 * @since 1.6.5
 *
 * @see Item
 * @see GunUtils
 * @see ShotGun
 * @see Rifle
 */


public class BasicGun extends ProjectileWeaponItem {
    private GunType gunType;


    /**
     * Constructs a new BasicGun with the given properties and gun type.
     *
     * @param properties The properties of the basic gun.
     * @param gunType    The Weapon type
     * @see GunType
     */
    public BasicGun(Properties properties, GunType gunType) {
        super(properties);
        this.gunType = gunType;
    }

    /**
     * Handles the use interaction of the basic gun. Updates the gun's custom model data
     * and starts using the item.
     *
     * @param level for the player.
     * @param player The player who uses the basic gun.
     * @param hand The hand used to interact with the basic gun.
     * @return The result of the interaction with the basic gun.
     */
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {

        ItemStack itemStack = player.getItemInHand(hand);
        CompoundTag tag = itemStack.getTag();
        tag.putFloat("CustomModelData", 1);
        if (tag.getString(GunsAccess.STATE.getKey()).equals(GunsAccess.RELOAD.getKey())) {
            tag.putString(GunsAccess.STATE.getKey(), GunsAccess.READY.getKey());
        }
        itemStack.setTag(tag);
        player.startUsingItem(hand);
        return InteractionResultHolder.consume(itemStack);
    }

    /**
     * Releases the use of the item after holding it for a certain duration.
     *
     * @param stack The ItemStack being released after usage.
     * @param level The Level (world) in which the item is used.
     * @param livingEntity The living entity using the item.
     * @param i The duration of item usage.
     */
    @Override
    public void releaseUsing(ItemStack stack, Level level, LivingEntity livingEntity, int i) {
        ItemStack itemStack = livingEntity.getItemInHand(InteractionHand.MAIN_HAND);
        CompoundTag tag = itemStack.getTag();
        tag.putFloat("CustomModelData", 0);
        itemStack.setTag(tag);
        super.releaseUsing(stack, level, livingEntity, i);
    }

    /**
     * Called every tick for the gun item while it is in the player's inventory.
     * It handles actions and updates related to the gun item's state.
     *
     * @param stack The ItemStack being ticked.
     * @param level The Level (world) in which the item exists.
     * @param entity The entity associated with the inventory containing the item.
     * @param selectedSlot The selected slot in the entity's inventory.
     * @param hasItemSelected A boolean indicating whether the player has this item selected.
     */
    private int tick = 0;

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int selectedSlot, boolean hasItemSelected) {
        if (!GunUtils.hasTags(stack.getTag())) {
            stack.setTag(GunUtils.generateGunTags(this.gunType));
        }
        if (entity instanceof Player player) {
            if (player.getMainHandItem() == stack) {
                if (stack.getTag().getString(GunsAccess.STATE.getKey()).equals(GunsAccess.RELOAD.getKey())) {
                    if ((tick % this.gunType.getReload_cooldown()) == 0) {
                        stack.setTag(GunUtils.reload(stack.getTag(), player, gunType));

                        tick = 0;
                    }
                    tick++;
                }
            } else {
                stack.getTag().putString(GunsAccess.STATE.getKey(), GunsAccess.READY.getKey());
                stack.getTag().putFloat("CustomModelData", 0);
            }
        }
        super.inventoryTick(stack, level, entity, selectedSlot, hasItemSelected);
    }

    /**
     * Adds additional information to the tooltip when hovering over the gun item.
     *
     * @param stack The ItemStack representing the gun item.
     * @param level The current game level.
     * @param toolTips The list of tooltip components to add information to.
     * @param flagIn The tooltip flag for additional control.
     */
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> toolTips, TooltipFlag flagIn) {
        if (!GunUtils.hasTags(stack.getTag())) {
            stack.setTag(GunUtils.generateGunTags(this.gunType));
        } else {
            toolTips.add(Component.translatable("util.metallics_arts.bullet_type." + stack.getTag().getString(GunsAccess.BULLET_TYPE.getKey())));
        }
        super.appendHoverText(stack, level, toolTips, flagIn);
    }

    /**
     * Checks if the durability bar should be visible for the gun item.
     *
     * @param itemStack The ItemStack representing the gun item.
     * @return True if the durability bar should be visible, otherwise false.
     */
    @Override
    public boolean isBarVisible(ItemStack itemStack) {
        if (itemStack.hasTag()) {
            if (itemStack.getTag().contains(GunsAccess.BULLETS.getKey())) {
                return itemStack.getTag().getInt(GunsAccess.BULLETS.getKey()) > 0;
            }
        }
        return false;
    }

    /**
     * Gets the width of the durability bar for the gun item.
     *
     * @param itemStack The ItemStack representing the gun item.
     * @return The width of the durability bar.
     */
    @Override
    public int getBarWidth(ItemStack itemStack) {
        if (itemStack.hasTag()) {
            if (itemStack.getTag().contains(GunsAccess.BULLETS.getKey())) {
                return Math.round(((float) itemStack.getTag().getInt(GunsAccess.BULLETS.getKey()) * 13F / (float) gunType.getMaxAmount()));
            }
        }
        return 0;
    }

    /**
     * Gets the type of the gun.
     *
     * @return The GunType of the gun.
     */
    public GunType getGunType() {
        return gunType;
    }

    /**
     * Called when an entity swings the gun item.
     *
     * @param stack The ItemStack representing the gun item.
     * @param entity The entity swinging the gun item.
     * @return Always returns true to allow entity swinging with the gun.
     */
    @Override
    public boolean onEntitySwing(ItemStack stack, LivingEntity entity) {
        return true;
    }


    /**
     * Gets the maximum duration for which the gun item can be used.
     *
     * @param itemStack The ItemStack representing the gun item.
     * @return The maximum duration for item usage.
     */
    @Override
    public int getUseDuration(ItemStack itemStack) {
        return 72000;
    }

    /**
     * Checks whether the ItemStack can be enchanted.
     *
     * @param itemStack The ItemStack to be checked for enchantability.
     *
     * @return False, as this item is not enchantable.
     */
    @Override
    public boolean isEnchantable(ItemStack itemStack) {
        return false;
    }

    /**
     * Retrieves a predicate representing all supported projectiles for this item.
     *
     * @return A predicate that checks if an ItemStack is one of the supported projectiles.
     */
    @Override
    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return itemStack -> itemStack.is(ModItemsRegister.REVOLVER_LEAD_BULLET.get()) || itemStack.is(ModItemsRegister.REVOLVER_ALUMINUM_BULLET.get());
    }

    /**
     * Gets the default range for projectiles fired from this item.
     *
     * @return The default projectile range, which is set to 8.
     */
    @Override
    public int getDefaultProjectileRange() {
        return 8;
    }



}
