package net.rudahee.metallics_arts.modules.custom_items.armors;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.rudahee.metallics_arts.data.enums.implementations.custom_items.Shields;

public class CustomShield extends ShieldItem {

    private Shields type;

    public CustomShield(Properties properties, Shields type) {
        super(properties);
        this.type = type;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return this.type.getUses();
    }
}
