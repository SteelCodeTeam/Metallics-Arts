package net.rudahee.metallics_arts.modules.custom_blocks.alloy_furnace;


import net.minecraft.nbt.CompoundTag;
import org.antlr.runtime.misc.IntArray;

public class AlloyFurnaceData extends IntArray {

    public int actualFuelBurning;
    public int maxFuelBurning;
    public int isCrafting; // 0 = false, 1 = true;
    public int actualTimeToActualRecipe;
    public int maxTimeToActualRecipe;


    public void updateNBTData(CompoundTag nbt) {
        nbt.putInt("actual_fuel_burning", actualFuelBurning);
        nbt.putInt("max_fuel_burning", maxFuelBurning);
        nbt.putInt("is_crafting", isCrafting);
        nbt.putInt("actual_time_to_recipe", actualTimeToActualRecipe);
        nbt.putInt("max_time_to_recipe", maxTimeToActualRecipe);
    }

    public void readNBTData(CompoundTag nbt) {
        actualFuelBurning = nbt.getInt("actual_fuel_burning");
        maxFuelBurning = nbt.getInt("max_fuel_burning");
        isCrafting = nbt.getInt("is_crafting");
        actualTimeToActualRecipe = nbt.getInt("actual_time_to_recipe");
        maxTimeToActualRecipe = nbt.getInt("max_time_to_recipe");
    }

    public int get(int key) {
        if (key == 1) {
            return actualFuelBurning;
        } else if (key == 2) {
            return maxFuelBurning;
        } else if (key == 3) {
            return isCrafting;
        } else if (key == 4) {
            return actualTimeToActualRecipe;
        } else if (key == 5) {
            return maxTimeToActualRecipe;
        } else {
            return -1;
        }
    }
    public void set(int key, int value) {
        if (key == 1) {
             actualFuelBurning = value;
        } else if (key == 2) {
             maxFuelBurning = value;
        } else if (key == 3) {
             isCrafting = value;
        } else if (key == 4) {
             actualTimeToActualRecipe = value;
        } else if (key == 5) {
             maxTimeToActualRecipe = value;
        }
    }
    public int getCount() {
        return 5;
    }
}
