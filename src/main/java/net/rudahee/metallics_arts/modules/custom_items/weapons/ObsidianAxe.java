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

public class ObsidianAxe extends SwordItem {
    private static final Tier OBSIDIAN_TIER = new ObsidianTier(2500,0.8F,19,6,3);

    public ObsidianAxe (Item.Properties properties) {
        super(OBSIDIAN_TIER, -1, -3.2F, properties.durability(2500).tab(MetallicsArts.MA_TAB));
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        if (EnchantmentHelper.getEnchantments(book).containsKey(Enchantments.MENDING)) {
            return false;
        }
        return super.isBookEnchantable(stack, book);
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        if (enchantment.equals(Enchantments.MENDING)) {
            return false;
        }
        return super.canApplyAtEnchantingTable(stack, enchantment);
    }

    @Override
    public float getXpRepairRatio(ItemStack stack) {
        return 0;
    }
}
