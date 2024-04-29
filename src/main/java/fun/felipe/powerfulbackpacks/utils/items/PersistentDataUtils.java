package fun.felipe.powerfulbackpacks.utils.items;

import fun.felipe.powerfulbackpacks.PowerfulBackpacks;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BundleMeta;
import org.bukkit.persistence.PersistentDataType;

public class PersistentDataUtils {

    public static ItemStack addStringData(ItemStack itemStack, String key, String data) {
        BundleMeta bundleMeta = (BundleMeta) itemStack.getItemMeta();
        bundleMeta.getPersistentDataContainer().set(buildKey(key), PersistentDataType.STRING, data);
        itemStack.setItemMeta(bundleMeta);
        return itemStack;
    }

    public static boolean hasData(ItemStack itemStack, String key) {
        BundleMeta bundleMeta = (BundleMeta) itemStack.getItemMeta();
        return bundleMeta.getPersistentDataContainer().has(buildKey(key));
    }

    public static String getStringData(ItemStack itemStack, String key) {
        BundleMeta bundleMeta = (BundleMeta) itemStack.getItemMeta();
        return bundleMeta.getPersistentDataContainer().get(buildKey(key), PersistentDataType.STRING);
    }

    public static NamespacedKey buildKey(String key) {
        return new NamespacedKey(PowerfulBackpacks.getInstance(), key);
    }
}
