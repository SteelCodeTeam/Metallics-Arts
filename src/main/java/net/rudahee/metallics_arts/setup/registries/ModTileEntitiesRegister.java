package net.rudahee.metallics_arts.setup.registries;


import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.rudahee.metallics_arts.MetallicsArts;

public class ModTileEntitiesRegister {

    public static DeferredRegister<BlockEntityType<?>> TILE_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, MetallicsArts.MOD_ID);

    /*public static RegistryObject<BlockEntityType<AlloyFurnaceTileEntity>> ALLOY_FURNACE_TILE_ENTITY =
            TILE_ENTITIES.register("alloy_furnace_tile_entity", () -> BlockEntityType.Builder
                    .of(AlloyFurnaceTileEntity::new, ModBlock.ALLOY_FURNACE_BLOCK.get()).build(null));*/

    public static void register(IEventBus eventBus) {
        TILE_ENTITIES.register(eventBus);
    }
}
