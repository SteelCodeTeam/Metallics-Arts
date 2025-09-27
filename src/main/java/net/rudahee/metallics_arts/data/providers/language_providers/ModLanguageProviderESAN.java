package net.rudahee.metallics_arts.data.providers.language_providers;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.data.enums.implementations.languages.*;

public class ModLanguageProviderESAN extends LanguageProvider {

    public ModLanguageProviderESAN(DataGenerator gen, String locale) {
        super(gen.getPackOutput(), MetallicsArts.MOD_ID, locale);
    }

    @Override
    protected void addTranslations() {
        for (BasicMetalItemsTranslation item: BasicMetalItemsTranslation.values()) {
            add(item.getKey(), item.getAndaluz());
        }

        for (WeaponTranslation item: WeaponTranslation.values()) {
            add(item.getKey(), item.getAndaluz());
        }

        for (UtilityItemsTranslation item: UtilityItemsTranslation.values()) {
            add(item.getKey(), item.getAndaluz());
        }

        for (MagicItemsTranslation item: MagicItemsTranslation.values()) {
            add(item.getKey(), item.getAndaluz());
        }

        for (UtilityDescriptionTranslation item: UtilityDescriptionTranslation.values()) {
            add(item.getKey(), item.getAndaluz());
        }

        for (DecorationBlockTranslation item: DecorationBlockTranslation.values()) {
            add(item.getKey(), item.getAndaluz());
        }

        for (IconsAndPatternsTranslation item: IconsAndPatternsTranslation.values()) {
            add(item.getKey(), item.getAndaluz());
        }

        for (EntityTranslation item: EntityTranslation.values()) {
            add(item.getKey(), item.getAndaluz());
        }

        for (GeodeTranslation item: GeodeTranslation.values()) {
            add(item.getKey(), item.getAndaluz());
        }

        for (GUIAndScreensTranslation item: GUIAndScreensTranslation.values()) {
            add(item.getKey(), item.getAndaluz());
        }

        for (RedstoneItemTranslation item: RedstoneItemTranslation.values()) {
            add(item.getKey(), item.getAndaluz());
        }

        for (EffectsTranslation item: EffectsTranslation.values()) {
            add(item.getKey(), item.getAndaluz());
        }

        for (CommandsTranslation item: CommandsTranslation.values()) {
            add(item.getKey(), item.getAndaluz());
        }
    }
}