package net.rudahee.metallics_arts.setup.enums.metals;

import net.minecraft.item.Item;

public enum MetalBurningRecipeData implements IMetal {

        STEEL("steel", "STEEL", null, 200),
        PEWTER("pewter", "PEWTER", null, 200),
        BRONZE("bronze", "BRONZE",null, 200),
        BRASS("brass", "BRASS",null, 200),
        ELECTRUM("electrum", "ELECTRUM",null, 200),
        BENDALLOY("bendalloy", "BENDALLOY",null, 200),
        DURALUMIN("duralumin", "DURALUMIN",null, 200),
        NICROSIL("nicrosil", "NICROSIL",null, 200),
        MALATIUM("malatium", "MALATIUM",null, 200);

        private final String metalNameLower;
        private final String metalNameUpper;
        private Item item;
        private final int ticksToCompleteBurning;

    MetalBurningRecipeData(String metalNameLower, String metalNameUpper, Item item, int ticksToCompleteBurning) {
        this.metalNameLower = metalNameLower;
        this.metalNameUpper = metalNameUpper;
        this.item = item;
        this.ticksToCompleteBurning = ticksToCompleteBurning;
    }

    @Override
    public String getMetalNameLower() {
        return metalNameLower;
    }

    @Override
    public String getMetalNameUpper() {
        return metalNameUpper;
    }

    public Item getItem() {
        return item;
    }

    public int getTicksToCompleteBurning() {
        return ticksToCompleteBurning;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
