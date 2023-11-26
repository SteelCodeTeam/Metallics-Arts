package net.rudahee.metallics_arts.modules.custom_goals;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Position;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.JumpGoal;
import net.minecraft.world.entity.animal.Rabbit;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.phys.Vec3;
import net.rudahee.metallics_arts.modules.logic.server.powers.allomancy.physical_metals.IronAndSteelHelpers;
import net.rudahee.metallics_arts.setup.network.ModNetwork;
import net.rudahee.metallics_arts.setup.network.packets.PullAndPushBlockPacket;

import java.util.EnumSet;

public class IronFerrinGoal extends Goal {

    private final Mob entity;
    private final int power;
    private final int duration;
    private final int jump;

    public IronFerrinGoal(Mob entity, int power, int duration, int jump) {
        this.entity = entity;
        this.power = power;
        this.duration = duration;
        this.jump = jump;
    }


    @Override
    public boolean canUse() {

        if (entity.level.canSeeSky(entity.blockPosition()) && this.entity.getTarget() instanceof ServerPlayer) {

            entity.addEffect(new MobEffectInstance(MobEffects.JUMP, duration, jump, true, true));
            entity.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, duration, power, true, true));
            entity.getJumpControl().jump();

        }

        return true;


    }
}
