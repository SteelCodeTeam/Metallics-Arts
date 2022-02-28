package net.rudahee.metallics_arts.modules.DataPlayer;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class InvestedDataProvider implements ICapabilitySerializable<CompoundNBT> {

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
    public CompoundNBT serializeNBT() {
        if (InvestedCapability.PLAYER_CAP == null) {
            return new CompoundNBT();
        } else {
            return (CompoundNBT) InvestedCapability.PLAYER_CAP.writeNBT(this.data, null);
        }

    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        if (InvestedCapability.PLAYER_CAP != null) {
            InvestedCapability.PLAYER_CAP.readNBT(this.data, null, nbt);
        }
    }

    public void invalidate() {
        this.dataOptional.invalidate();
    }
}
