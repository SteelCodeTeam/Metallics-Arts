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
    private SpikeEntity SLOT_BACK_3;
    private SpikeEntity SLOT_BACK_4;
    private SpikeEntity SLOT_BACK_5;
    private SpikeEntity SLOT_FRONT_0;
    private SpikeEntity SLOT_FRONT_1;
    private SpikeEntity SLOT_FRONT_2;
    private SpikeEntity SLOT_FRONT_3;
    private SpikeEntity SLOT_FRONT_4;
    private SpikeEntity SLOT_FRONT_5;

    /**
     * ArmsPartEntity is a class that represents the arms body part of a player.
     */
    public ArmsPartEntity() {
        super(BodyPartEnum.ARMS);

        addSpikeBySlot(null, 0, BodySlotEnum.BACK);
        addSpikeBySlot(null, 1, BodySlotEnum.BACK);
        addSpikeBySlot(null, 2, BodySlotEnum.BACK);
        addSpikeBySlot(null, 3, BodySlotEnum.BACK);
        addSpikeBySlot(null, 4, BodySlotEnum.BACK);
        addSpikeBySlot(null, 5, BodySlotEnum.BACK);
        addSpikeBySlot(null, 0, BodySlotEnum.FRONT);
        addSpikeBySlot(null, 1, BodySlotEnum.FRONT);
        addSpikeBySlot(null, 2, BodySlotEnum.FRONT);
        addSpikeBySlot(null, 3, BodySlotEnum.FRONT);
        addSpikeBySlot(null, 4, BodySlotEnum.FRONT);
        addSpikeBySlot(null, 5, BodySlotEnum.FRONT);
        setMaxQtySpikes(12);
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
        return Math.toIntExact(Stream.of(SLOT_BACK_0, SLOT_BACK_1, SLOT_BACK_2, SLOT_BACK_3, SLOT_BACK_4, SLOT_BACK_5,
                                            SLOT_FRONT_0, SLOT_FRONT_1, SLOT_FRONT_2, SLOT_FRONT_3, SLOT_FRONT_4, SLOT_FRONT_5)
                    .filter(Objects::nonNull).count());
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
            } else if (slotNum == 3) {
                SLOT_BACK_3 = entity;
            } else if (slotNum == 4) {
                SLOT_BACK_4 = entity;
            } else if (slotNum == 5) {
                SLOT_BACK_5 = entity;
            }
        } else if (slotPos == BodySlotEnum.FRONT) {
            if (slotNum == 0) {
                SLOT_FRONT_0 = entity;
            } else if (slotNum == 1) {
                SLOT_FRONT_1 = entity;
            } else if (slotNum == 2) {
                SLOT_FRONT_2 = entity;
            } else if (slotNum == 3) {
                SLOT_FRONT_3 = entity;
            } else if (slotNum == 4) {
                SLOT_FRONT_4 = entity;
            } else if (slotNum == 5) {
                SLOT_FRONT_5 = entity;
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
        if (SLOT_BACK_0 == null) {
            System.out.println("ARMS - BACK - 0");
            SLOT_BACK_0 = entity;
        } else if (SLOT_BACK_1 == null) {
            System.out.println("ARMS - BACK - 1");
            SLOT_BACK_1 = entity;
        } else if (SLOT_BACK_2 == null) {
            System.out.println("ARMS - BACK - 2");
            SLOT_BACK_2 = entity;
        } else if (SLOT_BACK_3 == null) {
            System.out.println("ARMS - BACK - 3");
            SLOT_BACK_3 = entity;
        } else if (SLOT_BACK_4 == null) {
            System.out.println("ARMS - BACK - 4");
            SLOT_BACK_4 = entity;
        } else if (SLOT_BACK_5 == null) {
            System.out.println("ARMS - BACK - 5");
            SLOT_BACK_5 = entity;
        } else if (SLOT_FRONT_0 == null) {
            System.out.println("ARMS - FRONT - 0");
            SLOT_FRONT_0 = entity;
        } else if (SLOT_FRONT_1 == null) {
            System.out.println("ARMS - FRONT - 1");
            SLOT_FRONT_1 = entity;
        } else if (SLOT_FRONT_2 == null) {
            System.out.println("ARMS - FRONT - 2");
            SLOT_FRONT_2 = entity;
        } else if (SLOT_FRONT_3 == null) {
            System.out.println("ARMS - FRONT - 3");
            SLOT_FRONT_3 = entity;
        } else if (SLOT_FRONT_4 == null) {
            System.out.println("ARMS - FRONT - 4");
            SLOT_FRONT_4 = entity;
        } else if (SLOT_FRONT_5 == null) {
            System.out.println("ARMS - FRONT - 5");
            SLOT_FRONT_5 = entity;
        } else {
            System.out.println("No se agrego la entidad " + entity + " \n EN ARMS");
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
            } else if (slotNum == 3) {
                return SLOT_BACK_3;
            } else if (slotNum == 4) {
                return SLOT_BACK_4;
            } else if (slotNum == 5) {
                return SLOT_BACK_5;
            }
        } else if (slotPos == BodySlotEnum.FRONT) {
            if (slotNum == 0) {
                return SLOT_FRONT_0;
            } else if (slotNum == 1) {
                return SLOT_FRONT_1;
            } else if (slotNum == 2) {
                return SLOT_FRONT_2;
            } else if (slotNum == 3) {
                return SLOT_FRONT_3;
            } else if (slotNum == 4) {
                return SLOT_FRONT_4;
            } else if (slotNum == 5) {
                return SLOT_FRONT_5;
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
        return Stream.of(SLOT_BACK_0, SLOT_BACK_1, SLOT_BACK_2, SLOT_BACK_3, SLOT_BACK_4, SLOT_BACK_5,
                        SLOT_FRONT_0, SLOT_FRONT_1, SLOT_FRONT_2, SLOT_FRONT_3, SLOT_FRONT_4, SLOT_FRONT_5)
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
        spikesBySlotAndNum.put("ARMS_BACK_0", SLOT_BACK_0);
        spikesBySlotAndNum.put("ARMS_BACK_1", SLOT_BACK_1);
        spikesBySlotAndNum.put("ARMS_BACK_2", SLOT_BACK_2);
        spikesBySlotAndNum.put("ARMS_BACK_3", SLOT_BACK_3);
        spikesBySlotAndNum.put("ARMS_BACK_4", SLOT_BACK_4);
        spikesBySlotAndNum.put("ARMS_BACK_5", SLOT_BACK_5);
        spikesBySlotAndNum.put("ARMS_FRONT_0", SLOT_FRONT_0);
        spikesBySlotAndNum.put("ARMS_FRONT_1", SLOT_FRONT_1);
        spikesBySlotAndNum.put("ARMS_FRONT_2", SLOT_FRONT_2);
        spikesBySlotAndNum.put("ARMS_FRONT_3", SLOT_FRONT_3);
        spikesBySlotAndNum.put("ARMS_FRONT_4", SLOT_FRONT_4);
        spikesBySlotAndNum.put("ARMS_FRONT_5", SLOT_FRONT_5);
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
            } else if (slotNum == 3) {
                SLOT_BACK_3 = null;
            } else if (slotNum == 4) {
                SLOT_BACK_4 = null;
            } else if (slotNum == 5) {
                SLOT_BACK_5 = null;
            }
        } else if (slotPos == BodySlotEnum.FRONT) {
            if (slotNum == 0) {
                SLOT_FRONT_0 = null;
            } else if (slotNum == 1) {
                SLOT_FRONT_1 = null;
            } else if (slotNum == 2) {
                SLOT_FRONT_2 = null;
            } else if (slotNum == 3) {
                SLOT_FRONT_3 = null;
            } else if (slotNum == 4) {
                SLOT_FRONT_4 = null;
            } else if (slotNum == 5) {
                SLOT_FRONT_5 = null;
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
        } else if (entity == SLOT_BACK_3) {
            SLOT_BACK_3 = null;
        } else if (entity == SLOT_BACK_4) {
            SLOT_BACK_4 = null;
        } else if (entity == SLOT_BACK_5) {
            SLOT_BACK_5 = null;
        } else if (entity == SLOT_FRONT_0) {
            SLOT_FRONT_0 = null;
        } else if (entity == SLOT_FRONT_1) {
            SLOT_FRONT_1 = null;
        } else if (entity == SLOT_FRONT_2) {
            SLOT_FRONT_2 = null;
        } else if (entity == SLOT_FRONT_3) {
            SLOT_FRONT_3 = null;
        } else if (entity == SLOT_FRONT_4) {
            SLOT_FRONT_4 = null;
        } else if (entity == SLOT_FRONT_5) {
            SLOT_FRONT_5 = null;
        }
    }

    @Override
    public String toString() {
        return "ArmsPartEntity{" +
                "maxQtySpikes=" + maxQtySpikes +
                ", SLOT_BACK_0=" + SLOT_BACK_0 +
                ", SLOT_BACK_1=" + SLOT_BACK_1 +
                ", SLOT_BACK_2=" + SLOT_BACK_2 +
                ", SLOT_BACK_3=" + SLOT_BACK_3 +
                ", SLOT_BACK_4=" + SLOT_BACK_4 +
                ", SLOT_BACK_5=" + SLOT_BACK_5 +
                ", SLOT_FRONT_0=" + SLOT_FRONT_0 +
                ", SLOT_FRONT_1=" + SLOT_FRONT_1 +
                ", SLOT_FRONT_2=" + SLOT_FRONT_2 +
                ", SLOT_FRONT_3=" + SLOT_FRONT_3 +
                ", SLOT_FRONT_4=" + SLOT_FRONT_4 +
                ", SLOT_FRONT_5=" + SLOT_FRONT_5 +
                '}';
    }
}
