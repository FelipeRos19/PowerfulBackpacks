package fun.felipe.powerfulbackpacks.messages.placeholder;

public abstract class Placeholder<T> {
    private final String regexKey;
    private final T replacement;

    public Placeholder(String regexKey, T replacement) {
        this.regexKey = regexKey;
        this.replacement = replacement;
    }

    public T getReplacement() {
        return replacement;
    }

    public String getRegexKey() {
        return regexKey;
    }

    public abstract String replace(String message);
}
