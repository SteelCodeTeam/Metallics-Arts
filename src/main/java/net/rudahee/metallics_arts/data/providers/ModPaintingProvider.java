package net.rudahee.metallics_arts.data.providers;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.data.player.IInvestedPlayerData;
import net.rudahee.metallics_arts.data.player.InvestedPlayerData;
import net.rudahee.metallics_arts.setup.registries.ModBlocksRegister;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ModPaintingProvider {

    public static final DeferredRegister<PaintingVariant>PAINTING_VARIANTS =
            DeferredRegister.create(ForgeRegistries.PAINTING_VARIANTS, MetallicsArts.MOD_ID);


    public static final RegistryObject<PaintingVariant> INQUISITOR_PAINTING = PAINTING_VARIANTS.register("inquisitor_painting",
            () -> new PaintingVariant(16,16));

    public static final RegistryObject<PaintingVariant> FUN_COBBER_PAINTING = PAINTING_VARIANTS.register("fun_cobber_painting",
            () -> new PaintingVariant(16,32));

     public static final RegistryObject<PaintingVariant> SANFRE_PAINTING = PAINTING_VARIANTS.register("sanfre_painting",
     () -> new PaintingVariant(64,64));


    public static void register(IEventBus eventBus){

        PAINTING_VARIANTS.register(eventBus);

    }

    public static class ModInvestedDataProvider implements ICapabilitySerializable<CompoundTag> {

        private final InvestedPlayerData data = new InvestedPlayerData();
        private final LazyOptional<IInvestedPlayerData> dataOptional = LazyOptional.of(() -> this.data);

        public ModInvestedDataProvider() {
        }
        @Nonnull
        @Override
        public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
            return ModBlocksRegister.InvestedCapabilityRegister.PLAYER_CAP.orEmpty(cap, this.dataOptional.cast());
        }
        @Override
        public CompoundTag serializeNBT() {
            if (ModBlocksRegister.InvestedCapabilityRegister.PLAYER_CAP == null) {
                return new CompoundTag();
            } else {
                return data.save();
            }

        }
        @Override
        public void deserializeNBT(CompoundTag nbt) {
            if (ModBlocksRegister.InvestedCapabilityRegister.PLAYER_CAP != null) {
                data.load(nbt);
            }
        }

        public void invalidate() {
            this.dataOptional.invalidate();
        }
    }
}
