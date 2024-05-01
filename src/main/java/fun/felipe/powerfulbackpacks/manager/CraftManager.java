package fun.felipe.powerfulbackpacks.manager;

import fun.felipe.powerfulbackpacks.entities.Recipe;
import fun.felipe.powerfulbackpacks.utils.items.ItemUtils;
import fun.felipe.powerfulbackpacks.utils.items.PersistentDataUtils;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.*;

//TODO: TRADUZIR TODAS AS LOGS PARA INGLÊS!
public class CraftManager {
    final Plugin plugin;
    @Getter
    private final List<Recipe> backpackRecipes;

    public CraftManager(Plugin plugin) {
        this.plugin = plugin;
        this.backpackRecipes = new ArrayList<>();
        loadRecipes();
    }

    private void loadRecipes() {
        ConfigurationSection backpackSection = this.plugin.getConfig().getConfigurationSection("Backpacks");
        if (backpackSection == null) {
            this.plugin.getLogger().severe("Não foi encontrada a Lista de Mochilas na Config.yml!");
            this.plugin.getServer().getPluginManager().disablePlugin(this.plugin);
            return;
        }

        for (String backpack : backpackSection.getKeys(false)) {
            ConfigurationSection internalBackpackSection = backpackSection.getConfigurationSection(backpack);
            if (internalBackpackSection == null) {
                this.plugin.getLogger().severe("Não foi encontrada as Configurações internas da Mochila: " + backpack + " na Config.yml!");
                this.plugin.getServer().getPluginManager().disablePlugin(this.plugin);
                return;
            }

            String backpackName = internalBackpackSection.getString("name");
            List<String> backpackLore = internalBackpackSection.getStringList("lore");
            int backpackRows = internalBackpackSection.getInt("rows");

            ItemStack backpackItemStack = ItemUtils.createBundleItemStack(Material.BUNDLE, backpackName, backpackLore);
            PersistentDataUtils.addStringData(backpackItemStack, "type", backpack);
            PersistentDataUtils.addStringData(backpackItemStack, "content", "");
            PersistentDataUtils.addIntData(backpackItemStack, "rows", backpackRows);

            ConfigurationSection craftInternalBackpackSection = internalBackpackSection.getConfigurationSection("craft");
            if (craftInternalBackpackSection == null) {
                this.plugin.getLogger().severe("Não foi encontrada as Configurações de Crafting da Mochila:" + backpack + " na Config.yml!");
                this.plugin.getServer().getPluginManager().disablePlugin(this.plugin);
                return;
            }

            ItemStack[] backpackShape = new ItemStack[9];
            List<String> backpackCraftShape = craftInternalBackpackSection.getStringList("shape");
            System.out.println(backpackCraftShape);

            ConfigurationSection itemsCraftInternalBackpackSection = craftInternalBackpackSection.getConfigurationSection("items");
            if (itemsCraftInternalBackpackSection == null) {
                this.plugin.getLogger().severe("Não foi encontrada a Lista de Itens do Craft da Mochila: " + backpack + " na Config.yml!");
                this.plugin.getServer().getPluginManager().disablePlugin(this.plugin);
                return;
            }

            Map<String, ItemStack> backpackCraftMaterials = new HashMap<>();
            for (String key : itemsCraftInternalBackpackSection.getKeys(false)) {
                String stringMaterial = itemsCraftInternalBackpackSection.getString(key.toUpperCase());
                if (stringMaterial == null) {
                    this.plugin.getLogger().severe("A Chave " + key + " se encontra Nula na Mochila: " + backpack + " na Config.yml!");
                    this.plugin.getServer().getPluginManager().disablePlugin(this.plugin);
                    return;
                }

                ItemStack item;

                if (key.equals("$")) {
                    ItemStack backpackRecoveredItem = getResultByBackpackID(stringMaterial);
                    if (backpackRecoveredItem == null) {
                        this.plugin.getLogger().severe("A Mochila referênciada não foi encontrada na Config.yml!");
                        this.plugin.getServer().getPluginManager().disablePlugin(this.plugin);
                        return;
                    }

                    item = backpackRecoveredItem;
                } else {
                    Material material = Material.matchMaterial(stringMaterial);
                    if (material == null) {
                        this.plugin.getLogger().severe("O Material não foi encontrado! (" + stringMaterial + ")");
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

                    this.backpackRecipes.add(new Recipe(backpack, backpackShape, backpackItemStack));
                    shapeIndex++;
                }
            }
        }
    }

    private ItemStack getResultByBackpackID(String backpackID) {
        for (Recipe recipe : this.backpackRecipes) {
            if (recipe.backpackID().equals(backpackID))
                return recipe.result();
        }
        return null;
    }

    public Recipe isCustomCraft(ItemStack[] craftMatrix) {
        for (int i = 0; i < craftMatrix.length; i++) {
            if (craftMatrix[i] != null) {
                if (craftMatrix[i].getType().equals(Material.BUNDLE)) {
                    String backpackID = PersistentDataUtils.getStringData(craftMatrix[i], "type");
                    craftMatrix[i] = getResultByBackpackID(backpackID);
                }
            }
        }

        for (Recipe recipe : this.backpackRecipes) {
            if (Arrays.equals(recipe.shape(), craftMatrix)) {
                return recipe;
            }
        }
        return null;
    }
}
