package fun.felipe.powerfulbackpacks.views;

import fun.felipe.powerfulbackpacks.PowerfulBackpacks;
import fun.felipe.powerfulbackpacks.utils.items.ItemUtils;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IconsManager {
    private final PowerfulBackpacks plugin;
    private final Map<String, ItemStack> registeredIcons;

    public IconsManager(PowerfulBackpacks plugin) {
        this.plugin = plugin;
        this.registeredIcons = new HashMap<>();
        this.loadIcons();
    }

    private void loadIcons() {
        ConfigurationSection iconsSection = this.plugin.getIconsFile().getFileConfiguration().getConfigurationSection("Icons");
        if (iconsSection == null) {
            this.plugin.getLogger().warning("[PowerfulBackpacks] Could not load icons section!");
            return;
        }

        for (String iconKey : iconsSection.getKeys(false)) {
            ConfigurationSection iconSection = iconsSection.getConfigurationSection(iconKey);
            if (iconSection == null) {
                this.plugin.getLogger().warning("[PowerfulBackpacks] Could not load item " + iconKey + " section!");
                continue;
             }

            String iconName = iconSection.getString("name");
            List<String> iconLore = iconSection.getStringList("lore");

            String iconMaterialName = iconSection.getString("material");
            if (iconMaterialName == null)
                iconMaterialName = "PAPER";

            Material iconMaterial = Material.getMaterial(iconMaterialName);
            if (iconMaterial == null)
                iconMaterial = Material.PAPER;

            String iconTextureKey = iconSection.getString("texture");
            int iconCustomModelData = iconSection.getInt("customModelData", -1);

            ItemStack icon = ItemUtils.createItem(iconMaterial, iconName, iconLore);
            if (iconCustomModelData != -1) {
                icon = ItemUtils.setModelData(icon, iconCustomModelData);
            }

            if (iconTextureKey != null) {
                icon =  ItemUtils.setTexture(icon, iconTextureKey);
            }

            this.registeredIcons.put(iconKey, icon);
        }
    }

    @Nullable
    public ItemStack getIcon(String itemKey) {
        return this.registeredIcons.get(itemKey);
    }
}
