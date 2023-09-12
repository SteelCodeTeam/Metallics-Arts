package net.rudahee.metallics_arts.setup.registries;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.modules.custom_block_entities.crucible_furnace.CrucibleFurnaceBlockEntity;
import net.rudahee.metallics_arts.modules.custom_block_entities.hemalurgy_altar_block.HemalurgyAltarBlockEntity;
import net.rudahee.metallics_arts.modules.custom_block_entities.signs.ModSingBlockEntity;

public class ModBlockEntitiesRegister {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, MetallicsArts.MOD_ID);

    public static final RegistryObject<BlockEntityType<CrucibleFurnaceBlockEntity>> CRUCIBLE_FURNACE_ENTITY =
            BLOCK_ENTITIES.register("crucible_furnace_entity", () ->
                    BlockEntityType.Builder.of(CrucibleFurnaceBlockEntity::new,
                            ModBlocksRegister.CRUCIBLE_FURNACE.get()).build(null));

    public static final RegistryObject<BlockEntityType<HemalurgyAltarBlockEntity>> HEMALURGY_ALTAR_ENTITY =
            BLOCK_ENTITIES.register("hemalurgy_altar_entity", () ->
                    BlockEntityType.Builder.of(HemalurgyAltarBlockEntity::new,
                            ModBlocksRegister.HEMALURGY_ALTAR.get()).build(null));



    public static final RegistryObject<BlockEntityType<ModSingBlockEntity>> SIGN_BLOCK =
            BLOCK_ENTITIES.register("sign_block_entity", () ->
                    BlockEntityType.Builder.of(ModSingBlockEntity::new,
                                    ModBlocksRegister.IRON_STANDING_SIGN.get(),
                                    ModBlocksRegister.IRON_WALL_SIGN.get())
                            .build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
