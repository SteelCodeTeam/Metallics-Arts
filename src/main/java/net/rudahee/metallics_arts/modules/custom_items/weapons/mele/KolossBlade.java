package net.rudahee.metallics_arts.modules.custom_items.weapons.mele;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.player.data.IInvestedPlayerData;
import net.rudahee.metallics_arts.modules.error_handling.exceptions.PlayerException;
import net.rudahee.metallics_arts.utils.CapabilityUtils;

/**
 * Class to create a custom sword item. This item it's a new stone tier item.
 *
 * @author SteelCode Team
 * @since 1.5.1
 *
 * @see Item
 * @see SwordItem
 * @see Tiers
 */
public class KolossBlade extends SwordItem {

    private static final int ATTACK_DAMAGE = 8;
    private static final float ATTACK_SPEED = -3F;
    public static final int COOLDOWN = 180;

    /**
     * Default constructor, we define the properties of to set a durability and creative tab.
     *
     * @param properties of the item.
     */
    public KolossBlade(Properties properties) {
        super(Tiers.STONE, ATTACK_DAMAGE, ATTACK_SPEED, properties.durability(200));
    }

    @Override
    public boolean canAttackBlock(BlockState blockstate, Level level, BlockPos blockPos, Player player) {
        return super.canAttackBlock(blockstate, level, blockPos, player);
    }


    @Override
    public void inventoryTick(ItemStack itemStack, Level level, Entity entity, int stack, boolean flag) {

        if (entity instanceof Player player) {
            if (player.getItemInHand(InteractionHand.MAIN_HAND).equals(itemStack)) {
                try {
                    IInvestedPlayerData capabilities = CapabilityUtils.getCapability(entity);
                    if (!capabilities.isBurning(MetalTagEnum.PEWTER)) {
                        if (itemStack.getTag().getFloat("CustomModelData")!=2) {
                            itemStack.getTag().putFloat("CustomModelData",2);
                        }
                    }
                } catch (PlayerException ex) {
                    ex.printResumeLog();
                }
            }
        }
        super.inventoryTick(itemStack, level, entity, stack, flag);
    }


    @Override
    public boolean onEntitySwing(ItemStack stack, LivingEntity entity) {

        try {
            IInvestedPlayerData capabilities = CapabilityUtils.getCapability(entity);
            if (!capabilities.isBurning(MetalTagEnum.PEWTER)) { 
                return true;
            }
        } catch (PlayerException ex) {
            ex.printResumeLog();
        }
        if (stack.getTag().getFloat("CustomModelData")!=0) {
            stack.getTag().putFloat("CustomModelData",0);
        }
        if (entity instanceof Player player) {
            player.getCooldowns().addCooldown(this,25);
        }
        return false;
    }

}
