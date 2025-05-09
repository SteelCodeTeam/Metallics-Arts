package net.rudahee.metallics_arts.data.providers.language_providers;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.data.enums.implementations.GemsEnum;
import net.rudahee.metallics_arts.data.enums.implementations.GunType;
import net.rudahee.metallics_arts.data.enums.implementations.MetalEnum;
import net.rudahee.metallics_arts.data.enums.implementations.languages.*;
import net.rudahee.metallics_arts.data.enums.implementations.languages.old.*;
import net.rudahee.metallics_arts.data.providers.language_providers.book.BookHelperES;

import java.util.HashMap;
import java.util.Map;

/**
 * A custom class for providing Spanish translations for mod elements.
 *
 * @author SteelCode Team
 * @since 1.5.6
 */
public class ModLanguageProviderES extends LanguageProvider {

    public ModLanguageProviderES(DataGenerator gen, String locale) {
        super(gen.getPackOutput(), MetallicsArts.MOD_ID, locale);
    }

    @Override
    protected void addTranslations() {
        for (BasicMetalItemsTranslation item: BasicMetalItemsTranslation.values()) {
            add(item.getKey(), item.getSpanish());
        }

        for (WeaponTranslation item: WeaponTranslation.values()) {
            add(item.getKey(), item.getSpanish());
        }

        for (UtilityItemsTranslation item: UtilityItemsTranslation.values()) {
            add(item.getKey(), item.getSpanish());
        }

        for (MagicItemsTranslation item: MagicItemsTranslation.values()) {
            add(item.getKey(), item.getSpanish());
        }

        for (UtilityDescriptionTranslation item: UtilityDescriptionTranslation.values()) {
            add(item.getKey(), item.getSpanish());
        }

        for (DecorationBlockTranslation item: DecorationBlockTranslation.values()) {
            add(item.getKey(), item.getSpanish());
        }

        for (IconsAndPatternsTranslation item: IconsAndPatternsTranslation.values()) {
            add(item.getKey(), item.getSpanish());
        }

        for (EntityTranslation item: EntityTranslation.values()) {
            add(item.getKey(), item.getSpanish());
        }

        for (GeodeTranslation item: GeodeTranslation.values()) {
            add(item.getKey(), item.getSpanish());
        }

        for (GUIAndScreensTranslation item: GUIAndScreensTranslation.values()) {
            add(item.getKey(), item.getSpanish());
        }

        for (RedstoneItemTranslation item: RedstoneItemTranslation.values()) {
            add(item.getKey(), item.getSpanish());
        }

        for (EffectsTranslation item: EffectsTranslation.values()) {
            add(item.getKey(), item.getSpanish());
        }

        for (CommandsTranslation item: CommandsTranslation.values()) {
            add(item.getKey(), item.getSpanish());
        }
    }
}
