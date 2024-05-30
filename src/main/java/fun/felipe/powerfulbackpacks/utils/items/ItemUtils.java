package fun.felipe.powerfulbackpacks.utils.items;

import fun.felipe.powerfulbackpacks.utils.StringUtils;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BundleMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class ItemUtils {
    public static ItemStack createBackpack(String name, List<Component> lore, int modelData) {
        ItemStack itemStack = new ItemStack(Material.BUNDLE);
        BundleMeta bundleMeta = (BundleMeta) itemStack.getItemMeta();
        bundleMeta.addItemFlags(ItemFlag.HIDE_ITEM_SPECIFICS);
        bundleMeta.setCustomModelData(modelData);
        bundleMeta.displayName(StringUtils.formatItemName(name));
        bundleMeta.lore(lore);
        itemStack.setItemMeta(bundleMeta);
        return itemStack;
    }

    public static ItemStack createBackpack(String name, List<Component> lore) {
        ItemStack itemStack = new ItemStack(Material.BUNDLE);
        BundleMeta bundleMeta = (BundleMeta) itemStack.getItemMeta();
        bundleMeta.addItemFlags(ItemFlag.HIDE_ITEM_SPECIFICS);
        bundleMeta.displayName(StringUtils.formatItemName(name));
        bundleMeta.lore(lore);
        itemStack.setItemMeta(bundleMeta);
        return itemStack;
    }

    public static ItemStack createCustomItem(Material type, int amount) {
        ItemStack itemStack = new ItemStack(type);
        itemStack.setAmount(amount);
        return itemStack;
    }

    public static ItemStack createCustomItem(String name, Material type, int amount) {
        ItemStack itemStack = new ItemStack(type);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.displayName(StringUtils.formatItemName(name));
        itemStack.setItemMeta(itemMeta);
        itemStack.setAmount(amount);
        return itemStack;
    }
}
