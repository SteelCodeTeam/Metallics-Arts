package net.rudahee.metallics_arts.modules.data_player;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
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
    private final int[] death_pos;
    private final int[] spawn_pos;
    private final boolean[] metal_mind_equiped;
    private String death_dimension;
    private String spawn_dimension;
    private boolean external_enhanced;
    private final ArrayList<MetalsNBTData> list_external_enhanced_drain;
    private final ArrayList<MetalsNBTData> list_duralumin_drain;


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

        this.external_enhanced = false;

        list_duralumin_drain = new ArrayList<>();
        list_external_enhanced_drain = new ArrayList<>();

    }


    @Override
    public void tickAllomancyBurningMetals(ServerPlayer player){
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
    public void clearListDuraluminDrain() {
        this.list_duralumin_drain.clear();
    }
    @Override
    public void addListDuraluminDrain(MetalsNBTData metalsNBTData) {
        this.list_duralumin_drain.add(metalsNBTData);
    }
    @Override
    public ArrayList<MetalsNBTData> getListDuraluminDrain() {
        return this.list_duralumin_drain;
    }
    @Override
    public boolean containsInListDuraluminDrain(MetalsNBTData metalsNBTData) {
        return this.list_duralumin_drain.contains(metalsNBTData);
    }

    @Override
    public void clearListExternalEnhancedDrain() {
        this.list_external_enhanced_drain.clear();
    }

    @Override
    public void addListExternalEnhancedDrain(MetalsNBTData metalsNBTData) {
        this.list_external_enhanced_drain.add(metalsNBTData);
    }

    @Override
    public ArrayList<MetalsNBTData> getListExternalEnhancedDrain() {
        return this.list_external_enhanced_drain;
    }

    @Override
    public boolean containsInListExternalEnhancedDrain(MetalsNBTData metalsNBTData) {
        return this.list_external_enhanced_drain.contains(metalsNBTData);
    }

    @Override
    public void tickFeruchemyStorageMetals(ServerPlayer player){

    }

    @Override
    public void tickFeruchemyDecantMetals(ServerPlayer player){

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
        for (MetalsNBTData metal : metals) {
            setAllomanticMetalsAmount(metal, 0);
            setBurning(metal, false);
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
    public boolean getExternalEnhanced() {
        return this.external_enhanced;
    }

    @Override
    public void setExternalEnhanced(boolean isExternalEnhanced) {
        this.external_enhanced = isExternalEnhanced;
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
            if (metal!=MetalsNBTData.ALUMINUM && metal!=MetalsNBTData.LERASIUM && metal!=MetalsNBTData.NICROSIL && metal!=MetalsNBTData.MALATIUM && metal!=MetalsNBTData.COPPER && metal!=MetalsNBTData.ETTMETAL && metal!=MetalsNBTData.ZINC){
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
            if (metal!=MetalsNBTData.ALUMINUM && metal!=MetalsNBTData.LERASIUM && metal!=MetalsNBTData.NICROSIL && metal!=MetalsNBTData.MALATIUM && metal!=MetalsNBTData.COPPER && metal!=MetalsNBTData.ETTMETAL && metal!=MetalsNBTData.ZINC){
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
    public void setAllomanticMetalsAmount(MetalsNBTData metal, int qty){
        int value = this.allomantic_reseve[metal.getIndex()];
        value =+ qty;
        if (this.max_burning_time[metal.getIndex()]<value)
            value = this.max_burning_time[metal.getIndex()];
        this.allomantic_reseve[metal.getIndex()]=value;
    }

    public boolean addAllomanticMetalAmount(MetalsNBTData metal, int qty) {
        int value = this.allomantic_reseve[metal.getIndex()];
        if (this.max_burning_time[metal.getIndex()] < value + qty) {
            this.allomantic_reseve[metal.getIndex()] = this.max_burning_time[metal.getIndex()];
            return false;
        } else {
            this.allomantic_reseve[metal.getIndex()] = value + qty;
            return true;
        }
    }
    public boolean substractAllomanticMetalAmount(MetalsNBTData metal, int qty) {
        int value = this.allomantic_reseve[metal.getIndex()];
        if (value - qty < 0) {
            this.allomantic_reseve[metal.getIndex()] = 0;
            return false;
        } else {
            this.allomantic_reseve[metal.getIndex()] = value - qty;
            return true;
        }
    }

    @Override
    public int getAllomanticAmount(MetalsNBTData metal){
        return this.allomantic_reseve[metal.getIndex()];
    }

    @Override
    public CompoundTag save() {

        CompoundTag invested_data = new CompoundTag();
        CompoundTag allomantic_powers = new CompoundTag();
        CompoundTag feruchemic_powers = new CompoundTag();
        CompoundTag allomantic_reserve = new CompoundTag();
        CompoundTag burning_metals = new CompoundTag();
        CompoundTag decanting_metals = new CompoundTag();
        CompoundTag storing_metals = new CompoundTag();
        CompoundTag death_pos = new CompoundTag();
        CompoundTag spawn_pos = new CompoundTag();
        CompoundTag spawn_dimension = new CompoundTag();
        CompoundTag death_dimension = new CompoundTag();
        CompoundTag metal_mind_equiped = new CompoundTag();

        for (MetalsNBTData metal : MetalsNBTData.values()) {
            allomantic_powers.putBoolean(metal.getNameLower(), this.hasAllomanticPower(metal));
            feruchemic_powers.putBoolean(metal.getNameLower(), this.hasFeruchemicPower(metal));
            allomantic_reserve.putInt(metal.getNameLower(), this.getAllomanticAmount(metal));
            burning_metals.putBoolean(metal.getNameLower(), this.isBurning(metal));
            decanting_metals.putBoolean(metal.getNameLower(), this.isDecanting(metal));
            storing_metals.putBoolean(metal.getNameLower(), this.isStoring(metal));
        }

        invested_data.put("allomantic_powers", allomantic_powers);
        invested_data.put("feruchemic_powers", feruchemic_powers);
        invested_data.put("allomantic_reserve", allomantic_reserve);
        invested_data.put("burning_metals", burning_metals);

        invested_data.putBoolean("invested",this.isInvested());
        invested_data.putBoolean("mistborn",this.isMistborn());
        invested_data.putBoolean("fullFeruchemic",this.isFullFeruchemic());
        invested_data.putBoolean("fullInvested",this.isFullInvested());

        death_pos.putIntArray("death_position",this.getDeathPos());

        try {
            if (this.getDeathDimension() != null) {
                death_dimension.putString("death_dim",this.getDeathDimension());
            } else {
                death_dimension.putString("death_dim", this.getSpawnDimension());
            }
        } catch (Exception ex) {
            System.out.println("TREMENDO PELOTUDO TOBIAS.");
        }

        spawn_pos.putIntArray("spawn_position", this.getSpawnPos());
        spawn_dimension.putString("spawn_dim",this.getSpawnDimension());

        invested_data.put("death_pos", death_pos);
        invested_data.put("death_dim", death_dimension);

        invested_data.put("spawn_pos", spawn_pos);
        invested_data.put("spawn_dim", spawn_dimension);

        for (int i=0;i<10;i++){
            metal_mind_equiped.putBoolean("group"+i,this.getMetalMindEquiped(i));
        }

        invested_data.put("metal_mind_equiped",metal_mind_equiped);

        invested_data.put("decanting_metals", decanting_metals);
        invested_data.put("storing_metals", storing_metals);

        invested_data.putBoolean("external_enhanced", this.getExternalEnhanced());

        return invested_data;
    }

    @Override
    public void load(CompoundTag nbt) {
        CompoundTag invested_data = nbt;

        this.setExternalEnhanced(invested_data.getBoolean("external_enhanced"));

        CompoundTag allomantic_powers = (CompoundTag) invested_data.get("allomantic_powers");
        CompoundTag feruchemic_powers = (CompoundTag) invested_data.get("feruchemic_powers");
        CompoundTag allomantic_reserve = (CompoundTag) invested_data.get("allomantic_reserve");
        CompoundTag burning_metals = (CompoundTag) invested_data.get("burning_metals");
        CompoundTag storing_metals = (CompoundTag) invested_data.get("storing_metals");
        CompoundTag decanting_metals = (CompoundTag) invested_data.get("decanting_metals");

        CompoundTag death_pos = (CompoundTag) invested_data.get("death_pos");
        CompoundTag spawn_pos = (CompoundTag) invested_data.get("spawn_pos");
        CompoundTag death_dimension = (CompoundTag) invested_data.get("death_dim");
        CompoundTag spawn_dimension = (CompoundTag) invested_data.get("spawn_dim");

        CompoundTag metal_mind_equiped = (CompoundTag) invested_data.get("metal_mind_equiped");

        for (MetalsNBTData metal : MetalsNBTData.values()) {
            if (allomantic_powers.getBoolean(metal.getNameLower())) {
                this.addAllomanticPower(metal);
            } else {
                this.removeAllomanticPower(metal);
            }

            if (feruchemic_powers.getBoolean(metal.getNameLower())) {
                this.addFeruchemicPower(metal);
            } else {
                this.removeFeruchemicPower(metal);
            }

            this.setDecanting(metal, decanting_metals.getBoolean(metal.getNameLower()));
            this.setStoring(metal, storing_metals.getBoolean(metal.getNameLower()));

            if (this.hasAllomanticPower(metal)){
                this.setAllomanticMetalsAmount(metal,allomantic_reserve.getInt(metal.getNameLower()));
                this.setBurning(metal,burning_metals.getBoolean(metal.getNameLower()));
            }
        }

        for (int i=0;i<10;i++){
            this.setMetalMindEquiped(i,metal_mind_equiped.getBoolean("group"+i));
        }

        try {
            if (death_pos.getIntArray("death_position") != null && death_dimension.getString("death_dim") != null) {
                this.setDeathPos(death_pos.getIntArray("death_position"));
                this.setDeathDimension(death_dimension.getString("death_dim"));
            }
            if (spawn_pos.getIntArray("spawn_position") != null && spawn_dimension.getString("spawn_dim") != null) {
                this.setSpawnPos(spawn_pos.getIntArray("spawn_position"));
                this.setSpawnDimension(spawn_dimension.getString("spawn_dim"));
            }
        } catch(Exception ex) {
            System.out.println("SIGUE SIENDO UNA COSTRA DE NULL EL DEATH O SPAWN POS :D");
        }
    }


}
