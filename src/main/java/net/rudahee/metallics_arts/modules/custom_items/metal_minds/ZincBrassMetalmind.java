package net.rudahee.metallics_arts.modules.custom_items.metal_minds;


import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.enums.implementations.MetalmindType;
import net.rudahee.metallics_arts.data.player.data.IInvestedPlayerData;
import net.rudahee.metallics_arts.modules.custom_items.metal_minds.abstracts.MetalmindAbstract;
import net.rudahee.metallics_arts.modules.effects.ModEffects;

/**
 * Class that specifies the zinc and brass band, we pass to the abstract class (which has the behavior) the metals that compose it,
 * in the order: metal and its alloy, along with their corresponding suppliers.
 *
 * @author SteelCode Team
 * @since 1.6.8
 *
 */
public class ZincBrassMetalmind extends MetalmindAbstract {

    /**
     * Default constructor, it is important to send the metals by parameter in the correct order, metal and its alloy.
     *
     * @param properties of the item.
     */
    public ZincBrassMetalmind(Item.Properties properties, MetalmindType type) {
        super(properties, MetalTagEnum.ZINC, MetalTagEnum.BRASS, type);
    }

    /**
     * Redefine of the method of the MetalmindAbstract class.
     * In this specific case, metalmind only charges when player is burning.
     *
     * @param compoundTag The compound tag associated with the curio item.
     * @param player The player performing the charge.
     * @param playerCapability The invested player data capability for the player.
     * @param metalReserve The current reserve of the metal to be charged.
     * @param nicConsume Specifies whether nicrosil consumption is enabled.
     * @param metal The metal being charged.
     *
     * @return The updated compound tag with the first metal charge applied.
     *
     */
    @Override
    public CompoundTag calculateSecondMetalCharge(CompoundTag compoundTag, Player player, IInvestedPlayerData playerCapability, int metalReserve, boolean nicConsume, MetalTagEnum metal) {
        ModEffects.giveFeruchemicalStorageEffect(player, metal);
        if (player.isOnFire()) {
            if (!playerCapability.isStoring(MetalTagEnum.NICROSIL) || !nicConsume) {
                compoundTag.putInt(metal.getNameLower() + "_feruchemic_reserve", metalReserve + 1);
            }
        }
        return compoundTag;
    }
}