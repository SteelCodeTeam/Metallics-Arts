package net.rudahee.metallics_arts.data.enums.implementations;

import net.rudahee.metallics_arts.data.enums.interfaces.gems.IGems;
import net.rudahee.metallics_arts.data.enums.interfaces.metals.IMetal;

public enum MetalsNBTData implements IMetal, IGems {
    /* 1s = 20 ticks, 1600 = 80s */
    IRON("iron", "IRON",0, 6000, true, false, 2,9600,3000),
    STEEL("steel", "STEEL",0, 6000, true, false, 3,9600,3000),
    TIN("tin", "TIN",1, 24000, false, false, 2,24000,7200),
    PEWTER("pewter", "PEWTER",1, 4000, false, false, 3,6000,1200),
    ZINC("zinc", "ZINC", 3, 6000, true, false, 1,6000,2400),
    BRASS("brass", "BRASS",3, 6000, true, false, 0,6000,2800),
    COPPER("copper", "COPPER",2, 8400, false, false, 1,5345,315),
    BRONZE("bronze", "BRONZE",2, 8400, false, false, 0,7200,2200),
    ALUMINUM("aluminum", "ALUMINUM",5, 10, false, false, 5,4,4),
    DURALUMIN("duralumin", "DURALUMIN",5, 100, false, false, 4,12000,4000),
    CHROMIUM("chromium", "CHROMIUM",4, 400, true, false, 5,6000,2400),
    NICROSIL("nicrosil", "NICROSIL",4, 400, true, false, 4,9600,3000),
    GOLD("gold", "GOLD",7, 12000, false, false, 6,6000,1000),
    ELECTRUM("electrum", "ELECTRUM",7, 12000, false, false, 7,4800,1200),
    CADMIUM("cadmium", "CADMIUM",6, 2400, true, false, 6,12000,4000),
    BENDALLOY("bendalloy", "BENDALLOY", 6, 1800, true, false, 7,9600,3000),
    ATIUM("atium", "ATIUM", 8,800, false, true, 0,2400,600),
    MALATIUM("malatium", "MALATIUM",8, 12000, false, true, 1,3000,500),
    LERASIUM("lerasium", "LERASIUM",9, 6000, false, true, 2,1,1),
    ETTMETAL("ettmetal", "ETTMETAL",9, 100, false, true, 3,4100,1640);

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
