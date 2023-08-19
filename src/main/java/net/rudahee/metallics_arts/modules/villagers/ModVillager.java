package net.rudahee.metallics_arts.modules.villagers;

import com.google.common.collect.ImmutableSet;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.setup.registries.ModBlocksRegister;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;

public class ModVillager {
    public static final DeferredRegister<PoiType> POI_TYPE =
            DeferredRegister.create(ForgeRegistries.POI_TYPES, MetallicsArts.MOD_ID);

    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSION =
            DeferredRegister.create(ForgeRegistries.VILLAGER_PROFESSIONS, MetallicsArts.MOD_ID);


    public static final RegistryObject<PoiType> CRUCIBLE_FURNACE_POI = POI_TYPE.register("crucible_furnace_poi",
            () -> new PoiType(getBlockStates(ModBlocksRegister.CRUCIBLE_FURNACE.get()),1,1));

    public static final RegistryObject<VillagerProfession> VILLAGER_PROFESSION_REGISTRY_OBJECT = VILLAGER_PROFESSION.register("crucible",
            () -> new VillagerProfession("name",PoiType.NONE, (poiTypeHolder) -> poiTypeHolder.is(CRUCIBLE_FURNACE_POI.getId()) , ImmutableSet.of(), ImmutableSet.of(),SoundEvents.VILLAGER_WORK_ARMORER));


    public static void registerPOIs () {
        try {
            ObfuscationReflectionHelper.findMethod(PoiType.class,"registerBlockStates", PoiType.class)
                    .invoke(null, CRUCIBLE_FURNACE_POI.get());

        } catch (InvocationTargetException | IllegalAccessException exception) {
                exception.printStackTrace();
        }
    }

    private static Set<BlockState> getBlockStates(Block p_218074_) {
        return ImmutableSet.copyOf(p_218074_.getStateDefinition().getPossibleStates());
    }

    public static void register(IEventBus eventBus) {
        POI_TYPE.register(eventBus);
        VILLAGER_PROFESSION.register(eventBus);
    }
}
