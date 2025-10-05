package net.rudahee.metallics_arts.data.providers;

import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.EnchantRandomlyFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.rudahee.metallics_arts.MetallicsArts;

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