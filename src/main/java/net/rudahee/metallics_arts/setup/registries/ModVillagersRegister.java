package net.rudahee.metallics_arts.setup.registries;

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

import java.lang.reflect.InvocationTargetException;

public class ModVillagersRegister {
    public static final DeferredRegister<PoiType> POI_TYPES =
            DeferredRegister.create(ForgeRegistries.POI_TYPES, MetallicsArts.MOD_ID);

    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSION =
            DeferredRegister.create(ForgeRegistries.VILLAGER_PROFESSIONS, MetallicsArts.MOD_ID);

    public static final RegistryObject<PoiType> CRUCIBLE_FURNACE_POI = POI_TYPES.register("crucible_furnace_poi",
            () -> new PoiType(ImmutableSet.copyOf(ModBlocksRegister.CRUCIBLE_FURNACE.get().getStateDefinition().getPossibleStates()), 1, 1));

    public static final RegistryObject<PoiType> HEMALURGY_ALTAR_FRONT_POI = POI_TYPES.register("hemalurgy_altar_front_poi",
            () -> new PoiType(ImmutableSet.copyOf(ModBlocksRegister.HEMALURGY_ALTAR_FRONT.get().getStateDefinition().getPossibleStates()), 1, 1));

    public static final RegistryObject<PoiType> HEMALURGY_ALTAR_BACK_POI = POI_TYPES.register("hemalurgy_altar_back_poi",
            () -> new PoiType(ImmutableSet.copyOf(ModBlocksRegister.HEMALURGY_ALTAR_BACK.get().getStateDefinition().getPossibleStates()), 1, 1));

    public static final RegistryObject<VillagerProfession> FORGE_MASTER = VILLAGER_PROFESSION.register("forge_master",
            () -> new VillagerProfession("forge_master",
                    x -> x.get() == CRUCIBLE_FURNACE_POI.get(),
                    x -> x.get() == CRUCIBLE_FURNACE_POI.get(),
                    ImmutableSet.of(), ImmutableSet.of(),
                    SoundEvents.VILLAGER_WORK_ARMORER));

    public static final RegistryObject<VillagerProfession> HEMALURGY_MONK = VILLAGER_PROFESSION.register("hemalurgy_monk",
            () -> new VillagerProfession("hemalurgy_monk",
                    x -> x.get() == HEMALURGY_ALTAR_FRONT_POI.get(),
                    x -> x.get() == HEMALURGY_ALTAR_FRONT_POI.get(),
                    ImmutableSet.of(), ImmutableSet.of(),
                    SoundEvents.VILLAGER_WORK_CLERIC));

    public static final RegistryObject<VillagerProfession> HEMALURGY_WARRIOR = VILLAGER_PROFESSION.register("hemalurgy_warrior",
            () -> new VillagerProfession("hemalurgy_warrior",
                    x -> x.get() == HEMALURGY_ALTAR_BACK_POI.get(),
                    x -> x.get() == HEMALURGY_ALTAR_BACK_POI.get(),
                    ImmutableSet.of(), ImmutableSet.of(),
                    SoundEvents.VILLAGER_WORK_WEAPONSMITH));


    public static void registerPOI() {
        try {
            ObfuscationReflectionHelper.findMethod(PoiType.class, "registerBlockStates").invoke(null);
        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void register(IEventBus bus) {
        POI_TYPES.register(bus);
        VILLAGER_PROFESSION.register(bus);
    }
}
