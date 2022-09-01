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
import net.minecraft.world.level.Level;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;
import net.rudahee.metallics_arts.setup.registries.ModItems;
import net.rudahee.metallics_arts.setup.registries.ModRecipeTypes;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LargeVialItemRecipe extends CustomRecipe {

    private ItemStack final_result = ItemStack.EMPTY;

    private static final Ingredient INGREDIENT_VIAL = Ingredient.of(ModItems.LARGE_VIAL.get());

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

    public LargeVialItemRecipe(ResourceLocation location) {
        super(location);
    }

    public ItemStack  auxiliar = null;



    @Override
    public boolean matches(CraftingContainer inv, Level world) {
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
                            if(metalsEnVial[metal.getIndex()] >= metal.getMaxAllomanticTicksStorage()){
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
            this.final_result = new ItemStack(ModItems.LARGE_VIAL.get(),1);
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

    @Override
    public ItemStack assemble(CraftingContainer inv) {
        return this.final_result.copy();
    }

    @Override
    public boolean canCraftInDimensions(int nose, int nose2) {
        return true;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipeTypes.LARGE_VIAL_ITEM_RECIPE_SERIALIZER.get();
    }

    public static class Serializer implements RecipeSerializer<LargeVialItemRecipe> {


        @Override
        public LargeVialItemRecipe fromJson(ResourceLocation p_44103_, JsonObject p_44104_) {
            return null;
        }

        @Override
        public @Nullable LargeVialItemRecipe fromNetwork(ResourceLocation p_44105_, FriendlyByteBuf p_44106_) {
            return null;
        }

        @Override
        public void toNetwork(FriendlyByteBuf p_44101_, LargeVialItemRecipe p_44102_) {

        }
    }
}
