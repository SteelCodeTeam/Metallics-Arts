package net.rudahee.metallics_arts.modules.custom_projectiles;


import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.rudahee.metallics_arts.setup.registries.ModItemsRegister;

public class CopperProjectile extends ThrowableItemProjectile {

    public CopperProjectile(EntityType<? extends CopperProjectile> entityType, Level level) {
        super(entityType, level);
    }

    public CopperProjectile(Level level, LivingEntity livingEntity) {
        super(EntityType.SNOWBALL, livingEntity, level);
    }

    public CopperProjectile(Level level, double v, double v1, double v2) {
        super(EntityType.SNOWBALL, v, v1, v2, level);
    }

    protected Item getDefaultItem() {
        return Items.SNOWBALL;
    }

    private ParticleOptions getParticle() {
        ItemStack itemstack = this.getItemRaw();
        return (ParticleOptions)(itemstack.isEmpty() ? ParticleTypes.ITEM_SNOWBALL : new ItemParticleOption(ParticleTypes.ITEM, itemstack));
    }

    public void handleEntityEvent(byte p_37402_) {
        if (p_37402_ == 3) {
            ParticleOptions particleoptions = this.getParticle();

            for(int i = 0; i < 8; ++i) {
                this.level.addParticle(particleoptions, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
            }
        }

    }

    protected void onHitEntity(EntityHitResult p_37404_) {
        super.onHitEntity(p_37404_);
        Entity entity = p_37404_.getEntity();
        entity.hurt(DamageSource.thrown(this, this.getOwner()), 80F);
    }

    protected void onHit(HitResult p_37406_) {
        super.onHit(p_37406_);
        if (!this.level.isClientSide) {
            this.level.broadcastEntityEvent(this, (byte)3);
            this.discard();
        }

    }
    /*private int damage;

    public CopperProjectile(EntityType<? extends Snowball> entityType, Level level, int damage) {
        super(entityType, level);
        this.damage = damage;
    }

    public CopperProjectile(Level level, LivingEntity livingEntity, int damage) {
        super(EntityType.SNOWBALL, livingEntity, level);
        this.damage = damage;
    }

    public CopperProjectile(Level level, double x, double y, double z, int damage) {
        super(EntityType.SNOWBALL, x, y, z, level);
        this.damage = damage;
    }

    protected Item getDefaultItem() {
        return ModItemsRegister.COPPER_COIN.get();
    }

    private ParticleOptions getParticle() {
        ItemStack itemstack = this.getItemRaw();
        return itemstack.isEmpty() ? ParticleTypes.WAX_ON : new ItemParticleOption(ParticleTypes.ITEM, itemstack);
    }

    public void handleEntityEvent(byte bytes) {
        if (bytes == 3) {
            ParticleOptions particleoptions = this.getParticle();

            for(int i = 0; i < 8; ++i) {
                this.level.addParticle(particleoptions, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
            }
        }

    }

    protected void onHitEntity(EntityHitResult entityHitResult) {
        super.onHitEntity(entityHitResult);
        Entity entity = entityHitResult.getEntity();
        //int i = entity instanceof Blaze ? 3 : 0;
        entity.hurt(DamageSource.thrown(this, this.getOwner()), this.damage);
    }

    protected void onHit(HitResult hitResult) {
        super.onHit(hitResult);
        if (!this.level.isClientSide) {
            this.level.broadcastEntityEvent(this, (byte)3);
            this.discard();
        }

    }*/
}
