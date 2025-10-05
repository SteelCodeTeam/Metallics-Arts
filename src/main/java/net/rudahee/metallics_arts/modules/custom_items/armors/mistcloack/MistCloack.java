package net.rudahee.metallics_arts.modules.custom_items.armors.mistcloack;

import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.RenderUtils;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Consumer;

import static net.rudahee.metallics_arts.modules.effects.ModModifiers.MISTCLOACK_SPEED;

public class MistCloack extends Item implements GeoItem, ICurioItem {

    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);


    public MistCloack(Properties properties) {
        super(properties);
    }


    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public double getTick(Object itemStack) {
        return RenderUtils.getCurrentTick();
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {

            private MistCloakRenderer renderer;

            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {

                if (renderer == null) {
                    renderer = new MistCloakRenderer();
                }

                return renderer;
            }
        });
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
                if (player.getAttribute(Attributes.MOVEMENT_SPEED).hasModifier(MISTCLOACK_SPEED)) {
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


    @Override
    public boolean canEquipFromUse(SlotContext slotContext, ItemStack stack) {
        return true;
    }

    private PlayState predicate(AnimationState state) {

        state.getController().setAnimation(RawAnimation.begin().then("move_forward", Animation.LoopType.LOOP));
        state.getController().setAnimation(RawAnimation.begin().then("move_backwards", Animation.LoopType.LOOP));

        state.getController().setAnimation(
                RawAnimation.begin().then("animation.mistcloack.move_forward", Animation.LoopType.LOOP)
        );
        return PlayState.CONTINUE;
    }

}

