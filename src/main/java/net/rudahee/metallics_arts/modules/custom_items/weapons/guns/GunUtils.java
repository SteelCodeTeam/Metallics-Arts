package net.rudahee.metallics_arts.modules.custom_items.weapons.guns;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.rudahee.metallics_arts.data.enums.implementations.BulletType;
import net.rudahee.metallics_arts.data.enums.implementations.GunType;
import net.rudahee.metallics_arts.data.enums.implementations.GunsAccess;
import net.rudahee.metallics_arts.setup.registries.ModItemsRegister;


/**
 * Utility class containing static methods related to gun functionality.
 *
 * @author SteelCode Team
 * @since 1.6.4
 */
public class GunUtils {
    public static CompoundTag reload(ItemStack gun, Player player, GunType gunType) {
        CompoundTag compoundTag = gun.getTag();

        int slot = hasBulletOfType(player, compoundTag.getString(GunsAccess.BULLET_TYPE.getKey()), gunType);
        if (slot != -1) {
            player.sendSystemMessage(Component.translatable("RECARGANDO"));
            compoundTag.putInt(GunsAccess.BULLETS.getKey(), compoundTag.getInt(GunsAccess.BULLETS.getKey()) + 1);
            player.getInventory().removeItem(slot, 1);

            if (compoundTag.getInt(GunsAccess.BULLETS.getKey()) == gunType.getMaxAmount()) {
                compoundTag.putString(GunsAccess.STATE.getKey(), GunsAccess.READY.getKey());
                compoundTag.putFloat("CustomModelData", 0);
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
        } else if (gunType == GunType.RIFLE) {
            item = type.equals(BulletType.LEAD.getType()) ? ModItemsRegister.RIFLE_LEAD_BULLET.get() : ModItemsRegister.RIFLE_ALUMINUM_BULLET.get();
        } else {
            item = type.equals(BulletType.LEAD.getType()) ? ModItemsRegister.PISTOL_LEAD_BULLET.get() : ModItemsRegister.PISTOL_ALUMINUM_BULLET.get();
        }
        for (ItemStack stack: player.getInventory().items) {
            if (stack.is(item)){
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
        compoundTag.putInt(GunsAccess.BULLETS_MAX.getKey(), gunType.getMaxAmount());
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
    public static CompoundTag shot(ItemStack gun, ServerPlayer player, GunType gunType) {
        CompoundTag tag = gun.getTag();
        //EL GUNTYPE SE USARIA PARA GENERAR EL PROYECTIL CORRESPONDIENTE
        if (tag.getInt(GunsAccess.BULLETS.getKey()) > 0) {
            player.sendSystemMessage(Component.translatable("BANG"));
            tag.putInt(GunsAccess.BULLETS.getKey(), tag.getInt(GunsAccess.BULLETS.getKey()) - 1);
            player.playSound(SoundEvents.CROSSBOW_HIT);
        } else {
            player.sendSystemMessage(Component.translatable("VACIO"));
        }
        return tag;
    }
}
