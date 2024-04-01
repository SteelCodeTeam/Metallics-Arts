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
 * Represents a LegsPartEntity, a subclass of BodyPartEntity, that represents a part of a body's legs.
 */
public final class LegsPartEntity extends BodyPartEntity {

    private Integer maxQtySpikes;
    private SpikeEntity SLOT_BACK_0;
    private SpikeEntity SLOT_BACK_1;
    private SpikeEntity SLOT_BACK_2;
    private SpikeEntity SLOT_FRONT_0;
    private SpikeEntity SLOT_FRONT_1;
    private SpikeEntity SLOT_FRONT_2;

    /**
     * Represents a Legs Part Entity that extends the BodyPartEntity class.
     * It represents the legs body part and contains spikes in both front and back slots.
     * The maximum quantity of spikes that can be added is 6.
     */
    public LegsPartEntity() {
        super(BodyPartEnum.LEGS);

        addSpikeBySlot(null, 0, BodySlotEnum.BACK);
        addSpikeBySlot(null, 1, BodySlotEnum.BACK);
        addSpikeBySlot(null, 2, BodySlotEnum.BACK);
        addSpikeBySlot(null, 0, BodySlotEnum.FRONT);
        addSpikeBySlot(null, 1, BodySlotEnum.FRONT);
        addSpikeBySlot(null, 2, BodySlotEnum.FRONT);

        setMaxQtySpikes(6);
    }

    /**
     * Sets the body part of the LegsPartEntity.
     *
     * @param bodyPart The BodyPartEnum representing the body part.
     */
    @Override
    public void setBodyPart(BodyPartEnum bodyPart) {
        this.bodyPart = bodyPart;
    }


    /**
     * Retrieves the maximum quantity of spikes that can be added to the LegsPartEntity.
     *
     * @return The maximum quantity of spikes.
     */
    @Override
    public Integer getMaxQtySpikes() {
        return maxQtySpikes;
    }


    /**
     * Sets the maximum quantity of spikes that can be added to the LegsPartEntity.
     *
     * @param maxQtySpikes The maximum quantity of spikes.
     */
    @Override
    public void setMaxQtySpikes(Integer maxQtySpikes) {
        this.maxQtySpikes = maxQtySpikes;
    }

    /**
     * Retrieves the actual quantity of spikes added to the LegsPartEntity.
     *
     * @return The actual quantity of spikes.
     */
    @Override
    public Integer getActualQtySpikes() {
        return Math.toIntExact(Stream.of(SLOT_BACK_0, SLOT_BACK_1, SLOT_BACK_2, SLOT_FRONT_0, SLOT_FRONT_1, SLOT_FRONT_2).filter(Objects::nonNull).count());
    }


    /**
     * Adds a spike to the LegsPartEntity in the specified slot position and slot number.
     *
     * @param entity   The SpikeEntity to be added.
     * @param slotNum  The slot number where the spike will be added (0, 1, or 2).
     * @param slotPos  The slot position where the spike will be added (BodySlotEnum.FRONT or BodySlotEnum.BACK).
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
     * Adds a spike randomly to the LegsPartEntity if the maximum quantity of spikes has not been reached.
     * The spike is added to an available slot position and slot number in the LegsPartEntity.
     *
     * @param entity The SpikeEntity to be added.
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
     * Retrieves the SpikeEntity located in the specified slot position and slot number of the LegsPartEntity.
     *
     * @param slotNum  The slot number where the spike is located (0, 1, or 2).
     * @param slotPos  The slot position where the spike is located (BodySlotEnum.FRONT or BodySlotEnum.BACK).
     * @return The SpikeEntity located in the specified slot position and slot number, or null if no spike is found.
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
     * Retrieves all the spikes in the LegsPartEntity.
     *
     * @return A List of SpikeEntity objects representing all the spikes.
     */
    @Override
    public List<SpikeEntity> getAllSpikes() {
        return Stream.of(SLOT_BACK_0, SLOT_BACK_1, SLOT_BACK_2, SLOT_FRONT_0, SLOT_FRONT_1, SLOT_FRONT_2)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }


    /**
     * Retrieves all the spikes in the LegsPartEntity by slot position and slot number.
     *
     * @return A HashMap containing the spike entities mapped by a string representing the slot position and slot number.
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
     * Removes a spike from the LegsPartEntity in the specified slot position and slot number.
     *
     * @param slotNum  The slot number where the spike will be removed (0, 1, or 2).
     * @param slotPos  The slot position where the spike will be removed (BodySlotEnum.FRONT or BodySlotEnum.BACK).
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
     * Removes a spike from the LegsPartEntity that matches the given SpikeEntity object.
     *
     * @param entity The SpikeEntity object representing the spike to be removed.
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
        return "LegsPartEntity{" +
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
