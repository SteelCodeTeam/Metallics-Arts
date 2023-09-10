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
import net.rudahee.metallics_arts.data.enums.implementations.GunType;
import net.rudahee.metallics_arts.setup.registries.ModItemsRegister;

public class CoinProjectile extends ThrowableItemProjectile {
    private GunType gunType;

    public CoinProjectile(EntityType<? extends CoinProjectile> entityType, Level level, GunType gunType) {
        super(entityType, level);
        this.gunType = gunType;
    }

    public CoinProjectile(Level level, LivingEntity livingEntity, GunType gunType) {
        super(EntityType.SNOWBALL, livingEntity, level);
        this.gunType = gunType;
    }

    public CoinProjectile(Level level, double v, double v1, double v2, GunType gunType) {
        super(EntityType.SNOWBALL, v, v1, v2, level);
        this.gunType = gunType;
    }

    protected Item getDefaultItem() {
        return (gunType == GunType.COPPER_COIN) ?
                ModItemsRegister.COPPER_COIN.get() :
                ModItemsRegister.BRONZE_COIN.get();
    }

    private ParticleOptions getParticle() {
        ItemStack itemstack = this.getItemRaw();
        return (ParticleOptions) (itemstack.isEmpty() ? ParticleTypes.ITEM_SNOWBALL : new ItemParticleOption(ParticleTypes.ITEM, itemstack));
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
        entity.hurt(entity.damageSources().drown(), gunType.getDamage());
    }

    protected void onHit(HitResult p_37406_) {
        super.onHit(p_37406_);
        if (!this.level.isClientSide) {
            this.level.broadcastEntityEvent(this, (byte)3);
            this.discard();
        }

    }
}
