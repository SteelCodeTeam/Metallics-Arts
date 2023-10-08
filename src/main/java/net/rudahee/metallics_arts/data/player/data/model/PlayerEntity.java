package net.rudahee.metallics_arts.data.player.data.model;

import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;
import net.rudahee.metallics_arts.data.enums.implementations.BodyPartEnum;
import net.rudahee.metallics_arts.data.enums.implementations.EttmetalState;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.enums.implementations.TypeOfSpikeEnum;
import net.rudahee.metallics_arts.setup.network.ModNetwork;

import java.util.*;
import java.util.stream.Stream;

public class PlayerEntity {

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
    private boolean firstJoin;

    public PlayerEntity() {
        this.head = new BodyPartEntity(BodyPartEnum.HEAD);
        this.chest = new BodyPartEntity(BodyPartEnum.CHEST);
        this.back = new BodyPartEntity(BodyPartEnum.BACK);
        this.arms = new BodyPartEntity(BodyPartEnum.ARMS);
        this.legs = new BodyPartEntity(BodyPartEnum.LEGS);
        this.allomanticReserve = new HashMap<>();
        this.burningMetals = new HashMap<>();
        this.tappingMetals = new HashMap<>();
        this.storingMetals = new HashMap<>();
        this.metalMindEquipped = new ArrayList<>();
        this.enhanced = false;
        this.metalsEnhanced = new ArrayList<>();
        this.modifiedHealth = false;
        this.ettmetalState = EttmetalState.NOTHING;
        this.firstJoin = true;
    }

    public boolean isFirstJoin() {
        return firstJoin;
    }

    public void setFirstJoin(boolean firstJoin) {
        this.firstJoin = firstJoin;
    }

    public boolean hasAnyAllomanticMetal() {

        return Stream.of(this.head, this.chest, this.back, this.arms, this.legs)
                .anyMatch(partOfBody -> partOfBody.getSpikes().stream()
                        .anyMatch(spike -> spike.getType().equals(TypeOfSpikeEnum.ALLOMANTIC)));
    }

    public boolean hasAllomanticMetal(MetalTagEnum metal) {
         Optional<BodyPartEntity> bodyPart = Stream.of(this.head, this.chest, this.back, this.arms, this.legs).filter(
                bodyPartEntity -> bodyPartEntity.getSpikes().stream().anyMatch(
                        spikeEntity -> spikeEntity.getType().equals(TypeOfSpikeEnum.ALLOMANTIC) && spikeEntity.getMetal().equals(metal))
         ).findAny();

         return bodyPart.isPresent();
    }

    public boolean hasAnyFeruchemicMetal() {

        return Stream.of(this.head, this.chest, this.back, this.arms, this.legs)
                .anyMatch(partOfBody -> partOfBody.getSpikes().stream()
                        .anyMatch(spike -> spike.getType().equals(TypeOfSpikeEnum.FERUCHEMIC)));
    }

    public boolean hasFeruchemicMetal(MetalTagEnum metal) {
        Optional<BodyPartEntity> bodyPart = Stream.of(this.head, this.chest, this.back, this.arms, this.legs).filter(
                bodyPartEntity -> bodyPartEntity.getSpikes().stream().anyMatch(
                        spikeEntity -> spikeEntity.getType().equals(TypeOfSpikeEnum.FERUCHEMIC) && spikeEntity.getMetal().equals(metal))
        ).findAny();

        return bodyPart.isPresent();
    }


    public long getCountAllomanticMetal() {

        long count = 0;

        count += Stream.of(this.head, this.chest, this.back, this.arms, this.legs)
                .filter(partOfBody -> partOfBody.getSpikes().stream()
                        .anyMatch(spike -> spike.getType().equals(TypeOfSpikeEnum.ALLOMANTIC))).count();

        return count;
    }

    public long getCountFeruchemicMetal() {
        long count = 0;

        count += Stream.of(this.head, this.chest, this.back, this.arms, this.legs)
                .filter(partOfBody -> partOfBody.getSpikes().stream()
                        .anyMatch(spike -> spike.getType().equals(TypeOfSpikeEnum.FERUCHEMIC))).count();

        return count;
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
