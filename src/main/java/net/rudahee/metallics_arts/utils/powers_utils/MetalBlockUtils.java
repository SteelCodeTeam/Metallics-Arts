package net.rudahee.metallics_arts.utils.powers_utils;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.HashSet;
import java.util.Set;

/**
 * The MetalBlockUtils class provides utility functions for working with
 * metallic blocks in a Minecraft world.
 *
 * It allows for adding block positions and calculating the center of the
 * group of blocks, which can be useful for various applications such as
 * finding the center of gravity for metallic structures or detecting
 * the focal point of a group of metal blocks.
 *
 * Example usage:
 * <pre>
 * {@code
 * MetalBlockUtils metalBlockUtils = new MetalBlockUtils();
 * metalBlockUtils.add(blockPos1);
 * metalBlockUtils.add(blockPos2);
 * Vec3 center = metalBlockUtils.getCenter();
 * }
 * </pre>
 *
 * @author SteelCode Team
 * @since 1.5.1
 */
public class MetalBlockUtils {

    private static final Level level = Minecraft.getInstance().level;
    private final Set<BlockPos> blocks = new HashSet<>();
    private Vec3 center = null;

    /**
     * Constructor for the MetalBlockUtils class, which provides utility methods for working with metal blocks in Minecraft.
     *
     * This constructor initializes the MetalBlockUtils object with an initial block position and adds it to an internal collection.
     * Additional block positions can be added later using utility methods.
     *
     * @param initial The BlockPos object representing the initial metal block position.
     */
    public MetalBlockUtils(BlockPos initial) {
        this.add(initial);
    }

    /**
     * Default constructor for the MetalBlockUtils class, which provides utility methods for working with metal blocks in Minecraft.
     *
     * This constructor initializes the MetalBlockUtils object with an empty internal collection to store block positions.
     * Block positions can be added later using utility methods.
     */
    public MetalBlockUtils() {

    }

    /**
     * Merges two MetalBlockUtils instances into a new one, combining their block collections and recalculating the center.
     *
     * If one of the instances is null, the other instance is returned. If both instances are null, the result will be null as well.
     *
     * @param blob1 The first MetalBlockUtils instance to merge.
     * @param blob2 The second MetalBlockUtils instance to merge.
     *
     * @return A new MetalBlockUtils instance containing the merged blocks from both input instances and a recalculated center.
     */
    public static MetalBlockUtils merge(MetalBlockUtils blob1, MetalBlockUtils blob2) {
        if (blob1 == null) {
            return blob2;
        } else if (blob2 == null) {
            return blob1;
        }

        MetalBlockUtils blob3 = new MetalBlockUtils();
        blob3.blocks.addAll(blob1.blocks);
        blob3.blocks.addAll(blob2.blocks);
        blob3.center = blob1.center.scale(blob1.blocks.size()).add(blob2.center.scale(blob2.blocks.size())).scale(1.0 / blob3.blocks.size());
        return blob3;
    }

    /**
     * Checks if the given BlockPos is a match for any BlockPos in this MetalBlockUtils instance.
     *
     * A match is defined as having a distance of 1.5 or less between the centers of the two block positions.
     *
     * @param pos The BlockPos to check for a match.
     *
     * @return True if there is a match, false otherwise.
     */
    public boolean isMatch(BlockPos pos) {
        return this.blocks.stream().anyMatch(bp -> Vec3.atCenterOf(bp).distanceTo(Vec3.atCenterOf(pos)) <= 1.5);
    }

    /**
     * Returns the number of blocks in this MetalBlockUtils instance.
     *
     * The method calculates the size by checking the size of the internal
     * block collection.
     *
     * @return The number of blocks in the MetalBlockUtils instance.
     */
    public int size() {
        return this.blocks.size();
    }


    /**
     * Calculates the center of the given BlockPos, considering the block's shape
     * if available. If the block shape is not available, the center is calculated
     * based on the block's position.
     *
     * @param pos The BlockPos to calculate the center of.
     *
     * @return A Vec3 object representing the center of the block.
     */
    private Vec3 getCenterOfBlock(BlockPos pos) {
        try {
            return Vec3.atLowerCornerOf(pos).add(level.getBlockState(pos).getShape(level, pos).bounds().getCenter());
        } catch (UnsupportedOperationException e) {
            return Vec3.atCenterOf(pos);
        }
    }

    /**
     * Adds a BlockPos to the internal collection of blocks and updates the center
     * of the MetalBlockUtils instance accordingly.
     *
     * The method ensures that the BlockPos is immutable before adding it to the
     * collection. If the block is successfully added, the center of the instance
     * is updated, either by using the center of the block (if it's the first block)
     * or by calculating the average center of all blocks.
     *
     * @param pos The BlockPos to be added.
     *
     * @return True if the block was successfully added, false otherwise.
     */
    public boolean add(BlockPos pos) {
        pos = pos.immutable();

        if (this.blocks.add(pos)) {

            if (this.center == null) {
                this.center = getCenterOfBlock(pos);
            } else {
                int count = this.blocks.size();
                this.center = this.center.scale(count - 1).add(getCenterOfBlock(pos)).scale(1.0D / count);

            }
            return true;
        }
        return false;
    }

    /**
     * Returns the center of the MetalBlockUtils instance.
     *
     * The method returns the center stored in the internal Vec3 object.
     *
     * @return A Vec3 object representing the center of the MetalBlockUtils instance.
     */
    public Vec3 getCenter() {
        return this.center;
    }

}
