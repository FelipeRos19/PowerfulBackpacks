package fun.felipe.powerfulbackpacks.manager;

import fun.felipe.powerfulbackpacks.utils.LanguageFile;
import lombok.Getter;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class LanguageManager {
    private final Plugin plugin;
    @Getter
    private final Map<String, LanguageFile> languages;
    private final File langFolder;
    @Getter
    private String pluginLanguage;
    @Getter
    private LanguageFile pluginLanguageFile;

    public LanguageManager(Plugin plugin) {
        this.plugin = plugin;
        this.languages = new HashMap<>();
        this.langFolder = new File(this.plugin.getDataFolder().getPath() + "/lang/");
        if (!this.langFolder.exists())
            if (!langFolder.mkdir())
                this.plugin.getLogger().severe("Error when creating Languages folder!");
        this.registerDefaultLanguages();
        this.registerLanguages();
        this.definePluginLanguage();
    }

    private void registerDefaultLanguages() {
        this.languages.put("en-us", new LanguageFile("lang/en-us.yml", this.plugin));
        this.languages.put("pt-br", new LanguageFile("lang/pt-br.yml", this.plugin));
    }

    private void registerLanguages() {
        this.plugin.getLogger().info("Starting language loading process.");
        File[] files = (this.langFolder != null) ? this.langFolder.listFiles() : null;
        if (files == null) return;

        for (File file : files) {
            String formattedFileName = file.getName().replace(".yml", "");
            if (this.languages.containsKey(formattedFileName)) continue;

            this.languages.put(file.getName().replace(".yml", ""), new LanguageFile("lang/" + file.getName(), this.plugin));
            this.plugin.getLogger().info("Language " + file.getName() + " successfully registered!");
        }

        this.plugin.getLogger().info("Finishing language loading process.");
    }

    private void definePluginLanguage() {
        String language = this.plugin.getConfig().getString("Language");
        if (language == null) language = "en-us";
        if (!this.getLanguages().containsKey(language)) language = "en-us";
        this.pluginLanguage = language;
        this.plugin.getLogger().info("Selected language was " + this.pluginLanguage + ".");
        this.pluginLanguageFile = this.languages.get(this.pluginLanguage);
    }
}
