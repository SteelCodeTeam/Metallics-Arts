package net.rudahee.metallics_arts.setup.registries;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.modules.blocks.alloy_furnace.AlloyFurnaceTileEntity;
import net.rudahee.metallics_arts.setup.registries.ModBlock;

public class ModTileEntities {

    public static DeferredRegister<TileEntityType<?>> TILE_ENTITIES =
            DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, MetallicsArts.MOD_ID);

    public static RegistryObject<TileEntityType<AlloyFurnaceTileEntity>> ALLOY_FURNACE_TILE_ENTITY =
            TILE_ENTITIES.register("alloy_furnace_tile_entity", () -> TileEntityType.Builder
                    .of(AlloyFurnaceTileEntity::new, ModBlock.ALLOY_FURNACE_BLOCK.get()).build(null));

    public static void register(IEventBus eventBus) {
        TILE_ENTITIES.register(eventBus);
    }
}
