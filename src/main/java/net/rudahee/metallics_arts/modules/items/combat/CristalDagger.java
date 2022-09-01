package net.rudahee.metallics_arts.modules.items.combat;

import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;

public class CristalDagger extends SwordItem {
    private static final int ATTACK_DAMAGE = 12;
    private static final float ATTACK_SPEED = 9.2F;

    private static final Tier tier = new Tier() {
        @Override
        public int getUses() {
            return 3;
        }

        @Override
        public float getSpeed() {
            return 0;
        }

        @Override
        public float getAttackDamageBonus() {
            return ATTACK_DAMAGE;
        }

        @Override
        public int getLevel() {
            return 0;
        }

        @Override
        public int getEnchantmentValue() {
            return 1;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return Ingredient.of(Blocks.OBSIDIAN);
        }
    };

    public CristalDagger(Properties properties) {
        super(tier, ATTACK_DAMAGE, ATTACK_SPEED, properties);
    }
}

