package fun.felipe.powerfulbackpacks.manager;

import fun.felipe.powerfulbackpacks.PowerfulBackpacks;
import fun.felipe.powerfulbackpacks.entities.BackpackEntity;
import fun.felipe.powerfulbackpacks.utils.StringUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextReplacementConfig;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.Plugin;
import org.intellij.lang.annotations.RegExp;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class MessagesManager {
    final Plugin plugin;
    private final Map<String, String> messages;
    private final Map<String, Function<String, Component>> placeholders;

    public MessagesManager(Plugin plugin) {
        this.plugin = plugin;
        this.messages = new HashMap<>();
        this.placeholders = new HashMap<>();
        loadMessages();
        registerPlaceholders();
    }

    private void loadMessages() {
        Bukkit.getConsoleSender().sendMessage(StringUtils.format("<green>[PowerfulBackpacks] Starting the process of loading the messages!"));

        ConfigurationSection messagesSection = this.plugin.getConfig().getConfigurationSection("Messages");
        if (messagesSection == null) {
            this.plugin.getLogger().severe("Messages not found in Config!");
            this.plugin.getServer().getPluginManager().disablePlugin(this.plugin);
            return;
        }

        for (String key : messagesSection.getKeys(false)) {
            String message = messagesSection.getString(key);
            if (message == null || message.isEmpty()) {
                this.plugin.getLogger().warning("Message %s is null!".formatted(key));
                continue;
            }

            this.messages.put(key, message);
        }
    }

    private void registerPlaceholders() {
        this.placeholders.put("%permission%", (param) -> StringUtils.format(PowerfulBackpacks.getInstance().getPluginPermission()));
        this.placeholders.put("%backpack%", StringUtils::format);
        this.placeholders.put("%backpack_name%", (param) -> {
            BackpackEntity backpack = PowerfulBackpacks.getInstance().getCraftManager().getRegisteredBackpacks().get(param);
            if (backpack == null)
                return StringUtils.format("");

            return StringUtils.format(backpack.name());
        });
        this.placeholders.put("%backpack_rows%", (param) -> {
            BackpackEntity backpack = PowerfulBackpacks.getInstance().getCraftManager().getRegisteredBackpacks().get(param);
            if (backpack == null)
                return StringUtils.format("");

            return StringUtils.format(String.valueOf(backpack.rows()));
        });
        this.placeholders.put("%backpack_slots%", (param) -> {
            BackpackEntity backpack = PowerfulBackpacks.getInstance().getCraftManager().getRegisteredBackpacks().get(param);
            if (backpack == null)
                return StringUtils.format("");

            return StringUtils.format(String.valueOf(backpack.rows() * 9));
        });
    }

    public Component createMessage(String messageKey, String param) {
        if (!this.messages.containsKey(messageKey))
            return StringUtils.format("<red>Message Not Found!");

        String message = this.messages.get(messageKey);
        Component formattedMessage = StringUtils.format(message);

        for (@RegExp String key : this.placeholders.keySet()) {
            if (!message.contains(key)) continue;
            Component placeholder = this.placeholders.get(key).apply(param);
            formattedMessage = formattedMessage.replaceText(TextReplacementConfig.builder()
                    .match(key)
                    .replacement(placeholder)
                    .build());
        }

        return formattedMessage;
    }
}
