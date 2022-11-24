package net.rudahee.metallics_arts.data.providers;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;
import net.rudahee.metallics_arts.MetallicsArts;

public class ModLanguageProvider extends LanguageProvider {

    public ModLanguageProvider(DataGenerator gen, String modid, String locale) {
        super(gen, MetallicsArts.MOD_ID, "es_es");
    }

    @Override
    protected void addTranslations() {
        add("item.banner.red.pattern.ettmetal", "Banner Rojo Ettmetal");
    }
}
