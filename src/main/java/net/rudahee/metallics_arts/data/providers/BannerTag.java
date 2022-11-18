package net.rudahee.metallics_arts.data.providers;

import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.modules.items.banners.Banners;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;

public class BannerTag extends TagsProvider<BannerPattern> {

    public BannerTag(DataGenerator pGenerator, net.minecraftforge.common.data.ExistingFileHelper existingFileHelper) {
        super(pGenerator, Registry.BANNER_PATTERN, MetallicsArts.MOD_ID, existingFileHelper);
    }

    protected void addTags() {

        for (MetalsNBTData metal : MetalsNBTData.values()) {
            MetallicsArts.LOGGER.debug("Creating banner tag for " + metal.getNameLower());

            this.tag(Banners.PATTERNS_KEYS.get("a_"+metal.getNameLower())).add(Banners.PATTERNS.get("a_"+metal.getNameLower()).get()).add(Banners.PATTERNS.get("a_"+metal.getNameLower()+"_2").get());

            this.tag(Banners.PATTERNS_KEYS.get("f_"+metal.getNameLower())).add(Banners.PATTERNS.get("f_"+metal.getNameLower()).get()).add(Banners.PATTERNS.get("f_"+metal.getNameLower()+"_2").get());

        }
    }

}
