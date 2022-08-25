package net.rudahee.metallics_arts.modules.items.metal_spike;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.rudahee.metallics_arts.modules.data_player.InvestedCapability;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;
import net.rudahee.metallics_arts.setup.enums.metals.Metal;
import net.rudahee.metallics_arts.setup.network.ModNetwork;
import net.rudahee.metallics_arts.setup.registries.ModItems;
import org.lwjgl.system.CallbackI;

public class AluminumSpike extends MetalSpikeAbstract {

    public AluminumSpike(Properties properties) {
        super(properties,MetalsNBTData.ALUMINUM);
    }



}
