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
        public static final IRecipeType<AlloyFurnaceRecipe> alloyFurnace = IRecipeType.register(
                MetallicsArts.MOD_ID + "alloyFurnace");
        private Types (){

        }
    }


    public static class Serializers {
        public static final RegistryObject<IRecipeSerializer<?>> alloyFurnace = Registration.RECIPE_SERIALIZERS.register(
                "alloyFurnace", AlloyFurnaceRecipe.Serializer::new);

    }
    static void register(){}
}
