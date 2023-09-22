package net.rudahee.metallics_arts.modules.custom_entities.iron_allomancer_entity;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.pathfinder.Path;
import net.rudahee.metallics_arts.modules.logic.server.powers.allomancy.physical_metals.IronAndSteelHelpers;
import net.rudahee.metallics_arts.utils.EntityUtils;

import java.util.EnumSet;

public class IronAllomancerGoal extends Goal {


    private final Monster entity;
    private long lastCanUseCheck;


    public IronAllomancerGoal(Monster entity) {
        this.entity = entity;
    }


    @Override
    public boolean canUse() {

        long i = entity.level.getGameTime();
        if (i - this.lastCanUseCheck < 20L) {
            return false;
        } else {
            this.lastCanUseCheck = i;
        }

        if(this.entity.getTarget() instanceof ServerPlayer && EntityUtils.distance((ServerPlayer) this.entity.getTarget(), this.entity)<10 ){
            IronAndSteelHelpers.move(-1,this.entity.getTarget(),this.entity.blockPosition());
            return true;
        }else
            return false;
    }


}
