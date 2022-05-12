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

public class BandCadmiumBendalloy extends BandMindAbstract {
    CompoundNBT nbt = new CompoundNBT();
    private static int MAX_CADMIUM = 100;
    private static int MAX_BENDALLOY = 16000;
    public BandCadmiumBendalloy (Item.Properties properties){
        super(properties, MetalsNBTData.CADMIUM,MetalsNBTData.BENDALLOY);
        nbt.putInt(MetallicsArts.MOD_ID+".BandCadmiumBendalloy.candmium",0);
        nbt.putInt(MetallicsArts.MOD_ID+".BandCadmiumBendalloy.bendalloy",0);
        nbt.putInt(MetallicsArts.MOD_ID+".BandCadmiumBendalloy.capacityCadmium",100);
        nbt.putInt(MetallicsArts.MOD_ID+".BandCadmiumBendalloy.capacityBendalloy",100);
        setNbt(nbt);

        nbt.putInt(MetallicsArts.MOD_ID + ".feruchemic_bendalloy_reserve", 0);
        nbt.putInt(MetallicsArts.MOD_ID + ".feruchemic_cadmium_reserve", 0);
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

                    if (data.isDecanting(MetalsNBTData.BENDALLOY)) {
                        if (nbt.getInt(MetallicsArts.MOD_ID + ".feruchemic_bendalloy_reserve") > 0) {
                            this.nbt.putInt(MetallicsArts.MOD_ID + ".feruchemic_bendalloy_reserve", this.nbt.getInt(MetallicsArts.MOD_ID + ".feruchemic_bendalloy_reserve") - 1);
                        } else {
                            data.setDecanting(MetalsNBTData.BENDALLOY, false);
                        }
                        needUpdate = true;
                    } else if (data.isStoring(MetalsNBTData.BENDALLOY)) {
                        if (nbt.getInt(MetallicsArts.MOD_ID + ".feruchemic_bendalloy_reserve") <= this.MAX_BENDALLOY) {
                            this.nbt.putInt(MetallicsArts.MOD_ID + ".feruchemic_bendalloy_reserve", this.nbt.getInt(MetallicsArts.MOD_ID + ".feruchemic_bendalloy_reserve") + 1);
                        } else {
                            data.setStoring(MetalsNBTData.BENDALLOY, false);
                        }
                        needUpdate = true;
                    }

                    if (data.isDecanting(MetalsNBTData.CADMIUM)) {
                        if (nbt.getInt(MetallicsArts.MOD_ID + ".feruchemic_cadmium_reserve") > 0) {
                            this.nbt.putInt(MetallicsArts.MOD_ID + ".feruchemic_cadmium_reserve", this.nbt.getInt(MetallicsArts.MOD_ID + ".feruchemic_cadmium_reserve") - 1);
                        } else {
                            data.setDecanting(MetalsNBTData.CADMIUM, false);
                        }
                        needUpdate = true;
                    } else if (data.isStoring(MetalsNBTData.CADMIUM)) {
                        if (nbt.getInt(MetallicsArts.MOD_ID + ".feruchemic_cadmium_reserve") <= this.MAX_CADMIUM) {
                            this.nbt.putInt(MetallicsArts.MOD_ID + ".feruchemic_cadmium_reserve", this.nbt.getInt(MetallicsArts.MOD_ID + ".feruchemic_cadmium_reserve") + 1);
                        } else {
                            data.setDecanting(MetalsNBTData.CADMIUM, false);
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