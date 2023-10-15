package net.rudahee.metallics_arts.setup.registries.blocks.redstone;

import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.material.Material;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.modules.custom_items.redstone.AllomanticLever;
import net.rudahee.metallics_arts.modules.custom_items.redstone.AllomanticPullButton;
import net.rudahee.metallics_arts.modules.custom_items.redstone.AllomanticPushButton;
import net.rudahee.metallics_arts.setup.registries.ModBlocksRegister;

public class Electric {

    public static void register() {

        ModBlocksRegister.ALLOMANTIC_LEVER = MetallicsArts.registerBlock("allomantic_lever",
                () -> new AllomanticLever(BlockBehaviour.Properties.of(Material.DECORATION)
                        .noCollission().strength(0.5F).sound(SoundType.WOOD)));

        ModBlocksRegister.ALLOMANTIC_PUSH_BUTTON = MetallicsArts.registerBlock("allomantic_push_button",
                () -> new AllomanticPushButton(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().strength(0.5F), BlockSetType.STONE,10));

        ModBlocksRegister.ALLOMANTIC_PULL_BUTTON = MetallicsArts.registerBlock("allomantic_pull_button",
                () -> new AllomanticPullButton(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().strength(0.5F), BlockSetType.STONE,10));

    }
}
