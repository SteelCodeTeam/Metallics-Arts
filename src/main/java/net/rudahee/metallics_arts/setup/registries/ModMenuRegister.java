package net.rudahee.metallics_arts.setup.registries;

import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.modules.custom_block_entities.crucible_furnace.CrucibleFurnaceMenu;
import net.rudahee.metallics_arts.modules.custom_block_entities.distillery.DistilleryMenu;
import net.rudahee.metallics_arts.modules.custom_block_entities.hemalurgy_altar_block.back.HemalurgyAltarBackMenu;
import net.rudahee.metallics_arts.modules.custom_block_entities.hemalurgy_altar_block.front.HemalurgyAltarFrontMenu;

public class ModMenuRegister {
    private ModMenuRegister() {
        throw new IllegalStateException("Class can't be instantiated");
    }
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, MetallicsArts.MOD_ID);

    public static final RegistryObject<MenuType<CrucibleFurnaceMenu>> CRUCIBLE_FURNACE_MENU =
            registerMenuType(CrucibleFurnaceMenu::new, "crucible_furnace_menu");

    public static final RegistryObject<MenuType<HemalurgyAltarFrontMenu>> HEMALURGY_ALTAR_FRONT_MENU =
            registerMenuType(HemalurgyAltarFrontMenu::new, "hemalurgy_altar_front_menu");
    public static final RegistryObject<MenuType<HemalurgyAltarBackMenu>> HEMALURGY_ALTAR_BACK_MENU =
            registerMenuType(HemalurgyAltarBackMenu::new, "hemalurgy_altar_back_menu");

    public static final RegistryObject<MenuType<DistilleryMenu>> DISTILLERY_MENU =
            registerMenuType(DistilleryMenu::new, "distillery_menu");

    private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> registerMenuType(IContainerFactory<T> factory,
                                                                                                  String name) {
        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}
