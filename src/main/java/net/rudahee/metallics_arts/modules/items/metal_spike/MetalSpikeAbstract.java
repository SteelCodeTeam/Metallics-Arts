package net.rudahee.metallics_arts.modules.items.metal_spike;

import net.minecraft.block.Blocks;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.item.SwordItem;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.rudahee.metallics_arts.modules.data_player.IDefaultInvestedPlayerData;
import net.rudahee.metallics_arts.modules.data_player.InvestedCapability;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;
import net.rudahee.metallics_arts.setup.network.ModNetwork;
import net.rudahee.metallics_arts.setup.registries.ModItems;

import javax.annotation.Nullable;
import java.util.List;

public abstract class MetalSpikeAbstract extends SwordItem {

    protected CompoundNBT nbt;

    private static final int ATTACK_DAMAGE = 1;
    private static final float ATTACK_SPEED = -3f;

    public MetalSpikeAbstract(Properties properties) {
        super(tier, ATTACK_DAMAGE, ATTACK_SPEED, properties);
        nbt = new CompoundNBT();
    }


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

    public INBT getAllNbt() {
        return nbt;
    }

    public boolean getFeruchemicNbt() {
        return this.nbt.getBoolean("feruchemic");
    }
    public void setFeruchemicNbt(boolean value) {
        this.nbt.putBoolean("feruchemic", value);
    }
    public boolean getAllomanticNbt() {
        return this.nbt.getBoolean("allomantic");
    }
    public void setAllomanticNbt(boolean value) {
        this.nbt.putBoolean("allomantic", value);
    }



    public boolean hasPlayerBothPowers(MetalsNBTData metal, IDefaultInvestedPlayerData cap) {
        if (cap.hasAllomanticPower(metal) && cap.hasFeruchemicPower(metal)) {
            return true;
        }
        return false;
    }

    public boolean hasPlayerAllomanticPower(MetalsNBTData metal, IDefaultInvestedPlayerData cap) {
        return cap.hasAllomanticPower(metal);
    }
    public boolean hasPlayerFeruchemicPower(MetalsNBTData metal, IDefaultInvestedPlayerData cap) {
        return cap.hasFeruchemicPower(metal);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> toolTips, ITooltipFlag flag) {

        if (stack.hasTag()){
            if (stack.getTag().getBoolean("feruchemic")){
                toolTips.add(new StringTextComponent("Power: Feruchemic"));
            }
            if (stack.getTag().getBoolean("allomantic")){
                toolTips.add(new StringTextComponent("Power: Allomantic"));
            }
        }
        super.appendHoverText(stack, world, toolTips, flag);
    }
}
