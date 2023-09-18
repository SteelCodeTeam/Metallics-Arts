package net.rudahee.metallics_arts.modules.custom_entities.iron_allomancer_entity.ettmetal_allomancer_entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class IronAllomancerEntity extends Monster {
    public IronAllomancerEntity(EntityType<? extends Monster> type, Level level) {
        super(type, level);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new LookAtPlayerGoal(this, Player.class,8.0F));
        this.goalSelector.addGoal(2, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1D, true));

    }

    public static AttributeSupplier.Builder getExampleAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.FOLLOW_RANGE, 50.0D)
                .add(Attributes.MOVEMENT_SPEED, (double)0.30F)
                .add(Attributes.ATTACK_DAMAGE, 10.0D)
                .add(Attributes.ARMOR, .0D)
                .add(Attributes.SPAWN_REINFORCEMENTS_CHANCE)
                .add(Attributes.MAX_HEALTH, 100)
                .add(Attributes.ATTACK_SPEED, 0.5F);

    }

}
