package net.rudahee.metallics_arts.data.providers.tags_providers;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.data.enums.implementations.languages.MetalAuxiliaryInfo;
import net.rudahee.metallics_arts.setup.registries.ModBlocksRegister;
import net.rudahee.metallics_arts.setup.registries.items.ModTags;

/**
 * A custom block tag provider class to create and manage tags for modded blocks.
 *
 * @author SteelCode Team
 * @since 1.5.1
 */
public class ModBlockTagProvider extends BlockTagsProvider {

    /**
     * Constructor for the ModBlockTagProvider class.
     *
     * @param gen the data generator used for creating block tags
     * @param modid the mod ID for the mod that's adding the block tags
     * @param exFileHelper the existing file helper for handling existing files
     */
    public ModBlockTagProvider(DataGenerator gen, String modid, ExistingFileHelper exFileHelper) {
        super(gen, modid, exFileHelper);
    }

    /**
     * Adds tags for all custom blocks in the mod.
     */
    @Override
    protected void addTags() {

        for (MetalAuxiliaryInfo metal: MetalAuxiliaryInfo.values()){
            if (!metal.isVanilla()) {
                if (metal.isAppearsInStone() && metal.isAppearsInDeepslate()) {
                    addForgeTag("ores/" + metal.getId(), ModBlocksRegister.BLOCK_METAL_ORES.get(metal.getId()), ModBlocksRegister.BLOCK_METAL_DEEPSLATE_ORES.get(metal.getId()));
                    makePickaxeMineable(ModBlocksRegister.BLOCK_METAL_ORES.get(metal.getId()));
                    makePickaxeMineable(ModBlocksRegister.BLOCK_METAL_DEEPSLATE_ORES.get(metal.getId()));

                    addForgeTag("ores_in_ground/deepslate",ModBlocksRegister.BLOCK_METAL_DEEPSLATE_ORES.get(metal.getId()));
                    addForgeTag("ores_in_ground/stone",ModBlocksRegister.BLOCK_METAL_ORES.get(metal.getId()));

                } else if (metal.isAppearsInStone()) {
                    addForgeTag("ores/" + metal.getId(), ModBlocksRegister.BLOCK_METAL_ORES.get(metal.getId()));
                    makePickaxeMineable(ModBlocksRegister.BLOCK_METAL_ORES.get(metal.getId()));
                    addForgeTag("ores_in_ground/stone",ModBlocksRegister.BLOCK_METAL_ORES.get(metal.getId()));
                } else if (metal.isAppearsInDeepslate()) {
                    addForgeTag("ores/" + metal.getId(), ModBlocksRegister.BLOCK_METAL_DEEPSLATE_ORES.get(metal.getId()));
                    makePickaxeMineable(ModBlocksRegister.BLOCK_METAL_DEEPSLATE_ORES.get(metal.getId()));
                    addForgeTag("ores_in_ground/deepslate",ModBlocksRegister.BLOCK_METAL_DEEPSLATE_ORES.get(metal.getId()));
                }

                if (!metal.isAlloy()&& !metal.isDivine()) {
                    addForgeTag("storage_blocks/raw_" + metal.getId(), ModBlocksRegister.RAW_METAL_BLOCKS.get(metal.getId()));
                    makePickaxeMineable(ModBlocksRegister.RAW_METAL_BLOCKS.get(metal.getId()));
                }


                if (!metal.isDivine()) {
                    addForgeTag("storage_blocks/"+metal.getId(), ModBlocksRegister.BLOCK_METAL_BLOCKS.get(metal.getId()));
                    makePickaxeMineable(ModBlocksRegister.BLOCK_METAL_BLOCKS.get(metal.getId()));
                } else {
                    addForgeTag("storage_blocks/" + metal.getId(), ModBlocksRegister.BLOCK_GEMS_BLOCKS.get(metal.getId()));
                    makePickaxeMineable(ModBlocksRegister.BLOCK_GEMS_BLOCKS.get(metal.getId()));
                }
            }


        }
        for (String key : ModBlocksRegister.DIVINE_CRISTAL_BLOCKS.keySet()) {
            addForgeTag("storage_blocks/" + key + "_cristal", ModBlocksRegister.DIVINE_CRISTAL_BLOCKS.get(key));
            makePickaxeMineable(ModBlocksRegister.DIVINE_CRISTAL_BLOCKS.get(key));
        }




        for (MetalAuxiliaryInfo metal: MetalAuxiliaryInfo.values()) {
            if (!metal.isVanilla() ) {
                tag(ModTags.forgeBlockTag("storage_blocks")).addTags(ModTags.forgeBlockTag("storage_blocks/" + metal.getId()));
                if ((metal.isAppearsInDeepslate() || metal.isAppearsInStone())){
                    /*if (metal.isAppearsInDeepslate()) {
                        tag(ModTags.forgeBlockTag("ores_in_ground")).addTags(ModTags.forgeBlockTag("ores/" + metal.getId()));
                    }*/
                    tag(ModTags.forgeBlockTag("ores")).addTags(ModTags.forgeBlockTag("ores/" + metal.getId()));
                    tag(ModTags.forgeBlockTag("storage_blocks")).addTags(ModTags.forgeBlockTag("storage_blocks/raw_" + metal.getId()));
                }
                if (metal.isDivine() && ModBlocksRegister.DIVINE_CRISTAL_BLOCKS.containsKey(metal.getId())) {
                    tag(ModTags.forgeBlockTag("storage_blocks")).addTags(ModTags.forgeBlockTag("storage_blocks/" + metal.getId() + "_cristal"));
                }

            }


        }

        /*for (String key: ModBlocksRegister.BLOCK_METAL_BLOCKS.keySet()) {

            if (ModBlocksRegister.BLOCK_METAL_ORES.containsKey(key)) {
                Block ore = ModBlocksRegister.BLOCK_METAL_ORES.get(key);
                addForgeTag("ores/" + key, ore);
                makePickaxeMineable(ore);
            }
            if (ModBlocksRegister.BLOCK_METAL_DEEPSLATE_ORES.containsKey(key)) {
                Block ds = ModBlocksRegister.BLOCK_METAL_DEEPSLATE_ORES.get(key);
                addForgeTag("deepslate_ores/"+key,ds);
                makePickaxeMineable(ds);
            }
            if (ModBlocksRegister.RAW_METAL_BLOCKS.containsKey(key)) {
                Block raw = ModBlocksRegister.RAW_METAL_BLOCKS.get(key);
                addForgeTag("storage_blocks/"+key, raw);
                makePickaxeMineable(raw);
            }
            addForgeTag("storage_blocks/"+key, ModBlocksRegister.BLOCK_METAL_BLOCKS.get(key));
            makePickaxeMineable(ModBlocksRegister.BLOCK_METAL_BLOCKS.get(key));
        }

        for (String key : ModBlocksRegister.BLOCK_GEMS_BLOCKS.keySet()) {
            addForgeTag("storage_blocks/" + key, ModBlocksRegister.BLOCK_GEMS_BLOCKS.get(key));
            makePickaxeMineable(ModBlocksRegister.BLOCK_GEMS_BLOCKS.get(key));
        }*/



    }


    /*
    this.tag(TagUtils.forgeBlockTag( "ores/adamantine"))
	        .add(ModBlocks.adamantium_ore.get())
			.add(ModBlocks.deepslate_adamantium_ore.get());
     */



    /**
     * Adds a forge tag for a group of blocks.
     *
     * @param name  the name of the tag
     * @param items the blocks to be added to the tag
     */
    private void addForgeTag(String name, Block... items) {
        MetallicsArts.LOGGER.debug("Creating block tag for forge:" + name);
        tag(net.minecraft.tags.BlockTags.create(new ResourceLocation("forge", name))).replace(false).add(items);
    }


    /**
     * Makes a group of blocks mineable with a pickaxe.
     *
     * @param items the blocks to be made mineable with a pickaxe
     */
    private void makePickaxeMineable(Block... items) {
        addTag("mineable/pickaxe", items);
        addTag("needs_iron_tool", items);
    }

    /**
     * Adds a custom tag for a group of blocks.
     *
     * @param name  the name of the tag
     * @param items the blocks to be added to the tag
     */
    private void addTag(String name, Block... items) {
        MetallicsArts.LOGGER.debug("Creating block tag for minecraft:" + name);
        tag(net.minecraft.tags.BlockTags.create(new ResourceLocation("minecraft", name))).replace(false).add(items);
    }

    /**
     * Returns the name of the block tag provider.
     *
     * @return a String representing the name of the block tag provider
     */
    @Override
    public String getName() {
        return "Metallic Arts Block Tags";
    }

}
