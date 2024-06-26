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
import net.rudahee.metallics_arts.data.player.data.IInvestedPlayerData;
import net.rudahee.metallics_arts.data.player.data.InvestedPlayerData;
import net.rudahee.metallics_arts.setup.registries.InvestedPlayerCapabilityRegister;
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
    public static final DeferredRegister<PaintingVariant> PAINTING_VARIANTS2 =
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

    // The RegistryObject for the Koloss Painting by Gar Leyva variant.
    public static final RegistryObject<PaintingVariant> KOLOSS_PAINTING = PAINTING_VARIANTS.register("gar_leyva_koloss_painting",
            () -> new PaintingVariant(48,64));

    // The RegistryObject for the Armonia Painting by Gar Leyva variant.
    public static final RegistryObject<PaintingVariant> ARMONIA_PAINTING = PAINTING_VARIANTS.register("gar_leyva_armonia_painting",
            () -> new PaintingVariant(32,48));

    public static final RegistryObject<PaintingVariant> CARLOS1 = PAINTING_VARIANTS2.register("carlos_wk_art_kelsier_painting",
            () -> new PaintingVariant(32,48));
    public static final RegistryObject<PaintingVariant> CARLOS2 = PAINTING_VARIANTS.register("carlos_wk_art_ascension_painting",
            () -> new PaintingVariant(32,48));
    public static final RegistryObject<PaintingVariant> CARLOS3 = PAINTING_VARIANTS.register("carlos_wk_art_windows_pose_painting",
            () -> new PaintingVariant(32,48));
    public static final RegistryObject<PaintingVariant> CARLOS4 = PAINTING_VARIANTS.register("carlos_wk_art_luthadel_painting",
            () -> new PaintingVariant(32,48));
    public static final RegistryObject<PaintingVariant> CARLOS5 = PAINTING_VARIANTS.register("carlos_wk_art_red_sun_painting",
            () -> new PaintingVariant(32,48));

    /**
     * Registers the painting variants with the given event bus.
     *
     * @param eventBus the event bus to register the painting variants
     */
    public static void register(IEventBus eventBus) {
        PAINTING_VARIANTS.register(eventBus);
        PAINTING_VARIANTS2.register(eventBus);
    }

}
