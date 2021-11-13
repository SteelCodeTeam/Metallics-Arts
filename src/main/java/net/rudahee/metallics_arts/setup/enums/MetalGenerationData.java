package net.rudahee.metallics_arts.setup.enums;

import net.minecraft.block.Block;

public enum MetalGenerationData implements IMetal{

    TIN("tin", "TIN", null,10, 10, 10, 5),
    COPPER("copper", "COPPER", null,10, 10, 10, 5),
    ZINC("zinc", "ZINC",null,10, 10, 10, 5),
    CADMIUM("cadmium", "CADMIUM",null,10, 10, 10, 5),
    ALUMINUM("aluminum", "ALUMINUM",null,10, 10, 10, 5),
    CHROMIUM("chromium", "CHROMIUM",null,10, 10, 10, 5),
    SILVER("silver", "SILVER",null,5, 5, 5, 5),
    LEAD("lead", "LEAD",null, 5, 5, 5, 5),
    NICKEL("nickel", "NICKEL",null,5, 5, 5, 5);

    private final String metalNameLower;
    private final String metalNameUpper;
    private Block block;
    private final int veinSize;
    private final int minHeight;
    private final int maxHeigth;
    private final int amountPerChunk;


    MetalGenerationData( String metalNameLower, String metalNameUpper, Block block, int veinSize, int minHeight, int maxHeigth, int amountPerChunk) {
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

    public Block getBlock() {
        return block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }
}
