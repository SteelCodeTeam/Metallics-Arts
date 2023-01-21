package net.rudahee.metallics_arts.utils;

import net.minecraft.world.item.Item;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.data.enums.implementations.MetalEnum;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.enums.implementations.custom_items.MetalMindEnum;
import net.rudahee.metallics_arts.data.enums.implementations.custom_items.SpikeEnum;
import net.rudahee.metallics_arts.modules.custom_items.metal_spikes.MetalSpike;
import net.rudahee.metallics_arts.setup.registries.ModItemsRegister;

import java.util.ArrayList;

public class GetItemsUtils {

    public static ArrayList<String> getRingList(){

        ArrayList<String> ringList = new ArrayList<>();
        for (MetalMindEnum metalMindEnum: MetalMindEnum.values()){
            ringList.add(MetallicsArts.MOD_ID + "_ring_" + metalMindEnum.getFirstMetal() + "_" + metalMindEnum.getSecondMetal());
            ringList.add(MetallicsArts.MOD_ID + "_ring_" + metalMindEnum.getFirstMetal() + "_" + metalMindEnum.getSecondMetal() + "_2");
        }
        return ringList;
    }
    public static ArrayList<String> getBandList(){

        ArrayList<String> bandList = new ArrayList<>();
        for (MetalMindEnum metalMindEnum: MetalMindEnum.values()){
            bandList.add(MetallicsArts.MOD_ID + "_band_" + metalMindEnum.getFirstMetal() + "_" + metalMindEnum.getSecondMetal());
            bandList.add(MetallicsArts.MOD_ID + "_band_" + metalMindEnum.getFirstMetal() + "_" + metalMindEnum.getSecondMetal() + "_2");
        }
        return bandList;
    }

    public static ArrayList<String> getSpikesList(){

        ArrayList<String> spikeList = new ArrayList<>();
        for (MetalTagEnum metal: MetalTagEnum.values()){
            spikeList.add(MetallicsArts.MOD_ID + "_spike_" + metal.getNameLower());
        }
        return spikeList;
    }

    public static ArrayList<String> getVialsList(){

        ArrayList<String> vialsList = new ArrayList<>();
        vialsList.add("allomantic_small_vial");
        vialsList.add("allomantic_large_vial");

        return vialsList;
    }

    public static ArrayList<String> getIconsList(){

        ArrayList<String> iconsList = new ArrayList<>();

        for (MetalTagEnum metal: MetalTagEnum.values()){
            if (!metal.isDivine()) {
                iconsList.add(metal.getNameLower() + "_allomantic_icon");
                iconsList.add(metal.getNameLower() + "_feruchemic_icon");
            }
        }
        return iconsList;
    }

    public static ArrayList<String> getAlloysList() {
        ArrayList<String> alloyList = new ArrayList<>();

        for (MetalEnum metal: MetalEnum.values()){
            if (metal.isAlloy()){
                alloyList.add(MetallicsArts.MOD_ID + "_" + metal.getMetalNameLower() + "_alloy_craft");
            }
        }
        //alloyList.add(MetallicsArts.MOD_ID + "_malatium_alloy_craft");
        return alloyList;
    }

    public static ArrayList<String> getPatterns() {
        ArrayList<String> patternsList = new ArrayList<>();
        for (MetalTagEnum metal: MetalTagEnum.values()){
            patternsList.add(MetallicsArts.MOD_ID + "_a_" + metal.getNameLower() + "_pattern");
            patternsList.add(MetallicsArts.MOD_ID + "_f_" + metal.getNameLower() + "_pattern");
        }
        return patternsList;
    }
}
