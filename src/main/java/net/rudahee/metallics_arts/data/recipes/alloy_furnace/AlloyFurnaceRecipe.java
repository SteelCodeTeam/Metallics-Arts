package net.rudahee.metallics_arts.data.recipes.alloy_furnace;

public class AlloyFurnaceRecipe implements IAlloyFurnaceRecipe {
/*
    private final ResourceLocation id;
    private final ItemStack output;
    private final NonNullList<Ingredient> recipeItems;

    public AlloyFurnaceRecipe(ResourceLocation id, ItemStack output, NonNullList<Ingredient> recipeItems) {
        this.id = id;
        this.output = output;
        this.recipeItems = recipeItems;
    }


    @Override
    public NonNullList<Ingredient> getIngredients() {
        return recipeItems;
    }


    public ItemStack getIcon() {
        return new ItemStack(ModBlock.ALLOY_FURNACE_BLOCK.get());
    }

    @Override
    public boolean matches(CraftingContainer inventory, Level world) {
        List<Ingredient> testTarget = recipeItems;

        if (testTarget.get(0).test(inventory.getItem(0))
                && testTarget.get(1).test(inventory.getItem(1))
                && testTarget.get(2).test(inventory.getItem(2))
                && testTarget.get(3).test(inventory.getItem(3))) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public ItemStack assemble(CraftingContainer inventory) {
        return output.copy();
    }

    @Override
    public ItemStack getResultItem() {
        return output.copy();
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipeTypes.ALLOY_FURNACE_SERIALIZER.get();
    }


    public static class AlloyFurnaceRecipeType implements RecipeType<AlloyFurnaceRecipe> {
        @Override
        public String toString() {
            return AlloyFurnaceRecipe.TYPE_ID.toString();
        }
    }

    public static class Serializer extends ForgeRegistryEntry<RecipeSerializer<?>>
            implements RecipeSerializer<AlloyFurnaceRecipe> {


        @Override
        public AlloyFurnaceRecipe fromJson(ResourceLocation recipeId, JsonObject json) {

            ItemStack output = ShapedRecipe.itemFromJson(JsonUtils.getAsJsonObject(json, "output"));

            JsonArray ingredients = JSONUtils.getAsJsonArray(json, "ingredients");
            NonNullList<Ingredient> inputs = NonNullList.withSize(4, Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
            }

            return new AlloyFurnaceRecipe(recipeId, output, inputs);
        }


        @Nullable
        @Override
        public AlloyFurnaceRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(buffer.readInt(), Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromNetwork(buffer));
            }

            ItemStack output = buffer.readItem();
            return new AlloyFurnaceRecipe(recipeId, output, inputs);
            //return new AlloyFurnaceRecipe(recipeId,new ItemStack(Items.ACACIA_WOOD),NonNullList.withSize(4, Ingredient.of(new ItemStack(Items.ACACIA_WOOD),new ItemStack(Items.ACACIA_WOOD),new ItemStack(Items.ACACIA_WOOD),new ItemStack(Items.ACACIA_WOOD))));
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, AlloyFurnaceRecipe recipe) {
            buffer.writeInt(recipe.getIngredients().size());

            for (Ingredient ing : recipe.getIngredients()) {
                ing.toNetwork(buffer);
            }
            buffer.writeItemStack(recipe.getResultItem(), false);
        }
    }*/
}
