package fun.felipe.powerfulbackpacks.utils.items;

import de.tr7zw.changeme.nbtapi.NBT;
import fun.felipe.powerfulbackpacks.utils.StringUtils;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BundleMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class ItemUtils {

    public static ItemStack createBundleItemStack(Material type, String name, List<Component> lore) {
        ItemStack itemStack = new ItemStack(type);
        BundleMeta bundleMeta = (BundleMeta) itemStack.getItemMeta();
        bundleMeta.addItemFlags(ItemFlag.HIDE_PLACED_ON);
        bundleMeta.displayName(StringUtils.formatItemName(name));
        bundleMeta.lore(lore);
        itemStack.setItemMeta(bundleMeta);
        return itemStack;
    }

    public static ItemStack createItem(Material type, String name, List<String> lore) {
        ItemStack itemStack = new ItemStack(type);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.displayName(StringUtils.formatItemName(name));
        itemMeta.lore(StringUtils.formatItemLore(lore));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static ItemStack setTexture(ItemStack itemStack, String texture) {
        NBT.modifyComponents(itemStack, nbt -> {
            nbt.setString("minecraft:item_model", texture);
        });
        return itemStack;
    }

    public static ItemStack setModelData(ItemStack itemStack, int modelData) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setCustomModelData(modelData);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static ItemStack createCustomItem(Material type, String name, List<String> lore, String itemID) {
        ItemStack itemStack = createItem(type, name, lore);
        ItemPersistentDataUtils.addStringData(itemStack, "custom-item", itemID);
        return itemStack;
    }
}
