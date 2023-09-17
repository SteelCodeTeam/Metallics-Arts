package net.rudahee.metallics_arts.modules.logic.server.server_events;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.player.data.IInvestedPlayerData;
import net.rudahee.metallics_arts.modules.error_handling.exceptions.PlayerException;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.cognitive_metals.BrassFeruchemicHelper;
import net.rudahee.metallics_arts.utils.CapabilityUtils;

public class OnAttackBlockEvent {
    public static void hitBlock(Player player, BlockPos blockPos) {
        try {
            IInvestedPlayerData playerCapability = CapabilityUtils.getCapability(player);
            if (playerCapability.isBurning(MetalTagEnum.BRASS) && playerCapability.isTapping(MetalTagEnum.BRASS)) {

                BrassFeruchemicHelper.compoundingBrass(player, blockPos);
            }

        } catch (PlayerException ex) {
            ex.printCompleteLog();
        }
    }
}
