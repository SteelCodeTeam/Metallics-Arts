package net.rudahee.metallics_arts.setup;

import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraftforge.fml.RegistryObject;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.block.crafting.recipe.AlloyFurnaceRecipe;

import java.util.function.Supplier;

public class ModRecipeSerializers {
    public static class Types {
        public static final IRecipeType<AlloyFurnaceRecipe> ALLOY_FURNACE_RECIPE = IRecipeType.register(
                MetallicsArts.MOD_ID + "alloy_furnace");

        private Types() {

        }
    }


    public static class Serializers {
        public static final RegistryObject<IRecipeSerializer<?>> ALLOY_FURNACE_RECIPE = Registration.RECIPE_SERIALIZERS.register(
                "alloy_furnace", AlloyFurnaceRecipe.Serializer::new);
    }

    static void register() {
    }

}

