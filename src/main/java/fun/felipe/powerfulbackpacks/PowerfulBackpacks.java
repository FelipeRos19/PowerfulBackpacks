package fun.felipe.powerfulbackpacks;

import fun.felipe.powerfulbackpacks.commands.BackpackCommand;
import fun.felipe.powerfulbackpacks.events.*;
import fun.felipe.powerfulbackpacks.manager.CraftManager;
import fun.felipe.powerfulbackpacks.manager.MessagesManager;
import fun.felipe.powerfulbackpacks.utils.Metrics;
import fun.felipe.powerfulbackpacks.utils.StringUtils;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

public final class PowerfulBackpacks extends JavaPlugin {
    @Getter
    private static PowerfulBackpacks instance;
    @Getter
    private CraftManager craftManager;
    @Getter
    private MessagesManager messagesManager;
    @Getter
    private String pluginPermission;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        registers();
        Bukkit.getConsoleSender().sendMessage(StringUtils.format("<green>[PowerfulBackpacks] has been started successfully!"));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        HandlerList.unregisterAll(this);
        Bukkit.getConsoleSender().sendMessage(StringUtils.format("<red>[PowerfulBackpacks] was successfully deactivated!"));
    }

    private void registers() {
        saveDefaultConfig();
        commands();
        events();
        new Metrics(this, 21797);
        this.messagesManager = new MessagesManager(this);
        this.craftManager = new CraftManager(this);
        String permission = this.getConfig().getString("Permission");
        if (permission == null) permission = "powerfulbackpacks.use";
        this.pluginPermission = permission;
    }

    private void commands() {
        new BackpackCommand(this);
    }

    private void events() {
        new PlayerInteractListener(this);
        new AnvilPrepareListener(this);
        new PlayerInventoryListener(this);
        new CraftPrepareListener(this);
    }
}
