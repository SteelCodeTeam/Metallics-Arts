package net.rudahee.metallics_arts.data.providers.tags_providers;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.setup.registries.ModBlocksRegister;

public class ModBlockTagProvider extends BlockTagsProvider {


    public ModBlockTagProvider(DataGenerator gen, String modid, ExistingFileHelper exFileHelper) {
        super(gen, modid, exFileHelper);
    }

    @Override
    protected void addTags() {
        for (String key: ModBlocksRegister.BLOCK_METAL_BLOCKS.keySet()) {
            if (ModBlocksRegister.BLOCK_METAL_ORES.containsKey(key)) {
                Block ore = ModBlocksRegister.BLOCK_METAL_ORES.get(key);
                addForgeTag("ores/"+key,ore);
                makePickaxeMineable(ore);
            }
            if (ModBlocksRegister.BLOCK_METAL_DEEPSLATE_ORES.containsKey(key)) {
                Block ds = ModBlocksRegister.BLOCK_METAL_DEEPSLATE_ORES.get(key);
                addForgeTag("deepslate_ores/"+key,ds);
                makePickaxeMineable(ds);
            }
            if (ModBlocksRegister.RAW_METAL_BLOCKS.containsKey(key)) {
                Block raw = ModBlocksRegister.RAW_METAL_BLOCKS.get(key);
                addForgeTag("raw_block/"+key,raw);
                makePickaxeMineable(raw);
            }
            addForgeTag("block/"+key, ModBlocksRegister.BLOCK_METAL_BLOCKS.get(key));
            makePickaxeMineable(ModBlocksRegister.BLOCK_METAL_BLOCKS.get(key));
        }
        for (String key : ModBlocksRegister.BLOCK_GEMS_BLOCKS.keySet()) {
            addForgeTag("block/" + key, ModBlocksRegister.BLOCK_GEMS_BLOCKS.get(key));
            makePickaxeMineable(ModBlocksRegister.BLOCK_GEMS_BLOCKS.get(key));
        }

        for (String key : ModBlocksRegister.DIVINE_CRISTAL_BLOCKS.keySet()) {
            addForgeTag("block/" + key, ModBlocksRegister.DIVINE_CRISTAL_BLOCKS.get(key));
            makePickaxeMineable(ModBlocksRegister.DIVINE_CRISTAL_BLOCKS.get(key));
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
