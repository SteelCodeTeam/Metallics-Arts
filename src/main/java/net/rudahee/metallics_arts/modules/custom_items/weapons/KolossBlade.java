package net.rudahee.metallics_arts.modules.custom_items.weapons;

import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.rudahee.metallics_arts.MetallicsArts;

public class KolossBlade extends SwordItem {

    private static final int ATTACK_DAMAGE = 8;
    private static final float ATTACK_SPEED = -2.6F;

    public KolossBlade(Properties properties) {
        super(Tiers.STONE, ATTACK_DAMAGE, ATTACK_SPEED, properties.durability(200).tab(MetallicsArts.MA_TAB));
    }


}
