package net.rudahee.metallics_arts.modules.items.combat;


import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.rudahee.metallics_arts.modules.items.combat.tiers.ObsidianCustomTier;

public class ObsidianAxe extends SwordItem {
    private static final Tier OBSIDIAN_TIER = new ObsidianCustomTier(1200,0,3,6,3);

    public ObsidianAxe (Item.Properties properties) {
        super(OBSIDIAN_TIER, 22, -3.4F, properties);
    }
}
