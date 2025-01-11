package net.rudahee.metallics_arts.data.enums.implementations.trades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.enums.implementations.custom_items.MetalMindEnum;
import net.rudahee.metallics_arts.data.enums.implementations.custom_items.SpikeEnum;
import net.rudahee.metallics_arts.data.enums.interfaces.ITrade;
import net.rudahee.metallics_arts.modules.custom_items.metal_spikes.MetalSpike;
import net.rudahee.metallics_arts.setup.registries.ModItemsRegister;

@Getter
@AllArgsConstructor
public enum HemalurgyMonkTrades implements ITrade {

    EMERALD_TO_IRONSTEEL_RING(1, new ItemStack(Items.EMERALD,48), ItemStack.EMPTY, new ItemStack(MetalMindEnum.IRON_STEEL.getRing(), 1),2, 5),
    EMERALD_TO_COPPERBRONZE_RING(1, new ItemStack(Items.EMERALD,48), ItemStack.EMPTY, new ItemStack(MetalMindEnum.COPPER_BRONZE.getRing(), 1),2, 5),
    EMERALD_TO_ALUMINUMDURALUMIN_RING(1, new ItemStack(Items.EMERALD,48), ItemStack.EMPTY, new ItemStack(MetalMindEnum.ALUMINUM_DURALUMIN.getRing(), 1),2, 5),
    EMERALD_TO_LERASIUMETTMETAL_RING(1, new ItemStack(Items.EMERALD,64), ItemStack.EMPTY, new ItemStack(MetalMindEnum.LERASIUM_ETTMETAL.getRing(), 1),1, 10),
    EMERALD_TO_VIAL(1, new ItemStack(Items.EMERALD,16), ItemStack.EMPTY, new ItemStack(ModItemsRegister.SMALL_VIAL.get(), 1),6, 3),
    MISTCLOAK_TO_EMERALD(2, new ItemStack(ModItemsRegister.MISTCLOACK.get(), 1), ItemStack.EMPTY, new ItemStack(Items.EMERALD, 12),4, 8),
    SILVERKNIFE_TO_EMERALD(2, new ItemStack(ModItemsRegister.SILVER_KNIFE.get(), 1), ItemStack.EMPTY, new ItemStack(Items.EMERALD, 15),3, 6),
    COPPERCOIN_TO_EMERALD(2, new ItemStack(ModItemsRegister.COPPER_COIN.get(), 12), ItemStack.EMPTY, new ItemStack(Items.EMERALD, 12),3, 6),
    BRONZECOIN_TO_EMERALD(2, new ItemStack(ModItemsRegister.BRONZE_COIN.get(), 6), ItemStack.EMPTY, new ItemStack(Items.EMERALD, 12),3, 6),
    BENDALLOYSPIKE_TO_EMERALD(3, new ItemStack(SpikeEnum.BENDALLOY.getSpike(), 1), ItemStack.EMPTY, new ItemStack(Items.EMERALD, 16),2, 8),
    CADMIUMSPIKE_TO_EMERALD(3, new ItemStack(SpikeEnum.CADMIUM.getSpike(), 1), ItemStack.EMPTY, new ItemStack(Items.EMERALD, 16),2, 8),
    TINSPIKE_TO_EMERALD(3, new ItemStack(SpikeEnum.TIN.getSpike(), 1), ItemStack.EMPTY, new ItemStack(Items.EMERALD, 16),2, 8),
    PEWTERSPIKE_TO_EMERALD(3, new ItemStack(SpikeEnum.PEWTER.getSpike(), 1), ItemStack.EMPTY, new ItemStack(Items.EMERALD, 16),2, 8),
    ZINCSPIKE_TO_EMERALD(3, new ItemStack(SpikeEnum.ZINC.getSpike(), 1), ItemStack.EMPTY, new ItemStack(Items.EMERALD, 16),2, 8),
    BRASSSPIKE_TO_EMERALD(3, new ItemStack(SpikeEnum.BRASS.getSpike(), 1), ItemStack.EMPTY, new ItemStack(Items.EMERALD, 16),2, 8),
    ALLOMANTICATIUMSYMBOL_TO_ATIUM(4, new ItemStack(ModItemsRegister.ITEM_ICONS_ALLOMANCY_DIVINE.get("atium"), 1), ItemStack.EMPTY, new ItemStack(ModItemsRegister.ITEM_GEMS_BASE.get("atium"), 4),1, 12),
    ALLOMANTICLERASIUMSYMBOL_TO_LERASIUM(4, new ItemStack(ModItemsRegister.ITEM_ICONS_ALLOMANCY_DIVINE.get("lerasiumn"), 1), ItemStack.EMPTY, new ItemStack(ModItemsRegister.ITEM_GEMS_BASE.get("lerasiumn"), 4),1, 12),
    ALLOMANTICETTMETALSYMBOL_TO_ETTMETAL(4, new ItemStack(ModItemsRegister.ITEM_ICONS_ALLOMANCY_DIVINE.get("ettmetal"), 1), ItemStack.EMPTY, new ItemStack(ModItemsRegister.ITEM_GEMS_BASE.get("ettmetal"), 4),1, 12),
    ALLOMANTICMALATIUMSYMBOL_TO_MALATIUM(4, new ItemStack(ModItemsRegister.ITEM_ICONS_ALLOMANCY_DIVINE.get("malatium"), 1), ItemStack.EMPTY, new ItemStack(ModItemsRegister.ITEM_GEMS_BASE.get("malatium"), 4),1, 12),
    ZINCSPIKE_TO_CHARGEDSPIKE(5, new ItemStack(SpikeEnum.ZINC.getSpike(), 1), new ItemStack(Items.EMERALD_BLOCK, 24), new ItemStack(SpikeEnum.ZINC.getSpike(), 1, MetalSpike.addSpikeWithFeruchemicalPower(MetalTagEnum.ZINC)),1, 24),
    BRASSSPIKE_TO_CHARGEDSPIKE(5, new ItemStack(SpikeEnum.BRASS.getSpike(), 1), new ItemStack(Items.EMERALD_BLOCK, 24), new ItemStack(SpikeEnum.BRASS.getSpike(), 1, MetalSpike.addSpikeWithFeruchemicalPower(MetalTagEnum.BRASS)),1, 24),
    COPPERSPIKE_TO_CHARGEDSPIKE(5, new ItemStack(SpikeEnum.COPPER.getSpike(), 1), new ItemStack(Items.EMERALD_BLOCK, 24), new ItemStack(SpikeEnum.COPPER.getSpike(), 1, MetalSpike.addSpikeWithFeruchemicalPower(MetalTagEnum.COPPER)),1, 24),
    BRONZESPIKE_TO_CHARGEDSPIKE(5, new ItemStack(SpikeEnum.BRONZE.getSpike(), 1), new ItemStack(Items.EMERALD_BLOCK, 24), new ItemStack(SpikeEnum.BRONZE.getSpike(), 1, MetalSpike.addSpikeWithFeruchemicalPower(MetalTagEnum.BRONZE)),1, 24);


    private final int level;
    private final ItemStack primaryInput;
    private final ItemStack secondaryInput;
    private final ItemStack output;
    private final int maxUses;
    private final int xp;
}
