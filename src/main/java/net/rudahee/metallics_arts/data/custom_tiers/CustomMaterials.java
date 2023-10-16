package net.rudahee.metallics_arts.data.custom_tiers;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.setup.registries.ModItemsRegister;

public enum CustomMaterials implements ArmorMaterial {


    STEEL("post_netherite", 45, 0.9F, 1.5F, Ingredient.of(ModItemsRegister.STEEL_CORE.get())),
    ALUMINUM("post_netherite", 30, Ingredient.of(ModItemsRegister.ALUMINUM_CORE.get())),
    ETTMETAL("post_netherite", 45, Ingredient.of(ModItemsRegister.ETTMETAL_CORE.get())),
    LERASIUM("post_netherite", 45, Ingredient.of(ModItemsRegister.LERASIUM_CORE.get())),
    ATIUM("post_netherite", 45, Ingredient.of(ModItemsRegister.ATIUM_CORE.get())),
    COPPER("post_netherite", 45, Ingredient.of(ModItemsRegister.COPPER_CORE.get()));


    private static final int[] HEALTH_PER_SLOT = new int[]{13, 15, 16, 11};
    private final String name;
    private final int durabilityMultiplier;
    private final int[] slotProtections;
    private final int enchantmentValue;
    private final SoundEvent sound;
    private final float toughness;
    private final float knockbackResistance;
    private final Ingredient repairIngredient;


     CustomMaterials(String name, int durability, Ingredient repairIngredient) {
        this.name = name;
        this.durabilityMultiplier = durability;
        this.slotProtections = new int[]{4, 6, 8, 4};
        this.enchantmentValue = 15;
        this.sound = SoundEvents.ARMOR_EQUIP_NETHERITE;
        this.toughness = 3.0F;
        this.knockbackResistance = 0.1F;
        this.repairIngredient = repairIngredient;
    }

    CustomMaterials(String name, int durability, float knockbackResistance, float toughness, Ingredient repairIngredient) {
        this.name = name;
        this.durabilityMultiplier = durability;
        this.slotProtections = new int[]{4, 6, 8, 4};
        this.enchantmentValue = 15;
        this.sound = SoundEvents.ARMOR_EQUIP_NETHERITE;
        this.toughness = 3.0F + toughness;
        this.knockbackResistance = 0.1F + knockbackResistance;
        this.repairIngredient = repairIngredient;
    }

    @Override
    public int getDurabilityForType(ArmorItem.Type slot) {
        return HEALTH_PER_SLOT[slot.getSlot().getIndex()] * this.durabilityMultiplier;
    }

    @Override
    public int getDefenseForType(ArmorItem.Type slot) {
        return this.slotProtections[slot.getSlot().getIndex()];
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    @Override
    public SoundEvent getEquipSound() {
        return this.sound;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient;
    }

    @Override
    public String getName() {
        return MetallicsArts.MOD_ID + ":" + this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }



}
