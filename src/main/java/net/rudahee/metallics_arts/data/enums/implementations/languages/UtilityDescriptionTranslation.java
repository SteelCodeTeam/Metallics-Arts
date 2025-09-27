package net.rudahee.metallics_arts.data.enums.implementations.languages;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UtilityDescriptionTranslation {

    MA_DECORATIONS("metallics_arts.tab.decorations", "MA: Decorations", "MA: Decoraciones","MA: Decoraçionê"),

    METALMIND_SLOT_IDENTIFIER("curios.identifier.metalmind_slot", "Metalmind", "Mente de metal","Mente de metâh"),
    METALMIND_SLOT_OWNER("metallics_arts.metal_mind.owner", "Owner", "Propietario","Propietario"),
    METALMIND_SLOT_NOBODY("metallics_arts.metal_mind.nobody", "Nobody", "Nadie","Nadie"),

    MORE_INFO_ON_SHIFT("metallics_arts.metal_mind_translate.shift_info", "More info: Shift", "Mas info: Shift","Mâh informaçión: Máyû"),
    OFF_POWER_ALUMINUM("metallics_arts.metal_mind_translate.off_power", "Deactivate", "Desactivado","Deçâttibâh"),
    STORE_POWER_ALUMINUM("metallics_arts.metal_mind_translate.store_identity", "Storing Identity", "Almacenando identidad","Armaçenâh Identidá"),
    TAP_POWER_ALUMINUM("metallics_arts.metal_mind_translate.tap_identity", "Tapping Identity", "Decantando identidad","Decantâh Identidá"),
    XP_POINTS_COPPER("metallics_arts.metal_mind_translate.xp_points", " Experience Points", " Puntos de experiencia","Puntô d'êpperiençia"),
    USES_MALATIUM("metallics_arts.metal_mind_translate.uses", "Durability Points", "Puntos de durabilidad","Puntô de durabilidá"),
    HAS_RESERVE_LERASIUM("metallics_arts.metal_mind_translate.has_reserve", "Has Reserves", "Tiene reservas","Tiene reçerbâ"),

    METAL_TRANSLATION_IRON("metallics_arts.metal_translate.iron", "Iron", "Hierro","Yerro"),
    METAL_TRANSLATION_STEEL("metallics_arts.metal_translate.steel", "Steel", "Acero","Açero"),
    METAL_TRANSLATION_TIN("metallics_arts.metal_translate.tin", "Tin", "Estaño","Êttaño"),
    METAL_TRANSLATION_PEWTER("metallics_arts.metal_translate.pewter", "Pewter", "Peltre","Pertre"),
    METAL_TRANSLATION_COPPER("metallics_arts.metal_translate.copper", "Copper", "Cobre","Cobre"),
    METAL_TRANSLATION_BRONZE("metallics_arts.metal_translate.bronze", "Bronze", "Bronce","Bronçe"),
    METAL_TRANSLATION_ZINC("metallics_arts.metal_translate.zinc", "Zinc", "Zinc","Çinc"),
    METAL_TRANSLATION_BRASS("metallics_arts.metal_translate.brass", "Brass", "Latón","Latón"),
    METAL_TRANSLATION_CHROMIUM("metallics_arts.metal_translate.chromium", "Chromium", "Cromo","Cromo"),
    METAL_TRANSLATION_NICROSIL("metallics_arts.metal_translate.nicrosil", "Nicrosil", "Nicrosil","Nicroçîh"),
    METAL_TRANSLATION_ALUMINUM("metallics_arts.metal_translate.aluminum", "Aluminum", "Aluminio","Aluminio"),
    METAL_TRANSLATION_DURALUMIN("metallics_arts.metal_translate.duralumin", "Duralumin", "Duraluminio","Duralumin"),
    METAL_TRANSLATION_CADMIUM("metallics_arts.metal_translate.cadmium", "Cadmium", "Cadmio","Câmmio"),
    METAL_TRANSLATION_BENDALLOY("metallics_arts.metal_translate.bendalloy", "Bendalloy", "Bendaleo","Bendaleo"),
    METAL_TRANSLATION_ELECTRUM("metallics_arts.metal_translate.electrum", "Electrum", "Electro","Elêttrun"),
    METAL_TRANSLATION_GOLD("metallics_arts.metal_translate.gold", "Gold", "Oro","Oro"),
    METAL_TRANSLATION_ATIUM("metallics_arts.metal_translate.atium", "Atium", "Atium","Atiun"),
    METAL_TRANSLATION_MALATIUM("metallics_arts.metal_translate.malatium", "Malatium", "Malatium","Malatiun"),
    METAL_TRANSLATION_LERASIUM("metallics_arts.metal_translate.lerasium", "Lerasium", "Lerasium","Leraçiun"),
    METAL_TRANSLATION_ETTMETAL("metallics_arts.metal_translate.ettmetal", "Ettmetal", "Ettmetal","Êttmetâh"),

    METALLIC_FERUCHEMIC_POWER("metallics_arts.spike_feruchemic_power", "Feruchemic Power", "Poder feruquimico","Podêh feruquímico"),
    METALLIC_ALLOMANTIC_POWER("metallics_arts.spike_allomantic_power", "Allomantic Power", "Poder alomantico","Podêh alomántico"),

    JOIN_WORLD_ALLOMANTIC("utility.metallics_arts.join_world.allomantic", "Ok bro, you have %s (allomantic)", "Has aterrizado en el mundo de Minecraft con el incrible poder alomantico del %s","Un alomante de %s a benío ar mundo."),
    JOIN_WORLD_FERUCHEMIC("utility.metallics_arts.join_world.feruchemic", "Ok bro, you have %s (feruchemic)", "Has aterrizado en el mundo de Minecraft con el incrible poder feruquimico del %s","Un ferrin de %s a benío ar mundo."),
    JOIN_WORLD_TWINBORN("utility.metallics_arts.join_world.twinborn", "Ok bro, you are not french, so not bad. Twinborn %s", "Eres genial, has venido a este mundo como nacidoble de %s","Un naçidoble de %s a benío ar mundo. Un naçidoble de %s."),

    BULLET_LEAD("util.metallics_arts.bullet_type.lead", "Lead bullet charged", "En el cargador: Balas de plomo","Balâh de plomo cargâh"),
    ALUMINUM_LEAD("util.metallics_arts.bullet_type.aluminum", "Aluminum bullet charged", "En el cargador: Balas de aluminio","Balâh d'aluminio cargâh");


    private final String key;
    private final String english;
    private final String spanish;
    private final String andaluz;
}
