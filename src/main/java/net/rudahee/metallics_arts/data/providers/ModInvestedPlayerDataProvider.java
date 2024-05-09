package net.rudahee.metallics_arts.data.providers;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.rudahee.metallics_arts.data.player.data.IInvestedPlayerData;
import net.rudahee.metallics_arts.data.player.data.InvestedPlayerData;
import net.rudahee.metallics_arts.setup.registries.InvestedPlayerCapabilityRegister;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ModInvestedPlayerDataProvider implements ICapabilitySerializable<CompoundTag> {
    private final InvestedPlayerData data = new InvestedPlayerData();
    private final LazyOptional<IInvestedPlayerData> dataOptional = LazyOptional.of(() -> this.data);

    /**
     * Constructs a new instance of the ModInvestedDataProvider class.
     */
    public ModInvestedPlayerDataProvider() {
    }

    /**
     * Gets the capability of the invested player data.
     *
     * @param cap   the capability instance requested
     * @param side  the direction to access the capability, can be null
     * @return LazyOptional containing the capability instance if available, otherwise empty
     */
    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return InvestedPlayerCapabilityRegister.PLAYER_CAP.orEmpty(cap, this.dataOptional.cast());
    }

    /**
     * Serializes the invested player data into a CompoundTag.
     *
     * @return a CompoundTag containing the serialized invested player data
     */
    @Override
    public CompoundTag serializeNBT() {
        if (InvestedPlayerCapabilityRegister.PLAYER_CAP == null) {
            return new CompoundTag();
        } else {
            return data.save();
        }

    }

    /**
     * Deserializes the invested player data from a CompoundTag.
     *
     * @param nbt the CompoundTag containing the serialized invested player data
     */
    @Override
    public void deserializeNBT(CompoundTag nbt) {
        if (InvestedPlayerCapabilityRegister.PLAYER_CAP != null) {
            data.load(nbt);
        }
    }

    /**
     * Invalidates the lazy optional data.
     */
    public void invalidate() {
        this.dataOptional.invalidate();
    }
}

