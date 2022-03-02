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
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;
import net.rudahee.metallics_arts.setup.registries.ModItems;
import net.rudahee.metallics_arts.setup.registries.ModRecipeTypes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SmallVialItemRecipe extends SpecialRecipe {

    private ItemStack final_result = ItemStack.EMPTY;

    private static final Ingredient INGREDIENT_VIAL = Ingredient.of(ModItems.SMALL_VIAL.get());

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

    public SmallVialItemRecipe(ResourceLocation location) {
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

        int cantMaxPep = 5;

        for(int i = 0; i < inv.getContainerSize(); i++) {
            actualIngredient = inv.getItem(i);
            if (actualIngredient != null && !actualIngredient.isEmpty()) {
                if (INGREDIENT_VIAL.test(inv.getItem(i))) {
                    if (actualIngredient.getTag() != null){
                        for (MetalsNBTData metal : MetalsNBTData.values()) {
                            if (actualIngredient.getTag().contains(metal.getGemNameLower())){
                                cantStorage[metal.getIndex()] = ((metal.getMaxAllomanticTicksStorage())/2)/cantMaxPep;
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
                if (INGREDIENT_NUGGET.stream().anyMatch(
                        ing -> ing.getItems()[0].getItem().getDescriptionId().equals(auxiliar.getItem().getDescriptionId()))) {
                    for (MetalsNBTData metal : MetalsNBTData.values()) {
                        if ((actualIngredient.getItem().getDescriptionId()).equals("item.minecraft."+metal.getNameLower()+"_nugget")
                                ||(actualIngredient.getItem().getDescriptionId()).equals("item.metallics_arts."+metal.getNameLower()+"_nugget")){
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

        //INTENTAR QUE SE PUEDAN CARGAR MAS DE UNA PEPITA DE UN METAL A LA VEZ

        if (ingredients[0] && ingredients[1]){
            this.final_result = new ItemStack(ModItems.SMALL_VIAL.get(),1);
            CompoundNBT compoundNBT = new CompoundNBT();
            for (MetalsNBTData metal : MetalsNBTData.values()){
                if (addMetal[metal.getIndex()]){
                    compoundNBT.putInt(metal.getNameLower(),metalsEnVial[metal.getIndex()]+cantStorage[metal.getIndex()]);
                }else{
                    compoundNBT.putInt(metal.getNameLower(),metalsEnVial[metal.getIndex()]);
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

    public static class Serializer extends SpecialRecipeSerializer<BigVialItemRecipe> {

        public Serializer() {
            super(BigVialItemRecipe::new);
        }
    }
}
