package fun.felipe.powerfulbackpacks.manager;

import fun.felipe.powerfulbackpacks.PowerfulBackpacks;
import fun.felipe.powerfulbackpacks.entities.BackpackEntity;
import fun.felipe.powerfulbackpacks.entities.RecipeEntity;
import fun.felipe.powerfulbackpacks.utils.items.ItemUtils;
import fun.felipe.powerfulbackpacks.utils.items.PersistentDataUtils;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecipeManager {
    private final Plugin plugin;
    @Getter
    private final Map<String, BackpackEntity> registeredBackpacks;
    @Getter
    private final Map<String, RecipeEntity> backpackRecipes;
    @Getter
    private final Map<String, List<String>> backpackLore;
    private final List<String> WILDCARDS = List.of("0", "1", "2", "3", "4", "5", "6", "7", "8", "9");

    public RecipeManager(Plugin plugin) {
        this.plugin = plugin;
        this.registeredBackpacks = new HashMap<>();
        this.backpackRecipes = new HashMap<>();
        this.backpackLore = new HashMap<>();
        this.loadRecipes();
    }

    private void loadRecipes() {
        this.plugin.getLogger().info("Starting backpacks loading process.");
        ConfigurationSection generalBackpackSection = this.plugin.getConfig().getConfigurationSection("Backpacks");
        if (generalBackpackSection == null) {
            this.plugin.getLogger().severe("Backpack List not found in Config!");
            this.plugin.getServer().getPluginManager().disablePlugin(this.plugin);
            return;
        }

        for (String backpack : generalBackpackSection.getKeys(false)) {
            ConfigurationSection backpackSection = generalBackpackSection.getConfigurationSection(backpack);
            if (backpackSection == null) {
                this.plugin.getLogger().severe("Backpack internal settings not found! (%s)".formatted(backpack));
                this.plugin.getServer().getPluginManager().disablePlugin(this.plugin);
                return;
            }

            String backpackName = backpackSection.getString("name");
            List<String> backpackLore = backpackSection.getStringList("lore");
            int backpackRows = backpackSection.getInt("rows");
            if (backpackRows == 0) backpackRows = 1;
            int backpackModelData = backpackSection.getInt("model_data");

            BackpackEntity backpackEntity = new BackpackEntity(backpack, backpackName, backpackRows, backpackModelData);

            this.registeredBackpacks.put(backpack, backpackEntity);
            this.backpackLore.put(backpack, backpackLore);

            ItemStack backpackItem;
            if (backpackModelData == 0)
                backpackItem = ItemUtils.createBackpack(backpackName, PowerfulBackpacks.getInstance().getMessagesManager().createLore(backpackEntity, backpackLore));
            else
                backpackItem = ItemUtils.createBackpack(backpackName, PowerfulBackpacks.getInstance().getMessagesManager().createLore(backpackEntity, backpackLore), backpackModelData);


            PersistentDataUtils.addStringData(backpackItem, "type", backpack);
            PersistentDataUtils.addStringData(backpackItem, "content", "");
            PersistentDataUtils.addIntData(backpackItem, "rows", backpackRows);

            ConfigurationSection craftBackpack = backpackSection.getConfigurationSection("craft");
            if (craftBackpack == null) {
                this.plugin.getLogger().severe("Backpack craft settings not found! (%s)".formatted(backpack));
                this.plugin.getServer().getPluginManager().disablePlugin(this.plugin);
                return;
            }

            ItemStack[] backpackShapeResult = new ItemStack[9];
            List<String> backpackShapeIdentifier = craftBackpack.getStringList("shape");

            ConfigurationSection itemsCraftBackpack = craftBackpack.getConfigurationSection("items");
            if (itemsCraftBackpack == null) {
                this.plugin.getLogger().severe("Backpack material settings no found! (%s)".formatted(backpack));
                this.plugin.getServer().getPluginManager().disablePlugin(this.plugin);
                return;
            }

            Map<String, ItemStack> backpackCraftItems = new HashMap<>();
            for (String key : itemsCraftBackpack.getKeys(false)) {
                ItemStack item;
                String materialID = itemsCraftBackpack.getString(key.toUpperCase());
                if (materialID == null && !this.WILDCARDS.contains(key)) {
                    this.plugin.getLogger().severe("Item key %s is null! (%s)".formatted(key, backpack));
                    this.plugin.getServer().getPluginManager().disablePlugin(this.plugin);
                    return;
                }

                if (this.WILDCARDS.contains(key)) {
                    String customItemName = itemsCraftBackpack.getString(key + ".name");
                    String customItemType = itemsCraftBackpack.getString(key + ".type");
                    int customItemAmount = itemsCraftBackpack.getInt(key + ".amount");

                    if (customItemType == null) {
                        this.plugin.getLogger().severe("Custom item type is null! (%s)".formatted(backpack));
                        this.plugin.getServer().getPluginManager().disablePlugin(this.plugin);
                        return;
                    }

                    Material customItemMaterial = Material.matchMaterial(customItemType);
                    if (customItemMaterial == null) {
                        this.plugin.getLogger().severe("Custom item Material is null! (%s)".formatted(backpack));
                        this.plugin.getServer().getPluginManager().disablePlugin(this.plugin);
                        return;
                    }

                    if (customItemName == null)
                        item = ItemUtils.createCustomItem(customItemMaterial, customItemAmount);
                    else
                        item = ItemUtils.createCustomItem(customItemName, customItemMaterial, customItemAmount);
                } else if (key.equals("$")) {
                    ItemStack backpackRecoveredItem = this.backpackRecipes.get(materialID).result();
                    if (backpackRecoveredItem == null) {
                        this.plugin.getLogger().severe("The referenced Backpack (%s) was not found! (%s)".formatted(materialID, backpack));
                        this.plugin.getServer().getPluginManager().disablePlugin(this.plugin);
                        return;
                    }

                    item = backpackRecoveredItem;
                } else {
                    if (materialID == null) {
                        this.plugin.getLogger().severe("The material is null! (%s)".formatted(backpack));
                        this.plugin.getServer().getPluginManager().disablePlugin(this.plugin);
                        return;
                    }

                    Material material = Material.matchMaterial(materialID);
                    if (material == null) {
                        this.plugin.getLogger().severe("The material was not found! (%s)".formatted(materialID));
                        this.plugin.getServer().getPluginManager().disablePlugin(this.plugin);
                        return;
                    }

                    item = new ItemStack(material);
                }

                backpackCraftItems.put(key, item);
            }

            int shapeIndex = 0;
            for (String shapeLine : backpackShapeIdentifier) {
                for (int i = 0; i < shapeLine.length(); i++) {
                    String materialKey = String.valueOf(shapeLine.charAt(i));
                    if (materialKey.equals(" "))
                        backpackShapeResult[shapeIndex] = null;
                    else
                        backpackShapeResult[shapeIndex] = new ItemStack(backpackCraftItems.get(materialKey));

                    shapeIndex++;
                }
            }

            this.backpackRecipes.put(backpack, new RecipeEntity(backpackShapeResult, backpackItem));
        }

        this.plugin.getLogger().info("Finishing backpacks loading process.");
    }

    public RecipeEntity isCustomCraft(ItemStack[] craftMatrix) {
        for (int i = 0; i < craftMatrix.length; i++) {
            if (craftMatrix[i] != null) {
                if (craftMatrix[i].getType().equals(Material.BUNDLE)) {
                    String backpackID = PersistentDataUtils.getStringData(craftMatrix[i], "type");
                    craftMatrix[i] = this.backpackRecipes.get(backpackID).result();
                }
            }
        }

        for (RecipeEntity recipe : this.backpackRecipes.values()) {
            if (Arrays.equals(recipe.shape(), craftMatrix)) {
                return recipe;
            }
        }
        return null;
    }
}
