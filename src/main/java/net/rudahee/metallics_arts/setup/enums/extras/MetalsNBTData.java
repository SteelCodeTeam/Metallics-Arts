package net.rudahee.metallics_arts.setup.enums.extras;

import net.rudahee.metallics_arts.setup.enums.gems.IGems;
import net.rudahee.metallics_arts.setup.enums.metals.IMetal;

public enum MetalsNBTData implements IMetal, IGems {
    /* 1s = 20 ticks, 1600 = 80s */
    IRON("iron", "IRON",0, 16000, true, false, 2,5000,1000),
    STEEL("steel", "STEEL",0, 16000, true, false, 3,5000,1000),
    TIN("tin", "TIN",1, 16000, false, false, 2,5000,1000),
    PEWTER("pewter", "PEWTER",1, 16000, false, false, 3,5000,1000),
    ZINC("zinc", "ZINC", 3, 16000, true, false, 1,5000,1000),
    BRASS("brass", "BRASS",3, 16000, true, false, 0,5000,1000),
    COPPER("copper", "COPPER",2, 16000, false, false, 1,5000,1000),
    BRONZE("bronze", "BRONZE",2, 16000, false, false, 0,5000,1000),
    ALUMINUM("aluminum", "ALUMINUM",5, 16000, false, false, 5,2,2),
    DURALUMIN("duralumin", "DURALUMIN",5, 16000, false, false, 4,5000,1000),
    CHROMIUM("chromium", "CHROMIUM",4, 16000, true, false, 5,5000,1000),
    NICROSIL("nicrosil", "NICROSIL",4, 16000, true, false, 4,5000,1000),
    GOLD("gold", "GOLD",7, 16000, false, false, 6,5000,1000),
    ELECTRUM("electrum", "ELECTRUM",7, 16000, false, false, 7,5000,1000),
    ATIUM("atium", "ATIUM", 8,16000, false, true, 0,5000,1000),
    CADMIUM("cadmium", "CADMIUM",6, 16000, true, false, 6,5000,1000),
    BENDALLOY("bendalloy", "BENDALLOY", 6, 16000, true, false, 7,5000,1000),
    MALATIUM("malatium", "MALATIUM",8, 16000, false, true, 1,5000,1000),
    LERASIUM("lerasium", "LERASIUM",9, 16000, false, true, 2,1,1),
    ETTMETAL("ettmetal", "ETTMETAL",9, 16000, false, true, 3,5000,1000);


    private final String nameLower;
    private final String nameUpper;
    private final int group;
    private final int maxAllomanticTicksStorage;
    private final boolean external;
    private final boolean divine;
    private final int order;
    private final int maxReserveBand;
    private final int maxReserveRing;


    MetalsNBTData(String nameLower,String nameUpper,int group,int maxAllomanticTicksStorage,boolean external,boolean divine,int order,int maxReserveBand,int maxReserveRing) {
        this.nameLower = nameLower;
        this.nameUpper = nameUpper;
        this.group = group;
        this.maxAllomanticTicksStorage = maxAllomanticTicksStorage;
        this.external = external;
        this.divine = divine;
        this.order = order;
        this.maxReserveBand = maxReserveBand;
        this.maxReserveRing = maxReserveRing;
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

    public int getMaxReserveBand() {
        return maxReserveBand;
    }

    public int getMaxReserveRing() {
        return maxReserveRing;
    }
}
