package net.rudahee.metallics_arts.modules.custom_items.weapons.guns;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.rudahee.metallics_arts.data.enums.implementations.BulletType;
import net.rudahee.metallics_arts.data.enums.implementations.GunType;
import net.rudahee.metallics_arts.data.enums.implementations.GunsAccess;
import net.rudahee.metallics_arts.modules.custom_projectiles.BulletProjectile;
import net.rudahee.metallics_arts.setup.registries.ModItemsRegister;

import java.util.Random;


/**
 * Utility class containing static methods related to gun functionality.
 *
 * @author SteelCode Team
 * @since 1.6.4
 */
public class GunUtils {

    /**
     * This method handles the reloading of a gun owned by a player, based on the specified bullet type and gun type.
     * The method updates the gun's CompoundTag to reflect to reload, including changes to bullet count and
     * custom model data based on the gun type.
     *
     * @param gun The ItemStack representing the gun to be reloaded.
     * @param player The player who owns the gun.
     * @param gunType The type of gun.
     * @return The updated CompoundTag of the reloaded gun ItemStack.
     */
    public static CompoundTag reload(ItemStack gun, Player player, GunType gunType) {
        CompoundTag compoundTag = gun.getTag();

        int slot = hasBulletOfType(player, compoundTag.getString(GunsAccess.BULLET_TYPE.getKey()), gunType);
        if (slot != -1 && compoundTag.getInt(GunsAccess.BULLETS.getKey()) < gunType.getMaxAmount()) {

            compoundTag.putInt(GunsAccess.BULLETS.getKey(), compoundTag.getInt(GunsAccess.BULLETS.getKey()) + 1);

            player.getInventory().removeItem(slot, 1);

            int bullets = compoundTag.getInt(GunsAccess.BULLETS.getKey());
            if (gunType == GunType.SHOTGUN) {
                if (bullets == 1) {
                    compoundTag.putFloat("CustomModelData", 3);
                } else {
                    compoundTag.putFloat("CustomModelData", 4);
                }
            } else if (gunType == GunType.RIFLE || gunType == GunType.RIFLE_SPYGLASS) {
                if (bullets == 1) {
                    compoundTag.putFloat("CustomModelData", 3);
                }
            } else if (gunType == GunType.REVOLVER) {
                if (bullets == 1) {
                    compoundTag.putFloat("CustomModelData", 3);
                } else if (bullets == 2) {
                    compoundTag.putFloat("CustomModelData", 4);
                } else if (bullets == 3) {
                    compoundTag.putFloat("CustomModelData", 5);
                } else if (bullets == 4) {
                    compoundTag.putFloat("CustomModelData", 6);
                } else if (bullets == 5) {
                    compoundTag.putFloat("CustomModelData", 7);
                } else {
                    compoundTag.putFloat("CustomModelData", 8);
                }
            } else if (gunType == GunType.VINDICATOR) {
                if (bullets == 1) {
                    compoundTag.putFloat("CustomModelData", 3);
                } else if (bullets == 2) {
                    compoundTag.putFloat("CustomModelData", 4);
                } else if (bullets == 3) {
                    compoundTag.putFloat("CustomModelData", 5);
                } else if (bullets == 4) {
                    compoundTag.putFloat("CustomModelData", 6);
                } else if (bullets == 5) {
                    compoundTag.putFloat("CustomModelData", 7);
                } else if (bullets == 6) {
                    compoundTag.putFloat("CustomModelData", 8);
                } else if (bullets == 7) {
                    compoundTag.putFloat("CustomModelData", 9);
                }
                else {
                    compoundTag.putFloat("CustomModelData", 10);
                }
            }
        } else {
            compoundTag.putString(GunsAccess.STATE.getKey(), GunsAccess.READY.getKey());
            compoundTag.putFloat("CustomModelData", 0);
        }
        return compoundTag;
    }


    /**
     * Checks if the player has a bullet of the specified type and gun type in their inventory.
     *
     * @param player The player whose inventory is checked for the bullet.
     * @param type The type of bullet to check for (e.g., "lead" or "aluminum").
     * @param gunType The type of the gun for which the bullet is needed.
     * @return The slot index of the bullet item if found, otherwise -1.
     */
    private static int hasBulletOfType(Player player, String type, GunType gunType) {
        Item item;
        if (gunType == GunType.SHOTGUN) {
            item = type.equals(BulletType.LEAD.getType()) ? ModItemsRegister.SHOTGUN_LEAD_BULLET.get() : ModItemsRegister.SHOTGUN_ALUMINUM_BULLET.get();
        } else if (gunType == GunType.RIFLE || gunType == GunType.RIFLE_SPYGLASS) {
            item = type.equals(BulletType.LEAD.getType()) ? ModItemsRegister.RIFLE_LEAD_BULLET.get() : ModItemsRegister.RIFLE_ALUMINUM_BULLET.get();
        } else {
            item = type.equals(BulletType.LEAD.getType()) ? ModItemsRegister.REVOLVER_LEAD_BULLET.get() : ModItemsRegister.REVOLVER_ALUMINUM_BULLET.get();
        }
        for (ItemStack stack: player.getInventory().items) {
            if (stack.is(item)) {
                return player.getInventory().findSlotMatchingItem(stack);
            }
        }
        return -1;
    }

    /**
     * Generates the initial CompoundTag for a gun item based on its GunType.
     *
     * @param gunType The type of the gun for which to generate tags.
     * @return The CompoundTag with initial gun properties.
     */
    public static CompoundTag generateGunTags(GunType gunType) {
        CompoundTag compoundTag = new CompoundTag();
        compoundTag.putInt(GunsAccess.BULLETS.getKey(), 0);
        compoundTag.putString(GunsAccess.BULLET_TYPE.getKey(), BulletType.LEAD.getType());
        return compoundTag;
    }

    /**
     * Simulates a shot with the specified gun item for the given player and gun type.
     *
     * @param gun The ItemStack representing the gun item being shot.
     * @param player The ServerPlayer shooting the gun.
     * @param gunType The type of the gun being shot.
     * @return The updated CompoundTag of the gun item after the shot.
     */
    public static CompoundTag shot(ItemStack gun, Level level , ServerPlayer player, GunType gunType) {
        CompoundTag tag = gun.getTag();

        if (tag.getInt(GunsAccess.BULLETS.getKey()) > 0) {

            BulletProjectile bullet = new BulletProjectile(level, player, gunType, bulletTypeToShot(tag));
            if (gunType != GunType.SHOTGUN) {
                if (gunType == GunType.RIFLE) {
                    bullet.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 5F, 1.0F);
                } else {
                    bullet.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 4F, 1.0F);
                }
                level.addFreshEntity(bullet);
            } else {
                bullet.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1F, 1.0F);
                level.addFreshEntity(bullet);
                BulletProjectile bullet1 = new BulletProjectile(level, player, gunType, bulletTypeToShot(tag));
                bullet1.shootFromRotation(player, player.getXRot()+ dispersion() , player.getYRot() + dispersion(),  dispersion(), 1F, 1.0F);
                level.addFreshEntity(bullet1);
                BulletProjectile bullet2 = new BulletProjectile(level, player, gunType, bulletTypeToShot(tag));
                bullet2.shootFromRotation(player, player.getXRot()+ dispersion(), player.getYRot() + dispersion(),  dispersion(), 1F, 1.0F);
                level.addFreshEntity(bullet2);
                BulletProjectile bullet3 = new BulletProjectile(level, player, gunType, bulletTypeToShot(tag));
                bullet3.shootFromRotation(player, player.getXRot()+ dispersion(), player.getYRot() + dispersion(),  dispersion(), 1F, 1.0F);
                level.addFreshEntity(bullet3);
                BulletProjectile bullet4 = new BulletProjectile(level, player, gunType, bulletTypeToShot(tag));
                bullet4.shootFromRotation(player, player.getXRot()+ dispersion(), player.getYRot() + dispersion(),  dispersion(), 1F, 1.0F);
                level.addFreshEntity(bullet4);
                BulletProjectile bullet5 = new BulletProjectile(level, player, gunType, bulletTypeToShot(tag));
                bullet5.shootFromRotation(player, player.getXRot()+ dispersion(), player.getYRot() + dispersion(),  dispersion(), 1F, 1.0F);
                level.addFreshEntity(bullet5);
                BulletProjectile bullet6 = new BulletProjectile(level, player, gunType, bulletTypeToShot(tag));
                bullet6.shootFromRotation(player, player.getXRot()+ dispersion(), player.getYRot() + dispersion(),  dispersion(), 1F, 1.0F);
                level.addFreshEntity(bullet6);
                BulletProjectile bullet7 = new BulletProjectile(level, player, gunType, bulletTypeToShot(tag));
                bullet7.shootFromRotation(player, player.getXRot()+ dispersion(), player.getYRot() + dispersion(),  dispersion(), 1F, 1.0F);
                level.addFreshEntity(bullet7);
                BulletProjectile bullet8 = new BulletProjectile(level, player, gunType, bulletTypeToShot(tag));
                bullet8.shootFromRotation(player, player.getXRot()+ dispersion(), player.getYRot() + dispersion(),  dispersion(), 1F, 1.0F);
                level.addFreshEntity(bullet8);

            }
            tag.putInt(GunsAccess.BULLETS.getKey(), tag.getInt(GunsAccess.BULLETS.getKey()) - 1);
        } else {
            player.sendSystemMessage(Component.translatable("VACIO"));
        }
        return tag;
    }

    private static BulletType bulletTypeToShot(CompoundTag compoundTag) {
        return compoundTag.getString(GunsAccess.BULLET_TYPE.getKey()).equals(BulletType.LEAD.getType()) ? BulletType.LEAD : BulletType.ALUMINUM;
    }

    /**
     * Randomly generates a spread value within a specified range for shotgun fire.
     *
     * This method creates a random dispersion value within the range [-15, 15].
     *
     * @return A random dispersion value between -15 and 15 (inclusive).
     */
    public static int dispersion() {
        return new Random().nextInt(30 + 1) - 15;
    }

    public static CompoundTag changeAmmo(Player player, ItemStack stack) {
        //todo, ver cuando se hace el cambio
        if (stack.getTag().getInt(GunsAccess.BULLETS.getKey()) == 0) {
            if (stack.getTag().getString(GunsAccess.BULLET_TYPE.getKey()).equals(BulletType.LEAD.getType())) {
                stack.getTag().putString(GunsAccess.BULLET_TYPE.getKey(), BulletType.ALUMINUM.getType());
            } else {
                stack.getTag().putString(GunsAccess.BULLET_TYPE.getKey(), BulletType.LEAD.getType());
            }
            player.sendSystemMessage(Component.translatable("Municion actual:"+ stack.getTag().getString(GunsAccess.BULLET_TYPE.getKey())));
        } else {
            player.sendSystemMessage(Component.translatable("No se puede cambiar, aun quedan cargas"));
        }
        return stack.getTag();
    }
}
