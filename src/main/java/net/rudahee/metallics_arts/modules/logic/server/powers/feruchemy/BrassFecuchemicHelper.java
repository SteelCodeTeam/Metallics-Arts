package net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.biome.Biomes;
import net.minecraftforge.common.Tags;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.players.IInvestedPlayerData;

import java.util.function.Supplier;

public class BrassFecuchemicHelper extends AbstractFechuchemicHelper{

    @Override
    public void decantPower(Player player) {
        if (player.getLevel().getBiome(player.getOnPos()).is(Biomes.DESERT) && player.getLevel().isDay()) {
            player.setSecondsOnFire(1);
        } else if (player.getLevel().getBiome(player.getOnPos()).is(Tags.Biomes.IS_HOT)) {
            player.setSecondsOnFire(1);
        }
    }

    //GIVE FIRE RESISTANCE OR FROZEN TICKS
    @Override
    public void storagePower(Player player) {
        player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 40, 1, true, false));

        if (player.getLevel().getBiome(player.getOnPos()).is(Tags.Biomes.IS_COLD) ||
                (player.getLevel().getBiome(player.getOnPos()).is(Biomes.DESERT) && player.getLevel().isNight())) {
            player.setTicksFrozen(player.getTicksFrozen() + 3);
        }
    }

    public static Supplier<? extends BrassFecuchemicHelper> getInstance() {
        return BrassFecuchemicHelper::new;
    }

    //CUSTOM CHARGE: IF PLAYER IS ON FIRE
    @Override
    public CompoundTag CalculateCharge(CompoundTag compoundTag, Player player, IInvestedPlayerData playerCapability, int metalReserve, String metalKey, boolean nicConsume) {
        if (player.isOnFire()) {
            if (!playerCapability.isStoring(MetalTagEnum.NICROSIL) || !nicConsume) {
                compoundTag.putInt(metalKey, metalReserve + 1);
            }
        }
        return compoundTag;
    }

    public static void addFireAspectToPlayer(LivingEntity livingEntity, int secondsFire){
        livingEntity.setSecondsOnFire(secondsFire);
    }
}
