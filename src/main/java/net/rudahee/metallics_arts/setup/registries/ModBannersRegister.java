package net.rudahee.metallics_arts.setup.registries;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BannerPatternItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;

import java.util.HashMap;

public class ModBannersRegister {
    public static final DeferredRegister<BannerPattern> BANNER_PATTERN =
            DeferredRegister.create(Registries.BANNER_PATTERN, MetallicsArts.MOD_ID);

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MetallicsArts.MOD_ID);
    public static final HashMap <String,RegistryObject<BannerPattern>> PATTERNS = new HashMap<>();
    public static final HashMap <String,TagKey<BannerPattern>> PATTERNS_KEYS = new HashMap<>();
    public static final HashMap <String, RegistryObject<BannerPatternItem>> PATTERN_ITEMS = new HashMap<>();

    static {
        for (MetalTagEnum metal : MetalTagEnum.values()) {
            RegistryObject<BannerPattern> pattern;
            TagKey<BannerPattern> bannerPatternTagKey;
            RegistryObject<BannerPatternItem> bannerPatternItem;
            //Drawing patterns
            pattern = BANNER_PATTERN.register("a_"+metal.getNameLower()+"_1", () -> new BannerPattern(MetallicsArts.MOD_ID + "_a_" + metal.getNameLower()+"_1"));
            PATTERNS.put("a_"+metal.getNameLower(),pattern);
            pattern = BANNER_PATTERN.register("a_"+metal.getNameLower()+"_2", () -> new BannerPattern(MetallicsArts.MOD_ID + "_a_" + metal.getNameLower()+"_2"));
            PATTERNS.put("a_"+metal.getNameLower()+"_2",pattern);

            pattern = BANNER_PATTERN.register("f_"+metal.getNameLower()+"_1", () -> new BannerPattern(MetallicsArts.MOD_ID + "_f_" + metal.getNameLower()+"_1"));
            PATTERNS.put("f_"+metal.getNameLower(),pattern);
            pattern = BANNER_PATTERN.register("f_"+metal.getNameLower()+"_2", () -> new BannerPattern(MetallicsArts.MOD_ID + "_f_" + metal.getNameLower()+"_2"));
            PATTERNS.put("f_"+metal.getNameLower()+"_2",pattern);

            //Tag that associates patterns with item that contains them
            bannerPatternTagKey = TagKey.create(Registries.BANNER_PATTERN, new ResourceLocation(MetallicsArts.MOD_ID, "a_"+metal.getNameLower()));
            PATTERNS_KEYS.put("a_"+metal.getNameLower(), bannerPatternTagKey);

            bannerPatternTagKey = TagKey.create(Registries.BANNER_PATTERN, new ResourceLocation(MetallicsArts.MOD_ID, "f_"+metal.getNameLower()));
            PATTERNS_KEYS.put("f_"+metal.getNameLower(), bannerPatternTagKey);

            //Item pattern for loom
            bannerPatternItem = ITEMS.register("a_"+metal.getNameLower() + "_pattern", () -> new BannerPatternItem(PATTERNS_KEYS.get("a_"+metal.getNameLower()), new Item.Properties().stacksTo(1)));
            PATTERN_ITEMS.put("a_"+metal.getNameLower(),bannerPatternItem);

            bannerPatternItem = ITEMS.register("f_"+metal.getNameLower() + "_pattern", () -> new BannerPatternItem(PATTERNS_KEYS.get("f_"+metal.getNameLower()), new Item.Properties().stacksTo(1)));
            PATTERN_ITEMS.put("f_"+metal.getNameLower(),bannerPatternItem);

        }
    }

    public static void register() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        BANNER_PATTERN.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
