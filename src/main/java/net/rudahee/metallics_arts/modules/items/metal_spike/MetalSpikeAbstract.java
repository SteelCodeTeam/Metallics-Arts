package net.rudahee.metallics_arts.modules.items.metal_spike;

import net.minecraft.block.Blocks;
import net.minecraft.item.IItemTier;
import net.minecraft.item.SwordItem;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;

public abstract class MetalSpikeAbstract extends SwordItem {

    private CompoundNBT nbt;

    private static final int ATTACK_DAMAGE = 1;
    private static final float ATTACK_SPEED = -3f;

    private static final IItemTier tier = new IItemTier() {
        @Override
        public int getUses() {
            return 1;
        }

        @Override
        public float getSpeed() {
            return 0;
        }

        @Override
        public float getAttackDamageBonus() {
            return 0;
        }

        @Override
        public int getLevel() {
            return 0;
        }

        @Override
        public int getEnchantmentValue() {
            return 0;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return Ingredient.of(Blocks.OBSIDIAN);
        }
    };

    public MetalSpikeAbstract(Properties properties) {
        super(tier,ATTACK_DAMAGE,ATTACK_SPEED,properties);
    }

    public CompoundNBT getNbt() {
        return nbt;
    }

    public void setNbt(CompoundNBT nbt) {
        this.nbt = nbt;
    }

    public void setFeruchemy(boolean bool){
        this.nbt.putBoolean("feruchemy", bool);
    }

    public void setAllomancy(boolean bool){
        this.nbt.putBoolean("allomancy", bool);
    }

}
