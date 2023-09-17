package net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.cognitive_metals;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.CandleBlock;
import net.minecraft.world.level.block.CandleCakeBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.common.Tags;

/**
 * Helper class containing the methods and implementations for using feruchemical Brass.
 *
 * @author SteelCode Team
 * @since 1.5.1
 */
public class BrassFeruchemicHelper {

    /**
     * Implementation of the abstract method of the AbstractFechuchemicHelper class.
     * In this specific case, for the power of the Brass: Burns the player if they are in the desert by day, or in a hot biome.
     *
     * @param player to whom the effect will be applied.
     *
     */

    public static void tapPower(Player player) {
        if (player.getLevel().getBiome(player.getOnPos()).is(Biomes.DESERT) && player.getLevel().isDay()) {
            player.setSecondsOnFire(2);
        } else if (player.getLevel().getBiome(player.getOnPos()).is(Tags.Biomes.IS_HOT)) {
            player.setSecondsOnFire(2);
        }
    }

    /**
     * Implementation of the abstract method of the AbstractFechuchemicHelper class.
     * In this specific case, for the power of the Brass: Add frozen ticks to the player in case they are in the desert at night, or in a cold biome
     *
     * @param player to whom the effect will be applied.
     *
     */
    public static void storagePower(Player player) {
        player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 120, 1, false, false));

        if (player.getLevel().getBiome(player.getOnPos()).is(Tags.Biomes.IS_COLD) ||
                (player.getLevel().getBiome(player.getOnPos()).is(Biomes.DESERT) && player.getLevel().isNight())) {
            player.setTicksFrozen(player.getTicksFrozen() + 3);
        }
    }

    /**
     * This is a unique method in the helpers, which is responsible for adding a few seconds of fire to the given livingEntity
     *
     * @param livingEntity to whom the seconds of fire will be applied.
     * @param secondsFire seconds the entity will be on fire.
     */
    public static void addFireAspectToPlayer(LivingEntity livingEntity, int secondsFire) {
        livingEntity.setSecondsOnFire(secondsFire);
    }

    public static void compoundingBrass(Player player, BlockPos blockPos) {

        Level level = player.getLevel();
        BlockState blockstate = level.getBlockState(blockPos);
        if (!CampfireBlock.canLight(blockstate) && !CandleBlock.canLight(blockstate) && !CandleCakeBlock.canLight(blockstate)) {
            //player.getClickedFace()

            BlockPos blockpos1 = blockPos.relative(player.getDirection().getOpposite());
            //player.getHorizontalDirection()
            if (BaseFireBlock.canBePlacedAt(level, blockpos1, player.getDirection().getOpposite())) {
                level.playSound(player, blockpos1, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.4F + 0.8F);
                BlockState blockstate1 = BaseFireBlock.getState(level, blockpos1);
                level.setBlock(blockpos1, blockstate1, 11);
                level.gameEvent(player, GameEvent.BLOCK_PLACE, blockPos);

            }
        }
    }
}
