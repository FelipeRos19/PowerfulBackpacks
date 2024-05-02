package fun.felipe.powerfulbackpacks.utils.items;

import fun.felipe.powerfulbackpacks.utils.StringUtils;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BundleMeta;

import java.util.List;

public class ItemUtils {
    public static ItemStack createBundleItemStack(Material type, String name, List<Component> lore) {
        ItemStack itemStack = new ItemStack(type);
        BundleMeta bundleMeta = (BundleMeta) itemStack.getItemMeta();
        bundleMeta.addItemFlags(ItemFlag.HIDE_ITEM_SPECIFICS);
        bundleMeta.displayName(StringUtils.formatItemName(name));
        bundleMeta.lore(lore);
        itemStack.setItemMeta(bundleMeta);
        return itemStack;
    }
}
