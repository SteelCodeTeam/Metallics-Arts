package net.rudahee.metallics_arts.data.enums.implementations.languages.book;

import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.data.enums.implementations.languages.CTW;

public enum WeaponsEntry {
    OBSIDIAN_DAGGER(CTW.OBSIDIAN_DAGGER.getId(), CTW.OBSIDIAN_DAGGER.getNameInEnglish(),MetallicsArts.MOD_ID + "_obsidian_dagger",MetallicsArts.MOD_ID + ":textures/item/combat/obsidian_dagger.png",""),
    CRISTAL_DAGGER(CTW.CRISTAL_DAGGER.getId(), CTW.CRISTAL_DAGGER.getNameInEnglish(),MetallicsArts.MOD_ID + "_cristal_dagger",MetallicsArts.MOD_ID + ":textures/item/combat/cristal_dagger.png",""),
    OBSIDIAN_AXE(CTW.OBSIDIAN_AXE.getId(), CTW.OBSIDIAN_AXE.getNameInEnglish(),MetallicsArts.MOD_ID + "_obsidian_axe",MetallicsArts.MOD_ID + ":textures/item/combat/obsidian_axe.png",""),
    KOLOSS_BLADE(CTW.KOLOSS_BLADE.getId(), CTW.KOLOSS_BLADE.getNameInEnglish(),MetallicsArts.MOD_ID + "_koloss_blade",MetallicsArts.MOD_ID + ":textures/item/combat/koloss_blade.png",""),
    DUELING_STAFF(CTW.DUELING_STAFF.getId(), CTW.DUELING_STAFF.getNameInEnglish(),MetallicsArts.MOD_ID + "_dueling_staff",MetallicsArts.MOD_ID + ":textures/item/combat/dueling_staff.png","");


    private final String id;
    private final String title;
    private final String presentation;
    private final String recipe;
    private final String icon;

    WeaponsEntry(String id, String title, String recipe, String icon, String presentation) {
        this.id = id;
        this.title = title;
        this.presentation = presentation;
        this.recipe = recipe;
        this.icon = icon;
    }

    public String getIcon() {
        return icon;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getPresentation() {
        return presentation;
    }

    public String getRecipe() {
        return recipe;
    }
}
