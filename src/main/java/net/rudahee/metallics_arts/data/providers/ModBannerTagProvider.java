package net.rudahee.metallics_arts.data.providers;

import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.data.enums.implementations.MetalsNBTData;
import net.rudahee.metallics_arts.setup.registries.ModBannersRegister;

public class ModBannerTagProvider extends TagsProvider<BannerPattern> {

    public ModBannerTagProvider(DataGenerator pGenerator, net.minecraftforge.common.data.ExistingFileHelper existingFileHelper) {
        super(pGenerator, Registry.BANNER_PATTERN, MetallicsArts.MOD_ID, existingFileHelper);
    }

    protected void addTags() {

        for (MetalsNBTData metal : MetalsNBTData.values()) {
            MetallicsArts.LOGGER.debug("Creating banner tag for " + metal.getNameLower());

            this.tag(ModBannersRegister.PATTERNS_KEYS.get("a_"+metal.getNameLower())).add(ModBannersRegister.PATTERNS.get("a_"+metal.getNameLower()).get()).add(ModBannersRegister.PATTERNS.get("a_"+metal.getNameLower()+"_2").get());

            this.tag(ModBannersRegister.PATTERNS_KEYS.get("f_"+metal.getNameLower())).add(ModBannersRegister.PATTERNS.get("f_"+metal.getNameLower()).get()).add(ModBannersRegister.PATTERNS.get("f_"+metal.getNameLower()+"_2").get());

        }
    }

}
