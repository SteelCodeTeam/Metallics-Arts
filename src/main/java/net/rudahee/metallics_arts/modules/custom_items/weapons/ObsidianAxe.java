package net.rudahee.metallics_arts.modules.custom_items.weapons;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.data.custom_tiers.ObsidianTier;

/**
 * Class to create a custom sword item. This item it's a new custom "Obsidian tier" item.
 *
 * @author SteelCode Team
 * @since 1.5.1
 *
 * @see Item
 * @see SwordItem
 * @see ObsidianTier
 */
public class ObsidianAxe extends SwordItem {

    private static final Tier OBSIDIAN_TIER = new ObsidianTier(2500,0.8F,19,6,3);

    /**
     * Default constructor, we define the properties of to set a durability and creative tab. So, we change the damage and speed.
     *
     * @param properties of the item.
     */
    public ObsidianAxe (Item.Properties properties) {
        super(OBSIDIAN_TIER, -1, -3.2F, properties.durability(2500).tab(MetallicsArts.MA_TAB));
    }

    /**
     * We define the enchantments available from book to item. We don't allow Mending enchantment.
     *
     * @param stackWeapon is the specific item of the weapon.
     * @param stackBook is the specific item of the book.
     *
     * @return boolean
     */
    @Override
    public boolean isBookEnchantable(ItemStack stackWeapon, ItemStack stackBook) {
        if (EnchantmentHelper.getEnchantments(stackBook).containsKey(Enchantments.MENDING)) {
            return false;
        }
        return super.isBookEnchantable(stackWeapon, stackBook);
    }

    /**
     * We define the enchantments available from enchanting table to item. We don't allow Mending enchantment.
     *
     * @param stack is the specific item of the weapon.
     * @param enchantment is the specific enchantment that can be available in enchanting table.
     *
     * @return boolean
     */
    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        if (enchantment.equals(Enchantments.MENDING)) {
            return false;
        }
        return super.canApplyAtEnchantingTable(stack, enchantment);
    }

    /**
     * We define how much uses can be repaired by experience. We define 0 because are not reparable with Mending.
     *
     * @param stack is the specific item of the weapon.
     *
     * @return float
     */
    @Override
    public float getXpRepairRatio(ItemStack stack) {
        return 0;
    }
}
