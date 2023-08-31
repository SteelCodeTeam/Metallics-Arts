package net.rudahee.metallics_arts.data.providers.tags_providers;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.setup.registries.ModBannersRegister;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

/**
 * A custom class for adding tags to banner patterns in the mod.
 *
 * @author SteelCode Team
 * @since 1.5.1
 */
public class ModBannerTagProvider extends TagsProvider<BannerPattern> {

    public ModBannerTagProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> completableFuture, @Nullable ExistingFileHelper existingFileHelper) {
        super(packOutput, Registries.BANNER_PATTERN, completableFuture, MetallicsArts.MOD_ID, existingFileHelper);
    }

    /**
     * Adds tags for custom mod banner patterns.
     */

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        for (MetalTagEnum metal : MetalTagEnum.values()) {
            MetallicsArts.LOGGER.debug("Creating banner tag for " + metal.getNameLower());

            this.tag(ModBannersRegister.PATTERNS_KEYS.get("a_"+metal.getNameLower())).add(ModBannersRegister.PATTERNS.get("a_"+metal.getNameLower()).getKey()).add(ModBannersRegister.PATTERNS.get("a_"+metal.getNameLower()+"_2").getKey());
            this.tag(ModBannersRegister.PATTERNS_KEYS.get("f_"+metal.getNameLower())).add(ModBannersRegister.PATTERNS.get("f_"+metal.getNameLower()).getKey()).add(ModBannersRegister.PATTERNS.get("f_"+metal.getNameLower()+"_2").getKey());

        }
    }
}
