package net.rudahee.metallics_arts.data.player.data.model.body;

import net.rudahee.metallics_arts.data.player.data.model.SpikeEntity;
import net.rudahee.metallics_arts.data.player.data.model.enums.BodyPartEnum;
import net.rudahee.metallics_arts.data.player.data.model.enums.BodySlotEnum;

import java.util.HashMap;
import java.util.List;

/**
 * The abstract class `BodyPartEntity` represents a body part entity in a player's body. It is the base class for specific body part entities such as ArmsPartEntity, BackPartEntity
 *, ChestPartEntity, HeadPartEntity, and LegsPartEntity. This class provides common functionality and properties for all body part entities.
 */
public abstract sealed class BodyPartEntity permits ArmsPartEntity, BackPartEntity, ChestPartEntity, HeadPartEntity, LegsPartEntity {

    BodyPartEnum bodyPart;

    /**
     * Constructs a new BodyPartEntity object with the specified body part.
     *
     * @param bodyPart the body part to be assigned to the entity
     */
    public BodyPartEntity(BodyPartEnum bodyPart) {
        this.bodyPart = bodyPart;
    }

    /**
     * Retrieves the body part associated with the entity.
     *
     * @return the body part of the entity
     */
    public BodyPartEnum getBodyPart() {
        return bodyPart;
    }

    /**
     * Sets the body part of the BodyPartEntity object.
     *
     * @param bodyPart the body part to be assigned to the entity
     */
    public abstract void setBodyPart(BodyPartEnum bodyPart);

    /**
     * Retrieves the maximum quantity of spikes that can be added.
     *
     * @return The maximum quantity of spikes.
     */
    public abstract Integer getMaxQtySpikes();

    /**
     * Sets the maximum quantity of spikes for the body part entity.
     *
     * @param maxQtySpikes The maximum quantity of spikes to set.
     */
    public abstract void setMaxQtySpikes(Integer maxQtySpikes);

    /**
     * Retrieves the actual quantity of spikes in the body part entity.
     *
     * @return The actual quantity of spikes.
     */
    public abstract Integer getActualQtySpikes();


    /**
     * Adds a spike to the specified slot of the body part entity.
     *
     * @param entity The spike entity to add.
     * @param slotNum The slot number of the body part entity.
     * @param slotPos The slot position (FRONT or BACK) of the body part entity.
     */
    public abstract void addSpikeBySlot(SpikeEntity entity, int slotNum, BodySlotEnum slotPos);

    /**
     * Adds a spike randomly to the body part entity.
     *
     * @param entity the spike entity to add
     */
    public abstract void addSpikeRandom(SpikeEntity entity);

    /**
     * Retrieves the spike entity in the specified slot of the body part.
     *
     * @param slotNum The slot number of the body part.
     * @param slotPos The slot position (FRONT or BACK) of the body part.
     * @return The spike entity in the specified slot of the body part, or null if no spike is present.
     */
    public abstract SpikeEntity getSpikeBySlot(int slotNum, BodySlotEnum slotPos);

    /**
     * Retrieves all spikes associated with the body part entity.
     *
     * @return A list of SpikeEntity objects representing all the spikes.
     */
    public abstract List<SpikeEntity> getAllSpikes();

    /**
     * Retrieves all spikes associated with the body part entity, organized by slot number and spike number.
     *
     * @return A HashMap where the key is in the format "slotNumber_spikeNumber" and the value is the SpikeEntity object representing the spike.
     */
    public abstract HashMap<String, SpikeEntity> getAllSpikesBySlotAndNum();

    /**
     * Removes a spike from the specified slot of the body part.
     *
     * @param slotNum The slot number of the body part.
     * @param slotPos The slot position (FRONT or BACK) of the body part.
     */
    public abstract void removeSpikeBySlot(int slotNum, BodySlotEnum slotPos);

    /**
     * Removes a spike from the body part entity based on the provided SpikeEntity object.
     *
     * @param entity The SpikeEntity object representing the spike to be removed from the body part entity.
     */
    public abstract void removeSpikeBySpikeEntity(SpikeEntity entity);

    @Override
    public String toString() {
        return "BodyPartEntity{" +
                "bodyPart=" + bodyPart +
                '}';
    }
}
