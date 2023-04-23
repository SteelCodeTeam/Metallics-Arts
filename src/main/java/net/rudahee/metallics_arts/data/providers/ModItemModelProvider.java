package net.rudahee.metallics_arts.data.providers;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.data.enums.implementations.GemsEnum;
import net.rudahee.metallics_arts.data.enums.implementations.MetalEnum;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.setup.registries.ModBannersRegister;

import java.util.Arrays;
import java.util.List;

/**
 * This provider class control the default model from item. We use to define a texture in each item.
 *
 * @author SteelCode Team
 * @since 1.5.6
 *
 * @see ItemModelProvider
 * @see Item
 * @see ModelFile
 */
public class ModItemModelProvider extends ItemModelProvider {

    /**
     * Default constructor.These will be default parameters to register model textures in each item.
     * By default, always use "metallics_arts" mod id.
     *
     * @param generator class that will be used to generate the json that will contain the information
     * @param existingFileHelper class to check if path and image are valid in GatherDataEvent
     *
     * @see DataGenerator
     * @see ExistingFileHelper
     */
    public ModItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, MetallicsArts.MOD_ID, existingFileHelper);
    }


    /**
     * In this override method we define which textures are linked to which items,
     * for this we use a tons of predefined (by super class) methods.
     */
    @Override
    protected void registerModels(){

        ModelFile itemGenerated = getExistingFile(mcLoc("item/generated"));
        ModelFile itemHandHeld = getExistingFile(mcLoc("item/handheld"));
        List<MetalEnum> metalEnumList = Arrays.asList(MetalEnum.values());
        List<GemsEnum> gemList = Arrays.asList(GemsEnum.values());

        metalEnumList.forEach(metal -> {
            builder(itemGenerated,"item/"+ metal.getMetalNameLower()+"_ingot", "item/metal/ingot/" +metal.getMetalNameLower()+"_ingot");
            builder(itemGenerated,"item/"+ metal.getMetalNameLower()+"_nugget", "item/metal/nugget/" +metal.getMetalNameLower()+"_nugget");
            if (!metal.isAlloy()) {
                builder(itemGenerated,"item/raw_"+ metal.getMetalNameLower(), "item/metal/raw/raw_" +metal.getMetalNameLower());
            }

        });
        builder(itemGenerated,"item/copper_nugget", "item/metal/nugget/copper_nugget");

        gemList.forEach(gem -> {
            builder(itemGenerated,"item/"+ gem.getGemNameLower(), "item/gems/gems/" +gem.getGemNameLower());
            builder(itemGenerated,"item/"+ gem.getGemNameLower()+"_nugget", "item/gems/nugget/" +gem.getGemNameLower()+"_nugget");
        });

        for (MetalTagEnum metals : MetalTagEnum.values()) {
            Item item = ModBannersRegister.PATTERN_ITEMS.get("a_"+metals.getNameLower()).get();
            String model =  "item/pattern/" + ForgeRegistries.ITEMS.getKey(ModBannersRegister.PATTERN_ITEMS.get("a_"+metals.getNameLower()).get()).getPath();
            getBuilder(ForgeRegistries.ITEMS.getKey(item).getPath()).parent(getExistingFile(mcLoc("item/generated"))).texture("layer0", modLoc(model));

            item = ModBannersRegister.PATTERN_ITEMS.get("f_"+metals.getNameLower()).get();
            model =  "item/pattern/" + ForgeRegistries.ITEMS.getKey(ModBannersRegister.PATTERN_ITEMS.get("f_"+metals.getNameLower()).get()).getPath();
            getBuilder(ForgeRegistries.ITEMS.getKey(item).getPath()).parent(getExistingFile(mcLoc("item/generated"))).texture("layer0", modLoc(model));

        }

        /* Cores for new armor or obsidian weapons */
        builder(itemGenerated, "item/core_obsidian", "item/cores/core_obsidian");
        builder(itemGenerated, "item/core_aluminum", "item/cores/core_aluminum");
        builder(itemGenerated, "item/core_steel", "item/cores/core_steel");

        /* All textures for feruchemic bands **/

        builder(itemGenerated,"item/band_aluminum_duralumin","item/metal_mind/aluminium_duralumin_band");
        builder(itemGenerated,"item/band_atium_malatium","item/metal_mind/atium_malatium_band");
        builder(itemGenerated,"item/band_cadmium_bendalloy","item/metal_mind/cadmium_bendalloy_band");
        builder(itemGenerated,"item/band_chromium_nicrosil","item/metal_mind/chromium_nicrosil_band");
        builder(itemGenerated,"item/band_copper_bronze","item/metal_mind/copper_bronze_band");
        builder(itemGenerated,"item/band_gold_electrum","item/metal_mind/gold_electrum_band");
        builder(itemGenerated,"item/band_lerasium_ettmetal","item/metal_mind/lerasium_ettmetal_band");
        builder(itemGenerated,"item/band_tin_pewter","item/metal_mind/tin_pewter_band");
        builder(itemGenerated,"item/band_iron_steel","item/metal_mind/iron_steel_band");
        builder(itemGenerated,"item/band_zinc_brass","item/metal_mind/zinc_brass_band");

        /* All textures for feruchemic rings **/

        builder(itemGenerated,"item/ring_aluminum_duralumin","item/metal_mind/aliminium_duralumin_ring");
        builder(itemGenerated,"item/ring_atium_malatium","item/metal_mind/atium_malatium_ring");
        builder(itemGenerated,"item/ring_cadmium_bendalloy","item/metal_mind/cadmium_bendalloy_ring");
        builder(itemGenerated,"item/ring_chromium_nicrosil","item/metal_mind/chromium_nicrosil_ring");
        builder(itemGenerated,"item/ring_copper_bronze","item/metal_mind/copper_bronze_ring");
        builder(itemGenerated,"item/ring_gold_electrum","item/metal_mind/gold_electrum_ring");
        builder(itemGenerated,"item/ring_lerasium_ettmetal","item/metal_mind/lerasium_ettmetal_ring");
        builder(itemGenerated,"item/ring_tin_pewter","item/metal_mind/tin_pewter_ring");
        builder(itemGenerated,"item/ring_iron_steel","item/metal_mind/iron_steel_ring");
        builder(itemGenerated,"item/ring_zinc_brass","item/metal_mind/zinc_brass_ring");

        /* All textures for spikes **/

        builder(itemHandHeld,"item/iron_spike","item/spikes/iron_spike");
        builder(itemHandHeld,"item/steel_spike","item/spikes/steel_spike");
        builder(itemHandHeld,"item/tin_spike","item/spikes/tin_spike");
        builder(itemHandHeld,"item/pewter_spike","item/spikes/pewter_spike");
        builder(itemHandHeld,"item/copper_spike","item/spikes/copper_spike");
        builder(itemHandHeld,"item/bronze_spike","item/spikes/bronze_spike");
        builder(itemHandHeld,"item/zinc_spike","item/spikes/zinc_spike");
        builder(itemHandHeld,"item/brass_spike","item/spikes/brass_spike");
        builder(itemHandHeld,"item/chromium_spike","item/spikes/chromium_spike");
        builder(itemHandHeld,"item/nicrosil_spike","item/spikes/nicrosil_spike");
        builder(itemHandHeld,"item/aluminum_spike","item/spikes/aluminum_spike");
        builder(itemHandHeld,"item/duralumin_spike","item/spikes/duralumin_spike");
        builder(itemHandHeld,"item/cadmium_spike","item/spikes/cadmium_spike");
        builder(itemHandHeld,"item/bendalloy_spike","item/spikes/bendalloy_spike");
        builder(itemHandHeld,"item/electrum_spike","item/spikes/electrum_spike");
        builder(itemHandHeld,"item/gold_spike","item/spikes/gold_spike");
        builder(itemHandHeld,"item/atium_spike","item/spikes/atium_spike");
        builder(itemHandHeld,"item/malatium_spike","item/spikes/malatium_spike");
        builder(itemHandHeld,"item/lerasium_spike","item/spikes/lerasium_spike");
        builder(itemHandHeld,"item/ettmetal_spike","item/spikes/ettmetal_spike");

        builder(itemHandHeld,"item/koloss_blade","item/combat/koloss_blade");
        builder(itemHandHeld,"item/silver_knife","item/combat/silver_knife");
        builder(itemHandHeld,"item/obsidian_dagger","item/combat/obsidian_dagger");
        builder(itemHandHeld,"item/dueling_staff","item/combat/dueling_staff");
        builder(itemHandHeld,"item/obsidian_axe","item/combat/obsidian_axe");


        ModelFile mf_small = builder(itemGenerated,"item/small_vial_fill","item/consumables/small_vial_fill");

        getBuilder("small_vial")
                .parent(itemGenerated)
                .texture("layer0","item/consumables/small_vial")
                .override()
                .predicate(mcLoc("custom_model_data"),1)
                .model(mf_small)
                .end();

        ModelFile mf_large = builder(itemGenerated,"item/large_vial_fill","item/consumables/large_vial_fill");

        getBuilder("large_vial")
                .parent(itemGenerated)
                .texture("layer0","item/consumables/large_vial")
                .override()
                .predicate(mcLoc("custom_model_data"), 1)
                .model(mf_large)
                .end();


        for (MetalTagEnum metal: MetalTagEnum.values()) {
            builder(itemGenerated,"item/"+metal.getNameLower()+"_allomantic_icon","item/symbols/allomantic_symbols/"+metal.getNameLower()+"_symbol");
            builder(itemGenerated,"item/"+metal.getNameLower()+"_feruchemic_icon","item/symbols/feruchemic_symbols/"+metal.getNameLower()+"_symbol");
        }

        builder(itemHandHeld,"item/metallics_arts_book","item/metallics_arts_book");

        builder(itemGenerated,"item/steel_helmet", "item/armors/steel_helmet");
        builder(itemGenerated,"item/steel_chestplate", "item/armors/steel_chestplate");
        builder(itemGenerated,"item/steel_leggings", "item/armors/steel_leggings");
        builder(itemGenerated,"item/steel_boots", "item/armors/steel_boots");
        builder(itemGenerated,"item/aluminum_helmet", "item/armors/aluminum_helmet");
        builder(itemGenerated,"item/aluminum_chestplate", "item/armors/aluminum_chestplate");
        builder(itemGenerated,"item/aluminum_leggings", "item/armors/aluminum_leggings");
        builder(itemGenerated,"item/aluminum_boots", "item/armors/aluminum_boots");
        builder(itemGenerated,"item/mistcloak","item/armors/mistcloak");

    }

    /**
     * Auxiliary method to generate a common builder for the item models.
     *
     * @param itemGenerated model from specific item.
     * @param outPath path to output the json.
     * @param texturePath path to texture.
     *
     * @return ItemModelBuilder
     *
     * @see ItemModelBuilder
     */
    private ItemModelBuilder builder(ModelFile itemGenerated, String outPath, String texturePath){
        return getBuilder(outPath).parent(itemGenerated).texture("layer0", texturePath);
    }

}
