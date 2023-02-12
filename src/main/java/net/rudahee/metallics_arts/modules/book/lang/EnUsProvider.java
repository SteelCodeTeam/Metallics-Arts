/*
 * SPDX-FileCopyrightText: 2022 klikli-dev
 *
 * SPDX-License-Identifier: MIT
 */

package net.rudahee.metallics_arts.modules.book.lang;

import com.klikli_dev.modonomicon.api.ModonomiconAPI;
import com.klikli_dev.modonomicon.api.datagen.BookLangHelper;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.enums.implementations.languages.*;
import net.rudahee.metallics_arts.data.enums.implementations.languages.book.allomancy.PowerDescriptions;
import net.rudahee.metallics_arts.data.enums.implementations.languages.book.allomancy.Interactions;
import net.rudahee.metallics_arts.data.enums.implementations.languages.book.feruchemic.Storage;
import net.rudahee.metallics_arts.data.enums.implementations.languages.book.feruchemic.Tap;
import net.rudahee.metallics_arts.data.enums.implementations.languages.book.multi_craft.MultiCaftDescriptions;
import net.rudahee.metallics_arts.data.enums.implementations.languages.book.multi_craft.MultiCraftData;
import net.rudahee.metallics_arts.data.enums.implementations.languages.book.sub_division.SubdivisionData;
import net.rudahee.metallics_arts.data.enums.implementations.languages.book.sub_division.SubdivisionDescription;
import net.rudahee.metallics_arts.data.enums.implementations.languages.book.weapons.WeaponDescriptions;
import net.rudahee.metallics_arts.data.enums.implementations.languages.book.weapons.WeaponsData;

public class EnUsProvider extends LanguageProvider {

    public EnUsProvider(DataGenerator generator, String modid) {
        super(generator, modid, "en_us");
    }

    private void addDemoBook(){
        //We again set up a lang helper to keep track of the translation keys for us.
        //Forge language provider does not give us access to this.modid, so we get it from our main mod class
        BookLangHelper helper = ModonomiconAPI.get().getLangHelper(MetallicsArts.MOD_ID);
        helper.book("metallics_arts_book"); //we tell the helper the book we're in.
        this.add(helper.bookName(), CTW.METALLICS_ARTS.getNameInEnglish() + ": " + CTW.GUIDE.getNameInEnglish()); //and now we add the actual textual book name
        this.add(helper.bookTooltip(), "Libro de poderes"); //and the tooltip text

        this.addAllomancyCategory(helper);
        this.feruchemyCategory(helper);
        this.addIntroCategory(helper);

    }

    public void addIntroCategory(BookLangHelper helper){
        helper.category("intro");
        this.add(helper.categoryName(), "Intro");
        for (SubdivisionData entry: SubdivisionData.values()) {
            if (!entry.isAllomantic() && !entry.isFeruchemical()){
                addIntroEntry(helper,entry);
            }
        }
        for (WeaponsData entry : WeaponsData.values()) {
            addWeaponsEntry(helper, entry);
        }
        for (MultiCraftData entry: MultiCraftData.values()) {
            addMultiCraftEntry(helper,entry);
        }
    }

    private void addIntroEntry(BookLangHelper helper, SubdivisionData subdivisionEntry) {
        helper.entry(subdivisionEntry.getId() + "_entry");
        this.add(helper.entryName(), CTW.valueOf(subdivisionEntry.name()).getNameInEnglish());
        this.add(helper.entryDescription(), "");

        helper.page("page"); //now we configure the intro page
        this.add(helper.pageTitle(), CTW.valueOf(subdivisionEntry.name()).getNameInEnglish());  //page title
        this.add(helper.pageText(), SubdivisionDescription.valueOf(subdivisionEntry.name()).getEnglish());

    }
    private void addWeaponsEntry(BookLangHelper helper, WeaponsData weaponsEntry) {
        helper.entry(weaponsEntry.getId() + "_entry");
        this.add(helper.entryName(), CTW.valueOf(weaponsEntry.name()).getNameInEnglish());
        this.add(helper.entryDescription(), "");

        helper.page("weapon_description"); //now we configure the intro page
        this.add(helper.pageTitle(), CTW.valueOf(weaponsEntry.name()).getNameInEnglish());  //page title
        this.add(helper.pageText(), WeaponDescriptions.valueOf(weaponsEntry.name()).getEnglish());

    }
    private void addMultiCraftEntry(BookLangHelper helper, MultiCraftData multiCraftData) {
        helper.entry(multiCraftData.getId() + "_entry");
        this.add(helper.entryName(),CTW.valueOf(multiCraftData.name()).getNameInEnglish());
        this.add(helper.entryDescription(), "");

        helper.page("items_description"); //now we configure the intro page
        this.add(helper.pageTitle(), CTW.valueOf(multiCraftData.name()).getNameInEnglish());  //page title
        this.add(helper.pageText(), MultiCaftDescriptions.valueOf(multiCraftData.name()).getEnglish());
    }

    private void addAllomancyCategory(BookLangHelper helper) {
        helper.category("allomancy");                                       //tell the helper the category we are in
        this.add(helper.categoryName(), CTW.ALLOMANCY.getNameInEnglish()); //and provide the category name text

        for (SubdivisionData oM: SubdivisionData.values()) {
            if (oM.isAllomantic()){
                addIntroEntry(helper,oM);
            }
        }
        for (MetalTagEnum metal: MetalTagEnum.values()) {
            this.addAllomancyEntry(helper, metal);
        }

    }
    private void feruchemyCategory(BookLangHelper helper) {
        helper.category("feruchemy");                //tell the helper the category we are in
        this.add(helper.categoryName(), CTW.FERUCHEMY.getNameInEnglish()); //and provide the category name text

        for (SubdivisionData oM: SubdivisionData.values()) {
            if (oM.isFeruchemical()){
                addIntroEntry(helper,oM);
            }
        }
        for (MetalTagEnum metal: MetalTagEnum.values()) {
            this.addFeruchemyEntry(helper, metal);
        }
    }

    private void addAllomancyEntry(BookLangHelper helper, MetalTagEnum metal) {
        helper.entry(metal.getNameLower() + "_entry"); //tell the helper the entry we are in

        this.add(helper.entryName(), MetalNamesEnum.valueOf(metal.name()).getNameInEnglish());  //provide the entry name
        this.add(helper.entryDescription(), "");                 //and description

        helper.page("power_description"); //now we configure the intro page
        this.add(helper.pageTitle(), MetalNamesEnum.valueOf(metal.name()).getNameInEnglish());  //page title
        this.add(helper.pageText(), PowerDescriptions.valueOf(metal.name()).getEnglish());    //page text

        helper.page("power_interactions");
        this.add(helper.pageTitle(), "Interactions");                                           //page title
        this.add(helper.pageText(), Interactions.valueOf(metal.name()).getEnglish());   //page text
    }

    private void addFeruchemyEntry(BookLangHelper helper, MetalTagEnum metal) {
        helper.entry(metal.getNameLower() + "_entry");                                          //tell the helper the entry we are in
        this.add(helper.entryName(), MetalNamesEnum.valueOf(metal.name()).getNameInEnglish());  //provide the entry name
        this.add(helper.entryDescription(), "");                 //and description

        helper.page("power_storage"); //now we configure the intro page
        this.add(helper.pageTitle(), MetalNamesEnum.valueOf(metal.name()).getNameInEnglish());  //page title
        this.add(helper.pageText(), Storage.valueOf(metal.name()).getEnglish());    //page text

        helper.page("power_tap");
        this.add(helper.pageTitle(), "");                           //page title
        this.add(helper.pageText(), Tap.valueOf(metal.name()).getEnglish());   //page text
    }


    protected void addTranslations() {
        this.addDemoBook();
    }
}
