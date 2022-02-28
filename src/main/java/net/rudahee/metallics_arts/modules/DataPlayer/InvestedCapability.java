package net.rudahee.metallics_arts.modules.DataPlayer;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.NBTTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;

import javax.annotation.Nullable;

public class InvestedCapability {

    @CapabilityInject(IDefaultInvestedPlayerData.class)
    public static final Capability<IDefaultInvestedPlayerData> PLAYER_CAP = null;

    public static final ResourceLocation IDENTIFIER = new ResourceLocation(MetallicsArts.MOD_ID, "allomancy_data");

    public static void register() {
        CapabilityManager.INSTANCE.register(IDefaultInvestedPlayerData.class, new InvestedStorage(), DefaultInvestedPlayerData::new);
    }

}
