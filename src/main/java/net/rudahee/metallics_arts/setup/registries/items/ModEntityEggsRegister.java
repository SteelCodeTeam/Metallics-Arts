package net.rudahee.metallics_arts.setup.registries.items;

import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.setup.registries.ModItemsRegister;
import net.rudahee.metallics_arts.setup.registries.ModLivingEntityRegister;

public class ModEntityEggsRegister {


   public static void register() {
      ModItemsRegister.ENTITY_EGGS.put("iron_allomancer_entity_spawn_egg",MetallicsArts.registerItem("iron_allomancer_entity_spawn_egg",
              () -> new ForgeSpawnEggItem(ModLivingEntityRegister.IRON_ALLOMANCER, 0xFF1100, 0xFFFFFF, new Item.Properties())));

      ModItemsRegister.ENTITY_EGGS.put("steel_allomancer_entity_spawn_egg",MetallicsArts.registerItem("steel_allomancer_entity_spawn_egg",
              () -> new ForgeSpawnEggItem(ModLivingEntityRegister.STEEL_ALLOMANCER, 0xFF1100, 0xD4D4D4, new Item.Properties())));

      ModItemsRegister.ENTITY_EGGS.put("ettmetal_allomancer_entity_spawn_egg",MetallicsArts.registerItem("ettmetal_allomancer_entity_spawn_egg",
              () -> new ForgeSpawnEggItem(ModLivingEntityRegister.ETTMETAL_ALLOMANCER, 0xFF1100, 0x878787, new Item.Properties())));

      ModItemsRegister.ENTITY_EGGS.put("pewter_allomancer_entity_spawn_egg",MetallicsArts.registerItem("pewter_allomancer_entity_spawn_egg",
              () -> new ForgeSpawnEggItem(ModLivingEntityRegister.PEWTER_ALLOMANCER, 0xFF1100, 0x4A4A4A, new Item.Properties())));

      ModItemsRegister.ENTITY_EGGS.put("gold_ferrin_entity", MetallicsArts.registerItem("gold_ferrin_entity_spawn_egg",
              () -> new ForgeSpawnEggItem(ModLivingEntityRegister.GOLD_FERRIN,  0x00FF00  , 0xC7C7C7 , new Item.Properties())));

      ModItemsRegister.ENTITY_EGGS.put("iron_ferrin_entity", MetallicsArts.registerItem("iron_ferrin_entity_spawn_egg",
              () -> new ForgeSpawnEggItem(ModLivingEntityRegister.IRON_FERRIN,  0x00FF00  , 0xD4D4D , new Item.Properties())));

      ModItemsRegister.ENTITY_EGGS.put("haze_killer_melee_entity", MetallicsArts.registerItem("haze_killer_melee_entity_spawn_egg",
              () -> new ForgeSpawnEggItem(ModLivingEntityRegister.HAZE_KILLER_MELEE,  0x0033FF   , 0xD9D9D9 , new Item.Properties())));

      ModItemsRegister.ENTITY_EGGS.put("haze_killer_ranged_entity", MetallicsArts.registerItem("haze_killer_ranged_entity_spawn_egg",
              () -> new ForgeSpawnEggItem(ModLivingEntityRegister.HAZE_KILLER_RANGED,  0x0033FF   , 0x8C8C8C , new Item.Properties())));

      ModItemsRegister.ENTITY_EGGS.put("haze_killer_tank_entity", MetallicsArts.registerItem("haze_killer_tank_entity_spawn_egg",
              () -> new ForgeSpawnEggItem(ModLivingEntityRegister.HAZE_KILLER_TANK,  0x0033FF   , 0x292929 , new Item.Properties())));
   }


}



