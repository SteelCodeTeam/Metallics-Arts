package net.rudahee.metallics_arts.setup.registries;


import net.minecraft.core.BlockPos;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.rudahee.metallics_arts.MetallicsArts;

public class ModContainers {

    /*public static DeferredRegister<ContainerType<?>> CONTAINERS
            = DeferredRegister.create(ForgeRegistries.CONTAINERS, MetallicsArts.MOD_ID);

    public static final RegistryObject<ContainerType<AlloyFurnaceContainer>> ALLOY_FURNACE_CONTAINER
            = CONTAINERS.register("alloy_furnace_container",
            () -> IForgeContainerType.create(((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                return AlloyFurnaceContainer.createContainerInClientSide(windowId, pos, inv, data);
            })));


    public static void register(IEventBus eventBus) {
        CONTAINERS.register(eventBus);
    }*/
}
