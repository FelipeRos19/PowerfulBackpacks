package fun.felipe.powerfulbackpacks.managers.items.interfaces;

import org.bukkit.Material;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.Plugin;

import java.util.List;
import java.util.Map;

public abstract class GenericCraftableItem extends GenericItem {

    public GenericCraftableItem(String itemID, String itemName, List<String> itemLore, Material itemMaterial) {
        super(itemID, itemName, itemLore, itemMaterial);
    }

    public abstract ShapedRecipe createShapedRecipe(List<String> shape, Map<Character, Material> materials, Plugin plugin);
}
