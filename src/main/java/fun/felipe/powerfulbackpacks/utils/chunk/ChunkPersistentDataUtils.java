package fun.felipe.powerfulbackpacks.utils.chunk;

import fun.felipe.powerfulbackpacks.utils.items.ItemPersistentDataUtils;
import org.bukkit.Chunk;
import org.bukkit.persistence.PersistentDataType;


public class ChunkPersistentDataUtils {

    public static void addBooleanData(Chunk chunk, String key, boolean data) {
        chunk.getPersistentDataContainer()
                .set(ItemPersistentDataUtils.buildKey(key), PersistentDataType.BOOLEAN, data);
    }

    public static boolean hasBooleanData(Chunk chunk, String key) {
        return chunk.getPersistentDataContainer().has(ItemPersistentDataUtils.buildKey(key), PersistentDataType.BOOLEAN);
    }
}
