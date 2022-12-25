package net.rudahee.metallics_arts.data.providers;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;
import net.rudahee.metallics_arts.data.enums.implementations.MetalsNBTData;
import net.rudahee.metallics_arts.data.enums.implementations.MetalSpanish;

import java.util.HashMap;
import java.util.Map;

public class ModLanguageProviderES extends LanguageProvider {


    private Map<String, String> base = new HashMap<>() {{

        put("item.metallics_arts.atium", "Atium");
        put("item.metallics_arts.malatium", "Malatium");
        put("item.metallics_arts.lerasium", "Lerasium");
        put("item.metallics_arts.ettmetal", "Ettmetal");


        put("item.metallics_arts.raw_tin", "Estaño en bruto");
        put("item.metallics_arts.raw_zinc", "Zinc en bruto");
        put("item.metallics_arts.raw_cadmium", "Cadmio en bruto");
        put("item.metallics_arts.raw_aluminum", "Aluminio en bruto");

        put("item.metallics_arts.raw_chromium", "Cromo en bruto");
        put("item.metallics_arts.raw_silver", "Plata en bruto");
        put("item.metallics_arts.raw_lead", "Plomo en bruto");
        put("item.metallics_arts.raw_nickel", "Níquel en bruto");


        put("block.metallics_arts.tin_ore", "Mena de estaño");
        put("block.metallics_arts.bronze_ore", "Mena de bronce");
        put("block.metallics_arts.zinc_ore", "Mena de zinc");
        put("block.metallics_arts.brass_ore", "Mena de latón");
        put("block.metallics_arts.cadmium_ore", "Mena de cadmio");
        put("block.metallics_arts.aluminum_ore", "Mena de aluminio");
        put("block.metallics_arts.chromium_ore", "Mena de cromo");
        put("block.metallics_arts.lead_ore", "Mena de plomo");

        put("block.metallics_arts.deepslate_chromium_ore", "Mena de cromo de pizarra profunda");
        put("block.metallics_arts.deepslate_aluminum_ore", "Mena de aluminio de pizarra profunda");
        put("block.metallics_arts.deepslate_silver_ore", "Mena de plata de pizarra profunda");
        put("block.metallics_arts.deepslate_lead_ore", "Mena de plomo de pizarra profunda");
        put("block.metallics_arts.deepslate_nickel_ore", "Mena de níquel de pizarra profunda");
        put("block.metallics_arts.deepslate_cadmium_ore", "Mena de cadmio de pizarra profunda");

        put("block.metallics_arts.raw_tin_block", "Bloque de estaño en bruto");
        put("block.metallics_arts.raw_zinc_block", "Bloque de zinc en bruto");
        put("block.metallics_arts.raw_cadmium_block", "Bloque de cadmio en bruto");
        put("block.metallics_arts.raw_aluminum_block", "Bloque de aluminio en bruto");

        put("block.metallics_arts.raw_chromium_block", "Bloque de cromo en bruto");
        put("block.metallics_arts.raw_silver_block", "Bloque de plata en bruto");
        put("block.metallics_arts.raw_lead_block", "Bloque de plomo en bruto");
        put("block.metallics_arts.raw_nickel_block", "Bloque de níquel en bruto");


        put("item.metallics_arts.band_aluminum_duralumin", "Brazal de aluminio-duralumín");
        put("item.metallics_arts.band_atium_malatium", "Brazal de atium-malatium");
        put("item.metallics_arts.band_cadmium_bendalloy", "Brazal de cadmio-bendaleo");
        put("item.metallics_arts.band_chromium_nicrosil", "Brazal de cromo-nicrosil");
        put("item.metallics_arts.band_copper_bronze", "Brazal de cobre-bronce");
        put("item.metallics_arts.band_electrum_gold", "Brazal de oro-electrum");
        put("item.metallics_arts.band_lerasium_ettmetal", "Brazal de lerasium-ettmetal");
        put("item.metallics_arts.band_pewter_tin", "Brazal de estaño-peltre");
        put("item.metallics_arts.band_zinc_brass", "Brazal de zinc-latón");
        put("item.metallics_arts.band_steel_iron", "Brazal de hierro-acero");

        put("item.metallics_arts.ring_aluminum_duralumin", "Anillo de aluminio-duralumín");
        put("item.metallics_arts.ring_atium_malatium", "Anillo de atium-malatium");
        put("item.metallics_arts.ring_cadmium_bendalloy", "Anillo de cadmio-bendaleo");
        put("item.metallics_arts.ring_chromium_nicrosil", "Anillo de cromo-nicrosil");
        put("item.metallics_arts.ring_copper_bronze", "Anillo de cobre-bronce");
        put("item.metallics_arts.ring_electrum_gold", "Anillo de oro-electrum");
        put("item.metallics_arts.ring_lerasium_ettmetal", "Anillo de lerasium-ettmetal");
        put("item.metallics_arts.ring_pewter_tin", "Anillo de estaño-peltre");
        put("item.metallics_arts.ring_zinc_brass", "Anillo de zinc-latón");
        put("item.metallics_arts.ring_steel_iron", "Anillo de hierro-acero");


        put("item.metallics_arts.obsidian_dagger", "Daga de obsidiana");
        put("item.metallics_arts.cristal_dagger", "Daga de cristal");
        put("item.metallics_arts.koloss_blade", "Espada de koloss");
        put("item.metallics_arts.dueling_staff", "Bastón de duelo");
        put("item.metallics_arts.obsidian_axe", "Hacha de obsidiana");

        put("item.metallics_arts.large_vial", "Vial grande");
        put("item.metallics_arts.small_vial", "Vial pequeño");


        put("curios.identifier.metalmind_slot", "Ranura de mente de metal");

        put("block.metallics_arts.atium_cristal_block", "Atium Cristalizado");
        put("block.metallics_arts.budding_atium", "Brotador de atium");
        put("block.metallics_arts.atium_cluster", "Cúmulo de atium");
        put("block.metallics_arts.small_atium_bud", "Brote de atium pequeño");
        put("block.metallics_arts.medium_atium_bud", "Brote de atium mediano");
        put("block.metallics_arts.large_atium_bud", "Brote de atium grande");

        put("block.metallics_arts.lerasium_cristal_block", "Lerasium Cristalizado");
        put("block.metallics_arts.budding_lerasium", "Brotador de lerasium");
        put("block.metallics_arts.lerasium_cluster", "Cúmulo de lerasium");
        put("block.metallics_arts.small_lerasium_bud", "Brote de lerasium pequeño");
        put("block.metallics_arts.medium_lerasium_bud", "Brote de lerasium mediano");
        put("block.metallics_arts.large_lerasium_bud", "Brote de lerasium grande");

        put("block.metallics_arts.ettmetal_cristal_block", "Ettmetal Cristalizado");
        put("block.metallics_arts.budding_ettmetal", "Brotador de ettmetal");
        put("block.metallics_arts.ettmetal_cluster", "Cúmulo de ettmetal");
        put("block.metallics_arts.small_ettmetal_bud", "Brote de ettmetal pequeño");
        put("block.metallics_arts.medium_ettmetal_bud", "Brote de ettmetal mediano");
        put("block.metallics_arts.large_ettmetal_bud", "Brote de ettmetal grande");


        put("itemGroup.metallics_arts", "Artes Metálicas");
        put("itemGroup.metallics_arts.decorations", "Artes Metálicas: Decoración");

        put("block.metallics_arts.alloy_furnace", "Horno de fusión");

        put("screen.metallics_arts.alloy_furnace", "Horno de fusión");
        put("curios.identifier.ma_metalmind_slot", "Ranura de mente de metal");

        put("key.categorymetallics_arts", "Artes Metálicas");
        put("key.metallics_arts.allomantic", "Selector de metal Alomántico");
        put("key.metallics_arts.feruchemic", "Selector de metal Feruquímico");
        put("key.metallics_arts.allomantic_push", "Empuje Alomántico");
        put("key.metallics_arts.allomantic_pull", "Tiron Alomántico");
        put("key.metallics_arts.vertical_jump", "Empuje vertical");


        put("metallics_arts.patchouli.name_book", "Artes Metalicas, Guia");

        put("metallics_arts.patchouli.landing_text", "Soy, por desgracia, el Héroe de las Eras, y tengo una pregunta que hacerte, ¿acaso un hombre no tiene derecho a poseer sus propios metales? No, dice el hombre del Imperio. Pertenece al \"dios\". No, dice el superviviente. Pertenece a todos. No, dice el hombre de Elendel. Pertenece a los pobres. Yo rechacé esas propuestas. En vez de eso elegí algo distinto. Elegi mi propio camino, elegí... Artes Metalicas Mod.");

        put("metallics_arts.mental_mind.owner", "Propietario");

        put("metallics_arts.mental_mind.nobody", "Sin Propietario");


        put("metallics_arts.mental_mind_translate.has_reserve", "Tiene Reservas Alomanticas");
        put("metallics_arts.mental_mind_translate.not_has_reserve", "No Tiene Reservas Alomanticas");

        put("metallics_arts.mental_mind_translate.uses", "Usos");

        put("metallics_arts.spike_feruchemic_power", "Poder Almacenado, Feruqumico");
        put("metallics_arts.spike_allomantic_power", "Poder Almacenado, Alomantico");

        put("metallics_arts.mental_mind_translate.store_identity", "Almacenando Identidad");
        put("metallics_arts.mental_mind_translate.off_power", "Poder Apagado");
        put("metallics_arts.spike_allomantic_power.tapping_identity", "Decantado Identidad");

        put("metallics_arts.mental_mind.owner_someone", "Alguien");


        put("key.category_powers_metallics_arts", "Artes Metalicas, Poderes");
        put("key.metallics_arts.feruchemic_decant", "Decante Feruquimico");
        put("key.metallics_arts.feruchemic_store", "Almacenaje Feruqimico");
        put("key.metallics_arts.switch_overlay", "Alternar interfaz");

    }};


    private Map<String, String> ingots = new HashMap<>() {{

        int cont = 0;
        for (MetalsNBTData metal: MetalsNBTData.values()) {
            if(cont <=16){
                put("item.metallics_arts."+ metal.getNameLower() +"_ingot","Lingote de "+ MetalSpanish.valueOf(metal.name()).getMetalNameLower());
                cont++;
            }
        }
        put("item.metallics_arts.silver_ingot", "Lingote de plata");
        put("item.metallics_arts.lead_ingot", "Lingote de plomo");
        put("item.metallics_arts.nickel_ingot", "Lingote de níquel");

    }};

    private Map<String, String> nuggets = new HashMap<>() {{

        for (MetalsNBTData metal: MetalsNBTData.values()) {
            put("item.metallics_arts."+ metal.getNameLower() +"_nugget","Pepita de "+ MetalSpanish.valueOf(metal.name()).getMetalNameLower());
        }

        put("item.metallics_arts.silver_nugget", "Pepita de plata");
        put("item.metallics_arts.lead_nugget", "Pepita de plomo");
        put("item.metallics_arts.nickel_nugget", "Pepita de níquel");

    }};
    private Map<String, String> blocks = new HashMap<>() {{

        for (MetalsNBTData metal: MetalsNBTData.values()) {
            put("block.metallics_arts."+ metal.getNameLower() +"_block","Bloque de "+ MetalSpanish.valueOf(metal.name()).getMetalNameLower());
        }

        put("block.metallics_arts.silver_block", "Bloque de plata");
        put("block.metallics_arts.lead_block", "Bloque de plomo");
        put("block.metallics_arts.nickel_block", "Bloque de níquel");

    }};

    private Map<String, String> spikes = new HashMap<>() {{

        for (MetalsNBTData metal: MetalsNBTData.values()) {
            put("item.metallics_arts."+ metal.getNameLower() +"_spìke","Clavo de "+ MetalSpanish.valueOf(metal.name()).getMetalNameLower());
        }

    }};
    private Map<String, String> icons = new HashMap<>() {{

        for (MetalsNBTData metal: MetalsNBTData.values()) {
            put("item.metallics_arts."+ metal.getNameLower()+"_allomantic_icon" , MetalSpanish.valueOf(metal.name()).getMetalName()+" Alomantico");
            put("item.metallics_arts."+ metal.getNameLower()+"_feruchemic_icon" , MetalSpanish.valueOf(metal.name()).getMetalName()+" Feruquimico");
        }

    }};

    private Map<String, String> metalls = new HashMap<>() {{

        for (MetalsNBTData metal: MetalsNBTData.values()) {
            put("metallics_arts.metal_translate."+ metal.getNameLower() , MetalSpanish.valueOf(metal.name()).getMetalName());
        }

    }};

    private Map<String, String> powers = new HashMap<>() {{

        for (MetalsNBTData metal: MetalsNBTData.values()) {
            put("key.metallics_arts."+ metal.getNameLower() +"_power", MetalSpanish.valueOf(metal.name()).getMetalName());
        }

    }};
    private Map<String, String> symbols = new HashMap<>() {{
        for (MetalsNBTData metal: MetalsNBTData.values()) {
            put("f_"+metal.getNameLower()+"_1", MetalSpanish.valueOf(metal.name()).getMetalName() + " feruquimico sombreado");
            put("f_"+metal.getNameLower()+"_2", MetalSpanish.valueOf(metal.name()).getMetalName() + " feruquimico solido");
            put("a_"+metal.getNameLower()+"_1", MetalSpanish.valueOf(metal.name()).getMetalName() + " alomantico sombreado");
            put("a_"+metal.getNameLower()+"_2", MetalSpanish.valueOf(metal.name()).getMetalName() + " alomantico solido");
        }
    }};

    private Map<String, String> patterns = new HashMap<>() {{
        for (MetalsNBTData metal: MetalsNBTData.values()) {
            put("item.metallics_arts.f_"+metal.getNameLower()+"_pattern","Patrón ferruquimico de " + MetalSpanish.valueOf(metal.name()).getMetalName());
            put("item.metallics_arts.f_"+metal.getNameLower()+"_pattern.desc", "Patrón ferruquimico de " + MetalSpanish.valueOf(metal.name()).getMetalName());
            put("item.metallics_arts.a_"+metal.getNameLower()+"_pattern", "Patrón alomántico de " + MetalSpanish.valueOf(metal.name()).getMetalName());
            put("item.metallics_arts.a_"+metal.getNameLower()+"_pattern.desc", "Patrón alomántico de " + MetalSpanish.valueOf(metal.name()).getMetalName());
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

    public ModLanguageProviderES(DataGenerator gen, String modid, String locale) {
        super(gen, modid, locale);

    }

    @Override
    protected void addTranslations() {

        for (String key: ingots.keySet()){
            add(key, ingots.get(key));
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

        for (String key: metalls.keySet()){
            add(key, metalls.get(key));
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
