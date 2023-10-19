package net.rudahee.metallics_arts.modules.custom_items.armors;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.rudahee.metallics_arts.data.enums.implementations.custom_items.Shields;

public class CustomShield extends ShieldItem {

    private Shields type;

    /**
     * Constructs a CustomShield with the specified properties and type.
     *
     * @param properties The properties to configure the shield.
     * @param type The type of shield to create.
     */
    public CustomShield(Properties properties, Shields type) {
        super(properties);
        this.type = type;
    }

    /**
     * Returns the duration of shield usage for the given ItemStack.
     *
     * @param stack The ItemStack representing the shield.
     * @return The duration of shield usage, based on the shield type.
     */
    @Override
    public int getUseDuration(ItemStack stack) {
        return this.type.getUses();
    }
}
