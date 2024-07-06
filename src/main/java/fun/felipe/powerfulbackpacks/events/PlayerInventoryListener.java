package fun.felipe.powerfulbackpacks.events;

import fun.felipe.powerfulbackpacks.gui.BackpackGUI;
import fun.felipe.powerfulbackpacks.utils.items.PersistentDataUtils;
import fun.felipe.powerfulbackpacks.utils.items.SerializationUtils;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.plugin.Plugin;

public class PlayerInventoryListener implements Listener {
    final Plugin plugin;

    public PlayerInventoryListener(Plugin plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerInventoryInteract(InventoryClickEvent event) {
        if (event.getClickedInventory() == null) return;
        if (!event.getClick().isRightClick()) {

            if (event.getInventory().getHolder() instanceof BackpackGUI backpack) {
                if (event.getCurrentItem() != null && event.getCurrentItem().equals(backpack.getBackpack())) {
                    plugin.getLogger().warning(event.getWhoClicked().getName() + " tries to put a backpack inside itself");
                    event.setCancelled(true);
                    return;
                }
            }

            return;
        }
        if (event.getCurrentItem() == null) return;
        if (event.getCurrentItem().getType().equals(Material.BUNDLE)) event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerCloseInventory(InventoryCloseEvent event) {
        if (!(event.getInventory().getHolder() instanceof BackpackGUI backpack)) return;
        String contentSerialized = SerializationUtils.inventoryToBase64(backpack.getInventory());
        PersistentDataUtils.addStringData(backpack.getBackpack(), "content", contentSerialized);
    }
}
