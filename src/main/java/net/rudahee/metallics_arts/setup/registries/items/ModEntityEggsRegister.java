package net.rudahee.metallics_arts.setup.registries.items;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.data.providers.tags_providers.ModItemTagsProvider;
import net.rudahee.metallics_arts.modules.custom_entities.ettmetal_allomancer_entity.EttmetalAllomancerEntity;
import net.rudahee.metallics_arts.modules.custom_entities.iron_allomancer_entity.IronAllomancerEntity;
import net.rudahee.metallics_arts.modules.custom_entities.steel_allomancer_entity.SteelAllomancerEntity;
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

   }

}



