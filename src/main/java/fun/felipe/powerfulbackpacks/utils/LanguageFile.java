package fun.felipe.powerfulbackpacks.utils;

import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;

public class LanguageFile {
    private final String fileName;
    private final Plugin plugin;
    @Getter
    private FileConfiguration fileConfiguration;

    public LanguageFile(String fileName, Plugin plugin) {
        this.fileName = fileName;
        this.plugin = plugin;
        this.setupFile();
    }

    private void setupFile() {
        if (!this.plugin.getDataFolder().exists()) this.plugin.getDataFolder().mkdir();

        File file = new File(this.plugin.getDataFolder(), this.fileName);

        if (!file.exists()) {
            try {
                this.plugin.saveResource(this.fileName, false);
            } catch (Exception exception) {
                this.plugin.getLogger().severe("Error while setup Language File: " + this.fileName);
                exception.printStackTrace(System.err);
            }
        }

        this.fileConfiguration = YamlConfiguration.loadConfiguration(file);
    }
}
