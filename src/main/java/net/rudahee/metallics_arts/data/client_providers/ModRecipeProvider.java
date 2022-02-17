package net.rudahee.metallics_arts.data.client_providers;

import net.minecraft.data.*;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.rudahee.metallics_arts.setup.enums.extras.MetalMindData;
import net.rudahee.metallics_arts.setup.enums.extras.MetalSpikesData;
import net.rudahee.metallics_arts.setup.registries.ModBlock;
import net.rudahee.metallics_arts.setup.registries.ModItems;

import java.util.Arrays;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void buildShapelessRecipes(Consumer<IFinishedRecipe> recipesConsumer) {
        ModItems.ITEM_METAL_INGOT.forEach((name, item) -> {
            ShapelessRecipeBuilder.shapeless(item.getItem(), 9)
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

        ModItems.ITEM_GEMS_BASE.forEach((name, item) -> {
            ShapelessRecipeBuilder.shapeless(item.getItem(), 9)
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

        ModItems.ITEM_METAL_NUGGET.forEach((name, item) -> {
            ShapedRecipeBuilder.shaped(ModItems.ITEM_METAL_INGOT.get(name))
                    .define('#', ModItems.ITEM_METAL_NUGGET.get(name))
                    .pattern("###")
                    .pattern("###")
                    .pattern("###")
                    .unlockedBy("has_block", has(item))
                    .save(recipesConsumer, new ResourceLocation(item.getDescriptionId() + "_to_" + ModItems.ITEM_METAL_INGOT.get(name).getDescriptionId()));
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

        ModBlock.BLOCK_METAL_ORES.forEach((name,block) -> {
            CookingRecipeBuilder.cooking(Ingredient.of(block),ModItems.ITEM_METAL_INGOT.get(name),0.5f,100, IRecipeSerializer.SMELTING_RECIPE)
                    .unlockedBy("has_block",has(block))
                    .save(recipesConsumer, new ResourceLocation(block.getDescriptionId() + "_to_"+ ModItems.ITEM_METAL_INGOT.get(name).getDescriptionId() + "_furnace"));
        });

        ModBlock.BLOCK_METAL_ORES.forEach((name,block) -> {
            CookingRecipeBuilder.blasting(Ingredient.of(block),ModItems.ITEM_METAL_INGOT.get(name),0.5f,100)
                    .unlockedBy("has_block",has(block))
                    .save(recipesConsumer, new ResourceLocation(block.getDescriptionId() + "_to_"+ ModItems.ITEM_METAL_INGOT.get(name).getDescriptionId() + "_blasting"));

        });

        Arrays.asList(MetalMindData.values()).forEach(object -> {
            Item item1,item2;

            //BLOCKS
            if (object.isGems()){
                item1 = ModBlock.BLOCK_GEMS_BLOCKS.get(object.getFirstMetal()).asItem();
                item2 = ModBlock.BLOCK_GEMS_BLOCKS.get(object.getSecondMetal()).asItem();
            }else if (object.isVanilla()){
                if (object.getSecondMetal()=="iron")
                    item1 = Items.IRON_BLOCK;
                else
                    item1 = Items.GOLD_BLOCK;
                item2 = ModBlock.BLOCK_METAL_BLOCKS.get(object.getFirstMetal()).asItem();
            }else{
               item1 = ModBlock.BLOCK_METAL_BLOCKS.get(object.getFirstMetal()).asItem();
               item2 = ModBlock.BLOCK_METAL_BLOCKS.get(object.getSecondMetal()).asItem();
            }
            //BANDS
            ShapedRecipeBuilder.shaped(object.getBand())
                    .define('#',item1)
                    .define('x',item2)
                    .pattern("xxx")
                    .pattern("# x")
                    .pattern("###")
                    .unlockedBy("has_item",has(object.getBand()))
                    .save(recipesConsumer,new ResourceLocation("alomantic_arts_band_"+object.getFirstMetal()+"_"+object.getSecondMetal()));

            ShapedRecipeBuilder.shaped(object.getBand())
                    .define('#',item2)
                    .define('x',item1)
                    .pattern("xxx")
                    .pattern("# x")
                    .pattern("###")
                    .unlockedBy("has_item",has(object.getBand()))
                    .save(recipesConsumer,new ResourceLocation("alomantic_arts_band_"+object.getFirstMetal()+"_"+object.getSecondMetal()+"2"));


            //INGOTS
            if (object.isGems()){
                item1 = ModItems.ITEM_GEMS_BASE.get(object.getFirstMetal()).asItem();
                item2 = ModItems.ITEM_GEMS_BASE.get(object.getSecondMetal()).asItem();
            }else if (object.isVanilla()){
                if (object.getSecondMetal()=="iron")
                    item1 = Items.IRON_INGOT;
                else
                    item1 = Items.GOLD_INGOT;
                item2 = ModItems.ITEM_METAL_INGOT.get(object.getFirstMetal()).asItem();
            }else{
                item1 = ModItems.ITEM_METAL_INGOT.get(object.getFirstMetal()).asItem();
                item2 = ModItems.ITEM_METAL_INGOT.get(object.getSecondMetal()).asItem();
            }

            //RINGS
            ShapedRecipeBuilder.shaped(object.getRing())
                    .define('#',item1)
                    .define('x',item2)
                    .pattern("xxx")
                    .pattern("# x")
                    .pattern("###")
                    .unlockedBy("has_item",has(object.getRing()))
                    .save(recipesConsumer,new ResourceLocation("alomantic_arts_ring_"+object.getFirstMetal()+"_"+object.getSecondMetal()));

            ShapedRecipeBuilder.shaped(object.getRing())
                    .define('#',item2)
                    .define('x',item1)
                    .pattern("xxx")
                    .pattern("# x")
                    .pattern("###")
                    .unlockedBy("has_item",has(object.getRing()))
                    .save(recipesConsumer,new ResourceLocation("alomantic_arts_ring_"+object.getFirstMetal()+"_"+object.getSecondMetal()+"2"));
        });

        //SPIKES
        Arrays.asList(MetalSpikesData.values()).forEach(object -> {
            Item head,body;
            if (object.isGems()){
                head = ModBlock.BLOCK_GEMS_BLOCKS.get(object.getName()).asItem();
                body = ModItems.ITEM_GEMS_BASE.get(object.getName()).asItem();
            }else if (object.isVanilla()){
                if (object.getName()=="iron"){
                    head = Items.IRON_BLOCK;
                    body = Items.IRON_INGOT;
                }
                else{
                    head = Items.GOLD_BLOCK;
                    body = Items.GOLD_INGOT;
                }
            }else{
                head = ModBlock.BLOCK_METAL_BLOCKS.get(object.getName()).asItem();
                body = ModItems.ITEM_METAL_INGOT.get(object.getName()).asItem();
            }
            ShapedRecipeBuilder.shaped(object.getSpike())
                    .define('#',head)
                    .define('x',body)
                    .pattern(" # ")
                    .pattern(" x ")
                    .pattern(" x ")
                    .unlockedBy("has_item",has(object.getSpike()))
                    .save(recipesConsumer,new ResourceLocation("alomantic_arts_spike"+object.getName()));
        });
    }


}
