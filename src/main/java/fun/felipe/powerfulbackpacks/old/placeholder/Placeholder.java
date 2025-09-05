package fun.felipe.powerfulbackpacks.old.placeholder;

import lombok.Getter;

@Getter
public abstract class Placeholder<T> {
    private final T placeholder;

    public Placeholder(T placeholder) {
        this.placeholder = placeholder;
    }
}
