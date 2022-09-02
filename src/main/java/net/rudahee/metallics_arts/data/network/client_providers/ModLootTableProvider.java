package net.rudahee.metallics_arts.data.network.client_providers;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.setup.registries.ModBlock;

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

    private void addBlocks() {
        for (String key: ModBlock.BLOCK_METAL_BLOCKS.keySet()) {
            Block block = ModBlock.BLOCK_METAL_BLOCKS.get(key);
            LootPool.Builder builder = LootPool.lootPool().name(MetallicsArts.MOD_ID).setRolls(ConstantValue.exactly(1)).add(LootItem.lootTableItem(block));
            this.lootTables.put(block, LootTable.lootTable().withPool(builder));
        }
    }

    @Override
    public void run(CachedOutput cachedOutputs) {
        addBlocks();
        Map<ResourceLocation, LootTable> lootTables = new HashMap<>();

        for (Map.Entry<Block, LootTable.Builder> entry: this.lootTables.entrySet()) {
            lootTables.put(entry.getKey().getLootTable(), entry.getValue().setParamSet(LootContextParamSets.BLOCK).build());
        }
    }


    //   protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootParameterSet>> getTables() {
  //      return ImmutableList.of(Pair.of(ModBlockLootTable::new, LootParameterSets.BLOCK));
   // }

  /*  @Override
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

    @Override
    public String getName() {
        return "metallics_arts_loot_table";
    }*/


}
