package net.rudahee.metallics_arts.modules.custom_items.armors;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class MistCloak extends Item implements ICurioItem {

    private Float DEFAULT_SPEED;

    public MistCloak(Properties properties) {
        super(properties);
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        LivingEntity livingEntity = slotContext.entity();

        if (livingEntity.level instanceof ServerLevel) {
            if (livingEntity instanceof Player player) {
                if (player.level.isThundering() || player.level.isNight()) {
                    if (DEFAULT_SPEED == null) {
                        DEFAULT_SPEED = player.getSpeed();
                    }
                    player.setSpeed(player.getSpeed() * 30f);
                } else {
                    player.setSpeed(DEFAULT_SPEED);
                }
            }
        }

        ICurioItem.super.curioTick(slotContext, stack);
    }


    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        LivingEntity livingEntity = slotContext.entity();

        if (livingEntity.level instanceof ServerLevel) {
            if (livingEntity instanceof Player player) {
                if (DEFAULT_SPEED != null) {
                    player.setSpeed(DEFAULT_SPEED);
                }
            }
        }

        ICurioItem.super.onUnequip(slotContext, newStack, stack);
    }
}
