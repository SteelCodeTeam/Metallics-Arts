package net.rudahee.metallics_arts.setup.enums.extras;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.rudahee.metallics_arts.setup.registries.ModItems;

public enum MetalMindData {
    STEEL_IRON("steel", "iron",null,null),
    TIN_PEWTER("tin", "pewter",null,null),
    COPPER_BRONZE("copper", "bronze",null,null),
    ZINC_BRASS("zinc", "brass",null,null),
    CHROMIUM_NICROSIL("chromium", "nicrosil",null,null),
    ALUMINUM_DURALUMIN("aluminum", "duralumin",null,null),
    CADMIUM_BENDALLOY("cadmium", "bendalloy",null,null),
    ELECTRUM_GOLD("electrum", "gold",null,null),
    ATIUM_MALTIUM("atium", "malatium",null,null),
    LERASIUM_ETTMETAL("lerasium", "ettmetal",null,null);
            //ModItems.BandLerasiumEttmetal.get(),ModItems.RingLerasiumEttmetal.get());

    private final String firstMetal;
    private final String secondMetal;
    private Item band;
    private Item ring;

    MetalMindData(String firstMetal, String secondMetal, Item band, Item ring) {
        this.firstMetal = firstMetal;
        this.secondMetal = secondMetal;
        this.band = band;
        this.ring = ring;
    }

    public String getFirstMetal() {
        return firstMetal;
    }

    public String getSecondMetal() {
        return secondMetal;
    }

    public Item getBand() {
        return band;
    }

    public Item getRing() {
        return ring;
    }

    public void setBand(Item band){
        this.band = band;
    }

    public void setRing(Item ring){
        this.ring = ring;
    }
}
