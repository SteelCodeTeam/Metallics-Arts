package net.rudahee.metallics_arts.utils;

import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.modules.error_handling.utils.LoggerUtils;

public class MetalTagsUtils {

    public static MetalTagEnum getMetalTagEnumByString(String name) {
        if (name.contains("iron")) {
            return MetalTagEnum.IRON;
        } else if (name.contains("steel")) {
            return MetalTagEnum.STEEL;
        } else if (name.contains("tin")) {
            return MetalTagEnum.TIN;
        } else if (name.contains("pewter")) {
            return MetalTagEnum.PEWTER;
        } else if (name.contains("zinc")) {
            return MetalTagEnum.ZINC;
        } else if (name.contains("brass")) {
            return MetalTagEnum.BRASS;
        } else if (name.contains("copper")) {
            return MetalTagEnum.COPPER;
        } else if (name.contains("bronze")) {
            return MetalTagEnum.BRONZE;
        } else if (name.contains("aluminum")) {
            return MetalTagEnum.ALUMINUM;
        } else if (name.contains("duralumin")) {
            return MetalTagEnum.DURALUMIN;
        } else if (name.contains("chromium")) {
            return MetalTagEnum.CHROMIUM;
        } else if (name.contains("nicrosil")) {
            return MetalTagEnum.NICROSIL;
        } else if (name.contains("gold")) {
            return MetalTagEnum.GOLD;
        } else if (name.contains("electrum")) {
            return MetalTagEnum.ELECTRUM;
        } else if (name.contains("cadmium")) {
            return MetalTagEnum.CADMIUM;
        } else if (name.contains("bendalloy")) {
            return MetalTagEnum.BENDALLOY;
        } else if (name.contains("atium")) {
            return MetalTagEnum.ATIUM;
        } else if (name.contains("malatium")) {
            return MetalTagEnum.MALATIUM;
        } else if (name.contains("lerasium")) {
            return MetalTagEnum.LERASIUM;
        } else if (name.contains("ettmetal")) {
            return MetalTagEnum.ETTMETAL;
        } else {
            LoggerUtils.printLogError("Se está tratando de conseguir un MetalTagEnum desde un String no valido: " + name);
            return null;
        }
    }
}
