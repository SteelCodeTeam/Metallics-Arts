package net.rudahee.metallics_arts.data.providers.tags_providers;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.data.enums.implementations.languages.MetalAuxiliaryInfo;
import net.rudahee.metallics_arts.setup.registries.ModBlocksRegister;
import net.rudahee.metallics_arts.setup.registries.items.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

/**
 * A custom block tag provider class to create and manage tags for modded blocks.
 *
 * @author SteelCode Team
 * @since 1.5.1
 */
public class ModBlockTagProvider extends BlockTagsProvider {

    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, MetallicsArts.MOD_ID, existingFileHelper);
    }


    @Override
    public CompletableFuture<TagLookup<Block>> contentsGetter() {
        return super.contentsGetter();
    }


        /**
         * Add tags for all custom blocks in the mod.
         */
    @Override
    protected void addTags(HolderLookup.Provider provider) {

        for (MetalAuxiliaryInfo metal: MetalAuxiliaryInfo.values()) {
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

                if (metal.isAppearsInStone() || metal.isAppearsInDeepslate()) {
                    addForgeTag("storage_blocks/raw_" + metal.getId(), ModBlocksRegister.RAW_METAL_BLOCKS.get(metal.getId())); //TODO
                    makePickaxeMineable(ModBlocksRegister.RAW_METAL_BLOCKS.get(metal.getId()));
                    addForgeTag("storage_blocks/" + metal.getId() + "_raw_stairs", ModBlocksRegister.BLOCK_METAL_STAIRS.get(metal.getId()+ "_raw"));
                    makePickaxeMineable(ModBlocksRegister.BLOCK_METAL_STAIRS.get(metal.getId() + "_raw"));
                }
            if (!metal.isDivine()) {
                    addForgeTag("storage_blocks/"+metal.getId(), ModBlocksRegister.BLOCK_METAL_BLOCKS.get(metal.getId()));
                    makePickaxeMineable(ModBlocksRegister.BLOCK_METAL_BLOCKS.get(metal.getId()));

                } else {
                    addForgeTag("storage_blocks/" + metal.getId(), ModBlocksRegister.BLOCK_GEMS_BLOCKS.get(metal.getId()));
                    makePickaxeMineable(ModBlocksRegister.BLOCK_GEMS_BLOCKS.get(metal.getId()));
                }

                addForgeTag("storage_blocks/" + metal.getId() + "_stairs", ModBlocksRegister.BLOCK_METAL_STAIRS.get(metal.getId()));
                makePickaxeMineable(ModBlocksRegister.BLOCK_METAL_STAIRS.get(metal.getId()));

            } else if (metal != MetalAuxiliaryInfo.COPPER) {
                addForgeTag("storage_blocks/" + metal.getId() + "_stairs", ModBlocksRegister.BLOCK_METAL_STAIRS.get(metal.getId()));
                makePickaxeMineable(ModBlocksRegister.BLOCK_METAL_STAIRS.get(metal.getId()));
            }


        }

        for (String key : ModBlocksRegister.DIVINE_CRISTAL_BLOCKS.keySet()) {
            addForgeTag("storage_blocks/" + key + "_cristal", ModBlocksRegister.DIVINE_CRISTAL_BLOCKS.get(key));
            makePickaxeMineable(ModBlocksRegister.DIVINE_CRISTAL_BLOCKS.get(key));
        }


        for (MetalAuxiliaryInfo metal: MetalAuxiliaryInfo.values()) {
            if (!metal.isVanilla() ) {
                tag(ModTags.forgeBlockTag("storage_blocks")).addTags(ModTags.forgeBlockTag("storage_blocks/" + metal.getId()));
                if ((metal.isAppearsInDeepslate() || metal.isAppearsInStone())) {
                    tag(ModTags.forgeBlockTag("ores")).addTags(ModTags.forgeBlockTag("ores/" + metal.getId()));
                    tag(ModTags.forgeBlockTag("storage_blocks")).addTags(ModTags.forgeBlockTag("storage_blocks/raw_" + metal.getId()));
                    tag(ModTags.forgeBlockTag("storage_blocks")).addTags(ModTags.forgeBlockTag("storage_blocks/" + metal.getId() + "_raw_stairs"));
                }
                if (metal.isDivine() && ModBlocksRegister.DIVINE_CRISTAL_BLOCKS.containsKey(metal.getId())) {
                    tag(ModTags.forgeBlockTag("storage_blocks")).addTags(ModTags.forgeBlockTag("storage_blocks/" + metal.getId() + "_cristal"));
                }
                tag(ModTags.forgeBlockTag("storage_blocks")).addTags(ModTags.forgeBlockTag("storage_blocks/" + metal.getId() + "_stairs"));

            } else if (metal != MetalAuxiliaryInfo.COPPER) {
                tag(ModTags.forgeBlockTag("storage_blocks")).addTags(ModTags.forgeBlockTag("storage_blocks/" + metal.getId() + "_stairs"));
            }

        }


        for (Block wall : ModBlocksRegister.BLOCK_METAL_WALLS.values()) {
            this.tag(BlockTags.WALLS).add(wall);
        }

        for (Block fence : ModBlocksRegister.BLOCK_METAL_FENCES.values()) {
            this.tag(BlockTags.FENCES).add(fence);
        }
        for (Block fence_gate : ModBlocksRegister.BLOCK_METAL_FENCE_GATES.values()) {
            this.tag(BlockTags.FENCE_GATES).add(fence_gate);
        }

    }

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
