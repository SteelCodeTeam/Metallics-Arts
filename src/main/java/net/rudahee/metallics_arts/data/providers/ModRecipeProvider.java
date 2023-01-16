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
import net.rudahee.metallics_arts.data.enums.implementations.custom_items.MetalMindEnum;
import net.rudahee.metallics_arts.data.enums.implementations.custom_items.SpikeEnum;
import net.rudahee.metallics_arts.setup.registries.ModBannersRegister;
import net.rudahee.metallics_arts.setup.registries.ModBlocksRegister;
import net.rudahee.metallics_arts.setup.registries.ModItemsRegister;
import net.rudahee.metallics_arts.setup.registries.ModRecipeTypesRegister;

import java.util.Arrays;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> recipesConsumer) {

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

        /*ModBlock.BLOCK_METAL_ORES.forEach((name, block) -> {
            SimpleCookingRecipeBuilder.cooking(Ingredient.of(block), ModItems.ITEM_METAL_INGOT.get(name), 0.5f, 100, RecipeSerializer.SMELTING_RECIPE)
                    .unlockedBy("has_block", has(block))
                    .save(recipesConsumer, new ResourceLocation(block.getDescriptionId() + "_to_" + ModItems.ITEM_METAL_INGOT.get(name).getDescriptionId() + "_furnace"));
        });

        ModBlock.BLOCK_METAL_ORES.forEach((name, block) -> {
            SimpleCookingRecipeBuilder.blasting(Ingredient.of(block), ModItems.ITEM_METAL_INGOT.get(name), 0.5f, 100)
                    .unlockedBy("has_block", has(block))
                    .save(recipesConsumer, new ResourceLocation(block.getDescriptionId() + "_to_" + ModItems.ITEM_METAL_INGOT.get(name).getDescriptionId() + "_blasting"));

        });*/

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
            //RINGS
            /*ShapedRecipeBuilder.shaped(object.getRing())
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
*/
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


        /*ShapedRecipeBuilder.shaped(ModBlock.ALLOY_FURNACE_BLOCK.get().asItem())
                .define('#', Items.BLAST_FURNACE)
                .define('X', Items.STONE_BRICKS)
                .define('_', Items.STONE_BRICK_SLAB)
                .define('U', Items.COAL_BLOCK)
                .pattern("___")
                .pattern("X#X")
                .pattern("UUU")
                .unlockedBy("has_item", has(Items.BLAST_FURNACE))
                .save(recipesConsumer, new ResourceLocation("allomantic_alloy_furnace"));*/


        ShapedRecipeBuilder.shaped(ModItemsRegister.OBSIDIAN_AXE.get())
                .define('#',Items.OBSIDIAN)
                .define('x',Items.STICK)
                .pattern(" ##")
                .pattern(" x#")
                .pattern(" x ")
                .unlockedBy("has_item",has(ModItemsRegister.OBSIDIAN_AXE.get()))
                .save(recipesConsumer,new ResourceLocation(MetallicsArts.MOD_ID + "_obsidian_axe"));

        ShapedRecipeBuilder.shaped(ModItemsRegister.OBSIDIAN_DAGGER.get())
                .define('#',Items.OBSIDIAN)
                .define('x',Items.STICK)
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

        ShapedRecipeBuilder.shaped(ModItemsRegister.CRISTAL_DAGGER.get())
                .define('#', Items.GLASS_PANE)
                .define('x', Items.STICK)
                .pattern("  #")
                .pattern(" # ")
                .pattern("x  ")
                .unlockedBy("has_item",has(ModItemsRegister.CRISTAL_DAGGER.get()))
                .save(recipesConsumer,new ResourceLocation(MetallicsArts.MOD_ID+"_cristal_dagger"));

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
                        .save(recipesConsumer,new ResourceLocation(key+"_allomantic_icon"));
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
                    .save(recipesConsumer,new ResourceLocation(MetallicsArts.MOD_ID+ ModBannersRegister.PATTERN_ITEMS.get("a_"+metal.getNameLower()).get()+"_pattern"));

            ShapelessRecipeBuilder.shapeless(ModBannersRegister.PATTERN_ITEMS.get("f_"+metal.getNameLower()).get())
                    .requires(!metal.isDivine() ? ModItemsRegister.ITEM_ICONS_FERUCHEMIC.get(metal.getNameLower()) : ModItemsRegister.ITEM_ICONS_FERUCHEMIC_DIVINE.get(metal.getNameLower()))
                    .requires(Items.PAPER)
                    .unlockedBy("has_item",has(ModBannersRegister.PATTERN_ITEMS.get("f_"+metal.getNameLower()).get()))
                    .save(recipesConsumer,new ResourceLocation(MetallicsArts.MOD_ID+ ModBannersRegister.PATTERN_ITEMS.get("f_"+metal.getNameLower()).get()+"_pattern"));

        }

    }
}






