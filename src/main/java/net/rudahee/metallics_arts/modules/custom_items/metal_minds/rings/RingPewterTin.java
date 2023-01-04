package net.rudahee.metallics_arts.modules.custom_items.metal_minds.rings;

import net.minecraft.world.entity.player.Player;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.players.IInvestedPlayerData;
import net.rudahee.metallics_arts.modules.custom_items.metal_minds.RingsMindAbstract;
import net.rudahee.metallics_arts.modules.logic.server.powers.PewterAndTinHelpers;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.PewterFeruchemicHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.TinFeruchemicHelper;

import java.util.function.Supplier;

public class RingPewterTin extends RingsMindAbstract<TinFeruchemicHelper, PewterFeruchemicHelper> {
    public RingPewterTin(Properties properties){
        super(properties, MetalTagEnum.TIN, MetalTagEnum.PEWTER,TinFeruchemicHelper.getInstance(), PewterFeruchemicHelper.getInstance());
    }

}
