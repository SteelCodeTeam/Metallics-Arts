package net.rudahee.metallics_arts.modules.data_player;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;
import net.rudahee.metallics_arts.setup.network.ModNetwork;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class DefaultInvestedPlayerData implements IDefaultInvestedPlayerData {

    private final boolean[] allomantic_powers;
    private final boolean[] feruchemic_powers;

    private final int[] allomantic_reseve;
    private final int[] lerasium_reseve;
    private final boolean[] burning_metals;

    private final boolean[] decanting_metals;

    private final boolean[] storing_metals;

    private final int[] max_burning_time;

    private boolean invested;
    private boolean mistborn;
    private boolean fullFeruchemic;
    private boolean fullInvested;

    private int[] death_pos;
    private int[] spawn_pos;

    private final boolean[] metal_mind_equiped;

    private String death_dimension;
    private String spawn_dimension;


    public DefaultInvestedPlayerData() {
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

        this.decanting_metals = new boolean[powers];
        Arrays.fill(this.decanting_metals, false);

        this.storing_metals = new boolean[powers];
        Arrays.fill(this.storing_metals, false);

        this.allomantic_reseve = new int[powers];
        Arrays.fill(this.allomantic_reseve, 0);

        this.lerasium_reseve = new int[powers];
        Arrays.fill(this.lerasium_reseve, 0);

        this.max_burning_time = new int[powers];

        for(MetalsNBTData metals: MetalsNBTData.values()){
            this.max_burning_time[i] = metals.getMaxAllomanticTicksStorage();
            i++;
        }

        this.death_pos = new int[3];
        this.spawn_pos = new int[3];

        this.metal_mind_equiped = new boolean[10];
        Arrays.fill(this.metal_mind_equiped, false);

        this.burning_metals = new boolean[powers];
        Arrays.fill(this.burning_metals, false);

    }


    @Override
    public void tickAllomancyBurningMetals(ServerPlayerEntity player){
        boolean readyToSync = false;

        for (MetalsNBTData metal: MetalsNBTData.values()) {
            // Allomatic things
            if (this.isBurning(metal)) {
                if (!this.hasAllomanticPower(metal)) {
                    this.setBurning(metal, false);
                    readyToSync = true;
                }
                else {
                    this.setAllomanticMetalsAmount(metal, this.getAllomanticAmount(metal) - 1);
                    if (this.getAllomanticAmount(metal) <= 0) {
                        this.setBurning(metal, false);
                    }
                    readyToSync = true;
                }

                if (readyToSync) {
                    ModNetwork.sync(this, player);
                }
            }

        }
    }
    @Override
    public void tickFeruchemyStorageMetals(ServerPlayerEntity player){

    }

    @Override
    public void tickFeruchemyDecantMetals(ServerPlayerEntity player){

    }

    @Override
    public boolean hasAllomanticPower(MetalsNBTData metal){
        return this.allomantic_powers[metal.getIndex()];
    }

    @Override
    public boolean hasFeruchemicPower(MetalsNBTData metal){
        return this.feruchemic_powers[metal.getIndex()];
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

    public void drainMetals(MetalsNBTData... metals) {
        for (MetalsNBTData mt : metals) {
            this.allomantic_reseve[mt.getIndex()] = 0;
            // So that they burn out next tick
            setAllomanticMetalsAmount(mt,1);
        }
    }

    @Override
    public int[] getDeathPos() {
        return this.death_pos;
    }

    @Override
    public int[] getSpawnPos() {
        return this.spawn_pos;
    }

    @Override
    public void setDeathPos(int[] deathPos) {
        for(int i=0;i<this.death_pos.length;i++){
            this.death_pos[i]=deathPos[i];
        }
    }

    @Override
    public void setSpawnPos(int[] spawnPos) {
        for(int i=0;i<this.spawn_pos.length;i++){
            this.spawn_pos[i]=spawnPos[i];
        }
    }

    @Override
    public void setDeathDimension(String dimension) {
        this.death_dimension = dimension;
    }

    @Override
    public void setSpawnDimension(String dimension) {
        this.spawn_dimension = dimension;
    }

    @Override
    public boolean getMetalMindEquiped(int group) {
        return this.metal_mind_equiped[group];
    }

    @Override
    public void setMetalMindEquiped(int group, boolean value) {
        this.metal_mind_equiped[group] = value;
    }

    @Override
    public boolean[] getMetalMindEquipedList() {
        return this.metal_mind_equiped;
    }

    @Override
    public void setMetalMindEquipedList(boolean[] list) {
        for (int i = 0;i<10;i++){
            this.metal_mind_equiped[i] =list[i];
        }
    }

    @Override
    public String getDeathDimension() {
        return this.death_dimension;
    }
    @Override
    public String getSpawnDimension() {
        return this.spawn_dimension;
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
        return this.mistborn;
    }

    @Override
    public boolean isFullFeruchemic() {
        return this.fullFeruchemic;
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
    public void setUninvested() {

    }

    //ADD
    @Override
    public void addAllomanticPower(MetalsNBTData metal){
        this.allomantic_powers[metal.getIndex()] = true;
        this.setInvested(true);
    }
    @Override
    public void addFeruchemicPower(MetalsNBTData metal){
        this.feruchemic_powers[metal.getIndex()] = true;
        this.setInvested(true);
    }

    @Override
    public void addAllAllomantic(){
        Arrays.fill(this.allomantic_powers, true);
        this.mistborn = true;
        this.setInvested(true);
    }

    @Override
    public void addAllFeruchemic(){
        Arrays.fill(this.feruchemic_powers, true);
        this.fullFeruchemic = true;
        this.setInvested(true);
    }

    //REMOVE
    @Override
    public void removeAllomanticPower(MetalsNBTData metal){
        this.allomantic_powers[metal.getIndex()] = false;
    }

    @Override
    public void removeAllAllomanticPower() {
        Arrays.fill(this.allomantic_powers, false);
        this.mistborn = false;
    }

    @Override
    public void removeFeruchemicPower(MetalsNBTData metal){
        this.feruchemic_powers[metal.getIndex()] = false;
    }

    @Override
    public void removeAllFeruchemicPower() {
        Arrays.fill(this.feruchemic_powers, false);
        this.fullFeruchemic = false;
    }

    //BURN
    @Override
    public boolean isBurning(MetalsNBTData metal){
        return this.burning_metals[metal.getIndex()];
    }

    @Override
    public boolean isBurningSomething() {
        boolean burning = false;
        for(MetalsNBTData metal: MetalsNBTData.values()) {
            if (isBurning(metal)) {
                burning = true;
            }
        }

        return burning;
    }

    @Override
    public MetalsNBTData getRandomBurningMetal() {
        ArrayList<MetalsNBTData> metals = new ArrayList<>();
        int index = 0;
        for (MetalsNBTData metal : MetalsNBTData.values()) {
            if (this.burning_metals[index]) {
                metals.add(metal);
            }
            index++;
        }

        Collections.shuffle(metals);
        return metals.get(0);
    }

    @Override
    public boolean isDecanting(MetalsNBTData metal) {
        return this.decanting_metals[metal.getIndex()];
    }

    @Override
    public boolean isStoring(MetalsNBTData metal) {
        return this.storing_metals[metal.getIndex()];
    }

    @Override
    public int cantMetalsDecanting(){
        int cant = 0;
        for (MetalsNBTData metal:MetalsNBTData.values()){
            if (metal!=MetalsNBTData.ALUMINUM && metal!=MetalsNBTData.LERASIUM && metal!=MetalsNBTData.NICROSIL && metal!=MetalsNBTData.MALATIUM && metal!=MetalsNBTData.COPPER){
                if (isDecanting(metal)){
                    cant++;
                }
            }
        }
        return cant;
    }

    @Override
    public int cantMetalsStoring() {
        int cant = 0;
        for (MetalsNBTData metal:MetalsNBTData.values()){
            if (metal!=MetalsNBTData.ALUMINUM && metal!=MetalsNBTData.LERASIUM && metal!=MetalsNBTData.NICROSIL && metal!=MetalsNBTData.MALATIUM && metal!=MetalsNBTData.COPPER ){
                if (isStoring(metal)){
                    cant++;
                }
            }
        }
        return cant;
    }

    @Override
    public void setDecanting(MetalsNBTData metal, boolean value) {
        this.decanting_metals[metal.getIndex()] = value;
    }

    @Override
    public void setStoring(MetalsNBTData metal, boolean value) {
        this.storing_metals[metal.getIndex()] = value;
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

    public boolean addAllomanticMetalAmount(MetalsNBTData metal, int amt) {
        int value = this.allomantic_reseve[metal.getIndex()];
        if (this.max_burning_time[metal.getIndex()] < value + amt) {
            this.allomantic_reseve[metal.getIndex()] = this.max_burning_time[metal.getIndex()];
            return false;
        } else {
            this.allomantic_reseve[metal.getIndex()] = value + amt;
            return true;
        }
    }
    public boolean substractAllomanticMetalAmount(MetalsNBTData metal, int amt) {
        int value = this.allomantic_reseve[metal.getIndex()];
        if (value - amt < 0) {
            this.allomantic_reseve[metal.getIndex()] = 0;
            return false;
        } else {
            this.allomantic_reseve[metal.getIndex()] = value - amt;
            return true;
        }
    }

    @Override
    public void setAmountLerasiumReserve(MetalsNBTData metal, int amt) {
        if (amt > 0 && amt < metal.getMaxAllomanticTicksStorage()) {
            this.lerasium_reseve[metal.getIndex()] = amt;
        }
    }

    @Override
    public int getAmountLerasiumReserve(MetalsNBTData metal) {
        return this.lerasium_reseve[metal.getIndex()];
    }


    @Override
    public int getAllomanticAmount(MetalsNBTData metal){
        return this.allomantic_reseve[metal.getIndex()];
    }


}
