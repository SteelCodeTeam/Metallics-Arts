package net.rudahee.metallics_arts.setup.enums;

public enum MetalGenerationData implements IMetal{

    TIN("tin", "TIN", 10, 10, 10, 5),
    COPPER("copper", "COPPER", 10, 10, 10, 5),
    ZINC("zinc", "ZINC", 10, 10, 10, 5),
    CADMIUM("cadmium", "CADMIUM", 10, 10, 10, 5),
    ALUMINUM("aluminum", "ALUMINUM", 10, 10, 10, 5),
    CHROMIUM("chromium", "CHROMIUM", 10, 10, 10, 5),
    SILVER("silver", "SILVER", 5, 5, 5, 5),
    LEAD("lead", "LEAD", 5, 5, 5, 5),
    NICKEL("nickel", "NICKEL", 5, 5, 5, 5);

    private final String metalNameLower;
    private final String metalNameUpper;
    private final int veinSize;
    private final int minHeight;
    private final int maxHeigth;
    private final int amountPerChunk;


    MetalGenerationData( String metalNameLower, String metalNameUpper,int veinSize, int minHeight, int maxHeigth, int amountPerChunk) {
        this.veinSize = veinSize;
        this.minHeight = minHeight;
        this.maxHeigth = maxHeigth;
        this.amountPerChunk = amountPerChunk;
        this.metalNameLower = metalNameLower;
        this.metalNameUpper = metalNameUpper;
    }

    public int getVeinSize() {
        return veinSize;
    }

    public int getMinHeight() {
        return minHeight;
    }

    public int getMaxHeigth() {
        return maxHeigth;
    }

    public int getAmountPerChunk() {
        return amountPerChunk;
    }

    @Override
    public String getMetalNameLower() {
        return null;
    }

    @Override
    public String getMetalNameUpper() {
        return null;
    }
}
