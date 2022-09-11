package net.rudahee.metallics_arts.data.recipes.vials;


import com.google.gson.JsonObject;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleRecipeSerializer;
import net.minecraft.world.level.Level;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;
import net.rudahee.metallics_arts.setup.registries.ModItems;
import net.rudahee.metallics_arts.setup.registries.ModRecipeTypes;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SmallVialItemRecipe extends CustomRecipe {

    private ItemStack final_result = ItemStack.EMPTY;

    private static final Ingredient INGREDIENT_VIAL = Ingredient.of(ModItems.SMALL_VIAL.get());

    private static final List<Ingredient> INGREDIENT_NUGGET = new ArrayList<Ingredient>() {{
        for (Item metal: ModItems.ITEM_GEMS_NUGGET.values()) {
            add(Ingredient.of(metal.asItem()));
        }
        for (Item metal: ModItems.ITEM_METAL_NUGGET.values()) {
            if (!ModItems.ITEM_METAL_NUGGET.get("lead").getDescriptionId().equals(metal.getDescriptionId())
                    && !ModItems.ITEM_METAL_NUGGET.get("silver").getDescriptionId().equals(metal.getDescriptionId())
                    && !ModItems.ITEM_METAL_NUGGET.get("nickel").getDescriptionId().equals(metal.getDescriptionId())) {

                add(Ingredient.of(metal.asItem()));
            }
            add(Ingredient.of(Items.IRON_NUGGET.asItem()));
            add(Ingredient.of(Items.GOLD_NUGGET.asItem()));
        }
    }};

    public SmallVialItemRecipe(ResourceLocation location) {
        super(location);
    }

    public ItemStack auxiliar = null;

    @Override
    public boolean matches(CraftingContainer inv, Level world) {
        int[] metalsEnVial = new int[MetalsNBTData.values().length];
        Arrays.fill(metalsEnVial,0);

        int[] cantStorage = new int[MetalsNBTData.values().length];
        Arrays.fill(cantStorage,0);

        boolean[] addMetal = new boolean[MetalsNBTData.values().length];
        Arrays.fill(addMetal,false);


        boolean[] ingredients = {false, false};

        int cantMaxPep = 5;

        ItemStack actualIngredient = null;
        for(int i = 0; i < inv.getContainerSize();i++) {
            actualIngredient = inv.getItem(i);
            if (!actualIngredient.isEmpty()) {
                if (INGREDIENT_VIAL.test(actualIngredient)) {
                    if (actualIngredient.hasTag()){
                        for (MetalsNBTData metal : MetalsNBTData.values()) {
                            if (actualIngredient.getTag().contains(metal.getGemNameLower())){
                                cantStorage[metal.getIndex()] = (metal.getMaxAllomanticTicksStorage()/2)/cantMaxPep;
                                metalsEnVial[metal.getIndex()] = actualIngredient.getTag().getInt(metal.getNameLower());
                            }
                        }
                        ingredients[0] = true;
                    }
                }
                auxiliar = actualIngredient;

                if (INGREDIENT_NUGGET.stream().anyMatch(
                        ing -> ing.getItems()[0].getItem().getDescriptionId().equals(auxiliar.getItem().getDescriptionId()))) {
                    for (MetalsNBTData metal : MetalsNBTData.values()) {
                        if ((actualIngredient.getItem().getDescriptionId()).equals("item.minecraft."+metal.getNameLower()+"_nugget")
                                ||(actualIngredient.getItem().getDescriptionId()).equals("item.metallics_arts."+metal.getNameLower()+"_nugget")){
                            if (addMetal[metal.getIndex()]){
                                return false;
                            }
                            if(metalsEnVial[metal.getIndex()] >= metal.getMaxAllomanticTicksStorage()/2){
                                return false;
                            }
                            addMetal[metal.getIndex()]=true;
                            ingredients[1] = true;
                        }
                    }
                }
            }
        }

        //INTENTAR QUE SE PUEDAN CARGAR MAS DE UNA PEPITA DE UN METAL A LA VEZ

        if (ingredients[0] && ingredients[1]){
            this.final_result = new ItemStack(ModItems.SMALL_VIAL.get(),1);
            CompoundTag compoundNBT = new CompoundTag();
            for (MetalsNBTData metal : MetalsNBTData.values()){
                if (addMetal[metal.getIndex()]){
                    compoundNBT.putInt(metal.getNameLower(),metalsEnVial[metal.getIndex()]+cantStorage[metal.getIndex()]);
                }else{
                    compoundNBT.putInt(metal.getNameLower(),metalsEnVial[metal.getIndex()]);
                }
            }
            compoundNBT.putInt("CustomModelData", 1);
            this.final_result.setTag(compoundNBT);
            return true;
        }
        else {
            return false;
        }
    }


    /*
    @Override
    public boolean matches(CraftingContainer inv, Level worldIn) {
        this.item_result = ItemStack.EMPTY;

        boolean[] metals = new boolean[Metal.values().length];
        Arrays.fill(metals, false);
        boolean[] ingredients = {false, false};

        for (int i = 0; i < inv.getContainerSize(); ++i) {
            ItemStack itemstack = inv.getItem(i);
            if (!itemstack.isEmpty()) {
                if (INGREDIENT_FLAKES.test(itemstack)) {
                    for (Metal mt : Metal.values()) {
                        if (itemstack.getItem() == MaterialsSetup.FLAKES.get(mt.getIndex()).get()) {
                            if (metals[mt.getIndex()]) {
                                return false;
                            }

                            metals[mt.getIndex()] = true;
                            ingredients[1] = true;
                        }
                    }
                } else if (INGREDIENT_VIAL.test(itemstack)) {
                    if (itemstack.getTag() != null) {
                        for (Metal mt : Metal.values()) {
                            if (itemstack.getTag().contains(mt.getName())) {
                                boolean hasMetalAlready = itemstack.getTag().getBoolean(mt.getName());
                                if (metals[mt.getIndex()] && hasMetalAlready) {
                                    return false;
                                } else {
                                    metals[mt.getIndex()] = metals[mt.getIndex()] || hasMetalAlready;
                                }
                            }
                        }
                    }
                    ingredients[0] = true;
                } else {
                    return false;
                }

            }
        }
        if (ingredients[0] && ingredients[1]) {
            this.item_result = new ItemStack(ConsumeSetup.VIAL.get(), 1);
            CompoundTag nbt = new CompoundTag();
            for (Metal mt : Metal.values()) {
                nbt.putBoolean(mt.getName(), metals[mt.getIndex()]);
            }
            nbt.putInt("CustomModelData", 1);
            this.item_result.setTag(nbt);
            return true;
        } else {
            return false;
        }
    }
     */

    @Override
    public ItemStack assemble(CraftingContainer inv) { //getCraftingResult
        return this.final_result.copy();
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public ItemStack getResultItem() {
        return this.final_result;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipeTypes.SMALL_VIAL_ITEM_RECIPE_SERIALIZER.get();
    }

    public static class Serializer extends SimpleRecipeSerializer<SmallVialItemRecipe> {
        public Serializer() {
            super(SmallVialItemRecipe::new);
        }
    }
}
