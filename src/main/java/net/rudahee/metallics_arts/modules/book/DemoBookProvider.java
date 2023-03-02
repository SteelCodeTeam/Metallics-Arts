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
import com.klikli_dev.modonomicon.api.datagen.book.*;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookCraftingRecipePageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookPageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookTextPageModel;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.LanguageProvider;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.enums.implementations.languages.book.multi_craft.MultiCraftData;
import net.rudahee.metallics_arts.data.enums.implementations.languages.book.sub_division.SubdivisionData;
import net.rudahee.metallics_arts.data.enums.implementations.languages.book.weapons.WeaponsData;
import net.rudahee.metallics_arts.utils.GetItemsUtils;

import java.util.ArrayList;


public class DemoBookProvider extends BookProvider {

    public DemoBookProvider(DataGenerator generator, String modid, LanguageProvider lang) {
        super(generator, modid, lang);
    }

    private BookModel makeDemoBook(String bookName) {
        //The lang helper keeps track of the "DescriptionIds", that is, the language keys for translations, for us
        BookLangHelper helper = ModonomiconAPI.get().getLangHelper(this.modid);

        //we tell the helper the book we're in.
        helper.book(bookName);

        BookCategoryModel introCategory = this.introCategory(helper);
        BookCategoryModel allomancyCategory = this.allomancyCategory(helper);
        BookCategoryModel feruchemyCategory = this.feruchemyCategory(helper);


        //Now we create the book with settings of our choosing
        BookModel demoBook = BookModel.builder()
                .withId(this.modLoc(bookName)) //the id of the book. modLoc() prepends the mod id.
                .withName(helper.bookName()) //the name of the book. The lang helper gives us the correct translation key.
                .withTooltip(helper.bookTooltip()) //the hover tooltip for the book. Again we get a translation key.
                .withGenerateBookItem(true) //auto-generate a book item for us.
                .withModel(this.modLoc("dummy_book")) //use the default red modonomicon icon for the book
                .withCreativeTab(MetallicsArts.MA_TAB.getRecipeFolderName()) //and put it in the modonomicon tab
                .withCategories(introCategory, allomancyCategory, feruchemyCategory)
                .build();
        return demoBook;
    }

    private BookCategoryModel introCategory(BookLangHelper helper) {
        helper.category("intro");
        EntryLocationHelper entryHelper = ModonomiconAPI.get().getEntryLocationHelper();
        entryHelper.setMap(
                "________________________________",
                "_________d__e__f__g__h__________",
                "________________________________",
                "_______________a_________j______",
                "________________________________",
                "_______________s__________k__l__",
                "________________________________",
                "__________c_________b______m____",
                "________________________________",
                "_______________i________________",
                "________________________________",
                "_________________________n___o__",
                "________________________________",
                "________________________________"
        );

        BookEntryModel welcome = this.welcomePowerEntry(helper, entryHelper, 's', SubdivisionData.WELCOME);
        BookEntryModel weapons = this.subDivisionEntry(helper, entryHelper, 'a', SubdivisionData.WEAPONS, welcome);

        BookEntryModel crafting = this.subDivisionEntry(helper, entryHelper, 'b', SubdivisionData.CAFTING, welcome);
        BookEntryParentModel craftingParent = BookEntryParentModel.builder().withEntryId(crafting.getId()).build();
        BookEntryModel alloyFurnace = this.subDivisionEntry(helper, entryHelper, 'c', SubdivisionData.ALLOY_FURNACE, welcome);
        BookEntryParentModel alloyFurnaceParent = BookEntryParentModel.builder().withEntryId(alloyFurnace.getId()).build();

        BookEntryModel crystalDagger = this.weaponsEntry(helper, entryHelper, 'd', weapons, WeaponsData.CRYSTAL_DAGGER);
        BookEntryModel obsidianDagger = this.weaponsEntry(helper, entryHelper, 'e', weapons, WeaponsData.OBSIDIAN_DAGGER);
        BookEntryModel obsidianAxe = this.weaponsEntry(helper, entryHelper, 'f', weapons, WeaponsData.OBSIDIAN_AXE);
        BookEntryModel kolossBlade = this.weaponsEntry(helper, entryHelper, 'g', weapons, WeaponsData.KOLOSS_BLADE);
        BookEntryModel duelingStaff = this.weaponsEntry(helper, entryHelper, 'h', weapons, WeaponsData.DUELING_STAFF);


        BookEntryModel vials = this.multiCraftsItemsEntry(helper, entryHelper, 'j', MultiCraftData.VIALS, GetItemsUtils.getVialsList(), craftingParent);
        BookEntryModel rings = this.multiCraftsItemsEntry(helper, entryHelper, 'k', MultiCraftData.RINGS, GetItemsUtils.getRingList(), craftingParent);
        BookEntryParentModel ringsParent = BookEntryParentModel.builder().withEntryId(rings.getId()).build();
        BookEntryModel bands = this.multiCraftsItemsEntry(helper, entryHelper, 'l', MultiCraftData.BANDS, GetItemsUtils.getBandList(), ringsParent);
        BookEntryModel spikes = this.multiCraftsItemsEntry(helper, entryHelper, 'm', MultiCraftData.SPIKES, GetItemsUtils.getSpikesList(), craftingParent);
        BookEntryModel icons = this.multiCraftsItemsEntry(helper, entryHelper, 'n', MultiCraftData.ICONS, GetItemsUtils.getIconsList(), craftingParent);
        BookEntryModel alloys = this.multiCraftsItemsEntry(helper, entryHelper, 'i', MultiCraftData.ALLOYS, GetItemsUtils.getAlloysList(), craftingParent, alloyFurnaceParent);

        BookEntryParentModel iconsParent = BookEntryParentModel.builder().withEntryId(icons.getId()).build();
        BookEntryModel patterns = this.multiCraftsItemsEntry(helper, entryHelper, 'o', MultiCraftData.PATTERNS, GetItemsUtils.getPatterns(), iconsParent);

        return BookCategoryModel.builder()
                .withId(this.modLoc(helper.category)) //the id of the category, as stored in the lang helper. modLoc() prepends the mod id.
                .withName(helper.categoryName()) //the name of the category. The lang helper gives us the correct translation key.
                .withIcon("minecraft:book") //the icon for the category. In this case we simply use an existing item.
                .withEntries(weapons,crafting,alloyFurnace,crystalDagger,obsidianDagger,obsidianAxe,kolossBlade,duelingStaff,alloys,vials,rings,bands,spikes,icons,patterns,welcome)
                .build();
    }

    private BookCategoryModel allomancyCategory(BookLangHelper helper) {
        helper.category("allomancy");

        EntryLocationHelper entryHelper = ModonomiconAPI.get().getEntryLocationHelper();
        entryHelper.setMap(
                "________i_____________a________",
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

        BookEntryModel allomancy = this.welcomePowerEntry(helper, entryHelper, 's', SubdivisionData.ALLOMANCY);

        BookEntryModel physical = this.subDivisionEntry(helper, entryHelper, 'r', SubdivisionData.PHYSICAL, allomancy);
        BookEntryModel enhancement = this.subDivisionEntry(helper, entryHelper, 't', SubdivisionData.ENHANCEMENT, allomancy);
        BookEntryModel temporal = this.subDivisionEntry(helper, entryHelper, 'v', SubdivisionData.TEMPORAL, allomancy);
        BookEntryModel cognitive = this.subDivisionEntry(helper, entryHelper, 'q', SubdivisionData.COGNITIVE, allomancy);
        BookEntryModel divine = this.subDivisionEntry(helper, entryHelper, 'A', SubdivisionData.DIVINE, allomancy);

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
        return BookCategoryModel.builder()
                .withId(this.modLoc(helper.category))
                .withName(helper.categoryName())
                .withIcon("minecraft:iron_ingot")
                .withEntries(iron,steel,tin,pewter,gold,electrum,cadmium,bendalloy,copper,bronze,zinc,brass,aluminum,duralumin,chromium,nicrosil,cognitive,physical,allomancy,enhancement,temporal,atium,malatium,lerasium,ettmetal,divine)
                .build();
    }

    private BookCategoryModel feruchemyCategory(BookLangHelper helper) {
        helper.category("feruchemy"); //tell our lang helper the category we are in

        //the entry helper is the second helper for book datagen
        //it allows us to place entries in the category without manually defining the coordinates.
        //each letter can be used to represent an entry
        EntryLocationHelper entryHelper = ModonomiconAPI.get().getEntryLocationHelper();
        entryHelper.setMap(
                "________i_____________a________",
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

        BookEntryModel feruchemy = this.welcomePowerEntry(helper, entryHelper, 's', SubdivisionData.FERUCHEMY);

        BookEntryModel physical = this.subDivisionEntry(helper, entryHelper, 'r', SubdivisionData.PHYSICAL, feruchemy);
        BookEntryModel spiritual = this.subDivisionEntry(helper, entryHelper, 't', SubdivisionData.SPIRITUAL, feruchemy);
        BookEntryModel hybrid = this.subDivisionEntry(helper, entryHelper, 'v', SubdivisionData.HYBRID, feruchemy);
        BookEntryModel cognitive = this.subDivisionEntry(helper, entryHelper, 'q', SubdivisionData.COGNITIVE, feruchemy);
        BookEntryModel divine = this.subDivisionEntry(helper, entryHelper, 'A', SubdivisionData.DIVINE, feruchemy);

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

        return BookCategoryModel.builder()
                .withId(this.modLoc(helper.category)) //the id of the category, as stored in the lang helper. modLoc() prepends the mod id.
                .withName(helper.categoryName()) //the name of the category. The lang helper gives us the correct translation key.
                .withIcon("minecraft:gold_ingot") //the icon for the category. In this case we simply use an existing item.
                .withEntries(iron,steel,tin,pewter,gold,electrum,cadmium,bendalloy,copper,bronze,zinc,brass,aluminum,duralumin,chromium,nicrosil,cognitive,physical,feruchemy,spiritual,hybrid,atium,malatium,lerasium,ettmetal,divine)
                .build();
    }

    private BookEntryModel multiCraftsItemsEntry(BookLangHelper helper, EntryLocationHelper entryHelper, char location, MultiCraftData multiCraftEntry, ArrayList<String> recipeList, BookEntryParentModel... parents) {
        helper.entry(multiCraftEntry.getId() + "_entry"); //tell our lang helper the entry we are in
        ArrayList<BookPageModel> list = new ArrayList<>();
        // PAGINA
        helper.page("items_description"); //and now the page
        BookTextPageModel page =
                BookTextPageModel.builder()
                        .withText(helper.pageText()) //lang key for the text
                        .withTitle(helper.pageTitle()) //and for the title
                        .build();
        // PAGINA

        list.add(page);
        while (!recipeList.isEmpty()) {
            list.add(BookCraftingRecipePageModel.builder()
                    .withRecipeId1(recipeList.remove(0))
                    .withRecipeId2(recipeList.remove(0))
                    .build());
        }

        return BookEntryModel.builder()
                .withId(this.modLoc(helper.category + "/" + helper.entry))    //make entry id from lang helper data
                .withName(helper.entryName())                                       //entry name lang key
                .withDescription(helper.entryDescription())                         //entry description lang key
                .withIcon(multiCraftEntry.getIcon())                    //we use furnace as icon
                .withLocation(entryHelper.get(location))                            //and we place it at the location we defined earlier in the entry helper mapping
                .withParents(parents)
                .withPages(list.toArray(new BookPageModel[0]))                                                //finally we add our pages to the entry
                .build();
    }

    private BookEntryModel weaponsEntry(BookLangHelper helper, EntryLocationHelper entryHelper, char location, BookEntryModel parent, WeaponsData weaponsEntry) {
        helper.entry(weaponsEntry.getId() + "_entry"); //tell our lang helper the entry we are in

        // PAGINA
        helper.page("weapon_description"); //and now the page
        BookTextPageModel page =
                BookTextPageModel.builder()
                        .withText(helper.pageText()) //lang key for the text
                        .withTitle(helper.pageTitle()) //and for the title
                        .build();
        // PAGINA
        helper.page("weapon_craft"); //and now the page
        BookCraftingRecipePageModel page2 =
                BookCraftingRecipePageModel.builder()
                        .withRecipeId1(weaponsEntry.getRecipe())
                        .build();



        return BookEntryModel.builder()
                .withId(this.modLoc(helper.category + "/" + helper.entry))  //make entry id from lang helper data
                .withName(helper.entryName())                                    //entry name lang key
                .withDescription(helper.entryDescription())                     //entry description lang key
                .withIcon(weaponsEntry.getIcon())                                  //we use furnace as icon
                .withLocation(entryHelper.get(location))                        //and we place it at the location we defined earlier in the entry helper mapping
                .withParent(parent)
                .withPages(page,page2)                                                //finally we add our pages to the entry
                .build();
    }


    private BookEntryModel subDivisionEntry(BookLangHelper helper, EntryLocationHelper entryHelper, char location, SubdivisionData subdivisionEntry, BookEntryModel parent) {
        helper.entry(subdivisionEntry.getId() + "_entry"); //tell our lang helper the entry we are in

        // PAGINA
        helper.page("page"); //and now the page
        BookTextPageModel page =
                BookTextPageModel.builder()
                        .withText(helper.pageText()) //lang key for the text
                        .withTitle(helper.pageTitle()) //and for the title
                        .build();

        return BookEntryModel.builder()
                .withId(this.modLoc(helper.category + "/" + helper.entry))  //make entry id from lang helper data
                .withName(helper.entryName())                                    //entry name lang key
                .withDescription(helper.entryDescription())                     //entry description lang key
                .withIcon(subdivisionEntry.getIcon())                                  //we use furnace as icon
                .withLocation(entryHelper.get(location))                        //and we place it at the location we defined earlier in the entry helper mapping
                .withParent(parent)
                .withPages(page)                                                //finally we add our pages to the entry
                .build();
    }

    private BookEntryModel allomancyPowerEntry(BookLangHelper helper, EntryLocationHelper entryHelper, char location, MetalTagEnum metal, BookEntryModel parent) {
        helper.entry(metal.getNameLower() + "_entry"); //tell our lang helper the entry we are in

        // PAGINA
        helper.page("power_description"); //and now the page
        BookTextPageModel page =
                BookTextPageModel.builder()
                        .withText(helper.pageText()) //lang key for the text
                        .withTitle(helper.pageTitle()) //and for the title
                        .build();
        // PAGINA
        helper.page("power_interactions"); //and now the page
        BookTextPageModel page2 =
                BookTextPageModel.builder()
                        .withText(helper.pageText()) //lang key for the text
                        .withTitle(helper.pageTitle()) //and for the title
                        .build();              //we start with a text page



        return BookEntryModel.builder()
                .withId(this.modLoc(helper.category + "/" + helper.entry))  //make entry id from lang helper data
                .withName(helper.entryName())                                     //entry name lang key
                .withDescription(helper.entryDescription())                     //entry description lang key
                .withIcon("metallics_arts:" + metal.getNameLower() + "_allomantic_icon")                                  //we use furnace as icon
                .withLocation(entryHelper.get(location))                        //and we place it at the location we defined earlier in the entry helper mapping
                .withParent(parent)
                .withPages(page,page2)                                                //finally we add our pages to the entry
                .build();
    }

    private BookEntryModel feruchemyPowerEntry(BookLangHelper helper, EntryLocationHelper entryHelper, char location, MetalTagEnum metal, BookEntryModel parent) {
        helper.entry(metal.getNameLower() + "_entry"); //tell our lang helper the entry we are in

        // PAGINA
        helper.page("power_storage");                           //and now the page
        BookTextPageModel page =
                BookTextPageModel.builder()
                        .withText(helper.pageText()) //lang key for the text
                        .withTitle(helper.pageTitle()) //and for the title
                        .build();
        // PAGINA
        helper.page("power_tap"); //and now the page
        BookTextPageModel page2 =
                BookTextPageModel.builder()
                        .withText(helper.pageText()) //lang key for the text
                        .withTitle(helper.pageTitle()) //and for the title
                        .build();


        return BookEntryModel.builder()
                .withId(this.modLoc(helper.category + "/" + helper.entry))  //make entry id from lang helper data
                .withName(helper.entryName())                                    //entry name lang key
                .withDescription(helper.entryDescription())                     //entry description lang key
                .withIcon("metallics_arts:" + metal.getNameLower() + "_feruchemic_icon")                                  //we use furnace as icon
                .withLocation(entryHelper.get(location))                        //and we place it at the location we defined earlier in the entry helper mapping
                .withParent(parent)
                .withPages(page,page2)                                           //finally we add our pages to the entry
                .build();
    }

    private BookEntryModel welcomePowerEntry(BookLangHelper helper, EntryLocationHelper entryHelper, char location, SubdivisionData subdivisionData) {
        helper.entry(subdivisionData.getId() + "_entry"); //tell our lang helper the entry we are in

        // PAGINA
        helper.page("page1"); //and now the page
        BookTextPageModel page =
                BookTextPageModel.builder()
                        .withText(helper.pageText()) //lang key for the text
                        .withTitle(helper.pageTitle()) //and for the title
                        .build();
        // PAGINA
        helper.page("page2"); //and now the page
        BookTextPageModel page2 =
                BookTextPageModel.builder()
                        .withText(helper.pageText()) //lang key for the text
                        .withTitle(helper.pageTitle()) //and for the title
                        .build();


        return BookEntryModel.builder()
                .withId(this.modLoc(helper.category + "/" + helper.entry))      //make entry id from lang helper data
                .withName(helper.entryName())                                       //entry name lang key
                .withDescription(helper.entryDescription())                         //entry description lang key
                .withIcon("minecraft:paper")                                        //we use furnace as icon
                .withLocation(entryHelper.get(location))                            //and we place it at the location we defined earlier in the entry helper mapping
                .withPages(page,page2)                                              //finally we add our pages to the entry
                .build();
    }

    @Override
    protected void generate() {
        //call our code that generates a book with the id "demo"
        BookModel demoBook = this.makeDemoBook("metallics_arts_book");

        //then add the book to the list of books to generate
        this.add(demoBook);
    }
}
