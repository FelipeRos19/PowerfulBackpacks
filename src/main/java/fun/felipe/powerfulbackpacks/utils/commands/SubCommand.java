package fun.felipe.powerfulbackpacks.utils.commands;

import org.bukkit.entity.Player;

public interface SubCommand {
    
    void onCommand(Player player, String[] args);
}
