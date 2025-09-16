package fun.felipe.powerfulbackpacks.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;

public class TestCommand implements CommandExecutor {

    public TestCommand(Plugin plugin) {
        PluginCommand command = plugin.getServer().getPluginCommand("teste");
        if (command == null)
            throw new RuntimeException("Error in Backpack Command!");
        command.setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String string, @NotNull String @NotNull [] args) {
        if (!(sender instanceof Player player)) return false;


        return true;
    }
}
