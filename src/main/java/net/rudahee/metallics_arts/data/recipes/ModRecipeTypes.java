package net.rudahee.metallics_arts.data.recipes;

import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.rudahee.metallics_arts.MetallicsArts;

public class ModRecipeTypes {
    public static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZER =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, MetallicsArts.MOD_ID);

    public static final RegistryObject<IRecipeSerializer<AlloyFurnaceRecipe>> ALLOY_FURNACE_SERIALIZER
            = RECIPE_SERIALIZER.register("alloy", AlloyFurnaceRecipe.Serializer::new);

    public static IRecipeType<AlloyFurnaceRecipe> ALLOY_FURNACE_RECIPE
            = IRecipeType.register("metallics_arts:alloy");

    public static void register(IEventBus eventBus) {
        RECIPE_SERIALIZER.register(eventBus);
        Registry.register(Registry.RECIPE_TYPE, AlloyFurnaceRecipe.TYPE_ID, ALLOY_FURNACE_RECIPE);
    }

}
