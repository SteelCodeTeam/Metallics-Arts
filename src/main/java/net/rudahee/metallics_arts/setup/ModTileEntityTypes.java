package net.rudahee.metallics_arts.setup;


import net.minecraft.block.Block;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.rudahee.metallics_arts.block.alloyfurnace.AlloyFurnaceScreen;
import net.rudahee.metallics_arts.block.alloyfurnace.ModAlloyFurnaceTileEntity;

import java.util.function.Supplier;

public class ModTileEntityTypes {

    public static final RegistryObject<TileEntityType<ModAlloyFurnaceTileEntity>> ALLOY_FURNACE = register("alloy_furnace", ModAlloyFurnaceTileEntity::new, (RegistryObject<Block>) ModBlock.ALLOY_FURNACE_REGISTRY);

    static void register(){

    }

    private static <T extends TileEntity> RegistryObject<TileEntityType<T>> register (
            String name, Supplier<T> factory, RegistryObject<Block> block){
        return Registration.TILE_ENTITIES.register(name, () -> TileEntityType.Builder.of(factory, block.get()).build(null));
    }
}
