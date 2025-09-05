package fun.felipe.powerfulbackpacks.managers.messages.placeholder.implementations;

import fun.felipe.powerfulbackpacks.managers.messages.placeholder.Placeholder;
import fun.felipe.powerfulbackpacks.utils.StringUtils;
import net.kyori.adventure.text.Component;

public class MessagePlaceholder extends Placeholder<String> {

    public MessagePlaceholder(String placeholderType) {
        super(placeholderType);
    }

    @Override
    public Component replace() {
        return StringUtils.format(this.getPlaceholder());
    }
}
