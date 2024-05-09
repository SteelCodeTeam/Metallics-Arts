package net.rudahee.metallics_arts.data.player.data.model.body;

import net.rudahee.metallics_arts.data.player.data.model.SpikeEntity;
import net.rudahee.metallics_arts.data.player.data.model.enums.BodyPartEnum;
import net.rudahee.metallics_arts.data.player.data.model.enums.BodySlotEnum;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * A concrete class representing a specific body part called "Back" in a player's entity.
 * <p>
 * This class extends the abstract class BodyPartEntity.
 * It provides methods for adding, getting, and removing spikes in the back part of the entity's body.
 * The maximum number of spikes that can be added is determined by the maxQtySpikes property.
 */
public final class BackPartEntity extends BodyPartEntity {

    private Integer maxQtySpikes;
    private SpikeEntity SLOT_BACK_0;
    private SpikeEntity SLOT_BACK_1;
    private SpikeEntity SLOT_BACK_2;
    private SpikeEntity SLOT_BACK_3;
    private SpikeEntity SLOT_BACK_4;
    private SpikeEntity SLOT_BACK_5;
    private SpikeEntity SLOT_BACK_6;

    /**
     * Represents a specific body part called "Back" in a player's entity.
     * This class inherits from the abstract class BodyPartEntity.
     */
    public BackPartEntity() {
        super(BodyPartEnum.BACK);

        addSpikeBySlot(null, 0, BodySlotEnum.BACK);
        addSpikeBySlot(null, 1, BodySlotEnum.BACK);
        addSpikeBySlot(null, 2, BodySlotEnum.BACK);
        addSpikeBySlot(null, 3, BodySlotEnum.BACK);
        addSpikeBySlot(null, 4, BodySlotEnum.BACK);
        addSpikeBySlot(null, 5, BodySlotEnum.BACK);
        addSpikeBySlot(null, 6, BodySlotEnum.BACK);

        setMaxQtySpikes(7);
    }

    /**
     * Sets the body part of the BackPartEntity to the specified value.
     *
     * @param bodyPart the BodyPartEnum value representing the body part
     */
    @Override
    public void setBodyPart(BodyPartEnum bodyPart) {
        this.bodyPart = bodyPart;
    }


    /**
     * Returns the maximum quantity of spikes allowed for this body part.
     *
     * @return The maximum quantity of spikes as an Integer value.
     */
    @Override
    public Integer getMaxQtySpikes() {
        return maxQtySpikes;
    }


    /**
     * Sets the maximum quantity of spikes allowed for this body part.
     *
     * @param maxQtySpikes The maximum quantity of spikes as an Integer value.
     */
    @Override
    public void setMaxQtySpikes(Integer maxQtySpikes) {
        this.maxQtySpikes = maxQtySpikes;
    }

    /**
     * Returns the actual quantity of spikes for the "Back" body part in a player's entity.
     *
     * @return The actual quantity of spikes as an Integer value.
     */
    @Override
    public Integer getActualQtySpikes() {
        return Math.toIntExact(Stream.of(SLOT_BACK_0, SLOT_BACK_1, SLOT_BACK_2, SLOT_BACK_3, SLOT_BACK_4, SLOT_BACK_5, SLOT_BACK_6).filter(Objects::nonNull).count());
    }


    /**
     * Adds a spike entity to the specified slot in the "Back" body part of the player's entity.
     *
     * @param entity   The spike entity to be added.
     * @param slotNum  The slot number where the spike entity should be added (0-6).
     * @param slotPos  The position of the body slot (FRONT or BACK).
     */
    @Override
    public void addSpikeBySlot(SpikeEntity entity, int slotNum, BodySlotEnum slotPos) {
        if (slotPos == BodySlotEnum.BACK) {
            if (slotNum == 0) {
                SLOT_BACK_0 = entity;
            } else if (slotNum == 1) {
                SLOT_BACK_1 = entity;
            } else if (slotNum == 2) {
                SLOT_BACK_2 = entity;
            } else if (slotNum == 3) {
                SLOT_BACK_3 = entity;
            } else if (slotNum == 4) {
                SLOT_BACK_4 = entity;
            } else if (slotNum == 5) {
                SLOT_BACK_5 = entity;
            } else if (slotNum == 6) {
                SLOT_BACK_6 = entity;
            }
        }
    }


    /**
     * Adds a spike entity to a random available slot in the "Back" body part of the player's entity.
     * The spike is added only if the maximum quantity of spikes has not been reached.
     *
     * @param entity The spike entity to be added.
     */
    @Override
    public void addSpikeRandom(SpikeEntity entity) {
        if (SLOT_BACK_0 == null) {
            SLOT_BACK_0 = entity;
        } else if (SLOT_BACK_1 == null) {
            SLOT_BACK_1 = entity;
        } else if (SLOT_BACK_2 == null) {
            SLOT_BACK_2 = entity;
        } else if (SLOT_BACK_3 == null) {
            SLOT_BACK_3 = entity;
        } else if (SLOT_BACK_4 == null) {
            SLOT_BACK_4 = entity;
        } else if (SLOT_BACK_5 == null) {
            SLOT_BACK_5 = entity;
        } else if (SLOT_BACK_6 == null) {
            SLOT_BACK_6 = entity;
        } else {
            System.out.println("No se agrego la entidad " + entity + " \n EN BACK");
        }

    }


    /**
     * Retrieves a spike entity from the specified slot in the "Back" body part of the player's entity.
     *
     * @param slotNum  The slot number where the spike entity is located (0-6).
     * @param slotPos  The position of the body slot (FRONT or BACK).
     * @return The spike entity found in the specified slot, or null if no spike entity is found.
     */
    @Override
    public SpikeEntity getSpikeBySlot(int slotNum, BodySlotEnum slotPos) {
        if (slotPos == BodySlotEnum.BACK) {
            if (slotNum == 0) {
                return SLOT_BACK_0;
            } else if (slotNum == 1) {
                return SLOT_BACK_1;
            } else if (slotNum == 2) {
                return SLOT_BACK_2;
            } else if (slotNum == 3) {
                return SLOT_BACK_3;
            } else if (slotNum == 4) {
                return SLOT_BACK_4;
            } else if (slotNum == 5) {
                return SLOT_BACK_5;
            } else if (slotNum == 6) {
                return SLOT_BACK_6;
            }
        }
        return null;
    }


    /**
     * Retrieves all the spike entities from the "Back" body part of the player's entity.
     *
     * @return A list of SpikeEntity objects representing all the spike entities in the "Back" body part.
     */
    @Override
    public List<SpikeEntity> getAllSpikes() {
        return Stream.of(SLOT_BACK_0, SLOT_BACK_1, SLOT_BACK_2, SLOT_BACK_3, SLOT_BACK_4, SLOT_BACK_5, SLOT_BACK_6)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }


    /**
     * Returns a HashMap containing all the spike entities in the "Back" body part of the player's entity.
     *
     * @return A HashMap where the keys are strings representing the slot and number, and the values are the corresponding spike entities.
     */
    @Override
    public HashMap<String, SpikeEntity> getAllSpikesBySlotAndNum() {
        HashMap<String, SpikeEntity> spikesBySlotAndNum = new HashMap<>();
        spikesBySlotAndNum.put("BACK_BACK_0", SLOT_BACK_0);
        spikesBySlotAndNum.put("BACK_BACK_1", SLOT_BACK_1);
        spikesBySlotAndNum.put("BACK_BACK_2", SLOT_BACK_2);
        spikesBySlotAndNum.put("BACK_BACK_3", SLOT_BACK_3);
        spikesBySlotAndNum.put("BACK_BACK_4", SLOT_BACK_4);
        spikesBySlotAndNum.put("BACK_BACK_5", SLOT_BACK_5);
        spikesBySlotAndNum.put("BACK_BACK_6", SLOT_BACK_6);
        return spikesBySlotAndNum;
    }


    /**
     * Removes a spike entity from the specified slot in the "Back" body part of the player's entity.
     *
     * @param slotNum  The slot number where the spike entity should be removed (0-6).
     * @param slotPos  The position of the body slot (FRONT or BACK).
     */
    @Override
    public void removeSpikeBySlot(int slotNum, BodySlotEnum slotPos) {
        if (slotPos == BodySlotEnum.BACK) {
            if (slotNum == 0) {
                SLOT_BACK_0 = null;
            } else if (slotNum == 1) {
                SLOT_BACK_1 = null;
            } else if (slotNum == 2) {
                SLOT_BACK_2 = null;
            } else if (slotNum == 3) {
                SLOT_BACK_3 = null;
            } else if (slotNum == 4) {
                SLOT_BACK_4 = null;
            } else if (slotNum == 5) {
                SLOT_BACK_5 = null;
            } else if (slotNum == 6) {
                SLOT_BACK_6 = null;
            }
        }
    }


    /**
     * Removes a spike entity from the specified slot in the "Back" body part of the player's entity.
     *
     * @param entity The spike entity to be removed.
     */
    @Override
    public void removeSpikeBySpikeEntity(SpikeEntity entity) {
        if (entity == SLOT_BACK_0) {
            SLOT_BACK_0 = null;
        } else if (entity == SLOT_BACK_1) {
            SLOT_BACK_1 = null;
        } else if (entity == SLOT_BACK_2) {
            SLOT_BACK_2 = null;
        } else if (entity == SLOT_BACK_3) {
            SLOT_BACK_3 = null;
        } else if (entity == SLOT_BACK_4) {
            SLOT_BACK_4 = null;
        } else if (entity == SLOT_BACK_5) {
            SLOT_BACK_5 = null;
        } else if (entity == SLOT_BACK_6) {
            SLOT_BACK_6 = null;
        }
    }

    @Override
    public String toString() {
        return "BackPartEntity{" +
                "maxQtySpikes=" + maxQtySpikes +
                ", SLOT_BACK_0=" + SLOT_BACK_0 +
                ", SLOT_BACK_1=" + SLOT_BACK_1 +
                ", SLOT_BACK_2=" + SLOT_BACK_2 +
                ", SLOT_BACK_3=" + SLOT_BACK_3 +
                ", SLOT_BACK_4=" + SLOT_BACK_4 +
                ", SLOT_BACK_5=" + SLOT_BACK_5 +
                ", SLOT_BACK_6=" + SLOT_BACK_6 +
                ", bodyPart=" + bodyPart +
                '}';
    }
}
