package fun.felipe.powerfulbackpacks.commands;

import fun.felipe.powerfulbackpacks.PowerfulBackpacks;
import fun.felipe.powerfulbackpacks.entities.BackpackEntity;
import fun.felipe.powerfulbackpacks.placeholder.implementations.BackpackPlaceholder;
import fun.felipe.powerfulbackpacks.placeholder.implementations.MessagePlaceholder;
import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class ListBackpackCommand implements CommandExecutor {
    final Plugin plugin;

    public ListBackpackCommand(Plugin plugin) {
        this.plugin = plugin;
        PluginCommand command = this.plugin.getServer().getPluginCommand("listbackpack");
        if (command == null)
            throw new RuntimeException("Error in List Backpack Command!");
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

        Component messageHeader = PowerfulBackpacks.getInstance().getMessagesManager().createMessage("list_command_message", new MessagePlaceholder(""));
        player.sendMessage(messageHeader);

        for (BackpackEntity backpack : PowerfulBackpacks.getInstance().getCraftManager().getRegisteredBackpacks().values()) {
            player.sendMessage(PowerfulBackpacks.getInstance().getMessagesManager().createMessage("list_command_items_message", new BackpackPlaceholder(backpack)));
        }

        return true;
    }
}
