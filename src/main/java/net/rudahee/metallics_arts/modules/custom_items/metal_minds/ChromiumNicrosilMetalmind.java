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
 * Class that specifies the chromium and nicrosil band, we pass to the abstract class (which has the behavior) the metals that compose it,
 * in the order: metal and its alloy, along with their corresponding suppliers.
 *
 * @author SteelCode Team
 * @since 1.6.8
 *
 */
public class ChromiumNicrosilMetalmind extends MetalmindAbstract {

    /**
     * Default constructor, it is important to send the metals by parameter in the correct order, metal and its alloy.
     *
     * @param properties of the item.
     */
    public ChromiumNicrosilMetalmind(Item.Properties properties, MetalmindType type) {
        super(properties, MetalTagEnum.CHROMIUM, MetalTagEnum.NICROSIL, type);
    }


    /**
     * Redefine of the method of the MetalmindAbstract class.
     * <p>
     * In this specific case, the Nicrosil reserve is discharged based on the amount of other metals being tapped at the same time as the Nicrosil.
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
    public CompoundTag calculateSecondMetalDischarge(CompoundTag compoundTag, Player player, IInvestedPlayerData playerCapability, int metalReserve, boolean nicConsume, MetalTagEnum metal) {
        ModEffects.giveFeruchemicalTapEffect(player, metal);
        compoundTag.putInt(metal.getNameLower() + "_feruchemic_reserve", metalReserve - (nicConsume ? 0 :playerCapability.cantMetalsTapping()));
        return compoundTag;

    }


    /**
     * Redefine of the method of the MetalmindAbstract class.
     * <p>
     * In this specific case, the Nicrosil reserve is charged based on the amount of other metals being stored at the same time as the Nicrosil.
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
        int value = playerCapability.cantMetalsStoring();
        if (playerCapability.isStoring(MetalTagEnum.BRASS) && !player.isOnFire()) {
            value = value - 1;
        }
        compoundTag.putInt(metal.getNameLower() + "_feruchemic_reserve", metalReserve + (nicConsume ? 0 : value));
        return compoundTag;
    }



}