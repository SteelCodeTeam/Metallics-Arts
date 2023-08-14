package net.rudahee.metallics_arts.data.configs;


import net.minecraft.world.item.Items;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.enums.implementations.custom_items.MetalMindEnum;

import java.util.HashSet;
import java.util.Set;

/**
 * class that controls lists of metals that can be pulled or pushed with iron or steel.
 * We need to redo it.
 *
 * @author SteelCode Team
 * @since 1.5.1
 *
 * @deprecated
 */
public class MetalListConfig {

    public static final Set<String> whitelist = new HashSet<String>() {{

        for (MetalTagEnum metals : MetalTagEnum.values()) {
            if (!metals.equals(MetalTagEnum.TIN)&&!metals.equals(MetalTagEnum.ALUMINUM)) {
                add(metals.getNameLower());
            }
        }

        add("lead");
        add("nickel");
        add("tin_");

        add("shield");
        add("anvil");
        add("piston");
        add("hook");
        add("cauldron");
        add("minecart");
        add("hopper");
        add("bucket");
        add("netherite");
        add("lantern");

        add(MetalMindEnum.TIN_PEWTER.getBand().getDescriptionId());
        add(MetalMindEnum.TIN_PEWTER.getRing().getDescriptionId());

        add(Items.CROSSBOW.getDescriptionId());
        add(Items.CROSSBOW.getDescription().toString());

        add(Items.HEAVY_WEIGHTED_PRESSURE_PLATE.getDescriptionId());
        add(Items.HEAVY_WEIGHTED_PRESSURE_PLATE.getDescription().toString());

        add(Items.LIGHT_WEIGHTED_PRESSURE_PLATE.getDescriptionId());
        add(Items.LIGHT_WEIGHTED_PRESSURE_PLATE.getDescription().toString());

        add(Items.LIGHTNING_ROD.getDescription().toString());

        add(Items.BELL.getDescriptionId());
        add(Items.BELL.getDescription().toString());

        add(Items.SMITHING_TABLE.getDescriptionId());
        add(Items.SMITHING_TABLE.getDescription().toString());

        add(Items.LODESTONE.getDescriptionId());
        add(Items.LODESTONE.getDescription().toString());
        add(Items.STONECUTTER.getDescriptionId());
        add(Items.STONECUTTER.getDescription().toString());

        add(Items.STONECUTTER.getDescription().toString());

        add(Items.BLAST_FURNACE.getDescriptionId());
        add(Items.STONECUTTER.getDescription().toString());

        add(Items.RAIL.getDescription().getString());
        add(Items.DETECTOR_RAIL.getDescription().getString());
        add(Items.POWERED_RAIL.getDescription().getString());
        add(Items.ACTIVATOR_RAIL.getDescription().getString());

        add(Items.CLOCK.getDescription().toString());
        add(Items.COMPASS.getDescription().toString());
        add(Items.SHEARS.getDescription().toString());
        add(Items.CHAIN.getDescription().toString());

    }};

}
