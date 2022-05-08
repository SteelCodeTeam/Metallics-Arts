package net.rudahee.metallics_arts.modules.data_player;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;

import java.util.Arrays;

public class InvestedStorage implements Capability.IStorage<IDefaultInvestedPlayerData> {

    @Override
    public INBT writeNBT(Capability<IDefaultInvestedPlayerData> capability, IDefaultInvestedPlayerData data, Direction side) {


        CompoundNBT invested_data = new CompoundNBT();
        CompoundNBT allomantic_powers = new CompoundNBT();
        CompoundNBT feruchemic_powers = new CompoundNBT();
        CompoundNBT allomantic_reseve = new CompoundNBT();
        CompoundNBT burning_metals = new CompoundNBT();
        CompoundNBT death_pos = new CompoundNBT();
        CompoundNBT spawn_pos = new CompoundNBT();
        CompoundNBT spawn_dimension = new CompoundNBT();
        CompoundNBT death_dimension = new CompoundNBT();


        for (MetalsNBTData metal : MetalsNBTData.values()) {
            allomantic_powers.putBoolean(metal.getNameLower(), data.hasAllomanticPower(metal));
            feruchemic_powers.putBoolean(metal.getNameLower(), data.hasFeruchemicPower(metal));
            allomantic_reseve.putInt(metal.getNameLower(), data.getAllomanticAmount(metal));
            burning_metals.putBoolean(metal.getNameLower(), data.isBurning(metal));
        }

        invested_data.put("allomantic_powers", allomantic_powers);
        invested_data.put("feruchemic_powers", feruchemic_powers);
        invested_data.put("allomantic_reseve", allomantic_reseve);
        invested_data.put("burning_metals", burning_metals);

        invested_data.putBoolean("invested",data.isInvested());
        invested_data.putBoolean("mistborn",data.isMistborn());
        invested_data.putBoolean("fullFeruchemic",data.isFullFeruchemic());
        invested_data.putBoolean("fullInvested",data.isFullInvested());

        death_pos.putIntArray("death_pos",data.getDeathPos());
        death_dimension.putString("death_dimension",data.getDeathDimension());

        spawn_pos.putIntArray("spawn_pos", data.getSpawnPos());
        spawn_dimension.putString("spawn_dimension",data.getSpawnDimension());


        invested_data.put("death_pos",death_pos);
        invested_data.put("death_dimension", spawn_dimension);
        invested_data.put("spawn_pos",spawn_pos);

        return invested_data;
    }

    @Override
    public void readNBT(Capability<IDefaultInvestedPlayerData> capability, IDefaultInvestedPlayerData data, Direction side, INBT nbt) {
        CompoundNBT invested_data = (CompoundNBT) nbt;

        CompoundNBT allomantic_powers = (CompoundNBT) invested_data.get("allomantic_powers");
        CompoundNBT feruchemic_powers = (CompoundNBT) invested_data.get("feruchemic_powers");
        CompoundNBT allomantic_reseve = (CompoundNBT) invested_data.get("allomantic_reseve");
        CompoundNBT burning_metals = (CompoundNBT) invested_data.get("burning_metals");

        CompoundNBT death_pos = (CompoundNBT) invested_data.get("death_pos");
        CompoundNBT spawn_pos = (CompoundNBT) invested_data.get("spawn_pos");
        CompoundNBT death_dimension = (CompoundNBT) invested_data.get("death_dimension");
        CompoundNBT spawn_dimension = (CompoundNBT) invested_data.get("spawn_dimension");
        for (MetalsNBTData metal : MetalsNBTData.values()) {
            if (allomantic_powers.getBoolean(metal.getNameLower())) {
                data.addAllomanticPower(metal);
            } else {
                data.removeAllomanticPower(metal);
            }

            if (feruchemic_powers.getBoolean(metal.getNameLower())) {
                data.addFeruchemicPower(metal);
            } else {
                data.removeFeruchemicPower(metal);
            }

            if (data.hasAllomanticPower(metal)){
                data.setAllomanticMetalsAmount(metal,allomantic_reseve.getInt(metal.getNameLower()));
                data.setBurning(metal,burning_metals.getBoolean(metal.getNameLower()));
            }
        }

        if (death_pos.getIntArray("death_pos") != null || death_dimension.getString("death_dimension") != null) {
            data.setDeathPos(death_pos.getIntArray("death_pos"));
            data.setDeathDimension(death_dimension.getString("death_dimension"));
        }
        if (spawn_pos.getIntArray("spawn_pos") != null || spawn_dimension.getString("spawn_dimension") != null) {
            data.setSpawnPos(spawn_pos.getIntArray("spawn_pos"));
            data.setSpawnDimension(spawn_dimension.getString("spawn_dimension"));
        }
    }
}

