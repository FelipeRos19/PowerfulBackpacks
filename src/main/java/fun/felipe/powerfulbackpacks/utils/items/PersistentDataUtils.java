package fun.felipe.powerfulbackpacks.utils.items;

import fun.felipe.powerfulbackpacks.PowerfulBackpacks;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class PersistentDataUtils {

    public static void addStringData(ItemStack itemStack, String key, String data) {
        ItemMeta meta = itemStack.getItemMeta();
        meta.getPersistentDataContainer().set(buildKey(key), PersistentDataType.STRING, data);
        itemStack.setItemMeta(meta);
    }

    public static boolean hasData(ItemStack itemStack, String key) {
        ItemMeta meta = itemStack.getItemMeta();
        return meta.getPersistentDataContainer().has(buildKey(key));
    }

    public static String getStringData(ItemStack itemStack, String key) {
        ItemMeta meta = itemStack.getItemMeta();
        return meta.getPersistentDataContainer().get(buildKey(key), PersistentDataType.STRING);
    }

    public static NamespacedKey buildKey(String key) {
        return new NamespacedKey(PowerfulBackpacks.getInstance(), key);
    }
}
