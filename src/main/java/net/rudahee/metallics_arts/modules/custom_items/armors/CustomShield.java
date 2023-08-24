package net.rudahee.metallics_arts.modules.custom_items.armors;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.rudahee.metallics_arts.data.enums.implementations.custom_items.Shields;

public class CustomShield extends ShieldItem {

    private Shields type;

    public CustomShield(Properties p_43089_, Shields type) {
        super(p_43089_);
        this.type = type;
    }

    @Override
    public int getUseDuration(ItemStack p_43107_) {
        return this.type.getUses();
    }
}
