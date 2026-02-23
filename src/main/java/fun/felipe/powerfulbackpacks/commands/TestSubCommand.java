package fun.felipe.powerfulbackpacks.commands;

import fun.felipe.powerfulbackpacks.utils.commands.SubCommand;
import fun.felipe.powerfulbackpacks.views.loom.LoomView;
import org.bukkit.entity.Player;

public class TestSubCommand implements SubCommand {

    @Override
    public void onCommand(Player player, String[] args) {
        player.sendMessage("tentando abrir menu!");
        new LoomView().openInventory(player);
    }
}
