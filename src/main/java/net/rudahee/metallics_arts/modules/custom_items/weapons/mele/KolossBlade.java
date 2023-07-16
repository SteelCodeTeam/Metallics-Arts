package net.rudahee.metallics_arts.modules.custom_items.weapons.mele;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.rudahee.metallics_arts.MetallicsArts;

/**
 * Class to create a custom sword item. This item it's a new stone tier item.
 *
 * @author SteelCode Team
 * @since 1.5.1
 *
 * @see Item
 * @see SwordItem
 * @see Tiers
 */
public class KolossBlade extends SwordItem {

    private static final int ATTACK_DAMAGE = 8;
    private static final float ATTACK_SPEED = -2.6F;

    /**
     * Default constructor, we define the properties of to set a durability and creative tab.
     *
     * @param properties of the item.
     */
    public KolossBlade(Properties properties) {
        super(Tiers.STONE, ATTACK_DAMAGE, ATTACK_SPEED, properties.durability(200).tab(MetallicsArts.MA_TAB));
    }

    @Override
    public boolean canAttackBlock(BlockState p_43291_, Level p_43292_, BlockPos p_43293_, Player p_43294_) {
        return super.canAttackBlock(p_43291_, p_43292_, p_43293_, p_43294_);
    }
}
