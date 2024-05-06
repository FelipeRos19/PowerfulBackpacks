package fun.felipe.powerfulbackpacks.commands;

import fun.felipe.powerfulbackpacks.PowerfulBackpacks;
import fun.felipe.powerfulbackpacks.entities.BackpackEntity;
import fun.felipe.powerfulbackpacks.placeholder.implementations.MessagePlaceholder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class GiveBackpackCommand implements CommandExecutor {
    final Plugin plugin;

    public GiveBackpackCommand(Plugin plugin) {
        this.plugin = plugin;
        PluginCommand command = this.plugin.getServer().getPluginCommand("givebackpack");
        if (command == null)
            throw new RuntimeException("Error in Give Backpack Command!");
        command.setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String string, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            PowerfulBackpacks.getInstance().getLogger().warning("Only players can execute this Command!");
            return false;
        }

        if (!player.hasPermission(PowerfulBackpacks.getInstance().getPluginPermission())) {
            player.sendMessage(PowerfulBackpacks.getInstance().getMessagesManager().createMessage("dont_have_permission", new MessagePlaceholder("")));
            return false;
        }

        if (args.length != 1) {
            player.sendMessage(PowerfulBackpacks.getInstance().getMessagesManager().createMessage("give_command_usage", new MessagePlaceholder("")));
            return false;
        }

        ItemStack backpackItemStack = PowerfulBackpacks.getInstance().getCraftManager().getResultByBackpackID(args[0]);

        if (backpackItemStack == null) {
            player.sendMessage(PowerfulBackpacks.getInstance().getMessagesManager().createMessage("not_found", new MessagePlaceholder(args[0])));
            return false;
        }

        player.getInventory().addItem(backpackItemStack);

        BackpackEntity backpack = PowerfulBackpacks.getInstance().getCraftManager().getRegisteredBackpacks().get(args[0]);
        player.sendMessage(PowerfulBackpacks.getInstance().getMessagesManager().createMessage("give_command_success", new MessagePlaceholder(backpack.name())));
        return true;
    }
}
