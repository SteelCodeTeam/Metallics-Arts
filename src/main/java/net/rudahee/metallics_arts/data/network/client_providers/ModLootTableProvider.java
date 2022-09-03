package net.rudahee.metallics_arts.data.network.client_providers;



import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
    protected final Map<Block, LootTable.Builder> lootTables = new HashMap<>();
    private final DataGenerator generator;

    public ModLootTableProvider(DataGenerator generator) {
        super(generator);
        this.generator = generator;
    }
    /*private void addBlockTables() {

        for (String key: ModBlock.BLOCK_METAL_ORES.keySet()) {
            Block ore = ModBlock.BLOCK_METAL_ORES.get(key);
            Item raw = ModItems.ITEM_RAW_METAL.get(key);
            addSilkTouchBlock("loot_tables/blocks/"+key, ore, raw, 1, 1);
            if (ModBlock.BLOCK_METAL_DEEPSLATE_ORES.containsKey(key)){
                addSilkTouchBlock("loot_tables/blocks/deepslate_"+key,
                        ModBlock.BLOCK_METAL_DEEPSLATE_ORES.get(key), raw, 1, 1);
            }
            addSimpleBlock("loot_tables/blocks/raw_"+key,ModBlock.RAW_METAL_BLOCKS.get(key));
        }

    }*/
/*
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


    protected void addSimpleBlock(String name, Block block) {
        MetallicsArts.LOGGER.debug("Creating Loot Table for block " + block.getDescriptionId());
        LootPool.Builder builder = LootPool.lootPool().name(name).setRolls(ConstantValue.exactly(1)).add(LootItem.lootTableItem(block));
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
    }*/

    /*@Override
    public void run(CachedOutput cachedOutputs) {
        addBlocks();
        Map<ResourceLocation, LootTable> lootTables = new HashMap<>();

        for (Map.Entry<Block, LootTable.Builder> entry: this.lootTables.entrySet()) {
            lootTables.put(entry.getKey().getLootTable(), entry.getValue().setParamSet(LootContextParamSets.BLOCK).build());
        }
    }


    protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootParameterSet>> getTables() {
        return ImmutableList.of(Pair.of(ModBlockLootTable::new, LootParameterSets.BLOCK));
    }

    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, ValidationContext validationtracker) {
        final Set<ResourceLocation> modLootTableIds =
                LootTables.
                        .all()
                        .stream()
                        .filter(lootTable -> lootTable.getNamespace().equals(MetallicsArts.MOD_ID))
                        .collect(Collectors.toSet());

        for (ResourceLocation id : Sets.difference(modLootTableIds, map.keySet()))
            validationtracker.reportProblem("Missing mod loot table: " + id);

        map.forEach((id, lootTable) ->
                LootTableManager.validate(validationtracker, id, lootTable));
    }

    public static class ModBlockLootTable extends BlockLootTables {

        public ModBlockLootTable() {

        }

        @Override
        protected void addTables() {

            ModBlock.BLOCK_METAL_ORES.forEach((name, ore) -> {
                dropSelf(ore.getBlock());
            });

            ModBlock.BLOCK_METAL_BLOCKS.forEach((name, block) -> {
                dropSelf(block.getBlock());
            });

            ModBlock.BLOCK_GEMS_BLOCKS.forEach((name, block) -> {
                dropSelf(block.getBlock());
            });

            dropSelf(ModBlock.ALLOY_FURNACE_BLOCK.get());

        }

        @Override
        protected Iterable<Block> getKnownBlocks() {
            return StreamSupport
                    .stream(ForgeRegistries.BLOCKS.spliterator(), false)
                    .filter(
                            entry -> entry.getRegistryName() != null &&
                                    entry.getRegistryName().getNamespace().equals(MetallicsArts.MOD_ID)
                    ).collect(Collectors.toSet());
        }
    }
    }*/


    /*@Override
    public String getName() {
        return "metallics_arts_loot_table";
    }*/


}
