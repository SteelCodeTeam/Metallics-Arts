package net.rudahee.metallics_arts.setup.dependency_management;

import net.rudahee.metallics_arts.data.enums.implementations.dependencies.Dependencies;
import net.rudahee.metallics_arts.modules.error_handling.utils.LoggerUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class ModClientDependencyManagement {

    public static void start() {
        boolean existsCurios = false;
        boolean existsModonomicon = false;
        boolean existsGeckolib = false;

        File modFolder = new File("mods");

        if (modFolder.isDirectory()) {
            for (File file : modFolder.listFiles()) {
                if (file.getName().contains(Dependencies.CURIOS.getName())) {
                    existsCurios = true;
                    LoggerUtils.printLogInfo("Mod: " + Dependencies.CURIOS.getName() + " already exists. Skipping download.");
                }
                if (file.getName().contains(Dependencies.MODONOMICON.getName())) {
                    existsModonomicon = true;
                    LoggerUtils.printLogInfo("Mod: " + Dependencies.MODONOMICON.getName() + " already exists. Skipping download.");
                }
                if (file.getName().contains(Dependencies.GECKOLIB.getName())) {
                    existsGeckolib = true;
                    LoggerUtils.printLogInfo("Mod: " + Dependencies.GECKOLIB.getName() + " already exists. Skipping download.");
                }
            }
        }

        if (!existsCurios) {
           downloadCurios();
        }
        if (!existsModonomicon) {
            downloadModonomicon();
        }
        if (!existsGeckolib) {
            downloadGeckolib();
        }
    }

    private static void downloadCurios() {
        try {
            LoggerUtils.printLogInfo("Downloading: " + Dependencies.CURIOS.getName());
            InputStream in = new URL(Dependencies.CURIOS.getURLDownload()).openStream();
            Files.copy(in, Paths.get("mods/" + Dependencies.CURIOS.getFileName() + "-" + Dependencies.CURIOS.getMaxVersion() + ".jar"), StandardCopyOption.REPLACE_EXISTING);
            LoggerUtils.printLogInfo("Finished: " + Dependencies.CURIOS.getName());
        } catch (IOException ex) {
            LoggerUtils.printLogFatal("Error downloading: " + Dependencies.CURIOS.getName());
            ex.printStackTrace();
        }
    }
    private static void downloadModonomicon() {
        try {
            LoggerUtils.printLogInfo("Downloading: " + Dependencies.MODONOMICON.getName());
            InputStream in = new URL(Dependencies.MODONOMICON.getURLDownload()).openStream();
            Files.copy(in, Paths.get("mods/" + Dependencies.MODONOMICON.getFileName() + "-" + Dependencies.MODONOMICON.getMaxVersion() + ".jar"), StandardCopyOption.REPLACE_EXISTING);
            LoggerUtils.printLogInfo("Finished: " + Dependencies.MODONOMICON.getName());
        } catch (IOException ex) {
            LoggerUtils.printLogFatal("Error downloading: " + Dependencies.MODONOMICON.getName());
            ex.printStackTrace();
        }
    }
    private static void downloadGeckolib() {
        try {
            LoggerUtils.printLogInfo("Downloading: " + Dependencies.GECKOLIB.getName());
            InputStream in = new URL(Dependencies.GECKOLIB.getURLDownload()).openStream();
            Files.copy(in, Paths.get("mods/" + Dependencies.GECKOLIB.getFileName() + "-" + Dependencies.GECKOLIB.getMaxVersion() + ".jar"), StandardCopyOption.REPLACE_EXISTING);
            LoggerUtils.printLogInfo("Finished: " + Dependencies.GECKOLIB.getName());
        } catch (IOException ex) {
            LoggerUtils.printLogFatal("Error downloading: " + Dependencies.GECKOLIB.getName());
            ex.printStackTrace();
        }
    }

}
