package net.rudahee.metallics_arts.modules.custom_items.metal_minds.rings;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.modules.custom_items.metal_minds.RingsMindAbstract;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.BrassFecuchemicHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.PewterFeruchemicHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.TinFeruchemicHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.ZincFecuchemicHelper;
import net.rudahee.metallics_arts.setup.network.ModNetwork;
import net.rudahee.metallics_arts.setup.registries.ModBlocksRegister;
import top.theillusivec4.curios.api.SlotContext;

public class RingZincBrass extends RingsMindAbstract {
    public RingZincBrass (Properties properties){
        super(properties, MetalTagEnum.ZINC, MetalTagEnum.BRASS, ZincFecuchemicHelper.getInstance(), BrassFecuchemicHelper.getInstance());
    }
}