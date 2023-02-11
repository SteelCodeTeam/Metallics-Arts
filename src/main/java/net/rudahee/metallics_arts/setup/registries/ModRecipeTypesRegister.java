package net.rudahee.metallics_arts.setup.registries;


import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleRecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.data.custom_recipes.vials.LargeVialItemRecipe;
import net.rudahee.metallics_arts.data.custom_recipes.vials.SmallVialItemRecipe;

public class ModRecipeTypesRegister {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZER =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, MetallicsArts.MOD_ID);

    public static final RegistryObject<SimpleRecipeSerializer<LargeVialItemRecipe>> LARGE_VIAL_ITEM_RECIPE_SERIALIZER
            = RECIPE_SERIALIZER.register("large_vial_filling", LargeVialItemRecipe.Serializer::new);

    public static final RegistryObject<SimpleRecipeSerializer<SmallVialItemRecipe>> SMALL_VIAL_ITEM_RECIPE_SERIALIZER
           = RECIPE_SERIALIZER.register("small_vial_filling", SmallVialItemRecipe.Serializer::new);

    public static void register(IEventBus eventBus) {
        RECIPE_SERIALIZER.register(eventBus);
    }

}
