package net.rudahee.metallics_arts.data.enums.implementations.custom_items;

public enum ArmorPiecesEnum {
    CHESTPLATE("chestplate"),
    LEGGINGS("leggings"),
    HELMET("helmet"),
    BOOTS("shoes");

    private final String piece;


    ArmorPiecesEnum(String piece) {
        this.piece = piece;
    }

    public String getPiece() {
        return this.piece;
    }

}
