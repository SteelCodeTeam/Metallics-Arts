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


        if (!Arrays.stream(data.getDeathpos()).anyMatch(pos -> pos==null)){
            death_pos.putInt("death_pos_x",data.getDeathpos()[0]);
            death_pos.putInt("death_pos_y",data.getDeathpos()[1]);
            death_pos.putInt("death_pos_z",data.getDeathpos()[2]);
            death_pos.putInt("death_dimension",data.getDeathpos()[3]);
        }

        if (!Arrays.stream(data.getSpawnPos()).anyMatch(pos -> pos==null)){
            spawn_pos.putInt("spawn_pos_x",data.getSpawnPos()[0]);
            spawn_pos.putInt("spawn_pos_y",data.getSpawnPos()[1]);
            spawn_pos.putInt("spawn_pos_z",data.getSpawnPos()[2]);
            spawn_pos.putInt("spawn_dimension",data.getSpawnPos()[3]);
        }

        invested_data.put ("death_pos",death_pos);
        invested_data.put ("spawn_pos",spawn_pos);



            /*CompoundNBT position = new CompoundNBT();
            BlockPos death_pos = data.getDeathLoc();
            if (death_pos != null) {
                position.putString("death_dimension", data.getDeathDim().location().toString());
                position.putInt("death_x", death_pos.getX());
                position.putInt("death_y", death_pos.getY());
                position.putInt("death_z", death_pos.getZ());
            }
            BlockPos spawn_pos = data.getSpawnLoc();
            if (spawn_pos != null) {
                position.putString("spawn_dimension", data.getSpawnDim().location().toString());
                position.putInt("spawn_x", spawn_pos.getX());
                position.putInt("spawn_y", spawn_pos.getY());
                position.putInt("spawn_z", spawn_pos.getZ());
            }
            invested_data.put("position", position);*/

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

        data.setDeathPos(new Integer[]{death_pos.getInt("death_pos_x"),death_pos.getInt("death_pos_y"),death_pos.getInt("death_pos_z"),death_pos.getInt("death_dimension")});

        data.setSpawnPos(new Integer[]{spawn_pos.getInt("spawn_pos_x"),spawn_pos.getInt("spawn_pos_y"),spawn_pos.getInt("spawn_pos_z"),spawn_pos.getInt("spawn_dimension")});

    }
}

