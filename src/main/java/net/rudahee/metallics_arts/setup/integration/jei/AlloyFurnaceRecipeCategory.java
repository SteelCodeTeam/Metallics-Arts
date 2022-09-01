package net.rudahee.metallics_arts.setup.integration.jei;


import com.mojang.blaze3d.vertex.PoseStack;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.rudahee.metallics_arts.setup.registries.ModBlock;

import java.awt.*;

/*public class AlloyFurnaceRecipeCategory implements IRecipeCategory<AlloyFurnaceRecipe> {

    private final static ResourceLocation UID = new ResourceLocation(MetallicsArts.MOD_ID, "alloy_furnace_uid");
    private final static ResourceLocation TEXTURE = new ResourceLocation(MetallicsArts.MOD_ID,
            "textures/gui/alloy_furnace_jei.png");

    private final IDrawable backgrund;
    private final IDrawable icon;
    private final IDrawableStatic arrow;


    public AlloyFurnaceRecipeCategory(IGuiHelper helper) {
        this.backgrund = helper.createDrawable(TEXTURE,0,0,118,65);
        this.icon = helper.createDrawableIngredient(new ItemStack(ModBlock.ALLOY_FURNACE_BLOCK.get()));
        this.arrow = helper.createDrawable(TEXTURE,65,14,20,16);
    }

    public static ResourceLocation getUidStatic (){
        return UID;
    }

    @Override
    public Class getRecipeClass() {
        return AlloyFurnaceRecipe.class;
    }

    @Override
    public RecipeType<AlloyFurnaceRecipe> getRecipeType() {
        return ;
                REHACER

    /}

    @Override
    public Component getTitle() {
        return ModBlock.ALLOY_FURNACE_BLOCK.get().getName();
    }

    @Override
    public IDrawable getBackground() {
        return backgrund;
    }

    @Override
    public IDrawable getIcon() {
        return icon;
    }




    @Override
    public void setIngredients(CraftingContainer recipe, IFocusGroup ingredients) {
        ingredients.setInputIngredients(recipe.getIngredients());
        ingredients.setOutput(VanillaTypes.ITEM,recipe.getResultItem());
    }


    @Override
    public void setRecipe(IRecipeLayoutBuilder recipeLayout, AlloyFurnaceRecipe recipe, IFocusGroup ingredients) {
        recipeLayout.getItemStacks().init(0,true,10,4);
        recipeLayout.getItemStacks().init(3,true,10,23);
        recipeLayout.getItemStacks().init(2,true,29,4);
        recipeLayout.getItemStacks().init(1,true,29,23);
        //recipeLayout.getItemStacks().init(4,true,50,55);
        recipeLayout.getItemStacks().init(5,false,91,14);

        recipeLayout.getItemStacks().set(ingredients);
    }

    @Override
    public void draw(AlloyFurnaceRecipe recipe, PoseStack matrixStack, double mouseX, double mouseY) {
        IRecipeCategory.super.draw(recipe, matrixStack, mouseX, mouseY);
    }

}*/
