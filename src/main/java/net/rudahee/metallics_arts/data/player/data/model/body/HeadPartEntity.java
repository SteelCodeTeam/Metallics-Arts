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
 * The HeadPartEntity class represents a head part entity.
 * It extends the abstract class BodyPartEntity.
 * A head part entity can have spikes attached to it.
 */
public final class HeadPartEntity extends BodyPartEntity {

    private Integer maxQtySpikes;
    private SpikeEntity SLOT_FRONT_0;
    private SpikeEntity SLOT_FRONT_1;
    private SpikeEntity SLOT_BACK_0;

    /**
     * Represents a Head Part Entity.
     * Extends the abstract class BodyPartEntity.
     */
    public HeadPartEntity() {
        super(BodyPartEnum.HEAD);

        addSpikeBySlot(null, 0, BodySlotEnum.FRONT);
        addSpikeBySlot(null, 1, BodySlotEnum.FRONT);
        addSpikeBySlot(null, 0, BodySlotEnum.BACK);
        setMaxQtySpikes(3);
    }

    @Override
    public void setBodyPart(BodyPartEnum bodyPart) {
        this.bodyPart = bodyPart;
    }

    /**
     * Retrieves the maximum quantity of spikes for the head part entity.
     *
     * @return The maximum quantity of spikes.
     */
    @Override
    public Integer getMaxQtySpikes() {
        return maxQtySpikes;
    }

    /**
     * Sets the maximum quantity of spikes for the head part entity.
     *
     * @param maxQtySpikes The maximum quantity of spikes to set.
     */
    @Override
    public void setMaxQtySpikes(Integer maxQtySpikes) {
        this.maxQtySpikes = maxQtySpikes;
    }

    /**
     * Retrieves the actual quantity of spikes in the head part entity.
     *
     * @return The actual number of spikes in the head part entity.
     */
    @Override
    public Integer getActualQtySpikes() {
        return Math.toIntExact(Stream.of(SLOT_BACK_0, SLOT_FRONT_0, SLOT_FRONT_1).filter(Objects::nonNull).count());
    }

    /**
     * Adds a spike to the body part entity in the specified slot number and slot position.
     *
     * @param entity   The SpikeEntity object to be added.
     * @param slotNum  The slot number of the spike.
     * @param slotPos  The slot position of the spike (FRONT or BACK).
     */
    @Override
    public void addSpikeBySlot(SpikeEntity entity, int slotNum, BodySlotEnum slotPos) {
        if (slotPos == BodySlotEnum.FRONT) {
            if (slotNum == 0) {
                SLOT_FRONT_0 = entity;
            } else if (slotNum == 1) {
                SLOT_FRONT_1 = entity;
            }
        } else if (slotPos == BodySlotEnum.BACK) {
            if (slotNum == 0) {
                SLOT_BACK_0 = entity;
            }
        }
    }

    /**
     * Adds a spike randomly to the body part entity.
     *
     * @param entity The SpikeEntity object to be added.
     */
    @Override
    public void addSpikeRandom(SpikeEntity entity) {

        // Check if front slots are all occupied
        if (SLOT_BACK_0 == null) {
            SLOT_BACK_0 = entity;
        } else if (SLOT_FRONT_0 == null) {
            SLOT_FRONT_0 = entity;
        } else if (SLOT_FRONT_1 == null) {
            SLOT_FRONT_1 = entity;
        } else {
            System.out.println("No se agrego la entidad " + entity + " \n EN HEAD");
        }
    }

    /**
     * Retrieves the spike at the specified slot number and slot position.
     *
     * @param slotNum The slot number of the spike.
     * @param slotPos The slot position of the spike (FRONT or BACK).
     * @return The SpikeEntity object representing the spike at the specified slot number and slot position,
     *         or null if no spike is found.
     */
    @Override
    public SpikeEntity getSpikeBySlot(int slotNum, BodySlotEnum slotPos) {
        if (slotPos == BodySlotEnum.FRONT) {
            if (slotNum == 0) {
                return SLOT_FRONT_0;
            } else if (slotNum == 1) {
                return SLOT_FRONT_1;
            }
        } else if (slotPos == BodySlotEnum.BACK) {
            if (slotNum == 0) {
                return SLOT_BACK_0;
            }
        }
        return null;
    }

    /**
     * Retrieves all spikes in the head part entity.
     *
     * @return A list of SpikeEntity objects representing the spikes.
     */
    @Override
    public List<SpikeEntity> getAllSpikes() {
        return Stream.of(SLOT_BACK_0, SLOT_FRONT_0, SLOT_FRONT_1)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves all spikes in the head part entity organized by slot number and slot position.
     *
     * @return A HashMap containing the spikes, where the key is the slot and position identifier and the value is the spike entity.
     */
    @Override
    public HashMap<String, SpikeEntity> getAllSpikesBySlotAndNum() {
        HashMap<String, SpikeEntity> spikesBySlotAndNum = new HashMap<>();
        spikesBySlotAndNum.put("HEAD_FRONT_0", SLOT_FRONT_0);
        spikesBySlotAndNum.put("HEAD_FRONT_1", SLOT_FRONT_1);
        spikesBySlotAndNum.put("HEAD_BACK_0", SLOT_BACK_0);
        return spikesBySlotAndNum;
    }

    /**
     * Removes a spike at the specified slot number and slot position.
     *
     * @param slotNum The slot number of the spike to remove.
     * @param slotPos The slot position of the spike to remove (FRONT or BACK).
     */
    @Override
    public void removeSpikeBySlot(int slotNum, BodySlotEnum slotPos) {
        if (slotPos == BodySlotEnum.FRONT) {
            if (slotNum == 0) {
                SLOT_FRONT_0 = null;
            } else if (slotNum == 1) {
                SLOT_FRONT_1 = null;
            }
        } else if (slotPos == BodySlotEnum.BACK) {
            if (slotNum == 0) {
                SLOT_BACK_0 = null;
            }
        }
    }

    /**
     * Removes a spike from the body part entity.
     *
     * @param entity The SpikeEntity object representing the spike to be removed.
     */
    @Override
    public void removeSpikeBySpikeEntity(SpikeEntity entity) {
        if (entity == SLOT_FRONT_0) {
            SLOT_FRONT_0 = null;
        } else if (entity == SLOT_FRONT_1) {
            SLOT_FRONT_1 = null;
        } else if (entity == SLOT_BACK_0) {
            SLOT_BACK_0 = null;
        }
    }

    @Override
    public String toString() {
        return "HeadPartEntity{" +
                "maxQtySpikes=" + maxQtySpikes +
                ", SLOT_FRONT_0=" + SLOT_FRONT_0 +
                ", SLOT_FRONT_1=" + SLOT_FRONT_1 +
                ", SLOT_BACK_0=" + SLOT_BACK_0 +
                ", bodyPart=" + bodyPart +
                '}';
    }
}
