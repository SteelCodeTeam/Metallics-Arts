package net.rudahee.metallics_arts.data.enums.implementations.languages.book.weapons;

import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.data.enums.implementations.languages.CTW;

public enum WeaponsData {
    OBSIDIAN_DAGGER(CTW.OBSIDIAN_DAGGER.getId(),MetallicsArts.MOD_ID + "_obsidian_dagger",MetallicsArts.MOD_ID + ":textures/item/combat/obsidian_dagger.png"),
    CRISTAL_DAGGER(CTW.CRISTAL_DAGGER.getId(),MetallicsArts.MOD_ID + "_cristal_dagger",MetallicsArts.MOD_ID + ":textures/item/combat/cristal_dagger.png"),
    OBSIDIAN_AXE(CTW.OBSIDIAN_AXE.getId(),MetallicsArts.MOD_ID + "_obsidian_axe",MetallicsArts.MOD_ID + ":textures/item/combat/obsidian_axe.png"),
    KOLOSS_BLADE(CTW.KOLOSS_BLADE.getId(),MetallicsArts.MOD_ID + "_koloss_blade",MetallicsArts.MOD_ID + ":textures/item/combat/koloss_blade.png"),
    DUELING_STAFF(CTW.DUELING_STAFF.getId(),MetallicsArts.MOD_ID + "_dueling_staff",MetallicsArts.MOD_ID + ":textures/item/combat/dueling_staff.png");


    private final String id;
    private final String recipe;
    private final String icon;

    WeaponsData(String id, String recipe, String icon) {
        this.id = id;
        this.recipe = recipe;
        this.icon = icon;
    }

    public String getIcon() {
        return icon;
    }

    public String getId() {
        return id;
    }

    public String getRecipe() {
        return recipe;
    }
}
