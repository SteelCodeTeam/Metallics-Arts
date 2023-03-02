package net.rudahee.metallics_arts.modules.custom_items.metal_minds.rings;


import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.modules.custom_items.metal_minds.RingsMindAbstract;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.AbstractFechuchemicHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.cognitive_metals.BronzeFecuchemicHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.cognitive_metals.CopperFecuchemicHelper;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

/**
 * Class that specifies the copper and bronze ring, we pass to the abstract class (which has the behavior) the metals that compose it,
 * in the order: metal and its alloy, along with their corresponding suppliers.
 *
 * @author SteelCode Team
 * @since 1.5.1
 *
 * @see RingsMindAbstract
 * @see AbstractFechuchemicHelper
 */
public class RingCopperBronze extends RingsMindAbstract <CopperFecuchemicHelper, BronzeFecuchemicHelper> {
    /**
     * Default constructor, it is important to send the metals by parameter in the correct order, metal and its alloy.
     *
     * @param properties of the item.
     */
    public RingCopperBronze(Properties properties){
        super(properties, MetalTagEnum.COPPER, MetalTagEnum.BRONZE, CopperFecuchemicHelper.getInstance(), BronzeFecuchemicHelper.getInstance());
    }

    /**
     * This method uses the internal information of the item to generate add the own Tooltips of the ring, for example, owner, and amount of current reservations.
     * <p>
     * This is a specific specification of the method for this particular item, since it has certain modifications with respect to the generic of the abstract class, it differs in that:
     * <p>
     * - The Copper has a reserve storage based on experience points, unlike the generic method that uses time, so this method adapts to that specification, showing experience points instead of seconds
     *
     * @param stack item that is being observed with the mouse over it.
     * @param level minecraft world you are in.
     * @param toolTips tooltips of the basic item, to which new information will be added.
     * @param flagIn flag that indicates if the tooltip information is normal or advanced, not used for mod item information.
     *
     */
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> toolTips, TooltipFlag flagIn) {
        if (stack.hasTag()) {
            if (!Screen.hasShiftDown()){
                toolTips.add(Component.translatable("metallics_arts.metal_translate."+getMetals(0).getNameLower()).append(": "+ stack.getTag().getInt(getMetals(0).getNameLower()+"_feruchemic_reserve") +" pts xp"));
                toolTips.add(Component.translatable("metallics_arts.metal_translate."+getMetals(1).getNameLower()).append(": "+ stack.getTag().getInt(getMetals(1).getNameLower()+"_feruchemic_reserve") / 20 + "s"));
            } else {
                toolTips.add(Component.translatable("metallics_arts.metal_translate."+getMetals(0).getNameLower()).append(": "+ ((stack.getTag().getInt(getMetals(0).getNameLower()+"_feruchemic_reserve") * 100)/stack.getTag().getInt(getMetals(0).getNameLower()+"_feruchemic_max_capacity"))+"%"));
                toolTips.add(Component.translatable("metallics_arts.metal_translate."+getMetals(1).getNameLower()).append(": "+ ((stack.getTag().getInt(getMetals(1).getNameLower()+"_feruchemic_reserve") * 100)/stack.getTag().getInt(getMetals(1).getNameLower()+"_feruchemic_max_capacity"))+"%"));
            }
            if (level != null) {
                toolTips.add(Component.translatable("metallics_arts.mental_mind.owner").append(": "+ ((stack.getTag().getString("key").equals("Nobody")) ? Component.translatable("metallics_arts.mental_mind.nobody").getString() : (level.getPlayerByUUID(UUID.fromString((stack.getTag().getString("key")))) == null) ? Component.translatable("metallics_arts.mental_mind.owner_someone") : level.getPlayerByUUID(UUID.fromString((stack.getTag().getString("key")))).getName().getString())));
            }
            if (!Screen.hasShiftDown()){
                toolTips.add(Component.translatable(" "));
                toolTips.add(Component.translatable("metallics_arts.mental_mind_translate.shift_info").withStyle(ChatFormatting.BLUE));
            }
        }
        super.appendHoverText(stack, level, toolTips, flagIn);
    }
}