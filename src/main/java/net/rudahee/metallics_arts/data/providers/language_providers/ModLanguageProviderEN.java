package net.rudahee.metallics_arts.data.providers.language_providers;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.data.enums.implementations.languages.*;

public class ModLanguageProviderEN extends LanguageProvider {

    public ModLanguageProviderEN(DataGenerator gen, String locale) {
        super(gen.getPackOutput(), MetallicsArts.MOD_ID, locale);
    }

    @Override
    protected void addTranslations() {
        for (BasicMetalItemsTranslation item: BasicMetalItemsTranslation.values()) {
            add(item.getKey(), item.getEnglish());
        }

        for (WeaponTranslation item: WeaponTranslation.values()) {
            add(item.getKey(), item.getEnglish());
        }

        for (UtilityItemsTranslation item: UtilityItemsTranslation.values()) {
            add(item.getKey(), item.getEnglish());
        }

        for (MagicItemsTranslation item: MagicItemsTranslation.values()) {
            add(item.getKey(), item.getEnglish());
        }

        for (UtilityDescriptionTranslation item: UtilityDescriptionTranslation.values()) {
            add(item.getKey(), item.getEnglish());
        }

        for (DecorationBlockTranslation item: DecorationBlockTranslation.values()) {
            add(item.getKey(), item.getEnglish());
        }

        for (IconsAndPatternsTranslation item: IconsAndPatternsTranslation.values()) {
            add(item.getKey(), item.getEnglish());
        }

        for (EntityTranslation item: EntityTranslation.values()) {
            add(item.getKey(), item.getEnglish());
        }

        for (GeodeTranslation item: GeodeTranslation.values()) {
            add(item.getKey(), item.getEnglish());
        }

        for (GUIAndScreensTranslation item: GUIAndScreensTranslation.values()) {
            add(item.getKey(), item.getEnglish());
        }

        for (RedstoneItemTranslation item: RedstoneItemTranslation.values()) {
            add(item.getKey(), item.getEnglish());
        }

    }
}