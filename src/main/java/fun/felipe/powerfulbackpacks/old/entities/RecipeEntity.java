package fun.felipe.powerfulbackpacks.old.entities;

import org.bukkit.inventory.ItemStack;

public record RecipeEntity(String backpackID, ItemStack[] shape, ItemStack result) {
}
