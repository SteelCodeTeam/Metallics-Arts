package net.rudahee.metallics_arts.data.client;

import net.minecraft.data.*;
import net.minecraft.item.crafting.FurnaceRecipe;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.util.datafix.fixes.FurnaceRecipes;
import net.rudahee.metallics_arts.setup.ModBlock;
import net.rudahee.metallics_arts.setup.ModItems;

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
                    .save(recipesConsumer);
        });

        ModBlock.BLOCK_METAL_BLOCKS.forEach((name, block) -> {
            ShapedRecipeBuilder.shaped(block)
                    .define('#' ,ModItems.ITEM_METAL_INGOT.get(name))
                    .pattern("###")
                    .pattern("###")
                    .pattern("###")
                    .unlockedBy("has_block", has(block))
                    .save(recipesConsumer);

        });

        ModItems.ITEM_GEMS_BASE.forEach((name, item) -> {
            ShapelessRecipeBuilder.shapeless(item.getItem(), 9)
                    .requires(ModBlock.BLOCK_GEMS_BLOCKS.get(name))
                    .unlockedBy("has_item", has(item))
                    .save(recipesConsumer);
        });


        ModBlock.BLOCK_GEMS_BLOCKS.forEach((name, block) -> {
            ShapedRecipeBuilder.shaped(block)
                    .define('#' ,ModItems.ITEM_GEMS_BASE.get(name))
                    .pattern("###")
                    .pattern("###")
                    .pattern("###")
                    .unlockedBy("has_block", has(block))
                    .save(recipesConsumer);
        });

    }


}
