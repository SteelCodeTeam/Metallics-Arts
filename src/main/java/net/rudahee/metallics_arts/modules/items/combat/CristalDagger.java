package net.rudahee.metallics_arts.modules.items.combat;

import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.rudahee.metallics_arts.MetallicsArts;

public class CristalDagger extends SwordItem {
    private static final int ATTACK_DAMAGE = -1;
    private static final float ATTACK_SPEED = 2F;

    public CristalDagger(Properties properties) {
        super(Tiers.IRON, ATTACK_DAMAGE, ATTACK_SPEED, properties.durability(50).tab(MetallicsArts.MA_TAB));
    }
}

