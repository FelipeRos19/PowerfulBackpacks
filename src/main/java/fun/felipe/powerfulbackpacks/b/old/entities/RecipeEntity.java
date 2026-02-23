package fun.felipe.powerfulbackpacks.b.old.entities;

import org.bukkit.inventory.ItemStack;

public record RecipeEntity(String backpackID, ItemStack[] shape, ItemStack result) {
}
