package net.rudahee.metallics_arts.modules.custom_blocks.broken_cristal_block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

/**
 * This class is used to create a custom broken cristal block.
 * It also defines the behavior that occurs when you walk on it.
 *
 * @author SteelCode Team
 * @since 1.5.1
 *
 * @see Block
 */
public class BrokenCristalBlock extends Block {

    /**
     * Default constructor to a custom Block. It is initialized with the basic properties passed by parameter.
     * This method only call to super() with properties.
     *
     * @param properties properties of the block.
     *
     * @see Block
     */
    public BrokenCristalBlock(Properties properties) {
        super(properties);
    }

    /**
     * Method to define the behaviour when an entity step on the block.
     *
     * @param level on which the block is placed.
     * @param blockPos position of the block.
     * @param blockState state of the unique block in the world.
     * @param entity that is on top of the block.
     *
     * @see Block
     */
    @Override
    public void stepOn(@NotNull Level level, @NotNull BlockPos blockPos, @NotNull BlockState blockState, Entity entity) {
        if (!entity.isSteppingCarefully() && entity instanceof LivingEntity && !EnchantmentHelper.hasFrostWalker((LivingEntity)entity)) {
            entity.hurt(DamageSource.STALAGMITE, 1.0F);
        }
        super.stepOn(level, blockPos, blockState, entity);
    }
}
