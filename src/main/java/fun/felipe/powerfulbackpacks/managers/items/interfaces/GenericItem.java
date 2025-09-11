package fun.felipe.powerfulbackpacks.managers.items.interfaces;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.List;


public abstract class GenericItem {
    private final String itemID;
    private final String itemName;
    private final List<String> itemLore;
    private final Material itemMaterial;

    public GenericItem(String itemID, String itemName, List<String> itemLore, Material itemMaterial) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.itemLore = itemLore;
        this.itemMaterial = itemMaterial;
    }

    public String getItemID() {
        return itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public List<String> getItemLore() {
        return itemLore;
    }

    public Material getItemMaterial() {
        return itemMaterial;
    }

    public abstract ItemStack createItemStack();


}
