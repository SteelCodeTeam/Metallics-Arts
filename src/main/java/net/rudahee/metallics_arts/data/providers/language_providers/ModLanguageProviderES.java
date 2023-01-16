package net.rudahee.metallics_arts.data.providers.language_providers;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.enums.implementations.languages.*;

import java.util.HashMap;
import java.util.Map;

public class ModLanguageProviderES extends LanguageProvider {

    private Map<String, String> base = new HashMap<>() {{
        put("item.metallics_arts.large_vial", CTW.VIAL.getNameInSpanish() + " " + CTW.LARGE.getNameInSpanish());
        put("item.metallics_arts.small_vial", CTW.VIAL.getNameInSpanish() + " " + CTW.SMALL.getNameInSpanish());
        put("curios.identifier.metalmind_slot", CTW.SLOT.getNameInSpanish() + " " + CTW.METALMIND);

        put("itemGroup.metallics_arts", CTW.METALLICS_ARTS.getNameInSpanish());
        put("itemGroup.metallics_arts.decorations", CTW.METALLICS_ARTS.getNameInSpanish() + " " + CTW.DECORATIONS.getNameInSpanish());

        put("key.category_powers_metallics_arts", CTW.METALLICS_ARTS.getNameInSpanish() + ": " + CTW.POWERS.getNameInSpanish());
        put("key.categorymetallics_arts", CTW.METALLICS_ARTS.getNameInSpanish());
        put("key.metallics_arts.allomantic", CTW.METAL_SELECTOR.getNameInSpanish() + " " + CTW.ALLOMANTIC.getNameInSpanish());
        put("key.metallics_arts.feruchemic", CTW.METAL_SELECTOR.getNameInSpanish() + " " + CTW.FERUCHEMICAL.getNameInSpanish());
        put("key.metallics_arts.allomantic_push", CTW.PUSH.getNameInSpanish()+ " " + CTW.ALLOMANTIC.getNameInSpanish());
        put("key.metallics_arts.allomantic_pull", CTW.PULL.getNameInSpanish()+ " " + CTW.ALLOMANTIC.getNameInSpanish());
        put("key.metallics_arts.vertical_jump", CTW.PUSH.getNameInSpanish()+ " " + CTW.VERTICAL);
        put("key.metallics_arts.feruchemic_decant", CTW.TAPPING.getNameInSpanish()+ " " + CTW.FERUCHEMICAL.getNameInSpanish());
        put("key.metallics_arts.feruchemic_store", CTW.STORAGE.getNameInSpanish()+ " " + CTW.FERUCHEMICAL.getNameInSpanish());
        put("key.metallics_arts.switch_overlay", CTW.SWITCH_OVERLAY.getNameInSpanish());

        put("metallics_arts.patchouli.name_book", CTW.METALLICS_ARTS.getNameInSpanish() + ": " + CTW.GUIDE.getNameInSpanish());
        put("metallics_arts.mental_mind.owner", CTW.OWNER.getNameInSpanish());
        put("metallics_arts.mental_mind.nobody", CTW.NOBODY.getNameInSpanish());
        put("metallics_arts.mental_mind_translate.has_reserve", CTW.HAS_RESERVE.getNameInSpanish());
        put("metallics_arts.mental_mind_translate.not_has_reserve", CTW.NOT_HAS_RESERVE.getNameInSpanish());
        put("metallics_arts.spike_feruchemic_power", CTW.STORAGE_POWER.getNameInSpanish() + ": " + CTW.FERUCHEMICAL.getNameInSpanish());
        put("metallics_arts.spike_allomantic_power", CTW.STORAGE_POWER.getNameInSpanish() + ": " + CTW.ALLOMANTIC.getNameInSpanish());
        put("metallics_arts.mental_mind_translate.store_identity", CTW.STORE_IDENTITY.getNameInSpanish());
        put("metallics_arts.mental_mind_translate.off_power", CTW.POWER_OFF.getNameInSpanish());
        put("metallics_arts.spike_allomantic_power.tapping_identity", CTW.TAPPING.getNameInSpanish());
        put("metallics_arts.mental_mind.owner_someone", CTW.OWNER_SOMEONE.getNameInSpanish());
        put("metallics_arts.mental_mind_translate.uses", CTW.USES.getNameInSpanish());

        put("metallics_arts.patchouli.landing_text", "Soy, por desgracia, el Héroe de las Eras, y tengo una pregunta que hacerte, ¿acaso un hombre no tiene derecho a poseer sus propios metales? No, dice el hombre del Imperio. Pertenece al \"dios\". No, dice el superviviente. Pertenece a todos. No, dice el hombre de Elendel. Pertenece a los pobres. Yo rechacé esas propuestas. En vez de eso elegí algo distinto. Elegi mi propio camino, elegí... Artes Metalicas Mod.");
        put("item.metallics_arts.obsidian_dagger", "Daga de obsidiana");
        put("item.metallics_arts.cristal_dagger", "Daga de cristal");
        put("item.metallics_arts.koloss_blade", "Espada de koloss");
        put("item.metallics_arts.dueling_staff", "Bastón de duelo");
        put("item.metallics_arts.obsidian_axe", "Hacha de obsidiana");

        /*put("metallics_arts.name", "Metallics Arts");

        put("metallics_arts.utils.separator", "/");
        put("metallics_arts.utils.percentage", "%");
        put("metallics_arts.utils.and", "y");*/
        //put("block.metallics_arts.alloy_furnace", "Horno de fusión");

        //put("screen.metallics_arts.alloy_furnace", "Horno de fusión");
        //put("curios.identifier.ma_metalmind_slot", "Ranura de mente de metal");

    }};


    private Map<String, String> ingots = new HashMap<>() {{
        for (MetalNamesEnum metal: MetalNamesEnum.values()) {
            if (!MetalAuxiliaryInfo.valueOf(metal.getId()).isVanilla()){
                put("item.metallics_arts.ingot_" + metal.getId(), CTW.INGOT.getNameInSpanish() + metal.getNameInSpanish());
            }
        }
    }};
    private Map<String, String> rawItems = new HashMap<>() {{
        for (MetalNamesEnum metal: MetalNamesEnum.values()) {
            if (!MetalAuxiliaryInfo.valueOf(metal.getId()).isVanilla()){
                put("item.metallics_arts.raw_" + metal.getId(), metal.getNameInSpanish() + " " + CTW.RAW.getNameInSpanish());
            }
        }
    }};
    private Map<String, String> gems = new HashMap<>() {{
        for (GemNames gem: GemNames.values()) {
            put("item.metallics_arts." + gem.getId(), CTW.GEM.getNameInSpanish() + " " + gem.getNameInSpanish());
        }
    }};
    private Map<String, String> nuggets = new HashMap<>() {{
        for (MetalNamesEnum metal: MetalNamesEnum.values()) {
            if (!MetalAuxiliaryInfo.valueOf(metal.getId()).isVanilla()){
                put("item.metallics_arts."+ metal.getId()+"_nugget", CTW.NUGGET.getNameInSpanish() + " " + metal.getNameInSpanish());
            }
        }
        for (GemNames gem: GemNames.values()) {
            put("item.metallics_arts." + gem.getId() + "_nugget", CTW.NUGGET.getNameInSpanish() + " " + gem.getNameInSpanish());
        }
    }};
    private Map<String, String> blocks = new HashMap<>() {{
        for (MetalNamesEnum metal: MetalNamesEnum.values()) {
            if (!MetalAuxiliaryInfo.valueOf(metal.getId()).isVanilla()){
                put("blocks.metallics_arts.block_" + metal.getId(), CTW.BLOCK.getNameInSpanish()+ " " + metal.getNameInSpanish());
            }
        }
        for (GemNames gem: GemNames.values()) {
            put("blocks.metallics_arts." + gem.getId() + "_block", CTW.BLOCK.getNameInSpanish()+ " " + gem.getNameInSpanish());
        }
    }};
    private Map<String, String> rawBlocks = new HashMap<>() {{
        for (MetalNamesEnum metal: MetalNamesEnum.values()) {
            if (!MetalAuxiliaryInfo.valueOf(metal.getId()).isVanilla()){
                put("block.metallics_arts.raw_"+metal.getId()+"_block", CTW.BLOCK.getNameInSpanish()+ " " + metal.getNameInSpanish() + " " +
                        CTW.RAW.getNameInSpanish());
            }
        }
    }};
    private Map<String, String> ores = new HashMap<>() {{
        for (MetalNamesEnum metal: MetalNamesEnum.values()) {
            if (!MetalAuxiliaryInfo.valueOf(metal.getId()).isVanilla()){
                if (!MetalAuxiliaryInfo.valueOf(metal.getId()).isAlloy()) {
                    if (!MetalAuxiliaryInfo.valueOf(metal.getId()).isAppearsInStone()) {
                        put("blocks.metallics_arts." + metal.getId()+"_ore", CTW.ORE.getNameInSpanish() + " " + metal.getNameInSpanish());
                    }
                }
            }
        }

    }};
    private Map<String, String> deepslateOres = new HashMap<>() {{
        for (MetalNamesEnum metal: MetalNamesEnum.values()) {
            if (!MetalAuxiliaryInfo.valueOf(metal.getId()).isVanilla()){
                if (!MetalAuxiliaryInfo.valueOf(metal.getId()).isAlloy()) {
                    if (!MetalAuxiliaryInfo.valueOf(metal.getId()).isAppearsInDeepslate()) {
                        put("blocks.metallics_arts.deepslate_" + metal.getId()+"_ore", CTW.ORE + " " + metal.getNameInSpanish() + " " +
                                CTW.DEEPSLATE.getNameInSpanish());
                    }
                }
            }
        }
    }};
    private Map<String, String> geodeBlocks = new HashMap<>() {{
        for (GemNames gem: GemNames.values()) {
            if (!GemsAuxiliaryInfo.valueOf(gem.getId()).isAlloy()) {
                put("block.metallics_arts." + gem.getId() + "_cristal_block", gem.getNameInSpanish() + " " + CTW.CRISTAL);
                put("block.metallics_arts.budding_" + gem.getId(), CTW.BUDDING.getNameInSpanish() + " " + gem.getNameInSpanish());
                put("block.metallics_arts." + gem.getId() + "_cluster", CTW.CLUSTER.getNameInSpanish() + " " + gem.getNameInSpanish());
                put("block.metallics_arts.small_" + gem.getId() + "_bud", CTW.BUD.getNameInSpanish() + " " + gem.getNameInSpanish() + " " +
                        CTW.SMALL.getNameInSpanish());
                put("block.metallics_arts.medium_" + gem.getId() + "_bud", CTW.BUD.getNameInSpanish() + " " + gem.getNameInSpanish() + " " +
                        CTW.MEDIUM.getNameInSpanish());
                put("block.metallics_arts.large_" + gem.getId() + "_bud", CTW.BUD.getNameInSpanish() + " " + gem.getNameInSpanish() + " " +
                        CTW.LARGE.getNameInSpanish());
            }
        }
    }};
    private Map<String, String> spikes = new HashMap<>() {{
        for (MetalTagEnum metal: MetalTagEnum.values()) {
            put("item.metallics_arts."+ metal.getNameLower() +"_spike", CTW.SPIKE.getNameInSpanish() + " " + MetalNamesEnum.valueOf(metal.name()).getNameInSpanish());
        }
    }};
    private Map<String, String> metalMinds = new HashMap<>() {{
        for (MetalMinds metalMind : MetalMinds.values()) {
            put("item.metallics_arts.band_" + metalMind.getId(), CTW.BAND.getNameInSpanish() + " " + metalMind.getNameInSpanish());
            put("item.metallics_arts.ring_" + metalMind.getId(), CTW.RING.getNameInSpanish() + " " + metalMind.getNameInSpanish());
        }
    }};
    private Map<String, String> icons = new HashMap<>() {{
        for (MetalTagEnum metal: MetalTagEnum.values()) {
            put("item.metallics_arts."+ metal.getNameLower()+"_allomantic_icon" , MetalNamesEnum.valueOf(metal.name()).getNameInSpanish()+" Alomantico");
            put("item.metallics_arts."+ metal.getNameLower()+"_feruchemic_icon" , MetalNamesEnum.valueOf(metal.name()).getNameInSpanish()+" Feruquimico");
        }

    }};
    private Map<String, String> metals = new HashMap<>() {{

        for (MetalTagEnum metal: MetalTagEnum.values()) {
            put("metallics_arts.metal_translate."+ metal.getNameLower() , MetalNamesEnum.valueOf(metal.name()).getNameInSpanish());
        }

    }};
    private Map<String, String> powers = new HashMap<>() {{
        for (MetalTagEnum metal: MetalTagEnum.values()) {
            put("key.metallics_arts."+ metal.getNameLower() +"_power", MetalNamesEnum.valueOf(metal.name()).getNameInSpanish());
        }
    }};
    private Map<String, String> symbols = new HashMap<>() {{
        for (MetalTagEnum metal: MetalTagEnum.values()) {
            put("f_"+metal.getNameLower()+"_1", MetalNamesEnum.valueOf(metal.name()).getNameInSpanish() + " " + CTW.FERUCHEMICAL_SHADING.getNameInSpanish());
            put("f_"+metal.getNameLower()+"_2", MetalNamesEnum.valueOf(metal.name()).getNameInSpanish() + " " + CTW.FERUCHEMICAL_SOLID.getNameInSpanish());
            put("a_"+metal.getNameLower()+"_1", MetalNamesEnum.valueOf(metal.name()).getNameInSpanish() + " " + CTW.ALLOMANTIC_SHADING.getNameInSpanish());
            put("a_"+metal.getNameLower()+"_2", MetalNamesEnum.valueOf(metal.name()).getNameInSpanish() + " " + CTW.ALLOMANTIC_SOLID.getNameInSpanish());
        }
    }};
    private Map<String, String> patterns = new HashMap<>() {{
        for (MetalTagEnum metal: MetalTagEnum.values()) {
            put("item.metallics_arts.f_"+metal.getNameLower()+"_pattern", CTW.FERUCHEMICAL_PATTERN.getNameInSpanish() + " " + MetalNamesEnum.valueOf(metal.name()).getNameInSpanish());
            put("item.metallics_arts.f_"+metal.getNameLower()+"_pattern.desc", CTW.FERUCHEMICAL_PATTERN.getNameInSpanish() + " " + MetalNamesEnum.valueOf(metal.name()).getNameInSpanish());
            put("item.metallics_arts.a_"+metal.getNameLower()+"_pattern", CTW.ALLOMANTIC_PATTERN.getNameInSpanish() + " " + MetalNamesEnum.valueOf(metal.name()).getNameInSpanish());
            put("item.metallics_arts.a_"+metal.getNameLower()+"_pattern.desc", CTW.ALLOMANTIC_PATTERN.getNameInSpanish() + " " + MetalNamesEnum.valueOf(metal.name()).getNameInSpanish());
        }
    }};
    private Map<String, String> colors = new HashMap<>() {{
        for (ColorsNames color : ColorsNames.values()) {
            put(color.getId(),color.getNameInSpanish());
        }
    }};

    public ModLanguageProviderES(DataGenerator gen, String modid, String locale) {
        super(gen, modid, locale);

    }

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
