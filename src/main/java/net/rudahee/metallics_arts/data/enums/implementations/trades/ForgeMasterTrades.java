package net.rudahee.metallics_arts.data.enums.implementations.trades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.enums.interfaces.ITrade;
import net.rudahee.metallics_arts.setup.registries.ModBlocksRegister;
import net.rudahee.metallics_arts.setup.registries.ModItemsRegister;

@Getter
@AllArgsConstructor
public enum ForgeMasterTrades implements ITrade {

    EMERALD_TO_IRON(1, new ItemStack(Items.EMERALD,1), ItemStack.EMPTY, new ItemStack(Items.IRON_INGOT,2),5, 1),
    EMERALD_TO_GOLD(1, new ItemStack(Items.EMERALD,1), ItemStack.EMPTY, new ItemStack(Items.GOLD_INGOT,2),5, 1),
    EMERALD_TO_COPPER(1, new ItemStack(Items.EMERALD,1), ItemStack.EMPTY, new ItemStack(Items.COPPER_INGOT,2),5, 1),
    EMERALD_TO_TIN(1, new ItemStack(Items.EMERALD,1), ItemStack.EMPTY, new ItemStack(ModItemsRegister.ITEM_METAL_INGOT.get(MetalTagEnum.TIN.getMetalNameLower()), 2),5, 1),
    EMERALD_TO_ZINC(1, new ItemStack(Items.EMERALD,1), ItemStack.EMPTY, new ItemStack(ModItemsRegister.ITEM_METAL_INGOT.get(MetalTagEnum.ZINC.getMetalNameLower()), 2),5, 1),
    EMERALD_TO_ALUMINUM(1, new ItemStack(Items.EMERALD,1), ItemStack.EMPTY, new ItemStack(ModItemsRegister.ITEM_METAL_INGOT.get(MetalTagEnum.ALUMINUM.getMetalNameLower()), 2),5, 1),
    EMERALD_TO_STEEL (2, new ItemStack(Items.EMERALD,1), ItemStack.EMPTY, new ItemStack(ModItemsRegister.ITEM_METAL_INGOT.get(MetalTagEnum.STEEL.getMetalNameLower()),1),5, 3),
    EMERALD_TO_ELECTRUM (2, new ItemStack(Items.EMERALD,1), ItemStack.EMPTY, new ItemStack(ModItemsRegister.ITEM_METAL_INGOT.get(MetalTagEnum.ELECTRUM.getMetalNameLower()),1),5, 3),
    EMERALD_TO_BRONZE (2, new ItemStack(Items.EMERALD,1), ItemStack.EMPTY, new ItemStack(ModItemsRegister.ITEM_METAL_INGOT.get(MetalTagEnum.BRONZE.getMetalNameLower()),1),5, 3),
    EMERALD_TO_PEWTER (2, new ItemStack(Items.EMERALD,1), ItemStack.EMPTY, new ItemStack(ModItemsRegister.ITEM_METAL_INGOT.get(MetalTagEnum.PEWTER.getMetalNameLower()),1),5, 3),
    EMERALD_TO_BRASS (2, new ItemStack(Items.EMERALD,1), ItemStack.EMPTY, new ItemStack(ModItemsRegister.ITEM_METAL_INGOT.get(MetalTagEnum.BRASS.getMetalNameLower()),1),5, 3),
    EMERALD_TO_DURALUMIN (2, new ItemStack(Items.EMERALD,1), ItemStack.EMPTY, new ItemStack(ModItemsRegister.ITEM_METAL_INGOT.get(MetalTagEnum.DURALUMIN.getMetalNameLower()),1),5, 3),
    NICROSIL_TO_EMERALD(3, new ItemStack(ModItemsRegister.ITEM_METAL_INGOT.get(MetalTagEnum.CHROMIUM.getMetalNameLower()),2), new ItemStack(ModItemsRegister.ITEM_METAL_INGOT.get("nickel"),2), new ItemStack(Items.EMERALD,12),3, 5),
    BENDALLOY_TO_EMERALD(3, new ItemStack(ModItemsRegister.ITEM_METAL_INGOT.get(MetalTagEnum.CADMIUM.getMetalNameLower()),2), new ItemStack(ModItemsRegister.ITEM_METAL_INGOT.get("lead"),2), new ItemStack(Items.EMERALD,10),3, 5),
    MALATIUM_TO_EMERALD(3, new ItemStack(Items.GOLD_INGOT,2), new ItemStack(ModItemsRegister.ITEM_GEMS_BASE.get("atium"),2), new ItemStack(Items.EMERALD,15),3, 5),
    ATIUM_TO_EMERALD(3, new ItemStack(ModItemsRegister.ITEM_GEMS_BASE.get("atium"),6), ItemStack.EMPTY, new ItemStack(Items.EMERALD,6),4, 5),
    LERASIUM_TO_EMERALD(3, new ItemStack(ModItemsRegister.ITEM_GEMS_BASE.get("lerasium"),8), ItemStack.EMPTY, new ItemStack(Items.EMERALD,6),5, 5),
    ETTMETAL_TO_EMERALD(4, new ItemStack(ModItemsRegister.ITEM_GEMS_BASE.get("ettmetal"),4), ItemStack.EMPTY, new ItemStack(Items.EMERALD,6),5, 8),
    ATIUM_TO_CHROMIUM(4, new ItemStack(ModItemsRegister.ITEM_GEMS_BASE.get("atium"),3), new ItemStack(ModItemsRegister.ITEM_METAL_INGOT.get("lead"),2), new ItemStack(ModItemsRegister.ITEM_METAL_INGOT.get(MetalTagEnum.CHROMIUM.getMetalNameLower()),3),5, 8),
    ATIUM_TO_CADMIUM(4, new ItemStack(ModItemsRegister.ITEM_GEMS_BASE.get("atium"),3), new ItemStack(ModItemsRegister.ITEM_METAL_INGOT.get("nickel"),2), new ItemStack(ModItemsRegister.ITEM_METAL_INGOT.get(MetalTagEnum.CADMIUM.getMetalNameLower()),3),5, 8),
    LERASIUM_TO_DIAMOND(4, new ItemStack(ModItemsRegister.ITEM_GEMS_BASE.get("lerasium"),6), new ItemStack(Items.EMERALD, 3), new ItemStack(Items.DIAMOND, 1),5, 8),
    ETTMETAL_TO_ATIUM(4, new ItemStack(ModItemsRegister.ITEM_GEMS_BASE.get("ettmetal"),8), new ItemStack(Items.EMERALD, 2), new ItemStack(ModItemsRegister.ITEM_GEMS_BASE.get("atium"),4),3, 8),
    ETTMETAL_TO_LERASIUM(4, new ItemStack(ModItemsRegister.ITEM_GEMS_BASE.get("ettmetal"),8), new ItemStack(Items.EMERALD, 2), new ItemStack(ModItemsRegister.ITEM_GEMS_BASE.get("lerasium"),4),3, 8),
    ESPECIAL_RIFLE(5, new ItemStack(ModBlocksRegister.BLOCK_METAL_BLOCKS.get(MetalTagEnum.STEEL.getMetalNameLower()),6),new ItemStack(Items.EMERALD, 48), new ItemStack(ModItemsRegister.RIFLE.get()),1,24),
    ESPECIAL_REVOLVER(5,new ItemStack(ModBlocksRegister.BLOCK_METAL_BLOCKS.get(MetalTagEnum.STEEL.getMetalNameLower()),3),new ItemStack(Items.EMERALD, 48), new ItemStack(ModItemsRegister.REVOLVER.get()),1,24),
    ESPECIAL_SHOTGUN(5, new ItemStack(ModBlocksRegister.BLOCK_METAL_BLOCKS.get(MetalTagEnum.STEEL.getMetalNameLower()),4),new ItemStack(Items.EMERALD, 48), new ItemStack(ModItemsRegister.SHOTGUN.get()), 1,24),
    GUNPOWDER_TO_SHOTGUN_BULLET(5, new ItemStack(Items.GUNPOWDER, 3), new ItemStack(ModItemsRegister.ITEM_METAL_INGOT.get("lead"),3), new ItemStack(ModItemsRegister.SHOTGUN_LEAD_BULLET.get(), 6), 3,12),
    GUNPOWDER_TO_RIFLE_BULLET(5, new ItemStack(Items.GUNPOWDER, 4), new ItemStack(ModItemsRegister.ITEM_METAL_INGOT.get("lead"),2), new ItemStack(ModItemsRegister.RIFLE_LEAD_BULLET.get(), 3), 3,12),
    GUNPOWDER_TO_REVOLVER_BULLET(5, new ItemStack(Items.GUNPOWDER, 2), new ItemStack(ModItemsRegister.ITEM_METAL_INGOT.get("lead"),4), new ItemStack(ModItemsRegister.REVOLVER_LEAD_BULLET.get(), 9), 3,12);


    private final int level;
    private final ItemStack primaryInput;
    private final ItemStack secondaryInput;
    private final ItemStack output;
    private final int maxUses;
    private final int xp;
}
