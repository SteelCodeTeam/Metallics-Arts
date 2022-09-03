package net.rudahee.metallics_arts.setup.enums.metals;

import net.minecraft.world.level.block.Block;

public enum MetalGenerationData implements IMetal {

    TIN("tin", "TIN", null,12, 10, 54, 5,1,1),
    COPPER("copper", "COPPER", null,8, 25, 50, 6,1,1),
    ZINC("zinc", "ZINC",null,8, 1, 24, 6,1,1),
    CADMIUM("cadmium", "CADMIUM",null,6, 1, 16, 4,1,1),
    ALUMINUM("aluminum", "ALUMINUM",null,6, 18, 45, 5,1,1),
    CHROMIUM("chromium", "CHROMIUM",null,8, 1, 20, 5,1,1),
    SILVER("silver", "SILVER",null,6, 18, 50, 5,1,1),
    LEAD("lead", "LEAD",null, 6, 1, 30, 5,1,1),
    NICKEL("nickel", "NICKEL",null,4, 10, 25, 5,1,1);

    private final String metalNameLower;
    private final String metalNameUpper;
    private Block block;
    private final int veinSize;
    private final int minHeight;
    private final int maxHeigth;
    private final int amountPerChunk;

    private final int minDrop;
    private final int maxDrop;


    MetalGenerationData(String metalNameLower, String metalNameUpper, Block block, int veinSize, int minHeight, int maxHeigth, int amountPerChunk, int minDrop, int maxDrop) {
        this.veinSize = veinSize;
        this.minHeight = minHeight;
        this.maxHeigth = maxHeigth;
        this.amountPerChunk = amountPerChunk;
        this.metalNameLower = metalNameLower;
        this.metalNameUpper = metalNameUpper;
        this.minDrop = minDrop;
        this.maxDrop = maxDrop;
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

    public int getMinDrop() {
        return minDrop;
    }

    public int getMaxDrop() {
        return maxDrop;
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
