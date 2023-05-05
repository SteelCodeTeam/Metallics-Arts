package net.rudahee.metallics_arts.data.custom_recipes.vials;

import it.unimi.dsi.fastutil.Hash;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
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
import net.minecraftforge.common.Tags;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.setup.registries.ModItemsRegister;
import net.rudahee.metallics_arts.setup.registries.ModRecipeTypesRegister;
import net.rudahee.metallics_arts.setup.registries.items.ModTags;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Class that control the large vial recipe. It's a custom recipe, so extends CustomRecipe.
 *
 * @author SteelCode Team
 * @since 1.5.1
 *
 * @see CustomRecipe
 * @see ItemStack
 * @see Ingredient
 * @see RecipeSerializer
 */
public class LargeVialItemRecipe extends CustomRecipe {

    private ItemStack finalResult = ItemStack.EMPTY;
    private static final Ingredient INGREDIENT_VIAL = Ingredient.of(ModItemsRegister.LARGE_VIAL.get());

    private static final HashMap<String ,Ingredient> INGREDIENT_MAP = new HashMap<>() {{
        /**MAPA DE INGREDIENTES,
         * KEY : NOMBRE DEL METAL EN MINUSCULA
         * VALUE: ITEM TAG CASTEADO A INGREDIENT*/


        for (MetalTagEnum metal : MetalTagEnum.values()) {
            if (metal.getNameLower().equals("iron")) {
                put(metal.getNameLower(), Ingredient.of(Tags.Items.NUGGETS_IRON));
            } else if (metal.getNameLower().equals("gold")) {
                put(metal.getNameLower(), Ingredient.of(Tags.Items.NUGGETS_GOLD));
            } else {
                put(metal.getNameLower(), Ingredient.of(ModTags.NUGGETS.get(metal.getMetalNameLower())));
            }
        }

    }};

    /**
     * Constructor that receive the path of json recipe.
     *
     * @param location of the path.
     */
    public LargeVialItemRecipe(ResourceLocation location) {
        super(location);
    }

/** FALTANTE SE USA PARA CALCULAR CUANTAS PEPITAS DE CADA METAL SE PUEDEN COLOCAR EN EL VIAL,
 * SI TIENE 2 DE HIERRO 4 DE ORO, DEBERIA ALMACENAR 8 EN HIERRO Y 6 EN ORO*/
    private HashMap<String, Integer> faltante = new HashMap<>();

    /** CANTIDAD EN STACK ACUMULA CUANTOS NUGGETS HAY EN EL STACK PARA CRAFTEO*/
    private HashMap<String, Integer> cantidadDelStack = new HashMap<>();

    /** INGREDIENTES A ELIMINAR ALMACENA LA LISTA DE INGREDIENTES QUE DEBEN CONSUMIRSE PARA EL CRAFTEO, SE UTILIZA EN EL METODO
     * getRemainingItems() PARA SABER EL NOMBRE DE LOS METALES A ELIMINAR Y COMPARAR SI HACE MACH CON LO QUE EL METODO VA A CONSUMIR*/
    private HashMap<String ,Ingredient> ingredienteAEliminar = new HashMap<>();

    /**
     * Method in which the ingredients of the recipe are evaluated if they are correct and coincide with this one.
     * <p>
     * In this case, it is verified that the pips are correct, and that the quantity is correct.
     * If everything matches, it returns 'true' because the recipe exists and is correct.
     *
     * @param inventory the inventory in which the crafting is taking place.
     * @param level world in which crafting is taking place.
     *
     * @return boolean
     */
    @Override
    public boolean matches(@NotNull CraftingContainer inventory, @NotNull Level level) {
        boolean[] ingredients = {false, false};
        int maxQtyNuggets = 10;
        ItemStack actualIngredient;

        //reserva actual del metal en el vial
        HashMap<String, Integer> metalsInVial = new HashMap<>();
        //cantidad a la que equivale cada pepita, si el maximo de hierro es 3000, entonces cada pepita equivale a 3000/10 (maximo de pepitas de este vial)
        HashMap<String, Integer> cantStorage = new HashMap<>();
        //lista de metales que deben agregarse al vial, boolean si se carga, false si no
        HashMap<String, Boolean> addMetal = new HashMap<>();
        //carga maxima posible de un metal, por ejemplo hierro 3000
        HashMap<String, Integer> maxValues = new HashMap<>();


        //LIMPIO TODAS LAS ESTRUCTURAS
        for (MetalTagEnum metal : MetalTagEnum.values()) {
            maxValues.put(metal.getNameLower(), metal.getMaxAllomanticTicksStorage());
            metalsInVial.put(metal.getNameLower(), 0);
            cantStorage.put(metal.getNameLower(),metal.getMaxAllomanticTicksStorage()/maxQtyNuggets);
            addMetal.put(metal.getNameLower(), false);
            faltante.put(metal.getNameLower(),0);
            cantidadDelStack.put(metal.getNameLower(),0);
            ingredienteAEliminar.clear();
        }

        //RECORRO EL INVENTARIO, QUE ES LA ZONA DE CRAFTEO (LOS 9 SLOTS DE LA MESA, O LOS 4 DE INVENTARIO)
        for( int i = 0; i < inventory.getContainerSize(); i++) {
            actualIngredient = inventory.getItem(i);
            if (!actualIngredient.isEmpty()) {

                // PREGUNTO SI EL INGREDIENTE EVALUADO ES UN VIA
                if (INGREDIENT_VIAL.test(actualIngredient)) {
                    if (ingredients[0]) {
                        return false; //RETURN FALSE CANCELA EL CRAFTEO, POR LO QUE NO DEBERIA TENER SALIDA
                    } else {
                        ingredients[0] = true; //SE USA PARA ASEGURARSE DE QUE TENGO UN UNICO VIAL
                    }
                    if (actualIngredient.hasTag()){
                        for (MetalTagEnum metal : MetalTagEnum.values()) {
                            //RECORRO LOS METALES Y ME GUARDO LOS VALORES DE CARGA ACTUAL, YA QUE GENERAR UN NUEVO VIAL DESTRUIRA ESTA INFO
                            if (actualIngredient.getTag().contains(metal.getGemNameLower())){
                                metalsInVial.put(metal.getNameLower(), actualIngredient.getTag().getInt(metal.getNameLower()));
                                faltante.put(metal.getNameLower(), maxQtyNuggets - (metalsInVial.get(metal.getNameLower())/cantStorage.get(metal.getNameLower())));
                                //valor de 0 a 10
                            }
                        }
                    }
                }

                ItemStack auxIngredient = actualIngredient; //ES NECESARIO PARA TRABAJAR SIN ROMPER EL INGREDIENTE ACTUAL, IDK

                //RECORRO LA LISTA DE ITEMS, PREGUNTO QUE LA CARGA DEL VIAL DE ESE METAL NO SEA SUPERIOR AL MAXIMO PERMITIDO, Y SI NO LO ES CHEQUEO QUE
                //EL ITEM ACTUAL SEA EL CORRESPONDIENTE A ESE METAL
                INGREDIENT_MAP.forEach((name, ing) -> {
                    if (!addMetal.get(name) && !(metalsInVial.get(name) >= maxValues.get(name))) {
                        if (ing.test(auxIngredient)) {
                            cantidadDelStack.put(name, auxIngredient.getCount());
                            ingredienteAEliminar.put(name, ing);
                            addMetal.put(name, true);
                            ingredients[1] = true;
                        }
                    }
                });
            }
        }

        //SI AMBOS SON TRUE, SIGNIFICA QUE TENGO 1 VIAL Y AL MENOS UN NUGGET QUE DEBERIA PODER CARGAR
        if (ingredients[0] && ingredients[1]){
            this.finalResult = new ItemStack(ModItemsRegister.LARGE_VIAL.get(),1);
            CompoundTag compoundNBT = new CompoundTag();
            //CREO UN NUEVO ITEM Y SUS TAGS PARA LA SALIDA

            /*for (MetalTagEnum metal : MetalTagEnum.values()) {
                if (ingredienteAEliminar.containsKey(metal.getNameLower()) && metalsInVial.get(metal.getNameLower()).equals(maxValues.get(metal.getNameLower()))) {
                    return false;
                }
            }*/

            for (MetalTagEnum metal : MetalTagEnum.values()) {
                //RECORRO LOS METALES Y HAGO LA CARGA CORRESPONDIENTE EN CADA METAL
                if (cantidadDelStack.get(metal.getNameLower()) > 0 && addMetal.get(metal.getNameLower())){
                    //POR LA RAMA DEL IF, SE QUE TENGO MENOS PEPITAS DE LAS NECESARIAS PARA LLENAR POR COMPLETO LA RESERVA
                    if (cantidadDelStack.get(metal.getNameLower()) < faltante.get(metal.getNameLower())) {
                        compoundNBT.putInt(metal.getNameLower(),
                                metalsInVial.get(metal.getNameLower()) +
                                        (cantStorage.get(metal.getNameLower()) * cantidadDelStack.get(metal.getNameLower())));
                    } else {
                        //POR LA RAMA DEL ELSE, TENGO LAS MAS PEPITAS DE LAS NECESARIAS, POR LO QUE SOLO CARGO LO QUE ME FALTA
                        compoundNBT.putInt(metal.getNameLower(),
                                metalsInVial.get(metal.getNameLower()) + (faltante.get(metal.getNameLower()) * cantStorage.get(metal.getNameLower())));
                    }
                } else {
                    //EN CASO DE QUE EL CRAFTEO NO TENGA NUGGETS DE UN METAL, CARGO LA INFORMACION VIEJA
                    compoundNBT.putInt(metal.getNameLower(), metalsInVial.get(metal.getNameLower()));
                }

            }
            compoundNBT.putFloat("CustomModelData", 1);
            //CAMBIO DE TEXTURA
            this.finalResult.setTag(compoundNBT);
            return true;
        }
        else {
            return false;
        }
    }


    @Override
    public NonNullList<ItemStack> getRemainingItems(CraftingContainer container) {

        NonNullList<ItemStack> list = NonNullList.withSize(container.getContainerSize(), ItemStack.EMPTY);
        for (MetalTagEnum metal : MetalTagEnum.values()){

            //SI EL MAPA CONTIENE EL METAL, ES QUE HAY QUE ELIMINAR DE ESOS NUGGETS
            if (ingredienteAEliminar.containsKey(metal.getNameLower())) {
                for (int i = 0; i < list.size(); ++i) {
                    //RECORRO LA MESA DE CRAFTEO, RECUPERO SU STACK
                    ItemStack item = container.getItem(i);
                    //SI EL INGREDIENTE QUE QUIERO ELIMNAR HACE MACH CON EL DE LA MESA DE CRAFTEO PASO A ELIMINAR
                    if (ingredienteAEliminar.get(metal.getNameLower()).test(item)) {
                        //pepitas de menos
                        if (cantidadDelStack.get(metal.getNameLower()) < faltante.get(metal.getNameLower())) {
                            item.setCount(0);
                            list.set(i,item);
                            //RAMA DEL IF, TENGO LA PEPITAS DE MENOS
                        } else {
                            //pepitas de mas
                            item.setCount(item.getCount() - faltante.get(metal.getNameLower()));
                            list.set(i,item);
                            //TENGO IGUAL PEPITAS DE MAS A LAS QUE NECESITO
                        }
                        //list.set(i, item.getCraftingRemainingItem());
                    }
                }
            }
        }

        return super.getRemainingItems(container);
    }

    /**
     * Method that return a copy of the final result item of matches method.
     *
     * @param inventory the inventory in which the crafting is taking place.
     *
     * @return ItemStack
     */
    @Override
    public @NotNull ItemStack assemble(@NotNull CraftingContainer inventory) {
        return this.finalResult.copy();
    }

    /**
     * This method evaluates if it is possible to craft the object in the different dimensions of the game.
     * <p>
     * Receive 2 parameters, but they don't use by nothing. The player always can craft vials, dimension no matters.
     *
     * @param num1 don't matter, because don't have any use.
     * @param num2 don't matter, because don't have any use.
     * 
     * @return boolean (Always true)
     */
    @Override
    public boolean canCraftInDimensions(int num1, int num2) {
        return true;
    }

    /**
     * This method its getter for serializer. So only return a LargeVialItemRecipeSerializer.
     *
     * @return RecipeSerializer
     */
    @Nonnull
    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipeTypesRegister.LARGE_VIAL_ITEM_RECIPE_SERIALIZER.get();
    }

    /**
     * Static class that controls Custom vial recipe serializer. Extend SimpleRecipeSerializer<LargeVialItemRecipe>
     *
     * @author SteelCode Team
     * @since 1.5.1
     *
     * @see SimpleRecipeSerializer
     * @see LargeVialItemRecipe
     */
    public static class Serializer extends SimpleRecipeSerializer<LargeVialItemRecipe> {

        /**
         * Constructor of the class. The only thing that constructor does its pass the Recipe class to superclass.
         */
        public Serializer() {
            super(LargeVialItemRecipe::new);
        }

    }

}
