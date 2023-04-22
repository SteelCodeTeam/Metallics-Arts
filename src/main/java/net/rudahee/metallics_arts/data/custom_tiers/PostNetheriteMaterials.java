package net.rudahee.metallics_arts.data.custom_tiers;

import net.minecraft.sounds.SoundEvent;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;

import net.minecraft.world.item.crafting.Ingredient;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.setup.registries.ModItemsRegister;

public enum PostNetheriteMaterials implements ArmorMaterial {


    STEEL("steel", 45, 0.9F, 1.5F, Ingredient.of(ModItemsRegister.CORE_STEEL.get())),
    ALUMINUM("aluminum", 30, Ingredient.of(ModItemsRegister.CORE_ALUMINUM.get()));


    private static final int[] HEALTH_PER_SLOT = new int[]{13, 15, 16, 11};
    private final String name;
    private final int durabilityMultiplier;
    private final int[] slotProtections;
    private final int enchantmentValue;
    private final SoundEvent sound;
    private final float toughness;
    private final float knockbackResistance;
    private final Ingredient repairIngredient;


     PostNetheriteMaterials(String name, int durability, Ingredient repairIngredient) {
        this.name = name;
        this.durabilityMultiplier = durability;
        this.slotProtections = new int[]{4, 6, 8, 4};
        this.enchantmentValue = 15;
        this.sound = SoundEvents.ARMOR_EQUIP_NETHERITE;
        this.toughness = 3.0F;
        this.knockbackResistance = 0.1F;
        this.repairIngredient = repairIngredient;
    }

    PostNetheriteMaterials(String name, int durability, float knockbackResistance, float toughness, Ingredient repairIngredient) {
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
    public int getDurabilityForSlot(EquipmentSlot slot) {
        return HEALTH_PER_SLOT[slot.getIndex()] * this.durabilityMultiplier;
    }

    @Override
    public int getDefenseForSlot(EquipmentSlot slot) {
        return this.slotProtections[slot.getIndex()];
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
