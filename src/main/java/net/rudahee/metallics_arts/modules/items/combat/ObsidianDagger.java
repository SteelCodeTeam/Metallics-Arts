package net.rudahee.metallics_arts.modules.items.combat;


import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.rudahee.metallics_arts.modules.items.combat.tiers.ObsidianCustomTier;

public class ObsidianDagger extends SwordItem {
    private static final int ATTACK_DAMAGE = 12;
    private static final float ATTACK_SPEED = 9.2F;
    private static final Tier OBSIDIAN_TIER = new ObsidianCustomTier(900,0,3,6,3);

    public ObsidianDagger(Item.Properties properties) {
        super(OBSIDIAN_TIER, ATTACK_DAMAGE, ATTACK_SPEED, properties);
    }
}
