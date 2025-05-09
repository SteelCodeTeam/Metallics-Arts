package net.rudahee.metallics_arts.modules.custom_projectiles;

import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.rudahee.metallics_arts.setup.registries.ModEntityTypesRegister;

public class AnvilProjectile extends ThrowableItemProjectile {

    public AnvilProjectile(Level level, LivingEntity livingEntity) {
        super(ModEntityTypesRegister.ANVIL_PROJECTILE.get(), livingEntity, level);
    }

    public AnvilProjectile(EntityType<AnvilProjectile> anvilProjectileEntityType, Level level) {
        super(anvilProjectileEntityType, level);

    }


    protected Item getDefaultItem() {

        return Items.ANVIL;
    }

    @Override
    public boolean shouldRenderAtSqrDistance(double distance) {
        return true;
    }

    /**
     * Determines whether the projectile should be rendered at specific coordinates.
     *
     * @param x The X-coordinate.
     * @param y The Y-coordinate.
     * @param z The Z-coordinate.
     * @return True if the projectile should be rendered.
     */
    @Override
    public boolean shouldRender(double x, double y, double z) {
        return true;
    }


    /**
     * Retrieves the gravity applied to the projectile.
     *
     * @return The gravity value (0 for no gravity).
     */
    @Override
    protected float getGravity() {
        return 0.05f;
    }


    private ParticleOptions getParticle() {
        ItemStack itemstack = this.getItemRaw();
        return (itemstack.isEmpty() ? ParticleTypes.WAX_ON : new ItemParticleOption(ParticleTypes.ITEM, itemstack));
    }

    @Override
    public void handleEntityEvent(byte b) {
        if (b == 3) {
            ParticleOptions particleoptions = this.getParticle();
            for(int i = 0; i < 8; ++i) {
                this.level.addParticle(particleoptions, this.getX(), this.getY(), this.getZ(), 0, 0, 0);
            }
        }
    }


    /**
     * Called when the projectile hits an entity.
     *
     * Here, the damage to the hit is applied, or the damage is canceled and a projectile is
     * created that is sent in another direction if the person who is hit is a player,
     * it is burning steel and the bullet is not aluminum.
     *
     * @param entityHitResult The result of the entity hit.
     */
    @Override
    protected void onHitEntity(EntityHitResult entityHitResult) {
        super.onHitEntity(entityHitResult);
        Entity entity = entityHitResult.getEntity();

        entity.hurt(entity.damageSources().thrown(entity, this.getOwner()), 10f);
        Entity entitySource = this.getOwner();
        if (entitySource != null) {
            if (entitySource instanceof LivingEntity) {
                ((LivingEntity) entitySource).setLastHurtMob(entity);
            }
        }
    }



    /**
     * Called when the projectile hits something.
     *
     * This method is triggered when the projectile makes contact with a target.
     * It broadcasts an entity event to notify clients and then discards the
     * projectile in the game world.
     *
     * @param hitResult The result of the hit.
     */
    @Override
    protected void onHit(HitResult hitResult) {
        super.onHit(hitResult);
        if (!this.level.isClientSide) {
            this.level.broadcastEntityEvent(this, (byte)3);
            this.discard();
        }
    }
}
