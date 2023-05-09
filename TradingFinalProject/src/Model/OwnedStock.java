package Model;

public class OwnedStock extends Stock {
    private int quantity;
    private final double purchasePrice;
    public OwnedStock(String name, int quantity, double purchasePrice) {
        super(name);
        if (quantity < 1) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }
        this.quantity = quantity;
        this.purchasePrice = purchasePrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPurchasePrice(){
        return this.purchasePrice;
    }

    public void addQuantity(int quantity) {
        this.quantity += quantity;
    }

    public void removeQuantity(int quantity) {
//        If customer removes all stocks, need to remove item from trading account
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
