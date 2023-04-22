package net.rudahee.metallics_arts.data.providers;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.data.enums.implementations.MetalEnum;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.enums.implementations.custom_items.ArmorPiecesEnum;
import net.rudahee.metallics_arts.data.enums.implementations.custom_items.MetalMindEnum;
import net.rudahee.metallics_arts.data.enums.implementations.custom_items.SpikeEnum;
import net.rudahee.metallics_arts.setup.registries.ModBannersRegister;
import net.rudahee.metallics_arts.setup.registries.ModBlocksRegister;
import net.rudahee.metallics_arts.setup.registries.ModItemsRegister;
import net.rudahee.metallics_arts.setup.registries.ModRecipeTypesRegister;
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
        super(generator);
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
    protected void buildCraftingRecipes(@NotNull Consumer<FinishedRecipe> recipesConsumer) {

        ModItemsRegister.ITEM_METAL_INGOT.forEach((name, item) -> {
            ShapelessRecipeBuilder.shapeless(item.asItem(), 9)
                    .requires(ModBlocksRegister.BLOCK_METAL_BLOCKS.get(name))
                    .unlockedBy("has_item", has(item))
                    .save(recipesConsumer, new ResourceLocation(item.getDescriptionId() + "_to_" + ModBlocksRegister.BLOCK_METAL_BLOCKS.get(name).getDescriptionId()));
        });

        ModBlocksRegister.BLOCK_METAL_BLOCKS.forEach((name, block) -> {
            ShapedRecipeBuilder.shaped(block)
                    .define('#', ModItemsRegister.ITEM_METAL_INGOT.get(name))
                    .pattern("###")
                    .pattern("###")
                    .pattern("###")
                    .unlockedBy("has_block", has(block))
                    .save(recipesConsumer, new ResourceLocation(block.getDescriptionId() + "_to_" + ModItemsRegister.ITEM_METAL_INGOT.get(name).getDescriptionId()));
        });

        ModBlocksRegister.RAW_METAL_BLOCKS.forEach((name, block) -> {
            ShapedRecipeBuilder.shaped(block)
                    .define('#', ModItemsRegister.ITEM_RAW_METAL.get(name))
                    .pattern("###")
                    .pattern("###")
                    .pattern("###")
                    .unlockedBy("has_block", has(block))
                    .save(recipesConsumer, new ResourceLocation(block.getDescriptionId() + "_to_" + ModItemsRegister.ITEM_RAW_METAL.get(name).getDescriptionId()));
        });

        ModItemsRegister.ITEM_RAW_METAL.forEach((name, item) -> {
            ShapelessRecipeBuilder.shapeless(item.asItem(), 9)
                    .requires(ModBlocksRegister.RAW_METAL_BLOCKS.get(name))
                    .unlockedBy("has_item", has(item))
                    .save(recipesConsumer, new ResourceLocation(item.getDescriptionId() + "_to_" + ModBlocksRegister.RAW_METAL_BLOCKS.get(name).getDescriptionId()));
        });



        ModItemsRegister.ITEM_GEMS_BASE.forEach((name, item) -> {
            ShapelessRecipeBuilder.shapeless(item.asItem(), 9)
                    .requires(ModBlocksRegister.BLOCK_GEMS_BLOCKS.get(name))
                    .unlockedBy("has_item", has(item))
                    .save(recipesConsumer, new ResourceLocation(item.getDescriptionId() + "_to_" + ModBlocksRegister.BLOCK_GEMS_BLOCKS.get(name).getDescriptionId()));
        });

        ModBlocksRegister.BLOCK_GEMS_BLOCKS.forEach((name, block) -> {
            ShapedRecipeBuilder.shaped(block)
                    .define('#', ModItemsRegister.ITEM_GEMS_BASE.get(name))
                    .pattern("###")
                    .pattern("###")
                    .pattern("###")
                    .unlockedBy("has_block", has(block))
                    .save(recipesConsumer, new ResourceLocation(block.getDescriptionId() + "_to_" + ModItemsRegister.ITEM_GEMS_BASE.get(name).getDescriptionId()));
        });
        ModItemsRegister.ITEM_METAL_INGOT.forEach((name, item) -> {
            ShapelessRecipeBuilder.shapeless(ModItemsRegister.ITEM_METAL_NUGGET.get(name), 9)
                    .requires(item)
                    .unlockedBy("has_block", has(item))
                    .save(recipesConsumer, new ResourceLocation(item.getDescriptionId() + "_to_" + ModItemsRegister.ITEM_METAL_NUGGET.get(name).getDescriptionId()));


        });

        ShapelessRecipeBuilder.shapeless(ModItemsRegister.ITEM_METAL_NUGGET.get("copper_nugget"), 9)
                .requires(Items.COPPER_INGOT)
                .unlockedBy("has_block", has(Items.COPPER_INGOT))
                .save(recipesConsumer, new ResourceLocation(Items.COPPER_INGOT.getDescriptionId() + "_to_" + ModItemsRegister.ITEM_METAL_NUGGET.get("copper_nugget").getDescriptionId()));


        ModItemsRegister.ITEM_METAL_NUGGET.forEach((name, item) -> {
            if (!name.equals("copper_nugget")) {
                ShapedRecipeBuilder.shaped(ModItemsRegister.ITEM_METAL_INGOT.get(name))
                        .define('#', ModItemsRegister.ITEM_METAL_NUGGET.get(name))
                        .pattern("###")
                        .pattern("###")
                        .pattern("###")
                        .unlockedBy("has_block", has(item))
                        .save(recipesConsumer, new ResourceLocation(item.getDescriptionId() + "_to_" + ModItemsRegister.ITEM_METAL_INGOT.get(name).getDescriptionId()));
            } else {

                ShapedRecipeBuilder.shaped(Items.COPPER_INGOT)
                        .define('#', ModItemsRegister.ITEM_METAL_NUGGET.get(name))
                        .pattern("###")
                        .pattern("###")
                        .pattern("###")
                        .unlockedBy("has_block", has(item))
                        .save(recipesConsumer, new ResourceLocation(item.getDescriptionId() + "_to_" + Items.COPPER_INGOT.getDescriptionId()));
            }

        });

        ModItemsRegister.ITEM_GEMS_BASE.forEach((name, item) -> {
            ShapelessRecipeBuilder.shapeless(ModItemsRegister.ITEM_GEMS_NUGGET.get(name), 9)
                    .requires(item)
                    .unlockedBy("has_block", has(item))
                    .save(recipesConsumer, new ResourceLocation(item.getDescriptionId() + "_to_" + ModItemsRegister.ITEM_GEMS_NUGGET.get(name).getDescriptionId()));
        });

        ModItemsRegister.ITEM_GEMS_NUGGET.forEach((name, item) -> {
            ShapedRecipeBuilder.shaped(ModItemsRegister.ITEM_GEMS_BASE.get(name))
                    .define('#', ModItemsRegister.ITEM_GEMS_NUGGET.get(name))
                    .pattern("###")
                    .pattern("###")
                    .pattern("###")
                    .unlockedBy("has_block", has(item))
                    .save(recipesConsumer, new ResourceLocation(item.getDescriptionId() + "_to_" + ModItemsRegister.ITEM_GEMS_BASE.get(name).getDescriptionId()));
        });

        Arrays.asList(MetalEnum.values()).forEach(metal -> {
            if (!metal.isAlloy()){
                SimpleCookingRecipeBuilder.cooking(Ingredient.of(ModItemsRegister.ITEM_RAW_METAL.get(metal.getMetalNameLower())), ModItemsRegister.ITEM_METAL_INGOT.get(metal.getMetalNameLower()), 0.5f, 250, RecipeSerializer.SMELTING_RECIPE)
                        .unlockedBy("has_block", has(ModItemsRegister.ITEM_RAW_METAL.get(metal.getMetalNameLower())))
                        .save(recipesConsumer, new ResourceLocation(ModItemsRegister.ITEM_RAW_METAL.get(metal.getMetalNameLower()).getDescriptionId() + "_to_" + ModItemsRegister.ITEM_METAL_INGOT.get(metal.getMetalNameLower()).getDescriptionId() + "_furnace"));
            }
        });

        Arrays.asList(MetalEnum.values()).forEach(metal -> {
            if (!metal.isAlloy()){
                SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModItemsRegister.ITEM_RAW_METAL.get(metal.getMetalNameLower())), ModItemsRegister.ITEM_METAL_INGOT.get(metal.getMetalNameLower()), 0.8f, 100)
                        .unlockedBy("has_block", has(ModItemsRegister.ITEM_RAW_METAL.get(metal.getMetalNameLower())))
                        .save(recipesConsumer, new ResourceLocation(ModItemsRegister.ITEM_RAW_METAL.get(metal.getMetalNameLower()).getDescriptionId() + "_to_" + ModItemsRegister.ITEM_METAL_INGOT.get(metal.getMetalNameLower()).getDescriptionId() + "_blast"));
            }
        });

        Arrays.asList(MetalMindEnum.values()).forEach(object -> {
            Item item1, item2;

            //BLOCKS
            if (object.isGems()) {
                item1 = ModBlocksRegister.BLOCK_GEMS_BLOCKS.get(object.getFirstMetal()).asItem();
                item2 = ModBlocksRegister.BLOCK_GEMS_BLOCKS.get(object.getSecondMetal()).asItem();
            } else if (object.isVanilla()) {
                if (object.getFirstMetal() == "iron") {
                    item1 = Items.IRON_BLOCK;
                }
                else if (object.getFirstMetal() == "gold") {
                    item1 = Items.GOLD_BLOCK;
                }
                else {
                    item1 = Items.COPPER_BLOCK;
                }
                item2 = ModBlocksRegister.BLOCK_METAL_BLOCKS.get(object.getSecondMetal()).asItem();
            } else {
                item1 = ModBlocksRegister.BLOCK_METAL_BLOCKS.get(object.getFirstMetal()).asItem();
                item2 = ModBlocksRegister.BLOCK_METAL_BLOCKS.get(object.getSecondMetal()).asItem();
            }
            //BANDS
            ShapedRecipeBuilder.shaped(object.getBand())
                    .define('#', item1)
                    .define('x', item2)
                    .pattern("xxx")
                    .pattern("# x")
                    .pattern("###")
                    .unlockedBy("has_item", has(object.getBand()))
                    .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_band_" + object.getFirstMetal() + "_" + object.getSecondMetal()));

            ShapedRecipeBuilder.shaped(object.getBand())
                    .define('#', item2)
                    .define('x', item1)
                    .pattern("xxx")
                    .pattern("# x")
                    .pattern("###")
                    .unlockedBy("has_item", has(object.getBand()))
                    .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_band_" + object.getFirstMetal() + "_" + object.getSecondMetal() + "_2"));


            //INGOTS
            if (object.isGems()) {
                item1 = ModItemsRegister.ITEM_GEMS_BASE.get(object.getFirstMetal()).asItem();
                item2 = ModItemsRegister.ITEM_GEMS_BASE.get(object.getSecondMetal()).asItem();
            } else if (object.isVanilla()) {
                if (object.getFirstMetal() == "iron") {
                    item1 = Items.IRON_INGOT;
                }
                else if (object.getFirstMetal() == "gold") {
                    item1 = Items.GOLD_INGOT;
                }
                else {
                    item1 = Items.COPPER_INGOT;
                }
                item2 = ModItemsRegister.ITEM_METAL_INGOT.get(object.getSecondMetal()).asItem();
            } else {
                item1 = ModItemsRegister.ITEM_METAL_INGOT.get(object.getFirstMetal()).asItem();
                item2 = ModItemsRegister.ITEM_METAL_INGOT.get(object.getSecondMetal()).asItem();
            }
            //RING
            ShapedRecipeBuilder.shaped(object.getRing())
                    .define('#', item1)
                    .define('x', item2)
                    .pattern("xxx")
                    .pattern("# x")
                    .pattern("###")
                    .unlockedBy("has_item", has(object.getRing()))
                    .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_ring_" + object.getFirstMetal() + "_" + object.getSecondMetal()));

            ShapedRecipeBuilder.shaped(object.getRing())
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

            Item head, body;
            if (object.isGems()) {
                head = ModBlocksRegister.BLOCK_GEMS_BLOCKS.get(object.getName()).asItem();
                body = ModItemsRegister.ITEM_GEMS_BASE.get(object.getName()).asItem();
            } else if (object.isVanilla()) {
                if (object.getName() == "iron") {
                    head = Items.IRON_BLOCK;
                    body = Items.IRON_INGOT;
                } else if (object.getName() == "gold") {
                    head = Items.GOLD_BLOCK;
                    body = Items.GOLD_INGOT;
                } else {
                    head = Items.COPPER_BLOCK;
                    body = Items.COPPER_INGOT;
                }
            } else {
                head = ModBlocksRegister.BLOCK_METAL_BLOCKS.get(object.getName()).asItem();
                body = ModItemsRegister.ITEM_METAL_INGOT.get(object.getName()).asItem();
            }

            ShapedRecipeBuilder.shaped(object.getSpike())
                    .define('#', head)
                    .define('x', body)
                    .pattern("   ")
                    .pattern("xx#")
                    .pattern("   ")
                    .unlockedBy("has_item", has(object.getSpike()))
                    .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_spike_" + object.getName()));
        });

        ShapedRecipeBuilder.shaped(ModItemsRegister.OBSIDIAN_AXE.get())
                .define('O',ModItemsRegister.CORE_OBSIDIAN.get())
                .define('#', Items.OBSIDIAN)
                .define('x',ModItemsRegister.ITEM_METAL_INGOT.get("aluminum"))
                .pattern(" O#")
                .pattern(" x#")
                .pattern(" x ")
                .unlockedBy("has_item",has(ModItemsRegister.OBSIDIAN_AXE.get()))
                .save(recipesConsumer,new ResourceLocation(MetallicsArts.MOD_ID + "_obsidian_axe"));

        ShapedRecipeBuilder.shaped(ModItemsRegister.OBSIDIAN_DAGGER.get())
                .define('#',Items.OBSIDIAN)
                .define('x',ModItemsRegister.ITEM_METAL_INGOT.get("aluminum"))
                .pattern("  #")
                .pattern(" # ")
                .pattern("x  ")
                .unlockedBy("has_item",has(ModItemsRegister.OBSIDIAN_DAGGER.get()))
                .save(recipesConsumer,new ResourceLocation(MetallicsArts.MOD_ID+"_obsidian_dagger"));


        ShapedRecipeBuilder.shaped(ModItemsRegister.DUELING_STAFF.get())
                .define('#', ModItemsRegister.ITEM_METAL_INGOT.get("aluminum"))
                .define('x',Items.STICK)
                .pattern("  #")
                .pattern(" x ")
                .pattern("x  ")
                .unlockedBy("has_item",has(ModItemsRegister.DUELING_STAFF.get()))
                .save(recipesConsumer,new ResourceLocation(MetallicsArts.MOD_ID+"_dueling_staff"));

        ShapedRecipeBuilder.shaped(ModItemsRegister.SILVER_KNIFE.get())
                .define('#', ModItemsRegister.ITEM_METAL_INGOT.get("silver"))
                .define('x', Items.STICK)
                .pattern("  #")
                .pattern(" # ")
                .pattern("x  ")
                .unlockedBy("has_item",has(ModItemsRegister.SILVER_KNIFE.get()))
                .save(recipesConsumer,new ResourceLocation(MetallicsArts.MOD_ID+"_silver_knife"));

        ShapedRecipeBuilder.shaped(ModItemsRegister.KOLOSS_BLADE.get())
                .define('#', Items.COBBLESTONE)
                .define('x', Items.STICK)
                .pattern("## ")
                .pattern("###")
                .pattern(" x ")
                .unlockedBy("has_item",has(ModItemsRegister.KOLOSS_BLADE.get()))
                .save(recipesConsumer,new ResourceLocation(MetallicsArts.MOD_ID+"_koloss_blade"));



        ShapedRecipeBuilder.shaped(ModItemsRegister.SMALL_VIAL.get())
                .define('#',Items.GLASS)
                .define('x',Items.GLASS_BOTTLE)
                .define('+',ItemTags.PLANKS)
                .pattern(" + ")
                .pattern("#x#")
                .pattern(" # ")
                .unlockedBy("has_item",has(ModItemsRegister.SMALL_VIAL.get()))
                .save(recipesConsumer,new ResourceLocation("allomantic_small_vial"));

        ShapedRecipeBuilder.shaped(ModItemsRegister.LARGE_VIAL.get())
                .define('#',Items.GLASS)
                .define('x', ModItemsRegister.SMALL_VIAL.get())
                .define('+',ItemTags.PLANKS)
                .pattern(" + ")
                .pattern("#x#")
                .pattern(" # ")
                .unlockedBy("has_item",has(ModItemsRegister.LARGE_VIAL.get()))
                .save(recipesConsumer,new ResourceLocation("allomantic_large_vial"));

        ShapelessRecipeBuilder.shapeless(ModItemsRegister.ITEM_METAL_INGOT.get("steel"),2).
                requires(Items.IRON_INGOT)
                .requires(Items.COAL)
                .requires(Items.COAL)
                .requires(Items.COAL)
                .unlockedBy("has_item",has(ModItemsRegister.ITEM_METAL_INGOT.get("steel")))
                .save(recipesConsumer,new ResourceLocation(MetallicsArts.MOD_ID+"_steel_alloy_craft"));

        ShapelessRecipeBuilder.shapeless(ModItemsRegister.ITEM_METAL_INGOT.get("pewter"),2)
                .requires(ModItemsRegister.ITEM_METAL_INGOT.get("lead"))
                .requires(ModItemsRegister.ITEM_METAL_INGOT.get("tin"))
                .requires(ModItemsRegister.ITEM_METAL_INGOT.get("tin"))
                .requires(ModItemsRegister.ITEM_METAL_INGOT.get("tin"))
                .unlockedBy("has_item",has(ModItemsRegister.ITEM_METAL_INGOT.get("pewter")))
                .save(recipesConsumer,new ResourceLocation(MetallicsArts.MOD_ID+"_pewter_alloy_craft"));

        ShapelessRecipeBuilder.shapeless(ModItemsRegister.ITEM_METAL_INGOT.get("bronze"),2)
                .requires(ModItemsRegister.ITEM_METAL_INGOT.get("tin"))
                .requires(Items.COPPER_INGOT)
                .requires(Items.COPPER_INGOT)
                .requires(Items.COPPER_INGOT)
                .unlockedBy("has_item",has(ModItemsRegister.ITEM_METAL_INGOT.get("bronze")))
                .save(recipesConsumer,new ResourceLocation(MetallicsArts.MOD_ID+"_bronze_alloy_craft"));

        ShapelessRecipeBuilder.shapeless(ModItemsRegister.ITEM_METAL_INGOT.get("brass"),2)
                .requires(ModItemsRegister.ITEM_METAL_INGOT.get("zinc"))
                .requires(ModItemsRegister.ITEM_METAL_INGOT.get("zinc"))
                .requires(Items.COPPER_INGOT)
                .requires(Items.COPPER_INGOT)
                .unlockedBy("has_item",has(ModItemsRegister.ITEM_METAL_INGOT.get("brass")))
                .save(recipesConsumer,new ResourceLocation(MetallicsArts.MOD_ID+"_brass_alloy_craft"));

        ShapelessRecipeBuilder.shapeless(ModItemsRegister.ITEM_METAL_INGOT.get("electrum"),2)
                .requires(ModItemsRegister.ITEM_METAL_INGOT.get("silver"))
                .requires(ModItemsRegister.ITEM_METAL_INGOT.get("silver"))
                .requires(ModItemsRegister.ITEM_METAL_INGOT.get("silver"))
                .requires(Items.GOLD_INGOT)
                .unlockedBy("has_item",has(ModItemsRegister.ITEM_METAL_INGOT.get("electrum")))
                .save(recipesConsumer,new ResourceLocation(MetallicsArts.MOD_ID+"_electrum_alloy_craft"));

        ShapelessRecipeBuilder.shapeless(ModItemsRegister.ITEM_METAL_INGOT.get("bendalloy"),2)
                .requires(ModItemsRegister.ITEM_METAL_INGOT.get("lead"))
                .requires(ModItemsRegister.ITEM_METAL_INGOT.get("lead"))
                .requires(ModItemsRegister.ITEM_METAL_INGOT.get("cadmium"))
                .requires(ModItemsRegister.ITEM_METAL_INGOT.get("cadmium"))
                .unlockedBy("has_item",has(ModItemsRegister.ITEM_METAL_INGOT.get("bendalloy")))
                .save(recipesConsumer,new ResourceLocation(MetallicsArts.MOD_ID+"_bendalloy_alloy_craft"));

        ShapelessRecipeBuilder.shapeless(ModItemsRegister.ITEM_METAL_INGOT.get("duralumin"),2)
                .requires(ModItemsRegister.ITEM_METAL_INGOT.get("aluminum"))
                .requires(ModItemsRegister.ITEM_METAL_INGOT.get("aluminum"))
                .requires(ModItemsRegister.ITEM_METAL_INGOT.get("aluminum"))
                .requires(Items.COPPER_INGOT)
                .unlockedBy("has_item",has(ModItemsRegister.ITEM_METAL_INGOT.get("duralumin")))
                .save(recipesConsumer,new ResourceLocation(MetallicsArts.MOD_ID+"_duralumin_alloy_craft"));

        ShapelessRecipeBuilder.shapeless(ModItemsRegister.ITEM_METAL_INGOT.get("nicrosil"),2)
                .requires(ModItemsRegister.ITEM_METAL_INGOT.get("nickel"))
                .requires(ModItemsRegister.ITEM_METAL_INGOT.get("nickel"))
                .requires(ModItemsRegister.ITEM_METAL_INGOT.get("nickel"))
                .requires(ModItemsRegister.ITEM_METAL_INGOT.get("chromium"))
                .unlockedBy("has_item",has(ModItemsRegister.ITEM_METAL_INGOT.get("nicrosil")))
                .save(recipesConsumer,new ResourceLocation(MetallicsArts.MOD_ID+"_nicrosil_alloy_craft"));

        ShapelessRecipeBuilder.shapeless(ModItemsRegister.ITEM_GEMS_BASE.get("malatium"),2)
                .requires(ModItemsRegister.ITEM_GEMS_BASE.get("atium"))
                .requires(ModItemsRegister.ITEM_GEMS_BASE.get("atium"))
                .requires(Items.GOLD_INGOT)
                .requires(Items.GOLD_INGOT)
                .unlockedBy("has_item",has(ModItemsRegister.ITEM_GEMS_BASE.get("malatium")))
                .save(recipesConsumer,new ResourceLocation(MetallicsArts.MOD_ID+"_malatium_alloy_craft"));

        SpecialRecipeBuilder.special(ModRecipeTypesRegister.LARGE_VIAL_ITEM_RECIPE_SERIALIZER.get()).save(recipesConsumer, MetallicsArts.MOD_ID+":large_vial_filling_recipe");
        SpecialRecipeBuilder.special(ModRecipeTypesRegister.SMALL_VIAL_ITEM_RECIPE_SERIALIZER.get()).save(recipesConsumer, MetallicsArts.MOD_ID+":small_vial_filling_recipe");

        ModItemsRegister.ITEM_ICONS_ALLOMANCY.forEach((key, value) -> {
                ShapedRecipeBuilder.shaped(value)
                        .define('#',Items.GLASS_PANE)
                        .define('x',(key.contains("gold")) ? Items.GOLD_INGOT : (key.contains("copper")) ? Items.COPPER_INGOT : (key.contains("iron")) ? Items.IRON_INGOT: ModItemsRegister.ITEM_METAL_INGOT.get(key))
                        .pattern(" # ")
                        .pattern("#x#")
                        .pattern(" # ")
                        .unlockedBy("has_item", has(value))
                        .save(recipesConsumer,new ResourceLocation(key + "_allomantic_icon"));
        });

        ModItemsRegister.ITEM_ICONS_FERUCHEMIC.forEach((key, value) -> {
            ShapedRecipeBuilder.shaped(value)
                    .define('#',Items.GLASS_PANE)
                    .define('x',(key.contains("gold")) ? Items.GOLD_INGOT : (key.contains("copper")) ? Items.COPPER_INGOT : (key.contains("iron")) ? Items.IRON_INGOT: ModItemsRegister.ITEM_METAL_INGOT.get(key))
                    .pattern("# #")
                    .pattern(" x ")
                    .pattern("# #")
                    .unlockedBy("has_item", has(value))
                    .save(recipesConsumer,new ResourceLocation(key+"_feruchemic_icon"));
        });

        for (MetalTagEnum metal : MetalTagEnum.values()) {

            ShapelessRecipeBuilder.shapeless(ModBannersRegister.PATTERN_ITEMS.get("a_"+metal.getNameLower()).get())

                    .requires(!metal.isDivine() ? ModItemsRegister.ITEM_ICONS_ALLOMANCY.get(metal.getNameLower()) : ModItemsRegister.ITEM_ICONS_ALLOMANCY_DIVINE.get(metal.getNameLower()))
                    .requires(Items.PAPER)
                    .unlockedBy("has_item",has(ModBannersRegister.PATTERN_ITEMS.get("a_"+metal.getNameLower()).get()))
                    .save(recipesConsumer,new ResourceLocation(MetallicsArts.MOD_ID + "_" + ModBannersRegister.PATTERN_ITEMS.get("a_"+metal.getNameLower()).get()));

            ShapelessRecipeBuilder.shapeless(ModBannersRegister.PATTERN_ITEMS.get("f_"+metal.getNameLower()).get())
                    .requires(!metal.isDivine() ? ModItemsRegister.ITEM_ICONS_FERUCHEMIC.get(metal.getNameLower()) : ModItemsRegister.ITEM_ICONS_FERUCHEMIC_DIVINE.get(metal.getNameLower()))
                    .requires(Items.PAPER)
                    .unlockedBy("has_item",has(ModBannersRegister.PATTERN_ITEMS.get("f_"+metal.getNameLower()).get()))
                    .save(recipesConsumer,new ResourceLocation(MetallicsArts.MOD_ID + "_" + ModBannersRegister.PATTERN_ITEMS.get("f_"+metal.getNameLower()).get()));

        }

        ShapedRecipeBuilder.shaped(ModItemsRegister.CORE_OBSIDIAN.get())
                .define('#', Items.OBSIDIAN)
                .define('*', Items.NETHERITE_SCRAP)
                .define('X', Items.GOLD_INGOT)
                .pattern("#X#")
                .pattern("X*X")
                .pattern("#X#")
                .unlockedBy("has_block", has(ModItemsRegister.CORE_OBSIDIAN.get()))
                .save(recipesConsumer, new ResourceLocation(ModItemsRegister.CORE_OBSIDIAN.get().getDescriptionId()));

        ShapedRecipeBuilder.shaped(ModItemsRegister.CORE_ALUMINUM.get())
                .define('#', ModBlocksRegister.BLOCK_METAL_BLOCKS.get("aluminum"))
                .define('*', Items.END_CRYSTAL)
                .define('X', Items.NETHERITE_SCRAP)
                .pattern("#X#")
                .pattern("X*X")
                .pattern("#X#")
                .unlockedBy("has_block", has(ModItemsRegister.CORE_ALUMINUM.get()))
                .save(recipesConsumer, new ResourceLocation(ModItemsRegister.CORE_ALUMINUM.get().getDescriptionId()));

        ShapedRecipeBuilder.shaped(ModItemsRegister.CORE_STEEL.get())
                .define('#', ModBlocksRegister.BLOCK_METAL_BLOCKS.get("steel"))
                .define('*', Items.END_CRYSTAL)
                .define('X', Items.NETHERITE_SCRAP)
                .pattern("#X#")
                .pattern("X*X")
                .pattern("#X#")
                .unlockedBy("has_block", has(ModItemsRegister.CORE_STEEL.get()))
                .save(recipesConsumer, new ResourceLocation(ModItemsRegister.CORE_STEEL.get().getDescriptionId()));

        UpgradeRecipeBuilder.smithing(
                        Ingredient.of(Items.NETHERITE_HELMET),
                        Ingredient.of(ModItemsRegister.CORE_STEEL.get()),
                        ModItemsRegister.STEEL_ARMOR.get(ArmorPiecesEnum.HELMET).get())
                .unlocks("has_armor", has(ModItemsRegister.STEEL_ARMOR.get(ArmorPiecesEnum.HELMET).get()))
                .save(recipesConsumer, new ResourceLocation(ModItemsRegister.STEEL_ARMOR.get(ArmorPiecesEnum.HELMET).get().getDescriptionId()));
        UpgradeRecipeBuilder.smithing(
                Ingredient.of(Items.NETHERITE_CHESTPLATE),
                Ingredient.of(ModItemsRegister.CORE_STEEL.get()),
                ModItemsRegister.STEEL_ARMOR.get(ArmorPiecesEnum.CHESTPLATE).get())
                .unlocks("has_armor", has(ModItemsRegister.STEEL_ARMOR.get(ArmorPiecesEnum.CHESTPLATE).get()))
                .save(recipesConsumer, new ResourceLocation(ModItemsRegister.STEEL_ARMOR.get(ArmorPiecesEnum.CHESTPLATE).get().getDescriptionId()));
        UpgradeRecipeBuilder.smithing(
                        Ingredient.of(Items.NETHERITE_LEGGINGS),
                        Ingredient.of(ModItemsRegister.CORE_STEEL.get()),
                        ModItemsRegister.STEEL_ARMOR.get(ArmorPiecesEnum.LEGGINGS).get())
                .unlocks("has_armor", has(ModItemsRegister.STEEL_ARMOR.get(ArmorPiecesEnum.LEGGINGS).get()))
                .save(recipesConsumer, new ResourceLocation(ModItemsRegister.STEEL_ARMOR.get(ArmorPiecesEnum.LEGGINGS).get().getDescriptionId()));
        UpgradeRecipeBuilder.smithing(
                        Ingredient.of(Items.NETHERITE_BOOTS),
                        Ingredient.of(ModItemsRegister.CORE_STEEL.get()),
                        ModItemsRegister.STEEL_ARMOR.get(ArmorPiecesEnum.BOOTS).get())
                .unlocks("has_armor", has(ModItemsRegister.STEEL_ARMOR.get(ArmorPiecesEnum.BOOTS).get()))
                .save(recipesConsumer, new ResourceLocation(ModItemsRegister.STEEL_ARMOR.get(ArmorPiecesEnum.BOOTS).get().getDescriptionId()));



        UpgradeRecipeBuilder.smithing(
                        Ingredient.of(Items.NETHERITE_HELMET),
                        Ingredient.of(ModItemsRegister.CORE_ALUMINUM.get()),
                        ModItemsRegister.ALUMINUM_ARMOR.get(ArmorPiecesEnum.HELMET).get())
                .unlocks("has_armor", has(ModItemsRegister.ALUMINUM_ARMOR.get(ArmorPiecesEnum.HELMET).get()))
                .save(recipesConsumer, new ResourceLocation(ModItemsRegister.ALUMINUM_ARMOR.get(ArmorPiecesEnum.HELMET).get().getDescriptionId()));
        UpgradeRecipeBuilder.smithing(
                        Ingredient.of(Items.NETHERITE_CHESTPLATE),
                        Ingredient.of(ModItemsRegister.CORE_ALUMINUM.get()),
                        ModItemsRegister.ALUMINUM_ARMOR.get(ArmorPiecesEnum.CHESTPLATE).get())
                .unlocks("has_armor", has(ModItemsRegister.ALUMINUM_ARMOR.get(ArmorPiecesEnum.CHESTPLATE).get()))
                .save(recipesConsumer, new ResourceLocation(ModItemsRegister.ALUMINUM_ARMOR.get(ArmorPiecesEnum.CHESTPLATE).get().getDescriptionId()));
        UpgradeRecipeBuilder.smithing(
                        Ingredient.of(Items.NETHERITE_LEGGINGS),
                        Ingredient.of(ModItemsRegister.CORE_ALUMINUM.get()),
                        ModItemsRegister.ALUMINUM_ARMOR.get(ArmorPiecesEnum.LEGGINGS).get())
                .unlocks("has_armor", has(ModItemsRegister.ALUMINUM_ARMOR.get(ArmorPiecesEnum.LEGGINGS).get()))
                .save(recipesConsumer, new ResourceLocation(ModItemsRegister.ALUMINUM_ARMOR.get(ArmorPiecesEnum.LEGGINGS).get().getDescriptionId()));
        UpgradeRecipeBuilder.smithing(
                        Ingredient.of(Items.NETHERITE_BOOTS),
                        Ingredient.of(ModItemsRegister.CORE_ALUMINUM.get()),
                        ModItemsRegister.ALUMINUM_ARMOR.get(ArmorPiecesEnum.BOOTS).get())
                .unlocks("has_armor", has(ModItemsRegister.ALUMINUM_ARMOR.get(ArmorPiecesEnum.BOOTS).get()))
                .save(recipesConsumer, new ResourceLocation(ModItemsRegister.ALUMINUM_ARMOR.get(ArmorPiecesEnum.BOOTS).get().getDescriptionId()));

        ShapedRecipeBuilder.shaped(ModItemsRegister.MISTCLOACK.get())
                .define('#', Items.STRING)
                .define('*', Items.PHANTOM_MEMBRANE)
                .define('X', Items.BLACK_WOOL)
                .pattern("XXX")
                .pattern("###")
                .pattern("***")
                .unlockedBy("has_block", has(ModItemsRegister.MISTCLOACK.get()))
                .save(recipesConsumer, new ResourceLocation(ModItemsRegister.MISTCLOACK.get().getDescriptionId()));
    }
}






