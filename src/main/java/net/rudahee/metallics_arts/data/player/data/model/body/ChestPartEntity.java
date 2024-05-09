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


public final class ChestPartEntity extends BodyPartEntity {

    private Integer maxQtySpikes;
    private SpikeEntity SLOT_FRONT_0;
    private SpikeEntity SLOT_FRONT_1;
    private SpikeEntity SLOT_FRONT_2;
    private SpikeEntity SLOT_FRONT_3;
    private SpikeEntity SLOT_FRONT_4;
    private SpikeEntity SLOT_FRONT_5;

    public ChestPartEntity() {
        super(BodyPartEnum.CHEST);

        addSpikeBySlot(null, 0, BodySlotEnum.FRONT);
        addSpikeBySlot(null, 1, BodySlotEnum.FRONT);
        addSpikeBySlot(null, 2, BodySlotEnum.FRONT);
        addSpikeBySlot(null, 3, BodySlotEnum.FRONT);
        addSpikeBySlot(null, 4, BodySlotEnum.FRONT);
        addSpikeBySlot(null, 5, BodySlotEnum.FRONT);
        setMaxQtySpikes(6);
    }

    @Override
    public void setBodyPart(BodyPartEnum bodyPart) {
        this.bodyPart = bodyPart;
    }


    @Override
    public Integer getMaxQtySpikes() {
        return maxQtySpikes;
    }


    @Override
    public void setMaxQtySpikes(Integer maxQtySpikes) {
        this.maxQtySpikes = maxQtySpikes;
    }

    @Override
    public Integer getActualQtySpikes() {
        return Math.toIntExact(Stream.of(SLOT_FRONT_0, SLOT_FRONT_1, SLOT_FRONT_2, SLOT_FRONT_3, SLOT_FRONT_4, SLOT_FRONT_5).filter(Objects::nonNull).count());
    }


    @Override
    public void addSpikeBySlot(SpikeEntity entity, int slotNum, BodySlotEnum slotPos) {
        if (slotPos == BodySlotEnum.FRONT) {
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


    @Override
    public void addSpikeRandom(SpikeEntity entity) {

        if (SLOT_FRONT_0 == null) {
            SLOT_FRONT_0 = entity;
        } else if (SLOT_FRONT_1 == null) {
            SLOT_FRONT_1 = entity;
        } else if (SLOT_FRONT_2 == null) {
            SLOT_FRONT_2 = entity;
        } else if (SLOT_FRONT_3 == null) {
            SLOT_FRONT_3 = entity;
        } else if (SLOT_FRONT_4 == null) {
            SLOT_FRONT_4 = entity;
        } else if (SLOT_FRONT_5 == null) {
            SLOT_FRONT_5 = entity;
        } else {
            System.out.println("No se agrego la entidad " + entity + " \n EN CHEST");
        }
    }


    @Override
    public SpikeEntity getSpikeBySlot(int slotNum, BodySlotEnum slotPos) {
        if (slotPos == BodySlotEnum.FRONT) {
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


    @Override
    public List<SpikeEntity> getAllSpikes() {
        return Stream.of(SLOT_FRONT_0, SLOT_FRONT_1, SLOT_FRONT_2, SLOT_FRONT_3, SLOT_FRONT_4, SLOT_FRONT_5)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }


    @Override
    public HashMap<String, SpikeEntity> getAllSpikesBySlotAndNum() {
        HashMap<String, SpikeEntity> spikesBySlotAndNum = new HashMap<>();
        spikesBySlotAndNum.put("CHEST_FRONT_0", SLOT_FRONT_0);
        spikesBySlotAndNum.put("CHEST_FRONT_1", SLOT_FRONT_1);
        spikesBySlotAndNum.put("CHEST_FRONT_2", SLOT_FRONT_2);
        spikesBySlotAndNum.put("CHEST_FRONT_3", SLOT_FRONT_3);
        spikesBySlotAndNum.put("CHEST_FRONT_4", SLOT_FRONT_4);
        spikesBySlotAndNum.put("CHEST_FRONT_5", SLOT_FRONT_5);
        return spikesBySlotAndNum;
    }


    @Override
    public void removeSpikeBySlot(int slotNum, BodySlotEnum slotPos) {
        if (slotPos == BodySlotEnum.FRONT) {
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


    @Override
    public void removeSpikeBySpikeEntity(SpikeEntity entity) {
        if (entity == SLOT_FRONT_0) {
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
        return "ChestPartEntity{" +
                "maxQtySpikes=" + maxQtySpikes +
                ", SLOT_FRONT_0=" + SLOT_FRONT_0 +
                ", SLOT_FRONT_1=" + SLOT_FRONT_1 +
                ", SLOT_FRONT_2=" + SLOT_FRONT_2 +
                ", SLOT_FRONT_3=" + SLOT_FRONT_3 +
                ", SLOT_FRONT_4=" + SLOT_FRONT_4 +
                ", SLOT_FRONT_5=" + SLOT_FRONT_5 +
                ", bodyPart=" + bodyPart +
                '}';
    }
}
