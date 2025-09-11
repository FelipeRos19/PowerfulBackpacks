package fun.felipe.powerfulbackpacks.managers.items;

import fun.felipe.powerfulbackpacks.PowerfulBackpacks;
import fun.felipe.powerfulbackpacks.managers.items.entities.LoadItemConfig;
import fun.felipe.powerfulbackpacks.managers.items.implementation.SewingKitItem;
import fun.felipe.powerfulbackpacks.managers.items.interfaces.GenericItem;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ItemManager {
    private final PowerfulBackpacks plugin;
    private final Map<String, LoadItemConfig> registeredItems;

    public ItemManager(PowerfulBackpacks plugin) {
        this.plugin = plugin;
        this.registeredItems = new HashMap<>();
    }

    private void loadItems() {
        Optional<LoadItemConfig> sewingKit = this.loadItemFromConfig("sewing_kit");
        sewingKit.ifPresent(config -> {
            this.registeredItems.put("sewing_kit", config);
        });
    }

    @NotNull
    private Optional<LoadItemConfig> loadItemFromConfig(String itemKey) {
        ConfigurationSection itemsSection = this.plugin.getItemFile().getFileConfiguration().getConfigurationSection("Items." + itemKey);
        if (itemsSection == null) {
            this.plugin.getLogger().severe("Item file not found in Config!");
            return Optional.empty();
        }

        String itemName = itemsSection.getString("name");
        if (itemName == null) {
            this.plugin.getLogger().severe("Item name not found in Config!");
            return Optional.empty();
        }
        List<String> itemLore =  itemsSection.getStringList("lore");
        String materialString = itemsSection.getString("material");
        if (materialString == null) {
            this.plugin.getLogger().severe("Material not found in Config! (" + itemKey + ")");
            return Optional.empty();
        }

        Material material =  Material.getMaterial(materialString);
        if (material == null) {
            this.plugin.getLogger().severe("Material invalid in Config! (" + itemKey + ")");
            return Optional.empty();
        }

        ConfigurationSection craftSection = itemsSection.getConfigurationSection("craft");
        if (craftSection == null) {
            this.plugin.getLogger().severe("Craft section not found in Config!");
            return Optional.empty();
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
                        return Optional.empty();
                    }

                    Material materialResult = Material.getMaterial(materialStringKey);
                    if (materialResult == null) {
                        this.plugin.getLogger().severe("Material invalid in Config! (" + key + ")");
                        return Optional.empty();
                    }

                    craftMaterials.put(key.charAt(0), materialResult);
                }
            }
        }

        return Optional.of(new LoadItemConfig(itemName, itemLore, material, craftShape, craftMaterials));
    }
}
