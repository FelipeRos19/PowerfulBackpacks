package fun.felipe.powerfulbackpacks.managers.messages.placeholder;

import net.kyori.adventure.text.Component;

public abstract class Placeholder<T> {
    private final T placeholder;

    public Placeholder(T placeholder) {
        this.placeholder = placeholder;
    }

    public T getPlaceholder() {
        return placeholder;
    }

    public abstract Component replace();
}
