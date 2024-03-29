package net.rudahee.metallics_arts.data.custom_recipes.tables;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.rudahee.metallics_arts.MetallicsArts;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CrucibleFurnaceRecipe implements Recipe<SimpleContainer> {
    private final ResourceLocation id;
    private final ItemStack output;
    private final NonNullList<Ingredient> recipeItems;


    public CrucibleFurnaceRecipe(ResourceLocation id, ItemStack output, NonNullList<Ingredient> recipeItems) {
        this.id = id;
        this.output = output;
        this.recipeItems = recipeItems;
    }


    @Override
    public boolean matches(@NotNull SimpleContainer container, Level level) {

        if (level.isClientSide()) {
            return false;
        }

        if (container.getItem(0).is(Items.LAVA_BUCKET)) {
            return true;
        }

        if  (recipeItems.get(0).test(container.getItem(1))
                && recipeItems.get(1).test(container.getItem(2))
                && recipeItems.get(2).test(container.getItem(3))
                && recipeItems.get(3).test(container.getItem(4))) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public ItemStack assemble(SimpleContainer container, RegistryAccess acess) {
        return output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }



    @Override
    public ItemStack getResultItem(RegistryAccess access) {
        return output.copy();
    }

    public ItemStack getResultItem() {
        return output.copy();
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }


    public static class Type implements RecipeType<CrucibleFurnaceRecipe> {
        private Type() {}

        public static final Type INSTANCE = new Type();

        public static final String ID = "crucible_furnace_recipe_type";
    }


    public static class Serializer implements RecipeSerializer<CrucibleFurnaceRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID = new ResourceLocation(MetallicsArts.MOD_ID, "crucible_furnace_recipe");


        @Override
        public @NotNull CrucibleFurnaceRecipe fromJson(@NotNull ResourceLocation loc, @NotNull JsonObject json) {
            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "output"));//
            JsonArray ingredients = GsonHelper.getAsJsonArray(json, "ingredients");
            NonNullList<Ingredient> inputs = NonNullList.withSize(4, Ingredient.EMPTY);
            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
            }
            return new CrucibleFurnaceRecipe(ID.withSuffix("_" + loc.getPath()), output, inputs);

        }

        @Override
        public @Nullable CrucibleFurnaceRecipe fromNetwork(ResourceLocation loc, FriendlyByteBuf buf) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(buf.readInt(), Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromNetwork(buf));
            }

            ItemStack output = buf.readItem();
            return new CrucibleFurnaceRecipe(ID.withSuffix("_" + loc.getPath()), output, inputs);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, CrucibleFurnaceRecipe recipe) {
            buf.writeInt(recipe.getIngredients().size());

            for (Ingredient ing : recipe.getIngredients()) {
                ing.toNetwork(buf);
            }

            buf.writeItemStack(recipe.getResultItem(), false);
        }
    }
}
