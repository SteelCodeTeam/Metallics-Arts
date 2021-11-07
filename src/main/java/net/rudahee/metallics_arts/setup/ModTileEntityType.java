package net.rudahee.metallics_arts.setup;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.rudahee.metallics_arts.block.alloyfurnace.ModAlloyFurnaceTileEntity;
import org.graalvm.compiler.nodes.cfg.Block;

import java.util.function.Supplier;

public class ModTileEntityType  {

    public static final RegistryObject <TileEntityType<ModTileEntityType>> ALLOY_FURNACE = register (
            "alloyFurcace",
            ModAlloyFurnaceTileEntity::new,
            ModBlock.alloyFurnace);


    static void register(){}

    private static <T extends  TileEntity> RegistryObject <TileEntityType<T>> register (String name,
                                                                                        Supplier<T> factory,
                                                                                        RegistryObject<? extends Block> block){
        return Registration.TILE_ENTITIES.register (name, () ->{
            //aa
            return TileEntityType.Builder.of(factory, (net.minecraft.block.Block) block.get()).build(null);
        });
    }
}
