package net.rudahee.metallics_arts.data.enums.implementations.custom_items.alloy_furnace;


import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public enum CustomFuelEnum {

    COAL_ITEM(Items.COAL, "coal", 1200),
    CHARCOAL_ITEM(Items.CHARCOAL, "charcoal", 1200),
    BLAZE_ROD_ITEM(Items.BLAZE_ROD, "blaze_rod", 1800),
    COAL_BLOCK(Items.COAL_BLOCK, "coal_block", 10800);


    Item item;
    String name;
    int ticksBurning;

    CustomFuelEnum(Item item, String name, int ticksBurning) {
        this.item = item;
        this.name = name;
        this.ticksBurning = ticksBurning;
    }

    public Item getItem() {
        return item;
    }

    public String getName() {
        return name;
    }

    public int getTicksBurning() {
        return ticksBurning;
    }
}

