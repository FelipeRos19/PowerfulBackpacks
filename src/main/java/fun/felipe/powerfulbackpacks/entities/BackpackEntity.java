package fun.felipe.powerfulbackpacks.entities;

import java.util.List;

public record BackpackEntity(String key, String name, List<String> lore, int rows) {
}
