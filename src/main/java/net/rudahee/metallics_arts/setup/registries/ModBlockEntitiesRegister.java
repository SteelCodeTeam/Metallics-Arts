package net.rudahee.metallics_arts.setup.registries;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.modules.custom_block_entities.crucible_furnace.CrucibleFurnaceBlockEntity;
import net.rudahee.metallics_arts.modules.custom_block_entities.distillery.DistilleryBlockEntity;
import net.rudahee.metallics_arts.modules.custom_block_entities.hemalurgy_altar_block.back.HemalurgyAltarBackBlockEntity;
import net.rudahee.metallics_arts.modules.custom_block_entities.hemalurgy_altar_block.front.HemalurgyAltarFrontBlockEntity;
import net.rudahee.metallics_arts.modules.custom_block_entities.signs.ModSingBlockEntity;

public class ModBlockEntitiesRegister {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, MetallicsArts.MOD_ID);

    public static final RegistryObject<BlockEntityType<CrucibleFurnaceBlockEntity>> CRUCIBLE_FURNACE_ENTITY =
            BLOCK_ENTITIES.register("crucible_furnace_entity", () ->
                    BlockEntityType.Builder.of(CrucibleFurnaceBlockEntity::new,
                            ModBlocksRegister.CRUCIBLE_FURNACE.get()).build(null));

    public static final RegistryObject<BlockEntityType<HemalurgyAltarFrontBlockEntity>> HEMALURGY_ALTAR_FRONT_ENTITY =
            BLOCK_ENTITIES.register("hemalurgy_altar_front_entity", () ->
                    BlockEntityType.Builder.of(HemalurgyAltarFrontBlockEntity::new,
                            ModBlocksRegister.HEMALURGY_ALTAR_FRONT.get()).build(null));

    public static final RegistryObject<BlockEntityType<HemalurgyAltarBackBlockEntity>> HEMALURGY_ALTAR_BACK_ENTITY =
            BLOCK_ENTITIES.register("hemalurgy_altar_back_entity", () ->
                    BlockEntityType.Builder.of(HemalurgyAltarBackBlockEntity::new,
                            ModBlocksRegister.HEMALURGY_ALTAR_BACK.get()).build(null));

    public static final RegistryObject<BlockEntityType<DistilleryBlockEntity>> DISTILLERY_ENTITY =
            BLOCK_ENTITIES.register("distillery_entity", () ->
                    BlockEntityType.Builder.of(DistilleryBlockEntity::new,
                            ModBlocksRegister.DISTILLERY.get()).build(null));

    public static final RegistryObject<BlockEntityType<ModSingBlockEntity>> SIGN_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("sign_block_entity", () ->
                    BlockEntityType.Builder.of(ModSingBlockEntity::new,
                                    ModBlocksRegister.IRON_STANDING_SIGN.get(),
                                    ModBlocksRegister.IRON_WALL_SIGN.get(),
                                    ModBlocksRegister.GOLD_STANDING_SIGN.get(),
                                    ModBlocksRegister.GOLD_WALL_SIGN.get(),
                                    ModBlocksRegister.COPPER_STANDING_SIGN.get(),
                                    ModBlocksRegister.COPPER_WALL_SIGN.get(),
                                    ModBlocksRegister.ALUMINUM_STANDING_SIGN.get(),
                                    ModBlocksRegister.ALUMINUM_WALL_SIGN.get())
                            .build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
