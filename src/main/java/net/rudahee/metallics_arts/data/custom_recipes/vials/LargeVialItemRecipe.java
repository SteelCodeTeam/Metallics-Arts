package net.rudahee.metallics_arts.data.custom_recipes.vials;

import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
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
import java.util.HashMap;

/**
 * Class that control the large vial recipe. It's a custom recipe, so extends CustomRecipe.
 *
 * @author SteelCode Team
 * @since 1.5.1
 *
 * @see CustomRecipe
 * @see ItemStack
 * @see Ingredient
 * @see RecipeSerializer
 */
public class LargeVialItemRecipe extends CustomRecipe {

    private static final Ingredient INGREDIENT_VIAL = Ingredient.of(ModItemsRegister.LARGE_VIAL.get());

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
    public LargeVialItemRecipe(ResourceLocation location) {
        super(location);
    }

    private final HashMap<String, Integer> required = new HashMap<>();
    private final HashMap<String, Integer> available = new HashMap<>();
    private final HashMap<String, Integer> inVial = new HashMap<>();
    private boolean cancel;

    /**
     Checks if the given crafting inventory matches the required ingredients for a specific crafting operation.
    <p>
     - Check for the presence of a vial ingredient, determines the quantities of metals in the vial,
     and calculates the required quantities for crafting based on the vial's contents.
     <p>
     - Check for duplicate items in different slots.

     @param inventory The crafting container to be checked.
     @param level The level associated with the crafting operation.

     @return {@code true} if the inventory contains the required ingredients and meets all conditions, {@code false} otherwise.
     */
    @Override
    public boolean matches(@NotNull CraftingContainer inventory, @NotNull Level level) {
        boolean[] ingredients = {false, false};
        ItemStack actualIngredient;
        int posVial = 0;
        this.cancel = false;

        for (MetalTagEnum metal : MetalTagEnum.values()) {
            inVial.put(metal.getNameLower(), 0);
            required.put(metal.getNameLower(), 0);
            available.put(metal.getNameLower(), 0);
        }

        if (!containsInvalidElements(inventory, new ArrayList<>() {{addAll(INGREDIENT_MAP.values()); add(INGREDIENT_VIAL); add(Ingredient.of(ItemStack.EMPTY)); }})) {
            return false;
        }
        for(int i = 0; i < inventory.getContainerSize(); i++) {
            actualIngredient = inventory.getItem(i);
            if (!actualIngredient.isEmpty()) {
                if (INGREDIENT_VIAL.test(actualIngredient)) {
                    if (ingredients[0]) {
                        return false;
                    } else {
                        posVial = i;
                        ingredients[0] = true;
                    }
                    if (actualIngredient.hasTag()) {
                        for (MetalTagEnum metal : MetalTagEnum.values()) {
                            if (actualIngredient.getTag().contains(metal.getNameLower())) {
                                inVial.put(metal.getNameLower(), actualIngredient.getTag().getInt(metal.getNameLower()));
                                required.put(metal.getNameLower(), 10 - inVial.get(metal.getNameLower()));
                            } else {
                                inVial.put(metal.getNameLower(), 0);
                                required.put(metal.getNameLower(), 10);
                            }
                        }
                    } else {
                        for (MetalTagEnum metal : MetalTagEnum.values()) {
                            inVial.put(metal.getNameLower(), 0);
                            required.put(metal.getNameLower(), 10);
                        }
                    }
                }
            }
        }

        for(int i = 0; i < inventory.getContainerSize(); i++) {
            actualIngredient = inventory.getItem(i);
            if (!actualIngredient.isEmpty()) {
                if (ingredients[0] && i != posVial) {
                    ItemStack auxIngredient = actualIngredient;
                    int finalI = i;
                    INGREDIENT_MAP.forEach((name, ing) -> {
                        if (ing.test(auxIngredient)) {
                            if (required.get(name) > 0) {
                                available.put(name, auxIngredient.getCount());
                                ingredients[1] = true;
                            } else { //If the vial contains the maximum capacity of this metal, cancel the crafting
                                this.cancel = true;
                            }
                            if (duplicateIngredients(inventory, finalI, ing)) {
                                this.cancel = true;
                            }
                        }
                    });
                }
            }
        }

        return ingredients[0] && ingredients[1] && !this.cancel;
    }

    /**
     Checks if the given crafting container contains duplicate ingredients.

     @param inventory The crafting container to be checked.
     @param slot The index of the current slot being compared (to exclude it from the check).
     @param ingredient The ingredient to be checked for duplicates.

     @return {@code true} if a duplicate ingredient is found, {@code false} otherwise.
     */
    private boolean duplicateIngredients(CraftingContainer inventory, int slot, Ingredient ingredient) {
        for (int i = 0; i < inventory.getContainerSize(); i++) {
            if (i != slot) {
                ItemStack actual = inventory.getItem(i);
                if (ingredient.test(actual)) {
                    return true;
                }
            }
        }
        return false;
    }
    /**
     Checks if the given crafting container contains any invalid elements.

     @param inventory The crafting container to be checked.
     @param list The list of ingredients used for validation.

     @return {@code true} if the crafting container contains only valid elements, {@code false} otherwise.
     */
    private boolean containsInvalidElements(CraftingContainer inventory, ArrayList<Ingredient> list) {
        for (int i = 0; i < inventory.getContainerSize(); i++) {
            ItemStack itemStack = inventory.getItem(i);
            if (list.stream().noneMatch(e -> e.test(itemStack))) {
                return false;
            }
        }
        return true;
    }

    /**

     Retrieves the remaining items after a crafting operation.

     @param inventory The crafting container used in the crafting operation.

     @return A list of ItemStack objects representing the remaining items.
     */
    @Override
    public NonNullList<ItemStack> getRemainingItems(CraftingContainer inventory) {
        NonNullList<ItemStack> nonnulllist = NonNullList.withSize(inventory.getContainerSize(), ItemStack.EMPTY);
        for(int i = 0; i < nonnulllist.size(); ++i) {
            ItemStack actualIngredient;
            actualIngredient = inventory.getItem(i);
            if (!actualIngredient.isEmpty()) {
                if (!INGREDIENT_VIAL.test(actualIngredient)) {
                    int finalI = i;
                    INGREDIENT_MAP.forEach((name, ing) -> {
                        if (ing.test(actualIngredient)) {
                            inventory.removeItem(finalI, required.get(name) - 1);

                        }
                    });
                }
            }
        }
        return super.getRemainingItems(inventory);
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

        ItemStack finalResult = new ItemStack(ModItemsRegister.LARGE_VIAL.get(), 1);
        CompoundTag compoundNBT = new CompoundTag();
        for (MetalTagEnum metal: MetalTagEnum.values()) {
            if (available.get(metal.getNameLower()) >= required.get(metal.getNameLower())) {
                compoundNBT.putInt(metal.getNameLower(), 10); //si tiene la cantidad justa o superior del metal
            } else {
                compoundNBT.putInt(metal.getNameLower(), inVial.get(metal.getNameLower()) + available.get(metal.getNameLower()));
            }
        }
        compoundNBT.putFloat("CustomModelData", 1);
        finalResult.setTag(compoundNBT);
        return finalResult.copy();
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
     * This method its getter for serializer. So only return a LargeVialItemRecipeSerializer.
     *
     * @return RecipeSerializer
     */
    @Nonnull
    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipeTypesRegister.LARGE_VIAL_ITEM_RECIPE_SERIALIZER.get();
    }

    /**
     * Static class that controls Custom vial recipe serializer. Extend SimpleRecipeSerializer<LargeVialItemRecipe>
     *
     * @author SteelCode Team
     * @since 1.5.1
     *
     * @see SimpleRecipeSerializer
     * @see LargeVialItemRecipe
     */
    public static class Serializer extends SimpleRecipeSerializer<LargeVialItemRecipe> {

        /**
         * Constructor of the class. The only thing that constructor does its pass the Recipe class to superclass.
         */
        public Serializer() {
            super(LargeVialItemRecipe::new);
        }

    }

}
