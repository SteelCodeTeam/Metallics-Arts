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

public class BandChromiumNicrosil extends BandMindAbstract {
    CompoundNBT nbt = new CompoundNBT();

    private static int MAX_CHROMIUM = 100;
    private static int MAX_NICROSIL = 16000;
    public BandChromiumNicrosil(Item.Properties properties){
        super(properties, MetalsNBTData.CHROMIUM,MetalsNBTData.NICROSIL);
        nbt.putInt(MetallicsArts.MOD_ID+".BandChromiumNicrosil.chromium",0);
        nbt.putInt(MetallicsArts.MOD_ID+".BandChromiumNicrosil.nicrosil",0);
        nbt.putInt(MetallicsArts.MOD_ID+".BandChromiumNicrosil.capacityChromium",100);
        nbt.putInt(MetallicsArts.MOD_ID+".BandChromiumNicrosil.capacityNicrosil",100);
        setNbt(nbt);
        nbt.putInt(MetallicsArts.MOD_ID + ".feruchemic_nicrosil_reserve", 0);
        nbt.putInt(MetallicsArts.MOD_ID + ".feruchemic_chromium_reserve", 0);
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

                    if (data.isDecanting(MetalsNBTData.NICROSIL)) {
                        if (nbt.getInt(MetallicsArts.MOD_ID + ".feruchemic_nicrosil_reserve") > 0) {
                            this.nbt.putInt(MetallicsArts.MOD_ID + ".feruchemic_nicrosil_reserve", this.nbt.getInt(MetallicsArts.MOD_ID + ".feruchemic_nicrosil_reserve") - 1);
                        } else {
                            data.setDecanting(MetalsNBTData.NICROSIL, false);
                        }
                        needUpdate = true;
                    } else if (data.isStoring(MetalsNBTData.NICROSIL)) {
                        if (nbt.getInt(MetallicsArts.MOD_ID + ".feruchemic_nicrosil_reserve") <= this.MAX_NICROSIL) {
                            this.nbt.putInt(MetallicsArts.MOD_ID + ".feruchemic_nicrosil_reserve", this.nbt.getInt(MetallicsArts.MOD_ID + ".feruchemic_nicrosil_reserve") + 1);
                        } else {
                            data.setStoring(MetalsNBTData.NICROSIL, false);
                        }
                        needUpdate = true;
                    }

                    if (data.isDecanting(MetalsNBTData.CHROMIUM)) {
                        if (nbt.getInt(MetallicsArts.MOD_ID + ".feruchemic_chromium_reserve") > 0) {
                            this.nbt.putInt(MetallicsArts.MOD_ID + ".feruchemic_chromium_reserve", this.nbt.getInt(MetallicsArts.MOD_ID + ".feruchemic_chromium_reserve") - 1);
                        } else {
                            data.setDecanting(MetalsNBTData.CHROMIUM, false);
                        }
                        needUpdate = true;
                    } else if (data.isStoring(MetalsNBTData.CHROMIUM)) {
                        if (nbt.getInt(MetallicsArts.MOD_ID + ".feruchemic_chromium_reserve") <= this.MAX_CHROMIUM) {
                            this.nbt.putInt(MetallicsArts.MOD_ID + ".feruchemic_chromium_reserve", this.nbt.getInt(MetallicsArts.MOD_ID + ".feruchemic_chromium_reserve") + 1);
                        } else {
                            data.setDecanting(MetalsNBTData.CHROMIUM, false);
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