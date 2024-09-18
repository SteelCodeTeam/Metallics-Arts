package net.rudahee.metallics_arts.modules.effects;

import net.minecraft.world.entity.ai.attributes.AttributeModifier;

public class ModModifiers {

    public static final AttributeModifier MISTCLOACK_SPEED = new AttributeModifier("mistcloak_speed_modifier", 1.25D,AttributeModifier.Operation.MULTIPLY_TOTAL);
    public static final AttributeModifier MAX_HEALTH_ELECTRUM = new AttributeModifier("max_health_electrum_modifier", 10D, AttributeModifier.Operation.ADDITION);

    public static final AttributeModifier MAX_HEALTH_ELECTRUM_COMPOUNDING = new AttributeModifier("max_health_electrum_modifier_compounding", 20D, AttributeModifier.Operation.ADDITION);
    public static final AttributeModifier MIN_HEALTH_ELECTRUM = new AttributeModifier("min_health_electrum_modifier", -10D, AttributeModifier.Operation.ADDITION);


}
