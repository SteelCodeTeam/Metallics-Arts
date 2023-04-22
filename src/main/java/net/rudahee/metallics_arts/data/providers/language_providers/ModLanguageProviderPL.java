package net.rudahee.metallics_arts.data.providers.language_providers;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;
import net.rudahee.metallics_arts.data.enums.implementations.languages.*;
import net.rudahee.metallics_arts.data.providers.language_providers.book.BookHelperPL;

import java.util.HashMap;
import java.util.Map;

/**
 * A custom class for providing Polish translations for mod elements.
 *
 * @author SteelCode Team
 * @since 1.5.1
 */
public class ModLanguageProviderPL extends LanguageProvider {

    private final Map<String, String> base = new HashMap<>() {{
        put("item.metallics_arts.large_vial", CTW.VIAL.getNameInPolish() + " " + CTW.LARGE.getNameInPolish());
        put("item.metallics_arts.small_vial", CTW.VIAL.getNameInPolish() + " " + CTW.SMALL.getNameInPolish());
        put("curios.identifier.metalmind_slot", CTW.SLOT.getNameInPolish() + " " + CTW.METALMIND);

        put("itemGroup.metallics_arts", CTW.METALLICS_ARTS.getNameInPolish());
        put("itemGroup.metallics_arts.decorations", CTW.METALLICS_ARTS.getNameInPolish() + " " + CTW.DECORATIONS.getNameInPolish());

        put("key.category_powers_metallics_arts", CTW.METALLICS_ARTS.getNameInPolish() + ": " + CTW.POWERS.getNameInPolish());
        put("key.category.metallics_arts", CTW.METALLICS_ARTS.getNameInPolish());
        put("key.metallics_arts.allomantic", CTW.POWER_SELECTOR.getNameInPolish() + " " + CTW.ALLOMANTIC.getNameInPolish());
        put("key.metallics_arts.feruchemic", CTW.POWER_SELECTOR.getNameInPolish() + " " + CTW.FERUCHEMICAL.getNameInPolish());
        put("key.metallics_arts.allomantic_push", CTW.PUSH.getNameInPolish()+ " " + CTW.ALLOMANTIC.getNameInPolish());
        put("key.metallics_arts.allomantic_pull", CTW.PULL.getNameInPolish()+ " " + CTW.ALLOMANTIC.getNameInPolish());
        put("key.metallics_arts.vertical_jump", CTW.PUSH.getNameInPolish()+ " " + CTW.VERTICAL.getNameInPolish());
        put("key.metallics_arts.feruchemic_decant", CTW.TAPPING.getNameInPolish()+ " " + CTW.FERUCHEMICAL.getNameInPolish());
        put("key.metallics_arts.feruchemic_store", CTW.STORAGE.getNameInPolish()+ " " + CTW.FERUCHEMICAL.getNameInPolish());
        put("key.metallics_arts.switch_overlay", CTW.SWITCH_OVERLAY.getNameInPolish());

        put("metallics_arts.mental_mind_translate.has_reserve", CTW.HAS_RESERVE.getNameInPolish());
        put("metallics_arts.mental_mind_translate.not_has_reserve", CTW.NOT_HAS_RESERVE.getNameInPolish());
        put("metallics_arts.spike_feruchemic_power", CTW.STORAGE_POWER.getNameInPolish() + ": " + CTW.FERUCHEMICAL.getNameInPolish());
        put("metallics_arts.spike_allomantic_power", CTW.STORAGE_POWER.getNameInPolish() + ": " + CTW.ALLOMANTIC.getNameInPolish());

        //ver porque usa "spike"
        put("metallics_arts.spike_allomantic_power.tapping_identity", CTW.TAPPING.getNameInPolish());

        //arreglar estos de aca abajo
        put("metallics_arts.mental_mind_translate.store_identity", CTW.STORE_IDENTITY.getNameInPolish());
        put("metallics_arts.mental_mind_translate.off_power", CTW.POWER_OFF.getNameInPolish());
        put("metallics_arts.mental_mind.owner", CTW.OWNER.getNameInPolish());
        put("metallics_arts.mental_mind.nobody", CTW.NOBODY.getNameInPolish());
        put("metallics_arts.mental_mind.owner_someone", CTW.OWNER_SOMEONE.getNameInPolish());
        put("metallics_arts.mental_mind_translate.uses", CTW.USES.getNameInPolish());
        put("metallics_arts.mental_mind_translate.shift_info",CTW.SHIFT_TO_MORE_INFO.getNameInPolish());

        put("item.metallics_arts.obsidian_dagger", CTW.OBSIDIAN_DAGGER.getNameInPolish());
        put("item.metallics_arts.cristal_dagger", CTW.SILVER_KNIFE.getNameInPolish());
        put("item.metallics_arts.koloss_blade", CTW.KOLOSS_BLADE.getNameInPolish());
        put("item.metallics_arts.dueling_staff", CTW.DUELING_STAFF.getNameInPolish());
        put("item.metallics_arts.obsidian_axe", CTW.OBSIDIAN_AXE.getNameInPolish());


    }};


    private final Map<String, String> ingots = new HashMap<>() {{
        for (MetalAuxiliaryInfo metal: MetalAuxiliaryInfo.values()) {

            if (!metal.isVanilla()) {
                put("item.metallics_arts." + metal.getId() + "_ingot", CTW.INGOT.getNameInPolish() + " " + MetalNamesEnum.valueOf(metal.name()).getNameInPolish());
            }

        }
    }};
    private final Map<String, String> rawItems = new HashMap<>() {{
        for (MetalAuxiliaryInfo metal: MetalAuxiliaryInfo.values()) {
            if (!metal.isVanilla()) {
                put("item.metallics_arts.raw_" + metal.getId(), MetalNamesEnum.valueOf(metal.name()).getNameInPolish() + " " + CTW.RAW.getNameInPolish());
            }
        }
    }};
    private final Map<String, String> gems = new HashMap<>() {{
        for (MetalAuxiliaryInfo metal: MetalAuxiliaryInfo.values()) {
            if (metal.isDivine()) {
                put("item.metallics_arts." + metal.getId(), CTW.GEM.getNameInPolish() + " " + MetalNamesEnum.valueOf(metal.name()).getNameInPolish());
            }
        }
    }};
    private final Map<String, String> nuggets = new HashMap<>() {{
        for (MetalAuxiliaryInfo metal: MetalAuxiliaryInfo.values()) {
            if (!metal.isVanilla()) {
                put("item.metallics_arts."+ metal.getId()+"_nugget", CTW.NUGGET.getNameInPolish() + " " + MetalNamesEnum.valueOf(metal.name()).getNameInPolish());
            }
            if (metal.isDivine()) {
                put("item.metallics_arts." + metal.getId() + "_nugget", CTW.NUGGET.getNameInPolish() + " " + MetalNamesEnum.valueOf(metal.name()).getNameInPolish());
            }
        }
        put("item.metallics_arts.copper_nugget", CTW.NUGGET.getNameInPolish() + " " + MetalNamesEnum.COPPER.getNameInPolish());
    }};
    private final Map<String, String> blocks = new HashMap<>() {{
        for (MetalAuxiliaryInfo metal: MetalAuxiliaryInfo.values()) {
            if (!metal.isVanilla()) {
                put("block.metallics_arts." + metal.getId() + "_block",
                        CTW.BLOCK.getNameInPolish()+ " " + MetalNamesEnum.valueOf(metal.name()).getNameInPolish());
            }
            if (metal.isDivine()) {
                put("block.metallics_arts." + metal.getId() + "_block", CTW.BLOCK.getNameInPolish()+ " " + MetalNamesEnum.valueOf(metal.name()).getNameInPolish());
            }
        }
    }};
    private final Map<String, String> rawBlocks = new HashMap<>() {{
        for (MetalAuxiliaryInfo metal: MetalAuxiliaryInfo.values()) {
            if (!metal.isVanilla()) {
                put("block.metallics_arts.raw_"+metal.getId()+"_block", CTW.BLOCK.getNameInPolish()+ " " +
                        MetalNamesEnum.valueOf(metal.name()).getNameInPolish() + " " + CTW.RAW.getNameInPolish());
            }
        }
    }};
    private final Map<String, String> ores = new HashMap<>() {{
        for (MetalAuxiliaryInfo metal: MetalAuxiliaryInfo.values()) {
            if (!metal.isVanilla() && !metal.isAlloy() && metal.isAppearsInStone()) {
                put("block.metallics_arts." + metal.getId()+"_ore", CTW.ORE.getNameInPolish() + " " + MetalNamesEnum.valueOf(metal.name()).getNameInPolish());

            }
        }

    }};
    private final Map<String, String> deepslateOres = new HashMap<>() {{
        for (MetalAuxiliaryInfo metal: MetalAuxiliaryInfo.values()) {
            if (!metal.isVanilla() && !metal.isAlloy() && metal.isAppearsInDeepslate()) {
                put("block.metallics_arts.deepslate_" + metal.getId()+"_ore", CTW.ORE.getNameInPolish() + " " +
                        MetalNamesEnum.valueOf(metal.name()).getNameInPolish() + " " + CTW.DEEPSLATE.getNameInPolish());
            }
        }
    }};
    private final Map<String, String> geodeBlocks = new HashMap<>() {{
        for (MetalAuxiliaryInfo metal: MetalAuxiliaryInfo.values()) {
            if (metal.isDivine() && !metal.isAlloy()) {
                put("block.metallics_arts." + metal.getId() + "_cristal_block", MetalNamesEnum.valueOf(metal.name()).getNameInPolish() + " " + CTW.CRISTAL.getNameInPolish());
                put("block.metallics_arts.budding_" + metal.getId(), CTW.BUDDING.getNameInPolish() + " " + MetalNamesEnum.valueOf(metal.name()).getNameInPolish());
                put("block.metallics_arts." + metal.getId() + "_cluster", CTW.CLUSTER.getNameInPolish() + " " + MetalNamesEnum.valueOf(metal.name()).getNameInPolish());
                put("block.metallics_arts.small_" + metal.getId() + "_bud", CTW.BUD.getNameInPolish() + " " + MetalNamesEnum.valueOf(metal.name()).getNameInPolish() + " " +
                        CTW.SMALL.getNameInPolish());
                put("block.metallics_arts.medium_" + metal.getId() + "_bud", CTW.BUD.getNameInPolish() + " " + MetalNamesEnum.valueOf(metal.name()).getNameInPolish() + " " +
                        CTW.MEDIUM.getNameInPolish());
                put("block.metallics_arts.large_" + metal.getId() + "_bud", CTW.BUD.getNameInPolish() + " " + MetalNamesEnum.valueOf(metal.name()).getNameInPolish() + " " +
                        CTW.LARGE.getNameInPolish());
            }
        }
    }};
    private final Map<String, String> spikes = new HashMap<>() {{
        for (MetalAuxiliaryInfo metal: MetalAuxiliaryInfo.values()) {
            if (!metal.isOnlyForAlloys()) {
                put("item.metallics_arts."+ metal.getId() +"_spike", CTW.SPIKE.getNameInPolish() + " " + MetalNamesEnum.valueOf(metal.name()).getNameInPolish());
            }
        }
    }};
    private final Map<String, String> metalMinds = new HashMap<>() {{
        for (MetalMinds metalMind : MetalMinds.values()) {
            put("item.metallics_arts.band_" + metalMind.getId(), CTW.BAND.getNameInPolish() + " " + metalMind.getNameInPolish());
            put("item.metallics_arts.ring_" + metalMind.getId(), CTW.RING.getNameInPolish() + " " + metalMind.getNameInPolish());
        }
    }};
    private final Map<String, String> icons = new HashMap<>() {{
        for (MetalAuxiliaryInfo metal: MetalAuxiliaryInfo.values()) {
            if (!metal.isOnlyForAlloys()) {
                put("item.metallics_arts."+ metal.getId()+"_allomantic_icon" , MetalNamesEnum.valueOf(metal.name()).getNameInPolish() + " " + CTW.ALLOMANTIC.getNameInPolish());
                put("item.metallics_arts."+ metal.getId()+"_feruchemic_icon" , MetalNamesEnum.valueOf(metal.name()).getNameInPolish() + " " + CTW.FERUCHEMICAL.getNameInPolish());
            }
        }
    }};
    private final Map<String, String> metals = new HashMap<>() {{
        for (MetalAuxiliaryInfo metal: MetalAuxiliaryInfo.values()) {
            if (!metal.isOnlyForAlloys()) {
                put("metallics_arts.metal_translate."+ metal.getId() , MetalNamesEnum.valueOf(metal.name()).getNameInPolish());
            }
        }
    }};
    private final Map<String, String> powers = new HashMap<>() {{
        for (MetalAuxiliaryInfo metal: MetalAuxiliaryInfo.values()) {
            if (!metal.isOnlyForAlloys()) {
                put("key.metallics_arts."+ metal.getId() +"_power", MetalNamesEnum.valueOf(metal.name()).getNameInPolish());
            }
        }
    }};
    private final Map<String, String> symbols = new HashMap<>() {{
        for (MetalAuxiliaryInfo metal: MetalAuxiliaryInfo.values()) {
            if (!metal.isOnlyForAlloys()) {
                put("f_"+metal.getId()+"_1", MetalNamesEnum.valueOf(metal.name()).getNameInPolish() + " " + CTW.FERUCHEMICAL_SHADING.getNameInPolish());
                put("f_"+metal.getId()+"_2", MetalNamesEnum.valueOf(metal.name()).getNameInPolish() + " " + CTW.FERUCHEMICAL_SOLID.getNameInPolish());
                put("a_"+metal.getId()+"_1", MetalNamesEnum.valueOf(metal.name()).getNameInPolish() + " " + CTW.ALLOMANTIC_SHADING.getNameInPolish());
                put("a_"+metal.getId()+"_2", MetalNamesEnum.valueOf(metal.name()).getNameInPolish() + " " + CTW.ALLOMANTIC_SOLID.getNameInPolish());
            }
        }
    }};
    private final Map<String, String> patterns = new HashMap<>() {{
        for (MetalAuxiliaryInfo metal: MetalAuxiliaryInfo.values()) {
            if (!metal.isOnlyForAlloys()) {
                put("item.metallics_arts.f_"+metal.getId()+"_pattern", CTW.FERUCHEMICAL_PATTERN.getNameInPolish() + " " + MetalNamesEnum.valueOf(metal.name()).getNameInPolish());
                put("item.metallics_arts.f_"+metal.getId()+"_pattern.desc", CTW.FERUCHEMICAL_PATTERN.getNameInPolish() + " " + MetalNamesEnum.valueOf(metal.name()).getNameInPolish());
                put("item.metallics_arts.a_"+metal.getId()+"_pattern", CTW.ALLOMANTIC_PATTERN.getNameInPolish() + " " + MetalNamesEnum.valueOf(metal.name()).getNameInPolish());
                put("item.metallics_arts.a_"+metal.getId()+"_pattern.desc", CTW.ALLOMANTIC_PATTERN.getNameInPolish() + " " + MetalNamesEnum.valueOf(metal.name()).getNameInPolish());
            }
        }

    }};
    private final Map<String, String> colors = new HashMap<>() {{
        for (ColorsNames color : ColorsNames.values()) {
            put(color.getId(),color.getNameInPolish());
        }
    }};

    /**
     * Constructs a new ModLanguageProviderPL instance.
     *
     * @param gen               the data generator for language files
     * @param modid             the mod ID for the target mod
     * @param locale            the locale for the target language
     */
    public ModLanguageProviderPL(DataGenerator gen, String modid, String locale) {
        super(gen, modid, locale);
    }

    /**
     * Adds the Polish translations for mod elements.
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
        HashMap<String, String> bookTraslationsHelper = BookHelperPL.addDemoBook();

        for (String key : bookTraslationsHelper.keySet()) {
            add(key,bookTraslationsHelper.get(key));
        }
    }
}
