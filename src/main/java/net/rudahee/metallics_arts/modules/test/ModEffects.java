package net.rudahee.metallics_arts.modules.test;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.enums.implementations.languages.CTW;
import net.rudahee.metallics_arts.data.enums.implementations.languages.MetalAuxiliaryInfo;
import net.rudahee.metallics_arts.data.enums.implementations.languages.MetalNamesEnum;

import java.util.HashMap;
import java.util.Map;

public class ModEffects {

    public static DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, MetallicsArts.MOD_ID);

    public static final Map<String, RegistryObject<MobEffect>> POWER_EFFECTS = new HashMap<>() {{
        for (MetalTagEnum metal: MetalTagEnum.values()) {
            put("allomantic_"+metal.getNameLower(), MOB_EFFECTS.register("allomantic_" + metal.getNameLower() + "_symbol", ()-> new PowerEffect(MobEffectCategory.NEUTRAL,11120)));
            put("feruchemical_"+metal.getNameLower()+"_storage", MOB_EFFECTS.register("feruchemical_" + metal.getNameLower() + "_storage", ()-> new PowerEffect(MobEffectCategory.NEUTRAL,11120)));
            put("feruchemical_"+metal.getNameLower()+"_tap", MOB_EFFECTS.register("feruchemical_" + metal.getNameLower() + "_tap", ()-> new PowerEffect(MobEffectCategory.NEUTRAL,11120)));
        }
    }};

    public static void register (IEventBus eventBus){
        MOB_EFFECTS.register(eventBus);
    }
}
