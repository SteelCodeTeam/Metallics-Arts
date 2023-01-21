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
import net.minecraftforge.common.data.LanguageProvider;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.enums.implementations.languages.book.MultiCraftEntry;
import net.rudahee.metallics_arts.data.enums.implementations.languages.book.SubdivisionEntry;
import net.rudahee.metallics_arts.data.enums.implementations.languages.book.WeaponsEntry;
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
                .withModel(new ResourceLocation("modonomicon:modonomicon_red")) //use the default red modonomicon icon for the book
                .withCreativeTab("modonomicon") //and put it in the modonomicon tab
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
                "_______________s__________k_____",
                "________________________________",
                "__________c_________b______l____",
                "________________________________",
                "_______________i__________m_____",
                "________________________________",
                "_________________________n___o__",
                "________________________________",
                "________________________________"
        );

        BookEntryModel s = this.welcomePowerEntry(helper, entryHelper, 's', "presentacion");
        BookEntryParentModel sParent = BookEntryParentModel.builder().withEntryId(s.getId()).build();

        BookEntryModel a = this.subDivisionEntry(helper, entryHelper, 'a', SubdivisionEntry.WEAPONS, sParent);
        BookEntryParentModel aParent = BookEntryParentModel.builder().withEntryId(a.getId()).build();

        BookEntryModel b = this.subDivisionEntry(helper, entryHelper, 'b', SubdivisionEntry.CAFTING, sParent);
        BookEntryParentModel bParent = BookEntryParentModel.builder().withEntryId(b.getId()).build();

        BookEntryModel c = this.subDivisionEntry(helper, entryHelper, 'c', SubdivisionEntry.ALLOY_FURNACE, sParent);

        BookEntryModel d = this.weaponsEntry(helper, entryHelper, 'd', aParent, WeaponsEntry.CRISTAL_DAGGER);
        BookEntryModel e = this.weaponsEntry(helper, entryHelper, 'e', aParent, WeaponsEntry.OBSIDIAN_DAGGER);
        BookEntryModel f = this.weaponsEntry(helper, entryHelper, 'f', aParent, WeaponsEntry.OBSIDIAN_AXE);
        BookEntryModel g = this.weaponsEntry(helper, entryHelper, 'g', aParent, WeaponsEntry.KOLOSS_BLADE);
        BookEntryModel h = this.weaponsEntry(helper, entryHelper, 'h', aParent, WeaponsEntry.DUELING_STAFF);


        BookEntryModel j = this.multiCraftsItemsEntry(helper, entryHelper, 'j', bParent, MultiCraftEntry.VIALS, GetItemsUtils.getVialsList());
        //BookEntryModel k = this.multiCraftsItemsEntry(helper, entryHelper, 'l',
          //     etRingList());
        BookEntryModel l = this.multiCraftsItemsEntry(helper, entryHelper, 'l', bParent, MultiCraftEntry.BANDS, GetItemsUtils.getBandList());
        BookEntryModel m = this.multiCraftsItemsEntry(helper, entryHelper, 'm', bParent, MultiCraftEntry.SPIKES, GetItemsUtils.getSpikesList());
        BookEntryModel n = this.multiCraftsItemsEntry(helper, entryHelper, 'n', bParent, MultiCraftEntry.ICONS, GetItemsUtils.getIconsList());
        BookEntryModel i = this.multiCraftsItemsEntry(helper, entryHelper, 'i', bParent, MultiCraftEntry.ALLOYS, GetItemsUtils.getAlloysList());

        BookEntryParentModel nParent = BookEntryParentModel.builder().withEntryId(n.getId()).build();
        BookEntryModel o = this.multiCraftsItemsEntry(helper, entryHelper, 'o', nParent, MultiCraftEntry.PATTERNS, GetItemsUtils.getPatterns());

        return BookCategoryModel.builder()
                .withId(this.modLoc(helper.category)) //the id of the category, as stored in the lang helper. modLoc() prepends the mod id.
                .withName(helper.categoryName()) //the name of the category. The lang helper gives us the correct translation key.
                .withIcon("minecraft:book") //the icon for the category. In this case we simply use an existing item.
                .withEntries(a,b,c,d,e,f,g,h,i,j,l,m,n,o,s)
                .build();
    }

    private BookEntryModel multiCraftsItemsEntry(BookLangHelper helper, EntryLocationHelper entryHelper, char location, BookEntryParentModel parent, MultiCraftEntry multiCraftEntry, ArrayList<String> recipeList) {
        helper.entry(multiCraftEntry.getId() + "_entry"); //tell our lang helper the entry we are in
        ArrayList<BookPageModel> list = new ArrayList<>();
        // PAGINA
        helper.page("items_description"); //and now the page
        BookTextPageModel page =
                BookTextPageModel.builder()                     //we start with a text page
                        .withText(helper.pageText())            //lang key for the text         TEXTO
                        .withTitle(helper.pageTitle())          //and for the title             TITULO
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
                .withParent(parent)
                .withPages(list.toArray(new BookPageModel[0]))                                                //finally we add our pages to the entry
                .build();
    }

    private BookEntryModel weaponsEntry(BookLangHelper helper, EntryLocationHelper entryHelper, char location, BookEntryParentModel parent, WeaponsEntry weaponsEntry) {
        helper.entry(weaponsEntry.getId() + "_entry"); //tell our lang helper the entry we are in

        // PAGINA
        helper.page("weapon_description"); //and now the page
        BookTextPageModel page =
                BookTextPageModel.builder()                     //we start with a text page
                        .withText(helper.pageText())            //lang key for the text         TEXTO
                        .withTitle(helper.pageTitle())          //and for the title             TITULO
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

    private BookEntryModel subDivisionEntry(BookLangHelper helper, EntryLocationHelper entryHelper, char location, SubdivisionEntry subdivisionEntry, BookEntryParentModel parent) {
        helper.entry(subdivisionEntry.getId() + "_entry"); //tell our lang helper the entry we are in

        // PAGINA
        helper.page("page"); //and now the page
        BookTextPageModel page =
                BookTextPageModel.builder()                     //we start with a text page
                        .withText(helper.pageText())            //lang key for the text         TEXTO
                        .withTitle(helper.pageTitle())          //and for the title             TITULO
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
                "________p_____________h________"
        );

        BookEntryModel s = this.welcomePowerEntry(helper, entryHelper, 's', "welcome");
        BookEntryParentModel sParent = BookEntryParentModel.builder().withEntryId(s.getId()).build();

        BookEntryModel r = this.subDivisionEntry(helper, entryHelper, 'r', SubdivisionEntry.PHYSICAL, sParent);
        BookEntryModel t = this.subDivisionEntry(helper, entryHelper, 't', SubdivisionEntry.ENHANCEMENT, sParent);
        BookEntryModel v = this.subDivisionEntry(helper, entryHelper, 'v', SubdivisionEntry.TEMPORAL, sParent);
        BookEntryModel q = this.subDivisionEntry(helper, entryHelper, 'q', SubdivisionEntry.COGNITIVE, sParent);
        BookEntryParentModel rParent = BookEntryParentModel.builder().withEntryId(r.getId()).build();
        BookEntryParentModel vParent = BookEntryParentModel.builder().withEntryId(v.getId()).build();
        BookEntryParentModel tParent = BookEntryParentModel.builder().withEntryId(t.getId()).build();
        BookEntryParentModel qParent = BookEntryParentModel.builder().withEntryId(q.getId()).build();

        BookEntryModel a = this.allomancyPowerEntry(helper, entryHelper, 'a',MetalTagEnum.IRON, rParent);
        BookEntryModel b = this.allomancyPowerEntry(helper, entryHelper, 'b',MetalTagEnum.STEEL, rParent);
        BookEntryModel c = this.allomancyPowerEntry(helper, entryHelper, 'c',MetalTagEnum.TIN, rParent);
        BookEntryModel d = this.allomancyPowerEntry(helper, entryHelper, 'd',MetalTagEnum.PEWTER, rParent);

        BookEntryModel e = this.allomancyPowerEntry(helper, entryHelper, 'e',MetalTagEnum.GOLD, vParent);
        BookEntryModel f = this.allomancyPowerEntry(helper, entryHelper, 'f',MetalTagEnum.ELECTRUM, vParent);
        BookEntryModel g = this.allomancyPowerEntry(helper, entryHelper, 'g',MetalTagEnum.CADMIUM, vParent);
        BookEntryModel h = this.allomancyPowerEntry(helper, entryHelper, 'h',MetalTagEnum.BENDALLOY, vParent);

        BookEntryModel i = this.allomancyPowerEntry(helper, entryHelper, 'i',MetalTagEnum.COPPER, qParent);
        BookEntryModel j = this.allomancyPowerEntry(helper, entryHelper, 'j',MetalTagEnum.BRONZE, qParent);
        BookEntryModel k = this.allomancyPowerEntry(helper, entryHelper, 'k',MetalTagEnum.ZINC, qParent);
        BookEntryModel l = this.allomancyPowerEntry(helper, entryHelper, 'l',MetalTagEnum.BRASS, qParent);

        BookEntryModel m = this.allomancyPowerEntry(helper, entryHelper, 'm',MetalTagEnum.ALUMINUM, tParent);
        BookEntryModel n = this.allomancyPowerEntry(helper, entryHelper, 'n',MetalTagEnum.DURALUMIN, tParent);
        BookEntryModel o = this.allomancyPowerEntry(helper, entryHelper, 'o',MetalTagEnum.CHROMIUM, tParent);
        BookEntryModel p = this.allomancyPowerEntry(helper, entryHelper, 'p',MetalTagEnum.NICROSIL, tParent);


        return BookCategoryModel.builder()
                .withId(this.modLoc(helper.category))
                .withName(helper.categoryName())
                .withIcon("minecraft:iron_ingot")
                .withEntries(a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,v)
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
                "________p_____________h________"
        );

        BookEntryModel s = this.welcomePowerEntry(helper, entryHelper, 's', "welcome");

        BookEntryParentModel sParent = BookEntryParentModel.builder().withEntryId(s.getId()).build();

        BookEntryModel r = this.subDivisionEntry(helper, entryHelper, 'r', SubdivisionEntry.PHYSICAL, sParent);
        BookEntryModel t = this.subDivisionEntry(helper, entryHelper, 't', SubdivisionEntry.SPIRITUAL, sParent);
        BookEntryModel v = this.subDivisionEntry(helper, entryHelper, 'v', SubdivisionEntry.HYBRID, sParent);
        BookEntryModel q = this.subDivisionEntry(helper, entryHelper, 'q', SubdivisionEntry.COGNITIVE, sParent);

        BookEntryParentModel rParent = BookEntryParentModel.builder().withEntryId(r.getId()).build();
        BookEntryParentModel vParent = BookEntryParentModel.builder().withEntryId(v.getId()).build();
        BookEntryParentModel tParent = BookEntryParentModel.builder().withEntryId(t.getId()).build();
        BookEntryParentModel qParent = BookEntryParentModel.builder().withEntryId(q.getId()).build();


        BookEntryModel a = this.feruchemyPowerEntry(helper, entryHelper, 'a',MetalTagEnum.IRON, rParent);  //ENTRADAS : CADA ICONO DEL MAPA
        BookEntryModel b = this.feruchemyPowerEntry(helper, entryHelper, 'b',MetalTagEnum.STEEL, rParent);
        BookEntryModel c = this.feruchemyPowerEntry(helper, entryHelper, 'c',MetalTagEnum.TIN, rParent);
        BookEntryModel d = this.feruchemyPowerEntry(helper, entryHelper, 'd',MetalTagEnum.PEWTER, rParent);

        BookEntryModel e = this.feruchemyPowerEntry(helper, entryHelper, 'e',MetalTagEnum.GOLD, vParent);
        BookEntryModel f = this.feruchemyPowerEntry(helper, entryHelper, 'f',MetalTagEnum.ELECTRUM, vParent);
        BookEntryModel g = this.feruchemyPowerEntry(helper, entryHelper, 'g',MetalTagEnum.CADMIUM, vParent);
        BookEntryModel h = this.feruchemyPowerEntry(helper, entryHelper, 'h',MetalTagEnum.BENDALLOY, vParent);

        BookEntryModel i = this.feruchemyPowerEntry(helper, entryHelper, 'i',MetalTagEnum.COPPER, qParent);
        BookEntryModel j = this.feruchemyPowerEntry(helper, entryHelper, 'j',MetalTagEnum.BRONZE, qParent);
        BookEntryModel k = this.feruchemyPowerEntry(helper, entryHelper, 'k',MetalTagEnum.ZINC, qParent);
        BookEntryModel l = this.feruchemyPowerEntry(helper, entryHelper, 'l',MetalTagEnum.BRASS, qParent);

        BookEntryModel m = this.feruchemyPowerEntry(helper, entryHelper, 'm',MetalTagEnum.ALUMINUM, tParent);
        BookEntryModel n = this.feruchemyPowerEntry(helper, entryHelper, 'n',MetalTagEnum.DURALUMIN, tParent);
        BookEntryModel o = this.feruchemyPowerEntry(helper, entryHelper, 'o',MetalTagEnum.CHROMIUM, tParent);
        BookEntryModel p = this.feruchemyPowerEntry(helper, entryHelper, 'p',MetalTagEnum.NICROSIL, tParent);




        //lista de entradas

        return BookCategoryModel.builder()
                .withId(this.modLoc(helper.category)) //the id of the category, as stored in the lang helper. modLoc() prepends the mod id.
                .withName(helper.categoryName()) //the name of the category. The lang helper gives us the correct translation key.
                .withIcon("minecraft:gold_ingot") //the icon for the category. In this case we simply use an existing item.
                .withEntries(a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,v)
                .build();
    }

    private BookEntryModel allomancyPowerEntry(BookLangHelper helper, EntryLocationHelper entryHelper, char location, MetalTagEnum metal, BookEntryParentModel parent) {
        helper.entry(metal.getNameLower() + "_entry"); //tell our lang helper the entry we are in

        // PAGINA
        helper.page("power_description"); //and now the page
        BookTextPageModel page =
                BookTextPageModel.builder()                     //we start with a text page
                        .withText(helper.pageText())            //lang key for the text         TEXTO
                        .withTitle(helper.pageTitle())          //and for the title             TITULO
                        .build();
        // PAGINA
        helper.page("power_interactions"); //and now the page
        BookTextPageModel page2 =
                BookTextPageModel.builder()                     //we start with a text page
                        .withText(helper.pageText())            //lang key for the text         TEXTO
                        .withTitle(helper.pageTitle())          //and for the title             TITULO
                        .build();


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

    private BookEntryModel feruchemyPowerEntry(BookLangHelper helper, EntryLocationHelper entryHelper, char location, MetalTagEnum metal, BookEntryParentModel parent) {
        helper.entry(metal.getNameLower() + "_entry"); //tell our lang helper the entry we are in

        // PAGINA
        helper.page("power_storage");                           //and now the page
        BookTextPageModel page =
                BookTextPageModel.builder()                     //we start with a text page
                        .withText(helper.pageText())            //lang key for the text         TEXTO
                        .withTitle(helper.pageTitle())          //and for the title             TITULO
                        .build();
        // PAGINA
        helper.page("power_tap"); //and now the page
        BookTextPageModel page2 =
                BookTextPageModel.builder()                     //we start with a text page
                        .withText(helper.pageText())            //lang key for the text         TEXTO
                        .withTitle(helper.pageTitle())          //and for the title             TITULO
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



    private BookEntryModel welcomePowerEntry(BookLangHelper helper, EntryLocationHelper entryHelper, char location, String key) {
        helper.entry(key + "_entry"); //tell our lang helper the entry we are in

        // PAGINA
        helper.page("power_description"); //and now the page
        BookTextPageModel page =
                BookTextPageModel.builder()                     //we start with a text page
                        .withText(helper.pageText())            //lang key for the text         TEXTO
                        .withTitle(helper.pageTitle())          //and for the title             TITULO
                        .build();
        // PAGINA
        helper.page("power_interactions"); //and now the page
        BookTextPageModel page2 =
                BookTextPageModel.builder()                     //we start with a text page
                        .withText(helper.pageText())            //lang key for the text         TEXTO
                        .withTitle(helper.pageTitle())          //and for the title             TITULO
                        .build();


        return BookEntryModel.builder()
                .withId(this.modLoc(helper.category + "/" + helper.entry))  //make entry id from lang helper data
                .withName(helper.entryName())                                    //entry name lang key
                .withDescription(helper.entryDescription())                     //entry description lang key
                .withIcon("minecraft:furnace")                                  //we use furnace as icon
                .withLocation(entryHelper.get(location))                        //and we place it at the location we defined earlier in the entry helper mapping
                .withPages(page,page2)                                                //finally we add our pages to the entry
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
