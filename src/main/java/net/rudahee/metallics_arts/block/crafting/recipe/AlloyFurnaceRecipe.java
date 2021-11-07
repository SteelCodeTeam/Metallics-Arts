package net.rudahee.metallics_arts.block.crafting.recipe;

import com.google.gson.JsonObject;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.SingleItemRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistryEntry;
import net.rudahee.metallics_arts.setup.ModRecipeSerializers;
import javax.annotation.Nullable;

public class AlloyFurnaceRecipe extends SingleItemRecipe {
    public AlloyFurnaceRecipe( ResourceLocation recipeId, Ingredient ingredient, ItemStack result) {
        super (ModRecipeSerializers.Types.alloyFurnace, ModRecipeSerializers.Serializers.alloyFurnace.get(), recipeId, "", ingredient, result);
    }

    @Override
    public boolean matches(IInventory inv, World world) {
        return this.ingredient.test(inv.getItem(0));
    }

    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements  IRecipeSerializer <AlloyFurnaceRecipe> {


        @Override
        public AlloyFurnaceRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            Ingredient ingredient = Ingredient.fromJson(json.get("ingredient"));
            ResourceLocation itemID = new ResourceLocation(JSONUtils.getAsString(json, "result"));
            int count = JSONUtils.getAsInt(json,"count",1);

            ItemStack result = new ItemStack(ForgeRegistries.ITEMS.getValue(itemID),count);
            return new AlloyFurnaceRecipe(recipeId, ingredient, result);
        }

        @Nullable
        @Override
        public AlloyFurnaceRecipe fromNetwork(ResourceLocation recipeId, PacketBuffer buffer) {
            Ingredient ingredient = Ingredient.fromNetwork(buffer);
            ItemStack result = buffer.readItem();
            return  new AlloyFurnaceRecipe(recipeId, ingredient, result);

            //revisar
            //buffer.writeByte(numberOfIngredients);
            //for (int i = 0; i< numberOfIngredients;i++){
              //  buffer.write(...)
            //}

        }

        @Override
        public void toNetwork(PacketBuffer buffer, AlloyFurnaceRecipe recipe) {
            recipe.ingredient.toNetwork(buffer);
            buffer.writeItem(recipe.result);
        }
    }
}
