package net.rudahee.metallics_arts.data.player.data.model;

import net.rudahee.metallics_arts.data.enums.implementations.BodyPartEnum;
import net.rudahee.metallics_arts.data.enums.implementations.EttmetalState;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.enums.implementations.TypeOfSpikeEnum;

import java.util.*;
import java.util.stream.Stream;

public class PlayerEntity {

    private List<SpikeEntity> originalMetals;
    private BodyPartEntity head;
    private BodyPartEntity chest;
    private BodyPartEntity back;
    private BodyPartEntity arms;
    private BodyPartEntity legs;

    private  HashMap<MetalTagEnum, Integer> allomanticReserve;

    private  HashMap<MetalTagEnum, Boolean> burningMetals;
    private  HashMap<MetalTagEnum, Boolean> tappingMetals;
    private  HashMap<MetalTagEnum, Boolean> storingMetals;

    private ArrayList<Boolean> metalMindEquipped = new ArrayList<>(10);

    private Boolean enhanced;
    private  ArrayList<MetalTagEnum> metalsEnhanced = new ArrayList<>();

    private Boolean modifiedHealth;

    private EttmetalState ettmetalState;

    public PlayerEntity() {
        this.head = new BodyPartEntity(BodyPartEnum.HEAD);
        this.chest = new BodyPartEntity(BodyPartEnum.CHEST);
        this.back = new BodyPartEntity(BodyPartEnum.BACK);
        this.arms = new BodyPartEntity(BodyPartEnum.ARMS);
        this.legs = new BodyPartEntity(BodyPartEnum.LEGS);
        this.originalMetals = new ArrayList<>();
        this.allomanticReserve = new HashMap<>();
        this.burningMetals = new HashMap<>();
        this.tappingMetals = new HashMap<>();
        this.storingMetals = new HashMap<>();
        this.metalMindEquipped = new ArrayList<>();
        this.enhanced = false;
        this.metalsEnhanced = new ArrayList<>();
        this.modifiedHealth = false;
        this.ettmetalState = EttmetalState.NOTHING;

    }

    public PlayerEntity(List<SpikeEntity> originalMetals,
                        BodyPartEntity head, BodyPartEntity chest, BodyPartEntity back, BodyPartEntity arms, BodyPartEntity legs) {
        this.originalMetals = originalMetals;
        this.head = head;
        this.chest = chest;
        this.back = back;
        this.arms = arms;
        this.legs = legs;
    }

    public PlayerEntity(List<SpikeEntity> originalMetals,
                        BodyPartEntity head, BodyPartEntity chest, BodyPartEntity back, BodyPartEntity arms, BodyPartEntity legs,
                        HashMap<MetalTagEnum, Integer> allomanticReserve, HashMap<MetalTagEnum, Boolean> burningMetals,
                        HashMap<MetalTagEnum, Boolean> tappingMetals, HashMap<MetalTagEnum, Boolean> storingMetals,
                        ArrayList<Boolean> metalMindEquipped, Boolean enhanced, ArrayList<MetalTagEnum> metalsEnhanced,
                        Boolean modifiedHealth, EttmetalState ettmetalState) {
        this.originalMetals = originalMetals;
        this.head = head;
        this.chest = chest;
        this.back = back;
        this.arms = arms;
        this.legs = legs;
        this.allomanticReserve = allomanticReserve;
        this.burningMetals = burningMetals;
        this.tappingMetals = tappingMetals;
        this.storingMetals = storingMetals;
        this.metalMindEquipped = metalMindEquipped;
        this.enhanced = enhanced;
        this.metalsEnhanced = metalsEnhanced;
        this.modifiedHealth = modifiedHealth;
        this.ettmetalState = ettmetalState;
    }

    public boolean hasAllomanticMetal() {

        if (originalMetals.stream().anyMatch(metal -> metal.getType().equals(TypeOfSpikeEnum.ALLOMANTIC))) {
            return true;
        }

        return Stream.of(this.head, this.chest, this.back, this.arms, this.legs)
                .anyMatch(partOfBody -> partOfBody.getSpikes().stream()
                        .anyMatch(spike -> spike.getType().equals(TypeOfSpikeEnum.ALLOMANTIC)));
    }

    public boolean hasFeruchemicMetal() {

        if (originalMetals.stream().anyMatch(metal -> metal.getType().equals(TypeOfSpikeEnum.FERUCHEMIC))) {
            return true;
        }

        return Stream.of(this.head, this.chest, this.back, this.arms, this.legs)
                .anyMatch(partOfBody -> partOfBody.getSpikes().stream()
                        .anyMatch(spike -> spike.getType().equals(TypeOfSpikeEnum.FERUCHEMIC)));
    }
    public long getCountAllomanticMetal() {

        long count = 0;

        count += originalMetals.stream().filter(metal -> metal.getType().equals(TypeOfSpikeEnum.ALLOMANTIC)).count();

        count += Stream.of(this.head, this.chest, this.back, this.arms, this.legs)
                .filter(partOfBody -> partOfBody.getSpikes().stream()
                        .anyMatch(spike -> spike.getType().equals(TypeOfSpikeEnum.ALLOMANTIC))).count();

        return count;
    }

    public long getCountFeruchemicMetal() {
        long count = 0;

        count += originalMetals.stream().filter(metal -> metal.getType().equals(TypeOfSpikeEnum.FERUCHEMIC)).count();

        count += Stream.of(this.head, this.chest, this.back, this.arms, this.legs)
                .filter(partOfBody -> partOfBody.getSpikes().stream()
                        .anyMatch(spike -> spike.getType().equals(TypeOfSpikeEnum.FERUCHEMIC))).count();

        return count;
    }


    public List<SpikeEntity> getOriginalMetals() {
        return originalMetals;
    }

    public void setOriginalMetals(List<SpikeEntity> originalMetals) {
        this.originalMetals = originalMetals;
    }

    public BodyPartEntity getHead() {
        return head;
    }

    public void setHead(BodyPartEntity head) {
        this.head = head;
    }

    public BodyPartEntity getChest() {
        return chest;
    }

    public void setChest(BodyPartEntity chest) {
        this.chest = chest;
    }

    public BodyPartEntity getBack() {
        return back;
    }

    public void setBack(BodyPartEntity back) {
        this.back = back;
    }

    public BodyPartEntity getArms() {
        return arms;
    }

    public void setArms(BodyPartEntity arms) {
        this.arms = arms;
    }

    public BodyPartEntity getLegs() {
        return legs;
    }

    public void setLegs(BodyPartEntity legs) {
        this.legs = legs;
    }

    public HashMap<MetalTagEnum, Integer> getAllomanticReserve() {
        return allomanticReserve;
    }

    public void setAllomanticReserve(HashMap<MetalTagEnum, Integer> allomanticReserve) {
        this.allomanticReserve = allomanticReserve;
    }

    public HashMap<MetalTagEnum, Boolean> getBurningMetals() {
        return burningMetals;
    }

    public void setBurningMetals(HashMap<MetalTagEnum, Boolean> burningMetals) {
        this.burningMetals = burningMetals;
    }

    public HashMap<MetalTagEnum, Boolean> getTappingMetals() {
        return tappingMetals;
    }

    public void setTappingMetals(HashMap<MetalTagEnum, Boolean> tappingMetals) {
        this.tappingMetals = tappingMetals;
    }

    public HashMap<MetalTagEnum, Boolean> getStoringMetals() {
        return storingMetals;
    }

    public void setStoringMetals(HashMap<MetalTagEnum, Boolean> storingMetals) {
        this.storingMetals = storingMetals;
    }

    public ArrayList<Boolean> getMetalMindEquipped() {
        return metalMindEquipped;
    }

    public void setMetalMindEquipped(ArrayList<Boolean> metalMindEquipped) {
        this.metalMindEquipped = metalMindEquipped;
    }

    public Boolean getEnhanced() {
        return enhanced;
    }

    public void setEnhanced(Boolean enhanced) {
        this.enhanced = enhanced;
    }

    public ArrayList<MetalTagEnum> getMetalsEnhanced() {
        return metalsEnhanced;
    }

    public void setMetalsEnhanced(ArrayList<MetalTagEnum> metalsEnhanced) {
        this.metalsEnhanced = metalsEnhanced;
    }

    public Boolean getModifiedHealth() {
        return modifiedHealth;
    }

    public void setModifiedHealth(Boolean modifiedHealth) {
        this.modifiedHealth = modifiedHealth;
    }

    public EttmetalState getEttmetalState() {
        return ettmetalState;
    }

    public void setEttmetalState(EttmetalState ettmetalState) {
        this.ettmetalState = ettmetalState;
    }
}
