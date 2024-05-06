package net.rudahee.metallics_arts.modules.custom_entities.iron_ferrin_entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.rudahee.metallics_arts.data.enums.implementations.GunType;
import net.rudahee.metallics_arts.modules.custom_entities.iron_ferrin_entity.IronFerrinEntity;
import net.rudahee.metallics_arts.modules.custom_goals.IronFerrinGoal;
import net.rudahee.metallics_arts.modules.custom_goals.PullAndPushGoal;
import net.rudahee.metallics_arts.modules.custom_goals.RegenerationGoal;
import net.rudahee.metallics_arts.modules.custom_projectiles.CoinProjectile;
import net.rudahee.metallics_arts.setup.registries.ModItemsRegister;
import org.jetbrains.annotations.Nullable;


public class IronFerrinEntity extends Animal implements RangedAttackMob {
    public IronFerrinEntity(EntityType<? extends Animal> type, Level level) {
        super(type, level);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new LookAtPlayerGoal(this, Player.class,8.0F));
        this.goalSelector.addGoal(2, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.goalSelector.addGoal(4, new IronFerrinGoal(this,15,50, 7));
        this.goalSelector.addGoal(4, new FloatGoal(this));
        this.goalSelector.addGoal(1, new RangedBowAttackGoal<IronFerrinEntity>(this, 1.0D, 20, 15.0F));



    }

    protected void populateDefaultEquipmentSlots(RandomSource source,  DifficultyInstance difficulty) {
        super.populateDefaultEquipmentSlots(source, difficulty);
        this.setItemSlot(EquipmentSlot.OFFHAND, new ItemStack(Items.ARROW));
        this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.BOW));
    }
    public static AttributeSupplier.Builder getExampleAttributes() {
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


        ItemStack itemstack = new ItemStack(Items.ARROW);

        AbstractArrow arrow = ProjectileUtil.getMobArrow(this, itemstack,p_32142_);

        double d0 = entity.getX() - this.getX();
        double d1 = entity.getY(0.3333333333333333D) - coin.getY();
        double d2 = entity.getZ() - this.getZ();
        double d3 = Math.sqrt(d0 * d0 + d2 * d2);

        arrow.shoot(d0, d1 + d3 * (double)0.2F, d2, 1.6F, (float)(14 - this.level.getDifficulty().getId() * 4));
        this.playSound(SoundEvents.SKELETON_SHOOT, 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
        this.level.addFreshEntity(arrow);

    }

    @javax.annotation.Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_32146_, DifficultyInstance p_32147_, MobSpawnType p_32148_, @javax.annotation.Nullable SpawnGroupData p_32149_, @javax.annotation.Nullable CompoundTag p_32150_) {
        p_32149_ = super.finalizeSpawn(p_32146_, p_32147_, p_32148_, p_32149_, p_32150_);
        RandomSource randomsource = p_32146_.getRandom();


        this.populateDefaultEquipmentSlots(randomsource, p_32147_);

        return p_32149_;
    }

    @org.jetbrains.annotations.Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_) {
        return null;
    }


    public static boolean canSpawn(EntityType<IronFerrinEntity> entityType, LevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random) {

        return checkMobSpawnRules(entityType, level, spawnType, pos, random);

    }
}
