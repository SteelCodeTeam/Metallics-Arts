package net.rudahee.metallics_arts.modules.custom_projectiles;


import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
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

    public CoinProjectile(EntityType<? extends CoinProjectile> entityType, Level level, GunType gunType) {
        super(entityType, level);
        this.gunType = gunType;
    }

    public CoinProjectile(Level level, LivingEntity livingEntity, GunType gunType) {
        super(ModEntityTypesRegister.COIN_PROJECTILE.get(), livingEntity, level);
        this.gunType = gunType;
    }

    public CoinProjectile(Level level, double v, double v1, double v2, GunType gunType) {
        super(ModEntityTypesRegister.COIN_PROJECTILE.get(), v, v1, v2, level);
        this.gunType = gunType;
    }

    public CoinProjectile(EntityType<? extends CoinProjectile> entityType, Level level) {
        super(entityType, level);
        this.gunType = GunType.COPPER_COIN;
    }


    protected Item getDefaultItem() {
        return (gunType == GunType.COPPER_COIN) ?
                ModItemsRegister.COPPER_COIN.get() :
                ModItemsRegister.BRONZE_COIN.get();
    }

    private ParticleOptions getParticle() {
        ItemStack itemstack = this.getItemRaw();
        return (ParticleOptions) (itemstack.isEmpty() ? ParticleTypes.WAX_ON : new ItemParticleOption(ParticleTypes.ITEM, itemstack));
    }

    public void handleEntityEvent(byte p_37402_) {
        if (p_37402_ == 3) {
            ParticleOptions particleoptions = this.getParticle();

            for(int i = 0; i < 8; ++i) {
                this.level.addParticle(particleoptions, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
            }
        }

    }
    protected void onHitEntity(EntityHitResult hitResult) {
        super.onHitEntity(hitResult);
        Entity entityHurt = hitResult.getEntity();

        entityHurt.hurt(entityHurt.damageSources().thrown(entityHurt, this.getOwner()), gunType.getDamage());
        Entity entitySource = this.getOwner();
        if (entitySource != null) {
            if (entitySource instanceof LivingEntity) {
                ((LivingEntity) entitySource).setLastHurtMob(entityHurt);
            }
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
