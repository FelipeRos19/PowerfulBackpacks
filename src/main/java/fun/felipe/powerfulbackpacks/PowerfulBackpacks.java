package fun.felipe.powerfulbackpacks;

import de.tr7zw.changeme.nbtapi.NBT;
import fun.felipe.powerfulbackpacks.commands.BackpackCommand;
import fun.felipe.powerfulbackpacks.commands.TestCommand;
import fun.felipe.powerfulbackpacks.managers.items.ItemFile;
import fun.felipe.powerfulbackpacks.managers.items.ItemManager;
import fun.felipe.powerfulbackpacks.managers.messages.MessageFile;
import fun.felipe.powerfulbackpacks.managers.messages.MessageManager;
import fun.felipe.powerfulbackpacks.utils.Metrics;
import fun.felipe.powerfulbackpacks.utils.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

public final class PowerfulBackpacks extends JavaPlugin {

    private static PowerfulBackpacks instance;
    private MessageFile messageFile;
    private MessageManager messageManager;
    private ItemFile itemFile;
    private ItemManager itemManager;
    /*
    @Getter
    private CraftManager craftManager;
     */
    private String pluginPermission;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        registers();
        Bukkit.getConsoleSender().sendMessage(StringUtils.format("<green>[PowerfulBackpacks] has been started successfully!"));

        if (!NBT.preloadApi()) {
            System.out.println("KRL DEU MERDA AQUI EM!");
            this.getServer().getPluginManager().disablePlugin(this);
        }
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

        String permission = this.getConfig().getString("Permission");
        if (permission == null) permission = "powerfulbackpacks.use";
        this.pluginPermission = permission;

        this.messageFile = new MessageFile("messages.yml", this);
        this.messageManager = new MessageManager(instance);

        this.itemFile = new ItemFile("items.yml", this);
        this.itemManager = new ItemManager(instance);

        //this.craftManager = new CraftManager(this);
    }

    private void commands() {
        new BackpackCommand(this);
        new TestCommand(this);
    }

    private void events() {
    }

    public static PowerfulBackpacks getInstance() {
        return instance;
    }

    public String getPluginPermission() {
        return this.pluginPermission;
    }

    public MessageManager getMessageManager() {
        return this.messageManager;
    }

    public MessageFile getMessageFile() {
        return this.messageFile;
    }

    public ItemFile getItemFile() {
        return this.itemFile;
    }
}
