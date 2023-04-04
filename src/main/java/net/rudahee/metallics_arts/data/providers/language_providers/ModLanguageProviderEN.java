package net.rudahee.metallics_arts.data.providers.language_providers;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.data.enums.implementations.languages.*;
import net.rudahee.metallics_arts.data.providers.language_providers.book.BookHelperEN;

import java.util.HashMap;
import java.util.Map;

/**
 * A custom class for providing English translations for mod elements.
 *
 * @author SteelCode Team
 * @since 1.5.1
 */
public class ModLanguageProviderEN extends LanguageProvider {
        private final Map<String, String> base = new HashMap<>() {{

                put("item.metallics_arts.large_vial",CTW.LARGE.getNameInEnglish() + " " + CTW.VIAL.getNameInEnglish());
                put("item.metallics_arts.small_vial",CTW.SMALL.getNameInEnglish() + " " + CTW.VIAL.getNameInEnglish());
                put("curios.identifier.metalmind_slot",CTW.METALMIND.getNameInEnglish() + " " + CTW.SLOT.getNameInEnglish());

                put("itemGroup.metallics_arts", CTW.METALLICS_ARTS.getNameInEnglish());
                put("itemGroup.metallics_arts.decorations", CTW.DECORATIONS.getNameInEnglish() + " " + CTW.METALLICS_ARTS.getNameInEnglish());

                put("key.category_powers_metallics_arts", CTW.POWERS.getNameInEnglish() + " " + CTW.METALLICS_ARTS.getNameInEnglish());
                put("key.categorymetallics_arts", CTW.METALLICS_ARTS.getNameInEnglish());
                put("key.metallics_arts.allomantic", CTW.ALLOMANTIC.getNameInEnglish() + " " + CTW.POWER_SELECTOR.getNameInEnglish() );
                put("key.metallics_arts.feruchemic", CTW.FERUCHEMICAL.getNameInEnglish() + " " + CTW.POWER_SELECTOR.getNameInEnglish());
                put("key.metallics_arts.allomantic_push",CTW.ALLOMANTIC.getNameInEnglish() + " " + CTW.PUSH.getNameInEnglish());
                put("key.metallics_arts.allomantic_pull", CTW.ALLOMANTIC.getNameInEnglish() + " " +CTW.PULL.getNameInEnglish());
                put("key.metallics_arts.vertical_jump", CTW.VERTICAL.getNameInEnglish() + " " +CTW.PUSH.getNameInEnglish());
                put("key.metallics_arts.feruchemic_decant", CTW.FERUCHEMICAL.getNameInEnglish() + " " + CTW.TAPPING.getNameInEnglish());
                put("key.metallics_arts.feruchemic_store",CTW.FERUCHEMICAL.getNameInEnglish() + " " +CTW.STORAGE.getNameInEnglish());
                put("key.metallics_arts.switch_overlay", CTW.SWITCH_OVERLAY.getNameInEnglish());

                put("metallics_arts.mental_mind_translate.has_reserve", CTW.HAS_RESERVE.getNameInEnglish());
                put("metallics_arts.mental_mind_translate.not_has_reserve", CTW.NOT_HAS_RESERVE.getNameInEnglish());
                put("metallics_arts.spike_feruchemic_power", CTW.STORAGE_POWER.getNameInEnglish() + ": " + CTW.FERUCHEMICAL.getNameInEnglish());
                put("metallics_arts.spike_allomantic_power", CTW.STORAGE_POWER.getNameInEnglish() + ": " + CTW.ALLOMANTIC.getNameInEnglish());

                //ver porque usa "spike"
                put("metallics_arts.spike_allomantic_power.tapping_identity", CTW.TAPPING.getNameInEnglish());

                //arreglar estos de aca abajo
                put("metallics_arts.mental_mind_translate.store_identity", CTW.STORE_IDENTITY.getNameInEnglish());
                put("metallics_arts.mental_mind_translate.off_power", CTW.POWER_OFF.getNameInEnglish());
                put("metallics_arts.mental_mind.owner", CTW.OWNER.getNameInEnglish());
                put("metallics_arts.mental_mind.nobody", CTW.NOBODY.getNameInEnglish());
                put("metallics_arts.mental_mind.owner_someone", CTW.OWNER_SOMEONE.getNameInEnglish());
                put("metallics_arts.mental_mind_translate.uses", CTW.USES.getNameInEnglish());

                put("item.metallics_arts.obsidian_dagger", CTW.OBSIDIAN_DAGGER.getNameInEnglish());
                put("item.metallics_arts.cristal_dagger", CTW.CRYSTAL_DAGGER.getNameInEnglish());
                put("item.metallics_arts.koloss_blade", CTW.KOLOSS_BLADE.getNameInEnglish());
                put("item.metallics_arts.dueling_staff", CTW.DUELING_STAFF.getNameInEnglish());
                put("item.metallics_arts.obsidian_axe", CTW.OBSIDIAN_AXE.getNameInEnglish());
        }};


        private final Map<String, String> ingots = new HashMap<>() {{
                for (MetalAuxiliaryInfo metal: MetalAuxiliaryInfo.values()) {
                        if (!metal.isVanilla()) {
                                put("item.metallics_arts." + metal.getId() + "_ingot", MetalNamesEnum.valueOf(metal.name()).getNameInEnglish() + " " + CTW.INGOT.getNameInEnglish());
                        }

                }
        }};

        private final Map<String, String> effects = new HashMap<>() {{
                for (MetalAuxiliaryInfo metal: MetalAuxiliaryInfo.values()) {
                        if (!metal.isOnlyForAlloys()) {
                                put("effect."+ MetallicsArts.MOD_ID + ".allomantic_" + metal.getId() + "_symbol", CTW.ALLOMANTIC.getNameInEnglish() + " " + MetalNamesEnum.valueOf(metal.name()).getNameInEnglish());
                                put("effect."+ MetallicsArts.MOD_ID + ".feruchemical_" + metal.getId() + "_tap", CTW.FERUCHEMICAL.getNameInEnglish() + " " + MetalNamesEnum.valueOf(metal.name()).getNameInEnglish());
                                put("effect."+ MetallicsArts.MOD_ID + ".feruchemical_" + metal.getId() + "_storage", CTW.FERUCHEMICAL.getNameInEnglish() + " " + MetalNamesEnum.valueOf(metal.name()).getNameInEnglish());
                        }
                }

        }};
        private final Map<String, String> rawItems = new HashMap<>() {{
                for (MetalAuxiliaryInfo metal: MetalAuxiliaryInfo.values()) {
                        if (!metal.isVanilla()) {
                                put("item.metallics_arts.raw_" + metal.getId(), CTW.RAW.getNameInEnglish() + " " + MetalNamesEnum.valueOf(metal.name()).getNameInEnglish());
                        }
                }
        }};
        private final Map<String, String> gems = new HashMap<>() {{
                for (MetalAuxiliaryInfo metal: MetalAuxiliaryInfo.values()) {
                        if (metal.isDivine()) {
                                put("item.metallics_arts." + metal.getId(), MetalNamesEnum.valueOf(metal.name()).getNameInEnglish() + " " + CTW.GEM.getNameInEnglish());
                        }
                }
        }};
        private final Map<String, String> nuggets = new HashMap<>() {{
                for (MetalAuxiliaryInfo metal: MetalAuxiliaryInfo.values()) {
                        if (!metal.isVanilla()) {
                                put("item.metallics_arts."+ metal.getId()+"_nugget",MetalNamesEnum.valueOf(metal.name()).getNameInEnglish() + " " + CTW.NUGGET.getNameInEnglish() );
                        }
                        if (metal.isDivine()) {
                                put("item.metallics_arts." + metal.getId() + "_nugget", MetalNamesEnum.valueOf(metal.name()).getNameInEnglish() + " " + CTW.NUGGET.getNameInEnglish());
                        }
                }
                put("item.metallics_arts.copper_nugget", MetalNamesEnum.COPPER.getNameInEnglish() + " " + CTW.NUGGET.getNameInEnglish());
        }};
        private final Map<String, String> blocks = new HashMap<>() {{
                for (MetalAuxiliaryInfo metal: MetalAuxiliaryInfo.values()) {
                        if (!metal.isVanilla()) {
                                put("block.metallics_arts." + metal.getId() + "_block", CTW.BLOCK.getNameInEnglish() + " " + MetalNamesEnum.valueOf(metal.name()).getNameInEnglish());
                        }
                        if (metal.isDivine()) {
                                put("block.metallics_arts." + metal.getId() + "_block", CTW.BLOCK.getNameInEnglish() + " " + MetalNamesEnum.valueOf(metal.name()).getNameInEnglish());
                        }
                }
        }};
        private final Map<String, String> rawBlocks = new HashMap<>() {{
                for (MetalAuxiliaryInfo metal: MetalAuxiliaryInfo.values()) {
                        if (!metal.isVanilla()) {
                                put("block.metallics_arts.raw_"+metal.getId()+"_block", CTW.BLOCK.getNameInEnglish()+ " " +
                                                CTW.RAW.getNameInEnglish() + " " + MetalNamesEnum.valueOf(metal.name()).getNameInEnglish() );
                        }
                }
        }};
        private final Map<String, String> ores = new HashMap<>() {{
                for (MetalAuxiliaryInfo metal: MetalAuxiliaryInfo.values()) {
                        if (!metal.isVanilla() && !metal.isAlloy() && metal.isAppearsInStone()) {
                                put("block.metallics_arts." + metal.getId()+"_ore", MetalNamesEnum.valueOf(metal.name()).getNameInEnglish() + " " + CTW.ORE.getNameInEnglish());
                        }
                }

        }};
        private final Map<String, String> deepslateOres = new HashMap<>() {{
                for (MetalAuxiliaryInfo metal: MetalAuxiliaryInfo.values()) {
                        if (!metal.isVanilla() && !metal.isAlloy() && metal.isAppearsInDeepslate()) {
                                put("block.metallics_arts.deepslate_" + metal.getId()+"_ore", CTW.ORE.getNameInEnglish() + " " +
                                        MetalNamesEnum.valueOf(metal.name()).getNameInEnglish() + " " + CTW.DEEPSLATE.getNameInEnglish());
                        }
                }
        }};
        private final Map<String, String> geodeBlocks = new HashMap<>() {{
                for (MetalAuxiliaryInfo metal: MetalAuxiliaryInfo.values()) {
                        if (metal.isDivine() && !metal.isAlloy()) {
                                put("block.metallics_arts." + metal.getId() + "_cristal_block", MetalNamesEnum.valueOf(metal.name()).getNameInEnglish() + " " + CTW.CRISTAL.getNameInEnglish());
                                put("block.metallics_arts.budding_" + metal.getId(), CTW.BUDDING.getNameInEnglish() + " " + MetalNamesEnum.valueOf(metal.name()).getNameInEnglish());
                                put("block.metallics_arts." + metal.getId() + "_cluster", CTW.CLUSTER.getNameInEnglish() + " " + MetalNamesEnum.valueOf(metal.name()).getNameInEnglish());
                                put("block.metallics_arts.small_" + metal.getId() + "_bud", CTW.BUD.getNameInEnglish() + " " + MetalNamesEnum.valueOf(metal.name()).getNameInEnglish() + " " +
                                        CTW.SMALL.getNameInEnglish());
                                put("block.metallics_arts.medium_" + metal.getId() + "_bud", CTW.BUD.getNameInEnglish() + " " + MetalNamesEnum.valueOf(metal.name()).getNameInEnglish() + " " +
                                        CTW.MEDIUM.getNameInEnglish());
                                put("block.metallics_arts.large_" + metal.getId() + "_bud", CTW.BUD.getNameInEnglish() + " " + MetalNamesEnum.valueOf(metal.name()).getNameInEnglish() + " " +
                                        CTW.LARGE.getNameInEnglish());
                        }
                }
        }};
        private final Map<String, String> spikes = new HashMap<>() {{
                for (MetalAuxiliaryInfo metal: MetalAuxiliaryInfo.values()) {
                        if (!metal.isOnlyForAlloys()) {
                                put("item.metallics_arts."+ metal.getId() +"_spike", MetalNamesEnum.valueOf(metal.name()).getNameInEnglish() + " " + CTW.SPIKE.getNameInEnglish());
                        }
                }
        }};
        private final Map<String, String> metalMinds = new HashMap<>() {{
                for (MetalMinds metalMind : MetalMinds.values()) {
                        put("item.metallics_arts.band_" + metalMind.getId(), metalMind.getNameInEnglish() + " " + CTW.BAND.getNameInEnglish());
                        put("item.metallics_arts.ring_" + metalMind.getId(), metalMind.getNameInEnglish() + " " + CTW.RING.getNameInEnglish());
                }
        }};
        private final Map<String, String> icons = new HashMap<>() {{
                for (MetalAuxiliaryInfo metal: MetalAuxiliaryInfo.values()) {
                        if (!metal.isOnlyForAlloys()) {
                                put("item.metallics_arts."+ metal.getId()+"_allomantic_icon" , CTW.ALLOMANTIC.getNameInEnglish() + " " + MetalNamesEnum.valueOf(metal.name()).getNameInEnglish());
                                put("item.metallics_arts."+ metal.getId()+"_feruchemic_icon" , CTW.FERUCHEMICAL.getNameInEnglish() + " " + MetalNamesEnum.valueOf(metal.name()).getNameInEnglish());
                        }
                }
        }};
        private final Map<String, String> metals = new HashMap<>() {{
                for (MetalAuxiliaryInfo metal: MetalAuxiliaryInfo.values()) {
                        if (!metal.isOnlyForAlloys()) {
                                put("metallics_arts.metal_translate."+ metal.getId() , MetalNamesEnum.valueOf(metal.name()).getNameInEnglish());
                        }
                }
        }};
        private final Map<String, String> powers = new HashMap<>() {{
                for (MetalAuxiliaryInfo metal: MetalAuxiliaryInfo.values()) {
                        if (!metal.isOnlyForAlloys()) {
                                put("key.metallics_arts."+ metal.getId() +"_power", MetalNamesEnum.valueOf(metal.name()).getNameInEnglish());
                        }
                }
        }};
        private final Map<String, String> symbols = new HashMap<>() {{
                for (MetalAuxiliaryInfo metal: MetalAuxiliaryInfo.values()) {
                        if (!metal.isOnlyForAlloys()) {
                                put("f_"+metal.getId()+"_1", MetalNamesEnum.valueOf(metal.name()).getNameInEnglish() + " " + CTW.FERUCHEMICAL_SHADING.getNameInEnglish());
                                put("f_"+metal.getId()+"_2", MetalNamesEnum.valueOf(metal.name()).getNameInEnglish() + " " + CTW.FERUCHEMICAL_SOLID.getNameInEnglish());
                                put("a_"+metal.getId()+"_1", MetalNamesEnum.valueOf(metal.name()).getNameInEnglish() + " " + CTW.ALLOMANTIC_SHADING.getNameInEnglish());
                                put("a_"+metal.getId()+"_2", MetalNamesEnum.valueOf(metal.name()).getNameInEnglish() + " " + CTW.ALLOMANTIC_SOLID.getNameInEnglish());
                        }
                }
        }};
        private final Map<String, String> patterns = new HashMap<>() {{
                for (MetalAuxiliaryInfo metal: MetalAuxiliaryInfo.values()) {
                        if (!metal.isOnlyForAlloys()) {
                                put("item.metallics_arts.f_"+metal.getId()+"_pattern", MetalNamesEnum.valueOf(metal.name()).getNameInEnglish() + " " + CTW.FERUCHEMICAL_PATTERN.getNameInEnglish());
                                put("item.metallics_arts.f_"+metal.getId()+"_pattern.desc", MetalNamesEnum.valueOf(metal.name()).getNameInEnglish() + " " + CTW.FERUCHEMICAL_PATTERN.getNameInEnglish());
                                put("item.metallics_arts.a_"+metal.getId()+"_pattern", MetalNamesEnum.valueOf(metal.name()).getNameInEnglish() + " " + CTW.ALLOMANTIC_PATTERN.getNameInEnglish());
                                put("item.metallics_arts.a_"+metal.getId()+"_pattern.desc", MetalNamesEnum.valueOf(metal.name()).getNameInEnglish() + " " + CTW.ALLOMANTIC_PATTERN.getNameInEnglish());
                        }
                }

        }};
        private final Map<String, String> colors = new HashMap<>() {{
                for (ColorsNames color : ColorsNames.values()) {
                        put(color.getId(),color.getNameInEnglish());
                }
        }};

    /**
     * Constructs a new ModLanguageProviderEN instance.
     *
     * @param gen               the data generator for language files
     * @param modid             the mod ID for the target mod
     * @param locale            the locale for the target language
     */
    public ModLanguageProviderEN(DataGenerator gen, String modid, String locale) {
        super(gen, modid, locale);
    }

    /**
     * Adds the English translations for mod elements.
     */
    @Override
    protected void addTranslations() {
            for (String key: ingots.keySet()){
                    add(key, ingots.get(key));
            }
            for (String key: effects.keySet()){
                    add(key, effects.get(key));
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

            HashMap<String, String> bookTraslationsHelper = BookHelperEN.addDemoBook();

            for (String key : bookTraslationsHelper.keySet()) {
                    add(key,bookTraslationsHelper.get(key));
            }
    }



}
