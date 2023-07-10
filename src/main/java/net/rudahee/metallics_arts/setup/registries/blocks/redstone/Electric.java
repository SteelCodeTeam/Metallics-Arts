package net.rudahee.metallics_arts.setup.registries.blocks.redstone;

import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.modules.custom_items.redstone.AllomanticLever;

public class Electric {

    public static void register() {
        //
        MetallicsArts.registerBlock("allomantic_lever",
                () -> new AllomanticLever(BlockBehaviour.Properties.of(Material.DECORATION)
                        .noCollission().strength(0.5F).sound(SoundType.WOOD)));

    }
}
