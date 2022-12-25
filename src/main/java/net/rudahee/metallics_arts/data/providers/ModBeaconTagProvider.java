package net.rudahee.metallics_arts.data.providers;

import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.setup.registries.ModBlocks;

public class ModBeaconTagProvider extends TagsProvider<Block> {
    public ModBeaconTagProvider(DataGenerator gen, net.minecraftforge.common.data.ExistingFileHelper existingFileHelper) {
        super(gen,Registry.BLOCK,MetallicsArts.MOD_ID,existingFileHelper);
    }

    @Override
    protected void addTags() {
        for (Block block: ModBlocks.BLOCK_METAL_BLOCKS.values()) {
            this.tag(BlockTags.BEACON_BASE_BLOCKS).add(block);
        }
        for (Block block: ModBlocks.BLOCK_GEMS_BLOCKS.values()) {
            this.tag(BlockTags.BEACON_BASE_BLOCKS).add(block);
        }

    }
}
