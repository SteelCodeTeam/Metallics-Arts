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
        CapabilityManager.INSTANCE.register(IDefaultInvestedPlayerData.class, new Storage(), () -> new DefaultInvestedPlayerData());
    }



    public static class Storage implements Capability.IStorage<IDefaultInvestedPlayerData> {

        @Override
        public INBT writeNBT(Capability<IDefaultInvestedPlayerData> capability, IDefaultInvestedPlayerData data, Direction side) {


            CompoundNBT invested_data = new CompoundNBT();

            CompoundNBT allomantic_powers = new CompoundNBT();
            CompoundNBT feruchemic_powers = new CompoundNBT();
            CompoundNBT allomantic_reseve = new CompoundNBT();
            CompoundNBT burning_metals = new CompoundNBT();


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

            data.setInvested(invested_data.getBoolean("invested"));
            data.setMistborn(invested_data.getBoolean("mistborn"));
            data.setFullFeruchemic(invested_data.getBoolean("fullFeruchemic"));
            data.setFullInvested(invested_data.getBoolean("fullInvested"));

            /*

            CompoundNBT position = (CompoundNBT) allomancy_data.get("position");
            if (position.contains("death_dimension")) {
                data.setDeathLoc(new BlockPos(position.getInt("death_x"), position.getInt("death_y"), position.getInt("death_z")), position.getString("death_dimension"));
            }
            if (position.contains("spawn_dimension")) {
                data.setSpawnLoc(new BlockPos(position.getInt("spawn_x"), position.getInt("spawn_y"), position.getInt("spawn_z")), position.getString("spawn_dimension"));
            }*/
        }
    }










}
