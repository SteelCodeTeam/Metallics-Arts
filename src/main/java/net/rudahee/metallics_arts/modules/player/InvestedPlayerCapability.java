package net.rudahee.metallics_arts.modules.player;

import net.minecraft.entity.EntityType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.util.INBTSerializable;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;

public class InvestedPlayerCapability implements IInvestedPlayer {

    @CapabilityInject(IInvestedPlayer.class)
    public static final Capability<IInvestedPlayerData> PLAYER_CAP = null;
    public static final String NBT_KEY = "metallics_arts.invested_player_data";


    public CompoundNBT serializeNBT(HashMap<String, HashMap<String, Integer>> data)
    {
        CompoundNBT nbt = new CompoundNBT();

        for (String metalKey: data.keySet()) {
            for (String valueKey : data.get(metalKey).keySet()) {

                nbt.putInt(NBT_KEY + "." + metalKey + "." + valueKey, data.get(metalKey).get(valueKey));
            }
        }

        return nbt;
    }

    @Override
    public CompoundNBT serializeNBT() {
        return null;
    }
    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        System.out.println(":)");
    }

    public HashMap<String, HashMap<String, Integer>> deserializeNBT(CompoundNBT nbt, List<String> metals)
    {
        HashMap<String, HashMap<String, Integer>> data = new HashMap<String, HashMap<String, Integer>>();
        HashMap<String, Integer> dataForMetal = new HashMap<>();

        for (String metal : metals) {

            for (String keyForMetal : DefaultInvestedPlayerData.getDataForEachMetalIndex()) {

                dataForMetal.put(keyForMetal, nbt.getInt(NBT_KEY +"." + metal + "." + keyForMetal));

            }


            data.put(metal, dataForMetal);
            dataForMetal.clear();
        }

        return data;
    }

}
