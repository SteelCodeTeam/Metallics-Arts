package net.rudahee.metallics_arts.modules.custom_projectiles;

import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.rudahee.metallics_arts.data.enums.implementations.BulletType;
import net.rudahee.metallics_arts.data.enums.implementations.GunType;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.player.data.IInvestedPlayerData;
import net.rudahee.metallics_arts.modules.error_handling.exceptions.PlayerException;
import net.rudahee.metallics_arts.setup.registries.ModEntityTypesRegister;
import net.rudahee.metallics_arts.setup.registries.ModItemsRegister;
import net.rudahee.metallics_arts.utils.CapabilityUtils;

public class BulletProjectile extends ThrowableItemProjectile {
    private GunType gunType;
    private BulletType bulletType;
    private int despawnTime;

    /**
     * Constructs a new instance of a BulletProjectile.
     *
     * @param entityType The entity type of the BulletProjectile.
     * @param level The level in which the BulletProjectile will exist.
     */
    public BulletProjectile(EntityType<? extends BulletProjectile> entityType, Level level) {
        super(entityType, level);
        this.gunType = GunType.REVOLVER;
        this.bulletType = BulletType.LEAD;
        this.despawnTime = gunType.getDespawn();
    }

    /**
     * Constructs a new instance of BulletProjectile associated with a living entity.
     *
     * @param level The level in which the BulletProjectile will exist.
     * @param livingEntity The living entity associated with the BulletProjectile.
     * @param gunType The type of gun associated with the BulletProjectile.
     * @param bulletType The type of bullet associated with the BulletProjectile.
     */
    public BulletProjectile(Level level, LivingEntity livingEntity, GunType gunType, BulletType bulletType) {
        super(ModEntityTypesRegister.BULLET_PROJECTILE.get(), livingEntity, level);
        this.gunType = gunType;
        this.bulletType = bulletType;
        this.despawnTime = gunType.getDespawn();
    }

    /**
     * Constructs a new instance of BulletProjectile associated with a living entity.
     *
     * @param level The level in which the BulletProjectile will exist.
     * @param livingEntity The living entity associated with the BulletProjectile.
     */
    public BulletProjectile(Level level, LivingEntity livingEntity) {
        super(ModEntityTypesRegister.BULLET_PROJECTILE.get(), livingEntity, level);
        this.gunType = GunType.REVOLVER;
        this.bulletType = BulletType.LEAD;
        this.despawnTime = gunType.getDespawn();
    }

    public BulletProjectile(Level level, double v, double v1, double v2) {
        super(ModEntityTypesRegister.BULLET_PROJECTILE.get(), v, v1, v2, level);
        this.gunType = GunType.REVOLVER;
        this.bulletType = BulletType.LEAD;
        this.despawnTime = gunType.getDespawn();
    }

    @Override
    public boolean shouldRenderAtSqrDistance(double distance) {
        return true;
    }

    @Override
    public boolean shouldRender(double x, double y, double z) {
        return true;
    }

    @Override
    protected float getGravity() {
        return 0;
    }

    @Override
    protected Item getDefaultItem() {
        return (this.bulletType == BulletType.LEAD ?
                ModItemsRegister.LEAD_BULLET_PROJECTILE.get() :
                ModItemsRegister.ALUMINUM_BULLET_PROJECTILE.get());
    }
    private ParticleOptions getParticle() {
        ItemStack itemstack = this.getItemRaw();
        return itemstack.isEmpty() ? ParticleTypes.EXPLOSION : new ItemParticleOption(ParticleTypes.ITEM, itemstack);
    }

    /**
     * Called every tick to update the projectile's state.
     *
     * This method is executed every tick to update the state of the projectile.
     * It decrements the despawn time, and if the despawn time reaches zero, the
     * projectile is discarded from the game world.
     */
    @Override
    public void tick() {
        this.despawnTime--;
        if (this.despawnTime == 0) {
            this.discard();
        }
        super.tick();
    }

    /**
     * Handles an entity event received from the server.
     *
     * This method is responsible for processing and responding to entity events sent from the server.
     * Specifically, when the event byte is 3, it generates particles associated with the projectile.
     *
     * @param b The entity event byte received from the server.
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
     * Called when the projectile hits an entity.
     *
     * Here, the damage to the hit is applied, or the damage is canceled and a projectile is
     * created that is sent in another direction if the person who is hit is a player,
     * it is burning steel and the bullet is not aluminum.
     *
     * @param entityHitResult The result of the entity hit.
     */
    protected void onHitEntity(EntityHitResult entityHitResult) {
        super.onHitEntity(entityHitResult);
        Entity entity = entityHitResult.getEntity();
        if (entity instanceof Player player) {
            try {
                IInvestedPlayerData playerCapability = CapabilityUtils.getCapability(entity);
                if (playerCapability.isBurning(MetalTagEnum.STEEL) && this.bulletType.getType().equals(BulletType.LEAD.getType())) {
                    BulletProjectile bullet = new BulletProjectile(level, player, gunType, this.bulletType);
                    bullet.shootFromRotation(entity, entity.getXRot(), entity.getYRot(), 0.0F, 5F, 1.0F);
                    level.addFreshEntity(bullet);
                }
            } catch (PlayerException ex) {
                ex.printCompleteLog();
            }
        } //todo chekear
        entity.hurt(entity.damageSources().thrown(entity, this.getOwner()), this.gunType.getDamage());
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
    protected void onHit(HitResult hitResult) {
        super.onHit(hitResult);
        if (!this.level.isClientSide) {
            this.level.broadcastEntityEvent(this, (byte)3);
            this.discard();
        }
    }
}
