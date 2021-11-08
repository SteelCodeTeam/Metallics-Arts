package net.rudahee.metallics_arts.setup;

import net.minecraft.client.gui.ScreenManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.rudahee.metallics_arts.block.alloyfurnace.AlloyFurnaceScreen;
import net.rudahee.metallics_arts.block.alloyfurnace.ModAlloyFurnaceTileEntity;
import org.graalvm.compiler.nodes.cfg.Block;

import java.util.function.Supplier;

public class ModTileEntityType  {

    public static final RegistryObject <TileEntityType<ModAlloyFurnaceTileEntity>> ALLOY_FURNACE = register ("alloyFurcace", ModAlloyFurnaceTileEntity::new);

    @OnlyIn(Dist.CLIENT)
    public static void registerScreens (FMLClientSetupEvent event){
        ScreenManager.register(ALLOY_FURNACE.get(), AlloyFurnaceScreen::new);
    }

    static void register(){

    }

    private static <T extends  TileEntity> RegistryObject <TileEntityType<T>> register (String name, Supplier<T> factory, RegistryObject<? extends Block> block){
        return Registration.TILE_ENTITIES.register (name, () ->{
            //aa
            return TileEntityType.Builder.of(factory, (net.minecraft.block.Block) block.get()).build(null);
        });
    }
}
