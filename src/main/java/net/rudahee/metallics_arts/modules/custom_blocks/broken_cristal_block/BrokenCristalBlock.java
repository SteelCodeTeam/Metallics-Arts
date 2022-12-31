package net.rudahee.metallics_arts.modules.custom_blocks.broken_cristal_block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class BrokenCristalBlock extends Block {
    public BrokenCristalBlock(Properties p_49795_) {
        super(p_49795_);
    }
    public void stepOn(Level world, BlockPos blockPos, BlockState blockState, Entity entity) {
        if (!entity.isSteppingCarefully() && entity instanceof LivingEntity && !EnchantmentHelper.hasFrostWalker((LivingEntity)entity)) {
            entity.hurt(DamageSource.STALAGMITE, 1.0F);
        }
        super.stepOn(world, blockPos, blockState, entity);
    }
}
