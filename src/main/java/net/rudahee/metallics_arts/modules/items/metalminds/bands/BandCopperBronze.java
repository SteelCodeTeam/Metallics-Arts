package net.rudahee.metallics_arts.modules.items.metalminds.bands;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.server.ServerWorld;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.modules.data_player.InvestedCapability;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;
import net.rudahee.metallics_arts.setup.network.ModNetwork;

public class BandCopperBronze extends BandMindAbstract {
    CompoundNBT nbt = new CompoundNBT();

    private static int MAX_COPPER = 100;
    private static int MAX_BRONZE = 16000;
    public BandCopperBronze(Item.Properties properties){
        super(properties, MetalsNBTData.COPPER,MetalsNBTData.BRONZE);
        nbt.putInt(MetallicsArts.MOD_ID+".BandCopperBronze.copper",0);
        nbt.putInt(MetallicsArts.MOD_ID+".BandCopperBronze.bronze",0);
        nbt.putInt(MetallicsArts.MOD_ID+".BandCopperBronze.capacityCopper",100);
        nbt.putInt(MetallicsArts.MOD_ID+".BandCopperBronze.capacityBronze",100);

        nbt.putInt(MetallicsArts.MOD_ID + ".feruchemic_bronze_reserve", 0);
        nbt.putInt(MetallicsArts.MOD_ID + ".feruchemic_copper_reserve", 0);
        nbt.putString(MetallicsArts.MOD_ID + ".user_key", super.unkeyedString);


    }

    private static boolean needUpdate = false;

    @Override
    public void curioTick(String identifier, int index, LivingEntity livingEntity, ItemStack stack) {
        super.curioTick(identifier, index, livingEntity, stack);

        if (livingEntity.level instanceof ServerWorld) {
            needUpdate = false;

            if (livingEntity instanceof PlayerEntity) {
                PlayerEntity player = (PlayerEntity) livingEntity;

                player.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(data -> {

                    if (data.isDecanting(MetalsNBTData.BRONZE)) {
                        if (nbt.getInt(MetallicsArts.MOD_ID + ".feruchemic_bronze_reserve") > 0) {
                            this.nbt.putInt(MetallicsArts.MOD_ID + ".feruchemic_bronze_reserve", this.nbt.getInt(MetallicsArts.MOD_ID + ".feruchemic_bronze_reserve") - 1);
                        } else {
                            data.setDecanting(MetalsNBTData.BRONZE, false);
                        }
                        needUpdate = true;
                    } else if (data.isStoring(MetalsNBTData.BRONZE)) {
                        if (nbt.getInt(MetallicsArts.MOD_ID + ".feruchemic_bronze_reserve") <= this.MAX_BRONZE) {
                            this.nbt.putInt(MetallicsArts.MOD_ID + ".feruchemic_bronze_reserve", this.nbt.getInt(MetallicsArts.MOD_ID + ".feruchemic_bronze_reserve") + 1);
                        } else {
                            data.setStoring(MetalsNBTData.BRONZE, false);
                        }
                        needUpdate = true;
                    }

                    if (data.isDecanting(MetalsNBTData.COPPER)) {
                        if (nbt.getInt(MetallicsArts.MOD_ID + ".feruchemic_copper_reserve") > 0) {
                            this.nbt.putInt(MetallicsArts.MOD_ID + ".feruchemic_copper_reserve", this.nbt.getInt(MetallicsArts.MOD_ID + ".feruchemic_copper_reserve") - 1);
                        } else {
                            data.setDecanting(MetalsNBTData.COPPER, false);
                        }
                        needUpdate = true;
                    } else if (data.isStoring(MetalsNBTData.COPPER)) {
                        if (nbt.getInt(MetallicsArts.MOD_ID + ".feruchemic_copper_reserve") <= this.MAX_COPPER) {
                            this.nbt.putInt(MetallicsArts.MOD_ID + ".feruchemic_copper_reserve", this.nbt.getInt(MetallicsArts.MOD_ID + ".feruchemic_copper_reserve") + 1);
                        } else {
                            data.setDecanting(MetalsNBTData.COPPER, false);
                        }
                        needUpdate = true;
                    }

                    if (needUpdate) {
                        ModNetwork.sync(data, player);
                    }

                });
            }
        }
    }

    @Override
    public void storing(CompoundNBT nbt, String metal, int qty) {
        nbt.putInt(metal, nbt.getInt(metal) + qty);
    }

    @Override
    public void decanting(CompoundNBT nbt, String metal, int qty) {
        nbt.putInt(metal,nbt.getInt(metal)-qty);
    }
}