package fun.felipe.powerfulbackpacks.managers.items;

import fun.felipe.powerfulbackpacks.PowerfulBackpacks;
import fun.felipe.powerfulbackpacks.managers.items.entities.LoadItemConfig;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemManager {
    private final PowerfulBackpacks plugin;

    public ItemManager(PowerfulBackpacks plugin) {
        this.plugin = plugin;
    }

    private void loadItems() {

    }

    private LoadItemConfig loadItem(String itemKey) {
        ConfigurationSection itemsSection = this.plugin.getItemFile().getFileConfiguration().getConfigurationSection("Items." + itemKey);
        if (itemsSection == null) {
            this.plugin.getLogger().severe("Item file not found in Config!");
            return null;
        }

        String itemName = itemsSection.getString("name");
        List<String> itemLore =  itemsSection.getStringList("lore");
        String materialString = itemsSection.getString("material");
        if (materialString == null) {
            this.plugin.getLogger().severe("Material not found in Config! (" + itemKey + ")");
            return null;
        }

        Material material =  Material.getMaterial(materialString);
        if (material == null) {
            this.plugin.getLogger().severe("Material invalid in Config! (" + itemKey + ")");
            return null;
        }

        ConfigurationSection craftSection = itemsSection.getConfigurationSection("craft");
        if (craftSection == null) {
            this.plugin.getLogger().severe("Craft section not found in Config!");
            return null;
        }

        List<String> craftShape = craftSection.getStringList("shape");
        Map<Character, Material> craftMaterials = new HashMap<>();
        if (!craftShape.isEmpty()) {
            ConfigurationSection craftMaterialsSection = craftSection.getConfigurationSection("materials");
            if (craftMaterialsSection != null) {
                this.plugin.getLogger().severe("Material section not found in Config!");
                for (String key : craftMaterialsSection.getKeys(false)) {
                    String materialStringKey = craftMaterialsSection.getString(key);

                    if (materialStringKey == null) {
                        this.plugin.getLogger().severe("Material key not found in Config! (" + key + ")");
                        return null;
                    }

                    Material materialResult = Material.getMaterial(materialStringKey);
                    if (materialResult == null) {
                        this.plugin.getLogger().severe("Material invalid in Config! (" + key + ")");
                        return null;
                    }

                    craftMaterials.put(key.charAt(0), materialResult);
                }
            }
        }

        return new LoadItemConfig(itemName, itemLore, material, craftShape, craftMaterials);
    }
}
