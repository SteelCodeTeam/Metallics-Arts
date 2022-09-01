package net.rudahee.metallics_arts.data.network.client_providers;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Sets;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTables;
import net.minecraftforge.registries.ForgeRegistries;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.setup.registries.ModBlock;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ModLootTableProvider extends LootTableProvider {
    public ModLootTableProvider(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootParameterSet>> getTables() {
        return ImmutableList.of(Pair.of(ModBlockLootTable::new, LootParameterSets.BLOCK));
    }

    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, ValidationTracker validationtracker) {
        final Set<ResourceLocation> modLootTableIds =
                LootTables
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
    }
}
