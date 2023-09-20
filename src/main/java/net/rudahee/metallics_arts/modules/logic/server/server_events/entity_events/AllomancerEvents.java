package net.rudahee.metallics_arts.modules.logic.server.server_events.entity_events;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.rudahee.metallics_arts.modules.logic.server.powers.allomancy.physical_metals.IronAndSteelHelpers;

public class AllomancerEvents {

    public static void OnEttmetalAllomancerHit(LivingHurtEvent event, LivingEntity source, LivingEntity target) {
        target.level.explode(target, target.getX(), target.getY(), target.getZ(), (float)30, Level.ExplosionInteraction.MOB);
    }


}
