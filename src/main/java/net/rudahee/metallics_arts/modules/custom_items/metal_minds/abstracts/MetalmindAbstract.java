package net.rudahee.metallics_arts.modules.custom_items.metal_minds.abstracts;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.enums.implementations.MetalmindType;
import net.rudahee.metallics_arts.data.player.data.IInvestedPlayerData;
import net.rudahee.metallics_arts.modules.custom_items.metal_minds.AluminumDuraluminMetalmind;
import net.rudahee.metallics_arts.modules.custom_items.metal_minds.AtiumMalatiumMetalmind;
import net.rudahee.metallics_arts.modules.custom_items.metal_minds.CopperBronzeMetalmind;
import net.rudahee.metallics_arts.modules.custom_items.metal_minds.LerasiumEttmetalMetalmind;
import net.rudahee.metallics_arts.modules.effects.ModEffects;
import net.rudahee.metallics_arts.modules.error_handling.exceptions.PlayerException;
import net.rudahee.metallics_arts.setup.network.ModNetwork;
import net.rudahee.metallics_arts.utils.CapabilityUtils;
import net.rudahee.metallics_arts.utils.MetalMindsUtils;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class MetalmindAbstract extends Item implements ICurioItem {

    private final MetalTagEnum[] metals = new MetalTagEnum[2];
    public String unkeyedString = "Nobody";
    private MetalmindType type = null;

    public MetalmindAbstract(Item.Properties properties, MetalTagEnum metal0, MetalTagEnum metal1, MetalmindType type) {
        super(properties);
        this.metals[0]=metal0;
        this.metals[1]=metal1;
        this.type = type;
    }

    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        ICurioItem.super.onEquip(slotContext, prevStack, stack);
        Player player = (Player) slotContext.getWearer();
        try {
            IInvestedPlayerData playerCapability = CapabilityUtils.getCapability(player);

            playerCapability.setMetalMindEquiped(this.metals[0].getGroup(),true);
            playerCapability.setMetalMindEquiped(this.metals[1].getGroup(),true);
            ModNetwork.syncInvestedDataPacket(playerCapability, player);

        } catch (PlayerException ex) {
            ex.printCompleteLog();
        }
    }


    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        ICurioItem.super.onUnequip(slotContext, newStack, stack);
        Player player = (Player) slotContext.getWearer();
        if (stack.getItem() != newStack.getItem()) {
            try {
                IInvestedPlayerData playerCapability = CapabilityUtils.getCapability(player);

                playerCapability.setMetalMindEquiped(this.metals[0].getGroup(),false);
                playerCapability.setMetalMindEquiped(this.metals[1].getGroup(),false);
                playerCapability.setStoring(this.metals[0],false);
                playerCapability.setStoring(this.metals[1],false);
                playerCapability.setTapping(this.metals[0],false);
                playerCapability.setTapping(this.metals[1],false);

                ModNetwork.syncInvestedDataPacket(playerCapability, player);

            } catch (PlayerException ex) {
                ex.printCompleteLog();
            }
        }
    }


    @Override
    public boolean canEquip(SlotContext slotContext, ItemStack stack) {
        ICurioItem.super.canEquip(slotContext, stack);
        Player player = (Player) slotContext.entity();

        if(!stack.hasTag()) {
            stack.setTag(addTags());
        }

        try {
            IInvestedPlayerData playerCapability = CapabilityUtils.getCapability(player);

            return !playerCapability.hasMetalMindEquiped(this.metals[0].getGroup()) && (stack.getTag().getString("key").equals(unkeyedString) || player.getStringUUID().equals(stack.getTag().getString("key")));

        } catch (PlayerException e) {
            e.printResumeLog();
            return false;
        }
    }

    @Override
    public boolean canEquipFromUse(SlotContext slotContext, ItemStack stack) {
        ICurioItem.super.canEquip(slotContext, stack);
        Player player = (Player) slotContext.entity();

        if(!stack.hasTag()) {
            stack.setTag(addTags());
        }

        try {
            IInvestedPlayerData playerCapability = CapabilityUtils.getCapability(player);

            return !playerCapability.hasMetalMindEquiped(this.metals[0].getGroup()) && (stack.getTag().getString("key").equals(unkeyedString) || player.getStringUUID().equals(stack.getTag().getString("key")));

        } catch (PlayerException e) {
            e.printResumeLog();
            return false;
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> toolTips, TooltipFlag flagIn) {

        if(!stack.hasTag()) {
            stack.setTag(addTags());
        }

        if (this instanceof LerasiumEttmetalMetalmind || this instanceof AtiumMalatiumMetalmind || this instanceof CopperBronzeMetalmind
                || this instanceof AluminumDuraluminMetalmind) {
            return;
        }

        int maxReserve0 = this.type == MetalmindType.BAND ? this.metals[0].getMaxReserveBand() : this.metals[0].getMaxReserveRing();
        int maxReserve1 = this.type == MetalmindType.BAND ? this.metals[1].getMaxReserveBand() : this.metals[1].getMaxReserveRing();

        if (stack.hasTag()) {
            if (!Screen.hasShiftDown()) {
                toolTips.add(Component.translatable("metallics_arts.metal_translate."+metals[0].getNameLower()).append(": "+ stack.getTag().getInt(metals[0].getNameLower()+"_feruchemic_reserve") / 20 + "s"));
                toolTips.add(Component.translatable("metallics_arts.metal_translate."+metals[1].getNameLower()).append(": "+ stack.getTag().getInt(metals[1].getNameLower()+"_feruchemic_reserve") / 20 + "s"));
            } else {
                toolTips.add(Component.translatable("metallics_arts.metal_translate."+metals[0].getNameLower()).append(": "+ ((stack.getTag().getInt(metals[0].getNameLower()+"_feruchemic_reserve") * 100)/maxReserve0)+"%"));
                toolTips.add(Component.translatable("metallics_arts.metal_translate."+metals[1].getNameLower()).append(": "+ ((stack.getTag().getInt(metals[1].getNameLower()+"_feruchemic_reserve") * 100)/maxReserve1)+"%"));
            }
            if (level != null) {
                toolTips.add(Component.translatable("metallics_arts.metal_mind.owner").append(": "+ ((stack.getTag().getString("key").equals("Nobody")) ? Component.translatable("metallics_arts.metal_mind.nobody").getString() : (level.getPlayerByUUID(UUID.fromString((stack.getTag().getString("key")))) == null) ? Component.translatable("metallics_arts.metal_mind.owner_someone") : level.getPlayerByUUID(UUID.fromString((stack.getTag().getString("key")))).getName().getString())));
            }
            if (!Screen.hasShiftDown()) {
                toolTips.add(Component.translatable(" "));
                toolTips.add(Component.translatable("metallics_arts.metal_mind_translate.shift_info").withStyle(ChatFormatting.BLUE));
            }
        }
        super.appendHoverText(stack, level, toolTips, flagIn);
    }

    private CompoundTag addTags() {
        CompoundTag compoundTag = new CompoundTag();
        compoundTag.putInt(this.metals[0].getNameLower()+"_feruchemic_reserve",0);
        compoundTag.putInt(this.metals[1].getNameLower()+"_feruchemic_reserve",0);
        compoundTag.putString("key",this.unkeyedString);
        return compoundTag;
    }

    private boolean nicConsume = false;

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        LivingEntity livingEntity = slotContext.entity();

        if(!stack.hasTag()) {
            stack.setTag(addTags());
        }

        CompoundTag compoundTag = stack.getTag();
        if (livingEntity.level instanceof ServerLevel) {
            if (livingEntity instanceof Player player) {
                IInvestedPlayerData playerCapability;
                try {
                    playerCapability = CapabilityUtils.getCapability(player);
                } catch (PlayerException ex) {
                    ex.printCompleteLog();
                    return;
                }

                if (playerCapability.isTapping(MetalTagEnum.ALUMINUM) || playerCapability.isStoring(MetalTagEnum.ALUMINUM)) {
                    stack.setTag(MetalMindsUtils.changeOwner(player, compoundTag, false, this.metals[0], this.metals[1]));
                }

                int actualReserve = stack.getTag().getInt(this.metals[0].getNameLower() + "_feruchemic_reserve");
                int maxReserve = this.type == MetalmindType.BAND ? this.metals[0].getMaxReserveBand() : this.metals[0].getMaxReserveRing();
                // Tap.
                if (playerCapability.isTapping(this.metals[0])) {
                    if (actualReserve > 0) {
                        stack.setTag(calculateDischarge(compoundTag, player,playerCapability, actualReserve, nicConsume, this.metals[0]));
                    } else {
                        stack.setTag(MetalMindsUtils.changeOwner(player, compoundTag, false, this.metals[0], this.metals[1]));
                        playerCapability.setTapping(this.metals[0], false);
                    }
                    // Storage.
                } else if (playerCapability.isStoring(this.metals[0])) {
                    if (actualReserve < maxReserve) {
                        stack.setTag(MetalMindsUtils.changeOwner(player, compoundTag, true, this.metals[0], this.metals[1]));
                        stack.setTag(calculateCharge(compoundTag, player,playerCapability, actualReserve, nicConsume, this.metals[0]));
                    } else {
                        playerCapability.setStoring(this.metals[0], false);
                    }
                }
                actualReserve = stack.getTag().getInt(this.metals[1].getNameLower() + "_feruchemic_reserve");
                maxReserve = this.type == MetalmindType.BAND ? this.metals[1].getMaxReserveBand() : this.metals[1].getMaxReserveRing();
                // Tap.
                if (playerCapability.isTapping(this.metals[1])) {
                    if (actualReserve > 0) {
                        stack.setTag(calculateDischarge(compoundTag, player,playerCapability, actualReserve, nicConsume, this.metals[1]));
                    } else {
                        stack.setTag(MetalMindsUtils.changeOwner(player, compoundTag, false, this.metals[0], this.metals[1]));
                        playerCapability.setTapping(this.metals[1], false);
                    }
                    // Storage.
                } else if (playerCapability.isStoring(this.metals[1])) {
                    if (actualReserve < maxReserve) {
                        stack.setTag(MetalMindsUtils.changeOwner(player, compoundTag, true, this.metals[0], this.metals[1]));
                        stack.setTag(calculateCharge(compoundTag, player,playerCapability, actualReserve, nicConsume, this.metals[1]));
                    } else {
                        playerCapability.setStoring(this.metals[1], false);
                    }
                }
                if (playerCapability.isStoring(MetalTagEnum.NICROSIL) || playerCapability.isTapping(MetalTagEnum.NICROSIL)) {
                    nicConsume = !nicConsume;
                }
                ModNetwork.syncInvestedDataPacket(playerCapability, player);
            }
        }
        ICurioItem.super.curioTick(slotContext, stack);
    }

    public MetalTagEnum getMetals(int pos) {
        return this.metals[pos];
    }

    public boolean isBand(MetalmindType type) {
        return this.type == type;
    }

    public CompoundTag calculateDischarge(CompoundTag compoundTag, Player player,IInvestedPlayerData playerCapability, int metalReserve, boolean nicConsume, MetalTagEnum metal) {
        String metalKey = metal.getNameLower() + "_feruchemic_reserve";

        ModEffects.giveFeruchemicalTapEffect(player, metal);

        if (metal == MetalTagEnum.COPPER) {
            return customDischargeCopper(compoundTag, metalReserve, metalKey);
        } else if (metal == MetalTagEnum.ETTMETAL) {
            return calculateDischargeEttmetal(compoundTag, player, metalKey);
        } else if (metal == MetalTagEnum.LERASIUM) {
            return calculateDischargeLerasium(compoundTag, playerCapability, metalKey);
        } else if (metal == MetalTagEnum.MALATIUM) {
            return calculateDischargeMalatium(compoundTag, player, metalReserve, metalKey);
        } else if (metal == MetalTagEnum.ALUMINUM) {
            return calculateDischargeAluminum(compoundTag, metalKey);
        } else if (metal == MetalTagEnum.NICROSIL) {
            return calculateDischargeNicrosil(compoundTag, playerCapability, metalReserve, metalKey, nicConsume);
        }else {
            if (!playerCapability.isTapping(MetalTagEnum.NICROSIL) || !nicConsume) {
                compoundTag.putInt(metalKey, metalReserve - 1);
            }
        }
        return compoundTag;
    }

    public CompoundTag calculateCharge(CompoundTag compoundTag, Player player,IInvestedPlayerData playerCapability, int metalReserve, boolean nicConsume, MetalTagEnum metal) {
        String metalKey = metal.getNameLower() + "_feruchemic_reserve";

        ModEffects.giveFeruchemicalStorageEffect(player, metal);

        if (metal == MetalTagEnum.BRASS) {
            return customChargeBrass(compoundTag, player, playerCapability, metalReserve, metalKey, nicConsume);
        } else if (metal == MetalTagEnum.COPPER) {
            return customChargeCopper(compoundTag, player, metalReserve, metalKey);
        } else if(metal == MetalTagEnum.ETTMETAL) {
            return calculateChargeEttmetal(compoundTag, player, metalReserve, metalKey);
        } else if (metal == MetalTagEnum.LERASIUM) {
            return calculateChargeLerasium(compoundTag, playerCapability, metalKey);
        } else if (metal == MetalTagEnum.MALATIUM) {
            return calculateChargeMalatium(compoundTag, player, metalReserve, metalKey);
        } else if (metal == MetalTagEnum.ALUMINUM) {
            return calculateChargeAluminum(compoundTag, metalKey);
        } else if (metal == MetalTagEnum.NICROSIL) {
            return calculateChargeNicrosil(compoundTag, player, playerCapability, metalReserve, metalKey, nicConsume);
        } else {
            if (!playerCapability.isStoring(MetalTagEnum.NICROSIL) || !nicConsume) {
                compoundTag.putInt(metalKey, metalReserve + 1);
            }
        }
        return compoundTag;
    }



    /**
     * Redefine of the method of the AbstractFechuchemicHelper class.
     * <p>
     * In this specific case, the Nicrosil reserve is discharged based on the amount of other metals being tapped at the same time as the Nicrosil.
     *
     * @param compoundTag the inside information of the metalmind.
     * @param playerCapability capabilities (data) of the player.
     * @param metalReserve present value of the metal reserve.
     * @param metalKey metal key to be modified.
     * @param nicConsume control value of whether it is necessary to store charge or not.
     * @return CompoundTag metalmind information update.
     *
     */

    public CompoundTag calculateDischargeNicrosil(CompoundTag compoundTag, IInvestedPlayerData playerCapability, int metalReserve, String metalKey, boolean nicConsume) {
        int value = playerCapability.cantMetalsTapping();
        if (nicConsume) {
            value = 0;
        }
        compoundTag.putInt(metalKey, metalReserve - value);
        return compoundTag;
    }

    /**
     * Redefine of the method of the AbstractFechuchemicHelper class.
     * <p>
     * In this specific case, the Nicrosil reserve is charged based on the amount of other metals being stored at the same time as the Nicrosil.
     *
     * @param compoundTag the inside information of the metalmind.
     * @param player with the mindmetal equipped.
     * @param playerCapability capabilities (data) of the player.
     * @param metalReserve present value of the metal reserve.
     * @param metalKey metal key to be modified.
     * @param nicConsume control value of whether it is necessary to store charge or not.
     * @return CompoundTag metalmind information update.
     *
     */

    public CompoundTag calculateChargeNicrosil(CompoundTag compoundTag, Player player, IInvestedPlayerData playerCapability, int metalReserve, String metalKey, boolean nicConsume) {
        int value = playerCapability.cantMetalsStoring();
        if (playerCapability.isStoring(MetalTagEnum.BRASS) && !player.isOnFire()) {
            value = value - 1;
        }
        if (nicConsume) {
            value = 0;
        }
        compoundTag.putInt(metalKey, metalReserve + value);
        return compoundTag;
    }



    /**
     * Redefine of the method of the AbstractFechuchemicHelper class.
     * In this specific case, because the logic of the charge is proper of the Aluminum.
     *
     * @param compoundTag the inside information of the metalmind.
     * @param metalKey metal key to be modified.
     * @return CompoundTag metalmind information update.
     *
     */
    public CompoundTag calculateDischargeAluminum(CompoundTag compoundTag, String metalKey) {
        compoundTag.putInt(metalKey, 1);
        return compoundTag;
    }

    /**
     * Redefine of the method of the AbstractFechuchemicHelper class.
     * In this specific case, because the logic of the charge is proper of the Aluminum.
     *
     * @param compoundTag the inside information of the metalmind.
     * @param metalKey metal key to be modified.
     * @return CompoundTag metalmind information update.
     *
     */

    public CompoundTag calculateChargeAluminum(CompoundTag compoundTag, String metalKey) {
        compoundTag.putInt(metalKey, 2);
        return compoundTag;
    }

    /**
     * Redefine of the method of the AbstractFechuchemicHelper class.
     * In this specific case, metalmind only charges when player is burning.
     *
     * @param player to whom the effect will be applied.
     *
     */

    public CompoundTag customChargeBrass(CompoundTag compoundTag, Player player, IInvestedPlayerData playerCapability, int metalReserve, String metalKey, boolean nicConsume) {
        if (player.isOnFire()) {
            if (!playerCapability.isStoring(MetalTagEnum.NICROSIL) || !nicConsume) {
                compoundTag.putInt(metalKey, metalReserve + 1);
            }
        }
        return compoundTag;
    }


    /**
     * Redefine of the method of the AbstractFechuchemicHelper class.
     * In this specific case, only charge when target player has experience.
     *
     * @param compoundTag the inside information of the metalmind.
     * @param player with the mindmetal equipped.
     * @param metalReserve present value of the metal reserve.
     * @param metalKey metal key to be modified.
     * @return CompoundTag metalmind information update.
     *
     */
    public CompoundTag customChargeCopper(CompoundTag compoundTag, Player player, int metalReserve, String metalKey) {
        if (player.totalExperience>0) {
            compoundTag.putInt(metalKey, metalReserve + 1);
        }
        return compoundTag;
    }

    /**
     * Redefine of the method of the AbstractFechuchemicHelper class.
     * In this specific case, removes the basic interaction of nicrosil.
     *
     * @param compoundTag the inside information of the metalmind.
     * @param metalReserve present value of the metal reserve.
     * @param metalKey metal key to be modified.
     * @return CompoundTag metalmind information update.
     *
     */
    public CompoundTag customDischargeCopper(CompoundTag compoundTag, int metalReserve, String metalKey) {
        compoundTag.putInt(metalKey, metalReserve - 1);
        return compoundTag;
    }

    /**
     * Implementation of the abstract method of the AbstractFechuchemicHelper class.
     * In this specific case, only charge when target player take damage from explosions.
     *
     * @param compoundTag the inside information of the metalmind.
     * @param player with the mindmetal equipped.
     * @param metalReserve present value of the metal reserve.
     * @param metalKey metal key to be modified.
     * @return CompoundTag metalmind information update.
     *
     */

    public CompoundTag calculateChargeEttmetal(CompoundTag compoundTag, Player player, int metalReserve, String metalKey) {
        if (player.getLastDamageSource() != null) {
            //todo chequear esto, no me gusta ese null ahi, he cambiado a una explosion por defecto, pero no tiene buena pinta, la verdad
            if (player.getLastDamageSource().type().equals(player.damageSources().explosion(new Explosion(player.level, player, player.position().x,player.position().y,player.position().z,4, List.of(player.blockPosition()))).type())) {
                compoundTag.putInt(metalKey, metalReserve + 1);
            }
        }
        return compoundTag;
    }


    /**
     * Implementation of the abstract method of the AbstractFechuchemicHelper class.
     * In this specific case, it generates the explosion based on the charge values.
     *
     * @param compoundTag the inside information of the metalmind.
     * @param player with the mindmetal equipped.
     * @param metalKey metal key to be modified.
     * @return CompoundTag metalmind information update.
     *
     */

    public CompoundTag calculateDischargeEttmetal(CompoundTag compoundTag, Player player, String metalKey) {
        player.level.explode(player,player.position().x,player.position().y,player.position().z,(float) compoundTag.getInt(metalKey)/683, Level.ExplosionInteraction.NONE); // todo antes era none
        player.setHealth((player.getHealth() - ((float) compoundTag.getInt(metalKey)/205)));
        compoundTag.putInt(metalKey,0);
        return compoundTag;
    }



    /**
     * Redefine of the method of the AbstractFechuchemicHelper class.
     * In this specific case, removes the basic interaction of nicrosil.
     * @param compoundTag the inside information of the metalmind.
     * @param playerCapability capabilities (data) of the player.
     * @param metalKey metal key to be modified.
     * @return CompoundTag metalmind information update.
     *

     */

    public CompoundTag calculateDischargeLerasium(CompoundTag compoundTag, IInvestedPlayerData playerCapability, String metalKey) {
        compoundTag.putInt(metalKey,0);
        return loadAllomanticReserve(playerCapability, compoundTag);
    }

    /**
     * Redefine of the method of the AbstractFechuchemicHelper class.
     * In this specific case, removes the basic interaction of nicrosil.
     * @param compoundTag the inside information of the metalmind.
     * @param playerCapability capabilities (data) of the player.
     * @param metalKey metal key to be modified.
     * @return CompoundTag metalmind information update.
     *
     */

    public CompoundTag calculateChargeLerasium(CompoundTag compoundTag, IInvestedPlayerData playerCapability, String metalKey) {
        if (havePlayerAnyReserve(playerCapability)) {
            compoundTag = saveAllomanticReserve(playerCapability, compoundTag);
            compoundTag.putInt(metalKey,1);
        }
        return compoundTag;
    }

    /**
     * Returns if target player has any allomantic reserves
     *
     * @param playerCapability capabilities (data) of the player.
     * @return Boolean
     */
    public boolean havePlayerAnyReserve (IInvestedPlayerData playerCapability) {
        for (MetalTagEnum metal: MetalTagEnum.values()) {
            if (playerCapability.getAllomanticAmount(metal)>0) {
                return true;
            }
        }
        return false;
    }

    /**
     * This is a unique method in the helpers, that is in charge of storing allomantic reserves in the metalmind and eliminating them from the target player.
     *
     * @param playerCapability capabilities (data) of the player.
     * @param compoundTag the inside information of the metalmind.
     * @return CompoundTag metalmind information update.
     */
    public CompoundTag saveAllomanticReserve(IInvestedPlayerData playerCapability, CompoundTag compoundTag) {
        ArrayList<MetalTagEnum> metals = playerCapability.getAllomanticPowers();
        int firstQty = 0;
        int qtyToRemove = 0;
        boolean continueSaving;

        for (MetalTagEnum metal: metals) {
            continueSaving = true;
            while (continueSaving) {
                if (firstQty == 0) {
                    firstQty = playerCapability.getAllomanticAmount(metal);
                    qtyToRemove = Math.toIntExact(Math.round(firstQty * 0.1));
                }
                continueSaving = playerCapability.substractAllomanticMetalAmount(metal, qtyToRemove);
                if (!continueSaving || firstQty == 0) {
                    firstQty = 0;
                    qtyToRemove = 0;
                    continueSaving = false;
                } else {
                    if (!compoundTag.contains(metal.getNameLower()+"inLerasiumBand")) { //no existe el tag
                        compoundTag.putInt(metal.getNameLower()+"inLerasiumBand",0);
                    }
                    compoundTag.putInt(metal.getNameLower()+"inLerasiumBand", compoundTag.getInt(metal.getNameLower()+"inLerasiumBand")+qtyToRemove);
                    if (compoundTag.getInt(metal.getNameLower()+"inLerasiumBand") > (metal.getMaxAllomanticTicksStorage())) {
                        compoundTag.putInt(metal.getNameLower()+"inLerasiumBand",(metal.getMaxAllomanticTicksStorage()));
                        continueSaving = false;
                    }

                }
            }
        }
        return compoundTag;
    }

    /**
     * This is a unique method in the helpers, which is in charge of recover allomantic reserves the metalmind and return them to the target player.
     *
     * @param playerCapability capabilities (data) of the player.
     * @param compoundTag the inside information of the metalmind.
     * @return CompoundTag metalmind information update.
     */
    public CompoundTag loadAllomanticReserve(IInvestedPlayerData playerCapability, CompoundTag compoundTag) {
        ArrayList<MetalTagEnum> metals = playerCapability.getAllomanticPowers();
        int firstQty = 0;
        int qtyToAdd = 0;
        boolean continueLoading;

        for (MetalTagEnum metal: metals) {
            continueLoading = true;
            while (continueLoading) {
                if (firstQty == 0) {
                    firstQty = compoundTag.getInt(metal.getNameLower()+"inLerasiumBand");
                    qtyToAdd = Math.toIntExact(Math.round(firstQty * 0.1));
                }
                if (compoundTag.getInt(metal.getNameLower()+"inLerasiumBand") == 0) {
                    firstQty = 0;
                    qtyToAdd = 0;
                    continueLoading = false;
                } else {
                    if (!compoundTag.contains(metal.getNameLower()+"inLerasiumBand")) { //no existe el tag
                        compoundTag.putInt(metal.getNameLower()+"inLerasiumBand",0);
                    }
                    compoundTag.putInt(metal.getNameLower()+"inLerasiumBand", compoundTag.getInt(metal.getNameLower()+"inLerasiumBand")-qtyToAdd);
                    continueLoading = playerCapability.addAllomanticMetalAmount(metal, qtyToAdd);
                }
            }
        }
        return compoundTag;
    }

    //MALATIUM

    /**
     * Returns an instance of MalatiumFecuchemicHelper using a factory method pattern.
     * This method allows you to create instances of MalatiumFecuchemicHelper with a consistent interface.
     *
     * @return a Supplier that returns a new instance of MalatiumFecuchemicHelper when called
     */

    /**
     * Redefine of the method of the AbstractFechuchemicHelper class.
     * <p>
     * In this specific case, removes interaction with nicrosil and performs the discharge only when the item in the user's hand is of the same tier that the metal mind has in reserve.
     *
     * @param compoundTag the inside information of the metalmind.
     * @param player with the mindmetal equipped.
     * @param metalReserve present value of the metal reserve.
     * @param metalKey metal key to be modified.
     * @return CompoundTag metalmind information update.
     *
     */
    public CompoundTag calculateDischargeMalatium(CompoundTag compoundTag, Player player, int metalReserve, String metalKey) {
        if (isDecanting(player,compoundTag)) {
            compoundTag.putInt(metalKey, metalReserve - 1);
        }
        if (compoundTag.getInt(metalKey) == 0) {
            compoundTag.putInt("tier_malatium_storage",-1);
        }
        return compoundTag;
    }

    /**
     * Redefine of the method of the AbstractFechuchemicHelper class.
     * <p>
     * In this specific case, removes interaction with nicrosil and performs the charge only when the item in the user's hand is of the same tier that the metal mind has in reserve or the metal mind does not have a tier assigned.
     *
     * @param compoundTag the inside information of the metalmind.
     * @param player with the mindmetal equipped.
     * @param metalReserve present value of the metal reserve.
     * @param metalKey metal key to be modified.
     * @return CompoundTag metalmind information update.
     *
     */
    public CompoundTag calculateChargeMalatium(CompoundTag compoundTag, Player player, int metalReserve, String metalKey) {
        if (isStoring(player,compoundTag)) {
            compoundTag.putInt(metalKey, metalReserve + 1);
        }
        return compoundTag;
    }

    /**
     * Repairs the durability of weapons and armor that target player has in hand.
     *
     * @param player to whom the effect will be applied.
     * @param compoundTag metalmind information update.
     * @return If the weapon or armor was repaired it returns true, otherwise false
     */
    public boolean isDecanting(Player player, CompoundTag compoundTag) {
        if (player.getMainHandItem().getItem() instanceof TieredItem tiered) {
            if (tiered.getTier().getLevel() == compoundTag.getInt("tier_malatium_storage")) {
                if (player.getItemInHand(InteractionHand.MAIN_HAND).getDamageValue() == 0) {
                    return false;
                }
                player.getItemInHand(InteractionHand.MAIN_HAND).setDamageValue(player.getItemInHand(InteractionHand.MAIN_HAND).getDamageValue()-1);
                return true;
            }
            return false; //el item no es del tier de la primer carga de la mente
        } else if (player.getMainHandItem().getItem() instanceof ArmorItem armorItem) {
            int tier = convertMaterialToTier(armorItem.getMaterial().getName());
            if (tier == compoundTag.getInt("tier_malatium_storage")) {
                if (player.getItemInHand(InteractionHand.MAIN_HAND).getDamageValue() == 0) {
                    return false;
                }
                player.getItemInHand(InteractionHand.MAIN_HAND).setDamageValue(player.getItemInHand(InteractionHand.MAIN_HAND).getDamageValue()-1);
                return true;
            }
            return false;
        }
        return false;
    }

    /**
     * Removes the durability of weapons and armor the target player has in hand.
     *
     * @param player to whom the effect will be applied.
     * @param compoundTag metalmind information update.
     * @return If durability was consumed from the weapon or armor returns true, otherwise false
     */
    public boolean isStoring (Player player, CompoundTag compoundTag) {
        if (!compoundTag.contains("tier_malatium_storage")) {
            compoundTag.putInt("tier_malatium_storage",-1);
        }
        if (compoundTag.getInt("tier_malatium_storage") == -1) {
            compoundTag = generateIternalReserve(player, compoundTag);
        }

        if (player.getMainHandItem().getItem() instanceof TieredItem tiered) {
            if (tiered.getTier().getLevel() == compoundTag.getInt("tier_malatium_storage")) {
                if (player.getItemInHand(InteractionHand.MAIN_HAND).getDamageValue() == player.getItemInHand(InteractionHand.MAIN_HAND).getMaxDamage()) {
                    player.setItemInHand(InteractionHand.MAIN_HAND,ItemStack.EMPTY);
                    player.level.playLocalSound(player.getX(),player.getY(),player.getZ(), SoundEvents.ITEM_BREAK, SoundSource.NEUTRAL,1.0f, 2.0f, true);
                    return false;
                }
                player.getItemInHand(InteractionHand.MAIN_HAND).setDamageValue(player.getItemInHand(InteractionHand.MAIN_HAND).getDamageValue()+1);
                return true;
            }
            return false; //el item no es del tier de la primer carga de la mente
        } else if (player.getMainHandItem().getItem() instanceof ArmorItem armorItem) {
            int tier = convertMaterialToTier(armorItem.getMaterial().getName());
            if (tier == compoundTag.getInt("tier_malatium_storage")) {
                if (player.getItemInHand(InteractionHand.MAIN_HAND).getDamageValue() == player.getItemInHand(InteractionHand.MAIN_HAND).getMaxDamage()) {
                    player.setItemInHand(InteractionHand.MAIN_HAND,ItemStack.EMPTY);
                    return false;
                }
                player.getItemInHand(InteractionHand.MAIN_HAND).setDamageValue(player.getItemInHand(InteractionHand.MAIN_HAND).getDamageValue()+1);
                return true;
            }
        }
        return false;
    }

    /**
     * Modify the information of the metal mind to assign it the corresponding tier.
     *
     * @param player to whom the effect will be applied.
     * @param compoundTag metalmind information update.
     * @return CompoundTag metalmind information update.
     */
    public CompoundTag generateIternalReserve (Player player, CompoundTag compoundTag) {
        if (player.getMainHandItem().getItem() instanceof TieredItem tiered) {
            compoundTag.putInt("tier_malatium_storage",tiered.getTier().getLevel());
        }
        if (player.getMainHandItem().getItem() instanceof ArmorItem armorItem) {
            compoundTag.putInt("tier_malatium_storage",convertMaterialToTier(armorItem.getMaterial().getName()));
        }
        return  compoundTag;
    }

    /**
     * Recovers the material tier.
     *
     * @param material string name
     * @return int value tier
     */
    public static int convertMaterialToTier (String material) {

        if (material.equals(ArmorMaterials.GOLD.getName()) || material.equals(ArmorMaterials.LEATHER.getName())) {
            return 0;
        } else if (material.equals(ArmorMaterials.TURTLE.getName())) {
            return 1;
        } else if (material.equals(ArmorMaterials.IRON.getName()) || material.equals(ArmorMaterials.CHAIN.getName())) {
            return 2;
        } else if (material.equals(ArmorMaterials.DIAMOND.getName())) {
            return 3;
        } else if (material.equals(ArmorMaterials.NETHERITE.getName())) {
            return 4;
        } else if (material.equals("Obsidian")) {
            return 6;
        }
        return -1;
    }

    /**
     * Retrieve the name of the tier.
     *
     * @param tier value of tier
     * @return String with the name of tier
     */
    public static String convertTierToMaterial (int tier) {
        if (tier == 0) {
            return Tiers.GOLD.name()+" "+ArmorMaterials.LEATHER.getName().toUpperCase();
        } else if (tier == 1) {
            return ArmorMaterials.TURTLE.getName();
        } else if (tier == 2) {
            return Tiers.IRON.name()+" "+ArmorMaterials.CHAIN.getName().toUpperCase();
        } else if (tier == 3) {
            return Tiers.DIAMOND.name();
        } else if (tier == 4) {
            return Tiers.NETHERITE.name();
        } else if (tier == 6) {
            return "Obsidian";
        }
        return "";
    }


    public static CompoundTag addBandTagsFull(MetalTagEnum metal1, MetalTagEnum metal2) {
        CompoundTag nbt = new CompoundTag();

        if(metal1.equals(MetalTagEnum.ALUMINUM)) {
            nbt.putInt(metal1.getNameLower()+"_feruchemic_reserve",3);

        }else if(metal1.equals(MetalTagEnum.LERASIUM)) {
            nbt.putInt(metal1.getNameLower() + "_feruchemic_reserve",1);
            for (MetalTagEnum metal: MetalTagEnum.values()) {
                nbt.putInt(metal.getNameLower()+"inLerasiumBand", metal.getMaxAllomanticTicksStorage());
            }
        }else {
            nbt.putInt(metal1.getNameLower()+"_feruchemic_reserve", metal1.getMaxReserveBand());

        }
        nbt.putInt(metal2.getNameLower()+"_feruchemic_reserve", metal2.getMaxReserveBand());
        nbt.putString("key","Nobody");

        return nbt;
    }

}
