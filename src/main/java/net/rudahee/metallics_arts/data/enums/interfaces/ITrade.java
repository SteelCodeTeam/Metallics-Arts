package net.rudahee.metallics_arts.data.enums.interfaces;

import net.minecraft.world.item.ItemStack;

public interface ITrade {

    int getLevel();
    ItemStack getPrimaryInput();
    ItemStack getSecondaryInput();
    ItemStack getOutput();
    int getMaxUses();
    int getXp();

}
