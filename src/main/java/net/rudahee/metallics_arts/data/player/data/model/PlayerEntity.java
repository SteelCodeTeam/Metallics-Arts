package net.rudahee.metallics_arts.data.player.data.model;

import net.rudahee.metallics_arts.data.player.data.model.enums.EttmetalStateEnum;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.player.data.model.enums.TypeOfSpikeEnum;
import net.rudahee.metallics_arts.data.player.data.model.body.*;

import java.util.*;
import java.util.stream.Stream;

/**
 * The PlayerEntity class represents a player entity in a game. It contains information about the player's body parts, metal reserves,
 * metal burning, tapping, and storing status, metal mind equipment, enhancements, modified health, and ettmetal state.
 */
public class PlayerEntity {

    private HeadPartEntity head;
    private ChestPartEntity chest;
    private BackPartEntity back;
    private ArmsPartEntity arms;
    private LegsPartEntity legs;

    private HashMap<MetalTagEnum, Integer> allomanticReserve;
    private HashMap<MetalTagEnum, Boolean> burningMetals;
    private HashMap<MetalTagEnum, Boolean> tappingMetals;
    private HashMap<MetalTagEnum, Boolean> storingMetals;
    private ArrayList<Boolean> metalMindEquipped = new ArrayList<>(10);
    private Boolean enhanced;
    private ArrayList<MetalTagEnum> metalsEnhanced = new ArrayList<>();
    private Boolean modifiedHealth;
    private EttmetalStateEnum ettmetalStateEnum;
    private boolean firstJoin;

    /**
     * This class represents a player entity in the game. It encapsulates various attributes and behaviors of a player character.
     */
    public PlayerEntity() {
        this.head = new HeadPartEntity();
        this.chest = new ChestPartEntity();
        this.back = new BackPartEntity();
        this.arms = new ArmsPartEntity();
        this.legs = new LegsPartEntity();
        this.allomanticReserve = new HashMap<>();
        this.burningMetals = new HashMap<>();
        this.tappingMetals = new HashMap<>();
        this.storingMetals = new HashMap<>();
        this.metalMindEquipped = new ArrayList<>();
        this.enhanced = false;
        this.metalsEnhanced = new ArrayList<>();
        this.modifiedHealth = false;
        this.ettmetalStateEnum = EttmetalStateEnum.NOTHING;
        this.firstJoin = true;
    }

    /**
     * Determines if the player entity is joining the game for the first time.
     *
     * @return True if it is the player's first join, false otherwise.
     */
    public boolean isFirstJoin() {
        return firstJoin;
    }

    /**
     * Sets the first join status of the player entity.
     *
     * @param firstJoin True if it is the player's first join, false otherwise.
     */
    public void setFirstJoin(boolean firstJoin) {
        this.firstJoin = firstJoin;
    }

    /**
     * Checks if the player has any allomantic metal in any body part.
     *
     * @return true if the player has any allomantic metal, false otherwise
     */
    public boolean hasAnyAllomanticMetal() {
        boolean anyAllomanticMetal = Stream.of(this.head, this.chest, this.back, this.arms, this.legs)
                .anyMatch(partOfBody -> partOfBody.getAllSpikes().stream()
                        .anyMatch(spike -> spike.getType().equals(TypeOfSpikeEnum.ALLOMANTIC)));
        return anyAllomanticMetal;
    }

    /**
     * Checks if the player has the specified allomantic metal in any body part.
     *
     * @param metal The metal to check.
     * @return true if the player has the specified allomantic metal, false otherwise.
     */
    public boolean hasAllomanticMetal(MetalTagEnum metal) {
        Optional<BodyPartEntity> bodyPart = Stream.of(this.head, this.chest, this.back, this.arms, this.legs).filter(
                bodyPartEntity -> bodyPartEntity.getAllSpikes().stream().anyMatch(
                        spikeEntity -> spikeEntity.getType().equals(TypeOfSpikeEnum.ALLOMANTIC) && spikeEntity.getMetal() != null && spikeEntity.getMetal().equals(metal))
        ).findAny();

        return bodyPart.isPresent();
    }

    /**
     * Checks if the player has any feruchemic metal in any body part.
     *
     * @return true if the player has any feruchemic metal,*/
    public boolean hasAnyFeruchemicMetal() {
        boolean anyFeruchemicMetal = Stream.of(this.head, this.chest, this.back, this.arms, this.legs)
                .anyMatch(partOfBody -> partOfBody.getAllSpikes().stream()
                        .anyMatch(spike -> spike.getType().equals(TypeOfSpikeEnum.FERUCHEMIC)));
        return anyFeruchemicMetal;
    }

    /**
     * Checks if the player has any feruchemic metal in any body part.
     *
     * @param metal The feruchemic metal to check.
     * @return true if the player has the specified feruchemic metal, false otherwise.
     */
    public boolean hasFeruchemicMetal(MetalTagEnum metal) {
        Optional<BodyPartEntity> bodyPart = Stream.of(this.head, this.chest, this.back, this.arms, this.legs).filter(
                bodyPartEntity -> bodyPartEntity.getAllSpikes().stream().anyMatch(
                        spikeEntity -> spikeEntity.getType().equals(TypeOfSpikeEnum.FERUCHEMIC) && spikeEntity.getMetal() != null && spikeEntity.getMetal().equals(metal))
        ).findAny();

        return bodyPart.isPresent();
    }


    /**
     * Retrieves the count of allomantic metals possessed by the player in all body parts.
     *
     * @return The count of allomantic metals.
     */
    public long getCountAllomanticMetal() {
        return this.head.getAllSpikes().stream().filter(spike -> spike.getType() == TypeOfSpikeEnum.ALLOMANTIC).count()
                + this.chest.getAllSpikes().stream().filter(spike -> spike.getType() == TypeOfSpikeEnum.ALLOMANTIC).count()
                + this.back.getAllSpikes().stream().filter(spike -> spike.getType() == TypeOfSpikeEnum.ALLOMANTIC).count()
                + this.arms.getAllSpikes().stream().filter(spike -> spike.getType() == TypeOfSpikeEnum.ALLOMANTIC).count()
                + this.legs.getAllSpikes().stream().filter(spike -> spike.getType() == TypeOfSpikeEnum.ALLOMANTIC).count();
    }

    /**
     * Retrieves the count of feruchemic metals possessed by the player in all body parts.
     *
     * @return The count of feruchemic metals.
     */
    public long getCountFeruchemicMetal() {
        return this.head.getAllSpikes().stream().filter(spike -> spike.getType() == TypeOfSpikeEnum.FERUCHEMIC).count()
                + this.chest.getAllSpikes().stream().filter(spike -> spike.getType() == TypeOfSpikeEnum.FERUCHEMIC).count()
                + this.back.getAllSpikes().stream().filter(spike -> spike.getType() == TypeOfSpikeEnum.FERUCHEMIC).count()
                + this.arms.getAllSpikes().stream().filter(spike -> spike.getType() == TypeOfSpikeEnum.FERUCHEMIC).count()
                + this.legs.getAllSpikes().stream().filter(spike -> spike.getType() == TypeOfSpikeEnum.FERUCHEMIC).count();
    }

    /**
     * Retrieves the HeadPartEntity object representing the head part of the player entity.
     *
     * @return The HeadPartEntity object representing the head part.
     */
    public HeadPartEntity getHead() {
        return head;
    }

    /**
     * Sets the HeadPartEntity object representing the head part of the player entity.
     *
     * @param head The HeadPartEntity object representing the head part.
     */
    public void setHead(HeadPartEntity head) {
        this.head = head;
    }

    /**
     * Retrieves the ChestPartEntity object representing the chest part of the player entity.
     *
     * @return The ChestPartEntity object representing the chest part.
     */
    public ChestPartEntity getChest() {
        return chest;
    }

    /**
     * Sets the ChestPartEntity object representing the chest part of the player entity.
     *
     * @param chest The ChestPartEntity object representing the chest part.
     */
    public void setChest(ChestPartEntity chest) {
        this.chest = chest;
    }

    /**
     * Retrieves the BackPartEntity object representing the back part of the player entity.
     *
     * @return The BackPartEntity object representing the back part.
     */
    public BackPartEntity getBack() {
        return back;
    }

    /**
     * Sets the BackPartEntity object representing the back part of the player entity.
     *
     * @param back The BackPartEntity object representing the back part.
     */
    public void setBack(BackPartEntity back) {
        this.back = back;
    }

    /**
     * Retrieves the ArmsPartEntity object representing the arms part of the player entity.
     *
     * @return The ArmsPartEntity object representing the arms part.
     */
    public ArmsPartEntity getArms() {
        return arms;
    }

    /**
     * Sets the ArmsPartEntity object representing the arms part of the player entity.
     *
     * @param arms The ArmsPartEntity object representing the arms part.
     */
    public void setArms(ArmsPartEntity arms) {
        this.arms = arms;
    }

    /**
     * Retrieves the LegsPartEntity object representing the legs part of the player entity.
     *
     * @return The LegsPartEntity object representing the legs part.
     */
    public LegsPartEntity getLegs() {
        return legs;
    }

    /**
     * Sets the LegsPartEntity object representing the legs part of the player entity.
     *
     * @param legs The LegsPartEntity object representing the legs part.
     */
    public void setLegs(LegsPartEntity legs) {
        this.legs = legs;
    }

    /**
     * Retrieves the HashMap representing the allomantic reserve of the player entity.
     *
     * @return The HashMap<MetalTagEnum, Integer> representing the allomantic reserve.
     */
    public HashMap<MetalTagEnum, Integer> getAllomanticReserve() {
        return allomanticReserve;
    }

    /**
     * Sets the Allomantic reserve of the player entity.
     *
     * @param allomanticReserve The HashMap representing the Allomantic reserve.
     *                          The keys are MetalTagEnum representing the metals, and the values are integers representing the amount of each metal.
     */
    public void setAllomanticReserve(HashMap<MetalTagEnum, Integer> allomanticReserve) {
        this.allomanticReserve = allomanticReserve;
    }

    /**
     * Retrieves the HashMap representing the burning metals of the player entity.
     * The keys in the HashMap represent the different metal types, and the values
     * indicate whether the corresponding metal is being burned or not.
     *
     * @return The HashMap<MetalTagEnum, Boolean> representing the burning metals.
     */
    public HashMap<MetalTagEnum, Boolean> getBurningMetals() {
        return burningMetals;
    }

    /**
     * Sets the burning metals of the player entity.
     *
     * @param burningMetals The HashMap representing the burning metals. The keys are MetalTagEnum representing
     *                      the metals, and the values indicate whether the corresponding metal is being burned or not.
     */
    public void setBurningMetals(HashMap<MetalTagEnum, Boolean> burningMetals) {
        this.burningMetals = burningMetals;
    }

    /**
     * Retrieves the HashMap representing the tapping metals of the player entity.
     *
     * @return The HashMap<MetalTagEnum, Boolean> representing the tapping metals.
     *         The keys in the HashMap represent the different metal types, and the values indicate whether the corresponding metal is being tapped or not.
     */
    public HashMap<MetalTagEnum, Boolean> getTappingMetals() {
        return tappingMetals;
    }

    /**
     * Sets the tapping metals of the player entity.
     * The tapping metals are represented as a HashMap with MetalTagEnum as the key and Boolean as the value.
     * The keys in the HashMap represent the different metal types, and the values indicate whether the corresponding metal is being tapped or not.
     *
     * @param tappingMetals The HashMap representing the tapping metals.
     *                      The keys are MetalTagEnum representing the metals, and the values indicate whether the corresponding metal is being tapped or not.
     */
    public void setTappingMetals(HashMap<MetalTagEnum, Boolean> tappingMetals) {
        this.tappingMetals = tappingMetals;
    }

    /**
     * Retrieves the HashMap representing the storing metals of the player entity.
     *
     * @return The HashMap<MetalTagEnum, Boolean> representing the storing metals.
     *         The keys in the HashMap represent the different metal types, and the values indicate whether the corresponding metal is being stored or not.
     */
    public HashMap<MetalTagEnum, Boolean> getStoringMetals() {
        return storingMetals;
    }

    /**
     * Sets the storing metals of the player entity.
     *
     * @param storingMetals The HashMap representing the storing metals.
     *                      The keys are MetalTagEnum representing the metals, and the values indicate
     *                      whether the corresponding metal is being stored or not.
     */
    public void setStoringMetals(HashMap<MetalTagEnum, Boolean> storingMetals) {
        this.storingMetals = storingMetals;
    }

    /**
     * Retrieves the list of Metal Mind equipped by the player.
     *
     * @return List of Metal Mind equipped by the player, represented as an ArrayList of Boolean values.
     */
    public ArrayList<Boolean> getMetalMindEquipped() {
        return metalMindEquipped;
    }

    /**
     * Sets the list of Metal Mind equipped by the player.
     *
     * @param metalMindEquipped The list of Metal Mind equipped by the player. The list is represented
     *                          as an ArrayList of Boolean values, where each value indicates whether the
     *                          corresponding Metal Mind is equipped or not.
     */
    public void setMetalMindEquipped(ArrayList<Boolean> metalMindEquipped) {
        this.metalMindEquipped = metalMindEquipped;
    }

    /**
     * Retrieves the value indicating whether the player entity is enhanced.
     *
     * @return The value indicating whether the player entity is enhanced. Returns true if the player entity is enhanced, false otherwise.
     */
    public Boolean getEnhanced() {
        return enhanced;
    }

    /**
     * Sets the enhanced status of the player entity.
     *
     * @param enhanced The value indicating whether the player entity is enhanced.
     *                 Set to true if the player entity is enhanced, false otherwise.
     */
    public void setEnhanced(Boolean enhanced) {
        this.enhanced = enhanced;
    }

    /**
     * Retrieves the list of enhanced metals possessed by the player entity.
     *
     * @return An ArrayList containing the enhanced metals. Each element in the list is of type MetalTagEnum.
     */
    public ArrayList<MetalTagEnum> getMetalsEnhanced() {
        return metalsEnhanced;
    }

    /**
     * Sets the list of enhanced metals.
     *
     * @param metalsEnhanced the list of enhanced metals to set
     */
    public void setMetalsEnhanced(ArrayList<MetalTagEnum> metalsEnhanced) {
        this.metalsEnhanced = metalsEnhanced;
    }

    /**
     * Gets the modified health.
     *
     * @return The modified health as a Boolean value. Returns true if the health has been modified, false otherwise.
     */
    public Boolean getModifiedHealth() {
        return modifiedHealth;
    }

    /**
     * Sets the modified health flag.
     *
     * @param modifiedHealth the boolean value indicating if the health is modified or not
     */
    public void setModifiedHealth(Boolean modifiedHealth) {
        this.modifiedHealth = modifiedHealth;
    }

    /**
     * Retrieves the current state of the Ettmetal.
     *
     * @return the current state of the Ettmetal
     */
    public EttmetalStateEnum getEttmetalState() {
        return ettmetalStateEnum;
    }

    /**
     * Sets the EttmetalState of the object.
     *
     * @param ettmetalStateEnum the EttmetalState to be set
     */
    public void setEttmetalState(EttmetalStateEnum ettmetalStateEnum) {
        this.ettmetalStateEnum = ettmetalStateEnum;
    }

    /**
     * Returns a string representation of the PlayerEntity object.
     * The string contains the values of all the attributes of the PlayerEntity object.
     *
     * @return a string representation of the PlayerEntity object
     */
    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("PlayerEntity{");
        sb.append("head=").append(head);
        sb.append(", chest=").append(chest);
        sb.append(", back=").append(back);
        sb.append(", arms=").append(arms);
        sb.append(", legs=").append(legs);
        sb.append(", allomanticReserve=").append(allomanticReserve);
        sb.append(", burningMetals=").append(burningMetals);
        sb.append(", tappingMetals=").append(tappingMetals);
        sb.append(", storingMetals=").append(storingMetals);
        sb.append(", metalMindEquipped=").append(metalMindEquipped);
        sb.append(", enhanced=").append(enhanced);
        sb.append(", metalsEnhanced=").append(metalsEnhanced);
        sb.append(", modifiedHealth=").append(modifiedHealth);
        sb.append(", ettmetalState=").append(ettmetalStateEnum);
        sb.append(", firstJoin=").append(firstJoin);
        sb.append(", hasAnyAllomanticMetal=").append(hasAnyAllomanticMetal());
        sb.append(", hasAnyFeruchemicMetal=").append(hasAnyFeruchemicMetal());
        sb.append(", countAllomanticMetal=").append(getCountAllomanticMetal());
        sb.append(", countFeruchemicMetal=").append(getCountFeruchemicMetal());
        sb.append('}');
        return sb.toString();
    }
}
