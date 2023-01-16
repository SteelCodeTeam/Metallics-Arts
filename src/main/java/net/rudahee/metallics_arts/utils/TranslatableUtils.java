package net.rudahee.metallics_arts.utils;

import net.minecraft.network.chat.Component;

public class TranslatableUtils {

    public static Component generateComponent(String... strings) {

        StringBuilder stylizedString = new StringBuilder();

        boolean firstLoop = true;
        for (String str: strings) {
            if (firstLoop) {
                stylizedString.append(str);
                firstLoop = false;
            } else {
                stylizedString.append(" " + str);
            }
        }

        return Component.translatable(stylizedString.toString());
    }

    public static Component generateComponentWithoutSpace(String... strings) {

        StringBuilder stylizedString = new StringBuilder();

        for (String str: strings) {
            stylizedString.append(str);
        }

        return Component.translatable(stylizedString.toString());
    }


}
