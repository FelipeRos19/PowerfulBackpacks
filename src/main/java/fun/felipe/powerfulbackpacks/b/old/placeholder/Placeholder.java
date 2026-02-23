package fun.felipe.powerfulbackpacks.b.old.placeholder;


public abstract class Placeholder<T> {
    private final T placeholder;

    public Placeholder(T placeholder) {
        this.placeholder = placeholder;
    }

    public T getPlaceholder() {
        return placeholder;
    }
}
