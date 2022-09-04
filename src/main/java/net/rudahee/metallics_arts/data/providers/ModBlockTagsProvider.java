package net.rudahee.metallics_arts.data.providers;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.setup.registries.ModBlock;

public class ModBlockTagsProvider extends BlockTagsProvider {


    public ModBlockTagsProvider(DataGenerator gen, String modid, ExistingFileHelper exFileHelper) {
        super(gen, modid, exFileHelper);
    }

    @Override
    protected void addTags() {
        for (String key: ModBlock.BLOCK_METAL_BLOCKS.keySet()) {
            if (ModBlock.BLOCK_METAL_ORES.containsKey(key)) {
                Block ore = ModBlock.BLOCK_METAL_ORES.get(key);
                addForgeTag("ores/"+key,ore);
                makePickaxeMineable(ore);
            }
            if (ModBlock.BLOCK_METAL_DEEPSLATE_ORES.containsKey(key)) {
                Block ds = ModBlock.BLOCK_METAL_DEEPSLATE_ORES.get(key);
                addForgeTag("deepslate_ores/"+key,ds);
                makePickaxeMineable(ds);
            }
            if (ModBlock.RAW_METAL_BLOCKS.containsKey(key)) {
                Block raw = ModBlock.RAW_METAL_BLOCKS.get(key);
                addForgeTag("raw_block/"+key,raw);
                makePickaxeMineable(raw);
            }
            addForgeTag("block/"+key,ModBlock.BLOCK_METAL_BLOCKS.get(key));
            makePickaxeMineable(ModBlock.BLOCK_METAL_BLOCKS.get(key));
        }
        for (String key : ModBlock.BLOCK_GEMS_BLOCKS.keySet()) {
            addForgeTag("block/" + key, ModBlock.BLOCK_GEMS_BLOCKS.get(key));
            makePickaxeMineable(ModBlock.BLOCK_GEMS_BLOCKS.get(key));
        }

    }

    private void addForgeTag(String name, Block... items) {
        MetallicsArts.LOGGER.debug("Creating block tag for forge:" + name);
        tag(net.minecraft.tags.BlockTags.create(new ResourceLocation("forge", name))).replace(false).add(items);
    }

    private void makePickaxeMineable(Block... items) {
        addTag("mineable/pickaxe", items);
        addTag("needs_iron_tool", items);
    }

    private void addTag(String name, Block... items) {
        MetallicsArts.LOGGER.debug("Creating block tag for minecraft:" + name);
        tag(net.minecraft.tags.BlockTags.create(new ResourceLocation("minecraft", name))).replace(false).add(items);
    }

    @Override
    public String getName() {
        return "Metallic Arts Block Tags";
    }

}