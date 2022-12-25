package net.rudahee.metallics_arts.data.providers;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.rudahee.metallics_arts.MetallicsArts;

public class ModPaintingProvider {

    public static final DeferredRegister<PaintingVariant>PAINTING_VARIANTS =
            DeferredRegister.create(ForgeRegistries.PAINTING_VARIANTS, MetallicsArts.MOD_ID);
/**
    //ALOMANTIC METALLS
    public static final RegistryObject<PaintingVariant> ALUMINIUM_ALOMANTIC_PAINTING = PAINTING_VARIANTS.register("aluminium_alomantic_painting",
            () -> new PaintingVariant(32,32));
    public static final RegistryObject<PaintingVariant> ATIUM_ALOMANTIC_PAINTING = PAINTING_VARIANTS.register("atium_alomantic_painting",
            () -> new PaintingVariant(32,32));
    public static final RegistryObject<PaintingVariant> BENDALLOY_ALOMANTIC_PAINTING = PAINTING_VARIANTS.register("bendalloy_alomantic_painting",
            () -> new PaintingVariant(32,32));
    public static final RegistryObject<PaintingVariant> BRASS_ALOMANTIC_PAINTING = PAINTING_VARIANTS.register("brass_alomantic_painting",
            () -> new PaintingVariant(32,32));
    public static final RegistryObject<PaintingVariant> BRONZE_ALOMANTIC_PAINTING = PAINTING_VARIANTS.register("bronze_alomantic_painting",
            () -> new PaintingVariant(32,32));
    public static final RegistryObject<PaintingVariant> CADMIUM_ALOMANTIC_PAINTING = PAINTING_VARIANTS.register("cadmium_alomantic_painting",
            () -> new PaintingVariant(32,32));
    public static final RegistryObject<PaintingVariant> CHROMIUM_ALOMANTIC_PAINTING = PAINTING_VARIANTS.register("chromium_alomantic_painting",
            () -> new PaintingVariant(32,32));
    public static final RegistryObject<PaintingVariant> COPPER_ALOMANTIC_PAINTING = PAINTING_VARIANTS.register("copper_alomantic_painting",
            () -> new PaintingVariant(32,32));
    public static final RegistryObject<PaintingVariant> DURALUMIN_ALOMANTIC_PAINTING = PAINTING_VARIANTS.register("duralumin_alomantic_painting",
            () -> new PaintingVariant(32,32));
    public static final RegistryObject<PaintingVariant> ELECTRUM_ALOMANTIC_PAINTING = PAINTING_VARIANTS.register("electrum_alomantic_painting",
            () -> new PaintingVariant(32,32));
    public static final RegistryObject<PaintingVariant> ETTMETAL_ALOMANTIC_PAINTING = PAINTING_VARIANTS.register("ettmetal_alomantic_painting",
            () -> new PaintingVariant(32,32));
    public static final RegistryObject<PaintingVariant> GOLD_ALOMANTIC_PAINTING = PAINTING_VARIANTS.register("gold_alomantic_painting",
            () -> new PaintingVariant(32,32));
    public static final RegistryObject<PaintingVariant> IRON_ALOMANTIC_PAINTING = PAINTING_VARIANTS.register("iron_alomantic_painting",
            () -> new PaintingVariant(32,32));
    public static final RegistryObject<PaintingVariant> LERASIUM_ALOMANTIC_PAINTING = PAINTING_VARIANTS.register("lerasium_alomantic_painting",
            () -> new PaintingVariant(32,32));
    public static final RegistryObject<PaintingVariant> MALATIUM_ALOMANTIC_PAINTING = PAINTING_VARIANTS.register("malatium_alomantic_painting",
            () -> new PaintingVariant(32,32));
    public static final RegistryObject<PaintingVariant> NICROSIL_ALOMANTIC_PAINTING = PAINTING_VARIANTS.register("nicrosil_alomantic_painting",
            () -> new PaintingVariant(32,32));
    public static final RegistryObject<PaintingVariant> PEWTER_ALOMANTIC_PAINTING = PAINTING_VARIANTS.register("pewter_alomantic_painting",
            () -> new PaintingVariant(32,32));
    public static final RegistryObject<PaintingVariant> STEEL_ALOMANTIC_PAINTING = PAINTING_VARIANTS.register("steel_alomantic_painting",
            () -> new PaintingVariant(32,32));
    public static final RegistryObject<PaintingVariant> TIN_ALOMANTIC_PAINTING = PAINTING_VARIANTS.register("tin_alomantic_painting",
            () -> new PaintingVariant(32,32));
    public static final RegistryObject<PaintingVariant> ZINC_ALOMANTIC_PAINTING = PAINTING_VARIANTS.register("zinc_alomantic_painting",
            () -> new PaintingVariant(32,32));

    //FERUCHEMIC METALLS
    public static final RegistryObject<PaintingVariant> ALUMINIUM_FERUCHEMIC_PAINTING = PAINTING_VARIANTS.register("aluminium_feruchemic_painting",
            () -> new PaintingVariant(32,32));
    public static final RegistryObject<PaintingVariant> ATIUM_FERUCHEMIC_PAINTING = PAINTING_VARIANTS.register("atium_feruchemic_painting",
            () -> new PaintingVariant(32,32));
    public static final RegistryObject<PaintingVariant> BENDALLOY_FERUCHEMIC_PAINTING = PAINTING_VARIANTS.register("bendalloy_feruchemic_painting",
            () -> new PaintingVariant(32,32));
    public static final RegistryObject<PaintingVariant> BRASS_FERUCHEMIC_PAINTING = PAINTING_VARIANTS.register("brass_feruchemic_painting",
            () -> new PaintingVariant(32,32));
    public static final RegistryObject<PaintingVariant> BRONZE_FERUCHEMIC_PAINTING = PAINTING_VARIANTS.register("bronze_feruchemic_painting",
            () -> new PaintingVariant(32,32));
    public static final RegistryObject<PaintingVariant> CADMIUM_FERUCHEMIC_PAINTING = PAINTING_VARIANTS.register("cadmium_feruchemic_painting",
            () -> new PaintingVariant(32,32));
    public static final RegistryObject<PaintingVariant> CHROMIUM_FERUCHEMIC_PAINTING = PAINTING_VARIANTS.register("chromium_feruchemic_painting",
            () -> new PaintingVariant(32,32));
    public static final RegistryObject<PaintingVariant> COPPER_FERUCHEMIC_PAINTING = PAINTING_VARIANTS.register("copper_feruchemic_painting",
            () -> new PaintingVariant(32,32));
    public static final RegistryObject<PaintingVariant> DURALUMIN_FERUCHEMIC_PAINTING = PAINTING_VARIANTS.register("duralumin_feruchemic_painting",
            () -> new PaintingVariant(32,32));
    public static final RegistryObject<PaintingVariant> ELECTRUM_FERUCHEMIC_PAINTING = PAINTING_VARIANTS.register("electrum_feruchemic_painting",
            () -> new PaintingVariant(32,32));
    public static final RegistryObject<PaintingVariant> ETTMETAL_FERUCHEMIC_PAINTING = PAINTING_VARIANTS.register("ettmetal_feruchemic_painting",
            () -> new PaintingVariant(32,32));
    public static final RegistryObject<PaintingVariant> GOLD_FERUCHEMIC_PAINTING = PAINTING_VARIANTS.register("gold_feruchemic_painting",
            () -> new PaintingVariant(32,32));
    public static final RegistryObject<PaintingVariant> IRON_FERUCHEMIC_PAINTING = PAINTING_VARIANTS.register("iron_feruchemic_painting",
            () -> new PaintingVariant(32,32));
    public static final RegistryObject<PaintingVariant> LERASIUM_FERUCHEMIC_PAINTING = PAINTING_VARIANTS.register("lerasium_feruchemic_painting",
            () -> new PaintingVariant(32,32));
    public static final RegistryObject<PaintingVariant> MALATIUM_FERUCHEMIC_PAINTING = PAINTING_VARIANTS.register("malatium_feruchemic_painting",
            () -> new PaintingVariant(32,32));
    public static final RegistryObject<PaintingVariant> NICROSIL_FERUCHEMIC_PAINTING = PAINTING_VARIANTS.register("nicrosil_feruchemic_painting",
            () -> new PaintingVariant(32,32));
    public static final RegistryObject<PaintingVariant> PEWTER_FERUCHEMIC_PAINTING = PAINTING_VARIANTS.register("pewter_feruchemic_painting",
            () -> new PaintingVariant(32,32));
    public static final RegistryObject<PaintingVariant> STEEL_FERUCHEMIC_PAINTING = PAINTING_VARIANTS.register("steel_feruchemic_painting",
            () -> new PaintingVariant(32,32));
    public static final RegistryObject<PaintingVariant> TIN_FERUCHEMIC_PAINTING = PAINTING_VARIANTS.register("tin_feruchemic_painting",
            () -> new PaintingVariant(32,32));
    public static final RegistryObject<PaintingVariant> ZINC_FERUCHEMIC_PAINTING = PAINTING_VARIANTS.register("zinc_feruchemic_painting",
            () -> new PaintingVariant(32,32));

 JSON textr
    "metallics_arts:aluminium_alomantic_painting","metallics_arts:atium_alomantic_painting","metallics_arts:bendalloy_alomantic_painting",
            "metallics_arts:brass_alomantic_painting","metallics_arts:bronze_alomantic_painting","metallics_arts:cadmium_alomantic_painting",
            "metallics_arts:chromium_alomantic_painting","metallics_arts:copper_alomantic_painting","metallics_arts:duralumin_alomantic_painting",
            "metallics_arts:electrum_alomantic_painting","metallics_arts:ettmetal_alomantic_painting","metallics_arts:gold_alomantic_painting",
            "metallics_arts:iron_alomantic_painting","metallics_arts:lerasium_alomantic_painting","metallics_arts:malatium_alomantic_painting",
            "metallics_arts:nicrosil_alomantic_painting","metallics_arts:pewter_alomantic_painting","metallics_arts:steel_alomantic_painting",
            "metallics_arts:tin_alomantic_painting","metallics_arts:zinc_alomantic_painting",

            "metallics_arts:aluminium_feruchemic_painting","metallics_arts:atium_feruchemic_painting","metallics_arts:bendalloy_feruchemic_painting",
            "metallics_arts:brass_feruchemic_painting","metallics_arts:bronze_feruchemic_painting","metallics_arts:cadmium_feruchemic_painting",
            "metallics_arts:chromium_feruchemic_painting","metallics_arts:copper_feruchemic_painting","metallics_arts:duralumin_feruchemic_painting",
            "metallics_arts:electrum_feruchemic_painting","metallics_arts:ettmetal_feruchemic_painting","metallics_arts:gold_feruchemic_painting",
            "metallics_arts:iron_feruchemic_painting","metallics_arts:lerasium_feruchemic_painting","metallics_arts:malatium_feruchemic_painting",
            "metallics_arts:nicrosil_feruchemic_painting","metallics_arts:pewter_feruchemic_painting","metallics_arts:steel_feruchemic_painting",
            "metallics_arts:tin_feruchemic_painting","metallics_arts:zinc_feruchemic_painting",
    */

    //Other paintingd

    public static final RegistryObject<PaintingVariant> INQUISITOR_PAINTING = PAINTING_VARIANTS.register("inquisitor_painting",
            () -> new PaintingVariant(16,16));

    public static final RegistryObject<PaintingVariant> FUN_COBBER_PAINTING = PAINTING_VARIANTS.register("fun_cobber_painting",
            () -> new PaintingVariant(16,32));

     public static final RegistryObject<PaintingVariant> SANFRE_PAINTING = PAINTING_VARIANTS.register("sanfre_painting",
     () -> new PaintingVariant(64,64));




    //TO MAKE THE PAINTINGS WORK WE HAVE TO ADD THIS LINES TO DE placeable.json :  , "metallics_arts:fun_cobber_painting","metallics_arts:inquisitor_painting"

    public static void register(IEventBus eventBus){

        PAINTING_VARIANTS.register(eventBus);

    }
}
