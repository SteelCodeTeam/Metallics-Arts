package net.rudahee.metallics_arts.data.providers.language_providers;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.StairBlock;
import net.minecraftforge.common.data.LanguageProvider;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.data.enums.implementations.GemsEnum;
import net.rudahee.metallics_arts.data.enums.implementations.MetalEnum;
import net.rudahee.metallics_arts.data.enums.implementations.languages.*;
import net.rudahee.metallics_arts.data.providers.language_providers.book.BookHelperEN;
import net.rudahee.metallics_arts.setup.registries.ModBlocksRegister;

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


                put("entity.minecraft.villager.metallics_arts.forge_master", "MAESTRO DE LA FORGA");

                put("item.metallics_arts.large_vial",CTW.LARGE.getNameInEnglish() + " " + CTW.VIAL.getNameInEnglish());
                put("item.metallics_arts.small_vial",CTW.SMALL.getNameInEnglish() + " " + CTW.VIAL.getNameInEnglish());
                put("curios.identifier.metalmind_slot",CTW.METALMIND.getNameInEnglish() + " " + CTW.SLOT.getNameInEnglish());

                put("itemGroup.metallics_arts", CTW.METALLICS_ARTS.getNameInEnglish());
                put("itemGroup.metallics_arts.decorations", CTW.DECORATIONS.getNameInEnglish() + " " + CTW.METALLICS_ARTS.getNameInEnglish());

                put("key.category_powers_metallics_arts", CTW.POWERS.getNameInEnglish() + " " + CTW.METALLICS_ARTS.getNameInEnglish());
                put("key.category_metallics_arts", CTW.METALLICS_ARTS.getNameInEnglish());
                put("key.metallics_arts.allomantic", CTW.ALLOMANTIC.getNameInEnglish() + " " + CTW.POWER_SELECTOR.getNameInEnglish() );
                put("key.metallics_arts.feruchemic", CTW.FERUCHEMICAL.getNameInEnglish() + " " + CTW.POWER_SELECTOR.getNameInEnglish());
                put("key.metallics_arts.allomantic_push",CTW.ALLOMANTIC.getNameInEnglish() + " " + CTW.PUSH.getNameInEnglish());
                put("key.metallics_arts.allomantic_pull", CTW.ALLOMANTIC.getNameInEnglish() + " " +CTW.PULL.getNameInEnglish());
                put("key.metallics_arts.vertical_jump", CTW.VERTICAL.getNameInEnglish() + " " +CTW.PUSH.getNameInEnglish());
                put("key.metallics_arts.feruchemic_decant", CTW.FERUCHEMICAL.getNameInEnglish() + " " + CTW.TAPPING.getNameInEnglish());
                put("key.metallics_arts.feruchemic_store",CTW.FERUCHEMICAL.getNameInEnglish() + " " +CTW.STORAGE.getNameInEnglish());
                put("key.metallics_arts.switch_overlay", CTW.SWITCH_OVERLAY.getNameInEnglish());

                put("metallics_arts.metal_mind_translate.has_reserve", CTW.HAS_RESERVE.getNameInEnglish());
                put("metallics_arts.metal_mind_translate.not_has_reserve", CTW.NOT_HAS_RESERVE.getNameInEnglish());
                put("metallics_arts.spike_feruchemic_power", CTW.STORAGE_POWER.getNameInEnglish() + ": " + CTW.FERUCHEMICAL.getNameInEnglish());
                put("metallics_arts.spike_allomantic_power", CTW.STORAGE_POWER.getNameInEnglish() + ": " + CTW.ALLOMANTIC.getNameInEnglish());

                //ver porque usa "spike"
                put("metallics_arts.metal_mind_translate.tapping_identity", CTW.TAPPING_IDENTITY.getNameInEnglish());

                //arreglar estos de aca abajo
                put("metallics_arts.metal_mind_translate.store_identity", CTW.STORE_IDENTITY.getNameInEnglish());
                put("metallics_arts.metal_mind_translate.off_power", CTW.POWER_OFF.getNameInEnglish());
                put("metallics_arts.metal_mind_translate.shift_info",CTW.SHIFT_TO_MORE_INFO.getNameInEnglish());
                put("metallics_arts.metal_mind.owner", CTW.OWNER.getNameInEnglish());
                put("metallics_arts.metal_mind.nobody", CTW.NOBODY.getNameInEnglish());
                put("metallics_arts.metal_mind.owner_someone", CTW.OWNER_SOMEONE.getNameInEnglish());
                put("metallics_arts.metal_mind_translate.uses", CTW.USES.getNameInEnglish());



                put("item.metallics_arts.obsidian_dagger", CTW.OBSIDIAN_DAGGER.getNameInEnglish());
                put("item.metallics_arts.silver_knife", CTW.SILVER_KNIFE.getNameInEnglish());
                put("item.metallics_arts.koloss_blade", CTW.KOLOSS_BLADE.getNameInEnglish());
                put("item.metallics_arts.dueling_staff", CTW.DUELING_STAFF.getNameInEnglish());
                put("item.metallics_arts.obsidian_axe", CTW.OBSIDIAN_AXE.getNameInEnglish());

                put("item.metallics_arts.core_obsidian", CTW.OBSIDIAN_CORE.getNameInEnglish());
                put("item.metallics_arts.core_steel", CTW.STEEL_CORE.getNameInEnglish());
                put("item.metallics_arts.core_aluminum", CTW.ALUMINUM_CORE.getNameInEnglish());
                put("item.metallics_arts.steel_helmet", CTW.STEEL_HELMET.getNameInEnglish());
                put("item.metallics_arts.steel_leggings", CTW.STEEL_LEGGINGS.getNameInEnglish());
                put("item.metallics_arts.steel_chestplate", CTW.STEEL_CHESTPLATE.getNameInEnglish());
                put("item.metallics_arts.steel_boots", CTW.STEEL_BOOTS.getNameInEnglish());
                put("item.metallics_arts.aluminum_helmet", CTW.ALUMINUM_HELMET.getNameInEnglish());
                put("item.metallics_arts.aluminum_leggings", CTW.ALUMINUM_LEGGINGS.getNameInEnglish());
                put("item.metallics_arts.aluminum_chestplate", CTW.ALUMINUM_CHESTPLATE.getNameInEnglish());
                put("item.metallics_arts.aluminum_boots", CTW.ALUMINUM_BOOTS.getNameInEnglish());
                put("metallics_arts.item.tooltip.mistcloak", "Increase your speed");
                put("item.metallics_arts.wood_shield", CTW.WOOD.getNameInEnglish() +" "+ CTW.SHIELD.getNameInEnglish());
                put("item.metallics_arts.bronze_aluminum_shield", MetalNamesEnum.ALUMINUM.getNameInEnglish() +" "+ CTW.SHIELD.getNameInEnglish());

        }};

        private final Map<String, String> tables = new HashMap<>() {{
                put("block.metallics_arts.menu.crucible_furnace", "Crucible Furnace");
                put("block.metallics_arts.crucible_furnace", "Crucible Furnace");
        }};

        private final Map<String, String> ingots = new HashMap<>() {{
                for (MetalAuxiliaryInfo metal: MetalAuxiliaryInfo.values()) {
                        if (!metal.isVanilla()) {
                                put("item.metallics_arts." + metal.getId() + "_ingot", MetalNamesEnum.valueOf(metal.name()).getNameInEnglish() + " " + CTW.INGOT.getNameInEnglish());
                        }

                }
        }};

        private final Map<String, String> stairs = new HashMap<>() {{
                for (MetalEnum metal : MetalEnum.values()){
                        put("block.metallics_arts." + metal.getMetalNameLower() + "_stairs", MetalNamesEnum.valueOf(metal.name()).getNameInEnglish() + " " + CTW.STAIRS.getNameInEnglish());
                                if (!metal.isAlloy()) {
                                        put("block.metallics_arts." + metal.getMetalNameLower() + "_raw_stairs",CTW.RAW.getNameInEnglish() + " " + MetalNamesEnum.valueOf(metal.name()).getNameInEnglish() + " " + CTW.STAIRS.getNameInEnglish());
                                }
                }
                for (GemsEnum metal : GemsEnum.values()){
                        put("block.metallics_arts." + metal.getGemNameLower() + "_stairs", MetalNamesEnum.valueOf(metal.name()).getNameInEnglish() + " " + CTW.STAIRS.getNameInEnglish());
                }
                put("block.metallics_arts.iron_stairs", MetalNamesEnum.IRON.getNameInEnglish() + " " + CTW.STAIRS.getNameInEnglish());
                put("block.metallics_arts.gold_stairs", MetalNamesEnum.GOLD.getNameInEnglish() + " " + CTW.STAIRS.getNameInEnglish());
                put("block.metallics_arts.iron_raw_stairs",CTW.RAW.getNameInEnglish() + " " + MetalNamesEnum.IRON.getNameInEnglish() + " " + CTW.STAIRS.getNameInEnglish());
                put("block.metallics_arts.gold_raw_stairs",CTW.RAW.getNameInEnglish() + " " + MetalNamesEnum.GOLD.getNameInEnglish() + " " + CTW.STAIRS.getNameInEnglish());
                put("block.metallics_arts.copper_raw_stairs",CTW.RAW.getNameInEnglish() + " " + MetalNamesEnum.COPPER.getNameInEnglish() + " " + CTW.STAIRS.getNameInEnglish());
        }};

        private final Map<String, String> slabs = new HashMap<>() {{
                for (MetalEnum metal : MetalEnum.values()){
                        put("block.metallics_arts." + metal.getMetalNameLower() + "_slabs", MetalNamesEnum.valueOf(metal.name()).getNameInEnglish() + " " + CTW.SLABS.getNameInEnglish());
                        if (!metal.isAlloy()) {
                                put("block.metallics_arts." + metal.getMetalNameLower() + "_raw_slabs",CTW.RAW.getNameInEnglish() + " " + MetalNamesEnum.valueOf(metal.name()).getNameInEnglish() + " " + CTW.SLABS.getNameInEnglish());
                        }
                }
                for (GemsEnum metal : GemsEnum.values()){
                        put("block.metallics_arts." + metal.getGemNameLower() + "_slabs", MetalNamesEnum.valueOf(metal.name()).getNameInEnglish() + " " + CTW.SLABS.getNameInEnglish());
                }
                put("block.metallics_arts.iron_slabs", MetalNamesEnum.IRON.getNameInEnglish() + " " + CTW.SLABS.getNameInEnglish());
                put("block.metallics_arts.gold_slabs", MetalNamesEnum.GOLD.getNameInEnglish() + " " + CTW.SLABS.getNameInEnglish());
                put("block.metallics_arts.iron_raw_slabs",CTW.RAW.getNameInEnglish() + " " + MetalNamesEnum.IRON.getNameInEnglish() + " " + CTW.SLABS.getNameInEnglish());
                put("block.metallics_arts.gold_raw_slabs",CTW.RAW.getNameInEnglish() + " " + MetalNamesEnum.GOLD.getNameInEnglish() + " " + CTW.SLABS.getNameInEnglish());
                put("block.metallics_arts.copper_raw_slabs",CTW.RAW.getNameInEnglish() + " " + MetalNamesEnum.COPPER.getNameInEnglish() + " " + CTW.SLABS.getNameInEnglish());
        }};

        private final Map<String, String> walls = new HashMap<>() {{
                for (MetalEnum metal : MetalEnum.values()){
                        put("block.metallics_arts." + metal.getMetalNameLower() + "_walls", MetalNamesEnum.valueOf(metal.name()).getNameInEnglish() + " " + CTW.WALLS.getNameInEnglish());
                        if (!metal.isAlloy()) {
                                put("block.metallics_arts." + metal.getMetalNameLower() + "_raw_walls",CTW.RAW.getNameInEnglish() + " " + MetalNamesEnum.valueOf(metal.name()).getNameInEnglish() + " " + CTW.WALLS.getNameInEnglish());
                        }
                }
                for (GemsEnum metal : GemsEnum.values()){
                        put("block.metallics_arts." + metal.getGemNameLower() + "_walls", MetalNamesEnum.valueOf(metal.name()).getNameInEnglish() + " " + CTW.WALLS.getNameInEnglish());
                }
                put("block.metallics_arts.iron_walls", MetalNamesEnum.IRON.getNameInEnglish() + " " + CTW.WALLS.getNameInEnglish());
                put("block.metallics_arts.gold_walls", MetalNamesEnum.GOLD.getNameInEnglish() + " " + CTW.WALLS.getNameInEnglish());
                put("block.metallics_arts.iron_raw_walls",CTW.RAW.getNameInEnglish() + " " + MetalNamesEnum.IRON.getNameInEnglish() + " " + CTW.WALLS.getNameInEnglish());
                put("block.metallics_arts.gold_raw_walls",CTW.RAW.getNameInEnglish() + " " + MetalNamesEnum.GOLD.getNameInEnglish() + " " + CTW.WALLS.getNameInEnglish());
        }};

        private final Map<String, String> fences = new HashMap<>() {{
                for (MetalEnum metal : MetalEnum.values()){
                        put("block.metallics_arts." + metal.getMetalNameLower() + "_fences", MetalNamesEnum.valueOf(metal.name()).getNameInEnglish() + " " + CTW.FENCES.getNameInEnglish());
                        if (!metal.isAlloy()) {
                                put("block.metallics_arts." + metal.getMetalNameLower() + "_raw_fences",CTW.RAW.getNameInEnglish() + " " + MetalNamesEnum.valueOf(metal.name()).getNameInEnglish() + " " + CTW.FENCES.getNameInEnglish());
                        }
                }
                for (GemsEnum metal : GemsEnum.values()){
                        put("block.metallics_arts." + metal.getGemNameLower() + "_fences", MetalNamesEnum.valueOf(metal.name()).getNameInEnglish() + " " + CTW.FENCES.getNameInEnglish());
                }
                put("block.metallics_arts.iron_fences", MetalNamesEnum.IRON.getNameInEnglish() + " " + CTW.FENCES.getNameInEnglish());
                put("block.metallics_arts.gold_fences", MetalNamesEnum.GOLD.getNameInEnglish() + " " + CTW.FENCES.getNameInEnglish());
                put("block.metallics_arts.copper_fences", MetalNamesEnum.COPPER.getNameInEnglish() + " " + CTW.FENCES.getNameInEnglish());
                put("block.metallics_arts.iron_raw_fences",CTW.RAW.getNameInEnglish() + " " + MetalNamesEnum.IRON.getNameInEnglish() + " " + CTW.FENCES.getNameInEnglish());
                put("block.metallics_arts.gold_raw_fences",CTW.RAW.getNameInEnglish() + " " + MetalNamesEnum.GOLD.getNameInEnglish() + " " + CTW.FENCES.getNameInEnglish());
                put("block.metallics_arts.copper_raw_fences",CTW.RAW.getNameInEnglish() + " " + MetalNamesEnum.COPPER.getNameInEnglish() + " " + CTW.FENCES.getNameInEnglish());

        }};

        private final Map<String, String> fence_gates = new HashMap<>() {{
                for (MetalEnum metal : MetalEnum.values()){
                        put("block.metallics_arts." + metal.getMetalNameLower() + "_fence_gates",MetalNamesEnum.valueOf(metal.name()).getNameInEnglish() + " " + CTW.FENCE_GATES.getNameInEnglish());
                        if (!metal.isAlloy()) {
                                put("block.metallics_arts." + metal.getMetalNameLower() + "_raw_fence_gates",CTW.RAW.getNameInEnglish() + " " + MetalNamesEnum.valueOf(metal.name()).getNameInEnglish() + " " + CTW.FENCE_GATES.getNameInEnglish());
                        }
                }
                for (GemsEnum metal : GemsEnum.values()){
                        put("block.metallics_arts." + metal.getGemNameLower() + "_fence_gates", MetalNamesEnum.valueOf(metal.name()).getNameInEnglish() + " " + CTW.FENCE_GATES.getNameInEnglish());
                }
                put("block.metallics_arts.iron_fence_gates", MetalNamesEnum.IRON.getNameInEnglish() + " " + CTW.FENCE_GATES.getNameInEnglish());
                put("block.metallics_arts.gold_fence_gates", MetalNamesEnum.GOLD.getNameInEnglish() + " " + CTW.FENCE_GATES.getNameInEnglish());
                put("block.metallics_arts.copper_fence_gates", MetalNamesEnum.COPPER.getNameInEnglish() + " " + CTW.FENCE_GATES.getNameInEnglish());
                put("block.metallics_arts.iron_raw_fence_gates",CTW.RAW.getNameInEnglish() + " " + MetalNamesEnum.IRON.getNameInEnglish() + " " + CTW.FENCE_GATES.getNameInEnglish());
                put("block.metallics_arts.gold_raw_fence_gates",CTW.RAW.getNameInEnglish() + " " + MetalNamesEnum.GOLD.getNameInEnglish() + " " + CTW.FENCE_GATES.getNameInEnglish());
                put("block.metallics_arts.copper_raw_fence_gates",CTW.RAW.getNameInEnglish() + " " + MetalNamesEnum.COPPER.getNameInEnglish() + " " + CTW.FENCE_GATES.getNameInEnglish());
        }};


        private final Map<String, String> effects = new HashMap<>() {{
                for (MetalAuxiliaryInfo metal: MetalAuxiliaryInfo.values()) {
                        if (!metal.isOnlyForAlloys()) {
                                put("effect."+ MetallicsArts.MOD_ID + ".allomantic_" + metal.getId(), CTW.ALLOMANTIC.getNameInEnglish() + " " + MetalNamesEnum.valueOf(metal.name()).getNameInEnglish());
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
        super(gen.getPackOutput(), modid, locale);
    }

    /**
     * Adds the English translations for mod elements.
     */
    @Override
    protected void addTranslations() {
            for (String key: ingots.keySet()) {
                    add(key, ingots.get(key));
            }
            for (String key: effects.keySet()) {
                    add(key, effects.get(key));
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
            for (String key: stairs.keySet()) {
                    add(key, stairs.get(key));
            }
            for (String key: slabs.keySet()) {
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

            HashMap<String, String> bookTraslationsHelper = BookHelperEN.addDemoBook();

            for (String key : bookTraslationsHelper.keySet()) {
                    add(key, bookTraslationsHelper.get(key));
            }

            for (String key : tables.keySet()) {
                    add(key, tables.get(key));
            }

    }



}
