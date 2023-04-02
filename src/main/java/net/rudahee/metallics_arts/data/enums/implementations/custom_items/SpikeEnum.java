package net.rudahee.metallics_arts.data.enums.implementations.custom_items;


import net.minecraft.world.item.Item;

/**
 * Enumerated type that represents the different types of spikes that can be used in the game.
 * Each value represents a different metal or alloy and its corresponding properties.
 *
 * @author SteelCode Team
 * @since 1.5.1
 */
public enum SpikeEnum {
    IRON("iron",null,true,false),
    STEEL("steel", null,false,false),
    TIN("tin", null,false,false),
    PEWTER("pewter", null,false,false),
    COPPER("copper", null,true,false),
    BRONZE("bronze", null,false,false),
    ZINC("zinc", null,false,false),
    BRASS("brass", null,false,false),
    CHROMIUM("chromium", null,false,false),
    NICROSIL("nicrosil", null,false,false),
    ALUMINUM("aluminum", null,false,false),
    DURALUMIN("duralumin",null,false,false),
    CADMIUM("cadmium", null,false,false),
    BENDALLOY("bendalloy", null,false,false),
    ELECTRUM("electrum", null,false,false),
    GOLD("gold", null,true,false),
    ATIUM("atium", null,false,true),
    MALATIUM("malatium", null,false,true),
    LERASIUM("lerasium", null,false,true),
    ETTMETAL("ettmetal", null,false,true);

    private final String name;
    private Item spike;
    private final boolean vanilla;
    private final boolean gems;


    /**
     * Constructs a new SpikeEnum object with the provided parameters.
     *
     * @param name the name of the metal or alloy.
     * @param spike the item representing the spike.
     * @param vanilla whether the metal or alloy is part of the vanilla game.
     * @param gems whether the metal or alloy is a gem metal.
     */
    SpikeEnum(String name, Item spike, boolean vanilla, boolean gems) {
        this.name = name;
        this.spike = spike;
        this.vanilla = vanilla;
        this.gems = gems;
    }
    /**
     * Returns the name of the metal or alloy.
     *
     * @return the name of the metal or alloy.
     */
    public String getName() {
        return name;
    }
    /**
     * Returns the item representing the spike.
     *
     * @return the item representing the spike.
     */
    public Item getSpike() {
        return spike;
    }
    /**
     * Sets the item representing the spike.
     *
     * @param spike the item representing the spike.
     */
    public void setSpike (Item spike){
        this.spike = spike;
    }

    /**
     * Returns whether the metal or alloy is part of the vanilla game.
     *
     * @return true if the metal or alloy is part of the vanilla game, false otherwise.
     */
    public boolean isVanilla() {
        return vanilla;
    }

    /**
     * Returns whether the metal or alloy is a gem metal.
     *
     * @return true if the metal or alloy is a gem metal, false otherwise.
     */
    public boolean isGems() {
        return gems;
    }



}
