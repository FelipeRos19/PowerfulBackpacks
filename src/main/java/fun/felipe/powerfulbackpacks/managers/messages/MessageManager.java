package fun.felipe.powerfulbackpacks.managers.messages;

import fun.felipe.powerfulbackpacks.PowerfulBackpacks;

import java.util.HashMap;
import java.util.Map;

public class MessageManager {
    private final PowerfulBackpacks plugin;
    private final Map<String, String> messages;

    public MessageManager(PowerfulBackpacks plugin) {
        this.plugin = plugin;
        this.messages = new HashMap<>();
    }
}
