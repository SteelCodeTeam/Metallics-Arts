package net.rudahee.metallics_arts.data.client;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.loading.moddiscovery.ModFile;
import net.rudahee.metallics_arts.MetallicsArts;

import javax.annotation.Nonnull;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, MetallicsArts.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels(){
//        withExistingParent("steel_block", modLoc("block/steel_block"));
  //      withExistingParent("steel_ore", modLoc("block/steel_ore"));

        ModelFile itemGenerated = getExistingFile (mcLoc("item/generated"));
        builder(itemGenerated, "steel_ingot");

    }
    private ItemModelBuilder builder (ModelFile itemGenerated, String name){
        return  getBuilder(name).parent(itemGenerated).texture("layalr0",name);
    }

}
//NO SE, BUSCA POR ACA