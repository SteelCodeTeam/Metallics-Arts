package net.rudahee.metallics_arts.setup.registries;


import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModItemGroup {


    public static final CreativeModeTab METALLIC_ARTS_TAG = new CreativeModeTab("metallic_arts_tag") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.ITEM_METAL_INGOT.get("aluminum").asItem());
        }
    };
}
