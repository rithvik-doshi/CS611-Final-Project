package Model;

import java.util.ArrayList;

public class Manager extends Person {
    private ArrayList<Request> requestQueue;

    public Manager(int ID, String name, String email, String password) {
        super(ID, name, email, password);
    }
}
