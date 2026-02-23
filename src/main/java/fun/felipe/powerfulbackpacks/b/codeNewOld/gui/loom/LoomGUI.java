package fun.felipe.powerfulbackpacks.b.codeNewOld.gui.loom;

import fun.felipe.powerfulbackpacks.PowerfulBackpacks;
import fun.felipe.powerfulbackpacks.utils.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class LoomGUI {
    /*
    private final PowerfulBackpacks plugin;
    private final Inventory inventory;
    private final List<Integer> CRAFT_SLOTS = List.of(10, 11, 12, 19, 20, 21, 28, 29, 30);
    private final int RECIPE_BOOK_SLOT = 6;
    private final int LOOM_GUI_SLOT = 7;
    private final int REINFORCE_GUI_SLOT = 8;
    private final int RESULT_ARROW_SLOT = 23;
    private final int RESULT_SLOT = 25;
    private final int SEWING_KIT_SLOT = 41;
    private final int DYE_SLOT = 43;

    public LoomGUI(PowerfulBackpacks plugin) {
        this.plugin = plugin;
        this.inventory = Bukkit.createInventory(this, 6*9, StringUtils.format("<gold><bold>Reinforced Loom"));

        this.plugin.getServer().getPluginManager().registerEvents(this, plugin);

        this.fillInventory();
    }

    public static void openInventory(Player player) {
        player.openInventory(new LoomGUI(PowerfulBackpacks.getInstance()).getInventory());
    }

    private void fillInventory() {
        for (int i = 0; i < 6*9; i++) {
            this.inventory.setItem(i, ItemStack.of(Material.GRAY_STAINED_GLASS_PANE));
        }

        for (int slot : this.CRAFT_SLOTS) {
            this.inventory.setItem(slot, ItemStack.of(Material.AIR));
        }

        this.inventory.setItem(this.RECIPE_BOOK_SLOT, ItemStack.of(Material.KNOWLEDGE_BOOK));
        this.inventory.setItem(this.LOOM_GUI_SLOT, ItemStack.of(Material.LOOM));
        this.inventory.setItem(this.REINFORCE_GUI_SLOT, ItemStack.of(Material.ANVIL));

        this.inventory.setItem(this.RESULT_ARROW_SLOT, this.plugin.getItemManager().getItem("arrow"));
        this.inventory.setItem(this.RESULT_SLOT, ItemStack.of(Material.AIR));
        this.inventory.setItem(this.SEWING_KIT_SLOT, this.plugin.getItemManager().getItem("sewing_kit_craft"));
        this.inventory.setItem(this.DYE_SLOT, this.plugin.getItemManager().getItem("dye_slot"));
    }

    @Override
    public @NotNull Inventory getInventory() {
        return this.inventory;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getInventory().getHolder() instanceof LoomGUI loomGUI)) return;


    }
     */
}
