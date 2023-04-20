package Model;

public class Account implements usesMoney {
    private double money;

    @Override
    public double getMoney() {
        return money;
    }

    @Override
    public void setMoney(double money) {

    }
}
