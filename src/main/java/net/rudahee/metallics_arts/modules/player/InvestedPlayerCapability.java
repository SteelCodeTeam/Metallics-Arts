package net.rudahee.metallics_arts.modules.player;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;

import javax.annotation.Nullable;

public class InvestedPlayerCapability {
    @CapabilityInject(IInvestedPlayerData.class)
    public static final Capability<IInvestedPlayerData> PLAYER_CAP = null;

    public static final ResourceLocation IDENTIFIER = new ResourceLocation(MetallicsArts.MOD_ID, "metallics_arts_data");

 /*   public static void register() {
        CapabilityManager.INSTANCE.register(IInvestedPlayerData.class, new Storage(), DefaultInvestedPlayerData::new);
    }

    public static class Storage implements Capability.IStorage<IInvestedPlayerData>{

        @Nullable
        @Override
        public INBT writeNBT(Capability<IInvestedPlayerData> capability, IInvestedPlayerData data, Direction side) {
            CompoundNBT metallic_arts_data = new CompoundNBT();
            CompoundNBT abilities = new CompoundNBT();

            for (MetalsNBTData metal : MetalsNBTData.values()){
                abilities.putBoolean("allomantic_"+metal.getGemNameLower(),data.hasAllomanticPower(metal));
                abilities.putBoolean("feruchemic_"+metal.getGemNameLower(),data.hasFeruchemicPower(metal));
            }
            metallic_arts_data.put("abilities",abilities);

            for (MetalsNBTData metal : MetalsNBTData.values()){
                abilities.putBoolean("allomantic_"+metal.getGemNameLower(),data.hasAllomanticPower(metal));
                abilities.putBoolean("feruchemic_"+metal.getGemNameLower(),data.hasFeruchemicPower(metal));
            }
            metallic_arts_data.put("metal",abilities);



        }

        @Override
        public void readNBT(Capability<IInvestedPlayerData> capability, IInvestedPlayerData instance, Direction side, INBT nbt) {

        }
    }


*/
}
