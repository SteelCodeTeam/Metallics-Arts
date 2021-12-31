package net.rudahee.metallics_arts.setup.enums.extras;

import net.rudahee.metallics_arts.setup.enums.gems.IGems;
import net.rudahee.metallics_arts.setup.enums.metals.IMetal;

public enum MetalsNBTData implements IMetal, IGems {
    /* 1s = 20 ticks, 1600 = 80s */
    STEEL("steel", "STEEL", 1, 1600, 6000),
    IRON("iron", "IRON", 1, 1600, 6000),
    TIN("tin", "TIN", 2, 1600, 6000),
    PEWTER("pewter", "PEWTER", 2, 1600, 6000),
    COPPER("copper", "COPPER", 3, 1600, 6000),
    BRONZE("bronze", "BRONZE", 3, 1600, 6000),
    ZINC("zinc", "ZINC", 4, 1600, 6000),
    BRASS("brass", "BRASS", 4, 1600, 6000),
    CHROMIUM("chromium", "CHROMIUM", 5, 1600, 6000),
    NICROSIL("nicrosil", "NICROSIL", 5, 1600, 6000),
    ALUMINUM("aluminum", "ALUMINUM", 6, 1600, 6000),
    DURALUMIN("duralumin", "DURALUMIN", 6, 1600, 6000),
    CADMIUM("cadmium", "CADMIUM", 7, 1600, 6000),
    BENDALLOY("bendalloy", "BENDALLOY", 7, 1600, 6000),
    ELECTRUM("electrum", "ELECTRUM", 8, 1600, 6000),
    GOLD("gold", "GOLD", 8, 1600, 6000),
    ATIUM("atium", "ATIUM", 9, 1600, 6000),
    MALATIUM("malatium", "MALATIUM", 9, 1600, 6000),
    LERASIUM("lerasium", "LERASIUM", 10, 1600, 6000),
    ETTMETAL("ettmetal", "ETTMETAL", 10, 1600, 6000);


    private final String nameLower;
    private final String nameUpper;
    private final int group;
    private final int maxAllomanticTicksStorage;
    private final int maxFeruchemicTicksStorage;


    MetalsNBTData(String nameLower, String nameUpper, int group, int maxAllomanticTicksStorage, int maxFeruchemicTicksStorage) {
        this.nameLower = nameLower;
        this.nameUpper = nameUpper;
        this.group = group;
        this.maxAllomanticTicksStorage = maxAllomanticTicksStorage;
        this.maxFeruchemicTicksStorage = maxFeruchemicTicksStorage;
    }

    public String getNameLower() {
        return nameLower;
    }

    public String getNameUpper() {
        return nameUpper;
    }

    public int getGroup() {
        return group;
    }

    @Override
    public String getGemNameLower() {
        return nameLower;
    }

    @Override
    public String getGemNameUpper() {
        return nameUpper;
    }

    @Override
    public String getMetalNameLower() {
        return null;
    }

    @Override
    public String getMetalNameUpper() {
        return null;
    }

    public int getMaxAllomanticTicksStorage() {
        return maxAllomanticTicksStorage;
    }

    public int getMaxFeruchemicTicksStorage() {
        return maxFeruchemicTicksStorage;
    }
}
