package net.rudahee.metallics_arts.modules.custom_items.metal_minds.rings;


import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.player.IInvestedPlayerData;
import net.rudahee.metallics_arts.modules.custom_items.metal_minds.RingsMindAbstract;
import net.rudahee.metallics_arts.modules.custom_items.metal_minds.bands.BandAluminumDuralumin;
import net.rudahee.metallics_arts.modules.custom_items.metal_minds.bands.BandAtiumMalatium;
import net.rudahee.metallics_arts.modules.custom_items.metal_minds.bands.BandCopperBronze;
import net.rudahee.metallics_arts.modules.custom_items.metal_minds.bands.BandLerasiumEttmetal;
import net.rudahee.metallics_arts.modules.error_handling.exceptions.PlayerException;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.AbstractFechuchemicHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.hybrid_metals.ElectrumFecuchemicHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.hybrid_metals.GoldFecuchemicHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.spiritual_metals.AluminumFecuchemicHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.spiritual_metals.DuraluminFecuchemicHelper;
import net.rudahee.metallics_arts.setup.network.ModNetwork;
import net.rudahee.metallics_arts.utils.CapabilityUtils;
import net.rudahee.metallics_arts.utils.MetalMindsUtils;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

/**
 * Class that specifies the aluminum and duralumin ring, we pass to the abstract class (which has the behavior) the metals that compose it,
 * in the order: metal and its alloy, along with their corresponding suppliers.
 *
 * @author SteelCode Team
 * @since 1.5.1
 *
 * @see RingsMindAbstract
 * @see AbstractFechuchemicHelper
 */
public class RingAluminumDuralumin extends RingsMindAbstract {

    /**
     * Default constructor, it is important to send the metals by parameter in the correct order, metal and its alloy.
     *
     * @param properties of the item.
     */
    public RingAluminumDuralumin(Properties properties){
        super(properties, MetalTagEnum.ALUMINUM, MetalTagEnum.DURALUMIN, new AluminumFecuchemicHelper(), new DuraluminFecuchemicHelper());
    }


    private boolean isEquiped = false;

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> toolTips, TooltipFlag flagIn) {
        if (stack.hasTag()) {
            int reserve = stack.getTag().getInt(this.getMetals(0).getNameLower()+"_feruchemic_reserve");
            if (reserve == 1) {
                toolTips.add(Component.translatable("metallics_arts.metal_translate."+getMetals(0).getNameLower()).append(": " + "metallics_arts.mental_mind_translate.store_identity"));
            } else if (reserve == 2) {
                toolTips.add(Component.translatable("metallics_arts.metal_translate."+getMetals(0).getNameLower()).append(": " + "metallics_arts.mental_mind_translate.tapping_identity"));
            } else {
                toolTips.add(Component.translatable("metallics_arts.metal_translate."+getMetals(0).getNameLower()).append(": " + "metallics_arts.mental_mind_translate.off_power"));
            }
            if (!Screen.hasShiftDown()){
                toolTips.add(Component.translatable("metallics_arts.metal_translate."+getMetals(1).getNameLower()).append(": "+ stack.getTag().getInt(getMetals(1).getNameLower()+"_feruchemic_reserve") / 20 + "s"));
            } else {
                toolTips.add(Component.translatable("metallics_arts.metal_translate."+getMetals(1).getNameLower()).append(": "+ ((stack.getTag().getInt(getMetals(1).getNameLower()+"_feruchemic_reserve") * 100)/this.getMetals(1).getMaxReserveRing())+"%"));
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

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        LivingEntity livingEntity = slotContext.entity();
        CompoundTag compoundTag = stack.getTag();

        if(stack.hasTag()) {
            if (livingEntity.level instanceof ServerLevel) {
                if (livingEntity instanceof Player player) {
                    IInvestedPlayerData playerCapability;
                    try {
                        playerCapability = CapabilityUtils.getCapability(player);
                        int actualReserve = stack.getTag().getInt(this.getMetals(0).getNameLower() + "_feruchemic_reserve");
                        if (!playerCapability.isTapping(MetalTagEnum.ALUMINUM) && !playerCapability.isStoring(MetalTagEnum.ALUMINUM) && actualReserve != 3) {
                            stack.setTag(AluminumFecuchemicHelper.turnOffPower(compoundTag,this.getMetals(0).getNameLower() + "_feruchemic_reserve"));
                        }
                    } catch (PlayerException ex) {
                        ex.printCompleteLog();
                        return;
                    }

                }
            }
        }
        super.curioTick(slotContext, stack);
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        if(stack.hasTag()) {
            stack.setTag(AluminumFecuchemicHelper.turnOffPower(stack.getTag(),this.getMetals(0).getNameLower() + "_feruchemic_reserve"));
        }
        super.onUnequip(slotContext, newStack, stack);
    }

}