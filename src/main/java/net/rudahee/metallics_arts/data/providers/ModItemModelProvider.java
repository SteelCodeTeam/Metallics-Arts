package net.rudahee.metallics_arts.data.providers;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.setup.enums.gems.Gems;
import net.rudahee.metallics_arts.setup.enums.metals.Metal;

import java.util.Arrays;
import java.util.List;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, MetallicsArts.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels(){

        ModelFile itemGenerated = getExistingFile(mcLoc("item/generated"));
        List<Metal> metalList = Arrays.asList(Metal.values());
        List<Gems> gemList = Arrays.asList(Gems.values());

        metalList.forEach(metal -> {
            builder(itemGenerated,"item/"+ metal.getMetalNameLower()+"_ingot", "item/metal/ingot/" +metal.getMetalNameLower()+"_ingot");
            builder(itemGenerated,"item/"+ metal.getMetalNameLower()+"_nugget", "item/metal/nugget/" +metal.getMetalNameLower()+"_nugget");
            if (!metal.isAlloy()) {
                builder(itemGenerated,"item/raw_"+ metal.getMetalNameLower(), "item/metal/raw/raw_" +metal.getMetalNameLower());
            }

        });

        gemList.forEach(gem -> {
            builder(itemGenerated,"item/"+ gem.getGemNameLower(), "item/gems/gems/" +gem.getGemNameLower());
            builder(itemGenerated,"item/"+ gem.getGemNameLower()+"_nugget", "item/gems/nugget/" +gem.getGemNameLower()+"_nugget");
        });


        /** All textures for feruchemic bands **/

        builder(itemGenerated,"item/band_aluminum_duralumin","item/metal_mind/aluminium_duralumin_band");
        builder(itemGenerated,"item/band_atium_malatium","item/metal_mind/atium_malatium_band");
        builder(itemGenerated,"item/band_cadmium_bendalloy","item/metal_mind/cadmium_bendalloy_band");
        builder(itemGenerated,"item/band_chromium_nicrosil","item/metal_mind/chromium_nicrosil_band");
        builder(itemGenerated,"item/band_copper_bronze","item/metal_mind/copper_bronze_band");
        builder(itemGenerated,"item/band_electrum_gold","item/metal_mind/gold_electrum_band");
        builder(itemGenerated,"item/band_lerasium_ettmetal","item/metal_mind/lerasium_ettmetal_band");
        builder(itemGenerated,"item/band_pewter_tin","item/metal_mind/tin_pewter_band");
        builder(itemGenerated,"item/band_steel_iron","item/metal_mind/iron_steel_band");
        builder(itemGenerated,"item/band_zinc_brass","item/metal_mind/zinc_brass_band");

        /** All textures for feruchemic rings **/

        builder(itemGenerated,"item/ring_aluminum_duralumin","item/metal_mind/aliminium_duralumin_ring");
        builder(itemGenerated,"item/ring_atium_malatium","item/metal_mind/atium_malatium_ring");
        builder(itemGenerated,"item/ring_cadmium_bendalloy","item/metal_mind/cadmium_bendalloy_ring");
        builder(itemGenerated,"item/ring_chromium_nicrosil","item/metal_mind/chromium_nicrosil_ring");
        builder(itemGenerated,"item/ring_copper_bronze","item/metal_mind/copper_bronze_ring");
        builder(itemGenerated,"item/ring_electrum_gold","item/metal_mind/gold_electrum_ring");
        builder(itemGenerated,"item/ring_lerasium_ettmetal","item/metal_mind/lerasium_ettmetal_ring");
        builder(itemGenerated,"item/ring_pewter_tin","item/metal_mind/tin_pewter_ring");
        builder(itemGenerated,"item/ring_steel_iron","item/metal_mind/iron_steel_ring");
        builder(itemGenerated,"item/ring_zinc_brass","item/metal_mind/zinc_brass_ring");

        /** All textures for spikes **/

        builder(itemGenerated,"item/iron_spike","item/spikes/iron_spike");
        builder(itemGenerated,"item/steel_spike","item/spikes/steel_spike");
        builder(itemGenerated,"item/tin_spike","item/spikes/tin_spike");
        builder(itemGenerated,"item/pewter_spike","item/spikes/pewter_spike");
        builder(itemGenerated,"item/copper_spike","item/spikes/copper_spike");
        builder(itemGenerated,"item/bronze_spike","item/spikes/bronze_spike");
        builder(itemGenerated,"item/zinc_spike","item/spikes/zinc_spike");
        builder(itemGenerated,"item/brass_spike","item/spikes/brass_spike");
        builder(itemGenerated,"item/chromium_spike","item/spikes/chromium_spike");
        builder(itemGenerated,"item/nicrosil_spike","item/spikes/nicrosil_spike");
        builder(itemGenerated,"item/aluminum_spike","item/spikes/aluminum_spike");
        builder(itemGenerated,"item/duralumin_spike","item/spikes/duralumin_spike");
        builder(itemGenerated,"item/cadmium_spike","item/spikes/cadmium_spike");
        builder(itemGenerated,"item/bendalloy_spike","item/spikes/bendalloy_spike");
        builder(itemGenerated,"item/electrum_spike","item/spikes/electrum_spike");
        builder(itemGenerated,"item/gold_spike","item/spikes/gold_spike");
        builder(itemGenerated,"item/atium_spike","item/spikes/atium_spike");
        builder(itemGenerated,"item/malatium_spike","item/spikes/malatium_spike");
        builder(itemGenerated,"item/lerasium_spike","item/spikes/lerasium_spike");
        builder(itemGenerated,"item/ettmetal_spike","item/spikes/ettmetal_spike");

        builder(itemGenerated,"item/koloss_blade","item/combat/koloss_blade");
        builder(itemGenerated,"item/cristal_dagger","item/combat/cristal_dagger");
        builder(itemGenerated,"item/obsidian_dagger","item/combat/obsidian_dagger");
        builder(itemGenerated,"item/dueling_staff","item/combat/dueling_staff");
        builder(itemGenerated,"item/obsidian_axe","item/combat/obsidian_axe");


        ModelFile mf_small = builder(itemGenerated,"item/small_vial_fill","item/consumables/small_vial_fill");

        getBuilder("small_vial")
                .parent(itemGenerated)
                .texture("layer0","item/consumables/small_vial")
                .override()
                .predicate(mcLoc("custom_model_data"),1)
                .model(mf_small)
                .end();

        ModelFile mf_large = builder(itemGenerated,"item/big_vial_fill","item/consumables/big_vial_fill");

        getBuilder("big_vial")
                .parent(itemGenerated)
                .texture("layer0","item/consumables/big_vial")
                .override()
                .predicate(mcLoc("custom_model_data"),1)
                .model(mf_large)
                .end();


    }
    private ItemModelBuilder builder (ModelFile itemGenerated, String outPath, String texturePath){
        return  getBuilder(outPath).parent(itemGenerated).texture("layer0",texturePath);
    }

}
