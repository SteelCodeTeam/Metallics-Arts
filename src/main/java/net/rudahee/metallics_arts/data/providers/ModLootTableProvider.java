package net.rudahee.metallics_arts.data.providers;

import net.minecraft.advancements.critereon.EnchantmentPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.AlternativesEntry;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.ApplyExplosionDecay;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.ForgeRegistries;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.setup.registries.ModBlocksRegister;
import net.rudahee.metallics_arts.setup.registries.ModItemsRegister;
import net.rudahee.metallics_arts.setup.registries.ModLivingEntityRegister;

import java.util.function.BiConsumer;

public class ModLootTableProvider implements LootTableSubProvider {
    // Useful boilerplate from McJtyLib
    protected static void addSimpleBlock(BiConsumer<ResourceLocation, LootTable.Builder> writer, String name, Block block) {
        MetallicsArts.LOGGER.debug("Creating Loot Table for block " + ForgeRegistries.BLOCKS.getKey(block));
        LootPool.Builder builder = LootPool.lootPool().name(name).setRolls(ConstantValue.exactly(1)).add(LootItem.lootTableItem(block));

        writer.accept(new ResourceLocation(MetallicsArts.MOD_ID, "blocks/" + name), LootTable.lootTable().withPool(builder));
    }


    protected static void addSilkTouchBlock(BiConsumer<ResourceLocation, LootTable.Builder> writer, String name, Block block, Item lootItem, float min, float max) {
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
        writer.accept(new ResourceLocation(MetallicsArts.MOD_ID, "blocks/" + name), LootTable.lootTable().withPool(builder));
    }

    protected static void addOnlySilkTouchBlock(BiConsumer<ResourceLocation, LootTable.Builder> writer, String name, Block block) {
        LootPool.Builder builder = LootPool
                .lootPool()
                .name(name)
                .setRolls(ConstantValue.exactly(1))
                .add(AlternativesEntry.alternatives(LootItem
                        .lootTableItem(block)
                        .when(MatchTool.toolMatches(ItemPredicate.Builder
                                .item()
                                .hasEnchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH,
                                        MinMaxBounds.Ints.atLeast(1)))))));
        writer.accept(new ResourceLocation(MetallicsArts.MOD_ID, "blocks/" + name), LootTable.lootTable().withPool(builder));
    }


    @Override
    public void generate(BiConsumer<ResourceLocation, LootTable.Builder> writer) {



        for (String key: ModBlocksRegister.BLOCK_METAL_ORES.keySet()) {
            Block stoneOre = ModBlocksRegister.BLOCK_METAL_ORES.get(key);
            Item raw = ModItemsRegister.ITEM_RAW_METAL.get(key);

            addSilkTouchBlock(writer, ForgeRegistries.BLOCKS.getKey(stoneOre).getPath(), stoneOre, raw, 1, 1);


            //addSimpleBlock(writer, ForgeRegistries.BLOCKS.getKey(stoneOre).getPath(), stoneOre);
            //createOreDrops(ore,raw,1,2);
        }
        for (String key: ModBlocksRegister.BLOCK_METAL_DEEPSLATE_ORES.keySet()) {
            Block deepSlateOre = ModBlocksRegister.BLOCK_METAL_DEEPSLATE_ORES.get(key);
            Item raw = ModItemsRegister.ITEM_RAW_METAL.get(key);

            addSilkTouchBlock(writer, ForgeRegistries.BLOCKS.getKey(deepSlateOre).getPath(), deepSlateOre, raw, 1, 1);
            //addSimpleBlock(writer, ForgeRegistries.BLOCKS.getKey(deepSlateOre).getPath(), deepSlateOre);

            //createOreDrops(block,raw,1,3);
        }
        for (String key: ModBlocksRegister.RAW_METAL_BLOCKS.keySet()) {
            Block rawBlock = ModBlocksRegister.RAW_METAL_BLOCKS.get(key);
            addSimpleBlock(writer, ForgeRegistries.BLOCKS.getKey(rawBlock).getPath(), rawBlock);
        }
        for (String key : ModBlocksRegister.BLOCK_METAL_BLOCKS.keySet()) {
            Block metalBlock = ModBlocksRegister.BLOCK_METAL_BLOCKS.get(key);
            Block wallBlock = ModBlocksRegister.BLOCK_METAL_WALL.get(key);
            Block fences = ModBlocksRegister.BLOCK_METAL_FENCE.get(key);
            Block fencesGates = ModBlocksRegister.BLOCK_METAL_FENCE_GATE.get(key);
            Block slabs = ModBlocksRegister.BLOCK_METAL_SLAB.get(key);

            addSimpleBlock(writer, ForgeRegistries.BLOCKS.getKey(metalBlock).getPath(), metalBlock);
            addSimpleBlock(writer, ForgeRegistries.BLOCKS.getKey(wallBlock).getPath(), wallBlock);
            addSimpleBlock(writer, ForgeRegistries.BLOCKS.getKey(fences).getPath(), fences);
            addSimpleBlock(writer, ForgeRegistries.BLOCKS.getKey(fencesGates).getPath(), fencesGates);
            addSimpleBlock(writer, ForgeRegistries.BLOCKS.getKey(slabs).getPath(), slabs);
        }
        for (String key : ModBlocksRegister.BLOCK_GEMS_BLOCKS.keySet()) {
            Block gemBlock = ModBlocksRegister.BLOCK_GEMS_BLOCKS.get(key);
            Block wallBlock = ModBlocksRegister.BLOCK_METAL_WALL.get(key);
            Block fences = ModBlocksRegister.BLOCK_METAL_FENCE.get(key);
            Block fencesGates = ModBlocksRegister.BLOCK_METAL_FENCE_GATE.get(key);
            Block slabs = ModBlocksRegister.BLOCK_METAL_SLAB.get(key);

            addSimpleBlock(writer, ForgeRegistries.BLOCKS.getKey(gemBlock).getPath(), gemBlock);
            addSimpleBlock(writer, ForgeRegistries.BLOCKS.getKey(wallBlock).getPath(), wallBlock);
            addSimpleBlock(writer, ForgeRegistries.BLOCKS.getKey(fences).getPath(), fences);
            addSimpleBlock(writer, ForgeRegistries.BLOCKS.getKey(fencesGates).getPath(), fencesGates);
            addSimpleBlock(writer, ForgeRegistries.BLOCKS.getKey(slabs).getPath(), slabs);
        }

        for (String key: ModBlocksRegister.DIVINE_CRISTAL_BLOCKS.keySet()) {
            Block block = ModBlocksRegister.DIVINE_CRISTAL_BLOCKS.get(key);
            addOnlySilkTouchBlock(writer, ForgeRegistries.BLOCKS.getKey(block).getPath(), block);
        }

        addSilkTouchBlock(writer, ForgeRegistries.BLOCKS.getKey(ModBlocksRegister.ATIUM_CLUSTER.get()).getPath(),
                ModBlocksRegister.ATIUM_CLUSTER.get(), ModItemsRegister.ITEM_GEMS_BASE.get("atium"), 1, 1);
        addSilkTouchBlock(writer, ForgeRegistries.BLOCKS.getKey(ModBlocksRegister.LERASIUM_CLUSTER.get()).getPath(),
                ModBlocksRegister.LERASIUM_CLUSTER.get(), ModItemsRegister.ITEM_GEMS_BASE.get("lerasium"), 1, 1);
        addSilkTouchBlock(writer, ForgeRegistries.BLOCKS.getKey(ModBlocksRegister.ETTMETAL_CLUSTER.get()).getPath(),
                ModBlocksRegister.ETTMETAL_CLUSTER.get(), ModItemsRegister.ITEM_GEMS_BASE.get("ettmetal"), 1, 1);

        addSimpleBlock(writer, ForgeRegistries.BLOCKS.getKey(ModBlocksRegister.CRUCIBLE_FURNACE.get()).getPath(), ModBlocksRegister.CRUCIBLE_FURNACE.get());

    }
}