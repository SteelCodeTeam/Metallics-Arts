package net.rudahee.metallics_arts.data.providers.language_providers.book;

import com.klikli_dev.modonomicon.api.ModonomiconAPI;
import com.klikli_dev.modonomicon.api.datagen.BookLangHelper;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.enums.implementations.languages.CTW;
import net.rudahee.metallics_arts.data.enums.implementations.languages.MetalNamesEnum;
import net.rudahee.metallics_arts.data.enums.implementations.languages.book.allomancy.Interactions;
import net.rudahee.metallics_arts.data.enums.implementations.languages.book.allomancy.PowerDescriptions;
import net.rudahee.metallics_arts.data.enums.implementations.languages.book.feruchemic.Storage;
import net.rudahee.metallics_arts.data.enums.implementations.languages.book.feruchemic.Tap;
import net.rudahee.metallics_arts.data.enums.implementations.languages.book.multi_craft.MultiCaftDescriptions;
import net.rudahee.metallics_arts.data.enums.implementations.languages.book.multi_craft.MultiCraftData;
import net.rudahee.metallics_arts.data.enums.implementations.languages.book.sub_division.SubdivisionData;
import net.rudahee.metallics_arts.data.enums.implementations.languages.book.sub_division.SubdivisionDescription;
import net.rudahee.metallics_arts.data.enums.implementations.languages.book.sub_division.SubdivisionDescriptionExtra;
import net.rudahee.metallics_arts.data.enums.implementations.languages.book.weapons.WeaponDescriptions;
import net.rudahee.metallics_arts.data.enums.implementations.languages.book.weapons.WeaponsData;

import java.util.HashMap;

public class BookHelperES {
    public static HashMap<String, String> addDemoBook(){

        HashMap<String, String> bookLangHelper = new HashMap<>();
        BookLangHelper helper = ModonomiconAPI.get().getLangHelper(MetallicsArts.MOD_ID);
        helper.book("metallics_arts_book");

        bookLangHelper.put(helper.bookName(), CTW.METALLICS_ARTS.getNameInSpanish() + ": " + CTW.GUIDE.getNameInSpanish());
        bookLangHelper.put(helper.bookTooltip(), "Libro de poderes");

        bookLangHelper.putAll(addAllomancyCategory(helper));
        bookLangHelper.putAll(feruchemyCategory(helper));
        bookLangHelper.putAll(addIntroCategory(helper));

        return bookLangHelper;
    }

    private static HashMap<String, String> addIntroCategory(BookLangHelper helper){
        HashMap<String, String> introTraslationsMap = new HashMap<>();
        helper.category("intro");
        introTraslationsMap.put(helper.categoryName(), "Intro");

        introTraslationsMap.putAll(addWelcomeEntry(helper, SubdivisionData.WELCOME));
        for (SubdivisionData entry: SubdivisionData.values()) {
            if (!entry.isAllomantic() && !entry.isFeruchemical() && !entry.isWelcome()){
                introTraslationsMap.putAll(addSubDivisionEntry(helper,entry));
            }
        }
        for (WeaponsData entry : WeaponsData.values()) {
            introTraslationsMap.putAll(addWeaponsEntry(helper, entry));
        }
        for (MultiCraftData entry: MultiCraftData.values()) {
            introTraslationsMap.putAll(addMultiCraftEntry(helper,entry));
        }
        return introTraslationsMap;
    }
    private static HashMap<String, String> addAllomancyCategory(BookLangHelper helper) {

        HashMap<String, String> allomancyTraslationsMap = new HashMap<>();

        helper.category("allomancy");
        allomancyTraslationsMap.put(helper.categoryName(), CTW.ALLOMANCY.getNameInSpanish());
        allomancyTraslationsMap.putAll(addWelcomeEntry(helper, SubdivisionData.ALLOMANCY));

        for (SubdivisionData oM: SubdivisionData.values()) {
            if (oM.isAllomantic()){
                allomancyTraslationsMap.putAll(addSubDivisionEntry(helper,oM));
            }
        }
        for (MetalTagEnum metal: MetalTagEnum.values()) {
            allomancyTraslationsMap.putAll(addAllomancyEntry(helper, metal));
        }
        return allomancyTraslationsMap;
    }

    private static HashMap<String, String> feruchemyCategory(BookLangHelper helper) {
        HashMap<String, String> feruchemyTraslationsMap = new HashMap<>();
        helper.category("feruchemy");
        feruchemyTraslationsMap.put(helper.categoryName(), CTW.FERUCHEMY.getNameInSpanish());

        feruchemyTraslationsMap.putAll(addWelcomeEntry(helper, SubdivisionData.FERUCHEMY));

        for (SubdivisionData oM: SubdivisionData.values()) {
            if (oM.isFeruchemical()){
                feruchemyTraslationsMap.putAll(addSubDivisionEntry(helper,oM));
            }
        }
        for (MetalTagEnum metal: MetalTagEnum.values()) {
            feruchemyTraslationsMap.putAll(addFeruchemyEntry(helper, metal));
        }
        return feruchemyTraslationsMap;
    }

    private static HashMap<String, String> addWelcomeEntry(BookLangHelper helper, SubdivisionData subdivisionEntry) {
        HashMap<String, String> welcomeTraslationsMap = new HashMap<>();
        helper.entry(subdivisionEntry.getId() + "_entry");

        welcomeTraslationsMap.put(helper.entryName(), "nombre");
        welcomeTraslationsMap.put(helper.entryDescription(), "descripcion");

        helper.page("page1");
        welcomeTraslationsMap.put(helper.pageTitle(), "textito");
        welcomeTraslationsMap.put(helper.pageText(), SubdivisionDescription.valueOf(subdivisionEntry.name()).getSpanish());

        helper.page("page2");
        welcomeTraslationsMap.put(helper.pageTitle(), "textito");
        welcomeTraslationsMap.put(helper.pageText(), SubdivisionDescriptionExtra.valueOf(subdivisionEntry.name()).getSpanish());

        return welcomeTraslationsMap;
    }

    private static HashMap<String, String> addSubDivisionEntry(BookLangHelper helper, SubdivisionData subdivisionEntry) {
        HashMap<String, String> subDivisionTraslationsMap = new HashMap<>();
        helper.entry(subdivisionEntry.getId() + "_entry");
        subDivisionTraslationsMap.put(helper.entryName(), CTW.valueOf(subdivisionEntry.name()).getNameInSpanish());
        subDivisionTraslationsMap.put(helper.entryDescription(), "");

        helper.page("page");
        subDivisionTraslationsMap.put(helper.pageTitle(), CTW.valueOf(subdivisionEntry.name()).getNameInSpanish());
        subDivisionTraslationsMap.put(helper.pageText(), SubdivisionDescription.valueOf(subdivisionEntry.name()).getSpanish());

        return subDivisionTraslationsMap;
    }
    private static HashMap<String, String> addWeaponsEntry(BookLangHelper helper, WeaponsData weaponsEntry) {
        HashMap<String, String> weaponsTraslationsMap = new HashMap<>();
        helper.entry(weaponsEntry.getId() + "_entry");
        weaponsTraslationsMap.put(helper.entryName(), CTW.valueOf(weaponsEntry.name()).getNameInSpanish());
        weaponsTraslationsMap.put(helper.entryDescription(), "");

        helper.page("weapon_description");
        weaponsTraslationsMap.put(helper.pageTitle(), CTW.valueOf(weaponsEntry.name()).getNameInSpanish());
        weaponsTraslationsMap.put(helper.pageText(), WeaponDescriptions.valueOf(weaponsEntry.name()).getSpanish());
        return weaponsTraslationsMap;

    }
    private static HashMap<String, String> addMultiCraftEntry(BookLangHelper helper, MultiCraftData multiCraftData) {
        HashMap<String, String> multiCraftTraslationsMap = new HashMap<>();
        helper.entry(multiCraftData.getId() + "_entry");
        multiCraftTraslationsMap.put(helper.entryName(),CTW.valueOf(multiCraftData.name()).getNameInSpanish());
        multiCraftTraslationsMap.put(helper.entryDescription(), "");

        helper.page("items_description");
        multiCraftTraslationsMap.put(helper.pageTitle(), CTW.valueOf(multiCraftData.name()).getNameInSpanish());
        multiCraftTraslationsMap.put(helper.pageText(), MultiCaftDescriptions.valueOf(multiCraftData.name()).getSpanish());
        return multiCraftTraslationsMap;
    }
    private static HashMap<String, String> addAllomancyEntry(BookLangHelper helper, MetalTagEnum metal) {
        HashMap<String, String> allomancyTraslationsMap = new HashMap<>();
        helper.entry(metal.getNameLower() + "_entry");

        allomancyTraslationsMap.put(helper.entryName(), MetalNamesEnum.valueOf(metal.name()).getNameInSpanish());
        allomancyTraslationsMap.put(helper.entryDescription(), "");

        helper.page("power_description");
        allomancyTraslationsMap.put(helper.pageTitle(), MetalNamesEnum.valueOf(metal.name()).getNameInSpanish());
        allomancyTraslationsMap.put(helper.pageText(), PowerDescriptions.valueOf(metal.name()).getSpanish());

        helper.page("power_interactions");
        allomancyTraslationsMap.put(helper.pageTitle(), "Interactions");
        allomancyTraslationsMap.put(helper.pageText(), Interactions.valueOf(metal.name()).getSpanish());
        return allomancyTraslationsMap;
    }
    private static HashMap<String, String> addFeruchemyEntry(BookLangHelper helper, MetalTagEnum metal) {
        HashMap<String, String> feruchemyTraslationsMap = new HashMap<>();
        helper.entry(metal.getNameLower() + "_entry");
        feruchemyTraslationsMap.put(helper.entryName(), MetalNamesEnum.valueOf(metal.name()).getNameInSpanish());
        feruchemyTraslationsMap.put(helper.entryDescription(), "");

        helper.page("power_storage");
        feruchemyTraslationsMap.put(helper.pageTitle(), MetalNamesEnum.valueOf(metal.name()).getNameInSpanish());
        feruchemyTraslationsMap.put(helper.pageText(), Storage.valueOf(metal.name()).getSpanish());

        helper.page("power_tap");
        feruchemyTraslationsMap.put(helper.pageTitle(), "");
        feruchemyTraslationsMap.put(helper.pageText(), Tap.valueOf(metal.name()).getSpanish());
        return feruchemyTraslationsMap;
    }
}
