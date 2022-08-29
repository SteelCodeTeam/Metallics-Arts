package net.rudahee.metallics_arts.data.recipes.alloy_furnace;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistryEntry;
import net.rudahee.metallics_arts.setup.registries.ModBlock;
import net.rudahee.metallics_arts.setup.registries.ModRecipeTypes;

import javax.annotation.Nullable;
import java.util.List;



public class AlloyFurnaceRecipe implements IAlloyFurnaceRecipe {

    private final ResourceLocation id;
    private final ItemStack output;
    private final NonNullList<Ingredient> recipeItems;

    public AlloyFurnaceRecipe(ResourceLocation id, ItemStack output, NonNullList<Ingredient> recipeItems) {
        this.id = id;
        this.output = output;
        this.recipeItems = recipeItems;
    }

    @Override
    public boolean matches(IInventory inventory, World world) {
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
    public NonNullList<Ingredient> getIngredients() {
        return recipeItems;
    }

    @Override
    public ItemStack assemble(IInventory inventory) {
        return output.copy();
    }

    public ItemStack getIcon() {
        return new ItemStack(ModBlock.ALLOY_FURNACE_BLOCK.get());
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
    public IRecipeSerializer<?> getSerializer() {
        return ModRecipeTypes.ALLOY_FURNACE_SERIALIZER.get();
    }


    public static class AlloyFurnaceRecipeType implements IRecipeType<AlloyFurnaceRecipe> {
        @Override
        public String toString() {
            return AlloyFurnaceRecipe.TYPE_ID.toString();
        }
    }

    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>>
            implements IRecipeSerializer<AlloyFurnaceRecipe> {


        @Override
        public AlloyFurnaceRecipe fromJson(ResourceLocation recipeId, JsonObject json) {

            ItemStack output = ShapedRecipe.itemFromJson(JSONUtils.getAsJsonObject(json, "output"));

            JsonArray ingredients = JSONUtils.getAsJsonArray(json, "ingredients");
            NonNullList<Ingredient> inputs = NonNullList.withSize(4, Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
            }

            return new AlloyFurnaceRecipe(recipeId, output, inputs);
        }


        @Nullable
        @Override
        public AlloyFurnaceRecipe fromNetwork(ResourceLocation recipeId, PacketBuffer buffer) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(4, Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromNetwork(buffer));
            }

            ItemStack output = buffer.readItem();
            return new AlloyFurnaceRecipe(recipeId, output, inputs);
            //return new AlloyFurnaceRecipe(recipeId,new ItemStack(Items.ACACIA_WOOD),NonNullList.withSize(4, Ingredient.of(new ItemStack(Items.ACACIA_WOOD),new ItemStack(Items.ACACIA_WOOD),new ItemStack(Items.ACACIA_WOOD),new ItemStack(Items.ACACIA_WOOD))));
        }

        @Override
        public void toNetwork(PacketBuffer buffer, AlloyFurnaceRecipe recipe) {
            buffer.writeInt(recipe.getIngredients().size());
            buffer.writeItemStack(recipe.getResultItem(), false);

            for (Ingredient ing : recipe.getIngredients()) {
                ing.toNetwork(buffer);
            }
        }
    }
}
