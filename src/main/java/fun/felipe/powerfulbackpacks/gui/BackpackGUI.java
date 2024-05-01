package fun.felipe.powerfulbackpacks.gui;

import lombok.Getter;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class BackpackGUI implements InventoryHolder {
    private final Inventory inventory;
    @Getter
    private final ItemStack backpack;

    public BackpackGUI(Component title, int slots, ItemStack itemStack, Inventory content) {
        this.backpack = itemStack;
        this.inventory = Bukkit.createInventory(this, slots, title);
        this.inventory.setContents(content.getContents());
    }

    /**
     * Get the object's inventory.
     *
     * @return The inventory.
     */
    @Override
    public @NotNull Inventory getInventory() {
        return this.inventory;
    }
}
