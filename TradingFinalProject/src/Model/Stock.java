package Model;

public abstract class Stock {
    private final String name;

    protected Stock(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return name + ":\n\t" + stringify();
    }

    protected abstract String stringify();
}
