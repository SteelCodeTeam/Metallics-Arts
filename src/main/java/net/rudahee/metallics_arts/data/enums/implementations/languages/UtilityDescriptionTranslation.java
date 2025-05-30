package net.rudahee.metallics_arts.data.enums.implementations.languages;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UtilityDescriptionTranslation {

    MA_DECORATIONS("metallics_arts.tab.decorations", "MA: Decorations", "MA: Decoraciones"),

    METALMIND_SLOT_IDENTIFIER("curios.identifier.metalmind_slot", "Metalmind", "Mente de metal"),
    METALMIND_SLOT_OWNER("metallics_arts.metal_mind.owner", "Owner", "Propietario"),
    METALMIND_SLOT_NOBODY("metallics_arts.metal_mind.nobody", "Nobody", "Nadie"),

    MORE_INFO_ON_SHIFT("metallics_arts.metal_mind_translate.shift_info", "More info: Shift", "Mas info: Shift"),
    OFF_POWER_ALUMINUM("metallics_arts.metal_mind_translate.off_power", "Deactivate", "Desactivado"),
    STORE_POWER_ALUMINUM("metallics_arts.metal_mind_translate.store_identity", "Storing Identity", "Almacenando identidad"),
    TAP_POWER_ALUMINUM("metallics_arts.metal_mind_translate.tap_identity", "Tapping Identity", "Decantando identidad"),
    XP_POINTS_COPPER("metallics_arts.metal_mind_translate.xp_points", " Experience Points", " Puntos de experiencia"),
    USES_MALATIUM("metallics_arts.metal_mind_translate.uses", "Durability Points", "Puntos de durabilidad"),
    HAS_RESERVE_LERASIUM("metallics_arts.metal_mind_translate.has_reserve", "Has Reserves", "Tiene reservas"),

    METAL_TRANSLATION_IRON("metallics_arts.metal_translate.iron", "Iron", "Hierro"),
    METAL_TRANSLATION_STEEL("metallics_arts.metal_translate.steel", "Steel", "Acero"),
    METAL_TRANSLATION_TIN("metallics_arts.metal_translate.tin", "Tin", "Estaño"),
    METAL_TRANSLATION_PEWTER("metallics_arts.metal_translate.pewter", "Pewter", "Peltre"),
    METAL_TRANSLATION_COPPER("metallics_arts.metal_translate.copper", "Copper", "Cobre"),
    METAL_TRANSLATION_BRONZE("metallics_arts.metal_translate.bronze", "Bronze", "Bronce"),
    METAL_TRANSLATION_ZINC("metallics_arts.metal_translate.zinc", "Zinc", "Zinc"),
    METAL_TRANSLATION_BRASS("metallics_arts.metal_translate.brass", "Brass", "Latón"),
    METAL_TRANSLATION_CHROMIUM("metallics_arts.metal_translate.chromium", "Chromium", "Cromo"),
    METAL_TRANSLATION_NICROSIL("metallics_arts.metal_translate.nicrosil", "Nicrosil", "Nicrosil"),
    METAL_TRANSLATION_ALUMINUM("metallics_arts.metal_translate.aluminum", "Aluminum", "Aluminio"),
    METAL_TRANSLATION_DURALUMIN("metallics_arts.metal_translate.duralumin", "Duralumin", "Duraluminio"),
    METAL_TRANSLATION_CADMIUM("metallics_arts.metal_translate.cadmium", "Cadmium", "Cadmio"),
    METAL_TRANSLATION_BENDALLOY("metallics_arts.metal_translate.bendalloy", "Bendalloy", "Bendaleo"),
    METAL_TRANSLATION_ELECTRUM("metallics_arts.metal_translate.electrum", "Electrum", "Electro"),
    METAL_TRANSLATION_GOLD("metallics_arts.metal_translate.gold", "Gold", "Oro"),
    METAL_TRANSLATION_ATIUM("metallics_arts.metal_translate.atium", "Atium", "Atium"),
    METAL_TRANSLATION_MALATIUM("metallics_arts.metal_translate.malatium", "Malatium", "Malatium"),
    METAL_TRANSLATION_LERASIUM("metallics_arts.metal_translate.lerasium", "Lerasium", "Lerasium"),
    METAL_TRANSLATION_ETTMETAL("metallics_arts.metal_translate.ettmetal", "Ettmetal", "Ettmetal"),

    METALLIC_FERUCHEMIC_POWER("metallics_arts.spike_feruchemic_power", "Feruchemic Power", "Poder feruquimico"),
    METALLIC_ALLOMANTIC_POWER("metallics_arts.spike_allomantic_power", "Allomantic Power", "Poder alomantico"),

    JOIN_WORLD_ALLOMANTIC("utility.metallics_arts.join_world.allomantic", "Ok bro, you have %s (allomantic)", "Has aterrizado en el mundo de Minecraft con el incrible poder alomantico del %s"),
    JOIN_WORLD_FERUCHEMIC("utility.metallics_arts.join_world.feruchemic", "Ok bro, you have %s (feruchemic)", "Has aterrizado en el mundo de Minecraft con el incrible poder feruquimico del %s"),
    JOIN_WORLD_TWINBORN("utility.metallics_arts.join_world.twinborn", "Ok bro, you are not french, so not bad. Twinborn %s", "Eres genial, has venido a este mundo como nacidoble de %s"),

    BULLET_LEAD("util.metallics_arts.bullet_type.lead", "Lead bullet charged", "En el cargador: Balas de plomo"),
    ALUMINUM_LEAD("util.metallics_arts.bullet_type.aluminum", "Aluminum bullet charged", "En el cargador: Balas de aluminio");


    private final String key;
    private final String english;
    private final String spanish;
}
