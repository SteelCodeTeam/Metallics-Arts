package net.rudahee.metallics_arts.setup;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.setup.registries.*;

public class Registration {

    // In this class we register each TYPE OF OBJECT we add in our mod.

    public static final DeferredRegister<Block> BLOCKS = createBlock();
    public static final DeferredRegister<Item> ITEMS = createItem();


    public static void register() {
        // We inject our objects to "Minecraft Bus" to load all of this

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);

        ModItems.register();
        ModBlock.register();
        ModTileEntities.register(modEventBus);
        //ModContainers.register(modEventBus);
        ModRecipeTypes.register(modEventBus);
    }

    private static DeferredRegister<Block> createBlock (){
        return DeferredRegister.create(ForgeRegistries.BLOCKS, MetallicsArts.MOD_ID);
    }
    private static DeferredRegister<Item> createItem (){
        return DeferredRegister.create(ForgeRegistries.ITEMS, MetallicsArts.MOD_ID);
    }
}
