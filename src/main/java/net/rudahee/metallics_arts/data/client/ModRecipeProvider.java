package net.rudahee.metallics_arts.data.client;

import net.minecraft.data.*;
import net.minecraft.item.PotionItem;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.Potion;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.brewing.BrewingRecipe;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.rudahee.metallics_arts.setup.ModBlock;
import net.rudahee.metallics_arts.setup.ModItems;
import net.rudahee.metallics_arts.setup.vial.Vial;

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

       //BrewingRecipeRegistry.addRecipe(Ingredient.of(() -> ModItems.VIAL.get() ),Ingredient.of(ModItems.ITEM_METAL_NUGGET.get("steel").asItem()),ModItems.VIAL.get().getDefaultInstance());

    }


}
