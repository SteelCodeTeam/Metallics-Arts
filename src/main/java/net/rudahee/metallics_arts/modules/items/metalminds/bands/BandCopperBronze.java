package net.rudahee.metallics_arts.modules.items.metalminds.bands;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.server.ServerWorld;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.modules.data_player.InvestedCapability;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;
import net.rudahee.metallics_arts.setup.network.ModNetwork;

public class BandCopperBronze extends BandMindAbstract {


    private static int MAX_COPPER = 100000;
    private static int MAX_BRONZE = 160000;
    public BandCopperBronze(Item.Properties properties){
        super(properties, MetalsNBTData.COPPER,MetalsNBTData.BRONZE,MAX_COPPER,MAX_BRONZE);
    }
}