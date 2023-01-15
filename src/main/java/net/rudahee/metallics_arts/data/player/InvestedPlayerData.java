package net.rudahee.metallics_arts.data.player;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.setup.network.ModNetwork;
import net.rudahee.metallics_arts.utils.CapabilityUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class InvestedPlayerData implements IInvestedPlayerData {
    private HashMap<MetalTagEnum, Boolean> allomanticPowers;
    private HashMap<MetalTagEnum, Boolean> feruchemicPowers;
    private HashMap<MetalTagEnum, Integer> allomanticReseve;
    private HashMap<MetalTagEnum, Integer> lerasiumReserve;
    private HashMap<MetalTagEnum, Boolean> burningMetals;
    private HashMap<MetalTagEnum, Boolean> tappingMetals;
    private HashMap<MetalTagEnum, Boolean> storingMetals;
    private Boolean invested;
    private Boolean mistborn;
    private Boolean fullFeruchemic;
    private Boolean fullInvested;
    private ArrayList<Boolean> metalMindEquiped = new ArrayList<>(10);
    private Boolean enhanced;
    private ArrayList<MetalTagEnum> metalsEnhanced = new ArrayList<>();
    private Boolean modifiedHealth;


    public InvestedPlayerData() {

        this.invested = false;
        this.mistborn = false;
        this.fullFeruchemic = false;
        this.fullInvested = false;
        this.enhanced = false;
        this.modifiedHealth = false;

        this.allomanticPowers = new CapabilityUtils<Boolean>().fillMetalTagMap(false);
        this.feruchemicPowers = new CapabilityUtils<Boolean>().fillMetalTagMap(false);

        this.tappingMetals = new CapabilityUtils<Boolean>().fillMetalTagMap(false);
        this.storingMetals = new CapabilityUtils<Boolean>().fillMetalTagMap(false);
        this.burningMetals = new CapabilityUtils<Boolean>().fillMetalTagMap(false);

        this.allomanticReseve = new CapabilityUtils<Integer>().fillMetalTagMap(0);
        this.lerasiumReserve = new CapabilityUtils<Integer>().fillMetalTagMap(0);

        this.metalMindEquiped = new CapabilityUtils<Boolean>().fillListWithDefaultValue(false, 10);

    }


    @Override
    public void tickAllomancyBurningMetals(ServerPlayer player) {
        boolean readyToSync = false;

        for (MetalTagEnum metal: MetalTagEnum.values()) {

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
            }
        }

        if (readyToSync) {
            ModNetwork.sync(this, player);
        }
    }

    @Override
    public boolean isBurningAnything() {
        return this.burningMetals.values().stream().anyMatch(isBurning -> true);
    }

    @Override
    public boolean isTappingAnything() {
        return this.tappingMetals.values().stream().anyMatch(isTapping -> true);
    }

    @Override
    public boolean isStoringAnything() {
        return this.storingMetals.values().stream().anyMatch(isStoring -> true);
    }

    @Override
    public void clearMetalsEnhanced() {
        this.metalsEnhanced.clear();
    }
    @Override
    public void addMetalsEnhanced(MetalTagEnum metalTagEnum) {
        this.metalsEnhanced.add(metalTagEnum);
    }
    @Override
    public ArrayList<MetalTagEnum> getMetalsEnhanced() {
        return this.metalsEnhanced;
    }
    @Override
    public boolean containsMetalsEnhanced(MetalTagEnum metalTagEnum) {
        return this.metalsEnhanced.contains(metalTagEnum);
    }

    @Override
    public boolean hasAllomanticPower(MetalTagEnum metal) {
        return this.allomanticPowers.get(metal);
    }

    @Override
    public boolean hasFeruchemicPower(MetalTagEnum metal) {
        return this.feruchemicPowers.get(metal);
    }

    @Override
    public int getAllomanticPowerCount(){
        return (int) this.allomanticPowers.values().stream().filter(power -> power).count();
    }
    @Override
    public int getFeruchemicPowerCount(){
        return (int) this.feruchemicPowers.values().stream().filter(power -> power).count();
    }

    @Override
    public ArrayList<MetalTagEnum> getAllomanticPowers() {
        ArrayList<MetalTagEnum> powers = new ArrayList<>();
        for(MetalTagEnum metals: MetalTagEnum.values()){
            if (hasAllomanticPower(metals))
                powers.add(metals);
        }
        return powers;
    }

    @Override
    public ArrayList<MetalTagEnum> getFeruchemicPowers() {
        ArrayList<MetalTagEnum> powers = new ArrayList<>();
        for(MetalTagEnum metals: MetalTagEnum.values()){
            if (hasFeruchemicPower(metals))
                powers.add(metals);
        }
        return powers;
    }

    @Override
    public void drainMetals(MetalTagEnum... metals) {
        for (MetalTagEnum metal : metals) {
            setAllomanticMetalsAmount(metal, 0);
            setBurning(metal, false);
        }
    }

    @Override
    public boolean getMetalMindEquiped(int group) {
        return this.metalMindEquiped.get(group);
    }

    @Override
    public void setMetalMindEquiped(int group, boolean value) {
        this.metalMindEquiped.set(group, value);
    }

    @Override
    public ArrayList<Boolean> getMetalMindEquipedList() {
        return this.metalMindEquiped;
    }

    @Override
    public void setMetalMindEquipedList(ArrayList<Boolean> list) {
        this.metalMindEquiped = list;
    }

    @Override
    public boolean getEnhanced() {
        return this.enhanced;
    }

    @Override
    public void setEnhanced(boolean isEnhanced) {
        this.enhanced = isEnhanced;
    }

    @Override
    public boolean hasModifiedHealth() {
        return this.modifiedHealth;
    }

    @Override
    public void setModifiedHealth(boolean modified) {
        this.modifiedHealth = modified;
    }

    @Override
    public void setMistborn(boolean mistborn) {
        this.mistborn = mistborn;
    }

    @Override
    public void setFullFeruchemic(boolean feruchemic) {
        this.fullFeruchemic = feruchemic;
    }

    @Override
    public void setFullInvested(boolean invested) {
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
    public boolean isInvested() {
        return this.invested;
    }

    @Override
    public void setInvested(boolean invested) {
        this.invested = invested;
    }

    @Override
    public void setUninvested() {
        this.invested = false;
    }

    @Override
    public void addAllomanticPower(MetalTagEnum metal) {
        this.allomanticPowers.put(metal, true);
        this.invested = true;
    }

    @Override
    public void addFeruchemicPower(MetalTagEnum metal) {
        this.feruchemicPowers.put(metal, true);
        this.invested = true;
    }

    @Override
    public void addAllAllomantic() {
        this.allomanticPowers = new CapabilityUtils<Boolean>().fillMetalTagMap(true);
        this.mistborn = true;
        this.invested = true;
    }

    @Override
    public void addAllFeruchemic() {
        this.feruchemicPowers = new CapabilityUtils<Boolean>().fillMetalTagMap(true);
        this.fullFeruchemic = true;
        this.invested = true;
    }

    //REMOVE
    @Override
    public void removeAllomanticPower(MetalTagEnum metal) {
        this.allomanticPowers.put(metal, false);
    }

    @Override
    public void removeAllAllomanticPower() {
        this.allomanticPowers = new CapabilityUtils<Boolean>().fillMetalTagMap(false);
        this.mistborn = false;
    }

    @Override
    public void removeFeruchemicPower(MetalTagEnum metal) {
        this.feruchemicPowers.put(metal, false);
    }

    @Override
    public void removeAllFeruchemicPower() {
        this.feruchemicPowers = new CapabilityUtils<Boolean>().fillMetalTagMap(false);
        this.fullFeruchemic = false;
    }

    @Override
    public boolean isTapping(MetalTagEnum metal) {
        return this.tappingMetals.get(metal);
    }

    @Override
    public boolean isStoring(MetalTagEnum metal) {
        return this.storingMetals.get(metal);
    }

    @Override
    public boolean isBurning(MetalTagEnum metal) {
        return this.burningMetals.get(metal);
    }

    @Override
    public boolean isUsingPowers() {
        return (this.isBurningAnything() || this.isStoringAnything() || this.isTappingAnything());
    }

    @Override
    public int cantMetalsTapping() {
        int cant = 0;
        for (MetalTagEnum metal: MetalTagEnum.values()) {
            if (metal != MetalTagEnum.ALUMINUM && metal != MetalTagEnum.LERASIUM && metal != MetalTagEnum.NICROSIL &&
                    metal != MetalTagEnum.MALATIUM && metal != MetalTagEnum.COPPER && metal != MetalTagEnum.ETTMETAL &&
                    metal != MetalTagEnum.ZINC) {
                if (isTapping(metal)) {
                    cant++;
                }
            }
        }
        return cant;
    }

    @Override
    public int cantMetalsStoring() {
        int cant = 0;
        for (MetalTagEnum metal: MetalTagEnum.values()) {
            if (metal != MetalTagEnum.ALUMINUM && metal != MetalTagEnum.LERASIUM && metal != MetalTagEnum.NICROSIL &&
                    metal != MetalTagEnum.MALATIUM && metal != MetalTagEnum.COPPER && metal != MetalTagEnum.ETTMETAL &&
                    metal != MetalTagEnum.ZINC) {

                if (isStoring(metal)) {
                    cant++;
                }
            }
        }
        return cant;
    }

    @Override
    public void setTapping(MetalTagEnum metal, boolean value) {
        this.tappingMetals.put(metal, value);
    }

    @Override
    public void setStoring(MetalTagEnum metal, boolean value) {
        this.storingMetals.put(metal, value);
    }

    @Override
    public void setBurning(MetalTagEnum metal, boolean value) {
        this.burningMetals.put(metal, value);
    }

    @Override
    public void setAllomanticMetalsAmount(MetalTagEnum metal, int qty) {

        if (metal.getMaxAllomanticTicksStorage()<qty) {
            qty = metal.getMaxAllomanticTicksStorage();
        }

        this.allomanticReseve.put(metal, qty);
    }

    @Override
    public boolean addAllomanticMetalAmount(MetalTagEnum metal, int qty) {
        int value = this.allomanticReseve.get(metal);

        if (metal.getMaxAllomanticTicksStorage() < value + qty) {
            this.allomanticReseve.put(metal, metal.getMaxAllomanticTicksStorage());
            return false;
        } else {
            this.allomanticReseve.put(metal, value + qty);
            return true;
        }
    }

    @Override
    public boolean substractAllomanticMetalAmount(MetalTagEnum metal, int qty) {
        int value = this.allomanticReseve.get(metal);
        if (value - qty < 0) {
            this.allomanticReseve.put(metal, 0);
            return false;
        } else {
            this.allomanticReseve.put(metal, value - qty);
            return true;
        }
    }

    @Override
    public int getAllomanticAmount(MetalTagEnum metal) {
        return this.allomanticReseve.get(metal);
    }

    @Override
    public CompoundTag save() {

        CompoundTag investedData = new CompoundTag();
        CompoundTag allomanticPowers = new CompoundTag();
        CompoundTag feruchemicPowers = new CompoundTag();
        CompoundTag allomantic_reserve = new CompoundTag();
        CompoundTag burningMetals = new CompoundTag();
        CompoundTag tappingMetals = new CompoundTag();
        CompoundTag storingMetals = new CompoundTag();
        CompoundTag deathPos = new CompoundTag();
        CompoundTag spawnPos = new CompoundTag();
        CompoundTag spawnDimension = new CompoundTag();
        CompoundTag deathDimension = new CompoundTag();
        CompoundTag metalMindEquiped = new CompoundTag();

        CompoundTag modified_health = new CompoundTag();

        for (MetalTagEnum metal : MetalTagEnum.values()) {
            allomanticPowers.putBoolean(metal.getNameLower(), this.hasAllomanticPower(metal));
            feruchemicPowers.putBoolean(metal.getNameLower(), this.hasFeruchemicPower(metal));
            allomantic_reserve.putInt(metal.getNameLower(), this.getAllomanticAmount(metal));
            burningMetals.putBoolean(metal.getNameLower(), this.isBurning(metal));
            tappingMetals.putBoolean(metal.getNameLower(), this.isTapping(metal));
            storingMetals.putBoolean(metal.getNameLower(), this.isStoring(metal));
        }

        modified_health.putBoolean("modified_health",this.modifiedHealth);

        investedData.put("allomantic_powers", allomanticPowers);
        investedData.put("feruchemic_powers", feruchemicPowers);
        investedData.put("allomantic_reserve", allomantic_reserve);
        investedData.put("burning_metals", burningMetals);

        investedData.putBoolean("invested",this.isInvested());
        investedData.putBoolean("mistborn",this.isMistborn());
        investedData.putBoolean("full_feruchemic",this.isFullFeruchemic());
        investedData.putBoolean("full_invested",this.isFullInvested());

        investedData.put("modified_health", modified_health);

        for (int i=0;i<10;i++){
            metalMindEquiped.putBoolean("group"+i,this.getMetalMindEquiped(i));
        }

        investedData.put("metal_mind_equiped",metalMindEquiped);

        investedData.put("tapping_metals", tappingMetals);
        investedData.put("storing_metals", storingMetals);

        investedData.putBoolean("is_enhanced", this.getEnhanced());

        return investedData;
    }

    @Override
    public void load(CompoundTag nbt) {
        CompoundTag investedData = nbt;

        this.setEnhanced(investedData.getBoolean("is_enhanced"));

        CompoundTag allomanticPowers = (CompoundTag) investedData.get("allomantic_powers");
        CompoundTag feruchemicPowers = (CompoundTag) investedData.get("feruchemic_powers");
        CompoundTag allomantic_reserve = (CompoundTag) investedData.get("allomantic_reserve");
        CompoundTag burningMetals = (CompoundTag) investedData.get("burning_metals");
        CompoundTag storingMetals = (CompoundTag) investedData.get("storing_metals");
        CompoundTag tappingMetals = (CompoundTag) investedData.get("tapping_metals");

        CompoundTag modified_health = (CompoundTag) investedData.get("modified_health");

        CompoundTag metalMindEquiped = (CompoundTag) investedData.get("metal_mind_equiped");

        for (MetalTagEnum metal : MetalTagEnum.values()) {
            if (allomanticPowers.getBoolean(metal.getNameLower())) {
                this.addAllomanticPower(metal);
            } else {
                this.removeAllomanticPower(metal);
            }

            if (feruchemicPowers.getBoolean(metal.getNameLower())) {
                this.addFeruchemicPower(metal);
            } else {
                this.removeFeruchemicPower(metal);
            }

            this.setTapping(metal, tappingMetals.getBoolean(metal.getNameLower()));
            this.setStoring(metal, storingMetals.getBoolean(metal.getNameLower()));

            if (this.hasAllomanticPower(metal)){
                this.setAllomanticMetalsAmount(metal,allomantic_reserve.getInt(metal.getNameLower()));
                this.setBurning(metal,burningMetals.getBoolean(metal.getNameLower()));
            }
        }

        this.setModifiedHealth(modified_health.getBoolean("modified_health"));

        this.setInvested(investedData.getBoolean("invested"));
        this.setMistborn(investedData.getBoolean("mistborn"));
        this.setFullFeruchemic(investedData.getBoolean("full_feruchemic"));
        this.setFullInvested(investedData.getBoolean("full_invested"));

        for (int i=0;i<10;i++){
            this.setMetalMindEquiped(i,metalMindEquiped.getBoolean("group"+i));
        }

    }
}
