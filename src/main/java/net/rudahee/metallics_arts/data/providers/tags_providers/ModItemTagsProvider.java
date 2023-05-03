package net.rudahee.metallics_arts.data.providers.tags_providers;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.rudahee.metallics_arts.data.enums.implementations.GemsEnum;
import net.rudahee.metallics_arts.data.enums.implementations.MetalEnum;
import net.rudahee.metallics_arts.data.enums.implementations.languages.MetalAuxiliaryInfo;
import net.rudahee.metallics_arts.setup.registries.ModItemsRegister;
import net.rudahee.metallics_arts.setup.registries.items.ModTags;
import org.jetbrains.annotations.Nullable;

public class ModItemTagsProvider extends ItemTagsProvider {
    public ModItemTagsProvider(DataGenerator dataGenerator, BlockTagsProvider blockTagsProvider, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(dataGenerator, blockTagsProvider, modId, existingFileHelper);
    }

    @Override
    protected void addTags() {
        for (MetalEnum metal : MetalEnum.values()) {
            tag(ModTags.NUGGETS.get(metal.getMetalNameLower())).add(ModItemsRegister.ITEM_METAL_NUGGET.get(metal.getMetalNameLower()));
            tag(ModTags.INGOTS.get(metal.getMetalNameLower())).add(ModItemsRegister.ITEM_METAL_INGOT.get(metal.getMetalNameLower()));
        }
        for (GemsEnum gemsEnum : GemsEnum.values()) {
            tag(ModTags.NUGGETS.get(gemsEnum.getGemNameLower())).add(ModItemsRegister.ITEM_GEMS_BASE.get(gemsEnum.getGemNameLower()));
            tag(ModTags.INGOTS.get(gemsEnum.getGemNameLower())).add(ModItemsRegister.ITEM_GEMS_NUGGET.get(gemsEnum.getGemNameLower()));
        }
        for (MetalAuxiliaryInfo metal : MetalAuxiliaryInfo.values()) {
            if (!metal.isDivine() && !metal.isAlloy() && !metal.isVanilla()){
                tag(ModTags.RAWS.get(metal.getId())).add(ModItemsRegister.ITEM_RAW_METAL.get(metal.getId()));
            }

            //ores
            //raws
        }

        tag(ModTags.NUGGETS.get("copper")).add(ModItemsRegister.ITEM_METAL_NUGGET.get("copper_nugget")).replace(false);

    }
}
