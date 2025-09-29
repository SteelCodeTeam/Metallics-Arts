package net.rudahee.metallics_arts.data.providers;

import net.minecraft.ChatFormatting;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.critereon.ImpossibleTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.enums.implementations.languages.PowersAdvancementTranslation;
import net.rudahee.metallics_arts.setup.registries.ModItemsRegister;

import java.util.function.Consumer;


//todo REVISAR EL ARBOL E IDIOMA
public class ModAdvancementsGenerator implements ForgeAdvancementProvider.AdvancementGenerator {

    public void generate(HolderLookup.Provider registries,
                         Consumer<Advancement> saver,
                         ExistingFileHelper fileHelper) {

        // Advancement raíz (pestaña única)
        Advancement metallicArtsRoot = Advancement.Builder.advancement()
                .display(
                        ModItemsRegister.ITEM_ICONS_ALLOMANCY_DIVINE.get(MetalTagEnum.ATIUM.getNameLower()), // ícono
                        Component.literal("Poderes"),
                        Component.literal("Domina el poder de los metales"),
                        new ResourceLocation("minecraft:textures/gui/advancements/backgrounds/end.png"), // fondo pestaña
                        FrameType.TASK,
                        false, false, false
                )
                .addCriterion("dummy", new ImpossibleTrigger.TriggerInstance())
                .save(saver, new ResourceLocation(MetallicsArts.MOD_ID, "root"), fileHelper);


        // Padre de Alomancia
        Advancement allomancyRoot = Advancement.Builder.advancement()
                .parent(metallicArtsRoot)
                .display(
                        ModItemsRegister.ITEM_ICONS_ALLOMANCY_DIVINE.get(MetalTagEnum.ATIUM.getNameLower()),
                        Component.literal("Alomancia"),
                        Component.literal("El arte de quemar metales"),
                        null, FrameType.GOAL, true, true, false
                )
                .addCriterion("dummy", new ImpossibleTrigger.TriggerInstance())
                .save(saver, new ResourceLocation(MetallicsArts.MOD_ID, "allomancy_root"), fileHelper);
        // Padre de Feruquimia
        Advancement feruchemyRoot = Advancement.Builder.advancement()
                .parent(metallicArtsRoot)
                .display(
                        ModItemsRegister.ITEM_ICONS_FERUCHEMIC_DIVINE.get(MetalTagEnum.ATIUM.getNameLower()),
                        Component.literal("Feruquimia"),
                        Component.literal("El arte de almacenar atributos"),
                        null, FrameType.GOAL, true, true, false
                )
                .addCriterion("dummy", new ImpossibleTrigger.TriggerInstance())
                .save(saver, new ResourceLocation(MetallicsArts.MOD_ID, "feruchemy_root"), fileHelper);


        for (MetalTagEnum metal: MetalTagEnum.values()){

            if (!metal.isDivine()) {
                Advancement.Builder.advancement()
                        .parent(allomancyRoot)
                        .display(
                                ModItemsRegister.ITEM_ICONS_ALLOMANCY.get(metal.getNameLower()),                           // ícono
                                Component.translatable(PowersAdvancementTranslation.valueOf("ALLOMANTIC_"+metal.getNameUpper()).getKey()),                      // título - ENUM CON NOMBRE DE PODERES
                                Component.literal("Un clavo metálico te transfirió un poder"), // descripción
                                null,                                               // fondo (puede ser un texture RL)
                                FrameType.TASK,                                     // tipo
                                true,   // toast
                                true,   // announce
                                false   // oculto
                        )
                        .addCriterion("Poder adquirido", new ImpossibleTrigger.TriggerInstance())
                        .save(saver, new ResourceLocation(MetallicsArts.MOD_ID, "allomancy_power_"+metal.getNameLower()), fileHelper);

                Advancement.Builder.advancement()
                        .parent(feruchemyRoot)
                        .display(
                                ModItemsRegister.ITEM_ICONS_FERUCHEMIC.get(metal.getNameLower()),                           // ícono
                                Component.translatable(PowersAdvancementTranslation.valueOf("FERUCHEMICAL_"+metal.getNameUpper()).getKey()),                      // título - ENUM CON NOMBRE DE PODERE,                      // título - ENUM CON NOMBRE DE PODERES
                                Component.literal("Un clavo metálico te transfirió un poder"), // descripción
                                null,                                               // fondo (puede ser un texture RL)
                                FrameType.TASK,                                     // tipo
                                true,   // toast
                                true,   // announce
                                false   // oculto
                        )
                        .addCriterion("Poder adquirido", new ImpossibleTrigger.TriggerInstance())
                        .save(saver, new ResourceLocation(MetallicsArts.MOD_ID, "feruchemy_power_"+metal.getNameLower()), fileHelper);
            } else {
                Advancement.Builder.advancement()
                        .parent(allomancyRoot)
                        .display(
                                ModItemsRegister.ITEM_ICONS_ALLOMANCY_DIVINE.get(metal.getNameLower()),                           // ícono
                                Component.literal("################").withStyle(ChatFormatting.OBFUSCATED),                      // título - ENUM CON NOMBRE DE PODERES
                                Component.literal("Un clavo metálico te transfirió un poder"), // descripción
                                null,                                               // fondo (puede ser un texture RL)
                                FrameType.TASK,                                     // tipo
                                true,   // toast
                                true,   // announce
                                false   // oculto
                        )
                        .addCriterion("Poder adquirido", new ImpossibleTrigger.TriggerInstance())
                        .save(saver, new ResourceLocation(MetallicsArts.MOD_ID, "allomancy_power_"+metal.getNameLower()), fileHelper);
                Advancement.Builder.advancement()
                        .parent(feruchemyRoot)
                        .display(
                                ModItemsRegister.ITEM_ICONS_FERUCHEMIC_DIVINE.get(metal.getNameLower()),                           // ícono
                                Component.literal("################").withStyle(ChatFormatting.OBFUSCATED),                      // título - ENUM CON NOMBRE DE PODERES
                                Component.literal("Un clavo metálico te transfirió un poder"), // descripción
                                null,                                               // fondo (puede ser un texture RL)
                                FrameType.TASK,                                     // tipo
                                true,   // toast
                                true,   // announce
                                false   // oculto
                        )
                        .addCriterion("Poder adquirido", new ImpossibleTrigger.TriggerInstance())
                        .save(saver, new ResourceLocation(MetallicsArts.MOD_ID, "feruchemy_power_"+metal.getNameLower()), fileHelper);

            }


        }


    }
}
