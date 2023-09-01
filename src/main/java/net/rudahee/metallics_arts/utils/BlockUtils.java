package net.rudahee.metallics_arts.utils;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.phys.AABB;

public class BlockUtils {

    /**
     * Auxiliary method to check if in the area exists water in specific world.
     *
     * @param level to check.
     * @param area to check.
     *
     * @return boolean
     *
     * @see AABB
     */
    public static boolean isTouchingWater(ServerLevel level, AABB area) {
        boolean isTouchingWater = false;


        for (int x = (int) area.minX; x <= area.maxX; x++) {
            for (int y = (int) area.minY; y <= area.maxY; y++) {
                for (int z = (int) area.minZ; z <= area.maxZ; z++) {

                    if (level.getFluidState(new BlockPos(x,y,z)).is(FluidTags.WATER)) {
                        isTouchingWater = true;
                    }
                }
            }
        }
        return isTouchingWater;
    }
}
