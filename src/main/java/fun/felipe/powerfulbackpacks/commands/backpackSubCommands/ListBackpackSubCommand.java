package fun.felipe.powerfulbackpacks.commands.backpackSubCommands;

import fun.felipe.powerfulbackpacks.PowerfulBackpacks;
import fun.felipe.powerfulbackpacks.entities.BackpackEntity;
import fun.felipe.powerfulbackpacks.interfaces.SubCommand;
import fun.felipe.powerfulbackpacks.placeholder.implementations.BackpackPlaceholder;
import fun.felipe.powerfulbackpacks.placeholder.implementations.MessagePlaceholder;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

public class ListBackpackSubCommand implements SubCommand {

    @Override
    public void onCommand(Player player, String[] args) {
        if (args.length != 1) {
            player.sendMessage(PowerfulBackpacks.getInstance().getMessagesManager().createMessage("backpack_command_usage", new MessagePlaceholder("")));
            return;
        }

        Component messageHeader = PowerfulBackpacks.getInstance().getMessagesManager().createMessage("list_command_message", new MessagePlaceholder(""));
        player.sendMessage(messageHeader);

        for (BackpackEntity backpack : PowerfulBackpacks.getInstance().getCraftManager().getRegisteredBackpacks().values()) {
            player.sendMessage(PowerfulBackpacks.getInstance().getMessagesManager().createMessage("list_command_items_message", new BackpackPlaceholder(backpack)));
        }
    }
}
