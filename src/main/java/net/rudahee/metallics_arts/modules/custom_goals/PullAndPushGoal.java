package net.rudahee.metallics_arts.modules.custom_goals;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.rudahee.metallics_arts.modules.logic.server.powers.allomancy.physical_metals.IronAndSteelHelpers;
import net.rudahee.metallics_arts.utils.EntityUtils;

public class PullAndPushGoal extends Goal {


    private final Animal entity;
    private final double power;
    private final int blocks;
    private long lastCanUseCheck;


    public PullAndPushGoal(Animal entity, double power, int blocks) {
        this.entity = entity;
        this.power = power;
        this.blocks = blocks;
    }


    @Override
    public boolean canUse() {

        double random;
        random = Math.random();
        random = 20 + (random*10);

        long i = entity.level.getGameTime();
        if (i - this.lastCanUseCheck < (int)random) {
            return false;
        } else {
            this.lastCanUseCheck = i;
        }



        if(this.entity.getTarget() instanceof ServerPlayer && EntityUtils.distance((ServerPlayer) this.entity.getTarget(), this.entity)<blocks ) {
            IronAndSteelHelpers.move(power,this.entity.getTarget(),this.entity.blockPosition());
            return true;
        }else
            return false;
    }


}
