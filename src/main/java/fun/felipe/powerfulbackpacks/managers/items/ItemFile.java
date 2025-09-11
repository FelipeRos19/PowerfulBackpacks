package fun.felipe.powerfulbackpacks.managers.items;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;

public class ItemFile {
    private final String fileName;
    private final Plugin plugin;
    private FileConfiguration fileConfiguration;

    public ItemFile(String fileName, Plugin plugin) {
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

    public FileConfiguration getFileConfiguration() {
        return fileConfiguration;
    }
}
