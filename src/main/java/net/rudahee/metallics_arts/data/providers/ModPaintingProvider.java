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

/**
 * A provider for custom painting variants in the mod.
 * It is responsible for registering painting variants and managing invested player data.
 *
 * @author SteelCode Team
 * @since 1.5.1
 */
public class ModPaintingProvider {


    // The DeferredRegister for registering custom PaintingVariants.
    public static final DeferredRegister<PaintingVariant> PAINTING_VARIANTS =
            DeferredRegister.create(ForgeRegistries.PAINTING_VARIANTS, MetallicsArts.MOD_ID);

    // The RegistryObject for the Inquisitor Painting variant.
    public static final RegistryObject<PaintingVariant> INQUISITOR_PAINTING = PAINTING_VARIANTS.register("inquisitor_painting",
            () -> new PaintingVariant(16,16));

    // The RegistryObject for the Fun Cobber Painting variant.
    public static final RegistryObject<PaintingVariant> FUN_COBBER_PAINTING = PAINTING_VARIANTS.register("fun_cobber_painting",
            () -> new PaintingVariant(16,32));

    // The RegistryObject for the Sanfre Painting variant.
    public static final RegistryObject<PaintingVariant> SANFRE_PAINTING = PAINTING_VARIANTS.register("sanfre_painting",
     () -> new PaintingVariant(64,64));


    /**
     * Registers the painting variants with the given event bus.
     *
     * @param eventBus the event bus to register the painting variants
     */
    public static void register(IEventBus eventBus){
        PAINTING_VARIANTS.register(eventBus);
    }

    /**
     * A custom data provider for invested player data.
     * It implements the ICapabilitySerializable interface to allow serialization and deserialization of the data.
     */
    public static class ModInvestedDataProvider implements ICapabilitySerializable<CompoundTag> {

        private final InvestedPlayerData data = new InvestedPlayerData();
        private final LazyOptional<IInvestedPlayerData> dataOptional = LazyOptional.of(() -> this.data);

        /**
         * Constructs a new instance of the ModInvestedDataProvider class.
         */
        public ModInvestedDataProvider() {
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
            return ModBlocksRegister.InvestedCapabilityRegister.PLAYER_CAP.orEmpty(cap, this.dataOptional.cast());
        }

        /**
         * Serializes the invested player data into a CompoundTag.
         *
         * @return a CompoundTag containing the serialized invested player data
         */
        @Override
        public CompoundTag serializeNBT() {
            if (ModBlocksRegister.InvestedCapabilityRegister.PLAYER_CAP == null) {
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
            if (ModBlocksRegister.InvestedCapabilityRegister.PLAYER_CAP != null) {
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
}
