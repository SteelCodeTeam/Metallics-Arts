package net.rudahee.metallics_arts.setup.registries;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.data.enums.implementations.GunType;
import net.rudahee.metallics_arts.modules.custom_projectiles.BulletProjectile;
import net.rudahee.metallics_arts.modules.custom_projectiles.CoinProjectile;

public class ModEntityTypesRegister {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MetallicsArts.MOD_ID);

    public static final RegistryObject<EntityType<BulletProjectile>> BULLET_PROJECTILE =
            ENTITY_TYPES.register("bullet_projectile", () -> EntityType.Builder
                    .<BulletProjectile>of(BulletProjectile::new, MobCategory.MISC)
                    .setShouldReceiveVelocityUpdates(true)
                    .setUpdateInterval(20)
                    .setCustomClientFactory((spawnEntity, world) -> new BulletProjectile(world, (LivingEntity) spawnEntity.getEntity()))
                    .sized(0.25F, 0.25F)
                    .build("bullet_projectile"));



    public static final RegistryObject<EntityType<CoinProjectile>> COIN_PROJECTILE =
            ENTITY_TYPES.register("coin_projectile", () -> EntityType.Builder
                    .<CoinProjectile>of(CoinProjectile::new, MobCategory.MISC)
                    .setShouldReceiveVelocityUpdates(true)
                    .setUpdateInterval(20)
                    .setCustomClientFactory((spawnEntity, world) -> new CoinProjectile(world, (LivingEntity) spawnEntity.getEntity(), GunType.COPPER_COIN))
                    .sized(0.25F, 0.25F)
                    .build("coin_projectile"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
