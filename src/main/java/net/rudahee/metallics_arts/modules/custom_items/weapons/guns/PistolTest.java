package net.rudahee.metallics_arts.modules.custom_items.weapons.guns;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.rudahee.metallics_arts.data.enums.implementations.BulletType;
import net.rudahee.metallics_arts.setup.registries.ModKeyRegister;


public class PistolTest extends Item  {
    private final String BULLETS  = "bullets_count";
    private final String BULLETS_MAX  = "bullets_max";
    private final String BULLET_TYPE  = "bullets_type";
    private BulletType bulletType;

    public PistolTest(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        player.sendSystemMessage(Component.translatable("APUNTANDO"));

        return super.use(level, player, hand);
    }



    public void reload (Player player, CompoundTag compoundTag) {
        player.sendSystemMessage(Component.translatable("RECARGANDO"));
        compoundTag.putInt(this.BULLETS, compoundTag.getInt(this.BULLETS_MAX));
    }

    public CompoundTag generateGunTags() {
        CompoundTag compoundTag = new CompoundTag();
        compoundTag.putInt(BULLETS, 0);
        compoundTag.putInt(BULLETS_MAX, 6);
        compoundTag.putString(BULLET_TYPE, BulletType.NONE.getType());
        return compoundTag;
    }

    public CompoundTag shot(LocalPlayer player, CompoundTag tag) {
        if (tag.getInt(BULLETS) > 0) {
            player.sendSystemMessage(Component.translatable("BANG"));
            tag.putInt(this.BULLETS, tag.getInt(this.BULLETS) - 1);
            player.playSound(SoundEvents.CROSSBOW_HIT);
        } else {
            player.sendSystemMessage(Component.translatable("VACIO"));
        }
        return tag;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack p_41452_) {
        return UseAnim.CROSSBOW;
    }

}
