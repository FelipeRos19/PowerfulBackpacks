package fun.felipe.powerfulbackpacks.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.plugin.Plugin;

import java.util.Arrays;

public class CraftPrepareListener implements Listener {
    final Plugin plugin;

    public CraftPrepareListener(Plugin plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onCraftInteract(PrepareItemCraftEvent event) {
        System.out.println("fui chamado!");
        System.out.println("Matrix: " + Arrays.toString(event.getInventory().getMatrix()));
    }
}
