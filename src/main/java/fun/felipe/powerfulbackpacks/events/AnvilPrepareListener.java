package fun.felipe.powerfulbackpacks.events;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.plugin.Plugin;

public class AnvilPrepareListener implements Listener {
    final Plugin plugin;

    public AnvilPrepareListener(Plugin plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onAnvilInteract(PrepareAnvilEvent event) {
        if (event.getInventory().getFirstItem() == null) return;
        if (!event.getInventory().getFirstItem().getType().equals(Material.BUNDLE)) return;

        event.setResult(null);
    }
}
