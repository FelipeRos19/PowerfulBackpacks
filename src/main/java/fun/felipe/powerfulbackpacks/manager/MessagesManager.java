package fun.felipe.powerfulbackpacks.manager;

import fun.felipe.powerfulbackpacks.PowerfulBackpacks;
import fun.felipe.powerfulbackpacks.entities.BackpackEntity;
import fun.felipe.powerfulbackpacks.placeholder.Placeholder;
import fun.felipe.powerfulbackpacks.placeholder.implementations.BackpackPlaceholder;
import fun.felipe.powerfulbackpacks.placeholder.implementations.ItemPlaceholder;
import fun.felipe.powerfulbackpacks.placeholder.implementations.MessagePlaceholder;
import fun.felipe.powerfulbackpacks.utils.StringUtils;
import fun.felipe.powerfulbackpacks.utils.items.PersistentDataUtils;
import fun.felipe.powerfulbackpacks.utils.items.SerializationUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextReplacementConfig;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.intellij.lang.annotations.RegExp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class MessagesManager {
    final Plugin plugin;
    private final Map<String, String> messages;
    private final Map<String, Function<Placeholder<?>, Component>> placeholders;

    public MessagesManager(Plugin plugin) {
        this.plugin = plugin;
        this.messages = new HashMap<>();
        this.placeholders = new HashMap<>();
        this.loadMessages();
        this.registerPlaceholders();
    }

    private void loadMessages() {
        this.plugin.getLogger().info("Starting the messages loading process");

        ConfigurationSection messagesSection = PowerfulBackpacks.getInstance().getLanguageManager().getPluginLanguageFile().getFileConfiguration().getConfigurationSection("Messages");
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
        this.placeholders.put("%backpack%", (param) -> {
            if (param instanceof MessagePlaceholder message) {
                return StringUtils.format(message.getPlaceholder());
            }

            if (param instanceof BackpackPlaceholder backpack) {
                return StringUtils.format(backpack.getPlaceholder().key());
            }

            return StringUtils.format("<red>Placeholder type error!");
        });
        this.placeholders.put("%backpack_name%", (param) -> {
            if (param instanceof MessagePlaceholder message) {
                return StringUtils.format(message.getPlaceholder());
            }

            if (param instanceof BackpackPlaceholder backpack) {
                return StringUtils.format(backpack.getPlaceholder().name());
            }

            return StringUtils.format("<red>Placeholder type error!");
        });
        this.placeholders.put("%backpack_rows%", (param) -> {
            if (param instanceof MessagePlaceholder message) {
                return StringUtils.format(message.getPlaceholder());
            }

            if (param instanceof BackpackPlaceholder backpack) {
                return StringUtils.format(String.valueOf(backpack.getPlaceholder().rows()));
            }

            return StringUtils.format("<red>Placeholder type error!");
        });
        this.placeholders.put("%backpack_slots%", (param) -> {
            if (param instanceof MessagePlaceholder message) {
                return StringUtils.format(message.getPlaceholder());
            }

            if (param instanceof BackpackPlaceholder backpack) {
                return StringUtils.format(String.valueOf(backpack.getPlaceholder().rows() * 9));
            }

            return StringUtils.format("<red>Placeholder type error!");
        });
        this.placeholders.put("%backpack_count_items%", (param) -> {
            if (param instanceof MessagePlaceholder message) {
                return StringUtils.format(message.getPlaceholder());
            }

            if (param instanceof ItemPlaceholder item) {
                if (!PersistentDataUtils.hasData(item.getPlaceholder(), "content")) return StringUtils.format("<red>Placeholder Item Error!");
                String serializedContent = PersistentDataUtils.getStringData(item.getPlaceholder(), "content");
                Inventory inventoryContent;

                if (serializedContent.isEmpty())
                    return StringUtils.format("0");
                else
                    inventoryContent = SerializationUtils.inventoryFromBase64(serializedContent);

                int count = 0;
                for (ItemStack itemStack : inventoryContent.getContents()) {
                    if (itemStack == null) continue;
                    count+= itemStack.getAmount();
                }

                return StringUtils.format(String.valueOf(count));
            }

            return StringUtils.format("<red>Placeholder type error!");
        });
    }

    public Component createMessage(String messageKey, Placeholder<?> param) {
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

    public List<Component> createLore(BackpackEntity backpack) {
        List<Component> formattedLore = new ArrayList<>();
        if (backpack.lore().isEmpty())
            return formattedLore;

        for (String line : backpack.lore()) {
            for (@RegExp String key : this.placeholders.keySet()) {
                Component formattedLine = StringUtils.format(line);
                if (!line.contains(key)) continue;
                Component placeholder = this.placeholders.get(key).apply(new BackpackPlaceholder(backpack));
                formattedLine = formattedLine.replaceText(TextReplacementConfig.builder()
                        .match(key)
                        .replacement(placeholder)
                        .build());

                formattedLore.add(formattedLine.decoration(TextDecoration.ITALIC, false));
            }
        }

        return formattedLore;
    }
}
