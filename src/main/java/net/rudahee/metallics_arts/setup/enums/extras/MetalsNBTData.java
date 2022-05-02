package net.rudahee.metallics_arts.setup.enums.extras;

import net.rudahee.metallics_arts.setup.enums.gems.IGems;
import net.rudahee.metallics_arts.setup.enums.metals.IMetal;

public enum MetalsNBTData implements IMetal, IGems {
    /* 1s = 20 ticks, 1600 = 80s */
    STEEL("steel", "STEEL", 1, 1600, true, false, 5),
    IRON("iron", "IRON", 1, 1600, true, false, 6),
    TIN("tin", "TIN", 2, 1600, false, false, 5),
    PEWTER("pewter", "PEWTER", 2, 1600, false, false, 4),
    COPPER("copper", "COPPER", 3, 16000, true, false, 6),

    BRONZE("bronze", "BRONZE", 3, 16000, true, false, 7),
    ZINC("zinc", "ZINC", 4, 1600, false, false, 6),
    BRASS("brass", "BRASS", 4, 1600, false, false, 7),
    CHROMIUM("chromium", "CHROMIUM", 5, 1600, true, false, 3),
    NICROSIL("nicrosil", "NICROSIL", 5, 1600, true, false, 4),
    ALUMINUM("aluminum", "ALUMINUM", 6, 1600, false, false, 2),
    DURALUMIN("duralumin", "DURALUMIN", 6, 1600, false, false, 3),
    CADMIUM("cadmium", "CADMIUM", 7, 1600, true, false, 1),
    BENDALLOY("bendalloy", "BENDALLOY", 7, 1600, true, false, 0),
    ELECTRUM("electrum", "ELECTRUM", 8, 1600, false, false, 0),
    GOLD("gold", "GOLD", 8, 1600, false, false, 1),
    ATIUM("atium", "ATIUM", 9, 1600, false, true, 0),
    MALATIUM("malatium", "MALATIUM", 9, 1600, false, true, 1),
    LERASIUM("lerasium", "LERASIUM", 10, 1600, false, true, 2),
    ETTMETAL("ettmetal", "ETTMETAL", 10, 1600, false, true, 3);


    private final String nameLower;
    private final String nameUpper;
    private final int group;
    private final int maxAllomanticTicksStorage;
    private final boolean external;
    private final boolean divine;
    private final int order;


    MetalsNBTData(String nameLower, String nameUpper, int group, int maxAllomanticTicksStorage, boolean external, boolean divine, int order) {
        this.nameLower = nameLower;
        this.nameUpper = nameUpper;
        this.group = group;
        this.maxAllomanticTicksStorage = maxAllomanticTicksStorage;
        this.external = external;
        this.divine = divine;
        this.order = order;
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

    public int getIndex() {
        return ordinal();
    }

    public static MetalsNBTData getMetal(int index) {
        for (MetalsNBTData metal : values()) {
            if (metal.getIndex() == index) {
                return metal;
            }
        }
        throw new IllegalArgumentException("Allomancy: Bad Metal Index");
    }

    public boolean isDivine() {
        return divine;
    }

    public boolean isExternal() {
        return external;
    }

    public int getOrder() {
        return order;
    }
}
