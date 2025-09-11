package fun.felipe.powerfulbackpacks.managers.items.implementation;

import de.tr7zw.changeme.nbtapi.NBT;
import fun.felipe.powerfulbackpacks.managers.items.interfaces.GenericCraftableItem;
import fun.felipe.powerfulbackpacks.utils.StringUtils;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.List;
import java.util.Map;

public class SewingKitItem extends GenericCraftableItem {

    public SewingKitItem(String itemName, List<String> itemLore, Material itemMaterial) {
        super("sewing_kit", itemName, itemLore, itemMaterial);
    }

    @Override
    public ItemStack createItemStack() {
        ItemStack itemStack = new ItemStack(this.getItemMaterial());
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.displayName(StringUtils.formatItemName(this.getItemName()));
        itemMeta.lore(StringUtils.formatItemLore(this.getItemLore()));
        itemStack.setItemMeta(itemMeta);

        NBT.modifyComponents(itemStack, nbt -> {
            nbt.setString("minecraft:item_model", "powerfulbackpacks:kit_costura_item");
        });

        return itemStack;
    }

    @Override
    public ShapedRecipe createShapedRecipe(List<String> shape, Map<Character, Material> materials, Plugin plugin) {
        NamespacedKey key = new NamespacedKey(plugin, this.getItemID());
        ShapedRecipe recipe = new ShapedRecipe(key, this.createItemStack());

        if (shape.isEmpty() || shape.size() < 3) {
            //TODO: TRATAR ISSO MELHOR!
            throw new RuntimeException("Invalid shape!");
        }

        materials.forEach(recipe::setIngredient);
        return recipe;
    }
}
