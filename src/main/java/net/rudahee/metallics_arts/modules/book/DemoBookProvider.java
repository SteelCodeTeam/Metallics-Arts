/*
 * SPDX-FileCopyrightText: 2022 klikli-dev
 *
 * SPDX-License-Identifier: MIT
 */

package net.rudahee.metallics_arts.modules.book;

import com.klikli_dev.modonomicon.api.ModonomiconAPI;
import com.klikli_dev.modonomicon.api.datagen.BookLangHelper;
import com.klikli_dev.modonomicon.api.datagen.BookProvider;
import com.klikli_dev.modonomicon.api.datagen.EntryLocationHelper;
import com.klikli_dev.modonomicon.api.datagen.book.BookCategoryModel;
import com.klikli_dev.modonomicon.api.datagen.book.BookEntryModel;
import com.klikli_dev.modonomicon.api.datagen.book.BookModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookCraftingRecipePageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookPageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSmithingRecipePageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookTextPageModel;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.enums.implementations.languages.book.multi_craft.MultiCraftData;
import net.rudahee.metallics_arts.data.enums.implementations.languages.book.sub_division.SubdivisionData;
import net.rudahee.metallics_arts.data.enums.implementations.languages.book.sub_division.SubdivisionDescription;
import net.rudahee.metallics_arts.data.enums.implementations.languages.book.weapons.WeaponsData;
import net.rudahee.metallics_arts.utils.GetItemsUtils;

import java.util.ArrayList;


public class DemoBookProvider extends BookProvider {

    /**
     * Constructs a new DemoBookProvider.
     *
     * @param generator The DataGenerator object used to generate the book data.
     * @param modid The ID of the mod that the book belongs to.
     * @param lang The LanguageProvider object used to provide translations for the book.
     */
    public DemoBookProvider(DataGenerator generator, String modid, LanguageProvider lang) {
        super(generator.getPackOutput(), modid, lang);
    }

    /**
     * Creates a demo book with the given book name using the language helper of the mod.
     * This book includes categories for introductions, allomancy, and feruchemy.
     *
     * @param bookName the name of the book to be created.
     *
     * @return a {@link BookModel} object representing the demo book.
     */
    private BookModel makeDemoBook(String bookName) {

        BookLangHelper helper = ModonomiconAPI.get().getLangHelper(this.modid);

        helper.book(bookName);

        BookCategoryModel introCategory = this.introCategory(helper);
        BookCategoryModel allomancyCategory = this.allomancyCategory(helper);
        BookCategoryModel feruchemyCategory = this.feruchemyCategory(helper);


        return BookModel.create(this.modLoc(bookName), helper.bookName())
                .withTooltip(helper.bookTooltip())
                .withGenerateBookItem(true)
                .withModel(this.modLoc("metallics_arts_book"))
                .withCategories(introCategory, allomancyCategory, feruchemyCategory);
    }

    /**
     * Creates a BookCategoryModel instance for the "Intro" category, containing various entries related to weapons, crafting, and items.
     *
     * @param helper The BookLangHelper instance used to create language keys.
     *
     * @return A BookCategoryModel instance representing the "Intro" category.
     */
    private BookCategoryModel introCategory(BookLangHelper helper) {
        // Sets the category name
        helper.category("intro");
        // Define a map used to determine the position of entries in the book.
        EntryLocationHelper entryHelper = ModonomiconAPI.get().getEntryLocationHelper();
        entryHelper.setMap(
                "_________g___________t__f_______",
                "________________________________",
                "_______d____a_____p_____________",
                "________________________________",
                "_________h___________r__e_______",
                "_______________s________________",
                "________________________________",
                "_______________b________________",
                "_________n___________k__________",
                "____________j_____i_____________",
                "_________o_____m_____l__________",
                "________________________________"
        );



        // Create various entries and entry parents.
        BookEntryModel welcome = this.welcomePowerEntry(helper, entryHelper, 's', SubdivisionData.WELCOME);
        BookEntryModel weapons = this.subDivisionEntry(helper, entryHelper, 'a', SubdivisionData.WEAPONS, welcome);

        BookEntryModel crafting = this.subDivisionEntry(helper, entryHelper, 'b', SubdivisionData.CAFTING, welcome);

        BookEntryModel cores = this.multiCraftsItemsEntry(helper, entryHelper, 'p', MultiCraftData.CORES, GetItemsUtils.getCores(), welcome);
        BookEntryModel aluminum_armor = this.multiSmithingItemsEntry(helper, entryHelper, 'r', MultiCraftData.ALUMINUM_ARMOR, GetItemsUtils.getAluminumArmor(), cores);
        BookEntryModel steel_armor = this.multiSmithingItemsEntry(helper, entryHelper, 't', MultiCraftData.STEEL_ARMOR, GetItemsUtils.getSteelArmor(), cores);

        BookEntryModel crystalDagger = this.weaponsEntry(helper, entryHelper, 'd', weapons, WeaponsData.SILVER_KNIFE);
        BookEntryModel obsidianDagger = this.weaponsEntry(helper, entryHelper, 'e', aluminum_armor, WeaponsData.OBSIDIAN_DAGGER);
        BookEntryModel obsidianAxe = this.weaponsEntry(helper, entryHelper, 'f', steel_armor, WeaponsData.OBSIDIAN_AXE);
        BookEntryModel kolossBlade = this.weaponsEntry(helper, entryHelper, 'g', weapons, WeaponsData.KOLOSS_BLADE);
        BookEntryModel duelingStaff = this.weaponsEntry(helper, entryHelper, 'h', weapons, WeaponsData.DUELING_STAFF);

        BookEntryModel vials = this.multiCraftsItemsEntry(helper, entryHelper, 'j', MultiCraftData.VIALS, GetItemsUtils.getVialsList(), crafting);
        BookEntryModel rings = this.multiCraftsItemsEntry(helper, entryHelper, 'k', MultiCraftData.RINGS, GetItemsUtils.getRingList(), crafting);
        BookEntryModel bands = this.multiCraftsItemsEntry(helper, entryHelper, 'l', MultiCraftData.BANDS, GetItemsUtils.getBandList(), rings);
        BookEntryModel spikes = this.multiCraftsItemsEntry(helper, entryHelper, 'm', MultiCraftData.SPIKES, GetItemsUtils.getSpikesList(), crafting);
        BookEntryModel icons = this.multiCraftsItemsEntry(helper, entryHelper, 'n', MultiCraftData.ICONS, GetItemsUtils.getIconsList(), crafting);
        BookEntryModel alloys = this.multiCraftsItemsEntry(helper, entryHelper, 'i', MultiCraftData.ALLOYS, GetItemsUtils.getAlloysList(), crafting); //, alloyFurnaceParent

        BookEntryModel patterns = this.multiCraftsItemsEntry(helper, entryHelper, 'o', MultiCraftData.PATTERNS, GetItemsUtils.getPatterns(), icons);


        return BookCategoryModel.create(this.modLoc(helper.category),helper.categoryName())
                .withIcon("minecraft:book")
                .withSortNumber(0)
                .withEntries(weapons,crafting,crystalDagger,
                        obsidianDagger,obsidianAxe,kolossBlade,
                        duelingStaff,alloys,vials,rings,bands,
                        spikes,icons,patterns,welcome,cores,
                        aluminum_armor,steel_armor);
    }

    /**
     * Generates a BookCategoryModel for the "Allomancy" category using the provided BookLangHelper.
     * The category contains entries for each Allomantic power, divided into subdivisions based on metal type.
     *
     * @param helper The BookLangHelper to use for generating the category and entry names.
     *
     * @return A BookCategoryModel for the "Allomancy" category.
     */
    private BookCategoryModel allomancyCategory(BookLangHelper helper) {
        // Sets the category name
        helper.category("allomancy");

        // Set up the entry location helper with a map of positions for the entries.
        EntryLocationHelper entryHelper = ModonomiconAPI.get().getEntryLocationHelper();
        entryHelper.setMap(
                "________i______B______a________",
                "_______j_______________b_______",
                "___________q_______r___________",
                "_______k_______________c_______",
                "________l_____________d________",
                "_______________s_______________",
                "________m_____________e________",
                "_______n_______________f_______",
                "___________t_______v___________",
                "_______o_______________g_______",
                "________p______A______h________",
                "__________w_________z__________",
                "_____________x___y______________"
        );

        // Generate entries for each subdivision and Allomantic powers.
        BookEntryModel allomancy = this.welcomePowerEntry(helper, entryHelper, 's', SubdivisionData.ALLOMANCY);

        BookEntryModel physical = this.subDivisionEntry(helper, entryHelper, 'r', SubdivisionData.PHYSICAL_ALLOMANCY, allomancy);
        BookEntryModel enhancement = this.subDivisionEntry(helper, entryHelper, 't', SubdivisionData.ENHANCEMENT, allomancy);
        BookEntryModel temporal = this.subDivisionEntry(helper, entryHelper, 'v', SubdivisionData.TEMPORAL, allomancy);
        BookEntryModel cognitive = this.subDivisionEntry(helper, entryHelper, 'q', SubdivisionData.MENTAL, allomancy);
        BookEntryModel divine = this.subDivisionEntry(helper, entryHelper, 'A', SubdivisionData.DIVINE_ALLOMANCY, allomancy);
        BookEntryModel tutorial = this.subDivisionEntry(helper, entryHelper, 'B', SubdivisionData.TUTORIAL_ALLOMANCY, allomancy);

        BookEntryModel iron = this.allomancyPowerEntry(helper, entryHelper, 'a',MetalTagEnum.IRON, physical);
        BookEntryModel steel = this.allomancyPowerEntry(helper, entryHelper, 'b',MetalTagEnum.STEEL, physical);
        BookEntryModel tin = this.allomancyPowerEntry(helper, entryHelper, 'c',MetalTagEnum.TIN, physical);
        BookEntryModel pewter = this.allomancyPowerEntry(helper, entryHelper, 'd',MetalTagEnum.PEWTER, physical);

        BookEntryModel gold = this.allomancyPowerEntry(helper, entryHelper, 'e',MetalTagEnum.GOLD, temporal);
        BookEntryModel electrum = this.allomancyPowerEntry(helper, entryHelper, 'f',MetalTagEnum.ELECTRUM, temporal);
        BookEntryModel cadmium = this.allomancyPowerEntry(helper, entryHelper, 'g',MetalTagEnum.CADMIUM, temporal);
        BookEntryModel bendalloy = this.allomancyPowerEntry(helper, entryHelper, 'h',MetalTagEnum.BENDALLOY, temporal);

        BookEntryModel copper = this.allomancyPowerEntry(helper, entryHelper, 'i',MetalTagEnum.COPPER, cognitive);
        BookEntryModel bronze = this.allomancyPowerEntry(helper, entryHelper, 'j',MetalTagEnum.BRONZE, cognitive);
        BookEntryModel zinc = this.allomancyPowerEntry(helper, entryHelper, 'k',MetalTagEnum.ZINC, cognitive);
        BookEntryModel brass = this.allomancyPowerEntry(helper, entryHelper, 'l',MetalTagEnum.BRASS, cognitive);

        BookEntryModel aluminum = this.allomancyPowerEntry(helper, entryHelper, 'm',MetalTagEnum.ALUMINUM, enhancement);
        BookEntryModel duralumin = this.allomancyPowerEntry(helper, entryHelper, 'n',MetalTagEnum.DURALUMIN, enhancement);
        BookEntryModel chromium = this.allomancyPowerEntry(helper, entryHelper, 'o',MetalTagEnum.CHROMIUM, enhancement);
        BookEntryModel nicrosil = this.allomancyPowerEntry(helper, entryHelper, 'p',MetalTagEnum.NICROSIL, enhancement);

        BookEntryModel atium = this.allomancyPowerEntry(helper, entryHelper, 'w',MetalTagEnum.ATIUM, divine);
        BookEntryModel malatium = this.allomancyPowerEntry(helper, entryHelper, 'x',MetalTagEnum.MALATIUM, divine);
        BookEntryModel lerasium = this.allomancyPowerEntry(helper, entryHelper, 'y',MetalTagEnum.LERASIUM, divine);
        BookEntryModel ettmetal = this.allomancyPowerEntry(helper, entryHelper, 'z',MetalTagEnum.ETTMETAL, divine);

        //.withBackground(new ResourceLocation(MetallicsArts.MOD_ID + ":textures/icons/background.png"))
        return BookCategoryModel.create(this.modLoc(helper.category), helper.categoryName())
                .withIcon("minecraft:iron_ingot")
                .withSortNumber(1)
                .withEntries(iron,steel,tin,pewter,gold,electrum,cadmium,bendalloy,
                        copper,bronze,zinc,brass,aluminum,duralumin,chromium,nicrosil,
                        cognitive,physical,allomancy,enhancement,temporal,atium,malatium,
                        lerasium,ettmetal,divine,tutorial);
    }

    /**
     * Generates a BookCategoryModel for the Feruchemy category with all its corresponding entries and sub-entries.
     *
     * @param helper A BookLangHelper to assist with generating the category name.
     *
     * @return A BookCategoryModel containing all the entries and sub-entries for the Feruchemy category.
     */
    private BookCategoryModel feruchemyCategory(BookLangHelper helper) {
        // Sets the category name
        helper.category("feruchemy");

        // Set up the entry location helper with a map of positions for the entries.
        EntryLocationHelper entryHelper = ModonomiconAPI.get().getEntryLocationHelper();
        entryHelper.setMap(
                "________i______B______a________",
                "_______j_______________b_______",
                "___________q_______r___________",
                "_______k_______________c_______",
                "________l_____________d________",
                "_______________s_______________",
                "________m_____________e________",
                "_______n_______________f_______",
                "___________t_______v___________",
                "_______o_______________g_______",
                "________p______A______h________",
                "__________w_________z__________",
                "_____________x___y______________"
        );

        // Generates all the entries and sub-entries for the Feruchemy category
        BookEntryModel feruchemy = this.welcomePowerEntry(helper, entryHelper, 's', SubdivisionData.FERUCHEMY);

        BookEntryModel physical = this.subDivisionEntry(helper, entryHelper, 'r', SubdivisionData.PHYSICAL_FERUCHEMY, feruchemy);
        BookEntryModel spiritual = this.subDivisionEntry(helper, entryHelper, 't', SubdivisionData.SPIRITUAL, feruchemy);
        BookEntryModel hybrid = this.subDivisionEntry(helper, entryHelper, 'v', SubdivisionData.HYBRID, feruchemy);
        BookEntryModel cognitive = this.subDivisionEntry(helper, entryHelper, 'q', SubdivisionData.COGNITIVE, feruchemy);
        BookEntryModel divine = this.subDivisionEntry(helper, entryHelper, 'A', SubdivisionData.DIVINE_FERUCHEMY, feruchemy);
        BookEntryModel tutorial = this.subDivisionEntry(helper, entryHelper, 'B', SubdivisionData.TUTORIAL_FERUCHEMY, feruchemy);

        BookEntryModel iron = this.feruchemyPowerEntry(helper, entryHelper, 'a',MetalTagEnum.IRON, physical);
        BookEntryModel steel = this.feruchemyPowerEntry(helper, entryHelper, 'b',MetalTagEnum.STEEL, physical);
        BookEntryModel tin = this.feruchemyPowerEntry(helper, entryHelper, 'c',MetalTagEnum.TIN, physical);
        BookEntryModel pewter = this.feruchemyPowerEntry(helper, entryHelper, 'd',MetalTagEnum.PEWTER, physical);

        BookEntryModel gold = this.feruchemyPowerEntry(helper, entryHelper, 'e',MetalTagEnum.GOLD, hybrid);
        BookEntryModel electrum = this.feruchemyPowerEntry(helper, entryHelper, 'f',MetalTagEnum.ELECTRUM, hybrid);
        BookEntryModel cadmium = this.feruchemyPowerEntry(helper, entryHelper, 'g',MetalTagEnum.CADMIUM, hybrid);
        BookEntryModel bendalloy = this.feruchemyPowerEntry(helper, entryHelper, 'h',MetalTagEnum.BENDALLOY, hybrid);

        BookEntryModel copper = this.feruchemyPowerEntry(helper, entryHelper, 'i',MetalTagEnum.COPPER, cognitive);
        BookEntryModel bronze = this.feruchemyPowerEntry(helper, entryHelper, 'j',MetalTagEnum.BRONZE, cognitive);
        BookEntryModel zinc = this.feruchemyPowerEntry(helper, entryHelper, 'k',MetalTagEnum.ZINC, cognitive);
        BookEntryModel brass = this.feruchemyPowerEntry(helper, entryHelper, 'l',MetalTagEnum.BRASS, cognitive);

        BookEntryModel aluminum = this.feruchemyPowerEntry(helper, entryHelper, 'm',MetalTagEnum.ALUMINUM, spiritual);
        BookEntryModel duralumin = this.feruchemyPowerEntry(helper, entryHelper, 'n',MetalTagEnum.DURALUMIN, spiritual);
        BookEntryModel chromium = this.feruchemyPowerEntry(helper, entryHelper, 'o',MetalTagEnum.CHROMIUM, spiritual);
        BookEntryModel nicrosil = this.feruchemyPowerEntry(helper, entryHelper, 'p',MetalTagEnum.NICROSIL, spiritual);

        BookEntryModel atium = this.feruchemyPowerEntry(helper, entryHelper, 'w',MetalTagEnum.ATIUM, divine);
        BookEntryModel malatium = this.feruchemyPowerEntry(helper, entryHelper, 'x',MetalTagEnum.MALATIUM, divine);
        BookEntryModel lerasium = this.feruchemyPowerEntry(helper, entryHelper, 'y',MetalTagEnum.LERASIUM, divine);
        BookEntryModel ettmetal = this.feruchemyPowerEntry(helper, entryHelper, 'z',MetalTagEnum.ETTMETAL, divine);


        //lista de entradas

        return BookCategoryModel.create(this.modLoc(helper.category), helper.categoryName())
                .withIcon("minecraft:gold_ingot")
                .withSortNumber(2)
                .withEntries(iron,steel,tin,pewter,gold,electrum,cadmium,bendalloy,copper,bronze,zinc,
                        brass,aluminum,duralumin,chromium,nicrosil,cognitive,physical,feruchemy,spiritual,
                        hybrid,atium,malatium,lerasium,ettmetal,divine,tutorial);
    }

    /**
     * Creates a BookEntryModel for a multi-crafts item entry.
     *
     * @param helper the BookLangHelper used to get the entry's text and title.
     * @param entryHelper the EntryLocationHelper used to map the entry's location.
     * @param location the location where the entry will be placed.
     * @param multiCraftEntry the MultiCraftData for the item.
     * @param recipeList the ArrayList of recipe IDs.
     * @param parent the parent BookEntryParentModels of the entry (optional).
     *
     * @return the BookEntryModel for the multi-crafts item entry
     */
    private BookEntryModel multiCraftsItemsEntry(BookLangHelper helper, EntryLocationHelper entryHelper, char location, MultiCraftData multiCraftEntry, ArrayList<String> recipeList, BookEntryModel parent) {
        helper.entry(multiCraftEntry.getId() + "_entry");
        ArrayList<BookPageModel> list = new ArrayList<>();

        // Add item description page
        helper.page("items_description");
        BookTextPageModel page =
                BookTextPageModel.builder()
                        .withText(helper.pageText())
                        .withTitle(helper.pageTitle())
                        .build();

        list.add(page);
        // Add recipe pages
        while (!recipeList.isEmpty()) {
            if (recipeList.size() != 1) {
                list.add(BookCraftingRecipePageModel.builder()
                        .withRecipeId1(recipeList.remove(0))
                        .withRecipeId2(recipeList.remove(0))
                        .build());
            } else {
                list.add(BookCraftingRecipePageModel.builder()
                        .withRecipeId1(recipeList.remove(0))
                        .build());
            }
        }

        return BookEntryModel.builder()
                .withId(this.modLoc(helper.category + "/" + helper.entry))
                .withName(helper.entryName())
                .withDescription(helper.entryDescription())
                .withIcon(multiCraftEntry.getIcon())
                .withLocation(entryHelper.get(location))
                .withParent(parent)
                .withPages(list.toArray(new BookPageModel[0]))
                .build();
    }
    private BookEntryModel multiSmithingItemsEntry(BookLangHelper helper, EntryLocationHelper entryHelper, char location, MultiCraftData multiCraftEntry, ArrayList<String> recipeList, BookEntryModel parent) {
        helper.entry(multiCraftEntry.getId() + "_entry");
        ArrayList<BookPageModel> list = new ArrayList<>();

        // Add item description page
        helper.page("items_description");
        BookTextPageModel page =
                BookTextPageModel.builder()
                        .withText(helper.pageText())
                        .withTitle(helper.pageTitle())
                        .build();

        list.add(page);
        // Add recipe pages
        while (!recipeList.isEmpty()) {
            if (recipeList.size() != 1) {
                list.add(BookSmithingRecipePageModel.builder()
                        .withRecipeId1(recipeList.remove(0))
                        .withRecipeId2(recipeList.remove(0))
                        .build());
            } else {
                list.add(BookSmithingRecipePageModel.builder()
                        .withRecipeId1(recipeList.remove(0))
                        .build());
            }
        }

        return BookEntryModel.builder()
                .withId(this.modLoc(helper.category + "/" + helper.entry))
                .withName(helper.entryName())
                .withDescription(helper.entryDescription())
                .withIcon(multiCraftEntry.getIcon())
                .withLocation(entryHelper.get(location))
                .withParent(parent)
                .withPages(list.toArray(new BookPageModel[0]))
                .build();
    }


    /**
     * Generates a BookEntryModel object for a weapons entry based on the given WeaponsData object.
     *
     * @param helper The BookLangHelper used to generate the entry's name, description, and pages.
     * @param entryHelper The EntryLocationHelper used to determine the entry's location within the book.
     * @param location The character representing the location of the entry within the book.
     * @param parent The parent BookEntryModel object for the entry.
     * @param weaponsEntry The WeaponsData object containing information about the weapons entry.
     *
     * @return A BookEntryModel object representing the weapons entry.
     */
     private BookEntryModel weaponsEntry(BookLangHelper helper, EntryLocationHelper entryHelper, char location, BookEntryModel parent, WeaponsData weaponsEntry) {
         // Set the entry name and ID using the WeaponsData object.
         helper.entry(weaponsEntry.getId() + "_entry");
         // Create the first page for the entry.
         helper.page("weapon_description");
         BookTextPageModel page =
                 BookTextPageModel.builder()
                         .withText(helper.pageText())
                         .withTitle(helper.pageTitle())
                         .build();
         // Create the second page for the entry, containing the crafting recipe.
         helper.page("weapon_craft");
         BookCraftingRecipePageModel page2 =
                 BookCraftingRecipePageModel.builder()
                         .withRecipeId1(weaponsEntry.getRecipe())
                         .build();

         // Create and return the BookEntryModel object for the weapons entry.
         return BookEntryModel.builder()
                 .withId(this.modLoc(helper.category + "/" + helper.entry))
                 .withName(helper.entryName())
                 .withDescription(helper.entryDescription())
                 .withIcon(weaponsEntry.getIcon())
                 .withLocation(entryHelper.get(location))
                 .withParent(parent)
                 .withPages(page,page2)
                 .build();
     }

    /**
     * Generates a new BookEntryModel object for a subdivision entry in the book.
     *
     * @param helper BookLangHelper object containing the necessary language keys and data for the entry.
     * @param entryHelper EntryLocationHelper object containing the necessary location mapping data for the entry.
     * @param location char indicating the location in the book where the entry will be placed.
     * @param subdivisionEntry SubdivisionData object containing the necessary data for the subdivision entry.
     * @param parent BookEntryModel object indicating the parent entry of the subdivision entry.
     *
     * @return a new BookEntryModel object representing the subdivision entry.
     */
    private BookEntryModel subDivisionEntry(BookLangHelper helper, EntryLocationHelper entryHelper, char location, SubdivisionData subdivisionEntry, BookEntryModel parent) {
        helper.entry(subdivisionEntry.getId() + "_entry");

        ArrayList<BookTextPageModel> list = new ArrayList<>();
        int x = 1;
        for (String ignored: SubdivisionDescription.valueOf(subdivisionEntry.name()).getSpanish()) {
            helper.page("page"+ x);
            BookTextPageModel page =
                    BookTextPageModel.builder()
                            .withText(helper.pageText())
                            .withTitle(helper.pageTitle())
                            .build();
            list.add(page);
            x++;
        }

        return BookEntryModel.builder()
                .withId(this.modLoc(helper.category + "/" + helper.entry))
                .withName(helper.entryName())
                .withDescription(helper.entryDescription())
                .withIcon(subdivisionEntry.getIcon())
                .withLocation(entryHelper.get(location))
                .withParent(parent)
                .withPages(list.toArray(new BookPageModel[0]))
                .build();
    }


    /**
     * Generates a book entry for an Allomancy power associated with a metal. The entry includes a description page and a page
     * about the interactions of the power with other elements in the game.
     *
     * @param helper The language helper object that contains the necessary strings for the entry.
     * @param entryHelper The helper object for determining the location of the entry in the book.
     * @param location The location in the book where the entry should be placed.
     * @param metal The metal associated with the power.
     * @param parent The parent entry for the new entry.
     *
     * @return A BookEntryModel object representing the new entry.
     */
    private BookEntryModel allomancyPowerEntry(BookLangHelper helper, EntryLocationHelper entryHelper, char location, MetalTagEnum metal, BookEntryModel parent) {
        // Generate entry id based on metal name
        helper.entry(metal.getNameLower() + "_entry");

        // Add description page
        helper.page("power_description");
        BookTextPageModel page =
                BookTextPageModel.builder()
                        .withText(helper.pageText())
                        .withTitle(helper.pageTitle())
                        .build();

        // Add page about power interactions
        helper.page("power_interactions");
        BookTextPageModel page2 =
                BookTextPageModel.builder()
                        .withText(helper.pageText())
                        .withTitle(helper.pageTitle())
                        .build();

        // Create and return new entry
        return BookEntryModel.builder()
                .withId(this.modLoc(helper.category + "/" + helper.entry))
                .withName(helper.entryName())
                .withDescription(helper.entryDescription())
                .withIcon("metallics_arts:" + metal.getNameLower() + "_allomantic_icon")
                .withLocation(entryHelper.get(location))
                .withParent(parent)
                .withPages(page,page2)
                .build();
    }


    /**
     * Creates a BookEntryModel object for a Feruchemy Power entry in the Metallic Arts book.
     *
     * @param helper The BookLangHelper object used for generating language-related content for the entry.
     * @param entryHelper The EntryLocationHelper object used for specifying the location of the entry in the book.
     * @param location The location of the entry in the book.
     * @param metal The MetalTagEnum corresponding to the metal that the Feruchemy power is associated with.
     * @param parent The parent BookEntryModel object of this entry.
     *
     * @return A BookEntryModel object for a Feruchemy Power entry in the Metallic Arts book.
     */
    private BookEntryModel feruchemyPowerEntry(BookLangHelper helper, EntryLocationHelper entryHelper, char location, MetalTagEnum metal, BookEntryModel parent) {

        helper.entry(metal.getNameLower() + "_entry");

        helper.page("power_storage");
        BookTextPageModel page =
                BookTextPageModel.builder()
                        .withText(helper.pageText())
                        .withTitle(helper.pageTitle())
                        .build();

        helper.page("power_tap");
        BookTextPageModel page2 =
                BookTextPageModel.builder()
                        .withText(helper.pageText())
                        .withTitle(helper.pageTitle())
                        .build();

        return BookEntryModel.builder()
                .withId(this.modLoc(helper.category + "/" + helper.entry))
                .withName(helper.entryName())
                .withDescription(helper.entryDescription())
                .withIcon("metallics_arts:" + metal.getNameLower() + "_feruchemic_icon")
                .withLocation(entryHelper.get(location))
                .withParent(parent)
                .withPages(page,page2)
                .build();
    }

    /**
     * Generates a book entry for a welcome power, which introduces the player to the mod.
     *
     * @param helper the BookLangHelper object used to generate the entry's name, description, and pages.
     * @param entryHelper the EntryLocationHelper object used to determine the entry's location.
     * @param location the location of the entry within the book.
     * @param subdivisionData the data for the subdivision associated with the entry.
     *
     * @return a BookEntryModel representing the welcome power entry.
     */
    private BookEntryModel welcomePowerEntry(BookLangHelper helper, EntryLocationHelper entryHelper, char location, SubdivisionData subdivisionData) {
        helper.entry(subdivisionData.getId() + "_entry");

        ArrayList<BookTextPageModel> list = new ArrayList<>();
        int x = 1;
        for (String ignored: SubdivisionDescription.valueOf(subdivisionData.name()).getSpanish()) {
            helper.page("page"+ x);
            BookTextPageModel page =
                    BookTextPageModel.builder()
                            .withText(helper.pageText())
                            .withTitle(helper.pageTitle())
                            .build();
            list.add(page);
            x++;
        }
        // Build and return the book entry model
        return BookEntryModel.builder()
                .withId(this.modLoc(helper.category + "/" + helper.entry))
                .withName(helper.entryName())
                .withDescription(helper.entryDescription())
                .withIcon("minecraft:paper")
                .withLocation(entryHelper.get(location))
                .withPages(list.toArray(new BookPageModel[0]))
                .build();
    }


    /**
     * Generates a demo book called "metallics_arts_book" and adds it to the registry.
     */
    @Override
    protected void generate() {
        BookModel demoBook = this.makeDemoBook("metallics_arts_book");
        this.add(demoBook);
    }
}
