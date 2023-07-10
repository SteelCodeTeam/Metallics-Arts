package net.rudahee.metallics_arts.modules.custom_items.weapons.guns;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
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
    public void onUsingTick(ItemStack stack, LivingEntity player, int count) {
        /*if (ModKeyRegister.RELOAD.isDown()) {
            if (count % 30 == 0) {
                reload(stack);
            }
        }*/
        player.sendSystemMessage(Component.translatable("carga = " + stack.getTag().get(BULLETS)));
        super.onUsingTick(stack, player, count);
    }

    @SubscribeEvent
    public void leftClick (PlayerInteractEvent.LeftClickEmpty event) {

        ItemStack item = event.getEntity().getItemInHand(event.getHand());
        if (item.getItem() instanceof PistolTest) {
            if (!item.hasTag()) {
                item.setTag(generateGunTags());
            }

            if (item.getTag().getInt(BULLETS) > 0) {
                //generar disparo
                event.getEntity().sendSystemMessage(Component.translatable("BANG"));
                //sonido de disparo
            } else {
                event.getEntity().sendSystemMessage(Component.translatable("VACIO"));
                //sonido de cargador vacio
            }
        }

        /**
         * ESTE METODO HAY QUE SINCRONIZARLO MANUALMENTE CON UN PACKET
         *
         */
    }
    public void reload (ItemStack itemStack) {
        CompoundTag t = itemStack.getTag();
        t.putInt(BULLETS, t.getInt(BULLETS) + 1);
        itemStack.setTag(t);
    }

    private CompoundTag generateGunTags() {
        CompoundTag compoundTag = new CompoundTag();
        compoundTag.putInt(BULLETS, 0);
        compoundTag.putInt(BULLETS_MAX, 6);
        compoundTag.putString(BULLET_TYPE, this.bulletType.getType());
        return compoundTag;
    }

}
