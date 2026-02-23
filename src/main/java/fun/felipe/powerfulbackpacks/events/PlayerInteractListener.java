package fun.felipe.powerfulbackpacks.events;

import fun.felipe.powerfulbackpacks.utils.chunk.ChunkPersistentDataUtils;
import fun.felipe.powerfulbackpacks.views.loom.LoomView;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;

public class PlayerInteractListener implements Listener {
    private final Plugin plugin;

    public PlayerInteractListener(Plugin plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    //TODO: REFATORAR E ADICIONAR INTERAÇÃO DA MOCHILA
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (!event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) return;
        if (event.getClickedBlock() == null) return;
        if (!event.getClickedBlock().getType().equals(Material.LOOM)) return;

        Location blockLocation = event.getClickedBlock().getLocation();
        String locationKey = "reinforced." + blockLocation.getBlockX() + "." + blockLocation.getBlockY() + "." + blockLocation.getBlockZ();
        if (!ChunkPersistentDataUtils.hasBooleanData(blockLocation.getChunk(), locationKey)) return;

        event.setCancelled(true);
        new LoomView().openInventory(event.getPlayer());
    }
}
