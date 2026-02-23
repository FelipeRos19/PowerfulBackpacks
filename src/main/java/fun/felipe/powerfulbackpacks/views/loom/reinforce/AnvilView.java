package fun.felipe.powerfulbackpacks.views.loom.reinforce;

import fun.felipe.inventoryCreator.core.models.InventoryModel;
import fun.felipe.powerfulbackpacks.utils.StringUtils;
import org.bukkit.event.inventory.InventoryType;

public class AnvilView extends InventoryModel {

    public AnvilView() {
        super(InventoryType.ANVIL, StringUtils.format("<dark_grey>Reinforced Anvil"));
    }
}
