package fun.felipe.powerfulbackpacks.commands.backpackSubCommands;

import fun.felipe.powerfulbackpacks.utils.commands.SubCommand;
import org.bukkit.entity.Player;

//TODO: ARRUMAR TUDO!
public class GiveBackpackSubCommand implements SubCommand {

    @Override
    public void onCommand(Player player, String[] args) {
        /*
        if (args.length != 2) {
            player.sendMessage(PowerfulBackpacks.getInstance().getMessagesManager().createMessage("give_command_usage", new MessagePlaceholder("")));
            return;
        }

        ItemStack backpackItemStack = PowerfulBackpacks.getInstance().getCraftManager().getResultByBackpackID(args[1]);

        if (backpackItemStack == null) {
            player.sendMessage(PowerfulBackpacks.getInstance().getMessagesManager().createMessage("not_found", new MessagePlaceholder(args[1])));
            return;
        }

        player.getInventory().addItem(backpackItemStack);
        BackpackEntity backpack = PowerfulBackpacks.getInstance().getCraftManager().getRegisteredBackpacks().get(args[1]);
        player.sendMessage(PowerfulBackpacks.getInstance().getMessagesManager().createMessage("give_command_success", new MessagePlaceholder(backpack.name())));
         */
    }
}
