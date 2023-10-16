package net.rudahee.metallics_arts.modules.custom_blocks.sings;

import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

public class WoodTypeMetal{
    public static WoodType IRON_TYPE = WoodType.register(new WoodType("iron_type", BlockSetType.IRON));
    public static WoodType GOLD_TYPE = WoodType.register(new WoodType("gold_type", BlockSetType.IRON));
    public static WoodType COPPER_TYPE = WoodType.register(new WoodType("copper_type", BlockSetType.IRON));
    public static WoodType ALUMINUM_TYPE = WoodType.register(new WoodType("aluminum_type", BlockSetType.IRON));

}
