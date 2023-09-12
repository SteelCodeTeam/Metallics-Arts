package net.rudahee.metallics_arts.setup.registries;


import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.AmethystBlock;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.registries.RegistryObject;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.data.player.data.IInvestedPlayerData;
import net.rudahee.metallics_arts.setup.registries.blocks.BasicBlocksRegister;
import net.rudahee.metallics_arts.setup.registries.blocks.decoration.DecorationBlockRegister;
import net.rudahee.metallics_arts.setup.registries.blocks.geodes.AtiumBlocksRegister;
import net.rudahee.metallics_arts.setup.registries.blocks.geodes.EttmetalBlocksRegister;
import net.rudahee.metallics_arts.setup.registries.blocks.geodes.LerasiumBlocksRegister;

import java.util.HashMap;


public class ModBlocksRegister {
    public static final HashMap<String, Block> BLOCK_METAL_ORES = new HashMap<>();
    public static final HashMap<String, Block> BLOCK_METAL_DEEPSLATE_ORES = new HashMap<>();
    public static final HashMap<String, Block> BLOCK_METAL_BLOCKS = new HashMap<>();
    public static final HashMap<String, Block> RAW_METAL_BLOCKS = new HashMap<>();
    public static final HashMap<String, Block> BLOCK_GEMS_BLOCKS = new HashMap<>();
    public static final HashMap<String, Block> DIVINE_CRISTAL_BLOCKS = new HashMap<>();
    public static final HashMap<String, Block> BLOCK_METAL_STAIRS = new HashMap<>();
    public static final HashMap<String, Block> BLOCK_METAL_SLAB = new HashMap<>();
    public static final HashMap<String, Block> BLOCK_METAL_WALL = new HashMap<>();
    public static final HashMap<String, Block> BLOCK_METAL_FENCE = new HashMap<>();
    public static final HashMap<String, Block> BLOCK_METAL_FENCE_GATE = new HashMap<>();
    public static RegistryObject<AmethystBlock> BUDDING_ATIUM;
    public static RegistryObject<AmethystBlock> ATIUM_CLUSTER;
    public static RegistryObject<AmethystBlock> LARGE_ATIUM_BUD;
    public static RegistryObject<AmethystBlock> MEDIUM_ATIUM_BUD;
    public static RegistryObject<AmethystBlock> SMALL_ATIUM_BUD;
    public static RegistryObject<AmethystBlock> BUDDING_LERASIUM;
    public static RegistryObject<AmethystBlock> LERASIUM_CLUSTER;
    public static RegistryObject<AmethystBlock> LARGE_LERASIUM_BUD;
    public static RegistryObject<AmethystBlock> MEDIUM_LERASIUM_BUD;
    public static RegistryObject<AmethystBlock> SMALL_LERASIUM_BUD;
    public static RegistryObject<AmethystBlock> BUDDING_ETTMETAL;
    public static RegistryObject<AmethystBlock> ETTMETAL_CLUSTER;
    public static RegistryObject<AmethystBlock> LARGE_ETTMETAL_BUD;
    public static RegistryObject<AmethystBlock> MEDIUM_ETTMETAL_BUD;
    public static RegistryObject<AmethystBlock> SMALL_ETTMETAL_BUD;
    public static RegistryObject<Block> ETTMETAL_BLOCK = null;

    public static RegistryObject<Block> CRUCIBLE_FURNACE;
    public static RegistryObject<Block> HEMALURGY_ALTAR;

    public static void register() {
        AtiumBlocksRegister.register();
        LerasiumBlocksRegister.register();
        EttmetalBlocksRegister.register();
        BasicBlocksRegister.register();
        DecorationBlockRegister.register();
        //Electric.register();
    }

    public static class InvestedCapabilityRegister {

        public static final Capability<IInvestedPlayerData> PLAYER_CAP = CapabilityManager.get(new CapabilityToken<IInvestedPlayerData>() {
            @Override
            public String toString() {
                return super.toString();
            }
        });

        public static final ResourceLocation IDENTIFIER = new ResourceLocation(MetallicsArts.MOD_ID, "ma_data");

        public static void register(final RegisterCapabilitiesEvent event) {
            event.register(IInvestedPlayerData.class);
        }

    }
}
