package net.rudahee.metallics_arts.modules.items.metalminds.bands;


import net.minecraft.client.gui.screens.Screen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.rudahee.metallics_arts.modules.data_player.InvestedCapability;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;
import net.rudahee.metallics_arts.setup.network.ModNetwork;
import top.theillusivec4.curios.api.SlotContext;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class BandCopperBronze extends BandMindAbstract {

    public BandCopperBronze(Item.Properties properties){
        super(properties, MetalsNBTData.COPPER,MetalsNBTData.BRONZE,MetalsNBTData.COPPER.getMaxReserveBand(),MetalsNBTData.BRONZE.getMaxReserveBand());
    }

    private boolean nicConsume = false;

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        LivingEntity livingEntity = slotContext.entity();

        CompoundTag nbtLocal = stack.getTag();

        if (livingEntity.level instanceof ServerLevel) {
            if (livingEntity instanceof Player) {
                Player player = (Player) livingEntity;
                player.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(data -> {

                    if (data.isDecanting(MetalsNBTData.ALUMINUM)||data.isStoring(MetalsNBTData.ALUMINUM)){
                        stack.getTag().putString("key",changeOwner(player,stack.getTag(),false));
                    }

                    //PROPIO DE ESTA MENTE DE METAL <- EXPERIENCIA
                    if (data.isDecanting(getMetals(0))) {
                        if (stack.getTag().getInt(getMetals(0).getNameLower()+"_feruchemic_reserve")>0) {
                            player.giveExperiencePoints(1);
                            nbtLocal.putInt(getMetals(0).getNameLower()+"_feruchemic_reserve",(stack.getTag().getInt(getMetals(0).getNameLower()+"_feruchemic_reserve")-1));
                            stack.setTag(nbtLocal);
                        } else {
                            stack.getTag().putString("key",changeOwner(player,stack.getTag(),false));
                            data.setDecanting(getMetals(0),false);
                        }
                    } else if (data.isStoring(getMetals(0))){

                        if (stack.getTag().getInt(getMetals(0).getNameLower()+"_feruchemic_reserve") < stack.getTag().getInt(getMetals(0).getNameLower()+"_feruchemic_max_capacity")) {
                            if (player.totalExperience>0){
                                stack.getTag().putString("key",changeOwner(player,stack.getTag(),true));
                                player.giveExperiencePoints(-1);
                                nbtLocal.putInt(getMetals(0).getNameLower()+"_feruchemic_reserve",(stack.getTag().getInt(getMetals(0).getNameLower()+"_feruchemic_reserve")+1));
                            }
                            stack.setTag(nbtLocal);
                        } else {
                            data.setStoring(getMetals(0),false);
                        }
                    }

                    if (data.isDecanting(getMetals(1))) {
                        if (stack.getTag().getInt(getMetals(1).getNameLower()+"_feruchemic_reserve")>0) {
                            if (data.isDecanting(MetalsNBTData.NICROSIL)){
                                if (!nicConsume){
                                    nbtLocal.putInt(getMetals(1).getNameLower()+"_feruchemic_reserve",(stack.getTag().getInt(getMetals(1).getNameLower()+"_feruchemic_reserve")-1));
                                    stack.setTag(nbtLocal);
                                }
                                nicConsume = !nicConsume;
                            } else {
                                //las dos lineas de abajo van sin el nicrosil
                                nbtLocal.putInt(getMetals(1).getNameLower()+"_feruchemic_reserve",(stack.getTag().getInt(getMetals(1).getNameLower()+"_feruchemic_reserve")-1));
                                stack.setTag(nbtLocal);
                            }
                        } else {
                            stack.getTag().putString("key",changeOwner(player,stack.getTag(),false));
                            data.setDecanting(getMetals(1),false);
                        }

                    } else if (data.isStoring(getMetals(1))) {
                        if (stack.getTag().getInt(getMetals(1).getNameLower()+"_feruchemic_reserve") < stack.getTag().getInt(getMetals(1).getNameLower()+"_feruchemic_max_capacity")) {
                            if (data.isStoring(MetalsNBTData.NICROSIL)) {
                                if (!nicConsume){
                                    stack.getTag().putString("key",changeOwner(player,stack.getTag(),true));
                                    nbtLocal.putInt(getMetals(1).getNameLower()+"_feruchemic_reserve",(stack.getTag().getInt(getMetals(1).getNameLower()+"_feruchemic_reserve")+1));
                                    stack.setTag(nbtLocal);
                                }
                                nicConsume = !nicConsume;
                            } else {
                                //3 lineas, sin nicrosil
                                stack.getTag().putString("key",changeOwner(player,stack.getTag(),true));
                                nbtLocal.putInt(getMetals(1).getNameLower()+"_feruchemic_reserve",(stack.getTag().getInt(getMetals(1).getNameLower()+"_feruchemic_reserve")+1));
                                stack.setTag(nbtLocal);
                            }
                        } else {
                            data.setStoring(getMetals(1),false);
                        }
                    }
                    ModNetwork.sync(data, player);
                });
            }
        }
        super.curioTick(slotContext, stack);
    }


    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> toolTips, TooltipFlag flagIn) {
        if (stack.hasTag()) {
            if (!Screen.hasControlDown()){
                toolTips.add(Component.translatable("metallics_arts.metal_translate."+getMetals(0).getNameLower()).append(": "+ stack.getTag().getInt(getMetals(0).getNameLower()+"_feruchemic_reserve") +" pts xp"));
                toolTips.add(Component.translatable("metallics_arts.metal_translate."+getMetals(1).getNameLower()).append(": "+ stack.getTag().getInt(getMetals(1).getNameLower()+"_feruchemic_reserve") / 40 + "s"));
            } else {
                toolTips.add(Component.translatable("metallics_arts.metal_translate."+getMetals(0).getNameLower()).append(": "+ ((stack.getTag().getInt(getMetals(0).getNameLower()+"_feruchemic_reserve") * 100)/stack.getTag().getInt(getMetals(0).getNameLower()+"_feruchemic_max_capacity"))+"%"));
                toolTips.add(Component.translatable("metallics_arts.metal_translate."+getMetals(1).getNameLower()).append(": "+ ((stack.getTag().getInt(getMetals(1).getNameLower()+"_feruchemic_reserve") * 100)/stack.getTag().getInt(getMetals(1).getNameLower()+"_feruchemic_max_capacity"))+"%"));
            }
            if (world != null) {
                toolTips.add(Component.translatable("metallics_arts.mental_mind.owner").append(": " + ((stack.getTag().getString("key").equals("Nobody")) ? Component.translatable("metallics_arts.mental_mind.nobody").getString() : world.getPlayerByUUID(UUID.fromString((stack.getTag().getString("key")))).getName().getString())));
            }
        }
        super.appendHoverText(stack, world, toolTips, flagIn);
    }


}