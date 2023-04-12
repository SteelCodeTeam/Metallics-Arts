package net.rudahee.metallics_arts.utils;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.player.IInvestedPlayerData;
import net.rudahee.metallics_arts.modules.logic.server.powers.allomancy.physical_metals.IronAndSteelHelpers;
import net.rudahee.metallics_arts.utils.powers_utils.MetalBlockUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * The FoundNearbyMetalUtils class provides utility methods for handling metal-related objects,
 * such as metal blocks and entities. This class is specifically designed for use on the client side.
 * <p><p>
 * This class includes methods for retrieving sets of metal blocks and metal entities, which can be
 * useful for various purposes, such as rendering, interaction, or client-side logic.
 *
 * @author SteelCode Team
 * @since 1.5.1
 *
 * @deprecated
 */
public class FoundNearbyMetalUtils {

    private static final Set<Entity> metalEntities = new HashSet<>();
    private static List<BlockPos> nearbyAllomancers = new ArrayList<>();
    private static final Set<MetalBlockUtils> metalBlobs = new HashSet<>();

    /**
     * Returns the set of Entity objects representing metal entities.
     * This method should only be called on the client side.
     * <p><p>
     * @return A set of {@link Entity} objects representing metal entities.
     */
    @OnlyIn(Dist.CLIENT)
    public static Set<Entity> getMetalEntities() {
        return metalEntities;
    }

    /**
     * Returns the set of MetalBlockUtils objects representing metal blobs.
     * This method should only be called on the client side.
     * <p><p>
     * @return A set of {@link MetalBlockUtils} objects representing metal blobs.
     */
    @OnlyIn(Dist.CLIENT)
    public static Set<MetalBlockUtils> getMetalBlocks() {
        return metalBlobs;
    }

    /**
     * Repopulates the metal entities and metal blobs lists based on the current state of the player and their surroundings.
     * This method would be changed soon, because it's so complex and inefficient.
     * <p><p>
     * This method should only be called on the client side.
     *
     * @param player The player for whom the lists need to be updated.
     * @param playerCapability The IInvestedPlayerData object containing the player's invested metal data.
     */
    @OnlyIn(Dist.CLIENT)
    public static void redoLists(Player player, IInvestedPlayerData playerCapability) {

        // Populate the metal lists
        metalEntities.clear();
        metalBlobs.clear();
        if (playerCapability.isBurning(MetalTagEnum.IRON) || playerCapability.isBurning(MetalTagEnum.STEEL)) {
            int max = 8;
            BlockPos negative = player.blockPosition().offset(-max, -max, -max);
            BlockPos positive = player.blockPosition().offset(max, max, max);

            // Add metal entities to metal list
            metalEntities.addAll(
                    player.level.getEntitiesOfClass(Entity.class, new AABB(negative, positive), e -> IronAndSteelHelpers.isEntityMetal(e) && !e.equals(player)));


            Stream<BlockPos> blocks = BlockPos.betweenClosedStream(negative, positive);
            blocks.filter(bp -> IronAndSteelHelpers.isBlockStateMetal(player.level.getBlockState(bp))).forEach(bp -> {
                Set<MetalBlockUtils> matches = metalBlobs.stream().filter(mbl -> mbl.isMatch(bp)).collect(Collectors.toSet());
                switch (matches.size()) {
                    case 0: // new blob
                        metalBlobs.add(new MetalBlockUtils(bp));
                        break;
                    case 1: // add to existing blob
                        matches.stream().findAny().get().add(bp);
                        break;
                    default: // this block serves as a bridge between (possibly many) existing blobs
                        metalBlobs.removeAll(matches);
                        MetalBlockUtils mbb = matches.stream().reduce(null, MetalBlockUtils::merge);
                        mbb.add(bp);
                        metalBlobs.add(mbb);
                        break;
                }
            });
        }
    }

    public static List<BlockPos> getNearbyAllomancers() {
        return nearbyAllomancers;
    }

    public static void setNearbyAllomancers(List<BlockPos> nearbyAllomancers) {
        nearbyAllomancers = nearbyAllomancers;
    }


}
