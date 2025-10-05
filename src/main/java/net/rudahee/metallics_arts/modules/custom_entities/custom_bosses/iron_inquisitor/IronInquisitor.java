package net.rudahee.metallics_arts.modules.custom_entities.custom_bosses.iron_inquisitor;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.BossEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WrappedGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.rudahee.metallics_arts.modules.custom_goals.PullBossGoal;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.object.PlayState;

import javax.annotation.Nullable;
import java.util.Optional;

public class IronInquisitor extends PathfinderMob implements GeoEntity, Enemy, PowerableMob {

    private final AnimatableInstanceCache instanceCache = new SingletonAnimatableInstanceCache(this);
    private boolean powered = false;
    private int phase = 0;
    private boolean alreadyGoalChanged = false;
    private int tickCounterPowered = 0;

    private final ServerBossEvent bossEvent =
            new ServerBossEvent(this.getDisplayName(), BossEvent.BossBarColor.RED, BossEvent.BossBarOverlay.PROGRESS);

    public IronInquisitor(EntityType<? extends PathfinderMob> entityType, Level level) {
        super(entityType, level);
        this.setHealth(this.getMaxHealth());
        this.xpReward = 1000;
    }

    @Override
    protected void registerGoals() {
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true, false));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 0.4D, true));
        this.goalSelector.addGoal(2, new PullBossGoal(this, Player.class, phase));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class,8.0F));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
    }

    public static AttributeSupplier.Builder getIronInquisitorAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.FOLLOW_RANGE, 64.0D)
                .add(Attributes.MOVEMENT_SPEED,1D)
                .add(Attributes.ATTACK_DAMAGE, 25.0D)
                .add(Attributes.ARMOR, 4D)
                .add(Attributes.MAX_HEALTH, 130)
                .add(Attributes.ATTACK_SPEED, 4.5F);
    }

    public static  boolean canSpawn(EntityType<IronInquisitor> entityType, LevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
        return false;
    }

    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        if (this.hasCustomName()) {
            this.bossEvent.setName(this.getDisplayName());
        }

    }

    public void setCustomName(@Nullable Component component) {
        super.setCustomName(component);
        this.bossEvent.setName(this.getDisplayName());
    }


    @Override
    public void tick() {
        super.tick();


        if (powered && phase == 0 && !alreadyGoalChanged) {
            phase = 1;
            alreadyGoalChanged = true;

            Optional<WrappedGoal> goal = this.goalSelector.getAvailableGoals().stream().filter(wrapped -> wrapped.getGoal() instanceof PullBossGoal).findFirst();

            goal.ifPresent(this.goalSelector::removeGoal);

            this.goalSelector.addGoal(2, new PullBossGoal(this, Player.class, phase));
        }

        if (this.getHealth() <= this.getMaxHealth() / 2) {
            if (tickCounterPowered++ < 100) {
                powered = true;

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

    public void startSeenByPlayer(ServerPlayer player) {
        super.startSeenByPlayer(player);
        this.bossEvent.addPlayer(player);
    }

    public void stopSeenByPlayer(ServerPlayer player) {
        super.stopSeenByPlayer(player);
        this.bossEvent.removePlayer(player);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<GeoAnimatable>(this, "controller", 0, this::predicate));

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
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return instanceCache;
    }

    @Override
    public boolean isPowered() {
        return powered;
    }
}
