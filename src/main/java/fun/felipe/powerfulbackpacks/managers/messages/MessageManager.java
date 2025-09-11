package fun.felipe.powerfulbackpacks.managers.messages;

import fun.felipe.powerfulbackpacks.PowerfulBackpacks;
import fun.felipe.powerfulbackpacks.managers.messages.placeholder.Placeholder;
import fun.felipe.powerfulbackpacks.managers.messages.placeholder.implementation.MessagePlaceholder;
import fun.felipe.powerfulbackpacks.utils.StringUtils;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.intellij.lang.annotations.RegExp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageManager {
    private final PowerfulBackpacks plugin;
    private final Map<String, String> messages;
    private final Map<String, Placeholder<?>> placeholders;

    public MessageManager(PowerfulBackpacks plugin) {
        this.plugin = plugin;
        this.messages = new HashMap<>();
        this.placeholders = new HashMap<>();
        this.loadMessages();
        this.registerPlaceholders();
    }

    private void loadMessages() {
        Bukkit.getConsoleSender().sendMessage(StringUtils.format("<green>[PowerfulBackpacks] Starting the process of loading the messages!"));
        ConfigurationSection messagesSection = this.plugin.getMessageFile().getFileConfiguration().getConfigurationSection("Messages");
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
        this.placeholders.put("%permission%", new MessagePlaceholder("%permission%", this.plugin.getPluginPermission()));
    }

    public Component formatCommandMessage(String messageKey) {
        if (!this.messages.containsKey(messageKey))
            return StringUtils.format("<red>Message Not Found!");

        String message = this.messages.get(messageKey);
        for (@RegExp String key : this.placeholders.keySet()) {
            if (!message.contains(key)) continue;
            MessagePlaceholder placeholder = (MessagePlaceholder) this.placeholders.get(key);
            message = placeholder.replace(message);
        }
        return StringUtils.format(message);
    }

    //TODO: ALTERAR OBJECT PARA ENTIDADE DA BACKPACK!
    public Component formatBackpackMessage(String messageKey, Object backpack) {
        return null;
    }

    //TODO: ALTERAR OBJECT PARA ENTIDADE DA BACKPACK!
    public List<Component> formatBackpackLore(Object backpack) {
        return null;
    }
}
