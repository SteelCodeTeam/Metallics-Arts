package net.rudahee.metallics_arts.modules.player;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class InvestedPlayerDataProvider implements ICapabilitySerializable<CompoundNBT> {

    private final DefaultInvestedPlayerData data = new DefaultInvestedPlayerData();
    private final LazyOptional<IInvestedPlayerData> optionalData = LazyOptional.of(()->this.data);

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return InvestedPlayerCapability.PLAYER_CAP.orEmpty(cap, this.optionalData.cast());
    }

    @Override
    public CompoundNBT serializeNBT() {
        if (InvestedPlayerCapability.PLAYER_CAP == null) {
            return new CompoundNBT();
        } else {
            return (CompoundNBT) InvestedPlayerCapability.PLAYER_CAP.writeNBT(this.data, null);
        }
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        if (InvestedPlayerCapability.PLAYER_CAP != null) {
            InvestedPlayerCapability.PLAYER_CAP.readNBT(this.data, null, nbt);
        }
    }
    public void invalidate(){
        this.optionalData.invalidate();
    }

}
