package net.rudahee.metallics_arts.modules.custom_items.metal_minds.bands;


import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.modules.custom_items.metal_minds.BandMindAbstract;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.AtiumFecuchemicHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.BrassFecuchemicHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.MalatiumFecuchemicHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.ZincFecuchemicHelper;
import net.rudahee.metallics_arts.setup.network.ModNetwork;
import net.rudahee.metallics_arts.setup.registries.ModBlocksRegister;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;


public class BandZincBrass extends BandMindAbstract <ZincFecuchemicHelper, BrassFecuchemicHelper>{
    public BandZincBrass (Item.Properties properties){
        super(properties, MetalTagEnum.ZINC, MetalTagEnum.BRASS, ZincFecuchemicHelper.getInstance(), BrassFecuchemicHelper.getInstance());
    }
   }