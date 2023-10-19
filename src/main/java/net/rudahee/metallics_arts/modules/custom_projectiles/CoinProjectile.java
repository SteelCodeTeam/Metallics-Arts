package net.rudahee.metallics_arts.modules.custom_projectiles;


import net.minecraft.client.multiplayer.chat.report.ReportEnvironment;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.game.ClientboundGameEventPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.rudahee.metallics_arts.data.enums.implementations.GunType;
import net.rudahee.metallics_arts.setup.registries.ModEntityTypesRegister;
import net.rudahee.metallics_arts.setup.registries.ModItemsRegister;

public class CoinProjectile extends ThrowableItemProjectile {
    private GunType gunType;

    /**
     * Constructs a CoinProjectile entity with the given entity type, level, and gun type.
     *
     * @param entityType The entity type of the coin projectile.
     * @param level The level in which the entity exists.
     * @param gunType The type of gun associated with this coin projectile.
     */
    public CoinProjectile(EntityType<? extends CoinProjectile> entityType, Level level, GunType gunType) {
        super(entityType, level);
        this.gunType = gunType;
    }

    /**
     * Constructs a CoinProjectile entity with the given level, living entity, and gun type.
     *
     * @param level The level in which the entity exists.
     * @param livingEntity The living entity that launches the projectile.
     * @param gunType The type of gun associated with this coin projectile.
     */
    public CoinProjectile(Level level, LivingEntity livingEntity, GunType gunType) {
        super(ModEntityTypesRegister.COIN_PROJECTILE.get(), livingEntity, level);
        this.gunType = gunType;
    }


    /**
     * Constructs a CoinProjectile entity with the initial velocity.
     *
     * @param level   The level in which the coin projectile is spawned.
     * @param v       The initial velocity in the X-axis.
     * @param v1      The initial velocity in the Y-axis.
     * @param v2      The initial velocity in the Z-axis.
     * @param gunType The type of gun that fired this projectile.
     */
    public CoinProjectile(Level level, double v, double v1, double v2, GunType gunType) {
        super(ModEntityTypesRegister.COIN_PROJECTILE.get(), v, v1, v2, level);
        this.gunType = gunType;
    }

    /**
     * Constructs a CoinProjectile entity with the given entity type and level. Sets the gun type to Copper Coin by default.
     *
     * @param entityType The entity type of the coin projectile.
     * @param level The level in which the entity exists.
     */
    public CoinProjectile(EntityType<? extends CoinProjectile> entityType, Level level) {
        super(entityType, level);
        this.gunType = GunType.COPPER_COIN;
    }

    /**
     * Retrieves the default item associated with this coin projectile based on its gun type.
     *
     * @return The default item, which is either a Copper Coin or a Bronze Coin.
     */
    protected Item getDefaultItem() {
        return (gunType == GunType.COPPER_COIN) ?
                ModItemsRegister.COPPER_COIN.get() :
                ModItemsRegister.BRONZE_COIN.get();
    }

    /**
     * Retrieves the particle option associated with the item held by this entity, or a default particle type if no item is present.
     *
     * @return The ParticleOptions representing the item's particle or ParticleTypes.WAX_ON if the item is empty.
     */
    private ParticleOptions getParticle() {
        ItemStack itemstack = this.getItemRaw();
        return (ParticleOptions) (itemstack.isEmpty() ? ParticleTypes.WAX_ON : new ItemParticleOption(ParticleTypes.ITEM, itemstack));
    }

    /**
     * Handles entity events for this entity. Specifically, it generates a particle effect when the entity event ID is 3.
     *
     * @param b The entity event ID to handle.
     */
    public void handleEntityEvent(byte b) {
        if (b == 3) {
            ParticleOptions particleoptions = this.getParticle();

            for(int i = 0; i < 8; ++i) {
                this.level.addParticle(particleoptions, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
            }
        }

    }

    /**
     * Handles the action when this projectile hits an entity.
     *
     * @param hitResult The result of the hit, containing information about the entity hit.
     */
    protected void onHitEntity(EntityHitResult hitResult) {
        super.onHitEntity(hitResult);
        Entity entityHurt = hitResult.getEntity();
        // Inflict damage to the hit entity
        Entity entitySource = this.getOwner();

        if (entitySource != null) {
            // If the entity that launched this projectile is a LivingEntity, set the last hurt mob to the hit entity
            if (entitySource instanceof LivingEntity) {
                ((LivingEntity) entitySource).setLastHurtMob(entityHurt);
            }
            // Inflict damage to the hit entity based on the source of the projectile
            if (entitySource instanceof ServerPlayer) {
                // If the source is a ServerPlayer, use indirect magic damage source
                entityHurt.hurt(entityHurt.damageSources().indirectMagic(entityHurt, entitySource), gunType.getDamage());
            } else {
                // Otherwise, use a generic damage source
                entityHurt.hurt(entityHurt.damageSources().generic(), gunType.getDamage());
            }
        } else {
            // If there is no specific source, use a generic damage source
            entityHurt.hurt(entityHurt.damageSources().generic(), gunType.getDamage());
        }
    }

    protected void onHit(HitResult hitResult) {
        super.onHit(hitResult);
        if (!this.level.isClientSide) {
            this.level.broadcastEntityEvent(this, (byte)3);
            this.discard();
        }

    }
}
