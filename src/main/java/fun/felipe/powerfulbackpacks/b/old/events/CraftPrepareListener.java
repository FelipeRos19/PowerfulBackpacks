package fun.felipe.powerfulbackpacks.b.old.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.plugin.Plugin;

public class CraftPrepareListener implements Listener {
    final Plugin plugin;

    public CraftPrepareListener(Plugin plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onCraftInteract(PrepareItemCraftEvent event) {
        /*
        if (!event.getInventory().getType().equals(InventoryType.WORKBENCH)) return;
        RecipeEntity recipe = PowerfulBackpacks.getInstance().getCraftManager().isCustomCraft(event.getInventory().getMatrix());
        if (recipe == null) return;

        ItemStack bundle = null;
        for (int i = 0; i < event.getInventory().getMatrix().length; i++) {
            ItemStack currentItem = event.getInventory().getMatrix()[i];
            if (currentItem == null) continue;
            if (currentItem.getType().equals(Material.BUNDLE))
                bundle = event.getInventory().getMatrix()[i];
        }

        String oldContent = "";
        if (bundle != null)
            oldContent = PersistentDataUtils.getStringData(bundle, "content");

        ItemStack result = recipe.result().clone();
        PersistentDataUtils.addStringData(result, "content", oldContent);
        event.getInventory().setResult(result);
         */
    }
}
