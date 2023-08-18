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
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.rudahee.metallics_arts.data.enums.implementations.BulletType;
import net.rudahee.metallics_arts.setup.registries.ModEntityTypesRegister;

public class BulletProjectile extends ThrowableItemProjectile {

    private BulletType bulletType;

    public BulletProjectile(EntityType<? extends BulletProjectile> entityType, Level level) {
        super(entityType, level);
    }

    public BulletProjectile(Level level, LivingEntity livingEntity) {
        super(ModEntityTypesRegister.BULLET_PROJECTILE.get(), livingEntity, level);
    }

    public BulletProjectile(Level level, double v, double v1, double v2) {
        super(ModEntityTypesRegister.BULLET_PROJECTILE.get(), v, v1, v2, level);
    }

    @Override
    public boolean shouldRenderAtSqrDistance(double p_37470_) {
        return true;
    }

    @Override
    public boolean shouldRender(double p_20296_, double p_20297_, double p_20298_) {
        return true;
    }

    @Override
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
}