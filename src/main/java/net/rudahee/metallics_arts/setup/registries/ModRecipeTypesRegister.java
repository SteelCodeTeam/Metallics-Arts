package net.rudahee.metallics_arts.setup.registries;


import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.data.custom_recipes.tables.CrucibleFurnaceRecipe;
import net.rudahee.metallics_arts.data.custom_recipes.vials.LargeVialItemRecipe;
import net.rudahee.metallics_arts.data.custom_recipes.vials.SmallVialItemRecipe;

public class ModRecipeTypesRegister {
    private ModRecipeTypesRegister() {
        throw new IllegalStateException("Class can't be instantiated");
    }
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, MetallicsArts.MOD_ID);

    public static final RegistryObject<RecipeSerializer<LargeVialItemRecipe>> LARGE_VIAL_ITEM_RECIPE_SERIALIZER
            = RECIPE_SERIALIZERS.register("large_vial_filling", LargeVialItemRecipe.Serializer::new);

    public static final RegistryObject<RecipeSerializer<SmallVialItemRecipe>> SMALL_VIAL_ITEM_RECIPE_SERIALIZER
           = RECIPE_SERIALIZERS.register("small_vial_filling", SmallVialItemRecipe.Serializer::new);

    public static final RegistryObject<RecipeSerializer<CrucibleFurnaceRecipe>> CRUCIBLE_FURNACE_RECIPE_SERIALIZER
            = RECIPE_SERIALIZERS.register("crucible_furnace_melting", () -> CrucibleFurnaceRecipe.Serializer.INSTANCE);


    public static void register(IEventBus eventBus) {
        RECIPE_SERIALIZERS.register(eventBus);
    }

}
