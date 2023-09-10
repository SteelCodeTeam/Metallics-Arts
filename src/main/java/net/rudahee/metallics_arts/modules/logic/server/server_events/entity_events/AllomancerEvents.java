package net.rudahee.metallics_arts.modules.logic.server.server_events.entity_events;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class AlomancerEvents {

    public static void OnEttmetalAllomancerHit(LivingHurtEvent event, LivingEntity source, LivingEntity target) {


        target.level.explode(target, target.getX(), target.getY(), target.getZ(), (float)10, Level.ExplosionInteraction.MOB);


    }

}
