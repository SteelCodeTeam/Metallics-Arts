package net.rudahee.metallics_arts.setup.registries;


import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.rudahee.metallics_arts.MetallicsArts;

public class ModTileEntitiesRegister {

    public static DeferredRegister<BlockEntityType<?>> TILE_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, MetallicsArts.MOD_ID);

    public static void register(IEventBus eventBus) {
        TILE_ENTITIES.register(eventBus);
    }
}
