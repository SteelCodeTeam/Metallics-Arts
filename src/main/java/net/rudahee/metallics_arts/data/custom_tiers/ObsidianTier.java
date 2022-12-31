package net.rudahee.metallics_arts.data.custom_tiers;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import org.jetbrains.annotations.Nullable;

public class ObsidianTier implements Tier {

    private int uses;
    private float speed;
    private float attackDamageBonus;
    private int level;
    private int enchantmentValue;

    public ObsidianTier(int uses, float speed, float attackDamageBonus, int level) {
        this.uses = uses;
        this.speed = speed;
        this.attackDamageBonus = attackDamageBonus;
        this.level = level;
        this.enchantmentValue = 1;
    }

    public ObsidianTier(int uses, float speed, float attackDamageBonus, int level, int enchantmentValue) {
        this.uses = uses;
        this.speed = speed;
        this.attackDamageBonus = attackDamageBonus;
        this.level = level;
        this.enchantmentValue = enchantmentValue;
    }

    @Override
    public int getUses() {
        return uses;
    }

    @Override
    public float getSpeed() {
        return speed;
    }

    @Override
    public float getAttackDamageBonus() {
        return attackDamageBonus;
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public int getEnchantmentValue() {
        return enchantmentValue;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.of(Blocks.OBSIDIAN);
    }

    @Override
    public @Nullable TagKey<Block> getTag() {
        return Tags.Blocks.OBSIDIAN;
    }

    public static TagKey<Block> getDefaultTag() {
        return Tags.Blocks.OBSIDIAN;
    }
}
