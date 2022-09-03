package net.rudahee.metallics_arts.data.providers;



import net.minecraft.advancements.critereon.EnchantmentPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
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
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.setup.registries.ModBlock;
import net.rudahee.metallics_arts.setup.registries.ModItems;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class ModLootTableProvider extends LootTableProvider {

    protected final Map<Block, LootTable.Builder> lootTables = new HashMap<>();
    private final DataGenerator generator;

    public ModLootTableProvider(DataGenerator generator) {
        super(generator);
        this.generator = generator;
    }

    private void addBlockTables() {
        //AGREGAR FOR POR ore y ore deepslate con add silktoch o como sea con valores maximos y miunimos por block
        for (String key: ModBlock.BLOCK_METAL_ORES.keySet()) {
            Block ore = ModBlock.BLOCK_METAL_ORES.get(key);
            Item raw = ModItems.ITEM_RAW_METAL.get(key);
            addSilkTouchBlock(ore.getLootTable().getPath(),ore,raw,1,1);
        }
        for (String key: ModBlock.BLOCK_METAL_DEEPSLATE_ORES.keySet()) {
            Block ds = ModBlock.BLOCK_METAL_DEEPSLATE_ORES.get(key);
            Item raw = ModItems.ITEM_RAW_METAL.get(key);
            addSilkTouchBlock(ds.getLootTable().getPath(),ds,raw,1,1);
        }
        for (String key: ModBlock.RAW_METAL_BLOCKS.keySet()) {
            addSimpleBlock(ModBlock.RAW_METAL_BLOCKS.get(key));
        }
        for (String key : ModBlock.BLOCK_METAL_BLOCKS.keySet()) {
            addSimpleBlock(ModBlock.BLOCK_METAL_BLOCKS.get(key));
        }
        for (String key : ModBlock.BLOCK_GEMS_BLOCKS.keySet()) {
            addSimpleBlock(ModBlock.BLOCK_GEMS_BLOCKS.get(key));
        }
    }

    @Override
    public void run(CachedOutput cachedOutput) {
        addBlockTables();
        Map<ResourceLocation, LootTable> tables;
        tables = new HashMap<>();
        for (Map.Entry<Block, LootTable.Builder> entry : this.lootTables.entrySet()) {
            tables.put(entry.getKey().getLootTable(), entry.getValue().setParamSet(LootContextParamSets.BLOCK).build());
        }
        writeTables(cachedOutput, tables);
    }

    private void writeTables(CachedOutput cache, Map<ResourceLocation, LootTable> tables) {
        Path outputFolder = this.generator.getOutputFolder();
        tables.forEach((key, lootTable) -> {
            Path path = outputFolder.resolve("data/" + key.getNamespace() + "/loot_tables/" + key.getPath() + ".json");
            try {
                DataProvider.saveStable(cache,net.minecraft.world.level.storage.loot.LootTables.serialize(lootTable),path);
            } catch (IOException e) {
                MetallicsArts.LOGGER.error("Couldn't write loot table {}", path, e);
            }
        });
    }

//String name,
    protected void addSimpleBlock(Block block) {
        MetallicsArts.LOGGER.debug("Creating Loot Table for block " + block.getDescriptionId());
        LootPool.Builder builder = LootPool.lootPool().setRolls(ConstantValue.exactly(1)).add(LootItem.lootTableItem(block));
        //LootPool.Builder builder = LootPool.lootPool().name(name).setRolls(ConstantValue.exactly(1)).add(LootItem.lootTableItem(block));
        this.lootTables.put(block, LootTable.lootTable().withPool(builder));
    }

    protected void addSilkTouchBlock(String name, Block block, Item lootItem, float min, float max) {
        LootPool.Builder builder = LootPool
                .lootPool()
                .name(name)
                .setRolls(ConstantValue.exactly(1))
                .add(AlternativesEntry.alternatives(LootItem
                        .lootTableItem(block)
                        .when(MatchTool.toolMatches(ItemPredicate.Builder
                                .item()
                                .hasEnchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH,
                                        MinMaxBounds.Ints.atLeast(1))))), LootItem
                        .lootTableItem(lootItem)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(min, max)))
                        .apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE, 1))
                        .apply(ApplyExplosionDecay.explosionDecay())));
        this.lootTables.put(block, LootTable.lootTable().withPool(builder));
    }

    @Override
    public String getName() {
        return "metallics_arts_loot_table";
    }
}
