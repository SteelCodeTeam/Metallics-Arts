package net.rudahee.metallics_arts.modules.player;

import net.minecraft.data.DirectoryCache;
import net.minecraft.data.IDataProvider;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.rudahee.metallics_arts.setup.DataGenerators;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.IOException;

public class InvestedPlayerDataProvider implements IDataProvider {

    private final DefaultInvestedPlayerData data = new DefaultInvestedPlayerData();

    private final Capability<IInvestedPlayerData> capability = InvestedPlayerCapability.PLAYER_CAP;
    private final InvestedPlayerCapability capabilityInstance = new InvestedPlayerCapability();

    private final LazyOptional<IInvestedPlayerData> optionalData = LazyOptional.of(()->this.data);

    @Nonnull
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap) {
        return this.getCapability(cap, null);
    }

    @Nonnull
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == capability) {
            return LazyOptional.of(() -> capabilityInstance).cast();
        } else{
            return LazyOptional.empty();
        }
    }


    @Override
    public void run(DirectoryCache cache) {
    }

    @Override
    public String getName() {
        return "invested_player_data_provider";
    }
}
