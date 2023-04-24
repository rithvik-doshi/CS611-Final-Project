package Model;

import java.util.ArrayList;

public class Manager extends Person {
    private ArrayList<Request> requestQueue;

    private ManagerKey key;

    public Manager(int ID, String name, String email, String password) {
        super(ID, name, email, password);
        key = new ManagerKey(ID);
    }

    public String keyValue() {
        return key.getKeyValue();
    }
    public ManagerKey getKey() {
        return key;
    }
}
