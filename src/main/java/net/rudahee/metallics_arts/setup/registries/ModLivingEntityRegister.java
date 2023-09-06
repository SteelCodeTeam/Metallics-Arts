package net.rudahee.metallics_arts.setup.registries;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.modules.custom_entities.alomancer_entity.AlomancerEntity;

public class ModLivingEntityRegister {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MetallicsArts.MOD_ID);
    public static final RegistryObject<EntityType<AlomancerEntity>> ALOMANCER = ENTITY_TYPES.register("alomancer_entity",
            () -> EntityType.Builder.of(AlomancerEntity::new, MobCategory.MONSTER).sized(1.0f, 2.0f).build(MetallicsArts.MOD_ID + ":alomancer"));
    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
