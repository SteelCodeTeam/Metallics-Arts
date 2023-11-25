package net.rudahee.metallics_arts.data.player.data;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.rudahee.metallics_arts.data.enums.implementations.BodyPartEnum;
import net.rudahee.metallics_arts.data.enums.implementations.EttmetalState;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.enums.implementations.TypeOfSpikeEnum;
import net.rudahee.metallics_arts.data.player.data.model.BodyPartEntity;
import net.rudahee.metallics_arts.data.player.data.model.PlayerEntity;
import net.rudahee.metallics_arts.data.player.data.model.SpikeEntity;
import net.rudahee.metallics_arts.modules.error_handling.exceptions.PlayerDataException;
import net.rudahee.metallics_arts.modules.error_handling.utils.LoggerUtils;
import net.rudahee.metallics_arts.setup.network.ModNetwork;
import net.rudahee.metallics_arts.utils.CapabilityUtils;
import net.rudahee.metallics_arts.utils.MetalTagsUtils;

import java.util.*;
import java.util.stream.Stream;

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

    PlayerEntity player;
    /**
     * Default constructor.These will be the initial values that a player will have in their tags.
     *
     * @see Tag
     */
    public InvestedPlayerData() {
        player = new PlayerEntity();

        player.setEnhanced(false);
        player.setModifiedHealth(false);

        player.setBurningMetals(new CapabilityUtils<Boolean>().fillMetalTagMap(false));
        player.setTappingMetals(new CapabilityUtils<Boolean>().fillMetalTagMap(false));
        player.setStoringMetals(new CapabilityUtils<Boolean>().fillMetalTagMap(false));

        player.setAllomanticReserve(new CapabilityUtils<Integer>().fillMetalTagMap(0));

        player.setMetalMindEquipped(new CapabilityUtils<Boolean>().fillListWithDefaultValue(false, 10));

        this.player.setEttmetalState(EttmetalState.NOTHING);
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
    public void tickAllomancyBurningMetals(Player player, int tick) {
        boolean readyToSync = false;

        for (MetalTagEnum metal: MetalTagEnum.values()) {
            if (this.isBurning(metal)) {
                if (!this.hasAllomanticPower(metal)) {
                    this.setBurning(metal, false);
                }
                else {
                    if (isTapping(metal)) {
                        if (tick % metal.getFeruchemicCompoundingMultiplier() == 0) {
                            this.setAllomanticMetalsAmount(metal, this.getAllomanticAmount(metal) - 1);
                        }
                    } else {
                        this.setAllomanticMetalsAmount(metal, this.getAllomanticAmount(metal) - 1);
                    }
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

    @Override
    public boolean isFirstJoin() {
        return this.player.isFirstJoin();
    }

    @Override
    public void setFirstJoin(boolean joined) {
        this.player.setFirstJoin(joined);
    }

    @Override
    public void alreadyJoin() {
        this.player.setFirstJoin(false);
    }

    /**
     * Check if the player is burning any metal to obtain the power.
     *
     * @return boolean
     */
    @Override
    public boolean isBurningAnything() {
        return this.player.getBurningMetals().values().stream().anyMatch(isBurning -> true);
    }

    /**
     * Check if the player is tapping any metal to use the power.
     *
     * @return boolean
     */
    @Override
    public boolean isTappingAnything() {
        return this.player.getTappingMetals().values().stream().anyMatch(isTapping -> true);
    }

    /**
     * Check if the player is storing any metal to save the power.
     *
     * @return boolean
     */
    @Override
    public boolean isStoringAnything() {
        return this.player.getStoringMetals().values().stream().anyMatch(isStoring -> true);
    }

    /**
     * Clear the list of the metals enhanced by nicrosil or duralumin.
     */
    @Override
    public void clearMetalsEnhanced() {
        this.player.getMetalsEnhanced().clear();
    }

    /**
     * Add one metal to the list of enhanced metals by nicrosil or duralumin powers.
     *
     * @param metal that was being added to the list.
     */
    @Override
    public void addMetalsEnhanced(MetalTagEnum metal) {
        this.player.getMetalsEnhanced().add(metal);
    }

    /**
     * Return the list of enhanced metals by nicrosil or duralumin powers.
     *
     * @return ArrayList<MetalTagEnum>
     */
    @Override
    public ArrayList<MetalTagEnum> getMetalsEnhanced() {
        return this.player.getMetalsEnhanced();
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
        return this.player.getMetalsEnhanced().contains(metal);
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
        return this.player.hasAllomanticMetal(metal);
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
        return this.player.hasFeruchemicMetal(metal);
    }

    /**
     * Counts the allomantic powers a player has.
     *
     * @return int
     */
    @Override
    public int getAllomanticPowerCount() {
        return (int) this.player.getCountAllomanticMetal();
    }

    /**
     * Counts the feruchemic powers a player has.
     *
     * @return int
     */
    @Override
    public int getFeruchemicPowerCount() {
        return (int) this.player.getCountFeruchemicMetal();
    }

    /**
     * Check if it has at least some allomantic power.
     *
     * @return boolean
     */
    @Override
    public boolean hasAnyAllomanticPower() {
        return this.player.hasAnyAllomanticMetal();
    }

    /**
     * Check if it has at least some feruchemic power.
     *
     * @return boolean
     */
    @Override
    public boolean hasAnyFeruchemicPower() {
        return  this.player.hasAnyFeruchemicMetal();
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
        return this.player.getMetalMindEquipped().get(group);
    }

    /**
     * It is defined if a metal mind is equipped or not, from a group.
     *
     * @param group of the Metal Mind that was being checked.
     * @param value to define if it is equipped or not.
     */
    @Override
    public void setMetalMindEquiped(int group, boolean value) {
        this.player.getMetalMindEquipped().set(group, value);
    }

    /**
     * Obtain all Metal Minds equipped by a player.
     *
     * @return ArrayList<Boolean>
     */
    @Override
    public ArrayList<Boolean> getMetalMindEquipedList() {
        return this.player.getMetalMindEquipped();
    }

    /**
     * It is defined if a list of metal mind is equipped,
     *
     * @param list of the Metal Mind that was being set.
     */
    @Override
    public void setMetalMindEquipedList(ArrayList<Boolean> list) {
        this.player.setMetalMindEquipped(list);
    }

    /**
     * Check if the player is enhanced
     *
     * @return boolean
     */
    @Override
    public boolean getEnhanced() {
        return this.player.getEnhanced();
    }

    /**
     * Set the player if enhanced of not.
     *
     * @param isEnhanced boolean to define if it's enhanced.
     */
    @Override
    public void setEnhanced(boolean isEnhanced) {
        this.player.setEnhanced(isEnhanced);
    }

    /**
     * Check if the player don't have the standard health
     *
     * @return boolean
     */
    @Override
    public boolean hasModifiedHealth() {
        return this.player.getModifiedHealth();
    }

    /**
     * Set the player if it has a modified quantity of health.
     *
     * @param modifiedHealth boolean to define if it's modified.
     */
    @Override
    public void setModifiedHealth(boolean modifiedHealth) {
        this.player.setModifiedHealth(modifiedHealth);
    }

    /**
     * Adds an allomantic power to the player.
     *
     * @param metal of the power.
     */
    @Override
    public void addAllomanticPower(MetalTagEnum metal) {
        try {
            Optional<BodyPartEntity> partEntity = Stream.of(player.getLegs(), player.getHead(), player.getBack(), player.getArms(), player.getChest())
                    .filter(bodyPart -> bodyPart.getActualSpikes() < bodyPart.getMaxSpikes()).findFirst();

            if (partEntity.isPresent()) {
                partEntity.get().addSpike(new SpikeEntity(metal, TypeOfSpikeEnum.ALLOMANTIC));
            }
        } catch (PlayerDataException ex) {
            LoggerUtils.printLogError(ex.getMessage());
        }
    }

    @Override
    public void addAllomanticPower(MetalTagEnum metal, TypeOfSpikeEnum spike, BodyPartEnum part) {
        try {
            if (part.equals(BodyPartEnum.HEAD)) {
                this.player.getHead().addSpike(new SpikeEntity(metal, spike));
            } else if (part.equals(BodyPartEnum.CHEST)) {
                this.player.getChest().addSpike(new SpikeEntity(metal, spike));
            } else if (part.equals(BodyPartEnum.ARMS)) {
                this.player.getArms().addSpike(new SpikeEntity(metal, spike));
            } else if (part.equals(BodyPartEnum.BACK)) {
                this.player.getBack().addSpike(new SpikeEntity(metal, spike));
            } else if (part.equals(BodyPartEnum.LEGS)) {
                this.player.getLegs().addSpike(new SpikeEntity(metal, spike));
            } else {
                Optional<BodyPartEntity> partEntity = Stream.of(player.getLegs(), player.getHead(), player.getBack(), player.getArms(), player.getChest())
                        .filter(bodyPart -> bodyPart.getActualSpikes() < bodyPart.getMaxSpikes()).findFirst();

                if (partEntity.isPresent()) {
                    partEntity.get().addSpike(new SpikeEntity(metal, spike));
                }
            }
        } catch (PlayerDataException ex) {
            LoggerUtils.printLogError(ex.getMessage());
        }

    }

    /**
     * Adds a feruchemic power to the player.
     *
     * @param metal of the power.
     */
    @Override
    public void addFeruchemicPower(MetalTagEnum metal) {
        try {
            Optional<BodyPartEntity> partEntity = Stream.of(player.getLegs(), player.getHead(), player.getBack(), player.getArms(), player.getChest())
                    .filter(bodyPart -> bodyPart.getActualSpikes() < bodyPart.getMaxSpikes()).findFirst();

            if (partEntity.isPresent()) {
                partEntity.get().addSpike(new SpikeEntity(metal, TypeOfSpikeEnum.FERUCHEMIC));
            }
        } catch (PlayerDataException ex) {
            LoggerUtils.printLogError(ex.getMessage());
        }
    }

    public void addFeruchemicPower(MetalTagEnum metal, TypeOfSpikeEnum spike, BodyPartEnum part) {
        try {
            if (part.equals(BodyPartEnum.HEAD)) {
                this.player.getHead().addSpike(new SpikeEntity(metal, spike));
            } else if (part.equals(BodyPartEnum.CHEST)) {
                this.player.getChest().addSpike(new SpikeEntity(metal, spike));
            } else if (part.equals(BodyPartEnum.ARMS)) {
                this.player.getArms().addSpike(new SpikeEntity(metal, spike));
            } else if (part.equals(BodyPartEnum.BACK)) {
                this.player.getBack().addSpike(new SpikeEntity(metal, spike));
            } else if (part.equals(BodyPartEnum.LEGS)) {
                this.player.getLegs().addSpike(new SpikeEntity(metal, spike));
            } else {
                Optional<BodyPartEntity> partEntity = Stream.of(player.getLegs(), player.getHead(), player.getBack(), player.getArms(), player.getChest())
                        .filter(bodyPart -> bodyPart.getActualSpikes() < bodyPart.getMaxSpikes()).findFirst();

                if (partEntity.isPresent()) {
                    partEntity.get().addSpike(new SpikeEntity(metal, spike));
                }
            }
        } catch (PlayerDataException ex) {
            LoggerUtils.printLogError(ex.getMessage());
        }

    }

    /**
     * Adds all allomantic powers to the player.
     */
    @Override
    public void addAllAllomantic() {

        List<MetalTagEnum> metals = Arrays.asList(MetalTagEnum.values());

        List<MetalTagEnum> filteredMetals = metals.stream().filter(metal -> !this.getAllomanticPowers().contains(metal)).toList();

        for (MetalTagEnum metal: filteredMetals) {
            addAllomanticPower(metal);
        }
    }

    /**
     * Adds all feruchemic powers to the player.
     */
    @Override
    public void addAllFeruchemic() {
        List<MetalTagEnum> metals = Arrays.asList(MetalTagEnum.values());

        List<MetalTagEnum> filteredMetals = metals.stream().filter(metal -> !this.getFeruchemicPowers().contains(metal)).toList();

        for (MetalTagEnum metal: filteredMetals) {
            addFeruchemicPower(metal);
        }
    }

    /**
     * Remove an allomantic power to the player.
     *
     * @param metal of the power.
     */
    @Override
    public void removeAllomanticPower(MetalTagEnum metal) {
        Stream.of(player.getLegs(), player.getHead(), player.getBack(), player.getArms(), player.getChest())
                .forEach(bodyPart -> bodyPart.removeSpike(new SpikeEntity(metal, TypeOfSpikeEnum.ALLOMANTIC)));
    }

    /**
     * Remove all allomantic powers to the player.
     */
    @Override
    public void removeAllAllomanticPower() {
        List<MetalTagEnum> metals = getAllomanticPowers();
        for (MetalTagEnum metal: metals) {
            this.removeAllomanticPower(metal);
        }
    }

    /**
     * Remove a feruchemic power to the player.
     *
     * @param metal of the power.
     */
    @Override
    public void removeFeruchemicPower(MetalTagEnum metal) {
        Stream.of(player.getLegs(), player.getHead(), player.getBack(), player.getArms(), player.getChest())
                .forEach(bodyPart -> bodyPart.removeSpike(new SpikeEntity(metal, TypeOfSpikeEnum.FERUCHEMIC)));
    }

    /**
     * Remove all feruchemic powers to the player.
     */
    @Override
    public void removeAllFeruchemicPower() {
        List<MetalTagEnum> metals = getFeruchemicPowers();
        for (MetalTagEnum metal: metals) {
            this.removeFeruchemicPower(metal);
        }
    }

    /**
     * Check if the player is tapping a specific metal.
     *
     * @param metal of the power.
     * @return boolean
     */
    @Override
    public boolean isTapping(MetalTagEnum metal) {
        return this.player.getTappingMetals().get(metal);
    }

    /**
     * Check if the player is storing a specific metal.
     *
     * @param metal of the power.
     * @return boolean
     */
    @Override
    public boolean isStoring(MetalTagEnum metal) {
        return this.player.getStoringMetals().get(metal);
    }

    /**
     * Check if the player is burning a specific metal.
     *
     * @param metal of the power.
     * @return boolean
     */
    @Override
    public boolean isBurning(MetalTagEnum metal) {
        return this.player.getBurningMetals().get(metal);
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
        this.player.getTappingMetals().put(metal, value);
    }

    /**
     * Defines whether the player is storing a metal
     *
     * @param metal to be modified.
     * @param value that defines if it is storing or not.
     */
    @Override
    public void setStoring(MetalTagEnum metal, boolean value) {
        this.player.getStoringMetals().put(metal, value);
    }

    /**
     * Defines whether the player is burning a metal
     *
     * @param metal to be modified.
     * @param value that defines if it is burning or not.
     */
    @Override
    public void setBurning(MetalTagEnum metal, boolean value) {
        this.player.getBurningMetals().put(metal, value);
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

        this.player.getAllomanticReserve().put(metal, qty);
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
        int value = this.player.getAllomanticReserve().get(metal);

        if (metal.getMaxAllomanticTicksStorage() < value + qty) {
            this.player.getAllomanticReserve().put(metal, metal.getMaxAllomanticTicksStorage());
            return false;
        } else {
            this.player.getAllomanticReserve().put(metal, value + qty);
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
        int value = this.player.getAllomanticReserve().get(metal);
        if (value - qty < 0) {
            this.player.getAllomanticReserve().put(metal, 0);
            return false;
        } else {
            this.player.getAllomanticReserve().put(metal, value - qty);
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
        return this.player.getAllomanticReserve().get(metal);
    }

    @Override
    public void setEttmetalState(EttmetalState state) {
        this.player.setEttmetalState(state);
    }

    @Override
    public EttmetalState getEttmetalState() {
        return this.player.getEttmetalState();
    }

    @Override
    public boolean hasAllomanticAmountOf(MetalTagEnum metal) {
        return (this.getAllomanticAmount(metal) > 0);
    }

    @Override
    public PlayerEntity getPlayerData() {
        return player;
    }


    /**
     * Save the actual data in the CompoundTag
     *
     * @return CompoundTag
     */
    @Override
    public CompoundTag save() {

        CompoundTag playerData = new CompoundTag();
        CompoundTag head = new CompoundTag();
        CompoundTag chest = new CompoundTag();
        CompoundTag back = new CompoundTag();
        CompoundTag arms = new CompoundTag();
        CompoundTag legs = new CompoundTag();
        CompoundTag metalminds = new CompoundTag();
        CompoundTag extraData = new CompoundTag();
        CompoundTag reserve = new CompoundTag();
        CompoundTag burningMetals = new CompoundTag();
        CompoundTag tappingMetals = new CompoundTag();
        CompoundTag storingMetals = new CompoundTag();
        CompoundTag enhanced = new CompoundTag();
        CompoundTag metalEnhanced = new CompoundTag();

        SpikeEntity spike;
        for (int i = 0; i < player.getHead().getMaxSpikes(); i++) {
            try {
                spike = player.getHead().getSpikes().get(i);
            } catch (IndexOutOfBoundsException e) {
                spike = null;
            }
            String slotValue = (spike != null) ? spike.getMetal().getNameLower() + "_" + spike.getType().getType() : "empty";
            head.putString("slot_"+i, slotValue);
        }

        for (int i = 0; i < player.getChest().getMaxSpikes(); i++) {
            try {
                spike = player.getChest().getSpikes().get(i);
            } catch (IndexOutOfBoundsException e) {
                spike = null;
            }
            String slotValue = (spike != null) ? spike.getMetal().getNameLower() + "_" + spike.getType().getType() : "empty";
            chest.putString("slot_"+i, slotValue);
        }

        for (int i = 0; i < player.getBack().getMaxSpikes(); i++) {
            try {
                spike = player.getBack().getSpikes().get(i);
            } catch (IndexOutOfBoundsException e) {
                spike = null;
            }
            String slotValue = (spike != null) ? spike.getMetal().getNameLower() + "_" + spike.getType().getType() : "empty";
            back.putString("slot_"+i, slotValue);
        }

        for (int i = 0; i < player.getArms().getMaxSpikes(); i++) {
            try {
                spike = player.getArms().getSpikes().get(i);
            } catch (IndexOutOfBoundsException e) {
                spike = null;
            }
            String slotValue = (spike != null) ? spike.getMetal().getNameLower() + "_" + spike.getType().getType() : "empty";
            arms.putString("slot_"+i, slotValue);
        }

        for (int i = 0; i < player.getLegs().getMaxSpikes(); i++) {
            try {
                spike = player.getLegs().getSpikes().get(i);
            } catch (IndexOutOfBoundsException e) {
                spike = null;
            }
            String slotValue = (spike != null) ? spike.getMetal().getNameLower() + "_" + spike.getType().getType() : "empty";
            legs.putString("slot_"+i, slotValue);
        }

        playerData.put("head", head);
        playerData.put("chest", chest);
        playerData.put("back", back);
        playerData.put("arms", arms);
        playerData.put("legs", legs);

        for (int i = 0; i < 10; i++) {
            metalminds.putBoolean("group_" + i, this.hasMetalMindEquiped(i));
        }
        playerData.put("metalminds", metalminds);

        for (MetalTagEnum metal : MetalTagEnum.values()) {
            reserve.putInt(metal.getNameLower(), this.getAllomanticAmount(metal));
            burningMetals.putBoolean(metal.getNameLower(), this.isBurning(metal));
            tappingMetals.putBoolean(metal.getNameLower(), this.isTapping(metal));
            storingMetals.putBoolean(metal.getNameLower(), this.isStoring(metal));
        }

        extraData.put("reserve", reserve);
        extraData.put("burning", burningMetals);
        extraData.put("tapping", tappingMetals);
        extraData.put("storing", storingMetals);


        for (MetalTagEnum metal : MetalTagEnum.values()) {
            metalEnhanced.putBoolean(metal.getNameLower(), this.getMetalsEnhanced().contains(metal));
        }

        enhanced.putBoolean("is_enhanced", this.getEnhanced());
        enhanced.put("metal_enhanced", metalEnhanced);
        extraData.put("enhanced_data", enhanced);

        extraData.putBoolean("has_modified_health", this.hasModifiedHealth());
        extraData.putString("ettmetal_state", this.getEttmetalState().getName());
        extraData.putBoolean("is_first_join", this.isFirstJoin());

        playerData.put("extra_data", extraData);

        return playerData;
    }


    /**
     * Load the actual data in the past by parameter tag.
     *
     * @param playerData tag to save data.
     */
    @Override
    public void load(CompoundTag playerData) {


        CompoundTag head = playerData.getCompound("head");
        CompoundTag back = playerData.getCompound("back");
        CompoundTag chest = playerData.getCompound("chest");
        CompoundTag legs = playerData.getCompound("legs");
        CompoundTag arms = playerData.getCompound("arms");
        CompoundTag metalminds = playerData.getCompound("metalminds");
        CompoundTag extraData = playerData.getCompound("extra_data");
        CompoundTag reserve = extraData.getCompound("reserve");
        CompoundTag burning = extraData.getCompound("burning");
        CompoundTag tapping= extraData.getCompound("tapping");
        CompoundTag storing = extraData.getCompound("storing");
        CompoundTag enhanced = extraData.getCompound("enhanced_data");
        CompoundTag metalEnhanced = extraData.getCompound("metal_enhanced");

        List<SpikeEntity> spikes = new ArrayList<>();
        String spikeStr;
        for (int i = 0; i < player.getHead().getMaxSpikes(); i++) {
            spikeStr = head.getString("slot_"+i);
            if (!spikeStr.equals("empty")) {
                if (spikeStr.contains("allomantic")) {
                    spikes.add(new SpikeEntity(MetalTagsUtils.getMetalTagEnumByString(spikeStr), TypeOfSpikeEnum.ALLOMANTIC));
                } else {
                    spikes.add(new SpikeEntity(MetalTagsUtils.getMetalTagEnumByString(spikeStr), TypeOfSpikeEnum.FERUCHEMIC));
                }
            }
        }
        try {
            player.getHead().setSpikes(spikes);
        } catch (PlayerDataException ex) {
            ex.printCompleteLog();
        }

        spikes = new ArrayList<>();
        for (int i = 0; i < player.getBack().getMaxSpikes(); i++) {
            spikeStr = back.getString("slot_"+i);
            if (!spikeStr.equals("empty")) {
                if (spikeStr.contains("allomantic")) {
                    spikes.add(new SpikeEntity(MetalTagsUtils.getMetalTagEnumByString(spikeStr), TypeOfSpikeEnum.ALLOMANTIC));
                } else {
                    spikes.add(new SpikeEntity(MetalTagsUtils.getMetalTagEnumByString(spikeStr), TypeOfSpikeEnum.FERUCHEMIC));
                }
            }
        }
        try {
            player.getBack().setSpikes(spikes);
        } catch (PlayerDataException ex) {
            ex.printCompleteLog();
        }

        spikes = new ArrayList<>();
        for (int i = 0; i < player.getChest().getMaxSpikes(); i++) {
            spikeStr = chest.getString("slot_"+i);
            if (!spikeStr.equals("empty")) {
                if (spikeStr.contains("allomantic")) {
                    spikes.add(new SpikeEntity(MetalTagsUtils.getMetalTagEnumByString(spikeStr), TypeOfSpikeEnum.ALLOMANTIC));
                } else {
                    spikes.add(new SpikeEntity(MetalTagsUtils.getMetalTagEnumByString(spikeStr), TypeOfSpikeEnum.FERUCHEMIC));
                }
            }
        }
        try {
            player.getChest().setSpikes(spikes);
        } catch (PlayerDataException ex) {
            ex.printCompleteLog();
        }

        spikes = new ArrayList<>();
        for (int i = 0; i < player.getArms().getMaxSpikes(); i++) {
            spikeStr = arms.getString("slot_"+i);
            if (!spikeStr.equals("empty")) {
                if (spikeStr.contains("allomantic")) {
                    spikes.add(new SpikeEntity(MetalTagsUtils.getMetalTagEnumByString(spikeStr), TypeOfSpikeEnum.ALLOMANTIC));
                } else {
                    spikes.add(new SpikeEntity(MetalTagsUtils.getMetalTagEnumByString(spikeStr), TypeOfSpikeEnum.FERUCHEMIC));

                }
            }
        }
        try {
            player.getArms().setSpikes(spikes);
        } catch (PlayerDataException ex) {
            ex.printCompleteLog();
        }

        spikes = new ArrayList<>();
        for (int i = 0; i < player.getLegs().getMaxSpikes(); i++) {
            spikeStr = legs.getString("slot_"+i);
            if (!spikeStr.equals("empty")) {
                if (spikeStr.contains("allomantic")) {
                    spikes.add(new SpikeEntity(MetalTagsUtils.getMetalTagEnumByString(spikeStr), TypeOfSpikeEnum.ALLOMANTIC));
                } else {
                    spikes.add(new SpikeEntity(MetalTagsUtils.getMetalTagEnumByString(spikeStr), TypeOfSpikeEnum.FERUCHEMIC));
                }
            }
        }
        try {
            player.getLegs().setSpikes(spikes);
        } catch (PlayerDataException ex) {
            ex.printCompleteLog();
        }


        for (int i = 0; i < 10; i++) {
            this.setMetalMindEquiped(i, metalminds.getBoolean("group_" + i));
        }

        for (MetalTagEnum metal : MetalTagEnum.values()) {
            this.setTapping(metal, tapping.getBoolean(metal.getNameLower()));
            this.setStoring(metal, storing.getBoolean(metal.getNameLower()));
            this.setAllomanticMetalsAmount(metal, reserve.getInt(metal.getNameLower()));
            this.setBurning(metal, burning.getBoolean(metal.getNameLower()));
        }

        this.setEnhanced(enhanced.getBoolean("is_enhanced"));

        for (MetalTagEnum metal : MetalTagEnum.values()) {
            if (metalEnhanced.getBoolean(metal.getNameLower())) {
                this.addMetalsEnhanced(metal);
            }
        }

        this.setModifiedHealth(extraData.getBoolean("has_modified_health"));
        this.setEttmetalState(EttmetalState.getEttmetaStateByName(extraData.getString("ettmetal_state")));

        this.setFirstJoin(extraData.getBoolean("is_first_join"));

    }


}
