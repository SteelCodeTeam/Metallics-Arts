package net.rudahee.metallics_arts.data.providers;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.data.enums.implementations.GemsEnum;
import net.rudahee.metallics_arts.data.enums.implementations.MetalEnum;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.enums.implementations.custom_items.ArmorPiecesEnum;
import net.rudahee.metallics_arts.data.enums.implementations.custom_items.MetalMindEnum;
import net.rudahee.metallics_arts.data.enums.implementations.custom_items.Shields;
import net.rudahee.metallics_arts.data.enums.implementations.custom_items.SpikeEnum;
import net.rudahee.metallics_arts.data.enums.implementations.languages.old.MetalNamesEnum;
import net.rudahee.metallics_arts.setup.registries.ModBannersRegister;
import net.rudahee.metallics_arts.setup.registries.ModBlocksRegister;
import net.rudahee.metallics_arts.setup.registries.ModItemsRegister;
import net.rudahee.metallics_arts.setup.registries.ModRecipeTypesRegister;
import net.rudahee.metallics_arts.setup.registries.items.ModTags;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.function.Consumer;

/**
 * Custom RecipeProvider class that extends the base RecipeProvider class.
 * It is used to create mod-specific crafting recipes.
 *
 * @author SteelCode Team
 * @since 1.5.6
 */
public class ModRecipeProvider extends RecipeProvider {

    private static final String ITEM_ACHIEVEMENT = "has_item";
    private static final String BLOCK_ACHIEVEMENT = "has_block";
    private static final String ARMOR_ACHIEVEMENT = "has_armor";

    /**
     * Constructs a new instance of the ModRecipeProvider class.
     *
     * @param generator the data generator used to generate crafting recipes
     */
    public ModRecipeProvider(DataGenerator generator) {
        super(generator.getPackOutput());
    }

    /**
     * Builds the custom crafting recipes for the mod, including ingot-to-block,
     * raw metal-to-block, gem-to-block, and nugget-to-ingot and other conversions.
     * This method assumes that mod items and blocks are registered in
     * ModItemsRegister and ModBlocksRegister classes.
     *
     * @param recipesConsumer a consumer to handle the finished recipes
     *
     * @see ModItemsRegister
     * @see ModBlocksRegister
     *
     * @deprecated
     */
    @Deprecated(since = "1.6.6", forRemoval = true)
    @Override
    @SuppressWarnings("removal")
    protected void buildRecipes(@NotNull Consumer<FinishedRecipe> recipesConsumer) {

        this.basicRecipes(recipesConsumer);
        this.metallicsArtsItemsRecipes(recipesConsumer);
        this.combatRecipe(recipesConsumer);
        this.iconsRecipe(recipesConsumer);


        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC ,ModItemsRegister.ITEM_METAL_INGOT.get("steel"),1).
                requires(Items.IRON_INGOT)
                .requires(Items.COAL)
                .requires(Items.COAL)
                .requires(Items.COAL)
                .unlockedBy(ITEM_ACHIEVEMENT,has(ModItemsRegister.ITEM_METAL_INGOT.get("steel")))
                .save(recipesConsumer,new ResourceLocation(MetallicsArts.MOD_ID+"_steel_alloy_craft"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC ,ModItemsRegister.ITEM_METAL_INGOT.get("pewter"),1)
                .requires(ModTags.INGOTS.get(MetalEnum.LEAD.getMetalNameLower()))
                .requires(ModTags.INGOTS.get(MetalEnum.TIN.getMetalNameLower()))
                .requires(ModTags.INGOTS.get(MetalEnum.TIN.getMetalNameLower()))
                .requires(ModTags.INGOTS.get(MetalEnum.TIN.getMetalNameLower()))
                .unlockedBy(ITEM_ACHIEVEMENT,has(ModItemsRegister.ITEM_METAL_INGOT.get("pewter")))
                .save(recipesConsumer,new ResourceLocation(MetallicsArts.MOD_ID+"_pewter_alloy_craft"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC ,ModItemsRegister.ITEM_METAL_INGOT.get("bronze"),1)
                .requires(ModTags.INGOTS.get(MetalEnum.TIN.getMetalNameLower()))
                .requires(Items.COPPER_INGOT)
                .requires(Items.COPPER_INGOT)
                .requires(Items.COPPER_INGOT)
                .unlockedBy(ITEM_ACHIEVEMENT,has(ModItemsRegister.ITEM_METAL_INGOT.get("bronze")))
                .save(recipesConsumer,new ResourceLocation(MetallicsArts.MOD_ID+"_bronze_alloy_craft"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC ,ModItemsRegister.ITEM_METAL_INGOT.get("brass"),1)
                .requires(ModTags.INGOTS.get(MetalEnum.ZINC.getMetalNameLower()))
                .requires(ModTags.INGOTS.get(MetalEnum.ZINC.getMetalNameLower()))
                .requires(Items.COPPER_INGOT)
                .requires(Items.COPPER_INGOT)
                .unlockedBy(ITEM_ACHIEVEMENT,has(ModItemsRegister.ITEM_METAL_INGOT.get("brass")))
                .save(recipesConsumer,new ResourceLocation(MetallicsArts.MOD_ID+"_brass_alloy_craft"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC ,ModItemsRegister.ITEM_METAL_INGOT.get("electrum"),1)
                .requires(ModTags.INGOTS.get(MetalEnum.SILVER.getMetalNameLower()))
                .requires(ModTags.INGOTS.get(MetalEnum.SILVER.getMetalNameLower()))
                .requires(Items.GOLD_INGOT)
                .requires(Items.GOLD_INGOT)
                .unlockedBy(ITEM_ACHIEVEMENT,has(ModItemsRegister.ITEM_METAL_INGOT.get("electrum")))
                .save(recipesConsumer,new ResourceLocation(MetallicsArts.MOD_ID+"_electrum_alloy_craft"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC ,ModItemsRegister.ITEM_METAL_INGOT.get("bendalloy"),1)
                .requires(ModTags.INGOTS.get(MetalEnum.LEAD.getMetalNameLower()))
                .requires(ModTags.INGOTS.get(MetalEnum.LEAD.getMetalNameLower()))
                .requires(ModTags.INGOTS.get(MetalEnum.CADMIUM.getMetalNameLower()))
                .requires(ModTags.INGOTS.get(MetalEnum.CADMIUM.getMetalNameLower()))
                .unlockedBy(ITEM_ACHIEVEMENT,has(ModItemsRegister.ITEM_METAL_INGOT.get("bendalloy")))
                .save(recipesConsumer,new ResourceLocation(MetallicsArts.MOD_ID+"_bendalloy_alloy_craft"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC ,ModItemsRegister.ITEM_METAL_INGOT.get("duralumin"),1)
                .requires(ModTags.INGOTS.get(MetalEnum.ALUMINUM.getMetalNameLower()))
                .requires(ModTags.INGOTS.get(MetalEnum.ALUMINUM.getMetalNameLower()))
                .requires(ModTags.INGOTS.get(MetalEnum.ALUMINUM.getMetalNameLower()))
                .requires(Items.COPPER_INGOT)
                .unlockedBy(ITEM_ACHIEVEMENT,has(ModItemsRegister.ITEM_METAL_INGOT.get("duralumin")))
                .save(recipesConsumer,new ResourceLocation(MetallicsArts.MOD_ID+"_duralumin_alloy_craft"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC ,ModItemsRegister.ITEM_METAL_INGOT.get("nicrosil"),1)
                .requires(ModTags.INGOTS.get(MetalEnum.NICKEL.getMetalNameLower()))
                .requires(ModTags.INGOTS.get(MetalEnum.NICKEL.getMetalNameLower()))
                .requires(ModTags.INGOTS.get(MetalEnum.CHROMIUM.getMetalNameLower()))
                .requires(ModTags.INGOTS.get(MetalEnum.CHROMIUM.getMetalNameLower()))
                .unlockedBy(ITEM_ACHIEVEMENT,has(ModItemsRegister.ITEM_METAL_INGOT.get("nicrosil")))
                .save(recipesConsumer,new ResourceLocation(MetallicsArts.MOD_ID+"_nicrosil_alloy_craft"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC ,ModItemsRegister.ITEM_GEMS_BASE.get("malatium"),1)
                .requires(ModTags.GEMS.get(GemsEnum.ATIUM.getGemNameLower()))
                .requires(Items.GOLD_INGOT)
                .requires(Items.GOLD_INGOT)
                .requires(Items.GOLD_INGOT)
                .unlockedBy(ITEM_ACHIEVEMENT,has(ModItemsRegister.ITEM_GEMS_BASE.get("malatium")))
                .save(recipesConsumer,new ResourceLocation(MetallicsArts.MOD_ID+"_malatium_alloy_craft"));

        SpecialRecipeBuilder.special(ModRecipeTypesRegister.LARGE_VIAL_ITEM_RECIPE_SERIALIZER.get()).save(recipesConsumer, MetallicsArts.MOD_ID+":large_vial_filling_recipe");
        SpecialRecipeBuilder.special(ModRecipeTypesRegister.SMALL_VIAL_ITEM_RECIPE_SERIALIZER.get()).save(recipesConsumer, MetallicsArts.MOD_ID+":small_vial_filling_recipe");


        for (MetalTagEnum metal : MetalTagEnum.values()) {

            ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS,ModBannersRegister.PATTERN_ITEMS.get("a_"+metal.getNameLower()).get())
                    .requires(!metal.isDivine() ? ModItemsRegister.ITEM_ICONS_ALLOMANCY.get(metal.getNameLower()) : ModItemsRegister.ITEM_ICONS_ALLOMANCY_DIVINE.get(metal.getNameLower()))
                    .requires(Items.PAPER)
                    .unlockedBy(ITEM_ACHIEVEMENT,has(ModBannersRegister.PATTERN_ITEMS.get("a_"+metal.getNameLower()).get()))
                    .save(recipesConsumer,new ResourceLocation(MetallicsArts.MOD_ID + "_" + ModBannersRegister.PATTERN_ITEMS.get("a_"+metal.getNameLower()).get()));

            ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS,ModBannersRegister.PATTERN_ITEMS.get("f_"+metal.getNameLower()).get())
                    .requires(!metal.isDivine() ? ModItemsRegister.ITEM_ICONS_FERUCHEMIC.get(metal.getNameLower()) : ModItemsRegister.ITEM_ICONS_FERUCHEMIC_DIVINE.get(metal.getNameLower()))
                    .requires(Items.PAPER)
                    .unlockedBy(ITEM_ACHIEVEMENT,has(ModBannersRegister.PATTERN_ITEMS.get("f_"+metal.getNameLower()).get()))
                    .save(recipesConsumer,new ResourceLocation(MetallicsArts.MOD_ID + "_" + ModBannersRegister.PATTERN_ITEMS.get("f_"+metal.getNameLower()).get()));
        }

        LegacyUpgradeRecipeBuilder.smithing(
                        Ingredient.of(Items.NETHERITE_HELMET),
                        Ingredient.of(ModItemsRegister.STEEL_CORE.get()),
                        RecipeCategory.COMBAT,
                        ModItemsRegister.STEEL_ARMOR.get(ArmorPiecesEnum.HELMET).get())
                .unlocks(ARMOR_ACHIEVEMENT, has(ModItemsRegister.STEEL_ARMOR.get(ArmorPiecesEnum.HELMET).get()))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_armor_steel_helmet"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocksRegister.METAL_PANELS.get("iron_panel"),2)
                .define('#', Tags.Items.INGOTS_IRON)
                .pattern("##")
                .pattern("##")
                .unlockedBy(BLOCK_ACHIEVEMENT, has(ModBlocksRegister.METAL_PANELS.get("iron_panel")))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_iron_panel"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocksRegister.METAL_PANELS.get("gold_panel"),2)
                .define('#', Tags.Items.INGOTS_GOLD)
                .pattern("##")
                .pattern("##")
                .unlockedBy(BLOCK_ACHIEVEMENT, has(ModBlocksRegister.METAL_PANELS.get("gold_panel")))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_gold_panel"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocksRegister.METAL_PANELS.get("copper_panel"),2)
                .define('#', Tags.Items.INGOTS_COPPER)
                .pattern("##")
                .pattern("##")
                .unlockedBy(BLOCK_ACHIEVEMENT, has(ModBlocksRegister.METAL_PANELS.get("copper_panel")))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_copper_panel"));



        for (MetalEnum metal : MetalEnum.values()) {

            ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,ModBlocksRegister.BLOCK_METAL_STAIRS.get(metal.getMetalNameLower()),4)
                    .define('#', ModTags.METAL_BLOCKS.get(metal.getMetalNameLower()))
                    .pattern("#  ")
                    .pattern("## ")
                    .pattern("###")
                    .unlockedBy(BLOCK_ACHIEVEMENT, has(ModBlocksRegister.BLOCK_METAL_STAIRS.get(metal.getMetalNameLower())))
                    .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_" + metal.getMetalNameLower() + "_stairs"));

            ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,ModBlocksRegister.BLOCK_METAL_SLAB.get(metal.getMetalNameLower()),6)
                    .define('#', ModTags.METAL_BLOCKS.get(metal.getMetalNameLower()))
                    .pattern("###")
                    .unlockedBy(BLOCK_ACHIEVEMENT, has(ModBlocksRegister.BLOCK_METAL_SLAB.get(metal.getMetalNameLower())))
                    .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_" + metal.getMetalNameLower() + "_slab"));

            ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,ModBlocksRegister.BLOCK_METAL_WALL.get(metal.getMetalNameLower()),3)
                    .define('#', ModTags.METAL_BLOCKS.get(metal.getMetalNameLower()))
                    .pattern("###")
                    .pattern("###")
                    .unlockedBy(BLOCK_ACHIEVEMENT, has(ModBlocksRegister.BLOCK_METAL_WALL.get(metal.getMetalNameLower())))
                    .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_" + metal.getMetalNameLower() + "_wall"));

            ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,ModBlocksRegister.BLOCK_METAL_FENCE.get(metal.getMetalNameLower()),3)
                    .define('#', ModTags.METAL_BLOCKS.get(metal.getMetalNameLower()))
                    .define('*', ModTags.INGOTS.get(metal.getMetalNameLower()))
                    .pattern("#*#")
                    .pattern("#*#")
                    .unlockedBy(BLOCK_ACHIEVEMENT, has(ModBlocksRegister.BLOCK_METAL_FENCE.get(metal.getMetalNameLower())))
                    .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_" + metal.getMetalNameLower() + "_fence"));

            ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,ModBlocksRegister.BLOCK_METAL_FENCE_GATE.get(metal.getMetalNameLower()),3)
                    .define('#', ModTags.METAL_BLOCKS.get(metal.getMetalNameLower()))
                    .define('*', ModTags.INGOTS.get(metal.getMetalNameLower()))
                    .pattern("*#*")
                    .pattern("*#*")
                    .unlockedBy(BLOCK_ACHIEVEMENT, has(ModBlocksRegister.BLOCK_METAL_FENCE_GATE.get(metal.getMetalNameLower())))
                    .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_" + metal.getMetalNameLower() + "_fence_gate"));

            if (Boolean.FALSE.equals(metal.isAlloy())) {
                ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,ModBlocksRegister.BLOCK_METAL_STAIRS.get(metal.getMetalNameLower() + "_raw"),4)
                        .define('#', ModTags.RAW_BLOCKS.get(metal.getMetalNameLower()))
                        .pattern("#  ")
                        .pattern("## ")
                        .pattern("###")
                        .unlockedBy(BLOCK_ACHIEVEMENT, has(ModBlocksRegister.BLOCK_METAL_STAIRS.get(metal.getMetalNameLower()+ "_raw")))
                        .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_" + metal.getMetalNameLower() + "_raw_stairs"));

                ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,ModBlocksRegister.BLOCK_METAL_SLAB.get(metal.getMetalNameLower() + "_raw"),6)
                        .define('#', ModTags.RAW_BLOCKS.get(metal.getMetalNameLower()))
                        .pattern("###")
                        .unlockedBy(BLOCK_ACHIEVEMENT, has(ModBlocksRegister.BLOCK_METAL_SLAB.get(metal.getMetalNameLower() + "_raw")))
                        .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_" + metal.getMetalNameLower() + "_raw_slab"));

                ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,ModBlocksRegister.BLOCK_METAL_WALL.get(metal.getMetalNameLower() + "_raw"),3)
                        .define('#', ModTags.RAW_BLOCKS.get(metal.getMetalNameLower()))
                        .pattern("###")
                        .pattern("###")
                        .unlockedBy(BLOCK_ACHIEVEMENT, has(ModBlocksRegister.BLOCK_METAL_WALL.get(metal.getMetalNameLower() + "_raw")))
                        .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_" + metal.getMetalNameLower() + "_raw_wall"));

                ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,ModBlocksRegister.BLOCK_METAL_FENCE.get(metal.getMetalNameLower() + "_raw"),3)
                        .define('#', ModTags.RAW_BLOCKS.get(metal.getMetalNameLower()))
                        .define('*', ModTags.RAWS.get(metal.getMetalNameLower()))
                        .pattern("#*#")
                        .pattern("#*#")
                        .unlockedBy(BLOCK_ACHIEVEMENT, has(ModBlocksRegister.BLOCK_METAL_FENCE.get(metal.getMetalNameLower() + "_raw")))
                        .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_" + metal.getMetalNameLower() + "_raw_fence"));

                ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,ModBlocksRegister.BLOCK_METAL_FENCE_GATE.get(metal.getMetalNameLower() + "_raw"),3)
                        .define('#', ModTags.RAW_BLOCKS.get(metal.getMetalNameLower()))
                        .define('*', ModTags.RAWS.get(metal.getMetalNameLower()))
                        .pattern("*#*")
                        .pattern("*#*")
                        .unlockedBy(BLOCK_ACHIEVEMENT, has(ModBlocksRegister.BLOCK_METAL_FENCE_GATE.get(metal.getMetalNameLower() + "_raw")))
                        .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_" + metal.getMetalNameLower() + "_raw_fence_wall"));
            }
        }
        for (GemsEnum metal : GemsEnum.values()) {
            ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,ModBlocksRegister.BLOCK_METAL_STAIRS.get(metal.getGemNameLower()),4)
                    .define('#', ModTags.METAL_BLOCKS.get(metal.getGemNameLower()))
                    .pattern("#  ")
                    .pattern("## ")
                    .pattern("###")
                    .unlockedBy(BLOCK_ACHIEVEMENT, has(ModBlocksRegister.BLOCK_METAL_STAIRS.get(metal.getGemNameLower())))
                    .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_" + metal.getGemNameLower() + "_stairs"));

            ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,ModBlocksRegister.BLOCK_METAL_SLAB.get(metal.getGemNameLower()),6)
                    .define('#', ModTags.METAL_BLOCKS.get(metal.getGemNameLower()))
                    .pattern("###")
                    .unlockedBy(BLOCK_ACHIEVEMENT, has(ModBlocksRegister.BLOCK_METAL_SLAB.get(metal.getGemNameLower())))
                    .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_" + metal.getGemNameLower() + "_slab"));

            ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,ModBlocksRegister.BLOCK_METAL_WALL.get(metal.getGemNameLower()),3)
                    .define('#', ModTags.METAL_BLOCKS.get(metal.getGemNameLower()))
                    .pattern("###")
                    .pattern("###")
                    .unlockedBy(BLOCK_ACHIEVEMENT, has(ModBlocksRegister.BLOCK_METAL_WALL.get(metal.getGemNameLower())))
                    .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_" + metal.getGemNameLower() + "_wall"));

            ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,ModBlocksRegister.BLOCK_METAL_FENCE.get(metal.getGemNameLower()),3)
                    .define('#', ModTags.METAL_BLOCKS.get(metal.getGemNameLower()))
                    .define('*', ModTags.GEMS.get(metal.getGemNameLower()))
                    .pattern("#*#")
                    .pattern("#*#")
                    .unlockedBy(BLOCK_ACHIEVEMENT, has(ModBlocksRegister.BLOCK_METAL_FENCE.get(metal.getGemNameLower())))
                    .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_" + metal.getGemNameLower() + "_fence"));

            ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,ModBlocksRegister.BLOCK_METAL_FENCE_GATE.get(metal.getGemNameLower()),3)
                    .define('#', ModTags.METAL_BLOCKS.get(metal.getGemNameLower()))
                    .define('*', ModTags.GEMS.get(metal.getGemNameLower()))
                    .pattern("*#*")
                    .pattern("*#*")
                    .unlockedBy(BLOCK_ACHIEVEMENT, has(ModBlocksRegister.BLOCK_METAL_FENCE_GATE.get(metal.getGemNameLower())))
                    .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_" + metal.getGemNameLower() + "_fence_wall"));

        }

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,ModBlocksRegister.BLOCK_METAL_STAIRS.get("gold"),4)
                .define('#', Items.GOLD_BLOCK)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .unlockedBy(BLOCK_ACHIEVEMENT, has(ModBlocksRegister.BLOCK_METAL_STAIRS.get("gold")))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_gold_stairs"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,ModBlocksRegister.BLOCK_METAL_STAIRS.get("iron"),4)
                .define('#', Items.IRON_BLOCK)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .unlockedBy(BLOCK_ACHIEVEMENT, has(ModBlocksRegister.BLOCK_METAL_STAIRS.get("iron")))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_iron_stairs"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,ModBlocksRegister.BLOCK_METAL_STAIRS.get(MetalTagEnum.IRON.getGemNameLower() + "_raw"),4)
                .define('#', Items.RAW_IRON_BLOCK)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .unlockedBy(BLOCK_ACHIEVEMENT, has(ModBlocksRegister.BLOCK_METAL_STAIRS.get(MetalTagEnum.IRON.getGemNameLower() + "_raw")))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_iron_raw_stairs"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,ModBlocksRegister.BLOCK_METAL_STAIRS.get(MetalTagEnum.GOLD.getGemNameLower() + "_raw"),4)
                .define('#', Items.RAW_GOLD_BLOCK)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .unlockedBy(BLOCK_ACHIEVEMENT, has(ModBlocksRegister.BLOCK_METAL_STAIRS.get(MetalTagEnum.GOLD.getGemNameLower() + "_raw")))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_gold_raw_stairs"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,ModBlocksRegister.BLOCK_METAL_STAIRS.get("copper_raw"),4)
                .define('#', Items.RAW_COPPER_BLOCK)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .unlockedBy(BLOCK_ACHIEVEMENT, has(ModBlocksRegister.BLOCK_METAL_STAIRS.get("copper_raw")))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_copper_raw_stairs"));



        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,ModBlocksRegister.BLOCK_METAL_SLAB.get("gold"),6)
                .define('#', Items.GOLD_BLOCK)
                .pattern("###")
                .unlockedBy(BLOCK_ACHIEVEMENT, has(ModBlocksRegister.BLOCK_METAL_SLAB.get("gold")))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_gold_slab"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,ModBlocksRegister.BLOCK_METAL_SLAB.get("iron"),6)
                .define('#', Items.IRON_BLOCK)
                .pattern("###")
                .unlockedBy(BLOCK_ACHIEVEMENT, has(ModBlocksRegister.BLOCK_METAL_SLAB.get("iron")))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_iron_slab"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,ModBlocksRegister.BLOCK_METAL_SLAB.get(MetalTagEnum.IRON.getGemNameLower() + "_raw"),6)
                .define('#', Items.RAW_IRON_BLOCK)
                .pattern("###")
                .unlockedBy(BLOCK_ACHIEVEMENT, has(ModBlocksRegister.BLOCK_METAL_SLAB.get(MetalTagEnum.IRON.getGemNameLower() + "_raw")))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_iron_raw_slab"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,ModBlocksRegister.BLOCK_METAL_SLAB.get(MetalTagEnum.GOLD.getGemNameLower() + "_raw"),6)
                .define('#', Items.RAW_GOLD_BLOCK)
                .pattern("###")
                .unlockedBy(BLOCK_ACHIEVEMENT, has(ModBlocksRegister.BLOCK_METAL_SLAB.get(MetalTagEnum.GOLD.getGemNameLower() + "_raw")))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_gold_raw_slab"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,ModBlocksRegister.BLOCK_METAL_SLAB.get("copper_raw"),6)
                .define('#', Items.RAW_COPPER_BLOCK)
                .pattern("###")
                .unlockedBy(BLOCK_ACHIEVEMENT, has(ModBlocksRegister.BLOCK_METAL_SLAB.get("copper_raw")))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_copper_raw_slab"));



        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,ModBlocksRegister.BLOCK_METAL_WALL.get("gold"),3)
                .define('#', Items.GOLD_BLOCK)
                .pattern("###")
                .pattern("###")
                .unlockedBy(BLOCK_ACHIEVEMENT, has(ModBlocksRegister.BLOCK_METAL_WALL.get("gold")))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_gold_wall"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,ModBlocksRegister.BLOCK_METAL_WALL.get("iron"),3)
                .define('#', Items.IRON_BLOCK)
                .pattern("###")
                .pattern("###")
                .unlockedBy(BLOCK_ACHIEVEMENT, has(ModBlocksRegister.BLOCK_METAL_WALL.get("iron")))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_iron_wall"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,ModBlocksRegister.BLOCK_METAL_WALL.get(MetalTagEnum.COPPER.getGemNameLower()),3)
                .define('#', Items.COPPER_BLOCK)
                .pattern("###")
                .pattern("###")
                .unlockedBy(BLOCK_ACHIEVEMENT, has(ModBlocksRegister.BLOCK_METAL_WALL.get(MetalTagEnum.COPPER.getGemNameLower())))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_copper_wall"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,ModBlocksRegister.BLOCK_METAL_WALL.get(MetalTagEnum.GOLD.getGemNameLower() + "_raw"),3)
                .define('#', Items.RAW_GOLD_BLOCK)
                .pattern("###")
                .pattern("###")
                .unlockedBy(BLOCK_ACHIEVEMENT, has(ModBlocksRegister.BLOCK_METAL_WALL.get(MetalTagEnum.GOLD.getGemNameLower() + "_raw")))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_gold_raw_wall"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,ModBlocksRegister.BLOCK_METAL_WALL.get(MetalTagEnum.IRON.getGemNameLower() + "_raw"),3)
                .define('#', Items.RAW_IRON_BLOCK)
                .pattern("###")
                .pattern("###")
                .unlockedBy(BLOCK_ACHIEVEMENT, has(ModBlocksRegister.BLOCK_METAL_WALL.get(MetalTagEnum.IRON.getGemNameLower() + "_raw")))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_iron_raw_wall"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,ModBlocksRegister.BLOCK_METAL_WALL.get("copper_raw"),3)
                .define('#', Items.RAW_COPPER_BLOCK)
                .pattern("###")
                .pattern("###")
                .unlockedBy(BLOCK_ACHIEVEMENT, has(ModBlocksRegister.BLOCK_METAL_WALL.get("copper_raw")))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_copper_raw_wall"));



        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,ModBlocksRegister.BLOCK_METAL_FENCE.get("gold"),3)
                .define('#', Blocks.GOLD_BLOCK)
                .define('*', Items.GOLD_INGOT)
                .pattern("#*#")
                .pattern("#*#")
                .unlockedBy(BLOCK_ACHIEVEMENT, has(ModBlocksRegister.BLOCK_METAL_FENCE.get("gold")))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_gold_fence"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,ModBlocksRegister.BLOCK_METAL_FENCE.get("iron"),3)
                .define('#', Blocks.IRON_BLOCK)
                .define('*', Items.IRON_INGOT)
                .pattern("#*#")
                .pattern("#*#")
                .unlockedBy(BLOCK_ACHIEVEMENT, has(ModBlocksRegister.BLOCK_METAL_FENCE.get("iron")))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_iron_fence"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,ModBlocksRegister.BLOCK_METAL_FENCE.get(MetalTagEnum.COPPER.getGemNameLower()),3)
                .define('#', Blocks.COPPER_BLOCK)
                .define('*', Items.COPPER_INGOT)
                .pattern("#*#")
                .pattern("#*#")
                .unlockedBy(BLOCK_ACHIEVEMENT, has(ModBlocksRegister.BLOCK_METAL_FENCE.get(MetalTagEnum.COPPER.getGemNameLower())))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_copper_fence"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,ModBlocksRegister.BLOCK_METAL_FENCE.get(MetalTagEnum.IRON.getGemNameLower() + "_raw"),3)
                .define('#', Blocks.RAW_IRON_BLOCK)
                .define('*', Items.RAW_IRON)
                .pattern("#*#")
                .pattern("#*#")
                .unlockedBy(BLOCK_ACHIEVEMENT, has(ModBlocksRegister.BLOCK_METAL_FENCE.get(MetalTagEnum.IRON.getGemNameLower() + "_raw")))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_iron_raw_fence"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,ModBlocksRegister.BLOCK_METAL_FENCE.get(MetalTagEnum.GOLD.getGemNameLower() + "_raw"),3)
                .define('#', Items.RAW_GOLD_BLOCK)
                .define('*', Items.RAW_GOLD)
                .pattern("#*#")
                .pattern("#*#")
                .unlockedBy(BLOCK_ACHIEVEMENT, has(ModBlocksRegister.BLOCK_METAL_FENCE.get(MetalTagEnum.GOLD.getGemNameLower() + "_raw")))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_gold_raw_fence"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,ModBlocksRegister.BLOCK_METAL_FENCE.get("copper_raw"),3)
                .define('#', Items.RAW_COPPER_BLOCK)
                .define('*', Items.RAW_COPPER)
                .pattern("#*#")
                .pattern("#*#")
                .unlockedBy(BLOCK_ACHIEVEMENT, has(ModBlocksRegister.BLOCK_METAL_FENCE.get("copper_raw")))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_copper_raw_fence"));



        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,ModBlocksRegister.BLOCK_METAL_FENCE_GATE.get("gold"),3)
                .define('#', Blocks.GOLD_BLOCK)
                .define('*', Items.GOLD_INGOT)
                .pattern("*#*")
                .pattern("*#*")
                .unlockedBy(BLOCK_ACHIEVEMENT, has(ModBlocksRegister.BLOCK_METAL_FENCE_GATE.get("gold")))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_gold_fence_gate"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,ModBlocksRegister.BLOCK_METAL_FENCE_GATE.get("iron"),3)
                .define('#', Blocks.IRON_BLOCK)
                .define('*', Items.IRON_INGOT)
                .pattern("*#*")
                .pattern("*#*")
                .unlockedBy(BLOCK_ACHIEVEMENT, has(ModBlocksRegister.BLOCK_METAL_FENCE_GATE.get("iron")))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_iron_fence_gate"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,ModBlocksRegister.BLOCK_METAL_FENCE_GATE.get(MetalTagEnum.COPPER.getGemNameLower()),3)
                .define('#', Blocks.COPPER_BLOCK)
                .define('*', Items.COPPER_INGOT)
                .pattern("*#*")
                .pattern("*#*")
                .unlockedBy(BLOCK_ACHIEVEMENT, has(ModBlocksRegister.BLOCK_METAL_FENCE_GATE.get("iron")))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_copper_fence_gate"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,ModBlocksRegister.BLOCK_METAL_FENCE_GATE.get(MetalTagEnum.IRON.getGemNameLower() + "_raw"),3)
                .define('#', Blocks.RAW_IRON_BLOCK)
                .define('*', Items.RAW_IRON)
                .pattern("*#*")
                .pattern("*#*")
                .unlockedBy(BLOCK_ACHIEVEMENT, has(ModBlocksRegister.BLOCK_METAL_FENCE_GATE.get(MetalTagEnum.IRON.getGemNameLower() + "_raw")))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_iron_raw_fence_gate"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,ModBlocksRegister.BLOCK_METAL_FENCE_GATE.get(MetalTagEnum.GOLD.getGemNameLower() + "_raw"),3)
                .define('#', Items.RAW_GOLD_BLOCK)
                .define('*', Items.RAW_GOLD)
                .pattern("*#*")
                .pattern("*#*")
                .unlockedBy(BLOCK_ACHIEVEMENT, has(ModBlocksRegister.BLOCK_METAL_FENCE_GATE.get(MetalTagEnum.GOLD.getGemNameLower() + "_raw")))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_gold_raw_fence_gate"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocksRegister.BLOCK_METAL_FENCE_GATE.get("copper_raw"),3)
                .define('#', Blocks.RAW_COPPER_BLOCK)
                .define('*', Items.RAW_COPPER)
                .pattern("*#*")
                .pattern("*#*")
                .unlockedBy(BLOCK_ACHIEVEMENT, has(ModBlocksRegister.BLOCK_METAL_FENCE_GATE.get("iron")))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_copper_raw_fence_gate"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,ModBlocksRegister.CRUCIBLE_FURNACE.get())
                .define('#', Blocks.SMOOTH_STONE)
                .define('*', Blocks.BLAST_FURNACE)
                .define('x', Blocks.BRICKS)
                .define('-', Blocks.IRON_BLOCK)
                .pattern("###")
                .pattern("-*-")
                .pattern("xxx")
                .unlockedBy(BLOCK_ACHIEVEMENT, has(ModBlocksRegister.CRUCIBLE_FURNACE.get()))
                .save(recipesConsumer, new ResourceLocation(ModBlocksRegister.CRUCIBLE_FURNACE.get().getDescriptionId()));

    }

    private void iconsRecipe(Consumer<FinishedRecipe> recipesConsumer) {

        ModItemsRegister.ITEM_ICONS_ALLOMANCY.forEach((key, value) -> ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS,value)
                .define('#',Items.GLASS_PANE)
                .define('x',(key.contains("gold")) ? Tags.Items.INGOTS_GOLD : (key.contains(MetalTagEnum.COPPER.getGemNameLower())) ? Tags.Items.INGOTS_COPPER : (key.contains("iron")) ? Tags.Items.INGOTS_IRON: ModTags.INGOTS.get(key))
                .pattern(" # ")
                .pattern("#x#")
                .pattern(" # ")
                .unlockedBy(ITEM_ACHIEVEMENT, has(value))
                .save(recipesConsumer,new ResourceLocation(key + "_allomantic_icon")));


        ModItemsRegister.ITEM_ICONS_FERUCHEMIC.forEach((key, value) -> ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS,value)
                .define('#',Items.GLASS_PANE)
                .define('x',(key.contains("gold")) ? Tags.Items.INGOTS_GOLD : (key.contains(MetalTagEnum.COPPER.getGemNameLower())) ? Tags.Items.INGOTS_COPPER : (key.contains("iron")) ? Tags.Items.INGOTS_IRON: ModTags.INGOTS.get(key))
                .pattern("# #")
                .pattern(" x ")
                .pattern("# #")
                .unlockedBy(ITEM_ACHIEVEMENT, has(value))
                .save(recipesConsumer,new ResourceLocation(key+"_feruchemic_icon")));
    }

    @SuppressWarnings("removal")
    private void combatRecipe(Consumer<FinishedRecipe> recipesConsumer) {

        Arrays.asList(SpikeEnum.values()).forEach(object -> {

            TagKey<Item> head;
            TagKey<Item> body;

            if (object.isGems()) {
                head = ModTags.METAL_BLOCKS.get(object.getName());
                body = ModTags.GEMS.get(object.getName());
            } else if (object.isVanilla()) {
                if (object.getName().equals("iron")) {
                    head = Tags.Items.STORAGE_BLOCKS_IRON;
                    body = Tags.Items.INGOTS_IRON;
                } else if (object.getName().equals("gold")) {
                    head = Tags.Items.STORAGE_BLOCKS_GOLD;
                    body = Tags.Items.INGOTS_GOLD;
                } else {
                    head = Tags.Items.STORAGE_BLOCKS_COPPER;
                    body = Tags.Items.INGOTS_COPPER;
                }
            } else {
                head = ModTags.METAL_BLOCKS.get(object.getName());
                body = ModTags.INGOTS.get(object.getName());
            }

            ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, object.getSpike())
                    .define('#', head)
                    .define('x', body)
                    .pattern("   ")
                    .pattern("xx#")
                    .pattern("   ")
                    .unlockedBy(ITEM_ACHIEVEMENT, has(object.getSpike()))
                    .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_spike_" + object.getName()));
        });

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItemsRegister.OBSIDIAN_AXE.get())
                .define('O', ModItemsRegister.OBSIDIAN_CORE.get())
                .define('#', Items.OBSIDIAN)
                .define('x', ModTags.INGOTS.get(MetalEnum.ALUMINUM.getMetalNameLower()))
                .pattern(" O#")
                .pattern(" x#")
                .pattern(" x ")
                .unlockedBy(ITEM_ACHIEVEMENT,has(ModItemsRegister.OBSIDIAN_AXE.get()))
                .save(recipesConsumer,new ResourceLocation(MetallicsArts.MOD_ID + "_obsidian_axe"));

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItemsRegister.OBSIDIAN_DAGGER.get())
                .define('#',Items.OBSIDIAN)
                .define('x',ModItemsRegister.ITEM_METAL_INGOT.get("aluminum"))
                .pattern("  #")
                .pattern(" # ")
                .pattern("x  ")
                .unlockedBy(ITEM_ACHIEVEMENT,has(ModItemsRegister.OBSIDIAN_DAGGER.get()))
                .save(recipesConsumer,new ResourceLocation(MetallicsArts.MOD_ID+"_obsidian_dagger"));


        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItemsRegister.DUELING_STAFF.get())
                .define('#', ModItemsRegister.ITEM_METAL_INGOT.get("aluminum"))
                .define('x',Items.STICK)
                .pattern("  #")
                .pattern(" x ")
                .pattern("x  ")
                .unlockedBy(ITEM_ACHIEVEMENT,has(ModItemsRegister.DUELING_STAFF.get()))
                .save(recipesConsumer,new ResourceLocation(MetallicsArts.MOD_ID+"_dueling_staff"));

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItemsRegister.SILVER_KNIFE.get())
                .define('#', ModTags.INGOTS.get("silver"))
                .define('x', Items.STICK)
                .pattern("  #")
                .pattern(" # ")
                .pattern("x  ")
                .unlockedBy(ITEM_ACHIEVEMENT,has(ModItemsRegister.SILVER_KNIFE.get()))
                .save(recipesConsumer,new ResourceLocation(MetallicsArts.MOD_ID + "_silver_knife"));

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItemsRegister.KOLOSS_BLADE.get())
                .define('#', Items.COBBLESTONE)
                .define('*', ModItemsRegister.ITEM_METAL_INGOT.get("steel"))
                .define('x', Items.STICK)
                .pattern("** ")
                .pattern("*#*")
                .pattern(" x ")
                .unlockedBy(ITEM_ACHIEVEMENT,has(ModItemsRegister.KOLOSS_BLADE.get()))
                .save(recipesConsumer,new ResourceLocation(MetallicsArts.MOD_ID + "_koloss_blade"));


        for (String panels: ModBlocksRegister.METAL_PANELS.keySet()) {
            if (panels.contains("variant")) {
                for (MetalTagEnum metal : MetalTagEnum.values()) {
                    if (panels.contains(metal.getNameLower())) {
                        SingleItemRecipeBuilder.stonecutting(
                                Ingredient.of(ModBlocksRegister.METAL_PANELS.get(metal.getNameLower()+"_panel").asItem()),
                                RecipeCategory.BUILDING_BLOCKS,
                                ModBlocksRegister.METAL_PANELS.get(panels),
                                1)
                                .unlockedBy(BLOCK_ACHIEVEMENT, has(ModBlocksRegister.METAL_PANELS.get(panels)))
                                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_" + panels));
                    }
                }
            }
        }


        LegacyUpgradeRecipeBuilder.smithing(
                        Ingredient.of(Items.NETHERITE_CHESTPLATE),
                        Ingredient.of(ModItemsRegister.STEEL_CORE.get()),
                        RecipeCategory.COMBAT,
                        ModItemsRegister.STEEL_ARMOR.get(ArmorPiecesEnum.CHESTPLATE).get())
                .unlocks(ARMOR_ACHIEVEMENT, has(ModItemsRegister.STEEL_ARMOR.get(ArmorPiecesEnum.CHESTPLATE).get()))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_armor_steel_chestplate"));


        LegacyUpgradeRecipeBuilder.smithing(
                        Ingredient.of(Items.NETHERITE_LEGGINGS),
                        Ingredient.of(ModItemsRegister.STEEL_CORE.get()),
                        RecipeCategory.COMBAT,
                        ModItemsRegister.STEEL_ARMOR.get(ArmorPiecesEnum.LEGGINGS).get())
                .unlocks(ARMOR_ACHIEVEMENT, has(ModItemsRegister.STEEL_ARMOR.get(ArmorPiecesEnum.LEGGINGS).get()))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_armor_steel_leggings"));

        LegacyUpgradeRecipeBuilder.smithing(
                        Ingredient.of(Items.NETHERITE_BOOTS),
                        Ingredient.of(ModItemsRegister.STEEL_CORE.get()),
                        RecipeCategory.COMBAT,
                        ModItemsRegister.STEEL_ARMOR.get(ArmorPiecesEnum.BOOTS).get())
                .unlocks(ARMOR_ACHIEVEMENT, has(ModItemsRegister.STEEL_ARMOR.get(ArmorPiecesEnum.BOOTS).get()))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_armor_steel_boots"));

        LegacyUpgradeRecipeBuilder.smithing(
                        Ingredient.of(Items.NETHERITE_HELMET),
                        Ingredient.of(ModItemsRegister.ALUMINUM_CORE.get()),
                        RecipeCategory.COMBAT,
                        ModItemsRegister.ALUMINUM_ARMOR.get(ArmorPiecesEnum.HELMET).get())
                .unlocks(ARMOR_ACHIEVEMENT, has(ModItemsRegister.ALUMINUM_ARMOR.get(ArmorPiecesEnum.HELMET).get()))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_armor_aluminum_helmet"));

        LegacyUpgradeRecipeBuilder.smithing(
                        Ingredient.of(Items.NETHERITE_CHESTPLATE),
                        Ingredient.of(ModItemsRegister.ALUMINUM_CORE.get()),
                        RecipeCategory.COMBAT,
                        ModItemsRegister.ALUMINUM_ARMOR.get(ArmorPiecesEnum.CHESTPLATE).get())
                .unlocks(ARMOR_ACHIEVEMENT, has(ModItemsRegister.ALUMINUM_ARMOR.get(ArmorPiecesEnum.CHESTPLATE).get()))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_armor_aluminum_chestplate"));

        LegacyUpgradeRecipeBuilder.smithing(
                        Ingredient.of(Items.NETHERITE_LEGGINGS),
                        Ingredient.of(ModItemsRegister.ALUMINUM_CORE.get()),
                        RecipeCategory.COMBAT,
                        ModItemsRegister.ALUMINUM_ARMOR.get(ArmorPiecesEnum.LEGGINGS).get())
                .unlocks(ARMOR_ACHIEVEMENT, has(ModItemsRegister.ALUMINUM_ARMOR.get(ArmorPiecesEnum.LEGGINGS).get()))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_armor_aluminum_leggings"));

        LegacyUpgradeRecipeBuilder.smithing(
                        Ingredient.of(Items.NETHERITE_BOOTS),
                        Ingredient.of(ModItemsRegister.ALUMINUM_CORE.get()),
                        RecipeCategory.COMBAT,
                        ModItemsRegister.ALUMINUM_ARMOR.get(ArmorPiecesEnum.BOOTS).get())
                .unlocks(ARMOR_ACHIEVEMENT, has(ModItemsRegister.ALUMINUM_ARMOR.get(ArmorPiecesEnum.BOOTS).get()))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_armor_aluminum_boots"));

        LegacyUpgradeRecipeBuilder.smithing(
                        Ingredient.of(Items.NETHERITE_HELMET),
                        Ingredient.of(ModItemsRegister.ATIUM_CORE.get()),
                        RecipeCategory.COMBAT,
                        ModItemsRegister.ATIUM_ARMOR.get(ArmorPiecesEnum.HELMET).get())
                .unlocks(ARMOR_ACHIEVEMENT, has(ModItemsRegister.ATIUM_ARMOR.get(ArmorPiecesEnum.HELMET).get()))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_armor_atium_helmet"));

        LegacyUpgradeRecipeBuilder.smithing(
                        Ingredient.of(Items.NETHERITE_CHESTPLATE),
                        Ingredient.of(ModItemsRegister.ATIUM_CORE.get()),
                        RecipeCategory.COMBAT,
                        ModItemsRegister.ATIUM_ARMOR.get(ArmorPiecesEnum.CHESTPLATE).get())
                .unlocks(ARMOR_ACHIEVEMENT, has(ModItemsRegister.ATIUM_ARMOR.get(ArmorPiecesEnum.CHESTPLATE).get()))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_armor_atium_chestplate"));

        LegacyUpgradeRecipeBuilder.smithing(
                        Ingredient.of(Items.NETHERITE_LEGGINGS),
                        Ingredient.of(ModItemsRegister.ATIUM_CORE.get()),
                        RecipeCategory.COMBAT,
                        ModItemsRegister.ATIUM_ARMOR.get(ArmorPiecesEnum.LEGGINGS).get())
                .unlocks(ARMOR_ACHIEVEMENT, has(ModItemsRegister.ATIUM_ARMOR.get(ArmorPiecesEnum.LEGGINGS).get()))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_armor_atium_leggings"));

        LegacyUpgradeRecipeBuilder.smithing(
                        Ingredient.of(Items.NETHERITE_BOOTS),
                        Ingredient.of(ModItemsRegister.ATIUM_CORE.get()),
                        RecipeCategory.COMBAT,
                        ModItemsRegister.ATIUM_ARMOR.get(ArmorPiecesEnum.BOOTS).get())
                .unlocks(ARMOR_ACHIEVEMENT, has(ModItemsRegister.ATIUM_ARMOR.get(ArmorPiecesEnum.BOOTS).get()))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_armor_atium_boots"));

        LegacyUpgradeRecipeBuilder.smithing(
                        Ingredient.of(Items.NETHERITE_HELMET),
                        Ingredient.of(ModItemsRegister.LERASIUM_CORE.get()),
                        RecipeCategory.COMBAT,
                        ModItemsRegister.LERASIUM_ARMOR.get(ArmorPiecesEnum.HELMET).get())
                .unlocks(ARMOR_ACHIEVEMENT, has(ModItemsRegister.LERASIUM_ARMOR.get(ArmorPiecesEnum.HELMET).get()))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_armor_lerasium_helmet"));

        LegacyUpgradeRecipeBuilder.smithing(
                        Ingredient.of(Items.NETHERITE_CHESTPLATE),
                        Ingredient.of(ModItemsRegister.LERASIUM_CORE.get()),
                        RecipeCategory.COMBAT,
                        ModItemsRegister.LERASIUM_ARMOR.get(ArmorPiecesEnum.CHESTPLATE).get())
                .unlocks(ARMOR_ACHIEVEMENT, has(ModItemsRegister.LERASIUM_ARMOR.get(ArmorPiecesEnum.CHESTPLATE).get()))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_armor_lerasium_chestplate"));

        LegacyUpgradeRecipeBuilder.smithing(
                        Ingredient.of(Items.NETHERITE_LEGGINGS),
                        Ingredient.of(ModItemsRegister.LERASIUM_CORE.get()),
                        RecipeCategory.COMBAT,
                        ModItemsRegister.LERASIUM_ARMOR.get(ArmorPiecesEnum.LEGGINGS).get())
                .unlocks(ARMOR_ACHIEVEMENT, has(ModItemsRegister.LERASIUM_ARMOR.get(ArmorPiecesEnum.LEGGINGS).get()))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_armor_lerasium_leggings"));

        LegacyUpgradeRecipeBuilder.smithing(
                        Ingredient.of(Items.NETHERITE_BOOTS),
                        Ingredient.of(ModItemsRegister.LERASIUM_CORE.get()),
                        RecipeCategory.COMBAT,
                        ModItemsRegister.LERASIUM_ARMOR.get(ArmorPiecesEnum.BOOTS).get())
                .unlocks(ARMOR_ACHIEVEMENT, has(ModItemsRegister.LERASIUM_ARMOR.get(ArmorPiecesEnum.BOOTS).get()))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_armor_lerasium_boots"));

        LegacyUpgradeRecipeBuilder.smithing(
                        Ingredient.of(Items.NETHERITE_HELMET),
                        Ingredient.of(ModItemsRegister.ETTMETAL_CORE.get()),
                        RecipeCategory.COMBAT,
                        ModItemsRegister.ETTMETAL_ARMOR.get(ArmorPiecesEnum.HELMET).get())
                .unlocks(ARMOR_ACHIEVEMENT, has(ModItemsRegister.ETTMETAL_ARMOR.get(ArmorPiecesEnum.HELMET).get()))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_armor_ettmetal_helmet"));

        LegacyUpgradeRecipeBuilder.smithing(
                        Ingredient.of(Items.NETHERITE_CHESTPLATE),
                        Ingredient.of(ModItemsRegister.ETTMETAL_CORE.get()),
                        RecipeCategory.COMBAT,
                        ModItemsRegister.ETTMETAL_ARMOR.get(ArmorPiecesEnum.CHESTPLATE).get())
                .unlocks(ARMOR_ACHIEVEMENT, has(ModItemsRegister.ETTMETAL_ARMOR.get(ArmorPiecesEnum.CHESTPLATE).get()))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_armor_ettmetal_chestplate"));

        LegacyUpgradeRecipeBuilder.smithing(
                        Ingredient.of(Items.NETHERITE_LEGGINGS),
                        Ingredient.of(ModItemsRegister.ETTMETAL_CORE.get()),
                        RecipeCategory.COMBAT,
                        ModItemsRegister.ETTMETAL_ARMOR.get(ArmorPiecesEnum.LEGGINGS).get())
                .unlocks(ARMOR_ACHIEVEMENT, has(ModItemsRegister.ETTMETAL_ARMOR.get(ArmorPiecesEnum.LEGGINGS).get()))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_armor_ettmetal_leggings"));

        LegacyUpgradeRecipeBuilder.smithing(
                        Ingredient.of(Items.NETHERITE_BOOTS),
                        Ingredient.of(ModItemsRegister.ETTMETAL_CORE.get()),
                        RecipeCategory.COMBAT,
                        ModItemsRegister.ETTMETAL_ARMOR.get(ArmorPiecesEnum.BOOTS).get())
                .unlocks(ARMOR_ACHIEVEMENT, has(ModItemsRegister.ETTMETAL_ARMOR.get(ArmorPiecesEnum.BOOTS).get()))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_armor_ettmetal_boots"));


        LegacyUpgradeRecipeBuilder.smithing(
                        Ingredient.of(Items.NETHERITE_HELMET),
                        Ingredient.of(ModItemsRegister.COPPER_CORE.get()),
                        RecipeCategory.COMBAT,
                        ModItemsRegister.COPPER_ARMOR.get(ArmorPiecesEnum.HELMET).get())
                .unlocks(ARMOR_ACHIEVEMENT, has(ModItemsRegister.COPPER_ARMOR.get(ArmorPiecesEnum.HELMET).get()))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_armor_copper_helmet"));

        LegacyUpgradeRecipeBuilder.smithing(
                        Ingredient.of(Items.NETHERITE_CHESTPLATE),
                        Ingredient.of(ModItemsRegister.COPPER_CORE.get()),
                        RecipeCategory.COMBAT,
                        ModItemsRegister.COPPER_ARMOR.get(ArmorPiecesEnum.CHESTPLATE).get())
                .unlocks(ARMOR_ACHIEVEMENT, has(ModItemsRegister.COPPER_ARMOR.get(ArmorPiecesEnum.CHESTPLATE).get()))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_armor_copper_chestplate"));

        LegacyUpgradeRecipeBuilder.smithing(
                        Ingredient.of(Items.NETHERITE_LEGGINGS),
                        Ingredient.of(ModItemsRegister.COPPER_CORE.get()),
                        RecipeCategory.COMBAT,
                        ModItemsRegister.COPPER_ARMOR.get(ArmorPiecesEnum.LEGGINGS).get())
                .unlocks(ARMOR_ACHIEVEMENT, has(ModItemsRegister.COPPER_ARMOR.get(ArmorPiecesEnum.LEGGINGS).get()))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_armor_copper_leggings"));

        LegacyUpgradeRecipeBuilder.smithing(
                        Ingredient.of(Items.NETHERITE_BOOTS),
                        Ingredient.of(ModItemsRegister.COPPER_CORE.get()),
                        RecipeCategory.COMBAT,
                        ModItemsRegister.COPPER_ARMOR.get(ArmorPiecesEnum.BOOTS).get())
                .unlocks(ARMOR_ACHIEVEMENT, has(ModItemsRegister.COPPER_ARMOR.get(ArmorPiecesEnum.BOOTS).get()))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_armor_copper_boots"));

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItemsRegister.MISTCLOACK.get())
                .define('#', Items.STRING)
                .define('*', Items.PHANTOM_MEMBRANE)
                .define('X', Items.BLACK_WOOL)
                .pattern("XXX")
                .pattern("###")
                .pattern("***")
                .unlockedBy(BLOCK_ACHIEVEMENT, has(ModItemsRegister.MISTCLOACK.get()))
                .save(recipesConsumer, new ResourceLocation(ModItemsRegister.MISTCLOACK.get().getDescriptionId()));

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItemsRegister.WOOD_SHIELD.get())
                .define('#', ItemTags.PLANKS)
                .define('*', ItemTags.LOGS)
                .pattern("###")
                .pattern("#*#")
                .pattern(" # ")
                .unlockedBy(BLOCK_ACHIEVEMENT, has(ModItemsRegister.WOOD_SHIELD.get()))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_" + Shields.WOOD.getId()));

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItemsRegister.BRONZE_ALUMINUM_SHIELD.get())
                .define('#', ModTags.INGOTS.get(MetalTagEnum.ALUMINUM.getMetalNameLower()))
                .define('*', ModTags.METAL_BLOCKS.get(MetalTagEnum.BRONZE.getMetalNameLower()))
                .pattern("###")
                .pattern("#*#")
                .pattern(" # ")
                .unlockedBy(BLOCK_ACHIEVEMENT, has(ModItemsRegister.BRONZE_ALUMINUM_SHIELD.get()))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_" + Shields.BRONZE_ALUMINUM.getId()));

    }

    private void metallicsArtsItemsRecipes(Consumer<FinishedRecipe> recipesConsumer) {
        Arrays.asList(MetalMindEnum.values()).forEach(object -> {
            TagKey<Item> item1;
            TagKey<Item> item2;
            //BLOCKS
            if (object.isVanilla()) {
                if (object.getFirstMetal().equals("iron")) {
                    item1 = Tags.Items.STORAGE_BLOCKS_IRON;
                }
                else if (object.getFirstMetal().equals("gold")) {
                    item1 = Tags.Items.STORAGE_BLOCKS_GOLD;
                }
                else {
                    item1 = Tags.Items.STORAGE_BLOCKS_COPPER;
                }
            } else {
                item1 = ModTags.METAL_BLOCKS.get(object.getFirstMetal());
            }
            item2 = ModTags.METAL_BLOCKS.get(object.getSecondMetal());
            //BANDS

            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, object.getBand())
                    .define('#', item1)
                    .define('x', item2)
                    .pattern("xxx")
                    .pattern("# x")
                    .pattern("###")
                    .unlockedBy(ITEM_ACHIEVEMENT, has(object.getBand()))
                    .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_band_" + object.getFirstMetal() + "_" + object.getSecondMetal()));

            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, object.getBand())
                    .define('#', item2)
                    .define('x', item1)
                    .pattern("xxx")
                    .pattern("# x")
                    .pattern("###")
                    .unlockedBy(ITEM_ACHIEVEMENT, has(object.getBand()))
                    .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_band_" + object.getFirstMetal() + "_" + object.getSecondMetal() + "_2"));


            if (object.isGems()) {
                item1 = ModTags.GEMS.get(object.getFirstMetal());
                item2 = ModTags.GEMS.get(object.getSecondMetal());
            } else if (object.isVanilla()) {
                if (object.getFirstMetal().equals("iron")) {
                    item1 = Tags.Items.INGOTS_IRON;
                }
                else if (object.getFirstMetal().equals("gold")) {
                    item1 = Tags.Items.INGOTS_GOLD;
                }
                else {
                    item1 = Tags.Items.INGOTS_COPPER;
                }
                item2 = ModTags.INGOTS.get(object.getSecondMetal());
            } else {
                item1 = ModTags.INGOTS.get(object.getFirstMetal());
                item2 = ModTags.INGOTS.get(object.getSecondMetal());
            }
            //RING
            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, object.getRing())
                    .define('#', item1)
                    .define('x', item2)
                    .pattern("xxx")
                    .pattern("# x")
                    .pattern("###")
                    .unlockedBy(ITEM_ACHIEVEMENT, has(object.getRing()))
                    .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_ring_" + object.getFirstMetal() + "_" + object.getSecondMetal()));

            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, object.getRing())
                    .define('#', item2)
                    .define('x', item1)
                    .pattern("xxx")
                    .pattern("# x")
                    .pattern("###")
                    .unlockedBy(ITEM_ACHIEVEMENT, has(object.getRing()))
                    .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_ring_" + object.getFirstMetal() + "_" + object.getSecondMetal() + "_2"));

        });

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItemsRegister.SMALL_VIAL.get())
                .define('#',Items.GLASS)
                .define('x',Items.GLASS_BOTTLE)
                .define('+',ItemTags.PLANKS)
                .pattern(" + ")
                .pattern("#x#")
                .pattern(" # ")
                .unlockedBy(ITEM_ACHIEVEMENT,has(ModItemsRegister.SMALL_VIAL.get()))
                .save(recipesConsumer,new ResourceLocation("allomantic_small_vial"));

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItemsRegister.LARGE_VIAL.get())
                .define('#',Items.GLASS)
                .define('x', ModItemsRegister.SMALL_VIAL.get())
                .define('+',ItemTags.PLANKS)
                .pattern(" + ")
                .pattern("#x#")
                .pattern(" # ")
                .unlockedBy(ITEM_ACHIEVEMENT,has(ModItemsRegister.LARGE_VIAL.get()))
                .save(recipesConsumer,new ResourceLocation("allomantic_large_vial"));
    }

    private void basicRecipes(Consumer<FinishedRecipe> recipesConsumer) {
        ModItemsRegister.ITEM_METAL_INGOT.forEach((name, item) ->
                ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,item.asItem(), 9)
                        .requires(ModTags.METAL_BLOCKS.get(name))
                        .unlockedBy(ITEM_ACHIEVEMENT, has(item))
                        .save(recipesConsumer, new ResourceLocation(name + "_block_to_" + name + "_ingot")));

        ModBlocksRegister.BLOCK_METAL_BLOCKS.forEach((name, block) ->
                ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,block)
                        .define('#', ModTags.INGOTS.get(name))
                        .pattern("###")
                        .pattern("###")
                        .pattern("###")
                        .unlockedBy(BLOCK_ACHIEVEMENT, has(block))
                        .save(recipesConsumer, new ResourceLocation(name + "_ingot_to_" + name + "_block")));

        ModBlocksRegister.RAW_METAL_BLOCKS.forEach((name, block) ->
                ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,block)
                        .define('#', ModTags.RAWS.get(name))
                        .pattern("###")
                        .pattern("###")
                        .pattern("###")
                        .unlockedBy(BLOCK_ACHIEVEMENT, has(block))
                        .save(recipesConsumer, new ResourceLocation(name + "_raw_to_" + name + "_block")));

        ModItemsRegister.ITEM_RAW_METAL.forEach((name, item) ->
                ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, item.asItem(), 9)
                        .requires(ModTags.RAW_BLOCKS.get(name))
                        .unlockedBy(ITEM_ACHIEVEMENT, has(item))
                        .save(recipesConsumer, new ResourceLocation(name + "_raw_block_to_" + name + "_raw")));



        ModItemsRegister.ITEM_GEMS_BASE.forEach((name, item) ->
                ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, item.asItem(), 9)
                        .requires(ModTags.METAL_BLOCKS.get(name))
                        .unlockedBy(ITEM_ACHIEVEMENT, has(item))
                        .save(recipesConsumer, new ResourceLocation(name + "_block" + "_to_" + name + "_gem")));

        ModBlocksRegister.BLOCK_GEMS_BLOCKS.forEach((name, block) ->
                ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,block)
                        .define('#', ModTags.GEMS.get(name))
                        .pattern("###")
                        .pattern("###")
                        .pattern("###")
                        .unlockedBy(BLOCK_ACHIEVEMENT, has(block))
                        .save(recipesConsumer, new ResourceLocation(name + "_gem_to_" + name + "_block")));

        ModItemsRegister.ITEM_METAL_INGOT.forEach((name, item) ->
                ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC ,ModItemsRegister.ITEM_METAL_NUGGET.get(name), 9)
                        .requires(ModTags.INGOTS.get(name))
                        .unlockedBy(BLOCK_ACHIEVEMENT, has(item))
                        .save(recipesConsumer, new ResourceLocation(name + "_ingot_to_" + name + "_nugget")));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC ,ModItemsRegister.ITEM_METAL_NUGGET.get(MetalTagEnum.COPPER.getGemNameLower()), 9)
                .requires(Tags.Items.INGOTS_COPPER)
                .unlockedBy(BLOCK_ACHIEVEMENT, has(ModTags.NUGGETS.get(MetalTagEnum.COPPER.getGemNameLower())))
                .save(recipesConsumer, new ResourceLocation("copper_ingot_to_copper_nugget"));


        ModItemsRegister.ITEM_METAL_NUGGET.forEach((name, item) -> ShapedRecipeBuilder.shaped(RecipeCategory.MISC, name.equals(MetalTagEnum.COPPER.getGemNameLower()) ? Items.COPPER_INGOT : ModItemsRegister.ITEM_METAL_INGOT.get(name))
                .define('#', ModTags.NUGGETS.get(name))
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .unlockedBy(BLOCK_ACHIEVEMENT, has(item))
                .save(recipesConsumer, new ResourceLocation(name + "_nugget_to_" + name + "_ingot")));

        ModItemsRegister.ITEM_GEMS_NUGGET.forEach((name, item) ->
                ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, item.asItem(), 9)
                        .requires(ModTags.GEMS.get(name))
                        .unlockedBy(BLOCK_ACHIEVEMENT, has(item))
                        .save(recipesConsumer, new ResourceLocation(name + "_gem_to_" + name + "_nugget")));

        ModItemsRegister.ITEM_GEMS_BASE.forEach((name, item) ->
                ShapedRecipeBuilder.shaped(RecipeCategory.MISC, item)
                        .define('#', ModTags.NUGGETS.get(name))
                        .pattern("###")
                        .pattern("###")
                        .pattern("###")
                        .unlockedBy(BLOCK_ACHIEVEMENT, has(item))
                        .save(recipesConsumer, new ResourceLocation(name + "_nugget_to_" + name + "_gem")));

        Arrays.asList(MetalEnum.values()).forEach(metal -> {
            if (Boolean.FALSE.equals(metal.isAlloy())) {
                SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItemsRegister.ITEM_RAW_METAL.get(metal.getMetalNameLower())), RecipeCategory.MISC,ModItemsRegister.ITEM_METAL_INGOT.get(metal.getMetalNameLower()), 0.5f, 250)
                        .unlockedBy(BLOCK_ACHIEVEMENT, has(ModItemsRegister.ITEM_RAW_METAL.get(metal.getMetalNameLower())))
                        .save(recipesConsumer, new ResourceLocation(metal.getMetalNameLower() + "_raw_to_" + metal.getMetalNameLower() + "_ingot_furnace"));
            }
        });



        Arrays.asList(MetalEnum.values()).forEach(metal -> {
            if (Boolean.FALSE.equals(metal.isAlloy())) {
                SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModItemsRegister.ITEM_RAW_METAL.get(metal.getMetalNameLower())), RecipeCategory.MISC,ModItemsRegister.ITEM_METAL_INGOT.get(metal.getMetalNameLower()), 0.8f, 100)
                        .unlockedBy(BLOCK_ACHIEVEMENT, has(ModItemsRegister.ITEM_RAW_METAL.get(metal.getMetalNameLower())))
                        .save(recipesConsumer, new ResourceLocation(metal.getMetalNameLower() + "_raw_to_" + metal.getMetalNameLower() + "_ingot_blast"));
            }
        });


        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,ModItemsRegister.OBSIDIAN_CORE.get())
                .define('#', Items.OBSIDIAN)
                .define('*', Items.NETHERITE_SCRAP)
                .define('X', Items.GOLD_INGOT)
                .pattern("#X#")
                .pattern("X*X")
                .pattern("#X#")
                .unlockedBy(BLOCK_ACHIEVEMENT, has(ModItemsRegister.OBSIDIAN_CORE.get()))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_core_obsidian"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,ModItemsRegister.ALUMINUM_CORE.get())
                .define('#', ModBlocksRegister.BLOCK_METAL_BLOCKS.get("aluminum"))
                .define('*', Items.END_CRYSTAL)
                .define('X', Items.NETHERITE_SCRAP)
                .pattern("#X#")
                .pattern("X*X")
                .pattern("#X#")
                .unlockedBy(BLOCK_ACHIEVEMENT, has(ModItemsRegister.ALUMINUM_CORE.get()))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_core_aluminum"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,ModItemsRegister.STEEL_CORE.get())
                .define('#', ModBlocksRegister.BLOCK_METAL_BLOCKS.get("steel"))
                .define('*', Items.END_CRYSTAL)
                .define('X', Items.NETHERITE_SCRAP)
                .pattern("#X#")
                .pattern("X*X")
                .pattern("#X#")
                .unlockedBy(BLOCK_ACHIEVEMENT, has(ModItemsRegister.STEEL_CORE.get()))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_core_steel"));


        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,ModItemsRegister.ATIUM_CORE.get())
                .define('#', ModBlocksRegister.BLOCK_GEMS_BLOCKS.get("atium"))
                .define('*', Items.END_CRYSTAL)
                .define('X', Items.NETHERITE_SCRAP)
                .pattern("#X#")
                .pattern("X*X")
                .pattern("#X#")
                .unlockedBy(BLOCK_ACHIEVEMENT, has(ModItemsRegister.ATIUM_CORE.get()))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_core_atium"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,ModItemsRegister.LERASIUM_CORE.get())
                .define('#', ModBlocksRegister.BLOCK_GEMS_BLOCKS.get("lerasium"))
                .define('*', Items.END_CRYSTAL)
                .define('X', Items.NETHERITE_SCRAP)
                .pattern("#X#")
                .pattern("X*X")
                .pattern("#X#")
                .unlockedBy(BLOCK_ACHIEVEMENT, has(ModItemsRegister.LERASIUM_CORE.get()))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_core_lerasium"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,ModItemsRegister.ETTMETAL_CORE.get())
                .define('#', ModBlocksRegister.BLOCK_GEMS_BLOCKS.get("ettmetal"))
                .define('*', Items.END_CRYSTAL)
                .define('X', Items.NETHERITE_SCRAP)
                .pattern("#X#")
                .pattern("X*X")
                .pattern("#X#")
                .unlockedBy(BLOCK_ACHIEVEMENT, has(ModItemsRegister.ETTMETAL_CORE.get()))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_core_ettmetal"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,ModItemsRegister.COPPER_CORE.get())
                .define('#', Tags.Items.STORAGE_BLOCKS_COPPER)
                .define('*', Items.END_CRYSTAL)
                .define('X', Items.NETHERITE_SCRAP)
                .pattern("#X#")
                .pattern("X*X")
                .pattern("#X#")
                .unlockedBy(BLOCK_ACHIEVEMENT, has(ModItemsRegister.COPPER_CORE.get()))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_core_copper"));

    }


}






