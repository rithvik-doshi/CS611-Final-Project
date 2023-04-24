package Model;

public class OwnedStock extends Stock {
    private int quantity;

    public OwnedStock(String name, int quantity) {
        super(name);
        if (quantity < 1) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void addQuantity(int quantity) {
        this.quantity += quantity;
    }

    public void removeQuantity(int quantity) {
        if (this.quantity - quantity < 1) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }
        this.quantity -= quantity;
    }

    @Override
    protected String stringify() {
        return "Quantity: " + quantity;
    }
}
