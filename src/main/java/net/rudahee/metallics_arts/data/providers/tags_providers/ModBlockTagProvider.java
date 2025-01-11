package net.rudahee.metallics_arts.data.providers.tags_providers;

import lombok.extern.log4j.Log4j2;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.data.enums.implementations.languages.old.MetalAuxiliaryInfo;
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
@Log4j2
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
            if (metal.isVanilla()) {
                addVanillaBlocksTags(metal.getId());
            } else {
                if (metal.isAppearsInDeepslate() && metal.isAppearsInStone()) {
                    addTagsStoneAndDeepslate(metal.getId());
                } else if (metal.isAppearsInDeepslate()) {
                    addDeepslateTags(metal.getId());
                } else if (metal.isAppearsInStone()) {
                    addStoneTags(metal.getId());
                }

                if (metal.isAppearsInDeepslate() || metal.isAppearsInStone()) {
                    addRawTags(metal.getId());
                }
                addBasicBlocksTags(metal.getId(), metal.isDivine());
            }
        }
        for (String key : ModBlocksRegister.DIVINE_CRISTAL_BLOCKS.keySet()) {
            addForgeTag("storage_blocks/" + key + "_cristal", ModBlocksRegister.DIVINE_CRISTAL_BLOCKS.get(key));
            makePickaxeMineable(ModBlocksRegister.DIVINE_CRISTAL_BLOCKS.get(key));
            //Added to "storage_blocks.json"
            tag(ModTags.forgeBlockTag("storage_blocks")).addTags(ModTags.forgeBlockTag("storage_blocks/" + key + "_cristal"));
        }

        for (Block wall : ModBlocksRegister.BLOCK_METAL_WALL.values()) {
            this.tag(BlockTags.WALLS).add(wall);
        }

        for (Block fence : ModBlocksRegister.BLOCK_METAL_FENCE.values()) {
            this.tag(BlockTags.FENCES).add(fence);
        }
        for (Block fence_gate : ModBlocksRegister.BLOCK_METAL_FENCE_GATE.values()) {
            this.tag(BlockTags.FENCE_GATES).add(fence_gate);
        }

        makePickaxeMineable(ModBlocksRegister.CRUCIBLE_FURNACE.get());
        makePickaxeDiamondMineable(ModBlocksRegister.HEMALURGY_ALTAR_BACK.get());
        makePickaxeDiamondMineable(ModBlocksRegister.HEMALURGY_ALTAR_FRONT.get());


    }

    private void addTagsStoneAndDeepslate(String key) {

        addForgeTag("ores/" + key, ModBlocksRegister.BLOCK_METAL_ORES.get(key), ModBlocksRegister.BLOCK_METAL_DEEPSLATE_ORES.get(key));
        makePickaxeMineable(ModBlocksRegister.BLOCK_METAL_ORES.get(key));
        makePickaxeMineable(ModBlocksRegister.BLOCK_METAL_DEEPSLATE_ORES.get(key));
        addForgeTag("ores_in_ground/deepslate",ModBlocksRegister.BLOCK_METAL_DEEPSLATE_ORES.get(key));
        addForgeTag("ores_in_ground/stone",ModBlocksRegister.BLOCK_METAL_ORES.get(key));

    }

    private void addStoneTags(String key) {
        addForgeTag("ores/" + key, ModBlocksRegister.BLOCK_METAL_ORES.get(key));
        addForgeTag("ores_in_ground/stone",ModBlocksRegister.BLOCK_METAL_ORES.get(key));
        makePickaxeMineable(ModBlocksRegister.BLOCK_METAL_ORES.get(key));
    }

    private void addDeepslateTags(String key) {
        addForgeTag("ores/" + key, ModBlocksRegister.BLOCK_METAL_DEEPSLATE_ORES.get(key));
        makePickaxeMineable(ModBlocksRegister.BLOCK_METAL_DEEPSLATE_ORES.get(key));
        addForgeTag("ores_in_ground/deepslate",ModBlocksRegister.BLOCK_METAL_DEEPSLATE_ORES.get(key));
    }

    private void addRawTags(String key) {
        addForgeTag("storage_blocks/raw_" + key, ModBlocksRegister.RAW_METAL_BLOCKS.get(key));
        makePickaxeMineable(ModBlocksRegister.RAW_METAL_BLOCKS.get(key));

        addForgeTagDecoration(key + "_raw");
        pickaxeMineableDecoration(key + "_raw");

        addGenericForgeTagDecoration(key + "_raw");

        //Added to "storage_blocks.json"
        tag(ModTags.forgeBlockTag("ores")).addTags(ModTags.forgeBlockTag("ores/" + key));
        tag(ModTags.forgeBlockTag("storage_blocks")).addTags(ModTags.forgeBlockTag("storage_blocks/raw_" + key));
    }

    private void addBasicBlocksTags(String key, boolean isDivine) {
        if (isDivine) {
            addForgeTag("storage_blocks/" + key, ModBlocksRegister.BLOCK_GEMS_BLOCKS.get(key));
            makePickaxeMineable(ModBlocksRegister.BLOCK_GEMS_BLOCKS.get(key));
        } else {
            addForgeTag("storage_blocks/" + key, ModBlocksRegister.BLOCK_METAL_BLOCKS.get(key));
            makePickaxeMineable(ModBlocksRegister.BLOCK_METAL_BLOCKS.get(key));
        }

        addForgeTagDecoration(key);
        addGenericForgeTagDecoration(key);
        pickaxeMineableDecoration(key);

        //Added to "storage_blocks.json"
        tag(ModTags.forgeBlockTag("storage_blocks")).addTags(ModTags.forgeBlockTag("storage_blocks/" + key));
    }
    public void addForgeTagDecoration(String key) {
        addForgeTag("storage_blocks/" + key + "_stairs", ModBlocksRegister.BLOCK_METAL_STAIRS.get(key));
        addForgeTag("storage_blocks/" + key + "_slab", ModBlocksRegister.BLOCK_METAL_SLAB.get(key));
        addForgeTag("storage_blocks/" + key + "_wall", ModBlocksRegister.BLOCK_METAL_WALL.get(key));
        addForgeTag("storage_blocks/" + key + "_fence", ModBlocksRegister.BLOCK_METAL_FENCE.get(key));
        addForgeTag("storage_blocks/" + key + "_fence_gate", ModBlocksRegister.BLOCK_METAL_FENCE_GATE.get(key));

    }
    public void addGenericForgeTagDecoration(String key) {
        //Added to "storage_blocks.json"
        tag(ModTags.forgeBlockTag("storage_blocks")).addTags(ModTags.forgeBlockTag("storage_blocks/" + key + "_stairs"));
        tag(ModTags.forgeBlockTag("storage_blocks")).addTags(ModTags.forgeBlockTag("storage_blocks/" + key + "_slab"));
        tag(ModTags.forgeBlockTag("storage_blocks")).addTags(ModTags.forgeBlockTag("storage_blocks/" + key + "_wall"));
        tag(ModTags.forgeBlockTag("storage_blocks")).addTags(ModTags.forgeBlockTag("storage_blocks/" + key + "_fence"));
        tag(ModTags.forgeBlockTag("storage_blocks")).addTags(ModTags.forgeBlockTag("storage_blocks/" + key + "_fence_gate"));
    }


    private void pickaxeMineableDecoration(String key) {
        makePickaxeMineable(ModBlocksRegister.BLOCK_METAL_STAIRS.get(key));
        makePickaxeMineable(ModBlocksRegister.BLOCK_METAL_SLAB.get(key));
        makePickaxeMineable(ModBlocksRegister.BLOCK_METAL_WALL.get(key));
        makePickaxeMineable(ModBlocksRegister.BLOCK_METAL_FENCE.get(key));
        makePickaxeMineable(ModBlocksRegister.BLOCK_METAL_FENCE_GATE.get(key));
    }

    private void addVanillaBlocksTags(String key) {

        //wall, fence_gate, fence
        addForgeTag("storage_blocks/" + key + "_wall", ModBlocksRegister.BLOCK_METAL_WALL.get(key));
        addForgeTag("storage_blocks/" + key + "_fence", ModBlocksRegister.BLOCK_METAL_FENCE.get(key));
        addForgeTag("storage_blocks/" + key + "_fence_gate", ModBlocksRegister.BLOCK_METAL_FENCE_GATE.get(key));

        //Added to "storage_blocks.json"
        tag(ModTags.forgeBlockTag("storage_blocks")).addTags(ModTags.forgeBlockTag("storage_blocks/" + key + "_wall"));
        tag(ModTags.forgeBlockTag("storage_blocks")).addTags(ModTags.forgeBlockTag("storage_blocks/" + key + "_fence"));
        tag(ModTags.forgeBlockTag("storage_blocks")).addTags(ModTags.forgeBlockTag("storage_blocks/" + key + "_fence_gate"));

        //wall, fence_gate, fence raw
        addForgeTag("storage_blocks/" + key + "_raw_wall", ModBlocksRegister.BLOCK_METAL_WALL.get(key + "_raw"));
        addForgeTag("storage_blocks/" + key + "_raw_fence", ModBlocksRegister.BLOCK_METAL_FENCE.get(key + "_raw"));
        addForgeTag("storage_blocks/" + key + "_raw_fence_gate", ModBlocksRegister.BLOCK_METAL_FENCE_GATE.get(key + "_raw"));

        //Added to "storage_blocks.json"
        tag(ModTags.forgeBlockTag("storage_blocks")).addTags(ModTags.forgeBlockTag("storage_blocks/" + key + "_raw_wall"));
        tag(ModTags.forgeBlockTag("storage_blocks")).addTags(ModTags.forgeBlockTag("storage_blocks/" + key + "_raw_fence"));
        tag(ModTags.forgeBlockTag("storage_blocks")).addTags(ModTags.forgeBlockTag("storage_blocks/" + key + "_raw_fence_gate"));

        //stair and slab raw
        addForgeTag("storage_blocks/" + key + "_raw_stairs", ModBlocksRegister.BLOCK_METAL_STAIRS.get(key + "_raw"));
        addForgeTag("storage_blocks/" + key + "_raw_slab", ModBlocksRegister.BLOCK_METAL_SLAB.get(key + "_raw"));

        //Added to "storage_blocks.json"
        tag(ModTags.forgeBlockTag("storage_blocks")).addTags(ModTags.forgeBlockTag("storage_blocks/" + key + "_raw_stairs"));
        tag(ModTags.forgeBlockTag("storage_blocks")).addTags(ModTags.forgeBlockTag("storage_blocks/" + key + "_raw_slab"));


        //wall, fence_gate, fence
        makePickaxeMineable(ModBlocksRegister.BLOCK_METAL_WALL.get(key));
        makePickaxeMineable(ModBlocksRegister.BLOCK_METAL_FENCE.get(key));
        makePickaxeMineable(ModBlocksRegister.BLOCK_METAL_FENCE_GATE.get(key));
        //wall, fence_gate, fence raw
        makePickaxeMineable(ModBlocksRegister.BLOCK_METAL_WALL.get(key + "_raw"));
        makePickaxeMineable(ModBlocksRegister.BLOCK_METAL_FENCE.get(key + "_raw"));
        makePickaxeMineable(ModBlocksRegister.BLOCK_METAL_FENCE_GATE.get(key + "_raw"));

        //stair and slab raw
        makePickaxeMineable(ModBlocksRegister.BLOCK_METAL_STAIRS.get(key + "_raw"));
        makePickaxeMineable(ModBlocksRegister.BLOCK_METAL_SLAB.get(key + "_raw"));

        if (!key.equals("copper")) {
            //stair and slab // not copper, exist in vanilla
            addForgeTag("storage_blocks/" + key + "_stairs", ModBlocksRegister.BLOCK_METAL_STAIRS.get(key));
            addForgeTag("storage_blocks/" + key + "_slab", ModBlocksRegister.BLOCK_METAL_SLAB.get(key));

            //Added to "storage_blocks.json"
            tag(ModTags.forgeBlockTag("storage_blocks")).addTags(ModTags.forgeBlockTag("storage_blocks/" + key + "_stairs"));
            tag(ModTags.forgeBlockTag("storage_blocks")).addTags(ModTags.forgeBlockTag("storage_blocks/" + key + "_slab"));

            makePickaxeMineable(ModBlocksRegister.BLOCK_METAL_STAIRS.get(key));
            makePickaxeMineable(ModBlocksRegister.BLOCK_METAL_SLAB.get(key));
        }
    }

    /**
     * Adds a forge tag for a group of blocks.
     *
     * @param name  the name of the tag
     * @param items the blocks to be added to the tag
     */
    private void addForgeTag(String name, Block... items) {
        log.debug("Creating block tag for forge:" + name);
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

    private void makePickaxeDiamondMineable(Block... items) {
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
        log.debug("Creating block tag for minecraft:" + name);
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
