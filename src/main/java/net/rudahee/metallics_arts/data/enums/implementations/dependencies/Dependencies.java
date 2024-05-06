package net.rudahee.metallics_arts.data.enums.implementations.dependencies;

import net.rudahee.metallics_arts.data.enums.interfaces.Dependency;

public enum Dependencies implements Dependency {
    CURIOS("curios", "curios-forge-1.19.2","5.1.6.0", "5.1.6.2", "https://www.curseforge.com/api/v1/mods/309927/files/5302436/download"),
    GECKOLIB("geckolib", "geckolib-forge-1.19","3.1.40", "3.1.40", "https://www.curseforge.com/api/v1/mods/388172/files/4407241/download"),
    MODONOMICON("modonomicon", "modonomicon-1.19.2","1.33.0", "1.34.0", "https://www.curseforge.com/api/v1/mods/538392/files/4675974/download");

    private final String name;
    private final String fileName;
    private final String minVersion;
    private final String maxVersion;
    private final String urlDownload;

    Dependencies(String name, String fileName, String minVersion, String maxVersion, String urlDownload) {
        this.name = name;
        this.fileName = fileName;
        this.minVersion = minVersion;
        this.maxVersion = maxVersion;
        this.urlDownload = urlDownload;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getMinVersion() {
        return minVersion;
    }

    @Override
    public String getMaxVersion() {
        return maxVersion;
    }

    @Override
    public String getURLDownload() {
        return urlDownload;
    }


    public String getFileName() {
        return fileName;
    }

    @Override
    public String toString() {
        return "CuriosDependency{" +
                "name='" + name + '\'' +
                ", fileName='" + fileName + '\'' +
                ", minVersion='" + minVersion + '\'' +
                ", maxVersion='" + maxVersion + '\'' +
                ", urlDownload='" + urlDownload + '\'' +
                '}';
    }
}
