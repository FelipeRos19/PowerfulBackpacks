package fun.felipe.powerfulbackpacks.events;

import fun.felipe.powerfulbackpacks.PowerfulBackpacks;
import fun.felipe.powerfulbackpacks.utils.chunk.ChunkPersistentDataUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class BlockBreakListener implements Listener {
    private final Plugin plugin;

    public BlockBreakListener(Plugin plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (!event.getBlock().getType().equals(Material.LOOM)) return;

        Location blockLocation = event.getBlock().getLocation();
        String locationKey = "reinforced." + blockLocation.getBlockX() + "." + blockLocation.getBlockY() + "." + blockLocation.getBlockZ();
        if (!ChunkPersistentDataUtils.hasBooleanData(blockLocation.getChunk(), locationKey)) return;

        event.setDropItems(false);
        ItemStack reinforcedLoom = PowerfulBackpacks.getInstance().getItemsManager().getItem("reinforced_loom");
        if (reinforcedLoom == null) {
            this.plugin.getLogger().warning("[PowerfulBackpacks] error while get loom in block break event!");
            return;
        }

        blockLocation.getWorld().dropItemNaturally(blockLocation, reinforcedLoom);
    }
}
