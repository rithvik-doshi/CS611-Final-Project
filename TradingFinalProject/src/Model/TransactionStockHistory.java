package Model;

public class TransactionStockHistory extends Transaction{
    private String Behaviour;
    private int quantity;
    private String item_Name;
    private int total_price;

    public TransactionStockHistory(String Behaviour, String item_Name, int quantity,int total_price) {
        super(Behaviour, quantity);
        this.item_Name = item_Name;
        this.total_price = total_price;
    }

    public String getItem_Name() {
        return item_Name;
    }

    public void setItem_Name(String item_Name) {
        this.item_Name = item_Name;
    }

    public int getTotal_price() {
        return total_price;
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }

    public void addStockHistory(String Behaviour, int quantity,String item_Name,int total_price){


    }
}
