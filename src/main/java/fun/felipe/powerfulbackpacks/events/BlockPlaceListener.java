package fun.felipe.powerfulbackpacks.events;

import fun.felipe.powerfulbackpacks.utils.chunk.ChunkPersistentDataUtils;
import fun.felipe.powerfulbackpacks.utils.items.ItemPersistentDataUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.Plugin;

public class BlockPlaceListener implements Listener {
    private final Plugin plugin;

    public BlockPlaceListener(Plugin plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (event.getBlockPlaced().getType() != Material.LOOM) return;
        if (!ItemPersistentDataUtils.hasData(event.getItemInHand(), "reinforced")) return;

        Location blockLocation = event.getBlockPlaced().getLocation();
        String locationKey = "reinforced." + blockLocation.getBlockX() + "." + blockLocation.getBlockY() + "." + blockLocation.getBlockZ();
        ChunkPersistentDataUtils.addBooleanData(blockLocation.getChunk(), locationKey, true);
    }
}
