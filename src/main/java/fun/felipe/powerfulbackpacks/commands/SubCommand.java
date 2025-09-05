package fun.felipe.powerfulbackpacks.commands;

import org.bukkit.entity.Player;

public interface SubCommand {
    void onCommand(Player player, String[] args);
}
