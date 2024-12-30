package net.rudahee.metallics_arts.setup.registries.items;

import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.setup.registries.ModItemsRegister;
import net.rudahee.metallics_arts.setup.registries.ModLivingEntityRegister;

public class ModEntityEggsRegister {


   public static void register() {
      ModItemsRegister.ENTITY_EGGS.put("iron_allomancer_entity_spawn_egg",MetallicsArts.registerItem("iron_allomancer_entity_spawn_egg",
              () -> new ForgeSpawnEggItem(ModLivingEntityRegister.IRON_ALLOMANCER, 0xF0ABD1, 0xAE4C82, new Item.Properties())));

      ModItemsRegister.ENTITY_EGGS.put("steel_allomancer_entity_spawn_egg",MetallicsArts.registerItem("steel_allomancer_entity_spawn_egg",
              () -> new ForgeSpawnEggItem(ModLivingEntityRegister.STEEL_ALLOMANCER, 0xC82A9F, 0xAE4C82, new Item.Properties())));

      ModItemsRegister.ENTITY_EGGS.put("ettmetal_allomancer_entity_spawn_egg",MetallicsArts.registerItem("ettmetal_allomancer_entity_spawn_egg",
              () -> new ForgeSpawnEggItem(ModLivingEntityRegister.ETTMETAL_ALLOMANCER, 0x5BFF9F, 0xAE4C82, new Item.Properties())));

      ModItemsRegister.ENTITY_EGGS.put("pewter_allomancer_entity_spawn_egg",MetallicsArts.registerItem("pewter_allomancer_entity_spawn_egg",
              () -> new ForgeSpawnEggItem(ModLivingEntityRegister.ETTMETAL_ALLOMANCER, 0x2FCD74, 0x2F0074, new Item.Properties())));

      ModItemsRegister.ENTITY_EGGS.put("gold_ferrin_entity", MetallicsArts.registerItem("gold_ferrin_entity_spawn_egg",
              () -> new ForgeSpawnEggItem(ModLivingEntityRegister.GOLD_FERRIN,  0xc6b722  , 0x000000 , new Item.Properties())));

      ModItemsRegister.ENTITY_EGGS.put("iron_ferrin_entity", MetallicsArts.registerItem("iron_ferrin_entity_spawn_egg",
              () -> new ForgeSpawnEggItem(ModLivingEntityRegister.IRON_FERRIN,  0xa3a2954  , 0xc6b722 , new Item.Properties())));

      ModItemsRegister.ENTITY_EGGS.put("haze_killer_melee_entity", MetallicsArts.registerItem("haze_killer_melee_entity_spawn_egg",
              () -> new ForgeSpawnEggItem(ModLivingEntityRegister.HAZE_KILLER_MELEE,  0x090909   , 0xa1a196 , new Item.Properties())));

      ModItemsRegister.ENTITY_EGGS.put("haze_killer_ranged_entity", MetallicsArts.registerItem("haze_killer_ranged_entity_spawn_egg",
              () -> new ForgeSpawnEggItem(ModLivingEntityRegister.HAZE_KILLER_RANGED,  0x090909   , 0xa1a196 , new Item.Properties())));

      ModItemsRegister.ENTITY_EGGS.put("haze_killer_tank_entity", MetallicsArts.registerItem("haze_killer_tank_entity_spawn_egg",
              () -> new ForgeSpawnEggItem(ModLivingEntityRegister.HAZE_KILLER_TANK,  0x090909   , 0xa1a196 , new Item.Properties())));
   }


}



