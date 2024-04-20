package net.rudahee.metallics_arts.data.providers;

import net.minecraft.advancements.critereon.EnchantmentPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.AlternativesEntry;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.ApplyExplosionDecay;
import net.minecraft.world.level.storage.loot.functions.EnchantRandomlyFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.BonusLevelTableCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceWithLootingCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.number.BinomialDistributionGenerator;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.ForgeRegistries;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.setup.registries.ModBlocksRegister;
import net.rudahee.metallics_arts.setup.registries.ModItemsRegister;
import net.rudahee.metallics_arts.setup.registries.ModLivingEntityRegister;

import java.util.Random;
import java.util.function.BiConsumer;

public class ModEntityLootTableProvider implements LootTableSubProvider {




    @Override
    public void generate(BiConsumer<ResourceLocation, LootTable.Builder> writer) {


        LootPool.Builder builder = LootPool.lootPool()
                .name("iron_ferrin_entity")
                .setRolls(ConstantValue.exactly(1))
                .add(LootItem.lootTableItem(Items.BOOK).setWeight(3))
                    .when(LootItemRandomChanceCondition.randomChance(0.5F))
                .add(LootItem.lootTableItem(Items.BOOK).setWeight(1)
                        .apply(EnchantRandomlyFunction.randomEnchantment().withEnchantment(Enchantments.FALL_PROTECTION))
                        .when(LootItemRandomChanceCondition.randomChance(0.5F)));


        writer.accept(new ResourceLocation(MetallicsArts.MOD_ID, "entities/" + "iron_ferrin_entity"), LootTable.lootTable().setParamSet(LootContextParamSets.ENTITY).withPool(builder));

        builder = LootPool.lootPool()
                .name("gold_ferrin_entity")
                .setRolls(ConstantValue.exactly(1))
                .add(LootItem.lootTableItem(Items.BOOK).setWeight(3))
                .when(LootItemRandomChanceCondition.randomChance(0.5F))
                .add(LootItem.lootTableItem(Items.BOOK).setWeight(1)
                        .apply(EnchantRandomlyFunction.randomEnchantment().withEnchantment(Enchantments.ALL_DAMAGE_PROTECTION))
                        .when(LootItemRandomChanceCondition.randomChance(0.5F)));


        writer.accept(new ResourceLocation(MetallicsArts.MOD_ID, "entities/" + "gold_ferrin_entity"), LootTable.lootTable().setParamSet(LootContextParamSets.ENTITY).withPool(builder));

    }
}