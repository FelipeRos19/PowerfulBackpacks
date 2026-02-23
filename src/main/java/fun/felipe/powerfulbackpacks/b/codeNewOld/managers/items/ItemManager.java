package fun.felipe.powerfulbackpacks.b.codeNewOld.managers.items;

import fun.felipe.powerfulbackpacks.PowerfulBackpacks;
import fun.felipe.powerfulbackpacks.utils.items.ItemUtils;
import fun.felipe.powerfulbackpacks.utils.items.ItemPersistentDataUtils;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemManager {
    /*
    private final PowerfulBackpacks plugin;
    private final Map<String, ItemStack> registeredItems;

    public ItemManager(PowerfulBackpacks plugin) {
        this.plugin = plugin;
        this.registeredItems = new HashMap<>();
        this.loadItems();
    }

    private void loadItems() {
        ConfigurationSection itemsSection = this.plugin.getItemFile().getFileConfiguration().getConfigurationSection("Items");
        if (itemsSection == null) {
            this.plugin.getLogger().warning("[PowerfulBackpacks] Could not load items section!");
            return;
        }

        NamespacedKey customItemKey = ItemPersistentDataUtils.buildKey("custom-item");

        for (String key : itemsSection.getKeys(false)) {
            ConfigurationSection internalItemSection  = itemsSection.getConfigurationSection(key);
            if (internalItemSection == null) {
                this.plugin.getLogger().warning("[PowerfulBackpacks] Could not load item " + key + " section!");
                continue;
            }

            String itemName = internalItemSection.getString("name");

            List<String> itemLore = internalItemSection.getStringList("lore");

            String itemMaterialName =  internalItemSection.getString("material");
            if (itemMaterialName == null)
                itemMaterialName = "PAPER";

            Material itemMaterial = Material.getMaterial(itemMaterialName);
            if (itemMaterial == null)
                itemMaterial = Material.PAPER;

            ItemStack customItem;

            String textureKey = internalItemSection.getString("texture");
            if (textureKey == null || textureKey.isEmpty())
                customItem = ItemUtils.createCustomItem(itemMaterial, itemName, itemLore, key);
            else
                customItem = ItemUtils.createCustomItem(itemMaterial, itemName, itemLore, textureKey, key);

            ConfigurationSection craftItemSection = internalItemSection.getConfigurationSection("craft");
            if (craftItemSection != null) {
                List<String> shape = craftItemSection.getStringList("shape");
                if (shape.size() != 3) {
                    this.plugin.getLogger().severe("[PowerfulBackpacks] Could not load craft item.");
                    continue;
                }

                ShapedRecipe recipe = new ShapedRecipe(customItemKey, customItem);
                recipe.shape(shape.get(0), shape.get(1), shape.get(2));

                ConfigurationSection craftMaterialSection = craftItemSection.getConfigurationSection("materials");
                if (craftMaterialSection == null) {
                    this.plugin.getLogger().severe("[PowerfulBackpacks] Could not load craft materials.");
                    continue;
                }

                for (String materialKey : craftMaterialSection.getKeys(false)) {
                    String materialName = craftMaterialSection.getString(materialKey);
                    if (materialName == null) {
                        this.plugin.getLogger().severe("[PowerfulBackpacks] Could not found craft material name.");
                        continue;
                    }
                    Material material = Material.getMaterial(materialName);
                    if (material == null) {
                        this.plugin.getLogger().severe("[PowerfulBackpacks] Could not found craft material.");
                        continue;
                    }

                    recipe.setIngredient(materialKey.charAt(0), material);
                }

                this.plugin.getServer().addRecipe(recipe);
            }


            this.registeredItems.put(key, customItem);
        }
    }

    @Nullable
    public ItemStack getItem(String itemKey) {
        return this.registeredItems.get(itemKey);
    }
     */
}
