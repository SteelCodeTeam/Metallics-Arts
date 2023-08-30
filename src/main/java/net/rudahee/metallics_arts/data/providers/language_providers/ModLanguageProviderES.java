package net.rudahee.metallics_arts.data.providers.language_providers;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.data.enums.implementations.GemsEnum;
import net.rudahee.metallics_arts.data.enums.implementations.MetalEnum;
import net.rudahee.metallics_arts.data.enums.implementations.languages.*;
import net.rudahee.metallics_arts.data.providers.language_providers.book.BookHelperES;

import java.util.HashMap;
import java.util.Map;

/**
 * A custom class for providing Spanish translations for mod elements.
 *
 * @author SteelCode Team
 * @since 1.5.6
 */
public class ModLanguageProviderES extends LanguageProvider {

    private final Map<String, String> base = new HashMap<>() {{

        put("entity.minecraft.villager.metallics_arts.forge_master", "MAESTRO DE LA FORGA");

        put("item.metallics_arts.large_vial", CTW.VIAL.getNameInSpanish() + " " + CTW.LARGE.getNameInSpanish());
        put("item.metallics_arts.small_vial", CTW.VIAL.getNameInSpanish() + " " + CTW.SMALL.getNameInSpanish());
        put("curios.identifier.metalmind_slot", CTW.SLOT.getNameInSpanish() + " " + CTW.METALMIND);

        put("itemGroup.metallics_arts", CTW.METALLICS_ARTS.getNameInSpanish());
        put("itemGroup.metallics_arts.decorations", CTW.METALLICS_ARTS.getNameInSpanish() + " " + CTW.DECORATIONS.getNameInSpanish());

        put("key.category_powers_metallics_arts", CTW.METALLICS_ARTS.getNameInSpanish() + ": " + CTW.POWERS.getNameInSpanish());
        put("key.category.metallics_arts", CTW.METALLICS_ARTS.getNameInSpanish());
        put("key.metallics_arts.allomantic", CTW.POWER_SELECTOR.getNameInSpanish() + " " + CTW.ALLOMANTIC.getNameInSpanish());
        put("key.metallics_arts.feruchemic", CTW.POWER_SELECTOR.getNameInSpanish() + " " + CTW.FERUCHEMICAL.getNameInSpanish());
        put("key.metallics_arts.allomantic_push", CTW.PUSH.getNameInSpanish()+ " " + CTW.ALLOMANTIC.getNameInSpanish());
        put("key.metallics_arts.allomantic_pull", CTW.PULL.getNameInSpanish()+ " " + CTW.ALLOMANTIC.getNameInSpanish());
        put("key.metallics_arts.vertical_jump", CTW.PUSH.getNameInSpanish()+ " " + CTW.VERTICAL.getNameInSpanish());
        put("key.metallics_arts.feruchemic_decant", CTW.TAPPING.getNameInSpanish()+ " " + CTW.FERUCHEMICAL.getNameInSpanish());
        put("key.metallics_arts.feruchemic_store", CTW.STORAGE.getNameInSpanish()+ " " + CTW.FERUCHEMICAL.getNameInSpanish());
        put("key.metallics_arts.switch_overlay", CTW.SWITCH_OVERLAY.getNameInSpanish());

        put("metallics_arts.metal_mind_translate.has_reserve", CTW.HAS_RESERVE.getNameInSpanish());
        put("metallics_arts.metal_mind_translate.not_has_reserve", CTW.NOT_HAS_RESERVE.getNameInSpanish());
        put("metallics_arts.spike_feruchemic_power", CTW.STORAGE_POWER.getNameInSpanish() + ": " + CTW.FERUCHEMICAL.getNameInSpanish());
        put("metallics_arts.spike_allomantic_power", CTW.STORAGE_POWER.getNameInSpanish() + ": " + CTW.ALLOMANTIC.getNameInSpanish());

        //ver porque usa "spike"
        put("metallics_arts.metal_mind_translate.tapping_identity", CTW.TAPPING_IDENTITY.getNameInSpanish());

        //arreglar estos de aca abajo
        put("metallics_arts.metal_mind_translate.store_identity", CTW.STORE_IDENTITY.getNameInSpanish());
        put("metallics_arts.metal_mind_translate.off_power", CTW.POWER_OFF.getNameInSpanish());
        put("metallics_arts.metal_mind.owner", CTW.OWNER.getNameInSpanish());
        put("metallics_arts.metal_mind.nobody", CTW.NOBODY.getNameInSpanish());
        put("metallics_arts.metal_mind.owner_someone", CTW.OWNER_SOMEONE.getNameInSpanish());
        put("metallics_arts.metal_mind_translate.uses", CTW.USES.getNameInSpanish());
        put("metallics_arts.metal_mind_translate.shift_info",CTW.SHIFT_TO_MORE_INFO.getNameInSpanish());

        put("item.metallics_arts.obsidian_dagger", CTW.OBSIDIAN_DAGGER.getNameInSpanish());
        put("item.metallics_arts.cristal_dagger", CTW.SILVER_KNIFE.getNameInSpanish());
        put("item.metallics_arts.koloss_blade", CTW.KOLOSS_BLADE.getNameInSpanish());
        put("item.metallics_arts.dueling_staff", CTW.DUELING_STAFF.getNameInSpanish());
        put("item.metallics_arts.obsidian_axe", CTW.OBSIDIAN_AXE.getNameInSpanish());

        put("item.metallics_arts.core_obsidian", CTW.OBSIDIAN_CORE.getNameInSpanish());
        put("item.metallics_arts.core_steel", CTW.STEEL_CORE.getNameInSpanish());
        put("item.metallics_arts.core_aluminum", CTW.ALUMINUM_CORE.getNameInSpanish());
        put("item.metallics_arts.steel_helmet", CTW.STEEL_HELMET.getNameInSpanish());
        put("item.metallics_arts.steel_leggings", CTW.STEEL_LEGGINGS.getNameInSpanish());
        put("item.metallics_arts.steel_chestplate", CTW.STEEL_CHESTPLATE.getNameInSpanish());
        put("item.metallics_arts.steel_boots", CTW.STEEL_BOOTS.getNameInSpanish());
        put("item.metallics_arts.aluminum_helmet", CTW.ALUMINUM_HELMET.getNameInSpanish());
        put("item.metallics_arts.aluminum_leggings", CTW.ALUMINUM_LEGGINGS.getNameInSpanish());
        put("item.metallics_arts.aluminum_chestplate", CTW.ALUMINUM_CHESTPLATE.getNameInSpanish());
        put("item.metallics_arts.aluminum_boots", CTW.ALUMINUM_BOOTS.getNameInSpanish());
        put("metallics_arts.item.tooltip.mistcloak", "Aumenta ligeramente tu velocidad");
        put("item.metallics_arts.wood_shield",CTW.SHIELD.getNameInSpanish() + " de " + CTW.WOOD.getNameInSpanish().toLowerCase());
        put("item.metallics_arts.bronze_aluminum_shield", CTW.SHIELD.getNameInSpanish() + " de " + MetalNamesEnum.ALUMINUM.getNameInSpanish().toLowerCase());

    }};

    private final Map<String, String> tables = new HashMap<>() {{
        put("block.metallics_arts.menu.crucible_furnace", "Horno de crisol");
        put("block.metallics_arts.crucible_furnace", "Horno de crisol");

    }};

    private final Map<String, String> ingots = new HashMap<>() {{
        for (MetalAuxiliaryInfo metal: MetalAuxiliaryInfo.values()) {

            if (!metal.isVanilla()) {
                put("item.metallics_arts." + metal.getId() + "_ingot", CTW.INGOT.getNameInSpanish() + " " + MetalNamesEnum.valueOf(metal.name()).getNameInLowerSpanish());
            }

        }
    }};

    private final Map<String, String> stairs = new HashMap<>() {{
        for (MetalEnum metal : MetalEnum.values()) {
            put("block.metallics_arts." + metal.getMetalNameLower() + "_stairs", CTW.STAIRS.getNameInSpanish() + " de " + MetalNamesEnum.valueOf(metal.name()).getNameInSpanish().toLowerCase());
            if (!metal.isAlloy()) {
                put("block.metallics_arts." + metal.getMetalNameLower() + "_raw_stairs", CTW.STAIRS.getNameInSpanish() + " de " + MetalNamesEnum.valueOf(metal.name()).getNameInSpanish().toLowerCase() + " " + CTW.RAW.getNameInSpanish());
            }
        }
        for (GemsEnum metal : GemsEnum.values()) {
            put("block.metallics_arts." + metal.getGemNameLower() + "_stairs", CTW.STAIRS.getNameInSpanish() + " de " + MetalNamesEnum.valueOf(metal.name()).getNameInSpanish().toLowerCase());
        }
        put("block.metallics_arts.iron_stairs",CTW.STAIRS.getNameInSpanish() + " de " + MetalNamesEnum.IRON.getNameInSpanish().toLowerCase());
        put("block.metallics_arts.gold_stairs",CTW.STAIRS.getNameInSpanish() + " de " + MetalNamesEnum.GOLD.getNameInSpanish().toLowerCase());
        put("block.metallics_arts.iron_raw_stairs",CTW.STAIRS.getNameInSpanish() + " de " + MetalNamesEnum.IRON.getNameInSpanish().toLowerCase() + " " + CTW.RAW.getNameInSpanish());
        put("block.metallics_arts.gold_raw_stairs",CTW.STAIRS.getNameInSpanish() + " de " + MetalNamesEnum.GOLD.getNameInSpanish().toLowerCase() + " " + CTW.RAW.getNameInSpanish());
        put("block.metallics_arts.copper_raw_stairs",CTW.STAIRS.getNameInSpanish() + " de " + MetalNamesEnum.COPPER.getNameInSpanish().toLowerCase() + " " + CTW.RAW.getNameInSpanish());
    }};

    private final Map<String, String> slabs = new HashMap<>() {{
        for (MetalEnum metal : MetalEnum.values()) {
            put("block.metallics_arts." + metal.getMetalNameLower() + "_slabs", CTW.SLABS.getNameInSpanish() + " de " + MetalNamesEnum.valueOf(metal.name()).getNameInSpanish().toLowerCase());
            if (!metal.isAlloy()) {
                put("block.metallics_arts." + metal.getMetalNameLower() + "_raw_slabs", CTW.SLABS.getNameInSpanish() + " de " + MetalNamesEnum.valueOf(metal.name()).getNameInSpanish().toLowerCase() + " " + CTW.RAW.getNameInSpanish());
            }
        }
        for (GemsEnum metal : GemsEnum.values()) {
            put("block.metallics_arts." + metal.getGemNameLower() + "_slabs", CTW.SLABS.getNameInSpanish() + " de " + MetalNamesEnum.valueOf(metal.name()).getNameInSpanish().toLowerCase());
        }
        put("block.metallics_arts.iron_slabs",CTW.SLABS.getNameInSpanish() + " de " + MetalNamesEnum.IRON.getNameInSpanish().toLowerCase());
        put("block.metallics_arts.gold_slabs",CTW.SLABS.getNameInSpanish() + " de " + MetalNamesEnum.GOLD.getNameInSpanish().toLowerCase());
        put("block.metallics_arts.iron_raw_slabs",CTW.SLABS.getNameInSpanish() + " de " + MetalNamesEnum.IRON.getNameInSpanish().toLowerCase() + " " + CTW.RAW.getNameInSpanish());
        put("block.metallics_arts.gold_raw_slabs",CTW.SLABS.getNameInSpanish() + " de " + MetalNamesEnum.GOLD.getNameInSpanish().toLowerCase() + " " + CTW.RAW.getNameInSpanish());
        put("block.metallics_arts.copper_raw_slabs",CTW.SLABS.getNameInSpanish() + " de " + MetalNamesEnum.COPPER.getNameInSpanish().toLowerCase() + " " + CTW.RAW.getNameInSpanish());

    }};
    private final Map<String, String> walls = new HashMap<>() {{
        for (MetalEnum metal : MetalEnum.values()) {
            put("block.metallics_arts." + metal.getMetalNameLower() + "_walls", CTW.WALLS.getNameInSpanish()  + " de " +MetalNamesEnum.valueOf(metal.name()).getNameInSpanish().toLowerCase());
            if (!metal.isAlloy()) {
                put("block.metallics_arts." + metal.getMetalNameLower() + "_raw_walls", CTW.WALLS.getNameInSpanish()  + " de " +MetalNamesEnum.valueOf(metal.name()).getNameInSpanish().toLowerCase()+ " " + CTW.RAW.getNameInSpanish().toLowerCase().toLowerCase());
            }
        }
        for (GemsEnum metal : GemsEnum.values()) {
            put("block.metallics_arts." + metal.getGemNameLower() + "_walls", CTW.WALLS.getNameInSpanish()  + " de " +MetalNamesEnum.valueOf(metal.name()).getNameInSpanish().toLowerCase());
        }
        put("block.metallics_arts.gold_walls", CTW.WALLS.getNameInSpanish()  + " de " +MetalNamesEnum.GOLD.getNameInSpanish().toLowerCase());
        put("block.metallics_arts.iron_walls", CTW.WALLS.getNameInSpanish()  + " de " +MetalNamesEnum.IRON.getNameInSpanish().toLowerCase());
        put("block.metallics_arts.copper_walls", CTW.WALLS.getNameInSpanish()  + " de " +MetalNamesEnum.COPPER.getNameInSpanish().toLowerCase());
        put("block.metallics_arts.iron_raw_walls",CTW.WALLS.getNameInSpanish()  + " de " +MetalNamesEnum.IRON.getNameInSpanish() + " " + CTW.RAW.getNameInSpanish().toLowerCase());
        put("block.metallics_arts.gold_raw_walls",CTW.WALLS.getNameInSpanish()  + " de " +MetalNamesEnum.GOLD.getNameInSpanish() + " " + CTW.RAW.getNameInSpanish().toLowerCase());
        put("block.metallics_arts.copper_raw_walls",CTW.WALLS.getNameInSpanish()  + " de " +MetalNamesEnum.COPPER.getNameInSpanish() + " " + CTW.RAW.getNameInSpanish().toLowerCase());
    }};

    private final Map<String, String> fences = new HashMap<>() {{
        for (MetalEnum metal : MetalEnum.values()) {
            put("block.metallics_arts." + metal.getMetalNameLower() + "_fences", CTW.FENCES.getNameInSpanish()  + " de " +MetalNamesEnum.valueOf(metal.name()).getNameInSpanish().toLowerCase());
            if (!metal.isAlloy()) {
                put("block.metallics_arts." + metal.getMetalNameLower() + "_raw_fences", CTW.FENCES.getNameInSpanish()  + " de " +MetalNamesEnum.valueOf(metal.name()).getNameInSpanish().toLowerCase()+ " " + CTW.RAW.getNameInSpanish().toLowerCase().toLowerCase());
            }
        }
        for (GemsEnum metal : GemsEnum.values()) {
            put("block.metallics_arts." + metal.getGemNameLower() + "_fences", CTW.FENCES.getNameInSpanish()  + " de " +MetalNamesEnum.valueOf(metal.name()).getNameInSpanish().toLowerCase());
        }
        put("block.metallics_arts.gold_fences", CTW.FENCES.getNameInSpanish()  + " de " +MetalNamesEnum.GOLD.getNameInSpanish().toLowerCase());
        put("block.metallics_arts.iron_fences", CTW.FENCES.getNameInSpanish()  + " de " +MetalNamesEnum.IRON.getNameInSpanish().toLowerCase());
        put("block.metallics_arts.copper_fences", CTW.FENCES.getNameInSpanish()  + " de " +MetalNamesEnum.COPPER.getNameInSpanish().toLowerCase());
        put("block.metallics_arts.iron_raw_fences",CTW.FENCES.getNameInSpanish()  + " de " +MetalNamesEnum.IRON.getNameInSpanish().toLowerCase() + " " + CTW.RAW.getNameInSpanish().toLowerCase().toLowerCase());
        put("block.metallics_arts.gold_raw_fences",CTW.FENCES.getNameInSpanish()  + " de " +MetalNamesEnum.GOLD.getNameInSpanish().toLowerCase() + " " + CTW.RAW.getNameInSpanish().toLowerCase().toLowerCase());
        put("block.metallics_arts.copper_raw_fences",CTW.FENCES.getNameInSpanish()  + " de " +MetalNamesEnum.COPPER.getNameInSpanish().toLowerCase() + " " + CTW.RAW.getNameInSpanish().toLowerCase().toLowerCase());
    }};

    private final Map<String, String> fence_gates = new HashMap<>() {{
        for (MetalEnum metal : MetalEnum.values()) {
            put("block.metallics_arts." + metal.getMetalNameLower() + "_fence_gates", CTW.FENCE_GATES.getNameInSpanish()  + " de " +MetalNamesEnum.valueOf(metal.name()).getNameInSpanish().toLowerCase());
            if (!metal.isAlloy()) {
                put("block.metallics_arts." + metal.getMetalNameLower() + "_raw_fence_gates", CTW.FENCE_GATES.getNameInSpanish()  + " de " +MetalNamesEnum.valueOf(metal.name()).getNameInSpanish().toLowerCase() + " " + CTW.RAW.getNameInSpanish().toLowerCase().toLowerCase());
            }
        }
        for (GemsEnum metal : GemsEnum.values()) {
            put("block.metallics_arts." + metal.getGemNameLower() + "_fence_gates", CTW.FENCE_GATES.getNameInSpanish()  + " de " +MetalNamesEnum.valueOf(metal.name()).getNameInSpanish().toLowerCase());
        }
        put("block.metallics_arts.gold_fence_gates", CTW.FENCE_GATES.getNameInSpanish()  + " de " +MetalNamesEnum.GOLD.getNameInSpanish().toLowerCase());
        put("block.metallics_arts.iron_fence_gates", CTW.FENCE_GATES.getNameInSpanish()  + " de " +MetalNamesEnum.IRON.getNameInSpanish().toLowerCase());
        put("block.metallics_arts.copper_fence_gates", CTW.FENCE_GATES.getNameInSpanish()  + " de " +MetalNamesEnum.COPPER.getNameInSpanish().toLowerCase());
        put("block.metallics_arts.iron_raw_fence_gates",CTW.FENCE_GATES.getNameInSpanish()  + " de " +MetalNamesEnum.IRON.getNameInSpanish().toLowerCase() + " " + CTW.RAW.getNameInSpanish().toLowerCase());
        put("block.metallics_arts.gold_raw_fence_gates",CTW.FENCE_GATES.getNameInSpanish()  + " de " +MetalNamesEnum.GOLD.getNameInSpanish().toLowerCase() + " " + CTW.RAW.getNameInSpanish().toLowerCase());
        put("block.metallics_arts.copper_raw_fence_gates",CTW.FENCE_GATES.getNameInSpanish()  + " de " +MetalNamesEnum.COPPER.getNameInSpanish().toLowerCase() + " " + CTW.RAW.getNameInSpanish().toLowerCase());
    }};

    private final Map<String, String> effects = new HashMap<>() {{
        for (MetalAuxiliaryInfo metal: MetalAuxiliaryInfo.values()) {
            if (!metal.isOnlyForAlloys()) {
                put("effect."+ MetallicsArts.MOD_ID + ".allomantic_" + metal.getId(),   MetalNamesEnum.valueOf(metal.name()).getNameInSpanish() + " " + CTW.ALLOMANTIC.getNameInSpanish());
                put("effect."+ MetallicsArts.MOD_ID + ".feruchemical_" + metal.getId() + "_tap",   MetalNamesEnum.valueOf(metal.name()).getNameInSpanish() + " " + CTW.FERUCHEMICAL.getNameInSpanish());
                put("effect."+ MetallicsArts.MOD_ID + ".feruchemical_" + metal.getId() + "_storage",   MetalNamesEnum.valueOf(metal.name()).getNameInSpanish() + " " + CTW.FERUCHEMICAL.getNameInSpanish());
            }
        }

    }};
    private final Map<String, String> rawItems = new HashMap<>() {{
        for (MetalAuxiliaryInfo metal: MetalAuxiliaryInfo.values()) {
            if (!metal.isVanilla()) {
                put("item.metallics_arts.raw_" + metal.getId(), MetalNamesEnum.valueOf(metal.name()).getNameInSpanish() + " " + CTW.RAW.getNameInSpanish());
            }
        }
    }};
    private final Map<String, String> gems = new HashMap<>() {{
        for (MetalAuxiliaryInfo metal: MetalAuxiliaryInfo.values()) {
            if (metal.isDivine()) {
                put("item.metallics_arts." + metal.getId(), CTW.GEM.getNameInSpanish() + " " + MetalNamesEnum.valueOf(metal.name()).getNameInLowerSpanish());
            }
        }
    }};
    private final Map<String, String> nuggets = new HashMap<>() {{
        for (MetalAuxiliaryInfo metal: MetalAuxiliaryInfo.values()) {
            if (!metal.isVanilla()) {
                put("item.metallics_arts."+ metal.getId()+"_nugget", CTW.NUGGET.getNameInSpanish() + " " + MetalNamesEnum.valueOf(metal.name()).getNameInLowerSpanish());
            }
            if (metal.isDivine()) {
                put("item.metallics_arts." + metal.getId() + "_nugget", CTW.NUGGET.getNameInSpanish() + " " + MetalNamesEnum.valueOf(metal.name()).getNameInLowerSpanish());
            }
        }
        put("item.metallics_arts.copper_nugget", CTW.NUGGET.getNameInSpanish() + " " + MetalNamesEnum.COPPER.getNameInLowerSpanish());
    }};
    private final Map<String, String> blocks = new HashMap<>() {{
        for (MetalAuxiliaryInfo metal: MetalAuxiliaryInfo.values()) {
            if (!metal.isVanilla()) {
                put("block.metallics_arts." + metal.getId() + "_block",
                        CTW.BLOCK.getNameInSpanish()+ " " + MetalNamesEnum.valueOf(metal.name()).getNameInLowerSpanish());
            }
            if (metal.isDivine()) {
                put("block.metallics_arts." + metal.getId() + "_block", CTW.BLOCK.getNameInSpanish()+ " " + MetalNamesEnum.valueOf(metal.name()).getNameInLowerSpanish());
            }
        }
    }};
    private final Map<String, String> rawBlocks = new HashMap<>() {{
        for (MetalAuxiliaryInfo metal: MetalAuxiliaryInfo.values()) {
            if (!metal.isVanilla()) {
                put("block.metallics_arts.raw_"+metal.getId()+"_block", CTW.BLOCK.getNameInSpanish()+ " " +
                        MetalNamesEnum.valueOf(metal.name()).getNameInLowerSpanish() + " " + CTW.RAW.getNameInSpanish());
            }
        }
    }};
    private final Map<String, String> ores = new HashMap<>() {{
        for (MetalAuxiliaryInfo metal: MetalAuxiliaryInfo.values()) {
            if (!metal.isVanilla() && !metal.isAlloy() && metal.isAppearsInStone()) {
                    put("block.metallics_arts." + metal.getId()+"_ore", CTW.ORE.getNameInSpanish() + " " + MetalNamesEnum.valueOf(metal.name()).getNameInLowerSpanish());

            }
        }

    }};
    private final Map<String, String> deepslateOres = new HashMap<>() {{
        for (MetalAuxiliaryInfo metal: MetalAuxiliaryInfo.values()) {
            if (!metal.isVanilla() && !metal.isAlloy() && metal.isAppearsInDeepslate()) {
                    put("block.metallics_arts.deepslate_" + metal.getId()+"_ore", CTW.ORE.getNameInSpanish() + " " +
                            MetalNamesEnum.valueOf(metal.name()).getNameInLowerSpanish() + " " + CTW.DEEPSLATE.getNameInSpanish());
            }
        }
    }};
    private final Map<String, String> geodeBlocks = new HashMap<>() {{
        for (MetalAuxiliaryInfo metal: MetalAuxiliaryInfo.values()) {
            if (metal.isDivine() && !metal.isAlloy()) {
                put("block.metallics_arts." + metal.getId() + "_cristal_block", MetalNamesEnum.valueOf(metal.name()).getNameInSpanish() + " " + CTW.CRISTAL.getNameInSpanish());
                put("block.metallics_arts.budding_" + metal.getId(), CTW.BUDDING.getNameInSpanish() + " " + MetalNamesEnum.valueOf(metal.name()).getNameInLowerSpanish());
                put("block.metallics_arts." + metal.getId() + "_cluster", CTW.CLUSTER.getNameInSpanish() + " " + MetalNamesEnum.valueOf(metal.name()).getNameInLowerSpanish());
                put("block.metallics_arts.small_" + metal.getId() + "_bud", CTW.BUD.getNameInSpanish() + " " + MetalNamesEnum.valueOf(metal.name()).getNameInLowerSpanish() + " " +
                        CTW.SMALL.getNameInSpanish());
                put("block.metallics_arts.medium_" + metal.getId() + "_bud", CTW.BUD.getNameInSpanish() + " " + MetalNamesEnum.valueOf(metal.name()).getNameInLowerSpanish() + " " +
                        CTW.MEDIUM.getNameInSpanish());
                put("block.metallics_arts.large_" + metal.getId() + "_bud", CTW.BUD.getNameInSpanish() + " " + MetalNamesEnum.valueOf(metal.name()).getNameInLowerSpanish() + " " +
                        CTW.LARGE.getNameInSpanish());
            }
        }
    }};
    private final Map<String, String> spikes = new HashMap<>() {{
        for (MetalAuxiliaryInfo metal: MetalAuxiliaryInfo.values()) {
            if (!metal.isOnlyForAlloys()) {
                put("item.metallics_arts."+ metal.getId() +"_spike", CTW.SPIKE.getNameInSpanish() + " " + MetalNamesEnum.valueOf(metal.name()).getNameInLowerSpanish());
            }
        }
    }};
    private final Map<String, String> metalMinds = new HashMap<>() {{
        for (MetalMinds metalMind : MetalMinds.values()) {
            put("item.metallics_arts.band_" + metalMind.getId(), CTW.BAND.getNameInSpanish() + " " + metalMind.getNameInSpanish());
            put("item.metallics_arts.ring_" + metalMind.getId(), CTW.RING.getNameInSpanish() + " " + metalMind.getNameInSpanish());
        }
    }};
    private final Map<String, String> icons = new HashMap<>() {{
        for (MetalAuxiliaryInfo metal: MetalAuxiliaryInfo.values()) {
            if (!metal.isOnlyForAlloys()) {
                put("item.metallics_arts."+ metal.getId()+"_allomantic_icon" , MetalNamesEnum.valueOf(metal.name()).getNameInSpanish() + " " + CTW.ALLOMANTIC.getNameInSpanish());
                put("item.metallics_arts."+ metal.getId()+"_feruchemic_icon" , MetalNamesEnum.valueOf(metal.name()).getNameInSpanish() + " " + CTW.FERUCHEMICAL.getNameInSpanish());
            }
        }
    }};
    private final Map<String, String> metals = new HashMap<>() {{
        for (MetalAuxiliaryInfo metal: MetalAuxiliaryInfo.values()) {
            if (!metal.isOnlyForAlloys()) {
                put("metallics_arts.metal_translate."+ metal.getId() , MetalNamesEnum.valueOf(metal.name()).getNameInSpanish());
            }
        }
    }};
    private final Map<String, String> powers = new HashMap<>() {{
        for (MetalAuxiliaryInfo metal: MetalAuxiliaryInfo.values()) {
            if (!metal.isOnlyForAlloys()) {
                put("key.metallics_arts."+ metal.getId() +"_power", MetalNamesEnum.valueOf(metal.name()).getNameInSpanish());
            }
        }
    }};
    private final Map<String, String> symbols = new HashMap<>() {{
        for (MetalAuxiliaryInfo metal: MetalAuxiliaryInfo.values()) {
            if (!metal.isOnlyForAlloys()) {
                put("f_"+metal.getId()+"_1", MetalNamesEnum.valueOf(metal.name()).getNameInSpanish() + " " + CTW.FERUCHEMICAL_SHADING.getNameInSpanish());
                put("f_"+metal.getId()+"_2", MetalNamesEnum.valueOf(metal.name()).getNameInSpanish() + " " + CTW.FERUCHEMICAL_SOLID.getNameInSpanish());
                put("a_"+metal.getId()+"_1", MetalNamesEnum.valueOf(metal.name()).getNameInSpanish() + " " + CTW.ALLOMANTIC_SHADING.getNameInSpanish());
                put("a_"+metal.getId()+"_2", MetalNamesEnum.valueOf(metal.name()).getNameInSpanish() + " " + CTW.ALLOMANTIC_SOLID.getNameInSpanish());
            }
        }
    }};
    private final Map<String, String> patterns = new HashMap<>() {{
        for (MetalAuxiliaryInfo metal: MetalAuxiliaryInfo.values()) {
            if (!metal.isOnlyForAlloys()) {
                put("item.metallics_arts.f_"+metal.getId()+"_pattern", CTW.FERUCHEMICAL_PATTERN.getNameInSpanish() + " " + MetalNamesEnum.valueOf(metal.name()).getNameInLowerSpanish());
                put("item.metallics_arts.f_"+metal.getId()+"_pattern.desc", CTW.FERUCHEMICAL_PATTERN.getNameInSpanish() + " " + MetalNamesEnum.valueOf(metal.name()).getNameInLowerSpanish());
                put("item.metallics_arts.a_"+metal.getId()+"_pattern", CTW.ALLOMANTIC_PATTERN.getNameInSpanish() + " " + MetalNamesEnum.valueOf(metal.name()).getNameInLowerSpanish());
                put("item.metallics_arts.a_"+metal.getId()+"_pattern.desc", CTW.ALLOMANTIC_PATTERN.getNameInSpanish() + " " + MetalNamesEnum.valueOf(metal.name()).getNameInLowerSpanish());
            }
        }

    }};
    private final Map<String, String> colors = new HashMap<>() {{
        for (ColorsNames color : ColorsNames.values()) {
            put(color.getId(),color.getNameInSpanish());
        }
    }};

    /**
     * Constructs a new ModLanguageProviderES instance.
     *
     * @param gen               the data generator for language files
     *
     * @param locale            the locale for the target language
     */
    public ModLanguageProviderES(DataGenerator gen, String locale) {
        super(gen.getPackOutput(), MetallicsArts.MOD_ID, locale);
    }

    /**
     * Adds the Spanish translations for mod elements.
     */
    @Override
    protected void addTranslations() {

        for (String key: ingots.keySet()) {
            add(key, ingots.get(key));
        }

        for (String key: gems.keySet()) {
            add(key, gems.get(key));
        }

        for (String key: rawBlocks.keySet()) {
            add(key, rawBlocks.get(key));
        }

        for (String key: metalMinds.keySet()) {
            add(key, metalMinds.get(key));
        }

        for (String key: rawItems.keySet()) {
            add(key, rawItems.get(key));
        }

        for (String key: geodeBlocks.keySet()) {
            add(key, geodeBlocks.get(key));
        }

        for (String key: ores.keySet()) {
            add(key, ores.get(key));
        }
        for (String key: deepslateOres.keySet()) {
            add(key, deepslateOres.get(key));
        }

        for (String key: nuggets.keySet()) {
            add(key, nuggets.get(key));
        }

        for (String key: blocks.keySet()) {
            add(key, blocks.get(key));
        }

        for (String key: spikes.keySet()) {
            add(key, spikes.get(key));
        }

        for (String key: icons.keySet()) {
            add(key, icons.get(key));
        }

        for (String key: metals.keySet()) {
            add(key, metals.get(key));
        }

        for (String key: powers.keySet()) {
            add(key, powers.get(key));
        }

        for(String key: base.keySet()) {
            add(key, base.get(key));
        }

        for(String key: patterns.keySet()) {
            add(key, patterns.get(key));
        }
        for(String key: effects.keySet()) {
            add(key, effects.get(key));
        }
        for(String key: stairs.keySet()) {
            add(key, stairs.get(key));
        }
        for(String key: slabs.keySet()) {
            add(key, slabs.get(key));
        }
        for (String key: walls.keySet()) {
            add(key, walls.get(key));
        }
        for (String key: fences.keySet()) {
            add(key, fences.get(key));
        }
        for (String key: fence_gates.keySet()) {
            add(key, fence_gates.get(key));
        }

        for (String keySymbol : symbols.keySet()) {
            for (String keyColor : colors.keySet()) {
                add("block.minecraft.banner.metallics_arts." + keySymbol + "." + keyColor, symbols.get(keySymbol) + " " + colors.get(keyColor));
            }
        }

        HashMap<String, String> bookTraslationsHelper = BookHelperES.addDemoBook();

        for (String key : bookTraslationsHelper.keySet()) {
            add(key,bookTraslationsHelper.get(key));
        }

        for (String key : tables.keySet()) {
            add(key, tables.get(key));
        }
    }
}
