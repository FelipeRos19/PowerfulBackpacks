package fun.felipe.powerfulbackpacks.items;

import fun.felipe.powerfulbackpacks.PowerfulBackpacks;
import fun.felipe.powerfulbackpacks.utils.items.ItemPersistentDataUtils;
import fun.felipe.powerfulbackpacks.utils.items.ItemUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemsManager {
    private final PowerfulBackpacks plugin;
    private final Map<String, ItemStack> registeredItems;
    private final Map<String, ItemStack[]> registeredItemRecipes;

    public ItemsManager(PowerfulBackpacks plugin) {
        this.plugin = plugin;
        this.registeredItems = new HashMap<>();
        this.registeredItemRecipes = new HashMap<>();
        this.loadItems();
    }

    private void loadItems() {
        ConfigurationSection itemsSection = this.plugin.getItemsFile().getFileConfiguration().getConfigurationSection("Items");
        if (itemsSection == null) {
            this.plugin.getLogger().warning("[PowerfulBackpacks] Could not load items section!");
            return;
        }

        for (String itemKey: itemsSection.getKeys(false)) {
            ConfigurationSection itemSection = itemsSection.getConfigurationSection(itemKey);
            if (itemSection == null) {
                this.plugin.getLogger().warning("[PowerfulBackpacks] Could not load items section!");
                continue;
            }

            this.plugin.getLogger().info("[PowerfulBackpacks] Loading " + itemKey);

            String itemName = itemSection.getString("name");
            List<String> itemLore = itemSection.getStringList("lore");

            String itemMaterialName = itemSection.getString("material");

            if (itemMaterialName == null)
                itemMaterialName = "PAPER";

            Material itemMaterial = Material.getMaterial(itemMaterialName);
            if (itemMaterial == null)
                itemMaterial = Material.PAPER;

            String itemTextureKey = itemSection.getString("texture");
            int itemCustomModelData = itemSection.getInt("customModelData", -1);

            ItemStack item = ItemUtils.createItem(itemMaterial, itemName, itemLore);
            if (itemCustomModelData != -1)
                item = ItemUtils.setModelData(item, itemCustomModelData);

            if (itemTextureKey != null)
                item = ItemUtils.setTexture(item, itemTextureKey);

            ItemPersistentDataUtils.addBooleanData(item, "reinforced", true);

            this.registeredItems.put(itemKey, item);
            //CRAFT RECIPE!
            NamespacedKey itemNamespacedKey = new NamespacedKey(this.plugin, itemKey);

            ConfigurationSection itemCraftSection = itemSection.getConfigurationSection("craft");
            if (itemCraftSection == null) {
                this.plugin.getLogger().warning("[PowerfulBackpacks] Could not load craft section! This item cannot be crafted.");
                continue;
            }

            ConfigurationSection materialSection = itemCraftSection.getConfigurationSection("materials");
            if (materialSection == null) {
                this.plugin.getLogger().warning("[PowerfulBackpacks] Could not load material section! This item cannot be crafted.");
                continue;
            }

            Map<String, Material> materials = new HashMap<>();
            for (String materialKey: materialSection.getKeys(false)) {
                String materialName = materialSection.getString(materialKey);
                if (materialName == null) {
                    this.plugin.getLogger().warning("[PowerfulBackpacks] Material can not be null!" + materialKey);
                    continue;
                }

                Material material = Material.getMaterial(materialName);
                if (material == null) {
                    this.plugin.getLogger().warning("[PowerfulBackpacks] Material can not be null!" + materialName);
                    continue;
                }

                materials.put(materialKey, material);
            }

            ShapedRecipe itemRecipe = new ShapedRecipe(itemNamespacedKey, item);

            ItemStack[] itemCraftShape = new ItemStack[9];
            String[] shapeString = new String[3];
            int shapeStringIndex = 0;
            int shapeIndex = 0;
            for (String shapeLine : itemCraftSection.getStringList("shape")) {
                shapeString[shapeStringIndex] = shapeLine;
                shapeStringIndex++;
                for (int i = 0; i < shapeLine.length(); i++) {
                    String materialKey = String.valueOf(shapeLine.charAt(i));
                    if (materialKey.equals(" "))
                        itemCraftShape[shapeIndex] = null;
                    else
                        itemCraftShape[shapeIndex] = new ItemStack(materials.get(materialKey));

                    shapeIndex++;
                }
            }

            itemRecipe.shape(shapeString);
            materials.forEach((key, material) -> {
                itemRecipe.setIngredient(key.charAt(0), material);
            });

            Bukkit.addRecipe(itemRecipe);
            this.registeredItemRecipes.put(itemKey, itemCraftShape);
        }
    }

    @Nullable
    public ItemStack getItem(String itemKey) {
        return this.registeredItems.get(itemKey);
    }
}
