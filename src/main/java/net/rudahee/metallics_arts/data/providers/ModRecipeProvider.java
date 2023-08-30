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
import net.rudahee.metallics_arts.data.enums.implementations.custom_items.MetalMindEnum;
import net.rudahee.metallics_arts.data.enums.implementations.custom_items.Shields;
import net.rudahee.metallics_arts.data.enums.implementations.custom_items.SpikeEnum;
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
     */
    @Override
    protected void buildRecipes(@NotNull Consumer<FinishedRecipe> recipesConsumer) {


        //todo revisar las recipe categorys mas epecificamente para cada tipo de item
        ModItemsRegister.ITEM_METAL_INGOT.forEach((name, item) ->
                ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS,item.asItem(), 9)
                    .requires(ModTags.METAL_BLOCKS.get(name))
                    .unlockedBy("has_item", has(item))
                    .save(recipesConsumer, new ResourceLocation(name + "_block_to_" + name + "_ingot")));

        ModBlocksRegister.BLOCK_METAL_BLOCKS.forEach((name, block) ->
                ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,block)
                    .define('#', ModTags.INGOTS.get(name))
                    .pattern("###")
                    .pattern("###")
                    .pattern("###")
                    .unlockedBy("has_block", has(block))
                    .save(recipesConsumer, new ResourceLocation(name + "_ingot_to_" + name + "_block")));

        ModBlocksRegister.RAW_METAL_BLOCKS.forEach((name, block) ->
                ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,block)
                        .define('#', ModTags.RAWS.get(name))
                        .pattern("###")
                        .pattern("###")
                        .pattern("###")
                        .unlockedBy("has_block", has(block))
                        .save(recipesConsumer, new ResourceLocation(name + "_raw_to_" + name + "_block")));

        ModItemsRegister.ITEM_RAW_METAL.forEach((name, item) ->
                ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, item.asItem(), 9)
                        .requires(ModTags.RAW_BLOCKS.get(name))
                        .unlockedBy("has_item", has(item))
                        .save(recipesConsumer, new ResourceLocation(name + "_raw_block_to_" + name + "_raw")));



        ModItemsRegister.ITEM_GEMS_BASE.forEach((name, item) ->
                ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, item.asItem(), 9)
                        .requires(ModTags.METAL_BLOCKS.get(name))
                        .unlockedBy("has_item", has(item))
                        .save(recipesConsumer, new ResourceLocation(name + "_block" + "_to_" + name + "_gem")));

        ModBlocksRegister.BLOCK_GEMS_BLOCKS.forEach((name, block) ->
                ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,block)
                        .define('#', ModTags.GEMS.get(name))
                        .pattern("###")
                        .pattern("###")
                        .pattern("###")
                        .unlockedBy("has_block", has(block))
                        .save(recipesConsumer, new ResourceLocation(name + "_gem_to_" + name + "_block")));

        ModItemsRegister.ITEM_METAL_INGOT.forEach((name, item) ->
                ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS ,ModItemsRegister.ITEM_METAL_NUGGET.get(name), 9)
                        .requires(ModTags.INGOTS.get(name))
                        .unlockedBy("has_block", has(item))
                        .save(recipesConsumer, new ResourceLocation(name + "_ingot_to_" + name + "_nugget")));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS ,ModItemsRegister.ITEM_METAL_NUGGET.get("copper"), 9)
                .requires(Tags.Items.INGOTS_COPPER)
                .unlockedBy("has_block", has(ModTags.NUGGETS.get("copper")))
                .save(recipesConsumer, new ResourceLocation("copper_ingot_to_copper_nugget"));


        ModItemsRegister.ITEM_METAL_NUGGET.forEach((name, item) -> {
            ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, name.equals("copper") ? Items.COPPER_INGOT : ModItemsRegister.ITEM_METAL_INGOT.get(name))
                    .define('#', ModTags.NUGGETS.get(name))
                    .pattern("###")
                    .pattern("###")
                    .pattern("###")
                    .unlockedBy("has_block", has(item))
                    .save(recipesConsumer, new ResourceLocation(name + "_nugget_to_" + name + "_ingot"));

        });

        ModItemsRegister.ITEM_GEMS_NUGGET.forEach((name, item) ->
                ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, item.asItem(), 9)
                    .requires(ModTags.GEMS.get(name))
                    .unlockedBy("has_block", has(item))
                    .save(recipesConsumer, new ResourceLocation(name + "_gem_to_" + name + "_nugget")));

        ModItemsRegister.ITEM_GEMS_BASE.forEach((name, item) ->
                ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, item)
                    .define('#', ModTags.NUGGETS.get(name))
                    .pattern("###")
                    .pattern("###")
                    .pattern("###")
                    .unlockedBy("has_block", has(item))
                    .save(recipesConsumer, new ResourceLocation(name + "_nugget_to_" + name + "_gem")));

        Arrays.asList(MetalEnum.values()).forEach(metal -> {
            if (!metal.isAlloy()) {
                SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItemsRegister.ITEM_RAW_METAL.get(metal.getMetalNameLower())), RecipeCategory.BUILDING_BLOCKS,ModItemsRegister.ITEM_METAL_INGOT.get(metal.getMetalNameLower()), 0.5f, 250)
                        .unlockedBy("has_block", has(ModItemsRegister.ITEM_RAW_METAL.get(metal.getMetalNameLower())))
                        .save(recipesConsumer, new ResourceLocation(metal.getMetalNameLower() + "_raw_to_" + metal.getMetalNameLower() + "_ingot_furnace"));
            }
        });



        Arrays.asList(MetalEnum.values()).forEach(metal -> {
            if (!metal.isAlloy()) {
                SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModItemsRegister.ITEM_RAW_METAL.get(metal.getMetalNameLower())), RecipeCategory.BUILDING_BLOCKS,ModItemsRegister.ITEM_METAL_INGOT.get(metal.getMetalNameLower()), 0.8f, 100)
                        .unlockedBy("has_block", has(ModItemsRegister.ITEM_RAW_METAL.get(metal.getMetalNameLower())))
                        .save(recipesConsumer, new ResourceLocation(metal.getMetalNameLower() + "_raw_to_" + metal.getMetalNameLower() + "_ingot_blast"));
            }
        });

        Arrays.asList(MetalMindEnum.values()).forEach(object -> {
            TagKey<Item> item1, item2;
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
                    .unlockedBy("has_item", has(object.getBand()))
                    .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_band_" + object.getFirstMetal() + "_" + object.getSecondMetal()));

            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, object.getBand())
                    .define('#', item2)
                    .define('x', item1)
                    .pattern("xxx")
                    .pattern("# x")
                    .pattern("###")
                    .unlockedBy("has_item", has(object.getBand()))
                    .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_band_" + object.getFirstMetal() + "_" + object.getSecondMetal() + "_2"));


            //INGOTS

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
                    .unlockedBy("has_item", has(object.getRing()))
                    .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_ring_" + object.getFirstMetal() + "_" + object.getSecondMetal()));

            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, object.getRing())
                    .define('#', item2)
                    .define('x', item1)
                    .pattern("xxx")
                    .pattern("# x")
                    .pattern("###")
                    .unlockedBy("has_item", has(object.getRing()))
                    .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_ring_" + object.getFirstMetal() + "_" + object.getSecondMetal() + "_2"));

        });

        //SPIKES
        Arrays.asList(SpikeEnum.values()).forEach(object -> {

            TagKey<Item> head, body;
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
                    .unlockedBy("has_item", has(object.getSpike()))
                    .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_spike_" + object.getName()));
        });

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItemsRegister.OBSIDIAN_AXE.get())
                .define('O', ModItemsRegister.CORE_OBSIDIAN.get())
                .define('#', Items.OBSIDIAN)
                .define('x', ModTags.INGOTS.get(MetalEnum.ALUMINUM.getMetalNameLower()))
                .pattern(" O#")
                .pattern(" x#")
                .pattern(" x ")
                .unlockedBy("has_item",has(ModItemsRegister.OBSIDIAN_AXE.get()))
                .save(recipesConsumer,new ResourceLocation(MetallicsArts.MOD_ID + "_obsidian_axe"));

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItemsRegister.OBSIDIAN_DAGGER.get())
                .define('#',Items.OBSIDIAN)
                .define('x',ModItemsRegister.ITEM_METAL_INGOT.get("aluminum"))
                .pattern("  #")
                .pattern(" # ")
                .pattern("x  ")
                .unlockedBy("has_item",has(ModItemsRegister.OBSIDIAN_DAGGER.get()))
                .save(recipesConsumer,new ResourceLocation(MetallicsArts.MOD_ID+"_obsidian_dagger"));


        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItemsRegister.DUELING_STAFF.get())
                .define('#', ModItemsRegister.ITEM_METAL_INGOT.get("aluminum"))
                .define('x',Items.STICK)
                .pattern("  #")
                .pattern(" x ")
                .pattern("x  ")
                .unlockedBy("has_item",has(ModItemsRegister.DUELING_STAFF.get()))
                .save(recipesConsumer,new ResourceLocation(MetallicsArts.MOD_ID+"_dueling_staff"));

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItemsRegister.SILVER_KNIFE.get())
                .define('#', ModTags.INGOTS.get("silver"))
                .define('x', Items.STICK)
                .pattern("  #")
                .pattern(" # ")
                .pattern("x  ")
                .unlockedBy("has_item",has(ModItemsRegister.SILVER_KNIFE.get()))
                .save(recipesConsumer,new ResourceLocation(MetallicsArts.MOD_ID + "_silver_knife"));

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItemsRegister.KOLOSS_BLADE.get())
                .define('#', Items.COBBLESTONE)
                .define('*', ModItemsRegister.ITEM_METAL_INGOT.get("steel"))
                .define('x', Items.STICK)
                .pattern("** ")
                .pattern("*#*")
                .pattern(" x ")
                .unlockedBy("has_item",has(ModItemsRegister.KOLOSS_BLADE.get()))
                .save(recipesConsumer,new ResourceLocation(MetallicsArts.MOD_ID + "_koloss_blade"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BREWING, ModItemsRegister.SMALL_VIAL.get())
                .define('#',Items.GLASS)
                .define('x',Items.GLASS_BOTTLE)
                .define('+',ItemTags.PLANKS)
                .pattern(" + ")
                .pattern("#x#")
                .pattern(" # ")
                .unlockedBy("has_item",has(ModItemsRegister.SMALL_VIAL.get()))
                .save(recipesConsumer,new ResourceLocation("allomantic_small_vial"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BREWING, ModItemsRegister.LARGE_VIAL.get())
                .define('#',Items.GLASS)
                .define('x', ModItemsRegister.SMALL_VIAL.get())
                .define('+',ItemTags.PLANKS)
                .pattern(" + ")
                .pattern("#x#")
                .pattern(" # ")
                .unlockedBy("has_item",has(ModItemsRegister.LARGE_VIAL.get()))
                .save(recipesConsumer,new ResourceLocation("allomantic_large_vial"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS ,ModItemsRegister.ITEM_METAL_INGOT.get("steel"),1).
                requires(Items.IRON_INGOT)
                .requires(Items.COAL)
                .requires(Items.COAL)
                .requires(Items.COAL)
                .unlockedBy("has_item",has(ModItemsRegister.ITEM_METAL_INGOT.get("steel")))
                .save(recipesConsumer,new ResourceLocation(MetallicsArts.MOD_ID+"_steel_alloy_craft"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS ,ModItemsRegister.ITEM_METAL_INGOT.get("pewter"),1)
                .requires(ModTags.INGOTS.get(MetalEnum.LEAD.getMetalNameLower()))
                .requires(ModTags.INGOTS.get(MetalEnum.TIN.getMetalNameLower()))
                .requires(ModTags.INGOTS.get(MetalEnum.TIN.getMetalNameLower()))
                .requires(ModTags.INGOTS.get(MetalEnum.TIN.getMetalNameLower()))
                .unlockedBy("has_item",has(ModItemsRegister.ITEM_METAL_INGOT.get("pewter")))
                .save(recipesConsumer,new ResourceLocation(MetallicsArts.MOD_ID+"_pewter_alloy_craft"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS ,ModItemsRegister.ITEM_METAL_INGOT.get("bronze"),1)
                .requires(ModTags.INGOTS.get(MetalEnum.TIN.getMetalNameLower()))
                .requires(Items.COPPER_INGOT)
                .requires(Items.COPPER_INGOT)
                .requires(Items.COPPER_INGOT)
                .unlockedBy("has_item",has(ModItemsRegister.ITEM_METAL_INGOT.get("bronze")))
                .save(recipesConsumer,new ResourceLocation(MetallicsArts.MOD_ID+"_bronze_alloy_craft"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS ,ModItemsRegister.ITEM_METAL_INGOT.get("brass"),1)
                .requires(ModTags.INGOTS.get(MetalEnum.ZINC.getMetalNameLower()))
                .requires(ModTags.INGOTS.get(MetalEnum.ZINC.getMetalNameLower()))
                .requires(Items.COPPER_INGOT)
                .requires(Items.COPPER_INGOT)
                .unlockedBy("has_item",has(ModItemsRegister.ITEM_METAL_INGOT.get("brass")))
                .save(recipesConsumer,new ResourceLocation(MetallicsArts.MOD_ID+"_brass_alloy_craft"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS ,ModItemsRegister.ITEM_METAL_INGOT.get("electrum"),1)
                .requires(ModTags.INGOTS.get(MetalEnum.SILVER.getMetalNameLower()))
                .requires(ModTags.INGOTS.get(MetalEnum.SILVER.getMetalNameLower()))
                .requires(ModTags.INGOTS.get(MetalEnum.SILVER.getMetalNameLower()))
                .requires(Items.GOLD_INGOT)
                .unlockedBy("has_item",has(ModItemsRegister.ITEM_METAL_INGOT.get("electrum")))
                .save(recipesConsumer,new ResourceLocation(MetallicsArts.MOD_ID+"_electrum_alloy_craft"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS ,ModItemsRegister.ITEM_METAL_INGOT.get("bendalloy"),1)
                .requires(ModTags.INGOTS.get(MetalEnum.LEAD.getMetalNameLower()))
                .requires(ModTags.INGOTS.get(MetalEnum.LEAD.getMetalNameLower()))
                .requires(ModTags.INGOTS.get(MetalEnum.CADMIUM.getMetalNameLower()))
                .requires(ModTags.INGOTS.get(MetalEnum.CADMIUM.getMetalNameLower()))
                .unlockedBy("has_item",has(ModItemsRegister.ITEM_METAL_INGOT.get("bendalloy")))
                .save(recipesConsumer,new ResourceLocation(MetallicsArts.MOD_ID+"_bendalloy_alloy_craft"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS ,ModItemsRegister.ITEM_METAL_INGOT.get("duralumin"),1)
                .requires(ModTags.INGOTS.get(MetalEnum.ALUMINUM.getMetalNameLower()))
                .requires(ModTags.INGOTS.get(MetalEnum.ALUMINUM.getMetalNameLower()))
                .requires(ModTags.INGOTS.get(MetalEnum.ALUMINUM.getMetalNameLower()))
                .requires(Items.COPPER_INGOT)
                .unlockedBy("has_item",has(ModItemsRegister.ITEM_METAL_INGOT.get("duralumin")))
                .save(recipesConsumer,new ResourceLocation(MetallicsArts.MOD_ID+"_duralumin_alloy_craft"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS ,ModItemsRegister.ITEM_METAL_INGOT.get("nicrosil"),1)
                .requires(ModTags.INGOTS.get(MetalEnum.NICKEL.getMetalNameLower()))
                .requires(ModTags.INGOTS.get(MetalEnum.NICKEL.getMetalNameLower()))
                .requires(ModTags.INGOTS.get(MetalEnum.NICKEL.getMetalNameLower()))
                .requires(ModTags.INGOTS.get(MetalEnum.CHROMIUM.getMetalNameLower()))
                .unlockedBy("has_item",has(ModItemsRegister.ITEM_METAL_INGOT.get("nicrosil")))
                .save(recipesConsumer,new ResourceLocation(MetallicsArts.MOD_ID+"_nicrosil_alloy_craft"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS ,ModItemsRegister.ITEM_GEMS_BASE.get("malatium"),1)
                .requires(ModTags.GEMS.get(GemsEnum.ATIUM.getGemNameLower()))
                .requires(ModTags.GEMS.get(GemsEnum.ATIUM.getGemNameLower()))
                .requires(Items.GOLD_INGOT)
                .requires(Items.GOLD_INGOT)
                .unlockedBy("has_item",has(ModItemsRegister.ITEM_GEMS_BASE.get("malatium")))
                .save(recipesConsumer,new ResourceLocation(MetallicsArts.MOD_ID+"_malatium_alloy_craft"));

        SpecialRecipeBuilder.special(ModRecipeTypesRegister.LARGE_VIAL_ITEM_RECIPE_SERIALIZER.get()).save(recipesConsumer, MetallicsArts.MOD_ID+":large_vial_filling_recipe");
        SpecialRecipeBuilder.special(ModRecipeTypesRegister.SMALL_VIAL_ITEM_RECIPE_SERIALIZER.get()).save(recipesConsumer, MetallicsArts.MOD_ID+":small_vial_filling_recipe");

        ModItemsRegister.ITEM_ICONS_ALLOMANCY.forEach((key, value) -> ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS,value)
                .define('#',Items.GLASS_PANE)
                .define('x',(key.contains("gold")) ? Tags.Items.INGOTS_GOLD : (key.contains("copper")) ? Tags.Items.INGOTS_COPPER : (key.contains("iron")) ? Tags.Items.INGOTS_IRON: ModTags.INGOTS.get(key))
                .pattern(" # ")
                .pattern("#x#")
                .pattern(" # ")
                .unlockedBy("has_item", has(value))
                .save(recipesConsumer,new ResourceLocation(key + "_allomantic_icon")));

        ModItemsRegister.ITEM_ICONS_FERUCHEMIC.forEach((key, value) -> ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS,value)
                .define('#',Items.GLASS_PANE)
                .define('x',(key.contains("gold")) ? Tags.Items.INGOTS_GOLD : (key.contains("copper")) ? Tags.Items.INGOTS_COPPER : (key.contains("iron")) ? Tags.Items.INGOTS_IRON: ModTags.INGOTS.get(key))
                .pattern("# #")
                .pattern(" x ")
                .pattern("# #")
                .unlockedBy("has_item", has(value))
                .save(recipesConsumer,new ResourceLocation(key+"_feruchemic_icon")));

        for (MetalTagEnum metal : MetalTagEnum.values()) {

            ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS,ModBannersRegister.PATTERN_ITEMS.get("a_"+metal.getNameLower()).get())

                    .requires(!metal.isDivine() ? ModItemsRegister.ITEM_ICONS_ALLOMANCY.get(metal.getNameLower()) : ModItemsRegister.ITEM_ICONS_ALLOMANCY_DIVINE.get(metal.getNameLower()))
                    .requires(Items.PAPER)
                    .unlockedBy("has_item",has(ModBannersRegister.PATTERN_ITEMS.get("a_"+metal.getNameLower()).get()))
                    .save(recipesConsumer,new ResourceLocation(MetallicsArts.MOD_ID + "_" + ModBannersRegister.PATTERN_ITEMS.get("a_"+metal.getNameLower()).get()));

            ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS,ModBannersRegister.PATTERN_ITEMS.get("f_"+metal.getNameLower()).get())
                    .requires(!metal.isDivine() ? ModItemsRegister.ITEM_ICONS_FERUCHEMIC.get(metal.getNameLower()) : ModItemsRegister.ITEM_ICONS_FERUCHEMIC_DIVINE.get(metal.getNameLower()))
                    .requires(Items.PAPER)
                    .unlockedBy("has_item",has(ModBannersRegister.PATTERN_ITEMS.get("f_"+metal.getNameLower()).get()))
                    .save(recipesConsumer,new ResourceLocation(MetallicsArts.MOD_ID + "_" + ModBannersRegister.PATTERN_ITEMS.get("f_"+metal.getNameLower()).get()));

        }

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,ModItemsRegister.CORE_OBSIDIAN.get())
                .define('#', Items.OBSIDIAN)
                .define('*', Items.NETHERITE_SCRAP)
                .define('X', Items.GOLD_INGOT)
                .pattern("#X#")
                .pattern("X*X")
                .pattern("#X#")
                .unlockedBy("has_block", has(ModItemsRegister.CORE_OBSIDIAN.get()))
                .save(recipesConsumer, new ResourceLocation(ModItemsRegister.CORE_OBSIDIAN.get().getDescriptionId()));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,ModItemsRegister.CORE_ALUMINUM.get())
                .define('#', ModBlocksRegister.BLOCK_METAL_BLOCKS.get("aluminum"))
                .define('*', Items.END_CRYSTAL)
                .define('X', Items.NETHERITE_SCRAP)
                .pattern("#X#")
                .pattern("X*X")
                .pattern("#X#")
                .unlockedBy("has_block", has(ModItemsRegister.CORE_ALUMINUM.get()))
                .save(recipesConsumer, new ResourceLocation(ModItemsRegister.CORE_ALUMINUM.get().getDescriptionId()));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,ModItemsRegister.CORE_STEEL.get())
                .define('#', ModBlocksRegister.BLOCK_METAL_BLOCKS.get("steel"))
                .define('*', Items.END_CRYSTAL)
                .define('X', Items.NETHERITE_SCRAP)
                .pattern("#X#")
                .pattern("X*X")
                .pattern("#X#")
                .unlockedBy("has_block", has(ModItemsRegister.CORE_STEEL.get()))
                .save(recipesConsumer, new ResourceLocation(ModItemsRegister.CORE_STEEL.get().getDescriptionId()));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,ModItemsRegister.CORE_OBSIDIAN.get())
                .define('#', Items.OBSIDIAN)
                .define('*', Items.NETHERITE_SCRAP)
                .define('X', Items.GOLD_INGOT)
                .pattern("#X#")
                .pattern("X*X")
                .pattern("#X#")
                .unlockedBy("has_block", has(ModItemsRegister.CORE_OBSIDIAN.get()))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_core_obsidian"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,ModItemsRegister.CORE_ALUMINUM.get())
                .define('#', ModBlocksRegister.BLOCK_METAL_BLOCKS.get("aluminum"))
                .define('*', Items.END_CRYSTAL)
                .define('X', Items.NETHERITE_SCRAP)
                .pattern("#X#")
                .pattern("X*X")
                .pattern("#X#")
                .unlockedBy("has_block", has(ModItemsRegister.CORE_ALUMINUM.get()))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_core_aluminum"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,ModItemsRegister.CORE_STEEL.get())
                .define('#', ModBlocksRegister.BLOCK_METAL_BLOCKS.get("steel"))
                .define('*', Items.END_CRYSTAL)
                .define('X', Items.NETHERITE_SCRAP)
                .pattern("#X#")
                .pattern("X*X")
                .pattern("#X#")
                .unlockedBy("has_block", has(ModItemsRegister.CORE_STEEL.get()))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_core_steel"));

        /*UpgradeRecipeBuilder.smithing(
                        Ingredient.of(Items.NETHERITE_HELMET),
                        Ingredient.of(ModItemsRegister.CORE_STEEL.get()),
                        ModItemsRegister.STEEL_ARMOR.get(ArmorPiecesEnum.HELMET).get())
                .unlocks("has_armor", has(ModItemsRegister.STEEL_ARMOR.get(ArmorPiecesEnum.HELMET).get()))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_armor_steel_helmet"));
        UpgradeRecipeBuilder.smithing(
                        Ingredient.of(Items.NETHERITE_CHESTPLATE),
                        Ingredient.of(ModItemsRegister.CORE_STEEL.get()),
                        ModItemsRegister.STEEL_ARMOR.get(ArmorPiecesEnum.CHESTPLATE).get())
                .unlocks("has_armor", has(ModItemsRegister.STEEL_ARMOR.get(ArmorPiecesEnum.CHESTPLATE).get()))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_armor_steel_chestplate"));

        UpgradeRecipeBuilder.smithing(
                        Ingredient.of(Items.NETHERITE_LEGGINGS),
                        Ingredient.of(ModItemsRegister.CORE_STEEL.get()),
                        ModItemsRegister.STEEL_ARMOR.get(ArmorPiecesEnum.LEGGINGS).get())
                .unlocks("has_armor", has(ModItemsRegister.STEEL_ARMOR.get(ArmorPiecesEnum.LEGGINGS).get()))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_armor_steel_leggings"));
        UpgradeRecipeBuilder.smithing(
                        Ingredient.of(Items.NETHERITE_BOOTS),
                        Ingredient.of(ModItemsRegister.CORE_STEEL.get()),
                        ModItemsRegister.STEEL_ARMOR.get(ArmorPiecesEnum.BOOTS).get())
                .unlocks("has_armor", has(ModItemsRegister.STEEL_ARMOR.get(ArmorPiecesEnum.BOOTS).get()))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_armor_steel_boots"));



        UpgradeRecipeBuilder.smithing(
                        Ingredient.of(Items.NETHERITE_HELMET),
                        Ingredient.of(ModItemsRegister.CORE_ALUMINUM.get()),
                        ModItemsRegister.ALUMINUM_ARMOR.get(ArmorPiecesEnum.HELMET).get())
                .unlocks("has_armor", has(ModItemsRegister.ALUMINUM_ARMOR.get(ArmorPiecesEnum.HELMET).get()))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_armor_aluminum_helmet"));
        UpgradeRecipeBuilder.smithing(
                        Ingredient.of(Items.NETHERITE_CHESTPLATE),
                        Ingredient.of(ModItemsRegister.CORE_ALUMINUM.get()),
                        ModItemsRegister.ALUMINUM_ARMOR.get(ArmorPiecesEnum.CHESTPLATE).get())
                .unlocks("has_armor", has(ModItemsRegister.ALUMINUM_ARMOR.get(ArmorPiecesEnum.CHESTPLATE).get()))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_armor_aluminum_chestplate"));
        UpgradeRecipeBuilder.smithing(
                        Ingredient.of(Items.NETHERITE_LEGGINGS),
                        Ingredient.of(ModItemsRegister.CORE_ALUMINUM.get()),
                        ModItemsRegister.ALUMINUM_ARMOR.get(ArmorPiecesEnum.LEGGINGS).get())
                .unlocks("has_armor", has(ModItemsRegister.ALUMINUM_ARMOR.get(ArmorPiecesEnum.LEGGINGS).get()))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_armor_aluminum_leggings"));
        UpgradeRecipeBuilder.smithing(
                        Ingredient.of(Items.NETHERITE_BOOTS),
                        Ingredient.of(ModItemsRegister.CORE_ALUMINUM.get()),
                        ModItemsRegister.ALUMINUM_ARMOR.get(ArmorPiecesEnum.BOOTS).get())
                .unlocks("has_armor", has(ModItemsRegister.ALUMINUM_ARMOR.get(ArmorPiecesEnum.BOOTS).get()))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_armor_aluminum_boots"));*/
        //todo mejoras de armaduras a nuevos tiers

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItemsRegister.WOOD_SHIELD.get())
                .define('#', ItemTags.PLANKS)
                .define('*', ItemTags.LOGS)
                .pattern("###")
                .pattern("#*#")
                .pattern(" # ")
                .unlockedBy("has_block", has(ModItemsRegister.WOOD_SHIELD.get()))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_" + Shields.WOOD.getId()));

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItemsRegister.BRONZE_ALUMINUM_SHIELD.get())
                .define('#', ModTags.INGOTS.get(MetalTagEnum.ALUMINUM.getMetalNameLower()))
                .define('*', ModTags.METAL_BLOCKS.get(MetalTagEnum.BRONZE.getMetalNameLower()))
                .pattern("###")
                .pattern("#*#")
                .pattern(" # ")
                .unlockedBy("has_block", has(ModItemsRegister.BRONZE_ALUMINUM_SHIELD.get()))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_" + Shields.BRONZE_ALUMINUM.getId()));

        for (MetalEnum metal : MetalEnum.values()) {
            ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,ModBlocksRegister.BLOCK_METAL_STAIRS.get(metal.getMetalNameLower()),4)
                    .define('#', ModTags.METAL_BLOCKS.get(metal.getMetalNameLower()))
                    .pattern("#  ")
                    .pattern("## ")
                    .pattern("###")
                    .unlockedBy("has_block", has(ModBlocksRegister.BLOCK_METAL_STAIRS.get(metal.getMetalNameLower())))
                    .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_" + metal.getMetalNameLower() + "_stairs"));

            ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,ModBlocksRegister.BLOCK_METAL_SLABS.get(metal.getMetalNameLower()),3)
                    .define('#', ModTags.METAL_BLOCKS.get(metal.getMetalNameLower()))
                    .pattern("###")
                    .unlockedBy("has_block", has(ModBlocksRegister.BLOCK_METAL_SLABS.get(metal.getMetalNameLower())))
                    .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_" + metal.getMetalNameLower() + "_slabs"));

            ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,ModBlocksRegister.BLOCK_METAL_WALLS.get(metal.getMetalNameLower()),3)
                    .define('#', ModTags.METAL_BLOCKS.get(metal.getMetalNameLower()))
                    .pattern("###")
                    .pattern("###")
                    .unlockedBy("has_block", has(ModBlocksRegister.BLOCK_METAL_WALLS.get(metal.getMetalNameLower())))
                    .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_" + metal.getMetalNameLower() + "_walls"));

            ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,ModBlocksRegister.BLOCK_METAL_FENCES.get(metal.getMetalNameLower()),3)
                    .define('#', ModTags.METAL_BLOCKS.get(metal.getMetalNameLower()))
                    .define('*', ModTags.INGOTS.get(metal.getMetalNameLower()))
                    .pattern("#*#")
                    .pattern("#*#")
                    .unlockedBy("has_block", has(ModBlocksRegister.BLOCK_METAL_FENCES.get(metal.getMetalNameLower())))
                    .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_" + metal.getMetalNameLower() + "_fences"));

            ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,ModBlocksRegister.BLOCK_METAL_FENCE_GATES.get(metal.getMetalNameLower()),3)
                    .define('#', ModTags.METAL_BLOCKS.get(metal.getMetalNameLower()))
                    .define('*', ModTags.INGOTS.get(metal.getMetalNameLower()))
                    .pattern("*#*")
                    .pattern("*#*")
                    .unlockedBy("has_block", has(ModBlocksRegister.BLOCK_METAL_FENCE_GATES.get(metal.getMetalNameLower())))
                    .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_" + metal.getMetalNameLower() + "_fence_walls"));

            if (!metal.isAlloy()) {
                ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,ModBlocksRegister.BLOCK_METAL_STAIRS.get(metal.getMetalNameLower()+"_raw"),4)
                        .define('#', ModTags.RAW_BLOCKS.get(metal.getMetalNameLower()))
                        .pattern("#  ")
                        .pattern("## ")
                        .pattern("###")
                        .unlockedBy("has_block", has(ModBlocksRegister.BLOCK_METAL_STAIRS.get(metal.getMetalNameLower())))
                        .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_" + metal.getMetalNameLower() + "_raw_stairs"));

                ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,ModBlocksRegister.BLOCK_METAL_SLABS.get(metal.getMetalNameLower()+"_raw"),4)
                        .define('#', ModTags.RAW_BLOCKS.get(metal.getMetalNameLower()))
                        .pattern("###")
                        .unlockedBy("has_block", has(ModBlocksRegister.BLOCK_METAL_SLABS.get(metal.getMetalNameLower())))
                        .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_" + metal.getMetalNameLower() + "_raw_slabs"));

                ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,ModBlocksRegister.BLOCK_METAL_WALLS.get(metal.getMetalNameLower()),3)
                        .define('#', ModTags.RAW_BLOCKS.get(metal.getMetalNameLower()))
                        .pattern("###")
                        .pattern("###")
                        .unlockedBy("has_block", has(ModBlocksRegister.BLOCK_METAL_WALLS.get(metal.getMetalNameLower())))
                        .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_" + metal.getMetalNameLower() + "_raw_walls"));

                ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,ModBlocksRegister.BLOCK_METAL_FENCES.get(metal.getMetalNameLower()),3)
                        .define('#', ModTags.RAW_BLOCKS.get(metal.getMetalNameLower()))
                        .define('*', ModTags.INGOTS.get(metal.getMetalNameLower()))
                        .pattern("#*#")
                        .pattern("#*#")
                        .unlockedBy("has_block", has(ModBlocksRegister.BLOCK_METAL_FENCES.get(metal.getMetalNameLower())))
                        .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_" + metal.getMetalNameLower() + "_raw_fences"));

                ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,ModBlocksRegister.BLOCK_METAL_FENCE_GATES.get(metal.getMetalNameLower()),3)
                        .define('#', ModTags.RAW_BLOCKS.get(metal.getMetalNameLower()))
                        .define('*', ModTags.INGOTS.get(metal.getMetalNameLower()))
                        .pattern("*#*")
                        .pattern("*#*")
                        .unlockedBy("has_block", has(ModBlocksRegister.BLOCK_METAL_FENCE_GATES.get(metal.getMetalNameLower())))
                        .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_" + metal.getMetalNameLower() + "_raw_fence_walls"));
            }
        }
        for (GemsEnum metal : GemsEnum.values()) {
            ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,ModBlocksRegister.BLOCK_METAL_STAIRS.get(metal.getGemNameLower()),4)
                    .define('#', ModTags.METAL_BLOCKS.get(metal.getGemNameLower()))
                    .pattern("#  ")
                    .pattern("## ")
                    .pattern("###")
                    .unlockedBy("has_block", has(ModBlocksRegister.BLOCK_METAL_STAIRS.get(metal.getGemNameLower())))
                    .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_" + metal.getGemNameLower() + "_stairs"));

            ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,ModBlocksRegister.BLOCK_METAL_SLABS.get(metal.getGemNameLower()),3)
                    .define('#', ModTags.METAL_BLOCKS.get(metal.getGemNameLower()))
                    .pattern("###")
                    .unlockedBy("has_block", has(ModBlocksRegister.BLOCK_METAL_SLABS.get(metal.getGemNameLower())))
                    .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_" + metal.getGemNameLower() + "_slab"));

            ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,ModBlocksRegister.BLOCK_METAL_WALLS.get(metal.getGemNameLower()),3)
                    .define('#', ModTags.METAL_BLOCKS.get(metal.getGemNameLower()))
                    .pattern("###")
                    .pattern("###")
                    .unlockedBy("has_block", has(ModBlocksRegister.BLOCK_METAL_WALLS.get(metal.getGemNameLower())))
                    .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_" + metal.getGemNameLower() + "_walls"));

            ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,ModBlocksRegister.BLOCK_METAL_FENCES.get(metal.getGemNameLower()),3)
                    .define('#', ModTags.METAL_BLOCKS.get(metal.getGemNameLower()))
                    .define('*', ModTags.GEMS.get(metal.getGemNameLower()))
                    .pattern("#*#")
                    .pattern("#*#")
                    .unlockedBy("has_block", has(ModBlocksRegister.BLOCK_METAL_FENCES.get(metal.getGemNameLower())))
                    .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_" + metal.getGemNameLower() + "_fences"));

            ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,ModBlocksRegister.BLOCK_METAL_FENCE_GATES.get(metal.getGemNameLower()),3)
                    .define('#', ModTags.METAL_BLOCKS.get(metal.getGemNameLower()))
                    .define('*', ModTags.GEMS.get(metal.getGemNameLower()))
                    .pattern("*#*")
                    .pattern("*#*")
                    .unlockedBy("has_block", has(ModBlocksRegister.BLOCK_METAL_FENCE_GATES.get(metal.getGemNameLower())))
                    .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_" + metal.getGemNameLower() + "_fence_walls"));

        }

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,ModBlocksRegister.BLOCK_METAL_STAIRS.get("gold"),4)
                .define('#', Items.GOLD_BLOCK)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .unlockedBy("has_block", has(ModBlocksRegister.BLOCK_METAL_STAIRS.get("gold")))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_gold_stairs"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,ModBlocksRegister.BLOCK_METAL_STAIRS.get("iron"),4)
                .define('#', Items.IRON_BLOCK)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .unlockedBy("has_block", has(ModBlocksRegister.BLOCK_METAL_STAIRS.get("iron")))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_iron_stairs"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,ModBlocksRegister.BLOCK_METAL_STAIRS.get("iron_raw"),4)
                .define('#', Items.RAW_IRON_BLOCK)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .unlockedBy("has_block", has(ModBlocksRegister.BLOCK_METAL_STAIRS.get("iron_raw")))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_iron_raw_stairs"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,ModBlocksRegister.BLOCK_METAL_STAIRS.get("gold_raw"),4)
                .define('#', Items.RAW_GOLD_BLOCK)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .unlockedBy("has_block", has(ModBlocksRegister.BLOCK_METAL_STAIRS.get("gold_raw")))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_gold_raw_stairs"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,ModBlocksRegister.BLOCK_METAL_STAIRS.get("copper_raw"),4)
                .define('#', Items.RAW_COPPER_BLOCK)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .unlockedBy("has_block", has(ModBlocksRegister.BLOCK_METAL_STAIRS.get("copper_raw")))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_copper_raw_stairs"));



        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,ModBlocksRegister.BLOCK_METAL_SLABS.get("gold"),3)
                .define('#', Items.GOLD_BLOCK)
                .pattern("###")
                .unlockedBy("has_block", has(ModBlocksRegister.BLOCK_METAL_SLABS.get("gold")))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_gold_slabs"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,ModBlocksRegister.BLOCK_METAL_SLABS.get("iron"),3)
                .define('#', Items.IRON_BLOCK)
                .pattern("###")
                .unlockedBy("has_block", has(ModBlocksRegister.BLOCK_METAL_SLABS.get("iron")))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_iron_slabs"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,ModBlocksRegister.BLOCK_METAL_SLABS.get("iron_raw"),3)
                .define('#', Items.RAW_IRON_BLOCK)
                .pattern("###")
                .unlockedBy("has_block", has(ModBlocksRegister.BLOCK_METAL_SLABS.get("iron_raw")))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_iron_raw_slabs"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,ModBlocksRegister.BLOCK_METAL_SLABS.get("gold_raw"),3)
                .define('#', Items.RAW_GOLD_BLOCK)
                .pattern("###")
                .unlockedBy("has_block", has(ModBlocksRegister.BLOCK_METAL_SLABS.get("gold_raw")))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_gold_raw_slabs"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,ModBlocksRegister.BLOCK_METAL_SLABS.get("copper_raw"),3)
                .define('#', Items.RAW_COPPER_BLOCK)
                .pattern("###")
                .unlockedBy("has_block", has(ModBlocksRegister.BLOCK_METAL_SLABS.get("copper_raw")))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_copper_raw_slabs"));



        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,ModBlocksRegister.BLOCK_METAL_WALLS.get("gold"),3)
                .define('#', Items.GOLD_BLOCK)
                .pattern("###")
                .pattern("###")
                .unlockedBy("has_block", has(ModBlocksRegister.BLOCK_METAL_WALLS.get("gold")))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_gold_walls"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,ModBlocksRegister.BLOCK_METAL_WALLS.get("iron"),3)
                .define('#', Items.IRON_BLOCK)
                .pattern("###")
                .pattern("###")
                .unlockedBy("has_block", has(ModBlocksRegister.BLOCK_METAL_WALLS.get("iron")))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_iron_walls"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,ModBlocksRegister.BLOCK_METAL_WALLS.get("copper"),3)
                .define('#', Items.COPPER_BLOCK)
                .pattern("###")
                .pattern("###")
                .unlockedBy("has_block", has(ModBlocksRegister.BLOCK_METAL_WALLS.get("copper")))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_copper_walls"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,ModBlocksRegister.BLOCK_METAL_WALLS.get("iron_raw"),3)
                .define('#', Items.RAW_IRON_BLOCK)
                .pattern("###")
                .pattern("###")
                .unlockedBy("has_block", has(ModBlocksRegister.BLOCK_METAL_WALLS.get("iron_raw")))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_iron_raw_walls"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,ModBlocksRegister.BLOCK_METAL_WALLS.get("iron_raw"),3)
                .define('#', Items.RAW_GOLD_BLOCK)
                .pattern("###")
                .pattern("###")
                .unlockedBy("has_block", has(ModBlocksRegister.BLOCK_METAL_WALLS.get("iron_raw")))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_gold_raw_walls"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,ModBlocksRegister.BLOCK_METAL_WALLS.get("copper_raw"),3)
                .define('#', Items.RAW_COPPER_BLOCK)
                .pattern("###")
                .pattern("###")
                .unlockedBy("has_block", has(ModBlocksRegister.BLOCK_METAL_WALLS.get("copper_raw")))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_copper_raw_walls"));



        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,ModBlocksRegister.BLOCK_METAL_FENCES.get("gold"),3)
                .define('#', Blocks.GOLD_BLOCK)
                .define('*', Items.GOLD_INGOT)
                .pattern("#*#")
                .pattern("#*#")
                .unlockedBy("has_block", has(ModBlocksRegister.BLOCK_METAL_FENCES.get("gold")))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_gold_fences"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,ModBlocksRegister.BLOCK_METAL_FENCES.get("iron"),3)
                .define('#', Blocks.IRON_BLOCK)
                .define('*', Items.IRON_INGOT)
                .pattern("#*#")
                .pattern("#*#")
                .unlockedBy("has_block", has(ModBlocksRegister.BLOCK_METAL_FENCES.get("iron")))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_iron_fences"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,ModBlocksRegister.BLOCK_METAL_FENCES.get("copper"),3)
                .define('#', Blocks.COPPER_BLOCK)
                .define('*', Items.COPPER_INGOT)
                .pattern("#*#")
                .pattern("#*#")
                .unlockedBy("has_block", has(ModBlocksRegister.BLOCK_METAL_FENCES.get("copper")))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_copper_fences"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,ModBlocksRegister.BLOCK_METAL_FENCES.get("iron_raw"),3)
                .define('#', Blocks.RAW_IRON_BLOCK)
                .define('*', Items.IRON_INGOT)
                .pattern("#*#")
                .pattern("#*#")
                .unlockedBy("has_block", has(ModBlocksRegister.BLOCK_METAL_FENCES.get("iron_raw")))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_iron_raw_fences"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,ModBlocksRegister.BLOCK_METAL_FENCES.get("gold_raw"),3)
                .define('#', Items.RAW_GOLD_BLOCK)
                .define('*', Items.GOLD_INGOT)
                .pattern("#*#")
                .pattern("#*#")
                .unlockedBy("has_block", has(ModBlocksRegister.BLOCK_METAL_FENCES.get("gold_raw")))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_gold_raw_fences"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,ModBlocksRegister.BLOCK_METAL_FENCES.get("copper_raw"),3)
                .define('#', Items.RAW_COPPER_BLOCK)
                .define('*', Items.COPPER_INGOT)
                .pattern("#*#")
                .pattern("#*#")
                .unlockedBy("has_block", has(ModBlocksRegister.BLOCK_METAL_FENCES.get("copper_raw")))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_copper_raw_fences"));



        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,ModBlocksRegister.BLOCK_METAL_FENCE_GATES.get("gold"),3)
                .define('#', Blocks.GOLD_BLOCK)
                .define('*', Items.GOLD_INGOT)
                .pattern("*#*")
                .pattern("*#*")
                .unlockedBy("has_block", has(ModBlocksRegister.BLOCK_METAL_FENCE_GATES.get("gold")))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_gold_fence_gates"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,ModBlocksRegister.BLOCK_METAL_FENCE_GATES.get("iron"),3)
                .define('#', Blocks.IRON_BLOCK)
                .define('*', Items.IRON_INGOT)
                .pattern("*#*")
                .pattern("*#*")
                .unlockedBy("has_block", has(ModBlocksRegister.BLOCK_METAL_FENCE_GATES.get("iron")))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_iron_fence_gates"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,ModBlocksRegister.BLOCK_METAL_FENCE_GATES.get("copper"),3)
                .define('#', Blocks.COPPER_BLOCK)
                .define('*', Items.COPPER_INGOT)
                .pattern("*#*")
                .pattern("*#*")
                .unlockedBy("has_block", has(ModBlocksRegister.BLOCK_METAL_FENCE_GATES.get("iron")))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_copper_fence_gates"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,ModBlocksRegister.BLOCK_METAL_FENCE_GATES.get("iron_raw"),3)
                .define('#', Blocks.RAW_IRON_BLOCK)
                .define('*', Items.IRON_INGOT)
                .pattern("*#*")
                .pattern("*#*")
                .unlockedBy("has_block", has(ModBlocksRegister.BLOCK_METAL_FENCE_GATES.get("iron_raw")))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_iron_raw_fence_gates"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,ModBlocksRegister.BLOCK_METAL_FENCE_GATES.get("gold_raw"),3)
                .define('#', Items.RAW_GOLD_BLOCK)
                .define('*', Items.GOLD_INGOT)
                .pattern("*#*")
                .pattern("*#*")
                .unlockedBy("has_block", has(ModBlocksRegister.BLOCK_METAL_FENCE_GATES.get("gold_raw")))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_gold_raw_fence_gates"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,ModBlocksRegister.BLOCK_METAL_FENCE_GATES.get("copper_raw"),3)
                .define('#', Blocks.RAW_COPPER_BLOCK)
                .define('*', Items.COPPER_INGOT)
                .pattern("*#*")
                .pattern("*#*")
                .unlockedBy("has_block", has(ModBlocksRegister.BLOCK_METAL_FENCE_GATES.get("iron")))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_copper_raw_fence_gates"));


        /*ShapedRecipeBuilder.shaped(ModItemsRegister.MISTCLOACK.get())
                .define('#', Items.STRING)
                .define('*', Items.PHANTOM_MEMBRANE)
                .define('X', Items.BLACK_WOOL)
                .pattern("XXX")
                .pattern("###")
                .pattern("***")
                .unlockedBy("has_block", has(ModItemsRegister.MISTCLOACK.get()))
                .save(recipesConsumer, new ResourceLocation(ModItemsRegister.MISTCLOACK.get().getDescriptionId()));*/
    }


}






