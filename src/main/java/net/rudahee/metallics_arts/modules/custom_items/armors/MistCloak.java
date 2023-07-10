package net.rudahee.metallics_arts.modules.custom_items.armors;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.rudahee.metallics_arts.setup.registries.ModKeyRegister;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import javax.annotation.Nullable;
import java.util.List;

import static net.rudahee.metallics_arts.modules.effects.ModModifiers.MISTCLOACK_SPEED;

public class MistCloak extends Item implements ICurioItem {

    public MistCloak(Properties properties) {
        super(properties);
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        LivingEntity livingEntity = slotContext.entity();


        if (livingEntity.level instanceof ServerLevel) {

            if (livingEntity instanceof Player player) {

                if (player.level.isNight() || player.level.isThundering()) {
                    if (!player.getAttribute(Attributes.MOVEMENT_SPEED).hasModifier(MISTCLOACK_SPEED)) {
                        player.getAttribute(Attributes.MOVEMENT_SPEED).addTransientModifier(MISTCLOACK_SPEED);
                    }
                } else {
                    player.getAttribute(Attributes.MOVEMENT_SPEED).removeModifier(MISTCLOACK_SPEED);
                }
            }
        }

        ICurioItem.super.curioTick(slotContext, stack);
    }

    public void appendHoverText(ItemStack stack, @Nullable Level level, @NotNull List<Component> toolTips, @NotNull TooltipFlag flag) {
        toolTips.add(Component.translatable("metallics_arts.item.tooltip.mistcloak"));
    }
    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        LivingEntity livingEntity = slotContext.entity();

        if (livingEntity.level instanceof ServerLevel) {
            if (livingEntity instanceof Player player) {
                if (!player.getAttribute(Attributes.MOVEMENT_SPEED).hasModifier(MISTCLOACK_SPEED)) {
                    player.getAttribute(Attributes.MOVEMENT_SPEED).removeModifier(MISTCLOACK_SPEED);
                }
                player.setCustomNameVisible(true);
            }
        }
        
        ICurioItem.super.onUnequip(slotContext, newStack, stack);
    }

    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        LivingEntity livingEntity =  slotContext.entity();

        if (livingEntity.level instanceof ServerLevel) {
            if (livingEntity instanceof Player player) {
                player.setCustomNameVisible(false);
            }
        }

        ICurioItem.super.onEquip(slotContext, prevStack, stack);
    }
}
