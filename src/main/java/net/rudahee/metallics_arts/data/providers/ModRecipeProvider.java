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
import net.rudahee.metallics_arts.setup.enums.extras.MetalMindData;
import net.rudahee.metallics_arts.setup.enums.extras.MetalSpikesData;
import net.rudahee.metallics_arts.setup.enums.metals.Metal;
import net.rudahee.metallics_arts.setup.registries.ModBlock;
import net.rudahee.metallics_arts.setup.registries.ModItems;
import net.rudahee.metallics_arts.setup.registries.ModRecipeTypes;

import java.util.Arrays;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> recipesConsumer) {

        ModItems.ITEM_METAL_INGOT.forEach((name, item) -> {
            ShapelessRecipeBuilder.shapeless(item.asItem(), 9)
                    .requires(ModBlock.BLOCK_METAL_BLOCKS.get(name))
                    .unlockedBy("has_item", has(item))
                    .save(recipesConsumer, new ResourceLocation(item.getDescriptionId() + "_to_" + ModBlock.BLOCK_METAL_BLOCKS.get(name).getDescriptionId()));
        });

        ModBlock.BLOCK_METAL_BLOCKS.forEach((name, block) -> {
            ShapedRecipeBuilder.shaped(block)
                    .define('#', ModItems.ITEM_METAL_INGOT.get(name))
                    .pattern("###")
                    .pattern("###")
                    .pattern("###")
                    .unlockedBy("has_block", has(block))
                    .save(recipesConsumer, new ResourceLocation(block.getDescriptionId() + "_to_" + ModItems.ITEM_METAL_INGOT.get(name).getDescriptionId()));
        });

        ModBlock.RAW_METAL_BLOCKS.forEach((name, block) -> {
            ShapedRecipeBuilder.shaped(block)
                    .define('#', ModItems.ITEM_RAW_METAL.get(name))
                    .pattern("###")
                    .pattern("###")
                    .pattern("###")
                    .unlockedBy("has_block", has(block))
                    .save(recipesConsumer, new ResourceLocation(block.getDescriptionId() + "_to_" + ModItems.ITEM_RAW_METAL.get(name).getDescriptionId()));
        });


        ModItems.ITEM_GEMS_BASE.forEach((name, item) -> {
            ShapelessRecipeBuilder.shapeless(item.asItem(), 9)
                    .requires(ModBlock.BLOCK_GEMS_BLOCKS.get(name))
                    .unlockedBy("has_item", has(item))
                    .save(recipesConsumer, new ResourceLocation(item.getDescriptionId() + "_to_" + ModBlock.BLOCK_GEMS_BLOCKS.get(name).getDescriptionId()));
        });

        ModBlock.BLOCK_GEMS_BLOCKS.forEach((name, block) -> {
            ShapedRecipeBuilder.shaped(block)
                    .define('#', ModItems.ITEM_GEMS_BASE.get(name))
                    .pattern("###")
                    .pattern("###")
                    .pattern("###")
                    .unlockedBy("has_block", has(block))
                    .save(recipesConsumer, new ResourceLocation(block.getDescriptionId() + "_to_" + ModItems.ITEM_GEMS_BASE.get(name).getDescriptionId()));
        });
        ModItems.ITEM_METAL_INGOT.forEach((name, item) -> {
            ShapelessRecipeBuilder.shapeless(ModItems.ITEM_METAL_NUGGET.get(name), 9)
                    .requires(item)
                    .unlockedBy("has_block", has(item))
                    .save(recipesConsumer, new ResourceLocation(item.getDescriptionId() + "_to_" + ModItems.ITEM_METAL_NUGGET.get(name).getDescriptionId()));


        });

        ShapelessRecipeBuilder.shapeless(ModItems.ITEM_METAL_NUGGET.get("copper_nugget"), 9)
                .requires(Items.COPPER_INGOT)
                .unlockedBy("has_block", has(Items.COPPER_INGOT))
                .save(recipesConsumer, new ResourceLocation(Items.COPPER_INGOT.getDescriptionId() + "_to_" + ModItems.ITEM_METAL_NUGGET.get("copper_nugget").getDescriptionId()));


        ModItems.ITEM_METAL_NUGGET.forEach((name, item) -> {
            if (!name.equals("copper_nugget")) {
                ShapedRecipeBuilder.shaped(ModItems.ITEM_METAL_INGOT.get(name))
                        .define('#', ModItems.ITEM_METAL_NUGGET.get(name))
                        .pattern("###")
                        .pattern("###")
                        .pattern("###")
                        .unlockedBy("has_block", has(item))
                        .save(recipesConsumer, new ResourceLocation(item.getDescriptionId() + "_to_" + ModItems.ITEM_METAL_INGOT.get(name).getDescriptionId()));
            } else {

                ShapedRecipeBuilder.shaped(Items.COPPER_INGOT)
                        .define('#', ModItems.ITEM_METAL_NUGGET.get(name))
                        .pattern("###")
                        .pattern("###")
                        .pattern("###")
                        .unlockedBy("has_block", has(item))
                        .save(recipesConsumer, new ResourceLocation(item.getDescriptionId() + "_to_" + Items.COPPER_INGOT.getDescriptionId()));
            }

        });

        ModItems.ITEM_GEMS_BASE.forEach((name, item) -> {
            ShapelessRecipeBuilder.shapeless(ModItems.ITEM_GEMS_NUGGET.get(name), 9)
                    .requires(item)
                    .unlockedBy("has_block", has(item))
                    .save(recipesConsumer, new ResourceLocation(item.getDescriptionId() + "_to_" + ModItems.ITEM_GEMS_NUGGET.get(name).getDescriptionId()));
        });

        ModItems.ITEM_GEMS_NUGGET.forEach((name, item) -> {
            ShapedRecipeBuilder.shaped(ModItems.ITEM_GEMS_BASE.get(name))
                    .define('#', ModItems.ITEM_GEMS_NUGGET.get(name))
                    .pattern("###")
                    .pattern("###")
                    .pattern("###")
                    .unlockedBy("has_block", has(item))
                    .save(recipesConsumer, new ResourceLocation(item.getDescriptionId() + "_to_" + ModItems.ITEM_GEMS_BASE.get(name).getDescriptionId()));
        });

        Arrays.asList(Metal.values()).forEach(metal -> {
            if (!metal.isAlloy()){
                SimpleCookingRecipeBuilder.cooking(Ingredient.of(ModItems.ITEM_RAW_METAL.get(metal.getMetalNameLower())), ModItems.ITEM_METAL_INGOT.get(metal.getMetalNameLower()), 0.5f, 250, RecipeSerializer.SMELTING_RECIPE)
                        .unlockedBy("has_block", has(ModItems.ITEM_RAW_METAL.get(metal.getMetalNameLower())))
                        .save(recipesConsumer, new ResourceLocation(ModItems.ITEM_RAW_METAL.get(metal.getMetalNameLower()).getDescriptionId() + "_to_" + ModItems.ITEM_METAL_INGOT.get(metal.getMetalNameLower()).getDescriptionId() + "_furnace"));
            }
        });

        Arrays.asList(Metal.values()).forEach(metal -> {
            if (!metal.isAlloy()){
                SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModItems.ITEM_RAW_METAL.get(metal.getMetalNameLower())), ModItems.ITEM_METAL_INGOT.get(metal.getMetalNameLower()), 0.8f, 100)
                        .unlockedBy("has_block", has(ModItems.ITEM_RAW_METAL.get(metal.getMetalNameLower())))
                        .save(recipesConsumer, new ResourceLocation(ModItems.ITEM_RAW_METAL.get(metal.getMetalNameLower()).getDescriptionId() + "_to_" + ModItems.ITEM_METAL_INGOT.get(metal.getMetalNameLower()).getDescriptionId() + "_blast"));
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

        Arrays.asList(MetalMindData.values()).forEach(object -> {
            Item item1, item2;

            //BLOCKS
            if (object.isGems()) {
                item1 = ModBlock.BLOCK_GEMS_BLOCKS.get(object.getFirstMetal()).asItem();
                item2 = ModBlock.BLOCK_GEMS_BLOCKS.get(object.getSecondMetal()).asItem();
            } else if (object.isVanilla()) {
                if (object.getSecondMetal() == "iron") {
                    item1 = Items.IRON_BLOCK;
                }
                else if (object.getSecondMetal() == "gold") {
                    item1 = Items.GOLD_BLOCK;
                }
                else {
                    item1 = Items.COPPER_BLOCK;
                }
                item2 = ModBlock.BLOCK_METAL_BLOCKS.get(object.getFirstMetal()).asItem();
            } else {
                item1 = ModBlock.BLOCK_METAL_BLOCKS.get(object.getFirstMetal()).asItem();
                item2 = ModBlock.BLOCK_METAL_BLOCKS.get(object.getSecondMetal()).asItem();
            }
            //BANDS
            ShapedRecipeBuilder.shaped(object.getBand())
                    .define('#', item1)
                    .define('x', item2)
                    .pattern("xxx")
                    .pattern("# x")
                    .pattern("###")
                    .unlockedBy("has_item", has(object.getBand()))
                    .save(recipesConsumer, new ResourceLocation("alomantic_arts_band_" + object.getFirstMetal() + "_" + object.getSecondMetal()));

            ShapedRecipeBuilder.shaped(object.getBand())
                    .define('#', item2)
                    .define('x', item1)
                    .pattern("xxx")
                    .pattern("# x")
                    .pattern("###")
                    .unlockedBy("has_item", has(object.getBand()))
                    .save(recipesConsumer, new ResourceLocation("alomantic_arts_band_" + object.getFirstMetal() + "_" + object.getSecondMetal() + "2"));


            //INGOTS
            if (object.isGems()) {
                item1 = ModItems.ITEM_GEMS_BASE.get(object.getFirstMetal()).asItem();
                item2 = ModItems.ITEM_GEMS_BASE.get(object.getSecondMetal()).asItem();
            } else if (object.isVanilla()) {
                if (object.getSecondMetal() == "iron") {item1 = Items.IRON_INGOT;}
                else if (object.getSecondMetal() == "gold") {item1 = Items.GOLD_INGOT;}
                else {item1 = Items.COPPER_INGOT;}

                item2 = ModItems.ITEM_METAL_INGOT.get(object.getFirstMetal()).asItem();
            } else {
                item1 = ModItems.ITEM_METAL_INGOT.get(object.getFirstMetal()).asItem();
                item2 = ModItems.ITEM_METAL_INGOT.get(object.getSecondMetal()).asItem();
            }

            //RINGS
            ShapedRecipeBuilder.shaped(object.getRing())
                    .define('#', item1)
                    .define('x', item2)
                    .pattern("xxx")
                    .pattern("# x")
                    .pattern("###")
                    .unlockedBy("has_item", has(object.getRing()))
                    .save(recipesConsumer, new ResourceLocation("alomantic_arts_ring_" + object.getFirstMetal() + "_" + object.getSecondMetal()));

            ShapedRecipeBuilder.shaped(object.getRing())
                    .define('#', item2)
                    .define('x', item1)
                    .pattern("xxx")
                    .pattern("# x")
                    .pattern("###")
                    .unlockedBy("has_item", has(object.getRing()))
                    .save(recipesConsumer, new ResourceLocation("alomantic_arts_ring_" + object.getFirstMetal() + "_" + object.getSecondMetal() + "2"));
        });

        //SPIKES
        Arrays.asList(MetalSpikesData.values()).forEach(object -> {

            Item head, body;
            if (object.isGems()) {
                head = ModBlock.BLOCK_GEMS_BLOCKS.get(object.getName()).asItem();
                body = ModItems.ITEM_GEMS_BASE.get(object.getName()).asItem();
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
                head = ModBlock.BLOCK_METAL_BLOCKS.get(object.getName()).asItem();
                body = ModItems.ITEM_METAL_INGOT.get(object.getName()).asItem();
            }

            ShapedRecipeBuilder.shaped(object.getSpike())
                    .define('#', head)
                    .define('x', body)
                    .pattern("   ")
                    .pattern("xx#")
                    .pattern("   ")
                    .unlockedBy("has_item", has(object.getSpike()))
                    .save(recipesConsumer, new ResourceLocation("allomantic_arts_spike_" + object.getName()));
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


        ShapedRecipeBuilder.shaped(ModItems.OBSIDIAN_AXE.get())
                .define('#',Items.OBSIDIAN)
                .define('x',Items.STICK)
                .pattern(" ##")
                .pattern(" x#")
                .pattern(" x ")
                .unlockedBy("has_item",has(ModItems.OBSIDIAN_AXE.get()))
                .save(recipesConsumer,new ResourceLocation(MetallicsArts.MOD_ID+"_obsidian_axe"));

        ShapedRecipeBuilder.shaped(ModItems.OBSIDIAN_DAGGER.get())
                .define('#',Items.OBSIDIAN)
                .define('x',Items.STICK)
                .pattern("  #")
                .pattern(" # ")
                .pattern("x  ")
                .unlockedBy("has_item",has(ModItems.OBSIDIAN_DAGGER.get()))
                .save(recipesConsumer,new ResourceLocation(MetallicsArts.MOD_ID+"_obsidian_dagger"));


        ShapedRecipeBuilder.shaped(ModItems.DUELING_STAFF.get())
                .define('#', ModItems.ITEM_METAL_INGOT.get("aluminum"))
                .define('x',Items.STICK)
                .pattern("  #")
                .pattern(" x ")
                .pattern("x  ")
                .unlockedBy("has_item",has(ModItems.DUELING_STAFF.get()))
                .save(recipesConsumer,new ResourceLocation(MetallicsArts.MOD_ID+"_dueling_staff"));

        ShapedRecipeBuilder.shaped(ModItems.CRISTAL_DAGGER.get())
                .define('#', Items.GLASS_PANE)
                .define('x', Items.STICK)
                .pattern("  #")
                .pattern(" # ")
                .pattern("x  ")
                .unlockedBy("has_item",has(ModItems.CRISTAL_DAGGER.get()))
                .save(recipesConsumer,new ResourceLocation(MetallicsArts.MOD_ID+"_cristal_dagger"));

        ShapedRecipeBuilder.shaped(ModItems.KOLOSS_BLADE.get())
                .define('#', Items.COBBLESTONE)
                .define('x', Items.STICK)
                .pattern("## ")
                .pattern("###")
                .pattern(" x ")
                .unlockedBy("has_item",has(ModItems.KOLOSS_BLADE.get()))
                .save(recipesConsumer,new ResourceLocation(MetallicsArts.MOD_ID+"_koloss_blade"));



        ShapedRecipeBuilder.shaped(ModItems.SMALL_VIAL.get())
                .define('#',Items.GLASS)
                .define('x',Items.GLASS_BOTTLE)
                .define('+',ItemTags.PLANKS)
                .pattern(" + ")
                .pattern("#x#")
                .pattern(" # ")
                .unlockedBy("has_item",has(ModItems.SMALL_VIAL.get()))
                .save(recipesConsumer,new ResourceLocation("allomantic_small_vial"));

        ShapedRecipeBuilder.shaped(ModItems.LARGE_VIAL.get())
                .define('#',Items.GLASS)
                .define('x',ModItems.SMALL_VIAL.get())
                .define('+',ItemTags.PLANKS)
                .pattern(" + ")
                .pattern("#x#")
                .pattern(" # ")
                .unlockedBy("has_item",has(ModItems.LARGE_VIAL.get()))
                .save(recipesConsumer,new ResourceLocation("allomantic_large_vial"));


        ShapelessRecipeBuilder.shapeless(ModItems.ITEM_METAL_INGOT.get("steel")).
                requires(Items.IRON_INGOT)
                .requires(Items.COAL)
                .requires(Items.COAL)
                .requires(Items.COAL)
                .unlockedBy("has_item",has(ModItems.ITEM_METAL_INGOT.get("steel")))
                .save(recipesConsumer,new ResourceLocation(MetallicsArts.MOD_ID+"_steel_alloy_craft"));

        ShapelessRecipeBuilder.shapeless(ModItems.ITEM_METAL_INGOT.get("pewter"))
                .requires(ModItems.ITEM_METAL_INGOT.get("lead"))
                .requires(ModItems.ITEM_METAL_INGOT.get("tin"))
                .requires(ModItems.ITEM_METAL_INGOT.get("tin"))
                .requires(ModItems.ITEM_METAL_INGOT.get("tin"))
                .unlockedBy("has_item",has(ModItems.ITEM_METAL_INGOT.get("pewter")))
                .save(recipesConsumer,new ResourceLocation(MetallicsArts.MOD_ID+"_pewter_alloy_craft"));

        ShapelessRecipeBuilder.shapeless(ModItems.ITEM_METAL_INGOT.get("bronze"))
                .requires(ModItems.ITEM_METAL_INGOT.get("tin"))
                .requires(Items.COPPER_INGOT)
                .requires(Items.COPPER_INGOT)
                .requires(Items.COPPER_INGOT)
                .unlockedBy("has_item",has(ModItems.ITEM_METAL_INGOT.get("bronze")))
                .save(recipesConsumer,new ResourceLocation(MetallicsArts.MOD_ID+"_bronze_alloy_craft"));

        ShapelessRecipeBuilder.shapeless(ModItems.ITEM_METAL_INGOT.get("brass"))
                .requires(ModItems.ITEM_METAL_INGOT.get("zinc"))
                .requires(ModItems.ITEM_METAL_INGOT.get("zinc"))
                .requires(Items.COPPER_INGOT)
                .requires(Items.COPPER_INGOT)
                .unlockedBy("has_item",has(ModItems.ITEM_METAL_INGOT.get("brass")))
                .save(recipesConsumer,new ResourceLocation(MetallicsArts.MOD_ID+"_brass_alloy_craft"));

        ShapelessRecipeBuilder.shapeless(ModItems.ITEM_METAL_INGOT.get("electrum"))
                .requires(ModItems.ITEM_METAL_INGOT.get("silver"))
                .requires(ModItems.ITEM_METAL_INGOT.get("silver"))
                .requires(ModItems.ITEM_METAL_INGOT.get("silver"))
                .requires(Items.GOLD_INGOT)
                .unlockedBy("has_item",has(ModItems.ITEM_METAL_INGOT.get("electrum")))
                .save(recipesConsumer,new ResourceLocation(MetallicsArts.MOD_ID+"_electrum_alloy_craft"));

        ShapelessRecipeBuilder.shapeless(ModItems.ITEM_METAL_INGOT.get("bendalloy"))
                .requires(ModItems.ITEM_METAL_INGOT.get("lead"))
                .requires(ModItems.ITEM_METAL_INGOT.get("lead"))
                .requires(ModItems.ITEM_METAL_INGOT.get("cadmium"))
                .requires(ModItems.ITEM_METAL_INGOT.get("cadmium"))
                .unlockedBy("has_item",has(ModItems.ITEM_METAL_INGOT.get("bendalloy")))
                .save(recipesConsumer,new ResourceLocation(MetallicsArts.MOD_ID+"_bendalloy_alloy_craft"));

        ShapelessRecipeBuilder.shapeless(ModItems.ITEM_METAL_INGOT.get("duralumin"))
                .requires(ModItems.ITEM_METAL_INGOT.get("aluminum"))
                .requires(ModItems.ITEM_METAL_INGOT.get("aluminum"))
                .requires(ModItems.ITEM_METAL_INGOT.get("aluminum"))
                .requires(Items.COPPER_INGOT)
                .unlockedBy("has_item",has(ModItems.ITEM_METAL_INGOT.get("duralumin")))
                .save(recipesConsumer,new ResourceLocation(MetallicsArts.MOD_ID+"_duralumin_alloy_craft"));

        ShapelessRecipeBuilder.shapeless(ModItems.ITEM_METAL_INGOT.get("nicrosil"))
                .requires(ModItems.ITEM_METAL_INGOT.get("nickel"))
                .requires(ModItems.ITEM_METAL_INGOT.get("nickel"))
                .requires(ModItems.ITEM_METAL_INGOT.get("nickel"))
                .requires(ModItems.ITEM_METAL_INGOT.get("chromium"))
                .unlockedBy("has_item",has(ModItems.ITEM_METAL_INGOT.get("nicrosil")))
                .save(recipesConsumer,new ResourceLocation(MetallicsArts.MOD_ID+"_nicrosil_alloy_craft"));

        ShapelessRecipeBuilder.shapeless(ModItems.ITEM_GEMS_BASE.get("malatium"))
                .requires(ModItems.ITEM_GEMS_BASE.get("atium"))
                .requires(ModItems.ITEM_GEMS_BASE.get("atium"))
                .requires(Items.GOLD_INGOT)
                .requires(Items.GOLD_INGOT)
                .unlockedBy("has_item",has(ModItems.ITEM_GEMS_BASE.get("malatium")))
                .save(recipesConsumer,new ResourceLocation(MetallicsArts.MOD_ID+"_malatium_alloy_craft"));

        SpecialRecipeBuilder.special(ModRecipeTypes.LARGE_VIAL_ITEM_RECIPE_SERIALIZER.get()).save(recipesConsumer, MetallicsArts.MOD_ID+":large_vial_filling_recipe");
        SpecialRecipeBuilder.special(ModRecipeTypes.SMALL_VIAL_ITEM_RECIPE_SERIALIZER.get()).save(recipesConsumer, MetallicsArts.MOD_ID+":small_vial_filling_recipe");

        ModItems.ITEM_ICONS_ALLOMANCY.forEach((key, value) -> {
            ShapedRecipeBuilder.shaped(value)
                    .define('#',Items.GLASS_PANE)
                    .define('x',ModItems.ITEM_METAL_INGOT.get(key))
                    .pattern(" # ")
                    .pattern("#x#")
                    .pattern(" # ")
                    .unlockedBy("has_item", has(value))
                    .save(recipesConsumer,new ResourceLocation(key+"_allomantic_icon"));
        });


        ModItems.ITEM_ICONS_FERUCHEMIC.forEach((key, value) -> {
            ShapedRecipeBuilder.shaped(value)
                    .define('#',Items.GLASS_PANE)
                    .define('x',ModItems.ITEM_METAL_INGOT.get(key))
                    .pattern("# #")
                    .pattern(" x ")
                    .pattern("# #")
                    .unlockedBy("has_item", has(value))
                    .save(recipesConsumer,new ResourceLocation(key+"_feruchemic_icon"));
        });

    }
}






