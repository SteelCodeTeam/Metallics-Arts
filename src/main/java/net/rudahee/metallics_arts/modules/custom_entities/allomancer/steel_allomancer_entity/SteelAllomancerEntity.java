package net.rudahee.metallics_arts.modules.custom_entities.allomancer.steel_allomancer_entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.rudahee.metallics_arts.data.enums.implementations.GunType;
import net.rudahee.metallics_arts.modules.custom_goals.PullAndPushGoal;
import net.rudahee.metallics_arts.modules.custom_projectiles.CoinProjectile;
import net.rudahee.metallics_arts.setup.registries.ModItemsRegister;

import javax.annotation.Nullable;


public class SteelAllomancerEntity extends Monster implements RangedAttackMob {
    public SteelAllomancerEntity(EntityType<? extends Monster> type, Level level) {
        super(type, level);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new LookAtPlayerGoal(this, Player.class,8.0F));
        this.goalSelector.addGoal(2, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.goalSelector.addGoal(4, new PullAndPushGoal(this, 3,4) );
        this.goalSelector.addGoal(1, new RangedBowAttackGoal<SteelAllomancerEntity>(this, 1.0D, 20, 15.0F));
        this.goalSelector.addGoal(4, new FloatGoal(this));
    }

    protected void populateDefaultEquipmentSlots(RandomSource source,  DifficultyInstance difficulty) {
        super.populateDefaultEquipmentSlots(source, difficulty);
        this.setItemSlot(EquipmentSlot.OFFHAND, new ItemStack(ModItemsRegister.BRONZE_COIN.get()));
        this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.BOW));
    }
    public static AttributeSupplier.Builder getSteelAllomancerAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.FOLLOW_RANGE, 50.0D)
                .add(Attributes.MOVEMENT_SPEED, (double)0.30F)
                .add(Attributes.ATTACK_DAMAGE, 5.0D)
                .add(Attributes.ARMOR, .0D)
                .add(Attributes.SPAWN_REINFORCEMENTS_CHANCE)
                .add(Attributes.MAX_HEALTH, 50)
                .add(Attributes.ATTACK_SPEED, 0.5F);

    }


    @Override
    public void performRangedAttack(LivingEntity entity, float p_32142_) {


        CoinProjectile coin = new CoinProjectile(level, this, GunType.COPPER_COIN);

        ItemStack itemstack = new ItemStack(ModItemsRegister.COPPER_COIN.get());

        double d0 = entity.getX() - this.getX();
        double d1 = entity.getY(0.3333333333333333D) - coin.getY();
        double d2 = entity.getZ() - this.getZ();
        double d3 = Math.sqrt(d0 * d0 + d2 * d2);

        coin.shoot(d0, d1 + d3 * (double)0.2F, d2, 1.6F, (float)(14 - this.level.getDifficulty().getId() * 4));
        this.playSound(SoundEvents.SKELETON_SHOOT, 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
        coin.setItem(itemstack);
        this.level.addFreshEntity(coin);

    }

    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor levelAccessor, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData groupData, @Nullable CompoundTag tag) {
        groupData = super.finalizeSpawn(levelAccessor, difficulty, spawnType, groupData, tag);
        RandomSource randomsource = levelAccessor.getRandom();

        this.populateDefaultEquipmentSlots(randomsource, difficulty);

        return groupData;
    }

    public static  boolean canSpawn(EntityType<SteelAllomancerEntity> entityType, LevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
        return checkMobSpawnRules(entityType, level, spawnType, pos, random);
    }
}
