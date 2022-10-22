package net.rudahee.metallics_arts.modules.items.combat;

import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;

public class CristalDagger extends SwordItem {
    private static final int ATTACK_DAMAGE = 12;
    private static final float ATTACK_SPEED = 9.2F;



    public CristalDagger(Properties properties) {
        super(Tiers.IRON, ATTACK_DAMAGE, ATTACK_SPEED, properties);
    }
}

