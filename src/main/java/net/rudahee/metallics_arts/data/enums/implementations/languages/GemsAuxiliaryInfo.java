package net.rudahee.metallics_arts.data.enums.implementations.languages;

import net.rudahee.metallics_arts.data.enums.interfaces.ILanguage;

public enum GemsAuxiliaryInfo {
    ATIUM("atium",false),
    MALATIUM("malatium", true),
    LERASIUM("lerasium",false),
    ETTMETAL("ettmetal",false);

    private final String id;
    private final boolean isAlloy;

    GemsAuxiliaryInfo(String id, boolean isAlloy) {
        this.id = id;
        this.isAlloy = isAlloy;

    }

    public String getId() {
        return this.id;
    }

    public boolean isAlloy() {
        return isAlloy;
    }



}
