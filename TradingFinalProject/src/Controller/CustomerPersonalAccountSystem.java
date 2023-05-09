package Controller;

import Model.Customer;

public class CustomerPersonalAccountSystem {

    private final Customer customer;


    public CustomerPersonalAccountSystem(Customer customer){
        this.customer = customer;
    }

    public Customer getCustomer(){
        return customer;
    }


    public double getPersonalAccountBalance(){
        return customer.getPersonalAccount().getBalance();
    }


    public void saveMoney(double money){
        customer.saveMoney(money);
    }

    public boolean withdrawMoney(double money){
        return customer.withDrawMoney(money);}

    public void sendOpenTradingAccountRequest(){
        customer.sendTradingAccountRequest();
    }

    public String getHistory(){
        return customer.getPersonalAccount().getHistory();
    }

}