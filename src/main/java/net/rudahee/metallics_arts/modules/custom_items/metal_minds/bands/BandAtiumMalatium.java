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
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.god_metals.AtiumFecuchemicHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.god_metals.MalatiumFecuchemicHelper;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class BandAtiumMalatium extends BandMindAbstract <AtiumFecuchemicHelper, MalatiumFecuchemicHelper> {

    public BandAtiumMalatium (Item.Properties properties){
        super(properties, MetalTagEnum.ATIUM, MetalTagEnum.MALATIUM, AtiumFecuchemicHelper.getInstance(), MalatiumFecuchemicHelper.getInstance());
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> toolTips, TooltipFlag flagIn) {
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
            if (world != null) {
                toolTips.add(Component.translatable("metallics_arts.mental_mind.owner").append(": "+ ((stack.getTag().getString("key").equals("Nobody")) ? Component.translatable("metallics_arts.mental_mind.nobody").getString() : (world.getPlayerByUUID(UUID.fromString((stack.getTag().getString("key")))) == null) ? Component.translatable("metallics_arts.mental_mind.owner_someone") : world.getPlayerByUUID(UUID.fromString((stack.getTag().getString("key")))).getName().getString())));
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
        super.appendHoverText(stack, world, toolTips, flagIn);
    }
}