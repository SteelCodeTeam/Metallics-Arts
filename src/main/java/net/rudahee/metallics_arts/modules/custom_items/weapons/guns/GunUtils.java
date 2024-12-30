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
import org.checkerframework.checker.units.qual.C;

import java.util.Random;


/**
 * Utility class containing static methods related to gun functionality.
 *
 * @author SteelCode Team
 * @since 1.6.4
 */
public class GunUtils {

    /**
     * Reloads of guns, updating its properties in the provided compound tag.
     * To do this, check if there is ammunition in the inventory, and modify the texture to the reloading one.
     *
     * @param compoundTag The compound tag representing the firearm's properties.
     * @param player The player performing to reload.
     * @param gunType The type of firearm being reloaded.
     * @return The updated compound tag with the firearm's properties after to reload.
     */
    public static CompoundTag reload(CompoundTag compoundTag, Player player, GunType gunType) {
        int slot = hasBulletOfType(player, compoundTag.getString(GunsAccess.BULLET_TYPE.getKey()), gunType);
        if (slot != -1 && compoundTag.getInt(GunsAccess.BULLETS.getKey()) < gunType.getMaxAmount()) {
            compoundTag.putInt(GunsAccess.BULLETS.getKey(), compoundTag.getInt(GunsAccess.BULLETS.getKey()) + 1);
            compoundTag.putFloat("CustomModelData",
                    GunUtils.updateTexture(compoundTag.getInt(GunsAccess.BULLETS.getKey()), gunType));
            player.getInventory().removeItem(slot, 1);
        } else {
            compoundTag.putString(GunsAccess.STATE.getKey(), GunsAccess.READY.getKey());
            compoundTag.putFloat("CustomModelData", 0);
        }
        return compoundTag;
    }

    /**
     * Updates the texture value for a gun based on the number of bullets loaded and the gun type.
     *
     * @param bullets The number of bullets loaded into the firearm.
     * @param gunType The type of the firearm for which the texture is being updated.
     * @return The updated texture value based on the bullets and gun type.
     */
    public static float updateTexture(int bullets, GunType gunType) {
        if (gunType == GunType.SHOTGUN) {
            if (bullets == 0) {
                return 2F;
            } else if (bullets == 1) {
                return 3F;
            } else {
                return 4F;
            }
        } else if (gunType == GunType.RIFLE || gunType == GunType.RIFLE_SPYGLASS) {
            if (bullets == 0) {
                return 2F;
            } else if (bullets == 1) {
                return 3F;
            }
        } else if (gunType == GunType.REVOLVER) {
            if (bullets == 0) {
                return 2F;
            } else if (bullets == 1) {
                return 3F;
            } else if (bullets == 2) {
                return 4F;
            } else if (bullets == 3) {
                return 5F;
            } else if (bullets == 4) {
                return 6F;
            } else if (bullets == 5) {
                return 7F;
            } else {
                return 8F;
            }
        } else if (gunType == GunType.VINDICATOR) {
            if (bullets == 0) {
                return 2F;
            } else if (bullets == 1) {
                return 3F;
            } else if (bullets == 2) {
                return 4F;
            } else if (bullets == 3) {
                return 5F;
            } else if (bullets == 4) {
                return 6F;
            } else if (bullets == 5) {
                return 7F;
            } else if (bullets == 6) {
                return 8F;
            } else if (bullets == 7) {
                return 9F;
            }
            else {
                return 10F;
            }
        }
        return 0F;
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
        compoundTag.putString(GunsAccess.STATE.getKey(), GunsAccess.READY.getKey());
        compoundTag.putInt(GunsAccess.BULLETS.getKey(), 0);
        compoundTag.putString(GunsAccess.BULLET_TYPE.getKey(), BulletType.LEAD.getType());
        return compoundTag;
    }

    /**
     * Checks if it contains the tags related to the gun.
     *
     * @param compoundTag The CompoundTag to check for tags.
     * @return True if the CompoundTag contains the required firearm tags, false otherwise.
     */
    public static boolean hasTags(CompoundTag compoundTag) {
        return compoundTag.contains(GunsAccess.STATE.getKey()) && compoundTag.contains(GunsAccess.BULLETS.getKey()) && compoundTag.contains(GunsAccess.BULLET_TYPE.getKey());
    }

    /**
     * Simulates a shot with the specified gun item for the given player and gun type.
     *
     * @param gun The ItemStack representing the gun item being shot.
     * @param player The ServerPlayer shooting the gun.
     * @param gunType The type of the gun being shot.
     * @return The updated CompoundTag of the gun item after the shot.
     */
    public static CompoundTag shot(ItemStack gun, Level level , Player player, GunType gunType) {
        CompoundTag tag = gun.getTag();

        if (tag.getInt(GunsAccess.BULLETS.getKey()) > 0) {

            BulletProjectile bullet = new BulletProjectile(level, player, gunType, bulletTypeToShot(tag));
            if (gunType != GunType.SHOTGUN) {
                if (gunType == GunType.RIFLE) {
                    bullet.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 5F, 1.0F);
                } else if (gunType == GunType.RIFLE_SPYGLASS) {
                    bullet.shootFromRotation(player, player.getXRot(), player.getYRot(), 0F, 4.0F, 1.0F);
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
            player.sendSystemMessage(Component.literal("VACIO"));
        }
        return tag;
    }

    /**
     * Converts the bullet type stored in the given CompoundTag to the corresponding BulletType enum.
     *
     * @param compoundTag The CompoundTag containing the bullet type information.
     * @return The BulletType enum that corresponds to the stored bullet type.
     */
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
            player.sendSystemMessage(Component.translatable("Municion actual:" + stack.getTag().getString(GunsAccess.BULLET_TYPE.getKey())));
        } else {
            player.sendSystemMessage(Component.literal("No se puede cambiar, aun quedan cargas"));
        }
        return stack.getTag();
    }

}
