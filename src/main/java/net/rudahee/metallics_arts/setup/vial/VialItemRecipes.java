package net.rudahee.metallics_arts.setup.vial;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.*;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistryEntry;
import net.rudahee.metallics_arts.data.recipes.AlloyFurnaceRecipe;
import net.rudahee.metallics_arts.data.recipes.ModRecipeTypes;
import net.rudahee.metallics_arts.setup.ModItems;
import net.rudahee.metallics_arts.setup.enums.Metal;
import net.rudahee.metallics_arts.setup.enums.MetalsNBTData;
import org.graalvm.compiler.nodes.java.MonitorIdNode;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VialItemRecipes implements IVialRecipe {

    /*private ItemStack item_result = ItemStack.EMPTY;
    List<MetalsNBTData> metalList = Arrays.asList(MetalsNBTData.values());
    List<Item> a = new ArrayList<>();*/

    private final ResourceLocation id;
    private final ItemStack output;
    private final NonNullList<Ingredient> recipeItems;


    /*public VialItemRecipes (){
        for (MetalsNBTData metal:metalList) {
            a.add(ModItems.ITEM_METAL_NUGGET.get(metal.getNameLower()+ "_nugget"));
        }
    }*/
    public VialItemRecipes(ResourceLocation id, ItemStack output, NonNullList<Ingredient> recipeItems) {
        this.id = id;
        this.output = output;
        this.recipeItems = recipeItems;
    }

    @Override
    public boolean matches(IInventory inventory, World world) {
        List<Ingredient> testTarget = recipeItems;

        for (Ingredient in: testTarget) {
            for (int i = 0; i < inventory.getContainerSize(); i++) {
                if (in.test(inventory.getItem(i))) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    @Override
    public ItemStack assemble(IInventory inventory) {
        return output.copy();
    }

    @Override
    public ItemStack getResultItem() {
        return null;
    }

    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return ModRecipeTypes.VIAL_SERIALIZER.get();
    }



    //SERIALIZER OF FURNACE

    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>>
            implements IRecipeSerializer<VialItemRecipes> {


        @Override
        public VialItemRecipes fromJson(ResourceLocation recipeId, JsonObject json) {

            ItemStack output = ShapedRecipe.itemFromJson(JSONUtils.getAsJsonObject(json, "output"));

            JsonArray ingredients = JSONUtils.getAsJsonArray(json, "ingredients");
            NonNullList<Ingredient> inputs = NonNullList.withSize(4, Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
            }

            return new VialItemRecipes(recipeId, output, inputs);
        }

        @Nullable
        @Override
        public VialItemRecipes fromNetwork(ResourceLocation recipeId, PacketBuffer buffer) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(4, Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromNetwork(buffer));
            }

            ItemStack output = buffer.readItem();
            return new VialItemRecipes(recipeId, output, inputs);
        }

        @Override
        public void toNetwork(PacketBuffer buffer, VialItemRecipes recipe) {
            buffer.writeInt(recipe.getIngredients().size());
            for (Ingredient ing : recipe.getIngredients()) {
                ing.toNetwork(buffer);
            }
            buffer.writeItemStack(recipe.getResultItem(), false);
        }
    }












    /*@Override
    public boolean matches(CraftingInventory inv, World worldIn) {
        List<Ingredient> testTarget = new ArrayList<>();
        for (Item item: a){
            testTarget.add(Ingredient.of(item));
        }
        testTarget.add(Ingredient.of(ModItems.VIAL.get()));

        for (Ingredient in: testTarget) {
            for (int i = 0; i < inv.getContainerSize(); i++) {
                if (in.test(inv.getItem(i))) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    @Override
    public ItemStack assemble(CraftingInventory inv) {
        return this.item_result.copy();
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= 2;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return null;
    }*/


}
