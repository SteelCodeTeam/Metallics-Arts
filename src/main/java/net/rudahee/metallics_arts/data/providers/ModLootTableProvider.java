package net.rudahee.metallics_arts.data.providers;


import net.minecraft.advancements.critereon.EnchantmentPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.AlternativesEntry;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.ApplyExplosionDecay;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.setup.registries.ModBlocksRegister;
import net.rudahee.metallics_arts.setup.registries.ModItemsRegister;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

/**
 * A custom loot table provider for the mod, responsible for generating loot tables
 * for various mod blocks such as ores, deepslate ores, metal blocks, gem blocks,
 * and divine crystal blocks.
 *
 * @author SteelCode Team
 * @since 1.5.1
 */
public class ModLootTableProvider extends LootTableProvider {

    private static final LootItemCondition.Builder HAS_SILK_TOUCH = MatchTool.toolMatches(ItemPredicate.Builder.item().hasEnchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.Ints.atLeast(1))));
    protected final Map<Block, LootTable.Builder> lootTables = new HashMap<>();
    private final DataGenerator generator;

    /**
     * Constructs a new instance of the ModLootTableProvider class.
     *
     * @param generator the data generator to use for generating the loot tables
     */
    public ModLootTableProvider(DataGenerator generator, Set<ResourceLocation> loc, List<SubProviderEntry> prov) {
        //TODO no se que onda con el super, agrege los dos parametros finales.
        super(generator.getPackOutput(), loc, prov);
        this.generator = generator;
    }

    public ModLootTableProvider(PackOutput packOutput, Set<ResourceLocation> resourceLocations, List<SubProviderEntry> subProviderEntries, DataGenerator generator) {
        super(packOutput, resourceLocations, subProviderEntries);
        this.generator = generator;
    }



    /**
     * Adds block-based loot tables for various mod blocks such as ores,
     * deepslate ores, metal blocks, gem blocks, and divine crystal blocks.
     */
    private void addBlockTables() {
        for (String key: ModBlocksRegister.BLOCK_METAL_ORES.keySet()) {
            Block ore = ModBlocksRegister.BLOCK_METAL_ORES.get(key);
            Item raw = ModItemsRegister.ITEM_RAW_METAL.get(key);
            createOreDrops(ore,raw,1,2);
        }
        for (String key: ModBlocksRegister.BLOCK_METAL_DEEPSLATE_ORES.keySet()) {
            Block block = ModBlocksRegister.BLOCK_METAL_DEEPSLATE_ORES.get(key);
            Item raw = ModItemsRegister.ITEM_RAW_METAL.get(key);
            createOreDrops(block,raw,1,3);
        }
        for (String key: ModBlocksRegister.RAW_METAL_BLOCKS.keySet()) {
            addSimpleBlock(ModBlocksRegister.RAW_METAL_BLOCKS.get(key));
        }
        for (String key : ModBlocksRegister.BLOCK_METAL_BLOCKS.keySet()) {
            addSimpleBlock(ModBlocksRegister.BLOCK_METAL_BLOCKS.get(key));
        }
        for (String key : ModBlocksRegister.BLOCK_GEMS_BLOCKS.keySet()) {
            addSimpleBlock(ModBlocksRegister.BLOCK_GEMS_BLOCKS.get(key));
        }

        for (String key: ModBlocksRegister.DIVINE_CRISTAL_BLOCKS.keySet()) {
            this.lootTables.put(ModBlocksRegister.DIVINE_CRISTAL_BLOCKS.get(key), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).add(LootItem.lootTableItem(ModBlocksRegister.DIVINE_CRISTAL_BLOCKS.get(key)).when(MatchTool.toolMatches(ItemPredicate.Builder
                    .item()
                    .hasEnchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH,
                            MinMaxBounds.Ints.atLeast(1))))))));
        }


        addSilkTouchBlock(ModBlocksRegister.ATIUM_CLUSTER.get().getLootTable().getPath(), ModBlocksRegister.ATIUM_CLUSTER.get(), ModItemsRegister.ITEM_GEMS_BASE.get("atium"),1,1);
        addSilkTouchBlock(ModBlocksRegister.LERASIUM_CLUSTER.get().getLootTable().getPath(), ModBlocksRegister.LERASIUM_CLUSTER.get(), ModItemsRegister.ITEM_GEMS_BASE.get("lerasium"),1,1);
        addSilkTouchBlock(ModBlocksRegister.ETTMETAL_CLUSTER.get().getLootTable().getPath(), ModBlocksRegister.ETTMETAL_CLUSTER.get(), ModItemsRegister.ITEM_GEMS_BASE.get("ettmetal"),1,1);

    }

    /**
     * The main method for generating loot tables using the provided CachedOutput.
     *
     * @param cachedOutput the cached output for storing the generated loot tables
     * @return
     */
    @Override
    public CompletableFuture<?> run(CachedOutput cachedOutput) {
        addBlockTables();
        Map<ResourceLocation, LootTable> tables;
        tables = new HashMap<>();
        for (Map.Entry<Block, LootTable.Builder> entry : this.lootTables.entrySet()) {
            tables.put(entry.getKey().getLootTable(), entry.getValue().setParamSet(LootContextParamSets.BLOCK).build());
        }
        writeTables(cachedOutput, tables);
        //TODO Arreglar el return return null;
        return null;
    }

    /**
     * Writes the generated loot tables to the cached output.
     *
     * @param cachedOutput the cached output for storing the generated loot tables
     * @param tables       the collection of generated loot tables
     */
    private void writeTables(CachedOutput cachedOutput, Map<ResourceLocation, LootTable> tables) {
        Path outputFolder = this.generator.getPackOutput().getOutputFolder();
        tables.forEach((key, lootTable) -> {
            Path path = outputFolder.resolve("data/" + key.getNamespace() + "/loot_tables/" + key.getPath() + ".json");
            DataProvider.saveStable(cachedOutput,net.minecraft.world.level.storage.loot.LootTables.serialize(lootTable),path);
        });
    }

    /**
     * Creates a loot table for a given simple block with no additional conditions.
     *
     * @param block the block for which the loot table is created
     */
    protected void addSimpleBlock(Block block) {
        MetallicsArts.LOGGER.debug("Creating Loot Table for block " + block.getDescriptionId());
        LootPool.Builder builder = LootPool.lootPool().setRolls(ConstantValue.exactly(1)).add(LootItem.lootTableItem(block));

        this.lootTables.put(block, LootTable.lootTable().withPool(builder));
    }

    /**
     * Creates a loot table for a given ore block with specific item drops and conditions.
     *
     * @param block   the ore block for which the loot table is created
     * @param item    the item to be dropped when the block is mined
     * @param minBase the minimum base value for the item drop count
     * @param maxBase the maximum base value for the item drop count
     */
    protected void createOreDrops(Block block, Item item, float minBase, float maxBase) {
        LootPool.Builder builder = LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1.0F))
                .add(LootItem.lootTableItem(block)
                        .when(HAS_SILK_TOUCH)
                        .otherwise(LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(minBase, maxBase)))
                                .apply(ApplyBonusCount.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE,0.5f, 2))));

        this.lootTables.put(block, LootTable.lootTable().withPool(builder));
    }

    /**
     * Adds a silk touch block to the loot table with a specific block, item, and loot table path.
     *
     * @param path        the loot table path
     * @param block       the block to be included in the loot table
     * @param item        the item to be included in the loot table
     * @param min         the minimum quantity of the item
     * @param max         the maximum quantity of the item
     */
    protected void addSilkTouchBlock(String path, Block block, Item item, float min, float max) {
        LootPool.Builder builder = LootPool
                .lootPool()
                .name(path)
                .setRolls(ConstantValue.exactly(1))
                .add(AlternativesEntry.alternatives(LootItem
                        .lootTableItem(block)
                        .when(MatchTool.toolMatches(ItemPredicate.Builder
                                .item()
                                .hasEnchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH,
                                        MinMaxBounds.Ints.atLeast(1))))), LootItem
                        .lootTableItem(item)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(min, max)))
                        .apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE, 0))
                        .apply(ApplyExplosionDecay.explosionDecay())));
        this.lootTables.put(block, LootTable.lootTable().withPool(builder));
    }


}
