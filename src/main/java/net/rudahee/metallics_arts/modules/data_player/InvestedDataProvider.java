package net.rudahee.metallics_arts.modules.data_player;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class InvestedDataProvider implements ICapabilitySerializable<CompoundTag> {

    private final DefaultInvestedPlayerData data = new DefaultInvestedPlayerData();
    private final LazyOptional<IDefaultInvestedPlayerData> dataOptional = LazyOptional.of(() -> this.data);

    public InvestedDataProvider() {
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return InvestedCapability.PLAYER_CAP.orEmpty(cap, this.dataOptional.cast());
    }

    @Override
    public CompoundTag serializeNBT() {
        if (InvestedCapability.PLAYER_CAP == null) {
            return new CompoundTag();
        } else {
            return data.save();
        }

    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        if (InvestedCapability.PLAYER_CAP != null) {
            data.load(nbt);
        }
    }

    public void invalidate() {
        this.dataOptional.invalidate();
    }
}
