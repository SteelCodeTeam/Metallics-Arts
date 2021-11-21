package net.rudahee.metallics_arts.setup;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.data.recipes.ModRecipeTypes;
import net.rudahee.metallics_arts.minecraft_objects.containers.ModContainers;
import net.rudahee.metallics_arts.minecraft_objects.tile_entity.ModTileEntities;

public class Registration {

    // In this class we register each TYPE OF OBJECT we add in our mod.

    public static final DeferredRegister<Block> BLOCKS = create(ForgeRegistries.BLOCKS);
    public static final DeferredRegister<Item> ITEMS = create(ForgeRegistries.ITEMS);


    public static void register() {
        // We inject our objects to "Minecraft Bus" to load all of this? these? i dont know?

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);

        ModItems.register();
        ModBlock.register();
        ModTileEntities.register(modEventBus);
        ModContainers.register(modEventBus);
        ModRecipeTypes.register(modEventBus);
    }

    private static <T extends IForgeRegistryEntry<T>> DeferredRegister<T> create (IForgeRegistry<T> registry){
        return DeferredRegister.create(registry, MetallicsArts.MOD_ID);
    }

}
