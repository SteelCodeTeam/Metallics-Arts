package net.rudahee.metallics_arts.setup;

import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.IContainerListener;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.network.IContainerFactory;
import net.rudahee.metallics_arts.block.alloyfurnace.AlloyFurnaceContainer;

public class ModContainerTypes {

    public static final RegistryObject<ContainerType<AlloyFurnaceContainer>> ALLOY_FURNACE = register("alloy_furnace",AlloyFurnaceContainer::new);


    static void register(){}

    private static <T extends Container> RegistryObject<ContainerType<T>> register (String name, IContainerFactory<T> factory){
        return Registration.CONTAINERS.register(name, () -> IForgeContainerType.create(factory));
    }

}
