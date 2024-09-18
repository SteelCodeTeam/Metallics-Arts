package net.rudahee.metallics_arts.modules.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;

import java.util.HashMap;
import java.util.Map;


/**
 * A class containing methods and properties related to effects in the mod.
 *
 *  @author SteelCode Team
 *  @since 1.5.1
 */
public class ModEffects {

    /**
     * A deferred register for registering mob effects with the Forge registries.
     * @see ForgeRegistries
     */
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, MetallicsArts.MOD_ID);

    /**
     * A map of allomantic and feruchemical effects registered in the mod.
     * Each metal has three registered effects: allomantic effect, feruchemical storage effect, and feruchemical tap effect.
     */
    public static final Map<String, RegistryObject<MobEffect>> POWER_EFFECTS = new HashMap<>();
    static {
        for (MetalTagEnum metal: MetalTagEnum.values()) {
            POWER_EFFECTS.put("allomantic_"+metal.getNameLower(), MOB_EFFECTS.register("allomantic_" + metal.getNameLower(), ()-> new PowerEffect(MobEffectCategory.NEUTRAL,11120)));
            POWER_EFFECTS.put("feruchemical_"+metal.getNameLower()+"_storage", MOB_EFFECTS.register("feruchemical_" + metal.getNameLower() + "_storage", ()-> new PowerEffect(MobEffectCategory.NEUTRAL,11120)));
            POWER_EFFECTS.put("feruchemical_"+metal.getNameLower()+"_tap", MOB_EFFECTS.register("feruchemical_" + metal.getNameLower() + "_tap", ()-> new PowerEffect(MobEffectCategory.NEUTRAL,11120)));
        }
    }

    /**
     * Gives an Allomantic effect to the specified player based on the given metal.
     *
     * @param player the player to give the effect to.
     * @param metal the metal to use as the basis for the effect.
     */
    public static void giveAllomanticEffect(Player player, MetalTagEnum metal) {
        MobEffectInstance effect = new MobEffectInstance(ModEffects.POWER_EFFECTS.get("allomantic_"+metal.getNameLower()).get(), 10, 0, true, true);
        player.addEffect(effect);
    }

    /**
     * Gives a Feruchemical Tap effect to the specified player based on the given metal.
     *
     * @param player the player to give the effect to.
     * @param metal the metal to use as the basis for the effect.
     */
    public static void giveFeruchemicalTapEffect(Player player, MetalTagEnum metal) {
        MobEffectInstance effect = new MobEffectInstance(ModEffects.POWER_EFFECTS.get("feruchemical_" + metal.getNameLower() + "_tap").get(), 2, 0, true, true);
        player.addEffect(effect);
    }
    /**
     * Gives a Feruchemical Storage effect to the specified player based on the given metal.
     *
     * @param player the player to give the effect to.
     * @param metal the metal to use as the basis for the effect.
     */
    public static void giveFeruchemicalStorageEffect(Player player, MetalTagEnum metal) {
        MobEffectInstance effect = new MobEffectInstance(ModEffects.POWER_EFFECTS.get("feruchemical_" + metal.getNameLower() + "_storage").get(), 2, 0, true, true);
        player.addEffect(effect);
    }

    /**
     * Registers the effects variants with the given event bus.
     *
     * @param eventBus the event bus to register the effects variants.
     */
    public static void register (IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}
