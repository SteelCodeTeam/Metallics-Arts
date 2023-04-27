package net.rudahee.metallics_arts.modules.custom_items.metal_minds.bands;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.modules.custom_items.metal_minds.BandMindAbstract;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.AbstractFechuchemicHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.god_metals.AtiumFecuchemicHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.god_metals.MalatiumFecuchemicHelper;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

/**
 * Class that specifies the atium and malatium band, we pass to the abstract class (which has the behavior) the metals that compose it,
 * in the order: metal and its alloy, along with their corresponding suppliers.
 *
 * @author SteelCode Team
 * @since 1.5.1
 *
 * @see BandMindAbstract
 * @see AbstractFechuchemicHelper
 */
public class BandAtiumMalatium extends BandMindAbstract {

    /**
     * Default constructor, it is important to send the metals by parameter in the correct order, metal and its alloy.
     *
     * @param properties of the item.
     */
    public BandAtiumMalatium (Item.Properties properties){
        super(properties, MetalTagEnum.ATIUM, MetalTagEnum.MALATIUM, new AtiumFecuchemicHelper(), new MalatiumFecuchemicHelper());
    }

    /**
     * This method uses the internal information of the item to generate add the own Tooltips of the ring, for example, owner, and amount of current reservations.
     * <p>
     * This is a specific specification of the method for this particular item, since it has certain modifications with respect to the generic of the abstract class, it differs in that:
     * <p>
     * - The Malatium has its own reserve storage since it stores the number of item uses, not times, as the text of the generic method shows.
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
            if (!stack.getTag().contains("tier_malatium_storage") || stack.getTag().getInt(getMetals(1).getNameLower()+"_feruchemic_reserve") == 0){
                stack.getTag().putInt("tier_malatium_storage",-1);
            }
            if (!Screen.hasShiftDown()){
                toolTips.add(Component.translatable("metallics_arts.metal_translate."+getMetals(0).getNameLower()).append(": "+ stack.getTag().getInt(getMetals(0).getNameLower()+"_feruchemic_reserve") / 20 + "s"));
                toolTips.add(Component.translatable("metallics_arts.metal_translate."+getMetals(1).getNameLower()).append(": "+ stack.getTag().getInt(getMetals(1).getNameLower()+"_feruchemic_reserve")+" ").append(Component.translatable("metallics_arts.mental_mind_translate.uses")));

            } else {
                toolTips.add(Component.translatable("metallics_arts.metal_translate."+getMetals(0).getNameLower()).append(": "+ ((stack.getTag().getInt(getMetals(0).getNameLower()+"_feruchemic_reserve") * 100)/this.getMetals(0).getMaxReserveBand())+"%"));
                toolTips.add(Component.translatable("metallics_arts.metal_translate."+getMetals(1).getNameLower()).append(": "+ ((stack.getTag().getInt(getMetals(1).getNameLower()+"_feruchemic_reserve") * 100)/this.getMetals(1).getMaxReserveBand())+"%"));
            }
            if (level != null) {
                toolTips.add(Component.translatable("metallics_arts.mental_mind.owner").append(": "+ ((stack.getTag().getString("key").equals("Nobody")) ? Component.translatable("metallics_arts.mental_mind.nobody").getString() : (level.getPlayerByUUID(UUID.fromString((stack.getTag().getString("key")))) == null) ? Component.translatable("metallics_arts.mental_mind.owner_someone") : level.getPlayerByUUID(UUID.fromString((stack.getTag().getString("key")))).getName().getString())));
            }
            if (stack.getTag().getInt("tier_malatium_storage")!=-1){
                toolTips.add(Component.translatable("-------------------"));
                toolTips.add(Component.translatable("Tier: "+MalatiumFecuchemicHelper.convertTierToMaterial(stack.getTag().getInt("tier_malatium_storage"))));
            }
            if (!Screen.hasShiftDown()){
                toolTips.add(Component.translatable(" "));
                toolTips.add(Component.translatable("metallics_arts.mental_mind_translate.shift_info").withStyle(ChatFormatting.BLUE));
            }
        }
        super.appendHoverText(stack, level, toolTips, flagIn);
    }
}