package Model;

public class TransactionPersonalHistory extends Transaction {
    private String Behaviour;
    private int quantity;

    public TransactionPersonalHistory(String Behaviour, int quantity) {
        super(Behaviour, quantity);
    }
    // add constructor to TransactionPersonalHistory
    public TransactionPersonalHistory(String textLine) {
        super(textLine.split(",")[0].trim(), Integer.parseInt(textLine.split(",")[1].trim()));
    }

}
