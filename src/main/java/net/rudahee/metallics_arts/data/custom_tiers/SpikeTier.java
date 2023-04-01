package net.rudahee.metallics_arts.data.custom_tiers;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import org.jetbrains.annotations.NotNull;


/**
 * Class that implements a new tier in the game. This class contains all the data needed to
 * implement spikes as a new tier. This class implements the tier interface.
 * <p>
 * All parameters in this class are predefined because all spikes are identical.
 *
 * @author SteelCode Team
 * @since 1.5.1
 *
 * @see Tier
 * @see Tiers
 */
public class SpikeTier implements Tier {

        private int uses;
        private float speed;
        private float attackDamageBonus;
        private int level;
        private int enchantmentValue;

    /**
     * Default constructor. All parameters are fixed for all spikes.
     */
    public void ObsidianTier() {
            this.uses = 1;
            this.speed = 1.2F;
            this.attackDamageBonus = 5;
            this.level = 10;
            this.enchantmentValue = 1;
        }

        /**
         * Getter of number of uses.
         *
         * @return int
         */
        @Override
        public int getUses() {
            return uses;
        }

        /**
         * Getter of speed, in float type.
         *
         * @return float
         */
        @Override
        public float getSpeed() {
            return speed;
        }

        /**
         * Getter of damage bonus for being this tier.
         *
         * @return float
         */
        @Override
        public float getAttackDamageBonus() {
            return attackDamageBonus;
        }

        /**
         * Getter of level for being this tier.
         *
         * @return int
         */
        @Override
        public int getLevel() {
            return level;
        }

        /**
         * Getter of enchantment value in enchanting table for being this tier.
         *
         * @return int
         */
        @Override
        public int getEnchantmentValue() {
            return enchantmentValue;
        }

        /**
         * Ingredient needed to repair item in this tier.
         *
         * @return Ingredient
         */
        @Override
        public @NotNull Ingredient getRepairIngredient() {
            return Ingredient.of(Blocks.OBSIDIAN);
        }


        /**
         * Getter of tag defined by this tier. It's used to compare different tiers.
         * But, this method return a default tag if it doesn't exist.
         *
         * @return TagKey
         */
        public static TagKey<Item> getDefaultTag() {
            return Tags.Items.TOOLS_SWORDS;
        }

}
