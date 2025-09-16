package fun.felipe.powerfulbackpacks.utils.items;

import de.tr7zw.changeme.nbtapi.NBT;
import fun.felipe.powerfulbackpacks.utils.StringUtils;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Item;
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

    public static ItemStack createCustomItem(Material type, String name, List<String> lore, String itemID) {
        ItemStack itemStack = new ItemStack(type);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.displayName(StringUtils.formatItemName(name));
        itemMeta.lore(StringUtils.formatItemLore(lore));
        itemStack.setItemMeta(itemMeta);
        PersistentDataUtils.addStringData(itemStack, "custom-item", itemID);
        return itemStack;
    }

    public static ItemStack createCustomItem(Material type, String name, List<String> lore, String textureKey, String itemID) {
        ItemStack itemStack = new ItemStack(type);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.displayName(StringUtils.formatItemName(name));
        itemMeta.lore(StringUtils.formatItemLore(lore));
        itemStack.setItemMeta(itemMeta);
        PersistentDataUtils.addStringData(itemStack, "custom-item", itemID);


        NBT.modifyComponents(itemStack, nbt -> {
            nbt.setString("minecraft:item_model", textureKey);
        });
        return itemStack;
    }
}
