package net.rudahee.metallics_arts.data.enums.implementations.custom_items;

public enum Shields {

    WOOD("wood_shield", 1000),
    BRONZE_ALUMINUM("bronze_aluminum_shield", 4000);

    private String id;
    private int uses;

    Shields(String id, int uses) {
        this.id = id;
        this.uses = uses;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getUses() {
        return uses;
    }

    public void setUses(int uses) {
        this.uses = uses;
    }
}
