package fun.felipe.powerfulbackpacks.entities;

import org.bukkit.inventory.ItemStack;

public record Recipe(String backpackID, ItemStack[] shape, ItemStack result) {
}
