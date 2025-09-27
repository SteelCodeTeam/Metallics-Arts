package net.rudahee.metallics_arts.data.providers.language_providers;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.data.enums.implementations.languages.*;

public class ModLanguageProviderCAES extends LanguageProvider {

    public ModLanguageProviderCAES(DataGenerator gen, String locale) {
        super(gen.getPackOutput(), MetallicsArts.MOD_ID, locale);
    }

    @Override
    protected void addTranslations() {
        for (BasicMetalItemsTranslation item: BasicMetalItemsTranslation.values()) {
            add(item.getKey(), item.getCatalan());
        }

        for (WeaponTranslation item: WeaponTranslation.values()) {
            add(item.getKey(), item.getCatalan());
        }

        for (UtilityItemsTranslation item: UtilityItemsTranslation.values()) {
            add(item.getKey(), item.getCatalan());
        }

        for (MagicItemsTranslation item: MagicItemsTranslation.values()) {
            add(item.getKey(), item.getCatalan());
        }

        for (UtilityDescriptionTranslation item: UtilityDescriptionTranslation.values()) {
            add(item.getKey(), item.getCatalan());
        }

        for (DecorationBlockTranslation item: DecorationBlockTranslation.values()) {
            add(item.getKey(), item.getCatalan());
        }

        for (IconsAndPatternsTranslation item: IconsAndPatternsTranslation.values()) {
            add(item.getKey(), item.getCatalan());
        }

        for (EntityTranslation item: EntityTranslation.values()) {
            add(item.getKey(), item.getCatalan());
        }

        for (GeodeTranslation item: GeodeTranslation.values()) {
            add(item.getKey(), item.getCatalan());
        }

        for (GUIAndScreensTranslation item: GUIAndScreensTranslation.values()) {
            add(item.getKey(), item.getCatalan());
        }

        for (RedstoneItemTranslation item: RedstoneItemTranslation.values()) {
            add(item.getKey(), item.getCatalan());
        }

        for (EffectsTranslation item: EffectsTranslation.values()) {
            add(item.getKey(), item.getCatalan());
        }

        for (CommandsTranslation item: CommandsTranslation.values()) {
            add(item.getKey(), item.getCatalan());
        }
    }
}