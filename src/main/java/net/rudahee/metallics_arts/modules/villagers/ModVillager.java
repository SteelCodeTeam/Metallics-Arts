package net.rudahee.metallics_arts.modules.villagers;

import com.google.common.collect.ImmutableSet;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.setup.registries.ModBlocksRegister;

import java.lang.reflect.InvocationTargetException;

public class ModVillager {
    public static final DeferredRegister<PoiType> POI_TYPE =
            DeferredRegister.create(ForgeRegistries.POI_TYPES, MetallicsArts.MOD_ID);

    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS =
            DeferredRegister.create(ForgeRegistries.VILLAGER_PROFESSIONS, MetallicsArts.MOD_ID);


    public static final RegistryObject<PoiType> CRUCIBLE_FURNACE_POI = POI_TYPE.register("crucible_furnace_poi",
            () -> new PoiType(ImmutableSet.copyOf(ModBlocksRegister.CRUCIBLE_FURNACE.get().getStateDefinition().getPossibleStates()),
                    1,1));

    public static final RegistryObject<VillagerProfession> VILLAGER_CRUCIBLE_PROFESSION = VILLAGER_PROFESSIONS.register("forge_master",
            () -> new VillagerProfession("forge_master", x -> x.get() == CRUCIBLE_FURNACE_POI.get(),
                    x -> x.get() == CRUCIBLE_FURNACE_POI.get(), ImmutableSet.of(), ImmutableSet.of(),
                    SoundEvents.VILLAGER_WORK_ARMORER));


    public static void registerPOIs () {
        try {
            ObfuscationReflectionHelper.findMethod(PoiType.class,"registerBlockStates", PoiType.class)
                    .invoke(null, CRUCIBLE_FURNACE_POI.get());

        } catch (InvocationTargetException | IllegalAccessException exception) {
                exception.printStackTrace();
        }
    }

    public static void register(IEventBus eventBus) {
        POI_TYPE.register(eventBus);
        VILLAGER_PROFESSIONS.register(eventBus);
    }
}
