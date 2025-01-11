package net.rudahee.metallics_arts.data.providers.tags_providers;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagEntry;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.data.enums.implementations.languages.old.MetalAuxiliaryInfo;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

/**
 * A custom class for adding tags to beacon base blocks in the mod.
 *
 * @author SteelCode Team
 * @since 1.5.1
 */
public class ModBeaconTagProvider extends TagsProvider<Block> {
    /**
     * Constructs a new ModBeaconTagProvider instance.
     *
     * @param existingFileHelper the existing file helper to assist with data generation
     */
    public ModBeaconTagProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(packOutput, Registries.BLOCK, lookupProvider, MetallicsArts.MOD_ID, existingFileHelper);
    }



    /**
     * Adds tags for custom mod blocks to be used as beacon base blocks.
     */

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        for (MetalAuxiliaryInfo metal : MetalAuxiliaryInfo.values()) {
            if (!metal.isVanilla()) {
                this.tag(BlockTags.BEACON_BASE_BLOCKS).add(TagEntry.tag(new ResourceLocation("forge", "storage_blocks/" + metal.getId())));
            }
        }
    }
}
