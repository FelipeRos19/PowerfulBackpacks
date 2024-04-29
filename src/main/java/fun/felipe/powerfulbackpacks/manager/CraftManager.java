package fun.felipe.powerfulbackpacks.manager;

import fun.felipe.powerfulbackpacks.entities.Recipe;
import fun.felipe.powerfulbackpacks.utils.items.ItemUtils;
import fun.felipe.powerfulbackpacks.utils.items.PersistentDataUtils;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class CraftManager {
    final Plugin plugin;
    private List<Recipe> backpackRecipes;

    public CraftManager(Plugin plugin) {
        this.plugin = plugin;
        this.backpackRecipes = new ArrayList<>();
        loadRecipes();
        registerRecipes();
    }

    private void loadRecipes() {
        ConfigurationSection backpackSection = this.plugin.getConfig().getConfigurationSection("Backpacks");
        if (backpackSection == null) {
            System.out.println("Erro!");
            return;
        }

        for (String backpack : backpackSection.getKeys(false)) {
            ConfigurationSection internalBackpackSection = backpackSection.getConfigurationSection(backpack);
            if (internalBackpackSection == null) {
                System.out.println("ERRO INTERNAL");
                return;
            }

            String backpackName = internalBackpackSection.getString("name");
            List<String> backpackLore = internalBackpackSection.getStringList("lore");
            int backpackRows = internalBackpackSection.getInt("rows");

            ConfigurationSection craftInternalBackpackSection = internalBackpackSection.getConfigurationSection("craft");
            if (craftInternalBackpackSection == null) {
                System.out.println("ERRO CRAFT INTERNAL");
                return;
            }

            List<String> backpackCraftShape = craftInternalBackpackSection.getStringList("shape");

            ConfigurationSection itemsCraftInternalBackpackSection = craftInternalBackpackSection.getConfigurationSection("items");
            if (itemsCraftInternalBackpackSection == null) {
                System.out.println("ERRO ITEMS CRAFT INTERNAL");
                return;
            }

            for (String keys : itemsCraftInternalBackpackSection.getKeys(false)) {
                System.out.println("Key: " + keys);
            }
        }
    }

    private void createRecipe() {

    }

    public void registerRecipes() {
        ItemStack bundleItem = ItemUtils.createItemStack(Material.BUNDLE, "<red>Mochila Legal", List.of("", "<red>Ela é realmente Legal!", ""));
        PersistentDataUtils.addStringData(bundleItem, "content", "");
        ShapedRecipe bundleRecipe = new ShapedRecipe(PersistentDataUtils.buildKey("mochila1"), bundleItem);
        bundleRecipe.shape("CCC", "C C", "CCC");
        bundleRecipe.setIngredient('C', Material.LEATHER);

        //this.plugin.getServer().addRecipe(bundleRecipe);
    }

    public boolean isCustomCraft(ItemStack[] item) {
        return false;
    }
}
