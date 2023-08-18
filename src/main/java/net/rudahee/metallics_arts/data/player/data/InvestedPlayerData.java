package net.rudahee.metallics_arts.data.player.data;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.rudahee.metallics_arts.data.enums.implementations.EttmetalState;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.setup.network.ModNetwork;
import net.rudahee.metallics_arts.utils.CapabilityUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class control all data from invested player in the game. Use IInvestedPlayerData interface
 * to control the methods that must exist.
 * <p>
 * This method has a lot of structures that contain different data necessary for the mod to work.
 * This class is one of the pillars of the mod, so if you are going to modify the mod, you must understand how it works.
 * <p><p>
 * If you are going to create new data to store on the user, you need to create the private property,
 * the getter, the setter, and modify the load and save methods to handle that data across the client and server.
 * <p>
 * In addition, you must include it in the interface. Apart from this, you can create your own methods to handle that data.
 * You must also include these methods in the interface.
 *
 * @author SteelCode Team
 * @since 1.5.1
 *
 * @see InvestedPlayerData
 * @see MetalTagEnum
 * @see Tag
 */
public class InvestedPlayerData implements IInvestedPlayerData {

    private HashMap<MetalTagEnum, Boolean> allomanticPowers;
    private HashMap<MetalTagEnum, Boolean> feruchemicPowers;
    private final HashMap<MetalTagEnum, Integer> allomanticReserve;
    private final HashMap<MetalTagEnum, Boolean> burningMetals;
    private final HashMap<MetalTagEnum, Boolean> tappingMetals;
    private final HashMap<MetalTagEnum, Boolean> storingMetals;
    private Boolean invested;
    private Boolean mistborn;
    private Boolean fullFeruchemist;
    private Boolean fullInvested;
    private ArrayList<Boolean> metalMindEquipped = new ArrayList<>(10);
    private Boolean enhanced;
    private final ArrayList<MetalTagEnum> metalsEnhanced = new ArrayList<>();
    private Boolean modifiedHealth;

    private EttmetalState ettmetalState;

    /**
     * Default constructor.These will be the initial values that a player will have in their tags.
     *
     * @see Tag
     */
    public InvestedPlayerData() {

        this.invested = false;
        this.mistborn = false;
        this.fullFeruchemist = false;
        this.fullInvested = false;
        this.enhanced = false;
        this.modifiedHealth = false;

        this.allomanticPowers = new CapabilityUtils<Boolean>().fillMetalTagMap(false);
        this.feruchemicPowers = new CapabilityUtils<Boolean>().fillMetalTagMap(false);

        this.tappingMetals = new CapabilityUtils<Boolean>().fillMetalTagMap(false);
        this.storingMetals = new CapabilityUtils<Boolean>().fillMetalTagMap(false);
        this.burningMetals = new CapabilityUtils<Boolean>().fillMetalTagMap(false);

        this.allomanticReserve = new CapabilityUtils<Integer>().fillMetalTagMap(0);

        this.metalMindEquipped = new CapabilityUtils<Boolean>().fillListWithDefaultValue(false, 10);

        this.ettmetalState = EttmetalState.NOTHING;
    }

    /**
     * This method must be called every tick.
     * Check if any metal is burning and subtract the required amount of metal each tick.
     *
     * @param player player to check nbt.
     *
     * @see ServerPlayer
     */
    @Override
    public void tickAllomancyBurningMetals(Player player) {
        boolean readyToSync = false;

        for (MetalTagEnum metal: MetalTagEnum.values()) {

            if (this.isBurning(metal)) {
                if (!this.hasAllomanticPower(metal)) {
                    this.setBurning(metal, false);
                }
                else {
                    this.setAllomanticMetalsAmount(metal, this.getAllomanticAmount(metal) - 1);
                    if (this.getAllomanticAmount(metal) <= 0) {
                        this.setBurning(metal, false);
                    }
                }
                readyToSync = true;
            }
        }

        if (readyToSync) {
            ModNetwork.syncInvestedDataPacket(this, player);
        }
    }

    /**
     * Check if the player is burning any metal to obtain the power.
     *
     * @return boolean
     */
    @Override
    public boolean isBurningAnything() {
        return this.burningMetals.values().stream().anyMatch(isBurning -> true);
    }

    /**
     * Check if the player is tapping any metal to use the power.
     *
     * @return boolean
     */
    @Override
    public boolean isTappingAnything() {
        return this.tappingMetals.values().stream().anyMatch(isTapping -> true);
    }

    /**
     * Check if the player is storing any metal to save the power.
     *
     * @return boolean
     */
    @Override
    public boolean isStoringAnything() {
        return this.storingMetals.values().stream().anyMatch(isStoring -> true);
    }

    /**
     * Clear the list of the metals enhanced by nicrosil or duralumin.
     */
    @Override
    public void clearMetalsEnhanced() {
        this.metalsEnhanced.clear();
    }

    /**
     * Add one metal to the list of enhanced metals by nicrosil or duralumin powers.
     *
     * @param metal that was being added to the list.
     */
    @Override
    public void addMetalsEnhanced(MetalTagEnum metal) {
        this.metalsEnhanced.add(metal);
    }

    /**
     * Return the list of enhanced metals by nicrosil or duralumin powers.
     *
     * @return ArrayList<MetalTagEnum>
     */
    @Override
    public ArrayList<MetalTagEnum> getMetalsEnhanced() {
        return this.metalsEnhanced;
    }

    /**
     * Check if a metal exists in the list in the list of metals enhanced by nicrosil or duralumin powers.
     *
     * @param metal for check in the list.
     *
     * @return boolean
     */
    @Override
    public boolean containsMetalsEnhanced(MetalTagEnum metal) {
        return this.metalsEnhanced.contains(metal);
    }

    /**
     * Check if the player has specific allomantic powers bound to specific metal.
     *
     * @param metal for check the power.
     *
     * @return boolean
     */
    @Override
    public boolean hasAllomanticPower(MetalTagEnum metal) {
        return this.allomanticPowers.get(metal);
    }

    /**
     * Check if the player has specific feruchemic powers bound to specific metal.
     *
     * @param metal for check the power.
     *
     * @return boolean
     */
    @Override
    public boolean hasFeruchemicPower(MetalTagEnum metal) {
        return this.feruchemicPowers.get(metal);
    }

    /**
     * Counts the allomantic powers a player has.
     *
     * @return int
     */
    @Override
    public int getAllomanticPowerCount() {
        return (int) this.allomanticPowers.values().stream().filter(power -> power).count();
    }

    /**
     * Counts the feruchemic powers a player has.
     *
     * @return int
     */
    @Override
    public int getFeruchemicPowerCount() {
        return (int) this.feruchemicPowers.values().stream().filter(power -> power).count();
    }

    /**
     * Check if it has at least some allomantic power.
     *
     * @return boolean
     */
    @Override
    public boolean hasAnyAllomanticPower() {
        return (getAllomanticPowerCount()) != 0;
    }

    /**
     * Check if it has at least some feruchemic power.
     *
     * @return boolean
     */
    @Override
    public boolean hasAnyFeruchemicPower() {
        return (getFeruchemicPowerCount()) != 0;
    }

    /**
     * returns all allomantic powers linked to metals in a ArrayList.
     *
     * @return ArrayList<MetalTagEnum>
     */
    @Override
    public ArrayList<MetalTagEnum> getAllomanticPowers() {
        ArrayList<MetalTagEnum> powers = new ArrayList<>();
        for(MetalTagEnum metals: MetalTagEnum.values()) {
            if (hasAllomanticPower(metals))
                powers.add(metals);
        }
        return powers;
    }

    /**
     * Returns all feruchemic powers linked to metals in a ArrayList.
     *
     * @return ArrayList<MetalTagEnum>
     */
    @Override
    public ArrayList<MetalTagEnum> getFeruchemicPowers() {
        ArrayList<MetalTagEnum> powers = new ArrayList<>();
        for(MetalTagEnum metals: MetalTagEnum.values()) {
            if (hasFeruchemicPower(metals))
                powers.add(metals);
        }
        return powers;
    }

    /**
     * Makes the amount of metal of a power to 0. This method can receive any amount of MetalTagEnum.
     *
     * @param metals any numbers of metals to drain separated by comma.
     */
    @Override
    public void drainMetals(MetalTagEnum... metals) {
        for (MetalTagEnum metal : metals) {
            setAllomanticMetalsAmount(metal, 0);
            setBurning(metal, false);
        }
    }

    /**
     * Check if a player have a Metal Mind equipped.
     *
     * @param group of the Metal Mind that was being checked.
     *
     * @return boolean
     */
    @Override
    public boolean hasMetalMindEquiped(int group) {
        return this.metalMindEquipped.get(group);
    }

    /**
     * It is defined if a metal mind is equipped or not, from a group.
     *
     * @param group of the Metal Mind that was being checked.
     * @param value to define if it is equipped or not.
     */
    @Override
    public void setMetalMindEquiped(int group, boolean value) {
        this.metalMindEquipped.set(group, value);
    }

    /**
     * Obtain all Metal Minds equipped by a player.
     *
     * @return ArrayList<Boolean>
     */
    @Override
    public ArrayList<Boolean> getMetalMindEquipedList() {
        return this.metalMindEquipped;
    }

    /**
     * It is defined if a list of metal mind is equipped,
     *
     * @param list of the Metal Mind that was being set.
     */
    @Override
    public void setMetalMindEquipedList(ArrayList<Boolean> list) {
        this.metalMindEquipped = list;
    }

    /**
     * Check if the player is enhanced
     *
     * @return boolean
     */
    @Override
    public boolean getEnhanced() {
        return this.enhanced;
    }

    /**
     * Set the player if enhanced of not.
     *
     * @param isEnhanced boolean to define if it's enhanced.
     */
    @Override
    public void setEnhanced(boolean isEnhanced) {
        this.enhanced = isEnhanced;
    }

    /**
     * Check if the player don't have the standard health
     *
     * @return boolean
     */
    @Override
    public boolean hasModifiedHealth() {
        return this.modifiedHealth;
    }

    /**
     * Set the player if it has a modified quantity of health.
     *
     * @param modifiedHealth boolean to define if it's modified.
     */
    @Override
    public void setModifiedHealth(boolean modifiedHealth) {
        this.modifiedHealth = modifiedHealth;
    }

    /**
     * Defines whether the player is Mistborn or not.
     *
     * @param mistborn boolean to define if it's full mistborn.
     */
    @Override
    public void setMistborn(boolean mistborn) {
        this.mistborn = mistborn;
    }

    /**
     * Defines whether the player is full feruchemist or not.
     *
     * @param feruchemist boolean to define if it's full feruchemist.
     */
    @Override
    public void setFullFeruchemist(boolean feruchemist) {
        this.fullFeruchemist = feruchemist;
    }

    /**
     * Defines whether the player is full mistborn AND full feruchemist or not.
     *
     * @param invested boolean to define if it's full mistborn and full feruchemist.
     */
    @Override
    public void setFullInvested(boolean invested) {
        this.fullInvested = invested;
    }

    /**
     * Check if the player is mistborn.
     *
     * @return boolean.
     */
    @Override
    public boolean isMistborn() {
        return this.mistborn;
    }

    /**
     * Check if the player is complete feruchemist.
     *
     * @return boolean.
     */
    @Override
    public boolean isFullFeruchemist() {
        return this.fullFeruchemist;
    }

    /**
     * Check if the player is complete feruchemist and complete mistborn.
     *
     * @return boolean.
     */
    @Override
    public boolean isFullInvested() {
        return this.fullInvested;
    }

    /**
     * Check if the player has had any power at some point, so, it's invested.
     *
     * @return boolean.
     */
    @Override
    public boolean isInvested() {
        return this.invested;
    }

    /**
     * Defines the value of whether the player is invested or not.
     *
     * @param invested value of the player invested.
     */
    @Override
    public void setInvested(boolean invested) {
        this.invested = invested;
    }

    /**
     * Defines the player as uninvested.
     */
    @Override
    public void setUninvested() {
        this.invested = false;
    }

    /**
     * Adds an allomantic power to the player.
     *
     * @param metal of the power.
     */
    @Override
    public void addAllomanticPower(MetalTagEnum metal) {
        this.allomanticPowers.put(metal, true);
        this.invested = true;
    }

    /**
     * Adds a feruchemic power to the player.
     *
     * @param metal of the power.
     */
    @Override
    public void addFeruchemicPower(MetalTagEnum metal) {
        this.feruchemicPowers.put(metal, true);
        this.invested = true;
    }

    /**
     * Adds all allomantic powers to the player.
     */
    @Override
    public void addAllAllomantic() {
        this.allomanticPowers = new CapabilityUtils<Boolean>().fillMetalTagMap(true);
        this.mistborn = true;
        this.invested = true;
    }

    /**
     * Adds all feruchemic powers to the player.
     */
    @Override
    public void addAllFeruchemic() {
        this.feruchemicPowers = new CapabilityUtils<Boolean>().fillMetalTagMap(true);
        this.fullFeruchemist = true;
        this.invested = true;
    }

    /**
     * Remove an allomantic power to the player.
     *
     * @param metal of the power.
     */
    @Override
    public void removeAllomanticPower(MetalTagEnum metal) {
        this.allomanticPowers.put(metal, false);
    }

    /**
     * Remove all allomantic powers to the player.
     */
    @Override
    public void removeAllAllomanticPower() {
        this.allomanticPowers = new CapabilityUtils<Boolean>().fillMetalTagMap(false);
        this.mistborn = false;
    }

    /**
     * Remove a feruchemic power to the player.
     *
     * @param metal of the power.
     */
    @Override
    public void removeFeruchemicPower(MetalTagEnum metal) {
        this.feruchemicPowers.put(metal, false);
    }

    /**
     * Remove all feruchemic powers to the player.
     */
    @Override
    public void removeAllFeruchemicPower() {
        this.feruchemicPowers = new CapabilityUtils<Boolean>().fillMetalTagMap(false);
        this.fullFeruchemist = false;
    }

    /**
     * Check if the player is tapping a specific metal.
     *
     * @param metal of the power.
     * @return boolean
     */
    @Override
    public boolean isTapping(MetalTagEnum metal) {
        return this.tappingMetals.get(metal);
    }

    /**
     * Check if the player is storing a specific metal.
     *
     * @param metal of the power.
     * @return boolean
     */
    @Override
    public boolean isStoring(MetalTagEnum metal) {
        return this.storingMetals.get(metal);
    }

    /**
     * Check if the player is burning a specific metal.
     *
     * @param metal of the power.
     * @return boolean
     */
    @Override
    public boolean isBurning(MetalTagEnum metal) {
        return this.burningMetals.get(metal);
    }

    /**
     * Check if the player is using any power.
     *
     * @return boolean
     */
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

    /**
     * Defines whether the player is tapping a metal
     *
     * @param metal to be modified.
     * @param value that defines if it is tapping or not.
     */
    @Override
    public void setTapping(MetalTagEnum metal, boolean value) {
        this.tappingMetals.put(metal, value);
    }

    /**
     * Defines whether the player is storing a metal
     *
     * @param metal to be modified.
     * @param value that defines if it is storing or not.
     */
    @Override
    public void setStoring(MetalTagEnum metal, boolean value) {
        this.storingMetals.put(metal, value);
    }

    /**
     * Defines whether the player is burning a metal
     *
     * @param metal to be modified.
     * @param value that defines if it is burning or not.
     */
    @Override
    public void setBurning(MetalTagEnum metal, boolean value) {
        this.burningMetals.put(metal, value);
    }

    /**
     * Defines whether the player's metal reserve.
     *
     * @param metal to be modified.
     * @param qty that defines quantity that's being set.
     */
    @Override
    public void setAllomanticMetalsAmount(MetalTagEnum metal, int qty) {

        if (metal.getMaxAllomanticTicksStorage()<qty) {
            qty = metal.getMaxAllomanticTicksStorage();
        }

        this.allomanticReserve.put(metal, qty);
    }

    /**
     * Add whether the player's metal reserve.
     *
     * @param metal to be modified.
     * @param qty that defines quantity that's being added.
     *
     * @return boolean
     */
    @Override
    public boolean addAllomanticMetalAmount(MetalTagEnum metal, int qty) {
        int value = this.allomanticReserve.get(metal);

        if (metal.getMaxAllomanticTicksStorage() < value + qty) {
            this.allomanticReserve.put(metal, metal.getMaxAllomanticTicksStorage());
            return false;
        } else {
            this.allomanticReserve.put(metal, value + qty);
            return true;
        }
    }

    /**
     * Subtract whether the player's metal reserve.
     *
     * @param metal to be modified.
     * @param qty that defines quantity that's being subtracted.
     *
     * @return boolean
     */
    @Override
    public boolean substractAllomanticMetalAmount(MetalTagEnum metal, int qty) {
        int value = this.allomanticReserve.get(metal);
        if (value - qty < 0) {
            this.allomanticReserve.put(metal, 0);
            return false;
        } else {
            this.allomanticReserve.put(metal, value - qty);
            return true;
        }
    }

    /**
     * Check the player's metal reserve.
     *
     * @param metal to check reserve
     *
     * @return boolean
     */
    @Override
    public int getAllomanticAmount(MetalTagEnum metal) {
        return this.allomanticReserve.get(metal);
    }

    @Override
    public void setEttmetalState(EttmetalState state) {
        this.ettmetalState = state;
    }

    @Override
    public EttmetalState getEttmetalState() {
        return this.ettmetalState;
    }


    /**
     * Save the actual data in the CompoundTag
     *
     * @return CompoundTag
     */
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
        CompoundTag metalMindEquipped = new CompoundTag();


        CompoundTag modified_health = new CompoundTag();

        for (MetalTagEnum metal : MetalTagEnum.values()) {
            allomanticPowers.putBoolean(metal.getNameLower(), this.hasAllomanticPower(metal));
            feruchemicPowers.putBoolean(metal.getNameLower(), this.hasFeruchemicPower(metal));
            allomantic_reserve.putInt(metal.getNameLower(), this.getAllomanticAmount(metal));
            burningMetals.putBoolean(metal.getNameLower(), this.isBurning(metal));
            tappingMetals.putBoolean(metal.getNameLower(), this.isTapping(metal));
            storingMetals.putBoolean(metal.getNameLower(), this.isStoring(metal));
        }

        modified_health.putBoolean("modified_health", this.modifiedHealth);

        investedData.put("allomantic_powers", allomanticPowers);
        investedData.put("feruchemic_powers", feruchemicPowers);
        investedData.put("allomantic_reserve", allomantic_reserve);
        investedData.put("burning_metals", burningMetals);

        investedData.putBoolean("invested",this.isInvested());
        investedData.putBoolean("mistborn",this.isMistborn());
        investedData.putBoolean("full_feruchemic",this.isFullFeruchemist());
        investedData.putBoolean("full_invested",this.isFullInvested());

        investedData.put("modified_health", modified_health);

        for (int i = 0; i < 10; i++) {
            metalMindEquipped.putBoolean("group" + i, this.hasMetalMindEquiped(i));
        }

        investedData.put("metal_mind_equipped", metalMindEquipped);

        investedData.put("tapping_metals", tappingMetals);
        investedData.put("storing_metals", storingMetals);

        investedData.putBoolean("is_enhanced", this.getEnhanced());

        return investedData;
    }

    /**
     * Load the actual data in the past by parameter tag.
     *
     * @param nbt tag to save data.
     */
    @Override
    public void load(CompoundTag nbt) {
        CompoundTag investedData;
        investedData = nbt;

        this.setEnhanced(investedData.getBoolean("is_enhanced"));

        CompoundTag allomanticPowers = (CompoundTag) investedData.get("allomantic_powers");
        CompoundTag feruchemicPowers = (CompoundTag) investedData.get("feruchemic_powers");
        CompoundTag allomantic_reserve = (CompoundTag) investedData.get("allomantic_reserve");
        CompoundTag burningMetals = (CompoundTag) investedData.get("burning_metals");
        CompoundTag storingMetals = (CompoundTag) investedData.get("storing_metals");
        CompoundTag tappingMetals = (CompoundTag) investedData.get("tapping_metals");

        CompoundTag modified_health = (CompoundTag) investedData.get("modified_health");

        CompoundTag metalMindEquipped = (CompoundTag) investedData.get("metal_mind_equipped");

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

            if (this.hasAllomanticPower(metal)) {
                this.setAllomanticMetalsAmount(metal,allomantic_reserve.getInt(metal.getNameLower()));
                this.setBurning(metal,burningMetals.getBoolean(metal.getNameLower()));
            }
        }

        this.setModifiedHealth(modified_health.getBoolean("modified_health"));

        this.setInvested(investedData.getBoolean("invested"));
        this.setMistborn(investedData.getBoolean("mistborn"));
        this.setFullFeruchemist(investedData.getBoolean("full_feruchemic"));
        this.setFullInvested(investedData.getBoolean("full_invested"));

        for (int i = 0; i < 10; i++) {
            this.setMetalMindEquiped(i,metalMindEquipped.getBoolean("group" + i));
        }

    }
}
