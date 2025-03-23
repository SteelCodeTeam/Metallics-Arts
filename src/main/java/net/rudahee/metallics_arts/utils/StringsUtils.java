package net.rudahee.metallics_arts.utils;

import net.minecraft.network.chat.Component;

public class StringsUtils {

    public static boolean versionIsInRange(String version, String minVersion, String maxVersion) {
        int minVersionInt = Integer.valueOf(minVersion.replace(".", ""));
        int maxVersionInt = Integer.valueOf(maxVersion.replace(".", ""));
        int versionInt = Integer.valueOf(version.replace(".", ""));

        return versionInt >= minVersionInt && versionInt <= maxVersionInt;
    }
    public static boolean versionIsInRange(Integer version, String minVersion, String maxVersion) {
        int minVersionInt = Integer.valueOf(minVersion.replace(".", ""));
        int maxVersionInt = Integer.valueOf(maxVersion.replace(".", ""));

        return version >= minVersionInt && version <= maxVersionInt;
    }


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

}
