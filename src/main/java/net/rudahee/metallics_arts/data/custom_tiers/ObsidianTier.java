package net.rudahee.metallics_arts.data.custom_tiers;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Class that implements a new tier in the game. This class contains all the data needed to
 * implement obsidian as a new tier. This class implements the tier interface.
 * <p>
 * This tier can be modified when instantiating it, since obsidian weapons are very variable.
 *
 * @author SteelCode Team
 * @since 1.5.1
 *
 * @see Tier
 * @see Tiers
 */
public class ObsidianTier implements Tier {

    private final int uses;
    private final float speed;
    private final float attackDamageBonus;
    private final int level;
    private final int enchantmentValue;

    /**
     * Default constructor. Define base uses, speed and attackDamage by default. You need to define tier level too.
     *
     * @param uses number of uses before breaking.
     * @param speed time that must elapse between two uses.
     * @param attackDamageBonus Bonus damage for being in this tier.
     * @param level of the tier. High level symbolize a better tier.
     */
    public ObsidianTier(int uses, float speed, float attackDamageBonus, int level) {
        this.uses = uses;
        this.speed = speed;
        this.attackDamageBonus = attackDamageBonus;
        this.level = level;
        this.enchantmentValue = 1;
    }

    /**
     * Constructor. Define base uses, speed and attackDamage and enchantment Value by default.
     * You need to define tier level too.
     *
     * @param uses number of uses before breaking.
     * @param speed time that must elapse between two uses.
     * @param attackDamageBonus Bonus damage for being in this tier.
     * @param level of the tier. High level symbolize a better tier.
     * @param enchantmentValue value of the enchantment in enchanting table.
     */
    public ObsidianTier(int uses, float speed, float attackDamageBonus, int level, int enchantmentValue) {
        this.uses = uses;
        this.speed = speed;
        this.attackDamageBonus = attackDamageBonus;
        this.level = level;
        this.enchantmentValue = enchantmentValue;
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
     *
     * @return TagKey
     */
    @Override
    public @Nullable TagKey<Block> getTag() {
        return Tags.Blocks.OBSIDIAN;
    }

    /**
     * Getter of tag defined by this tier. It's used to compare different tiers.
     * But, this method return a default tag if it doesn't exist.
     *
     * @return TagKey
     */
    public static TagKey<Block> getDefaultTag() {
        return Tags.Blocks.OBSIDIAN;
    }
}
