package net.rudahee.metallics_arts.data.providers.language_providers;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;

import java.util.HashMap;
import java.util.Map;

public class ModLanguageProviderEN extends LanguageProvider {


    private Map<String, String> base = new HashMap<>() {{

            put("item.metallics_arts.steel_ingot", "Steel Ingot");
            put("item.metallics_arts.tin_ingot", "Tin Ingot");
            put("item.metallics_arts.pewter_ingot", "Pewter Ingot");
            put("item.metallics_arts.bronze_ingot", "Bronze Ingot");
            put("item.metallics_arts.zinc_ingot", "Zinc Ingot");
            put("item.metallics_arts.brass_ingot", "Brass Ingot");
            put("item.metallics_arts.electrum_ingot", "Electrum Ingot");
            put("item.metallics_arts.cadmium_ingot", "Cadmium Ingot");
            put("item.metallics_arts.bendalloy_ingot", "Bendalloy Ingot");
            put("item.metallics_arts.aluminum_ingot", "Aluminum Ingot");
            put("item.metallics_arts.duralumin_ingot", "Duralumin Ingot");
            put("item.metallics_arts.chromium_ingot", "Chromium Ingot");
            put("item.metallics_arts.nicrosil_ingot", "Nicrosil Ingot");

            put( "item.metallics_arts.silver_ingot", "Silver Ingot");
            put("item.metallics_arts.lead_ingot", "Lead Ingot");
            put("item.metallics_arts.nickel_ingot", "Nickel Ingot");

            put( "item.metallics_arts.atium", "Atium");
            put("item.metallics_arts.malatium", "Malatium");
            put("item.metallics_arts.lerasium", "Lerasium");
            put("item.metallics_arts.ettmetal", "Ettmetal");


            put(  "item.metallics_arts.steel_nugget","Steel Nugget");
            put("item.metallics_arts.tin_nugget", "Tin Nugget");
            put("item.metallics_arts.pewter_nugget", "Pewter Nugget");
            put("item.metallics_arts.bronze_nugget", "Bronze Nugget");
            put("item.metallics_arts.copper_nugget", "Copper Nugget");
            put("item.metallics_arts.zinc_nugget", "Zinc Nugget");
            put("item.metallics_arts.brass_nugget", "Brass Nugget");
            put("item.metallics_arts.electrum_nugget", "Electrum Nugget");
            put("item.metallics_arts.cadmium_nugget", "Cadmium Nugget");
            put("item.metallics_arts.bendalloy_nugget", "Bendalloy Nugget");
            put("item.metallics_arts.aluminum_nugget", "Aluminum Nugget");
            put("item.metallics_arts.duralumin_nugget", "Duralumin Nugget");
            put("item.metallics_arts.chromium_nugget", "Chromium Nugget");
            put("item.metallics_arts.nicrosil_nugget", "Nicrosil Nugget");

            put( "item.metallics_arts.silver_nugget", "Silver Nugget");
            put("item.metallics_arts.lead_nugget", "Lead Nugget");
            put("item.metallics_arts.nickel_nugget", "Nickel Nugget");

            put( "item.metallics_arts.atium_nugget", "Atium Nugget");
            put("item.metallics_arts.malatium_nugget", "Malatium Nugget");
            put("item.metallics_arts.lerasium_nugget", "Lerasium Nugget");
            put("item.metallics_arts.ettmetal_nugget", "Ettmetal Nugget");


            put(
                    "item.metallics_arts.raw_tin", "Raw Tin");
            put("item.metallics_arts.raw_zinc", "Raw Zinc");
            put("item.metallics_arts.raw_cadmium", "Raw Cadmium");
            put("item.metallics_arts.raw_aluminum", "Raw Aluminum");

            put( "item.metallics_arts.raw_chromium", "Raw Chromium");
            put("item.metallics_arts.raw_silver", "Raw Silver");
            put("item.metallics_arts.raw_lead", "Raw Lead");
            put("item.metallics_arts.raw_nickel", "Raw Nickel");


            put(  "block.metallics_arts.tin_ore", "Tin Ore");
            put("block.metallics_arts.bronze_ore", "Bronze Ore");
            put("block.metallics_arts.zinc_ore", "Zinc Ore");
            put("block.metallics_arts.brass_ore", "Brass Ore");
            put("block.metallics_arts.cadmium_ore", "Cadmium Ore");
            put("block.metallics_arts.aluminum_ore", "Aluminum Ore");
            put("block.metallics_arts.chromium_ore", "Chromium Ore");
            put("block.metallics_arts.lead_ore", "Lead Ore");

            put( "block.metallics_arts.deepslate_chromium_ore", "Deepslate Chromium Ore");
            put("block.metallics_arts.deepslate_cadmium_ore", "Deepslate Cadmium Ore");
            put("block.metallics_arts.deepslate_aluminum_ore", "Deepslate Aluminum Ore");
            put("block.metallics_arts.deepslate_silver_ore", "Deepslate Silver Ore");
            put("block.metallics_arts.deepslate_lead_ore", "Deepslate Lead Ore");
            put("block.metallics_arts.deepslate_nickel_ore", "Deepslate Nickel Ore");

            put( "block.metallics_arts.raw_tin_block", "Raw Tin Block");
            put("block.metallics_arts.raw_zinc_block", "Raw Zinc Block");
            put("block.metallics_arts.raw_cadmium_block", "Raw Cadmium Block");
            put("block.metallics_arts.raw_aluminum_block", "Raw Aluminum Block");

            put( "block.metallics_arts.raw_chromium_block", "Raw Chromium Block");
            put("block.metallics_arts.raw_silver_block", "Raw Silver Block");
            put("block.metallics_arts.raw_lead_block", "Raw Lead Block");
            put("block.metallics_arts.raw_nickel_block", "Raw Nickel Block");


            put(  "block.metallics_arts.steel_block", "Block of Steel");
            put("block.metallics_arts.tin_block", "Block of Tin");
            put("block.metallics_arts.pewter_block", "Block of Pewter");
            put("block.metallics_arts.bronze_block", "Block of Bronze");
            put("block.metallics_arts.zinc_block", "Block of Zinc");
            put("block.metallics_arts.brass_block", "Block of Brass");
            put("block.metallics_arts.electrum_block", "Block of Electrum");
            put("block.metallics_arts.cadmium_block", "Block of Cadmium");
            put("block.metallics_arts.bendalloy_block", "Block of Bendalloy");
            put("block.metallics_arts.aluminum_block", "Block of Aluminum");
            put("block.metallics_arts.duralumin_block", "Block of Duralumin");
            put("block.metallics_arts.chromium_block", "Block of Chromium");
            put("block.metallics_arts.nicrosil_block", "Block of Nicrosil");

            put( "block.metallics_arts.silver_block", "Block of Silver");
            put("block.metallics_arts.lead_block", "Block of Lead");
            put("block.metallics_arts.nickel_block", "Block of Nickel");

            put( "block.metallics_arts.atium_block", "Block of Atium");
            put("block.metallics_arts.malatium_block", "Block of Malatium");
            put("block.metallics_arts.lerasium_block", "Block of Lerasium");
            put("block.metallics_arts.ettmetal_block", "Block of Ettmetal");

            put( "item.metallics_arts.band_aluminum_duralumin", "Band Aluminum-Duralumin");
            put("item.metallics_arts.band_atium_malatium", "Band Atium-Malatium");
            put("item.metallics_arts.band_cadmium_bendalloy", "Band Cadmium-Bendalloy");
            put("item.metallics_arts.band_chromium_nicrosil", "Band Chromium-Nicrosil");
            put("item.metallics_arts.band_copper_bronze", "Band Copper-Bronze");
            put("item.metallics_arts.band_electrum_gold", "Band Gold-Electrum");
            put("item.metallics_arts.band_lerasium_ettmetal", "Band Lerasium-Ettmetal");
            put("item.metallics_arts.band_pewter_tin", "Band Tin-Pewter");
            put("item.metallics_arts.band_zinc_brass", "Band Zinc-Brass");
            put("item.metallics_arts.band_steel_iron", "Band Iron-Steel");

            put( "item.metallics_arts.ring_aluminum_duralumin", "Ring Aluminum-Duralumin");
            put("item.metallics_arts.ring_atium_malatium", "Ring Atium-Malatium");
            put("item.metallics_arts.ring_cadmium_bendalloy", "Ring Cadmium-Bendalloy");
            put("item.metallics_arts.ring_chromium_nicrosil", "Ring Chromium-Nicrosil");
            put("item.metallics_arts.ring_copper_bronze", "Ring Copper-Bronze");
            put("item.metallics_arts.ring_electrum_gold", "Ring Gold-Electrum");
            put("item.metallics_arts.ring_lerasium_ettmetal", "Ring Lerasium-Ettmetal");
            put("item.metallics_arts.ring_pewter_tin", "Ring Tin-Pewter");
            put("item.metallics_arts.ring_zinc_brass", "Ring Zinc-Brass");
            put("item.metallics_arts.ring_steel_iron", "Ring Iron-Steel");

            put( "item.metallics_arts.iron_spike", "Iron Spike");
            put("item.metallics_arts.steel_spike", "Steel Spike");
            put("item.metallics_arts.tin_spike", "Tin Spike");
            put("item.metallics_arts.pewter_spike", "Pewter Spike");
            put("item.metallics_arts.copper_spike", "Copper Spike");
            put("item.metallics_arts.bronze_spike", "Bronze Spike");
            put("item.metallics_arts.zinc_spike", "Zinc Spike");
            put("item.metallics_arts.brass_spike", "Brass Spike");
            put("item.metallics_arts.chromium_spike", "Chromium Spike");
            put("item.metallics_arts.nicrosil_spike", "Nicrosil Spike");
            put("item.metallics_arts.aluminum_spike", "Aluminum Spike");
            put("item.metallics_arts.duralumin_spike", "Duralumin Spike");
            put("item.metallics_arts.cadmium_spike", "Cadmium Spike");
            put("item.metallics_arts.bendalloy_spike", "Bendalloy Spike");
            put("item.metallics_arts.electrum_spike", "Electrum Spike");
            put("item.metallics_arts.gold_spike", "Gold Spike");
            put("item.metallics_arts.atium_spike", "Atium Spike");
            put("item.metallics_arts.malatium_spike", "Malatium Spike");
            put("item.metallics_arts.lerasium_spike", "Lerasium Spike");
            put("item.metallics_arts.ettmetal_spike", "Ettmetal Spike");

            put( "item.metallics_arts.obsidian_dagger", "Obsidian Dagger");
            put("item.metallics_arts.cristal_dagger", "Cristal Dagger");
            put("item.metallics_arts.koloss_blade", "Koloss Blade");
            put("item.metallics_arts.dueling_staff", "Dueling Staff");
            put("item.metallics_arts.obsidian_axe", "Obsidian Axe");

            put( "item.metallics_arts.large_vial", "Large Vial");
            put("item.metallics_arts.small_vial", "Small Vial");


            put(  "curios.identifier.metalmind_slot", "MetalMind Slot");

            put( "block.metallics_arts.atium_cristal_block", "Crystallized Atium");
            put("block.metallics_arts.budding_atium", "Budding Atium");
            put("block.metallics_arts.atium_cluster", "Atium Cluster");
            put("block.metallics_arts.small_atium_bud", "Small Atium Bud");
            put("block.metallics_arts.medium_atium_bud", "Medium Atium Bud");
            put("block.metallics_arts.large_atium_bud", "Large Atium Bud");

            put( "block.metallics_arts.lerasium_cristal_block", "Crystallized Lerasium");
            put("block.metallics_arts.budding_lerasium", "Budding Lerasium");
            put("block.metallics_arts.lerasium_cluster", "Lerasium Cluster");
            put("block.metallics_arts.small_lerasium_bud", "Small Lerasium Bud");
            put("block.metallics_arts.medium_lerasium_bud", "Medium Lerasium Bud");
            put("block.metallics_arts.large_lerasium_bud", "Large Lerasium Bud");

            put( "block.metallics_arts.ettmetal_cristal_block", "Crystallized Ettmetal");
            put("block.metallics_arts.budding_ettmetal", "Budding Ettmetal");
            put("block.metallics_arts.ettmetal_cluster", "Ettmetal Cluster");
            put("block.metallics_arts.small_ettmetal_bud", "Small Ettmetal Bud");
            put("block.metallics_arts.medium_ettmetal_bud", "Medium Ettmetal Bud");
            put("block.metallics_arts.large_ettmetal_bud", "Large Ettmetal Bud");


            put(  "itemGroup.metallics_arts", "Metallics Arts");
            put("itemGroup.metallics_arts.decorations", "Metallics Arts: Decorations");

            put( "block.metallics_arts.alloy_furnace", "Alloy Furnace");

            put( "screen.metallics_arts.alloy_furnace", "Alloy Furnace");
            put("curios.identifier.ma_metalmind_slot", "MetalMind Slot");

            put("key.category_metallics_arts","Metallics Arts");
            put("key.metallics_arts.allomantic","Allomantic Metal Selector");
            put("key.metallics_arts.feruchemic","Feruchemic Metal Selector");
            put("key.metallics_arts.allomantic_push", "Allomantic Push");
            put("key.metallics_arts.allomantic_pull", "Allomantic Pull");
            put("key.metallics_arts.vertical_jump","Vertical Push");


            put(  "item.metallics_arts.steel_allomantic_icon", "Allomantic Steel");
            put("item.metallics_arts.tin_allomantic_icon", "Allomantic Tin");
            put("item.metallics_arts.pewter_allomantic_icon", "Allomantic Pewter");
            put("item.metallics_arts.bronze_allomantic_icon", "Allomantic Bronze");
            put("item.metallics_arts.zinc_allomantic_icon", "Allomantic Zinc");
            put("item.metallics_arts.brass_allomantic_icon", "Allomantic Brass");
            put("item.metallics_arts.electrum_allomantic_icon", "Allomantic Electrum");
            put("item.metallics_arts.cadmium_allomantic_icon", "Allomantic Cadmium");
            put("item.metallics_arts.bendalloy_allomantic_icon", "Allomantic Bendalloy");
            put("item.metallics_arts.aluminum_allomantic_icon", "Allomantic Aluminum");
            put("item.metallics_arts.duralumin_allomantic_icon", "Allomantic Duralumin");
            put("item.metallics_arts.chromium_allomantic_icon", "Allomantic Chromium");
            put("item.metallics_arts.nicrosil_allomantic_icon", "Allomantic Nicrosil");
            put("item.metallics_arts.gold_allomantic_icon", "Allomantic Gold");
            put("item.metallics_arts.iron_allomantic_icon", "Allomantic Iron");
            put("item.metallics_arts.copper_allomantic_icon", "Allomantic Copper");
            put("item.metallics_arts.atium_allomantic_icon", "Allomantic Atium");
            put("item.metallics_arts.malatium_allomantic_icon", "Allomantic Malatium");
            put("item.metallics_arts.lerasium_allomantic_icon", "Allomantic Lerasium");
            put("item.metallics_arts.ettmetal_allomantic_icon", "Allomantic Ettmetal");


            put(  "item.metallics_arts.steel_feruchemic_icon", "Feruchemic Steel");
            put("item.metallics_arts.tin_feruchemic_icon", "Feruchemic Tin");
            put("item.metallics_arts.pewter_feruchemic_icon", "Feruchemic Pewter");
            put("item.metallics_arts.bronze_feruchemic_icon", "Feruchemic Bronze");
            put("item.metallics_arts.zinc_feruchemic_icon", "Feruchemic Zinc");
            put("item.metallics_arts.brass_feruchemic_icon", "Feruchemic Brass");
            put("item.metallics_arts.electrum_feruchemic_icon", "Feruchemic Electrum");
            put("item.metallics_arts.cadmium_feruchemic_icon", "Feruchemic Cadmium");
            put("item.metallics_arts.bendalloy_feruchemic_icon", "Feruchemic Bendalloy");
            put("item.metallics_arts.aluminum_feruchemic_icon", "Feruchemic Aluminum");
            put("item.metallics_arts.duralumin_feruchemic_icon", "Feruchemic Duralumin");
            put("item.metallics_arts.chromium_feruchemic_icon", "Feruchemic Chromium");
            put("item.metallics_arts.nicrosil_feruchemic_icon", "Feruchemic Nicrosil");

            put( "item.metallics_arts.gold_feruchemic_icon", "Feruchemic Gold");
            put("item.metallics_arts.iron_feruchemic_icon", "Feruchemic Iron");
            put("item.metallics_arts.copper_feruchemic_icon", "Feruchemic Copper");

            put( "item.metallics_arts.atium_feruchemic_icon", "Feruchemic Atium");
            put("item.metallics_arts.malatium_feruchemic_icon", "Feruchemic Malatium");
            put("item.metallics_arts.lerasium_feruchemic_icon", "Feruchemic Lerasium");
            put("item.metallics_arts.ettmetal_feruchemic_icon", "Feruchemic Ettmetal");

            put( "metallics_arts.patchouli.name_book", "Metallics Arts, Guide");
            put("metallics_arts.patchouli.landing_text", "I am); unput(fortunately); Thput(e Hero of Ages and I have a question for you, does not a man have the right to posses his own metals? No); saput(ys the man from the empire. It belongs to \"god\". No); saput(ys the survivor. It belongs to everyone. No); saput(ys the man from Elendel. It belongs to the poor ones. I rejected those proposals. Instead of that I chose something different. I chose my own path); I put(chose… Metallics Arts Mod.");

            put( "metallics_arts.mental_mind.owner", "Owner");

            put( "metallics_arts.mental_mind.nobody", "Nobody");

            put( "metallics_arts.metal_translate.iron", "Iron");
            put("metallics_arts.metal_translate.steel", "Steel");
            put("metallics_arts.metal_translate.tin", "Tin");
            put("metallics_arts.metal_translate.pewter", "Pewter");
            put("metallics_arts.metal_translate.zinc", "Zinc");
            put("metallics_arts.metal_translate.brass", "Brass");
            put("metallics_arts.metal_translate.copper", "Copper");
            put("metallics_arts.metal_translate.bronze", "Bronze");
            put("metallics_arts.metal_translate.aluminum", "Aluminum");
            put("metallics_arts.metal_translate.duralumin", "Duralumin");
            put("metallics_arts.metal_translate.chromium", "Chromium");
            put("metallics_arts.metal_translate.nicrosil", "Nicrosil");
            put("metallics_arts.metal_translate.gold", "Gold");
            put("metallics_arts.metal_translate.electrum", "Electrum");
            put("metallics_arts.metal_translate.bendalloy", "Bendalloy");
            put("metallics_arts.metal_translate.cadmium", "Cadmium");
            put("metallics_arts.metal_translate.atium", "Atium");
            put("metallics_arts.metal_translate.malatium", "Malatium");
            put("metallics_arts.metal_translate.lerasium", "Lerasium");
            put("metallics_arts.metal_translate.ettmetal", "Ettmetal");

            put( "metallics_arts.mental_mind_translate.has_reserve", "Has Allomantic Reserves");
            put("metallics_arts.mental_mind_translate.not_has_reserve", "Hasn´t Allomantic Reserves");

            put( "metallics_arts.mental_mind_translate.uses", "Uses");

            put( "metallics_arts.spike_feruchemic_power", "Power stored, Feruchemic");
            put("metallics_arts.spike_allomantic_power", "Power stored, Allomantic");

            put( "metallics_arts.mental_mind_translate.store_identity", "Storing Identity");
            put("metallics_arts.mental_mind_translate.off_power", "Power Off");
            put("metallics_arts.spike_allomantic_power.tapping_identity", "Tapping Identity");

            put( "metallics_arts.mental_mind_translate.shift_info", "[Shift] More info");

            put( "metallics_arts.mental_mind.owner_someone", "Someone");


            put(  "key.category_powers_metallics_arts","Metallics Arts, Powers");
            put("key.metallics_arts.feruchemic_decant","Feruchemical Decant");
            put("key.metallics_arts.feruchemic_store","Feruchemical Storage");
            put("key.metallics_arts.switch_overlay"," Switch Overlay");

            put( "key.metallics_arts.iron_power", "Iron");
            put("key.metallics_arts.steel_power", "Steel");
            put("key.metallics_arts.tin_power", "Tin");
            put("key.metallics_arts.pewter_power", "Pewter");
            put("key.metallics_arts.copper_power", "Copper");
            put("key.metallics_arts.bronze_power", "Bronze");
            put("key.metallics_arts.zinc_power", "Zinc");
            put("key.metallics_arts.brass_power", "Brass");
            put("key.metallics_arts.chromium_power", "Chromium");
            put("key.metallics_arts.nicrosil_power", "Nicrosil");
            put("key.metallics_arts.aluminum_power", "Aluminum");
            put("key.metallics_arts.duralumin_power", "Duralumin");
            put("key.metallics_arts.cadmium_power", "Cadmium");
            put("key.metallics_arts.bendalloy_power", "Bendalloy");
            put("key.metallics_arts.electrum_power", "Electrum");
            put("key.metallics_arts.gold_power", "Gold");
            put("key.metallics_arts.atium_power", "Atium");
            put("key.metallics_arts.malatium_power", "Malatium");
            put("key.metallics_arts.lerasium_power", "Lerasium");
            put("key.metallics_arts.ettmetal_power", "Ettmetal");


    }};

    private Map<String, String> symbols = new HashMap<>() {{
        for (MetalTagEnum metal: MetalTagEnum.values()) {
            put("f_" + metal.getNameLower() + "_1", "Feruchemical " + metal.getNameLower() + " shading");
            put("f_" + metal.getNameLower() + "_2", "Feruchemical " + metal.getNameLower() + " solid");
            put("a_" + metal.getNameLower() + "_1", "Allomantical " + metal.getNameLower() + " shading");
            put("a_" + metal.getNameLower() + "_2", "Allomantical " + metal.getNameLower() + " solid");
        }
    }};

    private Map<String, String> patterns = new HashMap<>(){{

            for (MetalTagEnum metal : MetalTagEnum.values()) {

                    put("item.metallics_arts.f_"+metal.getNameLower()+"_pattern", "Feruchemical " + metal.getNameLower() + " pattern");
                    put("item.metallics_arts.f_"+metal.getNameLower()+"_pattern.desc", "Feruchemical " + metal.getNameLower() + " pattern");
                    put("item.metallics_arts.a_"+metal.getNameLower()+"_pattern", "Allomantic " + metal.getNameLower() + " pattern");
                    put("item.metallics_arts.a_"+metal.getNameLower()+"_pattern.desc", "Allomantic " + metal.getNameLower() + " pattern");

            }

    }};

    private Map<String, String> colors = new HashMap<>() {{
        put("white", "blanco");
        put("orange", "naranja");
        put("magenta", "magenta");
        put("light_blue", "celeste");
        put("yellow", "amarillo");
        put("lime", "verde lima");
        put("pink", "rosa");
        put("gray", "gris");
        put("light_gray", "gris claro");
        put("cyan", "cian");
        put("purple", "morado");
        put("blue", "azul");
        put("brown", "marron");
        put("green", "verde");
        put("red", "rojo");
        put("black", "negro");
    }};

    public ModLanguageProviderEN(DataGenerator gen, String modid, String locale) {
        super(gen, modid, locale);

    }

    @Override
    protected void addTranslations() {

        for(String key: base.keySet()) {
            add(key, base.get(key));
        }

        for(String key: patterns.keySet()){
                add(key, patterns.get(key));
        }

        for (String keySymbol : symbols.keySet()) {
            for (String keyColor : colors.keySet()) {
                add("block.minecraft.banner.metallics_arts." + keySymbol + "." + keyColor, symbols.get(keySymbol) + " " + keyColor);
            }
        }
    }
}
