package net.rudahee.metallics_arts.data.player.data;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.rudahee.metallics_arts.data.player.data.model.body.ChestPartEntity;
import net.rudahee.metallics_arts.data.player.data.model.enums.BodyPartEnum;
import net.rudahee.metallics_arts.data.player.data.model.enums.EttmetalStateEnum;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.player.data.model.enums.TypeOfSpikeEnum;
import net.rudahee.metallics_arts.data.player.data.model.body.BodyPartEntity;
import net.rudahee.metallics_arts.data.player.data.model.PlayerEntity;
import net.rudahee.metallics_arts.data.player.data.model.SpikeEntity;
import net.rudahee.metallics_arts.data.player.data.model.enums.BodySlotEnum;
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

        this.player.setEttmetalState(EttmetalStateEnum.NOTHING);
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

    /**
     * Determines if the player is joining for the first time.
     *
     * @return {@code true} if the player is joining for the first time,
     *         {@code false} otherwise.
     */
    @Override
    public boolean isFirstJoin() {
        return this.player.isFirstJoin();
    }

    /**
     * Sets the first join status for the player.
     *
     * @param joined {@code true} if the player has joined for the first time, {@code false} otherwise.
     */
    @Override
    public void setFirstJoin(boolean joined) {
        this.player.setFirstJoin(joined);
    }

    /**
     * Marks the player as already joined by setting the firstJoin flag to false.
     */
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
        System.out.println("ENTRANDO PARA EL METAL ALOMANTICO: " + metal);

        if (player.getLegs().getActualQtySpikes() < player.getLegs().getMaxQtySpikes()) {
            System.out.println("PIERNAS DISPONIBLES PARA EL METAL: " + metal);

            if (player.getLegs().getAllSpikes().stream().noneMatch(spike -> spike.getMetal().getNameLower().equals(metal.getNameLower()) && spike.getType() == TypeOfSpikeEnum.ALLOMANTIC)) {
                player.getLegs().addSpikeRandom(new SpikeEntity(metal, TypeOfSpikeEnum.ALLOMANTIC));
            } else {
                System.out.println("YA EXISTE EL METAL EN ESTA PARTE DEL CUERPO PARA EL METAL: " + metal);
            }
        }
        else if (player.getHead().getActualQtySpikes() < player.getHead().getMaxQtySpikes()) {
            System.out.println("CABEZA DISPONIBLES PARA EL METAL: " + metal);

            if (player.getHead().getAllSpikes().stream().noneMatch(spike -> spike.getMetal().getNameLower().equals(metal.getNameLower()) && spike.getType() == TypeOfSpikeEnum.ALLOMANTIC)) {
                player.getHead().addSpikeRandom(new SpikeEntity(metal, TypeOfSpikeEnum.ALLOMANTIC));
            } else {
                System.out.println("YA EXISTE EL METAL EN ESTA PARTE DEL CUERPO PARA EL METAL: " + metal);
            }
        }
        else if (player.getBack().getActualQtySpikes() < player.getBack().getMaxQtySpikes()) {
            System.out.println("ESPALDA DISPONIBLES PARA EL METAL: " + metal);

            if (player.getBack().getAllSpikes().stream().noneMatch(spike -> spike.getMetal().getNameLower().equals(metal.getNameLower()) && spike.getType() == TypeOfSpikeEnum.ALLOMANTIC)) {
                player.getBack().addSpikeRandom(new SpikeEntity(metal, TypeOfSpikeEnum.ALLOMANTIC));
            } else {
                System.out.println("YA EXISTE EL METAL EN ESTA PARTE DEL CUERPO PARA EL METAL: " + metal);
            }
        }
        else if (player.getArms().getActualQtySpikes() < player.getArms().getMaxQtySpikes()) {
            System.out.println("BRAZO DISPONIBLES PARA EL METAL: " + metal);
            if (player.getArms().getAllSpikes().stream().noneMatch(spike -> spike.getMetal().getNameLower().equals(metal.getNameLower()) && spike.getType() == TypeOfSpikeEnum.ALLOMANTIC)) {
                player.getArms().addSpikeRandom(new SpikeEntity(metal, TypeOfSpikeEnum.ALLOMANTIC));
            } else {
                System.out.println("YA EXISTE EL METAL EN ESTA PARTE DEL CUERPO PARA EL METAL: " + metal);
            }
        }
        else if (player.getChest().getActualQtySpikes() < player.getChest().getMaxQtySpikes()) {
            System.out.println("PECHO DISPONIBLES PARA EL METAL: " + metal);

            if (player.getChest().getAllSpikes().stream().noneMatch(spike -> spike.getMetal().getNameLower().equals(metal.getNameLower()) && spike.getType() == TypeOfSpikeEnum.ALLOMANTIC)) {
                player.getChest().addSpikeRandom(new SpikeEntity(metal, TypeOfSpikeEnum.ALLOMANTIC));
            } else {
                System.out.println("YA EXISTE EL METAL EN ESTA PARTE DEL CUERPO PARA EL METAL: " + metal);
            }
        } else {
            System.out.println("NO HAY HUECO");
        };
        System.out.println("FINALIZADO PROCESO PARA EL METAL: " + metal);

    }


    /**
     * Adds an Allomantic power to the player's body part at a specific slot.
     *
     * @param metal     the type of metal associated with the Allomantic power
     * @param part      the body part to add the Allomantic power to
     * @param slotPos   the position of the slot within the body part
     * @param slotNum   the number of the slot within the body part
     */
    @Override
    public void addAllomanticPower(MetalTagEnum metal, BodyPartEnum part, BodySlotEnum slotPos, int slotNum) {
        if (part.equals(BodyPartEnum.HEAD)) {
            this.player.getHead().addSpikeBySlot(new SpikeEntity(metal, TypeOfSpikeEnum.ALLOMANTIC), slotNum, slotPos);
        } else if (part.equals(BodyPartEnum.CHEST)) {
            this.player.getChest().addSpikeBySlot(new SpikeEntity(metal, TypeOfSpikeEnum.ALLOMANTIC), slotNum, slotPos);
        } else if (part.equals(BodyPartEnum.ARMS)) {
            this.player.getArms().addSpikeBySlot(new SpikeEntity(metal, TypeOfSpikeEnum.ALLOMANTIC), slotNum, slotPos);
        } else if (part.equals(BodyPartEnum.BACK)) {
            this.player.getBack().addSpikeBySlot(new SpikeEntity(metal, TypeOfSpikeEnum.ALLOMANTIC), slotNum, slotPos);
        } else if (part.equals(BodyPartEnum.LEGS)) {
            this.player.getLegs().addSpikeBySlot(new SpikeEntity(metal, TypeOfSpikeEnum.ALLOMANTIC), slotNum, slotPos);
        } else {
            Optional<BodyPartEntity> partEntity = Stream.of(player.getLegs(), player.getHead(), player.getBack(), player.getArms(), player.getChest())
                    .filter(bodyPart -> bodyPart.getActualQtySpikes() < bodyPart.getMaxQtySpikes()).findFirst();

            if (partEntity.isPresent()) {
                partEntity.get().addSpikeRandom(new SpikeEntity(metal, TypeOfSpikeEnum.ALLOMANTIC));
            }
        }
    }

    /**
     * Adds a feruchemic power to the player.
     *
     * @param metal of the power.
     */
    @Override
    public void addFeruchemicPower(MetalTagEnum metal) {

        if (player.getLegs().getActualQtySpikes() < player.getLegs().getMaxQtySpikes()) {
            if (player.getLegs().getAllSpikes().stream().noneMatch(spike -> spike.getMetal().getNameLower().equals(metal.getNameLower()) && spike.getType() == TypeOfSpikeEnum.FERUCHEMIC)) {
                player.getLegs().addSpikeRandom(new SpikeEntity(metal, TypeOfSpikeEnum.FERUCHEMIC));
            }
        }
        else if (player.getHead().getActualQtySpikes() < player.getHead().getMaxQtySpikes()) {
            if (player.getHead().getAllSpikes().stream().noneMatch(spike -> spike.getMetal().getNameLower().equals(metal.getNameLower()) && spike.getType() == TypeOfSpikeEnum.FERUCHEMIC)) {
                player.getHead().addSpikeRandom(new SpikeEntity(metal, TypeOfSpikeEnum.FERUCHEMIC));
            }
        }
        else if (player.getBack().getActualQtySpikes() < player.getBack().getMaxQtySpikes()) {
            if (player.getBack().getAllSpikes().stream().noneMatch(spike -> spike.getMetal().getNameLower().equals(metal.getNameLower()) && spike.getType() == TypeOfSpikeEnum.FERUCHEMIC)) {
                player.getBack().addSpikeRandom(new SpikeEntity(metal, TypeOfSpikeEnum.FERUCHEMIC));
            }        }
        else if (player.getArms().getActualQtySpikes() < player.getArms().getMaxQtySpikes()) {
            if (player.getArms().getAllSpikes().stream().noneMatch(spike -> spike.getMetal().getNameLower().equals(metal.getNameLower()) && spike.getType() == TypeOfSpikeEnum.FERUCHEMIC)) {
                player.getArms().addSpikeRandom(new SpikeEntity(metal, TypeOfSpikeEnum.FERUCHEMIC));
            }
        }
        else if (player.getChest().getActualQtySpikes() < player.getChest().getMaxQtySpikes()) {
            if (player.getChest().getAllSpikes().stream().noneMatch(spike -> spike.getMetal().getNameLower().equals(metal.getNameLower()) && spike.getType() == TypeOfSpikeEnum.FERUCHEMIC)) {
                player.getChest().addSpikeRandom(new SpikeEntity(metal, TypeOfSpikeEnum.FERUCHEMIC));
            }
        } else {
            System.out.println("No available body part to add feruchemic power");
        };

    }

    /**
     * Adds Feruchemic power to a specific body part and slot.
     *
     * @param metal   the type of metal to add Feruchemic power with
     * @param part    the body part to add Feruchemic power to
     * @param slotPos the position of the slot to add Feruchemic power to
     * @param slotNum the number of the slot to add Feruchemic power to
     */
    @Override
    public void addFeruchemicPower(MetalTagEnum metal, BodyPartEnum part, BodySlotEnum slotPos, int slotNum) {
        if (part.equals(BodyPartEnum.HEAD)) {
            this.player.getHead().addSpikeBySlot(new SpikeEntity(metal, TypeOfSpikeEnum.FERUCHEMIC), slotNum, slotPos);
        } else if (part.equals(BodyPartEnum.CHEST)) {
            this.player.getChest().addSpikeBySlot(new SpikeEntity(metal, TypeOfSpikeEnum.FERUCHEMIC), slotNum, slotPos);
        } else if (part.equals(BodyPartEnum.ARMS)) {
            this.player.getArms().addSpikeBySlot(new SpikeEntity(metal, TypeOfSpikeEnum.FERUCHEMIC), slotNum, slotPos);
        } else if (part.equals(BodyPartEnum.BACK)) {
            this.player.getBack().addSpikeBySlot(new SpikeEntity(metal, TypeOfSpikeEnum.FERUCHEMIC), slotNum, slotPos);
        } else if (part.equals(BodyPartEnum.LEGS)) {
            this.player.getLegs().addSpikeBySlot(new SpikeEntity(metal, TypeOfSpikeEnum.FERUCHEMIC), slotNum, slotPos);
        } else {
            Optional<BodyPartEntity> partEntity = Stream.of(player.getLegs(), player.getHead(), player.getBack(), player.getArms(), player.getChest())
                    .filter(bodyPart -> bodyPart.getActualQtySpikes() < bodyPart.getMaxQtySpikes()).findFirst();

            if (partEntity.isPresent()) {
                partEntity.get().addSpikeRandom(new SpikeEntity(metal, TypeOfSpikeEnum.FERUCHEMIC));
            }
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
            System.out.println("Agregado metal alomantico: " + metal.getNameLower());
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
            System.out.println("Agregado metal feruquimico: " + metal.getNameLower());
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
                .forEach(bodyPart -> bodyPart.removeSpikeBySpikeEntity(new SpikeEntity(metal, TypeOfSpikeEnum.ALLOMANTIC)));
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
                .forEach(bodyPart -> bodyPart.removeSpikeBySpikeEntity(new SpikeEntity(metal, TypeOfSpikeEnum.FERUCHEMIC)));
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

    /**
     * Returns the count of metals that can be tapped.
     *
     * @return The number of metals that can be tapped.
     */
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

    /**
     * Calculates the number of metals that can be stored.
     *
     * @return The number of metals that can be stored.
     */
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

    /**
     * Sets the Ettmetal state for the player.
     *
     * @param state the Ettmetal state to set
     */
    @Override
    public void setEttmetalState(EttmetalStateEnum state) {
        this.player.setEttmetalState(state);
    }

    /**
     * Returns the current state of the "Ettmetal" for the player.
     *
     * @return the Ettmetal state of the player
     */
    @Override
    public EttmetalStateEnum getEttmetalState() {
        return this.player.getEttmetalState();
    }

    /**
     * Checks if the given metal has an Allomantic amount.
     *
     * @param metal The metal to check for Allomantic amount.
     * @return {@code true} if the metal has an Allomantic amount, {@code false} otherwise.
     */
    @Override
    public boolean hasAllomanticAmountOf(MetalTagEnum metal) {
        return (this.getAllomanticAmount(metal) > 0);
    }

    /**
     * Returns the player data.
     *
     * @return The player entity containing the data.
     */
    @Override
    public PlayerEntity getPlayerData() {
        return player;
    }


    /**
     * Saves the player's data in a CompoundTag.
     *
     * @return CompoundTag object containing the player's data
     */
    @Override
    public CompoundTag save() {

        CompoundTag playerData = new CompoundTag();
        CompoundTag head = new CompoundTag();
        CompoundTag chest = new CompoundTag();
        CompoundTag back = new CompoundTag();
        CompoundTag arms = new CompoundTag();
        CompoundTag legs = new CompoundTag();
        CompoundTag spikes = new CompoundTag();
        CompoundTag metalminds = new CompoundTag();
        CompoundTag extraData = new CompoundTag();
        CompoundTag reserve = new CompoundTag();
        CompoundTag burningMetals = new CompoundTag();
        CompoundTag tappingMetals = new CompoundTag();
        CompoundTag storingMetals = new CompoundTag();
        CompoundTag enhanced = new CompoundTag();
        CompoundTag metalEnhanced = new CompoundTag();



        if (player.getHead() != null) {
            if (player.getHead().getSpikeBySlot(0, BodySlotEnum.FRONT) != null) {
                head.putString("head_front_0", MetalTagsUtils.getStringBySpike(player.getHead().getSpikeBySlot(0, BodySlotEnum.FRONT)));
            } else {
                head.putString("head_front_0", "empty");
            }
            if (player.getHead().getSpikeBySlot(1, BodySlotEnum.FRONT) != null) {
                head.putString("head_front_1", MetalTagsUtils.getStringBySpike(player.getHead().getSpikeBySlot(1, BodySlotEnum.FRONT)));
            } else {
                head.putString("head_front_1", "empty");
            }
            if (player.getHead().getSpikeBySlot(0, BodySlotEnum.BACK) != null) {
                head.putString("head_back_0", MetalTagsUtils.getStringBySpike(player.getHead().getSpikeBySlot(0, BodySlotEnum.BACK)));
            } else {
                head.putString("head_back_0", "empty");
            }
        }
        if (player.getChest() != null) {
            if (player.getChest().getSpikeBySlot(0, BodySlotEnum.FRONT) != null) {
                chest.putString("chest_front_0", MetalTagsUtils.getStringBySpike(player.getChest().getSpikeBySlot(0, BodySlotEnum.FRONT)));
            } else {
                chest.putString("chest_front_0", "empty");
            }
            if (player.getChest().getSpikeBySlot(1, BodySlotEnum.FRONT) != null) {
                chest.putString("chest_front_1", MetalTagsUtils.getStringBySpike(player.getChest().getSpikeBySlot(1, BodySlotEnum.FRONT)));
            } else {
                chest.putString("chest_front_1", "empty");
            }
            if (player.getChest().getSpikeBySlot(2, BodySlotEnum.FRONT) != null) {
                chest.putString("chest_front_2", MetalTagsUtils.getStringBySpike(player.getChest().getSpikeBySlot(2, BodySlotEnum.FRONT)));
            } else {
                chest.putString("chest_front_2", "empty");
            }
            if (player.getChest().getSpikeBySlot(3, BodySlotEnum.FRONT) != null) {
                chest.putString("chest_front_3", MetalTagsUtils.getStringBySpike(player.getChest().getSpikeBySlot(3, BodySlotEnum.FRONT)));
            } else {
                chest.putString("chest_front_3", "empty");
            }
            if (player.getChest().getSpikeBySlot(4, BodySlotEnum.FRONT) != null) {
                chest.putString("chest_front_4", MetalTagsUtils.getStringBySpike(player.getChest().getSpikeBySlot(4, BodySlotEnum.FRONT)));
            } else {
                chest.putString("chest_front_4", "empty");
            }
            if (player.getChest().getSpikeBySlot(5, BodySlotEnum.FRONT) != null) {
                chest.putString("chest_front_5", MetalTagsUtils.getStringBySpike(player.getChest().getSpikeBySlot(5, BodySlotEnum.FRONT)));
            } else {
                chest.putString("chest_front_5", "empty");
            }

        }
        if (player.getBack() != null) {
            if (player.getBack().getSpikeBySlot(0, BodySlotEnum.BACK) != null) {
                back.putString("back_back_0", MetalTagsUtils.getStringBySpike(player.getBack().getSpikeBySlot(0, BodySlotEnum.BACK)));
            } else {
                back.putString("back_back_0", "empty");
            }
            if (player.getBack().getSpikeBySlot(1, BodySlotEnum.BACK) != null) {
                back.putString("back_back_1", MetalTagsUtils.getStringBySpike(player.getBack().getSpikeBySlot(1, BodySlotEnum.BACK)));
            } else {
                back.putString("back_back_1", "empty");
            }
            if (player.getBack().getSpikeBySlot(2, BodySlotEnum.BACK) != null) {
                back.putString("back_back_2", MetalTagsUtils.getStringBySpike(player.getBack().getSpikeBySlot(2, BodySlotEnum.BACK)));
            } else {
                back.putString("back_back_2", "empty");
            }
            if (player.getBack().getSpikeBySlot(3, BodySlotEnum.BACK) != null) {
                back.putString("back_back_3", MetalTagsUtils.getStringBySpike(player.getBack().getSpikeBySlot(3, BodySlotEnum.BACK)));
            } else {
                back.putString("back_back_3", "empty");
            }
            if (player.getBack().getSpikeBySlot(4, BodySlotEnum.BACK) != null) {
                back.putString("back_back_4", MetalTagsUtils.getStringBySpike(player.getBack().getSpikeBySlot(4, BodySlotEnum.BACK)));
            } else {
                back.putString("back_back_4", "empty");
            }
            if (player.getBack().getSpikeBySlot(5, BodySlotEnum.BACK) != null) {
                back.putString("back_back_5", MetalTagsUtils.getStringBySpike(player.getBack().getSpikeBySlot(5, BodySlotEnum.BACK)));
            } else {
                back.putString("back_back_5", "empty");
            }
            if (player.getBack().getSpikeBySlot(6, BodySlotEnum.BACK) != null) {
                back.putString("back_back_6", MetalTagsUtils.getStringBySpike(player.getBack().getSpikeBySlot(6, BodySlotEnum.BACK)));
            } else {
                back.putString("back_back_6", "empty");
            }
        }

        if (player.getArms() != null) {
            if (player.getArms().getSpikeBySlot(0, BodySlotEnum.FRONT) != null) {
                arms.putString("arms_front_0", MetalTagsUtils.getStringBySpike(player.getArms().getSpikeBySlot(0, BodySlotEnum.FRONT)));
            } else {
                arms.putString("arms_front_0", "empty");
            }
            if (player.getArms().getSpikeBySlot(1, BodySlotEnum.FRONT) != null) {
                arms.putString("arms_front_1", MetalTagsUtils.getStringBySpike(player.getArms().getSpikeBySlot(1, BodySlotEnum.FRONT)));
            } else {
                arms.putString("arms_front_1", "empty");
            }
            if (player.getArms().getSpikeBySlot(2, BodySlotEnum.FRONT) != null) {
                arms.putString("arms_front_2", MetalTagsUtils.getStringBySpike(player.getArms().getSpikeBySlot(2, BodySlotEnum.FRONT)));
            } else {
                arms.putString("arms_front_2", "empty");
            }
            if (player.getArms().getSpikeBySlot(3, BodySlotEnum.FRONT) != null) {
                arms.putString("arms_front_3", MetalTagsUtils.getStringBySpike(player.getArms().getSpikeBySlot(3, BodySlotEnum.FRONT)));
            } else {
                arms.putString("arms_front_3", "empty");
            }
            if (player.getArms().getSpikeBySlot(4, BodySlotEnum.FRONT) != null) {
                arms.putString("arms_front_4", MetalTagsUtils.getStringBySpike(player.getArms().getSpikeBySlot(4, BodySlotEnum.FRONT)));
            } else {
                arms.putString("arms_front_4", "empty");
            }
            if (player.getArms().getSpikeBySlot(5, BodySlotEnum.FRONT) != null) {
                arms.putString("arms_front_5", MetalTagsUtils.getStringBySpike(player.getArms().getSpikeBySlot(5, BodySlotEnum.FRONT)));
            } else {
                arms.putString("arms_front_5", "empty");
            }
            if (player.getArms().getSpikeBySlot(0, BodySlotEnum.BACK) != null) {
                arms.putString("arms_back_0", MetalTagsUtils.getStringBySpike(player.getArms().getSpikeBySlot(0, BodySlotEnum.BACK)));
            } else {
                arms.putString("arms_back_0", "empty");
            }
            if (player.getArms().getSpikeBySlot(1, BodySlotEnum.BACK) != null) {
                arms.putString("arms_back_1", MetalTagsUtils.getStringBySpike(player.getArms().getSpikeBySlot(1, BodySlotEnum.BACK)));
            } else {
                arms.putString("arms_back_1", "empty");
            }
            if (player.getArms().getSpikeBySlot(2, BodySlotEnum.BACK) != null) {
                arms.putString("arms_back_2", MetalTagsUtils.getStringBySpike(player.getArms().getSpikeBySlot(2, BodySlotEnum.BACK)));
            } else {
                arms.putString("arms_back_2", "empty");
            }
            if (player.getArms().getSpikeBySlot(3, BodySlotEnum.BACK) != null) {
                arms.putString("arms_back_3", MetalTagsUtils.getStringBySpike(player.getArms().getSpikeBySlot(3, BodySlotEnum.BACK)));
            } else {
                arms.putString("arms_back_3", "empty");
            }
            if (player.getArms().getSpikeBySlot(4, BodySlotEnum.BACK) != null) {
                arms.putString("arms_back_4", MetalTagsUtils.getStringBySpike(player.getArms().getSpikeBySlot(4, BodySlotEnum.BACK)));
            } else {
                arms.putString("arms_back_4", "empty");
            }
            if (player.getArms().getSpikeBySlot(5, BodySlotEnum.BACK) != null) {
                arms.putString("arms_back_5", MetalTagsUtils.getStringBySpike(player.getArms().getSpikeBySlot(5, BodySlotEnum.BACK)));
            } else {
                arms.putString("arms_back_5", "empty");
            }
        }

        if (player.getLegs() != null) {
            if (player.getLegs().getSpikeBySlot(0, BodySlotEnum.FRONT) != null) {
                legs.putString("legs_front_0", MetalTagsUtils.getStringBySpike(player.getLegs().getSpikeBySlot(0, BodySlotEnum.FRONT)));
            } else {
                legs.putString("legs_front_0", "empty");
            }
            if (player.getLegs().getSpikeBySlot(1, BodySlotEnum.FRONT) != null) {
                legs.putString("legs_front_1", MetalTagsUtils.getStringBySpike(player.getLegs().getSpikeBySlot(1, BodySlotEnum.FRONT)));
            } else {
                legs.putString("legs_front_1", "empty");
            }
            if (player.getLegs().getSpikeBySlot(2, BodySlotEnum.FRONT) != null) {
                legs.putString("legs_front_2", MetalTagsUtils.getStringBySpike(player.getLegs().getSpikeBySlot(2, BodySlotEnum.FRONT)));
            } else {
                legs.putString("legs_front_2", "empty");
            }
            if (player.getLegs().getSpikeBySlot(3, BodySlotEnum.FRONT) != null) {
                legs.putString("legs_front_3", MetalTagsUtils.getStringBySpike(player.getLegs().getSpikeBySlot(3, BodySlotEnum.FRONT)));
            } else {
                legs.putString("legs_front_3", "empty");
            }
            if (player.getLegs().getSpikeBySlot(4, BodySlotEnum.FRONT) != null) {
                legs.putString("legs_front_4", MetalTagsUtils.getStringBySpike(player.getLegs().getSpikeBySlot(4, BodySlotEnum.FRONT)));
            } else {
                legs.putString("legs_front_4", "empty");
            }
            if (player.getLegs().getSpikeBySlot(5, BodySlotEnum.FRONT) != null) {
                legs.putString("legs_front_5", MetalTagsUtils.getStringBySpike(player.getLegs().getSpikeBySlot(5, BodySlotEnum.FRONT)));
            } else {
                legs.putString("legs_front_5", "empty");
            }
            if (player.getLegs().getSpikeBySlot(0, BodySlotEnum.BACK) != null) {
                legs.putString("legs_back_0", MetalTagsUtils.getStringBySpike(player.getLegs().getSpikeBySlot(0, BodySlotEnum.BACK)));
            } else {
                legs.putString("legs_back_0", "empty");
            }
            if (player.getLegs().getSpikeBySlot(1, BodySlotEnum.BACK) != null) {
                legs.putString("legs_back_1", MetalTagsUtils.getStringBySpike(player.getLegs().getSpikeBySlot(1, BodySlotEnum.BACK)));
            } else {
                legs.putString("legs_back_1", "empty");
            }
            if (player.getLegs().getSpikeBySlot(2, BodySlotEnum.BACK) != null) {
                legs.putString("legs_back_2", MetalTagsUtils.getStringBySpike(player.getLegs().getSpikeBySlot(2, BodySlotEnum.BACK)));
            } else {
                legs.putString("legs_back_2", "empty");
            }
            if (player.getLegs().getSpikeBySlot(3, BodySlotEnum.BACK) != null) {
                legs.putString("legs_back_3", MetalTagsUtils.getStringBySpike(player.getLegs().getSpikeBySlot(3, BodySlotEnum.BACK)));
            } else {
                legs.putString("legs_back_3", "empty");
            }
            if (player.getLegs().getSpikeBySlot(4, BodySlotEnum.BACK) != null) {
                legs.putString("legs_back_4", MetalTagsUtils.getStringBySpike(player.getLegs().getSpikeBySlot(4, BodySlotEnum.BACK)));
            } else {
                legs.putString("legs_back_4", "empty");
            }
            if (player.getLegs().getSpikeBySlot(5, BodySlotEnum.BACK) != null) {
                legs.putString("legs_back_5", MetalTagsUtils.getStringBySpike(player.getLegs().getSpikeBySlot(5, BodySlotEnum.BACK)));
            } else {
                legs.putString("legs_back_5", "empty");
            }
        }
        spikes.put("body_head", head);
        spikes.put("body_chest", chest);
        spikes.put("body_back", back);
        spikes.put("body_arms", arms);
        spikes.put("body_legs", legs);
        playerData.put("spikes", spikes);

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
     * Loads data from the given CompoundTag and adds spikes to the player's head and body slots.
     *
     * @param playerData the CompoundTag containing the player data
     */
    @Override
    public void load(CompoundTag playerData) {
        CompoundTag spikes = playerData.getCompound("spikes");
        CompoundTag head = spikes.getCompound("body_head");
        CompoundTag back = spikes.getCompound("body_back");
        CompoundTag chest = spikes.getCompound("body_chest");
        CompoundTag legs = spikes.getCompound("body_legs");
        CompoundTag arms = spikes.getCompound("body_arms");
        CompoundTag metalminds = playerData.getCompound("metalminds");
        CompoundTag extraData = playerData.getCompound("extra_data");
        CompoundTag reserve = extraData.getCompound("reserve");
        CompoundTag burning = extraData.getCompound("burning");
        CompoundTag tapping= extraData.getCompound("tapping");
        CompoundTag storing = extraData.getCompound("storing");
        CompoundTag enhanced = extraData.getCompound("enhanced_data");
        CompoundTag metalEnhanced = extraData.getCompound("metal_enhanced");

        if (head.getString("head_front_0") != null) {
            if (!head.getString("head_front_0").equals("empty")) {
                if (head.getString("head_front_0").contains("allomantic")) {
                    player.getHead().addSpikeBySlot(new SpikeEntity(MetalTagsUtils.getMetalTagEnumByString(head.getString("head_front_0")), TypeOfSpikeEnum.ALLOMANTIC), 0, BodySlotEnum.FRONT);
                } else {
                    player.getHead().addSpikeBySlot(new SpikeEntity(MetalTagsUtils.getMetalTagEnumByString(head.getString("head_front_0")), TypeOfSpikeEnum.FERUCHEMIC), 0, BodySlotEnum.FRONT);
                }
            }
        }

        if (head.getString("head_back_0") != null) {
            if (!head.getString("head_back_0").equals("empty")) {
                if (head.getString("head_back_0").contains("allomantic")) {
                    player.getHead().addSpikeBySlot(new SpikeEntity(MetalTagsUtils.getMetalTagEnumByString(head.getString("head_back_0")), TypeOfSpikeEnum.ALLOMANTIC), 0, BodySlotEnum.FRONT);
                } else {
                    player.getHead().addSpikeBySlot(new SpikeEntity(MetalTagsUtils.getMetalTagEnumByString(head.getString("head_back_0")), TypeOfSpikeEnum.FERUCHEMIC), 0, BodySlotEnum.FRONT);
                }
            }
        }

        if (head.getString("head_front_1") != null) {
            if (!head.getString("head_front_1").equals("empty")) {
                if (head.getString("head_front_1").contains("allomantic")) {
                    player.getHead().addSpikeBySlot(new SpikeEntity(MetalTagsUtils.getMetalTagEnumByString(head.getString("head_front_1")), TypeOfSpikeEnum.ALLOMANTIC), 1, BodySlotEnum.FRONT);
                } else {
                    player.getHead().addSpikeBySlot(new SpikeEntity(MetalTagsUtils.getMetalTagEnumByString(head.getString("head_front_1")), TypeOfSpikeEnum.FERUCHEMIC), 1, BodySlotEnum.FRONT);
                }
            }
        }

        if (back.getString("back_back_0") != null) {
            if (!back.getString("back_back_0").equals("empty")) {
                if (back.getString("back_back_0").contains("allomantic")) {
                    player.getBack().addSpikeBySlot(new SpikeEntity(MetalTagsUtils.getMetalTagEnumByString(back.getString("back_back_0")), TypeOfSpikeEnum.ALLOMANTIC), 0, BodySlotEnum.BACK);
                } else {
                    player.getBack().addSpikeBySlot(new SpikeEntity(MetalTagsUtils.getMetalTagEnumByString(back.getString("back_back_0")), TypeOfSpikeEnum.FERUCHEMIC), 0, BodySlotEnum.BACK);
                }
            }
        }

        if (back.getString("back_back_1") != null) {
            if (!back.getString("back_back_1").equals("empty")) {
                if (back.getString("back_back_1").contains("allomantic")) {
                    player.getBack().addSpikeBySlot(new SpikeEntity(MetalTagsUtils.getMetalTagEnumByString(back.getString("back_back_1")), TypeOfSpikeEnum.ALLOMANTIC), 1, BodySlotEnum.BACK);
                } else {
                    player.getBack().addSpikeBySlot(new SpikeEntity(MetalTagsUtils.getMetalTagEnumByString(back.getString("back_back_1")), TypeOfSpikeEnum.FERUCHEMIC), 1, BodySlotEnum.BACK);
                }
            }
        }

        if (back.getString("back_back_2") != null) {
            if (!back.getString("back_back_2").equals("empty")) {
                if (back.getString("back_back_2").contains("allomantic")) {
                    player.getBack().addSpikeBySlot(new SpikeEntity(MetalTagsUtils.getMetalTagEnumByString(back.getString("back_back_2")), TypeOfSpikeEnum.ALLOMANTIC), 2, BodySlotEnum.BACK);
                } else {
                    player.getBack().addSpikeBySlot(new SpikeEntity(MetalTagsUtils.getMetalTagEnumByString(back.getString("back_back_2")), TypeOfSpikeEnum.FERUCHEMIC), 2, BodySlotEnum.BACK);
                }
            }
        }

        if (back.getString("back_back_3") != null) {
            if (!back.getString("back_back_3").equals("empty")) {
                if (back.getString("back_back_3").contains("allomantic")) {
                    player.getBack().addSpikeBySlot(new SpikeEntity(MetalTagsUtils.getMetalTagEnumByString(back.getString("back_back_3")), TypeOfSpikeEnum.ALLOMANTIC), 3, BodySlotEnum.BACK);
                } else {
                    player.getBack().addSpikeBySlot(new SpikeEntity(MetalTagsUtils.getMetalTagEnumByString(back.getString("back_back_3")), TypeOfSpikeEnum.FERUCHEMIC), 3, BodySlotEnum.BACK);
                }
            }
        }

        if (back.getString("back_back_4") != null) {
            if (!back.getString("back_back_4").equals("empty")) {
                if (back.getString("back_back_4").contains("allomantic")) {
                    player.getBack().addSpikeBySlot(new SpikeEntity(MetalTagsUtils.getMetalTagEnumByString(back.getString("back_back_4")), TypeOfSpikeEnum.ALLOMANTIC), 4, BodySlotEnum.BACK);
                } else {
                    player.getBack().addSpikeBySlot(new SpikeEntity(MetalTagsUtils.getMetalTagEnumByString(back.getString("back_back_4")), TypeOfSpikeEnum.FERUCHEMIC), 4, BodySlotEnum.BACK);
                }
            }
        }

        if (back.getString("back_back_5") != null) {
            if (!back.getString("back_back_5").equals("empty")) {
                if (back.getString("back_back_5").contains("allomantic")) {
                    player.getBack().addSpikeBySlot(new SpikeEntity(MetalTagsUtils.getMetalTagEnumByString(back.getString("back_back_5")), TypeOfSpikeEnum.ALLOMANTIC), 5, BodySlotEnum.BACK);
                } else {
                    player.getBack().addSpikeBySlot(new SpikeEntity(MetalTagsUtils.getMetalTagEnumByString(back.getString("back_back_5")), TypeOfSpikeEnum.FERUCHEMIC), 5, BodySlotEnum.BACK);
                }
            }
        }

        if (back.getString("back_back_6") != null) {
            if (!back.getString("back_back_6").equals("empty")) {
                if (back.getString("back_back_6").contains("allomantic")) {
                    player.getBack().addSpikeBySlot(new SpikeEntity(MetalTagsUtils.getMetalTagEnumByString(back.getString("back_back_6")), TypeOfSpikeEnum.ALLOMANTIC), 6, BodySlotEnum.BACK);
                } else {
                    player.getBack().addSpikeBySlot(new SpikeEntity(MetalTagsUtils.getMetalTagEnumByString(back.getString("back_back_6")), TypeOfSpikeEnum.FERUCHEMIC), 6, BodySlotEnum.BACK);
                }
            }
        }


        if (chest.getString("chest_front_0") != null) {
            if (!chest.getString("chest_front_0").equals("empty")) {
                if (chest.getString("chest_front_0").contains("allomantic")) {
                    player.getChest().addSpikeBySlot(new SpikeEntity(MetalTagsUtils.getMetalTagEnumByString(chest.getString("chest_front_0")), TypeOfSpikeEnum.ALLOMANTIC), 0, BodySlotEnum.FRONT);
                } else {
                    player.getChest().addSpikeBySlot(new SpikeEntity(MetalTagsUtils.getMetalTagEnumByString(chest.getString("chest_front_0")), TypeOfSpikeEnum.FERUCHEMIC), 0, BodySlotEnum.FRONT);
                }
            }
        }

        if (chest.getString("chest_front_1") != null) {
            if (!chest.getString("chest_front_1").equals("empty")) {
                if (chest.getString("chest_front_1").contains("allomantic")) {
                    player.getChest().addSpikeBySlot(new SpikeEntity(MetalTagsUtils.getMetalTagEnumByString(chest.getString("chest_front_1")), TypeOfSpikeEnum.ALLOMANTIC), 1, BodySlotEnum.FRONT);
                } else {
                    player.getChest().addSpikeBySlot(new SpikeEntity(MetalTagsUtils.getMetalTagEnumByString(chest.getString("chest_front_1")), TypeOfSpikeEnum.FERUCHEMIC), 1, BodySlotEnum.FRONT);
                }
            }
        }

        if (chest.getString("chest_front_2") != null) {
            if (!chest.getString("chest_front_2").equals("empty")) {
                if (chest.getString("chest_front_2").contains("allomantic")) {
                    player.getChest().addSpikeBySlot(new SpikeEntity(MetalTagsUtils.getMetalTagEnumByString(chest.getString("chest_front_2")), TypeOfSpikeEnum.ALLOMANTIC), 2, BodySlotEnum.FRONT);
                } else {
                    player.getChest().addSpikeBySlot(new SpikeEntity(MetalTagsUtils.getMetalTagEnumByString(chest.getString("chest_front_2")), TypeOfSpikeEnum.FERUCHEMIC), 2, BodySlotEnum.FRONT);
                }
            }
        }

        if (chest.getString("chest_front_3") != null) {
            if (!chest.getString("chest_front_3").equals("empty")) {
                if (chest.getString("chest_front_3").contains("allomantic")) {
                    player.getChest().addSpikeBySlot(new SpikeEntity(MetalTagsUtils.getMetalTagEnumByString(chest.getString("chest_front_3")), TypeOfSpikeEnum.ALLOMANTIC), 3, BodySlotEnum.FRONT);
                } else {
                    player.getChest().addSpikeBySlot(new SpikeEntity(MetalTagsUtils.getMetalTagEnumByString(chest.getString("chest_front_3")), TypeOfSpikeEnum.FERUCHEMIC), 3, BodySlotEnum.FRONT);
                }
            }
        }

        if (chest.getString("chest_front_4") != null) {
            if (!chest.getString("chest_front_4").equals("empty")) {
                if (chest.getString("chest_front_4").contains("allomantic")) {
                    player.getChest().addSpikeBySlot(new SpikeEntity(MetalTagsUtils.getMetalTagEnumByString(chest.getString("chest_front_4")), TypeOfSpikeEnum.ALLOMANTIC), 4, BodySlotEnum.FRONT);
                } else {
                    player.getChest().addSpikeBySlot(new SpikeEntity(MetalTagsUtils.getMetalTagEnumByString(chest.getString("chest_front_4")), TypeOfSpikeEnum.FERUCHEMIC), 4, BodySlotEnum.FRONT);
                }
            }
        }

        if (chest.getString("chest_front_5") != null) {
            if (!chest.getString("chest_front_5").equals("empty")) {
                if (chest.getString("chest_front_5").contains("allomantic")) {
                    player.getChest().addSpikeBySlot(new SpikeEntity(MetalTagsUtils.getMetalTagEnumByString(chest.getString("chest_front_5")), TypeOfSpikeEnum.ALLOMANTIC), 5, BodySlotEnum.FRONT);
                } else {
                    player.getChest().addSpikeBySlot(new SpikeEntity(MetalTagsUtils.getMetalTagEnumByString(chest.getString("chest_front_5")), TypeOfSpikeEnum.FERUCHEMIC), 5, BodySlotEnum.FRONT);
                }
            }
        }

        if (arms.getString("arms_front_0") != null) {
            if (!arms.getString("arms_front_0").equals("empty")) {
                if (arms.getString("arms_front_0").contains("allomantic")) {
                    player.getArms().addSpikeBySlot(new SpikeEntity(MetalTagsUtils.getMetalTagEnumByString(arms.getString("arms_front_0")), TypeOfSpikeEnum.ALLOMANTIC), 0, BodySlotEnum.FRONT);
                } else {
                    player.getArms().addSpikeBySlot(new SpikeEntity(MetalTagsUtils.getMetalTagEnumByString(arms.getString("arms_front_0")), TypeOfSpikeEnum.FERUCHEMIC), 0, BodySlotEnum.FRONT);
                }
            }
        }

        if (arms.getString("arms_front_1") != null) {
            if (!arms.getString("arms_front_1").equals("empty")) {
                if (arms.getString("arms_front_1").contains("allomantic")) {
                    player.getArms().addSpikeBySlot(new SpikeEntity(MetalTagsUtils.getMetalTagEnumByString(arms.getString("arms_front_1")), TypeOfSpikeEnum.ALLOMANTIC), 1, BodySlotEnum.FRONT);
                } else {
                    player.getArms().addSpikeBySlot(new SpikeEntity(MetalTagsUtils.getMetalTagEnumByString(arms.getString("arms_front_1")), TypeOfSpikeEnum.FERUCHEMIC), 1, BodySlotEnum.FRONT);
                }
            }
        }

        if (arms.getString("arms_front_2") != null) {
            if (!arms.getString("arms_front_2").equals("empty")) {
                if (arms.getString("arms_front_2").contains("allomantic")) {
                    player.getArms().addSpikeBySlot(new SpikeEntity(MetalTagsUtils.getMetalTagEnumByString(arms.getString("arms_front_2")), TypeOfSpikeEnum.ALLOMANTIC), 2, BodySlotEnum.FRONT);
                } else {
                    player.getArms().addSpikeBySlot(new SpikeEntity(MetalTagsUtils.getMetalTagEnumByString(arms.getString("arms_front_2")), TypeOfSpikeEnum.FERUCHEMIC), 2, BodySlotEnum.FRONT);
                }
            }
        }
        if (arms.getString("arms_front_3") != null) {
            if (!arms.getString("arms_front_3").equals("empty")) {
                if (arms.getString("arms_front_3").contains("allomantic")) {
                    player.getArms().addSpikeBySlot(new SpikeEntity(MetalTagsUtils.getMetalTagEnumByString(arms.getString("arms_front_3")), TypeOfSpikeEnum.ALLOMANTIC), 3, BodySlotEnum.FRONT);
                } else {
                    player.getArms().addSpikeBySlot(new SpikeEntity(MetalTagsUtils.getMetalTagEnumByString(arms.getString("arms_front_3")), TypeOfSpikeEnum.FERUCHEMIC), 3, BodySlotEnum.FRONT);
                }
            }
        }

        if (arms.getString("arms_front_4") != null) {
            if (!arms.getString("arms_front_4").equals("empty")) {
                if (arms.getString("arms_front_4").contains("allomantic")) {
                    player.getArms().addSpikeBySlot(new SpikeEntity(MetalTagsUtils.getMetalTagEnumByString(arms.getString("arms_front_4")), TypeOfSpikeEnum.ALLOMANTIC), 4, BodySlotEnum.FRONT);
                } else {
                    player.getArms().addSpikeBySlot(new SpikeEntity(MetalTagsUtils.getMetalTagEnumByString(arms.getString("arms_front_4")), TypeOfSpikeEnum.FERUCHEMIC), 4, BodySlotEnum.FRONT);
                }
            }
        }

        if (arms.getString("arms_front_5") != null) {
            if (!arms.getString("arms_front_5").equals("empty")) {
                if (arms.getString("arms_front_5").contains("allomantic")) {
                    player.getArms().addSpikeBySlot(new SpikeEntity(MetalTagsUtils.getMetalTagEnumByString(arms.getString("arms_front_5")), TypeOfSpikeEnum.ALLOMANTIC), 5, BodySlotEnum.FRONT);
                } else {
                    player.getArms().addSpikeBySlot(new SpikeEntity(MetalTagsUtils.getMetalTagEnumByString(arms.getString("arms_front_5")), TypeOfSpikeEnum.FERUCHEMIC), 5, BodySlotEnum.FRONT);
                }
            }
        }

        if (arms.getString("arms_back_0") != null) {
            if (!arms.getString("arms_back_0").equals("empty")) {
                if (arms.getString("arms_back_0").contains("allomantic")) {
                    player.getArms().addSpikeBySlot(new SpikeEntity(MetalTagsUtils.getMetalTagEnumByString(arms.getString("arms_back_0")), TypeOfSpikeEnum.ALLOMANTIC), 0, BodySlotEnum.BACK);
                } else {
                    player.getArms().addSpikeBySlot(new SpikeEntity(MetalTagsUtils.getMetalTagEnumByString(arms.getString("arms_back_0")), TypeOfSpikeEnum.FERUCHEMIC), 0, BodySlotEnum.BACK);
                }
            }
        }

        if (arms.getString("arms_back_1") != null) {
            if (!arms.getString("arms_back_1").equals("empty")) {
                if (arms.getString("arms_back_1").contains("allomantic")) {
                    player.getArms().addSpikeBySlot(new SpikeEntity(MetalTagsUtils.getMetalTagEnumByString(arms.getString("arms_back_1")), TypeOfSpikeEnum.ALLOMANTIC), 1, BodySlotEnum.BACK);
                } else {
                    player.getArms().addSpikeBySlot(new SpikeEntity(MetalTagsUtils.getMetalTagEnumByString(arms.getString("arms_back_1")), TypeOfSpikeEnum.FERUCHEMIC), 1, BodySlotEnum.BACK);
                }
            }
        }

        if (arms.getString("arms_back_2") != null) {
            if (!arms.getString("arms_back_2").equals("empty")) {
                if (arms.getString("arms_back_2").contains("allomantic")) {
                    player.getArms().addSpikeBySlot(new SpikeEntity(MetalTagsUtils.getMetalTagEnumByString(arms.getString("arms_back_2")), TypeOfSpikeEnum.ALLOMANTIC), 2, BodySlotEnum.BACK);
                } else {
                    player.getArms().addSpikeBySlot(new SpikeEntity(MetalTagsUtils.getMetalTagEnumByString(arms.getString("arms_back_2")), TypeOfSpikeEnum.FERUCHEMIC), 2, BodySlotEnum.BACK);
                }
            }
        }
        if (arms.getString("arms_back_3") != null) {
            if (!arms.getString("arms_back_3").equals("empty")) {
                if (arms.getString("arms_back_3").contains("allomantic")) {
                    player.getArms().addSpikeBySlot(new SpikeEntity(MetalTagsUtils.getMetalTagEnumByString(arms.getString("arms_back_3")), TypeOfSpikeEnum.ALLOMANTIC), 3, BodySlotEnum.BACK);
                } else {
                    player.getArms().addSpikeBySlot(new SpikeEntity(MetalTagsUtils.getMetalTagEnumByString(arms.getString("arms_back_3")), TypeOfSpikeEnum.FERUCHEMIC), 3, BodySlotEnum.BACK);
                }
            }
        }

        if (arms.getString("arms_back_4") != null) {
            if (!arms.getString("arms_back_4").equals("empty")) {
                if (arms.getString("arms_back_4").contains("allomantic")) {
                    player.getArms().addSpikeBySlot(new SpikeEntity(MetalTagsUtils.getMetalTagEnumByString(arms.getString("arms_back_4")), TypeOfSpikeEnum.ALLOMANTIC), 4, BodySlotEnum.BACK);
                } else {
                    player.getArms().addSpikeBySlot(new SpikeEntity(MetalTagsUtils.getMetalTagEnumByString(arms.getString("arms_back_4")), TypeOfSpikeEnum.FERUCHEMIC), 4, BodySlotEnum.BACK);
                }
            }
        }

        if (arms.getString("arms_back_5") != null) {
            if (!arms.getString("arms_back_5").equals("empty")) {
                if (arms.getString("arms_back_5").contains("allomantic")) {
                    player.getArms().addSpikeBySlot(new SpikeEntity(MetalTagsUtils.getMetalTagEnumByString(arms.getString("arms_back_5")), TypeOfSpikeEnum.ALLOMANTIC), 5, BodySlotEnum.BACK);
                } else {
                    player.getArms().addSpikeBySlot(new SpikeEntity(MetalTagsUtils.getMetalTagEnumByString(arms.getString("arms_back_5")), TypeOfSpikeEnum.FERUCHEMIC), 5, BodySlotEnum.BACK);
                }
            }
        }

        if (legs.getString("legs_front_0") != null) {
            if (!legs.getString("legs_front_0").equals("empty")) {
                if (legs.getString("legs_front_0").contains("allomantic")) {
                    player.getLegs().addSpikeBySlot(new SpikeEntity(MetalTagsUtils.getMetalTagEnumByString(legs.getString("legs_front_0")), TypeOfSpikeEnum.ALLOMANTIC), 0, BodySlotEnum.FRONT);
                } else {
                    player.getLegs().addSpikeBySlot(new SpikeEntity(MetalTagsUtils.getMetalTagEnumByString(legs.getString("legs_front_0")), TypeOfSpikeEnum.FERUCHEMIC), 0, BodySlotEnum.FRONT);
                }
            }
        }

        if (legs.getString("legs_front_1") != null) {
            if (!legs.getString("legs_front_1").equals("empty")) {
                if (legs.getString("legs_front_1").contains("allomantic")) {
                    player.getLegs().addSpikeBySlot(new SpikeEntity(MetalTagsUtils.getMetalTagEnumByString(legs.getString("legs_front_1")), TypeOfSpikeEnum.ALLOMANTIC), 1, BodySlotEnum.FRONT);
                } else {
                    player.getLegs().addSpikeBySlot(new SpikeEntity(MetalTagsUtils.getMetalTagEnumByString(legs.getString("legs_front_1")), TypeOfSpikeEnum.FERUCHEMIC), 1, BodySlotEnum.FRONT);
                }
            }
        }

        if (legs.getString("legs_front_2") != null) {
            if (!legs.getString("legs_front_2").equals("empty")) {
                if (legs.getString("legs_front_2").contains("allomantic")) {
                    player.getLegs().addSpikeBySlot(new SpikeEntity(MetalTagsUtils.getMetalTagEnumByString(legs.getString("legs_front_2")), TypeOfSpikeEnum.ALLOMANTIC), 2, BodySlotEnum.FRONT);
                } else {
                    player.getLegs().addSpikeBySlot(new SpikeEntity(MetalTagsUtils.getMetalTagEnumByString(legs.getString("legs_front_2")), TypeOfSpikeEnum.FERUCHEMIC), 2, BodySlotEnum.FRONT);
                }
            }
        }
        if (legs.getString("legs_front_3") != null) {
            if (!legs.getString("legs_front_3").equals("empty")) {
                if (legs.getString("legs_front_3").contains("allomantic")) {
                    player.getLegs().addSpikeBySlot(new SpikeEntity(MetalTagsUtils.getMetalTagEnumByString(legs.getString("legs_front_3")), TypeOfSpikeEnum.ALLOMANTIC), 3, BodySlotEnum.FRONT);
                } else {
                    player.getLegs().addSpikeBySlot(new SpikeEntity(MetalTagsUtils.getMetalTagEnumByString(legs.getString("legs_front_3")), TypeOfSpikeEnum.FERUCHEMIC), 3, BodySlotEnum.FRONT);
                }
            }
        }

        if (legs.getString("legs_front_4") != null) {
            if (!legs.getString("legs_front_4").equals("empty")) {
                if (legs.getString("legs_front_4").contains("allomantic")) {
                    player.getLegs().addSpikeBySlot(new SpikeEntity(MetalTagsUtils.getMetalTagEnumByString(legs.getString("legs_front_4")), TypeOfSpikeEnum.ALLOMANTIC), 4, BodySlotEnum.FRONT);
                } else {
                    player.getLegs().addSpikeBySlot(new SpikeEntity(MetalTagsUtils.getMetalTagEnumByString(legs.getString("legs_front_4")), TypeOfSpikeEnum.FERUCHEMIC), 4, BodySlotEnum.FRONT);
                }
            }
        }

        if (legs.getString("legs_front_5") != null) {
            if (!legs.getString("legs_front_5").equals("empty")) {
                if (legs.getString("legs_front_5").contains("allomantic")) {
                    player.getLegs().addSpikeBySlot(new SpikeEntity(MetalTagsUtils.getMetalTagEnumByString(legs.getString("legs_front_5")), TypeOfSpikeEnum.ALLOMANTIC), 5, BodySlotEnum.FRONT);
                } else {
                    player.getLegs().addSpikeBySlot(new SpikeEntity(MetalTagsUtils.getMetalTagEnumByString(legs.getString("legs_front_5")), TypeOfSpikeEnum.FERUCHEMIC), 5, BodySlotEnum.FRONT);
                }
            }
        }

        if (legs.getString("legs_back_0") != null) {
            if (!legs.getString("legs_back_0").equals("empty")) {
                if (legs.getString("legs_back_0").contains("allomantic")) {
                    player.getLegs().addSpikeBySlot(new SpikeEntity(MetalTagsUtils.getMetalTagEnumByString(legs.getString("legs_back_0")), TypeOfSpikeEnum.ALLOMANTIC), 0, BodySlotEnum.BACK);
                } else {
                    player.getLegs().addSpikeBySlot(new SpikeEntity(MetalTagsUtils.getMetalTagEnumByString(legs.getString("legs_back_0")), TypeOfSpikeEnum.FERUCHEMIC), 0, BodySlotEnum.BACK);
                }
            }
        }

        if (legs.getString("legs_back_1") != null) {
            if (!legs.getString("legs_back_1").equals("empty")) {
                if (legs.getString("legs_back_1").contains("allomantic")) {
                    player.getLegs().addSpikeBySlot(new SpikeEntity(MetalTagsUtils.getMetalTagEnumByString(legs.getString("legs_back_1")), TypeOfSpikeEnum.ALLOMANTIC), 1, BodySlotEnum.BACK);
                } else {
                    player.getLegs().addSpikeBySlot(new SpikeEntity(MetalTagsUtils.getMetalTagEnumByString(legs.getString("legs_back_1")), TypeOfSpikeEnum.FERUCHEMIC), 1, BodySlotEnum.BACK);
                }
            }
        }

        if (legs.getString("legs_back_2") != null) {
            if (!legs.getString("legs_back_2").equals("empty")) {
                if (legs.getString("legs_back_2").contains("allomantic")) {
                    player.getLegs().addSpikeBySlot(new SpikeEntity(MetalTagsUtils.getMetalTagEnumByString(legs.getString("legs_back_2")), TypeOfSpikeEnum.ALLOMANTIC), 2, BodySlotEnum.BACK);
                } else {
                    player.getLegs().addSpikeBySlot(new SpikeEntity(MetalTagsUtils.getMetalTagEnumByString(legs.getString("legs_back_2")), TypeOfSpikeEnum.FERUCHEMIC), 2, BodySlotEnum.BACK);
                }
            }
        }
        if (legs.getString("legs_back_3") != null) {
            if (!legs.getString("legs_back_3").equals("empty")) {
                if (legs.getString("legs_back_3").contains("allomantic")) {
                    player.getLegs().addSpikeBySlot(new SpikeEntity(MetalTagsUtils.getMetalTagEnumByString(legs.getString("legs_back_3")), TypeOfSpikeEnum.ALLOMANTIC), 3, BodySlotEnum.BACK);
                } else {
                    player.getLegs().addSpikeBySlot(new SpikeEntity(MetalTagsUtils.getMetalTagEnumByString(legs.getString("legs_back_3")), TypeOfSpikeEnum.FERUCHEMIC), 3, BodySlotEnum.BACK);
                }
            }
        }

        if (legs.getString("legs_back_4") != null) {
            if (!legs.getString("legs_back_4").equals("empty")) {
                if (legs.getString("legs_back_4").contains("allomantic")) {
                    player.getLegs().addSpikeBySlot(new SpikeEntity(MetalTagsUtils.getMetalTagEnumByString(legs.getString("legs_back_4")), TypeOfSpikeEnum.ALLOMANTIC), 4, BodySlotEnum.BACK);
                } else {
                    player.getLegs().addSpikeBySlot(new SpikeEntity(MetalTagsUtils.getMetalTagEnumByString(legs.getString("legs_back_4")), TypeOfSpikeEnum.FERUCHEMIC), 4, BodySlotEnum.BACK);
                }
            }
        }

        if (legs.getString("legs_back_5") != null) {
            if (!legs.getString("legs_back_5").equals("empty")) {
                if (legs.getString("legs_back_5").contains("allomantic")) {
                    player.getLegs().addSpikeBySlot(new SpikeEntity(MetalTagsUtils.getMetalTagEnumByString(legs.getString("legs_back_5")), TypeOfSpikeEnum.ALLOMANTIC), 5, BodySlotEnum.BACK);
                } else {
                    player.getLegs().addSpikeBySlot(new SpikeEntity(MetalTagsUtils.getMetalTagEnumByString(legs.getString("legs_back_5")), TypeOfSpikeEnum.FERUCHEMIC), 5, BodySlotEnum.BACK);
                }
            }
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
        this.setEttmetalState(EttmetalStateEnum.getEttmetaStateByName(extraData.getString("ettmetal_state")));

        this.setFirstJoin(extraData.getBoolean("is_first_join"));

    }


}
