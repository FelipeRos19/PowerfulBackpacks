package fun.felipe.powerfulbackpacks.b.old.manager;

public class MessagesManager {
    /*
    final Plugin plugin;
    private final Map<String, String> messages;
    private final Map<String, Function<Placeholder<?>, Component>> placeholders;

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
     */
}
