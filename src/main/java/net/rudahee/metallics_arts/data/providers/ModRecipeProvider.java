package net.rudahee.metallics_arts.data.providers;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.common.Tags;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.data.enums.implementations.GemsEnum;
import net.rudahee.metallics_arts.data.enums.implementations.MetalEnum;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.enums.implementations.custom_items.ArmorPiecesEnum;
import net.rudahee.metallics_arts.data.enums.implementations.custom_items.MetalMindEnum;
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

        ModItemsRegister.ITEM_METAL_INGOT.forEach((name, item) -> ShapelessRecipeBuilder.shapeless(item.asItem(), 9)
                .requires(ModTags.METAL_BLOCKS.get(name))
                .unlockedBy("has_item", has(item))
                .save(recipesConsumer, new ResourceLocation(name + "_block_to_" + name + "_ingot")));

        ModBlocksRegister.BLOCK_METAL_BLOCKS.forEach((name, block) -> ShapedRecipeBuilder.shaped(block)
                .define('#', ModTags.INGOTS.get(name))
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .unlockedBy("has_block", has(block))
                .save(recipesConsumer, new ResourceLocation(name + "_ingot_to_" + name + "_block")));

        ModBlocksRegister.RAW_METAL_BLOCKS.forEach((name, block) -> ShapedRecipeBuilder.shaped(block)
                .define('#', ModTags.RAWS.get(name))
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .unlockedBy("has_block", has(block))
                .save(recipesConsumer, new ResourceLocation(name + "_raw_to_" + name + "_block")));

        ModItemsRegister.ITEM_RAW_METAL.forEach((name, item) -> ShapelessRecipeBuilder.shapeless(item.asItem(), 9)
                .requires(ModTags.RAW_BLOCKS.get(name))
                .unlockedBy("has_item", has(item))
                .save(recipesConsumer, new ResourceLocation(name + "_raw_block_to_" + name + "_raw")));



        ModItemsRegister.ITEM_GEMS_BASE.forEach((name, item) -> ShapelessRecipeBuilder.shapeless(item.asItem(), 9)
                .requires(ModTags.METAL_BLOCKS.get(name))
                .unlockedBy("has_item", has(item))
                .save(recipesConsumer, new ResourceLocation(name + "_block" + "_to_" + name + "_gem")));

        ModBlocksRegister.BLOCK_GEMS_BLOCKS.forEach((name, block) -> ShapedRecipeBuilder.shaped(block)
                .define('#', ModTags.GEMS.get(name))
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .unlockedBy("has_block", has(block))
                .save(recipesConsumer, new ResourceLocation(name + "_gem_to_" + name + "_block")));

        ModItemsRegister.ITEM_METAL_INGOT.forEach((name, item) -> ShapelessRecipeBuilder.shapeless(ModItemsRegister.ITEM_METAL_NUGGET.get(name), 9)
                .requires(ModTags.INGOTS.get(name))
                .unlockedBy("has_block", has(item))
                .save(recipesConsumer, new ResourceLocation(name + "_ingot_to_" + name + "_nugget")));

        ShapelessRecipeBuilder.shapeless(ModItemsRegister.ITEM_METAL_NUGGET.get("copper"), 9)
                .requires(Tags.Items.INGOTS_COPPER)
                .unlockedBy("has_block", has(ModTags.NUGGETS.get("copper")))
                .save(recipesConsumer, new ResourceLocation("copper_ingot_to_copper_nugget"));


        ModItemsRegister.ITEM_METAL_NUGGET.forEach((name, item) -> {
            ShapedRecipeBuilder.shaped(name.equals("copper") ? Items.COPPER_INGOT : ModItemsRegister.ITEM_METAL_INGOT.get(name))
                    .define('#', ModTags.NUGGETS.get(name))
                    .pattern("###")
                    .pattern("###")
                    .pattern("###")
                    .unlockedBy("has_block", has(item))
                    .save(recipesConsumer, new ResourceLocation(name + "_nugget_to_" + name + "_ingot"));

        });

        ModItemsRegister.ITEM_GEMS_BASE.forEach((name, item) -> ShapelessRecipeBuilder.shapeless(ModItemsRegister.ITEM_GEMS_NUGGET.get(name), 9)
                .requires(ModTags.NUGGETS.get(name))
                .unlockedBy("has_block", has(item))
                .save(recipesConsumer, new ResourceLocation(name + "_gem_to_" + name + "_nugget")));

        ModItemsRegister.ITEM_GEMS_NUGGET.forEach((name, item) -> ShapedRecipeBuilder.shaped(ModItemsRegister.ITEM_GEMS_BASE.get(name))
                .define('#', ModTags.NUGGETS.get(name))
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .unlockedBy("has_block", has(item))
                .save(recipesConsumer, new ResourceLocation(name + "_nugget_to_" + name + "_gem")));

        Arrays.asList(MetalEnum.values()).forEach(metal -> {
            if (!metal.isAlloy()){
                SimpleCookingRecipeBuilder.cooking(Ingredient.of(ModItemsRegister.ITEM_RAW_METAL.get(metal.getMetalNameLower())), ModItemsRegister.ITEM_METAL_INGOT.get(metal.getMetalNameLower()), 0.5f, 250, RecipeSerializer.SMELTING_RECIPE)
                        .unlockedBy("has_block", has(ModItemsRegister.ITEM_RAW_METAL.get(metal.getMetalNameLower())))
                        .save(recipesConsumer, new ResourceLocation(metal.getMetalNameLower() + "_raw_to_" + metal.getMetalNameLower() + "_ingot_furnace"));
            }
        });

        Arrays.asList(MetalEnum.values()).forEach(metal -> {
            if (!metal.isAlloy()){
                SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModItemsRegister.ITEM_RAW_METAL.get(metal.getMetalNameLower())), ModItemsRegister.ITEM_METAL_INGOT.get(metal.getMetalNameLower()), 0.8f, 100)
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
                .define('O', ModItemsRegister.CORE_OBSIDIAN.get())
                .define('#', Items.OBSIDIAN)
                .define('x', ModTags.INGOTS.get(MetalEnum.ALUMINUM.getMetalNameLower()))
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
                .define('#', ModTags.INGOTS.get("silver"))
                .define('x', Items.STICK)
                .pattern("  #")
                .pattern(" # ")
                .pattern("x  ")
                .unlockedBy("has_item",has(ModItemsRegister.SILVER_KNIFE.get()))
                .save(recipesConsumer,new ResourceLocation(MetallicsArts.MOD_ID + "_silver_knife"));

        ShapedRecipeBuilder.shaped(ModItemsRegister.KOLOSS_BLADE.get())
                .define('#', Items.COBBLESTONE)
                .define('x', Items.STICK)
                .pattern("## ")
                .pattern("###")
                .pattern(" x ")
                .unlockedBy("has_item",has(ModItemsRegister.KOLOSS_BLADE.get()))
                .save(recipesConsumer,new ResourceLocation(MetallicsArts.MOD_ID + "_koloss_blade"));


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
                .requires(ModTags.INGOTS.get(MetalEnum.LEAD.getMetalNameLower()))
                .requires(ModTags.INGOTS.get(MetalEnum.TIN.getMetalNameLower()))
                .requires(ModTags.INGOTS.get(MetalEnum.TIN.getMetalNameLower()))
                .requires(ModTags.INGOTS.get(MetalEnum.TIN.getMetalNameLower()))
                .unlockedBy("has_item",has(ModItemsRegister.ITEM_METAL_INGOT.get("pewter")))
                .save(recipesConsumer,new ResourceLocation(MetallicsArts.MOD_ID+"_pewter_alloy_craft"));

        ShapelessRecipeBuilder.shapeless(ModItemsRegister.ITEM_METAL_INGOT.get("bronze"),2)
                .requires(ModTags.INGOTS.get(MetalEnum.TIN.getMetalNameLower()))
                .requires(Items.COPPER_INGOT)
                .requires(Items.COPPER_INGOT)
                .requires(Items.COPPER_INGOT)
                .unlockedBy("has_item",has(ModItemsRegister.ITEM_METAL_INGOT.get("bronze")))
                .save(recipesConsumer,new ResourceLocation(MetallicsArts.MOD_ID+"_bronze_alloy_craft"));

        ShapelessRecipeBuilder.shapeless(ModItemsRegister.ITEM_METAL_INGOT.get("brass"),2)
                .requires(ModTags.INGOTS.get(MetalEnum.ZINC.getMetalNameLower()))
                .requires(ModTags.INGOTS.get(MetalEnum.ZINC.getMetalNameLower()))
                .requires(Items.COPPER_INGOT)
                .requires(Items.COPPER_INGOT)
                .unlockedBy("has_item",has(ModItemsRegister.ITEM_METAL_INGOT.get("brass")))
                .save(recipesConsumer,new ResourceLocation(MetallicsArts.MOD_ID+"_brass_alloy_craft"));

        ShapelessRecipeBuilder.shapeless(ModItemsRegister.ITEM_METAL_INGOT.get("electrum"),2)
                .requires(ModTags.INGOTS.get(MetalEnum.SILVER.getMetalNameLower()))
                .requires(ModTags.INGOTS.get(MetalEnum.SILVER.getMetalNameLower()))
                .requires(ModTags.INGOTS.get(MetalEnum.SILVER.getMetalNameLower()))
                .requires(Items.GOLD_INGOT)
                .unlockedBy("has_item",has(ModItemsRegister.ITEM_METAL_INGOT.get("electrum")))
                .save(recipesConsumer,new ResourceLocation(MetallicsArts.MOD_ID+"_electrum_alloy_craft"));

        ShapelessRecipeBuilder.shapeless(ModItemsRegister.ITEM_METAL_INGOT.get("bendalloy"),2)
                .requires(ModTags.INGOTS.get(MetalEnum.LEAD.getMetalNameLower()))
                .requires(ModTags.INGOTS.get(MetalEnum.LEAD.getMetalNameLower()))
                .requires(ModTags.INGOTS.get(MetalEnum.CADMIUM.getMetalNameLower()))
                .requires(ModTags.INGOTS.get(MetalEnum.CADMIUM.getMetalNameLower()))
                .unlockedBy("has_item",has(ModItemsRegister.ITEM_METAL_INGOT.get("bendalloy")))
                .save(recipesConsumer,new ResourceLocation(MetallicsArts.MOD_ID+"_bendalloy_alloy_craft"));

        ShapelessRecipeBuilder.shapeless(ModItemsRegister.ITEM_METAL_INGOT.get("duralumin"),2)
                .requires(ModTags.INGOTS.get(MetalEnum.ALUMINUM.getMetalNameLower()))
                .requires(ModTags.INGOTS.get(MetalEnum.ALUMINUM.getMetalNameLower()))
                .requires(ModTags.INGOTS.get(MetalEnum.ALUMINUM.getMetalNameLower()))
                .requires(Items.COPPER_INGOT)
                .unlockedBy("has_item",has(ModItemsRegister.ITEM_METAL_INGOT.get("duralumin")))
                .save(recipesConsumer,new ResourceLocation(MetallicsArts.MOD_ID+"_duralumin_alloy_craft"));

        ShapelessRecipeBuilder.shapeless(ModItemsRegister.ITEM_METAL_INGOT.get("nicrosil"),2)
                .requires(ModTags.INGOTS.get(MetalEnum.NICKEL.getMetalNameLower()))
                .requires(ModTags.INGOTS.get(MetalEnum.NICKEL.getMetalNameLower()))
                .requires(ModTags.INGOTS.get(MetalEnum.NICKEL.getMetalNameLower()))
                .requires(ModTags.INGOTS.get(MetalEnum.CHROMIUM.getMetalNameLower()))
                .unlockedBy("has_item",has(ModItemsRegister.ITEM_METAL_INGOT.get("nicrosil")))
                .save(recipesConsumer,new ResourceLocation(MetallicsArts.MOD_ID+"_nicrosil_alloy_craft"));

        ShapelessRecipeBuilder.shapeless(ModItemsRegister.ITEM_GEMS_BASE.get("malatium"),2)
                .requires(ModTags.GEMS.get(GemsEnum.ATIUM.getGemNameLower()))
                .requires(ModTags.GEMS.get(GemsEnum.ATIUM.getGemNameLower()))
                .requires(Items.GOLD_INGOT)
                .requires(Items.GOLD_INGOT)
                .unlockedBy("has_item",has(ModItemsRegister.ITEM_GEMS_BASE.get("malatium")))
                .save(recipesConsumer,new ResourceLocation(MetallicsArts.MOD_ID+"_malatium_alloy_craft"));

        SpecialRecipeBuilder.special(ModRecipeTypesRegister.LARGE_VIAL_ITEM_RECIPE_SERIALIZER.get()).save(recipesConsumer, MetallicsArts.MOD_ID+":large_vial_filling_recipe");
        SpecialRecipeBuilder.special(ModRecipeTypesRegister.SMALL_VIAL_ITEM_RECIPE_SERIALIZER.get()).save(recipesConsumer, MetallicsArts.MOD_ID+":small_vial_filling_recipe");

        ModItemsRegister.ITEM_ICONS_ALLOMANCY.forEach((key, value) -> ShapedRecipeBuilder.shaped(value)
                .define('#',Items.GLASS_PANE)
                .define('x',(key.contains("gold")) ? Tags.Items.INGOTS_GOLD : (key.contains("copper")) ? Tags.Items.INGOTS_COPPER : (key.contains("iron")) ? Tags.Items.INGOTS_IRON: ModTags.INGOTS.get(key))
                .pattern(" # ")
                .pattern("#x#")
                .pattern(" # ")
                .unlockedBy("has_item", has(value))
                .save(recipesConsumer,new ResourceLocation(key + "_allomantic_icon")));

        ModItemsRegister.ITEM_ICONS_FERUCHEMIC.forEach((key, value) -> ShapedRecipeBuilder.shaped(value)
                .define('#',Items.GLASS_PANE)
                .define('x',(key.contains("gold")) ? Tags.Items.INGOTS_GOLD : (key.contains("copper")) ? Tags.Items.INGOTS_COPPER : (key.contains("iron")) ? Tags.Items.INGOTS_IRON: ModTags.INGOTS.get(key))
                .pattern("# #")
                .pattern(" x ")
                .pattern("# #")
                .unlockedBy("has_item", has(value))
                .save(recipesConsumer,new ResourceLocation(key+"_feruchemic_icon")));

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

        ShapedRecipeBuilder.shaped(ModItemsRegister.CORE_OBSIDIAN.get())
                .define('#', Items.OBSIDIAN)
                .define('*', Items.NETHERITE_SCRAP)
                .define('X', Items.GOLD_INGOT)
                .pattern("#X#")
                .pattern("X*X")
                .pattern("#X#")
                .unlockedBy("has_block", has(ModItemsRegister.CORE_OBSIDIAN.get()))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_core_obsidian"));

        ShapedRecipeBuilder.shaped(ModItemsRegister.CORE_ALUMINUM.get())
                .define('#', ModBlocksRegister.BLOCK_METAL_BLOCKS.get("aluminum"))
                .define('*', Items.END_CRYSTAL)
                .define('X', Items.NETHERITE_SCRAP)
                .pattern("#X#")
                .pattern("X*X")
                .pattern("#X#")
                .unlockedBy("has_block", has(ModItemsRegister.CORE_ALUMINUM.get()))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_core_aluminum"));

        ShapedRecipeBuilder.shaped(ModItemsRegister.CORE_STEEL.get())
                .define('#', ModBlocksRegister.BLOCK_METAL_BLOCKS.get("steel"))
                .define('*', Items.END_CRYSTAL)
                .define('X', Items.NETHERITE_SCRAP)
                .pattern("#X#")
                .pattern("X*X")
                .pattern("#X#")
                .unlockedBy("has_block", has(ModItemsRegister.CORE_STEEL.get()))
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_core_steel"));

        UpgradeRecipeBuilder.smithing(
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
                .save(recipesConsumer, new ResourceLocation(MetallicsArts.MOD_ID + "_armor_aluminum_boots"));

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






