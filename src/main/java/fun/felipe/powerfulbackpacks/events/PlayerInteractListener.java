package fun.felipe.powerfulbackpacks.events;

import fun.felipe.powerfulbackpacks.PowerfulBackpacks;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractListener implements Listener {
    final PowerfulBackpacks plugin;

    public PlayerInteractListener(PowerfulBackpacks plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getClickedBlock() == null) return;
        if (!event.getAction().isRightClick() && event.getClickedBlock().getBlockData().getMaterial().equals(Material.LOOM)) return;
    }
}
