package net.rudahee.metallics_arts.modules.custom_entities.custom_bosses.steel_inquisitor;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.BossEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RangedAttackGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.rudahee.metallics_arts.data.enums.implementations.GunType;
import net.rudahee.metallics_arts.modules.custom_goals.JumpGoal;
import net.rudahee.metallics_arts.modules.custom_goals.PushBossGoal;
import net.rudahee.metallics_arts.modules.custom_projectiles.AnvilProjectile;
import net.rudahee.metallics_arts.modules.custom_projectiles.CoinProjectile;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.object.PlayState;

public class SteelInquisitor extends PathfinderMob implements GeoEntity, Enemy, PowerableMob, RangedAttackMob {

    private final AnimatableInstanceCache instanceCache = new SingletonAnimatableInstanceCache(this);
    private boolean powered = false;
    private int phase = 0;
    private int tickCounterPowered = 0;

    private final ServerBossEvent bossEvent =
            new ServerBossEvent(this.getDisplayName(), BossEvent.BossBarColor.BLUE, BossEvent.BossBarOverlay.PROGRESS);


    public SteelInquisitor(EntityType<? extends PathfinderMob> entityType, Level level) {
        super(entityType, level);
        this.setHealth(this.getMaxHealth());
        this.xpReward = 1000;
    }

    @Override
    protected void registerGoals() {
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true, false));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Player.class, 12.0F, 0.7D, 1.0D));
        this.goalSelector.addGoal(2, new RangedAttackGoal(this, 0.5D, 10, 70, 42.0F));
        this.goalSelector.addGoal(3, new PushBossGoal(this, Player.class));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class,8.0F));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(5, new JumpGoal(this, 9, 60));

    }




    public static AttributeSupplier.Builder getSteelInquisitorAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.FOLLOW_RANGE, 64.0D)
                .add(Attributes.MOVEMENT_SPEED,1D)
                .add(Attributes.ATTACK_DAMAGE, 15.0D)
                .add(Attributes.ARMOR, 4D)
                .add(Attributes.MAX_HEALTH, 130)
                .add(Attributes.ATTACK_SPEED, 0F);
    }

    @Override
    public boolean hurt(@NotNull DamageSource damageSource, float v) {
        if (this.isPowered()) {
            return false;
        } else {
            return super.hurt(damageSource, v);
        }
    }

    @Override
    public boolean doHurtTarget(Entity entity) {
        if (this.isPowered()) {
            return false;
        }
        return super.doHurtTarget(entity);
    }

    @Override
    public void tick() {
        super.tick();


        if (this.getHealth() <= this.getMaxHealth() / 2) {
            if (tickCounterPowered++ < 100) {
                powered = true;
                phase = 1;


                if (level instanceof ServerLevel serverLevel) {
                    serverLevel.sendParticles(ParticleTypes.SMOKE, this.position().x() + 0.5d, this.position().y() + 0.5d, this.position().z() + 0.5d, 0, 0d, 0.5d, 0.5d, 0d);
                    serverLevel.sendParticles(ParticleTypes.ENTITY_EFFECT, this.position().x() + 0.5d, this.position().y() + 0.5d, this.position().z() + 0.5d, 0, 0d, 0.5d, 0.5d, 0d);
                    serverLevel.sendParticles(ParticleTypes.LAVA, this.position().x() + 0.5d, this.position().y() + 0.5d, this.position().z() + 0.5d, 0, 0d, 0.5d, 0.5d, 0d);
                }
            } else {
                powered = false;
            }
        }

        this.bossEvent.setProgress(this.getHealth() / this.getMaxHealth());
    }

    public void startSeenByPlayer(ServerPlayer player) {
        super.startSeenByPlayer(player);
        this.bossEvent.addPlayer(player);
    }

    public void stopSeenByPlayer(ServerPlayer player) {
        super.stopSeenByPlayer(player);
        this.bossEvent.removePlayer(player);
    }


    @Override
    public void performRangedAttack(LivingEntity livingEntity, float f) {
        if (phase == 0) {
            CoinProjectile bronzeCoin = new CoinProjectile(level, this, GunType.BRONZE_COIN);
            double d0 = livingEntity.getEyeY() - (double)1.1F;
            double d1 = livingEntity.getX() - this.getX();
            double d2 = d0 - bronzeCoin.getY();
            double d3 = livingEntity.getZ() - this.getZ();
            double d4 = Math.sqrt(d1 * d1 + d3 * d3) * (double)0.2F;
            bronzeCoin.shoot(d1, d2 + d4, d3, 1.6F, 12.0F);
            this.level.addFreshEntity(bronzeCoin);
        } else if (phase == 1) {
            AnvilProjectile anvilProjectile = new AnvilProjectile(level, this);
            double d0 = livingEntity.getEyeY() - (double)1.1F;
            double d1 = livingEntity.getX() - this.getX();
            double d2 = d0 - anvilProjectile.getY();
            double d3 = livingEntity.getZ() - this.getZ();
            double d4 = Math.sqrt(d1 * d1 + d3 * d3) * (double)0.2F;
            anvilProjectile.shoot(d1, d2 + d4, d3, 1.6F, 12.0F);
            this.level.addFreshEntity(anvilProjectile);
        }
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<GeoAnimatable>(this, "controller", 0, this::predicate));

    }


    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return instanceCache;
    }


    private <T extends GeoAnimatable> PlayState predicate(AnimationState<T> animationState) {

        if (this.isPowered()) {
            animationState.getController().setAnimationSpeed(10);
        } else {
            animationState.getController().setAnimationSpeed(1);
        }

        if (animationState.isMoving()) {
            animationState.getController().setAnimation(RawAnimation.begin().then("walk", Animation.LoopType.LOOP));
            return PlayState.CONTINUE;
        }

        animationState.getController().setAnimation(RawAnimation.begin().then("idle", Animation.LoopType.LOOP));
        return PlayState.CONTINUE;
    }

    @Override
    public boolean isPowered() {
        return powered;
    }

}
