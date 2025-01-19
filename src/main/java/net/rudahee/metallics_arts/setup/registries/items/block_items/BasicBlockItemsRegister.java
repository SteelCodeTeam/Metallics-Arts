package net.rudahee.metallics_arts.setup.registries.items.block_items;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.state.BlockState;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.modules.custom_block_entities.hemalurgy_altar_block.back.HemalurgyAltarBackBlockItem;
import net.rudahee.metallics_arts.modules.custom_block_entities.hemalurgy_altar_block.front.HemalurgyAltarFrontBlockItem;
import net.rudahee.metallics_arts.setup.registries.ModBlocksRegister;
import net.rudahee.metallics_arts.setup.registries.ModItemsRegister;

import java.util.function.ToIntFunction;

public class BasicBlockItemsRegister {
    public static void register() {
        ModItemsRegister.HEMALURGY_ALTAR_BACK = MetallicsArts.registerItem("hemalurgy_altar_back",
                ()-> new HemalurgyAltarBackBlockItem(ModBlocksRegister.HEMALURGY_ALTAR_BACK.get(),
                        new Item.Properties().stacksTo(1).fireResistant()));

        ModItemsRegister.HEMALURGY_ALTAR_FRONT = MetallicsArts.registerItem("hemalurgy_altar_front",
                ()-> new HemalurgyAltarFrontBlockItem(ModBlocksRegister.HEMALURGY_ALTAR_FRONT.get(),
                        new Item.Properties().stacksTo(1).fireResistant()));

    }


    private static ToIntFunction<BlockState> litBlockEmission(int value) {
        return (funcValue) -> {
            return value;
            //return funcValue.getValue(BlockStateProperties.LIT) ? value : 0;
        };
    }
}
