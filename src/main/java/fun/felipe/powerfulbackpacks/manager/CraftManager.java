package fun.felipe.powerfulbackpacks.manager;

import fun.felipe.powerfulbackpacks.entities.BackpackEntity;
import fun.felipe.powerfulbackpacks.entities.RecipeEntity;
import fun.felipe.powerfulbackpacks.utils.StringUtils;
import fun.felipe.powerfulbackpacks.utils.items.ItemUtils;
import fun.felipe.powerfulbackpacks.utils.items.PersistentDataUtils;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.*;

public class CraftManager {
    final Plugin plugin;
    @Getter
    private final List<RecipeEntity> backpackRecipes;
    @Getter
    private final Map<String, BackpackEntity> registeredBackpacks;

    public CraftManager(Plugin plugin) {
        this.plugin = plugin;
        this.backpackRecipes = new ArrayList<>();
        this.registeredBackpacks = new HashMap<>();
        loadRecipes();
    }

    private void loadRecipes() {
        Bukkit.getConsoleSender().sendMessage(StringUtils.format("<green>[PowerfulBackpacks] Starting the process of loading the backpacks!"));
        ConfigurationSection backpackSection = this.plugin.getConfig().getConfigurationSection("Backpacks");
        if (backpackSection == null) {
            this.plugin.getLogger().severe("Backpack List not found in Config!");
            this.plugin.getServer().getPluginManager().disablePlugin(this.plugin);
            return;
        }

        for (String backpack : backpackSection.getKeys(false)) {
            ConfigurationSection internalBackpackSection = backpackSection.getConfigurationSection(backpack);
            if (internalBackpackSection == null) {
                this.plugin.getLogger().severe("Backpack internal settings not found! (%s)".formatted(backpack));
                this.plugin.getServer().getPluginManager().disablePlugin(this.plugin);
                return;
            }

            String backpackName = internalBackpackSection.getString("name");
            List<String> backpackLore = internalBackpackSection.getStringList("lore");
            int backpackRows = internalBackpackSection.getInt("rows");

            this.registeredBackpacks.put(backpack, new BackpackEntity(backpackName, backpackLore, backpackRows));

            if (backpackRows == 0) backpackRows = 1;

            ItemStack backpackItemStack = ItemUtils.createBundleItemStack(Material.BUNDLE, backpackName, backpackLore);
            PersistentDataUtils.addStringData(backpackItemStack, "type", backpack);
            PersistentDataUtils.addStringData(backpackItemStack, "content", "");
            PersistentDataUtils.addIntData(backpackItemStack, "rows", backpackRows);

            ConfigurationSection craftInternalBackpackSection = internalBackpackSection.getConfigurationSection("craft");
            if (craftInternalBackpackSection == null) {
                this.plugin.getLogger().severe("Backpack craft settings not found! (%s)".formatted(backpack));
                this.plugin.getServer().getPluginManager().disablePlugin(this.plugin);
                return;
            }

            ItemStack[] backpackShape = new ItemStack[9];
            List<String> backpackCraftShape = craftInternalBackpackSection.getStringList("shape");

            ConfigurationSection itemsCraftInternalBackpackSection = craftInternalBackpackSection.getConfigurationSection("items");
            if (itemsCraftInternalBackpackSection == null) {
                this.plugin.getLogger().severe("Backpack material settings no found! (%s)".formatted(backpack));
                this.plugin.getServer().getPluginManager().disablePlugin(this.plugin);
                return;
            }

            Map<String, ItemStack> backpackCraftMaterials = new HashMap<>();
            for (String key : itemsCraftInternalBackpackSection.getKeys(false)) {
                String stringMaterial = itemsCraftInternalBackpackSection.getString(key.toUpperCase());
                if (stringMaterial == null) {
                    this.plugin.getLogger().severe("Item key %s is null! (%s)".formatted(key, backpack));
                    this.plugin.getServer().getPluginManager().disablePlugin(this.plugin);
                    return;
                }

                ItemStack item;

                if (key.equals("$")) {
                    ItemStack backpackRecoveredItem = getResultByBackpackID(stringMaterial);
                    if (backpackRecoveredItem == null) {
                        this.plugin.getLogger().severe("The referenced Backpack (%s) was not found! (%s)".formatted(stringMaterial, backpack));
                        this.plugin.getServer().getPluginManager().disablePlugin(this.plugin);
                        return;
                    }

                    item = backpackRecoveredItem;
                } else {
                    Material material = Material.matchMaterial(stringMaterial);
                    if (material == null) {
                        this.plugin.getLogger().severe("The material was not found! (%s)".formatted(stringMaterial));
                        this.plugin.getServer().getPluginManager().disablePlugin(this.plugin);
                        return;
                    }

                    item = new ItemStack(material);
                }

                backpackCraftMaterials.put(key, new ItemStack(item));
            }

            int shapeIndex = 0;
            for (String shapeLine : backpackCraftShape) {
                for (int i = 0; i < shapeLine.length(); i++) {
                    String materialKey = String.valueOf(shapeLine.charAt(i));
                    if (materialKey.equals(" "))
                        backpackShape[shapeIndex] = null;
                    else
                        backpackShape[shapeIndex] = new ItemStack(backpackCraftMaterials.get(materialKey));

                    shapeIndex++;
                }
            }
            this.backpackRecipes.add(new RecipeEntity(backpack, backpackShape, backpackItemStack));
            Bukkit.getConsoleSender().sendMessage(StringUtils.format("<green>[PowerfulBackpacks] %s has been loaded Successfully!".formatted(backpack)));
        }
    }

    public ItemStack getResultByBackpackID(String backpackID) {
        for (RecipeEntity recipe : this.backpackRecipes) {
            if (recipe.backpackID().equals(backpackID))
                return recipe.result().clone();
        }
        return null;
    }

    public RecipeEntity isCustomCraft(ItemStack[] craftMatrix) {
        for (int i = 0; i < craftMatrix.length; i++) {
            if (craftMatrix[i] != null) {
                if (craftMatrix[i].getType().equals(Material.BUNDLE)) {
                    String backpackID = PersistentDataUtils.getStringData(craftMatrix[i], "type");
                    craftMatrix[i] = getResultByBackpackID(backpackID);
                }
            }
        }

        for (RecipeEntity recipe : this.backpackRecipes) {
            if (Arrays.equals(recipe.shape(), craftMatrix)) {
                return recipe;
            }
        }
        return null;
    }
}
