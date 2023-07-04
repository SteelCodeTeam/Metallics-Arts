package net.rudahee.metallics_arts.data.custom_recipes.vials;


import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleRecipeSerializer;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.Tags;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.setup.registries.ModItemsRegister;
import net.rudahee.metallics_arts.setup.registries.ModRecipeTypesRegister;
import net.rudahee.metallics_arts.setup.registries.items.ModTags;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Class that control the small vial recipe. It's a custom recipe, so extends CustomRecipe.
 *
 * @author SteelCode Team
 * @since 1.5.1
 *
 * @see CustomRecipe
 * @see ItemStack
 * @see Ingredient
 * @see RecipeSerializer
 */
public class SmallVialItemRecipe extends CustomRecipe {

    private ItemStack finalResult = ItemStack.EMPTY;
    private static final Ingredient INGREDIENT_VIAL = Ingredient.of(ModItemsRegister.SMALL_VIAL.get());

    private static final HashMap<String ,Ingredient> INGREDIENT_MAP = new HashMap<>() {{

        for (MetalTagEnum metal : MetalTagEnum.values()) {
            if (metal.getNameLower().equals("iron")) {
                put(metal.getNameLower(), Ingredient.of(Tags.Items.NUGGETS_IRON));
            } else if (metal.getNameLower().equals("gold")) {
                put(metal.getNameLower(), Ingredient.of(Tags.Items.NUGGETS_GOLD));
            } else {
                put(metal.getNameLower(), Ingredient.of(ModTags.NUGGETS.get(metal.getMetalNameLower())));
            }
        }

    }};

    /**
     * Constructor that receive the path of json recipe.
     *
     * @param location of the path.
     */
    public SmallVialItemRecipe(ResourceLocation location) {
        super(location);
    }


    /**
     * Method in which the ingredients of the recipe are evaluated if they are correct and coincide with this one.
     * <p>
     * In this case, it is verified that the pips are correct, and that the quantity is correct.
     * If everything matches, it returns 'true' because the recipe exists and is correct.
     *
     * @param inventory the inventory in which the crafting is taking place.
     * @param level world in which crafting is taking place.
     *
     * @return boolean
     */
    @Override
    public boolean matches(@NotNull CraftingContainer inventory, @NotNull Level level) {
        boolean[] ingredients = {false, false};
        int maxQtyNuggets = 5;
        ItemStack actualIngredient;

        HashMap<String, Integer> metalsInVial = new HashMap<>();
        HashMap<String, Integer> cantStorage = new HashMap<>();
        HashMap<String, Boolean> addMetal= new HashMap<>();
        HashMap<String, Integer> maxValues= new HashMap<>();

        for (MetalTagEnum metal : MetalTagEnum.values()) {
            maxValues.put(metal.getNameLower(), metal.getMaxAllomanticTicksStorage());
            metalsInVial.put(metal.getNameLower(), 0);
            cantStorage.put(metal.getNameLower(),metal.getMaxAllomanticTicksStorage()/maxQtyNuggets);
            addMetal.put(metal.getNameLower(), false);
        }


        for(int i = 0; i < inventory.getContainerSize(); i++) {
            actualIngredient = inventory.getItem(i);
            if (!actualIngredient.isEmpty()) {
                if (INGREDIENT_VIAL.test(actualIngredient)) {
                    if (ingredients[0]) {
                        return false;
                    } else {
                        ingredients[0] = true;
                    }
                    if (actualIngredient.hasTag()){
                        for (MetalTagEnum metal : MetalTagEnum.values()) {
                            if (actualIngredient.getTag().contains(metal.getGemNameLower())){
                                metalsInVial.put(metal.getNameLower(), actualIngredient.getTag().getInt(metal.getNameLower()));
                            }
                        }
                    }
                }
                ItemStack auxIngredient = actualIngredient;
                INGREDIENT_MAP.forEach((name, ing) -> {
                    if (addMetal.get(name) || metalsInVial.get(name) >= maxValues.get(name)) {
                        return;
                    }
                    if (ing.test(auxIngredient)) {
                        addMetal.put(name, true);
                        ingredients[1] = true;
                    }
                });
            }
        }

        if (ingredients[0] && ingredients[1]){
            this.finalResult = new ItemStack(ModItemsRegister.SMALL_VIAL.get(),1);
            CompoundTag compoundNBT = new CompoundTag();
            for (MetalTagEnum metal : MetalTagEnum.values()){
                if (addMetal.get(metal.getNameLower())) {
                    compoundNBT.putInt(metal.getNameLower(),metalsInVial.get(metal.getNameLower()) + cantStorage.get(metal.getNameLower()));
                }else{
                    compoundNBT.putInt(metal.getNameLower(),metalsInVial.get(metal.getNameLower()));
                }
            }
            compoundNBT.putFloat("CustomModelData", 1);
            this.finalResult.setTag(compoundNBT);
            return true;
        }
        else {
            return false;
        }
    }


    /**
     * Method that return a copy of the final result item of matches method.
     *
     * @param inventory the inventory in which the crafting is taking place.
     *
     * @return ItemStack
     */
    @Override
    public @NotNull ItemStack assemble(@NotNull CraftingContainer inventory) {
        return this.finalResult.copy();
    }

    /**
     * This method evaluates if it is possible to craft the object in the different dimensions of the game.
     * <p>
     * Receive 2 parameters, but they don't use by nothing. The player always can craft vials, dimension no matters.
     *
     * @param num1 don't matter, because don't have any use.
     * @param num2 don't matter, because don't have any use.
     *
     * @return boolean (Always true)
     */
    @Override
    public boolean canCraftInDimensions(int num1, int num2) {
        return true;
    }

    /**
     * This method its getter for serializer. So only return a SmallVialItemRecipeSerializer.
     *
     * @return RecipeSerializer
     */
    @Nonnull
    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipeTypesRegister.SMALL_VIAL_ITEM_RECIPE_SERIALIZER.get();
    }

    /**
     * Static class that controls Custom vial recipe serializer. Extend SimpleRecipeSerializer<SmallVialItemRecipe>
     *
     * @author SteelCode Team
     * @since 1.5.1
     *
     * @see SimpleRecipeSerializer
     * @see SmallVialItemRecipe
     */
    public static class Serializer extends SimpleRecipeSerializer<SmallVialItemRecipe> {

        /**
         * Constructor of the class. The only thing that constructor does its pass the Recipe class to superclass.
         */
        public Serializer() {
            super(SmallVialItemRecipe::new);
        }

    }
}
