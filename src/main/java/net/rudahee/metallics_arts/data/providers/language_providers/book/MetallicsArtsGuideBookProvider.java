/*
 * SPDX-FileCopyrightText: 2022 klikli-dev
 *
 * SPDX-License-Identifier: MIT
 */

package net.rudahee.metallics_arts.data.providers.language_providers.book;

import com.klikli_dev.modonomicon.api.ModonomiconAPI;
import com.klikli_dev.modonomicon.api.datagen.BookLangHelper;
import com.klikli_dev.modonomicon.api.datagen.BookProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookModel;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.CreativeModeTabRegistry;
import net.minecraftforge.common.data.LanguageProvider;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.setup.tabs.ModCreativeTabs;


public class MetallicsArtsGuideBookProvider extends BookProvider {


    public MetallicsArtsGuideBookProvider(DataGenerator generator, String modid, LanguageProvider lang) {
        super(generator.getPackOutput(), modid, lang);
    }


    private BookModel makeModel() {

        BookLangHelper helper = ModonomiconAPI.get().getLangHelper(this.modid);
        helper.book("metallics_arts.guide");

        return BookModel.create(this.modLoc("metallics_arts_guide"), "title.metallics_arts.guide")
                .withTooltip("tooltip.metallics_arts.guide")
                .withCreativeTab(new ResourceLocation(MetallicsArts.MOD_ID, "metallics_arts_tab"))
                .withGenerateBookItem(true)
                .withModel(this.modLoc("metallics_arts_guide"));
    }

    @Override
    protected void generate() {
        BookModel model = makeModel();
        this.add(model);
    }
}
