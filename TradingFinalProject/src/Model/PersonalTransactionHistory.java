package Model;

public class PersonalTransactionHistory extends TransactionHistory {


    public PersonalTransactionHistory(String customerID, String name) {
        super(customerID, name);
    }

    @Override
    public String historyType(String fileIdentity) {
        return "CustomerPersonalHistory/" + fileIdentity + "_PersonalHistory.txt";
    }

    @Override
    public Transaction getTransaction(String[] parts) {
        String behaviour = parts[0];
        double money = Double.parseDouble(parts[1]);
        return new PersonalTransaction(behaviour, money);
    }
}
