package net.rudahee.metallics_arts.modules.custom_entities.custom_bosses.iron_inquisitor;

import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.world.BossEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PowerableMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class IronInquisitorEntity extends Monster implements PowerableMob  {

    private final ServerBossEvent bossEvent =
            new ServerBossEvent(this.getDisplayName(), BossEvent.BossBarColor.WHITE, BossEvent.BossBarOverlay.PROGRESS);

    private static final TargetingConditions TARGETING_CONDITIONS = TargetingConditions
            .forCombat().range(64.0D).selector((living -> living instanceof Player));

    public IronInquisitorEntity(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
        this.setHealth(this.getMaxHealth());
        this.xpReward = 200;
    }

    @Override
    public boolean isPowered() {
        return false;
    }


    @Override
    protected void registerGoals() {
        this.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.goalSelector.addGoal(1, new LookAtPlayerGoal(this, Player.class,8.0F));
        this.goalSelector.addGoal(2, new RandomLookAroundGoal(this));
        //this.goalSelector.addGoal(4, new PullAndPushGoal(this, -2,10));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 0.3D, true));
        this.goalSelector.addGoal(4, new FloatGoal(this));
    }


    public static AttributeSupplier.Builder getExampleAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.FOLLOW_RANGE, 64.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.30F)
                .add(Attributes.ATTACK_DAMAGE, 8.0D)
                .add(Attributes.ARMOR, 10D)
                .add(Attributes.MAX_HEALTH, 100)
                .add(Attributes.ATTACK_SPEED, 3.0F);
    }
}
