package fun.felipe.powerfulbackpacks.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;

import java.util.ArrayList;
import java.util.List;


public class StringUtils {

    public static Component format(String input) {
        return MiniMessage.miniMessage().deserialize(input);
    }

    public static Component formatItemName(String nameInput) {
        return format(nameInput).decoration(TextDecoration.ITALIC, false);
    }

    public static List<Component> formatItemLore(List<String> itemLore) {
        List<Component> lore = new ArrayList<>();
        for (String line : itemLore) {
            lore.add(formatItemName(line));
        }

        return lore;
    }

    public static String strip(Component component) {
        return MiniMessage.miniMessage().serialize(component);
    }

    public static String getText(Component component) {
        TextComponent text = (TextComponent) component;
        return text.content();
    }
}
