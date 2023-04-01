package net.rudahee.metallics_arts.data.enums.implementations;

import net.minecraft.world.level.block.Block;
import net.rudahee.metallics_arts.data.enums.interfaces.IMetal;

/**
 * An enumeration representing various ore generation settings for different metals.
 * This enumeration also implements the IMetal interface.
 *
 * @author SteelCode Team
 * @since 1.5.1
 */
public enum OreGenerationEnum implements IMetal {

    TIN("tin", "TIN", null,12, 10, 54, 5),
    ZINC("zinc", "ZINC",null,8, 1, 24, 6),
    CADMIUM("cadmium", "CADMIUM",null,6, 1, 16, 4),
    ALUMINUM("aluminum", "ALUMINUM",null,6, 18, 45, 5),
    CHROMIUM("chromium", "CHROMIUM",null,8, 1, 20, 5),
    SILVER("silver", "SILVER",null,6, 18, 50, 5),
    LEAD("lead", "LEAD",null, 6, 1, 30, 5),
    NICKEL("nickel", "NICKEL",null,4, 10, 25, 5);

    private final String metalNameLower;
    private final String metalNameUpper;
    private Block block;
    private final int veinSize;
    private final int minHeight;
    private final int maxHeight;
    private final int amountPerChunk;

    /**
     * Constructs an OreGenerationEnum with the specified properties.
     *
     * @param metalNameLower  the lowercase metal name
     * @param metalNameUpper  the uppercase metal name
     * @param block           the block associated with the ore
     * @param veinSize        the size of the ore vein
     * @param minHeight       the minimum height for the ore generation
     * @param maxHeight       the maximum height for the ore generation
     * @param amountPerChunk  the amount of ore per chunk
     */
    OreGenerationEnum(String metalNameLower, String metalNameUpper, Block block, int veinSize, int minHeight, int maxHeight, int amountPerChunk) {
        this.veinSize = veinSize;
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
        this.amountPerChunk = amountPerChunk;
        this.metalNameLower = metalNameLower;
        this.metalNameUpper = metalNameUpper;
    }

    /**
     * Returns the vein size of the ore.
     *
     * @return the vein size
     */
    public int getVeinSize() {
        return veinSize;
    }

    /**
     * Returns the minimum height for the ore generation.
     *
     * @return the minimum height
     */
    public int getMinHeight() {
        return minHeight;
    }

    /**
     * Returns the maximum height for the ore generation.
     *
     * @return the maximum height
     */
    public int getMaxHeigth() {
        return maxHeight;
    }

    /**
     * Returns the amount of ore per chunk.
     *
     * @return the amount of ore per chunk
     */
    public int getAmountPerChunk() {
        return amountPerChunk;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getMetalNameLower() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getMetalNameUpper() {
        return null;
    }

    /**
     * Returns the block associated with the ore.
     *
     * @return the ore block
     */
    public Block getBlock() {
        return block;
    }

    /**
     * Sets the block associated with the ore.
     *
     * @param block the ore block
     */
    public void setBlock(Block block) {
        this.block = block;
    }
}
