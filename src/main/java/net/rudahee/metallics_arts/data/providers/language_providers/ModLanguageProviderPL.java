package net.rudahee.metallics_arts.data.providers.language_providers;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.data.enums.implementations.GemsEnum;
import net.rudahee.metallics_arts.data.enums.implementations.GunType;
import net.rudahee.metallics_arts.data.enums.implementations.MetalEnum;
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

        //put("item.metallics_arts.metallics_arts_book", CTW.METALLICS_ARTS.getNameInEnglish() + " " + CTW.GUIDE.getNameInEnglish());
        put("entity.minecraft.villager.metallics_arts.forge_master", "MAESTRO DE LA FORGA");

        put("item.metallics_arts.large_vial", CTW.VIAL.getNameInPolish() + " " + CTW.LARGE.getNameInPolish());
        put("item.metallics_arts.small_vial", CTW.VIAL.getNameInPolish() + " " + CTW.SMALL.getNameInPolish());
        put("curios.identifier.metalmind_slot", CTW.SLOT.getNameInPolish() + " " + CTW.METALMIND);

        put("metallics_arts.tab.decorations", CTW.METALLICS_ARTS.getNameInPolish() + " " + CTW.DECORATIONS.getNameInPolish());

        put("item.metallics_arts.wood_shield", CTW.WOOD.getNameInEnglish() + " "+ CTW.SHIELD.getNameInEnglish());
        put("item.metallics_arts.bronze_aluminum_shield", MetalNamesEnum.ALUMINUM.getNameInEnglish() + " " + CTW.SHIELD.getNameInEnglish());
    }};

    private final Map<String, String> signs = new HashMap<>() {{
        put("block.metallics_arts.iron_standing_sign",MetalNamesEnum.IRON.getNameInLowerEnglish()  + " " + CTW.SIGN.getNameInEnglish());
        put("block.metallics_arts.gold_standing_sign", MetalNamesEnum.GOLD.getNameInLowerEnglish() + " " + CTW.SIGN.getNameInEnglish());
        put("block.metallics_arts.copper_standing_sign", MetalNamesEnum.COPPER.getNameInLowerEnglish() + " " + CTW.SIGN.getNameInEnglish());
        put("block.metallics_arts.aluminum_standing_sign", MetalNamesEnum.ALUMINUM.getNameInLowerEnglish() + " " + CTW.SIGN.getNameInEnglish());
    }};
    private final Map<String, String> redstone = new HashMap<>() {{
        put("block.metallics_arts.allomantic_push_button", CTW.ALLOMANTIC.getNameInEnglish() + " " + CTW.PUSH.getNameInEnglish() + " " + CTW.BUTTON.getNameInEnglish());
        put("block.metallics_arts.allomantic_pull_button", CTW.ALLOMANTIC.getNameInEnglish() + " " + CTW.PULL.getNameInEnglish() + " " + CTW.BUTTON.getNameInEnglish());
        put("block.metallics_arts.allomantic_lever", CTW.ALLOMANTIC.getNameInEnglish() + " " + CTW.LEVER.getNameInEnglish());
    }};
    private final Map<String, String> metalMindsData = new HashMap<>() {{
        put("metallics_arts.metal_mind_translate.has_reserve", CTW.HAS_RESERVE.getNameInPolish());
        put("metallics_arts.metal_mind_translate.not_has_reserve", CTW.NOT_HAS_RESERVE.getNameInPolish());
        put("metallics_arts.spike_feruchemic_power", CTW.STORAGE_POWER.getNameInPolish() + ": " + CTW.FERUCHEMICAL.getNameInPolish());
        put("metallics_arts.spike_allomantic_power", CTW.STORAGE_POWER.getNameInPolish() + ": " + CTW.ALLOMANTIC.getNameInPolish());
        put("metallics_arts.metal_mind_translate.tapping_identity", CTW.TAPPING_IDENTITY.getNameInPolish());
        put("metallics_arts.metal_mind_translate.store_identity", CTW.STORE_IDENTITY.getNameInPolish());
        put("metallics_arts.metal_mind_translate.off_power", CTW.POWER_OFF.getNameInPolish());
        put("metallics_arts.metal_mind.owner", CTW.OWNER.getNameInPolish());
        put("metallics_arts.metal_mind.nobody", CTW.NOBODY.getNameInPolish());
        put("metallics_arts.metal_mind.owner_someone", CTW.OWNER_SOMEONE.getNameInPolish());
        put("metallics_arts.metal_mind_translate.uses", CTW.USES.getNameInPolish());
        put("metallics_arts.metal_mind_translate.shift_info",CTW.SHIFT_TO_MORE_INFO.getNameInPolish());
    }};

    private final Map<String, String> buttonConfigs = new HashMap<>() {{
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
        put("key.metallics_arts.change_ammo", CTW.CHANGE_AMMO.getNameInEnglish());
        put("key.metallics_arts.reload", CTW.RELOAD.getNameInEnglish());
    }};

    private final Map<String, String> cores = new HashMap<>() {{
        put("item.metallics_arts.core_obsidian", CTW.OBSIDIAN_CORE.getNameInEnglish());
        put("item.metallics_arts.core_steel", CTW.STEEL_CORE.getNameInEnglish());
        put("item.metallics_arts.core_aluminum", CTW.ALUMINUM_CORE.getNameInEnglish());
        put("item.metallics_arts.core_atium", CTW.ATIUM_CORE.getNameInEnglish());
        put("item.metallics_arts.core_lerasium", CTW.LERASIUM_CORE.getNameInEnglish());
        put("item.metallics_arts.core_ettmetal", CTW.ETTMETAL_CORE.getNameInEnglish());
    }};

    private final Map<String, String> armors = new HashMap<>() {{
        put("item.metallics_arts.steel_helmet", MetalNamesEnum.STEEL.getNameInEnglish() + " " + CTW.HELMET.getNameInEnglish());
        put("item.metallics_arts.steel_leggings", MetalNamesEnum.STEEL.getNameInEnglish() + " " + CTW.LEGGINGS.getNameInEnglish());
        put("item.metallics_arts.steel_chestplate", MetalNamesEnum.STEEL.getNameInEnglish() + " " + CTW.CHESTPLATE.getNameInEnglish());
        put("item.metallics_arts.steel_boots", MetalNamesEnum.STEEL.getNameInEnglish() + " " + CTW.BOOTS.getNameInEnglish());

        put("item.metallics_arts.aluminum_helmet", MetalNamesEnum.ALUMINUM.getNameInEnglish() + " " + CTW.HELMET.getNameInEnglish());
        put("item.metallics_arts.aluminum_leggings", MetalNamesEnum.ALUMINUM.getNameInEnglish() + " " + CTW.LEGGINGS.getNameInEnglish());
        put("item.metallics_arts.aluminum_chestplate", MetalNamesEnum.ALUMINUM.getNameInEnglish() + " " + CTW.CHESTPLATE.getNameInEnglish());
        put("item.metallics_arts.aluminum_boots", MetalNamesEnum.ALUMINUM.getNameInEnglish() + " " + CTW.BOOTS.getNameInEnglish());

        put("item.metallics_arts.atium_helmet", MetalNamesEnum.ATIUM.getNameInEnglish() + " " + CTW.HELMET.getNameInEnglish());
        put("item.metallics_arts.atium_leggings", MetalNamesEnum.ATIUM.getNameInEnglish() + " " + CTW.LEGGINGS.getNameInEnglish());
        put("item.metallics_arts.atium_chestplate", MetalNamesEnum.ATIUM.getNameInEnglish() + " " + CTW.CHESTPLATE.getNameInEnglish());
        put("item.metallics_arts.atium_boots", MetalNamesEnum.ATIUM.getNameInEnglish() + " " + CTW.BOOTS.getNameInEnglish());

        put("item.metallics_arts.lerasium_helmet", MetalNamesEnum.LERASIUM.getNameInEnglish() + " " + CTW.HELMET.getNameInEnglish());
        put("item.metallics_arts.lerasium_leggings", MetalNamesEnum.LERASIUM.getNameInEnglish() + " " + CTW.LEGGINGS.getNameInEnglish());
        put("item.metallics_arts.lerasium_chestplate", MetalNamesEnum.LERASIUM.getNameInEnglish() + " " + CTW.CHESTPLATE.getNameInEnglish());
        put("item.metallics_arts.lerasium_boots", MetalNamesEnum.LERASIUM.getNameInEnglish() + " " + CTW.BOOTS.getNameInEnglish());

        put("item.metallics_arts.ettmetal_helmet", MetalNamesEnum.ETTMETAL.getNameInEnglish() + " " + CTW.HELMET.getNameInEnglish());
        put("item.metallics_arts.ettmetal_leggings", MetalNamesEnum.ETTMETAL.getNameInEnglish() + " " + CTW.LEGGINGS.getNameInEnglish());
        put("item.metallics_arts.ettmetal_chestplate", MetalNamesEnum.ETTMETAL.getNameInEnglish() + " " + CTW.CHESTPLATE.getNameInEnglish());
        put("item.metallics_arts.ettmetal_boots", MetalNamesEnum.ETTMETAL.getNameInEnglish() + " " + CTW.BOOTS.getNameInEnglish());
    }};
    private final Map<String, String> paintings = new HashMap<>() {{
        put("painting.metallics_arts.inquisitor_painting.title", "Inquisitor Painting");
        put("painting.metallics_arts.inquisitor_painting.author", "-");

        put("painting.metallics_arts.fun_cobber_painting.title", "Fun Cobber Painting");
        put("painting.metallics_arts.fun_cobber_painting.author", "-");

        put("painting.metallics_arts.sanfre_painting.title", "Luthadel Pixel Painting");
        put("painting.metallics_arts.sanfre_painting.author", "Sanfre");

        put("painting.metallics_arts.gar_leyva_koloss_painting.title", "Koloss Painting");
        put("painting.metallics_arts.gar_leyva_koloss_painting.author", "Gar Leyva");

        put("painting.metallics_arts.gar_leyva_armonia_painting.title", "Harmony Painting");
        put("painting.metallics_arts.gar_leyva_armonia_painting.author", "Gar Leyva");

        put("painting.metallics_arts.carlos_wk_art_kelsier_painting.title", "Kelsier Painting");
        put("painting.metallics_arts.carlos_wk_art_kelsier_painting.author", "CarloswkArt");

        put("painting.metallics_arts.carlos_wk_art_ascension_painting.title", "Ascension Painting");
        put("painting.metallics_arts.carlos_wk_art_ascension_painting.author", "CarloswkArt");

        put("painting.metallics_arts.carlos_wk_art_windows_pose_painting.title", "Windows Pose Painting");
        put("painting.metallics_arts.carlos_wk_art_windows_pose_painting.author", "CarloswkArt");

        put("painting.metallics_arts.carlos_wk_art_luthadel_painting.title", "Luthadel Painting");
        put("painting.metallics_arts.carlos_wk_art_luthadel_painting.author", "CarloswkArt");

        put("painting.metallics_arts.carlos_wk_art_red_sun_painting.title", "Red Sun Painting");
        put("painting.metallics_arts.carlos_wk_art_red_sun_painting.author", "CarloswkArt");
    }};

    private final Map<String, String> ingots = new HashMap<>() {{
        for (MetalAuxiliaryInfo metal: MetalAuxiliaryInfo.values()) {

            if (!metal.isVanilla()) {
                put("item.metallics_arts." + metal.getId() + "_ingot", CTW.INGOT.getNameInPolish() + " " + MetalNamesEnum.valueOf(metal.name()).getNameInPolish());
            }

        }
    }};

    private final Map<String, String> gun_ammo_and_weapons = new HashMap<>() {{
        put("item.metallics_arts." + GunType.REVOLVER.getName(), CTW.REVOLVER.getNameInEnglish());
        put("item.metallics_arts." + GunType.VINDICATOR.getName(), CTW.VINDICATOR.getNameInEnglish());
        put("item.metallics_arts." + GunType.RIFLE.getName(), CTW.RIFLE.getNameInEnglish());
        put("item.metallics_arts." + GunType.SHOTGUN.getName(), CTW.SHOTGUN.getNameInEnglish());

        put("item.metallics_arts." + GunType.REVOLVER.getName() +"_aluminum_ammo", MetalNamesEnum.ALUMINUM.getNameInEnglish() + " " + CTW.REVOLVER.getNameInEnglish() + " " + CTW.AMMO.getNameInEnglish());
        put("item.metallics_arts." + GunType.REVOLVER.getName() +"_lead_ammo", MetalNamesEnum.LEAD.getNameInEnglish() + " " + CTW.REVOLVER.getNameInEnglish() + " " + CTW.AMMO.getNameInEnglish());

        put("item.metallics_arts." + GunType.RIFLE.getName() +"_aluminum_ammo", MetalNamesEnum.ALUMINUM.getNameInEnglish() + " " + CTW.RIFLE.getNameInEnglish() + " " + CTW.AMMO.getNameInEnglish());
        put("item.metallics_arts." + GunType.RIFLE.getName() +"_lead_ammo", MetalNamesEnum.LEAD.getNameInEnglish() + " " + CTW.RIFLE.getNameInEnglish() + " " + CTW.AMMO.getNameInEnglish());

        put("item.metallics_arts." + GunType.SHOTGUN.getName() +"_aluminum_ammo", MetalNamesEnum.ALUMINUM.getNameInEnglish() + " " + CTW.SHOTGUN.getNameInEnglish() + " " + CTW.AMMO.getNameInEnglish());
        put("item.metallics_arts." + GunType.SHOTGUN.getName() +"_lead_ammo", MetalNamesEnum.LEAD.getNameInEnglish() + " " + CTW.SHOTGUN.getNameInEnglish() + " " + CTW.AMMO.getNameInEnglish());

        put("item.metallics_arts.obsidian_dagger", CTW.OBSIDIAN_DAGGER.getNameInPolish());
        put("item.metallics_arts.cristal_dagger", CTW.SILVER_KNIFE.getNameInPolish());
        put("item.metallics_arts.koloss_blade", CTW.KOLOSS_BLADE.getNameInPolish());
        put("item.metallics_arts.dueling_staff", CTW.DUELING_STAFF.getNameInPolish());
        put("item.metallics_arts.obsidian_axe", CTW.OBSIDIAN_AXE.getNameInPolish());
    }};
    private final Map<String, String> stairs = new HashMap<>() {{
        for (MetalEnum metal : MetalEnum.values()) {
            put("block.metallics_arts." + metal.getMetalNameLower() + "_stairs", MetalNamesEnum.valueOf(metal.name()).getNameInEnglish() + " " + CTW.STAIRS.getNameInEnglish());
            if (!metal.isAlloy()) {
                put("block.metallics_arts." + metal.getMetalNameLower() + "_raw_stairs", MetalNamesEnum.valueOf(metal.name()).getNameInEnglish() + " " + CTW.STAIRS.getNameInEnglish());
            }
        }
        for (GemsEnum metal : GemsEnum.values()) {
            put("block.metallics_arts." + metal.getGemNameLower() + "_stairs", MetalNamesEnum.valueOf(metal.name()).getNameInEnglish() + " " + CTW.STAIRS.getNameInEnglish());
        }
        put("block.metallics_arts.iron_stairs", MetalNamesEnum.IRON.getNameInEnglish() + " " + CTW.STAIRS.getNameInEnglish());
        put("block.metallics_arts.gold_stairs", MetalNamesEnum.GOLD.getNameInEnglish() + " " + CTW.STAIRS.getNameInEnglish());
        put("block.metallics_arts.iron_raw_stairs",CTW.RAW.getNameInEnglish() + " " + MetalNamesEnum.IRON.getNameInEnglish() + " " + CTW.STAIRS.getNameInEnglish());
        put("block.metallics_arts.gold_raw_stairs",CTW.RAW.getNameInEnglish() + " " + MetalNamesEnum.GOLD.getNameInEnglish() + " " + CTW.STAIRS.getNameInEnglish());
        put("block.metallics_arts.copper_raw_stairs",CTW.RAW.getNameInEnglish() + " " + MetalNamesEnum.COPPER.getNameInEnglish() + " " + CTW.STAIRS.getNameInEnglish());
    }};

    private final Map<String, String> slabs = new HashMap<>() {{
        for (MetalEnum metal : MetalEnum.values()) {
            put("block.metallics_arts." + metal.getMetalNameLower() + "_slab", MetalNamesEnum.valueOf(metal.name()).getNameInEnglish() + " " + CTW.SLAB.getNameInEnglish());
            if (!metal.isAlloy()) {
                put("block.metallics_arts." + metal.getMetalNameLower() + "_raw_slab", MetalNamesEnum.valueOf(metal.name()).getNameInEnglish() + " " + CTW.SLAB.getNameInEnglish());
            }
        }
        for (GemsEnum metal : GemsEnum.values()) {
            put("block.metallics_arts." + metal.getGemNameLower() + "_slab", MetalNamesEnum.valueOf(metal.name()).getNameInEnglish() + " " + CTW.SLAB.getNameInEnglish());
        }
        put("block.metallics_arts.iron_slab", MetalNamesEnum.IRON.getNameInEnglish() + " " + CTW.SLAB.getNameInEnglish());
        put("block.metallics_arts.gold_slab", MetalNamesEnum.GOLD.getNameInEnglish() + " " + CTW.SLAB.getNameInEnglish());
        put("block.metallics_arts.iron_raw_slab",CTW.RAW.getNameInEnglish() + " " + MetalNamesEnum.IRON.getNameInEnglish() + " " + CTW.SLAB.getNameInEnglish());
        put("block.metallics_arts.gold_raw_slab",CTW.RAW.getNameInEnglish() + " " + MetalNamesEnum.GOLD.getNameInEnglish() + " " + CTW.SLAB.getNameInEnglish());
        put("block.metallics_arts.copper_raw_slab",CTW.RAW.getNameInEnglish() + " " + MetalNamesEnum.COPPER.getNameInEnglish() + " " + CTW.SLAB.getNameInEnglish());
    }};

    private final Map<String, String> walls = new HashMap<>() {{
        for (MetalEnum metal : MetalEnum.values()) {
            put("block.metallics_arts." + metal.getMetalNameLower() + "_wall", MetalNamesEnum.valueOf(metal.name()).getNameInEnglish() + " " + CTW.WALL.getNameInEnglish());
            if (!metal.isAlloy()) {
                put("block.metallics_arts." + metal.getMetalNameLower() + "_raw_wall", MetalNamesEnum.valueOf(metal.name()).getNameInEnglish() + " " + CTW.WALL.getNameInEnglish());
            }
        }
        for (GemsEnum metal : GemsEnum.values()) {
            put("block.metallics_arts." + metal.getGemNameLower() + "_wall", MetalNamesEnum.valueOf(metal.name()).getNameInEnglish() + " " + CTW.WALL.getNameInEnglish());
        }
        put("block.metallics_arts.iron_wall", MetalNamesEnum.IRON.getNameInEnglish() + " " + CTW.WALL.getNameInEnglish());
        put("block.metallics_arts.gold_wall", MetalNamesEnum.GOLD.getNameInEnglish() + " " + CTW.WALL.getNameInEnglish());
        put("block.metallics_arts.copper_wall", MetalNamesEnum.COPPER.getNameInEnglish() + " " + CTW.WALL.getNameInEnglish());
        put("block.metallics_arts.iron_raw_wall",CTW.RAW.getNameInEnglish() + " " + MetalNamesEnum.IRON.getNameInEnglish() + " " + CTW.WALL.getNameInEnglish());
        put("block.metallics_arts.gold_raw_wall",CTW.RAW.getNameInEnglish() + " " + MetalNamesEnum.GOLD.getNameInEnglish() + " " + CTW.WALL.getNameInEnglish());
        put("block.metallics_arts.copper_raw_wall",CTW.RAW.getNameInEnglish() + " " + MetalNamesEnum.COPPER.getNameInEnglish() + " " + CTW.WALL.getNameInEnglish());
    }};

    private final Map<String, String> fences = new HashMap<>() {{
        for (MetalEnum metal : MetalEnum.values()) {
            put("block.metallics_arts." + metal.getMetalNameLower() + "_fence", MetalNamesEnum.valueOf(metal.name()).getNameInEnglish() + " " + CTW.FENCE.getNameInEnglish());
            if (!metal.isAlloy()) {
                put("block.metallics_arts." + metal.getMetalNameLower() + "_raw_fence", MetalNamesEnum.valueOf(metal.name()).getNameInEnglish() + " " + CTW.FENCE.getNameInEnglish());
            }
        }
        for (GemsEnum metal : GemsEnum.values()) {
            put("block.metallics_arts." + metal.getGemNameLower() + "_fence", MetalNamesEnum.valueOf(metal.name()).getNameInEnglish() + " " + CTW.FENCE.getNameInEnglish());
        }
        put("block.metallics_arts.iron_fence", MetalNamesEnum.IRON.getNameInEnglish() + " " + CTW.FENCE.getNameInEnglish());
        put("block.metallics_arts.gold_fence", MetalNamesEnum.GOLD.getNameInEnglish() + " " + CTW.FENCE.getNameInEnglish());
        put("block.metallics_arts.iron_raw_fence",CTW.RAW.getNameInEnglish() + " " + MetalNamesEnum.IRON.getNameInEnglish() + " " + CTW.FENCE.getNameInEnglish());
        put("block.metallics_arts.gold_raw_fence",CTW.RAW.getNameInEnglish() + " " + MetalNamesEnum.GOLD.getNameInEnglish() + " " + CTW.FENCE.getNameInEnglish());
    }};

    private final Map<String, String> fence_gates = new HashMap<>() {{
        for (MetalEnum metal : MetalEnum.values()) {
            put("block.metallics_arts." + metal.getMetalNameLower() + "_fence_gate", MetalNamesEnum.valueOf(metal.name()).getNameInEnglish() + " " + CTW.FENCE_GATE.getNameInEnglish());
            if (!metal.isAlloy()) {
                put("block.metallics_arts." + metal.getMetalNameLower() + "_raw_fence_gate", MetalNamesEnum.valueOf(metal.name()).getNameInEnglish() + " " + CTW.FENCE_GATE.getNameInEnglish());
            }
        }
        for (GemsEnum metal : GemsEnum.values()) {
            put("block.metallics_arts." + metal.getGemNameLower() + "_fence_gate", MetalNamesEnum.valueOf(metal.name()).getNameInEnglish() + " " + CTW.FENCE_GATE.getNameInEnglish());
        }
        put("block.metallics_arts.iron_fence_gate", MetalNamesEnum.IRON.getNameInEnglish() + " " + CTW.FENCE_GATE.getNameInEnglish());
        put("block.metallics_arts.gold_fence_gate", MetalNamesEnum.GOLD.getNameInEnglish() + " " + CTW.FENCE_GATE.getNameInEnglish());
        put("block.metallics_arts.iron_raw_fence_gate",CTW.RAW.getNameInEnglish() + " " + MetalNamesEnum.IRON.getNameInEnglish() + " " + CTW.FENCE_GATE.getNameInEnglish());
        put("block.metallics_arts.gold_raw_fence_gate",CTW.RAW.getNameInEnglish() + " " + MetalNamesEnum.GOLD.getNameInEnglish() + " " + CTW.FENCE_GATE.getNameInEnglish());
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
     *
     * @param locale            the locale for the target language
     */
    public ModLanguageProviderPL(DataGenerator gen, String locale) {
        super(gen.getPackOutput(), MetallicsArts.MOD_ID, locale);
    }

    /**
     * Adds the Polish translations for mod elements.
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
        for (String key: paintings.keySet()) {
            add(key, paintings.get(key));
        }
        for (String key: gun_ammo_and_weapons.keySet()) {
            add(key, gun_ammo_and_weapons.get(key));
        }
        for (String key: cores.keySet()) {
            add(key, cores.get(key));
        }
        for (String key: armors.keySet()) {
            add(key, armors.get(key));
        }
        for (String key: buttonConfigs.keySet()) {
            add(key, buttonConfigs.get(key));
        }
        for (String key: metalMindsData.keySet()) {
            add(key, metalMindsData.get(key));
        }
        for (String key: signs.keySet()) {
            add(key, signs.get(key));
        }
        for (String key: redstone.keySet()) {
            add(key, redstone.get(key));
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
