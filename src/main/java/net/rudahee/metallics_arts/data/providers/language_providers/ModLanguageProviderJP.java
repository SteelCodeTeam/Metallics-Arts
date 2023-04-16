package net.rudahee.metallics_arts.data.providers.language_providers;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;
import net.rudahee.metallics_arts.data.enums.implementations.languages.*;

import java.util.HashMap;
import java.util.Map;

/**
 * A custom class for providing Japanese translations for mod elements.
 *
 * @author SteelCode Team
 * @since 1.5.1
 */
public class ModLanguageProviderJP extends LanguageProvider {

    private final Map<String, String> base = new HashMap<>() {{
        put("item.metallics_arts.large_vial",CTW.LARGE.getNameInJapanese() + " " + CTW.VIAL.getNameInJapanese());
        put("item.metallics_arts.small_vial",CTW.SMALL.getNameInJapanese() + " " + CTW.VIAL.getNameInJapanese());
        put("curios.identifier.metalmind_slot",CTW.METALMIND.getNameInJapanese() + " " + CTW.SLOT.getNameInJapanese());

        put("itemGroup.metallics_arts", CTW.METALLICS_ARTS.getNameInJapanese());
        put("itemGroup.metallics_arts.decorations", CTW.DECORATIONS.getNameInJapanese() + " " + CTW.METALLICS_ARTS.getNameInJapanese());

        put("key.category_powers_metallics_arts", CTW.POWERS.getNameInJapanese() + " " + CTW.METALLICS_ARTS.getNameInJapanese());
        put("key.category.metallics_arts", CTW.METALLICS_ARTS.getNameInJapanese());
        put("key.metallics_arts.allomantic", CTW.ALLOMANTIC.getNameInJapanese() + " " + CTW.POWER_SELECTOR.getNameInJapanese() );
        put("key.metallics_arts.feruchemic", CTW.FERUCHEMICAL.getNameInJapanese() + " " + CTW.POWER_SELECTOR.getNameInJapanese());
        put("key.metallics_arts.allomantic_push",CTW.ALLOMANTIC.getNameInJapanese() + " " + CTW.PUSH.getNameInJapanese());
        put("key.metallics_arts.allomantic_pull", CTW.ALLOMANTIC.getNameInJapanese() + " " +CTW.PULL.getNameInJapanese());
        put("key.metallics_arts.vertical_jump", CTW.VERTICAL.getNameInJapanese() + " " +CTW.PUSH.getNameInJapanese());
        put("key.metallics_arts.feruchemic_decant", CTW.FERUCHEMICAL.getNameInJapanese() + " " + CTW.TAPPING.getNameInJapanese());
        put("key.metallics_arts.feruchemic_store",CTW.FERUCHEMICAL.getNameInJapanese() + " " +CTW.STORAGE.getNameInJapanese());
        put("key.metallics_arts.switch_overlay", CTW.SWITCH_OVERLAY.getNameInJapanese());

        put("metallics_arts.mental_mind_translate.has_reserve", CTW.HAS_RESERVE.getNameInJapanese());
        put("metallics_arts.mental_mind_translate.not_has_reserve", CTW.NOT_HAS_RESERVE.getNameInJapanese());
        put("metallics_arts.spike_feruchemic_power", CTW.STORAGE_POWER.getNameInJapanese() + ": " + CTW.FERUCHEMICAL.getNameInJapanese());
        put("metallics_arts.spike_allomantic_power", CTW.STORAGE_POWER.getNameInJapanese() + ": " + CTW.ALLOMANTIC.getNameInJapanese());

        //ver porque usa "spike"
        put("metallics_arts.spike_allomantic_power.tapping_identity", CTW.TAPPING.getNameInJapanese());

        //arreglar estos de aca abajo
        put("metallics_arts.mental_mind_translate.store_identity", CTW.STORE_IDENTITY.getNameInJapanese());
        put("metallics_arts.mental_mind_translate.off_power", CTW.POWER_OFF.getNameInJapanese());
        put("metallics_arts.mental_mind.owner", CTW.OWNER.getNameInJapanese());
        put("metallics_arts.mental_mind.nobody", CTW.NOBODY.getNameInJapanese());
        put("metallics_arts.mental_mind.owner_someone", CTW.OWNER_SOMEONE.getNameInJapanese());
        put("metallics_arts.mental_mind_translate.uses", CTW.USES.getNameInJapanese());
        put("metallics_arts.mental_mind_translate.shift_info",CTW.SHIFT_TO_MORE_INFO.getNameInJapanese());

        put("item.metallics_arts.obsidian_dagger", CTW.OBSIDIAN_DAGGER.getNameInJapanese());
        put("item.metallics_arts.cristal_dagger", CTW.CRYSTAL_DAGGER.getNameInJapanese());
        put("item.metallics_arts.koloss_blade", CTW.KOLOSS_BLADE.getNameInJapanese());
        put("item.metallics_arts.dueling_staff", CTW.DUELING_STAFF.getNameInJapanese());
        put("item.metallics_arts.obsidian_axe", CTW.OBSIDIAN_AXE.getNameInJapanese());


    }};


    private final Map<String, String> ingots = new HashMap<>() {{
        for (MetalAuxiliaryInfo metal: MetalAuxiliaryInfo.values()) {
            if (!metal.isVanilla()) {
                put("item.metallics_arts." + metal.getId() + "_ingot", MetalNamesEnum.valueOf(metal.name()).getNameInJapanese() + " " + CTW.INGOT.getNameInJapanese());
            }

        }
    }};
    private final Map<String, String> rawItems = new HashMap<>() {{
        for (MetalAuxiliaryInfo metal: MetalAuxiliaryInfo.values()) {
            if (!metal.isVanilla()) {
                put("item.metallics_arts.raw_" + metal.getId(), CTW.RAW.getNameInJapanese() + " " + MetalNamesEnum.valueOf(metal.name()).getNameInJapanese());
            }
        }
    }};
    private final Map<String, String> gems = new HashMap<>() {{
        for (MetalAuxiliaryInfo metal: MetalAuxiliaryInfo.values()) {
            if (metal.isDivine()) {
                put("item.metallics_arts." + metal.getId(), MetalNamesEnum.valueOf(metal.name()).getNameInJapanese() + " " + CTW.GEM.getNameInJapanese());
            }
        }
    }};
    private final Map<String, String> nuggets = new HashMap<>() {{
        for (MetalAuxiliaryInfo metal: MetalAuxiliaryInfo.values()) {
            if (!metal.isVanilla()) {
                put("item.metallics_arts."+ metal.getId()+"_nugget",MetalNamesEnum.valueOf(metal.name()).getNameInJapanese() + " " + CTW.NUGGET.getNameInJapanese() );
            }
            if (metal.isDivine()) {
                put("item.metallics_arts." + metal.getId() + "_nugget", MetalNamesEnum.valueOf(metal.name()).getNameInJapanese() + " " + CTW.NUGGET.getNameInJapanese());
            }
        }
        put("item.metallics_arts.copper_nugget", MetalNamesEnum.COPPER.getNameInJapanese() + " " + CTW.NUGGET.getNameInJapanese());
    }};
    private final Map<String, String> blocks = new HashMap<>() {{
        for (MetalAuxiliaryInfo metal: MetalAuxiliaryInfo.values()) {
            if (!metal.isVanilla()) {
                put("block.metallics_arts." + metal.getId() + "_block", CTW.BLOCK.getNameInJapanese() + " " + MetalNamesEnum.valueOf(metal.name()).getNameInJapanese());
            }
            if (metal.isDivine()) {
                put("block.metallics_arts." + metal.getId() + "_block", CTW.BLOCK.getNameInJapanese() + " " + MetalNamesEnum.valueOf(metal.name()).getNameInJapanese());
            }
        }
    }};
    private final Map<String, String> rawBlocks = new HashMap<>() {{
        for (MetalAuxiliaryInfo metal: MetalAuxiliaryInfo.values()) {
            if (!metal.isVanilla()) {
                put("block.metallics_arts.raw_"+metal.getId()+"_block", CTW.BLOCK.getNameInJapanese()+ " " +
                        CTW.RAW.getNameInJapanese() + " " + MetalNamesEnum.valueOf(metal.name()).getNameInJapanese() );
            }
        }
    }};
    private final Map<String, String> ores = new HashMap<>() {{
        for (MetalAuxiliaryInfo metal: MetalAuxiliaryInfo.values()) {
            if (!metal.isVanilla() && !metal.isAlloy() && metal.isAppearsInStone()) {
                put("block.metallics_arts." + metal.getId()+"_ore", MetalNamesEnum.valueOf(metal.name()).getNameInJapanese() + " " + CTW.ORE.getNameInJapanese());

            }
        }

    }};
    private final Map<String, String> deepslateOres = new HashMap<>() {{
        for (MetalAuxiliaryInfo metal: MetalAuxiliaryInfo.values()) {
            if (!metal.isVanilla() && !metal.isAlloy() && metal.isAppearsInDeepslate()) {
                put("block.metallics_arts.deepslate_" + metal.getId()+"_ore", CTW.ORE.getNameInJapanese() + " " +
                        MetalNamesEnum.valueOf(metal.name()).getNameInJapanese() + " " + CTW.DEEPSLATE.getNameInJapanese());
            }
        }
    }};
    private final Map<String, String> geodeBlocks = new HashMap<>() {{
        for (MetalAuxiliaryInfo metal: MetalAuxiliaryInfo.values()) {
            if (metal.isDivine() && !metal.isAlloy()) {
                put("block.metallics_arts." + metal.getId() + "_cristal_block", MetalNamesEnum.valueOf(metal.name()).getNameInJapanese() + " " + CTW.CRISTAL.getNameInJapanese());
                put("block.metallics_arts.budding_" + metal.getId(), CTW.BUDDING.getNameInJapanese() + " " + MetalNamesEnum.valueOf(metal.name()).getNameInJapanese());
                put("block.metallics_arts." + metal.getId() + "_cluster", CTW.CLUSTER.getNameInJapanese() + " " + MetalNamesEnum.valueOf(metal.name()).getNameInJapanese());
                put("block.metallics_arts.small_" + metal.getId() + "_bud", CTW.BUD.getNameInJapanese() + " " + MetalNamesEnum.valueOf(metal.name()).getNameInJapanese() + " " +
                        CTW.SMALL.getNameInJapanese());
                put("block.metallics_arts.medium_" + metal.getId() + "_bud", CTW.BUD.getNameInJapanese() + " " + MetalNamesEnum.valueOf(metal.name()).getNameInJapanese() + " " +
                        CTW.MEDIUM.getNameInJapanese());
                put("block.metallics_arts.large_" + metal.getId() + "_bud", CTW.BUD.getNameInJapanese() + " " + MetalNamesEnum.valueOf(metal.name()).getNameInJapanese() + " " +
                        CTW.LARGE.getNameInJapanese());
            }
        }
    }};
    private final Map<String, String> spikes = new HashMap<>() {{
        for (MetalAuxiliaryInfo metal: MetalAuxiliaryInfo.values()) {
            if (!metal.isOnlyForAlloys()) {
                put("item.metallics_arts."+ metal.getId() +"_spike", MetalNamesEnum.valueOf(metal.name()).getNameInJapanese() + " " + CTW.SPIKE.getNameInJapanese());
            }
        }
    }};
    private final Map<String, String> metalMinds = new HashMap<>() {{
        for (MetalMinds metalMind : MetalMinds.values()) {
            put("item.metallics_arts.band_" + metalMind.getId(), metalMind.getNameInEnglish() + " " + CTW.BAND.getNameInJapanese());
            put("item.metallics_arts.ring_" + metalMind.getId(), metalMind.getNameInEnglish() + " " + CTW.RING.getNameInJapanese());
        }
    }};
    private final Map<String, String> icons = new HashMap<>() {{
        for (MetalAuxiliaryInfo metal: MetalAuxiliaryInfo.values()) {
            if (!metal.isOnlyForAlloys()) {
                put("item.metallics_arts."+ metal.getId()+"_allomantic_icon" , CTW.ALLOMANTIC.getNameInJapanese() + " " + MetalNamesEnum.valueOf(metal.name()).getNameInJapanese());
                put("item.metallics_arts."+ metal.getId()+"_feruchemic_icon" , CTW.FERUCHEMICAL.getNameInJapanese() + " " + MetalNamesEnum.valueOf(metal.name()).getNameInJapanese());
            }
        }
    }};
    private final Map<String, String> metals = new HashMap<>() {{
        for (MetalAuxiliaryInfo metal: MetalAuxiliaryInfo.values()) {
            if (!metal.isOnlyForAlloys()) {
                put("metallics_arts.metal_translate."+ metal.getId() , MetalNamesEnum.valueOf(metal.name()).getNameInJapanese());
            }
        }
    }};
    private final Map<String, String> powers = new HashMap<>() {{
        for (MetalAuxiliaryInfo metal: MetalAuxiliaryInfo.values()) {
            if (!metal.isOnlyForAlloys()) {
                put("key.metallics_arts."+ metal.getId() +"_power", MetalNamesEnum.valueOf(metal.name()).getNameInJapanese());
            }
        }
    }};
    private final Map<String, String> symbols = new HashMap<>() {{
        for (MetalAuxiliaryInfo metal: MetalAuxiliaryInfo.values()) {
            if (!metal.isOnlyForAlloys()) {
                put("f_"+metal.getId()+"_1", MetalNamesEnum.valueOf(metal.name()).getNameInJapanese() + " " + CTW.FERUCHEMICAL_SHADING.getNameInJapanese());
                put("f_"+metal.getId()+"_2", MetalNamesEnum.valueOf(metal.name()).getNameInJapanese() + " " + CTW.FERUCHEMICAL_SOLID.getNameInJapanese());
                put("a_"+metal.getId()+"_1", MetalNamesEnum.valueOf(metal.name()).getNameInJapanese() + " " + CTW.ALLOMANTIC_SHADING.getNameInJapanese());
                put("a_"+metal.getId()+"_2", MetalNamesEnum.valueOf(metal.name()).getNameInJapanese() + " " + CTW.ALLOMANTIC_SOLID.getNameInJapanese());
            }
        }
    }};
    private final Map<String, String> patterns = new HashMap<>() {{
        for (MetalAuxiliaryInfo metal: MetalAuxiliaryInfo.values()) {
            if (!metal.isOnlyForAlloys()) {
                put("item.metallics_arts.f_"+metal.getId()+"_pattern", MetalNamesEnum.valueOf(metal.name()).getNameInJapanese() + " " + CTW.FERUCHEMICAL_PATTERN.getNameInJapanese());
                put("item.metallics_arts.f_"+metal.getId()+"_pattern.desc", MetalNamesEnum.valueOf(metal.name()).getNameInJapanese() + " " + CTW.FERUCHEMICAL_PATTERN.getNameInJapanese());
                put("item.metallics_arts.a_"+metal.getId()+"_pattern", MetalNamesEnum.valueOf(metal.name()).getNameInJapanese() + " " + CTW.ALLOMANTIC_PATTERN.getNameInJapanese());
                put("item.metallics_arts.a_"+metal.getId()+"_pattern.desc", MetalNamesEnum.valueOf(metal.name()).getNameInJapanese() + " " + CTW.ALLOMANTIC_PATTERN.getNameInJapanese());
            }
        }

    }};
    private final Map<String, String> colors = new HashMap<>() {{
        for (ColorsNames color : ColorsNames.values()) {
            put(color.getId(),color.getNameInEnglish());
        }
    }};

    /**
     * Constructs a new ModLanguageProviderJP instance.
     *
     * @param gen               the data generator for language files
     * @param modid             the mod ID for the target mod
     * @param locale            the locale for the target language
     */
    public ModLanguageProviderJP(DataGenerator gen, String modid, String locale) {
        super(gen, modid, locale);
    }

    /**
     * Adds the Japanese translations for mod elements.
     */
    @Override
    protected void addTranslations() {

        for (String key: ingots.keySet()){
            add(key, ingots.get(key));
        }
        for (String key: gems.keySet()){
            add(key, gems.get(key));
        }
        for (String key: rawBlocks.keySet()){
            add(key, rawBlocks.get(key));
        }
        for (String key: metalMinds.keySet()){
            add(key, metalMinds.get(key));
        }

        for (String key: rawItems.keySet()){
            add(key, rawItems.get(key));
        }

        for (String key: geodeBlocks.keySet()){
            add(key, geodeBlocks.get(key));
        }

        for (String key: ores.keySet()){
            add(key, ores.get(key));
        }
        for (String key: deepslateOres.keySet()){
            add(key, deepslateOres.get(key));
        }

        for (String key: nuggets.keySet()){
            add(key, nuggets.get(key));
        }

        for (String key: blocks.keySet()){
            add(key, blocks.get(key));
        }

        for (String key: spikes.keySet()){
            add(key, spikes.get(key));
        }

        for (String key: icons.keySet()){
            add(key, icons.get(key));
        }

        for (String key: metals.keySet()){
            add(key, metals.get(key));
        }

        for (String key: powers.keySet()){
            add(key, powers.get(key));
        }

        for(String key: base.keySet()) {
            add(key, base.get(key));
        }

        for(String key: patterns.keySet()){
            add(key, patterns.get(key));
        }

        for (String keySymbol : symbols.keySet()) {
            for (String keyColor : colors.keySet()) {
                add("block.minecraft.banner.metallics_arts." + keySymbol + "." + keyColor, symbols.get(keySymbol) + " " + colors.get(keyColor));
            }
        }
    }
}
