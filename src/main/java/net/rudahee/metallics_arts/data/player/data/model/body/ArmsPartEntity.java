package net.rudahee.metallics_arts.data.player.data.model.body;

import net.rudahee.metallics_arts.data.player.data.model.SpikeEntity;
import net.rudahee.metallics_arts.data.player.data.model.enums.BodyPartEnum;
import net.rudahee.metallics_arts.data.player.data.model.enums.BodySlotEnum;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * The ArmsPartEntity class represents the arms body part in an entity.
 * It extends the BodyPartEntity class.
 */
public final class ArmsPartEntity extends BodyPartEntity {

    private Integer maxQtySpikes;
    private SpikeEntity SLOT_BACK_0;
    private SpikeEntity SLOT_BACK_1;
    private SpikeEntity SLOT_BACK_2;
    private SpikeEntity SLOT_FRONT_0;
    private SpikeEntity SLOT_FRONT_1;
    private SpikeEntity SLOT_FRONT_2;

    /**
     * ArmsPartEntity is a class that represents the arms body part of a player.
     */
    public ArmsPartEntity() {
        super(BodyPartEnum.ARMS);

        addSpikeBySlot(null, 0, BodySlotEnum.BACK);
        addSpikeBySlot(null, 1, BodySlotEnum.BACK);
        addSpikeBySlot(null, 2, BodySlotEnum.BACK);
        addSpikeBySlot(null, 0, BodySlotEnum.FRONT);
        addSpikeBySlot(null, 1, BodySlotEnum.FRONT);
        addSpikeBySlot(null, 2, BodySlotEnum.FRONT);

        setMaxQtySpikes(6);
    }

    /**
     * Sets the body part of the entity.
     *
     * @param bodyPart the body part to set
     */
    @Override
    public void setBodyPart(BodyPartEnum bodyPart) {
        this.bodyPart = bodyPart;
    }


    /**
     * Retrieves the maximum quantity of spikes that can be added to the body part.
     *
     * @return the maximum quantity of spikes
     */
    @Override
    public Integer getMaxQtySpikes() {
        return maxQtySpikes;
    }


    /**
     * Sets the maximum quantity of spikes that can be added to the body part.
     *
     * @param maxQtySpikes the maximum quantity of spikes
     */
    @Override
    public void setMaxQtySpikes(Integer maxQtySpikes) {
        this.maxQtySpikes = maxQtySpikes;
    }

    /**
     * Retrieves the actual quantity of spikes currently present in the body part.
     * The actual quantity of spikes is determined by counting the non-null spike slots in the body part.
     *
     * @return the actual quantity of spikes
     */
    @Override
    public Integer getActualQtySpikes() {
        return Math.toIntExact(Stream.of(SLOT_BACK_0, SLOT_BACK_1, SLOT_BACK_2, SLOT_FRONT_0, SLOT_FRONT_1, SLOT_FRONT_2).filter(Objects::nonNull).count());
    }


    /**
     * Adds a spike entity to the specified slot in the body part.
     *
     * @param entity   the spike entity to add
     * @param slotNum  the slot number
     * @param slotPos  the slot position (either FRONT or BACK)
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
            }
        } else if (slotPos == BodySlotEnum.FRONT) {
            if (slotNum == 0) {
                SLOT_FRONT_0 = entity;
            } else if (slotNum == 1) {
                SLOT_FRONT_1 = entity;
            } else if (slotNum == 2) {
                SLOT_FRONT_2 = entity;
            }
        }
    }


    /**
     * Adds a spike entity randomly to an available slot in the body part.
     *
     * @param entity the spike entity to add
     */
    @Override
    public void addSpikeRandom(SpikeEntity entity) {
        Random rng = new Random();
        boolean done = false;
        if (getMaxQtySpikes() > getActualQtySpikes()) {
            do {
                int slot = rng.nextInt(6);

                if (slot == 0) {
                    if (SLOT_BACK_0 == null) {
                        SLOT_BACK_0 = entity;
                        done = true;
                    }
                } else if (slot == 1) {
                    if (SLOT_BACK_1 == null) {
                        SLOT_BACK_1 = entity;
                        done = true;
                    }
                } else if (slot == 2) {
                    if (SLOT_BACK_2 == null) {
                        SLOT_BACK_2 = entity;
                        done = true;
                    }
                } else if (slot == 3) {
                    if (SLOT_FRONT_0 == null) {
                        SLOT_FRONT_0 = entity;
                        done = true;
                    }
                } else if (slot == 4) {
                    if (SLOT_FRONT_1 == null) {
                        SLOT_FRONT_1 = entity;
                        done = true;
                    }
                } else {
                    if (SLOT_FRONT_2 == null) {
                        SLOT_FRONT_2 = entity;
                        done = true;
                    }
                }
            } while (!done);
        }
    }


    /**
     * Retrieves the spike entity in the specified slot and position of the arms body part.
     *
     * @param slotNum The slot number of the spike entity (0, 1, or 2)
     * @param slotPos The slot position (either FRONT or BACK)
     * @return The SpikeEntity in the specified slot and position, or null if no spike entity is found
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
            }
        } else if (slotPos == BodySlotEnum.FRONT) {
            if (slotNum == 0) {
                return SLOT_FRONT_0;
            } else if (slotNum == 1) {
                return SLOT_FRONT_1;
            } else if (slotNum == 2) {
                return SLOT_FRONT_2;
            }
        }
        return null;
    }


    /**
     * Retrieves a list of all spike entities present in the body part.
     *
     * @return a list of all spike entities
     */
    @Override
    public List<SpikeEntity> getAllSpikes() {
        return Stream.of(SLOT_BACK_0, SLOT_BACK_1, SLOT_BACK_2, SLOT_FRONT_0, SLOT_FRONT_1, SLOT_FRONT_2)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }


    /**
     * Retrieves a map of all spike entities present in the arms body part, grouped by slot and number.
     *
     * @return a map where the key is a string representing the slot and number, and the value is the spike entity
     */
    @Override
    public HashMap<String, SpikeEntity> getAllSpikesBySlotAndNum() {
        HashMap<String, SpikeEntity> spikesBySlotAndNum = new HashMap<>();
        spikesBySlotAndNum.put("BACK_BACK_0", SLOT_BACK_0);
        spikesBySlotAndNum.put("BACK_BACK_1", SLOT_BACK_1);
        spikesBySlotAndNum.put("BACK_BACK_2", SLOT_BACK_2);
        spikesBySlotAndNum.put("BACK_FRONT_0", SLOT_FRONT_0);
        spikesBySlotAndNum.put("BACK_FRONT_1", SLOT_FRONT_1);
        spikesBySlotAndNum.put("BACK_FRONT_2", SLOT_FRONT_2);
        return spikesBySlotAndNum;
    }


    /**
     * Removes the spike entity from the specified slot in the body part.
     *
     * @param slotNum the slot number
     * @param slotPos the slot position (either FRONT or BACK)
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
            }
        } else if (slotPos == BodySlotEnum.FRONT) {
            if (slotNum == 0) {
                SLOT_FRONT_0 = null;
            } else if (slotNum == 1) {
                SLOT_FRONT_1 = null;
            } else if (slotNum == 2) {
                SLOT_FRONT_2 = null;
            }
        }
    }

    /**
     * Removes the specified spike entity from the body part.
     *
     * @param entity the spike entity to remove
     *
     * @throws IllegalArgumentException if the given entity is null
     */
    @Override
    public void removeSpikeBySpikeEntity(SpikeEntity entity) {
        if (entity == SLOT_BACK_0) {
            SLOT_BACK_0 = null;
        } else if (entity == SLOT_BACK_1) {
            SLOT_BACK_1 = null;
        } else if (entity == SLOT_BACK_2) {
            SLOT_BACK_2 = null;
        } else if (entity == SLOT_FRONT_0) {
            SLOT_FRONT_0 = null;
        } else if (entity == SLOT_FRONT_1) {
            SLOT_FRONT_1 = null;
        } else if (entity == SLOT_FRONT_2) {
            SLOT_FRONT_2 = null;
        }
    }

    @Override
    public String toString() {
        return "ArmsPartEntity{" +
                "maxQtySpikes=" + maxQtySpikes +
                ", SLOT_BACK_0=" + SLOT_BACK_0 +
                ", SLOT_BACK_1=" + SLOT_BACK_1 +
                ", SLOT_BACK_2=" + SLOT_BACK_2 +
                ", SLOT_FRONT_0=" + SLOT_FRONT_0 +
                ", SLOT_FRONT_1=" + SLOT_FRONT_1 +
                ", SLOT_FRONT_2=" + SLOT_FRONT_2 +
                ", bodyPart=" + bodyPart +
                '}';
    }
}
