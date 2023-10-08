package net.rudahee.metallics_arts.data.player.data.model;

import net.rudahee.metallics_arts.data.enums.implementations.BodyPartEnum;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.enums.implementations.TypeOfSpikeEnum;
import net.rudahee.metallics_arts.modules.error_handling.exceptions.PlayerDataException;
import net.rudahee.metallics_arts.modules.error_handling.messages.ErrorTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BodyPartEntity {

    private Integer maxSpikes;
    private Integer actualSpikes;
    private List<SpikeEntity> spikes;

    public BodyPartEntity(BodyPartEnum bodyPart) {
        if (bodyPart.equals(BodyPartEnum.HEAD)) {
            this.maxSpikes = 3;
        } else if (bodyPart.equals(BodyPartEnum.ARMS)) {
            this.maxSpikes = 8;
        } else if (bodyPart.equals(BodyPartEnum.CHEST)) {
            this.maxSpikes = 10;
        } else if (bodyPart.equals(BodyPartEnum.BACK)) {
            this.maxSpikes = 11;
        } else if (bodyPart.equals(BodyPartEnum.LEGS)) {
            this.maxSpikes = 8;
        }

        this.actualSpikes = 0;

        this.spikes = new ArrayList<>();
    }

    public BodyPartEntity(BodyPartEnum bodyPart, Integer actualSpikes, List<SpikeEntity> spikes) throws PlayerDataException {
        if (bodyPart.equals(BodyPartEnum.HEAD)) {
            this.maxSpikes = 3;
        } else if (bodyPart.equals(BodyPartEnum.ARMS)) {
            this.maxSpikes = 8;
        } else if (bodyPart.equals(BodyPartEnum.CHEST)) {
            this.maxSpikes = 10;
        } else if (bodyPart.equals(BodyPartEnum.BACK)) {
            this.maxSpikes = 11;
        } else if (bodyPart.equals(BodyPartEnum.LEGS)) {
            this.maxSpikes = 8;
        }

        if (spikes.size() <= getMaxSpikes()) {
            this.actualSpikes = actualSpikes;
            this.spikes = spikes;
        } else {
            throw new PlayerDataException(ErrorTypes.PLAYER_DATA_SPIKES_OVERLOAD);
        }

    }

    public Integer getMaxSpikes() {
        return maxSpikes;
    }

    public Integer getActualSpikes() {
        return actualSpikes;
    }

    public void setActualSpikes(Integer actualSpikes) {
        this.actualSpikes = actualSpikes;
    }

    public List<SpikeEntity> getSpikes() {
        return spikes;
    }

    public void setSpikes(List<SpikeEntity> spikes) throws PlayerDataException {
        if (spikes.size() <= getMaxSpikes()) {
            this.spikes = spikes;
            this.actualSpikes = this.spikes.size();
        } else {
            throw new PlayerDataException(ErrorTypes.PLAYER_DATA_SPIKES_OVERLOAD);
        }
    }

    public void addSpike(SpikeEntity spike) throws PlayerDataException {
        if (getActualSpikes() <= getMaxSpikes()) {
            this.spikes.add(spike);
            this.setActualSpikes(this.getActualSpikes() + 1);
        } else {
            throw new PlayerDataException(ErrorTypes.PLAYER_DATA_SPIKES_OVERLOAD);
        }

    }

    public void removeSpike(SpikeEntity spike) {
        Optional<SpikeEntity> spikeToDelete = this.spikes.stream()
                .filter(spikeSearched -> spikeSearched.getMetal().equals(spike.getMetal()) && spikeSearched.getType().equals(spike.getType()))
                .findFirst();

        spikeToDelete.ifPresent(spikeEntity -> this.spikes.remove(spikeEntity));
        this.setActualSpikes(this.getActualSpikes() - 1);
    }
}
