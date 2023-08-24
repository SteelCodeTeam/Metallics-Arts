package net.rudahee.metallics_arts.data.enums.implementations;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.rudahee.metallics_arts.setup.registries.ModBlocksRegister;
import net.rudahee.metallics_arts.setup.registries.ModItemsRegister;

public enum ForgeMasterTrades {
    T1 (1,new ItemStack(Items.EMERALD,5),new ItemStack(ModItemsRegister.ITEM_METAL_INGOT.get(MetalTagEnum.IRON.getMetalNameLower()),1),null,5,100),
    T2 (1,new ItemStack(Items.EMERALD,5),new ItemStack(ModItemsRegister.ITEM_METAL_INGOT.get(MetalTagEnum.TIN.getMetalNameLower()),1),null,5,100),
    T3 (1,new ItemStack(Items.EMERALD,5),new ItemStack(ModItemsRegister.ITEM_METAL_INGOT.get(MetalTagEnum.ZINC.getMetalNameLower()),1),null,5,100),
    T4 (4,new ItemStack(Items.EMERALD,5),new ItemStack(ModItemsRegister.ITEM_METAL_INGOT.get(MetalTagEnum.ALUMINUM.getMetalNameLower()),1),null,5,100),
    T5 (4,new ItemStack(Items.EMERALD,5),new ItemStack(ModItemsRegister.ITEM_METAL_INGOT.get(MetalTagEnum.CADMIUM.getMetalNameLower()),1),null,5,100),
    T6 (4,new ItemStack(Items.EMERALD,5),new ItemStack(ModItemsRegister.ITEM_METAL_INGOT.get(MetalTagEnum.CHROMIUM.getMetalNameLower()),1),null,5,100),
    T7 (1,new ItemStack(Items.EMERALD,5),new ItemStack(ModItemsRegister.ITEM_METAL_INGOT.get(MetalTagEnum.COPPER.getMetalNameLower()),1),null,5,100),
    T8 (1,new ItemStack(Items.EMERALD,5),new ItemStack(ModItemsRegister.ITEM_METAL_INGOT.get(MetalTagEnum.GOLD.getMetalNameLower()),1),null,5,100),
    T9 (1,new ItemStack(Items.EMERALD,5),new ItemStack(ModItemsRegister.ITEM_GEMS_BASE.get(MetalTagEnum.ATIUM.getMetalNameLower()),1),null,5,100),
    TLOL (4,new ItemStack(Items.EMERALD,5),new ItemStack(ModItemsRegister.ITEM_GEMS_BASE.get(MetalTagEnum.LERASIUM.getMetalNameLower()),1),null,5,100),
    T39 (4,new ItemStack(Items.EMERALD,5),new ItemStack(ModItemsRegister.ITEM_GEMS_BASE.get(MetalTagEnum.ETTMETAL.getMetalNameLower()),1),null,5,100),
    T10 (2,new ItemStack(Items.EMERALD,5),new ItemStack(ModItemsRegister.ITEM_METAL_INGOT.get(MetalTagEnum.PEWTER.getMetalNameLower()),1),null,5,100),
    T11 (2,new ItemStack(Items.EMERALD,5),new ItemStack(ModItemsRegister.ITEM_METAL_INGOT.get(MetalTagEnum.STEEL.getMetalNameLower()),1),null,5,100),
    T12 (3,new ItemStack(Items.EMERALD,5),new ItemStack(ModItemsRegister.ITEM_METAL_INGOT.get(MetalTagEnum.BRASS.getMetalNameLower()),1),null,5,100),
    T13 (3,new ItemStack(Items.EMERALD,5),new ItemStack(ModItemsRegister.ITEM_METAL_INGOT.get(MetalTagEnum.BRONZE.getMetalNameLower()),1),null,5,100),
    T14 (2,new ItemStack(Items.EMERALD,5),new ItemStack(ModItemsRegister.ITEM_METAL_INGOT.get(MetalTagEnum.BENDALLOY.getMetalNameLower()),1),null,5,100),
    T15 (3,new ItemStack(Items.EMERALD,5),new ItemStack(ModItemsRegister.ITEM_METAL_INGOT.get(MetalTagEnum.ELECTRUM.getMetalNameLower()),1),null,5,100),
    T16 (2,new ItemStack(Items.EMERALD,5),new ItemStack(ModItemsRegister.ITEM_METAL_INGOT.get(MetalTagEnum.DURALUMIN.getMetalNameLower()),1),null,5,100),
    T17 (3,new ItemStack(Items.EMERALD,5),new ItemStack(ModItemsRegister.ITEM_METAL_INGOT.get(MetalTagEnum.NICROSIL.getMetalNameLower()),1),null,5,100),
    T18 (3,new ItemStack(Items.EMERALD,5),new ItemStack(ModItemsRegister.ITEM_GEMS_BASE.get(MetalTagEnum.MALATIUM.getMetalNameLower()),1),null,5,100),
    T19 (5,new ItemStack(ModItemsRegister.RIFLE.get()),new ItemStack(ModBlocksRegister.BLOCK_METAL_BLOCKS.get(MetalTagEnum.STEEL.getMetalNameLower()),5),new ItemStack(Items.EMERALD, 25) ,5,100),
    T20 (5,new ItemStack(ModItemsRegister.REVOLVER.get()),new ItemStack(ModBlocksRegister.BLOCK_METAL_BLOCKS.get(MetalTagEnum.STEEL.getMetalNameLower()),5),new ItemStack(Items.EMERALD, 25) ,5,100),
    T21 (5,new ItemStack(ModItemsRegister.SHOTGUN.get()),new ItemStack(ModBlocksRegister.BLOCK_METAL_BLOCKS.get(MetalTagEnum.STEEL.getMetalNameLower()),5),new ItemStack(Items.EMERALD, 25) ,5,100),


    ;
    private int level;
    private ItemStack output;
    private ItemStack input;
    private ItemStack optionalSecondInput;
    private int maxqty;
    private int xp;

    ForgeMasterTrades(int level, ItemStack output, ItemStack input, ItemStack optionalSecondInput, int maxqty, int xp) {
        this.level = level;
        this.output = output;
        this.input = input;
        this.optionalSecondInput = optionalSecondInput;
        this.maxqty = maxqty;
        this.xp = xp;
    }

    public int getLevel() {
        return level;
    }

    public ItemStack getOutput() {
        return output;
    }

    public ItemStack getInput() {
        return input;
    }

    public int getMaxqty() {
        return maxqty;
    }

    public int getXp() {
        return xp;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setOutput(ItemStack output) {
        this.output = output;
    }

    public void setInput(ItemStack input) {
        this.input = input;
    }

    public void setMaxqty(int maxqty) {
        this.maxqty = maxqty;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }
}
