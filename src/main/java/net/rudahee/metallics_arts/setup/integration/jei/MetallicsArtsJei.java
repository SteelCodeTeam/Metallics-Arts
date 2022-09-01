package net.rudahee.metallics_arts.setup.integration.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.data.recipes.alloy_furnace.AlloyFurnaceRecipe;
import net.rudahee.metallics_arts.setup.registries.ModRecipeTypes;

import java.util.Objects;
import java.util.stream.Collectors;

@JeiPlugin
public class MetallicsArtsJei implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(MetallicsArts.MOD_ID,"jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new AlloyFurnaceRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager recipeManager = Objects.requireNonNull(Minecraft.getInstance().level).getRecipeManager();
        registration.addRecipes(recipeManager.getAllRecipesFor(ModRecipeTypes.ALLOY_FURNACE_RECIPE).stream()
                .filter(r -> r instanceof AlloyFurnaceRecipe).collect(Collectors.toList()),AlloyFurnaceRecipeCategory.getUidStatic());
    }
}
