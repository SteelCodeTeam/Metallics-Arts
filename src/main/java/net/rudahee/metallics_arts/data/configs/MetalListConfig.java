package net.rudahee.metallics_arts.data.configs;


import net.minecraft.world.item.Items;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.enums.implementations.custom_items.MetalMindEnum;
import net.rudahee.metallics_arts.setup.registries.ModBlocksRegister;
import net.rudahee.metallics_arts.setup.registries.ModItemsRegister;

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
            if (!metals.equals(MetalTagEnum.TIN) && !metals.equals(MetalTagEnum.ALUMINUM)) {
                add(metals.getNameLower());
            }
        }

        add("lead");
        add("nickel");
        add("tin_");
        add("raw tin");
        add("silver");
        add("chain");

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

        add(ModItemsRegister.ITEM_RAW_METAL.get(MetalTagEnum.TIN.getNameLower()).getDescriptionId());

        add(Items.CROSSBOW.getDescriptionId());
        add(Items.CROSSBOW.getDescription().toString());

        add(Items.GLISTERING_MELON_SLICE.getDescriptionId());
        add(Items.GLISTERING_MELON_SLICE.getDescription().toString());

        add(Items.SPYGLASS.getDescriptionId());
        add(Items.SPYGLASS.getDescription().toString());

        add(ModItemsRegister.HEMALURGY_ALTAR_BACK.get().getDescriptionId());
        add(ModBlocksRegister.HEMALURGY_ALTAR_BACK.get().asItem().getDescription().toString());

        add(ModItemsRegister.HEMALURGY_ALTAR_FRONT.get().getDescriptionId());
        add(ModBlocksRegister.HEMALURGY_ALTAR_FRONT.get().asItem().getDescription().toString());

        add(ModBlocksRegister.CRUCIBLE_FURNACE.get().getDescriptionId());
        add(ModBlocksRegister.CRUCIBLE_FURNACE.get().asItem().getDescription().toString());

        add(ModBlocksRegister.DISTILLERY.get().getDescriptionId());
        add(ModBlocksRegister.DISTILLERY.get().asItem().getDescription().toString());

        add(ModBlocksRegister.ALLOMANTIC_LEVER.get().getDescriptionId());
        add(ModBlocksRegister.ALLOMANTIC_LEVER.get().asItem().getDescription().toString());

        add(ModBlocksRegister.ALLOMANTIC_PULL_BUTTON.get().getDescriptionId());
        add(ModBlocksRegister.ALLOMANTIC_PULL_BUTTON.get().asItem().getDescription().toString());

        add(ModBlocksRegister.ALLOMANTIC_PUSH_BUTTON.get().getDescriptionId());
        add(ModBlocksRegister.ALLOMANTIC_PUSH_BUTTON.get().asItem().getDescription().toString());

        add(ModBlocksRegister.COPPER_STANDING_SIGN.get().getDescriptionId());
        add(ModBlocksRegister.COPPER_STANDING_SIGN.get().asItem().getDescription().toString());

        add(ModBlocksRegister.COPPER_WALL_SIGN.get().getDescriptionId());
        add(ModBlocksRegister.COPPER_WALL_SIGN.get().asItem().getDescription().toString());

        add(ModItemsRegister.KOLOSS_BLADE.get().getDescriptionId());
        add(ModItemsRegister.KOLOSS_BLADE.get().getDescription().toString());

        add(ModItemsRegister.SILVER_KNIFE.get().getDescriptionId());
        add(ModItemsRegister.SILVER_KNIFE.get().getDescription().toString());


        add(ModItemsRegister.COPPER_COIN.get().getDescriptionId());
        add(ModItemsRegister.COPPER_COIN.get().getDescription().toString());

        add(ModItemsRegister.BRONZE_COIN.get().getDescriptionId());
        add(ModItemsRegister.BRONZE_COIN.get().getDescription().toString());

        add(ModItemsRegister.REVOLVER.get().getDescriptionId());
        add(ModItemsRegister.REVOLVER.get().getDescription().toString());

        add(ModItemsRegister.VINDICATOR.get().getDescriptionId());
        add(ModItemsRegister.VINDICATOR.get().getDescription().toString());

        add(ModItemsRegister.REVOLVER_LEAD_BULLET.get().getDescriptionId());
        add(ModItemsRegister.REVOLVER_LEAD_BULLET.get().getDescription().toString());

        add(ModItemsRegister.RIFLE.get().getDescriptionId());
        add(ModItemsRegister.RIFLE.get().getDescription().toString());

        add(ModItemsRegister.RIFLE_WITH_SPYGLASS.get().getDescriptionId());
        add(ModItemsRegister.RIFLE_WITH_SPYGLASS.get().getDescription().toString());

        add(ModItemsRegister.RIFLE_LEAD_BULLET.get().getDescriptionId());
        add(ModItemsRegister.RIFLE_LEAD_BULLET.get().getDescription().toString());

        add(ModItemsRegister.SHOTGUN.get().getDescriptionId());
        add(ModItemsRegister.SHOTGUN.get().getDescription().toString());

        add(ModItemsRegister.SHOTGUN_LEAD_BULLET.get().getDescriptionId());
        add(ModItemsRegister.SHOTGUN_LEAD_BULLET.get().getDescription().toString());

        add(ModItemsRegister.ATIUM_CORE.get().getDescriptionId());
        add(ModItemsRegister.ATIUM_CORE.get().getDescription().toString());

        add(ModItemsRegister.LERASIUM_CORE.get().getDescriptionId());
        add(ModItemsRegister.LERASIUM_CORE.get().getDescription().toString());

        add(ModItemsRegister.ETTMETAL_CORE.get().getDescriptionId());
        add(ModItemsRegister.ETTMETAL_CORE.get().getDescription().toString());

        add(ModItemsRegister.COPPER_CORE.get().getDescriptionId());
        add(ModItemsRegister.COPPER_CORE.get().getDescription().toString());

        add(ModItemsRegister.STEEL_CORE.get().getDescriptionId());
        add(ModItemsRegister.STEEL_CORE.get().getDescription().toString());

        add(Items.HEAVY_WEIGHTED_PRESSURE_PLATE.getDescriptionId());
        add(Items.HEAVY_WEIGHTED_PRESSURE_PLATE.getDescription().toString());

        add(Items.LIGHT_WEIGHTED_PRESSURE_PLATE.getDescriptionId());
        add(Items.LIGHT_WEIGHTED_PRESSURE_PLATE.getDescription().toString());

        add(Items.LIGHTNING_ROD.getDescription().toString());
        add(Items.LIGHTNING_ROD.getDescriptionId());

        add(Items.BELL.getDescriptionId());
        add(Items.BELL.getDescription().toString());

        add(Items.SMITHING_TABLE.getDescriptionId());
        add(Items.SMITHING_TABLE.getDescription().toString());

        add(Items.CAULDRON.getDescriptionId());
        add(Items.CAULDRON.getDescription().toString());

        add(Items.HOPPER.getDescriptionId());
        add(Items.HOPPER.getDescription().toString());

        add(Items.IRON_DOOR.getDescriptionId());
        add(Items.IRON_DOOR.getDescription().toString());

        add(Items.LODESTONE.getDescriptionId());
        add(Items.LODESTONE.getDescription().toString());
        add(Items.STONECUTTER.getDescriptionId());
        add(Items.STONECUTTER.getDescription().toString());

        add(Items.STONECUTTER.getDescription().toString());

        add(Items.BLAST_FURNACE.getDescriptionId());
        add(Items.STONECUTTER.getDescription().toString());

        add(Items.RAIL.getDescriptionId());
        add(Items.RAIL.getDescription().getString());

        add(Items.DETECTOR_RAIL.getDescriptionId());
        add(Items.DETECTOR_RAIL.getDescription().getString());

        add(Items.POWERED_RAIL.getDescriptionId());
        add(Items.POWERED_RAIL.getDescription().getString());

        add(Items.ACTIVATOR_RAIL.getDescriptionId());
        add(Items.ACTIVATOR_RAIL.getDescription().getString());

        add(Items.CLOCK.getDescription().toString());
        add(Items.COMPASS.getDescription().toString());
        add(Items.SHEARS.getDescription().toString());

        add(Items.GILDED_BLACKSTONE.getDescriptionId());
        add(Items.GILDED_BLACKSTONE.getDescription().toString());

        add(Items.RECOVERY_COMPASS.getDescriptionId());
        add(Items.RECOVERY_COMPASS.getDescription().toString());
    }};

}
