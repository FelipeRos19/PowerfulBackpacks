package fun.felipe.powerfulbackpacks.events;

import fun.felipe.powerfulbackpacks.gui.BackpackGUI;
import fun.felipe.powerfulbackpacks.utils.items.PersistentDataUtils;
import fun.felipe.powerfulbackpacks.utils.items.SerializationUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;

public class PlayerInteractListener implements Listener {
    final Plugin plugin;

    public PlayerInteractListener(Plugin plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getItem() == null) return;
        if (!event.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.BUNDLE)) return;
        if (!event.getAction().isRightClick()) return;
        if (!PersistentDataUtils.hasData(event.getItem(), "content")) return;
        String serializedContent = PersistentDataUtils.getStringData(event.getItem(), "content");
        Inventory inventoryContent;

        if (serializedContent.isEmpty())
            inventoryContent = Bukkit.createInventory(null, 9);
        else
            inventoryContent = SerializationUtils.inventoryFromBase64(serializedContent);

        event.getPlayer().openInventory(new BackpackGUI("<red>Mochila", 9, event.getItem(), inventoryContent).getInventory());
    }
}
