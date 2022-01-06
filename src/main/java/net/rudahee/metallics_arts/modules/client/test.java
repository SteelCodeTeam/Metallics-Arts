package net.rudahee.metallics_arts.modules.client;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.rudahee.metallics_arts.MetallicsArts;

@Mod.EventBusSubscriber(modid = MetallicsArts.MOD_ID, value = Dist.CLIENT)
public class test {

    @SubscribeEvent
    public static void clientTick (TickEvent.ClientTickEvent event){
        final PlayerEntity player = Minecraft.getInstance().player;
        if  (KeyInit.allomancy.isDown()){
            player.inventory.add(new ItemStack(Items.STONE));
        }
        if  (KeyInit.feruchemic.isDown()){
            player.inventory.add(new ItemStack(Items.FURNACE));
        }
    }


}
