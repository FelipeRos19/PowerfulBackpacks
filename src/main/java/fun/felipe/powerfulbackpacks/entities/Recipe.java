package fun.felipe.powerfulbackpacks.entities;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public record Recipe(List<Material> materials, ItemStack[] shape, ItemStack result) {
}
