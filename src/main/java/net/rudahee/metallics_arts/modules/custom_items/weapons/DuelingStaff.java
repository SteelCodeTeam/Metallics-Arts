package net.rudahee.metallics_arts.modules.custom_items.weapons;


import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.rudahee.metallics_arts.MetallicsArts;


public class DuelingStaff extends SwordItem {

    private static final int ATTACK_DAMAGE = 4;
    private static final float ATTACK_SPEED = -2.8F;

    public DuelingStaff (Properties properties) {
        super(Tiers.GOLD, ATTACK_DAMAGE, ATTACK_SPEED, properties.durability(400).tab(MetallicsArts.MA_TAB));
    }
}
