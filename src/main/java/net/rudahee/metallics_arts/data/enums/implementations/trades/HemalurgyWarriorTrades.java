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
public enum HemalurgyWarriorTrades implements ITrade {

    EMERALD_TO_TINPEWTER_RING(1, new ItemStack(Items.EMERALD,48), ItemStack.EMPTY, new ItemStack(MetalMindEnum.TIN_PEWTER.getRing(), 1),2, 5),
    EMERALD_TO_ZINCBRASS_RING(1, new ItemStack(Items.EMERALD,48), ItemStack.EMPTY, new ItemStack(MetalMindEnum.ZINC_BRASS.getRing(), 1),2, 5),
    EMERALD_TO_CHROMIUMNICROSIL_RING(1, new ItemStack(Items.EMERALD,48), ItemStack.EMPTY, new ItemStack(MetalMindEnum.CHROMIUM_NICROSIL.getRing(), 1),2, 5),
    EMERALD_TO_ATIUMMALATIUM_RING(1, new ItemStack(Items.EMERALD,64), ItemStack.EMPTY, new ItemStack(MetalMindEnum.ATIUM_MALTIUM.getRing(), 1),1, 10),
    EMERALD_TO_VIAL(1, new ItemStack(Items.EMERALD,16), ItemStack.EMPTY, new ItemStack(ModItemsRegister.SMALL_VIAL.get(), 1),6, 3),
    MISTCLOAK_TO_EMERALD(2, new ItemStack(ModItemsRegister.MISTCLOACK.get(), 1), ItemStack.EMPTY, new ItemStack(Items.EMERALD, 12),4, 8),
    SILVERKNIFE_TO_EMERALD(2, new ItemStack(ModItemsRegister.SILVER_KNIFE.get(), 1), ItemStack.EMPTY, new ItemStack(Items.EMERALD, 15),3, 6),
    COPPERCOIN_TO_EMERALD(2, new ItemStack(ModItemsRegister.COPPER_COIN.get(), 12), ItemStack.EMPTY, new ItemStack(Items.EMERALD, 12),3, 6),
    BRONZECOIN_TO_EMERALD(2, new ItemStack(ModItemsRegister.BRONZE_COIN.get(), 6), ItemStack.EMPTY, new ItemStack(Items.EMERALD, 12),3, 6),
    IRONSPIKE_TO_EMERALD(3, new ItemStack(SpikeEnum.IRON.getSpike(), 1), ItemStack.EMPTY, new ItemStack(Items.EMERALD, 16),2, 8),
    STEELSPIKE_TO_EMERALD(3, new ItemStack(SpikeEnum.STEEL.getSpike(), 1), ItemStack.EMPTY, new ItemStack(Items.EMERALD, 16),2, 8),
    GOLDSPIKE_TO_EMERALD(3, new ItemStack(SpikeEnum.GOLD.getSpike(), 1), ItemStack.EMPTY, new ItemStack(Items.EMERALD, 16),2, 8),
    ELECTRUMSPIKE_TO_EMERALD(3, new ItemStack(SpikeEnum.ELECTRUM.getSpike(), 1), ItemStack.EMPTY, new ItemStack(Items.EMERALD, 16),2, 8),
    COPPERSPIKE_TO_EMERALD(3, new ItemStack(SpikeEnum.COPPER.getSpike(), 1), ItemStack.EMPTY, new ItemStack(Items.EMERALD, 16),2, 8),
    BRONZESPIKE_TO_EMERALD(3, new ItemStack(SpikeEnum.BRONZE.getSpike(), 1), ItemStack.EMPTY, new ItemStack(Items.EMERALD, 16),2, 8),
    FERUCHEMICATIUMSYMBOL_TO_ATIUM(4, new ItemStack(ModItemsRegister.ITEM_ICONS_FERUCHEMIC_DIVINE.get("atium"), 1), ItemStack.EMPTY, new ItemStack(ModItemsRegister.ITEM_GEMS_BASE.get("atium"), 4),1, 12),
    FERUCHEMICLERASIUMSYMBOL_TO_LERASIUM(4, new ItemStack(ModItemsRegister.ITEM_ICONS_FERUCHEMIC_DIVINE.get("lerasiumn"), 1), ItemStack.EMPTY, new ItemStack(ModItemsRegister.ITEM_GEMS_BASE.get("lerasiumn"), 4),1, 12),
    FERUCHEMICETTMETALSYMBOL_TO_ETTMETAL(4, new ItemStack(ModItemsRegister.ITEM_ICONS_FERUCHEMIC_DIVINE.get("ettmetal"), 1), ItemStack.EMPTY, new ItemStack(ModItemsRegister.ITEM_GEMS_BASE.get("ettmetal"), 4),1, 12),
    FERUCHEMICMALATIUMSYMBOL_TO_MALATIUM(4, new ItemStack(ModItemsRegister.ITEM_ICONS_FERUCHEMIC_DIVINE.get("malatium"), 1), ItemStack.EMPTY, new ItemStack(ModItemsRegister.ITEM_GEMS_BASE.get("malatium"), 4),1, 12),
    ZINCSPIKE_TO_CHARGEDSPIKE(5, new ItemStack(SpikeEnum.ZINC.getSpike(), 1), new ItemStack(Items.EMERALD_BLOCK, 24), new ItemStack(SpikeEnum.ZINC.getSpike(), 1, MetalSpike.addSpikeWithAllomanticPower(MetalTagEnum.ZINC)),1, 24),
    BRASSSPIKE_TO_CHARGEDSPIKE(5, new ItemStack(SpikeEnum.BRASS.getSpike(), 1), new ItemStack(Items.EMERALD_BLOCK, 24), new ItemStack(SpikeEnum.BRASS.getSpike(), 1, MetalSpike.addSpikeWithAllomanticPower(MetalTagEnum.BRASS)),1, 24),
    COPPERSPIKE_TO_CHARGEDSPIKE(5, new ItemStack(SpikeEnum.COPPER.getSpike(), 1), new ItemStack(Items.EMERALD_BLOCK, 24), new ItemStack(SpikeEnum.COPPER.getSpike(), 1, MetalSpike.addSpikeWithAllomanticPower(MetalTagEnum.COPPER)),1, 24),
    BRONZESPIKE_TO_CHARGEDSPIKE(5, new ItemStack(SpikeEnum.BRONZE.getSpike(), 1), new ItemStack(Items.EMERALD_BLOCK, 24), new ItemStack(SpikeEnum.BRONZE.getSpike(), 1, MetalSpike.addSpikeWithAllomanticPower(MetalTagEnum.BRONZE)),1, 24);

    private final int level;
    private final ItemStack primaryInput;
    private final ItemStack secondaryInput;
    private final ItemStack output;
    private final int maxUses;
    private final int xp;
}
