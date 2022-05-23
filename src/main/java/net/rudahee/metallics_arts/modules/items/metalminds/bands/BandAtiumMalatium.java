package net.rudahee.metallics_arts.modules.items.metalminds.bands;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.modules.data_player.InvestedCapability;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;
import net.rudahee.metallics_arts.setup.network.ModNetwork;

import javax.annotation.Nullable;
import java.util.List;

public class BandAtiumMalatium extends BandMindAbstract {
    private static int MAX_ATIUM = 100;
    private static int MAX_MALATIUM = 16000;

    public BandAtiumMalatium (Item.Properties properties){
        super(properties, MetalsNBTData.ATIUM,MetalsNBTData.MALATIUM);
    }

}