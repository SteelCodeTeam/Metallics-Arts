package net.rudahee.metallics_arts.data.recipes.vials;

import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.SpecialRecipe;
import net.minecraft.item.crafting.SpecialRecipeSerializer;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.modules.items.vials.vial.Vial;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;
import net.rudahee.metallics_arts.setup.enums.metals.Metal;
import net.rudahee.metallics_arts.setup.registries.ModItems;
import net.rudahee.metallics_arts.setup.registries.ModRecipeTypes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VialItemRecipe extends SpecialRecipe {

    private ItemStack final_result = ItemStack.EMPTY;

    private static final Ingredient INGREDIENT_VIAL = Ingredient.of(ModItems.VIAL.get());

    private static final List<Ingredient> INGREDIENT_NUGGET = new ArrayList<Ingredient>() {{
        for (Item metal: ModItems.ITEM_GEMS_NUGGET.values()) {
            add(Ingredient.of(metal.getItem()));
        }
        for (Item metal: ModItems.ITEM_METAL_NUGGET.values()) {
            if (!ModItems.ITEM_METAL_NUGGET.get("lead").getDescriptionId().equals(metal.getDescriptionId())
                && !ModItems.ITEM_METAL_NUGGET.get("silver").getDescriptionId().equals(metal.getDescriptionId())
                && !ModItems.ITEM_METAL_NUGGET.get("nickel").getDescriptionId().equals(metal.getDescriptionId())) {

                add(Ingredient.of(metal.getItem()));
            }
            add(Ingredient.of(Items.IRON_NUGGET.getItem()));
            add(Ingredient.of(Items.GOLD_NUGGET.getItem()));
        }
    }};

    public VialItemRecipe(ResourceLocation location) {
        super(location);
    }

    public ItemStack  auxiliar = null;
    @Override
    public boolean matches(CraftingInventory inv, World world) {
        ItemStack actualIngredient = null;

        int[] metalsEnVial = new int[MetalsNBTData.values().length];
        Arrays.fill(metalsEnVial,0);

        int[] cantStorage = new int[MetalsNBTData.values().length];
        Arrays.fill(cantStorage,0);

        boolean[] addMetal = new boolean[MetalsNBTData.values().length];
        Arrays.fill(addMetal,false);

        boolean[] ingredients = {false, false};

        int cantMaxPep = 10;

        for(int i = 0; i < inv.getContainerSize(); i++) {
            actualIngredient = inv.getItem(i);
            if (actualIngredient != null && !actualIngredient.isEmpty()) {
                if (INGREDIENT_VIAL.test(inv.getItem(i))) {
                    if (actualIngredient.getTag() != null){
                        for (MetalsNBTData metal : MetalsNBTData.values()) {
                            if (actualIngredient.getTag().contains(metal.getGemNameLower())){
                                cantStorage[metal.getIndex()] = metal.getMaxAllomanticTicksStorage()/cantMaxPep;
                                metalsEnVial[metal.getIndex()] = actualIngredient.getTag().getInt(metal.getNameLower());
                                if(metalsEnVial[metal.getIndex()]==metal.getMaxAllomanticTicksStorage()){
                                    return false;
                                }
                            }
                        }
                        ingredients[0] = true;
                    }
                }
                auxiliar = actualIngredient;
                /*for(Ingredient ingredient: INGREDIENT_NUGGET){
                    if (ingredient.test(actualIngredient)){
                        for (MetalsNBTData metal : MetalsNBTData.values()){
                        }
                        ingredients[1] = true;
                    }
                }*/
                if (INGREDIENT_NUGGET.stream().anyMatch(
                        pepe -> pepe.getItems()[0].getItem().getDescriptionId().equals(auxiliar.getItem().getDescriptionId()))) {
                    for (MetalsNBTData metal : MetalsNBTData.values()) {
                         if (metal.getNameLower().equals(actualIngredient.getItem().getDescriptionId().substring(20,actualIngredient.getItem().getDescriptionId().indexOf("_",21)))){
                            if (addMetal[metal.getIndex()]==true){
                                return false;
                            }
                            addMetal[metal.getIndex()]=true;
                            ingredients[1] = true;
                        }
                    }
                }
            }
        }

        if (ingredients[0] && ingredients[1]){
            this.final_result = new ItemStack(ModItems.VIAL.get(),1);
            CompoundNBT compoundNBT = new CompoundNBT();
            for (MetalsNBTData metal : MetalsNBTData.values()){
                if (addMetal[metal.getIndex()]){
                    compoundNBT.putInt(metal.getGemNameLower(),metalsEnVial[metal.getIndex()]+cantStorage[metal.getIndex()]);
                }else{
                    compoundNBT.putInt(metal.getGemNameLower(),metalsEnVial[metal.getIndex()]);
                }
            }
            this.final_result.setTag(compoundNBT);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public ItemStack assemble(CraftingInventory inv) {
        return this.final_result.copy();

    }

    @Override
    public boolean canCraftInDimensions(int nose, int nose2) {
        return true;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return ModRecipeTypes.VIAL_ITEM_RECIPE_SERIALIZER.get();
    }

    public static class Serializer extends SpecialRecipeSerializer<VialItemRecipe> {

        public Serializer() {
            super(VialItemRecipe::new);
        }
    }
}
