package fun.felipe.powerfulbackpacks.managers.items.entities;

import org.bukkit.Material;

import java.util.List;
import java.util.Map;

public record LoadItemConfig(String itemName,
                             List<String> lore,
                             Material material,
                             List<String> shape,
                             Map<Character, Material> materials) {
}
