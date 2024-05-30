package fun.felipe.powerfulbackpacks.commands.backpackSubCommands;

import fun.felipe.powerfulbackpacks.PowerfulBackpacks;
import fun.felipe.powerfulbackpacks.entities.BackpackEntity;
import fun.felipe.powerfulbackpacks.interfaces.SubCommand;
import fun.felipe.powerfulbackpacks.placeholder.implementations.MessagePlaceholder;
import fun.felipe.powerfulbackpacks.utils.items.ItemUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GiveBackpackSubCommand implements SubCommand {

    @Override
    public void onCommand(Player player, String[] args) {
        if (args.length != 2) {
            player.sendMessage(PowerfulBackpacks.getInstance().getMessagesManager().createMessage("give_command_usage", new MessagePlaceholder("")));
            return;
        }

        ItemStack backpackItemStack = PowerfulBackpacks.getInstance().getRecipeManager().getBackpackRecipes().get(args[1]).result();

        if (backpackItemStack == null) {
            player.sendMessage(PowerfulBackpacks.getInstance().getMessagesManager().createMessage("not_found", new MessagePlaceholder(args[1])));
            return;
        }

        player.getInventory().addItem(backpackItemStack);
        BackpackEntity backpack = PowerfulBackpacks.getInstance().getRecipeManager().getRegisteredBackpacks().get(args[1]);
        player.sendMessage(PowerfulBackpacks.getInstance().getMessagesManager().createMessage("give_command_success", new MessagePlaceholder(backpack.name())));

        player.getInventory().addItem(ItemUtils.createCustomItem("<grey>Custom Item!", Material.DIAMOND, 1));
    }
}
