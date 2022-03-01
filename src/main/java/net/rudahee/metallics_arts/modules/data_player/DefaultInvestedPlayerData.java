package net.rudahee.metallics_arts.modules.data_player;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;

import java.util.ArrayList;
import java.util.Arrays;

public class DefaultInvestedPlayerData implements IDefaultInvestedPlayerData {

    private final boolean[] allomantic_powers;
    private final boolean[] feruchemic_powers;

    private final int[] allomantic_reseve;
    private final boolean[] burning_metals;
    private final int[] max_burning_time;

    private boolean invested;
    private boolean mistborn;
    private boolean fullFeruchemic;
    private boolean fullInvested;


    public DefaultInvestedPlayerData(){
        int powers = MetalsNBTData.values().length;
        int i = 0;

        this.invested = false;
        this.mistborn = false;
        this.fullFeruchemic = false;
        this.fullInvested = false;

        this.allomantic_powers = new boolean[powers];
        Arrays.fill(this.allomantic_powers, false);

        this.feruchemic_powers = new boolean[powers];
        Arrays.fill(this.feruchemic_powers, false);

        this.allomantic_reseve = new int[powers];
        Arrays.fill(this.allomantic_reseve, 0);

        this.max_burning_time = new int[powers];

        for(MetalsNBTData metals: MetalsNBTData.values()){
            this.max_burning_time[i] = metals.getMaxAllomanticTicksStorage();
            i++;
        }

        this.burning_metals = new boolean[powers];
        Arrays.fill(this.burning_metals, false);

    }


    @Override
    public void tickAllomancyBurningMetals(ServerPlayerEntity player){

    }
    @Override
    public void tickFeruchemyStorageMetals(ServerPlayerEntity player){

    }
    @Override
    public void tickFeruchemyDecantMetals(ServerPlayerEntity player){

    }

    @Override
    public boolean hasAllomanticPower(MetalsNBTData metal){
        if (this.allomantic_powers[metal.getIndex()])
            return true;
        return false;
    }

    @Override
    public boolean hasFeruchemicPower(MetalsNBTData metal){
        if (this.feruchemic_powers[metal.getIndex()])
            return true;
        return false;
    }

    @Override
    public int getAllomanticPowerCount(){
        int count = 0;
        for (boolean value : this.allomantic_powers){
            if (value)
                count++;
        }
        return count;
    }
    @Override
    public int getFeruchemicPowerCount(){
        int count = 0;
        for (boolean value : this.feruchemic_powers){
            if (value)
                count++;
        }
        return count;
    }

    @Override
    public ArrayList<MetalsNBTData> getAllomanticPowers(){
        ArrayList<MetalsNBTData> powers = new ArrayList<>();
        for(MetalsNBTData metals: MetalsNBTData.values()){
            if (hasAllomanticPower(metals))
                powers.add(metals);
        }
        return powers;
    }
    @Override
    public ArrayList<MetalsNBTData> getFeruchemicPowers(){
        ArrayList<MetalsNBTData> powers = new ArrayList<>();
        for(MetalsNBTData metals: MetalsNBTData.values()){
            if (hasFeruchemicPower(metals))
                powers.add(metals);
        }
        return powers;
    }

    @Override
    public void setMistborn(boolean mistborn){
        this.mistborn = mistborn;
    }
    @Override
    public void setFullFeruchemic(boolean feruchemic){
        this.fullFeruchemic = feruchemic;
    }

    @Override
    public void setFullInvested(boolean invested){
        this.fullInvested = invested;
    }

    @Override
    public boolean isMistborn() {
        return  this.mistborn;
    }

    @Override
    public boolean isFullFeruchemic() {
        return  this.fullFeruchemic;
    }

    @Override
    public boolean isFullInvested() {
        return this.fullInvested;
    }

    @Override
    public boolean isInvested(){
        return this.invested;
    }

    @Override
    public void setInvested(boolean invested){
        this.invested = invested;
    }

    @Override
    public void addAllomanticPower(MetalsNBTData metal){
        this.allomantic_powers[metal.getIndex()] = true;
    }
    @Override
    public void addFeruchemicPower(MetalsNBTData metal){
        this.feruchemic_powers[metal.getIndex()] = true;
    }

    @Override
    public void removeAllomanticPower(MetalsNBTData metal){
        this.allomantic_powers[metal.getIndex()] = false;
    }

    @Override
    public void removeFeruchemicPower(MetalsNBTData metal){
        this.feruchemic_powers[metal.getIndex()] = false;
    }

    @Override
    public boolean isBurning(MetalsNBTData metal){
        return this.burning_metals[metal.getIndex()];
    }


    @Override
    public void setBurning(MetalsNBTData metal, boolean state){
        this.burning_metals[metal.getIndex()] = state;
    }

    @Override
    public void setAllomanticMetalsAmount(MetalsNBTData metal, int amt){
        int value = this.allomantic_reseve[metal.getIndex()];
        value =+ amt;
        if (this.max_burning_time[metal.getIndex()]<value)
            value = this.max_burning_time[metal.getIndex()];
        this.allomantic_reseve[metal.getIndex()]=value;
    }

    @Override
    public int getAllomanticAmount(MetalsNBTData metal){
        return this.allomantic_reseve[metal.getIndex()];
    }


}
