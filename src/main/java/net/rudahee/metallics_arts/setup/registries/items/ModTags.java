package net.rudahee.metallics_arts.setup.registries.items;

import it.unimi.dsi.fastutil.Hash;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.data.enums.implementations.GemsEnum;
import net.rudahee.metallics_arts.data.enums.implementations.MetalEnum;
import net.rudahee.metallics_arts.data.enums.implementations.languages.MetalAuxiliaryInfo;
import org.openjdk.nashorn.internal.ir.Block;

import javax.swing.text.html.HTML;
import java.util.HashMap;

public class ModTags {

    public static final HashMap<String, TagKey<Item>> NUGGETS = new HashMap<>();
    public static final HashMap<String, TagKey<Item>> INGOTS = new HashMap<>();
    public static final HashMap<String, TagKey<Item>> RAWS = new HashMap<>();




    static {
        for (MetalEnum metal : MetalEnum.values()) {
            NUGGETS.put(metal.getMetalNameLower(), forgeTag("nuggets/" + metal.getMetalNameLower()));
            INGOTS.put(metal.getMetalNameLower(), forgeTag("ingots/" + metal.getMetalNameLower()));

        }
        NUGGETS.put("copper", forgeTag("nuggets/copper"));

        for (GemsEnum gem : GemsEnum.values()) {
            NUGGETS.put(gem.getGemNameLower(), forgeTag("nuggets/" + gem.getGemNameLower()));
            INGOTS.put(gem.getGemNameLower(), forgeTag("gems/" + gem.getGemNameLower()));
        }

        for (MetalAuxiliaryInfo metal : MetalAuxiliaryInfo.values()) {
            if (!metal.isVanilla() && !metal.isAlloy() && !metal.isDivine()) {
                RAWS.put(metal.getId(),forgeTag("raws_materials/" + metal.getId()));
            }
        }


    }

    private static TagKey<Item> tag(String name)
    {
        return ItemTags.create(new ResourceLocation(MetallicsArts.MOD_ID, name));
    }

    private static TagKey<Item> forgeTag(String name)
    {
        return ItemTags.create(new ResourceLocation("forge", name));
    }
}
