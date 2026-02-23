package fun.felipe.powerfulbackpacks.messages.placeholder.implementation;

import fun.felipe.powerfulbackpacks.messages.placeholder.Placeholder;

public class MessagePlaceholder extends Placeholder<String> {

    public MessagePlaceholder(String regexKey, String replacement) {
        super(regexKey, replacement);
    }

    @Override
    public String replace(String message) {
        return message.replaceAll(this.getRegexKey(), this.getReplacement());
    }
}
