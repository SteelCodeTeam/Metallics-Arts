package net.rudahee.metallics_arts.setup.registries;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.rudahee.metallics_arts.setup.registries.ModItems;

public class ModItemGroup {
    public static final ItemGroup METALLIC_ARTS_TAG = new ItemGroup("metallic_arts_tag") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.ITEM_METAL_INGOT.get("aluminum").getItem());
        }
    };
}
