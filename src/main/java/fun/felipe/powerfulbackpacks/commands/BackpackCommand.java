package fun.felipe.powerfulbackpacks.commands;

import fun.felipe.powerfulbackpacks.PowerfulBackpacks;
import fun.felipe.powerfulbackpacks.commands.backpackSubCommands.GiveBackpackSubCommand;
import fun.felipe.powerfulbackpacks.commands.backpackSubCommands.ListBackpackSubCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class BackpackCommand implements CommandExecutor {

    public BackpackCommand(Plugin plugin) {
        PluginCommand command = plugin.getServer().getPluginCommand("backpack");
        if (command == null)
            throw new RuntimeException("Error in Backpack Command!");
        command.setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            PowerfulBackpacks.getInstance().getLogger().warning("Only players can execute this Command!");
            return false;
        }

        if (!player.hasPermission(PowerfulBackpacks.getInstance().getPluginPermission())) {
            player.sendMessage(PowerfulBackpacks.getInstance().getMessageManager().formatCommandMessage("dont_have_permission"));
            return false;
        }

        if (args.length < 1) {
            player.sendMessage(PowerfulBackpacks.getInstance().getMessageManager().formatCommandMessage("backpack_command_usage"));
            return false;
        }

        switch (args[0].toLowerCase()) {
            case "give" -> new GiveBackpackSubCommand().onCommand(player, args);
            case "list" -> new ListBackpackSubCommand().onCommand(player, args);
            case "teste" -> new TestSubCommand().onCommand(player, args);
            default -> player.sendMessage(PowerfulBackpacks.getInstance().getMessageManager().formatCommandMessage("backpack_command_usage"));
        }
        return false;
    }
}
