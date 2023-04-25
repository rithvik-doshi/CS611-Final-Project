package Model;

public class TransactionPersonalHistory extends Transaction {
    private String Behaviour;
    private int quantity;

    public TransactionPersonalHistory(String Behaviour, int quantity) {
        super(Behaviour, quantity);
    }
    // 在 TransactionPersonalHistory 类中添加以下构造函数
    public TransactionPersonalHistory(String textLine) {
        super(textLine.split(",")[0].trim(), Integer.parseInt(textLine.split(",")[1].trim()));
    }


}
