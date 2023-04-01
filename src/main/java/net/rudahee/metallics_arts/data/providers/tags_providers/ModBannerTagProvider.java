package net.rudahee.metallics_arts.data.providers.tags_providers;

import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.setup.registries.ModBannersRegister;

/**
 * A custom class for adding tags to banner patterns in the mod.
 *
 * @author SteelCode Team
 * @since 1.5.1
 */
public class ModBannerTagProvider extends TagsProvider<BannerPattern> {

    /**
     * Constructs a new ModBannerTagProvider instance.
     *
     * @param pGenerator        the data generator for tag generation
     * @param existingFileHelper the existing file helper to assist with data generation
     */
    public ModBannerTagProvider(DataGenerator pGenerator, net.minecraftforge.common.data.ExistingFileHelper existingFileHelper) {
        super(pGenerator, Registry.BANNER_PATTERN, MetallicsArts.MOD_ID, existingFileHelper);
    }

    /**
     * Adds tags for custom mod banner patterns.
     */
    protected void addTags() {

        for (MetalTagEnum metal : MetalTagEnum.values()) {
            MetallicsArts.LOGGER.debug("Creating banner tag for " + metal.getNameLower());

            this.tag(ModBannersRegister.PATTERNS_KEYS.get("a_"+metal.getNameLower())).add(ModBannersRegister.PATTERNS.get("a_"+metal.getNameLower()).get()).add(ModBannersRegister.PATTERNS.get("a_"+metal.getNameLower()+"_2").get());

            this.tag(ModBannersRegister.PATTERNS_KEYS.get("f_"+metal.getNameLower())).add(ModBannersRegister.PATTERNS.get("f_"+metal.getNameLower()).get()).add(ModBannersRegister.PATTERNS.get("f_"+metal.getNameLower()+"_2").get());

        }
    }
}
