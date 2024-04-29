package fun.felipe.powerfulbackpacks.utils.items;

import fun.felipe.powerfulbackpacks.utils.StringUtils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BundleMeta;

import java.util.List;

public class ItemUtils {
    public static ItemStack createItemStack(Material type, String name, List<String> lore) {
        ItemStack itemStack = new ItemStack(type);
        BundleMeta bundleMeta = (BundleMeta) itemStack.getItemMeta();
        //bundleMeta.addItemFlags(ItemFlag.HIDE_ITEM_SPECIFICS);
        bundleMeta.displayName(StringUtils.formatItemName(name));
        bundleMeta.lore(StringUtils.formatLore(lore));
        itemStack.setItemMeta(bundleMeta);
        return itemStack;
    }
}
