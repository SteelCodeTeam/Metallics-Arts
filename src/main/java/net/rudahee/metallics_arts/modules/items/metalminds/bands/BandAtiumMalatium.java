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

public class BandAtiumMalatium extends BandMindAbstract {
    private CompoundNBT nbt = new CompoundNBT();
    private static int MAX_ATIUM = 100;
    private static int MAX_MALATIUM = 16000;
    public BandAtiumMalatium (Item.Properties properties){
        super(properties, MetalsNBTData.ATIUM,MetalsNBTData.MALATIUM);
        nbt.putInt(MetallicsArts.MOD_ID+".BandAtiumMalatium.atium",0);
        nbt.putInt(MetallicsArts.MOD_ID+".BandAtiumMalatium.malatium",0);
        nbt.putInt(MetallicsArts.MOD_ID+".BandAtiumMalatium.capacityAtium",100);
        nbt.putInt(MetallicsArts.MOD_ID+".BandAtiumMalatium.capacityMalatium",100);
        setNbt(nbt);

        nbt.putInt(MetallicsArts.MOD_ID + ".feruchemic_atium_reserve", 0);
        nbt.putInt(MetallicsArts.MOD_ID + ".feruchemic_malatium_reserve", 0);
        nbt.putString(MetallicsArts.MOD_ID + ".user_key", super.unkeyedString);

        setNbt(nbt);
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

                    if (data.isDecanting(MetalsNBTData.ATIUM)) {
                        if (nbt.getInt(MetallicsArts.MOD_ID + ".feruchemic_atium_reserve") > 0) {
                            this.nbt.putInt(MetallicsArts.MOD_ID + ".feruchemic_atium_reserve", this.nbt.getInt(MetallicsArts.MOD_ID + ".feruchemic_atium_reserve") - 1);
                        } else {
                            data.setDecanting(MetalsNBTData.ATIUM, false);
                        }
                        needUpdate = true;
                    } else if (data.isStoring(MetalsNBTData.ATIUM)) {
                        if (nbt.getInt(MetallicsArts.MOD_ID + ".feruchemic_atium_reserve") <= this.MAX_ATIUM) {
                            this.nbt.putInt(MetallicsArts.MOD_ID + ".feruchemic_atium_reserve", this.nbt.getInt(MetallicsArts.MOD_ID + ".feruchemic_atium_reserve") + 1);
                        } else {
                            data.setStoring(MetalsNBTData.ATIUM, false);
                        }
                        needUpdate = true;
                    }

                    if (data.isDecanting(MetalsNBTData.MALATIUM)) {
                        if (nbt.getInt(MetallicsArts.MOD_ID + ".feruchemic_malatium_reserve") > 0) {
                            this.nbt.putInt(MetallicsArts.MOD_ID + ".feruchemic_malatium_reserve", this.nbt.getInt(MetallicsArts.MOD_ID + ".feruchemic_malatium_reserve") - 1);
                        } else {
                            data.setDecanting(MetalsNBTData.MALATIUM, false);
                        }
                        needUpdate = true;
                    } else if (data.isStoring(MetalsNBTData.MALATIUM)) {
                        if (nbt.getInt(MetallicsArts.MOD_ID + ".feruchemic_malatium_reserve") <= this.MAX_MALATIUM) {
                            this.nbt.putInt(MetallicsArts.MOD_ID + ".feruchemic_malatium_reserve", this.nbt.getInt(MetallicsArts.MOD_ID + ".feruchemic_malatium_reserve") + 1);
                        } else {
                            data.setDecanting(MetalsNBTData.MALATIUM, false);
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
}